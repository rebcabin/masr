(Module
    [(Assign
        [(Name
            x
            Store
        )]
        (ConstantInt
            10
            ()
        )
        ()
    )
    (Assign
        [(Name
            y
            Store
        )]
        (ConstantInt
            14
            ()
        )
        ()
    )
    (Assign
        [(Name
            z
            Store
        )]
        (ConstantInt
            12
            ()
        )
        ()
    )
    (If
        (BoolOp
            And
            [(Compare
                (Name
                    x
                    Load
                )
                GtE
                [(Name
                    y
                    Load
                )]
            )
            (Compare
                (Name
                    x
                    Load
                )
                GtE
                [(Name
                    z
                    Load
                )]
            )]
        )
        [(Assign
            [(Name
                largest
                Store
            )]
            (Name
                x
                Load
            )
            ()
        )
        (Expr
            (Call
                (Name
                    print
                    Load
                )
                [(ConstantStr
                    ":Largest "
                    ()
                )
                (Name
                    x
                    Load
                )]
                []
            )
        )]
        [(If
            (BoolOp
                And
                [(Compare
                    (Name
                        y
                        Load
                    )
                    GtE
                    [(Name
                        x
                        Load
                    )]
                )
                (Compare
                    (Name
                        y
                        Load
                    )
                    GtE
                    [(Name
                        z
                        Load
                    )]
                )]
            )
            [(Assign
                [(Name
                    largest
                    Store
                )]
                (Name
                    y
                    Load
                )
                ()
            )
            (Expr
                (Call
                    (Name
                        print
                        Load
                    )
                    [(ConstantStr
                        ":Largest "
                        ()
                    )
                    (Name
                        y
                        Load
                    )]
                    []
                )
            )]
            [(Assign
                [(Name
                    largest
                    Store
                )]
                (Name
                    z
                    Load
                )
                ()
            )
            (Expr
                (Call
                    (Name
                        print
                        Load
                    )
                    [(ConstantStr
                        ":Largest "
                        ()
                    )
                    (Name
                        z
                        Load
                    )]
                    []
                )
            )]
        )]
    )
    (If
        (Compare
            (Name
                y
                Load
            )
            Gt
            [(ConstantInt
                10
                ()
            )]
        )
        [(Expr
            (Call
                (Name
                    print
                    Load
                )
                [(ConstantStr
                    "Above ten,"
                    ()
                )]
                []
            )
        )
        (If
            (Compare
                (Name
                    y
                    Load
                )
                Gt
                [(ConstantInt
                    20
                    ()
                )]
            )
            [(Expr
                (Call
                    (Name
                        print
                        Load
                    )
                    [(ConstantStr
                        "and also above 20!"
                        ()
                    )]
                    []
                )
            )]
            [(Expr
                (Call
                    (Name
                        print
                        Load
                    )
                    [(ConstantStr
                        "but not above 20."
                        ()
                    )]
                    []
                )
            )]
        )]
        []
    )
    (If
        (Compare
            (Name
                a
                Load
            )
            Eq
            [(Name
                b
                Load
            )]
        )
        [(Expr
            (ConstantEllipsis
                ()
            )
        )
        (AugAssign
            (Name
                a
                Store
            )
            Add
            (Name
                b
                Load
            )
        )]
        []
    )
    (If
        (Compare
            (Name
                a
                Load
            )
            Eq
            [(Name
                b
                Load
            )]
        )
        [(Expr
            (ConstantEllipsis
                ()
            )
        )
        (AugAssign
            (Name
                a
                Store
            )
            Add
            (Name
                b
                Load
            )
        )]
        []
    )
    (If
        (Name
            b
            Load
        )
        [(Expr
            (ConstantEllipsis
                ()
            )
        )]
        [(Assign
            [(Name
                a
                Store
            )]
            (Name
                b
                Load
            )
            ()
        )]
    )
    (If
        (Name
            b
            Load
        )
        [(Expr
            (ConstantEllipsis
                ()
            )
        )]
        [(Assign
            [(Name
                a
                Store
            )]
            (Name
                b
                Load
            )
            ()
        )]
    )
    (If
        (Name
            b
            Load
        )
        [(Pass)]
        [(Assign
            [(Name
                a
                Store
            )]
            (Name
                b
                Load
            )
            ()
        )]
    )
    (If
        (Compare
            (Name
                a
                Load
            )
            Eq
            [(Name
                b
                Load
            )]
        )
        [(Pass)]
        []
    )
    (If
        (Compare
            (Name
                a
                Load
            )
            Eq
            [(Name
                b
                Load
            )]
        )
        [(Pass)]
        []
    )
    (If
        (Compare
            (Name
                a
                Load
            )
            Eq
            [(Name
                b
                Load
            )]
        )
        [(Assign
            [(Name
                x
                Store
            )]
            (BinOp
                (Name
                    a
                    Load
                )
                Add
                (Name
                    b
                    Load
                )
            )
            ()
        )
        (Break)]
        []
    )
    (If
        (Compare
            (Name
                a
                Load
            )
            Eq
            [(Name
                b
                Load
            )]
        )
        [(Assign
            [(Name
                x
                Store
            )]
            (BinOp
                (Name
                    a
                    Load
                )
                Add
                (Name
                    b
                    Load
                )
            )
            ()
        )
        (Break)]
        []
    )]
    []
)