(ns masr.specs
  (:require [clojure.spec.alpha            :as      s            ]
            [clojure.set                   :as      set          ]
            [clojure.spec.gen.alpha        :as      gen          ]
            [clojure.test.check.generators :as      tgen         ]
            [clojure.string                :as      str          ]
            [clojure.pprint                :refer   [pprint     ]]
            [masr.logic                    :refer   [iff implies]]
            [masr.utils                    :refer   [plnecho    ]]
            [hyperfiddle.rcf               :refer   [tests tap %]]
            [blaster.clj-fstring           :refer   [f-str]]
            #_[clojure.zip                 :as z                 ]
            ))


;; lightweight, load-time testing:
(hyperfiddle.rcf/enable!)


;; Unmap "Integer" and "Character" so I can use
;; those symbols in ttypes. Access the originals
;; via "java.lang.Integer" "java.lang.Character."
;; Lein test and lein run produce unmaskable
;; warnings.

(ns-unmap *ns* 'Integer)
(ns-unmap *ns* 'Character)


;; ASDL tuples like `(1 2)` are Clojure lists.
;; ASDL lists are ASDL tuples.
;; ASDL vectors like `[expr? stmt*]` are Clojure vectors.
;; ASDL symbol_tables are Clojure maps.


;; In general, these Clojure specs are more
;; discriminating, precise, and detailed than ASDL
;; permits.

;; terms (nodes) in the ASDL grammar (things to the left of equals signs):
;;
;;  1 unit            = TranslationUnit(symbol_table, node*)
;;  2 symbol          = ... many heads ...
;;  3 storage_type    = Default | Save | Parameter | Allocatable
;;  4 access          = Public | Private
;;  5 intent          = Local | In | Out | InOut | ReturnVar | Unspecified
;;  6 deftype         = Implementation | Interface
;;  7 presence        = Required | Optional
;;  8 abi             = Source | LFortranModule | ... | Intrinsic
;; 09 stmt            = ... many heads ...
;; 10 expr            = ... many heads ...
;; 11 ttype           = Integer(int, dimension*) | ... | FunctionType( ... )
;; 12 restriction_arg = RestrictionArg(identifier, symbol)
;; 13 binop           = Add | Sub | ... | BitRShift
;; 14 logicalbinop    = And | Or | Xor | NEqv | Eqv
;; 15 cmpop           = Eq | NotEq | Lt | LtE | Gt | GtE
;; 16 integerboz      = Binary | Hex | Octal
;; 17 arraybound      = LBound | UBound
;; 18 arraystorage    = RowMajor | ColMajor
;; 19 cast_kind       = RealToInteger | IntegerToReal | ... | LogicalToCharacter
;; 20 dimension       = (expr? start, expr? length)
;; 21 alloc_arg       = (expr a, dimension* dims)
;; 22 attribute       = Attribute(identifier name, attribute_arg *args)
;; 23 attribute_arg   = (identifier arg)
;; 24 call_arg        = (expr? value)
;; 25 tbind           = Bind(string lang, string name)
;; 26 array_index     = (expr? left, expr? right, expr? step)
;; 27 do_loop_head    = (expr? v, expr? start, expr? end, expr? increment)
;; 28 case_stmt       = CaseStmt(expr*, stmt*) | CaseStmt_Range( ... )
;; 29 type_stmt       = TypeStmtName(symbol, stmt*) | TypeStmtType(ttype, stmt*)
;; 30 enumtype        = IntegerConsecutiveFromZero | ... | NonInteger


;; terms not specified in ASDL:
;;
;; 31 symbol_table    = a clojure map
;; 32 dimensions      = dimension*, see below


;; term-like things that are not terms:
;;
;;  0 atoms           = int, float, bool, nat, bignat
;;  0 identifier      = specified below


;; ================================================================


;;       _
;;  __ _| |_ ___ _ __  ___
;; / _` |  _/ _ \ '  \(_-<
;; \__,_|\__\___/_|_|_/__/

;; not terms in the grammar:


(s/def ::int   int?)     ;; java.lang.Long
(s/def ::float float?)
(s/def ::bool  boolean?)


;;  _    _      _     _  ___
;; | |__(_)__ _(_)_ _| ||__ \
;; | '_ \ / _` | | ' \  _|/_/
;; |_.__/_\__, |_|_||_\__(_)
;;        |___/

;; not a term; missing from clojure:


;; ================================================================
;; See
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
(def biggest-hex 0x7fffffffffffffff)
(def too-big-int 9223372036854775808)
(def too-big-hex 0x8000000000000000)
(def not-minus-1 0xFFFFFFFFFFFFFFFF)
(def minus-1-int (unchecked-long 0xFFFFFFFFFFFFFFFF))

(tests
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


(s/def ::bignat
  (s/with-gen
    (s/and bigint? #(>= % 0))
    ;; size-bounded-bignat is not public, else I would call it
    (fn [] tgen/size-bounded-bigint)))

(tests
 (s/valid? ::bignat minus-1-int) := false
 (s/valid? ::bignat not-minus-1) := true
 (s/valid? ::bignat too-big-hex) := true
 (s/valid? ::bignat biggest-hex) := false)

#_
(->> ::bignat s/exercise (map second))
;; => (7 13 63 98225932 4572 28 31914670493 80 252 256185)

#_
(gen/sample (s/gen ::bignat) 5)
;; => (0 17 0 225 951132862023730457)


;;  _ _             _
;; (_|_)  _ _  __ _| |_
;;  _ _  | ' \/ _` |  _|
;; (_|_) |_||_\__,_|\__|

;; Recently refactored to exclude bigints for
;; convenience of s/conform in later specs.

;; Allowing bigints causes problems with s/conform
;; (gotta map second over s/conforms with an s/or,
;; and that mapping pollutes syntactic sugar and
;; just doesn't work with gen/generate).

;; (s/def ::nat (s/or :nat-int  nat-int?,
;;                    :bignat  ::bignat))


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(s/def ::nat nat-int?)

(tests
 (nat-int? biggest-int) := true
 (nat-int? too-big-hex) := false)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn nat [it]
  (let [cit (s/conform ::nat it)]
    (if (s/invalid? cit)
      ::invalid-nat
      cit)))

(tests
 (s/valid? ::nat (nat 42))                    := true
 (s/valid? ::nat (nat -42))                   := false
 (s/valid? ::nat (nat 0))                     := true
 (s/valid? ::nat (nat 0xFFFFFFFFFFFFFFFF))    := false
 (s/valid? ::nat (nat -0xFFFFFFFFFFFFFFFF))   := false
 (s/valid?
  ::nat
  (nat (unchecked-long 0xFFFFFFFFFFFFFFFF)))  := false
 (s/valid?
  ::nat
  (nat (unchecked-long -0xFFFFFFFFFFFFFFFF))) := true
 (s/valid? ::nat (nat 0x7FFFFFFFFFFFFFFF))    := true)

#_
(s/exercise ::nat 5)
;; => ([1 1] [0 0] [0 0] [0 0] [4 4])

#_
(gen/sample (s/gen ::nat) 5)
;; => (1 0 240306 4284 0)


;; ================================================================


;;                  _
;;  __ _ ____ _ ___| |_ ___ _ _ _ __
;; / _` (_-< '_|___|  _/ -_) '_| '  \
;; \__,_/__/_|      \__\___|_| |_|_|_|


;; See multi-spec in https://clojure.org/guides/spec
;; and https://clojure.github.io/spec.alpha/clojure.spec.alpha-api.html#clojure.spec.alpha/multi-spec


;; An asr-term is a map containing a ::term keyword,
;; i.e., :masr.specs/term, or ::asr/term if
;; masr.specs is aliased to asr, as in
;; (:use [masr.specs :as asr]) in core_tests.clj.


;; like ::intent, ::symbol, ::expr, ...
(s/def ::term qualified-keyword?)


;; ::term is a fn that picks the multi-spec dispatch value.
(defmulti term ::term)


;; Here is the multi-spec; see below for examples of its usage.
(s/def ::asr-term (s/multi-spec term ::term))


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _
;; | / _` / -_) ' \  _| |  _| / -_) '_|
;; |_\__,_\___|_||_\__|_|_| |_\___|_|


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(let [alpha-re #"[a-zA-Z_]"  ;; "let over lambda."
      alphameric-re #"[a-zA-Z0-9_]*"]

  (def alpha?
    #(re-matches alpha-re %))

  (def alphameric?
    #(re-matches alphameric-re %))

  (defn identifier? [sy]
    (and (symbol? sy)  ;; exclude strings, numbers, quoted numbers
         (let [s (str sy)]
           (and (alpha? (subs s 0 1))
                (alphameric? (subs s 1))))))

  (def identifier-generator
    (tgen/let [c (gen/char-alpha)
               s (gen/string-alphanumeric)]
      (symbol (str c s))))

  (s/def ::identifier
    (s/with-gen
      identifier?
      (fn [] identifier-generator))))  ;; fn wrapping a macro

(tests
 (s/valid? :masr.specs/identifier 'foobar)  := true
 (s/valid? :masr.specs/identifier '_f__547) := true
 (s/valid? :masr.specs/identifier '1234)    := false)

#_
(gen/sample (s/gen :masr.specs/identifier))
;; => (e c Q G Z2qP fXzg1 sRx2J6 YIhKlV k6 f7k1Xl4)
;; => (k hM LV QWC qW0X RGk3u W Kg6X Q2YvFO621 ODUt9)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn identifier [sym]
  (let [csym (s/conform ::identifier sym)]
    (if (s/invalid? csym)
      ::invalid-identifier
      csym)))

(tests
 (identifier 'foo) := 'foo
 (identifier 123)  := ::invalid-identifier)


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _ ___
;; | / _` / -_) ' \  _| |  _| / -_) '_(_-<
;; |_\__,_\___|_||_\__|_|_| |_\___|_| /__/

;; not an ASDL term

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


(s/def ::identifier-set
  (s/coll-of ::identifier
             :min-count MIN-NUMBER-OF-IDENTIFIERS,
             :max-count MAX-NUMBER-OF-IDENTIFIERS,
             :into #{})) ;; empty set

(tests
 (every? set? (gen/sample (s/gen ::identifier-set))) := true)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn identifier-set
  "Remove duplicates, i.e., create a _set_."
  [it] ;; candidate contents
  (if (or (not (coll? it))
          (map? it))
    ::invalid-identifier-set
    (let [idents-coll (map identifier it)
          idents-conf (s/conform ::identifier-set idents-coll)]
      (if (s/invalid? idents-conf)
        ::invalid-identifier-set
        idents-conf))))

(tests
 (s/valid? ::identifier-set #{'a 'b}) := true
 (let [x (identifier-set ['a 'a])]
   (s/valid? ::identifier-set x) := true
   (set?  x)                     := true
   (count x)                     := 1)
 (let [x (identifier-set [])]
   (s/valid? ::identifier-set x) := true
   (set?  x)                     := true
   (count x)                     := 0)
 (let [x (identifier-set ['a '1])]
   (s/valid? ::identifier-set x) := false
   x := ::invalid-identifier-set))


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  i d e n t i f i e r - l i s t
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(s/def ::identifier-list
  (s/coll-of ::identifier
             :min-count MIN-NUMBER-OF-IDENTIFIERS,
             :max-count MAX-NUMBER-OF-IDENTIFIERS,
             :into []))

(tests
 (every? vector? (gen/sample (s/gen ::identifier-list))) := true)


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
    ::invalid-identifier-list
    (let [idents-coll (map identifier it)
          idents-conf (s/conform ::identifier-list idents-coll)]
      (if (s/invalid? idents-conf)
        ::invalid-identifier-list
        idents-conf))))

(tests
 (s/valid? ::identifier-list ['a 'a 'b]) := true
 (let [x (identifier-list ['a 'a])]
   (s/valid? ::identifier-list x) := true
   (vector? x)                    := true
   (count   x)                    := 2)
 (let [x (identifier-list [])]
   (s/valid? ::identifier-list x) := true
   (vector? x)                    := true
   (count   x)                    := 0)
 (let [x (identifier-list ['a '1])]
   (s/valid? ::identifier-list x) := false
   x := ::invalid-identifier-list))


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  i d e n t i f i e r - s u i t
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(s/def ::identifier-suit
  (s/and
   (s/coll-of ::identifier
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
    ::invalid-identifier-suit
    (let [idents-coll (map identifier it)
          idents-conf (s/conform ::identifier-suit idents-coll)]
      (if (s/invalid? idents-conf)
        ::invalid-identifier-suit
        idents-conf))))

(tests
 (let [x (identifier-suit ['a 'a])]
   (s/valid? ::identifier-suit x) := false
   (vector? x)                    := false)
 (let [x (identifier-suit ['a 'b])]
   (s/valid? ::identifier-suit x) := true
   (vector? x)                    := true
   (count   x)                    := 2)
 (let [x (identifier-suit [])]
   (s/valid? ::identifier-suit x) := true
   (vector? x)                    := true
   (count   x)                    := 0)
 (let [x (identifier-suit ['a '1])]
   (s/valid? ::identifier-suit x) := false
   x := ::invalid-identifier-suit))


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_|


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-

;; dimension = (expr? start, expr? length)

;; Case with 1 index is disallowed.
;; https://github.com/rebcabin/masr/issues/5


(def MIN-DIMENSION-COUNT 0)
(def MAX-DIMENSION-COUNT 2)


(s/def ::dimension-content
  (s/and (fn [it] (not (= 1 (count it))))
   (s/coll-of ::nat
              :min-count MIN-DIMENSION-COUNT,
              :max-count MAX-DIMENSION-COUNT,
              :into ())))


(defmethod term ::dimension [_]
  (s/keys :req [::term ::dimension-content]))


(tests
 (s/valid? ::asr-term
           {::term ::dimension
            ::dimension-content [6 60]}) := true
 (s/valid? ::asr-term
           {::term ::dimension
            ::dimension-content [0]})    := false
 (s/valid? ::asr-term
           {::term ::dimension
            ::dimension-content []})     := true)


;; TODO gen/sample for dimension


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn dimension [it] ;; candidate contents
  (if (or (not (coll? it)) (set? it) (map? it))
    ::invalid-dimension
    (let [conf (s/conform ::asr-term
                          {::term ::dimension,
                           ::dimension-content it})]
      (if (s/invalid? conf)
        ::invalid-dimension
        {::term ::dimension,
         ::dimension-content
         (::dimension-content conf)}))))

(tests
 (s/conform ::asr-term
            {::term  ::dimension,
             ::dimension-content '(1 60)}) :=
 (dimension '(1 60))
 (s/valid? ::asr-term (dimension  60))             := false
 (s/valid? ::asr-term (dimension [[]]))            := false
 (s/valid? ::asr-term (dimension 'foobar))         := false
 (s/valid? ::asr-term (dimension ['foobar]))       := false
 ;; Arity throw! (s/valid? ::asr-term (dimension)) := false
 (s/valid? ::asr-term (dimension []))              := true
 (s/valid? ::asr-term (dimension [60]))            := false
 (s/valid? ::asr-term (dimension [0]))             := false
 (s/valid? ::asr-term (dimension '(1 60)))         := true
 (s/valid? ::asr-term (dimension '()))             := true)


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(def MIN-NUMBER-OF-DIMENSIONS 0)  ;; TODO: 1?
(def MAX-NUMBER-OF-DIMENSIONS 9)


(defn term-selector-spec [kwd]
  (s/and ::asr-term
         #(= kwd (::term %))))


(s/def ::dimensions
  (s/coll-of (term-selector-spec ::dimension)
             :min-count MIN-NUMBER-OF-DIMENSIONS,
             :max-count MAX-NUMBER-OF-DIMENSIONS,
             :into []))


(tests (s/valid?
        ::dimensions
        [#:masr.specs{:term :masr.specs/dimension,
                      :dimension-content [1 60]}
         #:masr.specs{:term :masr.specs/dimension,
                      :dimension-content ()}]) := true
       (s/valid?
        ::dimensions
        [{::term ::dimension,
          ::dimension-content [1 60]}
         {::term ::dimension,
          ::dimension-content ()}])            := true)

;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(tests
 (s/valid? ::dimensions [])                        := true
 (let [dims-example [(dimension '(1 60)) (dimension '())]]
   (s/valid? ::dimensions dims-example)  := true
   (s/conform ::dimensions dims-example) :=
   [#:masr.specs{:term :masr.specs/dimension,
                 :dimension-content [1 60]}
    #:masr.specs{:term :masr.specs/dimension,
                 :dimension-content ()}]))


;; TODO
#_
(gen/sample (s/gen ::dimensions) 5)
;; => ([]
;;     [#:masr.specs{:term :masr.specs/dimension,
;;                   :dimension-content (3093 25)}
;;      #:masr.specs{:term :masr.specs/dimension,
;;                   :dimension-content (1790998)}]
;;     []
;;     [#:masr.specs{:term :masr.specs/dimension,
;;                   :dimension-content (0 57)}
;;      #:masr.specs{:term :masr.specs/dimension,
;;                   :dimension-content ()}
;;      #:masr.specs{:term :masr.specs/dimension,
;;                   :dimension-content (0 2051435)}
;;      #:masr.specs{:term :masr.specs/dimension,
;;                   :dimension-content (13 163)}
;;      #:masr.specs{:term :masr.specs/dimension,
;;                   :dimension-content (9 4)}
;;      #:masr.specs{:term :masr.specs/dimension,
;;                   :dimension-content (0 105)}
;;      #:masr.specs{:term :masr.specs/dimension,
;;                   :dimension-content (1 533036)}]
;;     [#:masr.specs{:term :masr.specs/dimension,
;;                   :dimension-content (0)}])


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn dimensions
  [it] ;; candidate contents
  (if (or (not (coll? it))
          (set? it)
          (map? it))
    ::invalid-dimensions
    (let [dims-coll (map dimension it)
          dims-conf (s/conform ::dimensions dims-coll)]
      (if (s/invalid? dims-conf)
        ::invalid-dimensions
        (let [dims-cont (map
                         ::dimension-content
                         dims-conf)]
          (map dimension dims-cont))))))

(tests
 (dimensions [[6 60] []]) :=
 [#:masr.specs{:term :masr.specs/dimension,
               :dimension-content [6 60]}
  #:masr.specs{:term :masr.specs/dimension,
               :dimension-content ()}]
 (dimensions []) := ())


;; ================================================================


;;                        _ _ _
;;  ___ _ _ _  _ _ __ ___| (_) |_____
;; / -_) ' \ || | '  \___| | | / / -_)
;; \___|_||_\_,_|_|_|_|  |_|_|_\_\___|


(defmacro enum-like [term, heads]
  (let [ns "masr.specs"
        tkw (keyword ns (str term))
        tke (keyword ns (str term "-enum"))
        tki (keyword ns (str "invalid-" term))]
    `(do
       (s/def ~tke ~heads)       ;; the set
       (defmethod term ~tkw [_#] ;; the multi-spec
         (s/keys :req [:masr.specs/term ~tke]))
       (defn ~term [it#] ;; the sugar
         (let [st# (s/conform
                    :masr.specs/asr-term
                    {:masr.specs/term ~tkw
                     ~tke it#})
               result# (if (s/invalid? st#) ~tki, st#)]
           result#
           )))))


;;  _     _           _
;; (_)_ _| |_ ___ _ _| |_
;; | | ' \  _/ -_) ' \  _|
;; |_|_||_\__\___|_||_\__|

;; intent = Local | In | Out | InOut | ReturnVar | Unspecified


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m   &   s u g a r
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(enum-like intent #{'Local 'In 'Out 'InOut 'ReturnVar 'Unspecified})


(tests
 (s/valid?  ::intent-enum 'Local) := true
 (s/valid?  ::intent-enum 'fubar) := false
 (s/conform ::intent-enum 'Local) := 'Local
 (intent 'Local)                  :=
 #:masr.specs{:term :masr.specs/intent,
              :intent-enum 'Local}
 (intent 42) := :masr.specs/invalid-intent
 (let [intent-example (intent 'Local)]
   (s/conform ::asr-term intent-example)
                                  := intent-example))


;;     _                             _
;;  __| |_ ___ _ _ __ _ __ _ ___ ___| |_ _  _ _ __  ___
;; (_-<  _/ _ \ '_/ _` / _` / -_)___|  _| || | '_ \/ -_)
;; /__/\__\___/_| \__,_\__, \___|    \__|\_, | .__/\___|
;;                     |___/             |__/|_|

;; storage_type = Default | Save | Parameter | Allocatable


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m   &   s u g a r
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(enum-like storage-type #{'Default, 'Save, 'Parameter, 'Allocatable})

(tests
 (s/valid? ::storage-type-enum 'Default)       := true
 (s/valid? ::storage-type-enum 'foobar)        := false
 (s/valid? ::asr-term
           {::term ::storage-type
            ::storage-type-enum 'Default})     := true
 (s/valid? ::asr-term (storage-type 'Default)) := true
 (s/valid? ::asr-term (storage-type 'foobar))  := false
 (storage-type 'foobar) := ::invalid-storage-type
 (let [st-example (storage-type 'Default)]
   (s/conform ::asr-term st-example)           := st-example))


;;       _    _
;;  __ _| |__(_)
;; / _` | '_ \ |
;; \__,_|_.__/_|


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(def external-abis
  #{'LFortranModule, 'GFortranModule,
    'BindC, 'Interactive, 'Intrinsic})


(def internal-abis #{'Source})


(s/def ::abi-enum (set/union external-abis internal-abis))


(s/def ::abi-external ::bool)


(defmethod term ::abi [_]
  (s/with-gen
    (s/and
     #(iff (= 'Source (::abi-enum %)) (not (::abi-external %)))
     (s/keys :req [::term ::abi-enum ::abi-external]))
    (fn []
      (tgen/one-of
       [(tgen/hash-map
         ::term         (gen/return ::abi)
         ::abi-enum     (s/gen external-abis)
         ::abi-external (gen/return true))
        (tgen/hash-map
         ::term         (gen/return ::abi)
         ::abi-enum     (s/gen internal-abis)
         ::abi-external (gen/return false))] ))))


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn abi
  "Destructure the keyword :external"
  [the-abi-enum,
   & {:keys [external]}]  ;; defaults to nil
  (let [abi_ (s/conform
              ::asr-term
              {::term         ::abi,
               ::abi-enum     the-abi-enum,
               ::abi-external external})]
    (if (s/invalid? abi_)
      ::invalid-abi
      abi_)))


;; TODO simplify sugar to default the bool


(tests
 (s/valid? ::asr-term
           {::term      ::abi
            ::abi-enum 'Source
            ::abi-external false}) := true
 (let [abi-example (abi 'Source :external false)]
   (s/conform ::asr-term abi-example) := abi-example)
 (abi 'Source)                 := ::invalid-abi
 (abi 'Source :external true)  := ::invalid-abi
 (abi 'Soruce :external false) := ::invalid-abi
 (abi 'Source :extrenal false) := ::invalid-abi)


;;  _____ _
;; |_   _| |_ _  _ _ __  ___
;;   | | |  _| || | '_ \/ -_)
;;   |_|  \__|\_, | .__/\___|
;;            |__/|_|


;; kind: The `kind` member selects the kind of a
;; given type. We currently support the following:
;;
;; Integer kinds: 1 (i8), 2 (i16), 4 (i32), 8 (i64)
;; Real kinds: 4 (f32), 8 (f64)
;; Complex kinds: 4 (c32), 8 (c64)
;; Character kinds: 1 (utf8 string)
;; Logical kinds: 1, 2, 4: (boolean represented by
;;     1, 2, 4 bytes; the default kind is 4, just
;;     like the default integer kind, consistent
;;     with Python and Fortran: in Python "Booleans
;;     in Python are implemented as a subclass of
;;     integers", in Fortran the "default logical
;;     kind has the same storage size as the default
;;     integer"; we currently use kind=4 as default
;;     integer, so we also use kind=4 for the
;;     default logical.)


(s/def ::integer-kind   #{1 2 4 8 16})
(s/def ::real-kind      #{4 8})
(s/def ::complex-kind   #{4 8})
(s/def ::logical-kind   #{1 2 4})
(s/def ::character-kind #{1})

(tests (s/valid? ::integer-kind 42) := false)


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  I n t e g e r ,   R e a l ,   C o m p l e x ,   L o g i c a l
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


;; Here are the first four ttypes, which all follow
;; a common pattern captured in macros. There are
;; more that don't follow that pattern.

;; ttype
;;     = Integer(int kind, dimension* dims)
;;     | Real(int kind, dimension* dims)
;;     | Complex(int kind, dimension* dims)
;;     | Logical(int kind, dimension* dims)


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  N e s t e d   m u l t i - s p e c
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


;; All multi-specs begin with
;; ::asr-<the_rest_of_the_name>, as in ::asr-term and
;; ::asr-ttype-head.


;; nested multi-spec
(do (defmulti ttype-head ::ttype-head)
    ;; name of this multi-spec
    ;; \                     /
    ;;  `---------.---------'
    ;;            |
    ;;      .-----^------,
    ;;     /              \
    (s/def ::asr-ttype-head
      (s/multi-spec ttype-head ::ttype-head)))


(defmacro def-ttype-head [it]
  (let [ns     "masr.specs"
        strit  (str it)
        method (keyword ns (str/capitalize strit))
        kind   (keyword ns (str (str/lower-case strit) "-kind"))]
    `(defmethod ttype-head ~method [_#]
        (s/keys :req [::ttype-head ~kind ::dimensions]))))


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(def-ttype-head Integer)
(def-ttype-head Real)
(def-ttype-head Complex)
(def-ttype-head Logical)
(def-ttype-head Character)

(tests
 (s/valid? ::asr-ttype-head
           {::ttype-head ::Integer
            ::integer-kind 42
            ::dimensions []})                           := false
 (s/valid? ::asr-ttype-head
           {::ttype-head ::Integer
            ::integer-kind 4
            ::dimensions []})                           := true
 (s/valid? ::asr-ttype-head
           {::ttype-head ::Integer
            ::integer-kind 4
            ::dimensions (dimensions [[6 60] [1 42]])}) := true
 (let [a {::ttype-head   ::Integer
          ::integer-kind 4
          ::dimensions   (dimensions [[6 60] [1 42]])}]
   (s/conform ::asr-ttype-head a)                       := a)
 (let [a {::ttype-head   ::Real
          ::real-kind    8
          ::dimensions   (dimensions [[6 60] [1 42]])}]
   (s/conform ::asr-ttype-head a)                       := a))


;;; Now, the asr-term defmethod spec for ttype.


(defmethod term ::ttype [_]
  (s/keys :req [::term ::asr-ttype-head]))

(tests
 (s/valid? ::asr-term
           {::term ::ttype,
            ::asr-ttype-head
            {::ttype-head ::Real,
             ::real-kind  4
             ::dimensions []}})       := true
 (s/valid? ::asr-term
           {::term ::ttype,
            ::asr-ttype-head
            {::ttype-head ::Real,
             ::real-kind  2
             ::dimensions []}})       := false)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defmacro def-ttype-and-head [it]
  (let [ns  "masr.specs"
        cap (str/capitalize (str it)) ;; like "Integer"
        scp (symbol cap)              ;; Like Integer
        nym (symbol (str cap "-"))    ;; like Integer-
        tth (keyword ns cap)          ;; like ::Integer
        kdh (keyword ns (str/lower-case (str it "-kind")))
        ;; ... like ::integer-kind
        ivh (keyword ns (str/lower-case (str "invalid-" it)))
        ;; ... like ::invalid-integer
        dfk 4  ;; default kind
        dfd [] ;; default dimensions
        _   (case scp
              Integer true
              Real    true
              Complex true
              Logical true
              (throw (java.lang.IllegalArgumentException.
                      (f-str "Can't define sugar for {cap}."))))]
    `(do
       ;; Define the light-sugar fns Integer-, Real-,
       ;; Complex- Logical-, that require a full
       ;; map of arguments, like
       ;; (Integer- {:kind 4 :dimensions []}
       (defn ~nym ;; like Integer-
         [{kind# :kind, dimensions# :dimensions}]
         (let [cnf# (s/conform
                     ::asr-ttype-head
                     {::ttype-head ~tth,
                      ~kdh         kind#,
                      ::dimensions (dimensions
                                    dimensions#)})]
           (if (s/invalid? cnf#) ~ivh, cnf#)))
       ;; Define the full-sugar fns Integer, Real,
       ;; Complex Logical, Character that require a
       ;; full map of arguments, like
       ;; (Integer 4 []), (Integer 4), (Integer)
       (defn ~scp
         ([kindx# dimsx#] (~nym {:kind kindx# :dimensions dimsx#}))
         ([kindy#]        (~nym {:kind kindy# :dimensions ~dfd}))
         ;; dfk = 4  is the default kinds for
         ;;          Integer, Real, Complex, Logical
         ;; dfd = [] is the default dimensions
         ([]              (~nym {:kind ~dfk   :dimensions ~dfd})))
       )))


(def-ttype-and-head Integer)
(def-ttype-and-head Real)
(def-ttype-and-head Complex)
(def-ttype-and-head Logical)


;; Define the full-sugar fns
;; Integer, Real, Complex, Logical, Character

(tests
 (s/valid? ::asr-ttype-head (Integer 4))    := true
 (s/valid? ::asr-ttype-head (Integer 42))   := false
 (s/valid? ::asr-ttype-head (Integer))      := true
 (s/valid? ::asr-ttype-head (Integer 4 [])) := true
 )

(defn ttype [head]
  {::term ::ttype,
   ::asr-ttype-head head})

(tests
 (s/valid? ::asr-term (ttype (Integer 4)))                  := true
 (s/valid? ::asr-term (ttype (Integer 4 [])))               := true
 (s/valid? ::asr-term (ttype (Integer 4  [[6 60] [1 42]]))) := true
 (s/valid? ::asr-term (ttype (Integer 4 ["foo"])))          := false
 (s/valid? ::asr-term (ttype (Integer 42 [])))              := false
 (s/valid? ::asr-term (ttype (Real  1 [])))                 := false
 (s/valid? ::asr-term (ttype (Logical 8 [])))               := false)

;; ttype
;;     | Character(int kind, int len, expr? len_expr, dimension* dims)
;;     | Set(ttype type)
;;     | List(ttype type)
;;     | Tuple(ttype* type)
;;     | Struct(symbol derived_type, dimension* dims)
;;     | Enum(symbol enum_type, dimension *dims)
;;     | Union(symbol union_type, dimension *dims)
;;     | Class(symbol class_type, dimension* dims)
;;     | Dict(ttype key_type, ttype value_type)
;;     | Pointer(ttype type)
;;     | Const(ttype type)
;;     | CPtr()
;;     | TypeParameter(identifier param, dimension* dims)
;;     | FunctionType(ttype* arg_types, ttype? return_var_type,
;;         abi abi, deftype deftype, string? bindc_name, bool elemental,
;;         bool pure, bool module, bool inline, bool static,
;;         ttype* type_params, symbol* restrictions, bool is_restriction)


;;                _         _  _        _    _
;;  ____  _ _ __ | |__  ___| || |_ __ _| |__| |___
;; (_-< || | '  \| '_ \/ _ \ ||  _/ _` | '_ \ / -_)
;; /__/\_, |_|_|_|_.__/\___/_|_\__\__,_|_.__/_\___|
;;     |__/                 |___|


;; TODO: placeholder
(s/def ::symbol-table map?)



;;  __ _ __ __ ___ ______
;; / _` / _/ _/ -_|_-<_-<
;; \__,_\__\__\___/__/__/


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m   &   s u g a r
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(enum-like access #{'Public 'Private})

(tests
 (let [public (access 'Public)]
   (s/conform ::asr-term public) := public)
 (access 'foobar) := ::invalid-access)


;;  _ __ _ _ ___ ___ ___ _ _  __ ___
;; | '_ \ '_/ -_|_-</ -_) ' \/ _/ -_)
;; | .__/_| \___/__/\___|_||_\__\___|
;; |_|


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m   &   s u g a r
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(enum-like presence #{'Required 'Optional})

(tests
 (let [required (presence 'Required)]
   (s/conform ::asr-term required) := required)
 (presence 'fubar) := ::invalid-presence)


;;           _                   _   _
;; __ ____ _| |_  _ ___ ___ __ _| |_| |_ _ _
;; \ V / _` | | || / -_)___/ _` |  _|  _| '_|
;;  \_/\__,_|_|\_,_\___|   \__,_|\__|\__|_|


(s/def ::value-attr ::bool)


;;               _        _        _    _
;;  ____  _ _ __| |_ __ _| |__ ___(_)__| |
;; (_-< || | '  \  _/ _` | '_ \___| / _` |
;; /__/\_, |_|_|_\__\__,_|_.__/   |_\__,_|
;;     |__/


(s/def ::symtab-id ::nat)


;; __   __        _      _    _
;; \ \ / /_ _ _ _(_)__ _| |__| |___
;;  \ V / _` | '_| / _` | '_ \ / -_)
;;   \_/\__,_|_| |_\__,_|_.__/_\___|

;; | Variable(symbol_table   parent_symtab,   ;; really an integer id
;;            identifier     name,
;;            identifier   * dependencies,    ;; vector of dependency
;;            intent         intent,
;;            expr         ? symbolic_value,  ;; lack specified by nil
;;            expr         ? value,
;;            storage_type   storage,
;;            ttype          type,
;;            abi            abi,
;;            access         access,
;;            presence       presence,
;;            bool           value_attr)

;; (Variable                ;   head, term: symbol
;;  2                       ;   symbol_table    parent-symtab-id
;;                                         ;     TODO: NOT SYMBOL!
;;  x                       ;   identifier      nym
;;  []                      ;   identifier *    dependencies
;;  Local                   ;   intent          intent
;;  ()                      ;   expr ?          symbolic-value
;;  ()                      ;   expr ?          value
;;  Default                 ;   storage_type    storage
;;  (Integer 4 [])          ;   ttype           tipe
;;  Source                  ;   abi             abi
;;  Public                  ;   access          access
;;  Required                ;   presence        presence
;;  .false.)})              ;   bool            value-attr


(s/def ::varnym ::identifier)


;; TODO: check that dependencies are in the named table
(s/def ::dependencies ::identifier-set)


;; TODO: there is ambiguity regarding identifier-sets and lists:
(tests
 (s/valid? ::dependencies   (identifier-list ())) := true
 (s/valid? ::dependencies   (identifier-list ['a 'b 'c])) := true
 (s/valid? ::identifier-set (identifier-list ['a 'a 'c])) := true)


;; TODO placeholder; needs sugar
(s/def ::symbolic-value empty?)


;; TODO placeholder; needs sugar
(s/def ::value empty?)


;; nested multi-spec
(do (defmulti symbol-head ::symbol-head)
    (s/def ::asr-symbol-head
      (s/multi-spec symbol-head ::symbolhead)))


;; Employ the nested multi-spec:
(defmethod term ::symbol [_]
  (s/keys :req [::term ::asr-symbol-head]))


(s/def ::type-declaration
  (s/or :nil nil?
        :id  ::symtab-id))


(defn type-declaration [ptr]
  (let [td (s/conform ::type-declaration ptr)]
    (if (s/invalid? td)
      ::invalid-type-declaration
      ;; Unpack the s/or sigil :nil or :id
      (second td))))


(tests (s/valid? ::type-declaration
                 (type-declaration 'foo42)) := false
       (s/valid? ::type-declaration
                 (type-declaration nil))    := true
       (s/valid? ::type-declaration
                 (type-declaration 42))     := true)


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(defmethod symbol-head ::Variable [_]
  (s/keys :req [::symbol-head
                ::symtab-id        ::varnym          ::dependencies
                ::intent           ::symbolic-value  ::value
                ::storage-type     ::ttype           ::abi
                ::access           ::presence        ::value-attr
                ::type-declaration
                ]))

(let [a-var {::symbol-head      ::Variable

             ::symtab-id        (nat 2)
             ::varnym           (identifier 'x)
             ::dependencies     (identifier-set ())
             ::intent           (intent 'Local)

             ::symbolic-value   () ;; TODO sugar
             ::value            () ;; TODO sugar
             ::storage-type     (storage-type 'Default)
             ::ttype            (ttype (Integer 4 []))

             ::abi              (abi 'Source :external false)
             ::access           (access 'Public)
             ::presence         (presence 'Required)
             ::value-attr       false ;; TODO sugar

             ::type-declaration (type-declaration nil)
             }]
  ;; (pprint (s/conform ::asr-symbol-head a-var))
  ;; (s/explain ::asr-symbol-head a-var)
  (tests
   (s/valid? ::asr-symbol-head a-var) := true
   (s/valid? ::asr-term
             {::term ::symbol
              ::asr-symbol-head a-var}) := true)
  )


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn Variable
  [& {:keys [ ;; required
             symtab-id,
             varnym,
             ttype,
             ;; defaulted
             type-declaration
             dependencies,
             intent,

             symbolic-value,
             value,
             storage-type,

             abi,
             access,
             presence,

             value-attr
             ]
      :or {type-declaration (type-declaration nil)
           dependencies     (identifier-set ())
           intent           (intent 'Local)

           symbolic-value   ()
           value            ()
           storage-type     (storage-type 'Default)

           abi              (abi 'Source :external false)
           access           (access 'Public)
           presence         (presence 'Required)

           value-attr       false
           }}])


;; light sugar
;; (Variable- :varnym     (identifier 'x)
;;            :symtab-id  2
;;            :ttype      (ttype (Integer 4)))


;; (def i32 (ttype (Integer 4 [])))


;; heavy sugar

;; (Variable 'x 2 i32)


;; TODO: Generate ASDL meta from full-form specs.
;; (defn asdl-gen [variable-spec])


;; TODO: integrate with libasr
;; LASR
