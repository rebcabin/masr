(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        4
                        {
                            :test_List
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
                                                    (List
                                                        (Integer 4 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :a11
                                                (Variable
                                                    2
                                                    a11
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (Integer 4 [])
                                                    )
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
                                            :b11
                                                (Variable
                                                    2
                                                    b11
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (Integer 4 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :c
                                                (Variable
                                                    2
                                                    c
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (List
                                                            (Integer 4 [])
                                                        )
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :d
                                                (Variable
                                                    2
                                                    d
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
                                            :e
                                                (Variable
                                                    2
                                                    e
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (List
                                                            (Character 1 -2 () [])
                                                        )
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_List
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
                                        (Var 2 a)
                                        (ListConstant
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))
                                            (IntegerConstant 3 (Integer 4 []))]
                                            (List
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (ListConstant
                                            [(IntegerUnaryMinus
                                                (IntegerConstant 3 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -3 (Integer 4 []))
                                            )
                                            (IntegerUnaryMinus
                                                (IntegerConstant 2 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -2 (Integer 4 []))
                                            )
                                            (IntegerUnaryMinus
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -1 (Integer 4 []))
                                            )]
                                            (List
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b)
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
                                        (Var 2 c)
                                        (ListConstant
                                            [(ListConstant
                                                [(IntegerConstant 1 (Integer 4 []))
                                                (IntegerConstant 2 (Integer 4 []))
                                                (IntegerConstant 3 (Integer 4 []))]
                                                (List
                                                    (Integer 4 [])
                                                )
                                            )
                                            (ListConstant
                                                [(IntegerConstant 4 (Integer 4 []))
                                                (IntegerConstant 5 (Integer 4 []))
                                                (IntegerConstant 6 (Integer 4 []))]
                                                (List
                                                    (Integer 4 [])
                                                )
                                            )]
                                            (List
                                                (List
                                                    (Integer 4 [])
                                                )
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 d)
                                        (ListItem
                                            (Var 2 a)
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 e)
                                        (ListConstant
                                            [(ListConstant
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
                                            (ListConstant
                                                [(StringConstant
                                                    "d"
                                                    (Character 1 1 () [])
                                                )
                                                (StringConstant
                                                    "e"
                                                    (Character 1 1 () [])
                                                )]
                                                (List
                                                    (Character 1 1 () [])
                                                )
                                            )]
                                            (List
                                                (List
                                                    (Character 1 1 () [])
                                                )
                                            )
                                        )
                                        ()
                                    )
                                    (ListAppend
                                        (Var 2 a)
                                        (IntegerConstant 10 (Integer 4 []))
                                    )
                                    (ListRemove
                                        (Var 2 a)
                                        (IntegerConstant 1 (Integer 4 []))
                                    )
                                    (ListInsert
                                        (Var 2 a)
                                        (IntegerConstant 2 (Integer 4 []))
                                        (IntegerConstant 13 (Integer 4 []))
                                    )
                                    (=
                                        (Var 2 a)
                                        (ListSection
                                            (Var 2 a)
                                            ((IntegerConstant 0 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))
                                            ())
                                            (List
                                                (Integer 4 [])
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 d)
                                        (ListPop
                                            (Var 2 a)
                                            (IntegerConstant -1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 d)
                                        (ListPop
                                            (Var 2 a)
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (ListConcat
                                            (Var 2 a)
                                            (ListConstant
                                                [(IntegerConstant 4 (Integer 4 []))
                                                (IntegerConstant 5 (Integer 4 []))]
                                                (List
                                                    (Integer 4 [])
                                                )
                                            )
                                            (List
                                                (Integer 4 [])
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (ListConcat
                                            (ListConstant
                                                [(IntegerConstant 6 (Integer 4 []))
                                                (IntegerConstant 7 (Integer 4 []))]
                                                (List
                                                    (Integer 4 [])
                                                )
                                            )
                                            (Var 2 a)
                                            (List
                                                (Integer 4 [])
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a11)
                                        (ListConstant
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))]
                                            (List
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b11)
                                        (ListConstant
                                            [(IntegerConstant 3 (Integer 4 []))
                                            (IntegerConstant 4 (Integer 4 []))]
                                            (List
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (ListCompare
                                            (Var 2 a11)
                                            Eq
                                            (Var 2 b11)
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
                        3
                        {
                            
                        })
                    main_program
                    []
                    []
                )
        })
    []
)
