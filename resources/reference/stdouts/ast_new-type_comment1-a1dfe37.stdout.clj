(Module
    [(Import
        [(pytest
        ())]
    )
    (FunctionDef
        ndarray_func
        ([]
        [(x
        ()
        ())]
        []
        []
        []
        []
        [])
        [(Return
            (Name
                x
                Load
            )
        )]
        []
        ()
        "(np.ndarray) -> np.ndarray"
    )
    (FunctionDef
        test
        ([]
        [(x
        ()
        ())]
        []
        []
        []
        []
        [])
        [(Return
            (Name
                x
                Load
            )
        )]
        [(Name
            decorator1
            Load
        )
        (Name
            decorator2
            Load
        )
        (Name
            decorator3
            Load
        )]
        ()
        "(np.ndarray) -> np.ndarray"
    )
    (FunctionDef
        test
        ([]
        []
        []
        []
        []
        []
        [])
        [(Expr
            (ConstantEllipsis
                ()
            )
        )]
        []
        ()
        ()
    )
    (FunctionDef
        main
        ([]
        []
        []
        []
        []
        []
        [])
        [(Pass)]
        []
        ()
        ()
    )
    (Expr
        (Call
            (Name
                x
                Load
            )
            [(Name
                x
                Load
            )
            (Name
                y
                Load
            )]
            []
        )
    )
    (ImportFrom
        sympy.simplify
        [(collect
        ())
        (powsimp
        ())
        (separatevars
        ())
        (simplify
        ())]
        0
    )
    (Expr
        (Await
            (Call
                (Name
                    test
                    Load
                )
                [(Name
                    x
                    Load
                )
                (Name
                    y
                    Load
                )
                (Name
                    z
                    Load
                )]
                []
            )
        )
    )]
    [(TypeIgnore
        0
        ""
    )
    (TypeIgnore
        0
        ""
    )
    (TypeIgnore
        0
        ""
    )
    (TypeIgnore
        0
        ""
    )
    (TypeIgnore
        0
        ""
    )
    (TypeIgnore
        0
        ""
    )
    (TypeIgnore
        0
        ""
    )
    (TypeIgnore
        0
        ""
    )]
)