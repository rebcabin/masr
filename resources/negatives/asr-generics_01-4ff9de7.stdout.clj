(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        10
                        {
                            :T
                                (Variable
                                    10
                                    T
                                    []
                                    Local
                                    ()
                                    ()
                                    Default
                                    (TypeParameter
                                        T
                                        []
                                    )
                                    Source
                                    Public
                                    Required
                                    false
                                ),
                            :__asr_generic_f_0
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
                                                    (Integer 4 [])
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
                                            :y
                                                (Variable
                                                    6
                                                    y
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
                                    __asr_generic_f_0
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
                                    [add_integer]
                                    [(Var 6 x)
                                    (Var 6 y)]
                                    [(=
                                        (Var 6 _lpython_return_variable)
                                        (FunctionCall
                                            10 add_integer
                                            ()
                                            [((Var 6 x))
                                            ((Var 6 y))]
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 6 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :__asr_generic_f_1
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
                                                    (Character 1 1 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    7
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 1 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :y
                                                (Variable
                                                    7
                                                    y
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 1 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    __asr_generic_f_1
                                    (FunctionType
                                        [(Character 1 1 () [])
                                        (Character 1 1 () [])]
                                        (Character 1 1 () [])
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
                                    [add_string]
                                    [(Var 7 x)
                                    (Var 7 y)]
                                    [(=
                                        (Var 7 _lpython_return_variable)
                                        (FunctionCall
                                            10 add_string
                                            ()
                                            [((Var 7 x))
                                            ((Var 7 y))]
                                            (Character 1 1 () [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 7 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        9
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
                                    [__asr_generic_f_0
                                    __asr_generic_f_1]
                                    []
                                    [(Print
                                        ()
                                        [(FunctionCall
                                            10 __asr_generic_f_0
                                            ()
                                            [((IntegerConstant 1 (Integer 4 [])))
                                            ((IntegerConstant 2 (Integer 4 [])))]
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(FunctionCall
                                            10 __asr_generic_f_1
                                            ()
                                            [((StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            ))
                                            ((StringConstant
                                                "b"
                                                (Character 1 1 () [])
                                            ))]
                                            (Character 1 1 () [])
                                            ()
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(FunctionCall
                                            10 __asr_generic_f_1
                                            ()
                                            [((StringConstant
                                                "c"
                                                (Character 1 1 () [])
                                            ))
                                            ((StringConstant
                                                "d"
                                                (Character 1 1 () [])
                                            ))]
                                            (Character 1 1 () [])
                                            ()
                                            ()
                                        )]
                                        ()
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :add
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
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
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
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :y
                                                (Variable
                                                    2
                                                    y
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    add
                                    (FunctionType
                                        [(TypeParameter
                                            T
                                            []
                                        )
                                        (TypeParameter
                                            T
                                            []
                                        )]
                                        (TypeParameter
                                            T
                                            []
                                        )
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        false
                                        false
                                        [(TypeParameter
                                            T
                                            []
                                        )]
                                        []
                                        true
                                    )
                                    []
                                    [(Var 2 x)
                                    (Var 2 y)]
                                    []
                                    (Var 2 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :add_integer
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
                                                ),
                                            :y
                                                (Variable
                                                    3
                                                    y
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
                                    add_integer
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
                                    [(Var 3 x)
                                    (Var 3 y)]
                                    [(=
                                        (Var 3 _lpython_return_variable)
                                        (IntegerBinOp
                                            (Var 3 x)
                                            Add
                                            (Var 3 y)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 3 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :add_string
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
                                                    (Character 1 -2 () [])
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
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :y
                                                (Variable
                                                    4
                                                    y
                                                    []
                                                    In
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
                                    add_string
                                    (FunctionType
                                        [(Character 1 -2 () [])
                                        (Character 1 -2 () [])]
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
                                    [(Var 4 x)
                                    (Var 4 y)]
                                    [(=
                                        (Var 4 _lpython_return_variable)
                                        (StringConcat
                                            (Var 4 x)
                                            (Var 4 y)
                                            (Character 1 -4 () [])
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
                            :f
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
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    5
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :y
                                                (Variable
                                                    5
                                                    y
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    f
                                    (FunctionType
                                        [(TypeParameter
                                            T
                                            []
                                        )
                                        (TypeParameter
                                            T
                                            []
                                        )]
                                        (TypeParameter
                                            T
                                            []
                                        )
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        false
                                        false
                                        [(TypeParameter
                                            T
                                            []
                                        )]
                                        [10 add]
                                        false
                                    )
                                    [add]
                                    [(Var 5 x)
                                    (Var 5 y)]
                                    [(=
                                        (Var 5 _lpython_return_variable)
                                        (FunctionCall
                                            10 add
                                            ()
                                            [((Var 5 x))
                                            ((Var 5 y))]
                                            (TypeParameter
                                                T
                                                []
                                            )
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 5 _lpython_return_variable)
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
                        8
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    8
                                    _lpython_main_program
                                    10 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        8 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
