(Module
    [(ImportFrom
        lpython
        [(i32
        ())
        (f32
        ())]
        0
    )
    (FunctionDef
        test_binop
        ([]
        []
        []
        []
        []
        []
        [])
        [(AnnAssign
            (Name
                x
                Store
            )
            (Name
                i32
                Load
            )
            ()
            1
        )
        (AnnAssign
            (Name
                x2
                Store
            )
            (Name
                f32
                Load
            )
            ()
            1
        )
        (Assign
            [(Name
                x
                Store
            )]
            (BinOp
                (ConstantInt
                    2
                    ()
                )
                Pow
                (ConstantInt
                    3
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                x2
                Store
            )]
            (Call
                (Name
                    f32
                    Load
                )
                [(BinOp
                    (ConstantFloat
                        2.000000
                        ()
                    )
                    Pow
                    (ConstantFloat
                        3.500000
                        ()
                    )
                )]
                []
            )
            ()
        )
        (Assign
            [(Name
                x
                Store
            )]
            (BinOp
                (ConstantInt
                    54
                    ()
                )
                Sub
                (ConstantInt
                    100
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                x2
                Store
            )]
            (Call
                (Name
                    f32
                    Load
                )
                [(BinOp
                    (BinOp
                        (ConstantFloat
                            3.454000
                            ()
                        )
                        Sub
                        (ConstantFloat
                            765.430000
                            ()
                        )
                    )
                    Add
                    (ConstantFloat
                        534.600000
                        ()
                    )
                )]
                []
            )
            ()
        )
        (Assign
            [(Name
                x2
                Store
            )]
            (Call
                (Name
                    f32
                    Load
                )
                [(BinOp
                    (ConstantFloat
                        5346.565000
                        ()
                    )
                    Mult
                    (ConstantFloat
                        3.450000
                        ()
                    )
                )]
                []
            )
            ()
        )
        (Assign
            [(Name
                x2
                Store
            )]
            (Call
                (Name
                    f32
                    Load
                )
                [(BinOp
                    (ConstantFloat
                        5346.565000
                        ()
                    )
                    Pow
                    (ConstantFloat
                        3.450000
                        ()
                    )
                )]
                []
            )
            ()
        )
        (Assign
            [(Name
                x
                Store
            )]
            (BinOp
                (ConstantBool
                    true
                    ()
                )
                Add
                (ConstantBool
                    true
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                x
                Store
            )]
            (BinOp
                (ConstantBool
                    true
                    ()
                )
                Sub
                (ConstantBool
                    false
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                x
                Store
            )]
            (BinOp
                (ConstantBool
                    true
                    ()
                )
                Mult
                (ConstantBool
                    false
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                x
                Store
            )]
            (BinOp
                (ConstantBool
                    true
                    ()
                )
                Pow
                (ConstantBool
                    false
                    ()
                )
            )
            ()
        )
        (AnnAssign
            (Name
                b1
                Store
            )
            (Name
                bool
                Load
            )
            ()
            1
        )
        (AnnAssign
            (Name
                b2
                Store
            )
            (Name
                bool
                Load
            )
            ()
            1
        )
        (Assign
            [(Name
                b1
                Store
            )]
            (ConstantBool
                true
                ()
            )
            ()
        )
        (Assign
            [(Name
                b2
                Store
            )]
            (ConstantBool
                false
                ()
            )
            ()
        )
        (Assign
            [(Name
                x
                Store
            )]
            (Call
                (Name
                    i32
                    Load
                )
                [(BinOp
                    (Name
                        b1
                        Load
                    )
                    FloorDiv
                    (Name
                        b1
                        Load
                    )
                )]
                []
            )
            ()
        )
        (Assign
            [(Name
                x
                Store
            )]
            (BinOp
                (Name
                    b1
                    Load
                )
                Pow
                (Name
                    b2
                    Load
                )
            )
            ()
        )]
        []
        ()
        ()
    )]
    []
)