(Module
    [(Assign
        [(Name
            fruits
            Store
        )]
        (ListComp
            (Name
                f
                Load
            )
            [((Name
                f
                Store
            )
            (Name
                fruit_list
                Load
            )
            [(Call
                (Attribute
                    (Name
                        f
                        Load
                    )
                    startswith
                    Load
                )
                [(ConstantStr
                    "a"
                    ()
                )]
                []
            )]
            0)]
        )
        ()
    )
    (Assign
        [(Name
            fruit_list
            Store
        )]
        (ListComp
            (Name
                fruit
                Load
            )
            [((Name
                fruit
                Store
            )
            (Name
                fruits
                Load
            )
            []
            0)]
        )
        ()
    )
    (Assign
        [(Name
            sum_cord
            Store
        )]
        (ListComp
            (BinOp
                (Name
                    x
                    Load
                )
                Add
                (Name
                    y
                    Load
                )
            )
            [((Tuple
                [(Name
                    x
                    Store
                )
                (Name
                    y
                    Store
                )]
                Store
            )
            (Name
                points
                Load
            )
            [(BoolOp
                And
                [(Compare
                    (Name
                        x
                        Load
                    )
                    Gt
                    [(ConstantInt
                        0
                        ()
                    )]
                )
                (Compare
                    (Name
                        y
                        Load
                    )
                    Gt
                    [(ConstantInt
                        0
                        ()
                    )]
                )]
            )]
            0)]
        )
        ()
    )
    (Assign
        [(Name
            transform_1
            Store
        )]
        (ListComp
            (BinOp
                (BinOp
                    (ConstantInt
                        2
                        ()
                    )
                    Mult
                    (Name
                        x
                        Load
                    )
                )
                Add
                (ConstantInt
                    6
                    ()
                )
            )
            [((Name
                x
                Store
            )
            (Call
                (Name
                    range
                    Load
                )
                [(ConstantInt
                    10
                    ()
                )]
                []
            )
            []
            0)]
        )
        ()
    )
    (Assign
        [(Name
            distance_orig
            Store
        )]
        (ListComp
            (BinOp
                (BinOp
                    (BinOp
                        (Name
                            x
                            Load
                        )
                        Pow
                        (ConstantInt
                            2
                            ()
                        )
                    )
                    Add
                    (BinOp
                        (Name
                            y
                            Load
                        )
                        Pow
                        (ConstantInt
                            2
                            ()
                        )
                    )
                )
                Add
                (BinOp
                    (Name
                        z
                        Load
                    )
                    Pow
                    (ConstantInt
                        2
                        ()
                    )
                )
            )
            [((Tuple
                [(Name
                    x
                    Store
                )
                (Name
                    y
                    Store
                )
                (Name
                    z
                    Store
                )]
                Store
            )
            (Name
                points
                Load
            )
            []
            0)]
        )
        ()
    )
    (Assign
        [(Name
            odd_elements
            Store
        )]
        (ListComp
            (Name
                i
                Load
            )
            [((Name
                i
                Store
            )
            (Name
                main_list
                Load
            )
            [(BinOp
                (Name
                    i
                    Load
                )
                BitAnd
                (ConstantInt
                    1
                    ()
                )
            )]
            0)]
        )
        ()
    )
    (Assign
        [(Name
            first_ten_elements
            Store
        )]
        (ListComp
            (Name
                i
                Load
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
                [(ConstantInt
                    10
                    ()
                )]
                []
            )
            []
            0)]
        )
        ()
    )
    (Assign
        [(Name
            another_ten_elements
            Store
        )]
        (ListComp
            (Name
                i
                Load
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
                [(ConstantInt
                    10
                    ()
                )]
                []
            )
            []
            0)]
        )
        ()
    )
    (Assign
        [(Name
            comp
            Store
        )]
        (ListComp
            (BinOp
                (Name
                    i
                    Load
                )
                Pow
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
                [(ConstantInt
                    10
                    ()
                )]
                []
            )
            [(BoolOp
                And
                [(Compare
                    (Name
                        i
                        Load
                    )
                    NotIn
                    [(List
                        [(ConstantInt
                            3
                            ()
                        )
                        (ConstantInt
                            5
                            ()
                        )
                        (ConstantInt
                            7
                            ()
                        )]
                        Load
                    )]
                )
                (Compare
                    (Name
                        i
                        Load
                    )
                    In
                    [(Name
                        list3
                        Load
                    )]
                )]
            )]
            0)]
        )
        ()
    )
    (Assign
        [(Name
            items
            Store
        )]
        (ListComp
            (Name
                name
                Load
            )
            [((Tuple
                [(Name
                    name
                    Store
                )
                (Name
                    value
                    Store
                )]
                Store
            )
            (Call
                (Attribute
                    (Call
                        (Name
                            vars
                            Load
                        )
                        [(Name
                            argparse
                            Load
                        )]
                        []
                    )
                    items
                    Load
                )
                []
                []
            )
            [(UnaryOp
                Not
                (BoolOp
                    Or
                    [(Call
                        (Attribute
                            (Name
                                name
                                Load
                            )
                            startswith
                            Load
                        )
                        [(ConstantStr
                            "_"
                            ()
                        )]
                        []
                    )
                    (Compare
                        (Name
                            name
                            Load
                        )
                        Eq
                        [(ConstantStr
                            "ngettext"
                            ()
                        )]
                    )]
                )
            )
            (UnaryOp
                Not
                (Call
                    (Attribute
                        (Name
                            inspect
                            Load
                        )
                        ismodule
                        Load
                    )
                    [(Name
                        value
                        Load
                    )]
                    []
                )
            )]
            0)]
        )
        ()
    )
    (Expr
        (ListComp
            (ConstantInt
                0
                ()
            )
            [((Subscript
                (Name
                    tgt
                    Load
                )
                (ConstantInt
                    0
                    ()
                )
                Store
            )
            (Call
                (Name
                    source
                    Load
                )
                []
                []
            )
            []
            1)]
        )
    )
    (Assign
        [(Name
            prm_tup
            Store
        )]
        (Call
            (Name
                tuple
                Load
            )
            [(GeneratorExp
                (Call
                    (Name
                        next
                        Load
                    )
                    [(Name
                        parameters
                        Load
                    )]
                    []
                )
                [((Name
                    _
                    Store
                )
                (Attribute
                    (Name
                        i
                        Load
                    )
                    __parameters__
                    Load
                )
                []
                0)]
            )]
            []
        )
        ()
    )
    (Assign
        [(Name
            args
            Store
        )]
        (Call
            (Attribute
                (ConstantStr
                    ", "
                    ()
                )
                join
                Load
            )
            [(GeneratorExp
                (Call
                    (Name
                        _to_str
                        Load
                    )
                    [(Name
                        i
                        Load
                    )]
                    []
                )
                [((Name
                    i
                    Store
                )
                (Attribute
                    (Name
                        self
                        Load
                    )
                    __args__
                    Load
                )
                []
                0)]
            )]
            []
        )
        ()
    )
    (Assign
        [(Name
            rest
            Store
        )]
        (Call
            (Name
                tuple
                Load
            )
            [(GeneratorExp
                (Name
                    i
                    Load
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
                    [(Attribute
                        (Name
                            a
                            Load
                        )
                        ndim
                        Load
                    )]
                    []
                )
                [(Compare
                    (Name
                        i
                        Load
                    )
                    NotIn
                    [(Name
                        axis
                        Load
                    )]
                )]
                0)]
            )]
            []
        )
        ()
    )
    (Expr
        (GeneratorExp
            (Call
                (Name
                    sstr
                    Load
                )
                [(Name
                    x
                    Load
                )]
                []
            )
            [((List
                [(Name
                    x
                    Store
                )]
                Store
            )
            (Attribute
                (Attribute
                    (Name
                        self
                        Load
                    )
                    _module
                    Load
                )
                gens
                Load
            )
            []
            0)]
        )
    )
    (Expr
        (Call
            (Name
                func
                Load
            )
            [(Starred
                (ListComp
                    (List
                        [(BinOp
                            (Name
                                x
                                Load
                            )
                            Mult
                            (Name
                                y
                                Load
                            )
                        )]
                        Load
                    )
                    [((List
                        [(Name
                            x
                            Store
                        )]
                        Store
                    )
                    (Attribute
                        (Attribute
                            (Name
                                self
                                Load
                            )
                            _module
                            Load
                        )
                        gens
                        Load
                    )
                    []
                    0)
                    ((List
                        [(Name
                            y
                            Store
                        )]
                        Store
                    )
                    (Attribute
                        (Attribute
                            (Name
                                J
                                Load
                            )
                            _module
                            Load
                        )
                        gens
                        Load
                    )
                    []
                    0)]
                )
                Load
            )]
            []
        )
    )
    (Expr
        (GeneratorExp
            (Name
                x
                Load
            )
            [((List
                [(Name
                    a
                    Store
                )
                (Name
                    b
                    Store
                )]
                Store
            )
            (Name
                y
                Load
            )
            []
            0)]
        )
    )
    (Expr
        (GeneratorExp
            (Name
                x
                Load
            )
            [((List
                [(Name
                    a
                    Store
                )
                (Tuple
                    [(Name
                        b
                        Store
                    )
                    (Name
                        c
                        Store
                    )]
                    Store
                )]
                Store
            )
            (Name
                y
                Load
            )
            []
            0)]
        )
    )
    (Expr
        (GeneratorExp
            (Name
                x
                Load
            )
            [((List
                [(Tuple
                    [(Name
                        b
                        Store
                    )
                    (Name
                        c
                        Store
                    )]
                    Store
                )]
                Store
            )
            (Name
                y
                Load
            )
            []
            0)]
        )
    )
    (Expr
        (GeneratorExp
            (Name
                x
                Load
            )
            [((List
                []
                Store
            )
            (Name
                y
                Load
            )
            []
            0)]
        )
    )
    (Expr
        (GeneratorExp
            (Subscript
                (Name
                    string
                    Load
                )
                (Name
                    i
                    Load
                )
                Load
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
                [(BinOp
                    (Call
                        (Name
                            len
                            Load
                        )
                        [(Name
                            string
                            Load
                        )]
                        []
                    )
                    Sub
                    (ConstantInt
                        1
                        ()
                    )
                )
                (UnaryOp
                    USub
                    (ConstantInt
                        1
                        ()
                    )
                )
                (UnaryOp
                    USub
                    (ConstantInt
                        1
                        ()
                    )
                )]
                []
            )
            []
            0)]
        )
    )
    (Assign
        [(Name
            k
            Store
        )]
        (GeneratorExp
            (BinOp
                (Name
                    j
                    Load
                )
                Add
                (Name
                    k
                    Load
                )
            )
            [((Tuple
                [(Name
                    j
                    Store
                )
                (Name
                    k
                    Store
                )]
                Store
            )
            (Call
                (Name
                    range
                    Load
                )
                [(ConstantInt
                    10
                    ()
                )]
                []
            )
            [(Compare
                (Name
                    j
                    Load
                )
                Gt
                [(ConstantInt
                    0
                    ()
                )]
            )]
            0)]
        )
        ()
    )
    (Expr
        (GeneratorExp
            (BinOp
                (BinOp
                    (Name
                        left
                        Load
                    )
                    Add
                    (Name
                        size
                        Load
                    )
                )
                Add
                (Name
                    right
                    Load
                )
            )
            [((Tuple
                [(Name
                    size
                    Store
                )
                (Tuple
                    [(Name
                        left
                        Store
                    )
                    (Name
                        right
                        Store
                    )]
                    Store
                )]
                Store
            )
            (Call
                (Name
                    zip
                    Load
                )
                [(Attribute
                    (Name
                        array
                        Load
                    )
                    shape
                    Load
                )
                (Name
                    pad_width
                    Load
                )]
                []
            )
            []
            0)]
        )
    )
    (Assign
        [(Name
            viter
            Store
        )]
        (GeneratorExp
            (Tuple
                [(Name
                    i
                    Load
                )
                (Name
                    j
                    Load
                )]
                Load
            )
            [((Tuple
                [(Tuple
                    [(Name
                        i
                        Store
                    )
                    (Name
                        _
                        Store
                    )]
                    Store
                )
                (Tuple
                    [(Name
                        j
                        Store
                    )
                    (Name
                        _
                        Store
                    )]
                    Store
                )]
                Store
            )
            (Call
                (Name
                    zip
                    Load
                )
                [(Subscript
                    (Name
                        newargs
                        Load
                    )
                    (Slice
                        (ConstantInt
                            1
                            ()
                        )
                        ()
                        ()
                    )
                    Load
                )
                (Subscript
                    (Name
                        args
                        Load
                    )
                    (Slice
                        (ConstantInt
                            1
                            ()
                        )
                        ()
                        ()
                    )
                    Load
                )]
                []
            )
            []
            0)]
        )
        ()
    )
    (Assign
        [(Name
            newSet
            Store
        )]
        (SetComp
            (BinOp
                (Name
                    element
                    Load
                )
                Mult
                (ConstantInt
                    3
                    ()
                )
            )
            [((Name
                element
                Store
            )
            (Name
                myList
                Load
            )
            []
            0)]
        )
        ()
    )
    (Assign
        [(Name
            newSet
            Store
        )]
        (SetComp
            (BinOp
                (Name
                    element
                    Load
                )
                Mult
                (ConstantInt
                    3
                    ()
                )
            )
            [((Name
                element
                Store
            )
            (Name
                myList
                Load
            )
            [(Compare
                (BinOp
                    (Name
                        element
                        Load
                    )
                    Mod
                    (ConstantInt
                        2
                        ()
                    )
                )
                Eq
                [(ConstantInt
                    0
                    ()
                )]
            )]
            0)]
        )
        ()
    )
    (Expr
        (DictComp
            (Name
                x
                Load
            )
            (BinOp
                (Name
                    x
                    Load
                )
                Pow
                (ConstantInt
                    3
                    ()
                )
            )
            [((Name
                x
                Store
            )
            (Call
                (Name
                    range
                    Load
                )
                [(ConstantInt
                    10
                    ()
                )]
                []
            )
            [(Compare
                (BinOp
                    (BinOp
                        (Name
                            x
                            Load
                        )
                        Pow
                        (ConstantInt
                            3
                            ()
                        )
                    )
                    Mod
                    (ConstantInt
                        4
                        ()
                    )
                )
                Eq
                [(ConstantInt
                    0
                    ()
                )]
            )]
            0)]
        )
    )
    (Assign
        [(Name
            square_dict
            Store
        )]
        (DictComp
            (Name
                num
                Load
            )
            (BinOp
                (Name
                    num
                    Load
                )
                Mult
                (Name
                    num
                    Load
                )
            )
            [((Name
                num
                Store
            )
            (Call
                (Name
                    range
                    Load
                )
                [(ConstantInt
                    1
                    ()
                )
                (ConstantInt
                    11
                    ()
                )]
                []
            )
            []
            0)]
        )
        ()
    )
    (Assign
        [(Name
            error_names
            Store
        )]
        (ListComp
            (Subscript
                (Call
                    (Attribute
                        (Name
                            test_full_name
                            Load
                        )
                        split
                        Load
                    )
                    [(ConstantStr
                        " "
                        ()
                    )]
                    []
                )
                (ConstantInt
                    0
                    ()
                )
                Load
            )
            [((Tuple
                [(Name
                    test_full_name
                    Store
                )
                (Starred
                    (Name
                        _
                        Store
                    )
                    Store
                )]
                Store
            )
            (Name
                errors
                Load
            )
            []
            0)]
        )
        ()
    )]
    []
)