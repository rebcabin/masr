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
                                    [f]
                                    []
                                    [(SubroutineCall
                                        5 f
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :f
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
                                            :res
                                                (Variable
                                                    2
                                                    res
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
                                    f
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
                                        (Var 2 i)
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 res)
                                        (IntegerBitNot
                                            (Var 2 i)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 res)
                                            Eq
                                            (IntegerUnaryMinus
                                                (IntegerConstant 6 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -6 (Integer 4 []))
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i)
                                        (IntegerUnaryMinus
                                            (IntegerConstant 235346 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant -235346 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (IntegerBitNot
                                                (Var 2 i)
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 235345 (Integer 4 []))
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
