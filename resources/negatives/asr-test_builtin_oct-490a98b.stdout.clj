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
                                    [test_oct]
                                    []
                                    [(SubroutineCall
                                        112 test_oct
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_oct
                                (Function
                                    (SymbolTable
                                        2
                                        {
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
                                                ),
                                            :oct
                                                (ExternalSymbol
                                                    2
                                                    oct
                                                    4 oct
                                                    lpython_builtin
                                                    []
                                                    oct
                                                    0xBADBEEF
                                                )
                                        })
                                    test_oct
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
                                    [oct]
                                    []
                                    [(=
                                        (Var 2 i)
                                        (IntegerConstant 34 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (FunctionCall
                                                2 oct
                                                ()
                                                [((Var 2 i))]
                                                (Character 1 -2 () [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "0o42"
                                                (Character 1 4 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i)
                                        (IntegerUnaryMinus
                                            (IntegerConstant 4235 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant -4235 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (FunctionCall
                                                2 oct
                                                ()
                                                [((Var 2 i))]
                                                (Character 1 -2 () [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "-0o10213"
                                                (Character 1 8 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (FunctionCall
                                                2 oct
                                                ()
                                                [((IntegerConstant 34 (Integer 4 [])))]
                                                (Character 1 -2 () [])
                                                (StringConstant
                                                    "0o42"
                                                    (Character 1 4 () [])
                                                )
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "0o42"
                                                (Character 1 4 () [])
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
                                                2 oct
                                                ()
                                                [((IntegerUnaryMinus
                                                    (IntegerConstant 4235 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -4235 (Integer 4 []))
                                                ))]
                                                (Character 1 -2 () [])
                                                (StringConstant
                                                    "-0o10213"
                                                    (Character 1 8 () [])
                                                )
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "-0o10213"
                                                (Character 1 8 () [])
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
