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
                                    [main0]
                                    []
                                    [(SubroutineCall
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
                                        5
                                        {
                                            :i
                                                (Variable
                                                    5
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
                                            :j
                                                (Variable
                                                    5
                                                    j
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
                                    [test_factorial_1
                                    test_factorial_2
                                    test_factorial_3]
                                    []
                                    [(=
                                        (Var 5 i)
                                        (FunctionCall
                                            9 test_factorial_1
                                            ()
                                            [((IntegerConstant 4 (Integer 4 [])))]
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 5 i)
                                        (FunctionCall
                                            9 test_factorial_2
                                            ()
                                            [((IntegerConstant 4 (Integer 4 [])))]
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 5 j)
                                        (FunctionCall
                                            9 test_factorial_3
                                            ()
                                            [((IntegerConstant 5 (Integer 4 [])))]
                                            (Integer 8 [])
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
                            :test_factorial_1
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
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :result
                                                (Variable
                                                    2
                                                    result
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
                                            :x
                                                (Variable
                                                    2
                                                    x
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
                                                )
                                        })
                                    test_factorial_1
                                    (FunctionType
                                        [(Integer 4 [])]
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
                                    []
                                    [(Var 2 x)]
                                    [(If
                                        (IntegerCompare
                                            (Var 2 x)
                                            Lt
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        [(=
                                            (Var 2 _lpython_return_variable)
                                            (IntegerConstant 0 (Integer 4 []))
                                            ()
                                        )
                                        (Return)]
                                        []
                                    )
                                    (=
                                        (Var 2 result)
                                        (IntegerConstant 1 (Integer 4 []))
                                        ()
                                    )
                                    (WhileLoop
                                        ()
                                        (IntegerCompare
                                            (Var 2 x)
                                            Gt
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        [(=
                                            (Var 2 result)
                                            (IntegerBinOp
                                                (Var 2 result)
                                                Mul
                                                (Var 2 x)
                                                (Integer 4 [])
                                                ()
                                            )
                                            ()
                                        )
                                        (=
                                            (Var 2 x)
                                            (IntegerBinOp
                                                (Var 2 x)
                                                Sub
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                ()
                                            )
                                            ()
                                        )]
                                    )
                                    (=
                                        (Var 2 _lpython_return_variable)
                                        (Var 2 result)
                                        ()
                                    )
                                    (Return)]
                                    (Var 2 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :test_factorial_2
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
                                            :result
                                                (Variable
                                                    3
                                                    result
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
                                            :x
                                                (Variable
                                                    3
                                                    x
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
                                                )
                                        })
                                    test_factorial_2
                                    (FunctionType
                                        [(Integer 4 [])]
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
                                    []
                                    [(Var 3 x)]
                                    [(=
                                        (Var 3 result)
                                        (IntegerConstant 1 (Integer 4 []))
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 3 i)
                                        (IntegerConstant 1 (Integer 4 []))
                                        (IntegerBinOp
                                            (IntegerBinOp
                                                (Var 3 x)
                                                Add
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                ()
                                            )
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(=
                                            (Var 3 result)
                                            (IntegerBinOp
                                                (Var 3 result)
                                                Mul
                                                (Var 3 i)
                                                (Integer 4 [])
                                                ()
                                            )
                                            ()
                                        )]
                                    )
                                    (=
                                        (Var 3 _lpython_return_variable)
                                        (Var 3 result)
                                        ()
                                    )
                                    (Return)]
                                    (Var 3 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :test_factorial_3
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
                                                    (Integer 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :result
                                                (Variable
                                                    4
                                                    result
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
                                            :x
                                                (Variable
                                                    4
                                                    x
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
                                                )
                                        })
                                    test_factorial_3
                                    (FunctionType
                                        [(Integer 4 [])]
                                        (Integer 8 [])
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
                                    [(Var 4 x)]
                                    [(=
                                        (Var 4 result)
                                        (Cast
                                            (IntegerConstant 0 (Integer 4 []))
                                            IntegerToInteger
                                            (Integer 8 [])
                                            (IntegerConstant 0 (Integer 8 []))
                                        )
                                        ()
                                    )
                                    (If
                                        (IntegerCompare
                                            (Var 4 x)
                                            Lt
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        [(=
                                            (Var 4 _lpython_return_variable)
                                            (Var 4 result)
                                            ()
                                        )
                                        (Return)]
                                        []
                                    )
                                    (=
                                        (Var 4 result)
                                        (Cast
                                            (IntegerConstant 1 (Integer 4 []))
                                            IntegerToInteger
                                            (Integer 8 [])
                                            (IntegerConstant 1 (Integer 8 []))
                                        )
                                        ()
                                    )
                                    (WhileLoop
                                        ()
                                        (IntegerCompare
                                            (Var 4 x)
                                            Gt
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        [(=
                                            (Var 4 result)
                                            (IntegerBinOp
                                                (Var 4 result)
                                                Mul
                                                (Cast
                                                    (Var 4 x)
                                                    IntegerToInteger
                                                    (Integer 8 [])
                                                    ()
                                                )
                                                (Integer 8 [])
                                                ()
                                            )
                                            ()
                                        )
                                        (=
                                            (Var 4 x)
                                            (IntegerBinOp
                                                (Var 4 x)
                                                Sub
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                ()
                                            )
                                            ()
                                        )]
                                    )
                                    (=
                                        (Var 4 _lpython_return_variable)
                                        (Var 4 result)
                                        ()
                                    )
                                    (Return)]
                                    (Var 4 _lpython_return_variable)
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
