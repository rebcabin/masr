- [1. PROLOGUE](#1-prologue)
- [2. MASR OVERVIEW \& BACKGROUND](#2-masr-overview--background)
  - [2.1. Checking Entities](#21-checking-entities)
- [3. SNAPSHOT  SUMMARY ](#3-snapshot--summary-)
  - [3.1. Terms (Nodes) in the ASDL Grammar](#31-terms-nodes-in-the-asdl-grammar)
  - [3.2. Terms Used but not Defined in ASDL](#32-terms-used-but-not-defined-in-asdl)
  - [3.3. Term-Like Items](#33-term-like-items)
  - [3.4. Mappings from ASDL to MASR](#34-mappings-from-asdl-to-masr)
- [4. MAINTAINING THE CODE AND MARKDOWN](#4-maintaining-the-code-and-markdown)
  - [4.1. Reading the Markdown](#41-reading-the-markdown)
- [5. NAMESPACE DECLARATION](#5-namespace-declaration)
  - [5.1. Lightweight, Load-Time Testing:](#51-lightweight-load-time-testing)
  - [5.2. Unmap External Names](#52-unmap-external-names)
- [6. FULL-FORM ENTITY HASH-MAPS](#6-full-form-entity-hash-maps)
  - [6.1. Fully Qualified Keywords](#61-fully-qualified-keywords)
  - [6.2. Keywords and Specs ](#62-keywords-and-specs-)
- [7. IDEMPOTENCY](#7-idempotency)
- [8. SUGAR ](#8-sugar-)
  - [8.1. Naming Convention for Sugar](#81-naming-convention-for-sugar)
    - [8.1.1. Light Sugar](#811-light-sugar)
    - [8.1.2. Heavy Sugar](#812-heavy-sugar)
    - [8.1.3. Legacy Sugar](#813-legacy-sugar)
- [9. TERMS AND HEADS ](#9-terms-and-heads-)
- [10. QUALIFIED KEYWORDS ARE FUNCTIONS ](#10-qualified-keywords-are-functions-)
- [11. POLYMORPHIC SPECS FOR TERMS](#11-polymorphic-specs-for-terms)
- [12. NESTED MULTI-SPECS](#12-nested-multi-specs)
- [13. NAMING CONVENTION FOR MULTI-SPECS](#13-naming-convention-for-multi-specs)
- [14. TELESCOPING SPECS](#14-telescoping-specs)
- [15. TERM ENTITY KEY](#15-term-entity-key)
- [16. DEFMASRNESTED](#16-defmasrnested)
  - [16.1. Use of `defmasrnested`](#161-use-of-defmasrnested)
- [17. TERM-HEAD ENTITY KEY](#17-term-head-entity-key)
- [18. DEFMASRTYPE](#18-defmasrtype)
  - [18.1. EXTRACTING ASDL FROM MASR](#181-extracting-asdl-from-masr)
- [19. TO ASDL-TYPE](#19-to-asdl-type)
- [20. ADD NEW DEFINITIONS HERE](#20-add-new-definitions-here)
  - [20.1. UNIT](#201-unit)
  - [20.2. SYMBOL](#202-symbol)
  - [20.3. STMT](#203-stmt)
  - [20.4. EXPR](#204-expr)
  - [20.5. TTYPE](#205-ttype)
- [21. REWRITING FOR LEGACY](#21-rewriting-for-legacy)
- [22. IMPLEMENTATIONS](#22-implementations)
- [23. CALL-ARG](#23-call-arg)
  - [23.1. Issues](#231-issues)
  - [23.2. Original ASDL](#232-original-asdl)
  - [23.3. Examples](#233-examples)
- [24. DIMENSION](#24-dimension)
  - [24.1. Original ASDL](#241-original-asdl)
  - [24.2. Dimension-Content](#242-dimension-content)
  - [24.3. Full-Form](#243-full-form)
- [25. DIMENSION\*](#25-dimension)
  - [25.1. Heavy Sugar](#251-heavy-sugar)
- [26. SYMTAB-ID](#26-symtab-id)
  - [26.1. Heavy Sugar](#261-heavy-sugar)
- [27. SYMBOL TABLE](#27-symbol-table)
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
  - [29.1. Prerequisite Types and Aliases](#291-prerequisite-types-and-aliases)
    - [29.1.1. For Loop Statements](#2911-for-loop-statements)
    - [29.1.2. For Character](#2912-for-character)
  - [29.2. Kind](#292-kind)
  - [29.3. Support Specs For Kinds](#293-support-specs-for-kinds)
  - [29.4. Heavy Sugar for `ttype`](#294-heavy-sugar-for-ttype)
  - [29.5. Sugar for the Kinds](#295-sugar-for-the-kinds)
  - [29.6. INTEGER, REAL, COMPLEX, LOGICAL](#296-integer-real-complex-logical)
  - [29.7. CHARACTER](#297-character)
    - [29.7.1. Original ASDL](#2971-original-asdl)
    - [29.7.2. Example](#2972-example)
    - [29.7.3. Heavy Sugar](#2973-heavy-sugar)
  - [29.8. TUPLE](#298-tuple)
  - [29.9. List](#299-list)
  - [29.10. Set](#2910-set)
  - [29.11. Set](#2911-set)
  - [29.12. FUNCTION-TYPE](#2912-function-type)
    - [29.12.1. Original ASDL](#29121-original-asdl)
    - [29.12.2. Heavy Sugar](#29122-heavy-sugar)
  - [29.13. TODO The Rest of the `ttypes`](#2913-todo-the-rest-of-the-ttypes)
    - [29.13.1. Original ASDL](#29131-original-asdl)
- [30. PLACEHOLDERS](#30-placeholders)
  - [30.1. ESCAPE TARGET](#301-escape-target)
  - [30.2. SYMBOLIC VALUE](#302-symbolic-value)
    - [30.2.1. Sugar](#3021-sugar)
- [31. EXPR](#31-expr)
  - [31.1. Prerequisite Types and Aliases](#311-prerequisite-types-and-aliases)
    - [31.1.1. Scalar Detection](#3111-scalar-detection)
    - [31.1.2. Unchecked Element Types](#3112-unchecked-element-types)
    - [31.1.3. Logical Types](#3113-logical-types)
    - [31.1.4. Integer Types](#3114-integer-types)
    - [31.1.5. Index Types](#3115-index-types)
    - [31.1.6. Real Types](#3116-real-types)
    - [31.1.7. Complex Types](#3117-complex-types)
    - [31.1.8. Array Types](#3118-array-types)
    - [31.1.9. List Types](#3119-list-types)
    - [31.1.10. Tuple Types](#31110-tuple-types)
    - [31.1.11. String Types](#31111-string-types)
  - [31.2. IF EXP](#312-if-exp)
    - [31.2.1. Original ASDL](#3121-original-asdl)
    - [31.2.2. Example](#3122-example)
    - [31.2.3. Heavy Sugar](#3123-heavy-sugar)
  - [31.3. INTEGER BIT NOT](#313-integer-bit-not)
    - [31.3.1. Original ASDL](#3131-original-asdl)
    - [31.3.2. Heavy Sugar](#3132-heavy-sugar)
  - [31.4. INTEGER, REAL, COMPLEX UNARY MINUS](#314-integer-real-complex-unary-minus)
    - [31.4.1. Typed Uminus Macro](#3141-typed-uminus-macro)
    - [31.4.2. Using the Macro](#3142-using-the-macro)
  - [31.5. NAMED EXPR](#315-named-expr)
    - [31.5.1. Original ASDL](#3151-original-asdl)
    - [31.5.2. Example](#3152-example)
    - [31.5.3. Heavy Sugar](#3153-heavy-sugar)
  - [31.6. FUNCTION CALL](#316-function-call)
    - [31.6.1. Original ASDL](#3161-original-asdl)
    - [31.6.2. Example](#3162-example)
    - [31.6.3. Heavy Sugar](#3163-heavy-sugar)
    - [31.6.4. Legacy Sugar](#3164-legacy-sugar)
  - [31.7. INTRINSIC FUNCTION](#317-intrinsic-function)
    - [31.7.1. Original ASDL](#3171-original-asdl)
    - [31.7.2. Example](#3172-example)
    - [31.7.3. Heavy Sugar](#3173-heavy-sugar)
    - [31.7.4. Legacy Sugar](#3174-legacy-sugar)
  - [31.8. LOGICAL, INTEGER, REAL CONSTANTS](#318-logical-integer-real-constants)
    - [31.8.1. Typed Constant Macro](#3181-typed-constant-macro)
    - [31.8.2. Using the Macro](#3182-using-the-macro)
  - [31.9. STRING CONSTANT](#319-string-constant)
    - [31.9.1. Original ASDL](#3191-original-asdl)
    - [31.9.2. Example](#3192-example)
    - [31.9.3. Heavy Sugar](#3193-heavy-sugar)
  - [31.10. STRING CONCAT](#3110-string-concat)
    - [31.10.1. Heavy Sugar](#31101-heavy-sugar)
  - [31.11. STRING LEN](#3111-string-len)
    - [31.11.1. Heavy Sugar](#31111-heavy-sugar)
  - [31.12. STRING ITEM](#3112-string-item)
    - [31.12.1. Heavy Sugar](#31121-heavy-sugar)
  - [31.13. STRING SECTION](#3113-string-section)
    - [31.13.1. Heavy Sugar](#31131-heavy-sugar)
  - [31.14. STRING ORD](#3114-string-ord)
    - [31.14.1. Original ASDL](#31141-original-asdl)
    - [31.14.2. Example](#31142-example)
    - [31.14.3. Legacy Sugar](#31143-legacy-sugar)
  - [31.15. STRING CHR](#3115-string-chr)
    - [31.15.1. Heavy Sugar](#31151-heavy-sugar)
  - [31.16. COMPLEX CONSTANT](#3116-complex-constant)
    - [31.16.1. Original ASDL](#31161-original-asdl)
    - [31.16.2. Example](#31162-example)
    - [31.16.3. Heavy Sugar](#31163-heavy-sugar)
  - [31.17. VAR](#3117-var)
    - [31.17.1. Issue #23](#31171-issue-23)
    - [31.17.2. Heavy Sugar](#31172-heavy-sugar)
    - [31.17.3. Legacy Sugar](#31173-legacy-sugar)
  - [31.18. symbol-table! That's part of abstract execution.](#3118-symbol-table-thats-part-of-abstract-execution)
  - [31.19. ARRAY CONSTANT](#3119-array-constant)
  - [31.20. ARRAY ITEM](#3120-array-item)
    - [31.20.1. Example](#31201-example)
    - [31.20.2. Heavy Sugar](#31202-heavy-sugar)
  - [31.21. ARRAY RESHAPE](#3121-array-reshape)
    - [31.21.1. Example](#31211-example)
    - [31.21.2. Heavy Sugar](#31212-heavy-sugar)
  - [31.22. INTEGER BINOP](#3122-integer-binop)
    - [31.22.1. Original ASDL](#31221-original-asdl)
    - [31.22.2. Example](#31222-example)
    - [31.22.3. Heavy Sugar](#31223-heavy-sugar)
  - [31.23. REAL BINOP](#3123-real-binop)
    - [31.23.1. Original ASDL](#31231-original-asdl)
    - [31.23.2. Example](#31232-example)
    - [31.23.3. Heavy Sugar](#31233-heavy-sugar)
    - [31.23.4. Legacy Sugar](#31234-legacy-sugar)
  - [31.24. COMPLEX BINOP](#3124-complex-binop)
    - [31.24.1. Original ASDL](#31241-original-asdl)
    - [31.24.2. Heavy Sugar](#31242-heavy-sugar)
    - [31.24.3. Legacy Sugar](#31243-legacy-sugar)
  - [31.25. LOGICAL BINOP](#3125-logical-binop)
    - [31.25.1. Original ASDL](#31251-original-asdl)
    - [31.25.2. Example](#31252-example)
    - [31.25.3. Heavy Sugar](#31253-heavy-sugar)
  - [31.26. LIST CONSTANT](#3126-list-constant)
    - [31.26.1. Heavy Sugar](#31261-heavy-sugar)
  - [31.27. LIST LEN](#3127-list-len)
    - [31.27.1. Heavy Sugar](#31271-heavy-sugar)
  - [31.28. TUPLE CONSTANT](#3128-tuple-constant)
    - [31.28.1. Heavy Sugar](#31281-heavy-sugar)
  - [31.29. TUPLE LEN](#3129-tuple-len)
    - [31.29.1. Heavy Sugar](#31291-heavy-sugar)
  - [31.30. TUPLE COMPARE](#3130-tuple-compare)
    - [31.30.1. Heavy Sugar](#31301-heavy-sugar)
  - [31.31. COMPLEX RE](#3131-complex-re)
  - [31.32. COMPLEX IM](#3132-complex-im)
  - [31.33. GET POINTER](#3133-get-pointer)
  - [31.34. INTEGER COMPARE](#3134-integer-compare)
    - [31.34.1. Original ASDL](#31341-original-asdl)
    - [31.34.2. Example](#31342-example)
    - [31.34.3. Heavy Sugar](#31343-heavy-sugar)
  - [31.35. REAL COMPARE](#3135-real-compare)
    - [31.35.1. Heavy Sugar](#31351-heavy-sugar)
    - [31.35.2. Legacy Sugar](#31352-legacy-sugar)
  - [31.36. COMPLEX COMPARE](#3136-complex-compare)
    - [31.36.1. Heavy Sugar](#31361-heavy-sugar)
    - [31.36.2. Legacy Sugar](#31362-legacy-sugar)
  - [31.37. STRING COMPARE](#3137-string-compare)
    - [31.37.1. Heavy Sugar](#31371-heavy-sugar)
    - [31.37.2. Legacy Sugar](#31372-legacy-sugar)
  - [31.38. LOGICAL COMPARE](#3138-logical-compare)
    - [31.38.1. Original ASDL](#31381-original-asdl)
    - [31.38.2. Example](#31382-example)
    - [31.38.3. Heavy Sugar](#31383-heavy-sugar)
    - [31.38.4. Legacy Sugar](#31384-legacy-sugar)
  - [31.39. LOGICAL NOT](#3139-logical-not)
    - [31.39.1. Original ASDL](#31391-original-asdl)
    - [31.39.2. Heavy Sugar](#31392-heavy-sugar)
  - [31.40. CAST](#3140-cast)
    - [31.40.1. Original ASDL](#31401-original-asdl)
    - [31.40.2. Example](#31402-example)
    - [31.40.3. Heavy Sugar](#31403-heavy-sugar)
  - [31.41. LIST ITEM](#3141-list-item)
  - [31.42. TUPLE ITEM](#3142-tuple-item)
- [32. STMT](#32-stmt)
  - [32.1. Prerequisite Types and Aliases](#321-prerequisite-types-and-aliases)
  - [32.2. LIST APPEND](#322-list-append)
  - [32.3. EXPLICIT DEALLOCATE](#323-explicit-deallocate)
    - [32.3.1. Original ASDL](#3231-original-asdl)
    - [32.3.2. Heavy Sugar](#3232-heavy-sugar)
  - [32.4. ASSERT](#324-assert)
    - [32.4.1. Original ASDL](#3241-original-asdl)
    - [32.4.2. Heavy Sugar](#3242-heavy-sugar)
  - [32.5. GOTO](#325-goto)
    - [32.5.1. Heavy Sugar](#3251-heavy-sugar)
    - [32.5.2. Legacy Sugar](#3252-legacy-sugar)
  - [32.6. IF](#326-if)
    - [32.6.1. Original ASDL](#3261-original-asdl)
    - [32.6.2. Example](#3262-example)
    - [32.6.3. Heavy Sugar](#3263-heavy-sugar)
  - [32.7. ASSIGNMENT](#327-assignment)
    - [32.7.1. Original ASDL](#3271-original-asdl)
    - [32.7.2. Issues](#3272-issues)
    - [32.7.3. Heavy Sugar](#3273-heavy-sugar)
  - [32.8. DO LOOP](#328-do-loop)
    - [32.8.1. Example](#3281-example)
    - [32.8.2. Do-Loop Head Support](#3282-do-loop-head-support)
    - [32.8.3. Heavy Sugar](#3283-heavy-sugar)
  - [32.9. WHILE LOOP](#329-while-loop)
    - [32.9.1. Original ASDL](#3291-original-asdl)
    - [32.9.2. Example](#3292-example)
    - [32.9.3. Heavy Sugar](#3293-heavy-sugar)
  - [32.10. PRINT](#3210-print)
    - [32.10.1. Original ASDL](#32101-original-asdl)
    - [32.10.2. Heavy Sugar](#32102-heavy-sugar)
  - [32.11. RETURN](#3211-return)
  - [32.12. SUBROUTINE CALL](#3212-subroutine-call)
    - [32.12.1. Original ASDL](#32121-original-asdl)
    - [32.12.2. Examples](#32122-examples)
    - [32.12.3. Heavy Sugar](#32123-heavy-sugar)
    - [32.12.4. Legacy Sugar](#32124-legacy-sugar)
  - [32.13. BLOCK CALL](#3213-block-call)
    - [32.13.1. Original ASDL](#32131-original-asdl)
    - [32.13.2. Heavy Sugar](#32132-heavy-sugar)
    - [32.13.3. Legacy Sugar](#32133-legacy-sugar)
- [33. SYMBOL](#33-symbol)
  - [33.1. Prerequisite Types and Aliases](#331-prerequisite-types-and-aliases)
  - [33.2. PROGRAM](#332-program)
    - [33.2.1. Original ASDL](#3321-original-asdl)
    - [33.2.2. Heavy Sugar](#3322-heavy-sugar)
    - [33.2.3. Legacy Sugar](#3323-legacy-sugar)
  - [33.3. MODULE](#333-module)
    - [33.3.1. Original ASDL](#3331-original-asdl)
    - [33.3.2. Heavy Sugar](#3332-heavy-sugar)
    - [33.3.3. Legacy Sugar](#3333-legacy-sugar)
  - [33.4. FUNCTION](#334-function)
    - [33.4.1. Original ASDL](#3341-original-asdl)
    - [33.4.2. Heavy Sugar](#3342-heavy-sugar)
    - [33.4.3. Legacy Sugar](#3343-legacy-sugar)
  - [33.5. GENERIC PROCEDURE](#335-generic-procedure)
    - [33.5.1. Original ASDL](#3351-original-asdl)
    - [33.5.2. Example](#3352-example)
    - [33.5.3. Heavy Sugar](#3353-heavy-sugar)
    - [33.5.4. Legacy Sugar](#3354-legacy-sugar)
  - [33.6. EXTERNAL SYMBOL](#336-external-symbol)
    - [33.6.1. Original ASDL](#3361-original-asdl)
    - [33.6.2. Example](#3362-example)
    - [33.6.3. Heavy Sugar](#3363-heavy-sugar)
    - [33.6.4. Legacy Sugar](#3364-legacy-sugar)
  - [33.7. VARIABLE](#337-variable)
    - [33.7.1. Original ASDL](#3371-original-asdl)
    - [33.7.2. Example](#3372-example)
    - [33.7.3. Light Sugar](#3373-light-sugar)
    - [33.7.4. Heavy Sugar](#3374-heavy-sugar)
    - [33.7.5. Legacy Sugar](#3375-legacy-sugar)
  - [33.8. BLOCK](#338-block)
    - [33.8.1. Original ASDL](#3381-original-asdl)
    - [33.8.2. Legacy Sugar](#3382-legacy-sugar)
  - [33.9. INTRINSIC MODULE](#339-intrinsic-module)
    - [33.9.1. Original ASDL](#3391-original-asdl)
    - [33.9.2. Legacy Sugar](#3392-legacy-sugar)
- [34. UNIT](#34-unit)
  - [34.1. Prerequisite Type Aliases](#341-prerequisite-type-aliases)
  - [34.2. Pluralities](#342-pluralities)
  - [34.3. TRANSLATION UNIT](#343-translation-unit)
    - [34.3.1. Heavy Sugar](#3431-heavy-sugar)
- [35. REGISTRY](#35-registry)


# 1. PROLOGUE



MASR converts _ASR S-expressions_ like this:

```clojure
'(TranslationUnit
 (SymbolTable 42 {})
 [(Program
   (SymbolTable 3 {})
   main_program
   []
   [(= (Var 2 a)
       (LogicalConstant false (Logical 4 []))
       ())])])
```

into _entity hash-maps_ in _full-form_, like this:

```clojure
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
```

Entity hash-maps have all information from the
ASR S-expression syntax, but type-checked and
self-contained, ready for downstream analysis,
interpretation, optimization, code-generation,
storage, retrieval, decompilation, etc.


MASR type-checking is aggressive and
fine-grained. It is intended to find bugs in
compiler front ends like LPython and LFortran.

https://github.com/lcompilers

In the future, MASR will generate tests for
compiler back ends that process ASR.



# 2. MASR OVERVIEW & BACKGROUND



MASR specifications are boolean functions that
check whether an entity hash-map like that above
inhabits mathematical sets described by the spec.
An entity hash-map is a concrete instance.


See

1. [About Clojure Specs](https://clojure.org/about/spec)

2. [Clojure Spec Programmers' Guide](https://clojure.org/guides/spec)

3. [Differnces Between Set Theory and Type Theory](https://math.stackexchange.com/questions/489369)

4. [Formal Type Theory](https://softwarefoundations.cis.upenn.edu/plf-current/index.html)

for background on clojure.spec and type theory
in general.


MASR augments [libasr](https://github.com/lcompilers/libasr).

with the following facilities:

* MASR is more precise than ASDL; For example,
  MASR distinguishes names of programs from names
  of functions.

* MASR is more explicit than ASDL. For example,
  we can say a MASR argument list has no more
  than a certain number of arguments.

* MASR is less overloaded than ASDL. For example,
  `symbol` is separate from `symbol_table`,
  `symbol-ref` in MASR, but they are all the same
  in ASDL.

* MASR exposes secret semantics that ASDL cannot
  express. TODO: example.

----------------------------------------------------------------
## 2.1. Checking Entities


An entity hash-map may inhabit multiple sets.
For example, any `LogicalConstant` is an `expr`,
and any `expr` is an `asr-term`. These sets stand
in _subset_ relations. We can also check that
`LogicalConstant` is _not_ a `ttype`. Many
examples of tests like this are in
`core_test.clj`. Reading the tests and stepping
through them in the debugger is the best way to
learn MASR.


MASR types are recursive. Bigger types are
defined in terms of smaller types, all the way to
a handful of primitive _atomic_ types. All fields
of big entities are checked against specs, all
the way down to the atoms.



# 3. SNAPSHOT  SUMMARY <a id="snapshot"/>



We begin with a summary of a snapshot of the full
ASDL specification:
https://github.com/rebcabin/masr/blob/main/ASR_2023_APR_06_snapshot.asdl


## 3.1. Terms (Nodes) in the ASDL Grammar


`Terms` appear to the left of equals signs. Terms
may also be called `nodes`.


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

## 3.2. Terms Used but not Defined in ASDL

```c
31 symbol_table    = a clojure hash-map
32 dimension*      = see below
```

## 3.3. Term-Like Items

```c
 0 atoms           = int, float, bool, string, nat, bignat
 0 identifier      = specified below
```

## 3.4. Mappings from ASDL to MASR

* ASDL tuples like `(1 2)` are Clojure lists or
  vectors.

* ASDL lists are Clojure lists.

* ASDL vectors like `[expr? stmt*]` are Clojure vectors.

* ASDL symbol_tables are Clojure maps.


# 4. MAINTAINING THE CODE AND MARKDOWN



This file is semi-literate programming. Blocks of
code in this Markdown file, `specs.md`, are
extracted from the live source code in the file
`specs.clj`. This Markdown file, if extracted
properly, cannot get out-of-date with respect to
the code.


To contribute,

1. Identify candidate S-expressions to check and
   convert.

   a. Browse the examples in `resources/reference`.

   b. Generate new examples via `--show-asr` in
      LFortran or LPython.

   c. Write examples by hand using [light sugar
      or heavy sugar](#sugar).

2. Write tests in `core_test.clj` and
   `big_test.clj` following the patterns evident
   in those two files.

3. If the tests fail, write
   new [_term processers_](#term) in this file,
   `specs.clj`.

4. Double check for regressions by running all
   tests with your new code: `time lein test`

   a. Optionally, write negative tests to
      prevent false positives. See `big_test.clj`
      and `resources/negatives` for examples to
      follow.

5. Format accurate comments in your code in
   Markdown. Precede a code block with
   `#+_begin_src clojure` (or some other language
   like `bash` or `c`) in a
   double-semicolon-space comment beginning in
   column 1, by itself, plus a blank line.
   Terminate code blocks with `#+end_src` in a
   double-semicolon-space comment beginning in
   column 1. You'll see many examples below.

6. Extract `specs.md` from `specs.clj` as
   follows:


```bash
awk -f md4code.awk < ./src/masr/specs.clj > specs.md
```


7. Visual Studio Code can maintain Table of
   Contents and section numbers via an extension
   called `MarkdownForAll.` Install both.
   Rebuild the Table of Contents and section
   numbers in `specs.md`:

   a. Run `md4code.awk` as shown above.

   b. Open or revert `specs.md` in Visual Studio
      Code.

   c. Position the cursor at the top of
      `specs.md`, then `Cmd-Shift-P`, "Add or
      Update Section Numbers."

   d. Position the cursor at the top of
      `specs.md`, then `Cmd-Shift-P`, "Create
      Table of Contents."

   e. Save `specs.md` from Visual Studio Code.

8. `git commit`, `git push`, open a pull request.


## 4.1. Reading the Markdown


The Markdown viewer in Visual Studio Code is
_not_ adequate for reading this file. It jumps
around and gets stuck. We recommend the Markdown
viewer in PyCharm or CLion. There are numerous
paid Markdown viewers on the Mac, but we have not
tried them.



# 5. NAMESPACE DECLARATION


Declare Clojure dependencies for the rest of the
code in this file.


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

## 5.1. Lightweight, Load-Time Testing:

Currently used in `simplespecs.clj`, but
dependency retained here for convenience going
forward.

```clojure
(hyperfiddle.rcf/enable!)
```

## 5.2. Unmap External Names


Unmap `Integer` and `Character` so we can have
those symbols in `ttypes`. Access the originals
via the full syntax `java.lang.Integer`
`java.lang.Character`. We also want `deftype`.
Access original `deftype` as
`clojure.core/deftype`.


```clojure
(ns-unmap *ns* 'Integer)
(ns-unmap *ns* 'Character)
(ns-unmap *ns* 'deftype)
```


# 6. FULL-FORM ENTITY HASH-MAPS



Every MASR `asr-term` entity has a _full-form_. A
MASR full-form is a Clojure _hash-map_ that
contains the key `::term` at top level. [Hash-maps
are collections of key-value pairs like Python
dictionaries](https://clojuredocs.org/clojure.core/hash-map).


MASR checks full-form against Clojure specs.
For example,


```clojure
;; key         value
{::term        ::intent,
 ::intent-enum "Local"}
```

is an entity checked against specs for `::term`,
and `::intent-enum`.


## 6.1. Fully Qualified Keywords


All hash-map keys in MASR are _fully qualified
keywords_ (FQKWs) in the namespace `masr.specs`,
denoted with double-colons when _in_ that
namespace. See
https://clojure.org/reference/reader for more
about they syntax of Clojure keywords.


FKQWs serve three purposes:

1. names of registers specs

2. keys in entities

3. [tag-fetching functions](#tag-fetching) for `defmulti`


This file, `specs.clj`, is automatically _in_
namespace `masr.specs`, so FQKWs like `::term`
are written with double colons.


In other files, there are multiple options for
writing FQKWs in the namespace `masr.specs`: with
an explicit prefix as in `:masr.specs/intent`, or
with a namespace alias as in `::asr/intent`. The
test file, `core_tests.clj`, employs the
namespace alias `asr`. Other options include a
namespace distributed across unqualified keys in
a hash map, as in

```clojure
#:masr.specs{:term       :masr.specs/SymbolTable,
             :symtab-id  42,
             :hash-map   {}}
```

EXAMPLES -- all the following full-forms mean the
same:

* always acceptable, though verbose:

```clojure
{:masr.specs/term        :masr.specs/intent,
 :masr.specs/intent-enum "Unspecified"}
```

* Clojure-standard distributed form, always
  acceptable:

```clojure
#:masr.specs{:term        :masr.specs/intent,
             :intent-enum "Unspecified"}
```

* when in this file or _in_ namespace
  `masr.specs` via the
  line `(in-ns 'masr.specs)`:

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

## 6.2. Keywords and Specs <a id="registry"/>


FQKWs may have specs registered for them via
`s/def`. When a spec is registered for an FQKW,
Clojure automatically checks types of entities
recursively. For example, an entity that conforms
to `::SymbolTable` will have a `::term`,
`::symtab-id`, and a `::hash-map` that conform to
specs registered to those FQKWs.


*PITFALL WARNING* -- If you do not register a
spec for a qualified keyword, `k`, Clojure will
_always pass_ an item in a hash-map with key `k`.
Unregistered qualified keywords can lead to
_false positive checks_.



# 7. IDEMPOTENCY



`slurp-test` in `core_test.clj` attempts
recursive checking of snapshot examples from
LFortran and LPython. Recursive checks sometimes
overflow the Java method-size limit of 64KB. The
solution is to check bottom-up: explode
sub-entities into files or a database, then
implode them back into a top-level entity. The
file `big_test.clj` shows how to do this.


Bottom-up evaluation requires idempotency: that
evaluating a sub-entity full-form produces the
same full-form. This is easy only if we replace
Clojure symbols with strings because Clojure
symbols are not self-evaluating.


For an example, notice the external quote on the
following entity, preventing evaluation. Without
this quote, Clojure would error when evaluating
this entity a second time because it would
attempt to evaluate the unbound symbol `main0`:

```clojure
'#:masr.specs{:stmt-head ;; <~~~ external quote
              :masr.specs/SubroutineCall,
              :symbol-ref
              #:masr.specs{:identifier  main0,
                           :symtab-id   114},
              :orig-symref  (),
              :call-args    (),
              :dt?          ()}
```

The above is the spec produced by evaluating the
old, non-idempotent heavy-sugar function,
`SubroutineCall`. The naked symbol `main0` is
unbound and won't survive another round of
evaluation. The sugar function now converts the
symbol to a string, producing the following
idempotent form:

```clojure
#:masr.specs{:stmt-head  ;; <~~~ difference
             :masr.specs/SubroutineCall,
             :symbol-ref
             #:masr.specs{:identifier
                          "main0",  ;; <~~~ difference
                          :symtab-id  114},
             :orig-symref  (),
             :call-args    (),
             :dt?          ()}
```

Every element of that hash-map is
self-evaluating: keywords, numbers, strings, the
empty list `()`. It turns out that hash-maps and
vectors with self-evaluating elements are also
self-evaluating. We're in business if we replace
all unbound symbols with strings.



# 8. SUGAR <a id="sugar"/>



Most entities have sugared forms that are

1. easier for humans to read and write

2. compatible with output from `--show-asr` in
   lpython and lfortran.


Sugared forms are function-calls at
bottom (examples below). The function calls
produce full-form entities. Some sugared forms
employ macros at top to replace symbols with
strings and for other utilitarian transformations
on the way to bottoming out at a function call.


Sugar comes in three flavors: _light_, _heavy_,
and _legacy_.


1. Light sugar is for human writers. It employs
   functions with single-colon, non-qualified
   keyword (NQKW) arguments with default values.
   Light sugar is unambiguous but more verbose
   than heavy sugar.


2. Heavy sugar employs functions with positional
   arguments, with possible default values for
   tail arguments. Heavy sugar is short and often
   compatible with ASDL output from `--show-asr`.
   Heavy sugar is more risky for humans to write
   and much harder to read than light sugar,
   especially for long argument lists as with,
   say, `Variable` and `FunctionType`.


3. Legacy sugar is compatible with ASDL output
   from `--show-asr`.


All sugared forms produce identical full-forms.

----------------------------------------------------------------
## 8.1. Naming Convention for Sugar


### 8.1.1. Light Sugar


The names of light-sugar functions, like
`Integer-`, have a single trailing hyphen. The
keyword arguments of light-sugar functions are
partitioned into _required_ and
_optional-with-defaults_. The keyword-argument
lists of light-sugar functions do not depend on
order. The following two examples of light sugar
both conform to specs registered for `::asr-term`
and to `::ttype`:


```clojure
#_(Integer- {:dimension* [], :kind 4})
#_(Integer- {:kind 4, :dimension* []})
```

### 8.1.2. Heavy Sugar


The names of heavy-sugar functions, like
`Integer` or `Variable--`, have either zero or
two trailing hyphens. For example:


```clojure
#_(Variable-- 2 "x" (Integer 4)
            nil [] Local
            [] []  Default
            Source Public Required
            false)
```

If legacy sugar exists for a term, the legacy
sugar has the name with no hyphens, like
`Variable`, and the heavy sugar has the name with
two hyphens, like `Variable--`.


For specs like `Integer`, where heavy sugar and
legacy are identical, there is no function with
two trailing hyphens in its name.


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

### 8.1.3. Legacy Sugar


Legacy sugar

1. Converts symbols to idempotent strings.

2. Converts nests like `((..)...)` to `[(...)...]`
   lest they denote function calls.

3. Resolves `symbol-ref`: sometimes a list and
   sometimes a naked pair.

4. Corrects other syntactic defects.

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



# 9. TERMS AND HEADS <a id="term"/>



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



# 10. QUALIFIED KEYWORDS ARE FUNCTIONS <a id="tag-fetching"/>



`::term` is both a FQKW _and_ a _tag-fetching
function+. A tag-fetching function picks the value
of the key `::term` from any hash-map. For
example,


`(::term {::term ::intent ...})`


calls the function `::term` with argument
`{::term ::intent ...}` and produces `::intent`.


As a FQKW, `::term`, can also [name a Clojure
spec registered to it via `s/def`](#registry).
The following spec will check whether `::intent`
is a `::term`:


```clojure
(s/def ::term qualified-keyword?)
```

EXAMPLE: `::intent` is a valid `::term` because
`::intent` is a FQKW.


```clojure
(s/valid? ::term ::intent)
;; => true
```


# 11. POLYMORPHIC SPECS FOR TERMS



`defmulti/defmethod` is one [Clojure idiom for
_polymorphism_](https://clojure.org/about/runtime_polymorphism):,
a single `defmulti` interface with many
`defmethod` implementations.


`(defmulti term ::term)` links the name
`term` (no colons) to the [tag-fetcher
function](#tag-fetching) `::term` (with colons).
Each `defmethod` of `term` is tagged by the value
fetched via `::term`. For example, there is one
`defmethod` for `::symbol` and another for
`::expr`.

```clojure
(defmulti term ::term)
#_(defmethod term ::symbol [_] ,,,)
#_(defmethod term ::expr   [_] ,,,)
,,,
```

MASR handles _alternatives_ or _heads_ -- to the
right-hand sides of equals signs in ASDL -- via
_multi-specs_. By analogy, multi-specs are to
specs as `defmethods` are to functions -- one
spec interface to many implementations.


The name of the _one_ multi-spec for all terms is
`::asr-term`, an FQKW, as are all names of specs.
Multi-specs act like tagged unions in C.



# 12. NESTED MULTI-SPECS



At the top level, `::term` multi-specs dispatch
on values of the `::term` key, values like
`::intent`, `::symbol`, `::unit`, etc. Nested
multi-specs for those values specify the
remaining required keys for nested entities
conforming to the sub-specs `::intent`,
`::symbol`, `::unit`, etc.


Some `defmethods` like `::intent` are simple,
just checking that a value like `"Local"` or
`"ReturnVar"` inhabits a `set` of strings Other
`defmethods`, like `::symbol`, have _nested
multi-specs_ that dispatch on _heads_, like
`Variable` or `Program`. MASR handles nested
multi-specs via techniques shown below.



# 13. NAMING CONVENTION FOR MULTI-SPECS



The names of all multi-specs in MASR, nested or
not, begin with `::asr-...`, as in
`::asr-term` (not nested) and
`::asr-ttype-head`, nested in `ttypes`, and
`::asr-expr-head`, nested in `expr`.


```clojure
(s/def ::asr-term
  (s/multi-spec term ::term))
```


# 14. TELESCOPING SPECS



A certain given entity (instance hash-map) may be

* an `::asr-term` -- any one of the many terms

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




# 15. TERM ENTITY KEY



Each term, like `symbol`, needs its own spec,
named by a FQKW like `::symbol`. MASR recursively
checks `::symbol` fields in other specs.


`Def-term-entity-key` registers a spec for
`symbol`, for instance. That spec checks that a
given entity is an `::asr-term` and its `::term`
equals a given FQKW like `::symbol`.


`Def-term-entity-key` must be called for any
hand-written spec like `dimension`. It's
automatically invoked for any term defined via
`defmasrnested`, which appears immediately below.


```clojure
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
```


# 16. DEFMASRNESTED



MASR automates construction of nested
multi-specs, removing duplicated wordage. The
docstring of `defmasrnested` shows an example.


**READ ALL DOCSTRINGS**


It is not necessary to understand the
implementation of `defmasrnested` unless you are
maintaining it. The macro is tricky to understand
due mostly to Clojure's implicit insertion and
deletion of namespaces in macros. We step around
around it when necessary via Clojure's built-in
`name` function.


```clojure
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
```

## 16.1. Use of `defmasrnested`


```clojure
(defmasrnested expr)
(defmasrnested stmt)
(defmasrnested symbol)
(defmasrnested ttype)
(defmasrnested unit)
```


# 17. TERM-HEAD ENTITY KEY



We need specs for each nested multi-spec for
heads like `::Variable` and `::FunctionType`.


```clojure
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
```


# 18. DEFMASRTYPE



`Defmasrtype` is the easiest way to add new specs
that have nested multi-specs. Terms without
nested multi-specs are few. They are special
cases with hand-written specs.


`Defmasrtype` creates both (1) the specs for
particular heads like `Variable` and `Assignment`,
and (2) a function, `->asdl-type`, that extracts
the ASDL type from any entity hash-map. We
present the extraction code first ("define" before
"use"):

----------------------------------------------------------------
## 18.1. EXTRACTING ASDL FROM MASR


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


# 19. TO ASDL-TYPE



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


# 20. ADD NEW DEFINITIONS HERE



Fill out implementations later.

----------------------------------------------------------------
## 20.1. UNIT


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
## 20.2. SYMBOL


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
## 20.3. STMT


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
  (test-expr  body  orelse))
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
## 20.4. EXPR


```clojure
(defmulti  expr->asdl-type ::expr-head)
(term->asdl-type expr)
```
```clojure
(defmasrtype
  IfExp expr
  (test-expr body-expr orelse-expr ttype value?))
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
(defmasrtype ;; 1 of 9 String... terms
  StringConstant expr
  (string  Character))
```
```clojure
(defmasrtype ;; 2 of 9 String... terms
  StringConcat expr
  (string-left  string-right
   Character    string-value?))
```
```clojure
(defmasrtype ;; 3 of 9 String... terms
  StringRepeat expr
  (string-expr  integer-expr
   Character    string-value?))
```
```clojure
(defmasrtype ;; 4 of 9 String... terms
  StringLen expr
  (string-expr Integer integer-value?))
```
```clojure
(defmasrtype ;; 5 of 9 String... terms
  StringItem expr
  (string-expr index?
   character-or-integer-ttype
   character-or-integer-value?))
```
```clojure
(defmasrtype ;; 6 of 9 String... terms
  StringSection expr
  (string-expr
   index-start?  index-end?  index-step?
   Character     string-value?))
```
```clojure
(defmasrtype ;; 7 of 9 String... terms
  StringCompare expr
  (string-left  string-cmpop  string-right
   Logical      logical-value?))
```
```clojure
(defmasrtype ;; 8 of 9 String... terms
  StringOrd expr
  (string-expr  Integer  integer-value?))
```
```clojure
(defmasrtype ;; 9 of 9 String... terms
  StringChr expr
  (integer-scalar-or-expr
   Character  string-value?))
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
```clojure
(defmasrtype
  GetPointer expr
  (expr  Pointer  pointer-value?))
```
----------------------------------------------------------------
## 20.5. TTYPE


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
  Pointer ttype
  (ttype))
```
```clojure
(defmasrtype
  Character ttype
  (character-kind  len        disposition
                   len-expr?  dimension*))
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


# 21. REWRITING FOR LEGACY



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
      (->> sexp
           rewrite-for-legacy
           eval)))
```
```clojure
(defmacro legacy
  "DANGER: Call 'eval'."
  [it]
  `(to-full-form '~it))
```

Full-forms must be checked against a spec. The
following function does a rudimentary
uninformative check, returning a given error code
in case of any error. Its parameter order helps
reduce indentation at its call sites.

```clojure
(defn check-full-form
  [spec errcode form]
  (if (not (s/valid? spec form))
    errcode, #_else spec))
```


# 22. IMPLEMENTATIONS



The remaining sections of this document describe
detailed implementations of the sugar functions
for the `defmasrtype`s.



# 23. CALL-ARG



## 23.1. Issues

https://github.com/rebcabin/masr/issues/32
`call-arg` intentionally introduces a level of
nesting to a list of actual arguments to a
function call or subroutine call. We spec the
extra nesting as a collection of length one.


## 23.2. Original ASDL

```c
call_arg = (expr? value)
```


```clojure
(s/def ::call-arg
  (s/coll-of ::expr?
             :min-count 1 ;; Issue 32
             :max-count 1))

(s/def ::call-args (s/coll-of ::call-arg))

(s/def ::call-arg-or-args
  (s/or :call-arg  ::call-arg
        :call-args ::call-args))
```

## 23.3. Examples


Examples can't be executed until `expr?` is
defined. See the first example in in
`SubroutineCall.`



# 24. DIMENSION



`Dimension` is a term without nested multi-specs.
It is a handwritten special case, not defined via
`defmasrtype`.


## 24.1. Original ASDL

```c
dimension = (expr? start, expr? length)
```


The ASDL is imprecise. The real spec, realized
only in secret C++ code, is that we have either
both `start` and `length` or we have nothing.
MASR makes exposes this secret explicitly.


## 24.2. Dimension-Content



The next spec says that a `::dimension-content` is
a collection of `::nat` with either two or zero
elements.


```clojure
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
```

## 24.3. Full-Form



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
```clojure
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
```


# 25. DIMENSION*


`Dimension*` is not an `asr-term`. It's a collection
of `dimension`s, each of which is an `asr-term`.

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

## 25.1. Heavy Sugar


```clojure
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
```


# 26. SYMTAB-ID



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

## 26.1. Heavy Sugar


```clojure
(defn symtab-id [it] it)
```


# 27. SYMBOL TABLE



`SymbolTable` is an unwritten term. It doesn't have
nested multi-specs. Write it out fully by hand. Its
hash must relate keywords and valid asr-terms.


```clojure
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
```

## 27.1. Heavy Sugar


```clojure
(defn SymbolTable [id, hash-map]
  {::term      ::SymbolTable
   ::symtab-id id
   ::hash-map  hash-map})
```


# 28. ENUM-LIKE



Many ASDL types are like enums: a set of
alternative symbols, without parentheses and
without parameters _qua_ arguments. Example: ASDL
`access` has two possibilities: `Public` and
`Private`. MASR automates all enum-likes via
one macro, `enum-like`.


## 28.1. Helpers for Enum-Like


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
## 28.2. Enum-Like, Proper


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

## 28.3. Most Enum-Likes


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

(s/def ::any-cmpop
  (s/or :logical-cmpop ::logical-cmpop
        :real-compop   ::real-cmpop
        :complex-cmpop ::complex-cmpop
        :integer-cmpop ::integer-cmpop
        :string-cmpop  ::string-cmpop))
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
## 28.4. Abi



`Abi` is a special case of enum-like with rich logic.

```clojure
(def external-abis
  #{"LFortranModule", "GFortranModule",
    "BindC", "Interactive", "Intrinsic"})

(def internal-abis #{"Source"})

(s/def ::abi-enum (set/union external-abis internal-abis))

(s/def ::abi-external ::bool)
```

### 28.4.1. Full-Form


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

### 28.4.2. Heavy Sugar


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

### 28.4.3. The ABIs


```clojure
(def LFortranModule (abi "LFortranModule" :external true))
(def GFortranModule (abi "GFortranModule" :external true))
(def BindC          (abi "BindC"          :external true))
(def Interactive    (abi "Interactive"    :external true))
(def Intrinsic      (abi "Intrinsic"      :external true))
(def Source         (abi "Source"         :external false))
```


# 29. TTYPE



`Ttype` is a term with nested multi-specs.

----------------------------------------------------------------
## 29.1. Prerequisite Types and Aliases


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

### 29.1.1. For Loop Statements

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

### 29.1.2. For Character

```clojure
(s/def ::len         ::int)   ;; Issues #36
(s/def ::disposition #{"compile-time-length"   ;; >= 0
                       "inferred-at-run-time"  ;; = -1
                       "allocatable"           ;; = -2
                       "run-time-expression"}) ;; = -3
(s/def ::len-expr?   ::expr?) ;; TODO: check that it's >= 0
```
----------------------------------------------------------------
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

----------------------------------------------------------------
## 29.3. Support Specs For Kinds


```clojure
(s/def ::integer-kind        #{1 2 4 8 16})
(s/def ::real-kind           #{4 8})
(s/def ::complex-kind        #{4 8})
(s/def ::logical-kind        #{1 2 4})
(s/def ::character-kind      #{1})
```
----------------------------------------------------------------
## 29.4. Heavy Sugar for `ttype`


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
## 29.5. Sugar for the Kinds


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
```
----------------------------------------------------------------
## 29.6. INTEGER, REAL, COMPLEX, LOGICAL


See also `defmasrtypes` at top of the file.
```clojure
(def-ttype-and-head Integer)
(def-ttype-and-head Real)
(def-ttype-and-head Complex)
(def-ttype-and-head Logical)
```
----------------------------------------------------------------
## 29.7. CHARACTER



### 29.7.1. Original ASDL

```c
| Character(int kind, int len, expr? len_expr, dimension* dims)
```

### 29.7.2. Example


```clojure
#_
(Character 1 1 () [])
```

### 29.7.3. Heavy Sugar


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
## 29.8. TUPLE

```clojure
(defn Tuple [ttypes]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::Tuple
    ::ttype*     ttypes}})
```
----------------------------------------------------------------
## 29.9. List

```clojure
(defn List [ttype]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::List
    ::ttype      ttype}})
```
----------------------------------------------------------------
## 29.10. Set

```clojure
(defn Set [ttype]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::Set
    ::ttype      ttype}})
```
----------------------------------------------------------------
## 29.11. Set

```clojure
(defn Pointer [ttype]
  {::term ::ttype,
   ::asr-ttype-head
   {::ttype-head ::Pointer
    ::ttype      ttype}})
```
----------------------------------------------------------------
## 29.12. FUNCTION-TYPE



This is a rich `ttype` that we spell out by hand.


### 29.12.1. Original ASDL

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

### 29.12.2. Heavy Sugar


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
## 29.13. TODO The Rest of the `ttypes`


### 29.13.1. Original ASDL

```c
>>> Integer, Real, Complex, Logical are already done ...
>>> FunctionType is done.
>>> Character(int kind, int len, expr? len_expr, dimension* dims)
>>> Set(ttype type)
>>> List(ttype type)
>>> Tuple(ttype* type)
>>> Pointer(ttype type)
--- Here are the rest of the ttypes.
| Struct(symbol derived_type, dimension* dims)
| Enum(symbol enum_type, dimension *dims)
| Union(symbol union_type, dimension *dims)
| Class(symbol class_type, dimension* dims)
| Dict(ttype key_type, ttype value_type)
| Const(ttype type)
| CPtr()
| TypeParameter(identifier param, dimension* dims)
```




# 30. PLACEHOLDERS



things we haven't fully defined yet

----------------------------------------------------------------
## 30.1. ESCAPE TARGET


```clojure
(s/def ::escape-target empty?)
```
----------------------------------------------------------------
## 30.2. SYMBOLIC VALUE


```clojure
(s/def ::symbolic-value ::expr?)
```

### 30.2.1. Sugar


```clojure
(def symbolic-value identity)
```


# 31. EXPR


----------------------------------------------------------------
## 31.1. Prerequisite Types and Aliases


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
### 31.1.1. Scalar Detection


```clojure
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
```
```clojure
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
```
----------------------------------------------------------------
### 31.1.2. Unchecked Element Types



The following represent types of elements of
collections. TODO: MASR does not currently check
them because checking them requires checking that
the type of the element matches types stored
along with the collection. The cross-logic to
check these types must be implemented in each
collection type


```clojure
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
```
----------------------------------------------------------------
### 31.1.3. Logical Types


```clojure
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
```
```clojure
(s/def ::logical-expr?    (.? ::logical-expr))
(s/def ::logical-value?       ::logical-expr?)

(s/def ::logical-left         ::logical-expr)
(s/def ::logical-right        ::logical-expr)
```
----------------------------------------------------------------
### 31.1.4. Integer Types


```clojure
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
```
```clojure
(s/def ::integer-expr?    (.? ::integer-expr))
(s/def ::integer-value?       ::integer-expr?)

(s/def ::integer-left         ::integer-expr)
(s/def ::integer-right        ::integer-expr)
```
----------------------------------------------------------------
### 31.1.5. Index Types


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
### 31.1.6. Real Types


```clojure
(s/def ::real-expr
  (s/or :real-constant        ::RealConstant
        :real-binop           ::RealBinOp
        :real-unary-minus     ::RealUnaryMinus
        :complex-im           ::ComplexIm
        :complex-re           ::ComplexRe
        :unchecked            ::unchecked-element-expr))
```
```clojure
(s/def ::real-expr?       (.? ::real-expr))
(s/def ::real-value?          ::real-expr?)

(s/def ::real-left            ::real-expr)
(s/def ::real-right           ::real-expr)
```
----------------------------------------------------------------
### 31.1.7. Complex Types


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
```
----------------------------------------------------------------
### 31.1.8. Array Types


```clojure
(s/def ::array-expr
  (s/or :var                    ::Var
        :array-constant         ::ArrayConstant
        :array-reshape          ::ArrayReshape
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
### 31.1.9. List Types

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
### 31.1.10. Tuple Types

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
### 31.1.11. String Types


```clojure
(s/def ::string-expr
  (s/or :string-constant        ::StringConstant
        :string-concat          ::StringConcat
        :string-repeat          ::StringRepeat
        :string-item            ::StringItem
        :string-section         ::StringSection
        :string-chr             ::StringChr
        :unchecked              ::unchecked-element-expr))
```
```clojure
(s/def ::string-left            ::string-expr)
(s/def ::string-right           ::string-expr)
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
## 31.2. IF EXP



### 31.2.1. Original ASDL

```c
 IfExp(expr test,
       expr body,
       expr orelse,
       ttype type,
       expr? value)
```

### 31.2.2. Example


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

### 31.2.3. Heavy Sugar


```clojure
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
```
----------------------------------------------------------------
## 31.3. INTEGER BIT NOT



### 31.3.1. Original ASDL

```c
IntegerBitNot(expr arg, ttype type, expr? value)
```

### 31.3.2. Heavy Sugar


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
## 31.4. INTEGER, REAL, COMPLEX UNARY MINUS



### 31.4.1. Typed Uminus Macro

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
### 31.4.2. Using the Macro
```clojure
(typed-uminus Integer)
(typed-uminus Real)
(typed-uminus Complex)
```
----------------------------------------------------------------
## 31.5. NAMED EXPR



### 31.5.1. Original ASDL

```c
| NamedExpr(expr target, expr value, ttype type)
```

### 31.5.2. Example

```clojure
#_
(NamedExpr
 (Var 2 y)
 (IntegerConstant 0 (Integer 4 []))
 (Integer 4 [])    )
```

### 31.5.3. Heavy Sugar


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
## 31.6. FUNCTION CALL



### 31.6.1. Original ASDL

```c
| FunctionCall(symbol     name,
               symbol?    original_name,
               call_arg * args,
               ttype      type,
               expr     ? value,
               expr     ? dt)
```


### 31.6.2. Example

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

### 31.6.3. Heavy Sugar

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

### 31.6.4. Legacy Sugar

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
## 31.7. INTRINSIC FUNCTION



### 31.7.1. Original ASDL

```c
IntrinsicFunction(int    intrinsic_id,
                  expr * args,
                  int    overload_id,
                  ttype  type,
                  expr?  value)
```


### 31.7.2. Example

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

### 31.7.3. Heavy Sugar

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

### 31.7.4. Legacy Sugar

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
## 31.8. LOGICAL, INTEGER, REAL CONSTANTS



To reduce code duplication, we want to write
something like the following automatically for
Logical, Integer, and Real. String is a special
case because its ttype is Character and not
String. Complex is a special case because it
takes two Real inputs. Write those by hand.


### 31.8.1. Typed Constant Macro

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

### 31.8.2. Using the Macro

```clojure
(typed-constant Logical bool)
(typed-constant Real    float)
(typed-constant Integer int)
```
----------------------------------------------------------------
## 31.9. STRING CONSTANT



### 31.9.1. Original ASDL

```c
| StringConstant(string s, ttype type)
```

### 31.9.2. Example

```clojure
#_
(StringConstant "3" (Character 1 1 () []))
```

### 31.9.3. Heavy Sugar

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
## 31.10. STRING CONCAT



### 31.10.1. Heavy Sugar

```clojure
(defn StringConcat [l- r- tt- val?-]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head   ::StringConcat
    ::string-left    l-
    ::string-right   r-
    ::Character      tt-
    ::string-value?  val?-}})
```
```
----------------------------------------------------------------
## STRING REPEAT



### Heavy Sugar

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
     ::string-value?  compiler-computed?}})
  ([string-expr integer-expr]
   "binary"
   (StringRepeat string-expr (Character) ())))
```
----------------------------------------------------------------
## 31.11. STRING LEN



### 31.11.1. Heavy Sugar

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
## 31.12. STRING ITEM



### 31.12.1. Heavy Sugar

See Issues #51, #52.

```clojure
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
```
----------------------------------------------------------------
## 31.13. STRING SECTION



### 31.13.1. Heavy Sugar

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
## 31.14. STRING ORD



### 31.14.1. Original ASDL

```c
| StringOrd(expr arg, ttype type, expr? value)
```

### 31.14.2. Example

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

### 31.14.3. Legacy Sugar

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
## 31.15. STRING CHR



### 31.15.1. Heavy Sugar

Issue 53: Should integer-expr be tested for scalar property?
          Such might require run-time testing. Test statically
          when possible.

```clojure
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
```
----------------------------------------------------------------
## 31.16. COMPLEX CONSTANT



### 31.16.1. Original ASDL

```c
ComplexConstant(float re, float im, ttype type)
```


### 31.16.2. Example

```clojure
#_
(ComplexConstant 3.000000 4.000000 (Complex 8 []))
```

### 31.16.3. Heavy Sugar

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
## 31.17. VAR



### 31.17.1. Issue #23

Is the parameter `symbol` for `Var` really a `symbol`?
Or just an identifier? #23
https://github.com/rebcabin/masr/issues/23
The original ASDL

```c
Var(symbol v)
```

from `ASR.asdl` doesn't match the entity
instance. Instead, we probably need something
like:

```c
Var(symtab_id stid, identifier it)
```

### 31.17.2. Heavy Sugar

```clojure
(defn Var-- [stid, ident]
  {::term ::expr,
   ::asr-expr-head
   {::expr-head  ::Var
    ::symtab-id  stid
    ::varnym     (str ident)
    }})
```

### 31.17.3. Legacy Sugar

```clojure
(defmacro Var [stid, unquoted-ident]
  (let [i_ident (str unquoted-ident)]
   `(Var-- ~stid ~i_ident)))
```

TODO: make Var look up a value in the
31.18. symbol-table! That's part of abstract execution.
----------------------------------------------------------------
## 31.19. ARRAY CONSTANT


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
## 31.20. ARRAY ITEM



### 31.20.1. Example

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

### 31.20.2. Heavy Sugar

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
## 31.21. ARRAY RESHAPE



### 31.21.1. Example

```clojure
#_
(ArrayReshape
 (Var 186 b)
 (Var 186 newshape)
 (Real 8 [(() ())])
 ())
```

### 31.21.2. Heavy Sugar

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
## 31.22. INTEGER BINOP



### 31.22.1. Original ASDL

```c
| IntegerBinOp(expr  left,
               binop op,
               expr  right,
               ttype type,
               expr? value)
```

### 31.22.2. Example

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

### 31.22.3. Heavy Sugar

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
## 31.23. REAL BINOP



### 31.23.1. Original ASDL

```c
| RealBinOp(expr  left,
               binop op,
               expr  right,
               ttype type,
               expr? value)
```

### 31.23.2. Example

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

### 31.23.3. Heavy Sugar

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

### 31.23.4. Legacy Sugar

```clojure
(defmacro RealBinOp
  "Must use Add, Mul, etc."
  [left- bo- right- rtt- rval?-]
  (let [rop (symbol (str "R" bo-))]
    `(RealBinOp-- ~left- ~rop ~right- ~rtt- ~rval?-)))
```
----------------------------------------------------------------
## 31.24. COMPLEX BINOP



### 31.24.1. Original ASDL

```c
| ComplexBinOp(expr  left,
               binop op,
               expr  right,
               ttype type,
               expr? value)
```

### 31.24.2. Heavy Sugar

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

### 31.24.3. Legacy Sugar

```clojure
(defmacro ComplexBinOp
  "Must use Add, Mul, etc."
  [left- bo- right- ctt- cval?-]
  (let [rop (symbol (str "C" bo-))]
    `(ComplexBinOp-- ~left- ~rop ~right- ~ctt- ~cval?-)))
```
----------------------------------------------------------------
## 31.25. LOGICAL BINOP



### 31.25.1. Original ASDL

```c
| LogicalBinOp(expr left, logicalbinop op, expr
  right, ttype type, expr? value)
```

### 31.25.2. Example


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

### 31.25.3. Heavy Sugar

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
## 31.26. LIST CONSTANT



### 31.26.1. Heavy Sugar

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
## 31.27. LIST LEN



### 31.27.1. Heavy Sugar

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
## 31.28. TUPLE CONSTANT



### 31.28.1. Heavy Sugar

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
## 31.29. TUPLE LEN



### 31.29.1. Heavy Sugar

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
## 31.30. TUPLE COMPARE



### 31.30.1. Heavy Sugar

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
## 31.31. COMPLEX RE

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
## 31.32. COMPLEX IM

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
## 31.33. GET POINTER

```clojure
(s/def ::pointer-value? ::expr?) ;; TODO: until it's better

(defn GetPointer [expr, ptr, pv?]
{::term ::expr,
 ::asr-expr-head
 {::expr-head       ::GetPointer
  ::expr            expr
  ::Pointer         ptr
  ::pointer-value?  pv?
  }})
```
----------------------------------------------------------------
## 31.34. INTEGER COMPARE



### 31.34.1. Original ASDL

```c
| IntegerCompare(expr  left,
                 cmpop op,
                 expr  right,
                 ttype type,
                 expr? value)
```

### 31.34.2. Example

```clojure
#_
(IntegerCompare
 (Var 4 z)
 Eq
 (IntegerConstant 16 (Integer 4 []))
 (Logical 4 [])
 ())
```

### 31.34.3. Heavy Sugar

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
## 31.35. REAL COMPARE



### 31.35.1. Heavy Sugar

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

### 31.35.2. Legacy Sugar

```clojure
(defmacro RealCompare
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "R" cmp-))]
    `(RealCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
```
----------------------------------------------------------------
## 31.36. COMPLEX COMPARE



### 31.36.1. Heavy Sugar

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

### 31.36.2. Legacy Sugar

```clojure
(defmacro ComplexCompare
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "C" cmp-))]
    `(ComplexCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
```
----------------------------------------------------------------
## 31.37. STRING COMPARE



### 31.37.1. Heavy Sugar

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

### 31.37.2. Legacy Sugar

```clojure
(defmacro StringCompare
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "S" cmp-))]
    `(StringCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
```
----------------------------------------------------------------
## 31.38. LOGICAL COMPARE



### 31.38.1. Original ASDL

```c
| LogicalCompare(expr left,   ;; must have type ::Logical
                 cmpop op,    ;; not all cmpop, only Eq and NotEq
                 expr right,  ;; must have type ::Logical
                 ttype type,
                 expr? value)
```


### 31.38.2. Example

```clojure
#_
(LogicalCompare
  (Var 2 b)
  Eq
  (Var 2 b)
  (Logical 4 []) ())
```

### 31.38.3. Heavy Sugar

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

### 31.38.4. Legacy Sugar

```clojure
(defmacro LogicalCompare
  "Must use Eq, NotEq."
  [l- cmp- r- tt- val?-]
  (let [lop (symbol (str "L" cmp-))]
    `(LogicalCompare-- ~l- ~lop ~r- ~tt- ~val?-)))
```
----------------------------------------------------------------
## 31.39. LOGICAL NOT



### 31.39.1. Original ASDL

```c
LogicalNot(expr arg, ttype type, expr? value)
```

### 31.39.2. Heavy Sugar

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
## 31.40. CAST



### 31.40.1. Original ASDL

```c
| Cast(expr arg, cast-kind kind, ttype type, expr? value)
```

### 31.40.2. Example


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

### 31.40.3. Heavy Sugar


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
## 31.41. LIST ITEM


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
## 31.42. TUPLE ITEM


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


# 32. STMT


----------------------------------------------------------------
## 32.1. Prerequisite Types and Aliases


```clojure
(s/def ::stmt*          (.* ::stmt))
```
```clojure
(s/def ::stmt?          (.? ::stmt))
(s/def ::vars           (.* ::Var))
```


TODO: more cases for `lvalue`


```clojure
(s/def ::lvalue       (s/or :var         ::Var
                            :array-item  ::ArrayItem
                            :tuple-const ::TupleConstant
                            :list-item   ::ListItem
                            ))
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
## 32.2. LIST APPEND

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
## 32.3. EXPLICIT DEALLOCATE



### 32.3.1. Original ASDL

```c
    | ExplicitDeallocate(expr* vars)
```

### 32.3.2. Heavy Sugar

```clojure
(defn ExplicitDeallocate [vars]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::ExplicitDeallocate
    ::vars vars}})
```
----------------------------------------------------------------
## 32.4. ASSERT



### 32.4.1. Original ASDL

```c
| Assert(expr test, expr? msg)
```

### 32.4.2. Heavy Sugar

```clojure
(defn Assert [test-expr message?]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::Assert
    ::test-expr test-expr
    ::message?  message?}})
```
----------------------------------------------------------------
## 32.5. GOTO



### 32.5.1. Heavy Sugar

```clojure
(defn GoTo-- [goto-target identifier]
  {::term ::stmt
   ::asr-stmt-head
   {::stmt-head ::GoTo
    ::goto-target goto-target
    ::identifier  identifier}})
```

### 32.5.2. Legacy Sugar

```clojure
(defmacro GoTo [goto-target identifier]
  (let [i_ident (str identifier)]
   `(GoTo-- ~goto-target ~i_ident)))
```
----------------------------------------------------------------
## 32.6. IF



### 32.6.1. Original ASDL

```c
| If(expr test, stmt* body, stmt* orelse)
```

### 32.6.2. Example

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

### 32.6.3. Heavy Sugar

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
## 32.7. ASSIGNMENT



### 32.7.1. Original ASDL

```c
| Assignment(expr target, expr value, stmt? overloaded)
         --- Var ---
```

### 32.7.2. Issues


https://github.com/rebcabin/masr/issues/21
https://github.com/rebcabin/masr/issues/22
https://github.com/rebcabin/masr/issues/26

### 32.7.3. Heavy Sugar

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
## 32.8. DO LOOP



### 32.8.1. Example


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

### 32.8.2. Do-Loop Head Support

```clojure
(defn do-loop-head [[var start end incr]]
  {::loop-v         var
   ::loop-start     start
   ::loop-end       end
   ::loop-increment incr})
```

### 32.8.3. Heavy Sugar

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
## 32.9. WHILE LOOP



### 32.9.1. Original ASDL

```c
| WhileLoop(expr test, stmt* body)
```

### 32.9.2. Example

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

### 32.9.3. Heavy Sugar

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
## 32.10. PRINT


### 32.10.1. Original ASDL

```c
| Print(expr? fmt, expr* values, expr? separator, expr? end)
```


### 32.10.2. Heavy Sugar


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
## 32.11. RETURN


```clojure
(defn Return []
  {::term ::stmt,
   ::asr-stmt-head
   {::stmt-head ::Return}})
```
----------------------------------------------------------------
## 32.12. SUBROUTINE CALL


`SubroutineCall` is a special case because it
abuses the word `symbol` to mean a `symbol-ref`.


### 32.12.1. Original ASDL


```c
SubroutineCall(symbol     name,          ~~~> symref
               symbol   ? original_name, ~~~> orig-symref
               call_arg * args,          ~~~> call_args
               expr     ? dt)
```
```clojure
(s/def ::dt? ::expr?)
```

### 32.12.2. Examples

```clojure
#_
(SubroutineCall  7 test_fn1  ()  []  ())

#_
(SubroutineCall  7 test_fn1  ()  ((Var 42 i))  ())
```

### 32.12.3. Heavy Sugar

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

### 32.12.4. Legacy Sugar

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
      (if (empty? ~args) []
        (vec ~args))
      ~dt?))) ;; #+end_src
```
----------------------------------------------------------------
## 32.13. BLOCK CALL


`BlockCall` abuses `symbol` to mean `symbol-ref`.


### 32.13.1. Original ASDL


```c
BlockCall(int    label,
          symbol m)   // <~~~ symref
```
```clojure
(s/def ::label ::int)  ;; TODO: Issue 49: what do negative ones mean
```

### 32.13.2. Heavy Sugar

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

### 32.13.3. Legacy Sugar

```clojure
(defmacro BlockCall
  [label,
   stid ident]
  (let [i_ident (str ident)]
   `(BlockCall--
     ~label
     (symbol-ref ~i_ident ~stid))))
```


# 33. SYMBOL


----------------------------------------------------------------
## 33.1. Prerequisite Types and Aliases


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
## 33.2. PROGRAM



### 33.2.1. Original ASDL


```c
= Program(symbol_table symtab,
          identifier   name,
          identifier*  dependencies,
          stmt*        body)
```

### 33.2.2. Heavy Sugar

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

### 33.2.3. Legacy Sugar

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
## 33.3. MODULE



### 33.3.1. Original ASDL

```c
| Module(symbol_table   symtab,
         identifier     name,
         identifier   * dependencies,
         bool           loaded_from_mod,
         bool           intrinsic)
```

### 33.3.2. Heavy Sugar

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

### 33.3.3. Legacy Sugar

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

### 33.4.2. Heavy Sugar

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

### 33.4.3. Legacy Sugar

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
## 33.5. GENERIC PROCEDURE


### 33.5.1. Original ASDL


```c
GenericProcedure(symbol_table   parent_symtab, <~~~ symtab-id
                 identifier     name,
                 symbol       * procs,         <~~~ symbol-refs
                 access         access)
```

### 33.5.2. Example

```clojure
#_
(GenericProcedure
 3
 arccos
 [3 __lpython_overloaded_0__arccos
  3 __lpython_overloaded_1__arccos]
 Public )
```

### 33.5.3. Heavy Sugar

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

### 33.5.4. Legacy Sugar

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
## 33.6. EXTERNAL SYMBOL


### 33.6.1. Original ASDL


```c
| ExternalSymbol(symbol_table parent_symtab, ~~~> symtab-id
                 identifier   name,          ~~~> nym
                 symbol       external,      ~~~> extern-symref
                 identifier   module_name,   ~~~> module-nym
                 identifier*  scope_names,   ~~~> scope-nyms
                 identifier   original_name, ~~~> orig-nym
                 access       access)        ~~~> access
```

### 33.6.2. Example

```clojure
#_
(ExternalSymbol
 5 _lpython_main_program
 7 _lpython_main_program   ;; either () or a naked pair
 _global_symbols    []
 _lpython_main_program    Public)
```

### 33.6.3. Heavy Sugar

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

### 33.6.4. Legacy Sugar


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
## 33.7. VARIABLE



### 33.7.1. Original ASDL


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


### 33.7.2. Example


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

### 33.7.3. Light Sugar

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

### 33.7.4. Heavy Sugar

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

### 33.7.5. Legacy Sugar

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
## 33.8. BLOCK



### 33.8.1. Original ASDL


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

### 33.8.2. Legacy Sugar

```clojure
(defmacro Block [SymbolTable
                 blocknym
                 body]
  (let [i_nym (str blocknym)]
   `(Block-- ~SymbolTable ~i_nym ~body)))
```
----------------------------------------------------------------
## 33.9. INTRINSIC MODULE



### 33.9.1. Original ASDL


There is no ASDL for this symbol in our snapshot.

Heavy Sugar

```clojure
(defn IntrinsicModule-- [modnym]
  {::term ::symbol
   ::asr-symbol-head
   {::symbol-head     ::IntrinsicModule
    ::modulenym       modnym}})
```

### 33.9.2. Legacy Sugar

```clojure
(defmacro IntrinsicModule [modnym]
  (let [i_modnym (str modnym)]
   `(IntrinsicModule-- ~i_modnym)))
```


# 34. UNIT


----------------------------------------------------------------
## 34.1. Prerequisite Type Aliases


```clojure
(s/def ::node (s/or :expr   ::expr
                    :stmt   ::stmt
                    :symbol ::symbol))

(defn node [candidate] candidate)
```

## 34.2. Pluralities



TODO: Consider a regex-spec.

```clojure
(s/def ::nodes (.* ::node))
```
----------------------------------------------------------------
## 34.3. TRANSLATION UNIT


### 34.3.1. Heavy Sugar

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


# 35. REGISTRY



Here is a list of all 287 specs defined in this
file as of 12 June 2023, edited by hand to
shorten them and to eliminate built-ins (so
possibly buggy):

```clojure
#_(s/registry)
;; => {
;;     ::ArrayConstant  #o[s$and],
;;     ::ArrayItem  #o[s$and],
;;     ::ArrayReshape  #o[s$and],
;;     ::Assert  #o[s$and],
;;     ::Assignment  #o[s$and],
;;     ::Block  #o[s$and],
;;     ::BlockCall  #o[s$and],
;;     ::Cast  #o[s$and],
;;     ::Character  #o[s$and],
;;     ::Complex  #o[s$and],
;;     ::ComplexBinOp  #o[s$and],
;;     ::ComplexCompare  #o[s$and],
;;     ::ComplexConstant  #o[s$and],
;;     ::ComplexIm  #o[s$and],
;;     ::ComplexRe  #o[s$and],
;;     ::ComplexUnaryMinus  #o[s$and],
;;     ::DoLoop  #o[s$and],
;;     ::ExplicitDeallocate  #o[s$and],
;;     ::ExternalSymbol  #o[s$and],
;;     ::Function  #o[s$and],
;;     ::FunctionCall  #o[s$and],
;;     ::FunctionType  #o[s$and],
;;     ::GenericProcedure  #o[s$and],
;;     ::GetPointer  #o[s$and],
;;     ::GoTo  #o[s$and],
;;     ::If  #o[s$and],
;;     ::IfExp  #o[s$and],
;;     ::Integer  #o[s$and],
;;     ::IntegerBinOp  #o[s$and],
;;     ::IntegerBitNot  #o[s$and],
;;     ::IntegerCompare  #o[s$and],
;;     ::IntegerConstant  #o[s$and],
;;     ::IntegerConstant?  #o[s$or],
;;     ::IntegerUnaryMinus  #o[s$and],
;;     ::IntrinsicFunction  #o[s$and],
;;     ::IntrinsicModule  #o[s$and],
;;     ::List  #o[s$and],
;;     ::ListAppend  #o[s$and],
;;     ::ListConstant  #o[s$and],
;;     ::ListItem  #o[s$and],
;;     ::ListLen  #o[s$and],
;;     ::Logical  #o[s$and],
;;     ::LogicalBinOp  #o[s$and],
;;     ::LogicalCompare  #o[s$and],
;;     ::LogicalConstant  #o[s$and],
;;     ::LogicalNot  #o[s$and],
;;     ::NamedExpr  #o[s$and],
;;     ::Pointer  #o[s$and],
;;     ::Print  #o[s$and],
;;     ::Program  #o[s$and],
;;     ::Real  #o[s$and],
;;     ::RealBinOp  #o[s$and],
;;     ::RealCompare  #o[s$and],
;;     ::RealConstant  #o[s$and],
;;     ::RealUnaryMinus  #o[s$and],
;;     ::Return  #o[s$and],
;;     ::Set  #o[s$and],
;;     ::StringChr  #o[s$and],
;;     ::StringCompare  #o[s$and],
;;     ::StringConcat  #o[s$and],
;;     ::StringConstant  #o[s$and],
;;     ::StringItem  #o[s$and],
;;     ::StringLen  #o[s$and],
;;     ::StringOrd  #o[s$and],
;;     ::StringRepeat  #o[s$and],
;;     ::StringSection  #o[s$and],
;;     ::SubroutineCall  #o[s$and],
;;     ::SymbolTable  #o[s$and],
;;     ::TranslationUnit  #o[s$and],
;;     ::Tuple  #o[s$and],
;;     ::TupleCompare  #o[s$and],
;;     ::TupleConstant  #o[s$and],
;;     ::TupleItem  #o[s$and],
;;     ::TupleLen  #o[s$and],
;;     ::Var  #o[s$and],
;;     ::Variable  #o[s$and],
;;     ::WhileLoop  #o[s$and],
;;     ::abi  #o[s$and],
;;     ::abi-enum  #o[s$r_2053],
;;     ::abi-external ::bool,
;;     ::access  #o[s$and],
;;     ::access-enum  #o[s$r_2053],
;;     ::any-binop  #o[s$r_2053],
;;     ::any-cmpop  #o[s$or],
;;     ::arg ::expr,
;;     ::array-expr  #o[s$or],
;;     ::array-index  #o[s$map],
;;     ::array-index*  #o[s$every$r_2248],
;;     ::array-index-end?
;;     ::array-index-increment?
;;     ::array-index-start?
;;     ::array-shape ::expr,
;;     ::array-value? ::expr?,
;;     ::arraybound  #o[s$and],
;;     ::arraybound-enum  #o[s$r_2053],
;;     ::arraystorage  #o[s$and],
;;     ::arraystorage-enum  #o[s$r_2053],
;;     ::asr-expr-head  #o[s$multi],
;;     ::asr-stmt-head  #o[s$multi],
;;     ::asr-symbol-head  #o[s$multi],
;;     ::asr-term  #o[s$multi],
;;     ::asr-ttype-head  #o[s$multi],
;;     ::asr-unit-head  #o[s$multi],
;;     ::bignat  #o[s$and],
;;     ::bindc-name  #o[s$nilable$r_2550],
;;     ::blocknym ::identifier,
;;     ::body ::stmt*,
;;     ::body-expr ::expr,
;;     ::bool  #o[s$r_2053],
;;     ::call-arg  #o[s$every$r_2248],
;;     ::call-arg-or-args  #o[s$or],
;;     ::call-args  #o[s$every$r_2248],
;;     ::cast-kind  #o[s$and],
;;     ::cast-kind-enum  #o[s$r_2053],
;;     ::character-kind  #o[s$r_2053],
;;     ::character-or-integer-ttype  #o[s$or],
;;     ::character-or-integer-value?  #o[s$or],
;;     ::complex-binop  #o[s$and],
;;     ::complex-binop-enum  #o[s$r_2053],
;;     ::complex-cmpop  #o[s$and],
;;     ::complex-cmpop-enum  #o[s$r_2053],
;;     ::complex-expr,
;;     ::complex-expr?  #o[s$or],
;;     ::complex-kind  #o[s$r_2053],
;;     ::complex-left ::complex-expr,
;;     ::complex-right
;;     ::complex-scalar  #o[s$and],
;;     ::complex-value?
;;     ::deftype  #o[s$and],
;;     ::deftype-enum  #o[s$r_2053],
;;     ::dependencies  ::identifier-set,
;;     ::deterministic ::bool,
;;     ::dimension  #o[s$and],
;;     ::dimension*  #o[s$every$r_2248],
;;     ::dimension-content  #o[s$and],
;;     ::disposition  #o[s$r_2053],
;;     ::do-loop-head  #o[s$map],
;;     ::dt? ::expr?}
;;     ::elemental ::bool,
;;     ::elements ::expr*,
;;     ::end? ::expr?,
;;     ::escape-target  #o[s$r_2053],
;;     ::expr  #o[s$and],
;;     ::expr*  #o[s$every$r_2248],
;;     ::expr?  #o[s$or],
;;     ::extern-symref ::symbol-ref?,
;;     ::float  #o[s$r_2053],
;;     ::format? ::expr?,
;;     ::function-name ::identifier,
;;     ::function-signature  ::FunctionType,
;;     ::goto-target ::nat,
;;     ::hash-map  #o[s$r_2053],
;;     ::identifier  #o[s$r_2053],
;;     ::identifier-list  #o[s$every$r_2248],
;;     ::identifier-set  #o[s$every$r_2248],
;;     ::identifier-suit  #o[s$and],
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
;;     ::int  #o[s$r_2053],
;;     ::integer-binop  #o[s$and],
;;     ::integer-binop-enum  #o[s$r_2053],
;;     ::integer-cmpop  #o[s$and],
;;     ::integer-cmpop-enum  #o[s$r_2053],
;;     ::integer-expr  #o[s$or],
;;     ::integer-expr?  #o[s$or],
;;     ::integer-kind  #o[s$r_2053],
;;     ::integer-left ::integer-expr,
;;     ::integer-right
;;     ::integer-scalar  #o[s$and],
;;     ::integer-scalar-or-expr  #o[s$or],
;;     ::integer-value?
;;     ::intent  #o[s$and],
;;     ::intent-enum  #o[s$r_2053],
;;     ::intrinsic ::bool,
;;     ::intrinsic-ident
;;     ::is-restriction ::bool,
;;     ::label ::int,
;;     ::len ::int,
;;     ::len-expr? ::expr?,
;;     ::list-element  #o[s$or],
;;     ::loaded-from-mod ::bool,
;;     ::logical-cmpop  #o[s$and],
;;     ::logical-cmpop-enum  #o[s$r_2053],
;;     ::logical-expr  #o[s$or],
;;     ::logical-expr?  #o[s$or],
;;     ::logical-kind  #o[s$r_2053],
;;     ::logical-left ::logical-expr,
;;     ::logical-right
;;     ::logical-scalar  #o[s$and],
;;     ::logical-value?
;;     ::logicalbinop  #o[s$and],
;;     ::logicalbinop-enum  #o[s$r_2053],
;;     ::loop-end ::expr?,
;;     ::loop-increment ::expr?,
;;     ::loop-start ::expr?,
;;     ::loop-v ::expr?,
;;     ::lvalue  #o[s$or],
;;     ::message?  #o[s$or],
;;     ::module ::bool,
;;     ::modulenym ::identifier,
;;     ::nat  #o[s$r_2053],
;;     ::node  #o[s$or],
;;     ::nodes  #o[s$every$r_2248],
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
;;     ::presence  #o[s$and],
;;     ::presence-enum  #o[s$r_2053],
;;     ::prognym ::identifier,
;;     ::pure ::bool,
;;     ::real-binop  #o[s$and],
;;     ::real-binop-enum  #o[s$r_2053],
;;     ::real-cmpop  #o[s$and],
;;     ::real-cmpop-enum  #o[s$r_2053],
;;     ::real-expr  #o[s$or],
;;     ::real-expr?  #o[s$or],
;;     ::real-kind  #o[s$r_2053],
;;     ::real-left ::real-expr,
;;     ::real-part ::float,
;;     ::real-right ::real-expr,
;;     ::real-scalar  #o[s$and],
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
;;     ::stmt  #o[s$and],
;;     ::stmt*  #o[s$every$r_2248],
;;     ::stmt?  #o[s$or],
;;     ::storage-type  #o[s$and],
;;     ::storage-type-enum  #o[s$r_2053],
;;     ::string  #o[s$r_2053],
;;     ::string-cmpop  #o[s$and],
;;     ::string-cmpop-enum  #o[s$r_2053],
;;     ::string-expr  #o[s$or],
;;     ::string-expr?  #o[s$or],
;;     ::string-expr?,
;;     ::string-left ::string-expr,
;;     ::string-right ::string-expr,
;;     ::string-scalar  #o[s$and],
;;     ::string-value?
;;     ::symbol  #o[s$and],
;;     ::symbol-ref  #o[s$map],
;;     ::symbol-ref*  #o[s$every$r_2248],
;;     ::symbol-ref?  #o[s$or],
;;     ::symbol?  #o[s$or],
;;     ::symbolic-value ::expr?,
;;     ::symbols  #o[s$every$r_2248],
;;     ::symtab-id ::nat,
;;     ::target ::expr,
;;     ::term  #o[s$r_2053],
;;     ::test-expr ::logical-expr,
;;     ::ttype  #o[s$and],
;;     ::ttype*  #o[s$every$r_2248],
;;     ::ttype?  #o[s$or],
;;     ::tuple-left ::tuple-expr,
;;     ::tuple-right ::tuple-expr,
;;     ::type-declaration  #o[s$nilable$r_2550],
;;     ::type-param* ::ttype*,
;;     ::unchecked-element-expr  #o[s$or],
;;     ::unit  #o[s$and],
;;     ::value ::expr,
;;     ::value* ::expr*,
;;     ::value-attr ::bool,
;;     ::value? ::expr?,
;;     ::varnym ::identifier,
;;     ::vars  #o[s$every$r_2248],
```
