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
                                    [test_fn1]
                                    []
                                    [(SubroutineCall
                                        7 test_fn1
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :g
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
                                                )
                                        })
                                    g
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
                                    []
                                    []
                                    [(=
                                        (Var 2 _lpython_return_variable)
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (Return)]
                                    (Var 2 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :gsubrout
                                (Function
                                    (SymbolTable
                                        3
                                        {
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
                                    gsubrout
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
                                    [(Var 3 x)]
                                    [(Print
                                        ()
                                        [(Var 3 x)]
                                        ()
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_fn1
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :__lcompilers_dummy
                                                (Variable
                                                    4
                                                    __lcompilers_dummy
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
                                                    4
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
                                                    4
                                                    j
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
                                    test_fn1
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
                                    [g
                                    gsubrout]
                                    []
                                    [(=
                                        (Var 4 i)
                                        (FunctionCall
                                            7 g
                                            ()
                                            []
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 j)
                                        (FunctionCall
                                            7 g
                                            ()
                                            []
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 __lcompilers_dummy)
                                        (FunctionCall
                                            7 g
                                            ()
                                            []
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (SubroutineCall
                                        7 gsubrout
                                        ()
                                        [((Var 4 i))]
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