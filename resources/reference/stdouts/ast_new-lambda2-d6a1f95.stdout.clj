(Module
    [(Expr
        (Call
            (Name
                verify_matching_signatures
                Load
            )
            [(Lambda
                ([]
                [(x
                ()
                ())]
                []
                []
                []
                []
                [(ConstantInt
                    0
                    ()
                )])
                (ConstantInt
                    0
                    ()
                )
            )
            (Lambda
                ([]
                [(x
                ()
                ())]
                []
                []
                []
                []
                [(ConstantInt
                    0
                    ()
                )])
                (ConstantInt
                    0
                    ()
                )
            )]
            []
        )
    )
    (Assign
        [(Name
            input_func
            Store
        )]
        (Lambda
            ([]
            [(prompt
            ()
            ())]
            []
            []
            []
            []
            [(ConstantStr
                ""
                ()
            )])
            (Call
                (Name
                    next
                    Load
                )
                [(Name
                    gen
                    Load
                )]
                []
            )
        )
        ()
    )
    (Assign
        [(Subscript
            (Name
                cast
                Load
            )
            (Name
                key
                Load
            )
            Store
        )]
        (Lambda
            ([]
            [(x
            ()
            ())
            (k
            ()
            ())]
            []
            []
            []
            []
            [(Name
                key
                Load
            )])
            (Call
                (Attribute
                    (Call
                        (Name
                            array
                            Load
                        )
                        [(Name
                            x
                            Load
                        )]
                        [(copy
                        (ConstantBool
                            false
                            ()
                        ))]
                    )
                    astype
                    Load
                )
                [(Name
                    k
                    Load
                )]
                []
            )
        )
        ()
    )
    (Assign
        [(Name
            x
            Store
        )]
        (Lambda
            ([]
            [(func
            ()
            ())
            (attr
            ()
            ())]
            []
            []
            []
            []
            [(Attribute
                (Name
                    self
                    Load
                )
                _try_call
                Load
            )
            (Name
                attr
                Load
            )])
            (Call
                (Name
                    func
                    Load
                )
                [(Name
                    attr
                    Load
                )]
                []
            )
        )
        ()
    )
    (Assign
        [(Name
            fpos32
            Store
        )]
        (Lambda
            ([]
            [(x
            ()
            ())]
            []
            []
            []
            [(k
            ()
            ())]
            [])
            (Call
                (Attribute
                    (Name
                        np
                        Load
                    )
                    format_float_positional
                    Load
                )
                [(Call
                    (Attribute
                        (Name
                            np
                            Load
                        )
                        float32
                        Load
                    )
                    [(Name
                        x
                        Load
                    )]
                    []
                )]
                [(()
                (Name
                    k
                    Load
                ))]
            )
        )
        ()
    )
    (Assign
        [(Name
            m
            Store
        )]
        (Lambda
            ([]
            [(self
            ()
            ())]
            [(args
            ()
            ())]
            []
            []
            [(kw
            ()
            ())]
            [])
            (Call
                (Name
                    func
                    Load
                )
                [(Name
                    self
                    Load
                )
                (Starred
                    (Name
                        args
                        Load
                    )
                    Load
                )]
                [(()
                (Name
                    kw
                    Load
                ))]
            )
        )
        ()
    )
    (Expr
        (Call
            (Attribute
                (Name
                    stack
                    Load
                )
                push
                Load
            )
            [(Lambda
                ([]
                []
                [(exc
                ()
                ())]
                []
                []
                []
                [])
                (Subscript
                    (Dict
                        []
                        []
                    )
                    (ConstantInt
                        1
                        ()
                    )
                    Load
                )
            )]
            []
        )
    )
    (Expr
        (Lambda
            ([]
            []
            [(x
            ()
            ())]
            [(y
            ()
            ())
            (z
            ()
            ())]
            [(ConstantNone
                ()
            )
            (ConstantNone
                ()
            )]
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            []
            [(x
            ()
            ())]
            [(y
            ()
            ())
            (z
            ()
            ())]
            [(ConstantNone
                ()
            )
            (ConstantNone
                ()
            )]
            [(args
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            []
            [(x
            ()
            ())]
            []
            []
            [(args
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            []
            []
            []
            []
            [(args
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            [(x
            ()
            ())
            (y
            ()
            ())]
            []
            []
            []
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            [(x
            ()
            ())]
            [(y
            ()
            ())]
            []
            []
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            [(x
            ()
            ())]
            [(y
            ()
            ())]
            [(z
            ()
            ())]
            [(ConstantNone
                ()
            )]
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            [(x
            ()
            ())]
            [(y
            ()
            ())]
            [(z
            ()
            ())]
            [(ConstantNone
                ()
            )]
            [(args
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            [(x
            ()
            ())]
            [(y
            ()
            ())]
            []
            []
            [(args
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            [(x
            ()
            ())]
            []
            []
            []
            [(args
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            [(x
            ()
            ())]
            [(y
            ()
            ())]
            [(z
            ()
            ())]
            [(ConstantNone
                ()
            )]
            [(args
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            [(b
            ()
            ())
            (c
            ()
            ())]
            []
            []
            []
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            []
            [(b
            ()
            ())]
            []
            []
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            []
            [(b
            ()
            ())]
            [(c
            ()
            ())
            (d
            ()
            ())]
            [(ConstantNone
                ()
            )
            (ConstantNone
                ()
            )]
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            []
            [(b
            ()
            ())]
            [(c
            ()
            ())]
            [(ConstantNone
                ()
            )]
            [(d
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            []
            []
            []
            []
            [(b
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            []
            [(b
            ()
            ())]
            []
            []
            [(c
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            [(b
            ()
            ())
            (c
            ()
            ())]
            [(d
            ()
            ())]
            []
            []
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            [(b
            ()
            ())
            (c
            ()
            ())]
            []
            []
            []
            [(d
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            [(b
            ()
            ())
            (c
            ()
            ())]
            [(d
            ()
            ())]
            []
            []
            [(e
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            [(b
            ()
            ())
            (c
            ()
            ())]
            [(d
            ()
            ())]
            [(e
            ()
            ())]
            [(ConstantNone
                ()
            )]
            [(f
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            []
            []
            [(a
            ()
            ())]
            [(ConstantNone
                ()
            )]
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            []
            []
            [(a
            ()
            ())]
            [(ConstantNone
                ()
            )]
            [(b
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            [(a
            ()
            ())]
            []
            [(b
            ()
            ())]
            [(ConstantNone
                ()
            )]
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([]
            [(a
            ()
            ())]
            []
            [(b
            ()
            ())]
            [(ConstantNone
                ()
            )]
            [(c
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            []
            []
            [(b
            ()
            ())]
            [(ConstantNone
                ()
            )]
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            [(b
            ()
            ())]
            []
            [(c
            ()
            ())]
            [(ConstantNone
                ()
            )]
            []
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            []
            []
            [(c
            ()
            ())]
            [(ConstantNone
                ()
            )]
            [(d
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )
    (Expr
        (Lambda
            ([(a
            ()
            ())]
            [(b
            ()
            ())]
            []
            [(c
            ()
            ())]
            [(ConstantNone
                ()
            )]
            [(d
            ()
            ())]
            [])
            (Name
                test
                Load
            )
        )
    )]
    []
)
