(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        116
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        115
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
                                    [check]
                                    []
                                    [(SubroutineCall
                                        116 check
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :check
                                (Function
                                    (SymbolTable
                                        6
                                        {
                                            
                                        })
                                    check
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
                                    [test_max_int
                                    test_max_float
                                    test_min_int
                                    test_min_float]
                                    []
                                    [(SubroutineCall
                                        116 test_max_int
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        116 test_max_float
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        116 test_min_int
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        116 test_min_float
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_max_float
                                (Function
                                    (SymbolTable
                                        3
                                        {
                                            :d
                                                (Variable
                                                    3
                                                    d
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :e
                                                (Variable
                                                    3
                                                    e
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :f
                                                (Variable
                                                    3
                                                    f
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :max
                                                (ExternalSymbol
                                                    3
                                                    max
                                                    8 max
                                                    lpython_builtin
                                                    []
                                                    max
                                                    Private
                                                ),
                                            :max__AT____lpython_overloaded_2__max
                                                (ExternalSymbol
                                                    3
                                                    max__AT____lpython_overloaded_2__max
                                                    8 __lpython_overloaded_2__max
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_2__max
                                                    Public
                                                ),
                                            :max__AT____lpython_overloaded_3__max
                                                (ExternalSymbol
                                                    3
                                                    max__AT____lpython_overloaded_3__max
                                                    8 __lpython_overloaded_3__max
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_3__max
                                                    Public
                                                )
                                        })
                                    test_max_float
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
                                    [max__AT____lpython_overloaded_2__max
                                    max__AT____lpython_overloaded_3__max]
                                    []
                                    [(=
                                        (Var 3 d)
                                        (RealConstant
                                            23.233000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 e)
                                        (RealConstant
                                            23.223300
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 f)
                                        (RealConstant
                                            21.230000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (FunctionCall
                                                3 max__AT____lpython_overloaded_2__max
                                                3 max
                                                [((Var 3 d))
                                                ((Var 3 e))
                                                ((Var 3 f))]
                                                (Real 8 [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (Var 3 d)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (FunctionCall
                                                3 max__AT____lpython_overloaded_3__max
                                                3 max
                                                [((Var 3 e))
                                                ((Var 3 f))]
                                                (Real 8 [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (Var 3 e)
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
                            :test_max_int
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
                                                    (Integer 4 [])
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
                                                    (Integer 4 [])
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
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :max
                                                (ExternalSymbol
                                                    2
                                                    max
                                                    8 max
                                                    lpython_builtin
                                                    []
                                                    max
                                                    Private
                                                ),
                                            :max__AT____lpython_overloaded_0__max
                                                (ExternalSymbol
                                                    2
                                                    max__AT____lpython_overloaded_0__max
                                                    8 __lpython_overloaded_0__max
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_0__max
                                                    Public
                                                ),
                                            :max__AT____lpython_overloaded_1__max
                                                (ExternalSymbol
                                                    2
                                                    max__AT____lpython_overloaded_1__max
                                                    8 __lpython_overloaded_1__max
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_1__max
                                                    Public
                                                )
                                        })
                                    test_max_int
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
                                    [max__AT____lpython_overloaded_0__max
                                    max__AT____lpython_overloaded_1__max]
                                    []
                                    [(=
                                        (Var 2 a)
                                        (IntegerConstant 1 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 b)
                                        (IntegerConstant 2 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (IntegerConstant 3 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (FunctionCall
                                                2 max__AT____lpython_overloaded_0__max
                                                2 max
                                                [((Var 2 a))
                                                ((Var 2 b))]
                                                (Integer 4 [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (Var 2 b)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (FunctionCall
                                                2 max__AT____lpython_overloaded_1__max
                                                2 max
                                                [((Var 2 a))
                                                ((Var 2 b))
                                                ((Var 2 c))]
                                                (Integer 4 [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (Var 2 c)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (FunctionCall
                                                2 max__AT____lpython_overloaded_1__max
                                                2 max
                                                [((IntegerConstant 1 (Integer 4 [])))
                                                ((IntegerConstant 2 (Integer 4 [])))
                                                ((IntegerConstant 3 (Integer 4 [])))]
                                                (Integer 4 [])
                                                (IntegerConstant 3 (Integer 4 []))
                                                ()
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
                                    (Assert
                                        (IntegerCompare
                                            (FunctionCall
                                                2 max__AT____lpython_overloaded_0__max
                                                2 max
                                                [((IntegerConstant 1 (Integer 4 [])))
                                                ((IntegerConstant 6 (Integer 4 [])))]
                                                (Integer 4 [])
                                                (IntegerConstant 6 (Integer 4 []))
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 6 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_min_float
                                (Function
                                    (SymbolTable
                                        5
                                        {
                                            :d
                                                (Variable
                                                    5
                                                    d
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :e
                                                (Variable
                                                    5
                                                    e
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :f
                                                (Variable
                                                    5
                                                    f
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :min
                                                (ExternalSymbol
                                                    5
                                                    min
                                                    8 min
                                                    lpython_builtin
                                                    []
                                                    min
                                                    Private
                                                ),
                                            :min__AT____lpython_overloaded_2__min
                                                (ExternalSymbol
                                                    5
                                                    min__AT____lpython_overloaded_2__min
                                                    8 __lpython_overloaded_2__min
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_2__min
                                                    Public
                                                ),
                                            :min__AT____lpython_overloaded_3__min
                                                (ExternalSymbol
                                                    5
                                                    min__AT____lpython_overloaded_3__min
                                                    8 __lpython_overloaded_3__min
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_3__min
                                                    Public
                                                )
                                        })
                                    test_min_float
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
                                    [min__AT____lpython_overloaded_2__min
                                    min__AT____lpython_overloaded_3__min]
                                    []
                                    [(=
                                        (Var 5 d)
                                        (RealConstant
                                            23.233000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 5 e)
                                        (RealConstant
                                            23.223300
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 5 f)
                                        (RealConstant
                                            21.230000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (FunctionCall
                                                5 min__AT____lpython_overloaded_2__min
                                                5 min
                                                [((Var 5 d))
                                                ((Var 5 e))
                                                ((Var 5 f))]
                                                (Real 8 [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (Var 5 f)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (RealCompare
                                            (FunctionCall
                                                5 min__AT____lpython_overloaded_3__min
                                                5 min
                                                [((Var 5 e))
                                                ((Var 5 f))]
                                                (Real 8 [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (Var 5 f)
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
                            :test_min_int
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :a
                                                (Variable
                                                    4
                                                    a
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
                                            :b
                                                (Variable
                                                    4
                                                    b
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
                                            :c
                                                (Variable
                                                    4
                                                    c
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
                                            :min
                                                (ExternalSymbol
                                                    4
                                                    min
                                                    8 min
                                                    lpython_builtin
                                                    []
                                                    min
                                                    Private
                                                ),
                                            :min__AT____lpython_overloaded_0__min
                                                (ExternalSymbol
                                                    4
                                                    min__AT____lpython_overloaded_0__min
                                                    8 __lpython_overloaded_0__min
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_0__min
                                                    Public
                                                ),
                                            :min__AT____lpython_overloaded_1__min
                                                (ExternalSymbol
                                                    4
                                                    min__AT____lpython_overloaded_1__min
                                                    8 __lpython_overloaded_1__min
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_1__min
                                                    Public
                                                )
                                        })
                                    test_min_int
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
                                    [min__AT____lpython_overloaded_0__min
                                    min__AT____lpython_overloaded_1__min]
                                    []
                                    [(=
                                        (Var 4 a)
                                        (IntegerConstant 1 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 4 b)
                                        (IntegerConstant 2 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 4 c)
                                        (IntegerConstant 3 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (FunctionCall
                                                4 min__AT____lpython_overloaded_0__min
                                                4 min
                                                [((Var 4 a))
                                                ((Var 4 b))]
                                                (Integer 4 [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (Var 4 a)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (FunctionCall
                                                4 min__AT____lpython_overloaded_1__min
                                                4 min
                                                [((Var 4 a))
                                                ((Var 4 b))
                                                ((Var 4 c))]
                                                (Integer 4 [])
                                                ()
                                                ()
                                            )
                                            Eq
                                            (Var 4 a)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (FunctionCall
                                                4 min__AT____lpython_overloaded_1__min
                                                4 min
                                                [((IntegerConstant 1 (Integer 4 [])))
                                                ((IntegerConstant 2 (Integer 4 [])))
                                                ((IntegerConstant 3 (Integer 4 [])))]
                                                (Integer 4 [])
                                                (IntegerConstant 1 (Integer 4 []))
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
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
                                            (FunctionCall
                                                4 min__AT____lpython_overloaded_0__min
                                                4 min
                                                [((IntegerConstant 1 (Integer 4 [])))
                                                ((IntegerConstant 6 (Integer 4 [])))]
                                                (Integer 4 [])
                                                (IntegerConstant 1 (Integer 4 []))
                                                ()
                                            )
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
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
                        114
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    114
                                    _lpython_main_program
                                    116 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        114 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
