(Module
    [(FunctionDef
        test
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
            (Dict
                []
                []
            )
            ()
        )
        (Assign
            [(Name
                dict
                Store
            )]
            (Dict
                [(ConstantStr
                    "brand"
                    ()
                )
                (ConstantStr
                    "electric"
                    ()
                )
                (ConstantStr
                    "year"
                    ()
                )
                (ConstantStr
                    "colors"
                    ()
                )]
                [(ConstantStr
                    "Ford"
                    ()
                )
                (ConstantBool
                    false
                    ()
                )
                (ConstantInt
                    1964
                    ()
                )
                (List
                    [(ConstantStr
                        "red"
                        ()
                    )
                    (ConstantStr
                        "white"
                        ()
                    )]
                    Load
                )]
            )
            ()
        )
        (Assign
            [(Name
                num
                Store
            )]
            (Dict
                [(ConstantInt
                    1
                    ()
                )
                (ConstantInt
                    2
                    ()
                )]
                [(ConstantInt
                    2
                    ()
                )
                (ConstantInt
                    3
                    ()
                )]
            )
            ()
        )
        (AnnAssign
            (Name
                y
                Store
            )
            (Subscript
                (Name
                    dict
                    Load
                )
                (Tuple
                    [(Name
                        str
                        Load
                    )
                    (Name
                        i32
                        Load
                    )]
                    Load
                )
                Load
            )
            ()
            1
        )
        (Assign
            [(Name
                y
                Store
            )]
            (Dict
                [(ConstantStr
                    "a"
                    ()
                )
                (ConstantStr
                    "b"
                    ()
                )]
                [(UnaryOp
                    USub
                    (ConstantInt
                        1
                        ()
                    )
                )
                (UnaryOp
                    USub
                    (ConstantInt
                        2
                        ()
                    )
                )]
            )
            ()
        )
        (Assign
            [(Name
                y
                Store
            )]
            (Dict
                [(ConstantStr
                    "a"
                    ()
                )
                (ConstantStr
                    "b"
                    ()
                )]
                [(UnaryOp
                    USub
                    (ConstantInt
                        1
                        ()
                    )
                )
                (UnaryOp
                    USub
                    (ConstantInt
                        2
                        ()
                    )
                )]
            )
            ()
        )
        (Assign
            [(Subscript
                (Name
                    y
                    Load
                )
                (ConstantStr
                    "a"
                    ()
                )
                Store
            )]
            (ConstantInt
                123
                ()
            )
            ()
        )
        (Assign
            [(Name
                a
                Store
            )]
            (Dict
                [(ConstantStr
                    "a"
                    ()
                )
                (ConstantStr
                    "b"
                    ()
                )]
                [(ConstantInt
                    1
                    ()
                )
                (ConstantInt
                    2
                    ()
                )]
            )
            ()
        )
        (Assign
            [(Name
                x
                Store
            )]
            (Dict
                [(ConstantStr
                    "markdown2.tpl"
                    ()
                )]
                [(ConstantStr
                    "          Text\n          "
                    ()
                )]
            )
            ()
        )
        (Assign
            [(Name
                x
                Store
            )]
            (Dict
                [(ConstantNone
                    ()
                )
                (Name
                    y
                    Load
                )
                (ConstantNone
                    ()
                )
                (ConstantNone
                    ()
                )]
                [(Name
                    x
                    Load
                )
                (ConstantStr
                    "Text"
                    ()
                )
                (Name
                    z
                    Load
                )
                (ConstantStr
                    "None"
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
                test
                Load
            )
            []
            []
        )
    )]
    [(TypeIgnore
        0
        ""
    )]
)