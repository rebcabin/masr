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
                            :B
                                (StructType
                                    (SymbolTable
                                        3
                                        {
                                            :a
                                                (Variable
                                                    3
                                                    a
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
                                                ),
                                            :z
                                                (Variable
                                                    3
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
                                    B
                                    [A]
                                    [a
                                    z]
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
                            :f
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :b
                                                (Variable
                                                    4
                                                    b
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Struct
                                                        8 B
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
                                            8 B
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
                                    [(Var 4 b)]
                                    [(Print
                                        ()
                                        [(StructInstanceMember
                                            (Var 4 b)
                                            3 z
                                            (Integer 4 [])
                                            ()
                                        )
                                        (StructInstanceMember
                                            (StructInstanceMember
                                                (Var 4 b)
                                                3 a
                                                (Struct
                                                    8 A
                                                    []
                                                )
                                                ()
                                            )
                                            2 x
                                            (Integer 4 [])
                                            ()
                                        )
                                        (StructInstanceMember
                                            (StructInstanceMember
                                                (Var 4 b)
                                                3 a
                                                (Struct
                                                    8 A
                                                    []
                                                )
                                                ()
                                            )
                                            2 y
                                            (Real 4 [])
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StructInstanceMember
                                                (Var 4 b)
                                                3 z
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StructInstanceMember
                                                (StructInstanceMember
                                                    (Var 4 b)
                                                    3 a
                                                    (Struct
                                                        8 A
                                                        []
                                                    )
                                                    ()
                                                )
                                                2 x
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (Cast
                                                (StructInstanceMember
                                                    (StructInstanceMember
                                                        (Var 4 b)
                                                        3 a
                                                        (Struct
                                                            8 A
                                                            []
                                                        )
                                                        ()
                                                    )
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
                                                3.000000
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
                                ),
                            :g
                                (Function
                                    (SymbolTable
                                        5
                                        {
                                            :a1
                                                (Variable
                                                    5
                                                    a1
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
                                                ),
                                            :a2
                                                (Variable
                                                    5
                                                    a2
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
                                                ),
                                            :b
                                                (Variable
                                                    5
                                                    b
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Struct
                                                        8 B
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
                                    [f]
                                    []
                                    [(=
                                        (Var 5 a1)
                                        (StructTypeConstructor
                                            8 A
                                            [((Cast
                                                (RealConstant
                                                    1.000000
                                                    (Real 8 [])
                                                )
                                                RealToReal
                                                (Real 4 [])
                                                (RealConstant
                                                    1.000000
                                                    (Real 4 [])
                                                )
                                            ))
                                            ((IntegerConstant 1 (Integer 4 [])))]
                                            (Struct
                                                8 A
                                                []
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 5 a2)
                                        (StructTypeConstructor
                                            8 A
                                            [((Cast
                                                (RealConstant
                                                    2.000000
                                                    (Real 8 [])
                                                )
                                                RealToReal
                                                (Real 4 [])
                                                (RealConstant
                                                    2.000000
                                                    (Real 4 [])
                                                )
                                            ))
                                            ((IntegerConstant 2 (Integer 4 [])))]
                                            (Struct
                                                8 A
                                                []
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 5 b)
                                        (StructTypeConstructor
                                            8 B
                                            [((Var 5 a1))
                                            ((IntegerConstant 1 (Integer 4 [])))]
                                            (Struct
                                                8 B
                                                []
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (StructInstanceMember
                                            (Var 5 b)
                                            3 a
                                            (Struct
                                                8 A
                                                []
                                            )
                                            ()
                                        )
                                        (Var 5 a2)
                                        ()
                                    )
                                    (=
                                        (StructInstanceMember
                                            (Var 5 b)
                                            3 z
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 1 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (StructInstanceMember
                                            (StructInstanceMember
                                                (Var 5 b)
                                                3 a
                                                (Struct
                                                    8 A
                                                    []
                                                )
                                                ()
                                            )
                                            2 x
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 2 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (StructInstanceMember
                                            (StructInstanceMember
                                                (Var 5 b)
                                                3 a
                                                (Struct
                                                    8 A
                                                    []
                                                )
                                                ()
                                            )
                                            2 y
                                            (Real 4 [])
                                            ()
                                        )
                                        (Cast
                                            (RealConstant
                                                3.000000
                                                (Real 8 [])
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                3.000000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StructInstanceMember
                                                (Var 5 a1)
                                                2 x
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (Cast
                                                (StructInstanceMember
                                                    (Var 5 a1)
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
                                                1.000000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (StructInstanceMember
                                                (Var 5 a2)
                                                2 x
                                                (Integer 4 [])
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (Cast
                                                (StructInstanceMember
                                                    (Var 5 a2)
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
                                                2.000000
                                                (Real 8 [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (SubroutineCall
                                        8 f
                                        ()
                                        [((Var 5 b))]
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
