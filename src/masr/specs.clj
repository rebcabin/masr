(ns masr.specs
  (:require [clojure.spec.alpha            :as s   ]
            [clojure.set                   :as set ]
            #_[clojure.zip                   :as z   ]
            [clojure.spec.gen.alpha        :as gen ]
            [clojure.test.check.generators :as tgen]
            [masr.specs :as asr]))


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

;; things that are not terms:
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
(s/def :masr.specs/int   int?)
(s/def :masr.specs/float float?)
(s/def :masr.specs/bool  boolean?)


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


;;  _ _   _    _                _
;; (_|_) | |__(_)__ _ _ _  __ _| |_
;;  _ _  | '_ \ / _` | ' \/ _` |  _|
;; (_|_) |_.__/_\__, |_||_\__,_|\__|
;;              |___/

;; not a term


;; Overwrite print-method for clojure BigInt to get rid of
;; the "N" at the end (can't do this inside (-main) lest
;; compile errors).


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


;; C-c C-v C-f C-c e to generate pretty-printed
;; comments. Then stub off the call to save a tiny
;; bit of runtime. Remove the #_ and press C-c C-c
;; in the expression to see results in a CIDER Emacs
;; buffer. We follow this convenience convention
;; frequently in this development section. Comments
;; are cheap.

#_
(->> ::bignat s/exercise (map second))
;; => (7 13 63 98225932 4572 28 31914670493 80 252 256185)


;;  _ _             _
;; (_|_)  _ _  __ _| |_
;;  _ _  | ' \/ _` |  _|
;; (_|_) |_||_\__,_|\__|


;; -+-+-+-+-+-
;;  s p e c s
;; -+-+-+-+-+-


(s/def ::nat (s/or :nat-int  nat-int?,
                   :bignat  ::bignat))


;; -+-+-+-+-+-+-
;;  s y n t a x
;; -+-+-+-+-+-+-


(defn nat [it]
  (let [cit (s/conform ::nat it)]
    (or (s/invalid? cit)
        (second cit))))


#_
(s/exercise ::nat 5)
;; => ([0 [:nat-int 0]]
;;     [4 [:bignat 4]]
;;     [1328 [:bignat 1328]]
;;     [226 [:bignat 226]])
;;     [1 [:bignat 1]])

#_
(gen/sample (s/gen ::bignat) 5)
;; => (0 17 0 225 951132862023730457)

#_
(nat-int? 951132862023730457951132862023730457)
;; => false

#_
(gen/sample (s/gen ::nat) 5)
;; => (1 0 240306 4284 0)

#_
(s/valid? ::nat 42)
;; => true

#_
(s/valid? ::nat 951132862023730457951132862023730457)
;; => true

#_
(not (s/valid? ::nat [:nat-int 42]))
;; => true


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
;; (:use [masr.specs :as asr]).


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


#_
(gen/sample (s/gen :masr.specs/identifier))
;; => (e c Q G Z2qP fXzg1 sRx2J6 YIhKlV k6 f7k1Xl4)
;; => (k hM LV QWC qW0X RGk3u W Kg6X Q2YvFO621 ODUt9)


#_
(s/valid? :masr.specs/identifier 'foobar)
;; => true


;; -+-+-+-+-+-+-
;;  s y n t a x
;; -+-+-+-+-+-+-


(defn identifier [sym]
  (let [csym (s/conform ::identifier sym)]
    (or (s/invalid? csym) csym)))


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


(s/def ::identifier-set
  (s/coll-of ::identifier
             :min-count MIN-NUMBER-OF-IDENTIFIERS,
             :max-count MAX-NUMBER-OF-IDENTIFIERS,
             :into #{}))

#_
(gen/sample (s/gen ::identifier-set) 2)
;; => (#{U p lu qr R F V0 g2 b8 Od T4 YQ d}
;;     #{H t x p u q M v o R A W n m P J T k z E})


(defn identifier-set
  "Remove duplicates, i.e., create a _set_."
  [it]
  (if (or (not (coll? it))
          (map? it))
    false ;; "true" would do just as well.
    (let [idents-coll (map identifier it)
          idents-conf (s/conform ::identifier-set idents-coll)]
      (or (s/invalid? idents-conf)
          idents-conf))))


(s/def ::identifier-list
  (s/coll-of ::identifier
             :min-count MIN-NUMBER-OF-IDENTIFIERS,
             :max-count MAX-NUMBER-OF-IDENTIFIERS,
             :into []))

#_
(s/valid? ::identifier-list [])
;; => true

#_
(s/valid? ::identifier-list ['__foo_bar])
;; => true

#_
(gen/sample (s/gen ::identifier-list) 5)
;; => ([]
;;     [b HM F2c iB c Mf N h4V Wu sK cK dmf N7E j yl e2]
;;     [qN1p Nk O5]
;;     [li O0 y Hg vx Q8Zf5 X09AY t6N Emi0]
;;     [bR V]


(defn identifier-list
  "Create a vector; ordered, duplicates allowed. Some
  Clojure functions will convert the vector to a seq
  or list, it matters not."
  [it]
  (if (or (not (coll? it))
          (map? it))
    false ;; "true" would do just as well.
    (let [idents-coll (map identifier it)
          idents-conf (s/conform ::identifier-list idents-coll)]
      (or (s/invalid? idents-conf)
          idents-conf))))


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


#_
(s/exercise ::dimension 5)
;; => ([(132) ([:bignat 132])]
;;     [(0) ([:nat-int 0])]
;;     [(199) ([:bignat 199])]
;;     [(1 3) ([:nat-int 1] [:nat-int 3])]
;;     [(6 1049) ([:nat-int 6] [:bignat 1049])])

#_
(gen/sample (s/gen ::dimension) 7)
;; => ((1 60)
;;     (1)
;;     (606 66979216746710640882869059905284213752707)
;;     (52862))
;;     (0 0)
;;     ()
;;     (0))


;; -+-+-+-+-+-+-
;;  s y n t a x
;; -+-+-+-+-+-+-


(defn dimension [it]
  (if (or (not (coll? it)) (set? it) (map? it))
    false  ;; "true" would do just as well.
    (let [conf (s/conform ::asr-term
                              {::term ::dimension,
                               ::dimension-content it})]
          (or (s/invalid? conf)
              {::term ::dimension
               ::dimension-content
               (map second (::dimension-content conf))}))))

#_
(= (s/conform ::asr-term {::term ::dimension, ::dimension-content '(1 60)})
   (dimension '(1 60)))
;; => false

#_
(s/valid? ::asr-term (dimension '(1 60)))
;; => true


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/


;; -+-+-+-+-+-
;;  s p e c s
;; -+-+-+-+-+-


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

#_
(s/conform ::dimensions [(dimension '(1 60)) (dimension '())])
;; => [#:masr.specs{:term :masr.specs/dimension,
;;                  :dimension-content
;;                  ([:nat-int 1] [:nat-int 60])}
;;     #:masr.specs{:term :masr.specs/dimension,
;;                  :dimension-content ()}]


(defn dimensions [it]
  (if (or (not (coll? it))
          (set? it)
          (map? it))
    false ;; "true" would do just as well.
    (let [dims-coll (map dimension it)
          dims-conf (s/conform ::dimensions dims-coll)]
      (or (s/invalid? dims-conf)
          (let [dims-cont (map
                           #(map
                             second
                             (::dimension-content %))
                           dims-conf)]
            (map dimension dims-cont))))))


;;  _     _           _
;; (_)_ _| |_ ___ _ _| |_
;; | | ' \  _/ -_) ' \  _|
;; |_|_||_\__\___|_||_\__|

;; This is an example of how to spec an enum.


;; intent = Local | In | Out | InOut | ReturnVar | Unspecified

;; These enum values are also called "heads."
(s/def ::intent-enum #{'Local 'In 'Out 'InOut 'ReturnVar 'Unspecified})


;; {::term ::intent, ::intent-enum 'Local}
(defmethod term ::intent [_]
  (s/keys :req [::term ::intent-enum]))

#_
(s/valid? ::asr-term {::term ::intent, ::intent-enum 'Local})
;; => true


;; An instance written as {::term ::intent, ::intent-enum 'Local}
;; a bit ugly. Let's write


(defn intent [sym]
  (s/conform
   ::asr-term
   {::term ::intent, ::intent-enum sym}))

#_
(intent 'Local)
;; => #:masr.specs{:term :masr.specs/intent, :intent-enum Local}

#_
(intent 42)
;; => :clojure.spec.alpha/invalid


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
    'BindC, 'Interactive, 'Intrisic})


(def internal-abis #{'Source})


(s/def ::abi-enum (set/union external-abis internal-abis))


(s/def ::abi-external ::bool)


(defn iff [a b]
  (or (and a b)
      (not (or a b))))


(defn implies [a b]
  (not (and a (not b)))) ;; same as (or (not a) b)


(defmethod term ::abi [_]
  (s/with-gen
    (s/and
     #(iff (= 'Source (::abi-enum %)) (not (::abi-external %)))
     (s/keys :req [::term ::abi-enum ::abi-external]))
    (fn []
      (tgen/one-of
       [(tgen/hash-map
         ::term         (gen/return ::abi-enum)
         ::abi-enum     (s/gen external-abis)
         ::abi-external (gen/return true))
        (tgen/hash-map
         ::term         (gen/return ::abi-enum)
         ::abi-enum     (s/gen internal-abis)
         ::abi-external (gen/return false))] ))))

#_
(s/def ::tuple-ttype
  (s/with-gen
    (s/cat
     :head  #{'Tuple}
     :type (s/or :empty     empty?
                 :vector-of (s/* ::ttype)))
    (fn []
      (tgen/fmap
       list*
       (tgen/tuple
        (tgen/return 'Tuple)
        (tgen/vector (s/gen ::ttype)))))))

#_
(s/valid? ::asr-term
          {::term      ::abi
           ::abi-enum 'Source
           ::abi-external false})
;; => true


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
