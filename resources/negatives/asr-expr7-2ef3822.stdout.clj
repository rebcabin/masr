(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        114
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        113
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
                                        114 main0
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
                                        4
                                        {
                                            :c
                                                (Variable
                                                    4
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
                                    [test_pow
                                    test_pow_1]
                                    []
                                    [(SubroutineCall
                                        114 test_pow
                                        ()
                                        []
                                        ()
                                    )
                                    (=
                                        (Var 4 c)
                                        (FunctionCall
                                            114 test_pow_1
                                            ()
                                            [((IntegerConstant 1 (Integer 4 [])))
                                            ((IntegerConstant 2 (Integer 4 [])))]
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_pow
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :a
                                                (Variable
                                                    2
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
                                            :pow
                                                (ExternalSymbol
                                                    2
                                                    pow
                                                    6 pow
                                                    lpython_builtin
                                                    []
                                                    pow
                                                    Private
                                                ),
                                            :pow__AT____lpython_overloaded_0__pow
                                                (ExternalSymbol
                                                    2
                                                    pow__AT____lpython_overloaded_0__pow
                                                    6 __lpython_overloaded_0__pow
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_0__pow
                                                    Public
                                                )
                                        })
                                    test_pow
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
                                    [(=
                                        (Var 2 a)
                                        (Cast
                                            (FunctionCall
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
                                            )
                                            RealToInteger
                                            (Integer 4 [])
                                            (IntegerConstant 4 (Integer 4 []))
                                        )
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_pow_1
                                (Function
                                    (SymbolTable
                                        3
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    3
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
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
                                                    3
                                                    a
                                                    []
                                                    In
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
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :pow
                                                (ExternalSymbol
                                                    3
                                                    pow
                                                    6 pow
                                                    lpython_builtin
                                                    []
                                                    pow
                                                    Private
                                                ),
                                            :pow__AT____lpython_overloaded_0__pow
                                                (ExternalSymbol
                                                    3
                                                    pow__AT____lpython_overloaded_0__pow
                                                    6 __lpython_overloaded_0__pow
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_0__pow
                                                    Public
                                                ),
                                            :res
                                                (Variable
                                                    3
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
                                    test_pow_1
                                    (FunctionType
                                        [(Integer 4 [])
                                        (Integer 4 [])]
                                        (Integer 4 [])
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
                                    [(Var 3 a)
                                    (Var 3 b)]
                                    [(=
                                        (Var 3 res)
                                        (Cast
                                            (FunctionCall
                                                3 pow__AT____lpython_overloaded_0__pow
                                                3 pow
                                                [((Var 3 a))
                                                ((Var 3 b))]
                                                (Real 8 [])
                                                ()
                                                ()
                                            )
                                            RealToInteger
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 _lpython_return_variable)
                                        (Var 3 res)
                                        ()
                                    )
                                    (Return)]
                                    (Var 3 _lpython_return_variable)
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
                        112
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    112
                                    _lpython_main_program
                                    114 _lpython_main_program
                                    _global_symbols
                                    [0xBADBEEF]
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        112 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
