(ns masr.specs
  (:require [clojure.spec.alpha            :as s   ]
            [clojure.set                   :as set ]
            #_[clojure.zip                   :as z   ]
            [clojure.spec.gen.alpha        :as gen ]
            [clojure.test.check.generators :as tgen]))


;; ASDL tuples like `(1 2)` are Clojure lists.
;; ASDL lists are ASDL tuples.
;; ASDL vectors like `[expr? stmt*]` are Clojure vectors.
;; ASDL symbol_tables are Clojure maps.


;; In general, these Clojure specs are more
;; discriminating, precise, and detailed than ASDL
;; permits.


;; terms (nodes) in the ASDL grammar:
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
;; 31 identifier      = specified below
;; 32 symbol_table    = a clojure map
;; 33 dimension       = see below
;;  0 atoms           = int, float, bool, nat, bignat


;;       _
;;  __ _| |_ ___ _ __  ___
;; / _` |  _/ _ \ '  \(_-<
;; \__,_|\__\___/_|_|_/__/


;; See "numbers.clj" for big-int, big-nat, and nat.
(s/def :masr.specs/int   int?)
(s/def :masr.specs/float float?)
(s/def :masr.specs/bool  boolean?)


;;  _    _      _     _  ___
;; | |__(_)__ _(_)_ _| ||__ \
;; | '_ \ / _` | | ' \  _|/_/
;; |_.__/_\__, |_|_||_\__(_)
;;        |___/


(defn bigint?
  "Doesn't seem to be defined in system-supplied libraries."
  [n]
  (instance? clojure.lang.BigInt n))


;;  _ _   _    _                _
;; (_|_) | |__(_)__ _ _ _  __ _| |_
;;  _ _  | '_ \ / _` | ' \/ _` |  _|
;; (_|_) |_.__/_\__, |_||_\__,_|\__|
;;              |___/

;; Overwrite print-method for clojure BigInt to get rid of
;; the "N" at the end (can't do this inside (-main) lest
;; compile errors).


(import '(java.io Writer))


(defmethod print-method clojure.lang.BigInt
  [b, ^Writer w]
  (.write w (str b))
  #_(.write "N"))


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


(s/def ::nat (s/or :nat-int  nat-int?,
                   :bignat  ::bignat))

#_
(s/exercise ::nat 5)
;; => ([0 [:nat-int 0]]
;;     [4 [:bignat 4]]
;;     [1328 [:bignat 1328]]
;;     [226 [:bignat 226]])
;;     [1 [:bignat 1]])


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _
;; | / _` / -_) ' \  _| |  _| / -_) '_|
;; |_\__,_\___|_||_\__|_|_| |_\___|_|


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

  (s/def :masr.specs/identifier ;; side effects the spec registry!
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


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/


;; dimension = (expr? start, expr? length)


(def MIN-START 0)  ;; TODO: 1?
(def MAX-START 2)


(s/def ::dimension
  (s/coll-of ::nat
             :min-count MIN-START,
             :max-count MAX-START,
             :into ()))

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


(def MIN-NUMBER-OF-DIMENSIONS 0)  ;; TODO: 1?
(def MAX-NUMBER-OF-DIMENSIONS 9)


(s/def ::dimensions
  (s/coll-of ::dimension
             :min-count 0,
             :max-count MAX-NUMBER-OF-DIMENSIONS,
             :into []))

#_
(-> ::dimensions (s/exercise 5))
;; => ([[() (1 0) (0) ()] [() ([:bignat 1] [:nat-int 0]) ([:nat-int 0]) ()]]
;;     [[(0) (0) (1) (2902780)]
;;      [([:bignat 0]) ([:bignat 0]) ([:nat-int 1]) ([:bignat 2902780])]]
;;     [[() (15) (1 1) (1 264) (1 7) () (0 1) (2 0)]
;;      [()
;;       ([:bignat 15])
;;       ([:nat-int 1] [:nat-int 1])
;;       ([:nat-int 1] [:bignat 264])
;;       ([:nat-int 1] [:bignat 7])
;;       ()
;;       ([:nat-int 0] [:nat-int 1])
;;       ([:nat-int 2] [:nat-int 0])]]
;;     [[(6038406) () () () (7810)] [([:bignat 6038406]) () () () ([:bignat 7810])]]
;;     [[(43876) (6 8) ()] [([:bignat 43876]) ([:nat-int 6] [:nat-int 8]) ()]])

#_
(gen/sample (s/gen ::dimensions) 5)
;; => ([() (0) () (7 0) (0 1) (0 22) (7)]
;;     []
;;     [() (789 2768)]
;;     [(0 221667) (0 1)]
;;     [() (2) (0) (19931) (0 397) (1) (3) () ()])


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


(s/def ::term keyword?)  ;; like ::intent, ::symbol, ::expr, ...


;; ::term is a fn that picks the dispatch value
(defmulti term ::term)


(s/def ::asr-term (s/multi-spec term ::term))


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
