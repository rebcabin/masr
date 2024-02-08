(ns masr.resources.-test_numpy_04-3376b7a.Function.Function_60183)


#:masr.specs{:term :masr.specs/symbol,
             :asr-symbol-head
             #:masr.specs{:symbol-head :masr.specs/Function,
                          :body
                          ["Assignment_59916"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/If,
                                                     :test-expr
                                                     #:masr.specs{:term
                                                                  :masr.specs/expr,
                                                                  :asr-expr-head
                                                                  #:masr.specs{:expr-head
                                                                               :masr.specs/LogicalBinOp,
                                                                               :logical-left
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/expr,
                                                                                            :asr-expr-head
                                                                                            #:masr.specs{:expr-head
                                                                                                         :masr.specs/RealCompare,
                                                                                                         :real-left
                                                                                                         "Var_58980",
                                                                                                         :real-cmpop
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/real-cmpop,
                                                                                                                      :real-cmpop-enum
                                                                                                                      "RGtE"},
                                                                                                         :real-right
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/expr,
                                                                                                                      :asr-expr-head
                                                                                                                      #:masr.specs{:expr-head
                                                                                                                                   :masr.specs/Cast,
                                                                                                                                   :arg
                                                                                                                                   #:masr.specs{:term
                                                                                                                                                :masr.specs/expr,
                                                                                                                                                :asr-expr-head
                                                                                                                                                #:masr.specs{:Integer
                                                                                                                                                             #:masr.specs{:term
                                                                                                                                                                          :masr.specs/ttype,
                                                                                                                                                                          :asr-ttype-head
                                                                                                                                                                          #:masr.specs{:ttype-head
                                                                                                                                                                                       :masr.specs/Integer,
                                                                                                                                                                                       :integer-kind
                                                                                                                                                                                       4,
                                                                                                                                                                                       :dimension*
                                                                                                                                                                                       []}},
                                                                                                                                                             :int
                                                                                                                                                             0,
                                                                                                                                                             :expr-head
                                                                                                                                                             :masr.specs/IntegerConstant}},
                                                                                                                                   :cast-kind
                                                                                                                                   #:masr.specs{:term
                                                                                                                                                :masr.specs/cast-kind,
                                                                                                                                                :cast-kind-enum
                                                                                                                                                "IntegerToReal"},
                                                                                                                                   :ttype
                                                                                                                                   #:masr.specs{:term
                                                                                                                                                :masr.specs/ttype,
                                                                                                                                                :asr-ttype-head
                                                                                                                                                #:masr.specs{:ttype-head
                                                                                                                                                             :masr.specs/Real,
                                                                                                                                                             :real-kind
                                                                                                                                                             8,
                                                                                                                                                             :dimension*
                                                                                                                                                             []}},
                                                                                                                                   :value?
                                                                                                                                   #:masr.specs{:term
                                                                                                                                                :masr.specs/expr,
                                                                                                                                                :asr-expr-head
                                                                                                                                                #:masr.specs{:float
                                                                                                                                                             0.0,
                                                                                                                                                             :expr-head
                                                                                                                                                             :masr.specs/RealConstant,
                                                                                                                                                             :Real
                                                                                                                                                             #:masr.specs{:term
                                                                                                                                                                          :masr.specs/ttype,
                                                                                                                                                                          :asr-ttype-head
                                                                                                                                                                          #:masr.specs{:ttype-head
                                                                                                                                                                                       :masr.specs/Real,
                                                                                                                                                                                       :real-kind
                                                                                                                                                                                       8,
                                                                                                                                                                                       :dimension*
                                                                                                                                                                                       []}}}}}},
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
                                                                               :logicalbinop
                                                                               #:masr.specs{:logicalbinop-enum
                                                                                            "Or",
                                                                                            :term
                                                                                            :masr.specs/logicalbinop},
                                                                               :logical-right
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/expr,
                                                                                            :asr-expr-head
                                                                                            #:masr.specs{:expr-head
                                                                                                         :masr.specs/RealCompare,
                                                                                                         :real-left
                                                                                                         "Var_58983",
                                                                                                         :real-cmpop
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/real-cmpop,
                                                                                                                      :real-cmpop-enum
                                                                                                                      "REq"},
                                                                                                         :real-right
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/expr,
                                                                                                                      :asr-expr-head
                                                                                                                      #:masr.specs{:expr-head
                                                                                                                                   :masr.specs/Cast,
                                                                                                                                   :arg
                                                                                                                                   "Var_58986",
                                                                                                                                   :cast-kind
                                                                                                                                   #:masr.specs{:term
                                                                                                                                                :masr.specs/cast-kind,
                                                                                                                                                :cast-kind-enum
                                                                                                                                                "IntegerToReal"},
                                                                                                                                   :ttype
                                                                                                                                   #:masr.specs{:term
                                                                                                                                                :masr.specs/ttype,
                                                                                                                                                :asr-ttype-head
                                                                                                                                                #:masr.specs{:ttype-head
                                                                                                                                                             :masr.specs/Real,
                                                                                                                                                             :real-kind
                                                                                                                                                             8,
                                                                                                                                                             :dimension*
                                                                                                                                                             []}},
                                                                                                                                   :value?
                                                                                                                                   ()}},
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
                                                     ["Assignment_59919"
                                                      #:masr.specs{:term
                                                                   :masr.specs/stmt,
                                                                   :asr-stmt-head
                                                                   #:masr.specs{:stmt-head
                                                                                :masr.specs/Return}}],
                                                     :orelse []}}
                           "Assignment_59922"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Return}}],
                          :dependencies [],
                          :deterministic false,
                          :function-signature "FunctionType_59748",
                          :side-effect-free false,
                          :access
                          #:masr.specs{:term :masr.specs/access,
                                       :access-enum "Public"},
                          :return-var? "Var_59001",
                          :param* ["Var_58971"],
                          :SymbolTable
                          #:masr.specs{:term :masr.specs/SymbolTable,
                                       :symtab-id 74,
                                       :hash-map
                                       {:_lpython_return_variable
                                        "Variable_59496",
                                        :result "Variable_59499",
                                        :x "Variable_59502"}},
                          :function-name
                          "__lpython_overloaded_0__floor"}}

