(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        4
                        {
                            :test_ifexp
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
                                                    (Integer 4 [])
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
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :c
                                                (Variable
                                                    2
                                                    c
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
                                                )
                                        })
                                    test_ifexp
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
                                        (IntegerConstant 2 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 b)
                                        (IfExp
                                            (IntegerCompare
                                                (Var 2 a)
                                                Eq
                                                (IntegerConstant 2 (Integer 4 []))
                                                (Logical 4 [])
                                                ()
                                            )
                                            (IntegerConstant 6 (Integer 4 []))
                                            (IntegerConstant 8 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (IfExp
                                            (IntegerCompare
                                                (Var 2 b)
                                                Gt
                                                (IntegerConstant 5 (Integer 4 []))
                                                (Logical 4 [])
                                                ()
                                            )
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
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
