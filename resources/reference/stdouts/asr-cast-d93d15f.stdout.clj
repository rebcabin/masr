(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        112
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        111
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
                                    [f]
                                    []
                                    [(SubroutineCall
                                        112 f
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :f
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :list
                                                (ExternalSymbol
                                                    2
                                                    list
                                                    4 list
                                                    lpython_builtin
                                                    []
                                                    list
                                                    Private
                                                ),
                                            :s
                                                (Variable
                                                    2
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
                                                    (List
                                                        (Character 1 -2 () [])
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
                                                )
                                        })
                                    f
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
                                    [list]
                                    []
                                    [(=
                                        (Var 2 s)
                                        (StringConstant
                                            "lpython"
                                            (Character 1 7 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (FunctionCall
                                            2 list
                                            ()
                                            [((Var 2 s))]
                                            (List
                                                (Character 1 -2 () [])
                                            )
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 y)
                                        (ListConstant
                                            [(StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (StringConstant
                                                "b"
                                                (Character 1 1 () [])
                                            )
                                            (StringConstant
                                                "c"
                                                (Character 1 1 () [])
                                            )]
                                            (List
                                                (Character 1 1 () [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (FunctionCall
                                            2 list
                                            ()
                                            [((Var 2 y))]
                                            (List
                                                (Character 1 -2 () [])
                                            )
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 x)
                                        (FunctionCall
                                            2 list
                                            ()
                                            [((StringConstant
                                                "lpython"
                                                (Character 1 7 () [])
                                            ))]
                                            (List
                                                (Character 1 -2 () [])
                                            )
                                            (ListConstant
                                                [(StringConstant
                                                    "l"
                                                    (Character 1 1 () [])
                                                )
                                                (StringConstant
                                                    "p"
                                                    (Character 1 1 () [])
                                                )
                                                (StringConstant
                                                    "y"
                                                    (Character 1 1 () [])
                                                )
                                                (StringConstant
                                                    "t"
                                                    (Character 1 1 () [])
                                                )
                                                (StringConstant
                                                    "h"
                                                    (Character 1 1 () [])
                                                )
                                                (StringConstant
                                                    "o"
                                                    (Character 1 1 () [])
                                                )
                                                (StringConstant
                                                    "n"
                                                    (Character 1 1 () [])
                                                )]
                                                (List
                                                    (Character 1 1 () [])
                                                )
                                            )
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
                    [lpython_builtin]
                    false
                    false
                ),
            :lpython_builtin
                (IntrinsicModule lpython_builtin),
            :main_program
                (Program
                    (SymbolTable
                        110
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    110
                                    _lpython_main_program
                                    112 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        110 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
