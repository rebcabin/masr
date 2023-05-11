- [1. PROLOGUE](#1-prologue)
  - [1.1. Namespace Declaration](#11-namespace-declaration)
  - [1.2. Lightweight, Load-Time Testing:](#12-lightweight-load-time-testing)
  - [1.3. Unmap External Names](#13-unmap-external-names)
- [2. MASR OVERVIEW \& BACKGROUND](#2-masr-overview--background)
  - [2.1. MASR IS A TYPE SYSTEM](#21-masr-is-a-type-system)
    - [2.1.1. Terms (Nodes) in the ASDL Grammar](#211-terms-nodes-in-the-asdl-grammar)
    - [2.1.2. Terms Used but not Defined in ASDL](#212-terms-used-but-not-defined-in-asdl)
    - [2.1.3. Term-Like Things](#213-term-like-things)
    - [2.1.4. Mappings from ASDL to MASR](#214-mappings-from-asdl-to-masr)
- [3. WHAT IS A _SPECIFICATION_?](#3-what-is-a-specification)
  - [3.1. CHECKING INSTANCES](#31-checking-instances)
- [4. FULL-FORM ENTITY HASH-MAPS](#4-full-form-entity-hash-maps)
- [5. SUGAR](#5-sugar)
  - [5.1. SUGAR NAMING CONVENTION](#51-sugar-naming-convention)
    - [5.1.1. Light Sugar](#511-light-sugar)
    - [5.1.2. Heavy Sugar](#512-heavy-sugar)
    - [5.1.3. Legacy Sugar](#513-legacy-sugar)
- [6. WHAT ARE TERMS?](#6-what-are-terms)
- [7. QUALIFIED KEYWORDS AND `::TERM`](#7-qualified-keywords-and-term)
- [8. POLYMORPHIC SPECS FOR TERMS](#8-polymorphic-specs-for-terms)
- [9. NESTED MULTI-SPECS](#9-nested-multi-specs)
- [10. NAMING CONVENTION FOR MULTI-SPECS](#10-naming-convention-for-multi-specs)
- [11. TELESCOPING SPECS](#11-telescoping-specs)
- [12. TERM ENTITY KEY](#12-term-entity-key)
- [13. DEFMASRNESTED](#13-defmasrnested)
- [14. TERM-HEAD ENTITY KEY](#14-term-head-entity-key)
- [15. DEFMASRTYPE](#15-defmasrtype)
- [16. RECURSIVE TYPE CHECKING, AGAIN](#16-recursive-type-checking-again)
- [17. EXTRACTING ASDL FROM MASR](#17-extracting-asdl-from-masr)
- [18. TO ASDL-TYPE](#18-to-asdl-type)
- [19. TERMS WITH NESTED MULTI-SPECS](#19-terms-with-nested-multi-specs)
- [20. ADD NEW DEFINITIONS HERE](#20-add-new-definitions-here)
  - [20.1. UNIT](#201-unit)
  - [20.2. SYMBOL](#202-symbol)
  - [20.3. STMT](#203-stmt)
  - [20.4. EXPR](#204-expr)
  - [20.5. TTYPE](#205-ttype)
- [21. LEGACY MACRO](#21-legacy-macro)
- [22. IMPLEMENTATIONS](#22-implementations)
- [23. CALL-ARG](#23-call-arg)
  - [23.1. Issues](#231-issues)
  - [23.2. Original ASDL](#232-original-asdl)
  - [23.3. Examples](#233-examples)
- [24. DIMENSION](#24-dimension)
  - [24.1. Original ASDL](#241-original-asdl)
  - [24.2. Pluralities](#242-pluralities)
  - [24.3. Dimension-Content](#243-dimension-content)
  - [24.4. Full-Form](#244-full-form)
  - [24.5. Heavy Sugar](#245-heavy-sugar)
- [25. DIMENSIONS](#25-dimensions)
  - [25.1. Pluralities](#251-pluralities)
  - [25.2. Heavy Sugar](#252-heavy-sugar)
- [26. SYMTAB-ID](#26-symtab-id)
  - [26.1. Heavy Sugar](#261-heavy-sugar)
- [27. SYMBOL-TABLE](#27-symbol-table)
  - [27.1. Heavy Sugar](#271-heavy-sugar)
- [28. ENUM-LIKE](#28-enum-like)
  - [28.1. Helpers for Enum-Like](#281-helpers-for-enum-like)
  - [28.2. Enum-Like, Proper](#282-enum-like-proper)
  - [28.3. Most Enum-Likes](#283-most-enum-likes)
  - [28.4. Abi](#284-abi)
    - [28.4.1. Full-Form](#2841-full-form)
    - [28.4.2. Heavy Sugar](#2842-heavy-sugar)
    - [28.4.3. The ABIs](#2843-the-abis)
- [29. TTYPE](#29-ttype)
  - [29.1. Pluralities](#291-pluralities)
  - [29.2. Kind](#292-kind)
  - [29.3. Support Specs For Kinds](#293-support-specs-for-kinds)
  - [29.4. INTEGER, REAL, COMPLEX, LOGICAL](#294-integer-real-complex-logical)
  - [29.5. CHARACTER](#295-character)
    - [29.5.1. Original ASDL](#2951-original-asdl)
  - [29.6. Heavy Sugar for `ttype`](#296-heavy-sugar-for-ttype)
  - [29.7. Sugar for the Kinds](#297-sugar-for-the-kinds)
  - [29.8. TODO The Rest of the `ttypes`](#298-todo-the-rest-of-the-ttypes)
    - [29.8.1. Original ASDL](#2981-original-asdl)
  - [29.9. PREREQUISITE TYPES AND ALIASES](#299-prerequisite-types-and-aliases)
  - [29.10. FUNCTION-TYPE](#2910-function-type)
    - [29.10.1. Original ASDL](#29101-original-asdl)
    - [29.10.2. Pluralities](#29102-pluralities)
    - [29.10.3. Forward Reference](#29103-forward-reference)
    - [29.10.4. Heavy Sugar](#29104-heavy-sugar)
- [30. PLACEHOLDERS](#30-placeholders)
  - [30.1. SYMBOLIC VALUE](#301-symbolic-value)
    - [30.1.1. Sugar](#3011-sugar)
- [31. EXPR](#31-expr)
  - [31.1. PREREQUISITE TYPES AND ALIASES](#311-prerequisite-types-and-aliases)
  - [31.2. NAMED EXPR](#312-named-expr)
    - [31.2.1. Original ASDL](#3121-original-asdl)
    - [31.2.2. Example](#3122-example)
    - [31.2.3. Heavy Sugar](#3123-heavy-sugar)
  - [31.3. FUNCTION CALL](#313-function-call)
    - [31.3.1. Original ASDL](#3131-original-asdl)
    - [31.3.2. Example](#3132-example)
    - [31.3.3. Heavy Sugar](#3133-heavy-sugar)
    - [31.3.4. Legacy Sugar](#3134-legacy-sugar)
  - [31.4. LOGICAL CONSTANT](#314-logical-constant)
    - [31.4.1. Original ASDL](#3141-original-asdl)
    - [31.4.2. Example](#3142-example)
    - [31.4.3. Heavy Sugar](#3143-heavy-sugar)
  - [31.5. INTEGER CONSTANT](#315-integer-constant)
    - [31.5.1. Original ASDL](#3151-original-asdl)
    - [31.5.2. Example](#3152-example)
    - [31.5.3. Heavy Sugar](#3153-heavy-sugar)
  - [31.6. VAR](#316-var)
    - [31.6.1. Issue #23](#3161-issue-23)
    - [31.6.2. Heavy Sugar](#3162-heavy-sugar)
    - [31.6.3. Legacy Sugar](#3163-legacy-sugar)
  - [31.7. STRING CONSTANT](#317-string-constant)
    - [31.7.1. Original ASDL](#3171-original-asdl)
  - [31.8. Example](#318-example)
  - [31.9. STRING ORD](#319-string-ord)
    - [31.9.1. Original ASDL](#3191-original-asdl)
  - [31.10. Example](#3110-example)
  - [31.11. LOGICAL BINOP](#3111-logical-binop)
    - [31.11.1. Original ASDL](#31111-original-asdl)
    - [31.11.2. Example](#31112-example)
    - [31.11.3. Heavy Sugar](#31113-heavy-sugar)
  - [31.12. LOGICAL COMPARE](#3112-logical-compare)
    - [31.12.1. Original ASDL](#31121-original-asdl)
    - [31.12.2. Example](#31122-example)
    - [31.12.3. Heavy Sugar](#31123-heavy-sugar)
- [32. STMT](#32-stmt)
  - [32.1. PREREQUISITE TYPES AND ALIASES](#321-prerequisite-types-and-aliases)
  - [32.2. IF](#322-if)
    - [32.2.1. Original ASDL](#3221-original-asdl)
  - [32.3. Example](#323-example)
    - [32.3.1. Heavy Sugra](#3231-heavy-sugra)
  - [32.4. ASSIGNMENT](#324-assignment)
    - [32.4.1. Original ASDL](#3241-original-asdl)
    - [32.4.2. Issues](#3242-issues)
    - [32.4.3. Heavy Sugar](#3243-heavy-sugar)
  - [32.5. PRINT](#325-print)
    - [32.5.1. Original ASDL](#3251-original-asdl)
    - [32.5.2. Heavy Sugar](#3252-heavy-sugar)
  - [32.6. RETURN](#326-return)
  - [32.7. SUBROUTINE CALL](#327-subroutine-call)
    - [32.7.1. Original ASDL](#3271-original-asdl)
    - [32.7.2. Examples](#3272-examples)
    - [32.7.3. Heavy Sugar](#3273-heavy-sugar)
    - [32.7.4. Legacy Sugar](#3274-legacy-sugar)
- [33. SYMBOL](#33-symbol)
  - [33.1. EXTERNAL SYMBOL](#331-external-symbol)
    - [33.1.1. Original ASDL](#3311-original-asdl)
    - [33.1.2. Example](#3312-example)
    - [33.1.3. Heavy Sugar](#3313-heavy-sugar)
    - [33.1.4. Legacy Sugar](#3314-legacy-sugar)
  - [33.2. VARIABLE](#332-variable)
    - [33.2.1. Original ASDL](#3321-original-asdl)
    - [33.2.2. Example](#3322-example)
    - [33.2.3. Light Sugar](#3323-light-sugar)
    - [33.2.4. Heavy Sugar](#3324-heavy-sugar)
    - [33.2.5. Legacy Sugar](#3325-legacy-sugar)
  - [33.3. MODULE](#333-module)
    - [33.3.1. Original ASDL](#3331-original-asdl)
    - [33.3.2. Heavy Sugar](#3332-heavy-sugar)
    - [33.3.3. Legacy Sugar](#3333-legacy-sugar)
  - [33.4. FUNCTION](#334-function)
    - [33.4.1. Original ASDL](#3341-original-asdl)
    - [33.4.2. Heavy Sugar](#3342-heavy-sugar)
    - [33.4.3. Legacy Sugar](#3343-legacy-sugar)
  - [33.5. PROGRAM](#335-program)
    - [33.5.1. Original ASDL](#3351-original-asdl)
    - [33.5.2. Heavy Sugar](#3352-heavy-sugar)
    - [33.5.3. Legacy Sugar](#3353-legacy-sugar)
- [34. UNIT](#34-unit)
  - [34.1. Prerequisite Type Aliases](#341-prerequisite-type-aliases)
  - [34.2. Pluralities](#342-pluralities)
  - [34.3. TRANSLATION UNIT](#343-translation-unit)
    - [34.3.1. Heavy Sugar](#3431-heavy-sugar)


# 1. PROLOGUE


This file is semi-literate programming.
"Semi-literate" means that blocks of code in the
Markdown file, `specs.md`, are extracted from the
live source code in `specs.clj`, but without
reordering. In fully literate programming, we
present blocks of code in _narrative order_,
where we talk about and write code with things
before they're fully defined. Fully literate
programming requires tools for reassembling
blocks of code from _define-first_ order in
source files into narrative order in Markdown. We
don't have such tools for Clojure. Instead, we
suffice ourselves to go the other way and simply
reformat source files into Markdown files.


Write code in the source files, mostly in
`specs.clj`. Format code comments in Markdown.
End a block of comments with a comment line that
says `#+begin_src` by itself, beginning in column
1, then add a blank line. Terminate src blocks
with `#+end_src` in a Clojure comment beginning
in column 1. You'll see many examples below.


When reading this Markdown file, `specs.md`,
you'll just have to backtrack to definitions when
you encounter code that needs them. If you read a
lot of code, you will be accustomed to that
nuisance.


When the narrative really MUST talk about code
that isn't defined yet, comment out your code or
precede s-expressions with `#_`. Clojure will
parse but ignore such escaped s-expressions.


Just before checking in, extract `specs.md` from
`specs.clj` via the following shell command:


```bash
awk -f md4code.awk < ./src/masr/specs.clj > specs.md
```


Visual Studio Code can maintain Table of Contents
and section numbers via an extension called
MarkdownForAll. Install it. To rebuild the Table
of Contents and section numbers in `specs.md`:

1. Run `md4code.awk` as shown above.

2. Open or revert `specs.md` in Visual Studio
   Code.

3. Cmd-Shift-P, "Add or Update Section Numbers."

4. Position the cursor at the top of `specs.md`,
   then Cmd-Shift-P, "Create Table of Contents."

5. Save `specs.md` from Visual Studio Code.

6. git commit `specs.mf` and git push it.


In case of emergency, you might synchronize
`specs.clj` from `specs.md`:


```bash
awk -f code4md.awk < specs.md > ./src/masr/specs.clj
```

However, that's not normal workflow.




## 1.1. Namespace Declaration


Because we must define things before using them,
we must first declare Clojure dependencies for
the rest of the code in this file.


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


## 1.2. Lightweight, Load-Time Testing:


```clojure
(hyperfiddle.rcf/enable!)
```


## 1.3. Unmap External Names


Unmap `Integer` and `Character` so we can have
those symbols in `ttypes`. Access the originals
via `java.lang.Integer` `java.lang.Character`. We
also want `deftype`. Access original `deftype` as
`clojure.core/deftype`.


```clojure
(ns-unmap *ns* 'Integer)
(ns-unmap *ns* 'Character)
(ns-unmap *ns* 'deftype)
```


# 2. MASR OVERVIEW & BACKGROUND


## 2.1. MASR IS A TYPE SYSTEM


MASR is "Meta Abstract Semantics Representation,"
and also a physics pun, "Microwave Amplification
by Stimulated emission of Radiation." It is a
work-in-progress, intended eventually to replace
the ASDL with by a more precise and
discriminating type system.


In general, MASR specs are more discriminating,
precise, and detailed than ASDL could ever
permit. That means ASR in MASR

* is less ambiguous than ASDL; For example, we
  distinguish names of programs from names of
  functions. In ASDL, they're both just
  `identifier`.

* is more explicit than ASDL. For example, in MASR,
  we can say that an argument list may have no more
  than a certain `MAX-NUMBER-OF-CALL-ARGS`.

* has fewer overloaded specs, e.g. `symbol` are
  separate from `symbol_table`, `symbol-ref`

* exposes secret semantics lifted from the current
  C++ implementation into the specification level


Until MASR is integrated, it will have a legacy
back channel. The legacy back channel writes out
types in ASDL format given MASR instances. Such
will help us debug MASR and ensure it fulfills
existing contracts.


We begin with a summary of the full ASDL
specification, or, at least, a snapshot of it:
https://github.com/rebcabin/masr/blob/main/ASR_2023_APR_06_snapshot.asdl


### 2.1.1. Terms (Nodes) in the ASDL Grammar


i.e., things to the left of equals signs:


```c
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
```


### 2.1.2. Terms Used but not Defined in ASDL


```c
31 symbol_table    = a clojure map
32 dimensions      = dimension*, see below
```


### 2.1.3. Term-Like Things


```c
 0 atoms           = int, float, bool, nat, bignat
 0 identifier      = specified below
```

### 2.1.4. Mappings from ASDL to MASR


* ASDL tuples like `(1 2)` are Clojure lists or
  vectors.

* ASDL lists are Clojure lists.

* ASDL vectors like `[expr? stmt*]` are Clojure vectors.

* ASDL symbol_tables are Clojure maps.


# 3. WHAT IS A _SPECIFICATION_?


_Spec_ is short for _specification_. The_ noun
"specification" derives from the verb "to specify."
"To specify" means "to describe specifically:
clearly, explicitly, precisely, unambiguously."
Also, "to specify" means "to distinguish a class
of objects from others that might seem similar at
first glance, say to distinguish ships from
boats."


A major use-case for specifications is for
checking instances against specs: "does this
instance hash-map meet the general specification
of hash-maps of this type?" For
example, "does `(Integer 4 [])`, syntax sugar for
an hash-map instance, meet the general
specification of an ASR `ttype`, which describes
an infinite class of instances?"


In MASR, specs describe sets, and MASR type
checking is often just finding out whether an
instance inhabits a certain specified set.


A type system like like MASR's can act like a
_set theory_ with respect to instances. See this
Stack-Exchange question for the fine points of
set theory versus type theory:


https://math.stackexchange.com/questions/489369


The important point is that a type theory is a
self-contained logic. Clojure specs are arbitrary
predicate functions. Because we may build any
type system that predicate logic can support,
Clojure specs suffice for advanced types like
dependency types and concurrency types.




## 3.1. CHECKING INSTANCES


An instance hash-map may inhabit multiple sets.
For example, any `LogicalConstant` is an `expr`,
and any `expr` is an `asr-term`. These sets stand
in _subset_ relations. We can also check that
`LogicalConstant` is _not_ a `ttype`. All these
tests are in `core_test.clj`.


MASR types are recursive. Bigger types are defined
in terms of smaller types, all the way to some
primitives. All the fields of big entities are
checked against specs, all the way down to the
atoms.




# 4. FULL-FORM ENTITY HASH-MAPS


Every MASR `asr-term` has a full-form. A full-form
is a Clojure _hash-map_ that contains the key
`::term`. Clojure hash-maps are collections of
key-value pairs like Python
dictionaries.[https://clojuredocs.org/clojure.core/hash-map]


Full-forms that are checked against Clojure specs
are called _entities_. For example,


```clojure
;; key         value
{::term        ::intent,
 ::intent-enum 'Local}
```


is an entity checked against specs for `::term`,
`::intent`, and `::intent-enum`.


In MASR, all keys in all hash-maps are
namespace-qualified keywords. Namespace-qualified
keywords have double colons in this file,
`specs.clj`, which defines the namespace
`masr.specs`. In other files, namespace-qualified
keywords in the namespace `masr.specs` must be
written with an explicit prefix as in
`:masr.specs/intent`, or with a namespace alias
as in `::asr/intent`.


Namespace-qualified keywords may have specs
registered for them, or not. When a spec is
registered for a namespace-qualified keywords,
Clojure automatically checks types recursively.




# 5. SUGAR


Most entities have sugared forms that are

1. easier for humans to read and write

2. compatible with ASDL output from `--show-asr` in
   lpython and lfortran.


Sugar comes in three flavors: light, heavy, and
legacy.

1. Light sugar employs keyword arguments with
   defaults. Light sugar is unambiguous but more
   verbose than heavy sugar.

2. Heavy sugar employs positional arguments with
   defaults for some tail arguments. Heavy sugar is
   short and mostly compatible with ASDL output from
   `--show-asr`. Heavy sugar is more risky to write
   and much harder to read than light sugar,
   especially for long argument lists as with, say,
   `Variable` and `FunctionType`.

3. Legacy sugar is just like heavy sugar, just
   requiring fewer tick marks on symbols. Legacy
   sugar is the most compatible with ASDL output
   from `--show-asr`.


## 5.1. SUGAR NAMING CONVENTION


### 5.1.1. Light Sugar


The names of light-sugar functions, like `Integer-`,
have a single trailing hyphen. The keyword arguments
of light-sugar functions are partitioned into
required and optional-with-defaults. The keyword
argument lists of light-sugar functions do not
depend on order. The following two examples both
conform to `::asr-term` and to `::ttype`:


```clojure
#_(Integer- {:dimensions [], :kind 4})
#_(Integer- {:kind 4, :dimensions []})
```


### 5.1.2. Heavy Sugar


The names of heavy-sugar functions, like
`Integer` or `Variable--`, have either zero or
two trailing hyphens. The difference concerns
legacy. If a legacy sugar is needed for a term,
the legacy sugar has the name with no hyphens,
like `Variable`, and the heavy sugar has the name
with two hyphens, like `Variable--`. Both legacy
sugar and heavy sugar produce identical
full-forms.


For example, The following is heavy sugar for a
`Variable`, representing the more progressive,
desired form:


```clojure
#_(Variable-- 2 'x (Integer 4)
            nil [] Local
            [] []  Default
            Source Public Required
            false)
```


Heavy-sugar functions employ positional arguments
that depend on order. Final arguments may have
defaults. For example, the following examples
conform to both `::asr-term` and to `::ttype`:


```clojure
#_(Integer)
#_(Integer 4)
#_(Integer 2 [])
#_(Integer 8 [[6 60] [1 42]])
```


### 5.1.3. Legacy Sugar


The purpose of legacy sugar is to auto-quote
symbols and to correct certain defects in the
original design of ASR in ASDL, such as a
symbol-ref's sometimes being a list and sometimes
being a naked par.


Here is a legacy version of the entity above:


```clojure
#_(Variable 2 x []
          Local () ()
          Default (Integer 4 []) Source
          Public Required false)
```


Notice NO QUOTE MARK on the name of the variable.
That's the way `--show-asr` prints it. That's the
only difference between heavy sugar and legacy
sugar for `Variable`.


For specs where MASR heavy sugar and ASDL legacy
are identical, like `Integer`, there is no
function with two trailing hyphens in its name.




# 6. WHAT ARE TERMS?


MASR _terms_ are models of terms or productions in
the ASDL grammar, items to the left of equals signs
like symbol or stmt in the list above and in the
snapshot cited above.


EXAMPLE -- all these full-forms mean the same:

* always acceptable, if verbose:


```clojure
{:masr.specs/term        :masr.specs/intent,
 :masr.specs/intent-enum 'Unspecified}
```

* shorter form, always acceptable:


```clojure
#:masr.specs{:term        :intent,
             :intent-enum 'Unspecified}
```

* when in this file or in namespace masr.specs, via
  the line (in-ns 'masr.specs):


```clojure
{::term        ::intent,
 ::intent-enum 'Unspecified}
```

* if `masr.specs` is aliased to `asr`, as in `(:use
  [masr.specs :as asr])` in `core_test.clj`:


```clojure
;; {::asr/term        ::asr/intent,
;;  ::asr/intent-enum 'Unspecified}
```


# 7. QUALIFIED KEYWORDS AND `::TERM`


`::term` is both a qualified keyword _and_ a
tag-fetching function, which picks the value of
the key :`:term` from any hash-map. For example,
`(::term {... the instance above ...})` produces
`::intent`.


As a qualified keyword, `::term` can name a Clojure
spec. The following spec will check
whether `::intent` is a `::term`:


```clojure
(s/def ::term qualified-keyword?)
```

EXAMPLE: "intent" is a valid "term"


```clojure
(s/valid? ::term ::intent)
;; => true
```


# 8. POLYMORPHIC SPECS FOR TERMS


`defmulti` defines a name, say `term` (no colons),
for a collection of `defmethods` with the same name.
The `defmulti` links the defmulti-defmethod name
`term` to a dispatcher function, here exactly the
tag-fetcher `::term` (with colons), with this
qualified keyword acting in its role of tag-fetcher.
Each defmethod of `term` is also tagged by the value
fetched from an entity via `::term`.
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




# 9. NESTED MULTI-SPECS


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




# 10. NAMING CONVENTION FOR MULTI-SPECS


All multi-spec names in MASR, nested or not, begin
with `::asr-...`, as in `::asr-term` (not nested)
and `::asr-ttype-head` (nested in ttypes).


```clojure
(s/def ::asr-term
  (s/multi-spec term ::term))
```


# 11. TELESCOPING SPECS


A given entity (instance hash-map) may be

* an `::asr-term` -- any one of the terms

* a `::symbol` -- a particular one of the several
  terms,

* and a `::Variable` -- a particular one of the
  several symbols.

These three telescoping specs, `::asr-term`,
`::symbol`, `::Variable`, are of increasing
precision and discrimination. These specs _qua_
types are isomorphic to sets that stand in strict
subset relations: there are asr-terms that are
not symbols, and there are symbols that are not
Variables.


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




# 12. TERM ENTITY KEY


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


# 13. DEFMASRNESTED


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
```


Uses of the `defmasrnested` macro:


```clojure
(defmasrnested expr)
(defmasrnested stmt)
(defmasrnested symbol)
(defmasrnested ttype)
(defmasrnested unit)
```


# 14. TERM-HEAD ENTITY KEY


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


# 15. DEFMASRTYPE


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


# 16. RECURSIVE TYPE CHECKING, AGAIN


MASR automatically type-checks entities before
projecting them back to the less discriminating
and unchecked ASDL types. Recursive type-checking
pertains to terms with and without nested
multi-specs.


# 17. EXTRACTING ASDL FROM MASR


```clojure
(def asdl-types
  {
   ::SymbolTable  "symbol_table stab"
   ::body         "stmt* body"
   ::call-args    "call_arg* args"
   ::dependencies "identifier* dependencies"
   ::dimensions   "dimension* dims"
   ::dt?          "expr? dt"
   ::logical-kind "int kind"
   ::logicalbinop "logicalbinop"
   ::lvalue       "expr target"
   ::nymref       "symbol name"
   ::orig-nymref  "symbol? original_name"
   ::overloaded   "stmt? overloaded"
   ::prognym      "identifier program_name"
   ::rvalue       "expr value"
   ::symtab-id    "symbol_table stid" ;; TODO: this is TERRIBLE
   ::varnym       "identifier varnym"
   })
```

```clojure
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
```


```clojure
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


# 18. TO ASDL-TYPE


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


# 19. TERMS WITH NESTED MULTI-SPECS


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




# 20. ADD NEW DEFINITIONS HERE



## 20.1. UNIT


```clojure
(defmulti  unit->asdl-type ::unit-head)
(term->asdl-type unit)

(defmasrtype
  TranslationUnit unit
  ;; types of the attributes:
  (SymbolTable
   nodes))
```

## 20.2. SYMBOL


```clojure
(defmulti  symbol->asdl-type ::symbol-head)
(term->asdl-type symbol) ;; Don't expand in CIDER! console only.

(defmasrtype
  Program symbol
  ;; types of the attributes:
  (SymbolTable
   prognym    dependencies    body))
```
```clojure
(defmasrtype
  ExternalSymbol symbol
  (symtab-id
   nym          extern-symref
   modulenym    scope-nyms       orig-nym
   access))
```
```clojure
(defmasrtype
  Variable symbol
  (symtab-id        varnym              dependencies
                    intent              symbolic-value    value?
                    storage-type        ttype             abi
                    access              presence          value-attr
                    type-declaration))
```
```clojure
(defmasrtype
  Module symbol
  (SymbolTable
   modulenym       dependencies    loaded-from-mod
   intrinsic))
```
```clojure
(defmasrtype
  Function symbol
  (SymbolTable ;; not a symtab-id!
   function-name    function-signature    dependencies
   params           body                  return-var?
   access           deterministic         side-effect-free
   ))
```

## 20.3. STMT


```clojure
(defmulti  stmt->asdl-type ::stmt-head)
(term->asdl-type stmt)   ;; CIDER macro-expand removes namespace.

(defmasrtype
  Assignment stmt
  ;; types of the attributes:
  (lvalue    rvalue    overloaded))
```
```clojure
(defmasrtype
  If stmt
  (test-expr body orelse))
```
```clojure
(defmasrtype
  Print stmt
  (format? values separator? end?))
```
```clojure
(defmasrtype
  Return stmt
  ())
```
```clojure
(defmasrtype
  SubroutineCall stmt
  (nymref    orig-nymref    call-args    dt?))
```

## 20.4. EXPR


```clojure
(defmulti  expr->asdl-type ::expr-head)
(term->asdl-type expr)

(defmasrtype
  NamedExpr expr
  ;; types of the attributes:
  (target value ttype))
```
```clojure
(defmasrtype
  FunctionCall expr
  (nymref    orig-nymref    call-args
             return-type    value?    dt?))
```
```clojure
(defmasrtype
  LogicalBinOp expr
  (logical-left    logicalbinop    logical-right
                   Logical         value?))
```
```clojure
(defmasrtype
  LogicalCompare expr
  (logical-left    logicalcmpop    logical-right
                   Logical         value?))
```
```clojure
(defmasrtype
  LogicalConstant expr
  (bool    Logical))
```
```clojure
(defmasrtype
  IntegerConstant expr
  (int    Integer))
```
```clojure
(defmasrtype
  StringConstant expr
  (string ttype))
```
```clojure
(defmasrtype
  StringOrd expr
  (arg ttype value?))
```
```clojure
(defmasrtype
  Var expr
  (symtab-id    varnym))
```

## 20.5. TTYPE


```clojure
(defmulti  ttype->asdl-type ::ttype-head)
(term->asdl-type ttype)

(defmasrtype
  Complex ttype
  ;; types of the attributes:
  (complex-kind dimensions))
```
```clojure
(defmasrtype
  Integer ttype
  (integer-kind dimensions))
```
```clojure
(defmasrtype
  Logical ttype
  (logical-kind dimensions))
```
```clojure
(defmasrtype
  Real ttype
  (real-kind dimensions))
```
```clojure
(defmasrtype
  FunctionType ttype
  (param-types    return-var-type    abi
                  deftype            bindc-name     elemental
                  pure               module         inline
                  static             type-params    restrictions
                  is-restriction))
```


# 21. LEGACY MACRO


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
       ;; no nested fn calls as with ((Var 42 i))
       (if (list? (first x))
         ;; Replace ((Var 42 i)) with [(Var 42 i)]...
         (vec x) ;; ... and other such cases.
         ;; Replace = in function position of a list.
         (replace {'= 'Assignment--} x))
       x))
   it))
```


```clojure
(defmacro legacy
  "DANGER: Call 'eval'."
  [it]
  `(do (in-ns 'masr.specs)
       (eval (rewrite-for-legacy '~it))))
```


# 22. IMPLEMENTATIONS


The remaining sections of this document describe
detailed implementations for every term in MASR.




# 23. CALL-ARG


## 23.1. Issues


https://github.com/rebcabin/masr/issues/32
`call-arg` introduces a level of nesting to a
list of actual arguments to a function call or
subroutine call.


## 23.2. Original ASDL


```c
call_arg = (expr? value)
```


```clojure
(s/def ::call-arg
  (s/coll-of ::expr?
             :min-count 1   ;; Issue 32
             :max-count 1))
```


## 23.3. Examples


Examples can't be executed until `expr?` is
defined. See discussion in `SubroutineCall.`




# 24. DIMENSION


`Dimension` is a term without nested multi-specs.
It is a handwritten special case, not defined via
`defmasrtype`.


## 24.1. Original ASDL


```c
dimension = (expr? start, expr? length)
```


The ASDL is imprecise. The real spec, realized only
in secret C++ code, is that we have either both
`start` and `length` or we just have nothing. MASR
makes exposes this secret explicitly.


## 24.2. Pluralities


Case with 1 index is disallowed.
https://github.com/rebcabin/masr/issues/5


```clojure
(def MIN-DIMENSION-COUNT 0)
(def MAX-DIMENSION-COUNT 2)
```


## 24.3. Dimension-Content


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


## 24.4. Full-Form


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

;; => (() (0 0) (1 1))
```


## 24.5. Heavy Sugar


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


# 25. DIMENSIONS


`Dimensions` [sic] is not a term. Dimensions stands
for `dimension*`, a plurality of dimension. We do a
lot more pluralities later. This is just the first
example of a repeating pattern (TODO: macro?)


## 25.1. Pluralities


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


## 25.2. Heavy Sugar


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


# 26. SYMTAB-ID


In ASDL, `symbol_table` sometimes means a
`SymbolTable` and sometimes means an integer id of a
`SymbolTable` that is specified elsewhere. MASR does
better. MASR `->asdl-type` projects both of these
types, `SymbolTable` and `symtab-id`, back into ASDL
`symbol_table`, with its secret proviso. MASR
exposes the secret. ASDL embraces the secret.


```clojure
(s/def ::symtab-id ::nat)
```


## 26.1. Heavy Sugar


```clojure
(defn symtab-id [it]
  (let [cnf (s/conform ::symtab-id it)]
    (if (s/invalid? cnf)
      ::invalid-symtab-id
      cnf)))
```


# 27. SYMBOL-TABLE


`SymbolTable` is an unwritten term. It doesn't have
nested multi-specs. Write it out fully by hand.


```clojure
(s/def ::hash-map map?)

(defmethod term ::SymbolTable [_]
  (s/keys :req [::term
                ::symtab-id
                ::hash-map]))


(def-term-entity-key SymbolTable)
```


## 27.1. Heavy Sugar


```clojure
(defn SymbolTable [id, hash-map]
  (let [st {::term      ::SymbolTable
            ::symtab-id id
            ::hash-map  hash-map}]
    (if (s/invalid? st)
      ::invalid-symbol-table
      st)))
```


# 28. ENUM-LIKE


Many ASDL types are like enums: they are just a
set of alternative symbols, without parentheses
and without parameters _qua_ arguments. Example:
ASDL `access` has two possibilities: `Public`
and `Private`. MASR automates all of enum-likes
via one macro, `enum-like`.


## 28.1. Helpers for Enum-Like


```clojure
(defmacro symbolate
  "ASDL Back-Channel: create unquoted constants such as
  Local for 'Local."
  [a-set-syms]
  (let [a-set (eval a-set-syms)
        cmds (for [e a-set] (list 'def e `'~e))]
    `(list ~@cmds)))
```


```clojure
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


## 28.2. Enum-Like, Proper


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
```


## 28.3. Most Enum-Likes


```clojure
(enum-like logicalbinop #{'And  'Or  'Xor  'NEqv  'Eqv})
(enum-like cmpop        #{'Eq  'NotEq  'Lt  'LtE  'Gt  'GtE })
(enum-like intent       #{'Local 'In 'Out 'InOut 'ReturnVar 'Unspecified})
(enum-like storage-type #{'Default, 'Save, 'Parameter, 'Allocatable})
(enum-like logicalcmpop #{'Eq 'NotEq})
(enum-like access       #{'Public 'Private})
(enum-like presence     #{'Required 'Optional})
(enum-like deftype      #{'Implementation, 'Interface})
```


## 28.4. Abi


`Abi` is a special case of enum-like with rich logic.

```clojure
(def external-abis
  #{'LFortranModule, 'GFortranModule,
    'BindC, 'Interactive, 'Intrinsic})

(def internal-abis #{'Source})

(s/def ::abi-enum (set/union external-abis internal-abis))

(s/def ::abi-external ::bool)
```


### 28.4.1. Full-Form


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


### 28.4.2. Heavy Sugar


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
```


### 28.4.3. The ABIs


```clojure
(def LFortranModule (abi 'LFortranModule :external true))
(def GFortranModule (abi 'GFortranModule :external true))
(def BindC          (abi 'BindC          :external true))
(def Interactive    (abi 'Interactive    :external true))
(def Intrinsic      (abi 'Intrinsic      :external true))
(def Source         (abi 'Source         :external false))
```


# 29. TTYPE


`Ttype` is a term with nested multi-specs, so it
fits in the macro scheme written at the top of this
file. Its subtypes, `Integer`, `Real`, `Logical`,
etc., have additional structure we automate here.


## 29.1. Pluralities


```clojure
(def MIN-NUMBER-OF-TTYPES    0)
(def MAX-NUMBER-OF-TTYPES 1024)
```

TODO: Consider a regex-spec.


```clojure
(s/def ::ttypes
  (s/coll-of ::ttype
             :min-count MIN-NUMBER-OF-TTYPES
             :max-count MAX-NUMBER-OF-TTYPES))
```

TODO: Consider a regex-spec.


```clojure
(s/def ::ttype?
  (s/coll-of ::ttype
             :min-count 0
             :max-count 1))
```


## 29.2. Kind


The `kind` member selects the kind of a given `ttype`.
MASR currently supports the following:

- `Integer` kinds: `1 (i8)`, `2 (i16)`, `4 (i32)`, `8 (i64)`

- `Real` kinds: `4 (f32)`, `8 (f64)`

- `Complex` kinds: `4 (c32)`, `8 (c64)`

- `Character` kinds: `1 (utf8 string)`

- `Logical` kinds: 1, 2, 4:

   Boolean represented by 1, 2, 4 bytes; the default
   Logical kind is 4, just like the default Integer
   kind, consistent with Python and Fortran: in
   Python "Booleans in Python are implemented as a
   subclass of integers", in Fortran the "default
   logical kind has the same storage size as the
   default integer"; we currently use `kind=4` as
   default for Integer, so we also use `kind=4` as
   default for Logical.


## 29.3. Support Specs For Kinds


```clojure
(s/def ::integer-kind   #{1 2 4 8 16})
(s/def ::real-kind      #{4 8})
(s/def ::complex-kind   #{4 8})
(s/def ::logical-kind   #{1 2 4})
(s/def ::character-kind #{1})
```


## 29.4. INTEGER, REAL, COMPLEX, LOGICAL


Defined via `defmasrtype` at top.


## 29.5. CHARACTER


TODO: `Character` is more rich
`(def-ttype-head Character)`


### 29.5.1. Original ASDL


```c
| Character(int kind, int len, expr? len_expr, dimension* dims)
```


## 29.6. Heavy Sugar for `ttype`


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


## 29.7. Sugar for the Kinds


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
```


```clojure
(def-ttype-and-head Integer)
(def-ttype-and-head Real)
(def-ttype-and-head Complex)
(def-ttype-and-head Logical)
```


```clojure
(def-term-head--entity-key ttype Integer)
(def-term-head--entity-key ttype Real)
(def-term-head--entity-key ttype Complex)
(def-term-head--entity-key ttype Logical)
```


## 29.8. TODO The Rest of the `ttypes`


### 29.8.1. Original ASDL


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




## 29.9. PREREQUISITE TYPES AND ALIASES


```clojure
(s/def ::param-types     ::ttypes)
(s/def ::return-var-type ::ttype?)
```


## 29.10. FUNCTION-TYPE


This is a rich `ttype` that we spell out by hand.


### 29.10.1. Original ASDL


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


### 29.10.2. Pluralities


`symbol*` is written `symbols`, and `restrictions`
is a type alias for `symbols`.


### 29.10.3. Forward Reference


Can only test empty `symbol*`
restrictions until `symbol` is properly defined
below.


```clojure
(def MIN-NUMBER-OF-SYMBOLS   0)
(def MAX-NUMBER-OF-SYMBOLS 128)
```

TODO: Consider a regex-spec.


```clojure
(s/def ::symbols
  (s/coll-of ::symbol
             :min-count MIN-NUMBER-OF-SYMBOLS
             :max-count MAX-NUMBER-OF-SYMBOLS))
```

TODO: Consider a regex-spec.


```clojure
(s/def ::symbol?
  (s/coll-of ::symbol
             :min-count 0,
             :max-count 1))
(s/def ::restrictions    ::symbols)
(s/def ::is-restriction  ::bool)
```


### 29.10.4. Heavy Sugar


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
              ::return-var-type  (if (empty? return-var-type-)
                                   ()
                                   [return-var-type-])
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


# 30. PLACEHOLDERS




## 30.1. SYMBOLIC VALUE


```clojure
(s/def ::symbolic-value empty?)
```


### 30.1.1. Sugar


```clojure
(def symbolic-value identity)
```


# 31. EXPR


## 31.1. PREREQUISITE TYPES AND ALIASES




```clojure
(s/def ::arg ::expr)
```


```clojure
(def MIN-NUMBER-OF-EXPRS    0)
(def MAX-NUMBER-OF-EXPRS 1024)
```

TODO: Consider a regex-spec.


```clojure
(s/def ::exprs
  (s/coll-of ::expr
             :min-count MIN-NUMBER-OF-EXPRS
             :max-count MAX-NUMBER-OF-EXPRS))
```

TODO: Consider a regex-spec.


```clojure
(s/def ::expr?
  (s/coll-of ::expr
             :min-count 0
             :max-count 1))
```
```clojure
(s/def ::value  ::expr)
(s/def ::value? ::expr?)
(s/def ::target ::expr)
```


```clojure
(s/def ::symbol-ref
  (s/keys :req [::identifier
                ::symtab-id]))
```


```clojure
(defn symbol-ref [ident, stid]
  {::identifier ident,
   ::symtab-id  stid})
```


```clojure
(s/def ::return-type   ::ttype)
(s/def ::value?        ::expr?)
```


```clojure
(s/def ::varnym        ::identifier)
```


TODO: check that the types of the exprs are `::Logical`!


```clojure
(s/def ::logical-left  ::expr)
(s/def ::logical-right ::expr)
```


## 31.2. NAMED EXPR


### 31.2.1. Original ASDL


```c
| NamedExpr(expr target, expr value, ttype type)
```


### 31.2.2. Example


```clojure
#_
(NamedExpr
 (Var 2 y)
 (IntegerConstant 0 (Integer 4 []))
 (Integer 4 [])
 )
```


### 31.2.3. Heavy Sugar


```clojure

(defn NamedExpr [target value ttype]
  (let [cnf (s/conform
             ::NamedExpr
             {::term ::expr,
              ::asr-expr-head
              {::expr-head ::NamedExpr
               ::target target
               ::value  value
               ::ttype  ttype}})]
    (if (s/invalid? cnf)
      :invalid-named-expr
      cnf)))
```


## 31.3. FUNCTION CALL


### 31.3.1. Original ASDL


```c
| FunctionCall(symbol name, symbol? original_name, call_arg* args,
                      ttype type, expr? value, expr? dt)
```


### 31.3.2. Example


```clojure
#_
(FunctionCall
 7 g
 ()
 []
 (Integer 4 [])
 ()
 ()
 )
```


### 31.3.3. Heavy Sugar


```clojure
(defn FunctionCall-- [fn-nymref orig-nymref call-args
                      return-type value? dt?]
  (let [cnf (s/conform
             ::FunctionCall
             {::term ::expr,
              ::asr-expr-head
              {::expr-head ::FunctionCall
               ::nymref      (apply symbol-ref fn-nymref)
               ::orig-nymref orig-nymref ;; TODO
               ::call-args   call-args
               ::return-type return-type
               ::value?      value?
               ::dt?         dt?
               }})]
    (if (s/invalid? cnf)
      :invalid-function-call
      cnf)))
```


### 31.3.4. Legacy Sugar


```clojure
(defmacro FunctionCall
  [stid, ident, orig-symref, args, rettype, value?, dt?]
  `(FunctionCall-- ['~ident ~stid]
                   ~orig-symref
                   ~args
                   ~rettype
                   ~value?
                   ~dt?))
```


## 31.4. LOGICAL CONSTANT


### 31.4.1. Original ASDL


```c
| LogicalConstant(bool value, ttype type)
```


### 31.4.2. Example


```clojure
#_
(LogicalConstant true (Logical 4 []))
```


### 31.4.3. Heavy Sugar


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


## 31.5. INTEGER CONSTANT


### 31.5.1. Original ASDL


```c
IntegerConstant(int n, ttype type)
```


### 31.5.2. Example


```clojure
#_
(IntegerConstant 5 (Integer 4 []))
```


### 31.5.3. Heavy Sugar


```clojure
(defn IntegerConstant
  ;; arity-2
  ([an-int, a-ttype]
   (let [cnf {::term ::expr,
              ::asr-expr-head
              {::expr-head ::IntegerConstant
               ::int       an-int
               ::Integer   a-ttype}}]
     (if (s/invalid? cnf)
       :invalid-integer-constant
       cnf)))
  ;; arity-1
  ([an-int]
   (IntegerConstant an-int (Integer))))
```


## 31.6. VAR


### 31.6.1. Issue #23


Is the parameter `symbol` for `Var` really a `symbol`?
Or just an identifier? #23
https://github.com/rebcabin/masr/issues/23
The original ASDL

```c
Var(symbol v)
```

from `ASR.asdl` doesn't match the instance. Instead,
we probably need something like:

```c
Var(symtab_id stid, identifier it)
```




### 31.6.2. Heavy Sugar


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


### 31.6.3. Legacy Sugar


```clojure
(defmacro Var [stid, unquoted-ident]
  `(Var-- ~stid '~unquoted-ident))
```

TODO: make Var look up a value in the
symbol-table! That's part of abstract execution.


## 31.7. STRING CONSTANT


### 31.7.1. Original ASDL


```c
| StringConstant(string s, ttype type)
```


## 31.8. Example


```clojure
#_
(StringOrd
 (StringConstant
  "3"
  (Character 1 1 () [])
  )
 (Integer 4 [])
 (IntegerConstant 51 (Integer 4 []))
 )
```


## 31.9. STRING ORD


### 31.9.1. Original ASDL


```c
| StringOrd(expr arg, ttype type, expr? value)
```


## 31.10. Example


```clojure
#_
(StringOrd
 (StringConstant
  "3"
  (Character 1 1 () [])
  )
 (Integer 4 [])
 (IntegerConstant 51 (Integer 4 []))
 )
```


## 31.11. LOGICAL BINOP


### 31.11.1. Original ASDL


```c
| LogicalBinOp(expr left, logicalbinop op, expr
  right, ttype type, expr? value)
```


### 31.11.2. Example


```clojure
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
```


### 31.11.3. Heavy Sugar


```clojure
(defn LogicalBinOp [left- lbo- right- tt- val?-]
  (let [cnf {::term ::expr,
             ::asr-expr-head
             {::expr-head     ::LogicalBinOp
              ::logical-left  left-
              ::logicalbinop  lbo-
              ::logical-right right-
              ::Logical       tt-
              ::value?        val?-
              }}]
    (if (s/invalid? cnf)
      :invalid-logical-bin-op
      cnf)))
```


## 31.12. LOGICAL COMPARE


### 31.12.1. Original ASDL


```c
| LogicalCompare(expr left,   ;; must have type ::Logical
                 cmpop op,    ;; not all cmpop, only Eq and NotEq
                 expr right,  ;; must have type ::Logical
                 ttype type,
                 expr? value)
```


### 31.12.2. Example


```clojure
#_
(LogicalCompare
  (Var 2 b)
  Eq
  (Var 2 b)
  (Logical 4 []) ())
```


### 31.12.3. Heavy Sugar


```clojure
(defn LogicalCompare [l- cmp- r- tt- val?-]
  (let [cnf {::term ::expr,
             ::asr-expr-head
             {::expr-head     ::LogicalCompare
              ::logical-left  l-
              ::logicalcmpop  cmp-
              ::logical-right r-
              ::Logical       tt-
              ::value?        val?-}}]
    (if (s/invalid? cnf)
      :invalid-logical-compare
      cnf)))
```


# 32. STMT


## 32.1. PREREQUISITE TYPES AND ALIASES


```clojure
(def MIN-NUMBER-OF-STMTS    0)
(def MAX-NUMBER-OF-STMTS 1024)
```


TODO: Consider a regex-spec.


```clojure
(s/def ::stmts
  (s/coll-of ::stmt
             :min-count MIN-NUMBER-OF-STMTS
             :max-count MAX-NUMBER-OF-STMTS))
```


TODO: Consider a regex-spec.


```clojure
(s/def ::stmt?
  (s/coll-of ::stmt
             :min-count 0
             :max-count 1))
```


TODO: more cases for `lvalue`, and an `s/or`
with `second` hack


```clojure
(s/def ::lvalue     ::Var)
(s/def ::rvalue     ::expr)
(s/def ::overloaded ::stmt?)
```


```clojure
(s/def ::symbol-ref?
  (s/coll-of ::symbol-ref
             :min-count 0
             :max-count 1))
```


```clojure
(s/def ::format?    ::expr?)
(s/def ::values     ::exprs)
(s/def ::separator? ::expr?)
(s/def ::end?       ::expr?)
```


```clojure
(s/def ::nym                ::identifier)
(s/def ::extern-symref      ::symbol-ref?)
;; modulenym defined under Module
(s/def ::scope-nyms         ::identifier-set)
(s/def ::orig-nym           ::identifier)
```


```clojure
(s/def ::value-attr         ::bool)
```


`varnym` already defined for Var.
https://github.com/rebcabin/masr/issues/28


```clojure
(s/def ::type-declaration (s/nilable ::symtab-id))
```


TODO: there is ambiguity regarding identifier-sets and lists:


```clojure
(s/def ::dependencies       ::identifier-set)
```


```clojure
(s/def ::modulenym          ::identifier)
(s/def ::loaded-from-mod    ::bool)
(s/def ::intrinsic          ::bool)
```

* `SymbolTable` is already defined

```clojure
(s/def ::function-name      ::identifier)
(s/def ::function-signature ::FunctionType)
```

* `dependencies` is already defined

```clojure
(s/def ::params             ::exprs) ;; renamed from args
(s/def ::body               ::stmts)
(s/def ::return-var?        ::expr?)
```

* `access` is already defined

```clojure
(s/def ::deterministic      ::bool)
(s/def ::side-effect-free   ::bool)

;;
;;
```clojure
(s/def ::prognym            ::identifier)
(s/def ::test-expr          ::expr)
(s/def ::orelse             ::stmts)
```


## 32.2. IF


### 32.2.1. Original ASDL


```c
| If(expr test, stmt* body, stmt* orelse)
```


## 32.3. Example


```clojure
#_
(If
 (NamedExpr
  (Var 2 a)
  (StringOrd
   (StringConstant
    "3"
    (Character 1 1 () [])
    )
   (Integer 4 [])
   (IntegerConstant 51 (Integer 4 []))
   )
  (Integer 4 [])
  )
 [(=
   (Var 2 x)
   (IntegerConstant 1 (Integer 4 []))
   ()
   )]
 []
 )
```


### 32.3.1. Heavy Sugra


```clojure
(defn If [test-expr body orelse]
  (let [cnf {::term ::stmt
             ::asr-stmt-head
             {::stmt-head ::If
              ::test-expr test-expr
              ::body      body
              ::orelse    orelse}}]
    (if (s/invalid? cnf)
      :invalid-if
      cnf)))
```


## 32.4. ASSIGNMENT


### 32.4.1. Original ASDL


```c
| Assignment(expr target, expr value, stmt? overloaded)
         --- Var ---
```


### 32.4.2. Issues


https://github.com/rebcabin/masr/issues/21
https://github.com/rebcabin/masr/issues/22
https://github.com/rebcabin/masr/issues/26




### 32.4.3. Heavy Sugar


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


## 32.5. PRINT


### 32.5.1. Original ASDL

```c
| Print(expr? fmt, expr* values, expr? separator, expr? end)
```



### 32.5.2. Heavy Sugar


```clojure
(defn Print [fmt, values, separator, end]
  (let [cnf (s/conform
             ::Print
             {::term ::stmt,
              ::asr-stmt-head
              {::stmt-head ::Print
               ::format?    fmt
               ::values     values
               ::separator? separator
               ::end?       end}
              })]
    (if (s/invalid? cnf)
      :invalid-print
      cnf)))
```


## 32.6. RETURN


```clojure
(defn Return []
  (let [cnf (s/conform
             ::Return
             {::term ::stmt,
              ::asr-stmt-head
              {::stmt-head ::Return}})]
    (if (s/invalid? cnf)
      :invalid-return
      cnf)))
```


## 32.7. SUBROUTINE CALL


`SubroutineCall` is a special case because it
abuses the word `symbol` to mean a `symbol-ref`.


### 32.7.1. Original ASDL


```c
| SubroutineCall(symbol     name,          ~~~> nymref
                 symbol   ? original_name, ~~~> orig-nymref
                 call_arg * args,          ~~~> call_args
                 expr     ? dt)
```


```clojure
(def MIN-NUMBER-OF-CALL-ARGS   0)
(def MAX-NUMBER-OF-CALL-ARGS 128)

;; TODO: check that `call-args` with empty `expr`s
;; in them only occur at the ends of argument lists,
;; the only place where optional arguments obtain.

(s/def ::call-args
  (s/coll-of ::call-arg
             :min-count MIN-NUMBER-OF-CALL-ARGS
             :max-count MAX-NUMBER-OF-CALL-ARGS))
```


```clojure
(s/def ::dt? ::expr?)
```


### 32.7.2. Examples


We're in a position, here, to run some examples
of `::call-arg` and `::call-args`, because
`::expr?` is defined. Fitting `::call-arg` to
`legacy` is tricky because of the multiple levels
of nesting.


These are all tested in `core_test.clj`:


```clojure
#_(s/valid? ::expr?     ())
#_(s/valid? ::Var       (legacy (Var 42 x)))

#_(s/valid? ::expr?     (legacy ()))
#_(s/valid? ::expr?     (legacy [(Var 42 x)]))
#_(s/valid? ::expr?     (legacy ((Var 42 x))))
  ;; not allowed
#_(not (s/valid? ::call-arg  (legacy [])))
  ;; an empty ::expr?
#_(s/valid? ::call-arg  (legacy [()]))
  ;; various ways of ::expr? with one ::expr,
  ;; a natural expression of ::expr? without
  ;; s/or and its complications, and our
  ;; normal way of expressing ? pluralities,
  ;; via one extra level of nesting.
#_(s/valid? ::call-arg  (legacy (((Var 42 x)))))
#_(s/valid? ::call-arg  (legacy [((Var 42 x))]))
#_(s/valid? ::call-arg  (legacy [[(Var 42 x)]]))
  ;;                        call-args with two call-arg instances
  ;;                                call-arg       call-arg
  ;;                             .-----^------. .-----^------.
#_(s/valid? ::call-args (legacy [[((Var 42 x))] [((Var 43 j))] ]))
#_(s/valid? ::call-args (legacy [])) ;; empty call args
#_(s/valid? ::call-args (legacy [(())]))
```


```clojure
#_(SubroutineCall
   7 test_fn1
   ()
   []
   ())

#_(SubroutineCall
   7 test_fn1
   ()
   [((Var 42 i))]
   ())
```


### 32.7.3. Heavy Sugar


```clojure
(defn SubroutineCall--
  [subr-symref, orig-symref, args, dt?]
  (let [cnf (s/conform ::SubroutineCall
                       {::term ::stmt,
                        ::asr-stmt-head
                        {::stmt-head    ::SubroutineCall
                         ::nymref       (apply symbol-ref subr-symref)
                         ::orig-nymref  orig-symref ;; TODO
                         ::call-args    args
                         ::dt?          dt?
                         }})]
    (if (s/invalid? cnf)
      :invalid-subroutine-call
      cnf)))
```


### 32.7.4. Legacy Sugar


```clojure
(defmacro SubroutineCall
  [stid, ident, orig-symref, args, dt?]
  (if (empty? args)
    `(SubroutineCall-- ['~ident ~stid]
                       ~orig-symref
                       ~args
                       ~dt?)
    `(SubroutineCall-- ['~ident ~stid]
                       ~orig-symref
                       ;; Took a while to find this ...
                       ;; (map vec ~args) does not work!
                       (map (fn [a#] [a#]) ~args)
                       ~dt?)))
```


# 33. SYMBOL




## 33.1. EXTERNAL SYMBOL


### 33.1.1. Original ASDL


```c
| ExternalSymbol(symbol_table parent_symtab, ~~~> symtab-id
                 identifier   name,          ~~~> nym
                 symbol       external,      ~~~> extern-symref
                 identifier   module_name,   ~~~> module-nym
                 identifier*  scope_names,   ~~~> scope-nyms
                 identifier   original_name, ~~~> orig-nym
                 access       access)        ~~~> access
```


### 33.1.2. Example


```clojure
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
```


### 33.1.3. Heavy Sugar


```clojure
(defn ExternalSymbol--
  [stid,    nym-,        extern-symref-
   modnym-, scope-nyms-, orig-nym-,
   access- ]
  (let [cnf (s/conform
             ::ExternalSymbol
             {::term           ::symbol,
              ::asr-symbol-head
              {::symbol-head   ::ExternalSymbol,

               ::symtab-id     stid
               ::nym           nym-
               ::extern-symref (if (empty? extern-symref-)
                                 extern-symref-
                                 [(apply symbol-ref extern-symref-)]),

               ::modulenym     modnym-
               ::scope-nyms    scope-nyms-,
               ::orig-nym      orig-nym-,

               ::access        access-}
              })]
    (if (s/invalid? cnf)
      :invalid-external-symbol
      cnf)))
```


### 33.1.4. Legacy Sugar


First multiary (multiadic) macro


```clojure
(defmacro ExternalSymbol
  (;; "eight-parameter overload"
    [stid, nym,
    orig-symref-stid, orig-symref-ident,
    modnym, scope-nyms, orig-nym,
    access]

   `(ExternalSymbol--
     ~stid, '~nym,
     ['~orig-symref-ident, ~orig-symref-stid],
     '~modnym,
     ~scope-nyms, ;; TODO: distribute quote?
     '~orig-nym
     ~access))
  (;; "seven-parameter overload"
   [stid, nym,
    empty-symref,
    modnym, scope-nyms, orig-nym,
    access]
   `(ExternalSymbol--
     ~stid, '~nym,
     ~empty-symref,
     '~modnym,
     ~scope-nyms, ;; TODO: distribute quote?
     '~orig-nym
     ~access)))
```


## 33.2. VARIABLE


### 33.2.1. Original ASDL


```c
| Variable(symbol_table   parent_symtab,   ;; really an integer id
           identifier     name,
           identifier   * dependencies,    ;; vector of dependency
           intent         intent,
           expr         ? symbolic_value,  ;; lack specified by nil
           expr         ? value,           ;; replace with value?
           storage_type   storage,
           ttype          type,
           abi            abi,
           access         access,
           presence       presence,
           bool           value_attr)
```


### 33.2.2. Example


```clojure
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
```


### 33.2.3. Light Sugar


```clojure
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
           dependencies     ()
           intent           (intent 'Local)

           symbolic-value   ()
           value?           ()
           storage-type     (storage-type 'Default)

           abi              Source
           access           Public
           presence         Required
           value-attr       false}}]
  (let [cnf (s/conform
             ::Variable
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
               }})]
    (if (s/invalid? cnf)
      ::invalid-variable
      cnf)))
```


### 33.2.4. Heavy Sugar


```clojure
(defn Variable--
  "Heavy sugar; parameters that collide with functions
  have trailing hyphens."
  [symtab-id-,         varnym-,        ttype-,
   typedecl-,          dependencies-,  intent-,
   symbolic-value-,    value?-,        storage-type-,
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
               }})]
    (if (s/invalid? cnf)
      ::invalid-variable
      cnf)))
```


### 33.2.5. Legacy Sugar


```clojure
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
```


## 33.3. MODULE


### 33.3.1. Original ASDL


```c
| Module(symbol_table symtab, identifier name, identifier* dependencies,
                      bool loaded_from_mod, bool intrinsic)
```


### 33.3.2. Heavy Sugar


```clojure
(defn Module-- [symtab, modnym, deps, loaded, intrinsic-]
  (let [cnf {::term ::symbol
             ::asr-symbol-head
             {::symbol-head     ::Module
              ::SymbolTable     symtab
              ::modulenym       modnym
              ::dependencies    deps    ;; TODO quote it
              ::loaded-from-mod loaded
              ::intrinsic       intrinsic-}}]
    (if (s/invalid? cnf)
      :invalid-module
      cnf)))
```


### 33.3.3. Legacy Sugar


```clojure
(defmacro Module
  "Quote the mondnym"
  [symtab, modnym, deps, loaded, intrinsic-]
  `(Module-- ~symtab '~modnym ~deps ~loaded ~intrinsic-))
```


## 33.4. FUNCTION


### 33.4.1. Original ASDL


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


```clojure(def-term-head--entity-key symbol Function)
```


### 33.4.2. Heavy Sugar


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
              ::return-var?         (if (empty? retvar)
                                      () [retvar])

              ::access              access-
              ::deterministic       determ
              ::side-effect-free    sefree
              }}]
    (if (s/invalid? cnf)
      :invalid-function
      cnf)))
```


### 33.4.3. Legacy Sugar


```clojure
(defmacro Function
  "Quote the fnnym and the deps."
  [symtab,
   fnnym,   fnsig,  deps,
   params-, body-,  retvar,
   access-, determ, sefree]
  `(Function-- ~symtab,
               '~fnnym,  ~fnsig,  (for [d# '~deps] d#),
               ~params-, ~body-,  ~retvar,
               ~access-, ~determ, ~sefree))
```


## 33.5. PROGRAM


### 33.5.1. Original ASDL


```c
= Program(symbol_table symtab,
          identifier   name,
          identifier*  dependencies,
          stmt*        body)
```


### 33.5.2. Heavy Sugar


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


### 33.5.3. Legacy Sugar


```clojure
(defmacro Program
  "Quote the nym and the dependencies."
  [stab, nym, deps, body-]
  `(Program--
    ~stab,
    '~nym,
    (for [e# '~deps] e#),
    ~body-))
```

# 34. UNIT


## 34.1. Prerequisite Type Aliases


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
```


## 34.2. Pluralities


```clojure
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


## 34.3. TRANSLATION UNIT


### 34.3.1. Heavy Sugar


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
