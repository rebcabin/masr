(ns masr.specs
  (:require [clojure.spec.alpha            :as s   ]
            [clojure.set                   :as set ]
            [clojure.spec.gen.alpha        :as gen ]
            [clojure.test.check.generators :as tgen]
            [masr.logic :refer        [iff implies]]
            [masr.utils :refer        [plnecho    ]]
            [hyperfiddle.rcf :refer   [tests tap %]]
            #_[clojure.zip                   :as z   ]
            ))


;; lightweight, load-time testing:
(hyperfiddle.rcf/enable!)


;; Unmap "Integer" so I can use that symbol in
;; ttypes. Access the original
;; via "java.lang.Integer." Lein test and lein run
;; produce unmaskable warnings.

(ns-unmap *ns* 'Integer)


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


;; See "numbers.clj" for big-int, big-nat, and nat.
(s/def ::int   int?)     ;; java.lang.Long
(s/def ::float float?)
(s/def ::bool  boolean?)

;; See the REPL for test results:

(def biggest-int 9223372036854775807)
(def biggest-hex 0x7fffffffffffffff)
(def too-big-int 9223372036854775808)
(def too-big-hex 0x8000000000000000)
(def not-minus-1 0xFFFFFFFFFFFFFFFF)

(tests ;; ignored in prod
 (int? biggest-int)                   := true
 biggest-hex                          := biggest-int
 (try (inc biggest-hex)
      (catch java.lang.ArithmeticException
          e (.getMessage e)))         := "long overflow"
 (int? too-big-int)                   := false
 (int? too-big-hex)                   := false
 (dec  too-big-hex)                   := biggest-int
 ;; Too late! No way back!
 (int? (dec too-big-hex))             := false
 ;; corner case TODO
 (= -1 (dec not-minus-1))             := false
 (= (java.lang.Long. -1) not-minus-1) := false )


;;  _    _      _     _  ___
;; | |__(_)__ _(_)_ _| ||__ \
;; | '_ \ / _` | | ' \  _|/_/
;; |_.__/_\__, |_|_||_\__(_)
;;        |___/

;; not a term; missing from clojure:


(defn bigint?
  "Doesn't seem to be defined in system-supplied libraries."
  [n]
  (instance? clojure.lang.BigInt n))

(tests
 (bigint? too-big-hex) := true
 (bigint? not-minus-1) := true)


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


;; -+-+-+-+-+-
;;  s p e c s
;; -+-+-+-+-+-


(s/def ::nat nat-int?)

(tests
 (nat-int? biggest-int) := true
 (nat-int? too-big-hex) := false)


;; -+-+-+-+-+-+-
;;  s y n t a x
;; -+-+-+-+-+-+-


(defn nat [it]
  (let [cit (s/conform ::nat it)]
    (if (s/invalid? cit)
      ::invalid-nat
      cit)))

(tests
 (s/valid? ::nat 42)          := true
 (s/valid? ::nat -42)         := false
 (s/valid? ::nat 0)           := true
 (s/valid? ::nat too-big-hex) := false
 (s/valid? ::nat biggest-hex) := true)

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


;; -+-+-+-+-+-
;;  s p e c s
;; -+-+-+-+-+-


(let [alpha-re #"[a-zA-Z_]"  ;; "let over lambda."
      alphameric-re #"[a-zA-Z0-9_]*"]

  (def alpha?
    #(re-matches alpha-re %))

  (def alphameric?
    #(re-matches alphameric-re %))

  (defn identifier? [sy]
    (and (symbol? sy)  ;; excludes strings, numbers, quoted numbers
         (let [s (str sy)]
           (and (alpha? (subs s 0 1))
                (alphameric? (subs s 1))))))

  (def identifier-generator
    (tgen/let [c (gen/char-alpha)
               s (gen/string-alphanumeric)]
      (symbol (str c s))))

  (s/def ::identifier ;; side effects the spec registry!
    (s/with-gen
      identifier?
      (fn [] identifier-generator))))  ;; fn wrapping a macro

(tests
 (s/valid? :masr.specs/identifier 'foobar) := true
 (s/valid? :masr.specs/identifier '1234)   := false)

#_
(gen/sample (s/gen :masr.specs/identifier))
;; => (e c Q G Z2qP fXzg1 sRx2J6 YIhKlV k6 f7k1Xl4)
;; => (k hM LV QWC qW0X RGk3u W Kg6X Q2YvFO621 ODUt9)


;; -+-+-+-+-+-+-
;;  s y n t a x
;; -+-+-+-+-+-+-


(defn identifier [sym]
  (let [csym (s/conform ::identifier sym)]
    (if (s/invalid? csym)
      ::invalid-identifier
      csym)))

(tests
 (identifier 123) := ::invalid-identifier)


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _ ___
;; | / _` / -_) ' \  _| |  _| / -_) '_(_-<
;; |_\__,_\___|_||_\__|_|_| |_\___|_| /__/

;; for productions like identifier*;
;; not an ASDL term
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
             :into #{}))

(tests
 (every? set? (gen/sample (s/gen ::identifier-set))) := true)


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
   x                             := ::invalid-identifier-set))


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
   x                              := ::invalid-identifier-list))


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
   x                              := ::invalid-identifier-suit))


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_|


;; -+-+-+-+-+-
;;  s p e c s
;; -+-+-+-+-+-


;; dimension = (expr? start, expr? length)


(def MIN-START 0)  ;; TODO: 1?
(def MAX-START 2)


(s/def ::dimension-content
  (s/coll-of ::nat
             :min-count MIN-START,
             :max-count MAX-START,
             :into ()))


(defmethod term ::dimension [_]
  (s/keys :req [::term ::dimension-content]))


(defn term-selector-spec [kwd]
  (s/and ::asr-term
         #(= kwd (::term %))))


;; TODO gen/sample for dimension


(defn dimension [it]  ;; candidate contents
  (if (or (not (coll? it)) (set? it) (map? it))
    ::invalid-dimension
    (let [conf (s/conform ::asr-term
                          {::term ::dimension,
                           ::dimension-content it})]
      (if (s/invalid? conf)
        ::invalid-dimension
        {::term ::dimension
         ::dimension-content
         (::dimension-content conf)}))))

(tests
 (s/conform ::asr-term
            {::term  ::dimension,
             ::dimension-content '(1 60)}) :=
 (dimension '(1 60))
 (s/valid? ::asr-term (dimension  60))     := false
 (s/valid? ::asr-term (dimension [60]))    := true
 (s/valid? ::asr-term (dimension [0]))     := true
 (s/valid? ::asr-term (dimension []))      := true
 (s/valid? ::asr-term (dimension '(1 60))) := true
 (s/valid? ::asr-term (dimension '()))     := true)


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/


;; -+-+-+-+-+-
;;  s p e c s
;; -+-+-+-+-+-


(def MIN-NUMBER-OF-DIMENSIONS 0)  ;; TODO: 1?
(def MAX-NUMBER-OF-DIMENSIONS 9)


(s/def ::dimensions
  (s/coll-of (term-selector-spec ::dimension)
             :min-count MIN-NUMBER-OF-DIMENSIONS,
             :max-count MAX-NUMBER-OF-DIMENSIONS,
             :into []))

(tests
 (s/valid? ::dimensions
           [(dimension '(1 60)) (dimension '())])  := true
 (s/conform ::dimensions
            [(dimension '(1 60)) (dimension '())]) :=
 [#:masr.specs{:term :masr.specs/dimension,
               :dimension-content [1 60]}
  #:masr.specs{:term :masr.specs/dimension,
               :dimension-content ()}])


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


;; -+-+-+-+-+-+-
;;  s y n t a x
;; -+-+-+-+-+-+-


;; -+-+-+-+-+-+-
;;  s y n t a x
;; -+-+-+-+-+-+-


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
               :dimension-content ()}])


;;  _     _           _
;; (_)_ _| |_ ___ _ _| |_
;; | | ' \  _/ -_) ' \  _|
;; |_|_||_\__\___|_||_\__|

;; This is an example of how to spec an enum.


;; intent = Local | In | Out | InOut | ReturnVar | Unspecified

;; These enum values are also called "heads."
(s/def ::intent-enum #{'Local 'In 'Out 'InOut 'ReturnVar 'Unspecified})

(tests
 (s/valid?  ::intent-enum 'Local) := true
 (s/valid?  ::intent-enum 'fubar) := false
 (s/conform ::intent-enum 'Local) := 'Local)


(defmethod term ::intent [_]
  (s/keys :req [::term ::intent-enum]))

(tests
 (s/valid? ::asr-term
           {::term ::intent,
            ::intent-enum 'Local}) := true)


;; An instance written as {::term ::intent, ::intent-enum 'Local}
;; a bit ugly. Let's write


(defn intent [sym]
  (let [intent_ (s/conform
                 ::asr-term
                 {::term        ::intent,
                  ::intent-enum sym})]
    (if (s/invalid? intent_)
      ::invalid-intent
      intent_)))

(tests
 (intent 'Local) :=
 #:masr.specs{:term :masr.specs/intent,
              :intent-enum 'Local}
 (intent 42) := :masr.specs/invalid-intent)


;;     _                             _
;;  __| |_ ___ _ _ __ _ __ _ ___ ___| |_ _  _ _ __  ___
;; (_-<  _/ _ \ '_/ _` / _` / -_)___|  _| || | '_ \/ -_)
;; /__/\__\___/_| \__,_\__, \___|    \__|\_, | .__/\___|
;;                     |___/             |__/|_|

;; storage_type = Default | Save | Parameter | Allocatable


(s/def ::storage-type-enum #{'Default, 'Save, 'Parameter, 'Allocatable})


(defmethod term ::storage-type [_]
  (s/keys :req [::term ::storage-type-enum]))

#_
(s/valid? ::asr-term
          {::term ::storage-type
           ::storage-type-enum 'Default})
;; => true


;;       _    _
;;  __ _| |__(_)
;; / _` | '_ \ |
;; \__,_|_.__/_|


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

(tests
 (s/valid? ::asr-term
           {::term      ::abi
            ::abi-enum 'Source
            ::abi-external false}) := true)
;; => true


;;  _____ _
;; |_   _| |_ _  _ _ __  ___
;;   | | |  _| || | '_ \/ -_)
;;   |_|  \__|\_, | .__/\___|
;;            |__/|_|


;; kind: The `kind` member selects the kind of a given type. We currently
;; support the following:
;; Integer kinds: 1 (i8), 2 (i16), 4 (i32), 8 (i64)
;; Real kinds: 4 (f32), 8 (f64)
;; Complex kinds: 4 (c32), 8 (c64)
;; Character kinds: 1 (utf8 string)
;; Logical kinds: 1, 2, 4: (boolean represented by 1, 2, 4 bytes; the default
;;     kind is 4, just like the default integer kind, consistent with Python
;;     and Fortran: in Python "Booleans in Python are implemented as a subclass
;;     of integers", in Fortran the "default logical kind has the same storage
;;     size as the default integer"; we currently use kind=4 as default
;;     integer, so we also use kind=4 for the default logical.)

;; ttype
;;     = Integer(int kind, dimension* dims)
;;     | Real(int kind, dimension* dims)
;;     | Complex(int kind, dimension* dims)
;;     | Character(int kind, int len, expr? len_expr, dimension* dims)
;;     | Logical(int kind, dimension* dims)
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


;;; a support spec:


(s/def ::bytes-kind #{1 2 4 8})


;;; Nested multi-spec:


;; Stumble-on the naming convention: multi-specs begin with
;; ::asr-<the_rest_of_the_name>, as in ::asr-term and
;; ::asr-ttype-head.


(do (defmulti ttype-head ::ttype-head)
    (s/def ::asr-ttype-head
      (s/multi-spec ttype-head ::ttype-head)))


(defmacro def-ttype-head [it]
  `(defmethod ttype-head ~it [_#] ;; https://clojurians.slack.com/archives/C03S1KBA2/p1681407527447379
     (s/keys :req [::ttype-head ::bytes-kind ::dimensions])))


(def-ttype-head ::Integer)
(def-ttype-head ::Real)
(def-ttype-head ::Complex)
(def-ttype-head ::Logical)


;;; Now, the asr-term defmethod spec for ttype.


(defmethod term ::ttype [_]
  (s/keys :req [::term ::asr-ttype-head]))


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


;;; Too difficult to make the following into a macro
;;; because of namespace issues, but don't like copy-
;;; paste code like this.


(defn Integer
  [& {:keys [kind, dimensions],
      :or {kind 4, dimensions []}}] ;; defaults
  (let [Integer_ (s/conform
                  ::asr-ttype-head
                  {::ttype-head ::Integer
                   ::bytes-kind kind,
                   ::dimensions dimensions})]
    (if (s/invalid? Integer_)
      ::invalid-Integer
      Integer_)))


(defn Real
  [& {:keys [kind, dimensions],
      :or {kind 4, dimensions []}}] ;; defaults
  (let [Real_ (s/conform
                  ::asr-ttype-head
                  {::ttype-head ::Real
                   ::bytes-kind kind,
                   ::dimensions dimensions})]
    (if (s/invalid? Real_)
      ::invalid-Real
      Real_)))


(defn Complex
  [& {:keys [kind, dimensions],
      :or {kind 4, dimensions []}}] ;; defaults
  (let [Complex_ (s/conform
               ::asr-ttype-head
               {::ttype-head ::Complex
                ::bytes-kind kind,
                ::dimensions dimensions})]
    (if (s/invalid? Complex_)
      ::invalid-Complex
      Complex_)))


(defn Logical
  [& {:keys [kind, dimensions],
      :or {kind 4, dimensions []}}] ;; defaults
  (let [Logical_ (s/conform
               ::asr-ttype-head
               {::ttype-head ::Logical
                ::bytes-kind kind,
                ::dimensions dimensions})]
    (if (s/invalid? Logical_)
      ::invalid-Logical
      Logical_)))


(defn ttype [head]
  {::term ::ttype, ::head head})


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
