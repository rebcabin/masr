(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        7
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        6
                                        {
                                            
                                        })
                                    _lpython_main_program
                                    (FunctionType
                                        []
                                        ()
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        false
                                        false
                                        []
                                        []
                                        false
                                    )
                                    [test_ord
                                    test_chr
                                    more_test]
                                    []
                                    [(SubroutineCall
                                        7 test_ord
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        7 test_chr
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        7 more_test
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :more_test
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :a
                                                (Variable
                                                    4
                                                    a
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :b
                                                (Variable
                                                    4
                                                    b
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :c
                                                (Variable
                                                    4
                                                    c
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :d
                                                (Variable
                                                    4
                                                    d
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :p
                                                (Variable
                                                    4
                                                    p
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :q
                                                (Variable
                                                    4
                                                    q
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :r
                                                (Variable
                                                    4
                                                    r
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :s
                                                (Variable
                                                    4
                                                    s
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    more_test
                                    (FunctionType
                                        []
                                        ()
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        false
                                        false
                                        []
                                        []
                                        false
                                    )
                                    []
                                    []
                                    [(=
                                        (Var 4 p)
                                        (IntegerConstant 97 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 4 q)
                                        (IntegerConstant 112 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 4 r)
                                        (IntegerConstant 10 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 4 s)
                                        (IntegerConstant 65 (Integer 4 []))
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(StringChr
                                            (Var 4 p)
                                            (Character 1 1 () [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(StringChr
                                            (Var 4 q)
                                            (Character 1 1 () [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(StringChr
                                            (Var 4 r)
                                            (Character 1 1 () [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(StringChr
                                            (Var 4 s)
                                            (Character 1 1 () [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (=
                                        (Var 4 a)
                                        (StringConstant
                                            "!"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 b)
                                        (StringConstant
                                            " "
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 c)
                                        (StringConstant
                                            "Z"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 d)
                                        (StringConstant
                                            "g"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(StringOrd
                                            (Var 4 a)
                                            (Integer 4 [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(StringOrd
                                            (Var 4 b)
                                            (Integer 4 [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(StringOrd
                                            (Var 4 c)
                                            (Integer 4 [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(StringOrd
                                            (Var 4 d)
                                            (Integer 4 [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_chr
                                (Function
                                    (SymbolTable
                                        3
                                        {
                                            :capital_a
                                                (Variable
                                                    3
                                                    capital_a
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :capital_z
                                                (Variable
                                                    3
                                                    capital_z
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :dollar
                                                (Variable
                                                    3
                                                    dollar
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :exclamation
                                                (Variable
                                                    3
                                                    exclamation
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :i
                                                (Variable
                                                    3
                                                    i
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :left_parenthesis
                                                (Variable
                                                    3
                                                    left_parenthesis
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :nine
                                                (Variable
                                                    3
                                                    nine
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :plus
                                                (Variable
                                                    3
                                                    plus
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :right_brace
                                                (Variable
                                                    3
                                                    right_brace
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :right_bracket
                                                (Variable
                                                    3
                                                    right_bracket
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :semicolon
                                                (Variable
                                                    3
                                                    semicolon
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :small_a
                                                (Variable
                                                    3
                                                    small_a
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :small_z
                                                (Variable
                                                    3
                                                    small_z
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :zero
                                                (Variable
                                                    3
                                                    zero
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_chr
                                    (FunctionType
                                        []
                                        ()
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        false
                                        false
                                        []
                                        []
                                        false
                                    )
                                    []
                                    []
                                    [(=
                                        (Var 3 i)
                                        (IntegerConstant 33 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 exclamation)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 33 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "!"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "!"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 exclamation)
                                            Eq
                                            (StringConstant
                                                "!"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 36 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 dollar)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 36 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "$"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "$"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 dollar)
                                            Eq
                                            (StringConstant
                                                "$"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 40 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 left_parenthesis)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 40 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "("
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "("
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 left_parenthesis)
                                            Eq
                                            (StringConstant
                                                "("
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 43 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 plus)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 43 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "+"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "+"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 plus)
                                            Eq
                                            (StringConstant
                                                "+"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 48 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 zero)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 48 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "0"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "0"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 zero)
                                            Eq
                                            (StringConstant
                                                "0"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 57 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 nine)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 57 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "9"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "9"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 nine)
                                            Eq
                                            (StringConstant
                                                "9"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 59 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 semicolon)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 59 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    ";"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                ";"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 semicolon)
                                            Eq
                                            (StringConstant
                                                ";"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 65 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 capital_a)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 65 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "A"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "A"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 capital_a)
                                            Eq
                                            (StringConstant
                                                "A"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 90 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 capital_z)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 90 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "Z"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "Z"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 capital_z)
                                            Eq
                                            (StringConstant
                                                "Z"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 93 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 right_bracket)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 93 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "]"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "]"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 right_bracket)
                                            Eq
                                            (StringConstant
                                                "]"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 97 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 small_a)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 97 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "a"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 small_a)
                                            Eq
                                            (StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 122 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 small_z)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 122 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "z"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "z"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 small_z)
                                            Eq
                                            (StringConstant
                                                "z"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 i)
                                        (IntegerConstant 125 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 right_brace)
                                        (StringChr
                                            (Var 3 i)
                                            (Character 1 1 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringChr
                                                (IntegerConstant 125 (Integer 4 []))
                                                (Character 1 1 () [])
                                                (StringConstant
                                                    "}"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "}"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 right_brace)
                                            Eq
                                            (StringConstant
                                                "}"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_ord
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :capital_a_unicode
                                                (Variable
                                                    2
                                                    capital_a_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :capital_z_unicode
                                                (Variable
                                                    2
                                                    capital_z_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :dollar_unicode
                                                (Variable
                                                    2
                                                    dollar_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :exclamation_unicode
                                                (Variable
                                                    2
                                                    exclamation_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :left_parenthesis_unicode
                                                (Variable
                                                    2
                                                    left_parenthesis_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :nine_unicode
                                                (Variable
                                                    2
                                                    nine_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :plus_unicode
                                                (Variable
                                                    2
                                                    plus_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :right_brace_unicode
                                                (Variable
                                                    2
                                                    right_brace_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :right_bracket_unicode
                                                (Variable
                                                    2
                                                    right_bracket_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :s
                                                (Variable
                                                    2
                                                    s
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :semicolon_unicode
                                                (Variable
                                                    2
                                                    semicolon_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :small_a_unicode
                                                (Variable
                                                    2
                                                    small_a_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :small_z_unicode
                                                (Variable
                                                    2
                                                    small_z_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :zero_unicode
                                                (Variable
                                                    2
                                                    zero_unicode
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_ord
                                    (FunctionType
                                        []
                                        ()
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        false
                                        false
                                        []
                                        []
                                        false
                                    )
                                    []
                                    []
                                    [(=
                                        (Var 2 s)
                                        (StringConstant
                                            "!"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 exclamation_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "!"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 33 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 33 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 exclamation_unicode)
                                            Eq
                                            (IntegerConstant 33 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "$"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 dollar_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "$"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 36 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 36 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 dollar_unicode)
                                            Eq
                                            (IntegerConstant 36 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "("
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 left_parenthesis_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "("
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 40 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 40 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 left_parenthesis_unicode)
                                            Eq
                                            (IntegerConstant 40 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "+"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 plus_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "+"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 43 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 43 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 plus_unicode)
                                            Eq
                                            (IntegerConstant 43 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "0"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 zero_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "0"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 48 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 48 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 zero_unicode)
                                            Eq
                                            (IntegerConstant 48 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "9"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 nine_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "9"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 57 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 57 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 nine_unicode)
                                            Eq
                                            (IntegerConstant 57 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            ";"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 semicolon_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    ";"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 59 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 59 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 semicolon_unicode)
                                            Eq
                                            (IntegerConstant 59 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "A"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 capital_a_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "A"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 65 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 65 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 capital_a_unicode)
                                            Eq
                                            (IntegerConstant 65 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "Z"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 capital_z_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "Z"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 90 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 90 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 capital_z_unicode)
                                            Eq
                                            (IntegerConstant 90 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "]"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 right_bracket_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "]"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 93 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 93 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 right_bracket_unicode)
                                            Eq
                                            (IntegerConstant 93 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "a"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 small_a_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "a"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 97 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 97 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 small_a_unicode)
                                            Eq
                                            (IntegerConstant 97 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "z"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 small_z_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "z"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 122 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 122 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 small_z_unicode)
                                            Eq
                                            (IntegerConstant 122 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "}"
                                            (Character 1 1 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 right_brace_unicode)
                                        (StringOrd
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringOrd
                                                (StringConstant
                                                    "}"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 125 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 125 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 right_brace_unicode)
                                            Eq
                                            (IntegerConstant 125 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                )
                        })
                    _global_symbols
                    []
                    false
                    false
                ),
            :main_program
                (Program
                    (SymbolTable
                        5
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    5
                                    _lpython_main_program
                                    7 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        5 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
