(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        4
                        {
                            :test_StrOp_concat
                                (Function
                                    (SymbolTable
                                        2
                                        {
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
                                                )
                                        })
                                    test_StrOp_concat
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
                                        (StringConcat
                                            (StringConstant
                                                "3"
                                                (Character 1 1 () [])
                                            )
                                            (StringConstant
                                                "4"
                                                (Character 1 1 () [])
                                            )
                                            (Character 1 2 () [])
                                            (StringConstant
                                                "34"
                                                (Character 1 2 () [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConcat
                                            (StringConstant
                                                "a "
                                                (Character 1 2 () [])
                                            )
                                            (StringConstant
                                                "test"
                                                (Character 1 4 () [])
                                            )
                                            (Character 1 6 () [])
                                            (StringConstant
                                                "a test"
                                                (Character 1 6 () [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConcat
                                            (StringConcat
                                                (StringConstant
                                                    "test"
                                                    (Character 1 4 () [])
                                                )
                                                (StringConstant
                                                    "test"
                                                    (Character 1 4 () [])
                                                )
                                                (Character 1 8 () [])
                                                (StringConstant
                                                    "testtest"
                                                    (Character 1 8 () [])
                                                )
                                            )
                                            (StringConstant
                                                "test"
                                                (Character 1 4 () [])
                                            )
                                            (Character 1 12 () [])
                                            (StringConstant
                                                "testtesttest"
                                                (Character 1 12 () [])
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
                    []
                    false
                    false
                ),
            :main_program
                (Program
                    (SymbolTable
                        3
                        {
                            
                        })
                    main_program
                    []
                    []
                )
        })
    []
)
