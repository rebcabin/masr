(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        8
                        {
                            :A
                                (StructType
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
                                                    (Real 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    A
                                    []
                                    [y
                                    x]
                                    Source
                                    Public
                                    false
                                    false
                                    ()
                                    ()
                                ),
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        7
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
                                    [g]
                                    []
                                    [(SubroutineCall
                                        8 g
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :change_struct
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :a
                                                (Variable
                                                    4
                                                    a
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Struct
                                                        8 A
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    change_struct
                                    (FunctionType
                                        [(Struct
                                            8 A
                                            []
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
                                    [(Var 4 a)]
                                    [(=
                                        (StructInstanceMember
                                            (Var 4 a)
                                            2 x
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerBinOp
                                            (StructInstanceMember
                                                (Var 4 a)
                                                2 x
                                                (Integer 4 [])
                                                ()
                                            )
                                            Add
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (StructInstanceMember
                                            (Var 4 a)
                                            2 y
                                            (Real 4 [])
                                            ()
                                        )
                                        (RealBinOp
                                            (StructInstanceMember
                                                (Var 4 a)
                                                2 y
                                                (Real 4 [])
                                                ()
                                            )
                                            Add
                                            (Cast
                                                (IntegerConstant 1 (Integer 4 []))
                                                IntegerToReal
                                                (Real 4 [])
                                                (RealConstant
                                                    1.000000
                                                    (Real 4 [])
                                                )
                                            )
                                            (Real 4 [])
                                            ()
                                        )
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
                                        3
                                        {
                                            :a
                                                (Variable
                                                    3
                                                    a
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Struct
                                                        8 A
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
                                        [(Struct
                                            8 A
                                            []
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
                                    [(Var 3 a)]
                                    [(Print
                                        ()
                                        [(StructInstanceMember
                                            (Var 3 a)
                                            2 x
                                            (Integer 4 [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(StructInstanceMember
                                            (Var 3 a)
                                            2 y
                                            (Real 4 [])
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
                            :g
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
                                                    (Struct
                                                        8 A
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    g
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
                                    [f
                                    change_struct]
                                    []
                                    [(=
                                        (Var 5 x)
                                        (StructTypeConstructor
                                            8 A
                                            [((Cast
                                                (RealConstant
                                                    3.250000
                                                    (Real 8 [])
                                                )
                                                RealToReal
                                                (Real 4 [])
                                                (RealConstant
                                                    3.250000
                                                    (Real 4 [])
                                                )
                                            ))
                                            ((IntegerConstant 3 (Integer 4 [])))]
                                            (Struct
                                                8 A
                                                []
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (SubroutineCall
                                        8 f
                                        ()
                                        [((Var 5 x))]
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StructInstanceMember
                                                (Var 5 x)
                                                2 x
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 3 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (Cast
                                                (StructInstanceMember
                                                    (Var 5 x)
                                                    2 y
                                                    (Real 4 [])
                                                    ()
                                                )
                                                RealToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Eq
                                            (RealConstant
                                                3.250000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (StructInstanceMember
                                            (Var 5 x)
                                            2 x
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (StructInstanceMember
                                            (Var 5 x)
                                            2 y
                                            (Real 4 [])
                                            ()
                                        )
                                        (Cast
                                            (RealConstant
                                                5.500000
                                                (Real 8 [])
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                5.500000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (SubroutineCall
                                        8 f
                                        ()
                                        [((Var 5 x))]
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StructInstanceMember
                                                (Var 5 x)
                                                2 x
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
                                    (Assert
                                        (RealCompare
                                            (Cast
                                                (StructInstanceMember
                                                    (Var 5 x)
                                                    2 y
                                                    (Real 4 [])
                                                    ()
                                                )
                                                RealToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Eq
                                            (RealConstant
                                                5.500000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (SubroutineCall
                                        8 change_struct
                                        ()
                                        [((Var 5 x))]
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StructInstanceMember
                                                (Var 5 x)
                                                2 x
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 6 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (Cast
                                                (StructInstanceMember
                                                    (Var 5 x)
                                                    2 y
                                                    (Real 4 [])
                                                    ()
                                                )
                                                RealToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Eq
                                            (RealConstant
                                                6.500000
                                                (Real 8 [])
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
                        6
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    6
                                    _lpython_main_program
                                    8 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        6 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
