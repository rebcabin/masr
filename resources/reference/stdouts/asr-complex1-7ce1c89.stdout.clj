(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        112
                        {
                            :test
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
                                                    (Complex 8 [])
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
                                                    (Complex 8 [])
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
                                    test
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
                                        (Var 3 y)
                                        (ComplexBinOp
                                            (Cast
                                                (IntegerConstant 5 (Integer 4 []))
                                                IntegerToComplex
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    5.000000
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
                                                5.000000
                                                5.000000
                                                (Complex 8 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 z)
                                        (Cast
                                            (ComplexBinOp
                                                (Var 3 x)
                                                Add
                                                (Var 3 y)
                                                (Complex 8 [])
                                                ()
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 z)
                                        (Cast
                                            (ComplexBinOp
                                                (Var 3 x)
                                                Sub
                                                (Var 3 y)
                                                (Complex 8 [])
                                                ()
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 z)
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
                                                Mul
                                                (Var 3 x)
                                                (Complex 8 [])
                                                ()
                                            )
                                            ComplexToComplex
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
                            :test_complex
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
                                            :c
                                                (Variable
                                                    2
                                                    c
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
                                            :c1
                                                (Variable
                                                    2
                                                    c1
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
                                            :c2
                                                (Variable
                                                    2
                                                    c2
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
                                            :c3
                                                (Variable
                                                    2
                                                    c3
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
                                            :complex
                                                (ExternalSymbol
                                                    2
                                                    complex
                                                    5 complex
                                                    lpython_builtin
                                                    []
                                                    complex
                                                    Private
                                                ),
                                            :complex__AT____lpython_overloaded_0__complex
                                                (ExternalSymbol
                                                    2
                                                    complex__AT____lpython_overloaded_0__complex
                                                    5 __lpython_overloaded_0__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_0__complex
                                                    Public
                                                ),
                                            :complex__AT____lpython_overloaded_13__complex
                                                (ExternalSymbol
                                                    2
                                                    complex__AT____lpython_overloaded_13__complex
                                                    5 __lpython_overloaded_13__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_13__complex
                                                    Public
                                                ),
                                            :complex__AT____lpython_overloaded_1__complex
                                                (ExternalSymbol
                                                    2
                                                    complex__AT____lpython_overloaded_1__complex
                                                    5 __lpython_overloaded_1__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_1__complex
                                                    Public
                                                ),
                                            :complex__AT____lpython_overloaded_2__complex
                                                (ExternalSymbol
                                                    2
                                                    complex__AT____lpython_overloaded_2__complex
                                                    5 __lpython_overloaded_2__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_2__complex
                                                    Public
                                                ),
                                            :complex__AT____lpython_overloaded_5__complex
                                                (ExternalSymbol
                                                    2
                                                    complex__AT____lpython_overloaded_5__complex
                                                    5 __lpython_overloaded_5__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_5__complex
                                                    Public
                                                ),
                                            :complex__AT____lpython_overloaded_9__complex
                                                (ExternalSymbol
                                                    2
                                                    complex__AT____lpython_overloaded_9__complex
                                                    5 __lpython_overloaded_9__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_9__complex
                                                    Public
                                                )
                                        })
                                    test_complex
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
                                    [complex__AT____lpython_overloaded_0__complex
                                    complex__AT____lpython_overloaded_1__complex
                                    complex__AT____lpython_overloaded_5__complex
                                    complex__AT____lpython_overloaded_2__complex
                                    complex__AT____lpython_overloaded_9__complex
                                    complex__AT____lpython_overloaded_13__complex]
                                    []
                                    [(=
                                        (Var 2 c)
                                        (Cast
                                            (FunctionCall
                                                2 complex__AT____lpython_overloaded_0__complex
                                                2 complex
                                                []
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    0.000000
                                                    0.000000
                                                    (Complex 8 [])
                                                )
                                                ()
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                0.000000
                                                0.000000
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (Cast
                                            (FunctionCall
                                                2 complex__AT____lpython_overloaded_1__complex
                                                2 complex
                                                [((RealConstant
                                                    3.400000
                                                    (Real 8 [])
                                                ))]
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    3.400000
                                                    0.000000
                                                    (Complex 8 [])
                                                )
                                                ()
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                3.400000
                                                0.000000
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (Cast
                                            (FunctionCall
                                                2 complex__AT____lpython_overloaded_5__complex
                                                2 complex
                                                [((RealConstant
                                                    5.000000
                                                    (Real 8 [])
                                                ))
                                                ((RealConstant
                                                    4.300000
                                                    (Real 8 [])
                                                ))]
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    5.000000
                                                    4.300000
                                                    (Complex 8 [])
                                                )
                                                ()
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                5.000000
                                                4.300000
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (FunctionCall
                                            2 complex__AT____lpython_overloaded_2__complex
                                            2 complex
                                            [((IntegerConstant 1 (Integer 4 [])))]
                                            (Complex 4 [])
                                            (ComplexConstant
                                                1.000000
                                                0.000000
                                                (Complex 8 [])
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c1)
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
                                        (Var 2 c2)
                                        (Cast
                                            (FunctionCall
                                                2 complex__AT____lpython_overloaded_13__complex
                                                2 complex
                                                [((IntegerConstant 2 (Integer 4 [])))
                                                ((RealConstant
                                                    4.500000
                                                    (Real 8 [])
                                                ))]
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    2.000000
                                                    4.500000
                                                    (Complex 8 [])
                                                )
                                                ()
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                2.000000
                                                4.500000
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c3)
                                        (FunctionCall
                                            2 complex__AT____lpython_overloaded_5__complex
                                            2 complex
                                            [((RealConstant
                                                3.000000
                                                (Real 8 [])
                                            ))
                                            ((RealConstant
                                                4.000000
                                                (Real 8 [])
                                            ))]
                                            (Complex 8 [])
                                            (ComplexConstant
                                                3.000000
                                                4.000000
                                                (Complex 8 [])
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b)
                                        (ComplexCompare
                                            (Var 2 c1)
                                            NotEq
                                            (Var 2 c2)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b)
                                        (ComplexCompare
                                            (Cast
                                                (Var 2 c1)
                                                ComplexToComplex
                                                (Complex 8 [])
                                                ()
                                            )
                                            Eq
                                            (Var 2 c3)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (ComplexBinOp
                                            (Var 2 c1)
                                            Add
                                            (Var 2 c2)
                                            (Complex 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (ComplexBinOp
                                            (Var 2 c2)
                                            Sub
                                            (Var 2 c1)
                                            (Complex 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (ComplexBinOp
                                            (Var 2 c1)
                                            Mul
                                            (Var 2 c2)
                                            (Complex 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (Cast
                                            (ComplexBinOp
                                                (FunctionCall
                                                    2 complex__AT____lpython_overloaded_9__complex
                                                    2 complex
                                                    [((IntegerConstant 1 (Integer 4 [])))
                                                    ((IntegerConstant 2 (Integer 4 [])))]
                                                    (Complex 8 [])
                                                    (ComplexConstant
                                                        1.000000
                                                        2.000000
                                                        (Complex 8 [])
                                                    )
                                                    ()
                                                )
                                                Pow
                                                (FunctionCall
                                                    2 complex__AT____lpython_overloaded_5__complex
                                                    2 complex
                                                    [((RealConstant
                                                        3.345340
                                                        (Real 8 [])
                                                    ))
                                                    ((RealConstant
                                                        4.867868
                                                        (Real 8 [])
                                                    ))]
                                                    (Complex 8 [])
                                                    (ComplexConstant
                                                        3.345340
                                                        4.867868
                                                        (Complex 8 [])
                                                    )
                                                    ()
                                                )
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    0.015553
                                                    0.065561
                                                    (Complex 8 [])
                                                )
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                0.015553
                                                0.065561
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (Cast
                                            (ComplexBinOp
                                                (FunctionCall
                                                    2 complex__AT____lpython_overloaded_9__complex
                                                    2 complex
                                                    [((IntegerConstant 1 (Integer 4 [])))
                                                    ((IntegerConstant 2 (Integer 4 [])))]
                                                    (Complex 8 [])
                                                    (ComplexConstant
                                                        1.000000
                                                        2.000000
                                                        (Complex 8 [])
                                                    )
                                                    ()
                                                )
                                                Mul
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
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    -5.000000
                                                    10.000000
                                                    (Complex 8 [])
                                                )
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                -5.000000
                                                10.000000
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (Cast
                                            (ComplexBinOp
                                                (FunctionCall
                                                    2 complex__AT____lpython_overloaded_9__complex
                                                    2 complex
                                                    [((IntegerConstant 4 (Integer 4 [])))
                                                    ((IntegerConstant 5 (Integer 4 [])))]
                                                    (Complex 8 [])
                                                    (ComplexConstant
                                                        4.000000
                                                        5.000000
                                                        (Complex 8 [])
                                                    )
                                                    ()
                                                )
                                                Sub
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
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    1.000000
                                                    1.000000
                                                    (Complex 8 [])
                                                )
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                1.000000
                                                1.000000
                                                (Complex 4 [])
                                            )
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
                        111
                        {
                            
                        })
                    main_program
                    []
                    []
                )
        })
    []
)
