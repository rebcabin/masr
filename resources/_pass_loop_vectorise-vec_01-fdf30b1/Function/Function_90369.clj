(ns masr.resources._pass_loop_vectorise-vec_01-fdf30b1.Function.Function_90369)


#:masr.specs{:term :masr.specs/symbol,
             :asr-symbol-head
             #:masr.specs{:symbol-head :masr.specs/Function,
                          :body
                          ["DoLoop_90351"
                           "DoLoop_90354"
                           "DoLoop_90357"],
                          :dependencies
                          ["vector_copy_f64f64i32__AT__IntrinsicOptimization"],
                          :deterministic false,
                          :function-signature "FunctionType_89937",
                          :side-effect-free false,
                          :access
                          #:masr.specs{:term :masr.specs/access,
                                       :access-enum "Public"},
                          :return-var? (),
                          :param* [],
                          :SymbolTable
                          #:masr.specs{:term :masr.specs/SymbolTable,
                                       :symtab-id 185,
                                       :hash-map
                                       {:a "Variable_89433",
                                        :b "Variable_89436",
                                        :i "Variable_89439",
                                        :vector_copy_f64f64i32__AT__IntrinsicOptimization
                                        #:masr.specs{:term
                                                     :masr.specs/symbol,
                                                     :asr-symbol-head
                                                     #:masr.specs{:symbol-head
                                                                  :masr.specs/Function,
                                                                  :body
                                                                  ["Assignment_90162"
                                                                   #:masr.specs{:term
                                                                                :masr.specs/stmt,
                                                                                :asr-stmt-head
                                                                                #:masr.specs{:stmt-head
                                                                                             :masr.specs/WhileLoop,
                                                                                             :escape-target
                                                                                             (),
                                                                                             :test-expr
                                                                                             #:masr.specs{:term
                                                                                                          :masr.specs/expr,
                                                                                                          :asr-expr-head
                                                                                                          #:masr.specs{:expr-head
                                                                                                                       :masr.specs/IntegerCompare,
                                                                                                                       :integer-left
                                                                                                                       #:masr.specs{:term
                                                                                                                                    :masr.specs/expr,
                                                                                                                                    :asr-expr-head
                                                                                                                                    #:masr.specs{:expr-head
                                                                                                                                                 :masr.specs/IntegerBinOp,
                                                                                                                                                 :integer-left
                                                                                                                                                 "Var_88542",
                                                                                                                                                 :integer-binop
                                                                                                                                                 #:masr.specs{:integer-binop-enum
                                                                                                                                                              "Add",
                                                                                                                                                              :term
                                                                                                                                                              :masr.specs/integer-binop},
                                                                                                                                                 :integer-right
                                                                                                                                                 "Var_88545",
                                                                                                                                                 :Integer
                                                                                                                                                 #:masr.specs{:term
                                                                                                                                                              :masr.specs/ttype,
                                                                                                                                                              :asr-ttype-head
                                                                                                                                                              #:masr.specs{:ttype-head
                                                                                                                                                                           :masr.specs/Integer,
                                                                                                                                                                           :integer-kind
                                                                                                                                                                           4,
                                                                                                                                                                           :dimension*
                                                                                                                                                                           []}},
                                                                                                                                                 :integer-value?
                                                                                                                                                 ()}},
                                                                                                                       :integer-cmpop
                                                                                                                       #:masr.specs{:integer-cmpop-enum
                                                                                                                                    "Lt",
                                                                                                                                    :term
                                                                                                                                    :masr.specs/integer-cmpop},
                                                                                                                       :integer-right
                                                                                                                       "Var_88548",
                                                                                                                       :Logical
                                                                                                                       #:masr.specs{:term
                                                                                                                                    :masr.specs/ttype,
                                                                                                                                    :asr-ttype-head
                                                                                                                                    #:masr.specs{:logical-kind
                                                                                                                                                 4,
                                                                                                                                                 :ttype-head
                                                                                                                                                 :masr.specs/Logical,
                                                                                                                                                 :dimension*
                                                                                                                                                 []}},
                                                                                                                       :logical-value?
                                                                                                                       ()}},
                                                                                             :body
                                                                                             ["Assignment_90165"
                                                                                              "Assignment_90168"]}}],
                                                                  :dependencies
                                                                  [],
                                                                  :deterministic
                                                                  false,
                                                                  :function-signature
                                                                  "FunctionType_89934",
                                                                  :side-effect-free
                                                                  false,
                                                                  :access
                                                                  #:masr.specs{:term
                                                                               :masr.specs/access,
                                                                               :access-enum
                                                                               "Public"},
                                                                  :return-var?
                                                                  (),
                                                                  :param*
                                                                  ["Var_88515"
                                                                   "Var_88518"
                                                                   "Var_88521"
                                                                   "Var_88524"
                                                                   "Var_88527"
                                                                   "Var_88530"],
                                                                  :SymbolTable
                                                                  #:masr.specs{:term
                                                                               :masr.specs/SymbolTable,
                                                                               :symtab-id
                                                                               192,
                                                                               :hash-map
                                                                               {:__1_k
                                                                                "Variable_89442",
                                                                                :arg0
                                                                                "Variable_89445",
                                                                                :arg1
                                                                                "Variable_89448",
                                                                                :arg2
                                                                                "Variable_89451",
                                                                                :arg3
                                                                                "Variable_89454",
                                                                                :arg4
                                                                                "Variable_89457",
                                                                                :arg5
                                                                                "Variable_89460"}},
                                                                  :function-name
                                                                  "vector_copy_f64f64i32__AT__IntrinsicOptimization"}}}},
                          :function-name "loop_vec"}}

