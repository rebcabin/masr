(Module
    [(With
        [((Call
            (Name
                A
                Load
            )
            []
            []
        )
        (Name
            a
            Store
        ))
        ((Call
            (Name
                B
                Load
            )
            []
            []
        )
        (Name
            b
            Store
        ))]
        [(Expr
            (Call
                (Name
                    f
                    Load
                )
                [(Name
                    a
                    Load
                )
                (Name
                    b
                    Load
                )]
                []
            )
        )]
        ()
    )
    (With
        [((Call
            (Name
                A
                Load
            )
            []
            []
        )
        (Name
            a
            Store
        ))
        ((Call
            (Name
                B
                Load
            )
            [(Name
                a
                Load
            )]
            []
        )
        (Name
            b
            Store
        ))
        ((Call
            (Name
                C
                Load
            )
            [(Name
                a
                Load
            )
            (Name
                b
                Load
            )]
            []
        )
        (Name
            c
            Store
        ))]
        [(Expr
            (Call
                (Name
                    f
                    Load
                )
                [(Name
                    a
                    Load
                )
                (Name
                    c
                    Load
                )]
                []
            )
        )]
        ()
    )
    (With
        [((Call
            (Name
                open
                Load
            )
            [(ConstantStr
                "examples/expr2.py"
                ()
            )
            (ConstantStr
                "r"
                ()
            )]
            []
        )
        (Name
            file
            Store
        ))]
        [(Assign
            [(Name
                x
                Store
            )]
            (Call
                (Attribute
                    (Name
                        file
                        Load
                    )
                    read
                    Load
                )
                []
                []
            )
            ()
        )]
        ()
    )
    (With
        [((Call
            (Name
                open
                Load
            )
            [(ConstantEllipsis
                ()
            )]
            []
        )
        (Name
            f
            Store
        ))]
        [(Expr
            (ConstantEllipsis
                ()
            )
        )]
        ()
    )
    (With
        [((Call
            (Name
                open
                Load
            )
            [(ConstantStr
                "examples/expr2.py"
                ()
            )
            (ConstantStr
                "r"
                ()
            )]
            []
        )
        (Name
            file
            Store
        ))]
        [(Assign
            [(Name
                x
                Store
            )]
            (Call
                (Attribute
                    (Name
                        file
                        Load
                    )
                    read
                    Load
                )
                []
                []
            )
            ()
        )]
        ()
    )
    (With
        [((Name
            a
            Load
        )
        ())
        ((Name
            b
            Load
        )
        ())
        ((Name
            c
            Load
        )
        (Name
            y
            Store
        ))
        ((Name
            z
            Load
        )
        ())]
        [(Pass)]
        ()
    )
    (With
        [((Call
            (Name
                tag
                Load
            )
            [(ConstantStr
                "x"
                ()
            )]
            []
        )
        ())]
        [(Pass)]
        ()
    )
    (With
        [((Name
            t
            Load
        )
        (Name
            x
            Store
        ))
        ((Call
            (Name
                y
                Load
            )
            []
            []
        )
        ())]
        [(Expr
            (ConstantEllipsis
                ()
            )
        )]
        ()
    )
    (With
        [((Name
            a
            Load
        )
        (Name
            b
            Store
        ))
        ((Call
            (Name
                c
                Load
            )
            []
            []
        )
        ())
        ((Call
            (Name
                d
                Load
            )
            []
            []
        )
        ())
        ((Name
            e
            Load
        )
        (Name
            f
            Store
        ))
        ((Name
            g
            Load
        )
        (Name
            x
            Store
        ))]
        [(Pass)]
        ()
    )]
    []
)
