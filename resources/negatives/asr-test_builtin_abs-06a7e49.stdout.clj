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
                                    [test_abs]
                                    []
                                    [(SubroutineCall
                                        5 test_abs
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_abs
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
                                            :i2
                                                (Variable
                                                    2
                                                    i2
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :i3
                                                (Variable
                                                    2
                                                    i3
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 1 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :i4
                                                (Variable
                                                    2
                                                    i4
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 2 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    2
                                                    x
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
                                            :x2
                                                (Variable
                                                    2
                                                    x2
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
                                                )
                                        })
                                    test_abs
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
                                        (Var 2 x)
                                        (RealConstant
                                            5.500000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(Var 2 x)]
                                                0
                                                (Real 8 [])
                                                ()
                                            )
                                            Eq
                                            (RealConstant
                                                5.500000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (RealUnaryMinus
                                            (RealConstant
                                                5.500000
                                                (Real 8 [])
                                            )
                                            (Real 8 [])
                                            (RealConstant
                                                -5.500000
                                                (Real 8 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(Var 2 x)]
                                                0
                                                (Real 8 [])
                                                ()
                                            )
                                            Eq
                                            (RealConstant
                                                5.500000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(RealConstant
                                                    5.500000
                                                    (Real 8 [])
                                                )]
                                                0
                                                (Real 8 [])
                                                (RealConstant
                                                    5.500000
                                                    (Real 8 [])
                                                )
                                            )
                                            Eq
                                            (RealConstant
                                                5.500000
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
                                            (IntrinsicFunction
                                                Abs
                                                [(RealUnaryMinus
                                                    (RealConstant
                                                        5.500000
                                                        (Real 8 [])
                                                    )
                                                    (Real 8 [])
                                                    (RealConstant
                                                        -5.500000
                                                        (Real 8 [])
                                                    )
                                                )]
                                                0
                                                (Real 8 [])
                                                (RealConstant
                                                    5.500000
                                                    (Real 8 [])
                                                )
                                            )
                                            Eq
                                            (RealConstant
                                                5.500000
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
                                        (Var 2 x2)
                                        (RealUnaryMinus
                                            (Cast
                                                (RealConstant
                                                    5.500000
                                                    (Real 8 [])
                                                )
                                                RealToReal
                                                (Real 4 [])
                                                (RealConstant
                                                    5.500000
                                                    (Real 4 [])
                                                )
                                            )
                                            (Real 4 [])
                                            (RealConstant
                                                -5.500000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(Var 2 x2)]
                                                0
                                                (Real 4 [])
                                                ()
                                            )
                                            Eq
                                            (Cast
                                                (RealConstant
                                                    5.500000
                                                    (Real 8 [])
                                                )
                                                RealToReal
                                                (Real 4 [])
                                                (RealConstant
                                                    5.500000
                                                    (Real 4 [])
                                                )
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i)
                                        (IntegerUnaryMinus
                                            (IntegerConstant 5 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant -5 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(Var 2 i)]
                                                0
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 0xBADBEEF (Integer 0xBADBEEF []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(IntegerUnaryMinus
                                                    (IntegerConstant 1 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -1 (Integer 4 []))
                                                )]
                                                0
                                                (Integer 4 [])
                                                (IntegerConstant 1 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i2)
                                        (IntegerUnaryMinus
                                            (Cast
                                                (IntegerConstant 6 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 6 (Integer 8 []))
                                            )
                                            (Integer 8 [])
                                            (IntegerConstant -6 (Integer 8 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(Var 2 i2)]
                                                0
                                                (Integer 8 [])
                                                ()
                                            )
                                            Eq
                                            (Cast
                                                (IntegerConstant 6 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 6 (Integer 8 []))
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i3)
                                        (IntegerUnaryMinus
                                            (Cast
                                                (IntegerConstant 7 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 1 [])
                                                (IntegerConstant 7 (Integer 1 []))
                                            )
                                            (Integer 1 [])
                                            (IntegerConstant -7 (Integer 1 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(Var 2 i3)]
                                                0
                                                (Integer 1 [])
                                                ()
                                            )
                                            Eq
                                            (Cast
                                                (IntegerConstant 7 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 1 [])
                                                (IntegerConstant 7 (Integer 1 []))
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i4)
                                        (IntegerUnaryMinus
                                            (Cast
                                                (IntegerConstant 8 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 2 [])
                                                (IntegerConstant 8 (Integer 2 []))
                                            )
                                            (Integer 2 [])
                                            (IntegerConstant -8 (Integer 2 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(Var 2 i4)]
                                                0
                                                (Integer 2 [])
                                                ()
                                            )
                                            Eq
                                            (Cast
                                                (IntegerConstant 8 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 2 [])
                                                (IntegerConstant 8 (Integer 2 []))
                                            )
                                            (Logical 4 [])
                                            ()
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
                                    (Assert
                                        (IntegerCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(Cast
                                                    (Var 2 b)
                                                    LogicalToInteger
                                                    (Integer 4 [])
                                                    ()
                                                )]
                                                0
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
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
                                        (IntegerCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(Cast
                                                    (Var 2 b)
                                                    LogicalToInteger
                                                    (Integer 4 [])
                                                    ()
                                                )]
                                                0
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
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
