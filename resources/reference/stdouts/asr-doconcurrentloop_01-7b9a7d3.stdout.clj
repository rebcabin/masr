(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        8
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        7
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
                                    [main0]
                                    []
                                    [(SubroutineCall
                                        8 main0
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
                                                    (Real 4 [((IntegerConstant 0 (Integer 4 []))
                                                    (IntegerConstant 10000 (Integer 4 [])))])
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
                                                    (Real 4 [((IntegerConstant 0 (Integer 4 []))
                                                    (IntegerConstant 10000 (Integer 4 [])))])
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
                                                    (Real 4 [((IntegerConstant 0 (Integer 4 []))
                                                    (IntegerConstant 10000 (Integer 4 [])))])
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
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :nsize
                                                (Variable
                                                    3
                                                    nsize
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
                                            :scalar
                                                (Variable
                                                    3
                                                    scalar
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
                                    [triad]
                                    []
                                    [(=
                                        (Var 3 scalar)
                                        (Cast
                                            (RealConstant
                                                10.000000
                                                (Real 8 [])
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                10.000000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 nsize)
                                        (IntegerConstant 1234 (Integer 4 []))
                                        ()
                                    )
                                    (DoConcurrentLoop
                                        ((Var 3 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (Var 3 nsize)
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(=
                                            (ArrayItem
                                                (Var 3 a)
                                                [(()
                                                (Var 3 i)
                                                ())]
                                                (Real 4 [])
                                                RowMajor
                                                ()
                                            )
                                            (Cast
                                                (RealConstant
                                                    5.000000
                                                    (Real 8 [])
                                                )
                                                RealToReal
                                                (Real 4 [])
                                                (RealConstant
                                                    5.000000
                                                    (Real 4 [])
                                                )
                                            )
                                            ()
                                        )
                                        (=
                                            (ArrayItem
                                                (Var 3 b)
                                                [(()
                                                (Var 3 i)
                                                ())]
                                                (Real 4 [])
                                                RowMajor
                                                ()
                                            )
                                            (Cast
                                                (RealConstant
                                                    5.000000
                                                    (Real 8 [])
                                                )
                                                RealToReal
                                                (Real 4 [])
                                                (RealConstant
                                                    5.000000
                                                    (Real 4 [])
                                                )
                                            )
                                            ()
                                        )]
                                    )
                                    (SubroutineCall
                                        8 triad
                                        ()
                                        [((Var 3 a))
                                        ((Var 3 b))
                                        ((Var 3 scalar))
                                        ((Var 3 c))]
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(StringConstant
                                            "End Stream Triad"
                                            (Character 1 16 () [])
                                        )]
                                        ()
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :triad
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :N
                                                (Variable
                                                    2
                                                    N
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
                                            :a
                                                (Variable
                                                    2
                                                    a
                                                    []
                                                    InOut
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 4 [(()
                                                    ())])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :b
                                                (Variable
                                                    2
                                                    b
                                                    []
                                                    InOut
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 4 [(()
                                                    ())])
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
                                                    InOut
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 4 [(()
                                                    ())])
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
                                            :scalar
                                                (Variable
                                                    2
                                                    scalar
                                                    []
                                                    In
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
                                    triad
                                    (FunctionType
                                        [(Real 4 [(()
                                        ())])
                                        (Real 4 [(()
                                        ())])
                                        (Real 4 [])
                                        (Real 4 [(()
                                        ())])]
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
                                    [(Var 2 a)
                                    (Var 2 b)
                                    (Var 2 scalar)
                                    (Var 2 c)]
                                    [(=
                                        (Var 2 N)
                                        (IntegerConstant 1234 (Integer 4 []))
                                        ()
                                    )
                                    (DoConcurrentLoop
                                        ((Var 2 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (Var 2 N)
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(=
                                            (ArrayItem
                                                (Var 2 c)
                                                [(()
                                                (Var 2 i)
                                                ())]
                                                (Real 4 [])
                                                RowMajor
                                                ()
                                            )
                                            (RealBinOp
                                                (ArrayItem
                                                    (Var 2 a)
                                                    [(()
                                                    (Var 2 i)
                                                    ())]
                                                    (Real 4 [])
                                                    RowMajor
                                                    ()
                                                )
                                                Add
                                                (RealBinOp
                                                    (Var 2 scalar)
                                                    Mul
                                                    (ArrayItem
                                                        (Var 2 b)
                                                        [(()
                                                        (Var 2 i)
                                                        ())]
                                                        (Real 4 [])
                                                        RowMajor
                                                        ()
                                                    )
                                                    (Real 4 [])
                                                    ()
                                                )
                                                (Real 4 [])
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
                        6
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    6
                                    _lpython_main_program
                                    8 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        6 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
