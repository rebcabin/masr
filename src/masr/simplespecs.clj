(ns masr.simplespecs
  (:require
   [masr.logic                    :refer   [iff implies]]
   [masr.utils                    :refer   [plnecho    ]]

   [clojure.spec.alpha            :as      s            ]
   [clojure.set                   :as      set          ]
   [clojure.spec.gen.alpha        :as      gen          ]
   [clojure.test.check.generators :as      tgen         ]
   [clojure.string                :as      str          ]
   [clojure.pprint                :refer   [pprint     ]]

   [hyperfiddle.rcf               :refer   [tests tap %]]
   [blaster.clj-fstring           :refer   [f-str      ]]
   [clojure.walk                  :refer   [prewalk    ]]
   #_[clojure.zip                 :as z                 ]
   ))


;; ================================================================
;;     _  _____ ___  __  __ ____
;;    / \|_   _/ _ \|  \/  / ___|
;;   / _ \ | || | | | |\/| \___ \
;;  / ___ \| || |_| | |  | |___) |
;; /_/   \_\_| \___/|_|  |_|____/

;; not terms in the grammar:


;; Write specs into the masr.specs namespace.


(s/def :masr.specs/int    int?)     ;; java.lang.Long
(s/def :masr.specs/float  float?)
(s/def :masr.specs/bool   boolean?)
(s/def :masr.specs/string string?)


;;  _    _      _     _  ___
;; | |__(_)__ _(_)_ _| ||__ \
;; | '_ \ / _` | | ' \  _|/_/
;; |_.__/_\__, |_|_||_\__(_)
;;        |___/

;; not a term; missing from clojure:


;; ================================================================
;; For quirks on Clojure literals versus Java
;; literals, see
;; https://clojurians.slack.com/archives/C03S1KBA2/p1681690965585429.
;;
;; gist:
;;
;; Without a minus sign, clojure interprets hex
;; literals as non-negative. unchecked-long is
;; the way to sanity.


(tests (unchecked-long 0x8000000000000000)  := -9223372036854775808
       (unchecked-long 0xFFFFFFFFFFFFFFFF)  := -1
       (unchecked-long 0x8000000000000000)  := -0x8000000000000000
       (unchecked-long -0xFFFFFFFFFFFFFFFF) := 1)


(defn bigint?
  "Doesn't seem to be defined in system-supplied libraries."
  [n]
  (instance? clojure.lang.BigInt n))


(def biggest-int 9223372036854775807)
(def biggest-hex 0X7FFFFFFFFFFFFFFF)
(def too-big-int 9223372036854775808)
(def too-big-hex 0x8000000000000000)
(def not-minus-1 0xFFFFFFFFFFFFFFFF)
(def minus-1-int (unchecked-long 0xFFFFFFFFFFFFFFFF))


(tests
 minus-1-int           := -1
 (bigint? too-big-hex) := true
 (bigint? not-minus-1) := true
 (bigint? minus-1-int) := false)


;;  _ _   _    _                _
;; (_|_) | |__(_)__ _ _ _  __ _| |_
;;  _ _  | '_ \ / _` | ' \/ _` |  _|
;; (_|_) |_.__/_\__, |_||_\__,_|\__|
;;              |___/

;; not a term


;; Overwrite print-method for clojure BigInt to get rid of
;; the "N" at the end.


(import '(java.io Writer))


(defmethod print-method clojure.lang.BigInt
  [b, ^Writer w]
  (.write w (str b))
  #_(.write "N"))


;; -+-+-+-+-+-
;;  s p e c s
;; -+-+-+-+-+-


(s/def :masr.specs/bignat
  (s/with-gen
    (s/and bigint? #(>= % 0))
    ;; size-bounded-bignat is not public, else I would call it
    (fn [] tgen/size-bounded-bigint)))


(tests
 (s/valid? :masr.specs/bignat minus-1-int) := false
 (s/valid? :masr.specs/bignat not-minus-1) := true
 (s/valid? :masr.specs/bignat too-big-hex) := true
 (s/valid? :masr.specs/bignat biggest-hex) := false)


;; Map "second" to strip conformed versions from
;; results of s/sexercise:

#_
(->> :masr.specs/bignat s/exercise (map second))
;; => (7 13 63 98225932 4572 28 31914670493 80 252 256185)

#_
(gen/sample (s/gen :masr.specs/bignat) 5)
;; => (0 17 0 225 951132862023730457)


;;  _ _             _
;; (_|_)  _ _  __ _| |_
;;  _ _  | ' \/ _` |  _|
;; (_|_) |_||_\__,_|\__|

;; Recently refactored to exclude bigints for
;; convenience of s/conform in later specs.

;; Allowing bigints causes problems with s/conform
;; (gotta map second over s/conforms with an s/or,
;; and that mapping pollutes sugar functions.

;; Here is the old design:

;; (s/def :masr.specs/nat (s/or :nat-int  nat-int?,
;;                    :bignat  :masr.specs/bignat))


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(s/def :masr.specs/nat nat-int?)


(tests
 (nat-int? biggest-int) := true
 (nat-int? too-big-hex) := false)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn nat [it]
  (let [cit (s/conform :masr.specs/nat it)]
    (if (s/invalid? cit)
      :masr.specs/invalid-nat
      cit)))


(tests
 (s/valid? :masr.specs/nat (nat 42))                  := true
 (s/valid? :masr.specs/nat (nat -42))                 := false
 (s/valid? :masr.specs/nat (nat 0))                   := true
 (s/valid? :masr.specs/nat (nat not-minus-1))         := false
 (s/valid? :masr.specs/nat (nat -0xFFFFFFFFFFFFFFFF)) := false
 (s/valid?
  :masr.specs/nat
  (nat (unchecked-long 0xFFFFFFFFFFFFFFFF)))          := false
 (s/valid?
  :masr.specs/nat
  (nat (unchecked-long -0xFFFFFFFFFFFFFFFF)))         := true
 (s/valid? :masr.specs/nat (nat 0x7FFFFFFFFFFFFFFF))  := true)

#_
(s/exercise :masr.specs/nat 5)
;; => ([1 1] [0 0] [0 0] [0 0] [4 4])

#_
(gen/sample (s/gen :masr.specs/nat) 5)
;; => (1 0 240306 4284 0)


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _
;; | / _` / -_) ' \  _| |  _| / -_) '_|
;; |_\__,_\___|_||_\__|_|_| |_\___|_|

;; not an asr-term


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(let [alpha-re #"[a-zA-Z_]"  ;; "let over lambda."
      alphameric-re #"[a-zA-Z0-9_]*"]

  (def alpha?
    #(re-matches alpha-re %))

  (def alphameric?
    #(re-matches alphameric-re %))

  (defn identifier? [s]
    (and (string? s)  ;; exclude symbols, numbers, quoted numbers
         (not (zero? (count s)))
         (alpha? (subs s 0 1))
         (alphameric? (subs s 1))))

  (def identifier-generator
    (tgen/let [c (gen/char-alpha)
               s (gen/string-alphanumeric)]
      (str c s)))

  (s/def :masr.specs/identifier
    (s/with-gen
      identifier?
      (fn [] identifier-generator))))  ;; fn wrapping a macro


(tests
 (s/valid? :masr.specs/identifier "")        := false
 (s/valid? :masr.specs/identifier "foobar")  := true
 (s/valid? :masr.specs/identifier "_f__547") := true
 (s/valid? :masr.specs/identifier "1234")    := false)

#_
(gen/sample (s/gen :masr.specs/identifier))
;; => (e c Q G Z2qP fXzg1 sRx2J6 YIhKlV k6 f7k1Xl4)
;; => (k hM LV QWC qW0X RGk3u W Kg6X Q2YvFO621 ODUt9)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn identifier [str-]
  (let [cstr- (s/conform :masr.specs/identifier str-)]
    (if (s/invalid? cstr-)
      :masr.specs/invalid-identifier
      cstr-)))


(tests
 (identifier "foo") := "foo"
 (identifier 123)  := :masr.specs/invalid-identifier)


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _ ___
;; | / _` / -_) ' \  _| |  _| / -_) '_(_-<
;; |_\__,_\___|_||_\__|_|_| |_\___|_| /__/

;; not an asr-term

;; for productions like identifier*;
;;
;; there are three kinds https://github.com/rebcabin/masr/issues/1
;; identifier-set  : unordered, no duplicates
;; identifier-list : ordered, duplicates allowed (we use vector)
;; identifier-suit : ordered, duplicates not allowed


(def MIN-NUMBER-OF-IDENTIFIERS  0)
(def MAX-NUMBER-OF-IDENTIFIERS 99)


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  i d e n t i f i e r - s e t
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(s/def :masr.specs/identifier-set
  (s/coll-of :masr.specs/identifier
             :min-count MIN-NUMBER-OF-IDENTIFIERS,
             :max-count MAX-NUMBER-OF-IDENTIFIERS,
             :into #{})) ;; empty set


(tests
 (every?
  set?
  (gen/sample (s/gen :masr.specs/identifier-set))) := true)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn identifier-set
  "Remove duplicates, i.e., create a _set_."
  [it] ;; candidate contents
  (if (or (not (coll? it))
          (map? it))
    :masr.specs/invalid-identifier-set
    (let [idents-coll (map identifier it),
          cnf (s/conform :masr.specs/identifier-set idents-coll)]
      (if (s/invalid? cnf)
        :masr.specs/invalid-identifier-set
        cnf))))


(tests
 (s/valid? :masr.specs/identifier-set #{"a" "b"}) := true
 (let [x (identifier-set ["a" "a"])]
   (s/valid? :masr.specs/identifier-set x)      := true
   (set?  x)                                    := true
   (count x)                                    := 1)
 (let [x (identifier-set [])]
   (s/valid? :masr.specs/identifier-set x)      := true
   (set?  x)                                    := true
   (count x)                                    := 0)
 (let [x (identifier-set ["a" "1"])]
   (s/valid? :masr.specs/identifier-set x)      := false
   x := :masr.specs/invalid-identifier-set))


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  i d e n t i f i e r - l i s t
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(s/def :masr.specs/identifier-list
  (s/coll-of :masr.specs/identifier
             :min-count MIN-NUMBER-OF-IDENTIFIERS,
             :max-count MAX-NUMBER-OF-IDENTIFIERS,
             :into []))


(tests
 (every?
  vector?
  (gen/sample (s/gen :masr.specs/identifier-list))) := true)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn identifier-list
  "Create a vector; ordered, duplicates allowed. Some Clojure
  functions will convert the vector to a seq or list, it matters
  not."
  [it] ;; candidate contents
  (if (or (not (coll? it))
          (map? it))
    :masr.specs/invalid-identifier-list
    (let [idents-coll (map identifier it)
          cnf (s/conform :masr.specs/identifier-list idents-coll)]
      (if (s/invalid? cnf)
        :masr.specs/invalid-identifier-list
        cnf))))


(tests
 (s/valid? :masr.specs/identifier-list ["a" "a" "b"]) := true
 (let [x (identifier-list ["a" "a"])]
   (s/valid? :masr.specs/identifier-list x)        := true
   (vector? x)                                     := true
   (count   x)                                     := 2)
 (let [x (identifier-list [])]
   (s/valid? :masr.specs/identifier-list x)        := true
   (vector? x)                                     := true
   (count   x)                                     := 0)
 (let [x (identifier-list ["a" "1"])]
   (s/valid? :masr.specs/identifier-list x)        := false
   x := :masr.specs/invalid-identifier-list))


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  i d e n t i f i e r - s u i t
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(s/def :masr.specs/identifier-suit
  (s/and
   (s/coll-of :masr.specs/identifier
              :min-count MIN-NUMBER-OF-IDENTIFIERS,
              :max-count MAX-NUMBER-OF-IDENTIFIERS,
              :into [])
   ;; no duplicates
   #(= (count %) (count (set %)))))


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn identifier-suit
  "Create a vector; ordered, NO duplicates allowed. Some Clojure
  functions will convert the vector to a seq or list, it matters
  not."
  [it]
  (if (or (not (coll? it))
          (map? it)
          (not (= (count it) (count (set it)))))
    :masr.specs/invalid-identifier-suit
    (let [idents-coll (map identifier it)
          cnf (s/conform :masr.specs/identifier-suit idents-coll)]
      (if (s/invalid? cnf)
        :masr.specs/invalid-identifier-suit
        cnf))))


(tests
 (let [x (identifier-suit ["a" "a"])]
   (s/valid? :masr.specs/identifier-suit x) := false
   (vector? x)                              := false)
 (let [x (identifier-suit ["a" "b"])]
   (s/valid? :masr.specs/identifier-suit x) := true
   (vector? x)                              := true
   (count   x)                              := 2)
 (let [x (identifier-suit [])]
   (s/valid? :masr.specs/identifier-suit x) := true
   (vector? x)                              := true
   (count   x)                              := 0)
 (let [x (identifier-suit ["a" "1"])]
   (s/valid? :masr.specs/identifier-suit x) := false
   x  := :masr.specs/invalid-identifier-suit))
