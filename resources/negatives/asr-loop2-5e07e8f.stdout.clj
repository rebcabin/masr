(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        14
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        13
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
                                    [sys__AT__global_initializer
                                    test_for]
                                    []
                                    [(SubroutineCall
                                        14 sys__AT__global_initializer
                                        3 global_initializer
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        14 test_for
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :exit
                                (ExternalSymbol
                                    14
                                    exit
                                    3 exit
                                    sys
                                    []
                                    exit
                                    Public
                                ),
                            :sys__AT__global_initializer
                                (ExternalSymbol
                                    14
                                    sys__AT__global_initializer
                                    3 global_initializer
                                    sys
                                    []
                                    global_initializer
                                    Public
                                ),
                            :test_for
                                (Function
                                    (SymbolTable
                                        10
                                        {
                                            :i
                                                (Variable
                                                    10
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
                                                )
                                        })
                                    test_for
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
                                    [exit]
                                    []
                                    [(DoLoop
                                        ()
                                        ((Var 10 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (IntegerConstant 10 (Integer 4 []))
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant 9 (Integer 4 []))
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(If
                                            (IntegerCompare
                                                (Var 10 i)
                                                Eq
                                                (IntegerConstant 0 (Integer 4 []))
                                                (Logical 4 [])
                                                ()
                                            )
                                            [(Cycle
                                                ()
                                            )]
                                            []
                                        )
                                        (If
                                            (IntegerCompare
                                                (Var 10 i)
                                                Gt
                                                (IntegerConstant 5 (Integer 4 []))
                                                (Logical 4 [])
                                                ()
                                            )
                                            [(Exit
                                                ()
                                            )]
                                            []
                                        )
                                        (If
                                            (IntegerCompare
                                                (Var 10 i)
                                                Eq
                                                (IntegerConstant 3 (Integer 4 []))
                                                (Logical 4 [])
                                                ()
                                            )
                                            [(Stop
                                                ()
                                            )]
                                            []
                                        )]
                                    )
                                    (SubroutineCall
                                        14 exit
                                        ()
                                        [((IntegerConstant 2 (Integer 4 [])))]
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                )
                        })
                    _global_symbols
                    [sys]
                    false
                    false
                ),
            :main_program
                (Program
                    (SymbolTable
                        12
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    12
                                    _lpython_main_program
                                    14 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        12 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                ),
            :sys
                (Module
                    (SymbolTable
                        3
                        {
                            :_lpython_argv
                                (Function
                                    (SymbolTable
                                        7
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    7
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (Character 1 -2 () [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :argc
                                                (Variable
                                                    7
                                                    argc
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
                                            :argv
                                                (Variable
                                                    7
                                                    argv
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (Character 1 -2 () [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :i
                                                (Variable
                                                    7
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
                                                )
                                        })
                                    _lpython_argv
                                    (FunctionType
                                        []
                                        (List
                                            (Character 1 -2 () [])
                                        )
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
                                    [_lpython_get_argc
                                    _lpython_get_argv]
                                    []
                                    [(=
                                        (Var 7 argc)
                                        (FunctionCall
                                            3 _lpython_get_argc
                                            ()
                                            []
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 7 argv)
                                        (ListConstant
                                            []
                                            (List
                                                (Character 1 -2 () [])
                                            )
                                        )
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 7 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (Var 7 argc)
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(ListAppend
                                            (Var 7 argv)
                                            (FunctionCall
                                                3 _lpython_get_argv
                                                ()
                                                [((Var 7 i))]
                                                (Character 1 -2 () [])
                                                ()
                                                ()
                                            )
                                        )]
                                    )
                                    (=
                                        (Var 7 _lpython_return_variable)
                                        (Var 7 argv)
                                        ()
                                    )
                                    (Return)]
                                    (Var 7 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :_lpython_get_argc
                                (Function
                                    (SymbolTable
                                        5
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    5
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    BindC
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    _lpython_get_argc
                                    (FunctionType
                                        []
                                        (Integer 4 [])
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
                                    []
                                    []
                                    (Var 5 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :_lpython_get_argv
                                (Function
                                    (SymbolTable
                                        6
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    6
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    BindC
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :index
                                                (Variable
                                                    6
                                                    index
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
                                    _lpython_get_argv
                                    (FunctionType
                                        [(Integer 4 [])]
                                        (Character 1 -2 () [])
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
                                    [(Var 6 index)]
                                    []
                                    (Var 6 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :argv
                                (Variable
                                    3
                                    argv
                                    []
                                    Local
                                    ()
                                    ()
                                    Default
                                    (List
                                        (Character 1 -2 () [])
                                    )
                                    Source
                                    Public
                                    Required
                                    false
                                ),
                            :exit
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :error_code
                                                (Variable
                                                    4
                                                    error_code
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
                                    exit
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
                                    [(Var 4 error_code)]
                                    [(Stop
                                        (Var 4 error_code)
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :global_initializer
                                (Function
                                    (SymbolTable
                                        9
                                        {
                                            
                                        })
                                    global_initializer
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
                                    [_lpython_argv]
                                    []
                                    [(=
                                        (Var 3 argv)
                                        (FunctionCall
                                            3 _lpython_argv
                                            ()
                                            []
                                            (List
                                                (Character 1 -2 () [])
                                            )
                                            ()
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
                    sys
                    []
                    false
                    false
                )
        })
    []
)
