(TranslationUnit
 (SymbolTable
  1 {:_global_symbols
     (Module
      (SymbolTable
       4 {:test_boolOp
          (Function
           (SymbolTable
            2 {:a
               (Variable
                2 a [] Local
                () () Default (Logical 4 [])
                Source Public Required false),
               :b
               (Variable
                2 b [] Local
                () () Default (Logical 4 [])
                Source Public Required false)})
           test_boolOp
           (FunctionType
            [] () Source
            Implementation () false
            false false false
            false [] [] false)
           [] []
           [(= (Var 2 a)
               (LogicalConstant false (Logical 4 []))
               ())
            (= (Var 2 b)
               (LogicalConstant true (Logical 4 []))
               ())
            (= (Var 2 a)
               (LogicalBinOp
                (Var 2 a)
                And
                (Var 2 b)
                (Logical 4 []) ()) ())
            (= (Var 2 b)
               (LogicalBinOp
                (Var 2 a)
                Or
                (LogicalConstant true (Logical 4 []))
                (Logical 4 []) ()) ())
            (= (Var 2 a)
               (LogicalBinOp
                (Var 2 a)
                Or
                (Var 2 b)
                (Logical 4 []) ()) ())
            (= (Var 2 a)
               (LogicalBinOp
                (Var 2 a)
                And
                (LogicalCompare
                 (Var 2 b)
                 Eq
                 (Var 2 b)
                 (Logical 4 []) ())
                (Logical 4 []) ()) ())
            (= (Var 2 a)
               (LogicalBinOp
                (Var 2 a)
                And
                (LogicalCompare
                 (Var 2 b)
                 NotEq
                 (Var 2 b)
                 0xBADBEEF ())
                (Logical 4 []) ()) ())
            (= (Var 2 a)
               (LogicalBinOp
                (Var 2 b)
                Or
                (Var 2 b)
                (Logical 4 []) ()) ())]
           () Public false false)})
      _global_symbols
      [] false false),
     :main_program
     (Program
      (SymbolTable 3 {})
      main_program [] [])}) [])
