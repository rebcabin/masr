(Module
    [(FunctionDef
        test_namedexpr
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
        (AnnAssign
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
                y
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
                x
                Store
            )]
            (NamedExpr
                (Name
                    y
                    Store
                )
                (ConstantInt
                    0
                    ()
                )
            )
            ()
        )
        (If
            (NamedExpr
                (Name
                    a
                    Store
                )
                (Call
                    (Name
                        ord
                        Load
                    )
                    [(ConstantStr
                        "3"
                        ()
                    )]
                    []
                )
            )
            [(Assign
                [(Name
                    x
                    Store
                )]
                (ConstantInt
                    1
                    ()
                )
                ()
            )]
            []
        )
        (While
            (NamedExpr
                (Name
                    a
                    Store
                )
                (ConstantInt
                    1
                    ()
                )
            )
            [(Assign
                [(Name
                    y
                    Store
                )]
                (ConstantInt
                    1
                    ()
                )
                ()
            )]
            []
        )]
        []
        ()
        ()
    )]
    []
)
