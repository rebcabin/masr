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
                                    [test_hex]
                                    []
                                    [(SubroutineCall
                                        112 test_hex
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_hex
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :hex
                                                (ExternalSymbol
                                                    2
                                                    hex
                                                    4 hex
                                                    lpython_builtin
                                                    []
                                                    hex
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
                                    test_hex
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
                                    [hex]
                                    []
                                    [(=
                                        (Var 2 i)
                                        (IntegerConstant 34 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (FunctionCall
                                                2 hex
                                                ()
                                                [((Var 2 i))]
                                                (Character 1 -2 () [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "0x22"
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
                                                2 hex
                                                ()
                                                [((Var 2 i))]
                                                (Character 1 -2 () [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "-0x108b"
                                                (Character 1 7 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (FunctionCall
                                                2 hex
                                                ()
                                                [((IntegerConstant 34 (Integer 4 [])))]
                                                (Character 1 -2 () [])
                                                (StringConstant
                                                    "0x22"
                                                    (Character 1 4 () [])
                                                )
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "0x22"
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
                                                2 hex
                                                ()
                                                [((IntegerUnaryMinus
                                                    (IntegerConstant 4235 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -4235 (Integer 4 []))
                                                ))]
                                                (Character 1 -2 () [])
                                                (StringConstant
                                                    "-0x108b"
                                                    (Character 1 7 () [])
                                                )
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "-0x108b"
                                                (Character 1 7 () [])
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
