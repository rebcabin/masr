(Module
    [(ImportFrom
        lpython
        [(i32
        ())
        (f32
        ())
        (c32
        ())]
        0
    )
    (FunctionDef
        test_UnaryOp
        ([]
        []
        []
        []
        []
        []
        [])
        [(AnnAssign
            (Name
                a
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
                a
                Store
            )]
            (UnaryOp
                UAdd
                (ConstantInt
                    4
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                a
                Store
            )]
            (UnaryOp
                USub
                (ConstantInt
                    500
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                a
                Store
            )]
            (UnaryOp
                Invert
                (ConstantInt
                    5
                    ()
                )
            )
            ()
        )
        (AnnAssign
            (Name
                b
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
                b
                Store
            )]
            (UnaryOp
                Not
                (ConstantInt
                    5
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                b
                Store
            )]
            (UnaryOp
                Not
                (UnaryOp
                    USub
                    (ConstantInt
                        1
                        ()
                    )
                )
            )
            ()
        )
        (Assign
            [(Name
                b
                Store
            )]
            (UnaryOp
                Not
                (ConstantInt
                    0
                    ()
                )
            )
            ()
        )
        (AnnAssign
            (Name
                f
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
                f
                Store
            )]
            (UnaryOp
                UAdd
                (Call
                    (Name
                        f32
                        Load
                    )
                    [(ConstantFloat
                        1.000000
                        ()
                    )]
                    []
                )
            )
            ()
        )
        (Assign
            [(Name
                f
                Store
            )]
            (UnaryOp
                USub
                (Call
                    (Name
                        f32
                        Load
                    )
                    [(ConstantFloat
                        183745.534000
                        ()
                    )]
                    []
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
        (AnnAssign
            (Name
                b3
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
            (UnaryOp
                Not
                (ConstantBool
                    false
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                b3
                Store
            )]
            (UnaryOp
                Not
                (Name
                    b2
                    Load
                )
            )
            ()
        )
        (Assign
            [(Name
                a
                Store
            )]
            (UnaryOp
                UAdd
                (ConstantBool
                    true
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                a
                Store
            )]
            (UnaryOp
                USub
                (ConstantBool
                    false
                    ()
                )
            )
            ()
        )
        (Assign
            [(Name
                a
                Store
            )]
            (UnaryOp
                Invert
                (ConstantBool
                    true
                    ()
                )
            )
            ()
        )
        (AnnAssign
            (Name
                c
                Store
            )
            (Name
                c32
                Load
            )
            ()
            1
        )
        (Assign
            [(Name
                c
                Store
            )]
            (UnaryOp
                UAdd
                (Call
                    (Name
                        c32
                        Load
                    )
                    [(Call
                        (Name
                            complex
                            Load
                        )
                        [(ConstantInt
                            1
                            ()
                        )
                        (ConstantInt
                            2
                            ()
                        )]
                        []
                    )]
                    []
                )
            )
            ()
        )
        (Assign
            [(Name
                c
                Store
            )]
            (UnaryOp
                USub
                (Call
                    (Name
                        c32
                        Load
                    )
                    [(Call
                        (Name
                            complex
                            Load
                        )
                        [(ConstantInt
                            3
                            ()
                        )
                        (ConstantFloat
                            65.000000
                            ()
                        )]
                        []
                    )]
                    []
                )
            )
            ()
        )
        (Assign
            [(Name
                b1
                Store
            )]
            (UnaryOp
                Not
                (Call
                    (Name
                        complex
                        Load
                    )
                    [(ConstantInt
                        3
                        ()
                    )
                    (ConstantInt
                        4
                        ()
                    )]
                    []
                )
            )
            ()
        )
        (Assign
            [(Name
                b2
                Store
            )]
            (UnaryOp
                Not
                (Call
                    (Name
                        complex
                        Load
                    )
                    [(ConstantInt
                        0
                        ()
                    )
                    (ConstantInt
                        0
                        ()
                    )]
                    []
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
