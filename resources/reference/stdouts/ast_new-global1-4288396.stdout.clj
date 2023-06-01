(Module
    [(Assign
        [(Name
            x
            Store
        )]
        (ConstantStr
            "global "
            ()
        )
        ()
    )
    (FunctionDef
        outer
        ([]
        []
        []
        []
        []
        []
        [])
        [(Assign
            [(Name
                x
                Store
            )]
            (ConstantStr
                "local"
                ()
            )
            ()
        )
        (FunctionDef
            inner
            ([]
            []
            []
            []
            []
            []
            [])
            [(Nonlocal
                [x]
            )
            (Assign
                [(Name
                    x
                    Store
                )]
                (ConstantStr
                    "nonlocal"
                    ()
                )
                ()
            )
            (Assert
                (Compare
                    (Name
                        x
                        Load
                    )
                    Eq
                    [(ConstantStr
                        "nonlocal"
                        ()
                    )]
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
                    inner
                    Load
                )
                []
                []
            )
        )
        (Assert
            (Compare
                (Name
                    x
                    Load
                )
                Eq
                [(ConstantStr
                    "nonlocal"
                    ()
                )]
            )
            ()
        )]
        []
        ()
        ()
    )
    (FunctionDef
        test_1
        ([]
        []
        []
        []
        []
        []
        [])
        [(Global
            [x]
        )
        (Assign
            [(Name
                y
                Store
            )]
            (ConstantStr
                "local"
                ()
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
                    x
                    Load
                )
                Mult
                (ConstantInt
                    2
                    ()
                )
            )
            ()
        )
        (Assert
            (Compare
                (Name
                    x
                    Load
                )
                Eq
                [(ConstantStr
                    "global global "
                    ()
                )]
            )
            ()
        )
        (Assert
            (Compare
                (Name
                    y
                    Load
                )
                Eq
                [(ConstantStr
                    "local"
                    ()
                )]
            )
            ()
        )]
        []
        ()
        ()
    )
    (FunctionDef
        check
        ([]
        []
        []
        []
        []
        []
        [])
        [(Expr
            (Call
                (Name
                    test_1
                    Load
                )
                []
                []
            )
        )
        (Expr
            (Call
                (Name
                    outer
                    Load
                )
                []
                []
            )
        )]
        []
        ()
        ()
    )
    (Expr
        (Call
            (Name
                check
                Load
            )
            []
            []
        )
    )]
    []
)