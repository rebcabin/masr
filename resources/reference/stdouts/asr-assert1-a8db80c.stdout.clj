(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        4
                        {
                            :test_assert
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
                                                )
                                        })
                                    test_assert
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
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 a)
                                            Eq
                                            (IntegerConstant 5 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        (StringConstant
                                            "a is not 5"
                                            (Character 1 10 () [])
                                        )
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 a)
                                            NotEq
                                            (IntegerConstant 10 (Integer 4 []))
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
