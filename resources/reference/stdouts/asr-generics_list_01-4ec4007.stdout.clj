(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        21
                        {
                            :T
                                (Variable
                                    21
                                    T
                                    []
                                    Local
                                    ()
                                    ()
                                    Default
                                    (TypeParameter
                                        T
                                        []
                                    )
                                    Source
                                    Public
                                    Required
                                    false
                                ),
                            :__asr_generic_mean_0
                                (Function
                                    (SymbolTable
                                        16
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    16
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
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
                                                    16
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
                                            :k
                                                (Variable
                                                    16
                                                    k
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
                                            :res
                                                (Variable
                                                    16
                                                    res
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
                                                    16
                                                    x
                                                    []
                                                    In
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
                                                )
                                        })
                                    __asr_generic_mean_0
                                    (FunctionType
                                        [(List
                                            (Integer 4 [])
                                        )]
                                        (Real 8 [])
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
                                    [empty_integer
                                    add_integer
                                    div_integer]
                                    [(Var 16 x)]
                                    [(=
                                        (Var 16 k)
                                        (ListLen
                                            (Var 16 x)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (If
                                        (IntegerCompare
                                            (Var 16 k)
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        [(=
                                            (Var 16 _lpython_return_variable)
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
                                            )
                                            ()
                                        )
                                        (Return)]
                                        []
                                    )
                                    (=
                                        (Var 16 res)
                                        (FunctionCall
                                            21 empty_integer
                                            ()
                                            [((ListItem
                                                (Var 16 x)
                                                (IntegerConstant 0 (Integer 4 []))
                                                (Integer 4 [])
                                                ()
                                            ))]
                                            (Integer 4 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 16 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (Var 16 k)
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(=
                                            (Var 16 res)
                                            (FunctionCall
                                                21 add_integer
                                                ()
                                                [((Var 16 res))
                                                ((ListItem
                                                    (Var 16 x)
                                                    (Var 16 i)
                                                    (Integer 4 [])
                                                    ()
                                                ))]
                                                (Integer 4 [])
                                                ()
                                                ()
                                            )
                                            ()
                                        )]
                                    )
                                    (=
                                        (Var 16 _lpython_return_variable)
                                        (FunctionCall
                                            21 div_integer
                                            ()
                                            [((Var 16 res))
                                            ((Var 16 k))]
                                            (Real 8 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 16 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :__asr_generic_mean_1
                                (Function
                                    (SymbolTable
                                        17
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    17
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
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
                                                    17
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
                                            :k
                                                (Variable
                                                    17
                                                    k
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
                                            :res
                                                (Variable
                                                    17
                                                    res
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
                                            :x
                                                (Variable
                                                    17
                                                    x
                                                    []
                                                    In
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
                                                )
                                        })
                                    __asr_generic_mean_1
                                    (FunctionType
                                        [(List
                                            (Real 8 [])
                                        )]
                                        (Real 8 [])
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
                                    [empty_float
                                    add_float
                                    div_float]
                                    [(Var 17 x)]
                                    [(=
                                        (Var 17 k)
                                        (ListLen
                                            (Var 17 x)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (If
                                        (IntegerCompare
                                            (Var 17 k)
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        [(=
                                            (Var 17 _lpython_return_variable)
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
                                            )
                                            ()
                                        )
                                        (Return)]
                                        []
                                    )
                                    (=
                                        (Var 17 res)
                                        (FunctionCall
                                            21 empty_float
                                            ()
                                            [((ListItem
                                                (Var 17 x)
                                                (IntegerConstant 0 (Integer 4 []))
                                                (Real 8 [])
                                                ()
                                            ))]
                                            (Real 8 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 17 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (Var 17 k)
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(=
                                            (Var 17 res)
                                            (FunctionCall
                                                21 add_float
                                                ()
                                                [((Var 17 res))
                                                ((ListItem
                                                    (Var 17 x)
                                                    (Var 17 i)
                                                    (Real 8 [])
                                                    ()
                                                ))]
                                                (Real 8 [])
                                                ()
                                                ()
                                            )
                                            ()
                                        )]
                                    )
                                    (=
                                        (Var 17 _lpython_return_variable)
                                        (FunctionCall
                                            21 div_float
                                            ()
                                            [((Var 17 res))
                                            ((Var 17 k))]
                                            (Real 8 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 17 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :__asr_generic_mean_2
                                (Function
                                    (SymbolTable
                                        18
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    18
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
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
                                                    18
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
                                            :k
                                                (Variable
                                                    18
                                                    k
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
                                            :res
                                                (Variable
                                                    18
                                                    res
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 1 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    18
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (Character 1 1 () [])
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    __asr_generic_mean_2
                                    (FunctionType
                                        [(List
                                            (Character 1 1 () [])
                                        )]
                                        (Real 8 [])
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
                                    [empty_string
                                    add_string
                                    div_string]
                                    [(Var 18 x)]
                                    [(=
                                        (Var 18 k)
                                        (ListLen
                                            (Var 18 x)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (If
                                        (IntegerCompare
                                            (Var 18 k)
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        [(=
                                            (Var 18 _lpython_return_variable)
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
                                            )
                                            ()
                                        )
                                        (Return)]
                                        []
                                    )
                                    (=
                                        (Var 18 res)
                                        (FunctionCall
                                            21 empty_string
                                            ()
                                            [((ListItem
                                                (Var 18 x)
                                                (IntegerConstant 0 (Integer 4 []))
                                                (Character 1 1 () [])
                                                ()
                                            ))]
                                            (Character 1 1 () [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 18 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (Var 18 k)
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(=
                                            (Var 18 res)
                                            (FunctionCall
                                                21 add_string
                                                ()
                                                [((Var 18 res))
                                                ((ListItem
                                                    (Var 18 x)
                                                    (Var 18 i)
                                                    (Character 1 1 () [])
                                                    ()
                                                ))]
                                                (Character 1 1 () [])
                                                ()
                                                ()
                                            )
                                            ()
                                        )]
                                    )
                                    (=
                                        (Var 18 _lpython_return_variable)
                                        (FunctionCall
                                            21 div_string
                                            ()
                                            [((Var 18 res))
                                            ((Var 18 k))]
                                            (Real 8 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 18 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        20
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
                                    [__asr_generic_mean_0
                                    __asr_generic_mean_1
                                    __asr_generic_mean_2]
                                    []
                                    [(Print
                                        ()
                                        [(FunctionCall
                                            21 __asr_generic_mean_0
                                            ()
                                            [((ListConstant
                                                [(IntegerConstant 1 (Integer 4 []))
                                                (IntegerConstant 2 (Integer 4 []))
                                                (IntegerConstant 3 (Integer 4 []))]
                                                (List
                                                    (Integer 4 [])
                                                )
                                            ))]
                                            (Real 8 [])
                                            ()
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(FunctionCall
                                            21 __asr_generic_mean_1
                                            ()
                                            [((ListConstant
                                                [(RealConstant
                                                    1.000000
                                                    (Real 8 [])
                                                )
                                                (RealConstant
                                                    2.000000
                                                    (Real 8 [])
                                                )
                                                (RealConstant
                                                    3.000000
                                                    (Real 8 [])
                                                )]
                                                (List
                                                    (Real 8 [])
                                                )
                                            ))]
                                            (Real 8 [])
                                            ()
                                            ()
                                        )]
                                        ()
                                        ()
                                    )
                                    (Print
                                        ()
                                        [(FunctionCall
                                            21 __asr_generic_mean_2
                                            ()
                                            [((ListConstant
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
                                                (List
                                                    (Character 1 1 () [])
                                                )
                                            ))]
                                            (Real 8 [])
                                            ()
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
                            :add
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
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
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
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
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
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    add
                                    (FunctionType
                                        [(TypeParameter
                                            T
                                            []
                                        )
                                        (TypeParameter
                                            T
                                            []
                                        )]
                                        (TypeParameter
                                            T
                                            []
                                        )
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        false
                                        false
                                        [(TypeParameter
                                            T
                                            []
                                        )]
                                        []
                                        true
                                    )
                                    []
                                    [(Var 3 x)
                                    (Var 3 y)]
                                    []
                                    (Var 3 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :add_float
                                (Function
                                    (SymbolTable
                                        9
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    9
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    9
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :y
                                                (Variable
                                                    9
                                                    y
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    add_float
                                    (FunctionType
                                        [(Real 8 [])
                                        (Real 8 [])]
                                        (Real 8 [])
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
                                    [(Var 9 x)
                                    (Var 9 y)]
                                    [(=
                                        (Var 9 _lpython_return_variable)
                                        (RealBinOp
                                            (Var 9 x)
                                            Add
                                            (Var 9 y)
                                            (Real 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 9 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :add_integer
                                (Function
                                    (SymbolTable
                                        6
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    6
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
                                                    6
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
                                                    6
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
                                    add_integer
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
                                        false
                                        false
                                        []
                                        []
                                        false
                                    )
                                    []
                                    [(Var 6 x)
                                    (Var 6 y)]
                                    [(=
                                        (Var 6 _lpython_return_variable)
                                        (IntegerBinOp
                                            (Var 6 x)
                                            Add
                                            (Var 6 y)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 6 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :add_string
                                (Function
                                    (SymbolTable
                                        12
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    12
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    12
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :y
                                                (Variable
                                                    12
                                                    y
                                                    []
                                                    In
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
                                    add_string
                                    (FunctionType
                                        [(Character 1 -2 () [])
                                        (Character 1 -2 () [])]
                                        (Character 1 -2 () [])
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
                                    [(Var 12 x)
                                    (Var 12 y)]
                                    [(=
                                        (Var 12 _lpython_return_variable)
                                        (StringConcat
                                            (Var 12 x)
                                            (Var 12 y)
                                            (Character 1 -4 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 12 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :div
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    4
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :k
                                                (Variable
                                                    4
                                                    k
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
                                            :x
                                                (Variable
                                                    4
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    div
                                    (FunctionType
                                        [(TypeParameter
                                            T
                                            []
                                        )
                                        (Integer 4 [])]
                                        (Real 8 [])
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        false
                                        false
                                        [(TypeParameter
                                            T
                                            []
                                        )]
                                        []
                                        true
                                    )
                                    []
                                    [(Var 4 x)
                                    (Var 4 k)]
                                    []
                                    (Var 4 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :div_float
                                (Function
                                    (SymbolTable
                                        10
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    10
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :k
                                                (Variable
                                                    10
                                                    k
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
                                            :x
                                                (Variable
                                                    10
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    div_float
                                    (FunctionType
                                        [(Real 8 [])
                                        (Integer 4 [])]
                                        (Real 8 [])
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
                                    [(Var 10 x)
                                    (Var 10 k)]
                                    [(=
                                        (Var 10 _lpython_return_variable)
                                        (RealBinOp
                                            (Var 10 x)
                                            Div
                                            (Cast
                                                (Var 10 k)
                                                IntegerToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            (Real 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 10 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :div_integer
                                (Function
                                    (SymbolTable
                                        7
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    7
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :k
                                                (Variable
                                                    7
                                                    k
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
                                            :x
                                                (Variable
                                                    7
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
                                    div_integer
                                    (FunctionType
                                        [(Integer 4 [])
                                        (Integer 4 [])]
                                        (Real 8 [])
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
                                    [(Var 7 x)
                                    (Var 7 k)]
                                    [(=
                                        (Var 7 _lpython_return_variable)
                                        (RealBinOp
                                            (Cast
                                                (Var 7 x)
                                                IntegerToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            Div
                                            (Cast
                                                (Var 7 k)
                                                IntegerToReal
                                                (Real 8 [])
                                                ()
                                            )
                                            (Real 8 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 7 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :div_string
                                (Function
                                    (SymbolTable
                                        13
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    13
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :k
                                                (Variable
                                                    13
                                                    k
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
                                            :x
                                                (Variable
                                                    13
                                                    x
                                                    []
                                                    In
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
                                    div_string
                                    (FunctionType
                                        [(Character 1 -2 () [])
                                        (Integer 4 [])]
                                        (Real 8 [])
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
                                    [(Var 13 x)
                                    (Var 13 k)]
                                    [(=
                                        (Var 13 _lpython_return_variable)
                                        (RealConstant
                                            0.000000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 13 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :empty_float
                                (Function
                                    (SymbolTable
                                        8
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    8
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    8
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (Real 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    empty_float
                                    (FunctionType
                                        [(Real 8 [])]
                                        (Real 8 [])
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
                                    [(Var 8 x)]
                                    [(=
                                        (Var 8 _lpython_return_variable)
                                        (RealConstant
                                            0.000000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 8 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :empty_integer
                                (Function
                                    (SymbolTable
                                        5
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    5
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
                                                    5
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
                                    empty_integer
                                    (FunctionType
                                        [(Integer 4 [])]
                                        (Integer 4 [])
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
                                    [(Var 5 x)]
                                    [(=
                                        (Var 5 _lpython_return_variable)
                                        (IntegerConstant 0 (Integer 4 []))
                                        ()
                                    )
                                    (Return)]
                                    (Var 5 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :empty_string
                                (Function
                                    (SymbolTable
                                        11
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    11
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
                                                    ()
                                                    ()
                                                    Default
                                                    (Character 1 -2 () [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    11
                                                    x
                                                    []
                                                    In
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
                                    empty_string
                                    (FunctionType
                                        [(Character 1 -2 () [])]
                                        (Character 1 -2 () [])
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
                                    [(Var 11 x)]
                                    [(=
                                        (Var 11 _lpython_return_variable)
                                        (StringConstant
                                            ""
                                            (Character 1 0 () [])
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 11 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :mean
                                (Function
                                    (SymbolTable
                                        14
                                        {
                                            :_lpython_return_variable
                                                (Variable
                                                    14
                                                    _lpython_return_variable
                                                    []
                                                    ReturnVar
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
                                                    14
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
                                            :k
                                                (Variable
                                                    14
                                                    k
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
                                            :res
                                                (Variable
                                                    14
                                                    res
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :x
                                                (Variable
                                                    14
                                                    x
                                                    []
                                                    In
                                                    ()
                                                    ()
                                                    Default
                                                    (List
                                                        (TypeParameter
                                                            T
                                                            []
                                                        )
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    mean
                                    (FunctionType
                                        [(List
                                            (TypeParameter
                                                T
                                                []
                                            )
                                        )]
                                        (Real 8 [])
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        false
                                        false
                                        [(TypeParameter
                                            T
                                            []
                                        )]
                                        [21 zero
                                        21 add
                                        21 div]
                                        false
                                    )
                                    [zero
                                    add
                                    div]
                                    [(Var 14 x)]
                                    [(=
                                        (Var 14 k)
                                        (ListLen
                                            (Var 14 x)
                                            (Integer 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (If
                                        (IntegerCompare
                                            (Var 14 k)
                                            Eq
                                            (IntegerConstant 0 (Integer 4 []))
                                            (Logical 4 [])
                                            ()
                                        )
                                        [(=
                                            (Var 14 _lpython_return_variable)
                                            (RealConstant
                                                0.000000
                                                (Real 8 [])
                                            )
                                            ()
                                        )
                                        (Return)]
                                        []
                                    )
                                    (=
                                        (Var 14 res)
                                        (FunctionCall
                                            21 zero
                                            ()
                                            [((ListItem
                                                (Var 14 x)
                                                (IntegerConstant 0 (Integer 4 []))
                                                (TypeParameter
                                                    T
                                                    []
                                                )
                                                ()
                                            ))]
                                            (TypeParameter
                                                T
                                                []
                                            )
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 14 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (Var 14 k)
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(=
                                            (Var 14 res)
                                            (FunctionCall
                                                21 add
                                                ()
                                                [((Var 14 res))
                                                ((ListItem
                                                    (Var 14 x)
                                                    (Var 14 i)
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
                                                    ()
                                                ))]
                                                (TypeParameter
                                                    T
                                                    []
                                                )
                                                ()
                                                ()
                                            )
                                            ()
                                        )]
                                    )
                                    (=
                                        (Var 14 _lpython_return_variable)
                                        (FunctionCall
                                            21 div
                                            ()
                                            [((Var 14 res))
                                            ((Var 14 k))]
                                            (Real 8 [])
                                            ()
                                            ()
                                        )
                                        ()
                                    )
                                    (Return)]
                                    (Var 14 _lpython_return_variable)
                                    Public
                                    false
                                    false
                                ),
                            :zero
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
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
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
                                                    (TypeParameter
                                                        T
                                                        []
                                                    )
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                )
                                        })
                                    zero
                                    (FunctionType
                                        [(TypeParameter
                                            T
                                            []
                                        )]
                                        (TypeParameter
                                            T
                                            []
                                        )
                                        Source
                                        Implementation
                                        ()
                                        false
                                        false
                                        false
                                        false
                                        false
                                        [(TypeParameter
                                            T
                                            []
                                        )]
                                        []
                                        true
                                    )
                                    []
                                    [(Var 2 x)]
                                    []
                                    (Var 2 _lpython_return_variable)
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
                        19
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    19
                                    _lpython_main_program
                                    21 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        19 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
