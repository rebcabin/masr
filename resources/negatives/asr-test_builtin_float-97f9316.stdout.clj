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
                                    [test_float]
                                    []
                                    [(SubroutineCall
                                        5 test_float
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_float
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :b
                                                (Variable
                                                    2
                                                    b
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
                                                ),
                                            :f
                                                (Variable
                                                    2
                                                    f
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :f2
                                                (Variable
                                                    2
                                                    f2
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
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
                                    test_float
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
                                        (IntegerConstant 34 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 f)
                                        (Cast
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                0.000000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
                                            )
                                            Eq
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
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
                                        (RealCompare
                                            (Cast
                                                (IntegerConstant 34 (Integer 4 []))
                                                IntegerToReal
                                                (Real 8 [])
                                                (RealConstant
                                                    34.000000
                                                    (Real 8 [])
                                                )
                                            )
                                            Eq
                                            (RealConstant
                                                34.000000
                                                (Real 8 [])
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
                                        (RealCompare
                                            (Cast
                                                (Var 2 i)
                                                IntegerToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Eq
                                            (RealConstant
                                                34.000000
                                                (Real 8 [])
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
                                        (RealCompare
                                            (Cast
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 4235 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -4235 (Integer 4 []))
                                                )
                                                IntegerToReal
                                                (Real 8 [])
                                                (RealConstant
                                                    -4235.000000
                                                    (Real 8 [])
                                                )
                                            )
                                            Eq
                                            (RealUnaryMinus
                                                (RealConstant
                                                    4235.000000
                                                    (Real 8 [])
                                                )
                                                (Real 8 [])
                                                (RealConstant
                                                    -4235.000000
                                                    (Real 8 [])
                                                )
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
                                        (RealCompare
                                            (Cast
                                                (Var 2 i)
                                                IntegerToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Eq
                                            (RealUnaryMinus
                                                (RealConstant
                                                    4235.000000
                                                    (Real 8 [])
                                                )
                                                (Real 8 [])
                                                (RealConstant
                                                    -4235.000000
                                                    (Real 8 [])
                                                )
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (Cast
                                                (IntegerConstant 34 (Integer 4 []))
                                                IntegerToReal
                                                (Real 8 [])
                                                (RealConstant
                                                    34.000000
                                                    (Real 8 [])
                                                )
                                            )
                                            Eq
                                            (RealConstant
                                                34.000000
                                                (Real 8 [])
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
                                        (RealCompare
                                            (Cast
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 4235 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -4235 (Integer 4 []))
                                                )
                                                IntegerToReal
                                                (Real 8 [])
                                                (RealConstant
                                                    -4235.000000
                                                    (Real 8 [])
                                                )
                                            )
                                            Eq
                                            (RealUnaryMinus
                                                (RealConstant
                                                    4235.000000
                                                    (Real 8 [])
                                                )
                                                (Integer 8 []) ;; badbeef !
                                                (RealConstant
                                                    -4235.000000
                                                    (Real 8 [])
                                                )
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
                                        (RealCompare
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
                                            )
                                            Eq
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
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
                                        (RealCompare
                                            (Cast
                                                (Var 2 f)
                                                RealToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Eq
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToReal
                                                (Real 8 [])
                                                (RealConstant
                                                    1.000000
                                                    (Real 8 [])
                                                )
                                            )
                                            Eq
                                            (RealConstant
                                                1.000000
                                                (Real 8 [])
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
                                        (RealCompare
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToReal
                                                (Real 8 [])
                                                (RealConstant
                                                    0.000000
                                                    (Real 8 [])
                                                )
                                            )
                                            Eq
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b)
                                        (LogicalConstant
                                            true
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 f2)
                                        (Cast
                                            (Var 2 b)
                                            LogicalToReal
                                            (Real 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (Var 2 f2)
                                            Eq
                                            (RealConstant
                                                1.000000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b)
                                        (LogicalConstant
                                            false
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (Cast
                                                (Var 2 b)
                                                LogicalToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Eq
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
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
