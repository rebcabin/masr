(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        5
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        4
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
                                    [test_global_symbols]
                                    []
                                    [(=
                                        (Var 5 x)
                                        (ListConstant
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))]
                                            (List
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 5 i)
                                        (ListItem
                                            (Var 5 x)
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (SubroutineCall
                                        5 test_global_symbols
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :i
                                (Variable
                                    5
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
                            :test_global_symbols
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            
                                        })
                                    test_global_symbols
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
                                    [(Assert
                                        (IntegerCompare
                                            (Var 5 i)
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (ListItem
                                                (Var 5 x)
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 2 (Integer 4 []))
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
                            :x
                                (Variable
                                    5
                                    x
                                    []
                                    Local
                                    ()
                                    ()
                                    Default
                                    (List
                                        (Integer 4 [])
                                    )
                                    Source
                                    Public
                                    Required
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
                            :_lpython_main_program
                                (ExternalSymbol
                                    3
                                    _lpython_main_program
                                    5 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        3 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
