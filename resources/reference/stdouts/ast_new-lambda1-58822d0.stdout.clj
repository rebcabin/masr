(Module
    [(If
        (Compare
            (Name
                x
                Load
            )
            Is
            [(ConstantInt
                0
                ()
            )]
        )
        [(Assign
            [(Name
                x
                Store
            )]
            (Lambda
                ([]
                [(a
                ()
                ())]
                []
                []
                []
                []
                [])
                (ConstantStr
                    "``{a}```"
                    ()
                )
            )
            ()
        )]
        [(Assign
            [(Name
                y
                Store
            )]
            (Lambda
                ([]
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
                (Call
                    (Name
                        x
                        Load
                    )
                    [(Name
                        a
                        Load
                    )]
                    []
                )
            )
            ()
        )]
    )
    (Assign
        [(Name
            x
            Store
        )]
        (Call
            (Name
                sorted
                Load
            )
            [(Name
                x
                Load
            )]
            [(key
            (Lambda
                ([]
                [(f
                ()
                ())]
                []
                []
                []
                []
                [])
                (Subscript
                    (Name
                        f
                        Load
                    )
                    (ConstantInt
                        0
                        ()
                    )
                    Load
                )
            ))]
        )
        ()
    )
    (Assign
        [(Name
            add
            Store
        )]
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
            (BinOp
                (Call
                    (Attribute
                        (Name
                            np
                            Load
                        )
                        add
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
                Mod
                (ConstantInt
                    5
                    ()
                )
            )
        )
        ()
    )
    (Expr
        (Call
            (Attribute
                (Name
                    var
                    Load
                )
                sort
                Load
            )
            []
            [(key
            (Lambda
                ([]
                [(x
                ()
                ())]
                []
                []
                []
                []
                [])
                (Subscript
                    (Name
                        x
                        Load
                    )
                    (ConstantInt
                        2
                        ()
                    )
                    Load
                )
            ))]
        )
    )
    (FunctionDef
        __lt__
        ([]
        [(self
        ()
        ())
        (other
        ()
        ())]
        []
        []
        []
        []
        [])
        [(Return
            (Call
                (Attribute
                    (Name
                        self
                        Load
                    )
                    _compare
                    Load
                )
                [(Name
                    other
                    Load
                )
                (Lambda
                    ([]
                    [(s
                    ()
                    ())
                    (o
                    ()
                    ())]
                    []
                    []
                    []
                    []
                    [])
                    (Compare
                        (Name
                            s
                            Load
                        )
                        Lt
                        [(Name
                            o
                            Load
                        )]
                    )
                )]
                []
            )
        )]
        []
        ()
        ()
    )
    (FunctionDef
        formatargspec
        ([]
        [(args
        ()
        ())
        (varargs
        ()
        ())
        (varkw
        ()
        ())
        (defaults
        ()
        ())
        (formatarg
        ()
        ())
        (formatvarargs
        ()
        ())
        (formatvarkw
        ()
        ())
        (formatvalue
        ()
        ())
        (join
        ()
        ())]
        []
        []
        []
        []
        [(ConstantInt
            0
            ()
        )
        (ConstantInt
            0
            ()
        )
        (ConstantInt
            0
            ()
        )
        (Name
            str
            Load
        )
        (Lambda
            ([]
            [(name
            ()
            ())]
            []
            []
            []
            []
            [])
            (BinOp
                (ConstantStr
                    "*"
                    ()
                )
                Add
                (Name
                    name
                    Load
                )
            )
        )
        (Lambda
            ([]
            [(name
            ()
            ())]
            []
            []
            []
            []
            [])
            (BinOp
                (ConstantStr
                    "**"
                    ()
                )
                Add
                (Name
                    name
                    Load
                )
            )
        )
        (Lambda
            ([]
            [(value
            ()
            ())]
            []
            []
            []
            []
            [])
            (BinOp
                (ConstantStr
                    "="
                    ()
                )
                Add
                (Call
                    (Name
                        repr
                        Load
                    )
                    [(Name
                        value
                        Load
                    )]
                    []
                )
            )
        )
        (Name
            joinseq
            Load
        )])
        [(Pass)]
        []
        ()
        ()
    )
    (FunctionDef
        __init__
        ([]
        [(self
        ()
        ())
        (float_conv
        ()
        ())
        (int_conv
        ()
        ())
        (float_to_float
        ()
        ())
        (float_to_str
        ()
        ())
        (title
        ()
        ())]
        []
        []
        []
        []
        [(Name
            float
            Load
        )
        (Name
            int
            Load
        )
        (Name
            float
            Load
        )
        (Lambda
            ([]
            [(v
            ()
            ())]
            []
            []
            []
            []
            [])
            (BinOp
                (ConstantStr
                    "%24.16e"
                    ()
                )
                Mod
                (Name
                    v
                    Load
                )
            )
        )
        (ConstantStr
            "Python floating point numbe"
            ()
        )])
        [(Pass)]
        []
        ()
        ()
    )
    (FunctionDef
        test_deprecated
        ([]
        [(self
        ()
        ())
        (func
        ()
        ())]
        []
        []
        []
        []
        [])
        [(Expr
            (Call
                (Attribute
                    (Name
                        self
                        Load
                    )
                    assert_deprecated
                    Load
                )
                [(Lambda
                    ([]
                    []
                    []
                    []
                    []
                    []
                    [])
                    (Call
                        (Name
                            func
                            Load
                        )
                        [(List
                            [(ConstantFloat
                                0.000000
                                ()
                            )
                            (ConstantFloat
                                1.000000
                                ()
                            )]
                            Load
                        )
                        (ConstantFloat
                            0.000000
                            ()
                        )]
                        [(interpolation
                        (ConstantStr
                            "linear"
                            ()
                        ))]
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
                    assert_deprecated
                    Load
                )
                [(Lambda
                    ([]
                    []
                    []
                    []
                    []
                    []
                    [])
                    (Call
                        (Name
                            func
                            Load
                        )
                        [(List
                            [(ConstantFloat
                                0.000000
                                ()
                            )
                            (ConstantFloat
                                1.000000
                                ()
                            )]
                            Load
                        )
                        (ConstantFloat
                            0.000000
                            ()
                        )]
                        [(interpolation
                        (ConstantStr
                            "nearest"
                            ()
                        ))]
                    )
                )]
                []
            )
        )]
        []
        ()
        ()
    )
    (FunctionDef
        indirect
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
            (Lambda
                ([]
                []
                []
                []
                []
                []
                [])
                (Name
                    x
                    Load
                )
            )
        )]
        []
        ()
        ()
    )
    (FunctionDef
        _discovered_machar
        ([]
        [(ftype
        ()
        ())]
        []
        []
        []
        []
        [])
        [(Assign
            [(Name
                params
                Store
            )]
            (Subscript
                (Name
                    _MACHAR_PARAMS
                    Load
                )
                (Name
                    ftype
                    Load
                )
                Load
            )
            ()
        )
        (Return
            (Call
                (Name
                    MachAr
                    Load
                )
                [(Lambda
                    ([]
                    [(v
                    ()
                    ())]
                    []
                    []
                    []
                    []
                    [])
                    (Call
                        (Name
                            array
                            Load
                        )
                        [(List
                            [(Name
                                v
                                Load
                            )]
                            Load
                        )
                        (Name
                            ftype
                            Load
                        )]
                        []
                    )
                )
                (Lambda
                    ([]
                    [(v
                    ()
                    ())]
                    []
                    []
                    []
                    []
                    [])
                    (Subscript
                        (Call
                            (Name
                                _fr0
                                Load
                            )
                            [(Call
                                (Attribute
                                    (Name
                                        v
                                        Load
                                    )
                                    astype
                                    Load
                                )
                                [(Subscript
                                    (Name
                                        params
                                        Load
                                    )
                                    (ConstantStr
                                        "itype"
                                        ()
                                    )
                                    Load
                                )]
                                []
                            )]
                            []
                        )
                        (ConstantInt
                            0
                            ()
                        )
                        Load
                    )
                )
                (Lambda
                    ([]
                    [(v
                    ()
                    ())]
                    []
                    []
                    []
                    []
                    [])
                    (Call
                        (Name
                            array
                            Load
                        )
                        [(Subscript
                            (Call
                                (Name
                                    _fr0
                                    Load
                                )
                                [(Name
                                    v
                                    Load
                                )]
                                []
                            )
                            (ConstantInt
                                0
                                ()
                            )
                            Load
                        )
                        (Name
                            ftype
                            Load
                        )]
                        []
                    )
                )
                (Lambda
                    ([]
                    [(v
                    ()
                    ())]
                    []
                    []
                    []
                    []
                    [])
                    (BinOp
                        (Subscript
                            (Name
                                params
                                Load
                            )
                            (ConstantStr
                                "fmt"
                                ()
                            )
                            Load
                        )
                        Mod
                        (Call
                            (Name
                                array
                                Load
                            )
                            [(Subscript
                                (Call
                                    (Name
                                        _fr0
                                        Load
                                    )
                                    [(Name
                                        v
                                        Load
                                    )]
                                    []
                                )
                                (ConstantInt
                                    0
                                    ()
                                )
                                Load
                            )
                            (Name
                                ftype
                                Load
                            )]
                            []
                        )
                    )
                )
                (Subscript
                    (Name
                        params
                        Load
                    )
                    (ConstantStr
                        "title"
                        ()
                    )
                    Load
                )]
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
                assert_raises
                Load
            )
            [(Name
                IndexError
                Load
            )
            (Lambda
                ([]
                []
                []
                []
                []
                []
                [])
                (Subscript
                    (Name
                        a
                        Load
                    )
                    (Slice
                        ()
                        (ConstantInt
                            4
                            ()
                        )
                        ()
                    )
                    Load
                )
            )]
            []
        )
    )
    (Expr
        (Call
            (Name
                assert_equal
                Load
            )
            [(Call
                (Attribute
                    (Name
                        np
                        Load
                    )
                    array2string
                    Load
                )
                [(Name
                    x
                    Load
                )]
                [(formatter
                (Dict
                    [(ConstantStr
                        "int"
                        ()
                    )]
                    [(Lambda
                        ([]
                        [(x
                        ()
                        ())]
                        []
                        []
                        []
                        []
                        [])
                        (Call
                            (Name
                                oct
                                Load
                            )
                            [(Name
                                x
                                Load
                            )]
                            []
                        )
                    )]
                ))]
            )
            (Name
                x_oct
                Load
            )]
            []
        )
    )
    (Expr
        (Call
            (Name
                assert_equal
                Load
            )
            [(Call
                (Attribute
                    (Name
                        np
                        Load
                    )
                    array2string
                    Load
                )
                [(Name
                    a
                    Load
                )]
                [(separator
                (ConstantStr
                    ", "
                    ()
                ))
                (formatter
                (Dict
                    [(ConstantStr
                        "datetime"
                        ()
                    )]
                    [(Lambda
                        ([]
                        [(x
                        ()
                        ())]
                        []
                        []
                        []
                        []
                        [])
                        (BinOp
                            (ConstantStr
                                "'%s'"
                                ()
                            )
                            Mod
                            (Call
                                (Attribute
                                    (Name
                                        np
                                        Load
                                    )
                                    datetime_as_string
                                    Load
                                )
                                [(Name
                                    x
                                    Load
                                )]
                                [(timezone
                                (ConstantStr
                                    "UTC"
                                    ()
                                ))]
                            )
                        )
                    )]
                ))]
            )
            (ConstantStr
                "['2011-03-:16T1355Z', '1920-01-:01T0312Z']"
                ()
            )]
            []
        )
    )
    (Expr
        (Call
            (Name
                assert_raises_fpe
                Load
            )
            [(ConstantStr
                "underflow"
                ()
            )
            (Lambda
                ([]
                [(a
                ()
                ())
                (b
                ()
                ())]
                []
                []
                []
                []
                [])
                (BinOp
                    (Name
                        a
                        Load
                    )
                    Div
                    (Name
                        b
                        Load
                    )
                )
            )
            (Name
                sy16
                Load
            )
            (Name
                by16
                Load
            )]
            []
        )
    )
    (Assign
        [(Name
            MyArr
            Store
        )]
        (Call
            (Name
                type
                Load
            )
            [(ConstantStr
                "MyArr"
                ()
            )
            (Dict
                [(Name
                    protocol
                    Load
                )
                (ConstantStr
                    "__float__"
                    ()
                )]
                [(Call
                    (Name
                        getattr
                        Load
                    )
                    [(Name
                        blueprint
                        Load
                    )
                    (Name
                        protocol
                        Load
                    )]
                    []
                )
                (Lambda
                    ([]
                    [(_
                    ()
                    ())]
                    []
                    []
                    []
                    []
                    [])
                    (ConstantFloat
                        0.500000
                        ()
                    )
                )]
            )]
            []
        )
        ()
    )]
    []
)