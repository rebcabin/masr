(Module
    [(AsyncFunctionDef
        func
        ([]
        [(param1
        ()
        ())
        (param2
        ()
        ())]
        []
        []
        []
        []
        [])
        [(Expr
            (Await
                (Call
                    (Attribute
                        (Name
                            asyncio
                            Load
                        )
                        sleep
                        Load
                    )
                    [(ConstantInt
                        1
                        ()
                    )]
                    []
                )
            )
        )
        (Expr
            (Call
                (Name
                    do_something
                    Load
                )
                []
                []
            )
        )
        (AsyncFor
            (Name
                x
                Store
            )
            (Name
                y
                Load
            )
            [(Expr
                (Call
                    (Name
                        do_something
                        Load
                    )
                    [(Name
                        x
                        Load
                    )]
                    []
                )
            )]
            []
            ()
        )
        (AsyncWith
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
        )]
        []
        ()
        ()
    )
    (AsyncFunctionDef
        test_01
        ([]
        []
        []
        []
        []
        []
        [])
        [(Assign
            [(Name
                results
                Store
            )]
            (Await
                (Call
                    (Attribute
                        (Name
                            tasks
                            Load
                        )
                        gather
                        Load
                    )
                    [(Starred
                        (ListComp
                            (Call
                                (Attribute
                                    (Name
                                        ag
                                        Load
                                    )
                                    aclose
                                    Load
                                )
                                []
                                []
                            )
                            [((Name
                                ag
                                Store
                            )
                            (Name
                                closing_agens
                                Load
                            )
                            []
                            0)]
                        )
                        Load
                    )]
                    [(return_exceptions
                    (ConstantBool
                        true
                        ()
                    ))]
                )
            )
            ()
        )
        (With
            [((Await
                (Name
                    lock
                    Load
                )
            )
            ())]
            [(Pass)]
            ()
        )
        (AugAssign
            (Name
                data
                Store
            )
            Add
            (Await
                (Call
                    (Attribute
                        (Attribute
                            (Name
                                self
                                Load
                            )
                            loop
                            Load
                        )
                        sock_recv
                        Load
                    )
                    [(Name
                        sock
                        Load
                    )
                    (Name
                        DATA_SIZE
                        Load
                    )]
                    []
                )
            )
        )
        (Return
            (Await
                (Call
                    (Attribute
                        (Name
                            self
                            Load
                        )
                        run_in_executor
                        Load
                    )
                    [(ConstantNone
                        ()
                    )
                    (Name
                        getaddr_func
                        Load
                    )
                    (Name
                        host
                        Load
                    )
                    (Name
                        port
                        Load
                    )
                    (Name
                        family
                        Load
                    )
                    (Name
                        type
                        Load
                    )
                    (Name
                        proto
                        Load
                    )
                    (Name
                        flags
                        Load
                    )]
                    []
                )
            )
        )]
        []
        ()
        ()
    )
    (AsyncFunctionDef
        test_02
        ([]
        []
        []
        []
        []
        []
        [])
        [(Assign
            [(Name
                items
                Store
            )]
            (ListComp
                (Await
                    (Call
                        (Attribute
                            (Name
                                q
                                Load
                            )
                            get
                            Load
                        )
                        []
                        []
                    )
                )
                [((Name
                    _
                    Store
                )
                (Call
                    (Name
                        range
                        Load
                    )
                    [(ConstantInt
                        3
                        ()
                    )]
                    []
                )
                []
                0)]
            )
            ()
        )
        (Expr
            (SetComp
                (Await
                    (Name
                        c
                        Load
                    )
                )
                [((Name
                    c
                    Store
                )
                (List
                    [(Call
                        (Name
                            f
                            Load
                        )
                        [(ConstantInt
                            1
                            ()
                        )]
                        []
                    )
                    (Call
                        (Name
                            f
                            Load
                        )
                        [(ConstantInt
                            41
                            ()
                        )]
                        []
                    )]
                    Load
                )
                []
                0)]
            )
        )
        (Expr
            (DictComp
                (Name
                    i
                    Load
                )
                (Await
                    (Name
                        c
                        Load
                    )
                )
                [((Tuple
                    [(Name
                        i
                        Store
                    )
                    (Name
                        c
                        Store
                    )]
                    Store
                )
                (Call
                    (Name
                        enumerate
                        Load
                    )
                    [(List
                        [(Call
                            (Name
                                f
                                Load
                            )
                            [(ConstantInt
                                1
                                ()
                            )]
                            []
                        )
                        (Call
                            (Name
                                f
                                Load
                            )
                            [(ConstantInt
                                41
                                ()
                            )]
                            []
                        )]
                        Load
                    )]
                    []
                )
                []
                0)]
            )
        )
        (Expr
            (ListComp
                (Name
                    s
                    Load
                )
                [((Name
                    c
                    Store
                )
                (List
                    [(Call
                        (Name
                            f
                            Load
                        )
                        [(ConstantStr
                            ""
                            ()
                        )]
                        []
                    )
                    (Call
                        (Name
                            f
                            Load
                        )
                        [(ConstantStr
                            "abc"
                            ()
                        )]
                        []
                    )
                    (Call
                        (Name
                            f
                            Load
                        )
                        [(ConstantStr
                            ""
                            ()
                        )]
                        []
                    )
                    (Call
                        (Name
                            f
                            Load
                        )
                        [(List
                            [(ConstantStr
                                "de"
                                ()
                            )
                            (ConstantStr
                                "fg"
                                ()
                            )]
                            Load
                        )]
                        []
                    )]
                    Load
                )
                []
                0)
                ((Name
                    s
                    Store
                )
                (Await
                    (Name
                        c
                        Load
                    )
                )
                []
                0)]
            )
        )
        (Return
            (GeneratorExp
                (BinOp
                    (Name
                        i
                        Load
                    )
                    Mult
                    (ConstantInt
                        2
                        ()
                    )
                )
                [((Name
                    i
                    Store
                )
                (Call
                    (Name
                        range
                        Load
                    )
                    [(Name
                        n
                        Load
                    )]
                    []
                )
                [(Await
                    (Call
                        (Name
                            wrap
                            Load
                        )
                        [(Name
                            i
                            Load
                        )]
                        []
                    )
                )]
                0)]
            )
        )]
        []
        ()
        ()
    )
    (AsyncFunctionDef
        t
        ([]
        []
        []
        []
        []
        []
        [])
        [(Expr
            (Call
                (Attribute
                    (Name
                        results
                        Load
                    )
                    append
                    Load
                )
                [(Await
                    (Call
                        (Name
                            anext
                            Load
                        )
                        [(Name
                            g
                            Load
                        )]
                        []
                    )
                )]
                []
            )
        )
        (Expr
            (Call
                (Attribute
                    (Name
                        self
                        Load
                    )
                    assertIn
                    Load
                )
                [(ConstantStr
                    "..."
                    ()
                )
                (Call
                    (Name
                        repr
                        Load
                    )
                    [(Await
                        (Call
                            (Attribute
                                (Name
                                    asyncio
                                    Load
                                )
                                wait_for
                                Load
                            )
                            [(Call
                                (Name
                                    func
                                    Load
                                )
                                []
                                []
                            )]
                            [(timeout
                            (ConstantInt
                                10
                                ()
                            ))]
                        )
                    )]
                    []
                )]
                []
            )
        )
        (Assign
            [(Name
                x
                Store
            )]
            (UnaryOp
                USub
                (Await
                    (Call
                        (Name
                            bar
                            Load
                        )
                        []
                        []
                    )
                )
            )
            ()
        )
        (Return
            (BinOp
                (BinOp
                    (BinOp
                        (BinOp
                            (Await
                                (Call
                                    (Name
                                        bar
                                        Load
                                    )
                                    []
                                    []
                                )
                            )
                            Add
                            (Await
                                (Call
                                    (Call
                                        (Name
                                            wrap
                                            Load
                                        )
                                        []
                                        []
                                    )
                                    []
                                    []
                                )
                            )
                        )
                        Add
                        (Await
                            (Call
                                (Call
                                    (Call
                                        (Subscript
                                            (Name
                                                db
                                                Load
                                            )
                                            (ConstantStr
                                                "b"
                                                ()
                                            )
                                            Load
                                        )
                                        []
                                        []
                                    )
                                    []
                                    []
                                )
                                []
                                []
                            )
                        )
                    )
                    Add
                    (BinOp
                        (Await
                            (Call
                                (Name
                                    bar
                                    Load
                                )
                                []
                                []
                            )
                        )
                        Mult
                        (ConstantInt
                            1000
                            ()
                        )
                    )
                )
                Add
                (Await
                    (Call
                        (Call
                            (Attribute
                                (Name
                                    DB
                                    Load
                                )
                                b
                                Load
                            )
                            []
                            []
                        )
                        []
                        []
                    )
                )
            )
        )]
        []
        ()
        ()
    )]
    []
)