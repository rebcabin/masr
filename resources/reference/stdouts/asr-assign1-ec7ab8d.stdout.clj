(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        4
                        {
                            :test_augassign
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
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :r
                                                (Variable
                                                    2
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
                                                    2
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
                                    test_augassign
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
                                        (Var 2 r)
                                        (IntegerConstant 0 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 r)
                                        (IntegerBinOp
                                            (Var 2 r)
                                            Add
                                            (IntegerConstant 4 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 r)
                                        (IntegerBinOp
                                            (Var 2 r)
                                            Mul
                                            (Var 2 s)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 r)
                                        (IntegerBinOp
                                            (Var 2 r)
                                            Sub
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (IntegerConstant 10 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 r)
                                        (RealBinOp
                                            (Cast
                                                (Var 2 r)
                                                IntegerToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Div
                                            (Cast
                                                (Var 2 s)
                                                IntegerToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            (Real 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (StringConstant
                                            ""
                                            (Character 1 0 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (StringConcat
                                            (Var 2 a)
                                            (StringConstant
                                                "test"
                                                (Character 1 4 () [])
                                            )
                                            (Character 1 2 () [])
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
