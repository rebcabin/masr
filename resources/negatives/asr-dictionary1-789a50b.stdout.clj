(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        9
                        {
                            :f
                                (Function
                                    (SymbolTable
                                        6
                                        {
                                            :x
                                                (Variable
                                                    6
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Dict
                                                        (Integer 4 [])
                                                        (Integer 4 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    f
                                    (FunctionType
                                        [(Dict
                                            (Integer 4 [])
                                            (Integer 4 [])
                                        )]
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
                                    [(Var 6 x)]
                                    [(DictInsert
                                        (Var 6 x)
                                        (IntegerConstant 2 (Integer 4 []))
                                        (IntegerConstant 4 (Integer 4 []))
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_Dict
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :x
                                                (Variable
                                                    2
                                                    x
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Dict
                                                        (Integer 4 [])
                                                        (Integer 4 [])
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
                                                    (Dict
                                                        (Character 1 -2 () [])
                                                        (Integer 4 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :z
                                                (Variable
                                                    2
                                                    z
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
                                    test_Dict
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
                                    []
                                    []
                                    [(=
                                        (Var 2 x)
                                        (DictConstant
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 3 (Integer 4 []))]
                                            [(IntegerConstant 2 (Integer 4 []))
                                            (IntegerConstant 4 (Integer 4 []))]
                                            (Dict
                                                (Integer 4 [])
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 y)
                                        (DictConstant
                                            [(StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (StringConstant
                                                "b"
                                                (Character 1 1 () [])
                                            )]
                                            [(IntegerUnaryMinus
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -1 (Integer 4 []))
                                            )
                                            (IntegerUnaryMinus
                                                (IntegerConstant 2 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -2 (Integer 4 []))
                                            )]
                                            (Dict
                                                (Character 1 1 () [])
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 z)
                                        (DictItem
                                            (Var 2 y)
                                            (StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            ()
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 z)
                                        (DictItem
                                            (Var 2 y)
                                            (StringConstant
                                                "b"
                                                (Character 1 1 () [])
                                            )
                                            ()
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 z)
                                        (DictItem
                                            (Var 2 x)
                                            (IntegerConstant 1 (Integer 4 []))
                                            ()
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_dict_get
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
                                                ),
                                            :y
                                                (Variable
                                                    4
                                                    y
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Dict
                                                        (Character 1 -2 () [])
                                                        (Integer 4 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_dict_get
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
                                    []
                                    []
                                    [(=
                                        (Var 4 y)
                                        (DictConstant
                                            [(StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (StringConstant
                                                "b"
                                                (Character 1 1 () [])
                                            )]
                                            [(IntegerUnaryMinus
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -1 (Integer 4 []))
                                            )
                                            (IntegerUnaryMinus
                                                (IntegerConstant 2 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -2 (Integer 4 []))
                                            )]
                                            (Dict
                                                (Character 1 1 () [])
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 x)
                                        (DictItem
                                            (Var 4 y)
                                            (StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            ()
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 x)
                                        (DictItem
                                            (Var 4 y)
                                            (StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_dict_insert
                                (Function
                                    (SymbolTable
                                        3
                                        {
                                            :y
                                                (Variable
                                                    3
                                                    y
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Dict
                                                        (Character 1 -2 () [])
                                                        (Integer 4 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_dict_insert
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
                                    []
                                    []
                                    [(=
                                        (Var 3 y)
                                        (DictConstant
                                            [(StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (StringConstant
                                                "b"
                                                (Character 1 1 () [])
                                            )]
                                            [(IntegerUnaryMinus
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -1 (Integer 4 []))
                                            )
                                            (IntegerUnaryMinus
                                                (IntegerConstant 2 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -2 (Integer 4 []))
                                            )]
                                            (Dict
                                                (Character 1 1 () [])
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (DictInsert
                                        (Var 3 y)
                                        (StringConstant
                                            "c"
                                            (Character 1 1 () [])
                                        )
                                        (IntegerUnaryMinus
                                            (IntegerConstant 3 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant -3 (Integer 4 []))
                                        )
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_dict_pop
                                (Function
                                    (SymbolTable
                                        5
                                        {
                                            :x
                                                (Variable
                                                    5
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
                                                ),
                                            :y
                                                (Variable
                                                    5
                                                    y
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Dict
                                                        (Character 1 -2 () [])
                                                        (Integer 4 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_dict_pop
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
                                    []
                                    []
                                    [(=
                                        (Var 5 y)
                                        (DictConstant
                                            [(StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (StringConstant
                                                "b"
                                                (Character 1 1 () [])
                                            )]
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))]
                                            (Dict
                                                (Character 1 1 () [])
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 5 x)
                                        (DictPop
                                            (Var 5 y)
                                            (StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_issue_204
                                (Function
                                    (SymbolTable
                                        7
                                        {
                                            :x
                                                (Variable
                                                    7
                                                    x
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Dict
                                                        (Integer 4 [])
                                                        (Integer 4 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_issue_204
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
                                        9 f
                                        ()
                                        [((Var 7 x))]
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
                        8
                        {
                            
                        })
                    main_program
                    []
                    []
                )
        })
    []
)
