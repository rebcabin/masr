(Module
    [(Expr
        (BoolOp
            And
            [(Name
                a
                Load
            )
            (Name
                b
                Load
            )]
        )
    )
    (Expr
        (BoolOp
            Or
            [(BoolOp
                And
                [(Name
                    a
                    Load
                )
                (Name
                    b
                    Load
                )]
            )
            (Name
                c
                Load
            )]
        )
    )
    (Expr
        (BoolOp
            Or
            [(Name
                a
                Load
            )
            (Name
                b
                Load
            )]
        )
    )
    (Expr
        (BoolOp
            Or
            [(Name
                a
                Load
            )
            (BoolOp
                And
                [(Name
                    b
                    Load
                )
                (Name
                    c
                    Load
                )]
            )]
        )
    )
    (Expr
        (BoolOp
            Or
            [(BoolOp
                And
                [(BoolOp
                    And
                    [(Name
                        a
                        Load
                    )
                    (Name
                        b
                        Load
                    )]
                )
                (Name
                    c
                    Load
                )]
            )
            (BoolOp
                Or
                [(BoolOp
                    Or
                    [(Name
                        x
                        Load
                    )
                    (BoolOp
                        And
                        [(Name
                            y
                            Load
                        )
                        (BoolOp
                            Or
                            [(Name
                                z
                                Load
                            )
                            (Name
                                i
                                Load
                            )]
                        )]
                    )]
                )
                (Name
                    j
                    Load
                )]
            )]
        )
    )
    (Expr
        (BoolOp
            And
            [(BoolOp
                Or
                [(BoolOp
                    And
                    [(BoolOp
                        And
                        [(Name
                            a
                            Load
                        )
                        (BoolOp
                            And
                            [(Name
                                b
                                Load
                            )
                            (BoolOp
                                Or
                                [(Name
                                    c
                                    Load
                                )
                                (Name
                                    d
                                    Load
                                )]
                            )]
                        )]
                    )
                    (BoolOp
                        And
                        [(Name
                            e
                            Load
                        )
                        (Name
                            f
                            Load
                        )]
                    )]
                )
                (Name
                    x
                    Load
                )]
            )
            (Name
                y
                Load
            )]
        )
    )
    (Expr
        (BoolOp
            Or
            [(BoolOp
                Or
                [(Name
                    a
                    Load
                )
                (BoolOp
                    And
                    [(Name
                        b
                        Load
                    )
                    (Name
                        c
                        Load
                    )]
                )]
            )
            (Name
                z
                Load
            )]
        )
    )]
    []
)