(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        9
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        8
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
                                    [test_str_int_float
                                    str_conv_for_variables
                                    test_str_slice_step
                                    test_issue_883]
                                    []
                                    [(SubroutineCall
                                        9 test_str_int_float
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        9 str_conv_for_variables
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        9 test_str_slice_step
                                        ()
                                        []
                                        ()
                                    )
                                    (SubroutineCall
                                        9 test_issue_883
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :str_conv_for_variables
                                (Function
                                    (SymbolTable
                                        3
                                        {
                                            :bool_t
                                                (Variable
                                                    3
                                                    bool_t
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
                                                ),
                                            :str_t
                                                (Variable
                                                    3
                                                    str_t
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
                                                ),
                                            :xx
                                                (Variable
                                                    3
                                                    xx
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
                                            :yy
                                                (Variable
                                                    3
                                                    yy
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
                                                )
                                        })
                                    str_conv_for_variables
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
                                        (Var 3 x)
                                        (IntegerConstant 123 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringConstant
                                                "123"
                                                (Character 1 3 () [])
                                            )
                                            Eq
                                            (Cast
                                                (Var 3 x)
                                                IntegerToCharacter
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 x)
                                        (IntegerConstant 12345 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringConstant
                                                "12345"
                                                (Character 1 5 () [])
                                            )
                                            Eq
                                            (Cast
                                                (Var 3 x)
                                                IntegerToCharacter
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 x)
                                        (IntegerUnaryMinus
                                            (IntegerConstant 12 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant -12 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringConstant
                                                "-12"
                                                (Character 1 3 () [])
                                            )
                                            Eq
                                            (Cast
                                                (Var 3 x)
                                                IntegerToCharacter
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 x)
                                        (IntegerUnaryMinus
                                            (IntegerConstant 121212 (Integer 4 []))
                                            (Integer 4 [])
                                            (IntegerConstant -121212 (Integer 4 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringConstant
                                                "-121212"
                                                (Character 1 7 () [])
                                            )
                                            Eq
                                            (Cast
                                                (Var 3 x)
                                                IntegerToCharacter
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 xx)
                                        (Cast
                                            (RealConstant
                                                12.322234
                                                (Real 8 [])
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                12.322234
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Cast
                                                (Var 3 xx)
                                                RealToCharacter
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "12.322234"
                                                (Character 1 9 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 yy)
                                        (RealConstant
                                            12.322234
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Cast
                                                (Var 3 yy)
                                                RealToCharacter
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "12.322234"
                                                (Character 1 9 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 3 bool_t)
                                        (LogicalConstant
                                            true
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Cast
                                                (Var 3 bool_t)
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
                                        (Var 3 bool_t)
                                        (LogicalConstant
                                            false
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Cast
                                                (Var 3 bool_t)
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
                                    (=
                                        (Var 3 str_t)
                                        (StringConstant
                                            "just a str"
                                            (Character 1 10 () [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 3 str_t)
                                            Eq
                                            (Var 3 str_t)
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
                            :test_issue_883
                                (Function
                                    (SymbolTable
                                        5
                                        {
                                            :__explicit_iterator
                                                (Variable
                                                    5
                                                    __explicit_iterator
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
                                            :__tmp_assign_for_loop
                                                (Variable
                                                    5
                                                    __tmp_assign_for_loop
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
                                            :c
                                                (Variable
                                                    5
                                                    c
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
                                            :d
                                                (Variable
                                                    5
                                                    d
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
                                            :i
                                                (Variable
                                                    5
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
                                            :s
                                                (Variable
                                                    5
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
                                                )
                                        })
                                    test_issue_883
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
                                        (Var 5 s)
                                        (StringConstant
                                            "abcde"
                                            (Character 1 5 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 5 d)
                                        (StringConstant
                                            "edcba"
                                            (Character 1 5 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 5 i)
                                        (IntegerConstant 0 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 5 __tmp_assign_for_loop)
                                        (StringSection
                                            (Var 5 s)
                                            ()
                                            ()
                                            (IntegerUnaryMinus
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -1 (Integer 4 []))
                                            )
                                            (Character 1 -2 () [])
                                            ()
                                        )
                                        ()
                                    )
                                    (DoLoop
                                        ()
                                        ((Var 5 __explicit_iterator)
                                        (IntegerConstant 0 (Integer 4 []))
                                        (IntegerBinOp
                                            (StringLen
                                                (Var 5 __tmp_assign_for_loop)
                                                (Integer 4 [])
                                                ()
                                            )
                                            Sub
                                            (IntegerConstant 1 (Integer 4 []))
                                            (Integer 4 [])
                                            ()
                                        )
                                        (IntegerConstant 1 (Integer 4 [])))
                                        [(=
                                            (Var 5 c)
                                            (StringItem
                                                (Var 5 __tmp_assign_for_loop)
                                                (IntegerBinOp
                                                    (Var 5 __explicit_iterator)
                                                    Add
                                                    (IntegerConstant 1 (Integer 4 []))
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
                                            [(Var 5 c)]
                                            ()
                                            ()
                                        )
                                        (Assert
                                            (StringCompare
                                                (Var 5 c)
                                                Eq
                                                (StringItem
                                                    (Var 5 d)
                                                    (IntegerBinOp
                                                        (Var 5 i)
                                                        Add
                                                        (IntegerConstant 1 (Integer 4 []))
                                                        (Integer 4 [])
                                                        ()
                                                    )
                                                    (Character 1 -2 () [])
                                                    ()
                                                )
                                                (Logical 4 [])
                                                ()
                                            )
                                            ()
                                        )
                                        (=
                                            (Var 5 i)
                                            (IntegerBinOp
                                                (Var 5 i)
                                                Add
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                ()
                                            )
                                            ()
                                        )]
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_str_int_float
                                (Function
                                    (SymbolTable
                                        2
                                        {
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
                                                )
                                        })
                                    test_str_int_float
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
                                        (Cast
                                            (IntegerConstant 356 (Integer 4 []))
                                            IntegerToCharacter
                                            (Character 1 -2 () [])
                                            (StringConstant
                                                "356"
                                                (Character 1 3 () [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 2 s)
                                            Eq
                                            (StringConstant
                                                "356"
                                                (Character 1 3 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (Cast
                                            (IntegerUnaryMinus
                                                (IntegerConstant 567 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -567 (Integer 4 []))
                                            )
                                            IntegerToCharacter
                                            (Character 1 -2 () [])
                                            (StringConstant
                                                "-567"
                                                (Character 1 4 () [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Var 2 s)
                                            Eq
                                            (StringConstant
                                                "-567"
                                                (Character 1 4 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (Cast
                                                (IntegerConstant 4 (Integer 4 []))
                                                IntegerToCharacter
                                                (Character 1 -2 () [])
                                                (StringConstant
                                                    "4"
                                                    (Character 1 1 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "4"
                                                (Character 1 1 () [])
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
                                        (StringCompare
                                            (Cast
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 5 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -5 (Integer 4 []))
                                                )
                                                IntegerToCharacter
                                                (Character 1 -2 () [])
                                                (StringConstant
                                                    "-5"
                                                    (Character 1 2 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "-5"
                                                (Character 1 2 () [])
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
                                        (StringCompare
                                            (StringConstant
                                                ""
                                                (Character 1 0 () [])
                                            )
                                            Eq
                                            (StringConstant
                                                ""
                                                (Character 1 0 () [])
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
                                        (StringCompare
                                            (StringConstant
                                                "1234"
                                                (Character 1 4 () [])
                                            )
                                            Eq
                                            (StringConstant
                                                "1234"
                                                (Character 1 4 () [])
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
                                        (StringCompare
                                            (Cast
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                                LogicalToCharacter
                                                (Character 1 -2 () [])
                                                (StringConstant
                                                    "False"
                                                    (Character 1 5 () [])
                                                )
                                            )
                                            Eq
                                            (StringConstant
                                                "False"
                                                (Character 1 5 () [])
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
                                        (StringCompare
                                            (Cast
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
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringConstant
                                                "just a str"
                                                (Character 1 10 () [])
                                            )
                                            Eq
                                            (StringConstant
                                                "just a str"
                                                (Character 1 10 () [])
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
                                        (StringCompare
                                            (StringSection
                                                (Cast
                                                    (RealConstant
                                                        12.123400
                                                        (Real 8 [])
                                                    )
                                                    RealToCharacter
                                                    (Character 1 -2 () [])
                                                    (StringConstant
                                                        "12.1234"
                                                        (Character 1 7 () [])
                                                    )
                                                )
                                                ()
                                                (IntegerConstant 7 (Integer 4 []))
                                                ()
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "12.1234"
                                                (Character 1 7 () [])
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
                            :test_str_slice_step
                                (Function
                                    (SymbolTable
                                        4
                                        {
                                            :end
                                                (Variable
                                                    4
                                                    end
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
                                                    4
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
                                            :start
                                                (Variable
                                                    4
                                                    start
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
                                            :step
                                                (Variable
                                                    4
                                                    step
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
                                    test_str_slice_step
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
                                        (Var 4 s)
                                        (StringConstant
                                            "abcdefghijk"
                                            (Character 1 11 () [])
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 4 start)
                                        (IntegerConstant 1 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 4 end)
                                        (IntegerConstant 4 (Integer 4 []))
                                        ()
                                    )
                                    (=
                                        (Var 4 step)
                                        (IntegerConstant 1 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                ()
                                                ()
                                                ()
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "abcdefghijk"
                                                (Character 1 11 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (IntegerConstant 1 (Integer 4 []))
                                                (IntegerConstant 4 (Integer 4 []))
                                                ()
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "bcd"
                                                (Character 1 3 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                ()
                                                (IntegerConstant 4 (Integer 4 []))
                                                (IntegerConstant 5 (Integer 4 []))
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "a"
                                                (Character 1 1 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                ()
                                                ()
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 1 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -1 (Integer 4 []))
                                                )
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "kjihgfedcba"
                                                (Character 1 11 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (IntegerConstant 3 (Integer 4 []))
                                                (IntegerConstant 12 (Integer 4 []))
                                                (IntegerConstant 3 (Integer 4 []))
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "dgj"
                                                (Character 1 3 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (IntegerConstant 1 (Integer 4 []))
                                                ()
                                                (IntegerConstant 3 (Integer 4 []))
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "behk"
                                                (Character 1 4 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (IntegerConstant 4 (Integer 4 []))
                                                ()
                                                ()
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "efghijk"
                                                (Character 1 7 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                ()
                                                (IntegerConstant 5 (Integer 4 []))
                                                ()
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "abcde"
                                                (Character 1 5 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (IntegerConstant 3 (Integer 4 []))
                                                (IntegerConstant 9 (Integer 4 []))
                                                (IntegerConstant 3 (Integer 4 []))
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "dg"
                                                (Character 1 2 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (IntegerConstant 10 (Integer 4 []))
                                                (IntegerConstant 3 (Integer 4 []))
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 2 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -2 (Integer 4 []))
                                                )
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "kige"
                                                (Character 1 4 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 2 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -2 (Integer 4 []))
                                                )
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 10 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -10 (Integer 4 []))
                                                )
                                                ()
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                ""
                                                (Character 1 0 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 3 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -3 (Integer 4 []))
                                                )
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 9 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -9 (Integer 4 []))
                                                )
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 3 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -3 (Integer 4 []))
                                                )
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "if"
                                                (Character 1 2 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 3 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -3 (Integer 4 []))
                                                )
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 10 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -10 (Integer 4 []))
                                                )
                                                (IntegerUnaryMinus
                                                    (IntegerConstant 3 (Integer 4 []))
                                                    (Integer 4 [])
                                                    (IntegerConstant -3 (Integer 4 []))
                                                )
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "ifc"
                                                (Character 1 3 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (Var 4 start)
                                                (Var 4 end)
                                                (Var 4 step)
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "bcd"
                                                (Character 1 3 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (Var 4 start)
                                                (IntegerBinOp
                                                    (IntegerBinOp
                                                        (IntegerConstant 2 (Integer 4 []))
                                                        Mul
                                                        (Var 4 end)
                                                        (Integer 4 [])
                                                        ()
                                                    )
                                                    Sub
                                                    (IntegerConstant 3 (Integer 4 []))
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                (Var 4 step)
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                "bcde"
                                                (Character 1 4 () [])
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (StringCompare
                                            (StringSection
                                                (Var 4 s)
                                                (Var 4 start)
                                                (IntegerBinOp
                                                    (IntegerBinOp
                                                        (IntegerConstant 2 (Integer 4 []))
                                                        Mul
                                                        (Var 4 end)
                                                        (Integer 4 [])
                                                        ()
                                                    )
                                                    Sub
                                                    (IntegerConstant 3 (Integer 4 []))
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                (IntegerUnaryMinus
                                                    (Var 4 step)
                                                    (Integer 4 [])
                                                    ()
                                                )
                                                (Character 1 -2 () [])
                                                ()
                                            )
                                            Eq
                                            (StringConstant
                                                ""
                                                (Character 1 0 () [])
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
                        7
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    7
                                    _lpython_main_program
                                    9 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        7 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
