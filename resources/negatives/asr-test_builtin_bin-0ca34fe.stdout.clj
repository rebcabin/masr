(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        112
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        111
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
                                    [test_bin]
                                    []
                                    [(SubroutineCall
                                        112 test_bin
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_bin
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :bin
                                                (ExternalSymbol
                                                    2
                                                    bin
                                                    4 bin
                                                    lpython_builtin
                                                    []
                                                    bin
                                                    Private
                                                ),
                                            :i
                                                (Variable
                                                    2
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
                                                )
                                        })
                                    test_bin
                                    (FunctionType
                                        []
                                        ()
                                        Source
                                        Implementation
                                        ()
                                        0xBADBEEF
                                        false
                                        false
                                        false
                                        false
                                        []
                                        []
                                        false
                                    )
                                    [bin]
                                    []
                                    [(=
                                        (Var 2 i)
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (FunctionCall
                                                2 bin
                                                ()
                                                [((Var 2 i))]
                                                (Character 1 -2 () [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "0b101"
                                                (Character 1 5 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i)
                                        (IntegerConstant 64 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (FunctionCall
                                                2 bin
                                                ()
                                                [((Var 2 i))]
                                                (Character 1 -2 () [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "0b1000000"
                                                (Character 1 9 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i)
                                        (IntegerUnaryMinus
                                            (IntegerConstant 534 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant -534 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (FunctionCall
                                                2 bin
                                                ()
                                                [((Var 2 i))]
                                                (Character 1 -2 () [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "-0b1000010110"
                                                (Character 1 13 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (FunctionCall
                                                2 bin
                                                ()
                                                [((IntegerConstant 64 (Integer 4 [])))]
                                                (Character 1 -2 () [])
                                                (StringConstant
                                                    "0b1000000"
                                                    (Character 1 9 () [])
                                                )
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "0b1000000"
                                                (Character 1 9 () [])
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
                                            (FunctionCall
                                                2 bin
                                                ()
                                                [((IntegerUnaryMinus
                                                    (IntegerConstant 534 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -534 (Integer 4 []))
                                                ))]
                                                (Character 1 -2 () [])
                                                (StringConstant
                                                    "-0b1000010110"
                                                    (Character 1 13 () [])
                                                )
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "-0b1000010110"
                                                (Character 1 13 () [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
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
                            :_lpython_main_program
                                (ExternalSymbol
                                    110
                                    _lpython_main_program
                                    112 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        110 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
