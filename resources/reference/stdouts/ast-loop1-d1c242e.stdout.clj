(Module
    [(ImportFrom
        lpython
        [(i32
        ())
        (i64
        ())]
        0
    )
    (FunctionDef
        test_factorial_1
        ([]
        [(x
        (Name
            i32
            Load
        )
        ())]
        []
        []
        []
        []
        [])
        [(If
            (Compare
                (Name
                    x
                    Load
                )
                Lt
                [(ConstantInt
                    0
                    ()
                )]
            )
            [(Return
                (ConstantInt
                    0
                    ()
                )
            )]
            []
        )
        (AnnAssign
            (Name
                result
                Store
            )
            (Name
                i32
                Load
            )
            ()
            1
        )
        (Assign
            [(Name
                result
                Store
            )]
            (ConstantInt
                1
                ()
            )
            ()
        )
        (While
            (Compare
                (Name
                    x
                    Load
                )
                Gt
                [(ConstantInt
                    0
                    ()
                )]
            )
            [(Assign
                [(Name
                    result
                    Store
                )]
                (BinOp
                    (Name
                        result
                        Load
                    )
                    Mult
                    (Name
                        x
                        Load
                    )
                )
                ()
            )
            (AugAssign
                (Name
                    x
                    Store
                )
                Sub
                (ConstantInt
                    1
                    ()
                )
            )]
            []
        )
        (Return
            (Name
                result
                Load
            )
        )]
        []
        (Name
            i32
            Load
        )
        ()
    )
    (FunctionDef
        test_factorial_2
        ([]
        [(x
        (Name
            i32
            Load
        )
        ())]
        []
        []
        []
        []
        [])
        [(AnnAssign
            (Name
                result
                Store
            )
            (Name
                i32
                Load
            )
            ()
            1
        )
        (Assign
            [(Name
                result
                Store
            )]
            (ConstantInt
                1
                ()
            )
            ()
        )
        (AnnAssign
            (Name
                i
                Store
            )
            (Name
                i32
                Load
            )
            ()
            1
        )
        (For
            (Name
                i
                Store
            )
            (Call
                (Name
                    range
                    Load
                )
                [(ConstantInt
                    1
                    ()
                )
                (BinOp
                    (Name
                        x
                        Load
                    )
                    Add
                    (ConstantInt
                        1
                        ()
                    )
                )]
                []
            )
            [(Assign
                [(Name
                    result
                    Store
                )]
                (BinOp
                    (Name
                        result
                        Load
                    )
                    Mult
                    (Name
                        i
                        Load
                    )
                )
                ()
            )]
            []
            ()
        )
        (Return
            (Name
                result
                Load
            )
        )]
        []
        (Name
            i32
            Load
        )
        ()
    )
    (FunctionDef
        test_factorial_3
        ([]
        [(x
        (Name
            i32
            Load
        )
        ())]
        []
        []
        []
        []
        [])
        [(AnnAssign
            (Name
                result
                Store
            )
            (Name
                i64
                Load
            )
            ()
            1
        )
        (Assign
            [(Name
                result
                Store
            )]
            (Call
                (Name
                    i64
                    Load
                )
                [(ConstantInt
                    0
                    ()
                )]
                []
            )
            ()
        )
        (If
            (Compare
                (Name
                    x
                    Load
                )
                Lt
                [(ConstantInt
                    0
                    ()
                )]
            )
            [(Return
                (Name
                    result
                    Load
                )
            )]
            []
        )
        (Assign
            [(Name
                result
                Store
            )]
            (Call
                (Name
                    i64
                    Load
                )
                [(ConstantInt
                    1
                    ()
                )]
                []
            )
            ()
        )
        (While
            (Compare
                (Name
                    x
                    Load
                )
                Gt
                [(ConstantInt
                    0
                    ()
                )]
            )
            [(Assign
                [(Name
                    result
                    Store
                )]
                (BinOp
                    (Name
                        result
                        Load
                    )
                    Mult
                    (Call
                        (Name
                            i64
                            Load
                        )
                        [(Name
                            x
                            Load
                        )]
                        []
                    )
                )
                ()
            )
            (AugAssign
                (Name
                    x
                    Store
                )
                Sub
                (ConstantInt
                    1
                    ()
                )
            )]
            []
        )
        (Return
            (Name
                result
                Load
            )
        )]
        []
        (Name
            i64
            Load
        )
        ()
    )
    (FunctionDef
        main0
        ([]
        []
        []
        []
        []
        []
        [])
        [(AnnAssign
            (Name
                i
                Store
            )
            (Name
                i32
                Load
            )
            ()
            1
        )
        (Assign
            [(Name
                i
                Store
            )]
            (Call
                (Name
                    test_factorial_1
                    Load
                )
                [(ConstantInt
                    4
                    ()
                )]
                []
            )
            ()
        )
        (Assign
            [(Name
                i
                Store
            )]
            (Call
                (Name
                    test_factorial_2
                    Load
                )
                [(ConstantInt
                    4
                    ()
                )]
                []
            )
            ()
        )
        (AnnAssign
            (Name
                j
                Store
            )
            (Name
                i64
                Load
            )
            ()
            1
        )
        (Assign
            [(Name
                j
                Store
            )]
            (Call
                (Name
                    test_factorial_3
                    Load
                )
                [(ConstantInt
                    5
                    ()
                )]
                []
            )
            ()
        )]
        []
        ()
        ()
    )
    (Expr
        (Call
            (Name
                main0
                Load
            )
            []
            []
        )
    )]
    []
)
