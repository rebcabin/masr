(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        4
                        {
                            :test_Tuple
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :a1
                                                (Variable
                                                    2
                                                    a1
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Tuple
                                                        [(Integer 4 [])
                                                        (Integer 4 [])
                                                        (Integer 4 [])]
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
                                                    (Tuple
                                                        [(Integer 4 [])
                                                        (Integer 4 [])]
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :a2
                                                (Variable
                                                    2
                                                    a2
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Tuple
                                                        [(Character 1 -2 () [])
                                                        (Character 1 -2 () [])
                                                        (Character 1 -2 () [])]
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :a3
                                                (Variable
                                                    2
                                                    a3
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Tuple
                                                        [(Integer 4 [])
                                                        (Integer 4 [])
                                                        (Real 4 [])
                                                        (Character 1 -2 () [])]
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :a4
                                                (Variable
                                                    2
                                                    a4
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Tuple
                                                        [(Tuple
                                                            [(Integer 4 [])
                                                            (Integer 4 [])
                                                            (Integer 4 [])]
                                                        )
                                                        (Tuple
                                                            [(Integer 4 [])
                                                            (Integer 4 [])
                                                            (Integer 4 [])]
                                                        )]
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :a5
                                                (Variable
                                                    2
                                                    a5
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Tuple
                                                        [(Tuple
                                                            [(Character 1 -2 () [])
                                                            (Character 1 -2 () [])
                                                            (Real 4 [])]
                                                        )
                                                        (Tuple
                                                            [(Character 1 -2 () [])
                                                            (Integer 4 [])
                                                            (Real 4 [])]
                                                        )]
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :b0
                                                (Variable
                                                    2
                                                    b0
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
                                            :b1
                                                (Variable
                                                    2
                                                    b1
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
                                            :b11
                                                (Variable
                                                    2
                                                    b11
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Tuple
                                                        [(Integer 4 [])
                                                        (Integer 4 [])]
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :float_mem
                                                (Variable
                                                    2
                                                    float_mem
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :float_mem1
                                                (Variable
                                                    2
                                                    float_mem1
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :float_mem2
                                                (Variable
                                                    2
                                                    float_mem2
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_Tuple
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
                                        (Var 2 a1)
                                        (TupleConstant
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))
                                            (IntegerConstant 3 (Integer 4 []))]
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])
                                                (Integer 4 [])]
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a1)
                                        (TupleConstant
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
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])
                                                (Integer 4 [])]
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a2)
                                        (TupleConstant
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
                                            (Tuple
                                                [(Character 1 1 () [])
                                                (Character 1 1 () [])
                                                (Character 1 1 () [])]
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 float_mem)
                                        (Cast
                                            (RealConstant
                                                0.450000
                                                (Real 8 [])
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                0.450000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a3)
                                        (TupleConstant
                                            [(IntegerUnaryMinus
                                                (IntegerConstant 2 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -2 (Integer 4 []))
                                            )
                                            (IntegerUnaryMinus
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -1 (Integer 4 []))
                                            )
                                            (Var 2 float_mem)
                                            (StringConstant
                                                "d"
                                                (Character 1 1 () [])
                                            )]
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])
                                                (Real 4 [])
                                                (Character 1 1 () [])]
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a4)
                                        (TupleConstant
                                            [(TupleConstant
                                                [(IntegerConstant 1 (Integer 4 []))
                                                (IntegerConstant 2 (Integer 4 []))
                                                (IntegerConstant 3 (Integer 4 []))]
                                                (Tuple
                                                    [(Integer 4 [])
                                                    (Integer 4 [])
                                                    (Integer 4 [])]
                                                )
                                            )
                                            (TupleConstant
                                                [(IntegerConstant 4 (Integer 4 []))
                                                (IntegerConstant 5 (Integer 4 []))
                                                (IntegerConstant 6 (Integer 4 []))]
                                                (Tuple
                                                    [(Integer 4 [])
                                                    (Integer 4 [])
                                                    (Integer 4 [])]
                                                )
                                            )]
                                            (Tuple
                                                [(Tuple
                                                    [(Integer 4 [])
                                                    (Integer 4 [])
                                                    (Integer 4 [])]
                                                )
                                                (Tuple
                                                    [(Integer 4 [])
                                                    (Integer 4 [])
                                                    (Integer 4 [])]
                                                )]
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 float_mem1)
                                        (Cast
                                            (RealConstant
                                                3.400000
                                                (Real 8 [])
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                3.400000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 float_mem2)
                                        (Cast
                                            (RealConstant
                                                5.600000
                                                (Real 8 [])
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                5.600000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a5)
                                        (TupleConstant
                                            [(TupleConstant
                                                [(StringConstant
                                                    "a"
                                                    (Character 1 1 () [])
                                                )
                                                (StringConstant
                                                    "b"
                                                    (Character 1 1 () [])
                                                )
                                                (Var 2 float_mem1)]
                                                (Tuple
                                                    [(Character 1 1 () [])
                                                    (Character 1 1 () [])
                                                    (Real 4 [])]
                                                )
                                            )
                                            (TupleConstant
                                                [(StringConstant
                                                    "c"
                                                    (Character 1 1 () [])
                                                )
                                                (IntegerConstant 3 (Integer 4 []))
                                                (Var 2 float_mem2)]
                                                (Tuple
                                                    [(Character 1 1 () [])
                                                    (Integer 4 [])
                                                    (Real 4 [])]
                                                )
                                            )]
                                            (Tuple
                                                [(Tuple
                                                    [(Character 1 1 () [])
                                                    (Character 1 1 () [])
                                                    (Real 4 [])]
                                                )
                                                (Tuple
                                                    [(Character 1 1 () [])
                                                    (Integer 4 [])
                                                    (Real 4 [])]
                                                )]
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b0)
                                        (TupleItem
                                            (Var 2 a1)
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (TupleConstant
                                            [(Var 2 b0)
                                            (Var 2 b1)]
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])]
                                            )
                                        )
                                        (TupleConstant
                                            [(TupleItem
                                                (Var 2 a1)
                                                (IntegerConstant 2 (Integer 4 []))
                                                (Integer 4 [])
                                                ()
                                            )
                                            (TupleItem
                                                (Var 2 a1)
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                ()
                                            )]
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])]
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a11)
                                        (TupleConstant
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))]
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])]
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b11)
                                        (TupleConstant
                                            [(IntegerConstant 1 (Integer 4 []))
                                            (IntegerConstant 2 (Integer 4 []))]
                                            (Tuple
                                                [(Integer 4 [])
                                                (Integer 4 [])]
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (TupleCompare
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
