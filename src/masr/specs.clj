;;
;;
;; # PROLOGUE
;;
;;


;;
;; This file is semi-literate programming. Blocks of
;; code in this Markdown file, `specs.md`, are
;; extracted from the live source code in
;; `specs.clj`. This document cannot get out-of-date
;; from the code.
;;
;;
;; To contribute, write code in the source files,
;; mostly in `specs.clj`. Format comments in
;; Markdown. Precede a block of code with a
;; double-semicolon-space comment line that says
;; `#+begin_src` by itself, beginning in column 1,
;; then add a blank line. Terminate src blocks with
;; `#+end_src` in a double-semicolon-space comment
;; beginning in column 1. You'll see many examples
;; below.
;;
;;
;; When reading this Markdown file, `specs.md`,
;; backtrack to definitions when you encounter code
;; that needs the definitions. If you read a lot of
;; code, you will be accustomed to such interruption
;; to natural narrative order.
;;
;;
;; When the narrative really MUST talk about code
;; that isn't defined yet, comment out the code or
;; precede s-expressions with `#_`. Clojure will
;; parse but ignore such escaped s-expressions. You
;; will see examples below.
;;
;;
;; Just before checking in new code, extract
;; `specs.md` from `specs.clj` via the following
;; shell command:
;;
;;
;; ```bash
;; awk -f md4code.awk < ./src/masr/specs.clj > specs.md
;; ```
;;
;;
;; Visual Studio Code can maintain Table of Contents
;; and section numbers via an extension called
;; `MarkdownForAll.` Install it. To rebuild the
;; Table of Contents and section numbers in
;; `specs.md`:
;;
;; 1. Run `md4code.awk` as shown above.
;;
;; 2. Open or revert `specs.md` in Visual Studio
;;    Code.
;;
;; 3. Position the cursor at the top of `specs.md`,
;;    then `Cmd-Shift-P`, "Add or Update Section
;;    Numbers."
;;
;; 4. Position the cursor at the top of `specs.md`,
;;    then `Cmd-Shift-P`, "Create Table of
;;    Contents."
;;
;; 5. Save `specs.md` from Visual Studio Code.
;;
;; 6. `git commit` `specs.mf` and `git push` it.
;;
;;
;; The Markdown viewer in Visual Studio Code is
;; _not_ adequate for reading this file. It jumps
;; around too much and sometimes gets stuck. We
;; recommend the Markdown viewer in PyCharm or CLion
;; or other products from JetBrains.
;;
;;
;; In case of emergency _only_, you might rebuild
;; `specs.clj` from `specs.md`:
;;
;;
;; ```bash
;; awk -f code4md.awk < specs.md > ./src/masr/specs.clj
;; ```
;;
;; However, you should not need this emergency step if you
;; have committed frequently.
;;

;;
;; ## Namespace Declaration
;;
;;
;; Because we must define things before using them,
;; we must first declare Clojure dependencies for
;; the rest of the code in this file.
;;
;;
;; #+begin_src clojure

(ns masr.specs
  (:require [clojure.spec.alpha            :as      s            ]
            [clojure.set                   :as      set          ]
            [clojure.spec.gen.alpha        :as      gen          ]
            [clojure.test.check.generators :as      tgen         ]
            [clojure.string                :as      str          ]
            [clojure.pprint                :refer   [pprint     ]]
            [clojure.walk                  :refer   [prewalk    ]]
            #_[clojure.zip                 :as      z           ]
            [masr.specs :as asr])

  (:require [hyperfiddle.rcf               :refer   [tests tap %]]
            [blaster.clj-fstring           :refer   [f-str      ]]
            [camel-snake-kebab.core        :as      csk         ])

  (:require [masr.logic                    :refer   [iff implies]]
            [masr.utils                    :refer   [plnecho
                                                     plnecho-file
                                                     dosafely   ]]
            [masr.simplespecs              :refer   [nat
                                                     identifier-list
                                                     identifier-set
                                                     identifier-suit
                                                     identifier ]]
            ))
;; #+end_src

;;
;; ## Lightweight, Load-Time Testing:
;;
;;
;; #+begin_src clojure

(hyperfiddle.rcf/enable!)
;; #+end_src

;;
;; ## Unmap External Names
;;
;;
;; Unmap `Integer` and `Character` so we can have
;; those symbols in `ttypes`. Access the originals
;; via `java.lang.Integer` `java.lang.Character`. We
;; also want `deftype`. Access original `deftype` as
;; `clojure.core/deftype`.
;;
;;
;; #+begin_src clojure

(ns-unmap *ns* 'Integer)
(ns-unmap *ns* 'Character)
(ns-unmap *ns* 'deftype)
;; #+end_src


;;
;;
;; # MASR OVERVIEW & BACKGROUND
;;
;;


;; ----------------------------------------------------------------
;; ## MASR IS A TYPE SYSTEM
;;
;;
;; MASR is "Meta Abstract Semantics Representation,"
;; and also a physics pun, "Microwave Amplification
;; by Stimulated emission of Radiation." It is a
;; work-in-progress, intended eventually to replace
;; the aging ASDL with by a more precise and
;; discriminating type system.
;;
;; * MASR is less ambiguous than ASDL; For example,
;;   MASR distinguishes names of programs from names
;;   of functions. In ASDL, they're both just
;;   `identifier`.
;;
;; * MASR is more explicit than ASDL. For example,
;;   we can say in MASR an argument list may have no
;;   more than a certain number of arguments.
;;
;; * MASR is more precise and less overloaded. For
;;   example, `symbol` is separate from
;;   `symbol_table`, `symbol-ref` in MASR.
;;
;; * MASR exposes secret semantics that ASDL cannot
;; * express. TODO: example.
;;
;;
;; Until MASR is integrated, it will have a legacy
;; back-channel. The legacy back-channel writes
;; types in ASDL format from MASR instances.
;;
;;
;; We begin with a summary of a snapshot of the full
;; ASDL specification:
;; https://github.com/rebcabin/masr/blob/main/ASR_2023_APR_06_snapshot.asdl
;;

;;
;; ## Terms (Nodes) in the ASDL Grammar
;;
;;
;; Terms are items to the left of equals signs:
;;
;;
;; ```c
;;  1 unit            = TranslationUnit(symbol_table, node*)
;;  2 symbol          = ... many heads ...
;;  3 storage_type    = Default | Save | Parameter | Allocatable
;;  4 access          = Public | Private
;;  5 intent          = Local | In | Out | ... | Unspecified
;;  6 deftype         = Implementation | Interface
;;  7 presence        = Required | Optional
;;  8 abi             = Source | LFortranModule | ... | Intrinsic
;; 09 stmt            = ... many heads ...
;; 10 expr            = ... many heads ...
;; 11 ttype           = Integer(int, dim*) | ... | FunctionType( ... )
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
;; 22 attribute       = Attribute(ident name, attr_arg *args)
;; 23 attribute_arg   = (identifier arg)
;; 24 call_arg        = (expr? value)
;; 25 tbind           = Bind(string lang, string name)
;; 26 array_index     = (expr? left, expr? right, expr? step)
;; 27 do_loop_head    = (expr? v, expr? start, expr? end, expr? incr)
;; 28 case_stmt       = CaseStmt(expr*, stmt*) | CaseStmt_Range( ... )
;; 29 type_stmt       = TypeStmtName(symbol, stmt*) | ...
;; 30 enumtype        = IntegerConsecutiveFromZero | ... | NonInteger
;; ```
;;
;; ### Terms Used but not Defined in ASDL
;;
;; ```c
;; 31 symbol_table    = a clojure map
;; 32 dimension*      = dimension*, see below
;; ```
;;
;; ### Term-Like Items
;;
;; ```c
;;  0 atoms           = int, float, bool, string, nat, bignat
;;  0 identifier      = specified below
;; ```
;;
;; ### Mappings from ASDL to MASR
;;
;; * ASDL tuples like `(1 2)` are Clojure lists or
;;   vectors.
;;
;; * ASDL lists are Clojure lists.
;;
;; * ASDL vectors like `[expr? stmt*]` are Clojure vectors.
;;
;; * ASDL symbol_tables are Clojure maps.


;;
;;
;; # WHAT IS A _SPECIFICATION_?
;;
;;


;;
;; The noun "Specification" derives from the
;; verb "to specify." "To specify" means "to
;; describe specifically, clearly, explicitly,
;; precisely, unambiguously." Also, "to specify"
;; means "to distinguish objects from others that
;; seem similar, say to distinguish ships from
;; boats."
;;
;;
;; The main use-case for specifications is for
;; checking instances against specs: "does this
;; instance hash-map meet the general specification
;; for hash-maps of this type?" For
;; example, "does `(Integer 4 [])`, syntax sugar for
;; a hash-map instance, meet the general
;; specification of an ASR `ttype`, which describes
;; an infinite class of instances?"
;;
;;
;; In MASR, specs describe mathematical _sets_ of
;; valid or conforming values, and MASR type
;; checking is often just checking whether an
;; instance inhabits a certain specified set.
;;
;;
;; Therefore, a type system like MASR's can act like
;; a _set theory_ with respect to instances. See
;; this Stack-Exchange question for the fine points
;; of set theory versus type theory:
;;

;;
;;   https://math.stackexchange.com/questions/489369
;;

;;
;; The important point is that a type theory is a
;; self-contained logic, and set theory is just one
;; such logic. Clojure specs are arbitrary predicate
;; functions. Because we may build any custom logic
;; that predicate logic can support, Clojure specs
;; can be more general than set theory, and suffice
;; for advanced types like dependency types and
;; concurrency types. Such advanced types are
;; work-in-progress for MASR.
;;

;; ----------------------------------------------------------------
;; ## CHECKING INSTANCES
;;
;;

;;
;; An instance hash-map may inhabit multiple sets.
;; For example, any `LogicalConstant` is an `expr`,
;; and any `expr` is an `asr-term`. These sets stand
;; in _subset_ relations. We can also check that
;; `LogicalConstant` is _not_ a `ttype`. Many
;; examples of tests like this are in
;; `core_test.clj`. Reading the tests and stepping
;; through them in the debugger is a great way to
;; learn MASR.
;;
;;
;; MASR types are recursive. Bigger types are
;; defined in terms of smaller types, all the way to
;; a handful of primitive _atomic_ types. All fields
;; of big entities are checked against specs, all
;; the way down to the atoms.
;;


;;
;;
;; # FULL-FORM ENTITY HASH-MAPS
;;
;;


;;
;; Every MASR `asr-term` instance has a full-form. A
;; full-form is a Clojure _hash-map_ that contains
;; the key `::term`. Clojure hash-maps are
;; collections of key-value pairs like Python
;; dictionaries.[https://clojuredocs.org/clojure.core/hash-map]
;;
;;
;; Full-forms that are checked against Clojure specs
;; are _entities_. For example,
;;
;;
;; #+begin_src clojure

;; key         value
{::term        ::intent,
 ::intent-enum 'Local}
;; #+end_src

;;
;; is an entity checked against specs for `::term`,
;; `::intent`, and `::intent-enum`.
;;
;;
;; All hash-map keys in MASR are Clojure _qualified
;; keywords_ in the namespace `masr.specs`, often
;; denoted with double-colons.
;;
;; This file, `specs.clj` creates, defines, and is
;; _in_ namespace `masr.specs`. In any file in that
;; namespace, we denote qualified keywords with
;; double colons Other files, like `core_tests.clj`,
;; might be _in_ other namespaces. Qualified
;; keywords in `masr.specs` must be written out in
;; full in such files, say with an explicit prefix
;; as in `:masr.specs/intent`, or with a namespace
;; alias as in `::asr/intent`. The test file,
;; `core_tests.clj`, employs the namespace alias
;; `asr`.
;;
;;
;; Namespace-qualified keywords may have specs
;; registered for them. When a spec is registered
;; for a namespace-qualified keyword, Clojure
;; automatically checks types of entities
;; recursively.
;;
;;
;; EXAMPLES -- all the following full-forms mean the
;; same:
;;
;; * always acceptable, if verbose:
;;
;;
;; #+begin_src clojure

{:masr.specs/term        :masr.specs/intent,
 :masr.specs/intent-enum 'Unspecified}
;; #+end_src

;;
;; * Clojure-standard shorter form, always
;; * acceptable:
;;
;;
;; #+begin_src clojure

#:masr.specs{:term        :intent,
             :intent-enum 'Unspecified}
;; #+end_src

;;
;; * when in this file or in namespace masr.specs
;;   via the line `(in-ns 'masr.specs)`:
;;
;;
;; #+begin_src clojure

{::term        ::intent,
 ::intent-enum 'Unspecified}
;; #+end_src

;;
;; * if `masr.specs` is aliased to `asr`, as in
;; `(:use [masr.specs :as asr])` in `core_test.clj`:
;;
;;
;; #+begin_src clojure

;; {::asr/term        ::asr/intent,
;;  ::asr/intent-enum 'Unspecified}
;; #+end_src

;;
;; *PITFALL WARNING* -- If you do not register a
;; spec for a qualified keyword, `k`, Clojure will
;; _always pass_ an item in a hash-map with key `k`.
;; Unregistered qualified keywords can lead to
;; _false positive checks_.
;;


;;
;;
;; # SUGAR
;;
;;


;;
;; Most entities have sugared forms that are
;;
;; 1. easier for humans to read and write
;;
;; 2. compatible with ASDL output from `--show-asr` in
;;    lpython and lfortran.
;;
;;
;; Sugar comes in three flavors: _light_, _heavy_,
;; and _legacy_.
;;
;; 1. Light sugar employs functions with
;;    non-qualified-keyword, single-colon arguments
;;    with default values. Light sugar is
;;    unambiguous but more verbose than heavy sugar.
;;
;; 2. Heavy sugar employs functions with positional
;;    arguments, with possible default values for
;;    some tail arguments. Heavy sugar is short and
;;    mostly compatible with ASDL output from
;;    `--show-asr`. Heavy sugar is more risky to
;;    write and much harder to read than light
;;    sugar, especially for long argument lists as
;;    with, say, `Variable` and `FunctionType`.
;;
;; 3. Legacy sugar is just like heavy sugar, just,
;;    say, requiring fewer tick marks on symbols.
;;    Legacy sugar is the most compatible with ASDL
;;    output from `--show-asr`.
;;

;; ----------------------------------------------------------------
;; ## SUGAR NAMING CONVENTION
;;
;;

;;
;; ### Light Sugar
;;
;;
;; The names of light-sugar functions, like
;; `Integer-`, have a single trailing hyphen. The
;; keyword arguments of light-sugar functions are
;; partitioned into _required_ and
;; _optional-with-defaults_. The keyword argument
;; lists of light-sugar functions do not depend on
;; order. The following two examples of light sugar
;; both conform to specs registered for `::asr-term`
;; and to `::ttype`:
;;
;;
;; #+begin_src clojure

#_(Integer- {:dimension* [], :kind 4})
#_(Integer- {:kind 4, :dimension* []})
;; #+end_src

;;
;; ### Heavy Sugar
;;
;;
;; The names of heavy-sugar functions, like
;; `Integer` or `Variable--`, have either zero or
;; two trailing hyphens. The difference concerns
;; legacy. If legacy sugar exists for a term, the
;; legacy sugar has the name with no hyphens, like
;; `Variable`, and the heavy sugar has the name with
;; two hyphens, like `Variable--`. Both legacy sugar
;; and heavy sugar produce identical full-forms.
;;
;;
;; For example, The following is heavy sugar for a
;; `Variable`, similar but not identical to its
;; legacy sugar:
;;
;;
;; #+begin_src clojure

#_
(Variable-- 2 'x (Integer 4)
            nil [] Local
            [] []  Default
            Source Public Required
            false)
;; #+end_src

;; Heavy sugar and legacy sugar employ positional
;; arguments that depend on order. Heavy-sugar
;; functions may have final arguments with defaults.
;; For example, the following examples all conform
;; to both `::asr-term` and to `::ttype`:
;;
;;
;; #+begin_src clojure

#_(Integer)
#_(Integer 4)
#_(Integer 2 [])
#_(Integer 8 [[6 60] [1 42]])
;; #+end_src

;;
;; ### Legacy Sugar
;;
;;
;; The purpose of legacy sugar is to auto-quote
;; symbols and to accommodate certain defects in the
;; original design ASDL, such as a symbol-ref's
;; sometimes being a list and sometimes being a
;; naked par.
;;
;;
;; Here is a legacy version of the Variable above:
;;
;;
;; #+begin_src clojure

#_
(Variable 2 x []
          Local () ()
          Default (Integer 4 []) Source
          Public Required false)
;; #+end_src

;;
;; Notice NO QUOTE MARK on the name of the variable.
;; That's the way `--show-asr` prints it. That's the
;; only difference between heavy sugar and legacy
;; sugar for `Variable`.
;;
;;
;; For specs like `Integer` where heavy sugar and
;; legacy are identical, there is no function with
;; two trailing hyphens in its name.


;;
;;
;; # WHAT ARE TERMS AND HEADS?
;;
;;


;;
;; MASR _terms_ are models of ASDL _productions_.
;; ASDL productions are of the form:
;;
;;
;; `term = alternative_1 | alternative_2 | ...`
;;
;;
;; For example,
;;
;; ```c
;; cast_kind
;; = RealToInteger
;; | IntegerToReal
;; | LogicalToReal
;; | ...
;; ```
;;
;; is an ASDL production.
;;
;;
;; In MASR, alternatives are called _heads_.
;;


;;
;;
;; # QUALIFIED KEYWORDS ARE FUNCTIONS
;;
;;


;;
;; `::term` is both a qualified keyword _and_ a
;; tag-fetching function. A tag-fetching function
;; picks the value of the key `::term` from any
;; hash-map. For example,
;;
;;
;; `(::term {::term ::intent ...})`
;;
;; produces `::intent`.
;;
;;
;; As a qualified keyword, `::term` can name a
;; Clojure spec registered to it via `s/def`. The
;; following spec will check whether `::intent` is a
;; `::term`:
;;
;;
;; #+begin_src clojure

(s/def ::term qualified-keyword?)
;; #+end_src

;;
;; EXAMPLE: `::intent` is a valid `::term` because
;; `::intent` is a qualified keyword.
;;
;;
;; #+begin_src clojure

(s/valid? ::term ::intent)
;; => true
;; #+end_src


;;
;;
;; # POLYMORPHIC SPECS FOR TERMS
;;
;;


;;
;; `defmulti` defines a name, say `term` (no
;; colons), for a collection of `defmethods` also
;; named `term`. `defmulti` links the name `term` to
;; a dispatcher function, here exactly the
;; tag-fetcher `::term` (with colons). Each
;; `defmethod` of `term` is tagged by the value
;; fetched from an entity via `::term`.
;; `defmulti/defmethod` is a Clojure idiom for
;; _polymorphism_:, a single `defmulti` interface
;; with many implementations. The interface is the
;; same for all implementations -- any
;; implementation just accepts a `::term` entity.
;; The implementations differ from one entity to the
;; other, say `::symbol` from `::expr`.
;;
;;
;; #+begin_src clojure

(defmulti term ::term)
;; #+end_src

;;
;; MASR handles _alternatives_, or _heads_ as we
;; call them -- to the right-hand sides of equals
;; signs in the grammar -- via _multi-specs_.
;; Multi-specs are to specs as `defmethods` are to
;; functions -- one spec interface to many
;; implementations.
;;
;;
;; The name of the one multi-spec for all terms is
;; `::asr-term`, a qualified keyword. Multi-specs
;; act like tagged unions in C -- MASR's polymorphic
;; entities are like polymorphic structs in C.
;;


;;
;;
;; # NESTED MULTI-SPECS
;;
;;


;;
;; At the top level, `::term` multi-specs dispatch
;; on values of the `::term` key in entities, values
;; like `::intent`, `::symbol`, `::unit`, etc.
;; `Defmethods` for those values specify the
;; remaining required keys for the particular
;; entities conforming to the particular
;; `defmethod`.
;;
;;
;; Some `defmethods` like `::intent` are simple,
;; just checking that an instance like `'Local` or
;; `'ReturnVar` inhabits a set of allowed intents.
;; Other `defmethods`, like `::symbol`, have _nested
;; multi-specs_ that dispatch on _heads_, like
;; `Variable` or `Program`. MASR handles nested
;; multi-specs via techniques shown below.
;;


;;
;;
;; # NAMING CONVENTION FOR MULTI-SPECS
;;
;;


;;
;; All multi-spec names in MASR, nested or not, begin
;; with `::asr-...`, as in `::asr-term` (not nested)
;; and `::asr-ttype-head` (nested in ttypes).
;;
;;
;; #+begin_src clojure

(s/def ::asr-term
  (s/multi-spec term ::term))
;; #+end_src


;;
;;
;; # TELESCOPING SPECS
;;
;;


;;
;; A given entity (instance hash-map) may be
;;
;; * an `::asr-term` -- any one of the terms
;;
;; * a `::symbol` -- a particular one of the several
;;   terms,
;;
;; * and a `::Variable` -- a particular one of the
;;   several heads or alternatives of `::symbol`.
;;
;;
;; These three telescoping specs, `::asr-term`,
;; `::symbol`, `::Variable`, are of increasing
;; precision and discrimination. These specs _qua_
;; types are isomorphic to sets that stand in strict
;; subset relations: there are asr-terms that are
;; not symbols, and there are symbols that are not
;; Variables.
;;
;;
;; Vertically, in increasing precision, both
;; `::Variable` and `::symbol` are `::asr-term` and
;; `::Variable` is a `::symbol`. Horizontally, as
;; siblings of equal precision, both `::Variable` and
;; `::Function` are `::symbol`, and both are also
;; `::asr-term`. For another example, vertically, both
;; `::LogicalBinOp` and `::expr` are `::asr-term`, and
;; `::LogicalBinOp` is an `::expr`. Horizontally, both
;; `::LogicalBinOp` and `::LogicalCompare` are
;; `::expr`, and both are `::asr-term`.
;;


;;
;;
;; # TERM ENTITY KEY
;;
;;


;;
;; Each term, like symbol, needs its own spec, named
;; by a qualified keyword like `::symbol`. MASR
;; recursively checks specs when entity keys like
;; `::symbol` have their own specs or multi-specs.
;; Said another way, recursive conformance means
;; that `::symbol` fields in other entities are
;; checked by `::symbol` specs.
;;
;;
;; #+begin_src clojure

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
    `(s/def ~tkw    ;; e.g. ::dimension or ::symbol
       (term-selector-spec ~tkw))))
;; #+end_src


;;
;;
;; # DEFMASRNESTED
;;
;;


;;
;; MASR automates construction of nested
;; multi-specs, removing duplicated wordage. The
;; docstring of `defmasrnested` shows an example.
;;
;;
;; **READ ALL DOCSTRINGS**
;;
;;
;; It is not necessary to understand the
;; implementation of `defmasrnested` unless you are
;; maintaining it. The macro is tricky to understand
;; due mostly to Clojure's implicit insertion and
;; deletion of namespaces . Implicit namespacing is
;; a good design, overall, but we must step around
;; it when necessary via Clojure's built-in `name`
;; function.
;;
;;
;; #+begin_src clojure

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
        ttrm (keyword ns "term") ;; e.g. ::term
        tcst (symbol "term")     ;; e.g. term (no ns!?!)

        tstr (str term)          ;; e.g. "expr"
        tkwd (keyword ns tstr)   ;; e.g. ::expr
        tsym (symbol (name (symbol tstr))) ;; e.g. expr -- caution

        estr (str term "-head")  ;; e.g. "expr-head"
        ekwd (keyword ns estr)   ;; e.g. ::expr-head
        esym (symbol (name (symbol estr))) ;; e.g. expr-head

        ;; e.g. ::asr-expr-head
        akwd (keyword ns (str "asr-" (name term) "-head"))
        ]
    `(do (defmethod ~tcst ~tkwd [_#]
           (s/keys :req [~ttrm ~akwd]))
         (def-term-entity-key ~tsym) ;; caution
         (defmulti ~esym ~ekwd)
         (s/def ~akwd
           (s/multi-spec ~esym ~ekwd))
         )))
;; #+end_src

;;
;; ## Use of `defmasrnested`
;;
;;
;; #+begin_src clojure

(defmasrnested expr)
(defmasrnested stmt)
(defmasrnested symbol)
(defmasrnested ttype)
(defmasrnested unit)
;; #+end_src


;;
;;
;; # TERM-HEAD ENTITY KEY
;;
;;


;;
;; We need specs for each nested multi-spec
;; like `::Variable` and `::FunctionType`.
;;
;;
;; #+begin_src clojure

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
  [term, ;; e.g. symbol
   head  ;; e.g. Variable
   ]
  (let [ns "masr.specs"
        trm (keyword ns "term")     ;; e.g. ::term
        art (keyword ns "asr-term") ;; e.g. ::asr-term
        hkw (keyword ns (str head)) ;; e.g. ::Variable
        tmh (keyword ns (str term "-head")) ;; e.g. ::symbol-head
        amh (keyword ns ;; for the multi-spec
                     ;; e.g. ::asr-symbol-head
                     (str "asr-" term "-head"))]
    `(s/def ~hkw
       (s/and ~art #(= ~hkw (-> % ~amh ~tmh))))))
;; #+end_src


;;
;;
;; # DEFMASRTYPE
;;
;;


;;
;; `defmasrtype` is the easiest way to add new specs
;; that have nested multi-specs. Terms without
;; nested multi-specs are few. They are special
;; cases with hand-written specs.
;;
;;
;; `defmasrtype` creates both (1) the specs for
;; particular heads like `Variable` and `Assignment`,
;; and (2) a function, `->asdl-type`, that extracts
;; the ASDL type from any instance hash-map.
;;

;; ----------------------------------------------------------------
;; ## EXTRACTING ASDL FROM MASR
;;
;;
;; #+begin_src clojure

(def asdl-types
  {
   ::SymbolTable  "symbol_table stab"
   ::body         "stmt* body"
   ::call-args    "call_arg* args"
   ::dependencies "identifier* dependencies"
   ::dimension*   "dimension* dims"
   ::dt?          "expr? dt"
   ::logical-kind "int kind"
   ::logicalbinop "logicalbinop"
   ::lvalue       "expr target"
   ::symbol-ref   "symbol name"
   ::orig-symref  "symbol? original_name"
   ::overloaded   "stmt? overloaded"
   ::prognym      "identifier program_name"
   ::rvalue       "expr value"
   ::symtab-id    "symbol_table stid" ;; TODO: this is TERRIBLE
   ::varnym       "identifier varnym"
   })
;; #+end_src

;; #+begin_src clojure

(defmacro asdl-type-string
  "Construct a string like this
  Assignment(expr target, expr value, stmt? overloaded)"
  ;; term is a string like "symbol" or "stmt" in quotes
  [term sqkeysyms]
  (let [ns "masr.specs"
        ;; e.g. symbol-head or stmt-head
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
;; #+end_src

;; #+begin_src clojure

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
                ))"
  ;; e.g.
  ;; Program symbol (SymbolTable prognym dependencies body)
  [head, term, keyseq]
  (let [ns "masr.specs"
        ;; e.g. "Program"
        head-str  (str head)
        ;; e.g. ::Program
        head-key  (keyword ns head-str)
        ;; exactly ::keys
        keys-key  (keyword ns "keys")
        ;; e.g. Program
        head-sym  (symbol head-str)
        ;; e.g. symbol
        term-sym  (symbol (str term))
        ;; e.g. symbol-head
        term-head (symbol (str term "-head"))
        ;; e.g. symbol->asdl-type
        mthd-nym  (symbol (str term "->asdl-type"))
        ;; e.g. (symbol-head, SymbolTable, ..., body)
        sym-list  (conj keyseq term-head)
        ;; e.g. [symbol-head, SymbolTable, ..., body]
        ;; for destructuring:
        parm-vec  (vec sym-list)
        ;; e.g. (::symbol-head, ::SymbolTable, ..., ::body)
        ;; for entity specs via s/keys
        key-list  (map #(keyword ns (str %)) sym-list)
        ;; e.g. [::symbol-head, ::SymbolTable, ..., ::body]
        ;; for entity specs via s/keys
        key-vec   (vec key-list)]
    `(do ;; defmethod symbol->asdl-type ::Program
       (defmethod ~mthd-nym ~head-key
         ;; [{::keys [symbol-head, SymbolTable, ..., body]}]
         [{~keys-key ~parm-vec}]
         ;;              symbol (SymbolTable, ..., body)
         (asdl-type-string ~term ~keyseq))
       ;; defmethod symbol ::program [_]
       (defmethod ~term-head ~head-key [_#]
         ;; [::symbol-head, ::SymbolTable, ..., ::body]
         (s/keys :req ~key-vec))
       ;; e.g.                     symbol    Program
       (def-term-head--entity-key ~term-sym ~head-sym)
       )))
;; #+end_src


;;
;;
;; # TO ASDL-TYPE
;;
;;


;;
;; The function `->asdl-type` relies on multimethods
;; for nested multi-specs. The multimethods dispatch
;; on the _head_ keys of each multi-spec, like
;; `::symbol-head` and `::expr-head`.
;;
;;
;; #+begin_src clojure

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
        ;; e.g. ::keys
        keys-key (keyword ns "keys")
        ;; e.g. ::symbol or ::stmt, value of term
        mthd-key (keyword ns (str term))
        ;; e.g. asr-symbol-head or asr-stmt-head, a key-symbol
        ;; for destructuring
        nest-ksm (symbol (str "asr-" term "-head"))
        ;; e.g. symbol->asdl-type or stmt->asdl-type
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
;; #+end_src


;;
;;
;; # ADD NEW DEFINITIONS HERE
;;
;;

;; ----------------------------------------------------------------
;; ## UNIT
;;
;;
;; #+begin_src clojure

(defmulti  unit->asdl-type ::unit-head)
(term->asdl-type unit)

(defmasrtype
  TranslationUnit unit
  (SymbolTable    nodes))
;; #+end_src

;; ----------------------------------------------------------------
;; ## SYMBOL
;;
;;
;; #+begin_src clojure

(defmulti  symbol->asdl-type ::symbol-head)
(term->asdl-type symbol) ;; Don't expand in CIDER! console only.
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Program symbol
  (SymbolTable    prognym    dependencies    body))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Module symbol
  (SymbolTable
   modulenym       dependencies    loaded-from-mod
   intrinsic))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Function symbol
  (SymbolTable ;; not a symtab-id!
   function-name    function-signature    dependencies
   param*           body                  return-var?
   access           deterministic         side-effect-free
   ))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  GenericProcedure symbol
  (symtab-id
   function-name
   symbol-ref*
   access))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ExternalSymbol symbol
  (symtab-id
   nym          extern-symref
   modulenym    scope-nyms       orig-nym
   access))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Variable symbol
  (symtab-id
   varnym              dependencies
   intent              symbolic-value    value?
   storage-type        ttype             abi
   access              presence          value-attr
   type-declaration))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Block symbol
  (SymbolTable
   blocknym
   body))
;; #+end_src

;; #+begin_src clojure

;; not in ASDL
(defmasrtype
  IntrinsicModule symbol
  (modulenym))
;; #+end_src

;; ----------------------------------------------------------------
;; ## STMT
;;
;;
;; #+begin_src clojure

(defmulti  stmt->asdl-type ::stmt-head)
(term->asdl-type stmt)   ;; CIDER macro-expand removes namespace.

(defmasrtype
  Assignment stmt
  ;; types of the attributes:
  (lvalue    rvalue    overloaded))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ExplicitDeallocate stmt
  (vars))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  DoLoop stmt
  (escape-target ;; UNCHECKED! NO SPEC!
   do-loop-head ;; NOT AN ASR HEAD!
   body))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  GoTo stmt
  (goto-target identifier))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  If stmt
  (test-expr body orelse))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Print stmt
  (format? value* separator? end?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Return stmt
  ())
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Assert stmt
  (test-expr message?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  SubroutineCall stmt
  (symbol-ref    orig-symref    call-args    dt?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Block stmt
  (label symbol-ref))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  WhileLoop stmt
  (escape-target ;; NO SPEC! NO TARGET!
   test-expr    body))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  BlockCall stmt
  (label
   symbol-ref))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ListAppend stmt
  (list-expr    list-element))
;; #+end_src

;; ----------------------------------------------------------------
;; ## EXPR
;;
;;
;; #+begin_src clojure

(defmulti  expr->asdl-type ::expr-head)
(term->asdl-type expr)
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  IfExp expr
  (test-expr body orelse ttype value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  NamedExpr expr
  (target value ttype))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  FunctionCall expr
  (symbol-ref    orig-symref    call-args
                 return-type    value?    dt?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  IntrinsicFunction expr
  (intrinsic-ident    expr*          overload-id
                      return-type    value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  IntegerConstant expr
  (int    Integer))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  IntegerBitNot expr
  (integer-expr, Integer, integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  IntegerUnaryMinus expr
  (integer-expr, Integer, integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  IntegerCompare expr
  (integer-left    integer-cmpop   integer-right
                   Logical         logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  IntegerBinOp expr
  (integer-left    integer-binop   integer-right
                   Integer         integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  RealConstant expr
  (float    Real))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  RealUnaryMinus expr
  (real-expr, Real, real-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  RealCompare expr
  (real-left       real-cmpop      real-right
                   Logical         logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  RealBinOp expr
  (real-left       real-binop       real-right
                   Real             real-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexConstant expr
  (real-part    imaginary-part    Complex))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexUnaryMinus expr
  (complex-expr, Complex, complex-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexCompare expr
  (complex-left    complex-cmpop   complex-right
                   Logical         logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexBinOp expr
  (complex-left    complex-binop    complex-right
                   Complex          complex-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  LogicalConstant expr
  (bool    Logical))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  LogicalNot expr
  (logical-expr, Logical, logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  LogicalCompare expr
  (logical-left    logical-cmpop   logical-right
                   Logical         logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  LogicalBinOp expr
  (logical-left    logicalbinop    logical-right
                   Logical         logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ListConstant expr
  (expr* ttype))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ListLen expr
  (list-expr Integer integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  TupleConstant expr
  (elements ttype))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  TupleLen expr
  (tuple-expr Integer integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  TupleCompare expr
  (tuple-left any-cmpop tuple-right
              Logical logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  StringConstant expr
  (string Character))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  StringCompare expr
  (string-left     string-cmpop    string-right
                   Logical         logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  StringRepeat expr
  (string-expr integer-expr Character string-expr?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  StringLen expr
  (string-expr Integer integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  StringItem expr
  (string-expr index?
               Integer
               integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  StringSection expr
  (string-expr index-start?
               index-end?
               index-step?
               Character
               string-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  StringOrd expr
  (string-expr Integer integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  StringChr expr
  (string-expr Character string-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Var expr
  (symtab-id    varnym))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ArrayConstant expr
  (expr*
   ttype
   array-storage))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ArrayItem expr
  (array-expr
   array-index*
   ttype
   array-storage
   expr?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ArrayReshape expr
  (array-expr
   array-shape
   ttype
   array-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Cast expr
  (arg cast-kind ttype value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ListItem expr
  (list-expr index ttype value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  TupleItem expr
  (tuple-expr index ttype value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexRe expr
  (complex-expr    Real    real-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexIm expr
  (complex-expr    Real    real-value?))
;; #+end_src

;; ----------------------------------------------------------------
;; ## TTYPE
;;
;;
;; #+begin_src clojure

(defmulti  ttype->asdl-type ::ttype-head)
(term->asdl-type ttype)

(defmasrtype
  Complex ttype
  ;; types of the attributes:
  (complex-kind dimension*))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Integer ttype
  (integer-kind dimension*))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Logical ttype
  (logical-kind dimension*))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Real ttype
  (real-kind dimension*))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Tuple ttype
  (ttype*))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  List ttype
  (ttype))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Set ttype
  (ttype))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Character ttype
  (character-kind len disposition len-expr? dimension*))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  FunctionType ttype
  (param-type*
   return-var-type?    abi
   deftype            bindc-name     elemental
   pure               module         inline
   static             type-param*    restrictions
   is-restriction))
;; #+end_src


;;
;;
;; # REWRITING FOR LEGACY
;;
;;


;;
;; TODO: ASDL output from `--show-asr` currently
;; requires moving colons from the backs of keywords to
;; the front. That is necessary because colons at the
;; back fail the Clojure reader. We have a `sed` script
;; for that: `fix-show-asr.sed`. The script also
;; converts ASDL's `.false.` to `false` and `.true.` to
;; `true`, but that could be done at the Clojure level.
;;
;;
;; Here is `fix-show-asr.sed`:
;;
;;
;; ```sed
;; s/\~/__TILDE__/g
;; s/\@/__AT__/g
;; s/\(\([_a-zA-Z0-9]*\)\:\)/:\2/g
;; s/\.\(false\)\./\1/g
;; s/\.\(true\)\./\1/g
;; ```
;;
;;
;; The function `rewrite-for-legacy` converts `=`
;; into `Assignment` in a whole tree and converts
;; nested call syntax into vectors. `eval`, in the
;; namespace `masr.specs`, applies all the sugar
;; functions to an expression. Call `asr-eval` to do
;; that. The `legacy` macro simply quotes a whole
;; sugared expression.
;;
;; #+begin_src clojure

(defn rewrite-for-legacy
  "Replace = with Assignment anywhere in a MASR
  sugared expression. Replace nested lists with
  vectors of lists."
  [it]
  (prewalk
   (fn [x]
     (if (list? x)
       ;; no nested fn calls as with ((Var 42 i))
       (if (list? (first x))
         ;; Replace ((Var 42 i)) with [(Var 42 i)]...
         (vec x) ;; ... and other such cases.
         (replace {'= 'Assignment} x))
       x))
   it))
;; #+end_src

;; #+begin_src clojure

(defn asr-eval
  [sexp]
  (do (in-ns 'masr.specs)
      (eval (rewrite-for-legacy sexp))))
;; #+end_src

;; #+begin_src clojure

(defmacro legacy
  "DANGER: Call 'eval'."
  [it]
  `(asr-eval '~it))
;; #+end_src


;;
;;
;; # IMPLEMENTATIONS
;;
;;


;;
;; The remaining sections of this document describe
;; detailed implementations.
;;


;;
;;
;; # CALL-ARG
;;
;;

;;
;; ## Issues
;;
;; https://github.com/rebcabin/masr/issues/32
;; `call-arg` intentionally introduces a level of
;; nesting to a list of actual arguments to a
;; function call or subroutine call.
;;
;;
;; ## Original ASDL
;;
;; ```c
;; call_arg = (expr? value)
;; ```
;;
;;
;; #+begin_src clojure

(s/def ::call-arg
  (s/coll-of ::expr?
             :min-count 1   ;; Issue 32
             :max-count 1))

(s/def ::call-args (s/coll-of ::call-arg))

(s/def ::call-arg-or-args
  (s/or :call-arg  ::call-arg
        :call-args ::call-args))
;; #+end_src

;;
;; ## Examples
;;
;;
;; Examples can't be executed until `expr?` is
;; defined. See discussion in `SubroutineCall.`


;;
;;
;; # DIMENSION
;;
;;


;;
;; `Dimension` is a term without nested multi-specs.
;; It is a handwritten special case, not defined via
;; `defmasrtype`.
;;

;;
;; ## Original ASDL
;;
;; ```c
;; dimension = (expr? start, expr? length)
;; ```
;;
;;
;; The ASDL is imprecise. The real spec, realized only
;; in secret C++ code, is that we have either both
;; `start` and `length` or we just have nothing. MASR
;; makes exposes this secret explicitly.
;;

;;
;; ## Dimension-Content
;;
;;

;;
;; The next spec says that a `::dimension-content` is
;; a collection of `::nat` with either two or zero
;; elements. TODO Consider a regex-spec.
;;
;;
;; #+begin_src clojure

(def MIN-DIMENSION-COUNT 0)
(def MAX-DIMENSION-COUNT 2)

(s/def ::dimension-content
  (s/and
   (s/coll-of ::nat
              :min-count MIN-DIMENSION-COUNT,
              :max-count MAX-DIMENSION-COUNT,
              :into ())
   (fn [it] (not (= 1 (count it))))))
;; #+end_src

;;
;; ## Full-Form
;;
;;

;;
;; The next spec says that a `dimension` in full-form
;; is an entity hash-map with keys `::term` and
;; `::dimension-content`.
;;
;;
;; #+begin_src clojure

(defmethod term ::dimension [_]
  (s/keys :req [::term
                ::dimension-content]))
;; #+end_src

;;
;; As usual, we need a term-entity key, `::dimension`,
;; for recursive type-checking.
;;
;;
;; #+begin_src clojure

(def-term-entity-key dimension)
;; #+end_src

;;
;; This spec can generate samples.
;;
;;
;; #+begin_src clojure

#_
(gen/sample (s/gen ::dimension-content) 3)

;; => (() (0 0) (1 1))
;; #+end_src

;;
;; ## Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn dimension [candidate-contents]
  (if (or (not (coll? candidate-contents))
          (set? candidate-contents)
          (map? candidate-contents))
    ::invalid-dimension ;; return this,
    ;; else
    {::term ::dimension,
     ::dimension-content candidate-contents}
    ))
;; #+end_src


;;
;;
;; # DIMENSION*
;;
;;


;; #+begin_src clojure

(s/def ::dimension*
  (s/coll-of (term-selector-spec ::dimension),
             :into []))
;; #+end_src

;;
;; Generation of test cases does not currently work
;; TODO https://github.com/rebcabin/masr/issues/14
;;
;;
;; #+begin_src clojure

#_(gen/sample (s/gen ::dimension*) 3)
;; #+end_src

;;
;; ## Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn dimension*
  [candidate-contents]
  (if (or (not (coll? candidate-contents))
          (set? candidate-contents)
          (map? candidate-contents))
    ::invalid-dimension*
    ;; else
    (let [dims-coll (map dimension candidate-contents)]
      (if (s/valid? ::dimension* dims-coll)
        (let [dims-cont (map
                         ::dimension-content
                         dims-coll)]
          (map dimension dims-cont))
        ::invalid-dimension*))))
;; #+end_src


;;
;;
;; # SYMTAB-ID
;;
;;


;;
;; In ASDL, `symbol_table` sometimes means a
;; `SymbolTable` and sometimes means an integer id of a
;; `SymbolTable` that is specified elsewhere. MASR does
;; better. MASR `->asdl-type` projects both of these
;; types, `SymbolTable` and `symtab-id`, back into ASDL
;; `symbol_table`, with its secret proviso. MASR
;; exposes the secret. ASDL embraces the secret.
;;
;;
;; #+begin_src clojure

(s/def ::symtab-id ::nat)
;; #+end_src

;;
;; ## Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn symtab-id [it] it)
;; #+end_src


;;
;;
;; # SYMBOL TABLE
;;
;;


;;
;; `SymbolTable` is an unwritten term. It doesn't have
;; nested multi-specs. Write it out fully by hand.
;;
;;
;; #+begin_src clojure

(s/def ::hash-map map?)

(defmethod term ::SymbolTable [_]
  (s/keys :req [::term
                ::symtab-id
                ::hash-map]))


(def-term-entity-key SymbolTable)
;; #+end_src

;;
;; ## Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn SymbolTable [id, hash-map]
  {::term      ::SymbolTable
   ::symtab-id id
   ::hash-map  hash-map})
;; #+end_src


;;
;;
;; # ENUM-LIKE
;;
;;


;;
;; Many ASDL types are like enums: they are just a
;; set of alternative symbols, without parentheses
;; and without parameters _qua_ arguments. Example:
;; ASDL `access` has two possibilities: `Public`
;; and `Private`. MASR automates all of enum-likes
;; via one macro, `enum-like`.
;;

;;
;; ## Helpers for Enum-Like
;;
;;
;; #+begin_src clojure

(defmacro symbolate
  "ASDL Back-Channel: create unquoted constants such as
  Local for 'Local."
  [a-set-syms]
  (let [a-set (eval a-set-syms)
        cmds (for [e a-set] (list 'def e `'~e))]
    `(list ~@cmds)))
;; #+end_src

;; #+begin_src clojure

(defmacro legacicate
  "ASDL Back-Channel: create legacy function-calls
  for each constant, such as Local for (intent 'Local).
  This works only because all heads are unique -- no
  collisions in ASR."
  [term,       ;; e.g. intent
   a-set-syms] ;; e.g. #{Local, In, Out, ,,,}
  (let [a-set (eval a-set-syms)
        cmds  (for [e a-set] (list 'def e `(~term '~e)))]
    `(list ~@cmds)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## Enum-Like, Proper
;;
;;

;; #+begin_src clojure

(defmacro enum-like
  "Convert a set of symbols into a multi-spec under
  ::asr-term. Add an entity-key spec like ::intent,
  and a heavy sugar function like intent."
  [term, heads]
  (let [ns "masr.specs"                         ;; -- Examples --
        trm (keyword ns "term")                 ;; ::term
        art (keyword ns "asr-term")             ;; ::asr-term
        tkw (keyword ns (str term))             ;; ::intent
        tke (keyword ns (str term "-enum"))     ;; ::intent-enum
        ]
    `(do
       (s/def ~tke ~heads)
       ;; for the multi-spec
       (defmethod term ~tkw [_#]
         (s/keys :req [~trm ~tke]))
       ;; the entity-key spec
       (s/def ~tkw ;; e.g. ::intent
         (s/and ~art ;; e.g. ::asr-term, i.e., the multi-spec
                ;; e.g. the predicate #(= ::intent (::term %))
                (term-selector-spec ~tkw)))
       (defn ~term [it#] ;; the sugar
         {~trm ~tkw
          ~tke it#})
       #_(symbolicate ~heads)
       (legacicate ~term ~heads)
       )))
;; #+end_src

;;
;; ## Most Enum-Likes
;;
;;

;; #+begin_src clojure

(def logical-binops #{'And  'Or  'Xor  'NEqv  'Eqv})
(def real-binops    #{'RAdd 'RSub 'RMul 'RDiv 'RPow})
(def complex-binops #{'CAdd 'CSub 'CMul 'CDiv 'CPow})
(def integer-binops #{'Add 'Sub 'Mul 'Div 'Pow
                      'BitAnd 'BitOr 'BitXor
                      'BitLShift 'BitRShift})
;; #+end_src

;; #+begin_src clojure

(def logical-cmpops #{'LEq 'LNotEq
                      ;; some weird ones: see Issue #38
                      'LLt 'LLtE 'LGt 'LGtE})
(def real-cmpops    #{'REq 'RNotEq 'RLt 'RLtE 'RGt 'RGtE})
(def complex-cmpops #{'CEq 'CNotEq})
(def integer-cmpops #{'Eq 'NotEq 'Lt 'LtE 'Gt 'GtE})
(def string-cmpops  #{'SEq 'SNotEq 'SLt 'SLtE 'SGt 'SGtE})
(def all-cmpops     (set/union logical-cmpops
                               real-cmpops
                               complex-cmpops
                               integer-cmpops
                               string-cmpops))
;; #+end_src

;;
;; Collisions of names are NOT ALLOWED!
;; See Legacy Sugar for `RealBinOp.`
;;

;; #+begin_src clojure

(enum-like logicalbinop  logical-binops)
(enum-like real-binop    real-binops)
(enum-like complex-binop complex-binops)
(enum-like integer-binop integer-binops)

(s/def ::any-binop (set/union logical-binops
                              real-binops
                              complex-binops
                              integer-binops))
;; #+end_src

;;
;; Collisions of names are NOT ALLOWED!
;; See Legacy Sugar for `LogicalCompare.`
;;

;; #+begin_src clojure

(enum-like logical-cmpop logical-cmpops)
(enum-like real-cmpop    real-cmpops)
(enum-like complex-cmpop complex-cmpops)
(enum-like integer-cmpop integer-cmpops)
(enum-like string-cmpop  string-cmpops)

(s/def ::any-cmpop (set/union logical-cmpops
                              real-cmpops
                              complex-cmpops
                              integer-cmpops
                              string-cmpops))
;; #+end_src

;; #+begin_src clojure

(enum-like intent        #{'Local 'In 'Out 'InOut 'ReturnVar
                           'Unspecified})
(enum-like storage-type  #{'Default, 'Save, 'Parameter, 'Allocatable})
(enum-like access        #{'Public 'Private})
(enum-like presence      #{'Required 'Optional})
(enum-like deftype       #{'Implementation, 'Interface})
(enum-like arraybound    #{'LBound 'UBound})
(enum-like arraystorage  #{'RowMajor 'ColMajor})
(enum-like cast-kind     #{'RealToInteger       'IntegerToReal
                           'LogicalToReal       'RealToReal
                           'IntegerToInteger    'RealToComplex
                           'IntegerToComplex    'IntegerToLogical
                           'RealToLogical       'CharacterToLogical
                           'CharacterToInteger  'CharacterToList
                           'ComplexToLogical    'ComplexToComplex
                           'ComplexToReal       'ComplexToInteger
                           'LogicalToInteger    'RealToCharacter
                           'IntegerToCharacter  'LogicalToCharacter})
;; #+end_src

;; ----------------------------------------------------------------
;; ## Abi
;;
;;

;;
;; `Abi` is a special case of enum-like with rich logic.
;;
;; #+begin_src clojure

(def external-abis
  #{'LFortranModule, 'GFortranModule,
    'BindC, 'Interactive, 'Intrinsic})

(def internal-abis #{'Source})

(s/def ::abi-enum (set/union external-abis internal-abis))

(s/def ::abi-external ::bool)
;; #+end_src

;;
;; ### Full-Form
;;
;;
;; #+begin_src clojure

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
;; #+end_src

;;
;; ### Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn abi
  ;; unary --- default "external"
  ([the-enum]
   (abi the-enum
        :external (not (= the-enum 'Source))))
  ;; binary --- invalid
  ([the-enum, crap]
   ::invalid-abi)
  ;; trinary --- light sugar
  ([the-enum, ext-kw, the-bool]
   (cond
     (not (= ext-kw :external)) ::invalid-abi
     :else
     {::term         ::abi,
      ::abi-enum     the-enum,
      ::abi-external the-bool})))
;; #+end_src

;;
;; ### The ABIs
;;
;;
;; #+begin_src clojure

(def LFortranModule (abi 'LFortranModule :external true))
(def GFortranModule (abi 'GFortranModule :external true))
(def BindC          (abi 'BindC          :external true))
(def Interactive    (abi 'Interactive    :external true))
(def Intrinsic      (abi 'Intrinsic      :external true))
(def Source         (abi 'Source         :external false))
;; #+end_src


;;
;;
;; # TTYPE
;;
;;


;;
;; `Ttype` is a term with nested multi-specs.
;;


;; ----------------------------------------------------------------
;; ## Prerequisite Types and Aliases
;;
;;

;; #+begin_src clojure

(s/def ::ttype* (s/coll-of ::ttype))
;; #+end_src

;; #+begin_src clojure

(defmacro .? [thing]
  `(s/or :thing ~thing
         :empty empty?))

(defmacro .* [thing]
  `(s/coll-of ~thing))

(s/def ::ttype?           (.? ::ttype))
;; #+end_src

;; #+begin_src clojure

(s/def ::param-type*          ::ttype*)
(s/def ::return-var-type?     ::ttype?)
;; #+end_src

;; #+begin_src clojure

(s/def ::bindc-name           (s/nilable string?))
(s/def ::elemental            ::bool)
(s/def ::pure                 ::bool)
(s/def ::module               ::bool)
(s/def ::inline               ::bool)
(s/def ::static               ::bool)
(s/def ::type-param*          ::ttype*)
;; #+end_src

;; #+begin_src clojure

(s/def ::symbols          (.* ::symbol))
;; #+end_src

;; #+begin_src clojure

(s/def ::symbol?          (.? ::symbol))
(s/def ::restrictions         ::symbols)
(s/def ::is-restriction       ::bool)
;; #+end_src

;; #+begin_src clojure

(s/def ::expr*            (.* ::expr))
;; #+end_src

;; #+begin_src clojure

(s/def ::expr?            (.? ::expr))
;; #+end_src

;;
;; ### For Loop Statements
;;

;; #+begin_src clojure

(s/def ::loop-v               ::expr?) ;; TODO: ?
(s/def ::loop-start           ::expr?)
(s/def ::loop-end             ::expr?)
(s/def ::loop-increment       ::expr?)

(s/def ::do-loop-head
  (s/keys :req [::loop-v
                ::loop-start
                ::loop-end
                ::loop-increment]))
;; #+end_src

;;
;; ### For Character
;;

;; #+begin_src clojure

(s/def ::len                  ::int)   ;; Issues #36
(s/def ::disposition          #{'compile-time-length   ;; >= 0
                       'inferred-at-run-time  ;; = -1
                       'allocatable           ;; = -2
                       'run-time-expression}) ;; = -3
(s/def ::len-expr?            ::expr?) ;; TODO: check that it's >= 0
;; #+end_src

;; ----------------------------------------------------------------
;; ## Kind
;;
;;

;;
;; The `kind` member selects the kind of a given `ttype`.
;; MASR currently supports the following:
;;
;; - `Integer` kinds: `1 (i8)`, `2 (i16)`, `4 (i32)`, `8 (i64)`
;;
;; - `Real` kinds: `4 (f32)`, `8 (f64)`
;;
;; - `Complex` kinds: `4 (c32)`, `8 (c64)`
;;
;; - `Character` kinds: `1 (utf8 string)`
;;
;; - `Logical` kinds: 1, 2, 4:
;;
;;    Boolean represented by 1, 2, 4 bytes; the default
;;    Logical kind is 4, just like the default Integer
;;    kind, consistent with Python and Fortran: in
;;    Python "Booleans in Python are implemented as a
;;    subclass of integers", in Fortran the "default
;;    logical kind has the same storage size as the
;;    default integer"; we currently use `kind=4` as
;;    default for Integer, so we also use `kind=4` as
;;    default for Logical.
;;

;; ----------------------------------------------------------------
;; ## Support Specs For Kinds
;;
;;
;; #+begin_src clojure

(s/def ::integer-kind        #{1 2 4 8 16})
(s/def ::real-kind           #{4 8})
(s/def ::complex-kind        #{4 8})
(s/def ::logical-kind        #{1 2 4})
(s/def ::character-kind      #{1})
;; #+end_src

;; ----------------------------------------------------------------
;; ## Heavy Sugar for `ttype`
;;
;;
;; #+begin_src clojure

(defn ttype
  "Given a conforming full-form ::asr-type-head
  subtype like

      {::ttype-head   ::Integer,
       ::integer-kind 4,
       ::dimension*   []}

  produce a conforming ttype like

      {::term ::ttype,
       ::asr-ttype-head
       {::ttype-head   ::Integer,
        ::integer-kind 4,
        ::dimension*   []}}"
  [it]
  {::term ::ttype,
   ::asr-ttype-head it})
;; #+end_src

;; ----------------------------------------------------------------
;; ## Sugar for the Kinds
;;
;;
;; #+begin_src clojure

(defmacro def-ttype-and-head
  "Define light-sugar functions Integer-, Real-,
  etc., that take a full hash-map of arguments,
  e.g.,

      (Integer- {:kind 4, :dimension* []})

  Define heavy-sugar functions like Integer, Real,
  etc., that take positional arguments, with defaults,
  e.g.,

      (Integer 4 [])
      (Integer 4) ;; default scalar ttype
      (Integer)   ;; default 4-byte scalar ttype"
  [it]
  (let [ns  "masr.specs"
        cap (str/capitalize (str it)) ;; e.g. "Integer"
        scp (symbol cap)              ;; e.g. Integer  (heavy sugar)
        lcp (symbol (str cap "-"))    ;; e.g. Integer- (light sugar)
        tth (keyword ns cap)          ;; e.g. ::Integer
        kdh (keyword ns (str/lower-case (str it "-kind")))
        ;; ... e.g. ::integer-kind
        ivh (keyword ns (str/lower-case
                         (str "invalid-" it "-ttype")))
        ;; ... e.g. ::invalid-integer-ttype
        dfk 4  ;; default kind
        dfd [] ;; default dimension*
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
       ;; (Integer- {:kind 4 :dimension* []}
       (defn ~lcp ;; e.g. Integer-
         [{kind# :kind, dims# :dimension*}]
         (let [cnd# {::ttype-head ~tth,  ;; e.g. ::Integer
                     ~kdh         kind#, ;; e.g. ::integer-kind
                     ::dimension* (dimension* dims#)}]
           (if (s/valid? ::asr-ttype-head cnd#)
             (ttype cnd#), ~ivh)))
       ;; Define the HEAVY-SUGAR fns Integer, Real,
       ;; Complex Logical, Character that take
       ;; positional arguments, like
       ;; (Integer 4 []), (Integer 4), (Integer)
       (defn ~scp ;; e.g. Integer
         ;; binary
         ([kindx# dimsx#]
          (~lcp {:kind kindx# :dimension* dimsx#}))
         ;; unary
         ([kindy#]
          (~lcp {:kind kindy# :dimension* ~dfd}))
         ;; dfk = 4  is the default kinds for
         ;;          Integer, Real, Complex, Logical
         ;; dfd = [] is the default dimension*
         ;; nullary
         ([]
          (~lcp {:kind ~dfk   :dimension* ~dfd})))
       ;; TODO ? entity keywords for ::Integer, ::Real, etc.
       )))
;; #+end_src

;; ----------------------------------------------------------------
;; ## INTEGER, REAL, COMPLEX, LOGICAL
;;
;;
;; See also `defmasrtypes` at top of the file.

;; #+begin_src clojure

(def-ttype-and-head Integer)
(def-ttype-and-head Real)
(def-ttype-and-head Complex)
(def-ttype-and-head Logical)
;; #+end_src

;; ----------------------------------------------------------------
;; ## CHARACTER
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | Character(int kind, int len, expr? len_expr, dimension* dims)
;; ```

;;
;; ### Example
;;
;;
;; #+begin_src clojure

#_
(Character 1 1 () [])
;; #+end_src

;;
;; ### Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn Character
  ([kind, len, len-expr?, dims]
   ;; quaternary
   {::term ::ttype
    ::asr-ttype-head
    {::ttype-head ::Character
     ::character-kind kind
     ::len            len
     ::disposition
     (cond (>= len  0) 'compile-time-length
           (=  len -1) 'inferred-at-run-time
           (=  len -2) 'allocatable
           (=  len -3) 'run-time-expression)
     ::len-expr?      len-expr?
     ::dimension*     (dimension* dims)
     }})
  ([kind, len, dims]
   ;; trinary
   (Character kind len () dims))
  ([kind, len]
   ;; binary
   (Character kind len () []))
  ([len]
   ;; unary
   (Character 1 len ( [])))
  ([]
   ;; nullary
   (Character 1 1 () [])))
;; #+end_src

;; ----------------------------------------------------------------
;; ## TUPLE
;;
;; #+begin_src clojure

(defn Tuple [ttypes]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::Tuple
    ::ttype*     ttypes}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## List
;;
;; #+begin_src clojure

(defn List [ttype]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::List
    ::ttype      ttype}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## Set
;;
;; #+begin_src clojure

(defn Set [ttype]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::Set
    ::ttype      ttype}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## FUNCTION-TYPE
;;
;;

;;
;; This is a rich `ttype` that we spell out by hand.
;;
;;
;; ### Original ASDL
;;
;; ```c
;; | FunctionType(ttype*  arg_types,       ;; rename param-type*
;;                ttype?  return_var_type,
;;                abi     abi,
;;                deftype deftype,
;;                string? bindc_name,
;;                bool    elemental,
;;                bool    pure,
;;                bool    module,
;;                bool    inline,
;;                bool    static,
;;                ttype*  type_param*,
;;                symbol* restrictions,
;;                bool    is_restriction)
;; ```

;;
;; ### Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn FunctionType
  [param-type*-     return-var-type?-  abi-
   deftype-         bindc-name-        elemental-
   pure-            module-            inline-
   static-          type-param*-       restrictions-
   is-restriction-  ]
  (plnecho-file
   "/tmp/a.clj"
   {::term ::ttype
    ::asr-ttype-head
    {::ttype-head        ::FunctionType

     ::param-type*       param-type*-
     ::return-var-type?  return-var-type?-
     ::abi               abi-

     ::deftype           deftype-
     ::bindc-name        (if (empty? bindc-name-)
                           nil, bindc-name-)
     ::elemental         elemental-

     ::pure              pure-
     ::module            module-
     ::inline            inline-

     ::static            static-
     ::type-param*       type-param*-
     ::restrictions      restrictions-

     ::is-restriction    is-restriction-}}))
;; #+end_src

;; ----------------------------------------------------------------
;; ## TODO The Rest of the `ttypes`
;;
;;
;; ### Original ASDL
;;
;; ```c
;; >>> Integer, Real, Complex, Logical are already done ...
;; >>> FunctionType is done.
;; >>> Here are the rest of the ttypes.
;; | Character(int kind, int len, expr? len_expr, dimension* dims)
;; >>> Set(ttype type)
;; >>> List(ttype type)
;; >>> Tuple(ttype* type)
;; | Struct(symbol derived_type, dimension* dims)
;; | Enum(symbol enum_type, dimension *dims)
;; | Union(symbol union_type, dimension *dims)
;; | Class(symbol class_type, dimension* dims)
;; | Dict(ttype key_type, ttype value_type)
;; | Pointer(ttype type)
;; | Const(ttype type)
;; | CPtr()
;; | TypeParameter(identifier param, dimension* dims)
;; ```
;;
;;


;;
;;
;; # PLACEHOLDERS
;;
;;


;;
;; things we haven't fully defined yet
;;

;; ----------------------------------------------------------------
;; ## ESCAPE TARGET
;;
;;
;; #+begin_src clojure

(s/def ::escape-target empty?)
;; #+end_src

;; ----------------------------------------------------------------
;; ## SYMBOLIC VALUE
;;
;;
;; #+begin_src clojure

(s/def ::symbolic-value empty?)
;; #+end_src

;;
;; ### Sugar
;;
;;
;; #+begin_src clojure

(def symbolic-value identity)
;; #+end_src


;;
;;
;; # EXPR
;;
;;

;; ----------------------------------------------------------------
;; ## Prerequisite Types and Aliases
;;
;;
;; #+begin_src clojure

(s/def ::arg ::expr)
;; #+end_src

;; #+begin_src clojure

(s/def ::value  ::expr)
(s/def ::value? ::expr?)
(s/def ::target ::expr)
;; #+end_src

;; #+begin_src clojure

(s/def ::IntegerConstant? (.? ::IntegerConstant))
;; #+end_src

;; #+begin_src clojure

(s/def ::symbol-ref
  (s/keys :req [::identifier
                ::symtab-id]))
(s/def ::symbol-ref?      (.? ::symbol-ref))
(s/def ::symbol-ref*      (.* ::symbol-ref))
(s/def ::extern-symref        ::symbol-ref?)
(s/def ::orig-symref          ::symbol-ref?)
;; #+end_src

;; #+begin_src clojure

(defn symbol-ref [ident, stid]
  {::identifier ident,
   ::symtab-id  stid})
;; #+end_src

;; #+begin_src clojure

(s/def ::return-type          ::ttype)
;; #+end_src

;; #+begin_src clojure

(s/def ::varnym               ::identifier)
;; #+end_src

;; ----------------------------------------------------------------
;; ### Unchecked Element Types
;;
;;

;;
;; The following represent types of elements of
;; collections. TODO: MASR does not currently check
;; them because checking them requires checking that
;; the type of the element matches types stored
;; along with the collection. The cross-logic to
;; check these types must be implemented in each
;; collection type
;;
;;
;; #+begin_src clojure

(s/def ::unchecked-element-expr
  (s/or :array-item           ::ArrayItem ;; TODO check return type!
        :tuple-item           ::TupleItem ;; TODO check return type!
        :list-item            ::ListItem  ;; TODO check return type!
        :cast                 ::Cast      ;; TODO check return type!
        :if-expr              ::IfExp     ;; TODO check return type!
        :named-expr           ::NamedExpr ;; TODO check return type!
        :var                  ::Var       ;; TODO check return type!
))
;; #+end_src

;; ----------------------------------------------------------------
;; ### Logical Types
;;
;;
;; #+begin_src clojure

(s/def ::logical-expr
  (s/or :logical-constant     ::LogicalConstant
        :logical-compare      ::LogicalCompare
        :integer-compare      ::IntegerCompare
        :real-compare         ::RealCompare
        :complex-compare      ::ComplexCompare
        :tuple-compare        ::TupleCompare
        :logical-binop        ::LogicalBinOp
        :logical-not          ::LogicalNot
        :unchecked            ::unchecked-element-expr))
;; #+end_src

;; #+begin_src clojure

(s/def ::logical-expr?    (.? ::logical-expr))
(s/def ::logical-value?       ::logical-expr?)

(s/def ::logical-left         ::logical-expr)
(s/def ::logical-right        ::logical-expr)
;; #+end_src

;; ----------------------------------------------------------------
;; ### Integer Types
;;
;;

;; #+begin_src clojure

(s/def ::integer-expr
  (s/or :integer-constant     ::IntegerConstant
        :integer-binop        ::IntegerBinOp
        :integer-unary-minus  ::IntegerUnaryMinus
        :integer-bit-not      ::IntegerBitNot
        :string-ord           ::StringOrd
        :string-len           ::StringLen
        :string-item          ::StringItem
        :tuple-len            ::TupleLen
        :list-len             ::ListLen
        :unchecked            ::unchecked-element-expr))
;; #+end_src

;; #+begin_src clojure

(s/def ::integer-expr?    (.? ::integer-expr))
(s/def ::integer-value?       ::integer-expr?)

(s/def ::integer-left         ::integer-expr)
(s/def ::integer-right        ::integer-expr)
;; #+end_src

;; ----------------------------------------------------------------
;; ### Index Types
;;
;;
;; #+begin_src clojure

(s/def ::index                ::integer-expr)
(s/def ::index-start          ::integer-expr)
(s/def ::index-end            ::integer-expr)
(s/def ::index-step           ::integer-expr)

(s/def ::index?               ::integer-expr?)
(s/def ::index-start?         ::integer-expr?)
(s/def ::index-end?           ::integer-expr?)
(s/def ::index-step?          ::integer-expr?)
;; #+end_src

;; ----------------------------------------------------------------
;; ### Real Types
;;
;;

;; #+begin_src clojure

(s/def ::real-expr
  (s/or :real-constant        ::RealConstant
        :real-binop           ::RealBinOp
        :real-unary-minus     ::RealUnaryMinus
        :unchecked            ::unchecked-element-expr))
;; #+end_src

;; #+begin_src clojure

(s/def ::real-expr?       (.? ::real-expr))
(s/def ::real-value?          ::real-expr?)

(s/def ::real-left            ::real-expr)
(s/def ::real-right           ::real-expr)
;; #+end_src

;; ----------------------------------------------------------------
;; ### Complex Types
;;
;;

;; #+begin_src clojure

(s/def ::complex-expr
  (s/or :complex-constant     ::ComplexConstant
        :complex-binop        ::ComplexBinOp
        :complex-unary-minus  ::ComplexUnaryMinus
        :unchecked            ::unchecked-element-expr))
;; #+end_src

;; #+begin_src clojure

(s/def ::real-part            ::float)
(s/def ::imiginary-part       ::float)

(s/def ::complex-left         ::complex-expr)
(s/def ::complex-right        ::complex-expr)
;; #+end_src

;; #+begin_src clojure

(s/def ::complex-expr?    (.? ::complex-expr))
(s/def ::complex-value?       ::complex-expr?)

(s/def ::complex-left         ::complex-expr)
(s/def ::complex-right        ::complex-expr)
;; #+end_src

;; ----------------------------------------------------------------
;; ### Array Types
;;
;;
;; #+begin_src clojure

(s/def ::array-expr
  (s/or :var                    ::Var
        :array-constant         ::ArrayConstant
        :unchecked              ::unchecked-element-expr))
;; #+end_src

;; TODO: `array-shape` and `array-value?` are
;; work-in-progress:

;; #+begin_src clojure

(s/def ::array-shape            ::expr)
(s/def ::array-value?           ::expr?)
;; #+end_src

;; #+begin_src clojure

(s/def ::array-index-start?     ::integer-expr?)
(s/def ::array-index-end?       ::integer-expr?)
(s/def ::array-index-increment? ::integer-expr?)

(s/def ::array-index
  (s/keys :req [::array-index-start?
                ::array-index-end?
                ::array-index-increment?]))

(s/def ::array-index*       (.* ::array-index))
;; #+end_src

;; ----------------------------------------------------------------
;; ### List Types
;;
;; #+begin_src clojure

(s/def ::list-expr
  (s/or :list-constant          ::ListConstant
        :var                    ::Var
        :unchecked              ::unchecked-element-expr)  )
;; #+end_src

;; #+begin_src clojure

(s/def ::list-element
  (s/or :expr                   ::expr))
;; #+end_src

;; ----------------------------------------------------------------
;; ### Tuple Types
;;
;; #+begin_src clojure

(s/def ::tuple-expr
  (s/or :tuple-constant         ::TupleConstant
        :unchecked              ::unchecked-element-expr))
(s/def ::tuple-left             ::tuple-expr)
(s/def ::tuple-right            ::tuple-expr)
;; #+end_src

;; #+begin_src clojure

(s/def ::elements               ::expr*)
;; #+end_src


;; ----------------------------------------------------------------
;; ### String Types
;;
;;

;; #+begin_src clojure

(s/def ::string-expr
  (s/or :string-constant        ::StringConstant
        :string-item            ::StringItem
        :string-chr             ::StringChr
        :string-section         ::StringSection
        :string-repeat          ::StringRepeat
        :unchecked              ::unchecked-element-expr))
;; #+end_src

;; #+begin_src clojure

(s/def ::string-expr?       (.? ::string-expr))
(s/def ::string-value?          ::string-expr?)
;; #+end_src


;; For `IntrinsicFunction`:


;; #+begin_src clojure

(s/def ::intrinsic-ident        ::identifier)
(s/def ::overload-id            ::nat)
;; #+end_src

;; ----------------------------------------------------------------
;; ## IF EXP
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;;  IfExp(expr test,
;;        expr body,
;;        expr orelse,
;;        ttype type,
;;        expr? value)
;; ```

;;
;; ### Example
;;
;;
;; #+begin_src clojure

#_
(IfExp
 (IntegerCompare
  (Var 2 b)
  Gt
  (IntegerConstant 5 (Integer 4 []))
  (Logical 4 [])
  ())
 (LogicalConstant true (Logical 4 []))
 (LogicalConstant false (Logical 4 []))
 (Logical 4 []) () )
;; #+end_src

;;
;; ### Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn IfExp [test-expr, body, orelse, ttype, value?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head ::IfExp
    ::test-expr test-expr
    ::body      body
    ::orelse    orelse
    ::ttype     ttype
    ::value?    value?}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## INTEGER BIT NOT
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; IntegerBitNot(expr arg, ttype type, expr? value)
;; ```

;;
;; ### Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn IntegerBitNot
  [iarg, ittype, ivalue?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head ::IntegerBitNot
    ::integer-expr   iarg
    ::Integer        ittype
    ::integer-value? ivalue?}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## INTEGER, REAL, COMPLEX UNARY MINUS
;;
;;

;;
;; ### Typed Uminus Macro
;;
;; #+begin_src clojure

(defmacro typed-uminus                 ;; -- Examples --
  [ttype]                              ;; Integer
  (let [ns    "masr.specs"
        fnstr (str ttype "UnaryMinus") ;; "IntegerUnaryMinus"
        fnsym (symbol fnstr)           ;; 'IntegerUnaryMinus
        fnqkw (keyword ns fnstr)       ;; ::IntegerUnaryMinus
        ttstr (str ttype)              ;; "Integer"
        ttqkw (keyword ns ttstr)       ;; ::Integer
        tlstr (str/lower-case ttstr)   ;; "integer"
        testr (str tlstr "-expr")      ;; "integer-expr"
        teqkw (keyword ns testr)       ;; ::integer-expr
        tvstr (str tlstr "-value?")    ;; "integer-value?"
        tvqkw (keyword ns tvstr)       ;; ::integer-value?
        ]
    `(defn ~fnsym                      ;; (defn IntegerUnaryMinus
       [arg# ttype# val?#]             ;; ([arg ttype val?
       {::term ::expr,
        ::asr-expr-head
        {::expr-head ~fnqkw      ;; {::ex... ::IntegerUnaryMinus
         ~teqkw       arg#       ;;  ::integer-expr   arg
         ~ttqkw       ttype#     ;;  ::Integer        ttype
         ~tvqkw       val?#}}   ;;  ::integer-value? val?}}]
       )))
;; #+end_src

;; ### Using the Macro

;; #+begin_src clojure

(typed-uminus Integer)
(typed-uminus Real)
(typed-uminus Complex)
;; #+end_src

;; ----------------------------------------------------------------
;; ## NAMED EXPR
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | NamedExpr(expr target, expr value, ttype type)
;; ```

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(NamedExpr
 (Var 2 y)
 (IntegerConstant 0 (Integer 4 []))
 (Integer 4 [])    )
;; #+end_src

;;
;; ### Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn NamedExpr [target value ttype]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head ::NamedExpr
    ::target target
    ::value  value
    ::ttype  ttype}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## FUNCTION CALL
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | FunctionCall(symbol     name,
;;                symbol?    original_name,
;;                call_arg * args,
;;                ttype      type,
;;                expr     ? value,
;;                expr     ? dt)
;; ```
;;

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(FunctionCall
 7 g
 ()
 []
 (Integer 4 [])
 ()
 () )
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn FunctionCall-- [fn-symref orig-symref call-args
                      return-type value? dt?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head    ::FunctionCall
    ::symbol-ref   fn-symref
    ::orig-symref  orig-symref
    ::call-args    call-args
    ::return-type  return-type
    ::value?       value?
    ::dt?          dt? ;; TODO check arithmetic
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro FunctionCall
  ;; heptenary
  ([stid, ident, orig-symref,
    args, rettype, value?, dt?]
   `(FunctionCall-- (symbol-ref '~ident ~stid)
                    ~orig-symref
                    ~args
                    ~rettype
                    ~value?
                    ~dt?))
  ;; octenary
  ([stid, ident, ostid, oident,
    args, rettype, value?, dt?]
   `(FunctionCall-- (symbol-ref '~ident, ~stid)
                    (symbol-ref '~oident, ~ostid)
                    ~args
                    ~rettype
                    ~value?
                    ~dt?)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## INTRINSIC FUNCTION
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; IntrinsicFunction(int    intrinsic_id,
;;                   expr * args,
;;                   int    overload_id,
;;                   ttype  type,
;;                   expr?  value)
;; ```
;;

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(IntrinsicFunction
 Abs
 [(RealBinOp
   (Var 2 a3)
   Sub
   (RealConstant
    9.000000
    (Real 8 []))
   (Real 8 [])    ()    )]
 0
 (Real 8 [])   ()   )
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn IntrinsicFunction--
  [intrinsic-ident    expr*    overload-id
   return-type        value?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head           ::IntrinsicFunction
    ::intrinsic-ident     intrinsic-ident
    ::expr*               expr*
    ::overload-id         overload-id
    ::return-type         return-type
    ::value?              value?
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro IntrinsicFunction
  "Quote the intrinsic identifier."
  [intrinsic-ident    expr*    overload-id
   return-type        value?]
  `(IntrinsicFunction--
    '~intrinsic-ident, ~expr*, ~overload-id,
    ~return-type,      ~value?))
;; #+end_src

;; ----------------------------------------------------------------
;; ## LOGICAL, INTEGER, REAL CONSTANTS
;;
;;

;;
;; To reduce code duplication, we want to write
;; something like the following automatically for
;; Logical, Integer, and Real. String is a special
;; case because its ttype is Character and not
;; String. Complex is a special case because it
;; takes two Real inputs. Write those by hand.
;;

;;
;; ### Typed Constant Macro
;;
;; #+begin_src clojure

(defmacro typed-constant              ;; -- Examples --
  [ttype spec]                        ;; [Logical bool]
  (let [ns    "masr.specs"
        fnstr (str ttype "Constant")  ;; "LogicalConstant"
        fnsym (symbol fnstr)          ;; 'LogicalConstant
        fnqkw (keyword ns fnstr)      ;; ::LogicalConstant
        ttstr (str ttype)             ;; "Logical"
        ttsym (symbol ttstr)          ;; 'Logical
        ttqkw (keyword ns ttstr)      ;; ::Logical
        spqkw (keyword ns (str spec)) ;; ::bool
        vpsym                         ;; 'a-bool
        (symbol (case spec
                  'int (str "an-" spec)
                  (str "a-" spec)))
        tpsym ;; 'a-ttype
        (symbol (str 'a-ttype))]
    `(defn ~fnsym                     ;; (defn LogicalConstant
       ([~vpsym ~tpsym]               ;; ([a-bool, a-ttype]
        "binary"
        {::term ::expr,
         ::asr-expr-head
         {::expr-head ~fnqkw    ;; {::expr-head ::LogicalConstant
          ~spqkw      ~vpsym    ;; ::bool       a-bool
          ~ttqkw      ~tpsym}} ;; ::Logical    a-ttype}}]
        )
       ([~vpsym]                      ;; ([a-bool]
        "unary"
        ;; (LogicalConstant a-bool (Logical))))
        (~fnsym ~vpsym (~ttsym))))))
;; #+end_src

;;
;; ### Using the Macro
;;
;; #+begin_src clojure

(typed-constant Logical bool)
(typed-constant Real    float)
(typed-constant Integer int)
;; #+end_src

;; ----------------------------------------------------------------
;; ## STRING CONSTANT
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | StringConstant(string s, ttype type)
;; ```

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(StringConstant "3" (Character 1 1 () []))
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn StringConstant
  ([string, char-ttype]
   "binary"
   {::term ::expr,
    ::asr-expr-head
    {::expr-head ::StringConstant
     ::string    string
     ::Character char-ttype}})
  ([string]
   "unary"
   (StringConstant string (Character))))
;; #+end_src

;; ----------------------------------------------------------------
;; ## STRING REPEAT
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn StringRepeat
  ([string-expr, integer-expr, char-ttype, compiler-computed?]
   "quaternary"
   {::term ::expr,
    ::asr-expr-head
    {::expr-head ::StringRepeat
     ::string-expr    string-expr
     ::integer-expr   integer-expr
     ::Character      char-ttype
     ::string-expr?   compiler-computed?}})
  ([string-expr integer-expr]
   "binary"
   (StringRepeat string-expr (Character) ())))
;; #+end_src

;; ----------------------------------------------------------------
;; ## COMPLEX CONSTANT
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; ComplexConstant(float re, float im, ttype type)
;; ```
;;

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(ComplexConstant 3.000000 4.000000 (Complex 8 []))
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn ComplexConstant
  ;; trinary
  ([re-float, im-float c-ttype]
   {::term ::expr,
    ::asr-expr-head
    {::expr-head ::ComplexConstant
     ::real-part      re-float
     ::imaginary-part im-float
     ::Complex        c-ttype}})
  ;; binary
  ([re-float, im-float]
   (ComplexConstant re-float, im-float, (Complex))))
;; #+end_src

;; ----------------------------------------------------------------
;; ## VAR
;;
;;

;;
;; ### Issue #23
;;
;; Is the parameter `symbol` for `Var` really a `symbol`?
;; Or just an identifier? #23
;; https://github.com/rebcabin/masr/issues/23
;; The original ASDL
;;
;; ```c
;; Var(symbol v)
;; ```
;;
;; from `ASR.asdl` doesn't match the instance. Instead,
;; we probably need something like:
;;
;; ```c
;; Var(symtab_id stid, identifier it)
;; ```

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn Var-- [stid, ident]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head  ::Var
    ::symtab-id  stid
    ::varnym     ident
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro Var [stid, unquoted-ident]
  `(Var-- ~stid '~unquoted-ident))
;; #+end_src

;;
;; TODO: make Var look up a value in the
;; symbol-table! That's part of abstract execution.

;; ----------------------------------------------------------------
;; ## ARRAY CONSTANT
;;
;;
;; #+begin_src clojure

(defn ArrayConstant [args, ttype, arraystorage]
  {::term ::expr
   ::asr-expr-head
   {::expr-head ::ArrayConstant
    ::expr*           args
    ::ttype           ttype
    ::array-storage   arraystorage
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## ARRAY ITEM
;;
;;

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(ArrayItem
 (Var 186 b)
 [(()
   (Var 186 k)
   ())]
 (Real 8 [])
 RowMajor
 ())
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn array-index [[start, end, incr]]
  {::array-index-start?     start
   ::array-index-end?       end
   ::array-index-increment? incr})

(defn ArrayItem [array-expr
                 array-index*
                 ttype
                 array-storage
                 expr?]
  {::term ::expr
   ::asr-expr-head
   {::expr-head ::ArrayItem
    ::array-expr      array-expr
    ::array-index*    (map array-index array-index*)
    ::ttype           ttype
    ::array-storage   array-storage
    ::expr?           expr?
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## ARRAY RESHAPE
;;
;;

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(ArrayReshape
 (Var 186 b)
 (Var 186 newshape)
 (Real 8 [(() ())])
 ())
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn ArrayReshape
  [array-expr
   array-shape
   ttype
   array-value?]
  {::term ::expr
   ::asr-expr-head
   {::expr-head ::ArrayReshape
    ::array-expr   array-expr
    ::array-shape  array-shape
    ::ttype        ttype
    ::array-value? array-value?
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## STRING CHR
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn StringChr
  ([str-expr, char-ttype, string-val?]
   "trinary ... Return ascii value of the indicated
   character in the string."
   {::term ::expr,
    ::asr-expr-head
    {::expr-head ::StringChr
     ::string-expr       str-expr
     ::Character         char-ttype
     ::string-value?     string-val?}})
  ([str-expr, string-val?]
   (StringChr str-expr, (Character) string-val?)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## STRING LEN
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn StringLen
  ([str-expr, int-ttype, int-val?]
   "trinary ... Return ascii value of the indicated
   character in the string."
   {::term ::expr,
    ::asr-expr-head
    {::expr-head ::StringLen
     ::string-expr       str-expr
     ::Integer           int-ttype
     ::integer-value?    int-val?}})
  ([str-expr, int-val?]
   (StringLen str-expr, (Integer) int-val?)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## STRING ITEM
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn StringItem
  [string-expr
   index?
   Integer
   integer-value?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head         ::StringItem
    ::string-expr       string-expr
    ::index?            index?
    ::Integer           Integer
    ::integer-value?    integer-value?
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## STRING SECTION
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn StringSection
  [string-expr
   index-start?
   index-end?
   index-step?
   Character
   string-value?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head        ::StringSection
    ::string-expr      string-expr
    ::index-start?     index-start?
    ::index-end?       index-end?
    ::index-step?      index-step?
    ::Character        Character
    ::string-value?    string-value?
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## STRING ORD
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | StringOrd(expr arg, ttype type, expr? value)
;; ```

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(StringOrd
 (StringConstant
  "3"
  (Character 1 1 () [])
  )
 (Integer 4 [])
 (IntegerConstant 51 (Integer 4 []))
 )
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defn StringOrd
  ([str-expr, int-ttype, int-val?]
   "trinary ... Return ascii value of the indicated
   character in the string."
   {::term ::expr,
    ::asr-expr-head
    {::expr-head ::StringOrd
     ::string-expr       str-expr
     ::Integer           int-ttype
     ::integer-value?    int-val?}})
  ([str-expr, int-val?]
   (StringOrd str-expr, (Integer) int-val?)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## INTEGER BINOP
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | IntegerBinOp(expr  left,
;;                binop op,
;;                expr  right,
;;                ttype type,
;;                expr? value)
;; ```
;;

;; ### Example
;;
;; #+begin_src clojure

#_
(IntegerBinOp
 (IntegerBinOp
  (IntegerConstant 2 (Integer 4 []))
  Add
  (IntegerConstant 3 (Integer 4 []))
  (Integer 4 [])
  (IntegerConstant 5 (Integer 4 []))
  )
 Mul
 (IntegerConstant 5 (Integer 4 []))
 (Integer 4 [])
 (IntegerConstant 25 (Integer 4 [])))
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn IntegerBinOp [left- bo- right- itt- ival?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::IntegerBinOp
    ::integer-left   left-
    ::integer-binop  bo-
    ::integer-right  right-
    ::Integer        itt-
    ::integer-value? ival?-  ;; TODO: Check arithmetic!
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## REAL BINOP
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | RealBinOp(expr  left,
;;                binop op,
;;                expr  right,
;;                ttype type,
;;                expr? value)
;; ```

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(RealBinOp
 (RealBinOp
  (RealConstant 2 (Real 4 []))
  Add
  (RealConstant 3 (Real 4 []))
  (Real 4 [])
  (RealConstant 5 (Real 4 []))
  )
 Mul
 (RealConstant 5 (Real 4 []))
 (Real 4 [])
 (RealConstant 25 (Real 4 [])))
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn RealBinOp-- [left- bo- right- rtt- rval?-]
  "Must use RAdd, RMul, etc."
  {::term ::expr,
   ::asr-expr-head
   {::expr-head   ::RealBinOp
    ::real-left   left-
    ::real-binop  bo-
    ::real-right  right-
    ::Real        rtt-
    ::real-value? rval?- ;; TODO: Check arithmetic!
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro RealBinOp
  "Must use Add, Mul, etc."
  [left- bo- right- rtt- rval?-]
  (let [rop (symbol (str "R" bo-))]
    `(RealBinOp-- ~left- ~rop ~right- ~rtt- ~rval?-)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## COMPLEX BINOP
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | ComplexBinOp(expr  left,
;;                binop op,
;;                expr  right,
;;                ttype type,
;;                expr? value)
;; ```

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn ComplexBinOp-- [left- bo- right- ctt- cval?-]
  "Must use CAdd, CMul, etc."
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::ComplexBinOp
    ::complex-left   left-
    ::complex-binop  bo-
    ::complex-right  right-
    ::Complex        ctt-
    ::complex-value? cval?- ;; TODO: Check arithmetic!
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro ComplexBinOp
  "Must use Add, Mul, etc."
  [left- bo- right- ctt- cval?-]
  (let [rop (symbol (str "C" bo-))]
    `(ComplexBinOp-- ~left- ~rop ~right- ~ctt- ~cval?-)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## LOGICAL BINOP
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | LogicalBinOp(expr left, logicalbinop op, expr
;;   right, ttype type, expr? value)
;; ```

;;
;; ### Example
;;
;;
;; #+begin_src clojure

#_
(LogicalBinOp
 (Var 2 a)
 And
 (LogicalCompare
  (Var 2 b)
  Eq
  (Var 2 b)
  (Logical 4 []) ())
 (Logical 4 []) ())
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn LogicalBinOp [left- lbo- right- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::LogicalBinOp
    ::logical-left   left-
    ::logicalbinop   lbo-
    ::logical-right  right-
    ::Logical        tt-
    ::logical-value? val?-  ;; TODO: Check arithmetic!
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## LIST CONSTANT
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn ListConstant [expr* ttype]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::ListConstant
    ::expr*          expr*
    ;; TODO: check that all exprs have the ttype
    ::ttype          ttype
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## LIST LEN
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn ListLen [list-expr int-ttype int-val?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::ListLen
    ::list-expr      list-expr
    ::Integer        int-ttype
    ::integer-value? int-val?
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## TUPLE CONSTANT
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn TupleConstant [elements ttype]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::TupleConstant
    ::elements       elements
    ::ttype          ttype
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## TUPLE LEN
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn TupleLen [tuple-expr int-ttype int-val?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::TupleLen
    ::tuple-expr     tuple-expr
    ::Integer        int-ttype
    ::integer-value? int-val?
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## TUPLE COMPARE
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn TupleCompare [tuple-left any-cmpop tuple-right
                    logical-ttype logical-value?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::TupleCompare
    ::tuple-left     tuple-left
    ::any-cmpop      any-cmpop
    ::tuple-right    tuple-right
    ::Logical        logical-ttype
    ::logical-value? logical-value?
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## COMPLEX RE
;;
;; #+begin_src clojure

(defn ComplexRe [cexpr, rtt, rv?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::ComplexRe
    ::complex-expr   cexpr
    ::Real           rtt
    ::real-value?    rv? ;; TODO: Check arithmetic!
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## COMPLEX IM
;;
;; #+begin_src clojure

(defn ComplexIm [cexpr, rtt, rv?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::ComplexIm
    ::complex-expr   cexpr
    ::Real           rtt
    ::real-value?    rv? ;; TODO: Check arithmetic!
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## INTEGER COMPARE
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | IntegerCompare(expr  left,
;;                  cmpop op,
;;                  expr  right,
;;                  ttype type,
;;                  expr? value)
;; ```

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(IntegerCompare
 (Var 4 z)
 Eq
 (IntegerConstant 16 (Integer 4 []))
 (Logical 4 [])
 ())
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn IntegerCompare [l- cmp- r- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::IntegerCompare
    ::integer-left   l-
    ::integer-cmpop  cmp-
    ::integer-right  r-
    ::Logical        tt-
    ::logical-value? val?-}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## REAL COMPARE
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn RealCompare-- [l- cmp- r- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head   ::RealCompare
    ::real-left   l-
    ::real-cmpop  cmp-
    ::real-right  r-
    ::Logical     tt-
    ::logical-value? val?-}})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro RealCompare
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "R" cmp-))]
    `(RealCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## COMPLEX COMPARE
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn ComplexCompare-- [l- cmp- r- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head   ::ComplexCompare
    ::complex-left   l-
    ::complex-cmpop  cmp-
    ::complex-right  r-
    ::Logical        tt-
    ::logical-value? val?-}})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro ComplexCompare
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "C" cmp-))]
    `(ComplexCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## STRING COMPARE
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn StringCompare-- [l- cmp- r- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head   ::StringCompare
    ::string-left    l-
    ::string-cmpop   cmp-
    ::string-right   r-
    ::Logical        tt-
    ::logical-value? val?-}})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro StringCompare
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "S" cmp-))]
    `(StringCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## LOGICAL COMPARE
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | LogicalCompare(expr left,   ;; must have type ::Logical
;;                  cmpop op,    ;; not all cmpop, only Eq and NotEq
;;                  expr right,  ;; must have type ::Logical
;;                  ttype type,
;;                  expr? value)
;; ```
;;
;;
;; ### Example
;;
;; #+begin_src clojure

#_
(LogicalCompare
  (Var 2 b)
  Eq
  (Var 2 b)
  (Logical 4 []) ())
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn LogicalCompare-- [l- cmp- r- tt- val?-]
  "Must use LEq, LNotEq."
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::LogicalCompare
    ::logical-left   l-
    ::logical-cmpop  cmp-
    ::logical-right  r-
    ::Logical        tt-
    ::logical-value? val?-}})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro LogicalCompare
  "Must use Eq, NotEq."
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "L" cmp-))]
    `(LogicalCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## LOGICAL NOT
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; LogicalNot(expr arg, ttype type, expr? value)
;; ```

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn LogicalNot
  [larg, lttype, lvalue?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head ::LogicalNot
    ::logical-expr   larg
    ::Logical        lttype
    ::logical-value? lvalue?}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## CAST
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | Cast(expr arg, cast-kind kind, ttype type, expr? value)
;; ```

;;
;; ### Example
;;
;;
;; #+begin_src clojure

#_
(Cast
 (FunctionCall
  2 pow__AT____lpython_overloaded_0__pow
  2 pow
  [((IntegerConstant 2 (Integer 4 [])))
   ((IntegerConstant 2 (Integer 4 [])))]
  (Real 8 [])
  (RealConstant 4.000000 (Real 8 [])) ())
 RealToInteger
 (Integer 4 [])
 (IntegerConstant 4 (Integer 4 [])))
;; #+end_src

;;
;; ### Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn Cast
  [arg, cast-kind, ttype, value?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head ::Cast
    ::arg       arg
    ::cast-kind cast-kind
    ::ttype     ttype
    ::value?    value?}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## LIST ITEM
;;
;;
;; #+begin_src clojure

(defn ListItem [list-expr index ttype value?]
  {::term ::expr
   ::asr-expr-head
   {::expr-head       ::ListItem
    ::list-expr       list-expr
    ::index           index
    ::ttype           ttype
    ::value?          value?
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## TUPLE ITEM
;;
;;
;; #+begin_src clojure

(defn TupleItem [tuple-expr index ttype value?]
  {::term ::expr
   ::asr-expr-head
   {::expr-head       ::TupleItem
    ::tuple-expr      tuple-expr
    ::index           index
    ::ttype           ttype
    ::value?          value?
    }})
;; #+end_src


;;
;;
;; # STMT
;;
;;

;; ----------------------------------------------------------------
;; ## Prerequisite Types and Aliases
;;
;;
;; #+begin_src clojure

(s/def ::stmt*          (.* ::stmt))
;; #+end_src

;; #+begin_src clojure

(s/def ::stmt?          (.? ::stmt))
(s/def ::vars           (.* ::Var))
;; #+end_src

;;
;;
;; TODO: more cases for `lvalue`
;;
;;
;; #+begin_src clojure

(s/def ::lvalue             ::Var)
(s/def ::rvalue             ::expr)
(s/def ::overloaded         ::stmt?)
;; #+end_src

;; #+begin_src clojure

(s/def ::format?            ::expr?)
(s/def ::value*             ::expr*)
(s/def ::separator?         ::expr?)
(s/def ::end?               ::expr?)
;; #+end_src

;; #+begin_src clojure

(s/def ::nym                ::identifier)
(s/def ::scope-nyms         ::identifier-set)
(s/def ::orig-nym           ::identifier)
;; #+end_src

;; #+begin_src clojure

(s/def ::value-attr         ::bool)
;; #+end_src

;; #+begin_src clojure

(s/def ::type-declaration (s/nilable ::symtab-id))
;; #+end_src

;;
;;
;; TODO: there is ambiguity regarding identifier-sets and lists:
;;
;;
;; #+begin_src clojure

(s/def ::dependencies       ::identifier-set)
;; #+end_src

;; #+begin_src clojure

(s/def ::param*             ::expr*) ;; renamed from args
(s/def ::body               ::stmt*)
(s/def ::return-var?        ::expr?)
;; #+end_src

;; #+begin_src clojure

(s/def ::deterministic      ::bool)
(s/def ::side-effect-free   ::bool)

;; #+begin_src clojure

(s/def ::test-expr          ::logical-expr)
(s/def ::orelse             ::stmt*)
;; #+end_src

;; #+begin_src clojure

(s/def ::message?     (s/or :str string?
                            :nil empty?))
;; #+end_src

;; #+begin_src clojure

(s/def ::goto-target        ::nat)
;; #+end_src

;; ----------------------------------------------------------------
;; ## LIST APPEND
;;
;; #+begin_src clojure

(defn ListAppend [list-expr list-element]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head      ::ListAppend
    ::list-expr      list-expr
    ::list-element   list-element
    }})
;; #+end_src

;; ----------------------------------------------------------------
;; ## EXPLICIT DEALLOCATE
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;;     | ExplicitDeallocate(expr* vars)
;; ```

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn ExplicitDeallocate [vars]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::ExplicitDeallocate
    ::vars vars}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## ASSERT
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | Assert(expr test, expr? msg)
;; ```

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn Assert [test-expr message?]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::Assert
    ::test-expr test-expr
    ::message?  message?}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## GOTO
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn GoTo-- [goto-target identifier]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::GoTo
    ::goto-target goto-target
    ::identifier  identifier}})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro GoTo [goto-target identifier]
  `(GoTo-- ~goto-target '~identifier))
;; #+end_src

;; ----------------------------------------------------------------
;; ## IF
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | If(expr test, stmt* body, stmt* orelse)
;; ```

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(If
 (NamedExpr
  (Var 2 a)
  (StringOrd
   (StringConstant
    "3"
    (Character 1 1 () [])  )
   (Integer 4 [])
   (IntegerConstant 51 (Integer 4 []))  )
  (Integer 4 [])  )
 [(=
   (Var 2 x)
   (IntegerConstant 1 (Integer 4 []))
   ()
   )]  []  )
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn If [test-expr body orelse]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::If
    ::test-expr test-expr
    ::body      body
    ::orelse    orelse}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## ASSIGNMENT
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | Assignment(expr target, expr value, stmt? overloaded)
;;          --- Var ---
;; ```

;;
;; ### Issues
;;
;;
;; https://github.com/rebcabin/masr/issues/21
;; https://github.com/rebcabin/masr/issues/22
;; https://github.com/rebcabin/masr/issues/26

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn Assignment [lhs, rhs, unk]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head   ::Assignment
    ::lvalue      lhs
    ::rvalue      rhs
    ::overloaded  unk}})
;; #+end_src


;; ----------------------------------------------------------------
;; ## DO LOOP
;;
;;

;;
;; ### Example
;;
;;

;; #+begin_src clojure

#_(DoLoop
 ()
 ((Var 2 i)
  (IntegerConstant 0 (Integer 4 []))
  (IntegerBinOp
   (IntegerConstant 50 (Integer 4 []))
   Sub
   (IntegerConstant 1 (Integer 4 []))
   (Integer 4 [])
   (IntegerConstant 49 (Integer 4 [])))
  (IntegerConstant 1 (Integer 4 [])))
 [(ListAppend
   (Var 2 l3)
   (Var 2 i)
   )] )
;; #+end_src

;;
;; ### Do-Loop Head Support
;;
;; #+begin_src clojure

(defn do-loop-head [[var start end incr]]
  {::loop-v         var
   ::loop-start     start
   ::loop-end       end
   ::loop-increment incr})
;; #+end_src


;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn DoLoop [escape-target
              [var start end incr]
              body]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head ::DoLoop
    ::escape-target   escape-target
    ::do-loop-head
    {::loop-v         var
     ::loop-start     start
     ::loop-end       end
     ::loop-increment incr}
    ::body            body}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## WHILE LOOP
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | WhileLoop(expr test, stmt* body)
;; ```

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(WhileLoop
 ()
 (NamedExpr
  (Var 2 a)
  (IntegerConstant 1 (Integer 4 []))
  (Integer 4 [])
  )
 [(=
   (Var 2 y)
   (IntegerConstant 1 (Integer 4 []))
   ()
   )]
 )
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn WhileLoop
  [escape-target, test-expr, body]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::WhileLoop
    ::escape-target escape-target
    ::test-expr        test-expr
    ::body             body}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## PRINT
;;
;;


;; ### Original ASDL
;;
;; ```c
;; | Print(expr? fmt, expr* values, expr? separator, expr? end)
;; ```
;;

;;
;; ### Heavy Sugar
;;
;;
;; #+begin_src clojure

(defn Print [fmt, value*, separator, end]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head ::Print
    ::format?    fmt
    ::value*     value*
    ::separator? separator
    ::end?       end}
   })
;; #+end_src

;; ----------------------------------------------------------------
;; ## RETURN
;;
;;

;; #+begin_src clojure

(defn Return []
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head ::Return}})
;; #+end_src

;; ----------------------------------------------------------------
;; ## SUBROUTINE CALL
;;
;;

;; `SubroutineCall` is a special case because it
;; abuses the word `symbol` to mean a `symbol-ref`.
;;
;;
;; ### Original ASDL
;;
;;
;; ```c
;; SubroutineCall(symbol     name,          ~~~> symref
;;                symbol   ? original_name, ~~~> orig-symref
;;                call_arg * args,          ~~~> call_args
;;                expr     ? dt)
;; ```

;; #+begin_src clojure

(s/def ::dt? ::expr?)
;; #+end_src

;;
;; ### Examples
;;
;; #+begin_src clojure

#_
(SubroutineCall
   7 test_fn1
   ()
   []
   ())

#_
(SubroutineCall
   7 test_fn1
   ()
   ((Var 42 i))
   ())
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn SubroutineCall--
  [subr-symref, orig-symref, args, dt?]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head    ::SubroutineCall
    ::symbol-ref   subr-symref
    ::orig-symref  orig-symref ;; TODO
    ::call-args    args
    ::dt?          dt?
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro SubroutineCall
  [stid, ident, orig-symref, args, dt?]
  `(SubroutineCall-- (symbol-ref '~ident ~stid)
                     ~orig-symref
                     ;; Took a while to find this ...
                     ;; (map vec ~args) does not work!
                     (map (fn [a#] [a#]) ~args)
                     ~dt?));; #+end_src

;; ----------------------------------------------------------------
;; ## BLOCK CALL
;;
;;

;; `BlockCall` abuses `symbol` to mean `symbol-ref`.
;;
;;
;; ### Original ASDL
;;
;;
;; ```c
;; BlockCall(int    label,
;;           symbol m)   // <~~~ symref
;; ```

;; #+begin_src clojure

(s/def ::label ::nat)
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn BlockCall--
  [label, symref]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head    ::BlockCall
    ::label        label
    ::symbol-ref   symref
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro BlockCall
  [label,
   stid ident]
  `(BlockCall--
    ~label
    (symbol-ref '~ident ~stid)))
;; #+end_src


;;
;;
;; # SYMBOL
;;
;;

;; ----------------------------------------------------------------
;; ## Prerequisite Types and Aliases
;;
;;
;; #+begin_src clojure

(s/def ::modulenym          ::identifier)
(s/def ::loaded-from-mod    ::bool)
(s/def ::intrinsic          ::bool)
;; #+end_src

;; #+begin_src clojure

(s/def ::function-name      ::identifier)
(s/def ::function-signature ::FunctionType)
;; #+end_src

;; #+begin_src clojure

(s/def ::prognym            ::identifier)
(s/def ::blocknym           ::identifier)
;; #+end_src

;; ----------------------------------------------------------------
;; ## PROGRAM
;;
;;

;;
;; ### Original ASDL
;;
;;
;; ```c
;; = Program(symbol_table symtab,
;;           identifier   name,
;;           identifier*  dependencies,
;;           stmt*        body)
;; ```

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn Program-- [stab, nym, deps, body-]
  {::term ::symbol,
   ::asr-symbol-head
   {::symbol-head  ::Program
    ::SymbolTable  stab
    ::prognym      nym
    ::dependencies deps
    ::body         body-}})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro Program
  "Quote the nym and the dependencies."
  [stab, nym, deps, body-]
  `(Program--
    ~stab,
    '~nym,
    (for [e# '~deps] e#),
    ~body-))
;; #+end_src


;; ----------------------------------------------------------------
;; ## MODULE
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | Module(symbol_table   symtab,
;;          identifier     name,
;;          identifier   * dependencies,
;;          bool           loaded_from_mod,
;;          bool           intrinsic)
;; ```

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn Module-- [symtab, modnym, deps, loaded, intrinsic-]
  {::term ::symbol
   ::asr-symbol-head
   {::symbol-head     ::Module
    ::SymbolTable     symtab
    ::modulenym       modnym
    ::dependencies    deps
    ::loaded-from-mod loaded
    ::intrinsic       intrinsic-}})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro Module
  "Quote the mondnym and the deps."
  [symtab, modnym, deps, loaded, intrinsic-]
  (let [quotes (vec (for [d deps] `'~d))]
    `(Module--
      ~symtab
      '~modnym
      ~quotes
      ~loaded
      ~intrinsic-)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## FUNCTION
;;
;;

;;
;; ### Original ASDL
;;
;; ```c
;; | Function(symbol_table symtab,
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
;; ```

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn Function-- [symtab,
                  fnnym,   fnsig,  deps,
                  param*-, body-,  retvar?,
                  access-, determ, sefree]
  {::term ::symbol
   ::asr-symbol-head
   {::symbol-head         ::Function

    ::SymbolTable         symtab

    ::function-name       fnnym
    ::function-signature  fnsig
    ::dependencies        deps

    ::param*              param*-
    ::body                body-
    ::return-var?         retvar?

    ::access              access-
    ::deterministic       determ
    ::side-effect-free    sefree
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; The bodies of functions can get very big, too big
;; for Clojure to eval due to a limit in Java of
;; 64KB per method body (ludicrous in 2023). So
;; we'll just iterate over the statements in the
;; bodies, replacing them with their full-forms.
;;
;; #+begin_src clojure

(defmacro Function
  "Quote the fnnym and the deps."
  [symtab,
   fnnym,   fnsig,  deps,
   param*-, body-,  retvar?,
   access-, determ, sefree]
  `(Function-- ~symtab,
               '~fnnym,  ~fnsig,  (for [d# '~deps] d#),
               ~param*-,
               ~body-,  ;; <~~~ iterate over the statements inside
               ~retvar?,
               ~access-, ~determ, ~sefree))
;; #+end_src

;; ----------------------------------------------------------------
;; ## GENERIC PROCEDURE
;;
;;

;; ### Original ASDL
;;
;;
;; ```c
;; GenericProcedure(symbol_table   parent_symtab, <~~~ symtab-id
;;                  identifier     name,
;;                  symbol       * procs,         <~~~ symbol-refs
;;                  access         access)
;; ```

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(GenericProcedure
 3
 arccos
 [3 __lpython_overloaded_0__arccos
  3 __lpython_overloaded_1__arccos]
 Public )
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn GenericProcedure--
  [stid, fnym, symrefs, access]
  {::term ::symbol,
   ::asr-symbol-head
   {::symbol-head   ::GenericProcedure
    ::symtab-id     stid
    ::function-name fnym
    ::symbol-ref*   symrefs
    ::access        access}
   })
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro GenericProcedure
  [stid, fnym, naked-pairs, access]
  (let [pairs (partition 2 naked-pairs)
        fpairs (for [[fid fnym] pairs]
                 (symbol-ref fnym fid) )]
    `(GenericProcedure--
      ~stid
      '~fnym
      '~fpairs
      ~access)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## EXTERNAL SYMBOL
;;
;;

;; ### Original ASDL
;;
;;
;; ```c
;; | ExternalSymbol(symbol_table parent_symtab, ~~~> symtab-id
;;                  identifier   name,          ~~~> nym
;;                  symbol       external,      ~~~> extern-symref
;;                  identifier   module_name,   ~~~> module-nym
;;                  identifier*  scope_names,   ~~~> scope-nyms
;;                  identifier   original_name, ~~~> orig-nym
;;                  access       access)        ~~~> access
;; ```

;;
;; ### Example
;;
;; #+begin_src clojure

#_
(ExternalSymbol
 5
 _lpython_main_program
 7 _lpython_main_program   ;; either () or a naked pair
 _global_symbols
 []
 _lpython_main_program
 Public
 )
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn ExternalSymbol--
  [stid,    nym-,        extern-symref-
   modnym-, scope-nyms-, orig-nym-,
   access- ]
  {::term           ::symbol,
   ::asr-symbol-head
   {::symbol-head   ::ExternalSymbol,

    ::symtab-id     stid
    ::nym           nym-
    ::extern-symref extern-symref-

    ::modulenym     modnym-
    ::scope-nyms    scope-nyms-,
    ::orig-nym      orig-nym-,

    ::access        access-}
   })
;; #+end_src

;;
;; ### Legacy Sugar
;;
;;

;; #+begin_src clojure

(defmacro ExternalSymbol
  ([stid, nym,
    orig-symref-stid, orig-symref-ident,
    modnym, scope-nyms, orig-nym,
    access]
   "octenary"
   `(ExternalSymbol--
     ~stid, '~nym,
     (symbol-ref '~orig-symref-ident, ~orig-symref-stid),
     '~modnym,
     ~scope-nyms, ;; TODO: distribute quote?
     '~orig-nym
     ~access))
  ([stid, nym,
    empty-symref,
    modnym, scope-nyms, orig-nym,
    access]
   "heptenary"
   `(ExternalSymbol--
     ~stid, '~nym,
     ~empty-symref,
     '~modnym,
     ~scope-nyms, ;; TODO: distribute quote?
     '~orig-nym
     ~access)))
;; #+end_src

;; ----------------------------------------------------------------
;; ## VARIABLE
;;
;;

;;
;; ### Original ASDL
;;
;;
;; ```c
;; | Variable(symbol_table   parent_symtab,   ;; really an integer id
;;            identifier     name,
;;            identifier   * dependencies,    ;; vector of dependency
;;            intent         intent,
;;            expr         ? symbolic_value,  ;; lack specified by nil
;;            expr         ? value,           ;; replace with value?
;;            storage_type   storage,
;;            ttype          type,
;;            abi            abi,
;;            access         access,
;;            presence       presence,
;;            bool           value_attr)
;; ```
;;
;;
;; ### Example
;;
;;
;; #+begin_src clojure

#_
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
 .false.)                ;   bool            value-attr
;; #+end_src

;;
;; ### Light Sugar
;;
;; #+begin_src clojure

(defn Variable-
  [& {:keys [ ;; required
             symtab-id,          varnym,         ttype,
             ;; defaulted
             type-declaration,   dependencies,   intent,
             symbolic-value,     value?,         storage-type,
             abi,                access,         presence,
             value-attr
             ]
      :or {type-declaration nil
           dependencies     #{}
           intent           (intent 'Local)

           symbolic-value   ()
           value?           ()
           storage-type     (storage-type 'Default)

           abi              Source
           access           Public
           presence         Required
           value-attr       false}}]
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
    ::value?           value?,
    ::storage-type     storage-type,

    ::abi              abi,
    ::access           access,
    ::presence         presence,

    ::value-attr       value-attr,
    }})
;; #+end_src

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn Variable--
  "Heavy sugar; parameters that collide with functions
  have trailing hyphens."
  [symtab-id-,         varnym-,        ttype-,
   typedecl-,          dependencies-,  intent-,
   symbolic-value-,    value?-,        storage-type-,
   abi-,               access-,        presence-,
   value-attr-]
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
    ::value?           value?-,
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
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro Variable
  "Honor legacy parameter order of
  lpython/src/libasr/ASR.asdl as of 25 April 2023.
  Quote the varnym and dependencies; pass along
  all other params."
  [symtab-id-,     varnym-,          dependencies-,
   intent-,        symbolic-value-,  value?-,
   storage-type-,  ttype-,           abi-,
   access-,        presence-,        value-attr-]
  `(Variable-- ;; heavy sugar
    ~symtab-id-
    '~varnym-  ;; notice the tick mark
    ~ttype-    ;; moved up from between storage type and abi
    nil ;; legacy doesn't have type-declaration
    (for [d# '~dependencies-] d#)
    ~intent-
    ~symbolic-value-
    ~value?-
    ~storage-type-
    ;; ttype goes here in legacy
    ~abi-
    ~access-
    ~presence-
    ~value-attr-))
;; #+end_src

;; ----------------------------------------------------------------
;; ## BLOCK
;;
;;

;;
;; ### Original ASDL
;;
;;
;; ```c
;; Block(symbol_table symtab, identifier name, stmt* body)
;; ```
;;


;;
;; Heavy Sugar
;;
;; #+begin_src clojure

(defn Block-- [SymbolTable
               blocknym
               body]
  {::term ::symbol
   ::asr-symbol-head
   {::symbol-head     ::Block
    ::SymbolTable     SymbolTable
    ::blocknym        blocknym
    ::body            body
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro Block [SymbolTable
                 blocknym
                 body]
  `(Block-- ~SymbolTable '~blocknym ~body))
;; #+end_src

;; ----------------------------------------------------------------
;; ## INTRINSIC MODULE
;;
;;

;;
;; ### Original ASDL
;;
;;
;; There is no ASDL for this symbol in our snapshot.

;;
;; Heavy Sugar
;;
;; #+begin_src clojure

(defn IntrinsicModule-- [modnym]
  {::term ::symbol
   ::asr-symbol-head
   {::symbol-head     ::IntrinsicModule
    ::modulenym       modnym}})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro IntrinsicModule [modnym]
  `(IntrinsicModule-- '~modnym))
;; #+end_src

;;
;;
;; # UNIT
;;
;;

;; ----------------------------------------------------------------
;; ## Prerequisite Type Aliases
;;
;;
;; #+begin_src clojure

(s/def ::node (s/or :expr   ::expr
                    :stmt   ::stmt
                    :symbol ::symbol))

(defn node [candidate] candidate)
;; #+end_src

;;
;; ## Pluralities
;;
;;

;;
;; TODO: Consider a regex-spec.
;;
;; #+begin_src clojure

(s/def ::nodes (.* ::node))
;; #+end_src

;; ----------------------------------------------------------------
;; ## TRANSLATION UNIT
;;
;;

;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn TranslationUnit [stab, node-preimages]
  (let [node-cnd (map node node-preimages)]
    {::term          ::unit
     ::asr-unit-head
     {::unit-head    ::TranslationUnit
      ::SymbolTable  stab
      ::nodes        node-cnd}}))
;; #+end_src
