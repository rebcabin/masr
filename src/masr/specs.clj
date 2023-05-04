(ns masr.specs
  (:require [clojure.spec.alpha            :as      s            ]
            [clojure.set                   :as      set          ]
            [clojure.spec.gen.alpha        :as      gen          ]
            [clojure.test.check.generators :as      tgen         ]
            [clojure.string                :as      str          ]
            [clojure.pprint                :refer   [pprint     ]]
            [clojure.walk                  :refer   [prewalk    ]]
            #_[clojure.zip                 :as      z           ])

  (:require [hyperfiddle.rcf               :refer   [tests tap %]]
            [blaster.clj-fstring           :refer   [f-str      ]])

  (:require [masr.logic                    :refer   [iff implies]]
            [masr.utils                    :refer   [plnecho
                                                     dosafely   ]]
            [masr.simplespecs              :refer   [nat
                                                     identifier-list
                                                     identifier-set
                                                     identifier-suit
                                                     identifier ]]
            ))


;; lightweight, load-time testing:
(hyperfiddle.rcf/enable!)


;; Unmap "Integer" and "Character" so we have those
;; symbols in ttypes. Access the originals
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


;; An asr-term is a hash-map containing a ::term
;; keyword, i.e., :masr.specs/term, or ::asr/term if
;; masr.specs is aliased to asr, as in
;; (:use [masr.specs :as asr]) in core_tests.clj.


;; like ::intent, ::symbol, ::expr, ...
(s/def ::term qualified-keyword?)


;; ::term is both a qualified keyword _and_ a fn
;; that picks the value for the key ::term from a
;; hash-map. "defmulti" defines a name, say "term"
;; for a collection of "defmethods", and links the
;; name "term" to a dispatcher function, here
;; ::term. Each defmethod of term is tagged by the
;; value fetched from an instance hash-map by
;; the ::term function.
(defmulti term ::term)


;; Here is the multi-spec, named ::asr-term.
;; A multi-spec ties together the defmulti term and
;; the re-tagging function ::term. In our usage, the
;; re-tagging function is the same as the original
;; dispatcher. We don't need re-tagging.
(s/def ::asr-term
  (s/multi-spec term ::term))


;; All multi-spec names in MASR, nested or not,
;; begin with ::asr-<the_rest_of_the_name>, as
;; in ::asr-term _not nested_ and ::asr-ttype-head
;; _nested in ttypes_.


;; A given instance hash-map may be
;; an ::asr-term (any term), a
;; ::symbol (one of the several terms), and
;; a ::Variable (one of the several symbols). These
;; three specs, ::term, ::symbol, ::Variable, are of
;; increasing precision and discrimination.


;; Vertically, both ::symbol and ::Variable
;; are ::asr-term. Horizontally, both ::Variable
;; and ::Function are ::symbol, and both are
;; also ::asr-term. For another example, vertically,
;; both ::expr and ::LogicalBinOp are ::asr-term.
;; Horizontally, both ::LogicalBinOp
;; and ::LogicalCompare are ::expr, and both
;; are ::asr-term.


;;   __                           __  _ __         __
;;  / /____ ______ _    ___ ___  / /_(_) /___ __  / /_____ __ __
;; / __/ -_) __/  ' \  / -_) _ \/ __/ / __/ // / /  '_/ -_) // /
;; \__/\__/_/ /_/_/_/  \__/_//_/\__/_/\__/\_, / /_/\_\\__/\_, /
;;                                       /___/           /___/


;; For recursive conformance in multi-specs. Each
;; term, like symbol, needs its own spec, named
;; by a qualified keyword like ::symbol. That is so
;; fields in hash-maps with keys like ::symbol can
;; be checked by specs named ::symbol.


(defn term-selector-spec [kwd]
  (s/and ::asr-term
         #(= kwd (::term %))))


(defmacro def-term-entity-key
  "Define spec for entity key like ::symbol or ::expr, which is
  an ::asr-term, a top-level production in the grammar."
  [term]
  (let [ns "masr.specs"
        tkw (keyword ns (str term))]
    `(s/def ~tkw    ;; like ::dimension or ::symbol
       (term-selector-spec ~tkw))))


;; Now automate construction of the nested
;; multi-specs, removing all duplicated wordage.
;; This is a tricky macro, like many macros, due
;; mostly to insertion and deletion of namespaces,
;; which is not easily predictable.


(defmacro defmasrnested
  "Define specs for terms with nested multi-specs,
  like ::expr, ::symbol, ::ttype, ::stmt. Also define
  the defmulti's for the nested multi-specs themselves.
  Automate constructions like:

      (defmethod term ::expr [_]
        (s/keys :req [::term
                      ::asr-expr-head]))
      (def-term-entity-key expr)

      ;; nested multi-spec:
      (defmulti expr-head ::expr-head)
      (s/def ::asr-expr-head
        (s/multi-spec expr-head ::exprhead

  Remove repetitive wordage."
  [term]
  (let [ns "masr.specs"
        ttrm (keyword ns "term") ;; like ::term
        tcst (symbol "term")     ;; like term (no ns!?!)

        tstr (str term)          ;; like "expr"
        tkwd (keyword ns tstr)   ;; like ::expr
        tsym (symbol (name (symbol tstr))) ;; like expr -- caution

        estr (str term "-head")  ;; like "expr-head"
        ekwd (keyword ns estr)   ;; like ::expr-head
        esym (symbol (name (symbol estr))) ;; like expr-head

        ;; like ::asr-expr-head
        akwd (keyword ns (str "asr-" (name term) "-head"))
        ]
    `(do (defmethod ~tcst ~tkwd [_#]
           (s/keys :req [~ttrm ~akwd]))
         (def-term-entity-key ~tsym) ;; caution
         (defmulti ~esym ~ekwd)
         (s/def ~akwd
           (s/multi-spec ~esym ~ekwd))
         )))


(defmasrnested expr)
(defmasrnested stmt)
(defmasrnested symbol)
(defmasrnested ttype)
(defmasrnested unit)


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
   From term = symbol and head = Variable,
   generate a spec s/def like

   (s/def ::Variable               ;; head entity key
     (s/and ::asr-term             ;; top multi-spec
       #(= ::Variable              ;; nested tag
           (-> % ::asr-symbol-head ;; nested multi-spec
                 ::symbol-head)))) ;; tag fetcher

   From term = stmt and head = Assignment, generate
   a spec s/def like

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
        hkw (keyword ns (str head))         ;; like ::Variable
        tmh (keyword ns (str term "-head")) ;; like ::symbol-head
        amh (keyword ns                     ;; for the multi-spec
             (str "asr-" term "-head"))     ;; like ::asr-symbol-head
        ]
    `(s/def ~hkw
       (s/and ~art #(= ~hkw (-> % ~amh ~tmh))))))


;; ================================================================
;;  ____  _____ _____ __  __    _    ____  ____ _______   ______  _____
;; |  _ \| ____|  ___|  \/  |  / \  / ___||  _ \_   _\ \ / /  _ \| ____|
;; | | | |  _| | |_  | |\/| | / _ \ \___ \| |_) || |  \ V /| |_) |  _|
;; | |_| | |___|  _| | |  | |/ ___ \ ___) |  _ < | |   | | |  __/| |___
;; |____/|_____|_|   |_|  |_/_/   \_\____/|_| \_\|_|   |_| |_|   |_____|


;; Routines to find the ASDL type of any MASR instance. MASR
;; instances are automatically type-checked.


(def asdl-types
  {
   ::SymbolTable  "symbol_table stab"
   ::body         "stmt* body"
   ::dependencies "identifier* dependencies"
   ::dimensions   "dimension* dims"
   ::logical-kind "int kind"
   ::logicalbinop "logicalbinop"
   ::lvalue       "expr target"
   ::overloaded   "stmt? overloaded"
   ::prognym      "identifier program_name"
   ::rvalue       "expr value"
   ::symtab-id    "symbol_table stid" ;; TODO: this is TERRIBLE
   ::varnym       "identifier varnym"
   })


(defmacro asdl-type-string
  "Construct a string like this
  Assignment(expr target, expr value, stmt? overloaded)"
  ;; term is a string like "symbol" or "stmt" in quotes
  [term sqkeysyms]
  (let [ns "masr.specs"
        ;; like symbol-head or stmt-head
        str-head (str    term "-head")
        trm-head (symbol str-head)
        seq-keys (map    #(keyword ns (str %)) sqkeysyms)]
    ;; Strip the namespace inserted by backtick. symbol-head
    ;; becomes a bound symbol in context, bound to Program or
    ;; Variable or whatever.
    `(let [head#   (name ~trm-head)
           params# (str/join ", "
                    (map asdl-types (list ~@seq-keys)))]
       (str head# "(" params# ")"))))


(defmacro defmasrtype
  "Get rid of repetition in some expression like this:

  (defmethod stmt->asdl-type ::Assignment
      [{::keys [stmt-head
                lvalue        ;;  <~~~ this bit gets repeated
                rvalue        ;;  <~~~ this bit gets repeated
                overloaded]}] ;;  <~~~ this bit gets repeated
      (asdl-type-string \"stmt\" (lvalue     ;; <~~~ repeated
                                  rvalue     ;; <~~~ repeated
                                  overloaded ;; <~~~ repeated
                                  )))

  and this (the whole key list is just a transform of the
  sym-list above)

  (defmethod symbol-head ::Program [_]
    (s/keys :req [::symbol-head  ;; <~~~ repetitive
                  ::SymbolTable  ;; <~~~ repetitive
                  ::prognym      ;; <~~~ repetitive
                  ::dependencies ;; <~~~ repetitive
                  ::body]        ;; <~~~ repetitive
            ))
  "
  [head, term, keyseq]
  (let [ns "masr.specs"
        ;; like ::Program
        head-key  (keyword ns (str head))
        ;; ::keys
        keys-key  (keyword ns "keys")
        ;; like symbol-head
        term-head (symbol (str term "-head"))
        ;; like symbol->asdl-type
        mthd-nym  (symbol (str term "->asdl-type"))
        ;; like (symbol-head, SymbolTable, ..., body)
        sym-list  (conj keyseq term-head)
        ;; like [symbol-head, SymbolTable, ..., body]
        ;; for destructuring
        parm-vec  (vec sym-list)
        ;; like (::symbol-head, ::SymbolTable, ..., ::body)
        ;; for entity specs via s/keys
        key-list  (map #(keyword ns (str %)) sym-list)
        ;; like [::symbol-head, ::SymbolTable, ..., ::body]
        ;; for entity specs via s/keys
        key-vec   (vec key-list)
        ]

    `(do (defmethod ~mthd-nym ~head-key
           [{~keys-key ~parm-vec}]
           (asdl-type-string ~term ~keyseq))
         (defmethod ~term-head ~head-key [_#]
           (s/keys :req ~key-vec))
         )))


;;     __                _ _     _
;;  ___\ \   __ _ ___ __| | |___| |_ _  _ _ __  ___
;; |___|> > / _` (_-</ _` | |___|  _| || | '_ \/ -_)
;; |___/_/  \__,_/__/\__,_|_|    \__|\_, | .__/\___|
;;                                   |__/|_|


(defmulti  unit->asdl-type ::unit-head)

(defmasrtype
 TranslationUnit unit
 (SymbolTable
  nodes))


(defmulti  symbol->asdl-type ::symbol-head)

(defmasrtype
 Program symbol
 (SymbolTable
  prognym
  dependencies
  body))

(defmasrtype
 Variable symbol
 (symbol-head
  symtab-id        varnym          dependencies
  intent           symbolic-value  value
  storage-type     ttype           abi
  access           presence        value-attr
  type-declaration))

(defmasrtype
 Module symbol
 [symbol-head
  SymbolTable
  modulenym
  dependencies
  loaded-from-mod
  intrinsic])

(defmasrtype
 Function symbol
 (symbol-head
  ;;----------------
  SymbolTable ;; not a symtab-id!
  ;;----------------
  function-name
  function-signature
  dependencies
  ;;----------------
  params
  body
  return-var
  ;;----------------
  access
  deterministic
  side-effect-free
  ))

(defmulti  stmt->asdl-type ::stmt-head)

(defmasrtype
 Assignment stmt
 (lvalue
  rvalue
  overloaded))


(defmulti  expr->asdl-type ::expr-head)

(defmasrtype
 LogicalBinOp expr
 (logical-left
  logicalbinop
  logical-right
  Logical
  value))

(defmasrtype
 LogicalCompare expr
 (expr-head
  logical-left
  logicalcmpop
  logical-right
  Logical
  value))

(defmasrtype
 LogicalConstant expr
 (bool
  Logical))

(defmasrtype
 Var expr
 (symtab-id
  varnym))


(defmulti  ttype->asdl-type ::ttype-head)

(defmasrtype
 Logical ttype
 (logical-kind
  dimensions))


;;     __                _ _     _
;;  ___\ \   __ _ ___ __| | |___| |_ _  _ _ __  ___
;; |___|> > / _` (_-</ _` | |___|  _| || | '_ \/ -_)
;; |___/_/  \__,_/__/\__,_|_|    \__|\_, | .__/\___|
;;                                   |__/|_|


(defmulti  ->asdl-type ::term)
(defmacro term->asdl-type [term]
  (let [ns "masr.specs"
        ;; like ::keys
        keys-key (keyword ns "keys")
        ;; like ::symbol or ::stmt, value of term
        mthd-key (keyword ns term)
        ;; like asr-symbol-head or asr-stmt-head, a key-symbol
        ;; for destructuring
        nest-ksm (symbol (str "asr-" term "-head"))
        ;; like symbol->asdl-type or stmt->asdl-type
        call-sym (symbol (str term "->asdl-type"))
        ;; don't put the namespace on "term";
        ;; nons-term is a const destructuring key.
        nons-trm (symbol "term")]
    ;; (defmethod ->asdl-type :masr.specs/symbol
    ;;   [#:masr.specs{:keys [term asr-symbol-head]}]
    ;;   (symbol->asdl-type asr-symbol-head))
    `(defmethod ->asdl-type ~mthd-key
       [{~keys-key [~nons-trm ~nest-ksm]}]
       (~call-sym ~nest-ksm))))

(term->asdl-type "symbol") ;; Don't expand in CIDER! console only.
(term->asdl-type "stmt")   ;; CIDER macro-expand removes namespace.
(term->asdl-type "expr")   ;;
(term->asdl-type "ttype")


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_|

;; Dimension is a term. Dimensions [sic] is not a
;; term. Dimensions stands for dimension*, a
;; plurality of dimension.


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


;; original ASDL:
;; dimension = (expr? start, expr? length)


;; The ASDL is imprecise. The real spec is that we
;; have either both start and length or we just have
;; nothing. Case with 1 index is disallowed.
;; https://github.com/rebcabin/masr/issues/5


(def MIN-DIMENSION-COUNT 0)
(def MAX-DIMENSION-COUNT 2)


;; This says that a ::dimension-content is a
;; collection of ::nat with either two or zero
;; elements.
;; TODO Consider a regex-spec.
(s/def ::dimension-content
  (s/and
   (s/coll-of ::nat
              :min-count MIN-DIMENSION-COUNT,
              :max-count MAX-DIMENSION-COUNT,
              :into ())
   (fn [it] (not (= 1 (count it))))))


;; This says that a dimension in full-form is a
;; hash-map with keys ::term and
;; ::dimension-content.
(defmethod term ::dimension [_]
  (s/keys :req [::term
                ::dimension-content]))


;; As usual, we need a term-entity key for
;; recursive checking.
(def-term-entity-key dimension)

#_
(gen/sample (s/gen ::dimension-content) 3)
;; => (() (0 0) (1 1))


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn dimension [candidate-contents] ;; candidate contents
  (if (or (not (coll? candidate-contents))
          (set? candidate-contents)
          (map? candidate-contents))
    ::invalid-dimension
    (let [cnf (s/conform
               ::dimension
               {::term ::dimension,
                ::dimension-content candidate-contents})]
      (if (s/invalid? cnf)
        ::invalid-dimension
        {::term ::dimension,
         ::dimension-content
         (::dimension-content cnf)}))))


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/

;; not an asr-term


;; -+-+-+-+-+-+-+-+-+-+-+-
;;  p l u r a l i t i e s
;; -+-+-+-+-+-+-+-+-+-+-+-


(def MIN-NUMBER-OF-DIMENSIONS 0)  ;; TODO: 1?
(def MAX-NUMBER-OF-DIMENSIONS 9)

;; Consider a regex-spec.
(s/def ::dimensions
  (s/coll-of (term-selector-spec ::dimension)
             :min-count MIN-NUMBER-OF-DIMENSIONS,
             :max-count MAX-NUMBER-OF-DIMENSIONS,
             :into []))


;; TODO https://github.com/rebcabin/masr/issues/14
#_(gen/sample (s/gen ::dimensions) 3)


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn dimensions
  [candidate-contents]
  (if (or (not (coll? candidate-contents))
          (set? candidate-contents)
          (map? candidate-contents))
    ::invalid-dimensions
    ;; else
    (let [dims-coll (map dimension candidate-contents)
          dims-conf (s/conform ::dimensions dims-coll)]
      (if (s/invalid? dims-conf)
        ::invalid-dimensions
        (let [dims-cont (map
                         ::dimension-content
                         dims-conf)]
          (map dimension dims-cont))))))


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


(defn rewrite-for-legacy
  [it]
  (prewalk (fn [x] (if (list? x)
                     (replace {'= 'Assignment--} x)
                     x))
           it))


(defmacro legacy
  "DANGER: Call 'eval'."
  [it]
  `(do (in-ns 'masr.specs)
       (eval (rewrite-for-legacy '~it))))


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


;; ================================================================
;;  _____ _   _ _   _ __  __       _     ___ _  _______
;; | ____| \ | | | | |  \/  |     | |   |_ _| |/ / ____|
;; |  _| |  \| | | | | |\/| |_____| |    | || ' /|  _|
;; | |___| |\  | |_| | |  | |_____| |___ | || . \| |___
;; |_____|_| \_|\___/|_|  |_|     |_____|___|_|\_\_____|

;; Convert a set, heads, of symbols into a multi-spec under
;; ::asr-term, an entity-key spec like "::intent", and a
;; sugar function like intent.


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


(enum-like logicalbinop #{'And  'Or  'Xor  'NEqv  'Eqv})
(enum-like cmpop        #{'Eq  'NotEq  'Lt  'LtE  'Gt  'GtE })
(enum-like intent       #{'Local 'In 'Out 'InOut 'ReturnVar 'Unspecified})
(enum-like storage-type #{'Default, 'Save, 'Parameter, 'Allocatable})
(enum-like logicalcmpop #{'Eq 'NotEq})
(enum-like access       #{'Public 'Private})
(enum-like presence     #{'Required 'Optional})
(enum-like deftype      #{'Implementation, 'Interface})


;;       _    _
;;  __ _| |__(_)
;; / _` | '_ \ |
;; \__,_|_.__/_|

;; special case of enum-like with rich logic


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
     (let [cnf (s/conform ::abi
                          {::term         ::abi,
                           ::abi-enum     the-enum,
                           ::abi-external the-bool})]
       (if (s/invalid? cnf)
         ::invalid-abi
         cnf)))))

;; ASDL Back-Channel
(def LFortranModule (abi 'LFortranModule :external true))
(def GFortranModule (abi 'GFortranModule :external true))
(def BindC          (abi 'BindC          :external true))
(def Interactive    (abi 'Interactive    :external true))
(def Intrinsic      (abi 'Intrinsic      :external true))

(def Source         (abi 'Source         :external false))


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


;; Then, we need functions-to-dispatch-to, i.e.,
;; methods or defmethods. The next macro eliminates
;; the repetitive syntax in writing out defmethods
;; for Integer, Real, Complex, Logical, and
;; Character.

;; Now, the asr-term defmethod spec for ttype.
;; -+-+-+-+-+-+-+-+-+-+-+-
;;  p l u r a l i t i e s
;; -+-+-+-+-+-+-+-+-+-+-+-


(def MIN-NUMBER-OF-TTYPES    0)
(def MAX-NUMBER-OF-TTYPES 1024)

;; consider a regex-spec
(s/def ::ttypes (s/coll-of ::ttype
                           :min-count MIN-NUMBER-OF-TTYPES
                           :max-count MAX-NUMBER-OF-TTYPES))

;; consider a regex-spec
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


;; -+-+-+-+-+-
;;  s u g a r
;; -+-+-+-+-+-


(defn ttype [it]
  (let [cnf (s/conform ::ttype
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


(def-term-head--entity-key ttype Integer)
(def-term-head--entity-key ttype Real)
(def-term-head--entity-key ttype Complex)
(def-term-head--entity-key ttype Logical)


;; TODO the rest of the ttypes
;;     >>> Integer, Real, Complex, Logical are already done ...
;;     >>> FunctionType is done.
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


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  p r e r e q u i s i t e   t y p e   a l i a s e s
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;
;; Define type aliases for types that are too
;; vague.


(s/def ::param-types     ::ttypes)
(s/def ::return-var-type ::ttypeq)
;; ABI is already good enough.
;; deftype is found above
(s/def ::bindc-name      (s/nilable string?))
(s/def ::elemental       ::bool)
(s/def ::pure            ::bool)
(s/def ::module          ::bool)
(s/def ::inline          ::bool)
(s/def ::static          ::bool)
(s/def ::type-params     ::ttypes)


;; symbol* is written "symbols," and restrictions
;; is a type alias for symbols.
;;
;; forward reference. Can only test empty symbol*
;; restrictions until symbol is properly defined
;; below.


;; -+-+-+-+-+-+-+-+-+-+-+-
;;  p l u r a l i t i e s
;; -+-+-+-+-+-+-+-+-+-+-+-


(def MIN-NUMBER-OF-SYMBOLS   0)
(def MAX-NUMBER-OF-SYMBOLS 128)

;; consider a regex-spec
(s/def ::symbols (s/coll-of ::symbol
                            :min-count MIN-NUMBER-OF-SYMBOLS
                            :max-count MAX-NUMBER-OF-SYMBOLS))
;; symbol? is written "symbolq."
;; consider a regex-spec
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
  (let [cnf {::term ::ttype
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

              ::is-restriction   is-restriction-}}]
    (if (s/invalid? cnf)
      :invalid-function-type
      cnf)))


;; ================================================================
;;  ____  _        _    ____ _____
;; |  _ \| |      / \  / ___| ____|
;; | |_) | |     / _ \| |   |  _|
;; |  __/| |___ / ___ \ |___| |___
;; |_|   |_____/_/   \_\____|_____|
;;  _   _  ___  _     ____  _____ ____  ____
;; | | | |/ _ \| |   |  _ \| ____|  _ \/ ___|
;; | |_| | | | | |   | | | |  _| | |_) \___ \
;; |  _  | |_| | |___| |_| | |___|  _ < ___) |
;; |_| |_|\___/|_____|____/|_____|_| \_\____/


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
;;  _______  ______  ____
;; | ____\ \/ /  _ \|  _ \
;; |  _|  \  /| |_) | |_) |
;; | |___ /  \|  __/|  _ <
;; |_____/_/\_\_|   |_| \_\


;; -+-+-+-+-+-+-+-+-+-+-+-
;;  p l u r a l i t i e s
;; -+-+-+-+-+-+-+-+-+-+-+-


(def MIN-NUMBER-OF-EXPRS    0)
(def MAX-NUMBER-OF-EXPRS 1024)

;; consider a regex-spec
(s/def ::exprs (s/coll-of ::expr
                          :min-count MIN-NUMBER-OF-EXPRS
                          :max-count MAX-NUMBER-OF-EXPRS))

;; consider a regex-spec
(s/def ::exprq (s/coll-of ::expr
                          :min-count 0
                          :max-count 1))
;; - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


;; To add a new head to term "expr," create a
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


(def-term-head--entity-key expr LogicalConstant)

;; heavy sugar
(defn LogicalConstant
  ;; arity-2
  ([a-bool, a-ttype]
   (let [cnf {::term ::expr,
              ::asr-expr-head
              {::expr-head ::LogicalConstant
               ::bool      a-bool
               ::Logical   a-ttype}}]
     (if (s/invalid? cnf)
       :invalid-logical-constant
       cnf)))
  ;; arity-1
  ([a-bool]
   (LogicalConstant a-bool (Logical))))


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


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  p r e r e q u i s i t e   t y p e   a l i a s e s
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(s/def ::varnym           ::identifier)


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(def-term-head--entity-key expr Var)

;; heavy sugar
(defn Var-- [stid, ident]
  (let [cnf {::term ::expr,
             ::asr-expr-head
             {::expr-head  ::Var
              ::symtab-id  stid
              ::varnym     ident
              }}]
    (if (s/invalid? cnf)
      :invalid-var
      cnf)))

;; legacy sugar
(defmacro Var [stid, unquoted-ident]
  `(Var-- ~stid '~unquoted-ident))


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


(def-term-head--entity-key expr LogicalBinOp)


;; TODO: check that the types of the exprs are ::Logical!
(s/def ::logical-left  ::expr)
(s/def ::logical-right ::expr)


;; -+-+-+-+-+-+-+-+-+-+-+-+-
;;  t y p e   a l i a s e s
;; -+-+-+-+-+-+-+-+-+-+-+-+-


;; heavy sugar
(defn LogicalBinOp [left- lbo- right- tt- val-]
  (let [cnf {::term ::expr,
             ::asr-expr-head
             {::expr-head     ::LogicalBinOp
              ::logical-left  left-
              ::logicalbinop  lbo-
              ::logical-right right-
              ::Logical       tt-
              ::value         val-
              }}]
    (if (s/invalid? cnf)
      :invalid-logical-bin-op
      cnf)))


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


(def-term-head--entity-key expr LogicalCompare)

;; heavy sugar
(defn LogicalCompare [l- cmp- r- tt- val-]
  (let [cnf {::term ::expr,
             ::asr-expr-head
             {::expr-head     ::LogicalCompare
              ::logical-left  l-
              ::logicalcmpop  cmp-
              ::logical-right r-
              ::Logical       tt-
              ::value         val-}}]
    (if (s/invalid? cnf)
      :invalid-logical-compare
      cnf)))


;; ================================================================
;;  ____ _____ __  __ _____
;; / ___|_   _|  \/  |_   _|
;; \___ \ | | | |\/| | | |
;;  ___) || | | |  | | | |
;; |____/ |_| |_|  |_| |_|


;; -+-+-+-+-+-+-+-+-+-+-+-
;;  p l u r a l i t i e s
;; -+-+-+-+-+-+-+-+-+-+-+-


(def MIN-NUMBER-OF-STMTS    0)
(def MAX-NUMBER-OF-STMTS 1024)

;; consider a regex-spec
(s/def ::stmts (s/coll-of ::stmt
                          :min-count MIN-NUMBER-OF-STMTS
                          :max-count MAX-NUMBER-OF-STMTS))

;; consider a regex-spec
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


;; -+-+-+-+-+-+-+-+-+-+-+-+-
;;  t y p e   a l i a s e s
;; -+-+-+-+-+-+-+-+-+-+-+-+-


;; TODO: more cases for lvalue, and an s/or
;; with "second" hack
(s/def ::lvalue     ::Var)
(s/def ::rvalue     ::expr)
(s/def ::overloaded ::stmtq)


(def-term-head--entity-key stmt Assignment)

;; heavy sugar
(defn Assignment-- [lhs, rhs, unk]
  (let [cnf {::term ::stmt,
             ::asr-stmt-head
             {::stmt-head   ::Assignment
              ::lvalue      lhs
              ::rvalue      rhs
              ::overloaded  unk}}]
    (if (s/invalid? cnf)
      :invalid-assignment
      cnf)))


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


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  p r e r e q u i s i t e   t y p e   a l i a s e s
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(s/def ::value-attr       ::bool)
;; varnym already defined for Var.
;; https://github.com/rebcabin/masr/issues/28
(s/def ::type-declaration (s/nilable ::symtab-id))
;; TODO: there is ambiguity regarding identifier-sets and lists:
(s/def ::dependencies     ::identifier-set)


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l   f o r m
;; -+-+-+-+-+-+-+-+-+-


(def-term-head--entity-key symbol Variable)


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
      :or {type-declaration nil
           dependencies     ()
           intent           (intent 'Local)

           symbolic-value   ()
           value            ()
           storage-type     (storage-type 'Default)

           abi              Source
           access           Public
           presence         Required
           value-attr       false}}]
  (let [cnf (s/conform ::Variable
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
    (if (s/invalid? cnf)
      ::invalid-variable
      cnf)))


;; -+-+-+-+-+-+-+-+-+-+-+-
;;  h e a v y   s u g a r
;; -+-+-+-+-+-+-+-+-+-+-+-


(defn Variable--
  "Heavy sugar; parameters that collide with functions
  have trailing hyphens."
  [symtab-id-,         varnym-,        ttype-,
   typedecl-,          dependencies-,  intent-,
   symbolic-value-,    value-,         storage-type-,
   abi-,               access-,        presence-,
   value-attr-]
  (let [cnf (s/conform ::Variable
                       {::term              ::symbol,
                        ::asr-symbol-head
                        {::symbol-head      ::Variable,

                         ::symtab-id        symtab-id-,
                         ::varnym           varnym-,
                         ::ttype            ttype-, ;; already wrapped!

                         ;; https://github.com/rebcabin/masr/issues/28
                         ::type-declaration typedecl-
                         ::dependencies     dependencies-,
                         ::intent           (if (symbol? intent-)
                                              (intent  intent-)
                                              intent-),

                         ::symbolic-value   symbolic-value-,
                         ::value            value-,
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

                         ::value-attr       value-attr-,
                         }})]
    (if (s/invalid? cnf)
      ::invalid-variable
      cnf)))


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
    nil ;; legacy doesn't have type-declaration
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


;;  __  __         _      _
;; |  \/  |___  __| |_  _| |___
;; | |\/| / _ \/ _` | || | / -_)
;; |_|  |_\___/\__,_|\_,_|_\___|

;; | Module(symbol_table symtab, identifier name, identifier* dependencies,
;;                       bool loaded_from_mod, bool intrinsic)


(s/def ::modulenym       ::identifier)
(s/def ::loaded-from-mod ::bool)
(s/def ::intrinsic       ::bool)


(def-term-head--entity-key symbol Module)


(defn Module [symtab, modnym, deps, loaded, intrinsic-]
  (let [cnf {::term ::symbol
             ::asr-symbol-head
             {::symbol-head     ::Module
              ::SymbolTable     symtab
              ::modulenym       modnym
              ::dependencies    deps
              ::loaded-from-mod loaded
              ::intrinsic       intrinsic-}}]
    (if (s/invalid? cnf)
      :invalid-module
      cnf)))


;;  ___             _   _
;; | __|  _ _ _  __| |_(_)___ _ _
;; | _| || | ' \/ _|  _| / _ \ ' \
;; |_| \_,_|_||_\__|\__|_\___/_||_|

;; | Function(symbol_table symtab,
;;
;;            identifier   name,
;;            ttype        function_signature,
;;            identifier*  dependencies,
;;
;;            expr*        args,              ;; rename ::params
;;            stmt*        body,
;;            expr?        return_var,
;;
;;            access       access,
;;            bool         deterministic,
;;            bool         side_effect_free)


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  p r e r e q u i s i t e   t y p e   a l i a s e s
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


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


(def-term-head--entity-key symbol Function)

;; heavy sugar
(defn Function-- [symtab,
                  fnnym,   fnsig,  deps,
                  params-, body-,  retvar,
                  access-, determ, sefree]
  (let [cnf {::term ::symbol
             ::asr-symbol-head
             {::symbol-head         ::Function

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
              }}]
    (if (s/invalid? cnf)
      :invalid-function
      cnf)))


(defmacro Function
  "Quote the fnnym."
  [symtab,
   fnnym,   fnsig,  deps,
   params-, body-,  retvar,
   access-, determ, sefree]
  `(Function-- ~symtab,
               '~fnnym,  ~fnsig,  ~deps,
               ~params-, ~body-,  ~retvar,
               ~access-, ~determ, ~sefree))


;;  ___
;; | _ \_ _ ___  __ _ _ _ __ _ _ __
;; |  _/ '_/ _ \/ _` | '_/ _` | '  \
;; |_| |_| \___/\__, |_| \__,_|_|_|_|
;;              |___/

;; = Program(symbol_table symtab,
;;           identifier   name,
;;           identifier*  dependencies,
;;           stmt*        body)


;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
;;  p r e r e q u i s i t e   t y p e   a l i a s e s
;; -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


(s/def ::prognym ::identifier)


;; -+-+-+-+-+-+-+-+-+-
;;  f u l l - f o r m
;; -+-+-+-+-+-+-+-+-+-


(def-term-head--entity-key symbol Program)


(defn Program-- [stab, nym, deps, body-]
  (let [cnf (s/conform ::Program
                       {::term ::symbol,
                        ::asr-symbol-head
                        {::symbol-head  ::Program
                         ::SymbolTable  stab
                         ::prognym      nym
                         ::dependencies deps
                         ::body         body-}})]
    (if (s/invalid? cnf)
      ::invalid-program
      cnf)))


(defmacro Program
  "Quote the nym."
  [stab, nym, deps, body-]
  `(Program-- ~stab, '~nym, ~deps, ~body-))


;; ================================================================
;;  _   _ _   _ ___ _____
;; | | | | \ | |_ _|_   _|
;; | | | |  \| || |  | |
;; | |_| | |\  || |  | |
;;  \___/|_| \_|___| |_|


(s/def ::node (s/or :expr   ::expr
                    :stmt   ::stmt
                    :symbol ::symbol))


(defn node [candidate]
  (let [cnf (s/conform ::node candidate)]
    (if (s/invalid? cnf)
      :invalid-node
      (second cnf))))


(def MIN-NODE-COUNT    0)
(def MAX-NODE-COUNT 4096)

;; Consider a regex-spec.
(s/def ::nodes
  (s/and (s/coll-of ::node
                    :min-count MIN-NODE-COUNT,
                    :max-count MAX-NODE-COUNT)))


(def-term-head--entity-key unit TranslationUnit)


(defn TranslationUnit [stab, node-preimages]
  (let [node-cnf (map node node-preimages)
        ;; the s/conform slips back in the tag keys
        cnf
        (s/conform
             ::unit
             {::term          ::unit
              ::asr-unit-head
              {::unit-head    ::TranslationUnit
               ::SymbolTable  stab
               ::nodes        node-cnf}})
        ;; snip the tag keys
        fixed
        (assoc-in cnf
                  [::asr-unit-head ::nodes]
                  node-cnf)]
    (if (s/invalid? cnf)
      :invalid-translation-unit
      fixed)))
