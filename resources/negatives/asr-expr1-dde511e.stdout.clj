(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        4
                        {
                            :test_namedexpr
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :a
                                                (Variable
                                                    2
                                                    42 ;; ERROR
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
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_namedexpr
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
                                        (NamedExpr
                                            (Var 2 y)
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Integer 4 [])
                                        )
                                        ()
                                    )
                                    (If
                                        (NamedExpr
                                            (Var 2 a)
                                            (StringOrd
                                                (StringConstant
                                                    "3"
                                                    (Character 1 1 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 51 (Integer 4 []))
                                            )
                                            (Integer 4 [])
                                        )
                                        [(=
                                            (Var 2 x)
                                            (IntegerConstant 1 (Integer 4 []))
                                            ()
                                        )]
                                        []
                                    )
                                    (WhileLoop
                                        ()
                                        (NamedExpr
                                            (Var 2 a)
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                        )
                                        [(=
                                            (Var 2 y)
                                            (IntegerConstant 1 (Integer 4 []))
                                            ()
                                        )]
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
