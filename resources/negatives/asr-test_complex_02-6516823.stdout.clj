(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        115
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        114
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
                                    [check]
                                    []
                                    [(SubroutineCall
                                        115 check
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :check
                                (Function
                                    (SymbolTable
                                        5
                                        {

                                        })
                                    check
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
                                    [test_complex_abs
                                    test_complex_binop_32
                                    test_complex_binop_64]
                                    []
                                    [(SubroutineCall
                                        115 test_complex_abs
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        115 test_complex_binop_32
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        115 test_complex_binop_64
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_complex_abs
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :complex
                                                (ExternalSymbol
                                                    2
                                                    complex
                                                    7 complex
                                                    lpython_builtin
                                                    []
                                                    complex
                                                    Private
                                                ),
                                            :complex__AT____lpython_overloaded_9__complex
                                                (ExternalSymbol
                                                    2
                                                    complex__AT____lpython_overloaded_9__complex
                                                    7 __lpython_overloaded_9__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_9__complex
                                                    Public
                                                ),
                                            :eps
                                                (Variable
                                                    2
                                                    eps
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
                                            :x
                                                (Variable
                                                    2
                                                    x
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Complex 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :y
                                                (Variable
                                                    2
                                                    y
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Complex 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_complex_abs
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
                                    [complex__AT____lpython_overloaded_9__complex]
                                    []
                                    [(=
                                        (Var 2 x)
                                        (Cast
                                            (FunctionCall
                                                2 complex__AT____lpython_overloaded_9__complex
                                                2 complex
                                                [((IntegerConstant 3 (Integer 4 [])))
                                                ((IntegerConstant 4 (Integer 4 [])))]
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    3.000000
                                                    4.000000
                                                    (Complex 8 [])
                                                )
                                                ()
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                3.000000
                                                4.000000
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 eps)
                                        (RealConstant
                                            0.000000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(RealBinOp
                                                    (Cast
                                                        (IntrinsicFunction
                                                            Abs
                                                            [(Var 2 x)]
                                                            0
                                                            (Real 4 [])
                                                            ()
                                                        )
                                                        RealToReal
                                                        (Real 8 [])
                                                        ()
                                                    )
                                                    Sub
                                                    (RealConstant
                                                        0xBADBEEF
                                                        (Real 8 [])
                                                    )
                                                    (Real 8 [])
                                                    ()
                                                )]
                                                0
                                                (Real 8 [])
                                                ()
                                            )
                                            Lt
                                            (Var 2 eps)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 y)
                                        (FunctionCall
                                            2 complex__AT____lpython_overloaded_9__complex
                                            2 complex
                                            [((IntegerConstant 6 (Integer 4 [])))
                                            ((IntegerConstant 8 (Integer 4 [])))]
                                            (Complex 8 [])
                                            (ComplexConstant
                                                6.000000
                                                8.000000
                                                (Complex 8 [])
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(RealBinOp
                                                    (IntrinsicFunction
                                                        Abs
                                                        [(Var 2 y)]
                                                        0
                                                        (Real 8 [])
                                                        ()
                                                    )
                                                    Sub
                                                    (RealConstant
                                                        10.000000
                                                        (Real 8 [])
                                                    )
                                                    (Real 8 [])
                                                    ()
                                                )]
                                                0
                                                (Real 8 [])
                                                ()
                                            )
                                            Lt
                                            (Var 2 eps)
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
                            :test_complex_binop_32
                                (Function
                                    (SymbolTable
                                        3
                                        {
                                            :x
                                                (Variable
                                                    3
                                                    x
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Complex 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :y
                                                (Variable
                                                    3
                                                    y
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Complex 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :z
                                                (Variable
                                                    3
                                                    z
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Complex 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_complex_binop_32
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
                                        (Var 3 x)
                                        (Cast
                                            (ComplexBinOp
                                                (Cast
                                                    (IntegerConstant 2 (Integer 4 []))
                                                    IntegerToComplex
                                                    (Complex 8 [])
                                                    (ComplexConstant
                                                        2.000000
                                                        0.000000
                                                        (Complex 8 [])
                                                    )
                                                )
                                                Add
                                                (ComplexConstant
                                                    0.000000
                                                    3.000000
                                                    (Complex 8 [])
                                                )
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    2.000000
                                                    3.000000
                                                    (Complex 8 [])
                                                )
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                2.000000
                                                3.000000
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 y)
                                        (Cast
                                            (ComplexBinOp
                                                (Cast
                                                    (IntegerConstant 4 (Integer 4 []))
                                                    IntegerToComplex
                                                    (Complex 8 [])
                                                    (ComplexConstant
                                                        4.000000
                                                        0.000000
                                                        (Complex 8 [])
                                                    )
                                                )
                                                Add
                                                (ComplexConstant
                                                    0.000000
                                                    5.000000
                                                    (Complex 8 [])
                                                )
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    4.000000
                                                    5.000000
                                                    (Complex 8 [])
                                                )
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                4.000000
                                                5.000000
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 z)
                                        (ComplexBinOp
                                            (Var 3 x)
                                            Add
                                            (Var 3 y)
                                            (Complex 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 z)
                                        (ComplexBinOp
                                            (Var 3 x)
                                            Sub
                                            (Var 3 y)
                                            (Complex 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 z)
                                        (ComplexBinOp
                                            (Var 3 x)
                                            Mul
                                            (Var 3 y)
                                            (Complex 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 z)
                                        (ComplexBinOp
                                            (Var 3 x)
                                            Pow
                                            (Var 3 y)
                                            (Complex 4 [])
                                            ()
                                        )
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_complex_binop_64
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :x
                                                (Variable
                                                    4
                                                    x
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Complex 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :y
                                                (Variable
                                                    4
                                                    y
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Complex 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :z
                                                (Variable
                                                    4
                                                    z
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Complex 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_complex_binop_64
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
                                        (Var 4 x)
                                        (ComplexBinOp
                                            (Cast
                                                (IntegerConstant 2 (Integer 4 []))
                                                IntegerToComplex
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    2.000000
                                                    0.000000
                                                    (Complex 8 [])
                                                )
                                            )
                                            Add
                                            (ComplexConstant
                                                0.000000
                                                3.000000
                                                (Complex 8 [])
                                            )
                                            (Complex 8 [])
                                            (ComplexConstant
                                                2.000000
                                                3.000000
                                                (Complex 8 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 y)
                                        (ComplexBinOp
                                            (Cast
                                                (IntegerConstant 4 (Integer 4 []))
                                                IntegerToComplex
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    4.000000
                                                    0.000000
                                                    (Complex 8 [])
                                                )
                                            )
                                            Add
                                            (ComplexConstant
                                                0.000000
                                                5.000000
                                                (Complex 8 [])
                                            )
                                            (Complex 8 [])
                                            (ComplexConstant
                                                4.000000
                                                5.000000
                                                (Complex 8 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 z)
                                        (ComplexBinOp
                                            (Var 4 x)
                                            Add
                                            (Var 4 y)
                                            (Complex 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 z)
                                        (ComplexBinOp
                                            (Var 4 x)
                                            Sub
                                            (Var 4 y)
                                            (Complex 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 z)
                                        (ComplexBinOp
                                            (Var 4 x)
                                            Mul
                                            (Var 4 y)
                                            (Complex 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 z)
                                        (ComplexBinOp
                                            (Var 4 x)
                                            Pow
                                            (Var 4 y)
                                            (Complex 8 [])
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
                        113
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    113
                                    _lpython_main_program
                                    115 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        113 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
