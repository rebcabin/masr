```clojure
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
```

lightweight, load-time testing:

```clojure
(hyperfiddle.rcf/enable!)
```

Unmap "Integer" and "Character" so we can have
those symbols in ttypes. Access the originals
via "java.lang.Integer" "java.lang.Character."
Lein test and lein run produce unmaskable
warnings. Access original "deftype"
as "clojure.core/deftype".

```clojure
(ns-unmap *ns* 'Integer)
(ns-unmap *ns* 'Character)
(ns-unmap *ns* 'deftype)
```

ASDL tuples like `(1 2)` are Clojure lists.
ASDL lists are ASDL tuples.
ASDL vectors like `[expr? stmt*]` are Clojure vectors.
ASDL symbol_tables are Clojure maps.


In general, MASR specs are more discriminating,
precise, and detailed than ASDL could ever
permit.


"Spec" is short for "specification." The
noun "specification" derives from the verb "to
specify." "Specify" means "to make specific, to
state clearly and precisely, unambiguously. To
discriminate an object from others that might
seem similar at first glance."


full ASDL : `ASR_2023_APR_06_snapshot`.asdl
https://github.com/rebcabin/masr/blob/main/ASR_2023_APR_06_snapshot.asdl

```c
terms (nodes) in the ASDL grammar (things to the left of equals signs):
    ;;
 1 unit            = TranslationUnit(symbol_table, node*)
 2 symbol          = ... many heads ...
 3 storage_type    = Default | Save | Parameter | Allocatable
 4 access          = Public | Private
 5 intent          = Local | In | Out | InOut | ReturnVar | Unspecified
 6 deftype         = Implementation | Interface
 7 presence        = Required | Optional
 8 abi             = Source | LFortranModule | ... | Intrinsic
09 stmt            = ... many heads ...
10 expr            = ... many heads ...
11 ttype           = Integer(int, dimension*) | ... | FunctionType( ... )
12 restriction_arg = RestrictionArg(identifier, symbol)
13 binop           = Add | Sub | ... | BitRShift
14 logicalbinop    = And | Or | Xor | NEqv | Eqv
15 cmpop           = Eq | NotEq | Lt | LtE | Gt | GtE
16 integerboz      = Binary | Hex | Octal
17 arraybound      = LBound | UBound
18 arraystorage    = RowMajor | ColMajor
19 cast_kind       = RealToInteger | IntegerToReal | ... |
20 dimension       = (expr? start, expr? length)
21 alloc_arg       = (expr a, dimension* dims)
22 attribute       = Attribute(identifier name, attribute_arg *args)
23 attribute_arg   = (identifier arg)
24 call_arg        = (expr? value)
25 tbind           = Bind(string lang, string name)
26 array_index     = (expr? left, expr? right, expr? step)
27 do_loop_head    = (expr? v, expr? start, expr? end, expr? increment)
28 case_stmt       = CaseStmt(expr*, stmt*) | CaseStmt_Range( ... )
29 type_stmt       = TypeStmtName(symbol, stmt*) | ...
30 enumtype        = IntegerConsecutiveFromZero | ... | NonInteger


terms not specified in ASDL:
    ;;
31 symbol_table    = a clojure map
32 dimensions      = dimension*, see below


term-like things that are not terms:
    ;;
 0 atoms           = int, float, bool, nat, bignat
 0 identifier      = specified below
```

# FULL-FORM


A MASR `asr-term` in _full-form_ is an instance
hash-map, aka _entity_, that contains a `::term`
keyword (or equivalent; see EXAMPLE).


Every MASR `asr-term` has a full-form. Most have
sugared forms that are (1) easier for humans to
read and write (2) compatible with ASDL output
from `--show-asr` in lpython and lfortran.


# SUGAR


Sugar comes in three flavors: light, heavy, and
legacy Light sugar employs keyword arguments with
defaults. Heavy sugar employs positional
arguments with defaults for some tail arguments.
Light sugar is unambiguous but more verbose than
heavy sugar. Heavy sugar is the shortest and more
compatible with ASDL, but more risky to write and
much harder to read, especially for long argument
lists as with, say, `Variable` and
`FunctionType.` Legacy sugar is just like heavy
sugar, just requiring fewer tick marks on
symbols. Legacy sugar is the most compatible with
ASDL output from `--show-asr`.


# WHAT ARE TERMS?


MASR _terms_ are models of terms or productions
in the ASDL grammar, items to the left of equals
signs like symbol or stmt in the list above.


EXAMPLE -- all these full-forms mean the same:

always acceptable, if verbose:

```clojure
{:masr.specs/term        :masr.specs/intent,
 :masr.specs/intent-enum 'Unspecified}
```

shorter form, always acceptable:

```clojure
#:masr.specs{:term        :intent,
             :intent-enum 'Unspecified}
```

when in this file or in namespace masr.specs,
via the line (in-ns 'masr.specs):

```clojure
{::term        ::intent,
 ::intent-enum 'Unspecified}
```

if masr.specs is aliased to asr, as
in (:use [masr.specs :as asr]) in core_tests.clj

```clojure
{::asr/term        ::asr/intent,
 ::asr/intent-enum 'Unspecified}
```

# QUALIFIED KEYWORDS AND ::TERM


::term is both a qualified keyword _and_ a
tag-fetching function, which picks the value of
the key ::term from any hash-map. For example,
(::term {... the instance above ...}) produces
::intent.


As a qualified keyword, ::term can name a Clojure
spec. The following spec will check
whether ::intent is a ::term:

```clojure
(s/def ::term qualified-keyword?)
```

EXAMPLE: "intent" is a valid "term"

```clojure
(s/valid? ::term ::intent)
=> true
```

# SPECS ARE A TYPE SYSTEM FOR MASR


Specs can have arbitrary logic. Specs formalize
type-checking for entities of any depth and
richness.


# POLYMORPHIC SPECS FOR TERMS


`defmulti` defines a name, say `term` (no colons),
for a collection of `defmethods` with the same name.
The `defmulti` links the defmulti-defmethod name
`term` to a dispatcher function, here exactly the
tag-fetcher `::term` (with colons), with this
qualified keyword acting in its role of tag-fetcher.
Each defmethod of `term` is also tagged by the value
fetched from an entity via `::term`.`
defmulti/defmethod is a Clojure idiom for
_polymorphism_, a `defmulti` function interface with
many implementations. The interface is the same for
all implementations -- it just accepts a term. The
implementations differ from one to the other. That's
the meaning of "polymorphism" -- one interface, many
implementations.

```clojure
(defmulti term ::term)
```

MASR handles _alternatives_ -- to the right-hand
sides of equals signs in the grammar -- via
_multi-specs_. Multi-specs are to specs as
defmethods are to functions -- one spec interface
to many implementations.


The name of the multi-spec for all terms
is `::asr-term`, a qualified keyword, as must be
the names of all Clojure specs. Multi-specs act
like tagged unions in C -- polymorphic structs.


# NESTED MULTI-SPECS


At the top level, term multi-specs dispatch on
values of the `::term` key, values like `::intent`,
`::symbol`, `::unit`, etc. Defmethods for those
values specify the required keys for entities that
conform to the particular implementation of the
multi-spec.


Some defmethods like `::intent` are very simple,
just checking that an instance like `'Local` or
`'ReturnVar` inhabits a set of allowed intents.
Others, like `::symbol`, have _nested multi-specs_
that dispatch on _heads_, like `Variable` or
`Program`. MASR handles nested multi-specs via some
techniques shown below.


# NAMING CONVENTION FOR MASR MULTI-SPECS


All multi-spec names in MASR, nested or not, begin
with `::asr-...`, as in `::asr-term` (not nested)
and `::asr-ttype-head` (nested in ttypes).

```clojure
(s/def ::asr-term
  (s/multi-spec term ::term))
```

# TELESCOPING SPECS


A given entity (instance hash-map) may be

* an `::asr-term` -- any one of the terms

* a `::symbol` -- a particular one of the several
  terms,

* and a `::Variable` -- a particular one of the
  several symbols.

These three telescoping specs, `::term`, `::symbol`,
`::Variable`, are of increasing precision and
discrimination.


Vertically, in increasing precision, both
`::Variable` and `::symbol` are `::asr-term` and
`::Variable` is a `::symbol`. Horizontally, as
siblings of equal precision, both `::Variable` and
`::Function` are `::symbol`, and both are also
`::asr-term`. For another example, vertically, both
`::LogicalBinOp` and `::expr` are `::asr-term`, and
`::LogicalBinOp` is an `::expr`. Horizontally, both
`::LogicalBinOp` and `::LogicalCompare` are
`::expr`, and both are `::asr-term`.


# TERM ENTITY KEY


Each term, like symbol, needs its own spec, named by
a qualified keyword like `::symbol`. MASR
recursively checks specs when entity keys like
`::symbol` have their own specs. Said another way,
recursive conformance means that `::symbol` fields
in other entities are checked by `::symbol` specs.

```clojure
(defn term-selector-spec
  "Name a spec that checks that an asr-term entity
  has a given kwd as the value, e.g., that the
  ::term of an entity is ::symbol."
  [kwd]
  (s/and ::asr-term
         #(= kwd (::term %))))


(defmacro def-term-entity-key
  "Define spec for entity key like ::symbol or
  ::expr, which is an ::asr-term, a top-level
  production in the grammar."
  [term]
  (let [ns "masr.specs"
        tkw (keyword ns (str term))]
    `(s/def ~tkw    ;; like ::dimension or ::symbol
       (term-selector-spec ~tkw))))
```

# DEFMASRNESTED


Automate construction of nested multi-specs,
removing all duplicated wordage. The docstring of
defmasrnested shows an example with all the
duplicated verbiage that the macro eliminates.


READ ALL DOCSTRINGS.


It is not necessary to understand the
implementation of the macro unless you are
maintaining it. The implementation of the macro
is a bit tricky to understand, like many macros,
due mostly to Clojure's implicit insertion and
deletion of namespaces, which is not easily
predictable. Implicit namespacing is a good
design, overall, but we must be aware of it and
step around it when necessary. We step around it
via the built-in "name" function.

```clojure
(defmacro defmasrnested
  "Define specs for terms with nested multi-specs,
  like ::expr, ::symbol, ::ttype, ::stmt. Also define
  the defmulti's for the nested multi-specs themselves.

  Automate constructions like the following, which
  pertain to certain ::term specs that have nested
  multi-specs like ::expr, ::symbol, ::stmt, etc.
  There is a lot of duplicated wordage like expr,
  term, and head, in the constructions. The macro
  eliminates this duplication. Right after the
  definition of the macro are several examples of
  its usage, one for each term in the grammar that
  has a nested multi-spec.

      (defmethod term ::expr [_]
        (s/keys :req [::term
                      ::asr-expr-head]))
      (def-term-entity-key expr)

      ;; nested multi-spec:
      (defmulti expr-head ::expr-head)
      (s/def ::asr-expr-head
        (s/multi-spec expr-head ::expr-head"
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
```

# TERM-HEAD ENTITY KEY


We need specs for each nested multi-spec
like `::Variable` and `::FunctionType`.

```clojure
(defmacro def-term-head--entity-key
  "Define an entity key like ::Variable, which is an
  ::asr-symbol-head nested multi-spec, again
  eliminating duplicated wordage.

  From a term, e.g., symbol, and head, e.g., Variable,
  generate a spec s/def like

      (s/def ::Variable               ;; head entity key
        (s/and ::asr-term             ;; top multi-spec
          #(= ::Variable              ;; nested tag
              (-> % ::asr-symbol-head ;; nested multi-spec
                    ::symbol-head)))) ;; tag fetcher

  From term \"stmt\", and head \"Assignment\", generate
  a spec s/def like

      (s/def ::Assignment             ;; head entity key
        (s/and ::asr-term             ;; top multi-spec
          #(= ::Assignment            ;; nested tag
              (-> % ::asr-stmt-head   ;; nested multi-spec
                    ::stmt-head       ;; tag fetcher"
  [term, ;; like symbol
   head  ;; like Variable
   ]
  (let [ns "masr.specs"
        trm (keyword ns "term")     ;; like ::term
        art (keyword ns "asr-term") ;; like ::asr-term
        hkw (keyword ns (str head)) ;; like ::Variable
        tmh (keyword ns (str term "-head")) ;; like ::symbol-head
        amh (keyword ns ;; for the multi-spec
                     (str "asr-" term "-head")) ;; like ::asr-symbol-head
        ]
    `(s/def ~hkw
       (s/and ~art #(= ~hkw (-> % ~amh ~tmh))))))
```

# DEFMASRTYPE


`defmasrtype` is the primary way to add new specs
to MASR, that is, for incremental, test-driven
development of MASR. Use `defmasrtype` to define
new specs for terms with nested multi-specs.
Terms without nested multi-specs are few. They
are special cases with hand-written specs.


`defmasrtype` creates both (1) the specs for
particular heads like `Variable` and `Assignment`,
and (2) a function, `->asdl-type`, that extracts
the ASDL type from any instance hash-map.


# AUTOMATED RECURSIVE TYPE CHECKING, AGAIN


MASR automatically type-checks entities before
projecting them back to the less discriminating
and unchecked ASDL types. Recursive type-checking
pertains to terms with and without nested
multi-specs.


# FOR EXTRACTING ASDL-TYPES FROM ENTITIES

```clojure
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
  "Get rid of repetition in an expression like

      (defmethod stmt->asdl-type ::Assignment
          [{::keys [stmt-head     ;; symbol-binding list
                    lvalue        ;;  <~~~ this bit gets repeated
                    rvalue        ;;  <~~~ this bit gets repeated
                    overloaded]}] ;;  <~~~ this bit gets repeated
          (asdl-type-string \"stmt\" (lvalue     ;; <~~~ repeated
                                      rvalue     ;; <~~~ repeated
                                      overloaded ;; <~~~ repeated
                                      )))

  and this (the whole key list is just a transform of the
  symbol-binding list like the one above)

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
        ;; like "Program"
        head-str  (str head)
        ;; like ::Program
        head-key  (keyword ns head-str)
        ;; ::keys
        keys-key  (keyword ns "keys")
        ;; like Program
        head-sym  (symbol head-str)
        ;; like symbol
        term-sym  (symbol (str term))
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
         (def-term-head--entity-key ~term-sym ~head-sym)
         )))
```

# ->ASDL-TYPE


The function `->asdl-type` relies on multimethods
for terms that have a nested multi-spec. The
multimethods dispatch on the "head" keys of each
term-with-nested-multi-spec, terms like
`::symbol-head` and `::expr-head`.

```clojure
(defmulti  ->asdl-type ::term)
(defmacro term->asdl-type
  "Set up a method like

      (defmethod ->asdl-type ::symbol
        ;; bind the symbol asr-symbol-head
        [{::keys [::term ::asr-symbol-head]}]
          (symbol->asdl-type asr-symbol-head))

  for dispatching to functions that extract the ASDL
  types from MASR entities: types from examples."
  [term]
  (let [ns "masr.specs"
        ;; like ::keys
        keys-key (keyword ns "keys")
        ;; like ::symbol or ::stmt, value of term
        mthd-key (keyword ns (str term))
        ;; like asr-symbol-head or asr-stmt-head, a key-symbol
        ;; for destructuring
        nest-ksm (symbol (str "asr-" term "-head"))
        ;; like symbol->asdl-type or stmt->asdl-type
        call-sym (symbol (str term "->asdl-type"))
        ;; don't put the namespace on "term";
        ;; nons-term is a const destructuring key,
        ;; exactly ::term.
        nons-trm (symbol "term")]
    ;; (defmethod ->asdl-type :masr.specs/symbol
    ;;   [#:masr.specs{:keys [term asr-symbol-head]}]
    ;;   (symbol->asdl-type asr-symbol-head))
    `(defmethod ->asdl-type ~mthd-key
       [{~keys-key [~nons-trm ~nest-ksm]}]
       (~call-sym ~nest-ksm))))
```

# TERMS WITH NESTED MULTI-SPECS


The following blocks of code are as close to the
ASDL specs as we care to get in MASR. MASR is
more discriminating than ASDL. For example, the
spec for `Program` in ASDL declares that the name
of the Program is an identifier, but MASR
specifies the name as a `prognym`, giving both a
finer-grained name to the type of a Program name
and an opportunity for further processing.


The discriminating subtype-specs are defined via
`s/def` near each particular case, i.e., near
`Function`, Near `LogicalBinOp`, etc. The following
defmasrtypes just set up the multi-method and
multi-spec infrastructure for further refinement.
These defmasrtypes can refer to those more
discriminating types, like `prognym` and
`left-logical`, before they're defined as specs
via `s/def`.


# ADD NEW DEFINITIONS HERE


## UNIT

```clojure
(defmulti  unit->asdl-type ::unit-head)
(term->asdl-type unit)

(defmasrtype
 TranslationUnit unit
 (SymbolTable
  nodes))
```

## SYMBOL

```clojure
(defmulti  symbol->asdl-type ::symbol-head)
(term->asdl-type symbol) ;; Don't expand in CIDER! console only.

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
```

## STMT

```clojure
(defmulti  stmt->asdl-type ::stmt-head)
(term->asdl-type stmt)   ;; CIDER macro-expand removes namespace.

(defmasrtype
 Assignment stmt
 (lvalue
  rvalue
  overloaded))

(defmasrtype
  SoubroutineCall stmt
  (subr-nym
   orig-nym
   args
   dt))
```

## EXPR

```clojure
(defmulti  expr->asdl-type ::expr-head)
(term->asdl-type expr)   ;;

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
```

## TTYPE

```clojure
(defmulti  ttype->asdl-type ::ttype-head)
(term->asdl-type ttype)

(defmasrtype
 Logical ttype
 (logical-kind
  dimensions))

(defmasrtype
  FunctionType ttype
  (param-types     return-var-type  abi
   deftype         bindc-name       elemental
   pure            module           inline
   static          type-params      restrictions
   is-restriction))
```

# SPECIAL CASES AND SUGAR


## FULL-FORM


Full-form entities that are checked against specs
are Clojure
_hash-maps_:[https://clojuredocs.org/clojure.core/hash-map]
collections of key-value pairs like Python
dictionaries. When checked by multi-specs,
hash-maps are called _entities_. For example,

```clojure
;; key         value
{::term        ::intent,
 ::intent-enum 'Local}
```

In MASR, all keys in all hash-maps are
namespace-qualified keywords. Such keys may have
specs registered for them, or not. When a spec is
registered for a key, automatic recursive
type-checking is invoked.


## LIGHT SUGAR, HEAVY SUGAR, LEGACY SUGAR


_Light-sugar_ forms are shorter than full-form,
but longer and more explicit than _heavy-sugar_.
Light sugar employs functions with keyword
arguments and defaults. Heavy sugar employs
functions with positional arguments and defaults
only at the end of an argument list. Heavy-sugar
functions are thus more brittle, especially for
long specs with many arguments, with high risk of
writing arguments out of order. _Legacy sugar_ is
order-dependent, no keywords, no defaults, and
compatible with `--show-asr` output from current
LCompilers. Legacy sugaar will be deprecated when
MASR is integrated with LCompilers.


The names of light-sugar functions, like `Integer-`,
have a single trailing hyphen. The keyword arguments
of light-sugar functions are partitioned into
required and optional-with-defaults. The keyword
argument lists of light-sugar functions do not
depend on order. The following two examples both
conform to `::asr-term` and to `::ttype`:

```clojure
(Integer- {:dimensions [], :kind 4})
(Integer- {:kind 4, :dimensions []})
```

The names of heavy-sugar functions, like
`Integer` or `Variable--`, have either zero or
two trailing hyphens. The difference concerns
legacy. If a legacy sugar is needed for a term,
the legacy sugar has the name with no hyphens,
like `Variable` and the heavy sugar has the name
with two hyphens, like `Variable--`. Both legacy
sugar and heavy sugar produce identical
full-forms.


For example, The following is heavy sugar for a
`Variable`, representing the more progressive,
desired form:


Heavy Sugar:

```clojure
(Variable-- 2 'x (Integer 4)
            nil [] Local
            [] []  Default
            Source Public Required
            false)
```

Here is a legacy version of the same instance:


Legacy Sugar:

```clojure
(Variable 2 x []
          Local () ()
          Default (Integer 4 []) Source
          Public Required false)
```

Notice NO QUOTE MARK on the name of the variable.
That's the way `--show-asr` prints it. That's the
only difference between heavy sugar and legacy
sugar for _Variable_.


For specs where MASR heavy sugar and ASDL legacy
are identical, like `Integer`, there is only one
function with no trailing hyphens in its name.


Heavy-sugar functions employ positional arguments
that depend on order. Final arguments may have
defaults. For example, the following examples
conform to both `::asr-term` and to `::ttype`:

```clojure
(Integer)
(Integer 4)
(Integer 2 [])
(Integer 8 [[6 60] [1 42]])
```

# DIMENSION


`Dimension` is a term without nested multi-specs.
It is a handwritten special case.


original ASDL:

```c
dimension = (expr? start, expr? length)
```

The ASDL is imprecise. The real spec, realized only
in secret C++ code, is that we have either both
`start` and `length` or we just have nothing. MASR
makes exposes this secret explicitly.


Case with 1 index is disallowed.
https://github.com/rebcabin/masr/issues/5

```clojure
(def MIN-DIMENSION-COUNT 0)
(def MAX-DIMENSION-COUNT 2)
```

The next spec says that a `::dimension-content` is
a collection of `::nat` with either two or zero
elements. TODO Consider a regex-spec.

```clojure
(s/def ::dimension-content
  (s/and
   (s/coll-of ::nat
              :min-count MIN-DIMENSION-COUNT,
              :max-count MAX-DIMENSION-COUNT,
              :into ())
   (fn [it] (not (= 1 (count it))))))
```

The next spec says that a `dimension` in full-form
is an entity hash-map with keys `::term` and
`::dimension-content`.

```clojure
(defmethod term ::dimension [_]
  (s/keys :req [::term
                ::dimension-content]))
```

As usual, we need a term-entity key, `::dimension`,
for recursive type-checking.

```clojure
    (def-term-entity-key dimension)
```

This spec can generate samples.

```clojure
    #_
    (gen/sample (s/gen ::dimension-content) 3)

=> (() (0 0) (1 1))
```

heavy sugar

```clojure
(defn dimension [candidate-contents]
  (if (or (not (coll? candidate-contents))
          (set? candidate-contents)
          (map? candidate-contents))
    ::invalid-dimension  ;; return this,
    ;; else
    (let [cnf (s/conform
               ::dimension
               {::term ::dimension,
                ::dimension-content candidate-contents})]
      (if (s/invalid? cnf)
        ::invalid-dimension
        cnf))))
```

# DIMENSIONS


`Dimensions` [sic] is not a term. Dimensions stands
for `dimension*`, a plurality of dimension. We do a
lot more pluralities later. This is just the first
example of a repeating pattern (TODO: macro?)

```clojure
(def MIN-NUMBER-OF-DIMENSIONS 0)
(def MAX-NUMBER-OF-DIMENSIONS 9)
```

TODO Consider a regex-spec.

```clojure
(s/def ::dimensions
  (s/coll-of (term-selector-spec ::dimension)
             :min-count MIN-NUMBER-OF-DIMENSIONS,
             :max-count MAX-NUMBER-OF-DIMENSIONS,
             :into []))
```

Generation of test cases does not currently work
TODO https://github.com/rebcabin/masr/issues/14

```clojure
#_(gen/sample (s/gen ::dimensions) 3)
```

heavy sugar

```clojure
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
```

# SYMTAB-ID


In ASDL, `symbol_table` sometimes means a
`SymbolTable`, an unwritten spec, and sometimes
means an integer id of a `SymbolTable` specified
elsewhere. MASR does better. MASR `->asdl-type`
projects both of these types, `SymbolTable` and
`symtab-id`, back into ASDL `symbol_table`, with its
secret proviso. MASR exposes the secret. ASDL
embraces the secret.

```clojure
(s/def ::symtab-id ::nat)
```

heavy sugar

```clojure
(defn symtab-id [it]
  (let [cnf (s/conform ::symtab-id it)]
    (if (s/invalid? cnf)
      ::invalid-symtab-id
      cnf)))
```

# SYMBOL-TABLE


`SymbolTable` is an unwritten term. It doesn't have
nested multi-specs. We'll write it out fully by
hand.

```clojure
(s/def ::hash-map map?)


(defmethod term ::SymbolTable [_]
  (s/keys :req [::term
                ::symtab-id
                ::hash-map]))


(def-term-entity-key SymbolTable)
```

heavy sugar

```clojure
(defn SymbolTable [id, hash-map]
  (let [st {::term      ::SymbolTable
            ::symtab-id id
            ::hash-map  hash-map}]
    (if (s/invalid? st)
      ::invalid-symbol-table
      st)))
```

# LEGACY MACRO


The `legacy` macro currently just converts `=`
into `Assignment` in a whole tree. Apply "legacy"
to a whole expression.


TODO: ASDL output from `--show-asr` currently
requires moving colons from the backs of keywords to
the front. That is necessary because colons at the
back fail the Clojure reader. We have a `sed` script
for that: `fix-show-asr.sed`. The script also
converts ASDL's `.false.` to `false` and `.true.` to
`true`, but that could be done at the Clojure level.


Here is `fix-show-asr.sed`:


```sed
s/\(\([_a-zA-Z0-9]*\)\:\)/:\2/g
s/\.\(false\)\./\1/g
s/\.\(true\)\./\1/g
```


TODO: We might rework heavy sugar through the whole
code-base because we must apply `legacy` anyway. For
now, user-level code must call `legacy` when
appropriate.

```clojure
(defn rewrite-for-legacy
  "Replace = with Assignment anywhere in a MASR
  sugared expression."
  [it]
  (prewalk
   (fn [x]
     (if (list? x)
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
```

# ENUM-LIKE


Many ASDL types are like enums: they are just a
set of alternative symbols, without parentheses
and without parameters _qua_ arguments. Example:
ASDL `access` has two possibilities: `Public`
and `Private`. MASR automates all of enum-likes
via one macro.

```clojure
(defmacro enum-like
  "Convert a set of symbols into a multi-spec under
  ::asr-term. Add an entity-key spec like ::intent,
  and a heavy sugar function like intent."
  [term, heads]
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
```

## ABI


`Abi` is a special case of enum-like with rich logic.

```clojure
(def external-abis
  #{'LFortranModule, 'GFortranModule,
    'BindC, 'Interactive, 'Intrinsic})


(def internal-abis #{'Source})


(s/def ::abi-enum (set/union external-abis internal-abis))


(s/def ::abi-external ::bool)
```

full-form

```clojure
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
```

heavy sugar

```clojure
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


(def LFortranModule (abi 'LFortranModule :external true))
(def GFortranModule (abi 'GFortranModule :external true))
(def BindC          (abi 'BindC          :external true))
(def Interactive    (abi 'Interactive    :external true))
(def Intrinsic      (abi 'Intrinsic      :external true))
(def Source         (abi 'Source         :external false))
```

# TTYPE


`Ttype` is a term with nested multi-specs, so it
fits in the macro scheme written at the top of this
file. Its subtypes, `Integer`, `Real`, `Logical`,
etc., have additional structure we automate here.

pluralities

```clojure
(def MIN-NUMBER-OF-TTYPES    0)
(def MAX-NUMBER-OF-TTYPES 1024)
```

TODO: Consider a regex-spec.

```clojure
(s/def ::ttypes (s/coll-of ::ttype
                           :min-count MIN-NUMBER-OF-TTYPES
                           :max-count MAX-NUMBER-OF-TTYPES))
```

TODO: Consider a regex-spec.

```clojure
(s/def ::ttype? (s/coll-of ::ttype
                           :min-count 0
                           :max-count 1))
```

kind: The `kind` member selects the kind of a
given type. We currently support the following:

- Integer kinds: 1 (i8), 2 (i16), 4 (i32), 8 (i64)

- Real kinds: 4 (f32), 8 (f64)

- Complex kinds: 4 (c32), 8 (c64)

- Character kinds: 1 (utf8 string)

- Logical kinds: 1, 2, 4: (boolean represented by
    1, 2, 4 bytes; the default kind is 4, just
    like the default integer kind, consistent
    with Python and Fortran: in Python "Booleans
    in Python are implemented as a subclass of
    integers", in Fortran the "default logical
    kind has the same storage size as the default
    integer"; we currently use kind=4 as default
    integer, so we also use kind=4 for the
    default logical.)

support specs for subtypes

```clojure
(s/def ::integer-kind   #{1 2 4 8 16})
(s/def ::real-kind      #{4 8})
(s/def ::complex-kind   #{4 8})
(s/def ::logical-kind   #{1 2 4})
(s/def ::character-kind #{1})
```

Here are the first four ttypes, which all follow
a common pattern captured in macros. There are
more ttypes later that don't follow that pattern.

```c
ttype
    = Integer(int kind, dimension* dims)
    | Real(int kind, dimension* dims)
    | Complex(int kind, dimension* dims)
    | Logical(int kind, dimension* dims)
```

```clojure
(defmacro def-ttype-head
  "Defmethods for defmulti ttype-head, requiring
  entity keywords ::ttype-head and ::dimensions.
  Automates expressions like

      (defmethod ttype-head ::Integer [_]
        (s/keys :req [::ttype-head
                      ::integer-kind ;; see specs above
                      ::dimensions]))"
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
```

## INTEGER, REAL, COMPLEX, LOGICAL

```clojure
(def-ttype-head Integer)
(def-ttype-head Real)
(def-ttype-head Complex)
(def-ttype-head Logical)
```

TODO: `Character` is more rich
`(def-ttype-head Character)`

heavy sugar

```clojure
(defn ttype
  "Given a conforming full-form ::asr-type-head
  subtype like

      {::ttype-head   ::Integer,
       ::integer-kind 4,
       ::dimensions   []}

  produce a conforming ttype like

      {::term ::ttype,
       ::asr-ttype-head
       {::ttype-head   ::Integer,
        ::integer-kind 4,
        ::dimensions   []}}"
  [it]
  (let [cnf (s/conform ::ttype
                       {::term ::ttype,
                        ::asr-ttype-head it})]
    (if (s/invalid? cnf)
      ::invalid-ttype
      cnf)))
```

light sugar and heavy sugar

```clojure
(defmacro def-ttype-and-head
  "Define light-sugar functions Integer-, Real-,
  etc., that take a full hash-map of arguments,
  e.g.,

      (Integer- {:kind 4, :dimensions []})

  Define heavy-sugar functions like Integer, Real,
  etc., that take positional arguments, with defaults,
  e.g.,

      (Integer 4 [])
      (Integer 4) ;; default scalar ttype
      (Integer)   ;; default 4-byte scalar ttype"
  [it]
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
```

TODO the rest of the ttypes

```c
>>> Integer, Real, Complex, Logical are already done ...
>>> FunctionType is done.
>>> Here are the rest of the ttypes.
| Character(int kind, int len, expr? len_expr, dimension* dims)
| Set(ttype type)
| List(ttype type)
| Tuple(ttype* type)
| Struct(symbol derived_type, dimension* dims)
| Enum(symbol enum_type, dimension *dims)
| Union(symbol union_type, dimension *dims)
| Class(symbol class_type, dimension* dims)
| Dict(ttype key_type, ttype value_type)
| Pointer(ttype type)
| Const(ttype type)
| CPtr()
| TypeParameter(identifier param, dimension* dims)
```

## FUNCTION-TYPE

```c
| FunctionType(ttype*  arg_types,       ;; rename param-types
               ttype?  return_var_type,
               abi     abi,
               deftype deftype,
               string? bindc_name,
               bool    elemental,
               bool    pure,
               bool    module,
               bool    inline,
               bool    static,
               ttype*  type_params,
               symbol* restrictions,
               bool    is_restriction)
```

prerequisite type aliases:

```clojure
(s/def ::param-types     ::ttypes)
(s/def ::return-var-type ::ttype?)
```

* `ABI` is already good enough.
* `deftype` is found above

```clojure
(s/def ::bindc-name      (s/nilable string?))
(s/def ::elemental       ::bool)
(s/def ::pure            ::bool)
(s/def ::module          ::bool)
(s/def ::inline          ::bool)
(s/def ::static          ::bool)
(s/def ::type-params     ::ttypes)
```

pluralities:

`symbol*` is written `symbols`, and restrictions
is a type alias for symbols.

forward reference. Can only test empty `symbol*`
restrictions until `symbol` is properly defined
below.

```clojure
(def MIN-NUMBER-OF-SYMBOLS   0)
(def MAX-NUMBER-OF-SYMBOLS 128)
```

TODO: Consider a regex-spec.

```clojure
(s/def ::symbols (s/coll-of ::symbol
                            :min-count MIN-NUMBER-OF-SYMBOLS
                            :max-count MAX-NUMBER-OF-SYMBOLS))
```

TODO: Consider a regex-spec.

```clojure
(s/def ::symbol? (s/coll-of ::symbol :min-count 0, :max-count 1))
(s/def ::restrictions    ::symbols)
(s/def ::is-restriction  ::bool)
```

heavy sugar

```clojure
(defn FunctionType
  [param-types-     return-var-type-  abi-
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
```

# PLACEHOLDERS


## SYMBOLIC VALUE

```clojure
(s/def ::symbolic-value empty?)
```

sugar

```clojure
(def symbolic-value identity)
```

## VALUE

```clojure
(s/def ::value empty?)
```

sugar

```clojure
(def value identity)
```

# EXPR


pluralities

```clojure
(def MIN-NUMBER-OF-EXPRS    0)
(def MAX-NUMBER-OF-EXPRS 1024)
```

TODO: Consider a regex-spec.

```clojure
(s/def ::exprs (s/coll-of ::expr
                          :min-count MIN-NUMBER-OF-EXPRS
                          :max-count MAX-NUMBER-OF-EXPRS))
```

TODO: Consider a regex-spec.

```clojure
(s/def ::expr? (s/coll-of ::expr
                          :min-count 0
                          :max-count 1))
```

## LOGICAL CONSTANT

```c
(LogicalConstant true (Logical 4 []))
```

heavy sugar

```clojure
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
```

## VAR


Workaround; See Issue #23

Is the parameter `symbol` for `Var` really a `symbol`?
Or just an identifier? #23
https://github.com/rebcabin/masr/issues/23

```c
Var(symbol v)
```

from ASR.asdl doesn't match the instance. Instead,
we probably need something like:

```c
Var(symtab_id stid, identifier it)
```

prerequisite type alias:

```clojure
(s/def ::varnym           ::identifier)
```

heavy sugar

```clojure
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
```

legacy sugar

```clojure
(defmacro Var [stid, unquoted-ident]
  `(Var-- ~stid '~unquoted-ident))
```

TODO: make it look up a value in the
symbol-table! That's part of abstract execution.


## LOGICAL BINOP

```c
| LogicalBinOp(expr left, logicalbinop op, expr
  right, ttype type, expr? value)
```

```clojure
(LogicalBinOp
 (Var 2 a)
 And
 (LogicalCompare
  (Var 2 b)
  Eq
  (Var 2 b)
  (Logical 4 []) ())
 (Logical 4 []) ())
```

prerequisite type aliases:
TODO: check that the types of the exprs are `::Logical`!

```clojure
(s/def ::logical-left  ::expr)
(s/def ::logical-right ::expr)
```

heavy sugar

```clojure
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
```

## LOGICAL COMPARE

```c
| LogicalCompare(expr left,   ;; must have type ::Logical
                 cmpop op,    ;; not all cmpop, only Eq and NotEq
                 expr right,  ;; must have type ::Logical
                 ttype type,
                 expr? value)
```

```clojure
 (LogicalCompare
  (Var 2 b)
  Eq
  (Var 2 b)
  (Logical 4 []) ())
```

heavy sugar

```clojure
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
```


# STMT


pluralities

```clojure
(def MIN-NUMBER-OF-STMTS    0)
(def MAX-NUMBER-OF-STMTS 1024)
```

TODO: Consider a regex-spec.

```clojure
(s/def ::stmts (s/coll-of ::stmt
                          :min-count MIN-NUMBER-OF-STMTS
                          :max-count MAX-NUMBER-OF-STMTS))
```

TODO: Consider a regex-spec.

```clojure
(s/def ::stmt? (s/coll-of ::stmt
                          :min-count 0
                          :max-count 1))
```


## ASSIGNMENT

```c
| Assignment(expr target, expr value, stmt? overloaded)
         --- Var ---
```

See Issues

https://github.com/rebcabin/masr/issues/21
https://github.com/rebcabin/masr/issues/22
https://github.com/rebcabin/masr/issues/26


prerequisite type aliases:

TODO: more cases for `lvalue`, and an `s/or`
with `second` hack

```clojure
(s/def ::lvalue     ::Var)
(s/def ::rvalue     ::expr)
(s/def ::overloaded ::stmt?)
```

heavy sugar

```clojure
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
```

## SUBROUTINE CALL

```c
| SubroutineCall(symbol name,
                 symbol? original_name,
                 call_arg* args,
                 expr? dt)
```


# SYMBOL


## VARIABLE

```c
| Variable(symbol_table   parent_symtab,   ;; really an integer id
           identifier     name,
           identifier   * dependencies,    ;; vector of dependency
           intent         intent,
           expr         ? symbolic_value,  ;; lack specified by nil
           expr         ? value,
           storage_type   storage,
           ttype          type,
           abi            abi,
           access         access,
           presence       presence,
           bool           value_attr)
```

```clojure
(Variable                ;   head, term: symbol
 2                       ;   symbol_table    parent-symtab-id
                                            ;     TODO: NOT SYMBOL-TABLE!
 x                       ;   identifier      nym
 []                      ;   identifier *    dependencies
 Local                   ;   intent          intent
 ()                      ;   expr ?          symbolic-value
 ()                      ;   expr ?          value
 Default                 ;   storage_type    storage
 (Integer 4 [])          ;   ttype           tipe
 Source                  ;   abi             abi
 Public                  ;   access          access
 Required                ;   presence        presence
 .false.)})              ;   bool            value-attr
```

prerequisite type aliases:

```clojure
(s/def ::value-attr       ::bool)
```

`varnym` already defined for Var.
https://github.com/rebcabin/masr/issues/28

```clojure
(s/def ::type-declaration (s/nilable ::symtab-id))
```

TODO: there is ambiguity regarding identifier-sets and lists:

```clojure
(s/def ::dependencies     ::identifier-set)
```

light sugar

```clojure
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
```

heavy sugar

```clojure
(defn Variable--
  "Heavy sugar; parameters that collide with functions
  have trailing hyphens."
  [symtab-id-,         varnym-,        ttype-,
   typedecl-,          dependencies-,  intent-,
   symbolic-value-,    value-,         storage-type-,
   abi-,               access-,        presence-,
   value-attr-]
  (let [cnf (s/conform
             ::Variable
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
```

legacy sugar

```clojure
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
```


## MODULE


```c
| Module(symbol_table symtab, identifier name, identifier* dependencies,
                      bool loaded_from_mod, bool intrinsic)
```

prerequisite type aliases:

```clojure
(s/def ::modulenym       ::identifier)
(s/def ::loaded-from-mod ::bool)
(s/def ::intrinsic       ::bool)
```

heavy sugar

```clojure
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
```

## FUNCTION

```c
| Function(symbol_table symtab,
           identifier   name,
           ttype        function_signature,
           identifier*  dependencies,

           expr*        args,              ;; rename ::params
           stmt*        body,
           expr?        return_var,

           access       access,
           bool         deterministic,
           bool         side_effect_free)
```

prerequisite type aliases:

* `SymbolTable` is already defined

```clojure
(s/def ::function-name      ::identifier)
(s/def ::function-signature ::FunctionType)
```

* `dependencies` is already defined

```clojure
(s/def ::params             ::exprs) ;; renamed from args
(s/def ::body               ::stmts)
(s/def ::return-var         ::expr?)
```

* `access` is already defined

```clojure
(s/def ::deterministic      ::bool)
(s/def ::side-effect-free   ::bool)

(def-term-head--entity-key symbol Function)
```

heavy sugar

```clojure
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
```

legacy sugar

```clojure
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
```

## PROGRAM

```c
= Program(symbol_table symtab,
          identifier   name,
          identifier*  dependencies,
          stmt*        body)
```

prerequisite type alias:

```clojure
(s/def ::prognym ::identifier)
```

heavy sugar

```clojure
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
```

legacy sugar

```clojure
(defmacro Program
  "Quote the nym."
  [stab, nym, deps, body-]
  `(Program-- ~stab, '~nym, ~deps, ~body-))
```

# UNIT


prerequisite type aliases:

`s/conform` slips in the tag keys in from `s/or`,
requiring a step in heavy sugar to remove them.

```clojure
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
```

TODO: Consider a regex-spec.

```clojure
(s/def ::nodes
  (s/and (s/coll-of ::node
                    :min-count MIN-NODE-COUNT,
                    :max-count MAX-NODE-COUNT)))
```

## TRANSLATION UNIT

heavy sugar

```clojure
(defn TranslationUnit [stab, node-preimages]
  (let [node-cnf (map node node-preimages)
        ;; the s/conform slips back in the tag keys from s/or
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
```