(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        6
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        5
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
                                    [main]
                                    []
                                    [(SubroutineCall
                                        6 main
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :fib
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
                                                    (Integer 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :n
                                                (Variable
                                                    2
                                                    n
                                                    []
                                                    In
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
                                    fib
                                    (FunctionType
                                        [(Integer 8 [])]
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
                                    [fib]
                                    [(Var 2 n)]
                                    [(If
                                        (IntegerCompare
                                            (Var 2 n)
                                            Lt
                                            (Cast
                                                (IntegerConstant 2 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 2 (Integer 8 []))
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        [(=
                                            (Var 2 _lpython_return_variable)
                                            (Var 2 n)
                                            ()
                                        )
                                        (Return)]
                                        []
                                    )
                                    (=
                                        (Var 2 _lpython_return_variable)
                                        (IntegerBinOp
                                            (FunctionCall
                                                6 fib
                                                ()
                                                [((IntegerBinOp
                                                    (Var 2 n)
                                                    Sub
                                                    (Cast
                                                        (IntegerConstant 1 (Integer 4 []))
                                                        IntegerToInteger
                                                        (Integer 8 [])
                                                        (IntegerConstant 1 (Integer 8 []))
                                                    )
                                                    (Integer 8 [])
                                                    ()
                                                ))]
                                                (Integer 8 [])
                                                ()
                                                ()
                                            )
                                            Add
                                            (FunctionCall
                                                6 fib
                                                ()
                                                [((IntegerBinOp
                                                    (Var 2 n)
                                                    Sub
                                                    (Cast
                                                        (IntegerConstant 2 (Integer 4 []))
                                                        IntegerToInteger
                                                        (Integer 8 [])
                                                        (IntegerConstant 2 (Integer 8 []))
                                                    )
                                                    (Integer 8 [])
                                                    ()
                                                ))]
                                                (Integer 8 [])
                                                ()
                                                ()
                                            )
                                            (Integer 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 2 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :main
                                (Function
                                    (SymbolTable
                                        3
                                        {
                                            :_lpython_return_variable_fib
                                                (Variable
                                                    3
                                                    _lpython_return_variable_fib
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
                                            :ans
                                                (Variable
                                                    3
                                                    ans
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
                                            :n_fib
                                                (Variable
                                                    3
                                                    n_fib
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
                                                    3
                                                    x
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
                                            :__TILDE__empty_block
                                                (Block
                                                    (SymbolTable
                                                        7
                                                        {

                                                        })
                                                    __TILDE__empty_block
                                                    []
                                                )
                                        })
                                    main
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
                                    [fib]
                                    []
                                    [(=
                                        (Var 3 x)
                                        (Cast
                                            (IntegerConstant 40 (Integer 4 []))
                                            IntegerToInteger
                                            (Integer 8 [])
                                            (IntegerConstant 40 (Integer 8 []))
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 n_fib)
                                        (Var 3 x)
                                        ()
                                    )
                                    (If
                                        (IntegerCompare
                                            (Var 3 n_fib)
                                            Lt
                                            (Cast
                                                (IntegerConstant 2 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 2 (Integer 8 []))
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        [(=
                                            (Var 3 _lpython_return_variable_fib)
                                            (Var 3 n_fib)
                                            ()
                                        )
                                        (GoTo
                                            1
                                            __1
                                        )]
                                        []
                                    )
                                    (=
                                        (Var 3 _lpython_return_variable_fib)
                                        (IntegerBinOp
                                            (FunctionCall
                                                6 fib
                                                ()
                                                [((IntegerBinOp
                                                    (Var 3 n_fib)
                                                    Sub
                                                    (Cast
                                                        (IntegerConstant 1 (Integer 4 []))
                                                        IntegerToInteger
                                                        (Integer 8 [])
                                                        (IntegerConstant 1 (Integer 8 []))
                                                    )
                                                    (Integer 8 [])
                                                    ()
                                                ))]
                                                (Integer 8 [])
                                                ()
                                                ()
                                            )
                                            Add
                                            (FunctionCall
                                                6 fib
                                                ()
                                                [((IntegerBinOp
                                                    (Var 3 n_fib)
                                                    Sub
                                                    (Cast
                                                        (IntegerConstant 2 (Integer 4 []))
                                                        IntegerToInteger
                                                        (Integer 8 [])
                                                        (IntegerConstant 2 (Integer 8 []))
                                                    )
                                                    (Integer 8 [])
                                                    ()
                                                ))]
                                                (Integer 8 [])
                                                ()
                                                ()
                                            )
                                            (Integer 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (GoTo
                                        1
                                        __1
                                    )
                                    (BlockCall
                                        1
                                        3 __TILDE__empty_block
                                    )
                                    (=
                                        (Var 3 ans)
                                        (Var 3 _lpython_return_variable_fib)
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(Var 3 ans)]
                                        ()
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 ans)
                                            Eq
                                            (Cast
                                                (IntegerConstant 102334155 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 102334155 (Integer 8 []))
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
                        4
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    4
                                    _lpython_main_program
                                    6 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        4 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
