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
                                    [test_len]
                                    []
                                    [(SubroutineCall
                                        7 test_len
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_len
                                (Function
                                    (SymbolTable
                                        2
                                        {
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
                                            :l
                                                (Variable
                                                    2
                                                    l
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
                                            :l2
                                                (Variable
                                                    2
                                                    l2
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (Real 8 [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :l3
                                                (Variable
                                                    2
                                                    l3
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
                                            :list_len
                                                (Variable
                                                    2
                                                    list_len
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
                                                ),
                                            :t
                                                (Variable
                                                    2
                                                    t
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Tuple
                                                        [(Integer 4 [])
                                                        (Integer 4 [])
                                                        (Character 1 -2 () [])]
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :t2
                                                (Variable
                                                    2
                                                    t2
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Tuple
                                                        [(Real 8 [])
                                                        (Logical 4 [])
                                                        (Logical 4 [])
                                                        (Character 1 -2 () [])
                                                        (Integer 4 [])]
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :t3
                                                (Variable
                                                    2
                                                    t3
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
                                            :tmp
                                                (Variable
                                                    2
                                                    tmp
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
                                    test_len
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
                                            "abcd"
                                            (Character 1 4 () [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringLen
                                                (Var 2 s)
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 4 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            ""
                                            (Character 1 0 () [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringLen
                                                (Var 2 s)
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Integer 4 []) ;; badbeef
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringLen
                                                (StringConstant
                                                    "abcd"
                                                    (Character 1 4 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 4 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 4 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StringLen
                                                (StringConstant
                                                    ""
                                                    (Character 1 0 () [])
                                                )
                                                (Integer 4 [])
                                                (IntegerConstant 0 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 l)
                                        (ListConstant
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))
                                            (IntegerConstant 3 (Integer 4 []))
                                            (IntegerConstant 4 (Integer 4 []))]
                                            (List
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (ListLen
                                                (Var 2 l)
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 4 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 l2)
                                        (ListConstant
                                            [(RealConstant
                                                1.200000
                                                (Real 8 [])
                                            )
                                            (RealConstant
                                                3.400000
                                                (Real 8 [])
                                            )
                                            (RealConstant
                                                5.600000
                                                (Real 8 [])
                                            )
                                            (RealConstant
                                                7.800000
                                                (Real 8 [])
                                            )
                                            (RealConstant
                                                9.000000
                                                (Real 8 [])
                                            )]
                                            (List
                                                (Real 8 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (ListLen
                                                (Var 2 l2)
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 5 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 l3)
                                        (ListConstant
                                            []
                                            (List
                                                (Integer 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (ListLen
                                                (Var 2 l3)
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 2 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (IntegerConstant 50 (Integer 4 []))
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant 49 (Integer 4 []))
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(ListAppend
                                            (Var 2 l3)
                                            (Var 2 i)
                                        )]
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (ListLen
                                                (Var 2 l3)
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 50 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 list_len)
                                        (ListLen
                                            (ListConstant
                                                [(RealConstant
                                                    1.000000
                                                    (Real 8 [])
                                                )
                                                (RealConstant
                                                    2.000000
                                                    (Real 8 [])
                                                )]
                                                (List
                                                    (Real 8 [])
                                                )
                                            )
                                            (Integer 4 [])
                                            (IntegerConstant 2 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 list_len)
                                            Eq
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 t)
                                        (TupleConstant
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))
                                            (StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )]
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])
                                                (Character 1 1 () [])]
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (TupleLen
                                                (Var 2 t)
                                                (Integer 4 [])
                                                (IntegerConstant 3 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 3 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 t2)
                                        (TupleConstant
                                            [(RealConstant
                                                1.200000
                                                (Real 8 [])
                                            )
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
                                            (StringConstant
                                                "b"
                                                (Character 1 1 () [])
                                            )
                                            (IntegerConstant 3 (Integer 4 []))]
                                            (Tuple
                                                [(Real 8 [])
                                                (Logical 4 [])
                                                (Logical 4 [])
                                                (Character 1 1 () [])
                                                (Integer 4 [])]
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (TupleLen
                                                (Var 2 t2)
                                                (Integer 4 [])
                                                (IntegerConstant 5 (Integer 4 []))
                                            )
                                            Eq
                                            (IntegerConstant 5 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 t3)
                                        (TupleLen
                                            (TupleConstant
                                                [(IntegerConstant 1 (Integer 4 []))
                                                (IntegerConstant 2 (Integer 4 []))
                                                (IntegerConstant 3 (Integer 4 []))
                                                (IntegerConstant 4 (Integer 4 []))
                                                (IntegerConstant 5 (Integer 4 []))]
                                                (Tuple
                                                    [(Integer 4 [])
                                                    (Integer 4 [])
                                                    (Integer 4 [])
                                                    (Integer 4 [])
                                                    (Integer 4 [])]
                                                )
                                            )
                                            (Integer 4 [])
                                            (IntegerConstant 5 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 t3)
                                            Eq
                                            (IntegerConstant 5 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 tmp)
                                        (ListLen
                                            (Var 2 l)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 2 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (TupleLen
                                                (Var 2 t2)
                                                (Integer 4 [])
                                                (IntegerConstant 5 (Integer 4 []))
                                            )
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant 4 (Integer 4 []))
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(ListAppend
                                            (Var 2 l)
                                            (Var 2 i)
                                        )]
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (ListLen
                                                (Var 2 l)
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerBinOp
                                                (TupleLen
                                                    (Var 2 t2)
                                                    (Integer 4 [])
                                                    (IntegerConstant 5 (Integer 4 []))
                                                )
                                                Add
                                                (Var 2 tmp)
                                                (Integer 4 [])
                                                ()
                                            )
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
