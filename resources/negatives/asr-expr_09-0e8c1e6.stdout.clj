(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        9
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        8
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
                                    [test_multiple_assign_1
                                    test_issue_928
                                    main0]
                                    []
                                    [(SubroutineCall
                                        9 test_multiple_assign_1
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        9 test_issue_928
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        9 main0
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :main0
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :i1
                                                (Variable
                                                    2
                                                    i1
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
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    main0
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
                                        (Var 2 i1)
                                        (IntegerConstant 10 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 i2)
                                        (IntegerConstant 4 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 i1)
                                        (IntegerConstant 3 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 i2)
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(IntegerBinOp
                                            (IntegerUnaryMinus
                                                (Var 2 i1)
                                                (Integer 4 [])
                                                ()
                                            )
                                            BitXor
                                            (IntegerUnaryMinus
                                                (Var 2 i2)
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (IntegerBinOp
                                                (IntegerUnaryMinus
                                                    (Var 2 i1)
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                BitXor
                                                (IntegerUnaryMinus
                                                    (Var 2 i2)
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 6 (Integer 4 []))
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
                            :test_issue_928
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :a
                                                (Variable
                                                    4
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
                                                    4
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
                                                    4
                                                    c
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Tuple
                                                        [(Integer 4 [])
                                                        (Integer 4 [])]
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_issue_928
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
                                        (TupleConstant
                                            [(Var 4 a)
                                            (Var 4 b)]
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])]
                                            )
                                        )
                                        (TupleConstant
                                            [(IntegerConstant 2 (Integer 4 []))
                                            (IntegerConstant 1 (Integer 4 []))]
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])]
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 c)
                                        (TupleConstant
                                            [(IntegerConstant 2 (Integer 4 []))
                                            (IntegerConstant 1 (Integer 4 []))]
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])]
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 4 a)
                                            Eq
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 4 b)
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalBinOp
                                            (IntegerCompare
                                                (TupleItem
                                                    (Var 4 c)
                                                    (IntegerConstant 0 (Integer 4 []))
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                Eq
                                                (Var 4 a)
                                                (Logical 4 [])
                                                ()
                                            )
                                            And
                                            (IntegerCompare
                                                (TupleItem
                                                    (Var 4 c)
                                                    (IntegerConstant 1 (Integer 4 []))
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                Eq
                                                (Var 4 b)
                                                (Logical 4 [])
                                                ()
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
                                ),
                            :test_multiple_assign_1
                                (Function
                                    (SymbolTable
                                        3
                                        {
                                            :a
                                                (Variable
                                                    3
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
                                                    3
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
                                                    3
                                                    c
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
                                            :d
                                                (Variable
                                                    3
                                                    d
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
                                            :e
                                                (Variable
                                                    3
                                                    e
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
                                            :g
                                                (Variable
                                                    3
                                                    g
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
                                            :i
                                                (Variable
                                                    3
                                                    i
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (Real 8 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :j
                                                (Variable
                                                    3
                                                    j
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (Real 8 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :k
                                                (Variable
                                                    3
                                                    k
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (Real 8 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    3
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
                                            :y
                                                (Variable
                                                    3
                                                    y
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
                                                )
                                        })
                                    test_multiple_assign_1
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
                                        (Var 3 g)
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 d)
                                        (RealBinOp
                                            (Cast
                                                (Var 3 g)
                                                IntegerToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Add
                                            (RealConstant
                                                1.000000
                                                (Real 8 [])
                                            )
                                            (Real 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 e)
                                        (RealBinOp
                                            (Cast
                                                (Var 3 g)
                                                IntegerToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Add
                                            (RealConstant
                                                1.000000
                                                (Real 8 [])
                                            )
                                            (Real 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 a)
                                        (IntegerConstant 10 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerConstant 10 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 c)
                                        (IntegerConstant 10 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 a)
                                            Eq
                                            (Var 3 b)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (Var 3 c)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 a)
                                            Eq
                                            (IntegerConstant 10 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 x)
                                        (RealConstant
                                            23.000000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 y)
                                        (RealConstant
                                            23.000000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (IntrinsicFunction
                                                Abs
                                                [(RealBinOp
                                                    (Var 3 x)
                                                    Sub
                                                    (RealConstant
                                                        23.000000
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
                                            (RealConstant
                                                0.000001
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
                                                [(RealBinOp
                                                    (Var 3 y)
                                                    Sub
                                                    (RealConstant
                                                        23.000000
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
                                            (IntrinsicFunction
                                                Abs
                                                [(RealBinOp
                                                    (Var 3 e)
                                                    Sub
                                                    (RealConstant
                                                        6.000000
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
                                            (RealConstant
                                                0.000001
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
                                                [(RealBinOp
                                                    (Var 3 d)
                                                    Sub
                                                    (RealConstant
                                                        6.000000
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
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 k)
                                        (ListConstant
                                            []
                                            (List
                                                (Real 8 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 g)
                                        (IntegerConstant 0 (Integer 4 []))
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 3 g)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (IntegerConstant 10 (Integer 4 []))
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant 9 (Integer 4 []))
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(ListAppend
                                            (Var 3 k)
                                            (RealBinOp
                                                (RealBinOp
                                                    (Cast
                                                        (Var 3 g)
                                                        IntegerToReal
                                                        (Real 8 [])
                                                        ()
                                                    )
                                                    Mul
                                                    (RealConstant
                                                        2.000000
                                                        (Real 8 [])
                                                    )
                                                    (Real 8 [])
                                                    ()
                                                )
                                                Add
                                                (RealConstant
                                                    5.000000
                                                    (Real 8 [])
                                                )
                                                (Real 8 [])
                                                ()
                                            )
                                        )]
                                    )
                                    (=
                                        (Var 3 i)
                                        (Var 3 k)
                                        ()
                                    )
                                    (=
                                        (Var 3 j)
                                        (Var 3 k)
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 3 g)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (IntegerConstant 10 (Integer 4 []))
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant 9 (Integer 4 []))
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(Assert
                                            (RealCompare
                                                (IntrinsicFunction
                                                    Abs
                                                    [(RealBinOp
                                                        (ListItem
                                                            (Var 3 i)
                                                            (Var 3 g)
                                                            (Real 8 [])
                                                            ()
                                                        )
                                                        Sub
                                                        (ListItem
                                                            (Var 3 j)
                                                            (Var 3 g)
                                                            (Real 8 [])
                                                            ()
                                                        )
                                                        (Real 8 [])
                                                        ()
                                                    )]
                                                    0
                                                    (Real 8 [])
                                                    ()
                                                )
                                                Lt
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
                                                (IntrinsicFunction
                                                    Abs
                                                    [(RealBinOp
                                                        (ListItem
                                                            (Var 3 i)
                                                            (Var 3 g)
                                                            (Real 8 [])
                                                            ()
                                                        )
                                                        Sub
                                                        (ListItem
                                                            (Var 3 k)
                                                            (Var 3 g)
                                                            (Real 8 [])
                                                            ()
                                                        )
                                                        (Real 8 [])
                                                        ()
                                                    )]
                                                    0
                                                    (Real 8 [])
                                                    ()
                                                )
                                                Lt
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
                                                (IntrinsicFunction
                                                    Abs
                                                    [(RealBinOp
                                                        (RealBinOp
                                                            (RealBinOp
                                                                (Cast
                                                                    (Var 3 g)
                                                                    IntegerToReal
                                                                    (Real 8 [])
                                                                    ()
                                                                )
                                                                Mul
                                                                (RealConstant
                                                                    2.000000
                                                                    (Real 8 [])
                                                                )
                                                                (Real 8 [])
                                                                ()
                                                            )
                                                            Add
                                                            (RealConstant
                                                                5.000000
                                                                (Real 8 [])
                                                            )
                                                            (Real 8 [])
                                                            ()
                                                        )
                                                        Sub
                                                        (ListItem
                                                            (Var 3 k)
                                                            (Var 3 g)
                                                            (Real 8 [])
                                                            ()
                                                        )
                                                        (Real 8 [])
                                                        ()
                                                    )]
                                                    0
                                                    (Real 8 [])
                                                    ()
                                                )
                                                Lt
                                                (RealConstant
                                                    0.000000
                                                    (Real 8 [])
                                                )
                                                (Logical 4 [])
                                                ()
                                            )
                                            ()
                                        )]
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
                        7
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    7
                                    _lpython_main_program
                                    9 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        7 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
