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
                                    [f
                                    bool_to_str]
                                    []
                                    [(SubroutineCall
                                        7 f
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        7 bool_to_str
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :bool_to_str
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :var
                                                (Variable
                                                    4
                                                    var
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Logical 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    bool_to_str
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
                                        (Var 4 var)
                                        (LogicalConstant
                                            true
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(Cast
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                            LogicalToCharacter
                                            (Character 1 -2 () [])
                                            (StringConstant
                                                "True"
                                                (Character 1 4 () [])
                                            )
                                        )]
                                        ()
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Cast
                                                (Var 4 var)
                                                LogicalToCharacter
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "True"
                                                (Character 1 4 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 var)
                                        (LogicalConstant
                                            false
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Cast
                                                (Var 4 var)
                                                LogicalToCharacter
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "False"
                                                (Character 1 5 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                0xBADBEEF
                                                (Character 1 -2 () [])
                                                (StringConstant
                                                    "True"
                                                    (Character 1 4 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "True"
                                                (Character 1 4 () [])
                                            )
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
                                                    3
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
                                            :x
                                                (Variable
                                                    3
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
                                    [g]
                                    []
                                    [(=
                                        (Var 3 a)
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 x)
                                        (IntegerConstant 3 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 x)
                                        (IntegerConstant 5 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (Var 3 x)
                                            Add
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(Var 3 a)
                                        (Var 3 b)]
                                        ()
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerConstant 6 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (SubroutineCall
                                        7 g
                                        ()
                                        [((IntegerBinOp
                                            (IntegerBinOp
                                                (Var 3 a)
                                                Mul
                                                (Var 3 b)
                                                (Integer 4 [])
                                                ()
                                            )
                                            Add
                                            (IntegerConstant 3 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        ))]
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
                                        2
                                        {
                                            :x
                                                (Variable
                                                    2
                                                    x
                                                    []
                                                    In
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
                                    g
                                    (FunctionType
                                        [(Integer 4 [])]
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
                                    [(Var 2 x)]
                                    [(Print
                                        ()
                                        [(Var 2 x)]
                                        ()
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :x
                                (Variable
                                    7
                                    x
                                    []
                                    Local
                                    (IntegerConstant 7 (Integer 4 []))
                                    (IntegerConstant 7 (Integer 4 []))
                                    Default
                                    (Integer 4 [])
                                    Source
                                    Public
                                    Required
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
