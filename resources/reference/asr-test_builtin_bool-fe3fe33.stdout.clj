(TranslationUnit
    (SymbolTable
        1
        {
            :_global_symbols
                (Module
                    (SymbolTable
                        112
                        {
                            :_lpython_main_program
                                (Function
                                    (SymbolTable
                                        111
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
                                    [test_bool]
                                    []
                                    [(SubroutineCall
                                        112 test_bool
                                        ()
                                        []
                                        ()
                                    )]
                                    ()
                                    Public
                                    false
                                    false
                                ),
                            :test_bool
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
                                            :a2
                                                (Variable
                                                    2
                                                    a2
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
                                                    (Integer 1 [])
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
                                                    (Integer 2 [])
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
                                                    (Logical 4 [])
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
                                                    (Complex 4 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :c1
                                                (Variable
                                                    2
                                                    c1
                                                    []
                                                    Local
                                                    ()
                                                    ()
                                                    Default
                                                    (Complex 8 [])
                                                    Source
                                                    Public
                                                    Required
                                                    false
                                                ),
                                            :complex
                                                (ExternalSymbol
                                                    2
                                                    complex
                                                    4 complex
                                                    lpython_builtin
                                                    []
                                                    complex
                                                    Private
                                                ),
                                            complex@:__lpython_overloaded_13__complex
                                                (ExternalSymbol
                                                    2
                                                    complex@__lpython_overloaded_13__complex
                                                    4 __lpython_overloaded_13__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_13__complex
                                                    Public
                                                ),
                                            complex@:__lpython_overloaded_9__complex
                                                (ExternalSymbol
                                                    2
                                                    complex@__lpython_overloaded_9__complex
                                                    4 __lpython_overloaded_9__complex
                                                    lpython_builtin
                                                    []
                                                    __lpython_overloaded_9__complex
                                                    Public
                                                ),
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
                                            :f2
                                                (Variable
                                                    2
                                                    f2
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
                                    test_bool
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
                                    [complex@__lpython_overloaded_9__complex
                                    complex@__lpython_overloaded_13__complex]
                                    []
                                    [(=
                                        (Var 2 a)
                                        (IntegerConstant 34 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (Var 2 a)
                                            IntegerToLogical
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a)
                                        (IntegerConstant 0 (Integer 4 []))
                                        ()
                                    )
                                    (Assert
                                        (LogicalNot
                                            (Cast
                                                (Var 2 a)
                                                IntegerToLogical
                                                (Logical 4 [])
                                                ()
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (IntegerUnaryMinus
                                                (IntegerConstant 1 (Integer 4 []))
                                                (Integer 4 [])
                                                (IntegerConstant -1 (Integer 4 []))
                                            )
                                            IntegerToLogical
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalNot
                                            (Cast
                                                (IntegerConstant 0 (Integer 4 []))
                                                IntegerToLogical
                                                (Logical 4 [])
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a2)
                                        (Cast
                                            (IntegerConstant 34 (Integer 4 []))
                                            IntegerToInteger
                                            (Integer 8 [])
                                            (IntegerConstant 34 (Integer 8 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (Var 2 a2)
                                            IntegerToLogical
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a3)
                                        (Cast
                                            (IntegerConstant 34 (Integer 4 []))
                                            IntegerToInteger
                                            (Integer 1 [])
                                            (IntegerConstant 34 (Integer 1 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (Var 2 a3)
                                            IntegerToLogical
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 a4)
                                        (IntegerUnaryMinus
                                            (Cast
                                                (IntegerConstant 1 (Integer 4 []))
                                                IntegerToInteger
                                                (Integer 2 [])
                                                (IntegerConstant 1 (Integer 2 []))
                                            )
                                            (Integer 2 [])
                                            (IntegerConstant -1 (Integer 2 []))
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (Var 2 a4)
                                            IntegerToLogical
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 f)
                                        (RealConstant
                                            0.000000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalNot
                                            (Cast
                                                (Var 2 f)
                                                RealToLogical
                                                (Logical 4 [])
                                                ()
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 f)
                                        (RealConstant
                                            1.000000
                                            (Real 8 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (Var 2 f)
                                            RealToLogical
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (RealConstant
                                                56.786866
                                                (Real 8 [])
                                            )
                                            RealToLogical
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalNot
                                            (Cast
                                                (RealConstant
                                                    0.000000
                                                    (Real 8 [])
                                                )
                                                RealToLogical
                                                (Logical 4 [])
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 f2)
                                        (RealUnaryMinus
                                            (Cast
                                                (RealConstant
                                                    235.600000
                                                    (Real 8 [])
                                                )
                                                RealToReal
                                                (Real 4 [])
                                                (RealConstant
                                                    235.600000
                                                    (Real 4 [])
                                                )
                                            )
                                            (Real 4 [])
                                            (RealConstant
                                                -235.600000
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (Var 2 f2)
                                            RealToLogical
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 f2)
                                        (Cast
                                            (RealConstant
                                                0.000053
                                                (Real 8 [])
                                            )
                                            RealToReal
                                            (Real 4 [])
                                            (RealConstant
                                                0.000053
                                                (Real 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (Var 2 f2)
                                            RealToLogical
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
                                        (LogicalNot
                                            (Cast
                                                (Var 2 s)
                                                CharacterToLogical
                                                (Logical 4 [])
                                                ()
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 s)
                                        (StringConstant
                                            "str"
                                            (Character 1 3 () [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (Var 2 s)
                                            CharacterToLogical
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalNot
                                            (Cast
                                                (StringConstant
                                                    ""
                                                    (Character 1 0 () [])
                                                )
                                                CharacterToLogical
                                                (Logical 4 [])
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
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
                                        (Cast
                                            (StringConstant
                                                "str"
                                                (Character 1 3 () [])
                                            )
                                            CharacterToLogical
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 b)
                                        (LogicalConstant
                                            true
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Var 2 b)
                                        ()
                                    )
                                    (=
                                        (Var 2 b)
                                        (LogicalConstant
                                            false
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalNot
                                            (Var 2 b)
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalConstant
                                            true
                                            (Logical 4 [])
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalNot
                                            (LogicalConstant
                                                false
                                                (Logical 4 [])
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (Cast
                                            (FunctionCall
                                                2 complex@__lpython_overloaded_9__complex
                                                2 complex
                                                [((IntegerConstant 2 (Integer 4 [])))
                                                ((IntegerConstant 3 (Integer 4 [])))]
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    2.000000
                                                    3.000000
                                                    (Complex 8 [])
                                                )
                                                ()
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                2.000000
                                                3.000000
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (Var 2 c)
                                            ComplexToLogical
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c)
                                        (Cast
                                            (FunctionCall
                                                2 complex@__lpython_overloaded_9__complex
                                                2 complex
                                                [((IntegerConstant 0 (Integer 4 [])))
                                                ((IntegerConstant 0 (Integer 4 [])))]
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    0.000000
                                                    0.000000
                                                    (Complex 8 [])
                                                )
                                                ()
                                            )
                                            ComplexToComplex
                                            (Complex 4 [])
                                            (ComplexConstant
                                                0.000000
                                                0.000000
                                                (Complex 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalNot
                                            (Cast
                                                (Var 2 c)
                                                ComplexToLogical
                                                (Logical 4 [])
                                                ()
                                            )
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalNot
                                            (Cast
                                                (ComplexBinOp
                                                    (Cast
                                                        (IntegerConstant 0 (Integer 4 []))
                                                        IntegerToComplex
                                                        (Complex 8 [])
                                                        (ComplexConstant
                                                            0.000000
                                                            0.000000
                                                            (Complex 8 [])
                                                        )
                                                    )
                                                    Add
                                                    (ComplexConstant
                                                        0.000000
                                                        0.000000
                                                        (Complex 8 [])
                                                    )
                                                    (Complex 8 [])
                                                    (ComplexConstant
                                                        0.000000
                                                        0.000000
                                                        (Complex 8 [])
                                                    )
                                                )
                                                ComplexToLogical
                                                (Logical 4 [])
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
                                            )
                                            (Logical 4 [])
                                            (LogicalConstant
                                                true
                                                (Logical 4 [])
                                            )
                                        )
                                        ()
                                    )
                                    (=
                                        (Var 2 c1)
                                        (FunctionCall
                                            2 complex@__lpython_overloaded_13__complex
                                            2 complex
                                            [((IntegerConstant 0 (Integer 4 [])))
                                            ((RealConstant
                                                0.100202
                                                (Real 8 [])
                                            ))]
                                            (Complex 8 [])
                                            (ComplexConstant
                                                0.000000
                                                0.100202
                                                (Complex 8 [])
                                            )
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (Cast
                                            (Var 2 c1)
                                            ComplexToLogical
                                            (Logical 4 [])
                                            ()
                                        )
                                        ()
                                    )
                                    (Assert
                                        (LogicalNot
                                            (Cast
                                                (FunctionCall
                                                    2 complex@__lpython_overloaded_9__complex
                                                    2 complex
                                                    [((IntegerConstant 0 (Integer 4 [])))
                                                    ((IntegerConstant 0 (Integer 4 [])))]
                                                    (Complex 8 [])
                                                    (ComplexConstant
                                                        0.000000
                                                        0.000000
                                                        (Complex 8 [])
                                                    )
                                                    ()
                                                )
                                                ComplexToLogical
                                                (Logical 4 [])
                                                (LogicalConstant
                                                    false
                                                    (Logical 4 [])
                                                )
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
                                        (Cast
                                            (ComplexBinOp
                                                (Cast
                                                    (IntegerConstant 3 (Integer 4 []))
                                                    IntegerToComplex
                                                    (Complex 8 [])
                                                    (ComplexConstant
                                                        3.000000
                                                        0.000000
                                                        (Complex 8 [])
                                                    )
                                                )
                                                Add
                                                (ComplexConstant
                                                    0.000000
                                                    5.000000
                                                    (Complex 8 [])
                                                )
                                                (Complex 8 [])
                                                (ComplexConstant
                                                    3.000000
                                                    5.000000
                                                    (Complex 8 [])
                                                )
                                            )
                                            ComplexToLogical
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
                        110
                        {
                            :_lpython_main_program
                                (ExternalSymbol
                                    110
                                    _lpython_main_program
                                    112 _lpython_main_program
                                    _global_symbols
                                    []
                                    _lpython_main_program
                                    Public
                                )
                        })
                    main_program
                    [_global_symbols]
                    [(SubroutineCall
                        110 _lpython_main_program
                        ()
                        []
                        ()
                    )]
                )
        })
    []
)
