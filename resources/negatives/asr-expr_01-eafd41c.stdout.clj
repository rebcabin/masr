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
                                    [main0]
                                    []
                                    [(SubroutineCall
                                        7 main0
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :add
                                (Function
                                    (SymbolTable
                                        2
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    2
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
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
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Integer 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            0xBADBEEF
                                                (Variable
                                                    2
                                                    y
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
                                    add
                                    (FunctionType
                                        [(Integer 4 [])
                                        (Integer 4 [])]
                                        (Integer 4 [])
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        true
                                        false
                                        []
                                        []
                                        false
                                    )
                                    []
                                    [(Var 2 x)
                                    (Var 2 y)]
                                    [(=
                                        (Var 2 _lpython_return_variable)
                                        (IntegerBinOp
                                            (Var 2 x)
                                            Add
                                            (Var 2 y)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 2 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :and_op
                                (Function
                                    (SymbolTable
                                        3
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    3
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
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
                                                    In
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
                                                    3
                                                    y
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
                                    and_op
                                    (FunctionType
                                        [(Integer 4 [])
                                        (Integer 4 [])]
                                        (Integer 4 [])
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        true
                                        false
                                        []
                                        []
                                        false
                                    )
                                    []
                                    [(Var 3 x)
                                    (Var 3 y)]
                                    [(=
                                        (Var 3 _lpython_return_variable)
                                        (IntegerBinOp
                                            (Var 3 x)
                                            BitAnd
                                            (Var 3 y)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 3 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :main0
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :x
                                                (Variable
                                                    4
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
                                                    4
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
                                                ),
                                            :z
                                                (Variable
                                                    4
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
                                    main0
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
                                    [add
                                    and_op]
                                    []
                                    [(=
                                        (Var 4 x)
                                        (IntegerBinOp
                                            (IntegerBinOp
                                                (IntegerConstant 2 (Integer 4 []))
                                                Add
                                                (IntegerConstant 3 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant 5 (Integer 4 []))
                                            )
                                            Mul
                                            (IntegerConstant 5 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant 25 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 y)
                                        (IntegerBinOp
                                            (FunctionCall
                                                7 add
                                                ()
                                                [((Var 4 x))
                                                ((IntegerConstant 2 (Integer 4 [])))]
                                                (Integer 4 [])
                                                ()
                                                ()
                                            )
                                            Mul
                                            (IntegerConstant 2 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 4 x)
                                            Eq
                                            (IntegerConstant 25 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 4 y)
                                            Eq
                                            (IntegerConstant 54 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 z)
                                        (FunctionCall
                                            7 and_op
                                            ()
                                            [((Var 4 x))
                                            ((Var 4 y))]
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (IntegerCompare
                                            (Var 4 z)
                                            Eq
                                            (IntegerConstant 16 (Integer 4 []))
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
