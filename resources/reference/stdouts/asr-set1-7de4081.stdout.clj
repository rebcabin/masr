(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        4
                        {
                            :test_Set
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
                                                    (Set
                                                        (Integer 4 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :b
                                                (Variable
                                                    2
                                                    b
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Set
                                                        (Character 1 -2 () [])
                                                    )
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
                                                )
                                        })
                                    test_Set
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
                                        (Var 2 a)
                                        (SetConstant
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))
                                            (IntegerConstant 3 (Integer 4 []))]
                                            (Set
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (SetConstant
                                            [(IntegerConstant 2 (Integer 4 []))
                                            (IntegerConstant 3 (Integer 4 []))
                                            (IntegerConstant 4 (Integer 4 []))
                                            (IntegerConstant 5 (Integer 4 []))
                                            (IntegerConstant 5 (Integer 4 []))]
                                            (Set
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (SetInsert
                                        (Var 2 a)
                                        (IntegerConstant 9 (Integer 4 []))
                                    )
                                    (SetRemove
                                        (Var 2 a)
                                        (IntegerConstant 4 (Integer 4 []))
                                    )
                                    (=
                                        (Var 2 b)
                                        (SetConstant
                                            [(StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (StringConstant
                                                "b"
                                                (Character 1 1 () [])
                                            )
                                            (StringConstant
                                                "c"
                                                (Character 1 1 () [])
                                            )]
                                            (Set
                                                (Character 1 1 () [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (SetPop
                                            (Var 2 b)
                                            (Character 1 -2 () [])
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
