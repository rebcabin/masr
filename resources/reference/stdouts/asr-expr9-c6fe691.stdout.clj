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
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :s
                                                (Variable
                                                    6
                                                    s
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
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
                                    [test_return_1
                                    test_return_2
                                    test_return_3
                                    test_return_4]
                                    []
                                    [(=
                                        (Var 6 i)
                                        (FunctionCall
                                            9 test_return_1
                                            ()
                                            [((IntegerConstant 4 (Integer 4 [])))]
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 6 s)
                                        (FunctionCall
                                            9 test_return_2
                                            ()
                                            [((IntegerConstant 4 (Integer 4 [])))]
                                            (Character 1 -2 () [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 6 i)
                                        (FunctionCall
                                            9 test_return_3
                                            ()
                                            [((IntegerConstant 4 (Integer 4 [])))]
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (SubroutineCall
                                        9 test_return_4
                                        ()
                                        [((IntegerConstant 4 (Integer 4 [])))]
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_return_1
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
                                                )
                                        })
                                    test_return_1
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
                                    [(Var 2 a)]
                                    [(=
                                        (Var 2 x)
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 _lpython_return_variable)
                                        (Var 2 x)
                                        ()
                                    )
                                    (Return)]
                                    (Var 2 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :test_return_2
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
                                                    (Character 1 -2 () [])
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
                                            :x
                                                (Variable
                                                    3
                                                    x
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_return_2
                                    (FunctionType
                                        [(Integer 4 [])]
                                        (Character 1 -2 () [])
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
                                    [(Var 3 a)]
                                    [(=
                                        (Var 3 x)
                                        (StringConstant
                                            "test"
                                            (Character 1 4 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 _lpython_return_variable)
                                        (Var 3 x)
                                        ()
                                    )
                                    (Return)]
                                    (Var 3 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :test_return_3
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
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :a
                                                (Variable
                                                    4
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
                                                )
                                        })
                                    test_return_3
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
                                    [(Var 4 a)]
                                    [(=
                                        (Var 4 a)
                                        (IntegerConstant 3 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 4 _lpython_return_variable)
                                        (Var 4 a)
                                        ()
                                    )
                                    (Return)]
                                    (Var 4 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :test_return_4
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
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_return_4
                                    (FunctionType
                                        [(Integer 4 [])]
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
                                    [(Var 5 a)]
                                    [(=
                                        (Var 5 a)
                                        (IntegerConstant 1 (Integer 4 []))
                                        ()
                                    )
                                    (Return)]
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
