(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        111
                        {
                            :test_Compare
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :a
                                                (Variable
                                                    2
                                                    a
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Logical 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :complex
                                                (ExternalSymbol
                                                    2
                                                    complex
                                                    4 complex
                                                    lpython_builtin
                                                    []
                                                    complex
                                                    Private
                                                ),
                                            complex@:__lpython_overloaded_5__complex
                                                (ExternalSymbol
                                                    2
                                                    complex@__lpython_overloaded_5__complex
                                                    4 __lpython_overloaded_5__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_5__complex
                                                    Public
                                                ),
                                            complex@:__lpython_overloaded_9__complex
                                                (ExternalSymbol
                                                    2
                                                    complex@__lpython_overloaded_9__complex
                                                    4 __lpython_overloaded_9__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_9__complex
                                                    Public
                                                )
                                        })
                                    test_Compare
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
                                    [complex@__lpython_overloaded_9__complex
                                    complex@__lpython_overloaded_5__complex]
                                    []
                                    [(=
                                        (Var 2 a)
                                        (IntegerCompare
                                            (IntegerConstant 5 (Integer 4 []))
                                            Gt
                                            (IntegerConstant 4 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (IntegerCompare
                                            (IntegerConstant 5 (Integer 4 []))
                                            LtE
                                            (IntegerConstant 4 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (IntegerCompare
                                            (IntegerConstant 5 (Integer 4 []))
                                            Lt
                                            (IntegerConstant 4 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (RealCompare
                                            (RealConstant
                                                5.600000
                                                (Real 8 [])
                                            )
                                            GtE
                                            (RealConstant
                                                5.599990
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (RealCompare
                                            (RealConstant
                                                3.300000
                                                (Real 8 [])
                                            )
                                            Eq
                                            (RealConstant
                                                3.300000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (RealCompare
                                            (RealConstant
                                                3.300000
                                                (Real 8 [])
                                            )
                                            NotEq
                                            (RealConstant
                                                3.400000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (ComplexCompare
                                            (FunctionCall
                                                2 complex@__lpython_overloaded_9__complex
                                                2 complex
                                                [((IntegerConstant 3 (Integer 4 [])))
                                                ((IntegerConstant 4 (Integer 4 [])))]
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    3.000000
                                                    4.000000
                                                    (Complex 8 [])
                                                )
                                                ()
                                            )
                                            Eq
                                            (FunctionCall
                                                2 complex@__lpython_overloaded_5__complex
                                                2 complex
                                                [((RealConstant
                                                    3.000000
                                                    (Real 8 [])
                                                ))
                                                ((RealConstant
                                                    4.000000
                                                    (Real 8 [])
                                                ))]
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    3.000000
                                                    4.000000
                                                    (Complex 8 [])
                                                )
                                                ()
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (StringCompare
                                            (StringConstant
                                                "abc"
                                                (Character 1 3 () [])
                                            )
                                            Gt
                                            (StringConstant
                                                "abd"
                                                (Character 1 3 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (StringCompare
                                            (StringConstant
                                                ""
                                                (Character 1 0 () [])
                                            )
                                            Lt
                                            (StringConstant
                                                "s"
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
                                    (=
                                        (Var 2 a)
                                        (StringCompare
                                            (StringConstant
                                                "-abs"
                                                (Character 1 4 () [])
                                            )
                                            GtE
                                            (StringConstant
                                                "abs"
                                                (Character 1 3 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (StringCompare
                                            (StringConstant
                                                "abcd"
                                                (Character 1 4 () [])
                                            )
                                            LtE
                                            (StringConstant
                                                "abcde"
                                                (Character 1 5 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (StringCompare
                                            (StringConstant
                                                "abc"
                                                (Character 1 3 () [])
                                            )
                                            Eq
                                            (StringConstant
                                                "abc"
                                                (Character 1 3 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (StringCompare
                                            (StringConstant
                                                "abc"
                                                (Character 1 3 () [])
                                            )
                                            NotEq
                                            (StringConstant
                                                "abd"
                                                (Character 1 3 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (StringCompare
                                            (StringConstant
                                                ""
                                                (Character 1 0 () [])
                                            )
                                            Eq
                                            (StringConstant
                                                "+"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (LogicalCompare
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                            Gt
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (LogicalCompare
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                            Eq
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (LogicalCompare
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
                                            NotEq
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (LogicalCompare
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
                                            GtE
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
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
                    [lpython_builtin]
                    false
                    false
                ),
            :lpython_builtin
                (IntrinsicModule lpython_builtin),
            :main_program
                (Program
                    (SymbolTable
                        110
                        {
                            
                        })
                    main_program
                    []
                    []
                )
        })
    []
)
