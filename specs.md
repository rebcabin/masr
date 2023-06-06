- [1. PROLOGUE](#1-prologue)
  - [1.1. Namespace Declaration](#11-namespace-declaration)
  - [1.2. Lightweight, Load-Time Testing:](#12-lightweight-load-time-testing)
  - [1.3. Unmap External Names](#13-unmap-external-names)
- [2. MASR OVERVIEW \& BACKGROUND](#2-masr-overview--background)
  - [2.1. MASR IS A TYPE SYSTEM](#21-masr-is-a-type-system)
  - [2.2. SNAPSHOT SUMMARY](#22-snapshot-summary)
    - [2.2.1. Terms (Nodes) in the ASDL Grammar](#221-terms-nodes-in-the-asdl-grammar)
    - [2.2.2. Terms Used but not Defined in ASDL](#222-terms-used-but-not-defined-in-asdl)
    - [2.2.3. Term-Like Items](#223-term-like-items)
    - [2.2.4. Mappings from ASDL to MASR](#224-mappings-from-asdl-to-masr)
- [3. WHAT IS A _SPECIFICATION_?](#3-what-is-a-specification)
  - [3.1. CHECKING INSTANCES](#31-checking-instances)
- [4. FULL-FORM ENTITY HASH-MAPS](#4-full-form-entity-hash-maps)
- [5. IDEMPOTENCY](#5-idempotency)
- [6. SUGAR](#6-sugar)
  - [6.1. NAMING CONVENTION FOR SUGAR](#61-naming-convention-for-sugar)
    - [6.1.1. Light Sugar](#611-light-sugar)
    - [6.1.2. Heavy Sugar](#612-heavy-sugar)
    - [6.1.3. Legacy Sugar](#613-legacy-sugar)
- [7. WHAT ARE TERMS AND HEADS?](#7-what-are-terms-and-heads)
- [8. QUALIFIED KEYWORDS ARE FUNCTIONS \& SPEC-NAMES](#8-qualified-keywords-are-functions--spec-names)
- [9. POLYMORPHIC SPECS FOR TERMS](#9-polymorphic-specs-for-terms)
- [10. NESTED MULTI-SPECS](#10-nested-multi-specs)
- [11. NAMING CONVENTION FOR MULTI-SPECS](#11-naming-convention-for-multi-specs)
- [12. TELESCOPING SPECS](#12-telescoping-specs)
- [13. TERM ENTITY KEY](#13-term-entity-key)
- [14. DEFMASRNESTED](#14-defmasrnested)
  - [14.1. Use of `defmasrnested`](#141-use-of-defmasrnested)
- [15. TERM-HEAD ENTITY KEY](#15-term-head-entity-key)
- [16. DEFMASRTYPE](#16-defmasrtype)
  - [16.1. EXTRACTING ASDL FROM MASR](#161-extracting-asdl-from-masr)
- [17. TO ASDL-TYPE](#17-to-asdl-type)
- [18. ADD NEW DEFINITIONS HERE](#18-add-new-definitions-here)
  - [18.1. UNIT](#181-unit)
  - [18.2. SYMBOL](#182-symbol)
  - [18.3. STMT](#183-stmt)
  - [18.4. EXPR](#184-expr)
  - [18.5. TTYPE](#185-ttype)
- [19. REWRITING FOR LEGACY](#19-rewriting-for-legacy)
- [20. IMPLEMENTATIONS](#20-implementations)
- [21. CALL-ARG](#21-call-arg)
  - [21.1. Issues](#211-issues)
  - [21.2. Original ASDL](#212-original-asdl)
  - [21.3. Examples](#213-examples)
- [22. DIMENSION](#22-dimension)
  - [22.1. Original ASDL](#221-original-asdl)
  - [22.2. Dimension-Content](#222-dimension-content)
  - [22.3. Full-Form](#223-full-form)
  - [22.4. Heavy Sugar](#224-heavy-sugar)
- [23. DIMENSION\*](#23-dimension)
  - [23.1. Heavy Sugar](#231-heavy-sugar)
- [24. SYMTAB-ID](#24-symtab-id)
  - [24.1. Heavy Sugar](#241-heavy-sugar)
- [25. SYMBOL TABLE](#25-symbol-table)
  - [25.1. Heavy Sugar](#251-heavy-sugar)
- [26. ENUM-LIKE](#26-enum-like)
  - [26.1. Helpers for Enum-Like](#261-helpers-for-enum-like)
  - [26.2. Enum-Like, Proper](#262-enum-like-proper)
  - [26.3. Most Enum-Likes](#263-most-enum-likes)
  - [26.4. Abi](#264-abi)
    - [26.4.1. Full-Form](#2641-full-form)
    - [26.4.2. Heavy Sugar](#2642-heavy-sugar)
    - [26.4.3. The ABIs](#2643-the-abis)
- [27. TTYPE](#27-ttype)
  - [27.1. Prerequisite Types and Aliases](#271-prerequisite-types-and-aliases)
    - [27.1.1. For Loop Statements](#2711-for-loop-statements)
    - [27.1.2. For Character](#2712-for-character)
  - [27.2. Kind](#272-kind)
  - [27.3. Support Specs For Kinds](#273-support-specs-for-kinds)
  - [27.4. Heavy Sugar for `ttype`](#274-heavy-sugar-for-ttype)
  - [27.5. Sugar for the Kinds](#275-sugar-for-the-kinds)
  - [27.6. INTEGER, REAL, COMPLEX, LOGICAL](#276-integer-real-complex-logical)
  - [27.7. CHARACTER](#277-character)
    - [27.7.1. Original ASDL](#2771-original-asdl)
    - [27.7.2. Example](#2772-example)
    - [27.7.3. Heavy Sugar](#2773-heavy-sugar)
  - [27.8. TUPLE](#278-tuple)
  - [27.9. List](#279-list)
  - [27.10. Set](#2710-set)
  - [27.11. FUNCTION-TYPE](#2711-function-type)
    - [27.11.1. Original ASDL](#27111-original-asdl)
    - [27.11.2. Heavy Sugar](#27112-heavy-sugar)
  - [27.12. TODO The Rest of the `ttypes`](#2712-todo-the-rest-of-the-ttypes)
    - [27.12.1. Original ASDL](#27121-original-asdl)
- [28. PLACEHOLDERS](#28-placeholders)
  - [28.1. ESCAPE TARGET](#281-escape-target)
  - [28.2. SYMBOLIC VALUE](#282-symbolic-value)
    - [28.2.1. Sugar](#2821-sugar)
- [29. EXPR](#29-expr)
  - [29.1. Prerequisite Types and Aliases](#291-prerequisite-types-and-aliases)
    - [29.1.1. Unchecked Element Types](#2911-unchecked-element-types)
    - [29.1.2. Logical Types](#2912-logical-types)
    - [29.1.3. Integer Types](#2913-integer-types)
    - [29.1.4. Index Types](#2914-index-types)
    - [29.1.5. Real Types](#2915-real-types)
    - [29.1.6. Complex Types](#2916-complex-types)
    - [29.1.7. Array Types](#2917-array-types)
    - [29.1.8. List Types](#2918-list-types)
    - [29.1.9. Tuple Types](#2919-tuple-types)
    - [29.1.10. String Types](#29110-string-types)
  - [29.2. IF EXP](#292-if-exp)
    - [29.2.1. Original ASDL](#2921-original-asdl)
    - [29.2.2. Example](#2922-example)
    - [29.2.3. Heavy Sugar](#2923-heavy-sugar)
  - [29.3. INTEGER BIT NOT](#293-integer-bit-not)
    - [29.3.1. Original ASDL](#2931-original-asdl)
    - [29.3.2. Heavy Sugar](#2932-heavy-sugar)
  - [29.4. INTEGER, REAL, COMPLEX UNARY MINUS](#294-integer-real-complex-unary-minus)
    - [29.4.1. Typed Uminus Macro](#2941-typed-uminus-macro)
    - [29.4.2. Using the Macro](#2942-using-the-macro)
  - [29.5. NAMED EXPR](#295-named-expr)
    - [29.5.1. Original ASDL](#2951-original-asdl)
    - [29.5.2. Example](#2952-example)
    - [29.5.3. Heavy Sugar](#2953-heavy-sugar)
  - [29.6. FUNCTION CALL](#296-function-call)
    - [29.6.1. Original ASDL](#2961-original-asdl)
    - [29.6.2. Example](#2962-example)
    - [29.6.3. Heavy Sugar](#2963-heavy-sugar)
    - [29.6.4. Legacy Sugar](#2964-legacy-sugar)
  - [29.7. INTRINSIC FUNCTION](#297-intrinsic-function)
    - [29.7.1. Original ASDL](#2971-original-asdl)
    - [29.7.2. Example](#2972-example)
    - [29.7.3. Heavy Sugar](#2973-heavy-sugar)
    - [29.7.4. Legacy Sugar](#2974-legacy-sugar)
  - [29.8. LOGICAL, INTEGER, REAL CONSTANTS](#298-logical-integer-real-constants)
    - [29.8.1. Typed Constant Macro](#2981-typed-constant-macro)
    - [29.8.2. Using the Macro](#2982-using-the-macro)
  - [29.9. STRING CONSTANT](#299-string-constant)
    - [29.9.1. Original ASDL](#2991-original-asdl)
    - [29.9.2. Example](#2992-example)
    - [29.9.3. Heavy Sugar](#2993-heavy-sugar)
  - [29.10. STRING REPEAT](#2910-string-repeat)
    - [29.10.1. Heavy Sugar](#29101-heavy-sugar)
  - [29.11. COMPLEX CONSTANT](#2911-complex-constant)
    - [29.11.1. Original ASDL](#29111-original-asdl)
    - [29.11.2. Example](#29112-example)
    - [29.11.3. Heavy Sugar](#29113-heavy-sugar)
  - [29.12. VAR](#2912-var)
    - [29.12.1. Issue #23](#29121-issue-23)
    - [29.12.2. Heavy Sugar](#29122-heavy-sugar)
    - [29.12.3. Legacy Sugar](#29123-legacy-sugar)
  - [29.13. symbol-table! That's part of abstract execution.](#2913-symbol-table-thats-part-of-abstract-execution)
  - [29.14. ARRAY CONSTANT](#2914-array-constant)
  - [29.15. ARRAY ITEM](#2915-array-item)
    - [29.15.1. Example](#29151-example)
    - [29.15.2. Heavy Sugar](#29152-heavy-sugar)
  - [29.16. ARRAY RESHAPE](#2916-array-reshape)
    - [29.16.1. Example](#29161-example)
    - [29.16.2. Heavy Sugar](#29162-heavy-sugar)
  - [29.17. STRING CHR](#2917-string-chr)
    - [29.17.1. Heavy Sugar](#29171-heavy-sugar)
  - [29.18. STRING LEN](#2918-string-len)
    - [29.18.1. Heavy Sugar](#29181-heavy-sugar)
  - [29.19. STRING ITEM](#2919-string-item)
    - [29.19.1. Heavy Sugar](#29191-heavy-sugar)
  - [29.20. STRING SECTION](#2920-string-section)
    - [29.20.1. Heavy Sugar](#29201-heavy-sugar)
  - [29.21. STRING ORD](#2921-string-ord)
    - [29.21.1. Original ASDL](#29211-original-asdl)
    - [29.21.2. Example](#29212-example)
    - [29.21.3. Legacy Sugar](#29213-legacy-sugar)
  - [29.22. INTEGER BINOP](#2922-integer-binop)
    - [29.22.1. Original ASDL](#29221-original-asdl)
    - [29.22.2. Example](#29222-example)
    - [29.22.3. Heavy Sugar](#29223-heavy-sugar)
  - [29.23. REAL BINOP](#2923-real-binop)
    - [29.23.1. Original ASDL](#29231-original-asdl)
    - [29.23.2. Example](#29232-example)
    - [29.23.3. Heavy Sugar](#29233-heavy-sugar)
    - [29.23.4. Legacy Sugar](#29234-legacy-sugar)
  - [29.24. COMPLEX BINOP](#2924-complex-binop)
    - [29.24.1. Original ASDL](#29241-original-asdl)
    - [29.24.2. Heavy Sugar](#29242-heavy-sugar)
    - [29.24.3. Legacy Sugar](#29243-legacy-sugar)
  - [29.25. LOGICAL BINOP](#2925-logical-binop)
    - [29.25.1. Original ASDL](#29251-original-asdl)
    - [29.25.2. Example](#29252-example)
    - [29.25.3. Heavy Sugar](#29253-heavy-sugar)
  - [29.26. LIST CONSTANT](#2926-list-constant)
    - [29.26.1. Heavy Sugar](#29261-heavy-sugar)
  - [29.27. LIST LEN](#2927-list-len)
    - [29.27.1. Heavy Sugar](#29271-heavy-sugar)
  - [29.28. TUPLE CONSTANT](#2928-tuple-constant)
    - [29.28.1. Heavy Sugar](#29281-heavy-sugar)
  - [29.29. TUPLE LEN](#2929-tuple-len)
    - [29.29.1. Heavy Sugar](#29291-heavy-sugar)
  - [29.30. TUPLE COMPARE](#2930-tuple-compare)
    - [29.30.1. Heavy Sugar](#29301-heavy-sugar)
  - [29.31. COMPLEX RE](#2931-complex-re)
  - [29.32. COMPLEX IM](#2932-complex-im)
  - [29.33. INTEGER COMPARE](#2933-integer-compare)
    - [29.33.1. Original ASDL](#29331-original-asdl)
    - [29.33.2. Example](#29332-example)
    - [29.33.3. Heavy Sugar](#29333-heavy-sugar)
  - [29.34. REAL COMPARE](#2934-real-compare)
    - [29.34.1. Heavy Sugar](#29341-heavy-sugar)
    - [29.34.2. Legacy Sugar](#29342-legacy-sugar)
  - [29.35. COMPLEX COMPARE](#2935-complex-compare)
    - [29.35.1. Heavy Sugar](#29351-heavy-sugar)
    - [29.35.2. Legacy Sugar](#29352-legacy-sugar)
  - [29.36. STRING COMPARE](#2936-string-compare)
    - [29.36.1. Heavy Sugar](#29361-heavy-sugar)
    - [29.36.2. Legacy Sugar](#29362-legacy-sugar)
  - [29.37. LOGICAL COMPARE](#2937-logical-compare)
    - [29.37.1. Original ASDL](#29371-original-asdl)
    - [29.37.2. Example](#29372-example)
    - [29.37.3. Heavy Sugar](#29373-heavy-sugar)
    - [29.37.4. Legacy Sugar](#29374-legacy-sugar)
  - [29.38. LOGICAL NOT](#2938-logical-not)
    - [29.38.1. Original ASDL](#29381-original-asdl)
    - [29.38.2. Heavy Sugar](#29382-heavy-sugar)
  - [29.39. CAST](#2939-cast)
    - [29.39.1. Original ASDL](#29391-original-asdl)
    - [29.39.2. Example](#29392-example)
    - [29.39.3. Heavy Sugar](#29393-heavy-sugar)
  - [29.40. LIST ITEM](#2940-list-item)
  - [29.41. TUPLE ITEM](#2941-tuple-item)
- [30. STMT](#30-stmt)
  - [30.1. Prerequisite Types and Aliases](#301-prerequisite-types-and-aliases)
  - [30.2. LIST APPEND](#302-list-append)
  - [30.3. EXPLICIT DEALLOCATE](#303-explicit-deallocate)
    - [30.3.1. Original ASDL](#3031-original-asdl)
    - [30.3.2. Heavy Sugar](#3032-heavy-sugar)
  - [30.4. ASSERT](#304-assert)
    - [30.4.1. Original ASDL](#3041-original-asdl)
    - [30.4.2. Heavy Sugar](#3042-heavy-sugar)
  - [30.5. GOTO](#305-goto)
    - [30.5.1. Heavy Sugar](#3051-heavy-sugar)
    - [30.5.2. Legacy Sugar](#3052-legacy-sugar)
  - [30.6. IF](#306-if)
    - [30.6.1. Original ASDL](#3061-original-asdl)
    - [30.6.2. Example](#3062-example)
    - [30.6.3. Heavy Sugar](#3063-heavy-sugar)
  - [30.7. ASSIGNMENT](#307-assignment)
    - [30.7.1. Original ASDL](#3071-original-asdl)
    - [30.7.2. Issues](#3072-issues)
    - [30.7.3. Heavy Sugar](#3073-heavy-sugar)
  - [30.8. DO LOOP](#308-do-loop)
    - [30.8.1. Example](#3081-example)
    - [30.8.2. Do-Loop Head Support](#3082-do-loop-head-support)
    - [30.8.3. Heavy Sugar](#3083-heavy-sugar)
  - [30.9. WHILE LOOP](#309-while-loop)
    - [30.9.1. Original ASDL](#3091-original-asdl)
    - [30.9.2. Example](#3092-example)
    - [30.9.3. Heavy Sugar](#3093-heavy-sugar)
  - [30.10. PRINT](#3010-print)
    - [30.10.1. Original ASDL](#30101-original-asdl)
    - [30.10.2. Heavy Sugar](#30102-heavy-sugar)
  - [30.11. RETURN](#3011-return)
  - [30.12. SUBROUTINE CALL](#3012-subroutine-call)
    - [30.12.1. Original ASDL](#30121-original-asdl)
    - [30.12.2. Examples](#30122-examples)
    - [30.12.3. Heavy Sugar](#30123-heavy-sugar)
    - [30.12.4. Legacy Sugar](#30124-legacy-sugar)
  - [30.13. BLOCK CALL](#3013-block-call)
    - [30.13.1. Original ASDL](#30131-original-asdl)
    - [30.13.2. Heavy Sugar](#30132-heavy-sugar)
    - [30.13.3. Legacy Sugar](#30133-legacy-sugar)
- [31. SYMBOL](#31-symbol)
  - [31.1. Prerequisite Types and Aliases](#311-prerequisite-types-and-aliases)
  - [31.2. PROGRAM](#312-program)
    - [31.2.1. Original ASDL](#3121-original-asdl)
    - [31.2.2. Heavy Sugar](#3122-heavy-sugar)
    - [31.2.3. Legacy Sugar](#3123-legacy-sugar)
  - [31.3. MODULE](#313-module)
    - [31.3.1. Original ASDL](#3131-original-asdl)
    - [31.3.2. Heavy Sugar](#3132-heavy-sugar)
    - [31.3.3. Legacy Sugar](#3133-legacy-sugar)
  - [31.4. FUNCTION](#314-function)
    - [31.4.1. Original ASDL](#3141-original-asdl)
    - [31.4.2. Heavy Sugar](#3142-heavy-sugar)
    - [31.4.3. Legacy Sugar](#3143-legacy-sugar)
  - [31.5. GENERIC PROCEDURE](#315-generic-procedure)
    - [31.5.1. Original ASDL](#3151-original-asdl)
    - [31.5.2. Example](#3152-example)
    - [31.5.3. Heavy Sugar](#3153-heavy-sugar)
    - [31.5.4. Legacy Sugar](#3154-legacy-sugar)
  - [31.6. EXTERNAL SYMBOL](#316-external-symbol)
    - [31.6.1. Original ASDL](#3161-original-asdl)
    - [31.6.2. Example](#3162-example)
    - [31.6.3. Heavy Sugar](#3163-heavy-sugar)
    - [31.6.4. Legacy Sugar](#3164-legacy-sugar)
  - [31.7. VARIABLE](#317-variable)
    - [31.7.1. Original ASDL](#3171-original-asdl)
    - [31.7.2. Example](#3172-example)
    - [31.7.3. Light Sugar](#3173-light-sugar)
    - [31.7.4. Heavy Sugar](#3174-heavy-sugar)
    - [31.7.5. Legacy Sugar](#3175-legacy-sugar)
  - [31.8. BLOCK](#318-block)
    - [31.8.1. Original ASDL](#3181-original-asdl)
    - [31.8.2. Legacy Sugar](#3182-legacy-sugar)
  - [31.9. INTRINSIC MODULE](#319-intrinsic-module)
    - [31.9.1. Original ASDL](#3191-original-asdl)
    - [31.9.2. Legacy Sugar](#3192-legacy-sugar)
- [32. UNIT](#32-unit)
  - [32.1. Prerequisite Type Aliases](#321-prerequisite-type-aliases)
  - [32.2. Pluralities](#322-pluralities)
  - [32.3. TRANSLATION UNIT](#323-translation-unit)
    - [32.3.1. Heavy Sugar](#3231-heavy-sugar)


# 1. PROLOGUE



This file is semi-literate programming. Blocks of
code in this Markdown file, `specs.md`, are
extracted from the live source code in
`specs.clj`. This document, if built properly
prior to check-in, cannot get out-of-date from
the code.


To contribute, write code in the source files,
mostly in `specs.clj` and format comments in
Markdown. Precede a block of code with a
double-semicolon-space comment beginning in
column 1 that says `#+begin_src clojure` by
itself, then add a blank line. Terminate such src
blocks with `#+end_src` in a
double-semicolon-space comment beginning in
column 1. You'll see many examples below. AWK
picks up these lines, so be sure to format them
exactly as the examples show.


When reading this Markdown file, `specs.md`,
backtrack to definitions when you encounter code
that needs the definitions. If you read a lot of
code, you will be accustomed to such interruption
to natural narrative order. Alternatively, read
the entire documentation _backwards_: such will
give you an approximation of narrative order.


When the narrative really MUST talk about code
that isn't defined yet, comment out the code or
precede s-expressions with `#_`. Clojure will
parse but ignore such escaped s-expressions. You
will see examples below.


Just before checking in new code, extract
`specs.md` from `specs.clj` via the following
shell command:


```bash
awk -f md4code.awk < ./src/masr/specs.clj > specs.md
```


Visual Studio Code can maintain Table of Contents
and section numbers via an extension called
`MarkdownForAll.` Install it. To rebuild the
Table of Contents and section numbers in
`specs.md`:

1. Run `md4code.awk` as shown above.

2. Open or revert `specs.md` in Visual Studio
   Code.

3. Position the cursor at the top of `specs.md`,
   then `Cmd-Shift-P`, "Add or Update Section
   Numbers."

4. Position the cursor at the top of `specs.md`,
   then `Cmd-Shift-P`, "Create Table of
   Contents."

5. Save `specs.md` from Visual Studio Code.

6. `git commit` `specs.mf` and `git push` it.


The Markdown viewer in Visual Studio Code is
_not_ adequate for reading this file. It jumps
around too much and sometimes gets stuck. We
recommend the Markdown viewer in PyCharm or CLion
or other products from JetBrains. There are
numerous paid alternatives for Markdown on the
Mac, but we have not tried them.


In case of emergency _only_, you might rebuild
`specs.clj` from `specs.md` with the following
**UNTESTED** command:


```bash
awk -f code4md.awk < specs.md > ./src/masr/specs.clj
```

However, you should not need this emergency step if you
have committed frequently.


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


----------------------------------------------------------------
## 2.1. MASR IS A TYPE SYSTEM


MASR is "Meta Abstract Semantics Representation,"
and also a physics pun, "Microwave Amplification
by Stimulated emission of Radiation." It is a
work-in-progress, intended eventually to replace
the aging ASDL with by a more precise and
discriminating type system.

* MASR is less ambiguous than ASDL; For example,
  MASR distinguishes names of programs from names
  of functions. In ASDL, they're both just
  `identifier`.

* MASR is more explicit than ASDL. For example,
  we can say in MASR an argument list may have no
  more than a certain number of arguments.

* MASR is more precise and less overloaded. For
  example, `symbol` is separate from
  `symbol_table`, `symbol-ref` in MASR.

* MASR exposes secret semantics that ASDL cannot
  express. TODO: example.


Until MASR is integrated, it will have a legacy
back-channel. The legacy back-channel writes
types in ASDL format from MASR instances.

----------------------------------------------------------------
## 2.2. SNAPSHOT SUMMARY


We begin with a summary of a snapshot of the full
ASDL specification:
https://github.com/rebcabin/masr/blob/main/ASR_2023_APR_06_snapshot.asdl


### 2.2.1. Terms (Nodes) in the ASDL Grammar


Terms are items to the left of equals signs. Terms may
also be called `nodes`.


```c
 1 unit            = TranslationUnit(symbol_table, node*)
 2 symbol          = ... many heads ...
 3 storage_type    = Default | Save | Parameter | Allocatable
 4 access          = Public | Private
 5 intent          = Local | In | Out | ... | Unspecified
 6 deftype         = Implementation | Interface
 7 presence        = Required | Optional
 8 abi             = Source | LFortranModule | ... | Intrinsic
 9 stmt            = ... many heads ...
10 expr            = ... many heads ...
11 ttype           = Integer(int, dim*) | ... | FunctionType( ... )
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
22 attribute       = Attribute(ident name, attr_arg *args)
23 attribute_arg   = (identifier arg)
24 call_arg        = (expr? value)
25 tbind           = Bind(string lang, string name)
26 array_index     = (expr? left, expr? right, expr? step)
27 do_loop_head    = (expr? v, expr? start, expr? end, expr? incr)
28 case_stmt       = CaseStmt(expr*, stmt*) | CaseStmt_Range( ... )
29 type_stmt       = TypeStmtName(symbol, stmt*) | ...
30 enumtype        = IntegerConsecutiveFromZero | ... | NonInteger
```

### 2.2.2. Terms Used but not Defined in ASDL

```c
31 symbol_table    = a clojure hash-map
32 dimension*      = see below
```

### 2.2.3. Term-Like Items

```c
 0 atoms           = int, float, bool, string, nat, bignat
 0 identifier      = specified below
```

### 2.2.4. Mappings from ASDL to MASR

* ASDL tuples like `(1 2)` are Clojure lists or
  vectors.

* ASDL lists are Clojure lists.

* ASDL vectors like `[expr? stmt*]` are Clojure vectors.

* ASDL symbol_tables are Clojure maps.


# 3. WHAT IS A _SPECIFICATION_?



The noun "Specification" derives from the
verb "to specify." "To specify" means "to
describe specifically, clearly, explicitly,
precisely, unambiguously." Also, "to specify"
means "to distinguish objects from others that
seem similar, say to distinguish ships from
boats."


The main use-case for specifications is checking
instances against specs: "does this instance
hash-map meet the general specification for
hash-maps of this type?" For
example, "does `(Integer 4 [])`, syntax sugar for
a hash-map instance, meet the general
specification of an ASR `ttype`, which describes
an infinite class of hash-map instances?"


In MASR, specs describe mathematical _sets_ of
valid or conforming values, and MASR type
checking is often just checking whether an
instance inhabits a certain set.


Therefore, a type system like MASR's can act like
a _decidable set theory_ with respect to
instances. See this Stack-Exchange question for
the fine points of set theory versus type theory:


  https://math.stackexchange.com/questions/489369


Clojure specs are arbitrary predicate functions.
We can build any type-theory that needs only
first-order predicate calculus. Clojure specs
suffice for advanced types like dependency types
and concurrency types. Such advanced types are
work-in-progress for MASR.

----------------------------------------------------------------
## 3.1. CHECKING INSTANCES


An instance hash-map may inhabit multiple sets.
For example, any `LogicalConstant` is an `expr`,
and any `expr` is an `asr-term`. These sets stand
in _subset_ relations. We can also check that
`LogicalConstant` is _not_ a `ttype`. Many
examples of tests like this are in
`core_test.clj`. Reading the tests and stepping
through them in the debugger is a great way to
learn MASR.


MASR types are recursive. Bigger types are
defined in terms of smaller types, all the way to
a handful of primitive _atomic_ types. All fields
of big entities are checked against specs, all
the way down to the atoms.



# 4. FULL-FORM ENTITY HASH-MAPS



Every MASR `asr-term` instance has a full-form. A
full-form is a Clojure _hash-map_ that contains
the key `::term` at top level. Clojure hash-maps
are collections of key-value pairs like Python
dictionaries.[https://clojuredocs.org/clojure.core/hash-map]


Full-forms that are checked against Clojure specs
are _entities_. For example,


```clojure
;; key         value
{::term        ::intent,
 ::intent-enum "Local"}
```

is an entity checked against specs for `::term`,
`::intent`, and `::intent-enum`.


All hash-map keys in MASR are Clojure _qualified
keywords_ in the namespace `masr.specs`, often
denoted with double-colons.


This file, `specs.clj` creates, defines, and is
_in_ namespace `masr.specs`. In any file in that
namespace, we denote qualified keywords with
double colons. Other files, like
`core_tests.clj`, might be _in_ other namespaces.
Qualified keywords in `masr.specs` must be
written out in full in such files, say with an
explicit prefix as in `:masr.specs/intent`, or
with a namespace alias as in `::asr/intent`. The
test file, `core_tests.clj`, employs the
namespace alias `asr`.


Namespace-qualified keywords may have specs
registered for them. When a spec is registered
for a namespace-qualified keyword, Clojure
automatically checks types of entities
recursively.


EXAMPLES -- all the following full-forms mean the
same:

* always acceptable, though verbose:


```clojure
{:masr.specs/term        :masr.specs/intent,
 :masr.specs/intent-enum "Unspecified"}
```

* Clojure-standard shorter form, always
  acceptable:


```clojure
#:masr.specs{:term        :intent,
             :intent-enum "Unspecified"}
```

* when in this file or in namespace `masr.specs`
  via the line `(in-ns 'masr.specs)`:


```clojure
{::term        ::intent,
 ::intent-enum "Unspecified"}
```

* if `masr.specs` is aliased to `asr`, as in
`(:use [masr.specs :as asr])` in
`core_test.clj` (commented out because it's not
executable in the file `specs.clj`):


```clojure
;; {::asr/term        ::asr/intent,
;;  ::asr/intent-enum 'Unspecified}
```

*PITFALL WARNING* -- If you do not register a
 spec for a qualified keyword, `k`, Clojure will
 _always pass_ an item in a hash-map with key
 `k`. Unregistered qualified keywords can lead to
 _false positive checks_.



# 5. IDEMPOTENCY



Large ASR expressions overflow the Java
method-size limit of 64KB when evaluated
recursively. Bottom-up evaluation requires
idempotency: evaluating a full-form produces the
same full-form. This is easy only if we replace
symbols with strings because the mechanics of
quoting symbols idempotently in context is too
difficult and subtle.


For an example, consider the following hash-map,
and notice the external quote, preventing
evaluation. Without this quote, Clojure would
error when evaluating this expression at
load-time because it would attempt to evaluate
the unbound symbol `main0`:

```clojure
'#:masr.specs{:stmt-head  ;; <~~~ external quote
             :masr.specs/SubroutineCall,
             :symbol-ref
             #:masr.specs{:identifier
                          main0,
                          :symtab-id
                          114},
             :orig-symref (),
             :call-args (),
             :dt? ()}
```

The above is the spec produced by evaluating the
non-idempotent heavy-sugar function,
`SubroutineCall`. The naked symbol `main0` is
unbound and won't survive another round of
evaluation. The sugar function must be modified
to the following:

```clojure
#:masr.specs{:stmt-head  ;; <~~~ difference
             :masr.specs/SubroutineCall,
             :symbol-ref
             #:masr.specs{:identifier
                          "main0",  ;; <~~~ difference
                          :symtab-id
                          114},
             :orig-symref (),
             :call-args (),
             :dt? ()}
```

Every element of that hash-map is
self-evaluating: keywords, numbers, strings, the
empty list `()`. It turns out that vectors with
self-evaluating elements are also
self-evaluating. We're in business if we replace
all unbound symbols with strings.



# 6. SUGAR



Most entities have sugared forms that are

1. easier for humans to read and write

2. compatible with output from `--show-asr` in
   lpython and lfortran.


Sugared forms are function-calls at
bottom (examples below). Some sugared forms
employ macros to replace symbols with strings and
for other utilitarian transformations on the way
to bottoming out at a function call.


Sugar comes in three flavors: _light_, _heavy_,
and _legacy_.


1. Light sugar employs functions with
   single-colon keyword arguments with default
   values. Light sugar is unambiguous but more
   verbose than heavy sugar.


2. Heavy sugar employs functions with positional
   arguments, with possible default values for
   tail arguments. Heavy sugar is short and
   mostly compatible with ASDL output from
   `--show-asr`. Heavy sugar is more risky to
   write and much harder to read than light
   sugar, especially for long argument lists as
   with, say, `Variable` and `FunctionType`.


3. Legacy sugar is just like heavy sugar, just,
   say, requiring fewer tick marks on symbols.
   Legacy sugar is the most compatible with ASDL
   output from `--show-asr`.


All sugared forms produce identical full-forms.

----------------------------------------------------------------
## 6.1. NAMING CONVENTION FOR SUGAR


### 6.1.1. Light Sugar


The names of light-sugar functions, like
`Integer-`, have a single trailing hyphen. The
keyword arguments of light-sugar functions are
partitioned into _required_ and
_optional-with-defaults_. The keyword-argument
lists of light-sugar functions do not depend on
order. The following two examples of light sugar
both conform to specs registered for `::asr-term`
and to `::ttype` (this is an example of escaped
code that can't run yet because of narrative
order):


```clojure
#_(Integer- {:dimension* [], :kind 4})
#_(Integer- {:kind 4, :dimension* []})
```

### 6.1.2. Heavy Sugar


The names of heavy-sugar functions, like
`Integer` or `Variable--`, have either zero or
two trailing hyphens. The difference concerns
legacy. If legacy sugar exists for a term, the
legacy sugar has the name with no hyphens, like
`Variable`, and the heavy sugar has the name with
two hyphens, like `Variable--`. For example:

```clojure
#_(Variable-- 2 "x" (Integer 4)
            nil [] Local
            [] []  Default
            Source Public Required
            false)
```


Heavy sugar and legacy sugar employ positional
arguments that depend on order. Heavy-sugar
functions may have final arguments with defaults.
The following examples of heavy sugar all conform
to both `::asr-term` and to `::ttype`:


```clojure
#_(Integer)
#_(Integer 4)
#_(Integer 2 [])
#_(Integer 8 [[6 60] [1 42]])
```

### 6.1.3. Legacy Sugar


The purpose of legacy sugar is to convert symbols
to idempotent strings and to accommodate certain
defects in the original design of ASDL, such as
(1) nested lists' denoting improper function
calls and (2) ambiguity in `symbol-ref`,
sometimes a list and sometimes a naked pair.


Here is a legacy version of the `Variable` above:


```clojure
#_
(Variable 2 x [] ;; <~~~ unquoted `x`
          Local () ()
          Default (Integer 4 []) Source
          Public Required false)
```

Notice NO QUOTES on the name of the variable.
That's the way `--show-asr` prints it.


For specs like `Integer`, where heavy sugar and
legacy are identical, there is no function with
two trailing hyphens in its name.



# 7. WHAT ARE TERMS AND HEADS?



MASR _terms_ are models of ASDL _productions_.
ASDL productions are of the form:


`term = alternative_1 | alternative_2 | ...`


For example,

```c
cast_kind
= RealToInteger
| IntegerToReal
| LogicalToReal
| ...
```


MASR alternatives are called _heads_.



# 8. QUALIFIED KEYWORDS ARE FUNCTIONS & SPEC-NAMES



`::term` is both a qualified keyword _and_ a
tag-fetching function. A tag-fetching function
picks the value of the key `::term` from any
hash-map. For example,


`(::term {::term ::intent ...})`

calls the function `::term` with argument
`{::term ::intent ...}` and produces `::intent`.


As a qualified keyword, `::term`, in addition to
being a tag-fetching function, can name a Clojure
spec registered to it via `s/def`. The following
spec will check whether `::intent` is a `::term`:


```clojure
(s/def ::term qualified-keyword?)
```

EXAMPLE: `::intent` is a valid `::term` because
`::intent` is a qualified keyword.


```clojure
(s/valid? ::term ::intent)
;; => true
```


# 9. POLYMORPHIC SPECS FOR TERMS



`defmulti/defmethod` is one Clojure idiom for
_polymorphism_:, a single `defmulti` interface
with many `defmethod` implementations.


`(defmulti term ::term)` links the name
`term` (no colons) to the tag-fetcher
`::term` (with colons). Each `defmethod` of
`term` is tagged by the value fetched from an
entity via `::term`. For example, there is one
`defmethod` for `::symbol` and another for
`::expr`.;;

```clojure
(defmulti term ::term)
#_(defmethod term ::symbol [_] ,,,)
#_(defmethod term ::expr   [_] ,,,)
,,,
```

MASR handles _alternatives_ or _heads_ -- to the
right-hand sides of equals signs in ASDL -- via
_multi-specs_. Multi-specs are to specs as
`defmethods` are to functions -- one spec
interface to many implementations. Multi-specs
are thus another Clojure idiom for polymorphism.


The name of the _one_ multi-spec for all terms is
`::asr-term`, a qualified keyword, as are all
names of specs. Multi-specs act like tagged
unions in C -- MASR's polymorphic entities are
like polymorphic structs in C.



# 10. NESTED MULTI-SPECS



At the top level, `::term` multi-specs dispatch
on values of the `::term` key in entities, values
like `::intent`, `::symbol`, `::unit`, etc.
`defmethods` for those values specify the
remaining required keys for the particular
entities conforming to the particular spec named
by `::intent`, `::symbol`, `::unit`, etc.


Some `defmethods` like `::intent` are simple,
just checking that an instance like `"Local"` or
`"ReturnVar"` inhabits a set of allowed intents.
Other `defmethods`, like `::symbol`, have _nested
multi-specs_ that dispatch on _heads_, like
`Variable` or `Program`. MASR handles nested
multi-specs via techniques shown below.



# 11. NAMING CONVENTION FOR MULTI-SPECS



The names of all multi-specs in MASR, nested or
not, begin with `::asr-...`, as in
`::asr-term` (not nested) and
`::asr-ttype-head`, nested in `ttypes`, and
`::asr-expr-head`, nested in `expr`.


```clojure
(s/def ::asr-term
  (s/multi-spec term ::term))
```


# 12. TELESCOPING SPECS



A given entity (instance hash-map) may be

* an `::asr-term` -- any one of the terms

* a `::symbol` -- a particular one of the several
  terms,

* and a `::Variable` -- a particular one of the
  several heads or alternatives of `::symbol`.


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



# 13. TERM ENTITY KEY



Each term, like symbol, needs its own spec, named
by a qualified keyword like `::symbol`. MASR
recursively check specs when entity keys like
`::symbol` have their own specs or multi-specs.
Said another way, recursive conformance means
that `::symbol` fields in other entities are
checked by `::symbol` specs.


```clojure
(defn term-selector-spec
  "Name a spec that checks that an asr-term entity
  has a given kwd as the value, e.g., that the
  ::term of an entity is ::symbol."
  [kwd]
  (s/and ::asr-term
         #(= kwd (::term %))))


(defmacro def-term-entity-key
  "Define a spec for an entity key like ::symbol or
  ::expr, which is an ::asr-term, a top-level
  production in the ASDL grammar."
  [term]
  (let [ns "masr.specs"
        tkw (keyword ns (str term))]
    `(s/def ~tkw    ;; e.g. ::dimension or ::symbol
       (term-selector-spec ~tkw))))
```


# 14. DEFMASRNESTED



MASR automates construction of nested
multi-specs, removing duplicated wordage. The
docstring of `defmasrnested` shows an example.


**READ ALL DOCSTRINGS**


It is not necessary to understand the
implementation of `defmasrnested` unless you are
maintaining it. The macro is tricky to understand
due mostly to Clojure's implicit insertion and
deletion of namespaces in macros. Implicit
namespacing is a good design, overall, but we
must step around it when necessary via Clojure's
built-in `name` function.


```clojure
(defmacro defmasrnested
  "Automate constructions like the following, which
  pertain to certain ::term specs that have nested
  multi-specs like ::expr, ::symbol, ::stmt, etc.
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
         (def-term-entity-key ~tsym) ;; caution
         ;; e.g.    expr-head ::expr-head
         (defmulti ~esym       ~ekwd)
         ;; e.g. ::asr-expr-head
         (s/def ~akwd
           ;; e.g.        expr-head ::expr-head
           (s/multi-spec ~esym       ~ekwd))
         )))
```

## 14.1. Use of `defmasrnested`


```clojure
(defmasrnested expr)
(defmasrnested stmt)
(defmasrnested symbol)
(defmasrnested ttype)
(defmasrnested unit)
```


# 15. TERM-HEAD ENTITY KEY



We need specs for each nested multi-spec
like `::Variable` and `::FunctionType`.


```clojure
(defmacro def-term-head--entity-key
  "Define an entity key like ::Variable, which is an
  ::asr-symbol-head nested multi-spec, again
  eliminating duplicated wordage.

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
```


# 16. DEFMASRTYPE



`defmasrtype` is the easiest way to add new specs
that have nested multi-specs. Terms without
nested multi-specs are few. They are special
cases with hand-written specs.


`defmasrtype` creates both (1) the specs for
particular heads like `Variable` and `Assignment`,
and (2) a function, `->asdl-type`, that extracts
the ASDL type from any instance hash-map. We
present the extraction code first ("define" before
"use"):

----------------------------------------------------------------
## 16.1. EXTRACTING ASDL FROM MASR


```clojure
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
```
```clojure
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
```
```clojure
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
```


# 17. TO ASDL-TYPE



Undone Work-in-Progress


The function `->asdl-type` relies on multimethods
for nested multi-specs. The multimethods dispatch
on the _head_ keys of each multi-spec, keys like
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
```


# 18. ADD NEW DEFINITIONS HERE



Fill out implementations later.

----------------------------------------------------------------
## 18.1. UNIT


```clojure
(defmulti  unit->asdl-type ::unit-head)
(term->asdl-type unit)
```
```clojure
(defmasrtype
  TranslationUnit unit
  (SymbolTable  nodes))
```
----------------------------------------------------------------
## 18.2. SYMBOL


```clojure
(defmulti  symbol->asdl-type ::symbol-head)
(term->asdl-type symbol) ;; Don't expand in CIDER! console only.
```
```clojure
(defmasrtype
  Program symbol
  (SymbolTable  prognym  dependencies  body))
```
```clojure
(defmasrtype
  Module symbol
  (SymbolTable
   modulenym  dependencies  loaded-from-mod
   intrinsic))
```
```clojure
(defmasrtype
  Function symbol
  (SymbolTable ;; not a symtab-id!
   function-name  function-signature  dependencies
   param*         body                return-var?
   access         deterministic       side-effect-free
   ))
```
```clojure
(defmasrtype
  GenericProcedure symbol
  (symtab-id  function-name  symbol-ref*
   access))
```
```clojure
(defmasrtype
  ExternalSymbol symbol
  (symtab-id
   nym        extern-symref
   modulenym  scope-nyms     orig-nym
   access))
```
```clojure
(defmasrtype
  Variable symbol
  (symtab-id
   varnym              dependencies
   intent              symbolic-value    value?
   storage-type        ttype             abi
   access              presence          value-attr
   type-declaration))
```
```clojure
(defmasrtype
  Block symbol
  (SymbolTable
   blocknym
   body))
```
```clojure
;; not in ASDL
(defmasrtype
  IntrinsicModule symbol
  (modulenym))
```
----------------------------------------------------------------
## 18.3. STMT


```clojure
(defmulti  stmt->asdl-type ::stmt-head)
(term->asdl-type stmt)   ;; CIDER macro-expand removes namespace.
```
```clojure
(defmasrtype
  Assignment stmt
  ;; types of the attributes:
  (lvalue  rvalue  overloaded))
```
```clojure
(defmasrtype
  ExplicitDeallocate stmt
  (vars))
```
```clojure
(defmasrtype
  DoLoop stmt
  (escape-target ;; UNCHECKED! NO SPEC!
   do-loop-head ;; NOT AN ASR HEAD!
   body))
```
```clojure
(defmasrtype
  GoTo stmt
  (goto-target identifier))
```
```clojure
(defmasrtype
  If stmt
  (test-expr body orelse))
```
```clojure
(defmasrtype
  Print stmt
  (format? value* separator? end?))
```
```clojure
(defmasrtype
  Return stmt
  ())
```
```clojure
(defmasrtype
  Assert stmt
  (test-expr message?))
```
```clojure
(defmasrtype
  SubroutineCall stmt
  (symbol-ref  orig-symref  call-args  dt?))
```
```clojure
(defmasrtype
  Block stmt
  (label symbol-ref))
```
```clojure
(defmasrtype
  WhileLoop stmt
  (escape-target  test-expr  body))
```
```clojure
(defmasrtype
  BlockCall stmt
  (label
   symbol-ref))
```
```clojure
(defmasrtype
  ListAppend stmt
  (list-expr  list-element))
```
----------------------------------------------------------------
## 18.4. EXPR


```clojure
(defmulti  expr->asdl-type ::expr-head)
(term->asdl-type expr)
```
```clojure
(defmasrtype
  IfExp expr
  (test-expr body orelse ttype value?))
```
```clojure
(defmasrtype
  NamedExpr expr
  (target value ttype))
```
```clojure
(defmasrtype
  FunctionCall expr
  (symbol-ref  orig-symref  call-args
               return-type  value?    dt?))
```
```clojure
(defmasrtype
  IntrinsicFunction expr
  (intrinsic-ident  expr*        overload-id
   return-type      value?))
```
```clojure
(defmasrtype
  IntegerConstant expr
  (int    Integer))
```
```clojure
(defmasrtype
  IntegerBitNot expr
  (integer-expr, Integer, integer-value?))
```
```clojure
(defmasrtype
  IntegerUnaryMinus expr
  (integer-expr  Integer  integer-value?))
```
```clojure
(defmasrtype
  IntegerCompare expr
  (integer-left  integer-cmpop   integer-right
                 Logical         logical-value?))
```
```clojure
(defmasrtype
  IntegerBinOp expr
  (integer-left  integer-binop   integer-right
                 Integer         integer-value?))
```
```clojure
(defmasrtype
  RealConstant expr
  (float  Real))
```
```clojure
(defmasrtype
  RealUnaryMinus expr
  (real-expr, Real, real-value?))
```
```clojure
(defmasrtype
  RealCompare expr
  (real-left     real-cmpop    real-right
                 Logical       logical-value?))
```
```clojure
(defmasrtype
  RealBinOp expr
  (real-left  real-binop  real-right
   Real       real-value?))
```
```clojure
(defmasrtype
  ComplexConstant expr
  (real-part  imaginary-part  Complex))
```
```clojure
(defmasrtype
  ComplexUnaryMinus expr
  (complex-expr  Complex  complex-value?))
```
```clojure
(defmasrtype
  ComplexCompare expr
  (complex-left  complex-cmpop complex-right
   Logical       logical-value?))
```
```clojure
(defmasrtype
  ComplexBinOp expr
  (complex-left  complex-binop  complex-right
   Complex       complex-value?))
```
```clojure
(defmasrtype
  LogicalConstant expr
  (bool  Logical))
```
```clojure
(defmasrtype
  LogicalNot expr
  (logical-expr  Logical  logical-value?))
```
```clojure
(defmasrtype
  LogicalCompare expr
  (logical-left  logical-cmpop  logical-right
   Logical       logical-value?))
```
```clojure
(defmasrtype
  LogicalBinOp expr
  (logical-left  logicalbinop  logical-right
   Logical       logical-value?))
```
```clojure
(defmasrtype
  ListConstant expr
  (expr*  ttype))
```
```clojure
(defmasrtype
  ListLen expr
  (list-expr  Integer  integer-value?))
```
```clojure
(defmasrtype
  TupleConstant expr
  (elements  ttype))
```
```clojure
(defmasrtype
  TupleLen expr
  (tuple-expr  Integer  integer-value?))
```
```clojure
(defmasrtype
  TupleCompare expr
  (tuple-left  any-cmpop  tuple-right
   Logical     logical-value?))
```
```clojure
(defmasrtype
  StringConstant expr
  (string  Character))
```
```clojure
(defmasrtype
  StringCompare expr
  (string-left  string-cmpop  string-right
   Logical      logical-value?))
```
```clojure
(defmasrtype
  StringRepeat expr
  (string-expr  integer-expr  Character
   string-expr?))
```
```clojure
(defmasrtype
  StringLen expr
  (string-expr Integer integer-value?))
```
```clojure
(defmasrtype
  StringItem expr
  (string-expr index?
               Integer
               integer-value?))
```
```clojure
(defmasrtype
  StringSection expr
  (string-expr index-start?
               index-end?
               index-step?
               Character
               string-value?))
```
```clojure
(defmasrtype
  StringOrd expr
  (string-expr  Integer  integer-value?))
```
```clojure
(defmasrtype
  StringChr expr
  (string-expr  Character  string-value?))
```
```clojure
(defmasrtype
  Var expr
  (symtab-id  varnym))
```
```clojure
(defmasrtype
  ArrayConstant expr
  (expr*  ttype  array-storage))
```
```clojure
(defmasrtype
  ArrayItem expr
  (array-expr     array-index*  ttype
   array-storage  expr?))
```
```clojure
(defmasrtype
  ArrayReshape expr
  (array-expr  array-shape  ttype
   array-value?))
```
```clojure
(defmasrtype
  Cast expr
  (arg  cast-kind  ttype  value?))
```
```clojure
(defmasrtype
  ListItem expr
  (list-expr  index  ttype  value?))
```
```clojure
(defmasrtype
  TupleItem expr
  (tuple-expr  index  ttype  value?))
```
```clojure
(defmasrtype
  ComplexRe expr
  (complex-expr  Real  real-value?))
```
```clojure
(defmasrtype
  ComplexIm expr
  (complex-expr  Real  real-value?))
```
----------------------------------------------------------------
## 18.5. TTYPE


```clojure
(defmulti  ttype->asdl-type ::ttype-head)
(term->asdl-type ttype)
```
```clojure
(defmasrtype
  Complex ttype
  ;; types of the attributes:
  (complex-kind  dimension*))
```
```clojure
(defmasrtype
  Integer ttype
  (integer-kind  dimension*))
```
```clojure
(defmasrtype
  Logical ttype
  (logical-kind  dimension*))
```
```clojure
(defmasrtype
  Real ttype
  (real-kind  dimension*))
```
```clojure
(defmasrtype
  Tuple ttype
  (ttype*))
```
```clojure
(defmasrtype
  List ttype
  (ttype))
```
```clojure
(defmasrtype
  Set ttype
  (ttype))
```
```clojure
(defmasrtype
  Character ttype
  (character-kind  len         disposition
   len-expr?       dimension*))
```
```clojure
(defmasrtype
  FunctionType ttype
  (param-type*
   return-var-type?   abi
   deftype            bindc-name     elemental
   pure               module         inline
   static             type-param*    restrictions
   is-restriction))
```


# 19. REWRITING FOR LEGACY



TODO: ASDL output from `--show-asr` currently
requires moving colons from the backs of keywords
to the front. That is necessary because colons at
the back fail the Clojure reader. We have a `sed`
script for moving colons: `fix-show-asr.sed`. The
script also replaces illegal characters like `@`
and `~` and converts ASDL's `.false.` to `false`
and `.true.` to `true`.


Here is `fix-show-asr.sed`:


```sed
s/\~/__TILDE__/g
s/\@/__AT__/g
s/\(\([_a-zA-Z0-9]*\)\:\)/:\2/g
s/\.\(false\)\./\1/g
s/\.\(true\)\./\1/g
```


The function `rewrite-for-legacy` converts `=`
into `Assignment` in a whole tree and converts
unwanted nested call syntax into vectors. `Eval`,
in the namespace `masr.specs`, applies all sugar
functions to an expression. Call `to-full-form`
to do both. The `legacy` macro simply quotes a
whole sugared expression before feeding it to
`to-full-form`.

```clojure
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
```
```clojure
(defn to-full-form
  [sexp]
  (do (in-ns 'masr.specs)
      (eval (rewrite-for-legacy sexp))))
```
```clojure
(defmacro legacy
  "DANGER: Call 'eval'."
  [it]
  `(to-full-form '~it))
```


# 20. IMPLEMENTATIONS



The remaining sections of this document describe
detailed implementations of the sugar functions
for the `defmasrtype`s.



# 21. CALL-ARG



## 21.1. Issues

https://github.com/rebcabin/masr/issues/32
`call-arg` intentionally introduces a level of
nesting to a list of actual arguments to a
function call or subroutine call. We spec the
extra nesting as a collection of length one.


## 21.2. Original ASDL

```c
call_arg = (expr? value)
```


```clojure
(s/def ::call-arg
  (s/coll-of ::expr?
             :min-count 1   ;; Issue 32
             :max-count 1))

(s/def ::call-args (s/coll-of ::call-arg))

(s/def ::call-arg-or-args
  (s/or :call-arg  ::call-arg
        :call-args ::call-args))
```

## 21.3. Examples


Examples can't be executed until `expr?` is
defined. See the first example in in
`SubroutineCall.`



# 22. DIMENSION



`Dimension` is a term without nested multi-specs.
It is a handwritten special case, not defined via
`defmasrtype`.


## 22.1. Original ASDL

```c
dimension = (expr? start, expr? length)
```


The ASDL is imprecise. The real spec, realized
only in secret C++ code, is that we have either
both `start` and `length` or we have nothing.
MASR makes exposes this secret explicitly.


## 22.2. Dimension-Content



The next spec says that a `::dimension-content` is
a collection of `::nat` with either two or zero
elements.


```clojure
(def MIN-DIMENSION-COUNT 0)
(def MAX-DIMENSION-COUNT 2)

(s/def ::dimension-content
  (s/and
   (s/coll-of ::nat
              :min-count MIN-DIMENSION-COUNT,
              :max-count MAX-DIMENSION-COUNT,
              :into [])
   (fn [it] (not (= 1 (count it))))))
```

## 22.3. Full-Form



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

This spec can generate samples. TODO: Samples are
not fleshed out in general.


```clojure
#_
(gen/sample (s/gen ::dimension-content) 3)

;; => (() (0 0) (1 1))
```

## 22.4. Heavy Sugar


```clojure
(defn dimension [candidate-contents]
  (if (or (not (coll? candidate-contents))
          (set? candidate-contents)
          (map? candidate-contents))
    ::invalid-dimension ;; return this,
    ;; else
    {::term ::dimension,
     ;; `vec` for idempotency
     ::dimension-content (vec candidate-contents)}
    ))
```


# 23. DIMENSION*



Convert lists to vectors on-the-fly.

```clojure
(s/def ::dimension*
  (s/coll-of (term-selector-spec ::dimension),
             :into []))
```

Generation of test cases does not currently work
TODO https://github.com/rebcabin/masr/issues/14


```clojure
#_(gen/sample (s/gen ::dimension*) 3)
```

## 23.1. Heavy Sugar


```clojure
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
          ;; `vec` for idempotency
          (vec (map dimension dims-cont)))
        ::invalid-dimension*))))
```


# 24. SYMTAB-ID



In ASDL, `symbol_table` sometimes means a
`SymbolTable` and sometimes means an integer id
of a `SymbolTable` that is specified elsewhere.
MASR does better. MASR `->asdl-type` projects
both of these types, `SymbolTable` and
`symtab-id`, back into ASDL `symbol_table`, with
its secret proviso. MASR exposes the secret,
whilst ASDL embraces the secret.


```clojure
(s/def ::symtab-id ::nat)
```

## 24.1. Heavy Sugar


```clojure
(defn symtab-id [it] it)
```


# 25. SYMBOL TABLE



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

## 25.1. Heavy Sugar


```clojure
(defn SymbolTable [id, hash-map]
  {::term      ::SymbolTable
   ::symtab-id id
   ::hash-map  hash-map})
```


# 26. ENUM-LIKE



Many ASDL types are like enums: a set of
alternative symbols, without parentheses and
without parameters _qua_ arguments. Example: ASDL
`access` has two possibilities: `Public` and
`Private`. MASR automates all enum-likes via
one macro, `enum-like`.


## 26.1. Helpers for Enum-Like


```clojure
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
```
----------------------------------------------------------------
## 26.2. Enum-Like, Proper


```clojure
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
```

## 26.3. Most Enum-Likes


```clojure
(def logical-binops #{"And"  "Or"  "Xor"  "NEqv"  "Eqv"})
(def real-binops    #{"RAdd" "RSub" "RMul" "RDiv" "RPow"})
(def complex-binops #{"CAdd" "CSub" "CMul" "CDiv" "CPow"})
(def integer-binops #{"Add" "Sub" "Mul" "Div" "Pow"
                      "BitAnd" "BitOr" "BitXor"
                      "BitLShift" "BitRShift"})
```
```clojure
(def logical-cmpops #{"LEq" "LNotEq"
                      ;; some weird ones: see Issue #38
                      "LLt" "LLtE" "LGt" "LGtE"})
(def real-cmpops    #{"REq" "RNotEq" "RLt" "RLtE" "RGt" "RGtE"})
(def complex-cmpops #{"CEq" "CNotEq"})
(def integer-cmpops #{"Eq" "NotEq" "Lt" "LtE" "Gt" "GtE"})
(def string-cmpops  #{"SEq" "SNotEq" "SLt" "SLtE" "SGt" "SGtE"})
(def all-cmpops     (set/union logical-cmpops
                               real-cmpops
                               complex-cmpops
                               integer-cmpops
                               string-cmpops))
```

Collisions of names are NOT ALLOWED!
See Legacy Sugar for `RealBinOp.`

```clojure
(enum-like logicalbinop  logical-binops)
(enum-like real-binop    real-binops)
(enum-like complex-binop complex-binops)
(enum-like integer-binop integer-binops)

(s/def ::any-binop (set/union logical-binops
                              real-binops
                              complex-binops
                              integer-binops))
```

Collisions of names are NOT ALLOWED!
See Legacy Sugar for `LogicalCompare.`

```clojure
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
```
```clojure
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
```
----------------------------------------------------------------
## 26.4. Abi



`Abi` is a special case of enum-like with rich logic.

```clojure
(def external-abis
  #{"LFortranModule", "GFortranModule",
    "BindC", "Interactive", "Intrinsic"})

(def internal-abis #{"Source"})

(s/def ::abi-enum (set/union external-abis internal-abis))

(s/def ::abi-external ::bool)
```

### 26.4.1. Full-Form


```clojure
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
```

### 26.4.2. Heavy Sugar


```clojure
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
```

### 26.4.3. The ABIs


```clojure
(def LFortranModule (abi "LFortranModule" :external true))
(def GFortranModule (abi "GFortranModule" :external true))
(def BindC          (abi "BindC"          :external true))
(def Interactive    (abi "Interactive"    :external true))
(def Intrinsic      (abi "Intrinsic"      :external true))
(def Source         (abi "Source"         :external false))
```


# 27. TTYPE



`Ttype` is a term with nested multi-specs.

----------------------------------------------------------------
## 27.1. Prerequisite Types and Aliases


```clojure
(s/def ::ttype* (s/coll-of ::ttype))
```
```clojure
(defmacro .? [thing]
  `(s/or :thing ~thing
         :empty empty?))

(defmacro .* [thing]
  `(s/coll-of ~thing))

(s/def ::ttype?           (.? ::ttype))
```
```clojure
(s/def ::param-type*          ::ttype*)
(s/def ::return-var-type?     ::ttype?)
```
```clojure
(s/def ::bindc-name           (s/nilable string?))
(s/def ::elemental            ::bool)
(s/def ::pure                 ::bool)
(s/def ::module               ::bool)
(s/def ::inline               ::bool)
(s/def ::static               ::bool)
(s/def ::type-param*          ::ttype*)
```
```clojure
(s/def ::symbols          (.* ::symbol))
```
```clojure
(s/def ::symbol?          (.? ::symbol))
(s/def ::restrictions         ::symbols)
(s/def ::is-restriction       ::bool)
```
```clojure
(s/def ::expr*            (.* ::expr))
```
```clojure
(s/def ::expr?            (.? ::expr))
```

### 27.1.1. For Loop Statements

```clojure
(s/def ::loop-v               ::expr?) ;; TODO: ?
(s/def ::loop-start           ::expr?)
(s/def ::loop-end             ::expr?)
(s/def ::loop-increment       ::expr?)

(s/def ::do-loop-head
  (s/keys :req [::loop-v
                ::loop-start
                ::loop-end
                ::loop-increment]))
```

### 27.1.2. For Character

```clojure
(s/def ::len         ::int)   ;; Issues #36
(s/def ::disposition #{"compile-time-length"   ;; >= 0
                       "inferred-at-run-time"  ;; = -1
                       "allocatable"           ;; = -2
                       "run-time-expression"}) ;; = -3
(s/def ::len-expr?   ::expr?) ;; TODO: check that it's >= 0
```
----------------------------------------------------------------
## 27.2. Kind



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

----------------------------------------------------------------
## 27.3. Support Specs For Kinds


```clojure
(s/def ::integer-kind        #{1 2 4 8 16})
(s/def ::real-kind           #{4 8})
(s/def ::complex-kind        #{4 8})
(s/def ::logical-kind        #{1 2 4})
(s/def ::character-kind      #{1})
```
----------------------------------------------------------------
## 27.4. Heavy Sugar for `ttype`


```clojure
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
```
----------------------------------------------------------------
## 27.5. Sugar for the Kinds


```clojure
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
```
----------------------------------------------------------------
## 27.6. INTEGER, REAL, COMPLEX, LOGICAL


See also `defmasrtypes` at top of the file.
```clojure
(def-ttype-and-head Integer)
(def-ttype-and-head Real)
(def-ttype-and-head Complex)
(def-ttype-and-head Logical)
```
----------------------------------------------------------------
## 27.7. CHARACTER



### 27.7.1. Original ASDL

```c
| Character(int kind, int len, expr? len_expr, dimension* dims)
```

### 27.7.2. Example


```clojure
#_
(Character 1 1 () [])
```

### 27.7.3. Heavy Sugar


```clojure
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
```
----------------------------------------------------------------
## 27.8. TUPLE

```clojure
(defn Tuple [ttypes]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::Tuple
    ::ttype*     ttypes}})
```
----------------------------------------------------------------
## 27.9. List

```clojure
(defn List [ttype]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::List
    ::ttype      ttype}})
```
----------------------------------------------------------------
## 27.10. Set

```clojure
(defn Set [ttype]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::Set
    ::ttype      ttype}})
```
----------------------------------------------------------------
## 27.11. FUNCTION-TYPE



This is a rich `ttype` that we spell out by hand.


### 27.11.1. Original ASDL

```c
| FunctionType(ttype*  arg_types,       ;; rename param-type*
               ttype?  return_var_type,
               abi     abi,
               deftype deftype,
               string? bindc_name,
               bool    elemental,
               bool    pure,
               bool    module,
               bool    inline,
               bool    static,
               ttype*  type_param*,
               symbol* restrictions,
               bool    is_restriction)
```

### 27.11.2. Heavy Sugar


```clojure
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
```
----------------------------------------------------------------
## 27.12. TODO The Rest of the `ttypes`


### 27.12.1. Original ASDL

```c
>>> Integer, Real, Complex, Logical are already done ...
>>> FunctionType is done.
>>> Here are the rest of the ttypes.
| Character(int kind, int len, expr? len_expr, dimension* dims)
>>> Set(ttype type)
>>> List(ttype type)
>>> Tuple(ttype* type)
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




# 28. PLACEHOLDERS



things we haven't fully defined yet

----------------------------------------------------------------
## 28.1. ESCAPE TARGET


```clojure
(s/def ::escape-target empty?)
```
----------------------------------------------------------------
## 28.2. SYMBOLIC VALUE


```clojure
(s/def ::symbolic-value empty?)
```

### 28.2.1. Sugar


```clojure
(def symbolic-value identity)
```


# 29. EXPR


----------------------------------------------------------------
## 29.1. Prerequisite Types and Aliases


```clojure
(s/def ::arg ::expr)
```
```clojure
(s/def ::value  ::expr)
(s/def ::value? ::expr?)
(s/def ::target ::expr)
```
```clojure
(s/def ::IntegerConstant? (.? ::IntegerConstant))
```
```clojure
(s/def ::symbol-ref
  (s/keys :req [::identifier
                ::symtab-id]))
(s/def ::symbol-ref?      (.? ::symbol-ref))
(s/def ::symbol-ref*      (.* ::symbol-ref))
(s/def ::extern-symref        ::symbol-ref?)
(s/def ::orig-symref          ::symbol-ref?)
```
```clojure
(defn symbol-ref [ident, stid]
  {::identifier ident,
   ::symtab-id  stid})
```
```clojure
(s/def ::return-type          ::ttype)
```
```clojure
(s/def ::varnym               ::identifier)
```
----------------------------------------------------------------
### 29.1.1. Unchecked Element Types



The following represent types of elements of
collections. TODO: MASR does not currently check
them because checking them requires checking that
the type of the element matches types stored
along with the collection. The cross-logic to
check these types must be implemented in each
collection type


```clojure
(s/def ::unchecked-element-expr
  (s/or :array-item           ::ArrayItem ;; TODO check return type!
        :tuple-item           ::TupleItem ;; TODO check return type!
        :list-item            ::ListItem  ;; TODO check return type!
        :cast                 ::Cast      ;; TODO check return type!
        :if-expr              ::IfExp     ;; TODO check return type!
        :named-expr           ::NamedExpr ;; TODO check return type!
        :var                  ::Var       ;; TODO check return type!
))
```
----------------------------------------------------------------
### 29.1.2. Logical Types


```clojure
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
```
```clojure
(s/def ::logical-expr?    (.? ::logical-expr))
(s/def ::logical-value?       ::logical-expr?)

(s/def ::logical-left         ::logical-expr)
(s/def ::logical-right        ::logical-expr)
```
----------------------------------------------------------------
### 29.1.3. Integer Types


```clojure
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
```
```clojure
(s/def ::integer-expr?    (.? ::integer-expr))
(s/def ::integer-value?       ::integer-expr?)

(s/def ::integer-left         ::integer-expr)
(s/def ::integer-right        ::integer-expr)
```
----------------------------------------------------------------
### 29.1.4. Index Types


```clojure
(s/def ::index                ::integer-expr)
(s/def ::index-start          ::integer-expr)
(s/def ::index-end            ::integer-expr)
(s/def ::index-step           ::integer-expr)

(s/def ::index?               ::integer-expr?)
(s/def ::index-start?         ::integer-expr?)
(s/def ::index-end?           ::integer-expr?)
(s/def ::index-step?          ::integer-expr?)
```
----------------------------------------------------------------
### 29.1.5. Real Types


```clojure
(s/def ::real-expr
  (s/or :real-constant        ::RealConstant
        :real-binop           ::RealBinOp
        :real-unary-minus     ::RealUnaryMinus
        :unchecked            ::unchecked-element-expr))
```
```clojure
(s/def ::real-expr?       (.? ::real-expr))
(s/def ::real-value?          ::real-expr?)

(s/def ::real-left            ::real-expr)
(s/def ::real-right           ::real-expr)
```
----------------------------------------------------------------
### 29.1.6. Complex Types


```clojure
(s/def ::complex-expr
  (s/or :complex-constant     ::ComplexConstant
        :complex-binop        ::ComplexBinOp
        :complex-unary-minus  ::ComplexUnaryMinus
        :unchecked            ::unchecked-element-expr))
```
```clojure
(s/def ::real-part            ::float)
(s/def ::imiginary-part       ::float)

(s/def ::complex-left         ::complex-expr)
(s/def ::complex-right        ::complex-expr)
```
```clojure
(s/def ::complex-expr?    (.? ::complex-expr))
(s/def ::complex-value?       ::complex-expr?)

(s/def ::complex-left         ::complex-expr)
(s/def ::complex-right        ::complex-expr)
```
----------------------------------------------------------------
### 29.1.7. Array Types


```clojure
(s/def ::array-expr
  (s/or :var                    ::Var
        :array-constant         ::ArrayConstant
        :unchecked              ::unchecked-element-expr))
```
TODO: `array-shape` and `array-value?` are
work-in-progress:
```clojure
(s/def ::array-shape            ::expr)
(s/def ::array-value?           ::expr?)
```
```clojure
(s/def ::array-index-start?     ::integer-expr?)
(s/def ::array-index-end?       ::integer-expr?)
(s/def ::array-index-increment? ::integer-expr?)

(s/def ::array-index
  (s/keys :req [::array-index-start?
                ::array-index-end?
                ::array-index-increment?]))

(s/def ::array-index*       (.* ::array-index))
```
----------------------------------------------------------------
### 29.1.8. List Types

```clojure
(s/def ::list-expr
  (s/or :list-constant          ::ListConstant
        :var                    ::Var
        :unchecked              ::unchecked-element-expr)  )
```
```clojure
(s/def ::list-element
  (s/or :expr                   ::expr))
```
----------------------------------------------------------------
### 29.1.9. Tuple Types

```clojure
(s/def ::tuple-expr
  (s/or :tuple-constant         ::TupleConstant
        :unchecked              ::unchecked-element-expr))
(s/def ::tuple-left             ::tuple-expr)
(s/def ::tuple-right            ::tuple-expr)
```
```clojure
(s/def ::elements               ::expr*)
```
----------------------------------------------------------------
### 29.1.10. String Types


```clojure
(s/def ::string-expr
  (s/or :string-constant        ::StringConstant
        :string-item            ::StringItem
        :string-chr             ::StringChr
        :string-section         ::StringSection
        :string-repeat          ::StringRepeat
        :unchecked              ::unchecked-element-expr))
```
```clojure
(s/def ::string-expr?       (.? ::string-expr))
(s/def ::string-value?          ::string-expr?)
```
For `IntrinsicFunction`:
```clojure
(s/def ::intrinsic-ident        ::identifier)
(s/def ::overload-id            ::nat)
```
----------------------------------------------------------------
## 29.2. IF EXP



### 29.2.1. Original ASDL

```c
 IfExp(expr test,
       expr body,
       expr orelse,
       ttype type,
       expr? value)
```

### 29.2.2. Example


```clojure
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
```

### 29.2.3. Heavy Sugar


```clojure
(defn IfExp [test-expr, body, orelse, ttype, value?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head ::IfExp
    ::test-expr test-expr
    ::body      body
    ::orelse    orelse
    ::ttype     ttype
    ::value?    value?}})
```
----------------------------------------------------------------
## 29.3. INTEGER BIT NOT



### 29.3.1. Original ASDL

```c
IntegerBitNot(expr arg, ttype type, expr? value)
```

### 29.3.2. Heavy Sugar


```clojure
(defn IntegerBitNot
  [iarg, ittype, ivalue?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head ::IntegerBitNot
    ::integer-expr   iarg
    ::Integer        ittype
    ::integer-value? ivalue?}})
```
----------------------------------------------------------------
## 29.4. INTEGER, REAL, COMPLEX UNARY MINUS



### 29.4.1. Typed Uminus Macro

```clojure
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
```
### 29.4.2. Using the Macro
```clojure
(typed-uminus Integer)
(typed-uminus Real)
(typed-uminus Complex)
```
----------------------------------------------------------------
## 29.5. NAMED EXPR



### 29.5.1. Original ASDL

```c
| NamedExpr(expr target, expr value, ttype type)
```

### 29.5.2. Example

```clojure
#_
(NamedExpr
 (Var 2 y)
 (IntegerConstant 0 (Integer 4 []))
 (Integer 4 [])    )
```

### 29.5.3. Heavy Sugar


```clojure
(defn NamedExpr [target value ttype]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head ::NamedExpr
    ::target target
    ::value  value
    ::ttype  ttype}})
```
----------------------------------------------------------------
## 29.6. FUNCTION CALL



### 29.6.1. Original ASDL

```c
| FunctionCall(symbol     name,
               symbol?    original_name,
               call_arg * args,
               ttype      type,
               expr     ? value,
               expr     ? dt)
```


### 29.6.2. Example

```clojure
#_
(FunctionCall
 7 g
 ()
 []
 (Integer 4 [])
 ()
 () )
```

### 29.6.3. Heavy Sugar

```clojure
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
```

### 29.6.4. Legacy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.7. INTRINSIC FUNCTION



### 29.7.1. Original ASDL

```c
IntrinsicFunction(int    intrinsic_id,
                  expr * args,
                  int    overload_id,
                  ttype  type,
                  expr?  value)
```


### 29.7.2. Example

```clojure
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
```

### 29.7.3. Heavy Sugar

```clojure
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
```

### 29.7.4. Legacy Sugar

```clojure
(defmacro IntrinsicFunction
  "Stringulate the intrinsic identifier."
  [intrinsic-ident    expr*    overload-id
   return-type        value?]
  (let [i_ident (str intrinsic-ident)]
    `(IntrinsicFunction--
      ~i_ident,     ~expr*, ~overload-id,
      ~return-type, ~value?)))
```
----------------------------------------------------------------
## 29.8. LOGICAL, INTEGER, REAL CONSTANTS



To reduce code duplication, we want to write
something like the following automatically for
Logical, Integer, and Real. String is a special
case because its ttype is Character and not
String. Complex is a special case because it
takes two Real inputs. Write those by hand.


### 29.8.1. Typed Constant Macro

```clojure
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
```

### 29.8.2. Using the Macro

```clojure
(typed-constant Logical bool)
(typed-constant Real    float)
(typed-constant Integer int)
```
----------------------------------------------------------------
## 29.9. STRING CONSTANT



### 29.9.1. Original ASDL

```c
| StringConstant(string s, ttype type)
```

### 29.9.2. Example

```clojure
#_
(StringConstant "3" (Character 1 1 () []))
```

### 29.9.3. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.10. STRING REPEAT



### 29.10.1. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.11. COMPLEX CONSTANT



### 29.11.1. Original ASDL

```c
ComplexConstant(float re, float im, ttype type)
```


### 29.11.2. Example

```clojure
#_
(ComplexConstant 3.000000 4.000000 (Complex 8 []))
```

### 29.11.3. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.12. VAR



### 29.12.1. Issue #23

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

### 29.12.2. Heavy Sugar

```clojure
(defn Var-- [stid, ident]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head  ::Var
    ::symtab-id  stid
    ::varnym     (str ident)
    }})
```

### 29.12.3. Legacy Sugar

```clojure
(defmacro Var [stid, unquoted-ident]
  (let [i_ident (str unquoted-ident)]
   `(Var-- ~stid ~i_ident)))
```

TODO: make Var look up a value in the
29.13. symbol-table! That's part of abstract execution.
----------------------------------------------------------------
## 29.14. ARRAY CONSTANT


```clojure
(defn ArrayConstant [args, ttype, arraystorage]
  {::term ::expr
   ::asr-expr-head
   {::expr-head ::ArrayConstant
    ::expr*           args
    ::ttype           ttype
    ::array-storage   arraystorage
    }})
```
----------------------------------------------------------------
## 29.15. ARRAY ITEM



### 29.15.1. Example

```clojure
#_
(ArrayItem
 (Var 186 b)
 [(()
   (Var 186 k)
   ())]
 (Real 8 [])
 RowMajor
 ())
```

### 29.15.2. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.16. ARRAY RESHAPE



### 29.16.1. Example

```clojure
#_
(ArrayReshape
 (Var 186 b)
 (Var 186 newshape)
 (Real 8 [(() ())])
 ())
```

### 29.16.2. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.17. STRING CHR



### 29.17.1. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.18. STRING LEN



### 29.18.1. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.19. STRING ITEM



### 29.19.1. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.20. STRING SECTION



### 29.20.1. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.21. STRING ORD



### 29.21.1. Original ASDL

```c
| StringOrd(expr arg, ttype type, expr? value)
```

### 29.21.2. Example

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

### 29.21.3. Legacy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.22. INTEGER BINOP



### 29.22.1. Original ASDL

```c
| IntegerBinOp(expr  left,
               binop op,
               expr  right,
               ttype type,
               expr? value)
```

### 29.22.2. Example

```clojure
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
```

### 29.22.3. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.23. REAL BINOP



### 29.23.1. Original ASDL

```c
| RealBinOp(expr  left,
               binop op,
               expr  right,
               ttype type,
               expr? value)
```

### 29.23.2. Example

```clojure
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
```

### 29.23.3. Heavy Sugar

```clojure
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
```

### 29.23.4. Legacy Sugar

```clojure
(defmacro RealBinOp
  "Must use Add, Mul, etc."
  [left- bo- right- rtt- rval?-]
  (let [rop (symbol (str "R" bo-))]
    `(RealBinOp-- ~left- ~rop ~right- ~rtt- ~rval?-)))
```
----------------------------------------------------------------
## 29.24. COMPLEX BINOP



### 29.24.1. Original ASDL

```c
| ComplexBinOp(expr  left,
               binop op,
               expr  right,
               ttype type,
               expr? value)
```

### 29.24.2. Heavy Sugar

```clojure
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
```

### 29.24.3. Legacy Sugar

```clojure
(defmacro ComplexBinOp
  "Must use Add, Mul, etc."
  [left- bo- right- ctt- cval?-]
  (let [rop (symbol (str "C" bo-))]
    `(ComplexBinOp-- ~left- ~rop ~right- ~ctt- ~cval?-)))
```
----------------------------------------------------------------
## 29.25. LOGICAL BINOP



### 29.25.1. Original ASDL

```c
| LogicalBinOp(expr left, logicalbinop op, expr
  right, ttype type, expr? value)
```

### 29.25.2. Example


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

### 29.25.3. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.26. LIST CONSTANT



### 29.26.1. Heavy Sugar

```clojure
(defn ListConstant [expr* ttype]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::ListConstant
    ::expr*          expr*
    ;; TODO: check that all exprs have the ttype
    ::ttype          ttype
    }})
```
----------------------------------------------------------------
## 29.27. LIST LEN



### 29.27.1. Heavy Sugar

```clojure
(defn ListLen [list-expr int-ttype int-val?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::ListLen
    ::list-expr      list-expr
    ::Integer        int-ttype
    ::integer-value? int-val?
    }})
```
----------------------------------------------------------------
## 29.28. TUPLE CONSTANT



### 29.28.1. Heavy Sugar

```clojure
(defn TupleConstant [elements ttype]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::TupleConstant
    ::elements       elements
    ::ttype          ttype
    }})
```
----------------------------------------------------------------
## 29.29. TUPLE LEN



### 29.29.1. Heavy Sugar

```clojure
(defn TupleLen [tuple-expr int-ttype int-val?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::TupleLen
    ::tuple-expr     tuple-expr
    ::Integer        int-ttype
    ::integer-value? int-val?
    }})
```
----------------------------------------------------------------
## 29.30. TUPLE COMPARE



### 29.30.1. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 29.31. COMPLEX RE

```clojure
(defn ComplexRe [cexpr, rtt, rv?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::ComplexRe
    ::complex-expr   cexpr
    ::Real           rtt
    ::real-value?    rv? ;; TODO: Check arithmetic!
    }})
```
----------------------------------------------------------------
## 29.32. COMPLEX IM

```clojure
(defn ComplexIm [cexpr, rtt, rv?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::ComplexIm
    ::complex-expr   cexpr
    ::Real           rtt
    ::real-value?    rv? ;; TODO: Check arithmetic!
    }})
```
----------------------------------------------------------------
## 29.33. INTEGER COMPARE



### 29.33.1. Original ASDL

```c
| IntegerCompare(expr  left,
                 cmpop op,
                 expr  right,
                 ttype type,
                 expr? value)
```

### 29.33.2. Example

```clojure
#_
(IntegerCompare
 (Var 4 z)
 Eq
 (IntegerConstant 16 (Integer 4 []))
 (Logical 4 [])
 ())
```

### 29.33.3. Heavy Sugar

```clojure
(defn IntegerCompare [l- cmp- r- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head      ::IntegerCompare
    ::integer-left   l-
    ::integer-cmpop  cmp-
    ::integer-right  r-
    ::Logical        tt-
    ::logical-value? val?-}})
```
----------------------------------------------------------------
## 29.34. REAL COMPARE



### 29.34.1. Heavy Sugar

```clojure
(defn RealCompare-- [l- cmp- r- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head   ::RealCompare
    ::real-left   l-
    ::real-cmpop  cmp-
    ::real-right  r-
    ::Logical     tt-
    ::logical-value? val?-}})
```

### 29.34.2. Legacy Sugar

```clojure
(defmacro RealCompare
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "R" cmp-))]
    `(RealCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
```
----------------------------------------------------------------
## 29.35. COMPLEX COMPARE



### 29.35.1. Heavy Sugar

```clojure
(defn ComplexCompare-- [l- cmp- r- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head   ::ComplexCompare
    ::complex-left   l-
    ::complex-cmpop  cmp-
    ::complex-right  r-
    ::Logical        tt-
    ::logical-value? val?-}})
```

### 29.35.2. Legacy Sugar

```clojure
(defmacro ComplexCompare
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "C" cmp-))]
    `(ComplexCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
```
----------------------------------------------------------------
## 29.36. STRING COMPARE



### 29.36.1. Heavy Sugar

```clojure
(defn StringCompare-- [l- cmp- r- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head   ::StringCompare
    ::string-left    l-
    ::string-cmpop   cmp-
    ::string-right   r-
    ::Logical        tt-
    ::logical-value? val?-}})
```

### 29.36.2. Legacy Sugar

```clojure
(defmacro StringCompare
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "S" cmp-))]
    `(StringCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
```
----------------------------------------------------------------
## 29.37. LOGICAL COMPARE



### 29.37.1. Original ASDL

```c
| LogicalCompare(expr left,   ;; must have type ::Logical
                 cmpop op,    ;; not all cmpop, only Eq and NotEq
                 expr right,  ;; must have type ::Logical
                 ttype type,
                 expr? value)
```


### 29.37.2. Example

```clojure
#_
(LogicalCompare
  (Var 2 b)
  Eq
  (Var 2 b)
  (Logical 4 []) ())
```

### 29.37.3. Heavy Sugar

```clojure
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
```

### 29.37.4. Legacy Sugar

```clojure
(defmacro LogicalCompare
  "Must use Eq, NotEq."
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "L" cmp-))]
    `(LogicalCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
```
----------------------------------------------------------------
## 29.38. LOGICAL NOT



### 29.38.1. Original ASDL

```c
LogicalNot(expr arg, ttype type, expr? value)
```

### 29.38.2. Heavy Sugar

```clojure
(defn LogicalNot
  [larg, lttype, lvalue?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head ::LogicalNot
    ::logical-expr   larg
    ::Logical        lttype
    ::logical-value? lvalue?}})
```
----------------------------------------------------------------
## 29.39. CAST



### 29.39.1. Original ASDL

```c
| Cast(expr arg, cast-kind kind, ttype type, expr? value)
```

### 29.39.2. Example


```clojure
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
```

### 29.39.3. Heavy Sugar


```clojure
(defn Cast
  [arg, cast-kind, ttype, value?]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head ::Cast
    ::arg       arg
    ::cast-kind cast-kind
    ::ttype     ttype
    ::value?    value?}})
```
----------------------------------------------------------------
## 29.40. LIST ITEM


```clojure
(defn ListItem [list-expr index ttype value?]
  {::term ::expr
   ::asr-expr-head
   {::expr-head       ::ListItem
    ::list-expr       list-expr
    ::index           index
    ::ttype           ttype
    ::value?          value?
    }})
```
----------------------------------------------------------------
## 29.41. TUPLE ITEM


```clojure
(defn TupleItem [tuple-expr index ttype value?]
  {::term ::expr
   ::asr-expr-head
   {::expr-head       ::TupleItem
    ::tuple-expr      tuple-expr
    ::index           index
    ::ttype           ttype
    ::value?          value?
    }})
```


# 30. STMT


----------------------------------------------------------------
## 30.1. Prerequisite Types and Aliases


```clojure
(s/def ::stmt*          (.* ::stmt))
```
```clojure
(s/def ::stmt?          (.? ::stmt))
(s/def ::vars           (.* ::Var))
```


TODO: more cases for `lvalue`


```clojure
(s/def ::lvalue             ::Var)
(s/def ::rvalue             ::expr)
(s/def ::overloaded         ::stmt?)
```
```clojure
(s/def ::format?            ::expr?)
(s/def ::value*             ::expr*)
(s/def ::separator?         ::expr?)
(s/def ::end?               ::expr?)
```
```clojure
(s/def ::nym                ::identifier)
(s/def ::scope-nyms         ::identifier-set)
(s/def ::orig-nym           ::identifier)
```
```clojure
(s/def ::value-attr         ::bool)
```
```clojure
(s/def ::type-declaration (s/nilable ::symtab-id))
```


TODO: there is ambiguity regarding identifier-sets and lists:


```clojure
(s/def ::dependencies       ::identifier-set)
```
```clojure
(s/def ::param*             ::expr*) ;; renamed from args
(s/def ::body               ::stmt*)
(s/def ::return-var?        ::expr?)
```
```clojure
(s/def ::deterministic      ::bool)
(s/def ::side-effect-free   ::bool)

```clojure
(s/def ::test-expr          ::logical-expr)
(s/def ::orelse             ::stmt*)
```
```clojure
(s/def ::message?     (s/or :str string?
                            :nil empty?))
```
```clojure
(s/def ::goto-target        ::nat)
```
----------------------------------------------------------------
## 30.2. LIST APPEND

```clojure
(defn ListAppend [list-expr list-element]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head      ::ListAppend
    ::list-expr      list-expr
    ::list-element   list-element
    }})
```
----------------------------------------------------------------
## 30.3. EXPLICIT DEALLOCATE



### 30.3.1. Original ASDL

```c
    | ExplicitDeallocate(expr* vars)
```

### 30.3.2. Heavy Sugar

```clojure
(defn ExplicitDeallocate [vars]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::ExplicitDeallocate
    ::vars vars}})
```
----------------------------------------------------------------
## 30.4. ASSERT



### 30.4.1. Original ASDL

```c
| Assert(expr test, expr? msg)
```

### 30.4.2. Heavy Sugar

```clojure
(defn Assert [test-expr message?]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::Assert
    ::test-expr test-expr
    ::message?  message?}})
```
----------------------------------------------------------------
## 30.5. GOTO



### 30.5.1. Heavy Sugar

```clojure
(defn GoTo-- [goto-target identifier]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::GoTo
    ::goto-target goto-target
    ::identifier  identifier}})
```

### 30.5.2. Legacy Sugar

```clojure
(defmacro GoTo [goto-target identifier]
  (let [i_ident (str identifier)]
   `(GoTo-- ~goto-target ~i_ident)))
```
----------------------------------------------------------------
## 30.6. IF



### 30.6.1. Original ASDL

```c
| If(expr test, stmt* body, stmt* orelse)
```

### 30.6.2. Example

```clojure
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
```

### 30.6.3. Heavy Sugar

```clojure
(defn If [test-expr body orelse]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::If
    ::test-expr test-expr
    ::body      body
    ::orelse    orelse}})
```
----------------------------------------------------------------
## 30.7. ASSIGNMENT



### 30.7.1. Original ASDL

```c
| Assignment(expr target, expr value, stmt? overloaded)
         --- Var ---
```

### 30.7.2. Issues


https://github.com/rebcabin/masr/issues/21
https://github.com/rebcabin/masr/issues/22
https://github.com/rebcabin/masr/issues/26

### 30.7.3. Heavy Sugar

```clojure
(defn Assignment [lhs, rhs, unk]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head   ::Assignment
    ::lvalue      lhs
    ::rvalue      rhs
    ::overloaded  unk}})
```
----------------------------------------------------------------
## 30.8. DO LOOP



### 30.8.1. Example


```clojure
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
```

### 30.8.2. Do-Loop Head Support

```clojure
(defn do-loop-head [[var start end incr]]
  {::loop-v         var
   ::loop-start     start
   ::loop-end       end
   ::loop-increment incr})
```

### 30.8.3. Heavy Sugar

```clojure
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
```
----------------------------------------------------------------
## 30.9. WHILE LOOP



### 30.9.1. Original ASDL

```c
| WhileLoop(expr test, stmt* body)
```

### 30.9.2. Example

```clojure
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
```

### 30.9.3. Heavy Sugar

```clojure
(defn WhileLoop
  [escape-target, test-expr, body]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::WhileLoop
    ::escape-target  escape-target
    ::test-expr      test-expr
    ::body           body}})
```
----------------------------------------------------------------
## 30.10. PRINT


### 30.10.1. Original ASDL

```c
| Print(expr? fmt, expr* values, expr? separator, expr? end)
```


### 30.10.2. Heavy Sugar


```clojure
(defn Print [fmt, value*, separator, end]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head ::Print
    ::format?    fmt
    ::value*     value*
    ::separator? separator
    ::end?       end}
   })
```
----------------------------------------------------------------
## 30.11. RETURN


```clojure
(defn Return []
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head ::Return}})
```
----------------------------------------------------------------
## 30.12. SUBROUTINE CALL


`SubroutineCall` is a special case because it
abuses the word `symbol` to mean a `symbol-ref`.


### 30.12.1. Original ASDL


```c
SubroutineCall(symbol     name,          ~~~> symref
               symbol   ? original_name, ~~~> orig-symref
               call_arg * args,          ~~~> call_args
               expr     ? dt)
```
```clojure
(s/def ::dt? ::expr?)
```

### 30.12.2. Examples

```clojure
#_
(SubroutineCall  7 test_fn1  ()  []  ())

#_
(SubroutineCall  7 test_fn1  ()  ((Var 42 i))  ())
```

### 30.12.3. Heavy Sugar

```clojure
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
```

### 30.12.4. Legacy Sugar

```clojure
(defmacro SubroutineCall
  [stid, ident, orig-symref, args, dt?]
  (let [i_ident (str ident)]
    `(SubroutineCall--
      (symbol-ref ~i_ident ~stid)
      ~orig-symref
      ;; Took a while to find this ...
      ;; `(map vec ~args)` does not work!
      ;; outer `vec` for idempotency
      (vec (map (fn [a#] [a#]) ~args))
      ~dt?))) ;; #+end_src
```
----------------------------------------------------------------
## 30.13. BLOCK CALL


`BlockCall` abuses `symbol` to mean `symbol-ref`.


### 30.13.1. Original ASDL


```c
BlockCall(int    label,
          symbol m)   // <~~~ symref
```
```clojure
(s/def ::label ::nat)
```

### 30.13.2. Heavy Sugar

```clojure
(defn BlockCall--
  [label, symref]
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head    ::BlockCall
    ::label        label
    ::symbol-ref   symref
    }})
```

### 30.13.3. Legacy Sugar

```clojure
(defmacro BlockCall
  [label,
   stid ident]
  (let [i_ident (str ident)]
   `(BlockCall--
     ~label
     (symbol-ref ~i_ident ~stid))))
```


# 31. SYMBOL


----------------------------------------------------------------
## 31.1. Prerequisite Types and Aliases


```clojure
(s/def ::modulenym          ::identifier)
(s/def ::loaded-from-mod    ::bool)
(s/def ::intrinsic          ::bool)
```
```clojure
(s/def ::function-name      ::identifier)
(s/def ::function-signature ::FunctionType)
```
```clojure
(s/def ::prognym            ::identifier)
(s/def ::blocknym           ::identifier)
```
----------------------------------------------------------------
## 31.2. PROGRAM



### 31.2.1. Original ASDL


```c
= Program(symbol_table symtab,
          identifier   name,
          identifier*  dependencies,
          stmt*        body)
```

### 31.2.2. Heavy Sugar

```clojure
(defn Program-- [stab, nym, deps, body-]
  {::term ::symbol,
   ::asr-symbol-head
   {::symbol-head  ::Program
    ::SymbolTable  stab
    ::prognym      nym
    ::dependencies deps
    ::body         body-}})
```

### 31.2.3. Legacy Sugar

```clojure
(defmacro Program
  "Stringulate the nym and the dependencies
  and vectorate the dependencies."
  [stab, nym, deps, body-]
  (let [i_nym (str nym)
        i_deps (vec (map str deps))]
   `(Program--  ~stab,  ~i_nym,  ~i_deps,  ~body-)))
```
----------------------------------------------------------------
## 31.3. MODULE



### 31.3.1. Original ASDL

```c
| Module(symbol_table   symtab,
         identifier     name,
         identifier   * dependencies,
         bool           loaded_from_mod,
         bool           intrinsic)
```

### 31.3.2. Heavy Sugar

```clojure
(defn Module-- [symtab, modnym, deps, loaded, intrinsic-]
  {::term ::symbol
   ::asr-symbol-head
   {::symbol-head     ::Module
    ::SymbolTable     symtab
    ::modulenym       modnym
    ::dependencies    deps
    ::loaded-from-mod loaded
    ::intrinsic       intrinsic-}})
```

### 31.3.3. Legacy Sugar

```clojure
(defmacro Module
  "Stringulate the mondnym and the deps
  and vectorate the deps."
  [symtab, modnym, deps, loaded, intrinsic-]
  (let [i_modnym (str modnym)
        i_strss (vec (map str deps))]
    `(Module--
      ~symtab  ~i_modnym  ~i_strss
      ~loaded  ~intrinsic-)))
```
----------------------------------------------------------------
## 31.4. FUNCTION



### 31.4.1. Original ASDL

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

### 31.4.2. Heavy Sugar

```clojure
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
```

### 31.4.3. Legacy Sugar

The bodies of functions can get very big, too big
for Clojure to eval due to a limit in Java of
64KB per method body (ludicrous in 2023). So
we'll just iterate over the statements in the
bodies, replacing them with their full-forms.

```clojure
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
```
----------------------------------------------------------------
## 31.5. GENERIC PROCEDURE


### 31.5.1. Original ASDL


```c
GenericProcedure(symbol_table   parent_symtab, <~~~ symtab-id
                 identifier     name,
                 symbol       * procs,         <~~~ symbol-refs
                 access         access)
```

### 31.5.2. Example

```clojure
#_
(GenericProcedure
 3
 arccos
 [3 __lpython_overloaded_0__arccos
  3 __lpython_overloaded_1__arccos]
 Public )
```

### 31.5.3. Heavy Sugar

```clojure
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
```

### 31.5.4. Legacy Sugar

```clojure
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
```
----------------------------------------------------------------
## 31.6. EXTERNAL SYMBOL


### 31.6.1. Original ASDL


```c
| ExternalSymbol(symbol_table parent_symtab, ~~~> symtab-id
                 identifier   name,          ~~~> nym
                 symbol       external,      ~~~> extern-symref
                 identifier   module_name,   ~~~> module-nym
                 identifier*  scope_names,   ~~~> scope-nyms
                 identifier   original_name, ~~~> orig-nym
                 access       access)        ~~~> access
```

### 31.6.2. Example

```clojure
#_
(ExternalSymbol
 5 _lpython_main_program
 7 _lpython_main_program   ;; either () or a naked pair
 _global_symbols    []
 _lpython_main_program    Public)
```

### 31.6.3. Heavy Sugar

```clojure
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
```

### 31.6.4. Legacy Sugar


```clojure
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
```
----------------------------------------------------------------
## 31.7. VARIABLE



### 31.7.1. Original ASDL


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


### 31.7.2. Example


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

### 31.7.3. Light Sugar

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
```

### 31.7.4. Heavy Sugar

```clojure
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
```

### 31.7.5. Legacy Sugar

```clojure
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
```
----------------------------------------------------------------
## 31.8. BLOCK



### 31.8.1. Original ASDL


```c
Block(symbol_table symtab, identifier name, stmt* body)
```


Heavy Sugar

```clojure
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
```

### 31.8.2. Legacy Sugar

```clojure
(defmacro Block [SymbolTable
                 blocknym
                 body]
  (let [i_nym (str blocknym)]
   `(Block-- ~SymbolTable ~i_nym ~body)))
```
----------------------------------------------------------------
## 31.9. INTRINSIC MODULE



### 31.9.1. Original ASDL


There is no ASDL for this symbol in our snapshot.

Heavy Sugar

```clojure
(defn IntrinsicModule-- [modnym]
  {::term ::symbol
   ::asr-symbol-head
   {::symbol-head     ::IntrinsicModule
    ::modulenym       modnym}})
```

### 31.9.2. Legacy Sugar

```clojure
(defmacro IntrinsicModule [modnym]
  (let [i_modnym (str modnym)]
   `(IntrinsicModule-- ~i_modnym)))
```


# 32. UNIT


----------------------------------------------------------------
## 32.1. Prerequisite Type Aliases


```clojure
(s/def ::node (s/or :expr   ::expr
                    :stmt   ::stmt
                    :symbol ::symbol))

(defn node [candidate] candidate)
```

## 32.2. Pluralities



TODO: Consider a regex-spec.

```clojure
(s/def ::nodes (.* ::node))
```
----------------------------------------------------------------
## 32.3. TRANSLATION UNIT


### 32.3.1. Heavy Sugar

```clojure
(defn TranslationUnit [stab, node-preimages]
  ;; `vec` for idempotency
  (let [node-cnd (vec (map node node-preimages))]
    {::term          ::unit
     ::asr-unit-head
     {::unit-head    ::TranslationUnit
      ::SymbolTable  stab
      ::nodes        node-cnd}}))
```
