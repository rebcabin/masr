(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        4
                        {
                            :test_subscript
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :A
                                                (Variable
                                                    2
                                                    A
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [((IntegerConstant 0 (Integer 4 []))
                                                    (IntegerConstant 5 (Integer 4 [])))])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :B
                                                (Variable
                                                    2
                                                    B
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [((IntegerConstant 0 (Integer 4 []))
                                                    (IntegerConstant 2 (Integer 4 [])))])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :i
                                                (Variable
                                                    2
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
                                                )
                                        })
                                    test_subscript
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
                                        (Var 2 s)
                                        (StringConstant
                                            "abc"
                                            (Character 1 3 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringItem
                                            (Var 2 s)
                                            (IntegerBinOp
                                                (IntegerConstant 0 (Integer 4 []))
                                                Add
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringSection
                                            (Var 2 s)
                                            (IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))
                                            ()
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringSection
                                            (Var 2 s)
                                            ()
                                            ()
                                            ()
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringSection
                                            (Var 2 s)
                                            ()
                                            ()
                                            (IntegerUnaryMinus
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -1 (Integer 4 []))
                                            )
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringSection
                                            (Var 2 s)
                                            ()
                                            ()
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringSection
                                            (Var 2 s)
                                            (IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 88 (Integer 4 []))
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringSection
                                            (Var 2 s)
                                            ()
                                            (IntegerConstant 1 (Integer 4 []))
                                            (IntegerUnaryMinus
                                                (IntegerConstant 4 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -4 (Integer 4 []))
                                            )
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringSection
                                            (Var 2 s)
                                            (IntegerUnaryMinus
                                                (IntegerConstant 89 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -89 (Integer 4 []))
                                            )
                                            ()
                                            (IntegerConstant 4 (Integer 4 []))
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringSection
                                            (Var 2 s)
                                            (IntegerUnaryMinus
                                                (IntegerConstant 3 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -3 (Integer 4 []))
                                            )
                                            (IntegerUnaryMinus
                                                (IntegerConstant 3 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -3 (Integer 4 []))
                                            )
                                            (IntegerUnaryMinus
                                                (IntegerConstant 3 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -3 (Integer 4 []))
                                            )
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringSection
                                            (Var 2 s)
                                            (IntegerConstant 2 (Integer 4 []))
                                            (IntegerConstant 3 (Integer 4 []))
                                            ()
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i)
                                        (ArrayItem
                                            (Var 2 A)
                                            [(()
                                            (IntegerConstant 0 (Integer 4 []))
                                            ())]
                                            (Integer 4 [])
                                            RowMajor
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 B)
                                        (ArraySection
                                            (Var 2 A)
                                            [((IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 3 (Integer 4 []))
                                            ())]
                                            (Integer 4 [((IntegerConstant 0 (Integer 4 []))
                                            (IntegerConstant 5 (Integer 4 [])))])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 B)
                                        (ArraySection
                                            (Var 2 A)
                                            [((IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))
                                            (IntegerConstant 3 (Integer 4 [])))]
                                            (Integer 4 [((IntegerConstant 0 (Integer 4 []))
                                            (IntegerConstant 5 (Integer 4 [])))])
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
