(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        111
                        {
                            :test_binop
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :_lpython_floordiv
                                                (ExternalSymbol
                                                    2
                                                    _lpython_floordiv
                                                    4 _lpython_floordiv
                                                    lpython_builtin
                                                    []
                                                    _lpython_floordiv
                                                    Private
                                                ),
                                            :_lpython_floordiv__AT____lpython_overloaded_6___lpython_floordiv
                                                (ExternalSymbol
                                                    2
                                                    _lpython_floordiv__AT____lpython_overloaded_6___lpython_floordiv
                                                    4 __lpython_overloaded_6___lpython_floordiv
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_6___lpython_floordiv
                                                    Public
                                                ),
                                            :b1
                                                (Variable
                                                    2
                                                    b1
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
                                            :b2
                                                (Variable
                                                    2
                                                    b2
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
                                            :x
                                                (Variable
                                                    2
                                                    x
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
                                    test_binop
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
                                    [_lpython_floordiv__AT____lpython_overloaded_6___lpython_floordiv]
                                    []
                                    [(=
                                        (Var 2 x)
                                        (IntegerBinOp
                                            (IntegerConstant 2 (Integer 4 []))
                                            Pow
                                            (IntegerConstant 3 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant 8 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x2)
                                        (Cast
                                            (RealBinOp
                                                (RealConstant
                                                    2.000000
                                                    (Real 8 [])
                                                )
                                                Pow
                                                (RealConstant
                                                    3.500000
                                                    (Real 8 [])
                                                )
                                                (Real 8 [])
                                                (RealConstant
                                                    11.313708
                                                    (Real 8 [])
                                                )
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                11.313708
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (IntegerBinOp
                                            (IntegerConstant 54 (Integer 4 []))
                                            Sub
                                            (IntegerConstant 100 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant -46 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x2)
                                        (Cast
                                            (RealBinOp
                                                (RealBinOp
                                                    (RealConstant
                                                        3.454000
                                                        (Real 8 [])
                                                    )
                                                    Sub
                                                    (RealConstant
                                                        765.430000
                                                        (Real 8 [])
                                                    )
                                                    (Real 8 [])
                                                    (RealConstant
                                                        -761.976000
                                                        (Real 8 [])
                                                    )
                                                )
                                                Add
                                                (RealConstant
                                                    534.600000
                                                    (Real 8 [])
                                                )
                                                (Real 8 [])
                                                (RealConstant
                                                    -227.376000
                                                    (Real 8 [])
                                                )
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                -227.376000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x2)
                                        (Cast
                                            (RealBinOp
                                                (RealConstant
                                                    5346.565000
                                                    (Real 8 [])
                                                )
                                                Mul
                                                (RealConstant
                                                    3.450000
                                                    (Real 8 [])
                                                )
                                                (Real 8 [])
                                                (RealConstant
                                                    18445.649250
                                                    (Real 8 [])
                                                )
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                18445.649250
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x2)
                                        (Cast
                                            (RealBinOp
                                                (RealConstant
                                                    5346.565000
                                                    (Real 8 [])
                                                )
                                                Pow
                                                (RealConstant
                                                    0xBADBEEF
                                                    (Real 8 [])
                                                )
                                                (Real 8 [])
                                                (RealConstant
                                                    7275422789925.217773
                                                    (Real 8 [])
                                                )
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                7275422789925.217773
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Add
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Sub
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Mul
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Pow
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b1)
                                        (LogicalConstant
                                            true
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b2)
                                        (LogicalConstant
                                            false
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (Cast
                                            (FunctionCall
                                                2 _lpython_floordiv__AT____lpython_overloaded_6___lpython_floordiv
                                                2 _lpython_floordiv
                                                [((Var 2 b1))
                                                ((Var 2 b1))]
                                                (Logical 4 [])
                                                ()
                                                ()
                                            )
                                            LogicalToInteger
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (IntegerBinOp
                                            (Cast
                                                (Var 2 b1)
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Pow
                                            (Cast
                                                (Var 2 b2)
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
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

                        })
                    main_program
                    []
                    []
                )
        })
    []
)
