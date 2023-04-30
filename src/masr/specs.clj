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
            [blaster.clj-fstring           :refer   [f-str      ]]
            [clojure.walk                  :refer   [prewalk    ]]
            #_[clojure.zip                 :as z                 ]
            )
  (:require [masr.simplespecs
             :refer [nat
                     identifier-list
                     identifier-set
                     identifier-suit
                     identifier]]))


;; lightweight, load-time testing:
(hyperfiddle.rcf/enable!)


;; Unmap "Integer" and "Character" so I can use
;; those symbols in ttypes. Access the originals
;; via "java.lang.Integer" "java.lang.Character."
;; Lein test and lein run produce unmaskable
;; warnings. Access original "deftype"
;; as "clojure.core/deftype".

(ns-unmap *ns* 'Integer)
(ns-unmap *ns* 'Character)
(ns-unmap *ns* 'deftype)


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
;; 19 cast_kind       = RealToInteger | IntegerToReal | ... |
;; 20 dimension       = (expr? start, expr? length)
;; 21 alloc_arg       = (expr a, dimension* dims)
;; 22 attribute       = Attribute(identifier name, attribute_arg *args)
;; 23 attribute_arg   = (identifier arg)
;; 24 call_arg        = (expr? value)
;; 25 tbind           = Bind(string lang, string name)
;; 26 array_index     = (expr? left, expr? right, expr? step)
;; 27 do_loop_head    = (expr? v, expr? start, expr? end, expr? increment)
;; 28 case_stmt       = CaseStmt(expr*, stmt*) | CaseStmt_Range( ... )
;; 29 type_stmt       = TypeStmtName(symbol, stmt*) | ...
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
;;     _    ____  ____     _____ _____ ____  __  __
;;    / \  / ___||  _ \   |_   _| ____|  _ \|  \/  |
;;   / _ \ \___ \| |_) |____| | |  _| | |_) | |\/| |
;;  / ___ \ ___) |  _ <_____| | | |___|  _ <| |  | |
;; /_/   \_\____/|_| \_\    |_| |_____|_| \_\_|  |_|

;;   __      __                      _
;;  / /____ / /__ ___ _______  ___  (_)__  ___ _  ___ ___  ___ _______
;; / __/ -_) / -_|_-</ __/ _ \/ _ \/ / _ \/ _ `/ (_-</ _ \/ -_) __(_-<
;; \__/\__/_/\__/___/\__/\___/ .__/_/_//_/\_, / /___/ .__/\__/\__/___/
;;                          /_/          /___/     /_/


;; A given instance may be an ::asr-term (any term), a
;; ::symbol (a particular term), and
;; a ::Variable (one of the several symbols). These
;; three specs are of increasing precision and
;; discrimination. Both ::symbol, e.g., ::Variable,
;; and ::expr, e.g., ::LogicalBinOp,
;; are ::asr-terms. Both ::Variable and ::Function
;; are ::symbol,


;; -+-+-+-+-+-+-+-+-+-+-
;;  m u l t i - s p e c
;; -+-+-+-+-+-+-+-+-+-+-


;; See multi-spec in https://clojure.org/guides/spec
;; and https://clojure.github.io/spec.alpha/clojure.spec.alpha-api.html#clojure.spec.alpha/multi-spec


;; An asr-term is a map containing a ::term keyword,
;; i.e., :masr.specs/term, or ::asr/term if
;; masr.specs is aliased to asr, as in
;; (:use [masr.specs :as asr]) in core_tests.clj.


;; like ::intent, ::symbol, ::expr, ...
(s/def ::term qualified-keyword?)


;; ::term is a fn that picks the ::term value from a
;; hash-map:
(defmulti term ::term)


;; Here is the multi-spec; see below for examples of
;; its usage.
(s/def ::asr-term (s/multi-spec term ::term))


;;   __                           __  _ __         __
;;  / /____ ______ _    ___ ___  / /_(_) /___ __  / /_____ __ __
;; / __/ -_) __/  ' \  / -_) _ \/ __/ / __/ // / /  '_/ -_) // /
;; \__/\__/_/ /_/_/_/  \__/_//_/\__/_/\__/\_, / /_/\_\\__/\_, /
;;                                       /___/           /___/


;; For recursive conformance in multi-specs.


(defn term-selector-spec [kwd]
  (s/and ::asr-term
         #(= kwd (::term %))))


(defmacro def-term-entity-key
  "Define entity key like ::symbol or ::expr, which
  is an ::asr-term, a top-level production in the
  grammar."
  [term]
  (let [ns "masr.specs"
        tkw (keyword ns (str term))]
    `(s/def ~tkw    ;; like ::dimension
       (term-selector-spec ~tkw))))


;;   __                    __               __
;;  / /____ ______ _  ____/ /  ___ ___ ____/ /
;; / __/ -_) __/  ' \/___/ _ \/ -_) _ `/ _  /
;; \__/\__/_/ /_/_/_/   /_//_/\__/\_,_/\_,_/
;;            __  _ __         __
;;  ___ ___  / /_(_) /___ __  / /_____ __ __
;; / -_) _ \/ __/ / __/ // / /  '_/ -_) // /
;; \__/_//_/\__/_/\__/\_, / /_/\_\\__/\_, /
;;                   /___/           /___/


(defmacro def-term-head--entity-key
  "Define entity key like ::Variable, which is an
   ::asr-symbol-head by the nested head multi-spec.
   From term = symbol and head = Variable
   or   term = stmt   and head = Assignment,
   generate an s/def like

   (s/def ::Variable               ;; head entity key
     (s/and ::asr-term             ;; top multi-spec
       #(= ::Variable              ;; nested tag
           (-> % ::asr-symbol-head ;; nested multi-spec
                 ::symbol-head)))) ;; tag fetcher

   or

   (s/def ::Assignment             ;; head entity key
     (s/and ::asr-term             ;; top multi-spec
       #(= ::Assignment            ;; nested tag
           (-> % ::asr-stmt-head   ;; nested multi-spec
                 ::stmt-head       ;; tag fetcher
  "
  [term, ;; like symbol
   head  ;; like Variable
   ]
  (let [ns "masr.specs"
        trm (keyword ns "term")             ;; like ::term
        art (keyword ns "asr-term")         ;; like ::asr-term
        hkw (keyword ns
             ;; BUG: breaks "LogicalConstant", etc.
             ;; (str/capitalize (str head))
             (str head))                    ;; like ::Variable
        tmh (keyword ns (str term "-head")) ;; like ::symbol-head
        amh (keyword ns                     ;; for the multi-spec
             (str "asr-" term "-head"))     ;; like ::asr-symbol-head
        ]
    `(s/def ~hkw
       (s/and ~art #(= ~hkw (-> % ~amh ~tmh))))))


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_|

;; Dimension is a term. Dimensions [sic] is not
;; a term. Dimensions stands for dimension*.


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


(def-term-entity-key dimension)


(tests
 (s/valid? ::asr-term
           {::term ::dimension
            ::dimension-content [6 60]}) := true
 (s/valid? ::asr-term
           {::term ::dimension
            ::dimension-content [0]})    := false
 (s/valid? ::asr-term
           {::term ::dimension
            ::dimension-content []})     := true

 (s/valid? ::dimension
           {::term ::dimension
            ::dimension-content [6 60]}) := true
 (s/valid? ::dimension
           {::term ::dimension
            ::dimension-content [0]})    := false
 (s/valid? ::dimension
           {::term ::dimension
            ::dimension-content []})     := true)


;; TODO https://github.com/rebcabin/masr/issues/14
#_(gen/sample (s/gen ::dimension) 3)


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
 (s/valid? ::asr-term  (dimension  60))             := false
 (s/valid? ::asr-term  (dimension [[]]))            := false
 (s/valid? ::asr-term  (dimension 'foobar))         := false
 (s/valid? ::asr-term  (dimension ['foobar]))       := false
 ;; Arity throw! (s/valid? ::asr-term  (dimension)) := false
 (s/valid? ::asr-term  (dimension []))              := true
 (s/valid? ::asr-term  (dimension [60]))            := false
 (s/valid? ::asr-term  (dimension [0]))             := false
 (s/valid? ::asr-term  (dimension '(1 60)))         := true
 (s/valid? ::asr-term  (dimension '()))             := true

 (s/valid? ::dimension (dimension  60))             := false
 (s/valid? ::dimension (dimension [[]]))            := false
 (s/valid? ::dimension (dimension 'foobar))         := false
 (s/valid? ::dimension (dimension ['foobar]))       := false
 (s/valid? ::dimension (dimension []))              := true
 (s/valid? ::dimension (dimension [60]))            := false
 (s/valid? ::dimension (dimension [0]))             := false
 (s/valid? ::dimension (dimension '(1 60)))         := true
 (s/valid? ::dimension (dimension '()))             := true)


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/

;; not an asr-term


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(def MIN-NUMBER-OF-DIMENSIONS 0)  ;; TODO: 1?
(def MAX-NUMBER-OF-DIMENSIONS 9)


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
          ::dimension-content ()}])            := true

       (s/valid?
        ::dimensions
        [(dimension [1 60]), (dimension [])])  := true)


(tests
 (s/valid? ::dimensions [])                    := true
 (let [dims-example
       [(dimension '(1 60)) (dimension '())]]

   (s/valid? ::dimensions dims-example)        := true

   (s/conform ::dimensions dims-example)       :=
   dims-example

   (s/conform ::dimensions dims-example)       :=
   [#:masr.specs{:term :masr.specs/dimension,
                 :dimension-content [1 60]}
    #:masr.specs{:term :masr.specs/dimension,
                 :dimension-content ()}]))


;; TODO https://github.com/rebcabin/masr/issues/14
#_(gen/sample (s/gen ::dimensions) 5)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn dimensions
  [it] ;; candidate contents
  (if (or (not (coll? it))
          (set? it)
          (map? it))
    ::invalid-dimensions
    ;; else
    (let [dims-coll (map dimension it)
          dims-conf (s/conform ::dimensions dims-coll)]
      (if (s/invalid? dims-conf)
        ::invalid-dimensions
        (let [dims-cont (map
                         ::dimension-content
                         dims-conf)]
          (map dimension dims-cont))))))


(tests
 (dimensions [[6 60] []])             :=
 [#:masr.specs{:term :masr.specs/dimension,
               :dimension-content [6 60]}
  #:masr.specs{:term :masr.specs/dimension,
               :dimension-content ()}]

 (dimensions [[6 60] []])             :=
 [(dimension [6 60]), (dimension [])]

 (s/conform ::dimensions
            (dimensions [[6 60] []])) :=
 [(dimension [6 60]), (dimension [])]

 (dimensions [])                      := ())


;;               _        _        _    _
;;  ____  _ _ __| |_ __ _| |__ ___(_)__| |
;; (_-< || | '  \  _/ _` | '_ \___| / _` |
;; /__/\_, |_|_|_\__\__,_|_.__/   |_\__,_|
;;     |__/


(s/def ::symtab-id ::nat)


;; sugar
(defn symtab-id [it]
  (let [cnf (s/conform ::symtab-id it)]
    (if (s/invalid? cnf)
      ::invalid-symtab-id
      cnf)))


(tests
 (symtab-id  42)                        := 42
 (symtab-id -42)                        := ::invalid-symtab-id
 (symtab-id 'foo)                       := ::invalid-symtab-id
 (s/conform ::nat 42)                   := 42
 (s/conform ::nat (nat 42))             := 42
 (s/conform ::symtab-id 42)             := 42
 (s/conform ::symtab-id (symtab-id 42)) := 42
 (s/conform ::symtab-id (nat 42))       := 42)


;;                _         _  _        _    _
;;  ____  _ _ __ | |__  ___| || |_ __ _| |__| |___
;; (_-< || | '  \| '_ \/ _ \ ||  _/ _` | '_ \ / -_)
;; /__/\_, |_|_|_|_.__/\___/_|_\__\__,_|_.__/_\___|
;;     |__/                 |___|

;; SymbolTable is an unwritten term.


(s/def ::hash-map map?)


(defmethod term ::SymbolTable [_]
  (s/keys :req [::term
                ::symtab-id
                ::hash-map]))


(def-term-entity-key SymbolTable)


(defn SymbolTable [id, hash-map]
  (let [st {::term      ::SymbolTable
            ::symtab-id id
            ::hash-map  hash-map}]
    (if (s/invalid? st)
      ::invalid-symbol-table
      st)))


(tests
 (s/valid? ::asr-term
           (SymbolTable 42 {:main 'main}))   := true
 (s/valid? ::SymbolTable
           (SymbolTable 42 {:main 'main}))   := true
 (s/valid? ::SymbolTable
           (SymbolTable 'foo {:main 'main})) := false
 (s/valid? ::SymbolTable
           (SymbolTable 42 [:main 'main]))   := false)


;; ================================================================
;;  _     _____ ____    _    ______   __
;; | |   | ____/ ___|  / \  / ___\ \ / /
;; | |   |  _|| |  _  / _ \| |    \ V /
;; | |___| |__| |_| |/ ___ \ |___  | |
;; |_____|_____\____/_/   \_\____| |_|

;; It's difficult to override = on a single
;; expression. That means we must apply "legacy" to
;; a whole expression. That means we might rework
;; heavy sugar through the whole code-base because
;; we must apply "legacy" anyway.
(defn rewrite-=
  [it]
  (prewalk (fn [x] (if (list? x)
                     (replace {'= 'Assignment--} x)
                     x))
           it))


(defmacro legacy
  "DANGER: Call 'eval'."
  [it]
  `(eval (rewrite-= '~it)))


;; ================================================================
;;  _____ _   _ _   _ __  __       _     ___ _  _______
;; | ____| \ | | | | |  \/  |     | |   |_ _| |/ / ____|
;; |  _| |  \| | | | | |\/| |_____| |    | || ' /|  _|
;; | |___| |\  | |_| | |  | |_____| |___ | || . \| |___
;; |_____|_| \_|\___/|_|  |_|     |_____|___|_|\_\_____|

;; Convert a set, heads, of symbols into a multi-spec under
;; ::asr-term, an entity-key spec like "::intent", and a
;; sugar function like intent.


(defmacro symbolate
  "ASDL Back-Channel: create unquoted constants such as
  Local for 'Local."
  [a-set-syms]
  (let [a-set (eval a-set-syms)
        cmds (for [e a-set] (list 'def e `'~e))]
    `(list ~@cmds)))


(defmacro legacicate
  "ASDL Back-Channel: create legacy function-calls
  for each constant, such as Local for (intent 'Local).
  This works only because all heads are unique -- no
  collisions in ASR."
  [term,       ;; like intent
   a-set-syms] ;; like #{Local, In, Out, ,,,}
  (let [a-set (eval a-set-syms)
        cmds  (for [e a-set] (list 'def e `(~term '~e)))]
    `(list ~@cmds)))


(defmacro enum-like [term, heads]
  (let [ns "masr.specs"
        trm (keyword ns "term")     ;; like ::term
        art (keyword ns "asr-term") ;; like ::asr-term
        tkw (keyword ns (str term)) ;; like ::intent
        tke (keyword ns (str term "-enum")) ;; like ::intent-enum
        tki (keyword ns (str "invalid-" term))]
    `(do
       (s/def ~tke ~heads)
       ;; for the multi-spec
       (defmethod term ~tkw [_#]
         (s/keys :req [~trm ~tke]))
       ;; the entity-key spec
       (s/def ~tkw ;; like ::intent
         (s/and ~art ;; like ::asr-term, i.e., the multi-spec
                ;; like the predicate #(= ::intent (::term %))
                (term-selector-spec ~tkw)))
       (defn ~term [it#] ;; the sugar
         (let [cnf# (s/conform ~art
                               {~trm ~tkw
                                ~tke it#})
               result# (if (s/invalid? cnf#) ~tki, cnf#)]
           result#))
       #_(symbolicate ~heads)
       (legacicate ~term ~heads)
       )))


;;  _           _         _ _    _
;; | |___  __ _(_)__ __ _| | |__(_)_ _  ___ _ __
;; | / _ \/ _` | / _/ _` | | '_ \ | ' \/ _ \ '_ \
;; |_\___/\__, |_\__\__,_|_|_.__/_|_||_\___/ .__/
;;        |___/                            |_|
;;
;; consider adding Implies to logicalbinop in ASR #24
;; https://github.com/rebcabin/masr/issues/24


(enum-like logicalbinop #{'And  'Or  'Xor  'NEqv  'Eqv})


;;  __ _ __  _ __  ___ _ __
;; / _| '  \| '_ \/ _ \ '_ \
;; \__|_|_|_| .__/\___/ .__/
;;          |_|       |_|
;;
;; Not every one is applicable to every type, e.g.,
;; what does 'Gt mean for Logicals?


(enum-like cmpop #{'Eq  'NotEq  'Lt  'LtE  'Gt  'GtE })


;;  _     _           _
;; (_)_ _| |_ ___ _ _| |_
;; | | ' \  _/ -_) ' \  _|
;; |_|_||_\__\___|_||_\__|

;; intent = Local | In | Out | InOut | ReturnVar | Unspecified


(enum-like intent #{'Local 'In 'Out 'InOut 'ReturnVar 'Unspecified})


(tests
 (s/valid? ::intent   (intent 'Local)) := true
 (s/valid? ::intent   Local)           := true
 (s/valid? ::intent   (intent Local))  := false
 (s/valid? ::intent   (intent 'local)) := false

 (s/valid? ::asr-term (intent 'Local)) := true
 (s/valid? ::asr-term Local)           := true
 (s/valid? ::asr-term (intent Local))  := false
 (s/valid? ::asr-term (intent 'local)) := false
 )


(tests
 (s/valid?  ::intent-enum 'Local)     := true
 (s/valid?  ::intent-enum 'fubar)     := false
 (s/conform ::intent-enum 'Local)     := 'Local
 (intent 'Local)                      :=
 #:masr.specs{:term :masr.specs/intent,
              :intent-enum 'Local}
 (intent 42)                          := :masr.specs/invalid-intent

 (s/valid?  ::intent (intent 'Local)) := true

 (let [iex (intent 'Local)]
   (s/conform ::asr-term iex)         := iex
   (s/conform ::intent iex)           := iex))

;; ASDL Back-Channel
(tests
 (let [iex Local]
   (s/conform ::asr-term iex)         := iex
   (s/conform ::intent iex)           := iex))


;;     _                             _
;;  __| |_ ___ _ _ __ _ __ _ ___ ___| |_ _  _ _ __  ___
;; (_-<  _/ _ \ '_/ _` / _` / -_)___|  _| || | '_ \/ -_)
;; /__/\__\___/_| \__,_\__, \___|    \__|\_, | .__/\___|
;;                     |___/             |__/|_|

;; storage_type = Default | Save | Parameter | Allocatable


(enum-like storage-type #{'Default, 'Save, 'Parameter, 'Allocatable})


(tests
 (s/valid? ::storage-type-enum 'Default)           := true
 (s/valid? ::storage-type-enum 'foobar)            := false
 (s/valid? ::asr-term
           {::term ::storage-type
            ::storage-type-enum 'Default})         := true
 (s/valid? ::asr-term (storage-type 'Default))     := true
 (s/valid? ::asr-term (storage-type 'foobar))      := false
 (s/valid? ::storage-type
           {::term ::storage-type
            ::storage-type-enum 'Default})         := true
 (s/valid? ::storage-type (storage-type 'Default)) := true
 (s/valid? ::storage-type (storage-type 'foobar))  := false
 (storage-type 'foobar)                            := ::invalid-storage-type
 (let [ste (storage-type 'Default)]
   (s/conform ::storage-type ste)                  := ste
   (s/conform ::asr-term ste)                      := ste))

;; ASDL Back-Channel
(tests
 (let [ste Default]
   (s/conform ::storage-type ste)                  := ste
   (s/conform ::asr-term ste)                      := ste))


;;       _    _
;;  __ _| |__(_)
;; / _` | '_ \ |
;; \__,_|_.__/_|

;; Special case with rich logic.


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


(def-term-entity-key abi)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn abi
  ;; arity 1 --- default "external"
  ([the-enum]
   (abi the-enum
        :external (not (= the-enum 'Source))))
  ;; arity 2 --- invalid
  ([the-enum, crap]
   ::invalid-abi)
  ;; arity 3 --- light sugar
  ([the-enum, ext-kw, the-bool]
   (cond
     (not (= ext-kw :external)) ::invalid-abi
     :else
     (let [abi_ (s/conform
                 ::abi
                 {::term         ::abi,
                  ::abi-enum     the-enum,
                  ::abi-external the-bool})]
       (if (s/invalid? abi_)
         ::invalid-abi
         abi_)))))

;; ASDL Back-Channel
(def LFortranModule (abi 'LFortranModule :external true))
(def GFortranModule (abi 'GFortranModule :external true))
(def BindC          (abi 'BindC          :external true))
(def Interactive    (abi 'Interactive    :external true))
(def Intrinsic      (abi 'Intrinsic      :external true))

(def Source         (abi 'Source         :external false))


(tests
 (s/valid? ::asr-term
           {::term      ::abi
            ::abi-enum 'Source
            ::abi-external false})       := true
 (s/valid? ::abi
           {::term      ::abi
            ::abi-enum 'Source
            ::abi-external false})       := true

 (let [abe (abi 'Source :external false)]
   (s/conform ::abi      abe)            := abe
   (s/conform ::asr-term abe)            := abe
   ;; defaults to correct value
   (abi 'Source)                         := abe
   Source                                := abe
   ;; missing keyword
   (abi 'Source false)                   := ::invalid-abi
   ;; wrong value
   (abi 'Source :external true)          := ::invalid-abi
   ;; misspellings
   (abi 'Soruce :external false)         := ::invalid-abi
   (abi 'Source :extrenal false)         := ::invalid-abi)

 (let [abe (abi 'LFortranModule :external true)]
   (s/conform ::asr-term abe)            := abe
   (s/conform ::abi      abe)            := abe
   ;; defaults to correct value
   (abi 'LFortranModule)                 := abe
   LFortranModule                        := abe
   ;; missing keyword
   (abi 'LFortranModule true)            := ::invalid-abi
   ;; wrong value
   (abi 'LFortranModule :external false) := ::invalid-abi
))

;; ASDL Back-Channel
(tests
 (let [ablf {::term         ::abi
             ::abi-enum     'Source
             ::abi-external false}]
   (s/valid? ::asr-term ablf)          := true
   (s/valid? ::abi ablf)               := true)

 (s/conform ::abi      Source)         := Source
 (s/conform ::asr-term Source)         := Source
 (abi 'Source false)                   := ::invalid-abi
 (abi 'Source :external true)          := ::invalid-abi

 (s/conform ::asr-term LFortranModule) := LFortranModule
 (s/conform ::abi      LFortranModule) := LFortranModule
 (abi 'LFortranModule true)            := ::invalid-abi
 (abi 'LFortranModule :external false) := ::invalid-abi)


;;  __ _ __ __ ___ ______
;; / _` / _/ _/ -_|_-<_-<
;; \__,_\__\__\___/__/__/


(enum-like access #{'Public 'Private})


(tests
 (let [public (access 'Public)]
   (s/conform ::asr-term public) := public
   (s/conform ::access   public) := public)
 (access 'foobar) := ::invalid-access)

;; ASDL Back-Channel
(tests
 (let [public Public]
   (s/conform ::asr-term public) := public
   (s/conform ::access   public) := public))


;;  _ __ _ _ ___ ___ ___ _ _  __ ___
;; | '_ \ '_/ -_|_-</ -_) ' \/ _/ -_)
;; | .__/_| \___/__/\___|_||_\__\___|
;; |_|


(enum-like presence #{'Required 'Optional})


(tests
 (let [required (presence 'Required)]
   (s/conform ::asr-term required) := required
   (s/conform ::presence required) := required)
 (presence 'fubar) := ::invalid-presence)

;; ASDL Back-Channel
(tests
 (let [required Required]
   (s/conform ::asr-term required) := required
   (s/conform ::presence required) := required))


;; ================================================================
;;  _____ _______   ______  _____
;; |_   _|_   _\ \ / /  _ \| ____|
;;   | |   | |  \ V /| |_) |  _|
;;   | |   | |   | | |  __/| |___
;;   |_|   |_|   |_| |_|   |_____|


;;  _   _                    _                _
;; | |_| |_ _  _ _ __  ___  | |_  ___ __ _ __| |
;; |  _|  _| || | '_ \/ -_) | ' \/ -_) _` / _` |
;;  \__|\__|\_, | .__/\___| |_||_\___\__,_\__,_|
;;          |__/|_|
;;              _          _             _ _   _
;;  _ _  ___ __| |_ ___ __| |  _ __ _  _| | |_(_)___ ____ __  ___ __
;; | ' \/ -_|_-<  _/ -_) _` | | '  \ || | |  _| |___(_-< '_ \/ -_) _|
;; |_||_\___/__/\__\___\__,_| |_|_|_\_,_|_|\__|_|   /__/ .__/\___\__|
;;                                                     |_|


;; All multi-spec names in MASR, nested or not,
;; begin with
;; ::asr-<the_rest_of_the_name>, as
;; in ::asr-term (not nested)
;; and ::asr-ttype-head (nested in ttypes).


;; At first, a multi-spec needs a dispatcher,
;; ttype-head in this case:


;; nested multi-spec
(do (defmulti ttype-head ::ttype-head)
    ;;        ----------
    ;; name of this multi-spec
    ;; \                     /
    ;;  `---------.---------'
    ;;            |
    ;;      .-----^------,
    ;;     /              \
    (s/def ::asr-ttype-head
      (s/multi-spec ttype-head ::ttype-head)))


;; Then, we need functions-to-dispatch-to, i.e.,
;; methods or defmethods. The next macro eliminates
;; the repetitive syntax in writing out defmethods
;; for Integer, Real, Complex, Logical, and
;; Character.


;;; Now, the asr-term defmethod spec for ttype.
(defmethod term ::ttype [_]
  (s/keys :req [::term ::asr-ttype-head]))


(def-term-entity-key ttype)


(def MIN-NUMBER-OF-TTYPES    0)
(def MAX-NUMBER-OF-TTYPES 1024)


(s/def ::ttypes (s/coll-of ::ttype
                          :min-count MIN-NUMBER-OF-TTYPES
                          :max-count MAX-NUMBER-OF-TTYPES))


(s/def ::ttypeq (s/coll-of ::ttype
                          :min-count 0
                          :max-count 1))
;; - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


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


;; Here are the first four ttypes, which all follow
;; a common pattern captured in macros. There are
;; more ttypes later that don't follow that pattern.

;; ttype
;;     = Integer(int kind, dimension* dims)
;;     | Real(int kind, dimension* dims)
;;     | Complex(int kind, dimension* dims)
;;     | Logical(int kind, dimension* dims)


(defmacro def-ttype-head
  "Defmethods for defmulti ttype-head, requiring
  entity keywords ::ttype-head and ::dimensions."
  [it]
  (let [ns     "masr.specs"
        ;; Like "integer"
        strit  (str it)
        ;; Like ::Integer
        method (keyword ns (str/capitalize strit))
        ;; Like ::integer-kind
        kind   (keyword ns (str (str/lower-case strit) "-kind"))]
    `(defmethod ttype-head ~method [_#]
       (s/keys :req [::ttype-head ~kind ::dimensions]))))


;;  ___     _                      ___          _
;; |_ _|_ _| |_ ___ __ _ ___ _ _  | _ \___ __ _| |
;;  | || ' \  _/ -_) _` / -_) '_| |   / -_) _` | |
;; |___|_||_\__\___\__, \___|_|   |_|_\___\__,_|_|
;;                 |___/
;;   ___                _           _              _         _
;;  / __|___ _ __  _ __| |_____ __ | |   ___  __ _(_)__ __ _| |
;; | (__/ _ \ '  \| '_ \ / -_) \ / | |__/ _ \/ _` | / _/ _` | |
;;  \___\___/_|_|_| .__/_\___/_\_\ |____\___/\__, |_\__\__,_|_|
;;                |_|                        |___/


(def-ttype-head Integer)
(def-ttype-head Real)
(def-ttype-head Complex)
(def-ttype-head Logical)


;; Character is more rich
;; (def-ttype-head Character)


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-
;;
;; just for the nested multi-spec, ::asr-type-head:
(tests
 (s/valid? ::asr-ttype-head
           {::ttype-head ::Integer
            ::integer-kind 42  ;; wrong kind
            ::dimensions []})                       := false

 (s/valid? ::asr-ttype-head
           {::ttype-head ::Integer
            ::integer-kind 4
            ::dimensions []})                       := true

 (s/valid? ::asr-ttype-head
           {::ttype-head ::Integer
            ::integer-kind 4
            ::dimensions
            (dimensions [[6 60] [1 42]])}) := true

 ;; Check a conformed one.
 (let [a {::ttype-head   ::Integer
          ::integer-kind 4
          ::dimensions
          (dimensions [[6 60] [1 42]])}]
   (s/conform ::asr-ttype-head a)                   := a)

 ;; Check a Real instead of an Integer.
 (let [a {::ttype-head   ::Real
          ::real-kind    8
          ::dimensions
          (dimensions [[6 60] [1 42]])}]
   (s/conform ::asr-ttype-head a)                   := a))

;; full-forms
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
             ::real-kind  2  ;; wrong kind
             ::dimensions []}})       := false)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn ttype [it]
  (let [cnf (s/conform
             ::asr-term
             {::term ::ttype,
              ::asr-ttype-head it})]
    (if (s/invalid? cnf)
      ::invalid-ttype
      cnf)))


(defmacro def-ttype-and-head [it]
  (let [ns  "masr.specs"
        cap (str/capitalize (str it)) ;; like "Integer"
        scp (symbol cap)              ;; Like Integer  (heavy sugar)
        lcp (symbol (str cap "-"))    ;; like Integer- (light sugar)
        tth (keyword ns cap)          ;; like ::Integer
        kdh (keyword ns (str/lower-case (str it "-kind")))
        ;; ... like ::integer-kind
        ivh (keyword ns (str/lower-case
                         (str "invalid-" it "-ttype")))
        ;; ... like ::invalid-integer-ttype
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
       ;; Define the LIGHT-SUGAR fns Integer-,
       ;; Real-, Complex- Logical-, that require a
       ;; full map of arguments, like
       ;; (Integer- {:kind 4 :dimensions []}
       (defn ~lcp ;; like Integer-
         [{kind# :kind, dims# :dimensions}]
         (let [cnf# (s/conform
                     ::asr-ttype-head
                     {::ttype-head ~tth,  ;; like ::Integer
                      ~kdh         kind#, ;; like ::integer-kind
                      ::dimensions (dimensions dims#)})]
           (if (s/invalid? cnf#) ~ivh, (ttype cnf#))))
       ;; Define the HEAVY-SUGAR fns Integer, Real,
       ;; Complex Logical, Character that take
       ;; positional arguments, like
       ;; (Integer 4 []), (Integer 4), (Integer)
       (defn ~scp ;; like Integer
         ;; arity-2
         ([kindx# dimsx#]
          (~lcp {:kind kindx# :dimensions dimsx#}))
         ;; arity-1
         ([kindy#]
          (~lcp {:kind kindy# :dimensions ~dfd}))
         ;; dfk = 4  is the default kinds for
         ;;          Integer, Real, Complex, Logical
         ;; dfd = [] is the default dimensions
         ;; arity-0
         ([]
          (~lcp {:kind ~dfk   :dimensions ~dfd})))
       ;; TODO ? entity keywords for ::Integer, ::Real, etc.
       )))


(def-ttype-and-head Integer)
(def-ttype-and-head Real)
(def-ttype-and-head Complex)
(def-ttype-and-head Logical)


(tests
 (s/valid? ::asr-ttype-head (::asr-ttype-head (Integer 4)))    := true
 (s/valid? ::asr-ttype-head (::asr-ttype-head (Integer 42)))   := false
 (s/valid? ::asr-ttype-head (::asr-ttype-head (Integer)))      := true
 (s/valid? ::asr-ttype-head (::asr-ttype-head (Integer 4 []))) := true)


(tests
 (s/valid? ::asr-term (Integer 4))                  := true
 (s/valid? ::asr-term (Integer 4 []))               := true
 (s/valid? ::asr-term (Integer 4 [[6 60] [1 42]]))  := true
 (s/valid? ::asr-term (Integer 4 ["foo"]))          := false
 (s/valid? ::asr-term (Integer 42 []))              := false
 (s/valid? ::asr-term (Real  1 []))                 := false
 (s/valid? ::asr-term (Logical 8 []))               := false

 (s/valid? ::ttype    (Integer 4))                  := true
 (s/valid? ::ttype    (Integer 4 []))               := true
 (s/valid? ::ttype    (Integer 4 [[6 60] [1 42]]))  := true
 (s/valid? ::ttype    (Integer 4 ["foo"]))          := false
 (s/valid? ::ttype    (Integer 42 []))              := false
 (s/valid? ::ttype    (Real  1 []))                 := false
 (s/valid? ::ttype    (Logical 8 []))               := false

 (s/valid? ::ttype
           (Integer- {:dimensions [], :kind 4}))    := true
 (s/valid? ::ttype
           (Integer- {:kind 4, :dimensions []}))    := true
 )


(def-term-head--entity-key ttype Integer)
(def-term-head--entity-key ttype Real)
(def-term-head--entity-key ttype Complex)
(def-term-head--entity-key ttype Logical)


;;  ___             _   _        _____
;; | __|  _ _ _  __| |_(_)___ _ |_   _|  _ _ __  ___
;; | _| || | ' \/ _|  _| / _ \ ' \| || || | '_ \/ -_)
;; |_| \_,_|_||_\__|\__|_\___/_||_|_| \_, | .__/\___|
;;                                    |__/|_|

;;     | FunctionType(ttype*  arg_types,       ;; rename param-types
;;                    ttype?  return_var_type,
;;                    abi     abi,
;;                    deftype deftype,
;;                    string? bindc_name,
;;                    bool    elemental,
;;                    bool    pure,
;;                    bool    module,
;;                    bool    inline,
;;                    bool    static,
;;                    ttype*  type_params,
;;                    symbol* restrictions,
;;                    bool    is_restriction)


(s/def ::param-types     ::ttypes)
(s/def ::return-var-type ::ttypeq)
;; abi is good enough
(enum-like deftype #{'Implementation, 'Interface})
(s/def ::bindc-name      (s/nilable string?))
(s/def ::elemental       ::bool)
(s/def ::pure            ::bool)
(s/def ::module          ::bool)
(s/def ::inline          ::bool)
(s/def ::static          ::bool)
(s/def ::type-params     ::ttypes)
;; forward reference. Can only test empty
;; restrictions until symbol is properly defined
;; below.
(def-term-entity-key symbol)
(def MIN-NUMBER-OF-SYMBOLS   0)
(def MAX-NUMBER-OF-SYMBOLS 128)
(s/def ::symbols (s/coll-of ::symbol
                            :min-count MIN-NUMBER-OF-SYMBOLS
                            :max-count MAX-NUMBER-OF-SYMBOLS))
(s/def ::symbolq (s/coll-of ::symbol :min-count 0, :max-count 1))
(s/def ::restrictions    ::symbols)
(s/def ::is-restriction  ::bool)

(defmethod ttype-head ::FunctionType [_]
  (s/keys :req [::ttype-head
                ::param-types     ::return-var-type  ::abi
                ::deftype         ::bindc-name       ::elemental
                ::pure            ::module           ::inline
                ::static          ::type-params      ::restrictions
                ::is-restriction
                ]))


(def-term-head--entity-key ttype FunctionType)

;; heavy sugar
(defn FunctionType [param-types-     return-var-type-  abi-
                    deftype-         bindc-name-       elemental-
                    pure-            module-           inline-
                    static-          type-params-      restrictions-
                    is-restriction-  ]
  {::term ::ttype
   ::asr-ttype-head
   {::ttype-head       ::FunctionType

    ::param-types      param-types-
    ::return-var-type  return-var-type-
    ::abi              abi-

    ::deftype          deftype-
    ::bindc-name       (if (empty? bindc-name-) nil bindc-name-)
    ::elemental        elemental-

    ::pure             pure-
    ::module           module-
    ::inline           inline-

    ::static           static-
    ::type-params      type-params-
    ::restrictions     restrictions-

    ::is-restriction   is-restriction-}})


(tests (let [ft (FunctionType
                 [] () Source
                 Implementation () false
                 false false false
                 false [] [] false)]
         (s/valid?  ::asr-term      ft)  := true
         (s/valid?  ::ttype         ft)  := true
         (s/valid?  ::FunctionType  ft)  := true))


;; TODO the rest of the ttypes
;;     >>> Integer, Real, Complex, Logical are already done ...
;;     >>> Here are the rest of the ttypes.
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


;; ================================================================
;;  ____ ___ __  __ ____  _     _____   _____ _____ ____  __  __ ____
;; / ___|_ _|  \/  |  _ \| |   | ____| |_   _| ____|  _ \|  \/  / ___|
;; \___ \| || |\/| | |_) | |   |  _|     | | |  _| | |_) | |\/| \___ \
;;  ___) | || |  | |  __/| |___| |___    | | | |___|  _ <| |  | |___) |
;; |____/___|_|  |_|_|   |_____|_____|   |_| |_____|_| \_\_|  |_|____/

;; The next few are simple terms that do not
;; satisfy ::asr-term because that's really too
;; heavyweight for them. They're just alternative
;; names for atomic types. They're like typedefs.
;; - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


;;           _                   _   _
;; __ ____ _| |_  _ ___ ___ __ _| |_| |_ _ _
;; \ V / _` | | || / -_)___/ _` |  _|  _| '_|
;;  \_/\__,_|_|\_,_\___|   \__,_|\__|\__|_|


(s/def ::value-attr ::bool)

;; sugar
(defn value-attr [it]
  (let [cnf (s/conform ::value-attr it)]
    (if (s/invalid? cnf)
      ::invalid-value-attr
      cnf)))


(tests
 (value-attr true)  := true
 (value-attr false) := false
 (value-attr 'foo)  := ::invalid-value-attr)


;;     _                       _             _
;;  __| |___ _ __  ___ _ _  __| |___ _ _  __(_)___ ___
;; / _` / -_) '_ \/ -_) ' \/ _` / -_) ' \/ _| / -_|_-<
;; \__,_\___| .__/\___|_||_\__,_\___|_||_\__|_\___/__/
;;          |_|


(s/def ::dependencies ::identifier-set)

;; sugar
(defn dependencies [it]
  (let [cnf (s/conform ::dependencies it)]
    (if (s/invalid? cnf)
      ::invalid-dependencies
      cnf)))


(tests (s/conform ::dependencies ())         := #{}
       (s/conform ::dependencies ['a 'b 'c]) := #{'a 'b 'c}
       (s/conform ::dependencies ['a 'a 'c]) := #{'a 'c}
       (dependencies ())                     := #{}
       (dependencies ['a 'b 'c])             := #{'a 'b 'c}
       (dependencies ['a 'a 'c])             := #{'a 'c})

;; TODO: there is ambiguity regarding identifier-sets and lists:
(tests
 (s/valid? ::dependencies   (identifier-list ())) := true
 (s/valid? ::dependencies   (identifier-list ['a 'b 'c])) := true
 (s/valid? ::identifier-set (identifier-list ['a 'a 'c])) := true)


;;  _                       _        _               _   _
;; | |_ _  _ _ __  ___   __| |___ __| |__ _ _ _ __ _| |_(_)___ _ _
;; |  _| || | '_ \/ -_) / _` / -_) _| / _` | '_/ _` |  _| / _ \ ' \
;;  \__|\_, | .__/\___| \__,_\___\__|_\__,_|_| \__,_|\__|_\___/_||_|
;;      |__/|_|


(s/def ::type-declaration
  (s/nilable ::symtab-id))

;; heavy sugar
(defn type-declaration [ptr]
  (let [td (s/conform
            ::type-declaration
            (if (seqable? ptr) (seq ptr) ptr))]
    (if (s/invalid? td)
      ::invalid-type-declaration
      td)))


(tests (s/valid? ::type-declaration
                 (type-declaration 'foo42)) := false
       (s/valid? ::type-declaration
                 (type-declaration nil))    := true
       (s/valid? ::type-declaration
                 (type-declaration ()))     := true
       (s/valid? ::type-declaration
                 (type-declaration []))     := true
       (s/valid? ::type-declaration
                 (type-declaration '(42)))  := false
       (s/valid? ::type-declaration
                 (type-declaration [42]))   := false
       (s/valid? ::type-declaration
                 (type-declaration 42))     := true)


;; __ ____ _ _ _ _ _ _  _ _ __
;; \ V / _` | '_| ' \ || | '  \
;;  \_/\__,_|_| |_||_\_, |_|_|_|
;;                   |__/


(s/def ::varnym ::identifier)

;; sugar
(defn varnym [it]
  (let [cnf (s/conform ::varnym it)]
    (if (s/invalid? cnf)
      ::invalid-varnym
      cnf)))


(tests
 (s/valid? ::varnym 'foo) := true
 (varnym 'foo)            := 'foo
 (varnym "foo")           := ::invalid-varnym)


;; ================================================================
;;  ____  _        _    ____ _____ _   _  ___  _     ____  _____ ____  ____
;; |  _ \| |      / \  / ___| ____| | | |/ _ \| |   |  _ \| ____|  _ \/ ___|
;; | |_) | |     / _ \| |   |  _| | |_| | | | | |   | | | |  _| | |_) \___ \
;; |  __/| |___ / ___ \ |___| |___|  _  | |_| | |___| |_| | |___|  _ < ___) |
;; |_|   |_____/_/   \_\____|_____|_| |_|\___/|_____|____/|_____|_| \_\____/
;; - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


;;                _         _ _               _
;;  ____  _ _ __ | |__  ___| (_)__  __ ____ _| |_  _ ___
;; (_-< || | '  \| '_ \/ _ \ | / _| \ V / _` | | || / -_)
;; /__/\_, |_|_|_|_.__/\___/_|_\__|  \_/\__,_|_|\_,_\___|
;;     |__/


(s/def ::symbolic-value empty?)

;; sugar
(def symbolic-value identity)


;;           _
;; __ ____ _| |_  _ ___
;; \ V / _` | | || / -_)
;;  \_/\__,_|_|\_,_\___|


(s/def ::value empty?)

;; sugar
(def value identity)


;; ================================================================
;;  ______   ____  __ ____   ___  _
;; / ___\ \ / /  \/  | __ ) / _ \| |
;; \___ \\ V /| |\/| |  _ \| | | | |
;;  ___) || | | |  | | |_) | |_| | |___
;; |____/ |_| |_|  |_|____/ \___/|_____|

;;                _         _   _                _
;;  ____  _ _ __ | |__  ___| | | |_  ___ __ _ __| |
;; (_-< || | '  \| '_ \/ _ \ | | ' \/ -_) _` / _` |
;; /__/\_, |_|_|_|_.__/\___/_| |_||_\___\__,_\__,_|
;;     |__/     _          _             _ _   _
;;  _ _  ___ __| |_ ___ __| |  _ __ _  _| | |_(_)___ ____ __  ___ __
;; | ' \/ -_|_-<  _/ -_) _` | | '  \ || | |  _| |___(_-< '_ \/ -_) _|
;; |_||_\___/__/\__\___\__,_| |_|_|_\_,_|_|\__|_|   /__/ .__/\___\__|
;;                                                     |_|


;; nested multi-spec
(do (defmulti symbol-head ::symbol-head)
    (s/def ::asr-symbol-head
      (s/multi-spec symbol-head ::symbol-head)))

;; Employ the nested multi-spec:
(defmethod term ::symbol [_]
  (s/keys :req [::term ::asr-symbol-head]))

;; This is moved above Function type
;; (def-term-entity-key symbol)
;; - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


;; __   __        _      _    _
;; \ \ / /_ _ _ _(_)__ _| |__| |___
;;  \ V / _` | '_| / _` | '_ \ / -_)
;;   \_/\__,_|_| |_\__,_|_.__/_\___|
;;
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
                            ;     TODO: NOT SYMBOL-TABLE!
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


(def-term-head--entity-key symbol Variable)

;; Generates:
#_(s/def ::Variable
  (s/and ::asr-term
         #(= ::Variable (-> % ::asr-symbol-head ::symbol-head))))


;; -+-+-+-+-+-+-+-+-+-+-+-
;;  l i g h t   s u g a r
;; -+-+-+-+-+-+-+-+-+-+-+-


(defn Variable-
  [& {:keys [ ;; required
             symtab-id,          varnym,         ttype,
             ;; defaulted
             type-declaration,   dependencies,   intent,
             symbolic-value,     value,          storage-type,
             abi,                access,         presence,
             value-attr
             ]
      :or {type-declaration (type-declaration nil)
           dependencies     (dependencies ())
           intent           (intent 'Local)

           symbolic-value   ()
           value            ()
           storage-type     (storage-type 'Default)

           abi              (abi 'Source :external false)
           access           (access 'Public)
           presence         (presence 'Required)
           value-attr       (value-attr false)}}]
  (let [a (s/conform
           ::asr-term
           {::term              ::symbol,
            ::asr-symbol-head
            {::symbol-head      ::Variable,

             ::symtab-id        symtab-id,
             ::varnym           varnym,
             ::ttype            ttype,

             ::type-declaration type-declaration,
             ::dependencies     dependencies,
             ::intent           intent,

             ::symbolic-value   symbolic-value,
             ::value            value,
             ::storage-type     storage-type,

             ::abi              abi,
             ::access           access,
             ::presence         presence,

             ::value-attr       value-attr,
             }})]
    (if (s/invalid? a)
      ::invalid-variable
      a)))

;; Test full-form:
(let [a-var-head {::symbol-head      ::Variable

                  ::symtab-id        (nat 2)
                  ::varnym           (varnym 'x)
                  ::ttype            (Integer 4 [])

                  ::type-declaration (type-declaration nil)
                  ::dependencies     (identifier-set ())
                  ::intent           (intent 'Local)

                  ::symbolic-value   () ;; TODO sugar
                  ::value            () ;; TODO sugar
                  ::storage-type     (storage-type 'Default)

                  ::abi              (abi 'Source :external false)
                  ::access           (access 'Public)
                  ::presence         (presence 'Required)
                  ::value-attr       false ;; TODO sugar
                  }
      ;; nested
      a-var {::term ::symbol
             ::asr-symbol-head a-var-head}
      a-var-light (Variable- :varnym     (identifier 'x)
                             :symtab-id  2
                             :ttype      (Integer 4))
      avl-2  (Variable- :varnym     (identifier 'x)
                        :symtab-id  2
                        :ttype      (Integer 42))]
  (tests
   a-var-light := (s/conform ::asr-term a-var)
   a-var-light := (s/conform ::Variable a-var)

   (s/valid? ::asr-symbol-head a-var-head) := true

   (s/valid? ::asr-term a-var)             := true
   (s/valid? ::asr-term a-var-light)       := true
   (s/valid? ::asr-term avl-2)             := false

   (s/valid? ::symbol   a-var)             := true
   (s/valid? ::symbol   a-var-light)       := true
   (s/valid? ::symbol   avl-2)             := false

   (s/valid? ::Variable a-var)             := true
   (s/valid? ::Variable a-var-light)       := true
   (s/valid? ::Variable avl-2)             := false
   ))

;; Test light sugar:
(tests
 ;; fully spec'ced, order does not matter
 (let [a-valid
       (Variable- :symtab-id        2
                  :varnym           'x
                  :intent           Local ;; ASDL back-channel

                  :ttype            (Integer)
                  :access           Private
                  :presence         Required

                  :abi              Source
                  :type-declaration nil
                  :value-attr       false

                  :symbolic-value   []
                  :value            []
                  :storage-type     Default

                  :dependencies     ['y 'z]
                  )]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::Variable a-valid) := true))


(tests
 (let [a-valid
       (Variable- :symtab-id 2,
                  :varnym    'x,
                  :intent    (intent 'Local) ;; explicit
                  :ttype     (Integer 4 [[1 42]]))]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true)
 (let [a-valid
       (Variable- :symtab-id 2,
                  :varnym    'x,
                  :intent    Local ;; ASDL back-channel
                  :ttype     (Integer 4 [[1 42]]))]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true)
 (let [a-valid ;; default intent
       (Variable- :symtab-id 2,
                  :varnym    'x,
                  :ttype     (Integer 4 [[1 42]]))]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true)
 (let [a-valid
       (Variable- :symtab-id (symtab-id 2),
                  :varnym    (varnym 'x),
                  :ttype     (Integer 4 [[1 42]]))]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true)
 (let [a-valid
       (Variable- :symtab-id 2,
                  :varnym    'x,
                  :ttype     (Integer 4 [[1 42]]))]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true)
 (let [a-valid
       (Variable- :symtab-id 2,
                  :varnym    'x,
                  :ttype     (Integer 4 [[1 42]])
                  ;; explicit abi
                  :abi       (abi 'Source :external false))]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true)
 (let [a-valid
       (Variable- :symtab-id 2,
                  :varnym    'x,
                  :ttype     (Integer 4 [[1 42]])
                  ;; explicit defaulted abi
                  :abi       (abi 'Source))]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true) ;; invalid examples
 (let [a-valid
       (Variable- :symtab-id 2,
                  :varnym    'x,
                  :ttype     (Integer 4 [[1 42]])
                  ;; explicit ASDL back-channel abi
                  :abi       Source)]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true)
 (let [a-valid
       (Variable- :symtab-id 2,
                  :varnym    'x,
                  :ttype     (Integer)
                  :abi       Source)]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true)
 (let [a-inval
       (Variable- :symtab-id 2,
                  :varnym    'x,
                  :ttype     (Integer 4 [[1 42]])
                  ;; wrong abi
                  :abi       (abi 'Source :external true))]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 )


;; -+-+-+-+-+-+-+-+-+-+-+-
;;  h e a v y   s u g a r
;; -+-+-+-+-+-+-+-+-+-+-+-


(defn Variable--
  "Heavy sugar; parameters that collide with functions
  have trailing hyphens."
  [symtab-id-,         varnym-,        ttype-,
   type-declaration-,  dependencies-,  intent-,
   symbolic-value-,    value-,         storage-type-,
   abi-,               access-,        presence-,
   value-attr-]
  (let [cnf (s/conform
             ::asr-term
             {::term              ::symbol,
              ::asr-symbol-head
              {::symbol-head      ::Variable,

               ::symtab-id        (symtab-id        symtab-id-),
               ::varnym           (varnym           varnym-),
               ::ttype            ttype-, ;; already wrapped!

               ::type-declaration (type-declaration type-declaration-),
               ::dependencies     (dependencies     dependencies-),
               ::intent           (if (symbol? intent-)
                                      (intent  intent-)
                                      intent-),

               ::symbolic-value   (symbolic-value   symbolic-value-),
               ::value            (value            value-),
               ::storage-type     (if (symbol? storage-type-)
                                    (storage-type storage-type-)
                                    storage-type-),

               ::abi              (if (symbol? abi-)
                                    (abi abi-)
                                    abi-),
               ::access           (if (symbol? access-)
                                    (access access-)
                                    access-),
               ::presence         (if (symbol? presence-)
                                    (presence presence-)
                                    presence-),

               ::value-attr       (value-attr       value-attr-),
               }})]
    (if (s/invalid? cnf)
      ::invalid-variable
      cnf)))

;; Test heavy sugar:
(tests
 ;; valid examples
  (let [a-valid (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::Variable a-valid) := true)

 ;; invalid examples
 ;; Show that every entity key is checked.
 (let [a-inval (Variable-- "foo" 'x (Integer 4) ;; bad symtab-id
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 "foo" (Integer 4) ;; bad varnym
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 42424242) ;; bad ttupe
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 42424242)
                         'FOOBAR [] 'Local ;; bad dependencies
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'FOOBAR ;; bad intent
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil ['x 'y "foo"] 'Local ;; bad dependencies
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'FOOBAR ;; bad storage-type
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'Default
                         'FOOBAR 'Public 'Required ;; bad abi
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'Default
                         'Source 'FOOBAR 'Required ;; bad access
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'FOOBAR ;; bad presence
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'Required
                         'FOOBAR)] ;; bad value-attr
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::Variable a-inval) := false))


(tests
 ;; valid examples
  (let [a-valid (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true)

 ;; invalid examples
 ;; Show that every entity key is checked.
 (let [a-inval (Variable-- "foo" 'x (Integer 4) ;; bad symtab-id
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 "foo" (Integer 4) ;; bad varnym
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 42424242) ;; bad ttupe
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 42424242)
                         'FOOBAR [] 'Local ;; bad dependencies
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'FOOBAR ;; bad intent
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil ['x 'y "foo"] 'Local ;; bad dependencies
                         [] []  'Default
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'FOOBAR ;; bad storage-type
                         'Source 'Public 'Required
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'Default
                         'FOOBAR 'Public 'Required ;; bad abi
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'Default
                         'Source 'FOOBAR 'Required ;; bad access
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'FOOBAR ;; bad presence
                         false)]
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false)
 (let [a-inval (Variable-- 2 'x (Integer 4)
                         nil [] 'Local
                         [] []  'Default
                         'Source 'Public 'Required
                         'FOOBAR)] ;; bad value-attr
   (s/valid? ::asr-term a-inval) := false
   (s/valid? ::symbol   a-inval) := false
   (s/valid? ::Variable a-inval) := false))

;; ASDL Back-Channel
(tests
 ;; valid examples
 (let [a-valid (Variable-- 2 'x (Integer 4)
                           nil [] Local
                           [] []  Default
                           Source Public Required
                           false)]
   (s/valid? ::asr-term a-valid) := true
   (s/valid? ::symbol   a-valid) := true
   (s/valid? ::Variable a-valid) := true))


;; -+-+-+-+-+-+-+-+-+-+-+-+-
;;  l e g a c y   m a c r o
;; -+-+-+-+-+-+-+-+-+-+-+-+-

;; It's a judgment call whether to introduce a
;; legacy macro for a term or head. Legacy macros
;; track the "current" state of ASR as it evolves
;; toward the ideal of MASR.
;;
;; When a legacy macro exists, the heavy-sugar
;; function will be named with two trailing hyphens,
;; as in Variable--. Light-sugar functions will
;; continue to have names with a single trailing
;; hyphen, as in Variable-, and full form will
;; continue to represent what we ultimately want.


(defmacro Variable
  "Honor legacy parameter order of
  lpython/src/libasr/ASR.asdl as of 25 April 2023.
  Quote the varnym and pass along all other params."
  [symtab-id-,     varnym-,          dependencies-,
   intent-,        symbolic-value-,  value-,
   storage-type-,  ttype-,           abi-,
   access-,        presence-,        value-attr-]
  `(Variable-- ;; heavy sugar
    ~symtab-id-
    '~varnym-  ;; notice the tick mark
    ~ttype-    ;; moved up from between storage type and abi
    (type-declaration nil) ;; legacy doesn't have this
    ~dependencies-
    ~intent-
    ~symbolic-value-
    ~value-
    ~storage-type-
    ;; ttype goes here in legacy
    ~abi-
    ~access-
    ~presence-
    ~value-attr-))

;; Test legacy macro:
(tests
 (let [v (Variable
          2 a []
          Local () ()
          Default (Logical 4 []) Source
          Public Required false)]
   ;; using "legacicated" symbols:
   v := (Variable-- 2 'a (Logical 4)
                    nil [] Local
                    [] [] Default
                    Source Public Required
                    false)
   ;; using tick marks (quoted symbolic constants)
   v := (Variable-- 2 'a (Logical 4)
                    nil [] 'Local
                    [] [] 'Default
                    'Source 'Public 'Required
                    false)
   ;; using telescoping specs
   (s/valid? :masr.specs/Variable v) := true
   (s/valid? :masr.specs/symbol   v) := true
   (s/valid? :masr.specs/asr-term v) := true))

;; Test SymbolTable with Variable:
(tests
 (let [st (SymbolTable
           2 {:a
              (Variable
               2 a [] Local
               () () Default (Logical 4 [])
               Source Public Required false),
              :b
              (Variable
               2 b [] Local
               () () Default (Logical 4 [])
               Source Public Required false)})]
   (s/valid? :masr.specs/SymbolTable st) := true
   ;; A SymbolTable is not a symbol:
   (s/valid? :masr.specs/symbol      st) := false
   (s/valid? :masr.specs/asr-term    st) := true))


;; ================================================================
;;  _______  ______  ____
;; | ____\ \/ /  _ \|  _ \
;; |  _|  \  /| |_) | |_) |
;; | |___ /  \|  __/|  _ <
;; |_____/_/\_\_|   |_| \_\


;; nested multi-spec
(do (defmulti expr-head ::expr-head)
    (s/def ::asr-expr-head
      (s/multi-spec expr-head ::expr-head)))

;; Employ the nested multi-spec:
(defmethod term ::expr [_]
  (s/keys :req [::term ::asr-expr-head]))


(def-term-entity-key expr)


(def MIN-NUMBER-OF-EXPRS    0)
(def MAX-NUMBER-OF-EXPRS 1024)


(s/def ::exprs (s/coll-of ::expr
                          :min-count MIN-NUMBER-OF-EXPRS
                          :max-count MAX-NUMBER-OF-EXPRS))


(s/def ::exprq (s/coll-of ::expr
                          :min-count 0
                          :max-count 1))
;; - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


;; To add a new head to term expr, create a
;; defmethod and a term-head entity key according to
;; the patterns obvious in the examples below. Also
;; add sugar functions like LogicalConstant-,
;; LogicalConstant--, LogicalConstant, to taste.


;;  _              _         _  ___             _            _
;; | |   ___  __ _(_)__ __ _| |/ __|___ _ _  __| |_ __ _ _ _| |_
;; | |__/ _ \/ _` | / _/ _` | | (__/ _ \ ' \(_-<  _/ _` | ' \  _|
;; |____\___/\__, |_\__\__,_|_|\___\___/_||_/__/\__\__,_|_||_\__|
;;           |___/

;; (LogicalConstant true (Logical 4 []))


(defmethod expr-head ::LogicalConstant [_]
  (s/keys :req [::expr-head
                ::bool
                ::Logical]))


(def-term-head--entity-key expr LogicalConstant)

;; heavy sugar
(defn LogicalConstant
  ;; arity-2
  ([a-bool, a-ttype]
   {::term ::expr,
    ::asr-expr-head
    {::expr-head ::LogicalConstant
     ::bool      a-bool
     ::Logical   a-ttype}})
  ;; arity-1
  ([a-bool]
   (LogicalConstant a-bool (Logical))))

;; valid
(tests
 (let [alv {::term ::expr,
            ::asr-expr-head
            {::expr-head ::LogicalConstant
             ::bool      true
             ::Logical   (Logical)}}]
   ;; telescoping specs
   (s/valid? ::asr-term        alv) := true
   (s/valid? ::expr            alv) := true
   (s/valid? ::LogicalConstant alv) := true

   (alv := (LogicalConstant true))
   (alv := (LogicalConstant true (Logical 4 [])))
   (alv := (LogicalConstant true (Logical 4)))
   (alv := (LogicalConstant true (Logical)))
   ))

;; invalid
(tests
 (let [alv {::term ::expr,
            ::asr-expr-head
            {::expr-head ::LogicalConstant
             ::bool      true
             ::Logical   (Integer)}}]
   (s/valid? ::expr     alv)        := false
   (s/valid? ::LogicalConstant alv) := false
   (s/valid? ::asr-term alv)        := false))


;; __   __
;; \ \ / /_ _ _ _
;;  \ V / _` | '_|
;;   \_/\__,_|_|

;;
;; Workaround; See Issue #23
;;
;; Is the parameter symbol for Var really a symbol?
;; Or just an identifier? #23
;; https://github.com/rebcabin/masr/issues/23

;; Var(symbol v)

;; from ASR.asdl doesn't match the instance. Instead,
;; we probably need something like:

;; Var(symtab_id stid, identifier it)


(defmethod expr-head ::Var [_]
  (s/keys :req [::expr-head
                ::symtab-id
                ::identifier]))


(def-term-head--entity-key expr Var)

;; heavy sugar
(defn Var-- [stid, ident]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head  ::Var
    ::symtab-id  stid
    ::identifier ident
    }})

;; legacy sugar
(defmacro Var [stid, unquoted-ident]
  `(Var-- ~stid '~unquoted-ident))

;; valid
(tests
 (let [vlv {::term ::expr,
            ::asr-expr-head
            {::expr-head  ::Var
             ::symtab-id  2
             ::identifier 'x
             }}]
   (Var-- 2 'x)              := vlv
   (Var   2  x)              := vlv
   (s/valid? ::asr-term vlv) := true
   (s/valid? ::Var      vlv) := true))


;; TODO: make it look up a value in the
;; symbol-table! That's part of abstract execution.


;;  _              _         _ ___ _      ___
;; | |   ___  __ _(_)__ __ _| | _ |_)_ _ / _ \ _ __
;; | |__/ _ \/ _` | / _/ _` | | _ \ | ' \ (_) | '_ \
;; |____\___/\__, |_\__\__,_|_|___/_|_||_\___/| .__/
;;           |___/                            |_|

;; | LogicalBinOp(expr left, logicalbinop op, expr
;;   right, ttype type, expr? value)

;; (LogicalBinOp
;;  (Var 2 a)
;;  And
;;  (LogicalCompare
;;   (Var 2 b)
;;   Eq
;;   (Var 2 b)
;;   (Logical 4 []) ())
;;  (Logical 4 []) ())


(defmethod expr-head ::LogicalBinOp [_]
  (s/keys :req [::expr-head
                ::expr-left     ;; check ::Logical
                ::logicalbinop
                ::expr-right    ;; check ::Logical
                ::Logical
                ::value
                ]))


(def-term-head--entity-key expr LogicalBinOp)


(s/def ::expr-left  ::expr)
(s/def ::expr-right ::expr)

;; heavy sugar
(defn LogicalBinOp [left- lbo- right- tt- val-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head    ::LogicalBinOp
    ::expr-left    left-
    ::logicalbinop lbo-
    ::expr-right   right-
    ::Logical      tt-
    ::value        val-
    }})

;; heavy sugar
(tests
 (s/valid? ::LogicalBinOp
           (LogicalBinOp
            (Var 2 a)
            And
            (Var 2 b)
            (Logical 4 [])
            ()))             := true)


;;  _              _         _  ___
;; | |   ___  __ _(_)__ __ _| |/ __|___ _ __  _ __  __ _ _ _ ___
;; | |__/ _ \/ _` | / _/ _` | | (__/ _ \ '  \| '_ \/ _` | '_/ -_)
;; |____\___/\__, |_\__\__,_|_|\___\___/_|_|_| .__/\__,_|_| \___|
;;           |___/                           |_|

;;  (LogicalCompare
;;   (Var 2 b)
;;   Eq
;;   (Var 2 b)
;;   (Logical 4 []) ())

;; | LogicalCompare(expr left,   ;; must have type ::Logical
;;                  cmpop op,    ;; not all cmpop, only Eq and NotEq
;;                  expr right,  ;; must have type ::Logical
;;                  ttype type,
;;                  expr? value)


(enum-like logicalcmpop #{'Eq 'NotEq})


(defmethod expr-head ::LogicalCompare [_]
  (s/keys :req [::expr-head
                ::expr-left
                ::logicalcmpop
                ::Logical
                ::value]))


(def-term-head--entity-key expr LogicalCompare)

;; heavy sugar
(defn LogicalCompare [l- cmp- r- tt- val-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head    ::LogicalCompare
    ::expr-left    l-
    ::logicalcmpop cmp-
    ::expr-right   r-
    ::Logical      tt-
    ::value        val-}})

;; heavy sugar
(tests
 (s/valid? ::LogicalCompare
           (LogicalCompare
            (Var 2 b)
            Eq
            (Var 2 b)
            (Logical 4 []) ()))    := true)


;; ================================================================
;;  ____ _____ __  __ _____
;; / ___|_   _|  \/  |_   _|
;; \___ \ | | | |\/| | | |
;;  ___) || | | |  | | | |
;; |____/ |_| |_|  |_| |_|


;; nested multi-spec
(do (defmulti stmt-head ::stmt-head)
    (s/def ::asr-stmt-head
      (s/multi-spec stmt-head ::stmt-head)))

;; Employ the nested multi-spec:
(defmethod term ::stmt [_]
  (s/keys :req [::term ::asr-stmt-head]))


(def-term-entity-key stmt)


(def MIN-NUMBER-OF-STMTS    0)
(def MAX-NUMBER-OF-STMTS 1024)


(s/def ::stmts (s/coll-of ::stmt
                          :min-count MIN-NUMBER-OF-STMTS
                          :max-count MAX-NUMBER-OF-STMTS))


(s/def ::stmtq (s/coll-of ::stmt
                          :min-count 0
                          :max-count 1))
;; - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


;;    _          _                         _
;;   /_\   _____(_)__ _ _ _  _ __  ___ _ _| |_
;;  / _ \ (_-<_-< / _` | ' \| '  \/ -_) ' \  _|
;; /_/ \_\/__/__/_\__, |_||_|_|_|_\___|_||_\__|
;;                |___/

;; | Assignment(expr target, expr value, stmt? overloaded)
;;          --- Var ---

;; See Issues
;;
;; https://github.com/rebcabin/masr/issues/21
;; https://github.com/rebcabin/masr/issues/22
;; https://github.com/rebcabin/masr/issues/26


;; meant to handle statements like
;; (= (Var 2 a)
;;    (LogicalConstant false (Logical 4 []))
;;    ())


(s/def ::lvalue     ::Var)
(s/def ::rvalue     ::expr)
(s/def ::overloaded ::stmtq)


(defmethod stmt-head ::Assignment [_]
  (s/keys :req [::stmt-head
                ::lvalue
                ::rvalue
                ::overloaded]))


(def-term-head--entity-key stmt Assignment)

;; heavy sugar
(defn Assignment-- [lhs, rhs, unk]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head   ::Assignment
    ::lvalue      lhs
    ::rvalue      rhs
    ::overloaded  unk}})

;;
(tests
 (s/valid? ::Assignment
           (Assignment-- (Var 2 a)
                     (LogicalConstant false (Logical 4 []))
                     ()))                    := true)

;; legacy sugar
(tests
 (s/valid? ::Assignment
           (legacy (= (Var 2 a)
                      (LogicalConstant false (Logical 4 []))
                      ())))                  := true
 (let [e (legacy (= (Var 2 a)
                    (LogicalBinOp
                     (Var 2 a)
                     And
                     (LogicalCompare
                      (Var 2 b)
                      Eq
                      (Var 2 b)
                      (Logical 4 []) ())
                     (Logical 4 []) ()) ()))]
   (s/valid? ::Assignment e) := true
   (s/valid? ::stmt       e) := true
   (s/valid? ::asr-term   e) := true)
 (s/valid? ::Assignment
           (legacy (= (Var 2 a)
                      (LogicalBinOp
                       (Var 2 a)
                       And
                       (LogicalCompare
                        (Var 2 b)
                        NotEq
                        (Var 2 b)
                        (Logical 4 []) ())
                       (Logical 4 []) ()) ()))) := true
 (s/valid? ::Assignment
           (legacy (= (Var 2 a)
                      (LogicalBinOp
                       (Var 2 b)
                       Or
                       (Var 2 b)
                       (Logical 4 []) ()) ()))) := true)

;; back-tests
(tests
 (let [v (Variable-- 2 'a (Logical 4)
                     nil [] 'Local
                     [] [] 'Default
                     'Source 'Public 'Required
                     false)]
   (s/valid? ::LogicalBinOp    v) := false
   (s/valid? ::LogicalConstant v) := false
   (s/valid? ::Logical         v) := false
   (s/valid? ::stmt            v) := false
   (s/valid? ::Assignment      v) := false
   ))


;;  ___             _   _
;; | __|  _ _ _  __| |_(_)___ _ _
;; | _| || | ' \/ _|  _| / _ \ ' \
;; |_| \_,_|_||_\__|\__|_\___/_||_|

;; | Function(symbol_table symtab,

;;            identifier   name,
;;            ttype        function_signature,
;;            identifier*  dependencies,

;;            expr*        args,              ;; rename ::params
;;            stmt*        body,
;;            expr?        return_var,

;;            access       access,
;;            bool         deterministic,
;;            bool         side_effect_free)


;; SymbolTable is already defined

(s/def ::function-name      ::identifier)
(s/def ::function-signature ::FunctionType)
;; dependencies is already defined

(s/def ::params             ::exprs) ;; renamed from args
(s/def ::body               ::stmts)
(s/def ::return-var         ::exprq)

;; access is already defined
(s/def ::deterministic      ::bool)
(s/def ::side-effect-free   ::bool)

(defmethod symbol-head ::Function [_]
  (s/keys :req [::symbol-head

                ::SymbolTable  ;; not a symtab-id!

                ::function-name
                ::function-signature
                ::dependencies

                ::params
                ::body
                ::return-var

                ::access
                ::deterministic
                ::side-effect-free
                ]))


(def-term-head--entity-key symbol Function)


;;heavy sugar
(defn Function [symtab,
                fnnym,   fnsig,  deps,
                params-, body-,  retvar,
                access-, determ, sefree]
  {::term ::symbol
   ::asr-symbol-head
   {::symbol-head ::Function

    ::SymbolTable         symtab

    ::function-name       fnnym
    ::function-signature  fnsig
    ::dependencies        deps

    ::params              params-
    ::body                body-
    ::return-var          retvar

    ::access              access-
    ::deterministic       determ
    ::side-effect-free    sefree
    }})


(tests
 (let [ft (FunctionType
           [] () Source
           Implementation () false
           false false false
           false [] [] false)
       afn (Function
            (SymbolTable 42 {})
            'test_boolOp ft []
            [] [] ()
            Public false false)]
   (s/valid? ::asr-term afn)  := true
   (s/valid? ::symbol   afn)  := true
   (s/valid? ::Function afn)  := true
   )
 (let [afn (legacy (Function
                      (SymbolTable
                       2 {:a
                          (Variable
                           2 a [] Local
                           () () Default (Logical 4 [])
                           Source Public Required false),
                          :b
                          (Variable
                           2 b [] Local
                           () () Default (Logical 4 [])
                           Source Public Required false)})
                      'test_boolOp
                      (FunctionType
                       [] () Source
                       Implementation () false
                       false false false
                       false [] [] false)
                      [] []
                      [(= (Var 2 a)
                          (LogicalConstant false (Logical 4 []))
                          ())
                       (= (Var 2 b)
                          (LogicalConstant true (Logical 4 []))
                          ())
                       (= (Var 2 a)
                          (LogicalBinOp
                           (Var 2 a)
                           And
                           (Var 2 b)
                           (Logical 4 []) ()) ())
                       (= (Var 2 b)
                          (LogicalBinOp
                           (Var 2 a)
                           Or
                           (LogicalConstant true (Logical 4 []))
                           (Logical 4 []) ()) ())
                       (= (Var 2 a)
                          (LogicalBinOp
                           (Var 2 a)
                           Or
                           (Var 2 b)
                           (Logical 4 []) ()) ())
                       (= (Var 2 a)
                          (LogicalBinOp
                           (Var 2 a)
                           And
                           (LogicalCompare
                            (Var 2 b)
                            Eq
                            (Var 2 b)
                            (Logical 4 []) ())
                           (Logical 4 []) ()) ())
                       (= (Var 2 a)
                          (LogicalBinOp
                           (Var 2 a)
                           And
                           (LogicalCompare
                            (Var 2 b)
                            NotEq
                            (Var 2 b)
                            (Logical 4 []) ())
                           (Logical 4 []) ()) ())
                       (= (Var 2 a)
                          (LogicalBinOp
                           (Var 2 b)
                           Or
                           (Var 2 b)
                           (Logical 4 []) ()) ())]
                      () Public false false))]
     (s/valid? ::asr-term afn)  :=  true
     (s/valid? ::symbol   afn)  :=  true
     (s/valid? ::Function afn)  :=  true
     ))
