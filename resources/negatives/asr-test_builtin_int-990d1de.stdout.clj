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
                                    [check_all]
                                    []
                                    [(SubroutineCall
                                        7 check_all
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :check_all
                                (Function
                                    (SymbolTable
                                        4
                                        {

                                        })
                                    check_all
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
                                    [test_int
                                    test_bool_to_int]
                                    []
                                    [(SubroutineCall
                                        7 test_int
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        7 test_bool_to_int
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_bool_to_int
                                (Function
                                    (SymbolTable
                                        3
                                        {
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
                                                )
                                        })
                                    test_bool_to_int
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
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Sub
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Sub
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Sub
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerUnaryMinus
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -1 (Integer 4 []))
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Sub
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 []) ;; badbeef
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Add
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Add
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Add
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            Add
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (IntegerBinOp
                                                (Cast
                                                    (LogicalConstant
                                                        true
                                                        (Logical 4 [])
                                                    )
                                                    LogicalToInteger
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                Add
                                                (Cast
                                                    (LogicalConstant
                                                        true
                                                        (Logical 4 [])
                                                    )
                                                    LogicalToInteger
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                (Integer 4 [])
                                                ()
                                            )
                                            Add
                                            (IntegerBinOp
                                                (Cast
                                                    (LogicalConstant
                                                        true
                                                        (Logical 4 [])
                                                    )
                                                    LogicalToInteger
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                Sub
                                                (Cast
                                                    (LogicalConstant
                                                        false
                                                        (Logical 4 [])
                                                    )
                                                    LogicalToInteger
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(Var 3 b)]
                                        ()
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerConstant 3 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 b)
                                        (IntegerBinOp
                                            (IntegerBinOp
                                                (Cast
                                                    (LogicalConstant
                                                        true
                                                        (Logical 4 [])
                                                    )
                                                    LogicalToInteger
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                Add
                                                (IntegerBinOp
                                                    (Cast
                                                        (LogicalConstant
                                                            true
                                                            (Logical 4 [])
                                                        )
                                                        LogicalToInteger
                                                        (Integer 4 [])
                                                        ()
                                                    )
                                                    Add
                                                    (Cast
                                                        (LogicalConstant
                                                            true
                                                            (Logical 4 [])
                                                        )
                                                        LogicalToInteger
                                                        (Integer 4 [])
                                                        ()
                                                    )
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                (Integer 4 [])
                                                ()
                                            )
                                            Sub
                                            (IntegerBinOp
                                                (Cast
                                                    (LogicalConstant
                                                        false
                                                        (Logical 4 [])
                                                    )
                                                    LogicalToInteger
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                Add
                                                (Cast
                                                    (LogicalConstant
                                                        true
                                                        (Logical 4 [])
                                                    )
                                                    LogicalToInteger
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                (Integer 4 [])
                                                ()
                                            )
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 3 b)
                                            Eq
                                            (IntegerConstant 2 (Integer 4 []))
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
                            :test_int
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :f
                                                (Variable
                                                    2
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
                                            :i2
                                                (Variable
                                                    2
                                                    i2
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    test_int
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
                                        (Var 2 f)
                                        (RealConstant
                                            5.678000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i)
                                        (IntegerConstant 4 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (IntegerConstant 0 (Integer 8 []))
                                            Eq
                                            (Cast
                                                (IntegerConstant 0 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 0 (Integer 8 []))
                                            )
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
                                            (Cast
                                                (Var 2 i)
                                                IntegerToInteger
                                                (Integer 8 [])
                                                ()
                                            )
                                            Eq
                                            (Cast
                                                (IntegerConstant 4 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 4 (Integer 8 []))
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 i2)
                                        (Cast
                                            (RealConstant
                                                3.000000
                                                (Real 8 [])
                                            )
                                            RealToInteger
                                            (Integer 8 [])
                                            (IntegerConstant 3 (Integer 8 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 2 i2)
                                            Eq
                                            (Cast
                                                (IntegerConstant 3 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 3 (Integer 8 []))
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Cast
                                                (RealConstant
                                                    5.678000
                                                    (Real 8 [])
                                                )
                                                RealToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 5 (Integer 8 []))
                                            )
                                            Eq
                                            (Cast
                                                (IntegerConstant 5 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 5 (Integer 8 []))
                                            )
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
                                            (Cast
                                                (Var 2 f)
                                                RealToInteger
                                                (Integer 8 [])
                                                ()
                                            )
                                            Eq
                                            (Cast
                                                (IntegerConstant 5 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 5 (Integer 8 []))
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 f)
                                        (RealUnaryMinus
                                            (RealConstant
                                                183745.230000
                                                (Real 8 [])
                                            )
                                            (Real 8 [])
                                            (RealConstant
                                                -183745.230000
                                                (Real 8 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Cast
                                                (RealUnaryMinus
                                                    (RealConstant
                                                        183745.230000
                                                        (Real 8 [])
                                                    )
                                                    (Real 8 [])
                                                    (RealConstant
                                                        -183745.230000
                                                        (Real 8 [])
                                                    )
                                                )
                                                RealToInteger
                                                (Integer 8 [])
                                                (IntegerConstant -183745 (Integer 8 []))
                                            )
                                            Eq
                                            (Cast
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 183745 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -183745 (Integer 4 []))
                                                )
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant -183745 (Integer 8 []))
                                            )
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
                                            (Cast
                                                (Var 2 f)
                                                RealToInteger
                                                (Integer 8 [])
                                                ()
                                            )
                                            Eq
                                            (Cast
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 183745 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -183745 (Integer 4 []))
                                                )
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant -183745 (Integer 8 []))
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Cast
                                                (RealConstant
                                                    5.500000
                                                    (Real 8 [])
                                                )
                                                RealToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 5 (Integer 8 []))
                                            )
                                            Eq
                                            (Cast
                                                (IntegerConstant 5 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 5 (Integer 8 []))
                                            )
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
                                            (Cast
                                                (RealUnaryMinus
                                                    (RealConstant
                                                        5.500000
                                                        (Real 8 [])
                                                    )
                                                    (Real 8 [])
                                                    (RealConstant
                                                        -5.500000
                                                        (Real 8 [])
                                                    )
                                                )
                                                RealToInteger
                                                (Integer 8 [])
                                                (IntegerConstant -5 (Integer 8 []))
                                            )
                                            Eq
                                            (Cast
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 5 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -5 (Integer 4 []))
                                                )
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant -5 (Integer 8 []))
                                            )
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
                                            (Cast
                                                (LogicalConstant
                                                    true
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 1 (Integer 8 []))
                                            )
                                            Eq
                                            (Cast
                                                (IntegerConstant 1 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 1 (Integer 8 []))
                                            )
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
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 0 (Integer 8 []))
                                            )
                                            Eq
                                            (Cast
                                                (IntegerConstant 0 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 8 [])
                                                (IntegerConstant 0 (Integer 8 []))
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
