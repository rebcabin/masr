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
                                    [main]
                                    []
                                    [(SubroutineCall
                                        112 main
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :main
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :pow
                                                (ExternalSymbol
                                                    2
                                                    pow
                                                    4 pow
                                                    lpython_builtin
                                                    []
                                                    pow
                                                    Private
                                                ),
                                            :pow__AT____lpython_overloaded_0__pow
                                                (ExternalSymbol
                                                    2
                                                    pow__AT____lpython_overloaded_0__pow
                                                    4 __lpython_overloaded_0__pow
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_0__pow
                                                    Public
                                                )
                                        })
                                    main
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
                                    [pow__AT____lpython_overloaded_0__pow]
                                    []
                                    [(Print
                                        ()
                                        [(FunctionCall
                                            2 pow__AT____lpython_overloaded_0__pow
                                            2 pow
                                            [((IntegerConstant 2 (Integer 4 [])))
                                            ((IntegerConstant 2 (Integer 4 [])))]
                                            (Real 8 [])
                                            (RealConstant
                                                4.000000
                                                (Real 8 [])
                                            )
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(IntegerBinOp
                                            (IntegerConstant 2 (Integer 4 []))
                                            Pow
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant 4 (Integer 4 []))
                                        )]
                                        ()
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
