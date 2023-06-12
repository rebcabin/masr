;;
;;
;; # PROLOGUE
;;
;;


;;
;; MASR converts _ASR S-expressions_ like this:
;;
;; #+begin_src clojure

'(TranslationUnit
 (SymbolTable 42 {})
 [(Program
   (SymbolTable 3 {})
   main_program
   []
   [(= (Var 2 a)
       (LogicalConstant false (Logical 4 []))
       ())])])
;; #+end_src

;;
;; into _entity hash-maps_ in _full-form_, like this:
;;
;; #+begin_src clojure

{:term :m/unit,
 :asr-unit-head
 #:m{:unit-head     :m/TranslationUnit,
     :SymbolTable  #:m{:term       :m/SymbolTable,
                       :symtab-id  42,
                       :hash-map   {}},
     :nodes
     [#:m{:term :m/symbol,
          :asr-symbol-head
          #:m{:symbol-head   :m/Program,
              :SymbolTable  #:m{:term :m/SymbolTable,
                                :symtab-id 3,
                                :hash-map
                                {}},
              :prognym      "main_program",
              :dependencies [],
              :body
              [#:m{:term  :m/stmt,
                   :asr-stmt-head
                   #:m{:stmt-head :m/Assignment,
                       :lvalue   #:m{:term  :m/expr,
                                     :asr-expr-head
                                     #:m{:expr-head  :m/Var,
                                         :symtab-id  2,
                                         :varnym     "a"}},
                       :rvalue   #:m{:term  :m/expr,
                                     :asr-expr-head
                                     #:m{:Logical
                                         #:m{:term  :m/ttype,
                                             :asr-ttype-head
                                             #:m{:logical-kind  4,
                                                 :ttype-head
                                                 :m/Logical,
                                                 :dimension*   []}},
                                         :expr-head  :m/LogicalConstant,
                                         :bool      false}},
                       :overloaded  ()}}]}}]}}
;; #+end_src

;;
;; Entity hash-maps have all information from the
;; ASR S-expression syntax, but type-checked and in
;; a form for downstream analysis, interpretation,
;; optimization, code-generation, storage,
;; retrieval, decompilation, etc. While the ASR
;; S-expression syntax is more terse, it cannot be
;; understood without reference to ASDL
;; documentation because the component data items
;; are not labeled. The entity hash-map is intended
;; to be more intelligible and self-contained.
;;
;;
;; The type-checking performed during conversion is
;; aggressive and fine-grained. It is intended to
;; find bugs in compiler front ends like LPython and
;; LFortran.
;;

;;
;; ## Maintaining the Code and Markdown
;;

;;
;; This file is semi-literate programming. Blocks of
;; code in this Markdown file, `specs.md`, are
;; extracted from the live source code in the file
;; `specs.clj`. This Markdown file, if extracted
;; properly, cannot get out-of-date with respect to
;; the code.
;;
;;
;; To contribute, write code in the source files,
;; mostly in `specs.clj`. Write accurate comments
;; and format them in Markdown. Precede a code block
;; with `#+_begin_src clojure` (or some other
;; language like `bash` or `c`) in a
;; double-semicolon-space comment beginning in
;; column 1, by itself, plus a blank line. Terminate
;; code blocks with `#+end_src` in a
;; double-semicolon-space comment beginning in
;; column 1. You'll see many examples below.
;;
;;
;; This file is in order of definitions before
;; usages, not in top-down narrative order. Skim
;; over the definitions until you get to use cases,
;; then backtrack for details. Alternatively, read
;; the documentation _backwards_ for an
;; approximation of narrative order.
;;
;;
;; When narrative really MUST talk about use-cases
;; that are not defined yet, comment out the code or
;; escape S-expressions with `#_`. You will see
;; examples below.
;;
;;
;; Just before checking in new code, extract
;; `specs.md` from `specs.clj` as follows:
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
;; ## Reading the Markdown
;;

;;
;; The Markdown viewer in Visual Studio Code is
;; _not_ adequate for reading this file. It jumps
;; around and gets stuck. We recommend the Markdown
;; viewer in PyCharm or CLion. There are numerous
;; paid Markdown viewers on the Mac, but we have not
;; tried them.
;;

;;
;; # NAMESPACE DECLARATION
;;
;;
;; Declare Clojure dependencies for the rest of the
;; code in this file.
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
;; Currently used in `simplespecs.clj`, but
;; dependency retained here for convenience going
;; forward.
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
;; via the full syntax `java.lang.Integer`
;; `java.lang.Character`. We also want `deftype`.
;; Access original `deftype` as
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
;; ## Masr is a Type System
;;
;;
;; MASR is "Meta Abstract Semantics Representation,"
;; and also a physics pun, "Microwave Amplification
;; by Stimulated emission of Radiation." It is a
;; work-in-progress, intended eventually to replace
;; the aging ASDL with by a more precise and
;; discriminating type system.
;;
;; * MASR is more precise than ASDL; For example,
;;   MASR distinguishes names of programs from names
;;   of functions.
;;
;; * MASR is more explicit than ASDL. For example,
;;   we can say a MASR argument list has no more
;;   than a certain number of arguments.
;;
;; * MASR is less overloaded than ASDL. For example,
;;   `symbol` is separate from `symbol_table`,
;;   `symbol-ref` in MASR, but they are all the same
;;   in ASDL.
;;
;; * MASR exposes secret semantics that ASDL cannot
;;   express. TODO: example.
;;
;;
;; Until MASR is integrated, it will have a legacy
;; back-channel. The legacy back-channel writes
;; types in ASDL format from MASR entity hash-maps.
;;

;; ----------------------------------------------------------------
;; ## Snapshot Summary
;;

;;
;; We begin with a summary of a snapshot of the full
;; ASDL specification:
;; https://github.com/rebcabin/masr/blob/main/ASR_2023_APR_06_snapshot.asdl
;;

;;
;; ### Terms (Nodes) in the ASDL Grammar
;;
;;
;; `Terms` appear to the left of equals signs. Terms
;; may also be called `nodes`.
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
;;  9 stmt            = ... many heads ...
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
;; 31 symbol_table    = a clojure hash-map
;; 32 dimension*      = see below
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
;; The main use-case for specifications is checking
;; entity hash-maps against specs: "does this entity
;; hash-map meet the general specification for
;; entity hash-maps of this type?" For
;; example, "does `(Integer 4 [])`, syntax sugar for
;; an entity hash-map, meet the general
;; specification of an ASR `ttype`, which describes
;; a whole class of entity hash-maps?"
;;
;;
;; In MASR, specs describe decidable _sets_ of valid
;; or conforming values. MASR type checking is often
;; just checking whether an instance inhabits a
;; certain set. A "decidable set" is one for which
;; the question of set membership is decidable. See
;; this Stack-Exchange question for theoretical fine
;; points:
;;
;;
;;   https://math.stackexchange.com/questions/489369
;;
;;
;; Clojure specs are arbitrary boolean-valued
;; functions. We can build any type-theory that
;; needs only first-order predicate calculus.
;; Clojure specs suffice for advanced types like
;; dependency types and concurrency types.
;;

;; ----------------------------------------------------------------
;; ## Checking Instances
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
;; Every MASR `asr-term` entity has a _full-form_. A
;; MASR full-form is a Clojure _hash-map_ that
;; contains the key `::term` at top level. Hash-maps
;; are collections of key-value pairs like Python
;; dictionaries.[https://clojuredocs.org/clojure.core/hash-map]
;;
;;
;; MASR checks full-form against Clojure specs.
;; For example,
;;
;;
;; #+begin_src clojure

;; key         value
{::term        ::intent,
 ::intent-enum "Local"}
;; #+end_src

;;
;; is an entity checked against specs for `::term`,
;; and `::intent-enum`.
;;

;;
;; ## Fully Qualified Keywords
;;

;;
;; All hash-map keys in MASR are _fully qualified
;; keywords_ (FQKWs) in the namespace `masr.specs`,
;; denoted with double-colons when _in_ that
;; namespace.
;;
;;
;; This file, `specs.clj`, is automatically _in_
;; namespace `masr.specs`, so FQKWs like `::term`
;; are written with double colons.
;;
;;
;; In other files, there are multiple options for
;; writing FQKWs in the namespace `masr.specs`: with
;; an explicit prefix as in `:masr.specs/intent`, or
;; with a namespace alias as in `::asr/intent`. The
;; test file, `core_tests.clj`, employs the
;; namespace alias `asr`. Other options include a
;; namespace distributed across unqualified keys in
;; a hash map, as in
;;
;; #+begin_src clojure

#:masr.specs{:term       :masr.specs/SymbolTable,
             :symtab-id  42,
             :hash-map   {}}
;; #+end_src

;;
;; FQKWs may have specs registered for them. When a
;; spec is registered for an FQKW, automatically
;; checks types of entities recursively. For
;; example, an entity that conforms to
;; `::SymbolTable` will have a `::term`,
;; `::symtab-id`, and a `::hash-map` that conform to
;; specs registered to those FQKWs.
;;
;;
;; EXAMPLES -- all the following full-forms mean the
;; same:
;;
;; * always acceptable, though verbose:
;;
;; #+begin_src clojure

{:masr.specs/term        :masr.specs/intent,
 :masr.specs/intent-enum "Unspecified"}
;; #+end_src

;;
;; * Clojure-standard distributed form, always
;;   acceptable:
;;
;; #+begin_src clojure

#:masr.specs{:term        :masr.specs/intent,
             :intent-enum "Unspecified"}
;; #+end_src

;;
;; * when in this file or _in_ namespace
;;   `masr.specs` via the
;;   line `(in-ns 'masr.specs)`:
;;
;; #+begin_src clojure

{::term        ::intent,
 ::intent-enum "Unspecified"}
;; #+end_src

;;
;; * if `masr.specs` is aliased to `asr`, as in
;; `(:use [masr.specs :as asr])` in
;; `core_test.clj` (commented out because it's not
;; executable in the file `specs.clj`):
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
;; # IDEMPOTENCY
;;
;;


;;
;; Large ASR expressions overflow the Java
;; method-size limit of 64KB when Clojure evaluates
;; them recursively. The solution is to evaluate
;; bottom-up: explode sub-entities into files or a
;; database, then implode them back into a top-level
;; entity. The file `big_test.clj` shows how to do
;; this.
;;
;;
;; Bottom-up evaluation also requires idempotency:
;; evaluating a sub-entity full-form produces the
;; same full-form. This is easy only if we replace
;; symbols with strings because the mechanics of
;; quoting symbols idempotently is difficult and
;; subtle.
;;
;;
;; For an example, consider the following hash-map,
;; and notice the external quote, preventing
;; evaluation. Without this quote, Clojure would
;; error when evaluating this expression a second
;; time because it would attempt to evaluate the
;; unbound symbol `main0`:
;;
;; #+begin_src clojure

'#:masr.specs{:stmt-head  ;; <~~~ external quote
             :masr.specs/SubroutineCall,
             :symbol-ref
             #:masr.specs{:identifier  main0,
                          :symtab-id   114},
             :orig-symref  (),
             :call-args    (),
             :dt?          ()}
;; #+end_src

;;
;; The above is the spec produced by evaluating the
;; old, non-idempotent heavy-sugar function,
;; `SubroutineCall`. The naked symbol `main0` is
;; unbound and won't survive another round of
;; evaluation. The sugar function now produces
;; to the following:
;;
;; #+begin_src clojure

#:masr.specs{:stmt-head  ;; <~~~ difference
             :masr.specs/SubroutineCall,
             :symbol-ref
             #:masr.specs{:identifier
                          "main0",  ;; <~~~ difference
                          :symtab-id  114},
             :orig-symref  (),
             :call-args    (),
             :dt?          ()}
;; #+end_src

;;
;; Every element of that hash-map is
;; self-evaluating: keywords, numbers, strings, the
;; empty list `()`. It turns out that hash-maps and
;; vectors with self-evaluating elements are also
;; self-evaluating. We're in business if we replace
;; all unbound symbols with strings.
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
;; 2. compatible with output from `--show-asr` in
;;    lpython and lfortran.
;;
;;
;; Sugared forms are function-calls at
;; bottom (examples below). Some sugared forms
;; employ macros to replace symbols with strings and
;; for other utilitarian transformations on the way
;; to bottoming out at a function call.
;;
;;
;; Sugar comes in three flavors: _light_, _heavy_,
;; and _legacy_.
;;
;;
;; 1. Light sugar employs functions with
;;    single-colon, non-qualified keyword (NQKW)
;;    arguments with default values. Light sugar is
;;    unambiguous but more verbose than heavy sugar.
;;
;;
;; 2. Heavy sugar employs functions with positional
;;    arguments, with possible default values for
;;    tail arguments. Heavy sugar is short and
;;    often compatible with ASDL output from
;;    `--show-asr`. Heavy sugar is more risky to
;;    write and much harder to read than light
;;    sugar, especially for long argument lists as
;;    with, say, `Variable` and `FunctionType`.
;;
;;
;; 3. Legacy sugar is like heavy sugar, just, say,
;;    requiring fewer tick marks on symbols. Legacy
;;    sugar is compatible with ASDL output from
;;    `--show-asr`.
;;
;;
;; All sugared forms produce identical full-forms.
;;

;; ----------------------------------------------------------------
;; ## Naming Convention for Sugar
;;

;;
;; ### Light Sugar
;;
;;
;; The names of light-sugar functions, like
;; `Integer-`, have a single trailing hyphen. The
;; keyword arguments of light-sugar functions are
;; partitioned into _required_ and
;; _optional-with-defaults_. The keyword-argument
;; lists of light-sugar functions do not depend on
;; order. The following two examples of light sugar
;; both conform to specs registered for `::asr-term`
;; and to `::ttype` (the following is an example of
;; escaped code that can't run yet because of
;; narrative order):
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
;; two hyphens, like `Variable--`. For example:
;;

;; #+begin_src clojure

#_(Variable-- 2 "x" (Integer 4)
            nil [] Local
            [] []  Default
            Source Public Required
            false)
;; #+end_src

;;
;;
;; Heavy sugar and legacy sugar employ positional
;; arguments that depend on order. Heavy-sugar
;; functions may have final arguments with defaults.
;; The following examples of heavy sugar all conform
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
;; The purpose of legacy sugar is to convert symbols
;; to idempotent strings and to accommodate certain
;; defects in the original design of ASDL, such as
;; (1) nested lists' denoting improper function
;; calls and (2) ambiguity in `symbol-ref`,
;; sometimes a list and sometimes a naked pair.
;;
;;
;; Here is a legacy version of the `Variable` above:
;;
;;
;; #+begin_src clojure

#_
(Variable 2 x [] ;; <~~~ unquoted `x`
          Local () ()
          Default (Integer 4 []) Source
          Public Required false)
;; #+end_src

;;
;; Notice NO QUOTES on the name of the variable.
;; That's the way `--show-asr` prints it.
;;
;;
;; For specs like `Integer`, where heavy sugar and
;; legacy are identical, there is no function with
;; two trailing hyphens in its name.
;;


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
;;
;; MASR alternatives are called _heads_.
;;


;;
;;
;; # QUALIFIED KEYWORDS ARE FUNCTIONS
;;
;;


;;
;; `::term` is both a FQKW _and_ a tag-fetching
;; function. A tag-fetching function picks the value
;; of the key `::term` from any hash-map. For
;; example,
;;
;;
;; `(::term {::term ::intent ...})`
;;
;;
;; calls the function `::term` with argument
;; `{::term ::intent ...}` and produces `::intent`.
;;
;;
;; As a FQKW, `::term`, in addition to
;; being a tag-fetching function, can name a Clojure
;; spec registered to it via `s/def`. The following
;; spec will check whether `::intent` is a `::term`:
;;
;;
;; #+begin_src clojure

(s/def ::term qualified-keyword?)
;; #+end_src

;;
;; EXAMPLE: `::intent` is a valid `::term` because
;; `::intent` is a FQKW.
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
;; `defmulti/defmethod` is one Clojure idiom for
;; _polymorphism_:, a single `defmulti` interface
;; with many `defmethod` implementations.
;;
;;
;; `(defmulti term ::term)` links the name
;; `term` (no colons) to the tag-fetcher function
;; `::term` (with colons). Each `defmethod` of
;; `term` is tagged by the value fetched via
;; `::term`. For example, there is one `defmethod`
;; for `::symbol` and another for `::expr`.
;;
;; #+begin_src clojure

(defmulti term ::term)
#_(defmethod term ::symbol [_] ,,,)
#_(defmethod term ::expr   [_] ,,,)
,,,
;; #+end_src

;;
;; MASR handles _alternatives_ or _heads_ -- to the
;; right-hand sides of equals signs in ASDL -- via
;; _multi-specs_. By analogy, multi-specs are to
;; specs as `defmethods` are to functions -- one
;; spec interface to many implementations.
;; Multi-specs are thus another Clojure idiom for
;; polymorphism.
;;
;;
;; The name of the _one_ multi-spec for all terms is
;; `::asr-term`, an FQKW, as are all names of specs.
;; Multi-specs act like tagged unions in C -- MASR's
;; polymorphic entities are like polymorphic structs
;; in C.
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
;; entities conforming to the particular spec named
;; by `::intent`, `::symbol`, `::unit`, etc.
;;
;;
;; Some `defmethods` like `::intent` are simple,
;; just checking that an instance like `"Local"` or
;; `"ReturnVar"` inhabits a literal Clojure `set` of
;; allowed intents. Other `defmethods`, like
;; `::symbol`, have _nested multi-specs_ that
;; dispatch on _heads_, like `Variable` or
;; `Program`. MASR handles nested multi-specs via
;; techniques shown below.
;;


;;
;;
;; # NAMING CONVENTION FOR MULTI-SPECS
;;
;;


;;
;; The names of all multi-specs in MASR, nested or
;; not, begin with `::asr-...`, as in
;; `::asr-term` (not nested) and
;; `::asr-ttype-head`, nested in `ttypes`, and
;; `::asr-expr-head`, nested in `expr`.
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
;; A certain given entity (instance hash-map) may be
;;
;; * an `::asr-term` -- any one of the many terms
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


;;
;;
;; # TERM ENTITY KEY
;;
;;


;;
;; Each term, like `symbol`, needs its own spec,
;; named by a FQKW like `::symbol`. MASR recursively
;; checks `::symbol` fields in other specs.
;;
;;
;; `Def-term-entity-key` registers a spec for
;; `symbol`, for instance. That spec checks that a
;; given entity is an `::asr-term` and its `::term`
;; equals a given FQKW like `::symbol`.
;;
;;
;; `Def-term-entity-key` must be called for any
;; hand-written spec like `dimension`. It's
;; automatically invoked for any term defined via
;; `defmasrnested`, which appears immediately below.
;;
;;
;; #+begin_src clojure

(defn term-selector-spec
  "Return a s/and spec that checks that a given
  entity is an ::asr-term entity and that its
  ::term equals a given FQKW, e.g., that the
  ::term of an entity is ::symbol."
  [fqkw]
  (s/and ::asr-term
         #(= fqkw (::term %))))


(defmacro def-term-entity-key
  "Register a spec for an entity key like ::symbol
  or ::expr, which is an ::asr-term, a top-level
  production in the ASDL grammar."
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
;; deletion of namespaces in macros. We step around
;; around it when necessary via Clojure's built-in
;; `name` function.
;;
;;
;; #+begin_src clojure

(defmacro defmasrnested
  "Automate constructions like the following, which
  pertain to certain `::term` specs that have nested
  multi-specs, e.g., `::expr`, `::symbol`, `::stmt`.
  The only pertinent token is `expr`, `symbol`, etc.,
  and this macro mitigates the repetition of that
  token.

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
        tcst (symbol "term")     ;; e.g. term (no ns)

        tstr (str term)          ;; e.g. "expr"
        tkwd (keyword ns tstr)   ;; e.g. ::expr
        tsym (symbol (name (symbol tstr))) ;; e.g. expr

        estr (str term "-head")  ;; e.g. "expr-head"
        ekwd (keyword ns estr)   ;; e.g. ::expr-head
        esym (symbol (name (symbol estr))) ;; e.g. expr-head

        ;; e.g. ::asr-expr-head
        akwd (keyword ns (str "asr-" (name term) "-head"))
        ]
    ;; e.g.          term  ::expr  [_]
    `(do (defmethod ~tcst ~tkwd    [_#]
           ;; e.g.      ::term ::asr-expr-head
           (s/keys :req [~ttrm ~akwd]))
         ;; e.g.               expr
         (def-term-entity-key ~tsym)
         ;; e.g.    expr-head ::expr-head
         (defmulti ~esym       ~ekwd)
         ;; e.g. ::asr-expr-head
         (s/def ~akwd
           ;; e.g.        expr-head ::expr-head
           (s/multi-spec ~esym       ~ekwd))
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
;; We need specs for each nested multi-spec for
;; heads like `::Variable` and `::FunctionType`.
;;
;;
;; #+begin_src clojure

(defmacro def-term-head--entity-key
  "Define an entity key like ::Variable, which is an
  ::asr-symbol-head nested multi-spec, eliminating
  duplicated wordage.

  From a term, e.g., `symbol`, and head, e.g., `Variable`,
  generate a spec `s/def` like

      (s/def ::Variable               ;; head entity key
        (s/and ::asr-term             ;; top multi-spec
          #(= ::Variable              ;; nested tag
              (-> % ::asr-symbol-head ;; nested multi-spec
                    ::symbol-head)))) ;; tag fetcher

  For another example, From term `stmt`, and head
  `Assignment`, generate a spec `s/def` like

      (s/def ::Assignment             ;; head entity key
        (s/and ::asr-term             ;; top multi-spec
          #(= ::Assignment            ;; nested tag
              (-> % ::asr-stmt-head   ;; nested multi-spec
                    ::stmt-head       ;; tag fetcher"
  [term, ;; e.g. symbol
   head] ;; e.g. Variable
  (let [ns "masr.specs"
        trm (keyword ns "term")     ;; e.g. ::term
        art (keyword ns "asr-term") ;; e.g. ::asr-term
        hkw (keyword ns (str head)) ;; e.g. ::Variable
        tmh (keyword ns (str term "-head")) ;; e.g. ::symbol-head
        amh (keyword ns ;; for the multi-spec
                     ;; e.g. ::asr-symbol-head
                     (str "asr-" term "-head"))]
    ;; e.g.  ::Variable
    `(s/def ~hkw
       ;; e.g. ::asr-term
       (s/and ~art
              ;;   ::Variable ::asr-symbol-head ::symbol-head
              #(= ~hkw (-> % ~amh ~tmh))))))
;; #+end_src


;;
;;
;; # DEFMASRTYPE
;;
;;


;;
;; `Defmasrtype` is the easiest way to add new specs
;; that have nested multi-specs. Terms without
;; nested multi-specs are few. They are special
;; cases with hand-written specs.
;;
;;
;; `Defmasrtype` creates both (1) the specs for
;; particular heads like `Variable` and `Assignment`,
;; and (2) a function, `->asdl-type`, that extracts
;; the ASDL type from any instance hash-map. We
;; present the extraction code first ("define" before
;; "use"):
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
  "Get rid of repetition in expressions like

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
       ;; defmethod symbol ::Program [_]
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
;; Undone Work-in-Progress
;;

;;
;; The function `->asdl-type` relies on multimethods
;; for nested multi-specs. The multimethods dispatch
;; on the _head_ keys of each multi-spec, keys like
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

;;
;; Fill out implementations later.
;;

;; ----------------------------------------------------------------
;; ## UNIT
;;
;;
;; #+begin_src clojure

(defmulti  unit->asdl-type ::unit-head)
(term->asdl-type unit)
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  TranslationUnit unit
  (SymbolTable  nodes))
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
  (SymbolTable  prognym  dependencies  body))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Module symbol
  (SymbolTable
   modulenym  dependencies  loaded-from-mod
   intrinsic))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Function symbol
  (SymbolTable ;; not a symtab-id!
   function-name  function-signature  dependencies
   param*         body                return-var?
   access         deterministic       side-effect-free
   ))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  GenericProcedure symbol
  (symtab-id  function-name  symbol-ref*
   access))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ExternalSymbol symbol
  (symtab-id
   nym        extern-symref
   modulenym  scope-nyms     orig-nym
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
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Assignment stmt
  ;; types of the attributes:
  (lvalue  rvalue  overloaded))
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
  (test-expr  body  orelse))
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
  (symbol-ref  orig-symref  call-args  dt?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Block stmt
  (label symbol-ref))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  WhileLoop stmt
  (escape-target  test-expr  body))
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
  (list-expr  list-element))
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
  (test-expr body-expr orelse-expr ttype value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  NamedExpr expr
  (target value ttype))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  FunctionCall expr
  (symbol-ref  orig-symref  call-args
               return-type  value?    dt?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  IntrinsicFunction expr
  (intrinsic-ident  expr*        overload-id
   return-type      value?))
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
  (integer-expr  Integer  integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  IntegerCompare expr
  (integer-left  integer-cmpop   integer-right
                 Logical         logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  IntegerBinOp expr
  (integer-left  integer-binop   integer-right
                 Integer         integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  RealConstant expr
  (float  Real))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  RealUnaryMinus expr
  (real-expr, Real, real-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  RealCompare expr
  (real-left     real-cmpop    real-right
                 Logical       logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  RealBinOp expr
  (real-left  real-binop  real-right
   Real       real-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexConstant expr
  (real-part  imaginary-part  Complex))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexUnaryMinus expr
  (complex-expr  Complex  complex-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexCompare expr
  (complex-left  complex-cmpop complex-right
   Logical       logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexBinOp expr
  (complex-left  complex-binop  complex-right
   Complex       complex-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  LogicalConstant expr
  (bool  Logical))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  LogicalNot expr
  (logical-expr  Logical  logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  LogicalCompare expr
  (logical-left  logical-cmpop  logical-right
   Logical       logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  LogicalBinOp expr
  (logical-left  logicalbinop  logical-right
   Logical       logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ListConstant expr
  (expr*  ttype))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ListLen expr
  (list-expr  Integer  integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  TupleConstant expr
  (elements  ttype))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  TupleLen expr
  (tuple-expr  Integer  integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  TupleCompare expr
  (tuple-left  any-cmpop  tuple-right
   Logical     logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype ;; 1 of 9 String... terms
  StringConstant expr
  (string  Character))
;; #+end_src

;; #+begin_src clojure

(defmasrtype ;; 2 of 9 String... terms
  StringConcat expr
  (string-left  string-right
   Character    string-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype ;; 3 of 9 String... terms
  StringRepeat expr
  (string-expr  integer-expr
   Character    string-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype ;; 4 of 9 String... terms
  StringLen expr
  (string-expr Integer integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype ;; 5 of 9 String... terms
  StringItem expr
  (string-expr index?
   character-or-integer-ttype
   character-or-integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype ;; 6 of 9 String... terms
  StringSection expr
  (string-expr
   index-start?  index-end?  index-step?
   Character     string-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype ;; 7 of 9 String... terms
  StringCompare expr
  (string-left  string-cmpop  string-right
   Logical      logical-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype ;; 8 of 9 String... terms
  StringOrd expr
  (string-expr  Integer  integer-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype ;; 9 of 9 String... terms
  StringChr expr
  (integer-scalar-or-expr
   Character  string-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Var expr
  (symtab-id  varnym))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ArrayConstant expr
  (expr*  ttype  array-storage))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ArrayItem expr
  (array-expr     array-index*  ttype
   array-storage  expr?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ArrayReshape expr
  (array-expr  array-shape  ttype
   array-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Cast expr
  (arg  cast-kind  ttype  value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ListItem expr
  (list-expr  index  ttype  value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  TupleItem expr
  (tuple-expr  index  ttype  value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexRe expr
  (complex-expr  Real  real-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  ComplexIm expr
  (complex-expr  Real  real-value?))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  GetPointer expr
  (expr  Pointer  pointer-value?))
;; #+end_src

;; ----------------------------------------------------------------
;; ## TTYPE
;;
;;
;; #+begin_src clojure

(defmulti  ttype->asdl-type ::ttype-head)
(term->asdl-type ttype)
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Complex ttype
  ;; types of the attributes:
  (complex-kind  dimension*))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Integer ttype
  (integer-kind  dimension*))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Logical ttype
  (logical-kind  dimension*))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Real ttype
  (real-kind  dimension*))
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
  Pointer ttype
  (ttype))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  Character ttype
  (character-kind  len        disposition
                   len-expr?  dimension*))
;; #+end_src

;; #+begin_src clojure

(defmasrtype
  FunctionType ttype
  (param-type*
   return-var-type?   abi
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
;; requires moving colons from the backs of keywords
;; to the front. That is necessary because colons at
;; the back fail the Clojure reader. We have a `sed`
;; script for moving colons: `fix-show-asr.sed`. The
;; script also replaces illegal characters like `@`
;; and `~` and converts ASDL's `.false.` to `false`
;; and `.true.` to `true`.
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
;; unwanted nested call syntax into vectors. `Eval`,
;; in the namespace `masr.specs`, applies all sugar
;; functions to an expression. Call `to-full-form`
;; to do both. The `legacy` macro simply quotes a
;; whole sugared expression before feeding it to
;; `to-full-form`.
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

(defn to-full-form
  [sexp]
  (do (in-ns 'masr.specs)
      (->> sexp
           rewrite-for-legacy
           eval)))
;; #+end_src

;; #+begin_src clojure

(defmacro legacy
  "DANGER: Call 'eval'."
  [it]
  `(to-full-form '~it))
;; #+end_src


;;
;; Full-forms must be checked against a spec. The
;; following function does a rudimentary
;; uninformative check, returning a given error code
;; in case of any error. Its parameter order helps
;; reduce indentation at its call sites.
;;
;; #+begin_src clojure

(defn check-full-form
  [spec errcode form]
  (if (not (s/valid? spec form))
    errcode, #_else spec))
;; #+end_src


;;
;;
;; # IMPLEMENTATIONS
;;
;;


;;
;; The remaining sections of this document describe
;; detailed implementations of the sugar functions
;; for the `defmasrtype`s.
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
;; function call or subroutine call. We spec the
;; extra nesting as a collection of length one.
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
             :min-count 1 ;; Issue 32
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
;; defined. See the first example in in
;; `SubroutineCall.`
;;

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
;; The ASDL is imprecise. The real spec, realized
;; only in secret C++ code, is that we have either
;; both `start` and `length` or we have nothing.
;; MASR makes exposes this secret explicitly.
;;

;;
;; ## Dimension-Content
;;
;;

;;
;; The next spec says that a `::dimension-content` is
;; a collection of `::nat` with either two or zero
;; elements.
;;
;;
;; #+begin_src clojure

(def MIN-DIMENSION-COUNT 0)
(def MAX-DIMENSION-COUNT 2)

(s/def ::dimension-content
  (s/and
   (s/coll-of (s/or :empty    empty?
                    :i-scalar ::integer-scalar)
              :min-count MIN-DIMENSION-COUNT,
              :max-count MAX-DIMENSION-COUNT,
              :into [])
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

;; #+begin_src clojure

(defn IntegerConstant [stt end] :forward-reference)
(defn Integer         [       ] :forward-reference)

(defn dimension [candidate-contents]
  (if (or (not (coll? candidate-contents))
          (set? candidate-contents)
          (map? candidate-contents))
    ::invalid-dimension ;; return this,
    ;; else
    {::term ::dimension,
     ::dimension-content
     (if (every? #(or (and (coll? %) (empty? %))
                      (s/valid? ::integer-scalar %))
                 candidate-contents)
         candidate-contents
         (->> candidate-contents
              (map #(IntegerConstant % (Integer)))
              ;; `vec` for idempotency
              vec))}))
;; #+end_src


;;
;;
;; # DIMENSION*
;;
;;


;; `Dimension*` is not an `asr-term`. It's a collection
;; of `dimension`s, each of which is an `asr-term`.


;;
;; Convert lists to vectors on-the-fly.
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
          (map? candidate-contents)
          (not (every? coll? candidate-contents)))
    ::invalid-dimension*
    ;; else
    (->> candidate-contents
         (map vec) ;; Like legacy -- prevent bogus fn call;
         (map dimension)
         vec))) ;; again -- dimension* are double-nested.
;; #+end_src


;;
;;
;; # SYMTAB-ID
;;
;;


;;
;; In ASDL, `symbol_table` sometimes means a
;; `SymbolTable` and sometimes means an integer id
;; of a `SymbolTable` that is specified elsewhere.
;; MASR does better. MASR `->asdl-type` projects
;; both of these types, `SymbolTable` and
;; `symtab-id`, back into ASDL `symbol_table`, with
;; its secret proviso. MASR exposes the secret,
;; whilst ASDL embraces the secret.
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
;; nested multi-specs. Write it out fully by hand. Its
;; hash must relate keywords and valid asr-terms.
;;
;;
;; #+begin_src clojure

(s/def ::hash-map map?)

(defmethod term ::SymbolTable [_]
  (s/and
   #(every? (fn [[k v]]
              (and (keyword? k)
                   (s/valid? ::asr-term v)))
            (::hash-map %))
   (s/keys :req [::term
                 ::symtab-id
                 ::hash-map])))

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
;; Many ASDL types are like enums: a set of
;; alternative symbols, without parentheses and
;; without parameters _qua_ arguments. Example: ASDL
;; `access` has two possibilities: `Public` and
;; `Private`. MASR automates all enum-likes via
;; one macro, `enum-like`.
;;

;;
;; ## Helpers for Enum-Like
;;
;;
;; #+begin_src clojure

(defmacro legacicate
  "ASDL Back-Channel: create legacy function-calls
  for each constant, such as Local for (intent 'Local).
  This works only because all heads are unique -- no
  collisions in ASR."
  [term,       ;; e.g. intent
   a-set-syms] ;; e.g. #{"Local", "In", "Out", ,,,}
               ;; or a variable referring to a set,
               ;; ... so must evaluate it
  (let [a-set (eval a-set-syms)
        cmds  (for [e a-set]
                (list 'def (symbol e) `(~term ~e)))]
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
  (let [ns "masr.specs"                      ;; -- Examples --
        trm (keyword ns "term")              ;; ::term
        art (keyword ns "asr-term")          ;; ::asr-term
        tkw (keyword ns (str term))          ;; ::intent
        tke (keyword ns (str term "-enum"))] ;; ::intent-enum
    `(do
       ;; e.g. ::intent-enum #{'Local, 'In, ...}
       (s/def ~tke           ~heads)
       ;; e.g.    intent ::intent [_]
       (defmethod term   ~tkw     [_#]
         (s/keys :req [~trm ~tke]))
       (s/def ~tkw ;; e.g. ::intent, the entity-key spec
         (s/and ~art ;; e.g. ::asr-term, i.e., the multi-spec
                ;; e.g. the predicate #(= ::intent (::term %))
                (term-selector-spec ~tkw)))
       ;; e.g. defn intent [it], the sugar function
       (defn ~term [it#]
         ;; e.g. ::term ::intent
         {~trm ~tkw
          ;; e.g. ::intent-enum it
          ~tke it#})
       ;; e.g. (def Local {::term ::intent "Local") ...
       (legacicate ~term ~heads)
       )))
;; #+end_src

;;
;; ## Most Enum-Likes
;;
;;

;; #+begin_src clojure

(def logical-binops #{"And"  "Or"  "Xor"  "NEqv"  "Eqv"})
(def real-binops    #{"RAdd" "RSub" "RMul" "RDiv" "RPow"})
(def complex-binops #{"CAdd" "CSub" "CMul" "CDiv" "CPow"})
(def integer-binops #{"Add" "Sub" "Mul" "Div" "Pow"
                      "BitAnd" "BitOr" "BitXor"
                      "BitLShift" "BitRShift"})
;; #+end_src

;; #+begin_src clojure

(def logical-cmpops #{"LEq" "LNotEq"
                      ;; some weird ones: see Issue #38
                      "LLt" "LLtE" "LGt" "LGtE"})
(def real-cmpops    #{"REq" "RNotEq" "RLt" "RLtE" "RGt" "RGtE"})
(def complex-cmpops #{"CEq" "CNotEq"})
(def integer-cmpops #{"Eq" "NotEq" "Lt" "LtE" "Gt" "GtE"})
(def string-cmpops  #{"SEq" "SNotEq" "SLt" "SLtE" "SGt" "SGtE"})
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

(s/def ::any-cmpop
  (s/or :logical-cmpop ::logical-cmpop
        :real-compop   ::real-cmpop
        :complex-cmpop ::complex-cmpop
        :integer-cmpop ::integer-cmpop
        :string-cmpop  ::string-cmpop))
;; #+end_src

;; #+begin_src clojure

(enum-like intent        #{"Local" "In" "Out" "InOut" "ReturnVar"
                           "Unspecified"})
(enum-like storage-type  #{"Default", "Save", "Parameter", "Allocatable"})
(enum-like access        #{"Public" "Private"})
(enum-like presence      #{"Required" "Optional"})
(enum-like deftype       #{"Implementation", "Interface"})
(enum-like arraybound    #{"LBound" "UBound"})
(enum-like arraystorage  #{"RowMajor" "ColMajor"})
(enum-like cast-kind     #{"RealToInteger"       "IntegerToReal"
                           "LogicalToReal"       "RealToReal"
                           "IntegerToInteger"    "RealToComplex"
                           "IntegerToComplex"    "IntegerToLogical"
                           "RealToLogical"       "CharacterToLogical"
                           "CharacterToInteger"  "CharacterToList"
                           "ComplexToLogical"    "ComplexToComplex"
                           "ComplexToReal"       "ComplexToInteger"
                           "LogicalToInteger"    "RealToCharacter"
                           "IntegerToCharacter"  "LogicalToCharacter"})
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
  #{"LFortranModule", "GFortranModule",
    "BindC", "Interactive", "Intrinsic"})

(def internal-abis #{"Source"})

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
     #(iff (= "Source" (::abi-enum %)) (not (::abi-external %)))
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
        :external (not (= the-enum "Source"))))
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

(def LFortranModule (abi "LFortranModule" :external true))
(def GFortranModule (abi "GFortranModule" :external true))
(def BindC          (abi "BindC"          :external true))
(def Interactive    (abi "Interactive"    :external true))
(def Intrinsic      (abi "Intrinsic"      :external true))
(def Source         (abi "Source"         :external false))
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

(s/def ::len         ::int)   ;; Issues #36
(s/def ::disposition #{"compile-time-length"   ;; >= 0
                       "inferred-at-run-time"  ;; = -1
                       "allocatable"           ;; = -2
                       "run-time-expression"}) ;; = -3
(s/def ::len-expr?   ::expr?) ;; TODO: check that it's >= 0
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
          (~lcp {:kind kindx#
                 :dimension* dimsx#}))
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
     (cond (>= len  0) "compile-time-length"
           (=  len -1) "inferred-at-run-time"
           (=  len -2) "allocatable"
           (=  len -3) "run-time-expression")
     ::len-expr?      len-expr?
     ::dimension*     (dimension* dims)
     }})
  ([kind, len, dims]
   "trinary"
   (Character kind len () dims))
  ([kind, len]
   "binary"
   (Character kind len () []))
  ([len]
   "unary"
   (Character 1 len ( [])))
  ([]
   "nullary"
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
;; ## Set
;;
;; #+begin_src clojure

(defn Pointer [ttype]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::Pointer
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

(s/def ::symbolic-value ::expr?)
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
;; ### Scalar Detection
;;
;;
;; #+begin_src clojure

(defmacro ttyped-scalar-spec
  [it]
  (let [ns  "masr.specs"
        cap (str/capitalize (str it)) ;; e.g. "Integer"
        tth (keyword ns cap)          ;; e.g. ::Integer
        lwr (str/lower-case (str it)) ;; e.g. "integer"
        scl (keyword
             ns (str lwr "-scalar"))  ;; e.g. ::integer-scalar
        xpr (keyword
             ns (str lwr "-expr"))    ;; e.g. ::integer-expr
        ]
    `(s/def ~scl
       (s/and (fn [x#] (s/valid? ~xpr x#))
              (fn [x#] (let [dims# (-> x# ::asr-expr-head
                                       ~tth
                                       ::asr-ttype-head
                                       ::dimension*)]
                         ;; nil is empty? !!!
                         (and (not (nil? dims#))
                              (empty? dims#))))))))
;; #+end_src

;; #+begin_src clojure

(ttyped-scalar-spec Integer)
(ttyped-scalar-spec Logical)
(ttyped-scalar-spec Real)
(ttyped-scalar-spec Complex)
;; special case for String because its ttype is ::Character
(s/def ::string-scalar
  (s/and #(s/valid? ::string-expr %)
         #(let [dims (-> % ::asr-expr-head
                         ::Character
                         ::asr-ttype-head
                         ::dimension*)]
            (and (not (nil? dims))
                 (empty? dims)))))
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
  (s/or :array-item         ::ArrayItem    ;; TODO check return type!
        :tuple-item         ::TupleItem    ;; TODO check return type!
        :list-item          ::ListItem     ;; TODO check return type!
        :cast               ::Cast         ;; TODO check return type!
        :if-expr            ::IfExp        ;; TODO check return type!
        :named-expr         ::NamedExpr    ;; TODO check return type!
        :function-call      ::FunctionCall ;; TODO check return type!
        :var                ::Var          ;; TODO check return type!
        :intrinsic-function ::IntrinsicFunction ;; TODO return type!
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
        :string-compare       ::StringCompare
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
        :string-len           ::StringLen
        :string-item          ::StringItem ;; Issues 52, 53, 54
        :string-ord           ::StringOrd
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
        :complex-im           ::ComplexIm
        :complex-re           ::ComplexRe
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
;; #+end_src

;; ----------------------------------------------------------------
;; ### Array Types
;;
;;
;; #+begin_src clojure

(s/def ::array-expr
  (s/or :var                    ::Var
        :array-constant         ::ArrayConstant
        :array-reshape          ::ArrayReshape
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
        :string-concat          ::StringConcat
        :string-repeat          ::StringRepeat
        :string-item            ::StringItem
        :string-section         ::StringSection
        :string-chr             ::StringChr
        :unchecked              ::unchecked-element-expr))
;; #+end_src

;; #+begin_src clojure

(s/def ::string-left            ::string-expr)
(s/def ::string-right           ::string-expr)
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

(s/def ::body-expr   ::expr)
(s/def ::orelse-expr ::expr)

(defn IfExp [test-expr, body, orelse, ttype, value?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head    ::IfExp
    ::test-expr    test-expr
    ::body-expr    body
    ::orelse-expr  orelse
    ::ttype        ttype
    ::value?       value?}})
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
  ([stid, ident, orig-symref,
    args, rettype, value?, dt?]
   "septenary"
   (let [i_ident (str ident)]
    `(FunctionCall-- (symbol-ref ~i_ident ~stid)
                     ~orig-symref
                     ~args
                     ~rettype
                     ~value?
                     ~dt?)))
  ([stid, ident, ostid, odent,
    args, rettype, value?, dt?]
   "octonary"
   (let [i_ident (str ident)
         i_odent (str odent)]
    `(FunctionCall-- (symbol-ref ~i_ident, ~stid)
                     (symbol-ref ~i_odent, ~ostid)
                     ~args
                     ~rettype
                     ~value?
                     ~dt?))))
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
    ::intrinsic-ident     (str intrinsic-ident)
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
  "Stringulate the intrinsic identifier."
  [intrinsic-ident    expr*    overload-id
   return-type        value?]
  (let [i_ident (str intrinsic-ident)]
    `(IntrinsicFunction--
      ~i_ident,     ~expr*, ~overload-id,
      ~return-type, ~value?)))
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
;; ## STRING CONCAT
;;
;;

;;
;; ### Heavy Sugar
;;
;; #+begin_src clojure

(defn StringConcat [l- r- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head   ::StringConcat
    ::string-left    l-
    ::string-right   r-
    ::Character      tt-
    ::string-value?  val?-}})
;; #+end_src

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
     ::string-value?  compiler-computed?}})
  ([string-expr integer-expr]
   "binary"
   (StringRepeat string-expr (Character) ())))
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
;; See Issues #51, #52.
;;
;; #+begin_src clojure

(s/def ::character-or-integer-ttype
  (s/or :character      ::Character
        :integer        ::Integer))

(s/def ::character-or-integer-value?
  (s/or :string-value?  ::string-value?
        :integer-value? ::integer-value?))

(defn StringItem
  [string-expr
   index?
   character-or-integer-ttype
   character-or-integer-value?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head    ::StringItem
    ::string-expr  string-expr
    ::index?       index?
    ::character-or-integer-ttype
    character-or-integer-ttype
    ::character-or-integer-value?
    character-or-integer-value?
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
;; ## STRING CHR
;;
;;

;;
;; ### Heavy Sugar
;;
;; Issue 53: Should integer-expr be tested for scalar property?
;;           Such might require run-time testing. Test statically
;;           when possible.
;;
;; #+begin_src clojure

(s/def ::integer-scalar-or-expr
  (s/or :integer-scalar ::integer-scalar
        :integer-expr   ::integer-expr))

(defn StringChr
  ([integer-scalar-or-expr, char-ttype, string-val?]
   "trinary ... Return ascii value of the integer
   form of the character in the string."
   {::term ::expr,
    ::asr-expr-head
    {::expr-head ::StringChr
     ::integer-scalar-or-expr  integer-scalar-or-expr
     ::Character               char-ttype
     ::string-value?           string-val?}})
  ([str-expr, string-val?]
   (StringChr str-expr, (Character) string-val?)))
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
  ([re-float, im-float c-ttype]
   "trinary"
   {::term ::expr,
    ::asr-expr-head
    {::expr-head ::ComplexConstant
     ::real-part      re-float
     ::imaginary-part im-float
     ::Complex        c-ttype}})
  ([re-float, im-float]
   "binary"
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
    ::varnym     (str ident)
    }})
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro Var [stid, unquoted-ident]
  (let [i_ident (str unquoted-ident)]
   `(Var-- ~stid ~i_ident)))
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
    ;; `vec` for idempotency
    ::array-index*    (vec (map array-index array-index*))
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
;; ## GET POINTER
;;
;; #+begin_src clojure

(s/def ::pointer-value? ::expr?) ;; TODO: until it's better

(defn GetPointer [expr, ptr, pv?]
{::term ::expr,
 ::asr-expr-head
 {::expr-head       ::GetPointer
  ::expr            expr
  ::Pointer         ptr
  ::pointer-value?  pv?
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

(s/def ::lvalue       (s/or :var         ::Var
                            :array-item  ::ArrayItem
                            :tuple-const ::TupleConstant
                            :list-item   ::ListItem
                            ))
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
  (let [i_ident (str identifier)]
   `(GoTo-- ~goto-target ~i_ident)))
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
    ::escape-target  escape-target
    ::test-expr      test-expr
    ::body           body}})
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
(SubroutineCall  7 test_fn1  ()  []  ())

#_
(SubroutineCall  7 test_fn1  ()  ((Var 42 i))  ())
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
  (let [i_ident (str ident)]
    `(SubroutineCall--
      (symbol-ref ~i_ident ~stid)
      ~orig-symref
      ;; Took a while to find this ...
      ;; `(map vec ~args)` does not work!
      ;; outer `vec` for idempotency
      (if (empty? ~args) []
        (vec ~args))
      ~dt?))) ;; #+end_src
;; #+end_src

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

(s/def ::label ::int)  ;; TODO: Issue 49: what do negative ones mean
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
  (let [i_ident (str ident)]
   `(BlockCall--
     ~label
     (symbol-ref ~i_ident ~stid))))
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
  "Stringulate the nym and the dependencies
  and vectorate the dependencies."
  [stab, nym, deps, body-]
  (let [i_nym (str nym)
        i_deps (vec (map str deps))]
   `(Program--  ~stab,  ~i_nym,  ~i_deps,  ~body-)))
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
  "Stringulate the mondnym and the deps
  and vectorate the deps."
  [symtab, modnym, deps, loaded, intrinsic-]
  (let [i_modnym (str modnym)
        i_strss (vec (map str deps))]
    `(Module--
      ~symtab  ~i_modnym  ~i_strss
      ~loaded  ~intrinsic-)))
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
  "Stringuluate the fnnym and the deps."
  [symtab,
   fnnym,   fnsig,  deps,
   param*-, body-,  retvar?,
   access-, determ, sefree]
  (let [i_fnnym (str fnnym)
        i_deps  (vec (map str deps))]
   `(Function-- ~symtab,
                ~i_fnnym,  ~fnsig,  ~i_deps
                ~param*-,
                ~body-, ;; <~~~ iterate over the statements inside
                ~retvar?,
                ~access-, ~determ, ~sefree)))
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
        fpairs (vec (for [[fid fnym] pairs]
                      (symbol-ref (str fnym) fid)))
        i_fnym (str fnym)]
    `(GenericProcedure--
      ~stid
      ~i_fnym
      ~fpairs
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
 5 _lpython_main_program
 7 _lpython_main_program   ;; either () or a naked pair
 _global_symbols    []
 _lpython_main_program    Public)
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
    modnym, scope-nyms, orig-nym, access]
   "octonary"
   (let [i_nym    (str nym)
         i_oid    (str orig-symref-ident)
         i_modnym (str modnym)
         i_onym   (str orig-nym)
         i_snyms  (vec (map str scope-nyms))]
    `(ExternalSymbol--
      ~stid, ~i_nym,
      (symbol-ref ~i_oid, ~orig-symref-stid),
      ~i_modnym,  ~i_snyms,  ~i_onym,  ~access)))
  ([stid, nym, empty-symref,
    modnym, scope-nyms, orig-nym, access]
   "septenary"
   (let [i_nym (str nym)
         i_modnym (str modnym)
         i_onym   (str orig-nym)
         i_snyms  (vec (map str scope-nyms))]
     `(ExternalSymbol--
            ~stid, ~i_nym, ~empty-symref,
            ~i_modnym,  ~i_snyms,  ~i_onym,  ~access))))
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
           intent           (intent "Local")

           symbolic-value   ()
           value?           ()
           storage-type     (storage-type "Default")

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
  (let [i_varnym (str varnym-)]
   {::term              ::symbol,
    ::asr-symbol-head
    {::symbol-head      ::Variable,

     ::symtab-id        symtab-id-,
     ::varnym           i_varnym,
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
     }}))
;; #+end_src

;;
;; ### Legacy Sugar
;;
;; #+begin_src clojure

(defmacro Variable
  "Honor legacy parameter order of
  lpython/src/libasr/ASR.asdl as of 25 April 2023.
  Stringulate the varnym and dependencies,
  vectorate the dependencies; pass along
  all other params."
  [symtab-id-,     varnym-,          dependencies-,
   intent-,        symbolic-value-,  value?-,
   storage-type-,  ttype-,           abi-,
   access-,        presence-,        value-attr-]
  (let [i_varnym (str varnym-)
        i_deps (vec dependencies-)]
   `(Variable-- ;; heavy sugar
     ~symtab-id-
     ~i_varnym
     ~ttype- ;; moved up from between storage type and abi
     nil     ;; legacy doesn't have type-declaration
     ~i_deps   ~intent-   ~symbolic-value-
     ~value?-  ~storage-type-
     ;; ttype goes here in legacy
     ~abi-     ~access-   ~presence-
     ~value-attr-)))
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
  (let [i_nym (str blocknym)]
   `(Block-- ~SymbolTable ~i_nym ~body)))
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
  (let [i_modnym (str modnym)]
   `(IntrinsicModule-- ~i_modnym)))
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
  ;; `vec` for idempotency
  (let [node-cnd (vec (map node node-preimages))]
    {::term          ::unit
     ::asr-unit-head
     {::unit-head    ::TranslationUnit
      ::SymbolTable  stab
      ::nodes        node-cnd}}))
;; #+end_src

;;
;;
;; REGISTRY
;;
;;

;;
;; Here is a list of all the 301 specs defined in
;; this file as of 12 June 2023, edited by hand to
;; shorten them and to eliminate built-ins:
;;

;; #+begin_src clojure

#_(s/registry)
;; => {
;;     ::ArrayConstant  #o[s$and_r_2177 0x1dffa309],
;;     ::ArrayItem  #o[s$and_r_2177 0x34a828c0],
;;     ::ArrayReshape  #o[s$and_r_2177 0xa4ca753],
;;     ::Assert  #o[s$and_r_2177 0x5f7bd50c],
;;     ::Assignment  #o[s$and_r_2177 0x460fc467],
;;     ::Block  #o[s$and_r_2177 0x4edfa7fc],
;;     ::BlockCall  #o[s$and_r_2177 0x1b1b9ed7],
;;     ::Cast  #o[s$and_r_2177 0x4c3f0ece],
;;     ::Character  #o[s$and_r_2177 0xdf12bdd],
;;     ::Complex  #o[s$and_r_2177 0x4717b339],
;;     ::ComplexBinOp  #o[s$and_r_2177 0x1a1a6722],
;;     ::ComplexCompare  #o[s$and_r_2177 0xd1f083a],
;;     ::ComplexConstant  #o[s$and_r_2177 0x5e2062a3],
;;     ::ComplexIm  #o[s$and_r_2177 0x4f53036d],
;;     ::ComplexRe  #o[s$and_r_2177 0xb040170],
;;     ::ComplexUnaryMinus  #o[s$and_r_2177 0x1778f4ad],
;;     ::DoLoop  #o[s$and_r_2177 0x30167fc3],
;;     ::ExplicitDeallocate  #o[s$and_r_2177 0x44c8be43],
;;     ::ExternalSymbol  #o[s$and_r_2177 0x3a258c0e],
;;     ::Function  #o[s$and_r_2177 0x2472f561],
;;     ::FunctionCall  #o[s$and_r_2177 0x442960a9],
;;     ::FunctionType  #o[s$and_r_2177 0x2c0fab79],
;;     ::GenericProcedure  #o[s$and_r_2177 0x156ba6e3],
;;     ::GetPointer  #o[s$and_r_2177 0x35cdbbbd],
;;     ::GoTo  #o[s$and_r_2177 0x5e8b3faf],
;;     ::If  #o[s$and_r_2177 0x47fa80f2],
;;     ::IfExp  #o[s$and_r_2177 0x1efc1d81],
;;     ::Integer  #o[s$and_r_2177 0x16a2e13b],
;;     ::IntegerBinOp  #o[s$and_r_2177 0x1ce9d2ab],
;;     ::IntegerBitNot  #o[s$and_r_2177 0x5d349ba6],
;;     ::IntegerCompare  #o[s$and_r_2177 0x4ee88671],
;;     ::IntegerConstant  #o[s$and_r_2177 0xd4dbba8],
;;     ::IntegerConstant?  #o[s$or_r_2112 0x4478bc9a],
;;     ::IntegerUnaryMinus  #o[s$and_r_2177 0x1054eb07],
;;     ::IntrinsicFunction  #o[s$and_r_2177 0xc067abe],
;;     ::IntrinsicModule  #o[s$and_r_2177 0x4cb16a35],
;;     ::List  #o[s$and_r_2177 0x20f52638],
;;     ::ListAppend  #o[s$and_r_2177 0x7744954e],
;;     ::ListConstant  #o[s$and_r_2177 0x48744e38],
;;     ::ListItem  #o[s$and_r_2177 0x13e8579a],
;;     ::ListLen  #o[s$and_r_2177 0x64d6e50],
;;     ::Logical  #o[s$and_r_2177 0x225aabb6],
;;     ::LogicalBinOp  #o[s$and_r_2177 0x28ca756e],
;;     ::LogicalCompare  #o[s$and_r_2177 0x1a14b908],
;;     ::LogicalConstant  #o[s$and_r_2177 0x5cda5ff3],
;;     ::LogicalNot  #o[s$and_r_2177 0x21c5fcac],
;;     ::NamedExpr  #o[s$and_r_2177 0x3f8aa38e],
;;     ::Pointer  #o[s$and_r_2177 0x5b37b325],
;;     ::Print  #o[s$and_r_2177 0x4affd722],
;;     ::Program  #o[s$and_r_2177 0x44d004a8],
;;     ::Real  #o[s$and_r_2177 0x7502e0af],
;;     ::RealBinOp  #o[s$and_r_2177 0x6901d91],
;;     ::RealCompare  #o[s$and_r_2177 0x76cb26bf],
;;     ::RealConstant  #o[s$and_r_2177 0x7c768e16],
;;     ::RealUnaryMinus  #o[s$and_r_2177 0x10a6ef4c],
;;     ::Return  #o[s$and_r_2177 0x4750cf0],
;;     ::Set  #o[s$and_r_2177 0x4e57d61c],
;;     ::StringChr  #o[s$and_r_2177 0x61812d7f],
;;     ::StringCompare  #o[s$and_r_2177 0x2fbc13de],
;;     ::StringConcat  #o[s$and_r_2177 0x5d835614],
;;     ::StringConstant  #o[s$and_r_2177 0x23f65dc5],
;;     ::StringItem  #o[s$and_r_2177 0x1950ce2a],
;;     ::StringLen  #o[s$and_r_2177 0x10687f84],
;;     ::StringOrd  #o[s$and_r_2177 0x38589b3c],
;;     ::StringRepeat  #o[s$and_r_2177 0x669f60ee],
;;     ::StringSection  #o[s$and_r_2177 0x57d216aa],
;;     ::SubroutineCall  #o[s$and_r_2177 0x41707fea],
;;     ::SymbolTable  #o[s$and_r_2177 0x4f00a848],
;;     ::TranslationUnit  #o[s$and_r_2177 0x70de2576],
;;     ::Tuple  #o[s$and_r_2177 0x1f28a18d],
;;     ::TupleCompare  #o[s$and_r_2177 0x5b7c122f],
;;     ::TupleConstant  #o[s$and_r_2177 0x460faf61],
;;     ::TupleItem  #o[s$and_r_2177 0x1168b9ee],
;;     ::TupleLen  #o[s$and_r_2177 0x1962c92d],
;;     ::Var  #o[s$and_r_2177 0x460571de],
;;     ::Variable  #o[s$and_r_2177 0x5597779f],
;;     ::WhileLoop  #o[s$and_r_2177 0x1a3faf6b],
;;     ::abi  #o[s$and_r_2177 0x699b1f12],
;;     ::abi-enum  #o[s$r_2053 0x1c40bf19],
;;     ::abi-external ::bool,
;;     ::access  #o[s$and_r_2177 0x82faaac],
;;     ::access-enum  #o[s$r_2053 0x15d387be],
;;     ::any-binop  #o[s$r_2053 0x3dda8a81],
;;     ::any-cmpop  #o[s$or_r_2112 0x4d5fdc08],
;;     ::arg ::expr,
;;     ::array-expr  #o[s$or_r_2112 0x26afb40b],
;;     ::array-index  #o[s$map_r_1991 0x685e5961],
;;     ::array-index*  #o[s$every_impl$reify__2248 0x7df1881a],
;;     ::array-index-end?
;;     ::array-index-increment?
;;     ::array-index-start?
;;     ::array-shape ::expr,
;;     ::array-value? ::expr?,
;;     ::arraybound  #o[s$and_r_2177 0x59fae241],
;;     ::arraybound-enum  #o[s$r_2053 0x500ac6aa],
;;     ::arraystorage  #o[s$and_r_2177 0x59fe72c0],
;;     ::arraystorage-enum  #o[s$r_2053 0x28b92d9e],
;;     ::asr-expr-head  #o[s$multi_r_2068 0x1f970ace],
;;     ::asr-stmt-head  #o[s$multi_r_2068 0x29a93b05],
;;     ::asr-symbol-head  #o[s$multi_r_2068 0x5ebadcc8],
;;     ::asr-term  #o[s$multi_r_2068 0x1cb0d70c],
;;     ::asr-ttype-head  #o[s$multi_r_2068 0xa03ac9d],
;;     ::asr-unit-head  #o[s$multi_r_2068 0x14f74fbe],
;;     ::bignat  #o[s$and_r_2177 0x40d13ff6],
;;     ::bindc-name  #o[s$nilable_impl$reify__2550 0x2946fff2],
;;     ::blocknym ::identifier,
;;     ::body ::stmt*,
;;     ::body-expr ::expr,
;;     ::bool  #o[s$r_2053 0x438b304c],
;;     ::call-arg  #o[s$every_impl$reify__2248 0x7096232b],
;;     ::call-arg-or-args  #o[s$or_r_2112 0x3971e124],
;;     ::call-args  #o[s$every_impl$reify__2248 0x3de0f6c6],
;;     ::cast-kind  #o[s$and_r_2177 0x194577a8],
;;     ::cast-kind-enum  #o[s$r_2053 0x579c44a0],
;;     ::character-kind  #o[s$r_2053 0x54784d4f],
;;     ::character-or-integer-ttype  #o[s$or_r_2112 0x2f88813c],
;;     ::character-or-integer-value?  #o[s$or_r_2112 0x6821c2e2],
;;     ::complex-binop  #o[s$and_r_2177 0x5b17c798],
;;     ::complex-binop-enum  #o[s$r_2053 0x61c6bebe],
;;     ::complex-cmpop  #o[s$and_r_2177 0x49a80d97],
;;     ::complex-cmpop-enum  #o[s$r_2053 0x59e1c0c3],
;;     ::complex-expr,
;;     ::complex-expr?  #o[s$or_r_2112 0x730798f3],
;;     ::complex-expr?,
;;     ::complex-kind  #o[s$r_2053 0x29d5f40e],
;;     ::complex-left ::complex-expr,
;;     ::complex-right
;;     ::complex-scalar  #o[s$and_r_2177 0x62537e05],
;;     ::complex-value?
;;     ::deftype  #o[s$and_r_2177 0x653f3655],
;;     ::deftype-enum  #o[s$r_2053 0x7b8256],
;;     ::dependencies  ::identifier-set,
;;     ::deterministic ::bool,
;;     ::dimension  #o[s$and_r_2177 0x77c2873c],
;;     ::dimension*  #o[s$every_impl$reify__2248 0x5812eed4],
;;     ::dimension-content  #o[s$and_r_2177 0x2b503d27],
;;     ::disposition  #o[s$r_2053 0x43c0b861],
;;     ::do-loop-head  #o[s$map_r_1991 0x1ac5219],
;;     ::dt? ::expr?}
;;     ::elemental ::bool,
;;     ::elements ::expr*,
;;     ::end? ::expr?,
;;     ::escape-target  #o[s$r_2053 0x9d143b3],
;;     ::expr  #o[s$and_r_2177 0x7d2fe606],
;;     ::expr*  #o[s$every_impl$reify__2248 0x64e08208],
;;     ::expr?  #o[s$or_r_2112 0x5456c664],
;;     ::extern-symref ::symbol-ref?,
;;     ::float  #o[s$r_2053 0x45653834],
;;     ::format? ::expr?,
;;     ::function-name ::identifier,
;;     ::function-signature  ::FunctionType,
;;     ::goto-target ::nat,
;;     ::hash-map  #o[s$r_2053 0xa292968],
;;     ::identifier  #o[s$r_2053 0x38c7996d],
;;     ::identifier,
;;     ::identifier-list  #o[s$every_impl$reify__2248 0x82e2f39],
;;     ::identifier-set  #o[s$every_impl$reify__2248 0x6c9b0aca],
;;     ::identifier-suit  #o[s$and_r_2177 0x29366b6],
;;     ::imiginary-part ::float,
;;     ::index ::integer-expr,
;;     ::index-end ::integer-expr,
;;     ::index-end? ::integer-expr?,
;;     ::index-start ::integer-expr,
;;     ::index-start?
;;     ::index-step ::integer-expr,
;;     ::index-step? ::integer-expr?,
;;     ::index? ::integer-expr?,
;;     ::inline ::bool,
;;     ::int  #o[s$r_2053 0x43c468],
;;     ::integer-binop  #o[s$and_r_2177 0x2f87ad4a],
;;     ::integer-binop-enum  #o[s$r_2053 0x1df5b9ff],
;;     ::integer-cmpop  #o[s$and_r_2177 0x28843525],
;;     ::integer-cmpop-enum  #o[s$r_2053 0x167b9758],
;;     ::integer-expr  #o[s$or_r_2112 0x629e1132],
;;     ::integer-expr,
;;     ::integer-expr?  #o[s$or_r_2112 0x15204b8f],
;;     ::integer-expr?,
;;     ::integer-expr?,
;;     ::integer-expr?,
;;     ::integer-expr?,
;;     ::integer-expr?,
;;     ::integer-kind  #o[s$r_2053 0x350af25f],
;;     ::integer-left ::integer-expr,
;;     ::integer-right
;;     ::integer-scalar  #o[s$and_r_2177 0x6e28e680],
;;     ::integer-scalar-or-expr  #o[s$or_r_2112 0x312d2bc5],
;;     ::integer-value?
;;     ::intent  #o[s$and_r_2177 0x41480ad0],
;;     ::intent-enum  #o[s$r_2053 0x6b1f393f],
;;     ::intrinsic ::bool,
;;     ::intrinsic-ident
;;     ::is-restriction ::bool,
;;     ::label ::int,
;;     ::len ::int,
;;     ::len-expr? ::expr?,
;;     ::list-element  #o[s$or_r_2112 0x4e57e7b6],
;;     ::loaded-from-mod ::bool,
;;     ::logical-cmpop  #o[s$and_r_2177 0x401c4687],
;;     ::logical-cmpop-enum  #o[s$r_2053 0x5a83c963],
;;     ::logical-expr  #o[s$or_r_2112 0x2ba76202],
;;     ::logical-expr,
;;     ::logical-expr?  #o[s$or_r_2112 0x6d8c0300],
;;     ::logical-expr?,
;;     ::logical-kind  #o[s$r_2053 0x245b7fa6],
;;     ::logical-left ::logical-expr,
;;     ::logical-right
;;     ::logical-scalar  #o[s$and_r_2177 0x75274b13],
;;     ::logical-value?
;;     ::logicalbinop  #o[s$and_r_2177 0x5b97fe89],
;;     ::logicalbinop-enum  #o[s$r_2053 0x4dc13de5],
;;     ::loop-end ::expr?,
;;     ::loop-increment ::expr?,
;;     ::loop-start ::expr?,
;;     ::loop-v ::expr?,
;;     ::lvalue  #o[s$or_r_2112 0x6f061f3a],
;;     ::message?  #o[s$or_r_2112 0x95bf20e],
;;     ::module ::bool,
;;     ::modulenym ::identifier,
;;     ::nat  #o[s$r_2053 0x5a731fd],
;;     ::node  #o[s$or_r_2112 0x45294831],
;;     ::nodes  #o[s$every_impl$reify__2248 0x39bf5cc8],
;;     ::nym ::identifier,
;;     ::orelse ::stmt*,
;;     ::orelse-expr ::expr,
;;     ::orig-nym ::identifier,
;;     ::orig-symref ::symbol-ref?,
;;     ::overload-id ::nat,
;;     ::overloaded ::stmt?,
;;     ::param* ::expr*,
;;     ::param-type* ::ttype*,
;;     ::pointer-value? ::expr?,
;;     ::presence  #o[s$and_r_2177 0xbe75cb2],
;;     ::presence-enum  #o[s$r_2053 0x3ad957cc],
;;     ::prognym ::identifier,
;;     ::pure ::bool,
;;     ::real-binop  #o[s$and_r_2177 0x6b2c8483],
;;     ::real-binop-enum  #o[s$r_2053 0x40395ef],
;;     ::real-cmpop  #o[s$and_r_2177 0x779302a7],
;;     ::real-cmpop-enum  #o[s$r_2053 0x6896e32],
;;     ::real-expr  #o[s$or_r_2112 0x7f3d7ed8],
;;     ::real-expr?  #o[s$or_r_2112 0x53526522],
;;     ::real-kind  #o[s$r_2053 0x1b970a01],
;;     ::real-left ::real-expr,
;;     ::real-part ::float,
;;     ::real-right ::real-expr,
;;     ::real-scalar  #o[s$and_r_2177 0x5f8ba3fc],
;;     ::real-value? ::real-expr?,
;;     ::restrictions ::symbols,
;;     ::return-type ::ttype,
;;     ::return-var-type? ::ttype?,
;;     ::return-var? ::expr?,
;;     ::rvalue ::expr,
;;     ::scope-nyms ::identifier-set,
;;     ::separator? ::expr?,
;;     ::side-effect-free ::bool,
;;     ::static ::bool,
;;     ::stmt  #o[s$and_r_2177 0x1e143f12],
;;     ::stmt*  #o[s$every_impl$reify__2248 0x586a2aca],
;;     ::stmt?  #o[s$or_r_2112 0x178a64bd],
;;     ::storage-type  #o[s$and_r_2177 0xe432984],
;;     ::storage-type-enum  #o[s$r_2053 0x81d6185],
;;     ::string  #o[s$r_2053 0x793da1b0],
;;     ::string-cmpop  #o[s$and_r_2177 0xd74ce15],
;;     ::string-cmpop-enum  #o[s$r_2053 0x48242e92],
;;     ::string-expr  #o[s$or_r_2112 0x348914d5],
;;     ::string-expr?  #o[s$or_r_2112 0x5f2b56d2],
;;     ::string-expr?,
;;     ::string-left ::string-expr,
;;     ::string-right ::string-expr,
;;     ::string-scalar  #o[s$and_r_2177 0x4de54ba4],
;;     ::string-value?
;;     ::symbol  #o[s$and_r_2177 0x725faf0],
;;     ::symbol-ref  #o[s$map_r_1991 0x343dbffb],
;;     ::symbol-ref*  #o[s$every_impl$reify__2248 0x3de53264],
;;     ::symbol-ref?  #o[s$or_r_2112 0x75b8e115],
;;     ::symbol?  #o[s$or_r_2112 0x377acf11],
;;     ::symbolic-value ::expr?,
;;     ::symbols  #o[s$every_impl$reify__2248 0x67bd7a86],
;;     ::symtab-id ::nat,
;;     ::target ::expr,
;;     ::term  #o[s$r_2053 0x789b045f],
;;     ::test-expr ::logical-expr,
;;     ::ttype  #o[s$and_r_2177 0x7430c052],
;;     ::ttype*  #o[s$every_impl$reify__2248 0x66ecff12],
;;     ::ttype?  #o[s$or_r_2112 0x1fba706b],
;;     ::tuple-left ::tuple-expr,
;;     ::tuple-right ::tuple-expr,
;;     ::type-declaration  #o[s$nilable_impl$reify__2550 0x10f55b35],
;;     ::type-param* ::ttype*,
;;     ::unchecked-element-expr  #o[s$or_r_2112 0x7108ee55],
;;     ::unit  #o[s$and_r_2177 0x2483fc1e],
;;     ::value ::expr,
;;     ::value* ::expr*,
;;     ::value-attr ::bool,
;;     ::value? ::expr?,
;;     ::varnym ::identifier,
;;     ::vars  #o[s$every_impl$reify__2248 0x1bd91c1d],
;; #+end_src
