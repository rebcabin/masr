(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        7
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        6
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
                                        7 main0
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
                                    check
                                    (FunctionType
                                        []
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
                                    [test]
                                    []
                                    [(=
                                        (Var 3 a)
                                        (FunctionCall
                                            7 test
                                            ()
                                            [((IntegerConstant 2 (Integer 4 [])))
                                            ((IntegerConstant 2 (Integer 4 [])))]
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 _lpython_return_variable)
                                        (Var 3 a)
                                        ()
                                    )
                                    (Return)]
                                    (Var 3 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :main0
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
                                    [check]
                                    []
                                    [(=
                                        (Var 4 x)
                                        (FunctionCall
                                            7 check
                                            ()
                                            []
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
                            :test
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
                                            :a
                                                (Variable
                                                    2
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
                                                    2
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
                                                )
                                        })
                                    test
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
                                    []
                                    [(Var 2 a)
                                    (Var 2 b)]
                                    [(=
                                        (Var 2 _lpython_return_variable)
                                        (IntegerBinOp
                                            (Var 2 a)
                                            0xBADBEEF
                                            (Var 2 b)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 2 _lpython_return_variable)
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
                        5
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    5
                                    _lpython_main_program
                                    7 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        5 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
