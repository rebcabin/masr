(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        8
                        {
                            :f
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    2
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    2
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    true
                                                )
                                        })
                                    f
                                    (FunctionType
                                        [(Real 8 [])]
                                        (Real 8 [])
                                        BindC
                                        Interface
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
                                    [(Var 2 x)]
                                    []
                                    (Var 2 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :g
                                (Function
                                    (SymbolTable
                                        3
                                        {
                                            :a
                                                (Variable
                                                    3
                                                    a
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    true
                                                ),
                                            :b
                                                (Variable
                                                    3
                                                    b
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 4 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    true
                                                ),
                                            :c
                                                (Variable
                                                    3
                                                    c
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 8 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    true
                                                ),
                                            :d
                                                (Variable
                                                    3
                                                    d
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    true
                                                )
                                        })
                                    g
                                    (FunctionType
                                        [(Real 8 [])
                                        (Real 4 [])
                                        (Integer 8 [])
                                        (Integer 4 [])]
                                        ()
                                        BindC
                                        Interface
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
                                    [(Var 3 a)
                                    (Var 3 b)
                                    (Var 3 c)
                                    (Var 3 d)]
                                    []
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :h
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    4
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    4
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    true
                                                )
                                        })
                                    h
                                    (FunctionType
                                        [(Real 8 [])]
                                        (Real 8 [])
                                        BindC
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
                                    [(Var 4 x)]
                                    [(=
                                        (Var 4 _lpython_return_variable)
                                        (RealBinOp
                                            (Var 4 x)
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
                                    (Return)]
                                    (Var 4 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :l
                                (Function
                                    (SymbolTable
                                        5
                                        {
                                            :a
                                                (Variable
                                                    5
                                                    a
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    true
                                                ),
                                            :b
                                                (Variable
                                                    5
                                                    b
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 4 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    true
                                                ),
                                            :c
                                                (Variable
                                                    5
                                                    c
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 8 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    true
                                                ),
                                            :d
                                                (Variable
                                                    5
                                                    d
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    true
                                                )
                                        })
                                    l
                                    (FunctionType
                                        [(Real 8 [])
                                        (Real 4 [])
                                        (Integer 8 [])
                                        (Integer 4 [])]
                                        ()
                                        BindC
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
                                    [(Var 5 a)
                                    (Var 5 b)
                                    (Var 5 c)
                                    (Var 5 d)]
                                    [(Print
                                        ()
                                        [(StringConstant
                                            "OK"
                                            (Character 1 2 () [])
                                        )]
                                        ()
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
                                        6
                                        {
                                            :i
                                                (Variable
                                                    6
                                                    i
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
                                                    6
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
                                                    6
                                                    y
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
                                            :z
                                                (Variable
                                                    6
                                                    z
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
                                            :zz
                                                (Variable
                                                    6
                                                    zz
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
                                    [f
                                    g
                                    h
                                    l]
                                    []
                                    [(=
                                        (Var 6 x)
                                        (RealConstant
                                            5.000000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 6 i)
                                        (FunctionCall
                                            8 f
                                            ()
                                            [((Var 6 x))]
                                            (Real 8 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 6 y)
                                        (Cast
                                            (RealConstant
                                                5.400000
                                                (Real 8 [])
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                5.400000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 6 z)
                                        (Cast
                                            (IntegerConstant 3 (Integer 4 []))
                                            IntegerToInteger
                                            (Integer 8 [])
                                            (IntegerConstant 3 (Integer 8 []))
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 6 zz)
                                        (IntegerConstant 2 (Integer 4 []))
                                        ()
                                    )
                                    (SubroutineCall
                                        8 g
                                        ()
                                        [((Var 6 x))
                                        ((Var 6 y))
                                        ((Var 6 z))
                                        ((Var 6 zz))]
                                        ()
                                    )
                                    (=
                                        (Var 6 i)
                                        (FunctionCall
                                            8 h
                                            ()
                                            [((Var 6 x))]
                                            (Real 8 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (SubroutineCall
                                        8 l
                                        ()
                                        [((Var 6 x))
                                        ((Var 6 y))
                                        ((Var 6 z))
                                        ((Var 6 zz))]
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
                        7
                        {
                            
                        })
                    main_program
                    []
                    []
                )
        })
    []
)
