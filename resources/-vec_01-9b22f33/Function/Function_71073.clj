(ns masr.resources.-vec_01-9b22f33.Function.Function_71073)


#:masr.specs{:term :masr.specs/symbol,
             :asr-symbol-head
             #:masr.specs{:symbol-head :masr.specs/Function,
                          :body
                          ["Assignment_70866"
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
                                                                                                         "Var_69384",
                                                                                                         :real-cmpop
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/real-cmpop,
                                                                                                                      :real-cmpop-enum
                                                                                                                      "RLtE"},
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
                                                                                                         "Var_69387",
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
                                                                                                                                   "Var_69390",
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
                                                     ["Assignment_70869"
                                                      #:masr.specs{:term
                                                                   :masr.specs/stmt,
                                                                   :asr-stmt-head
                                                                   #:masr.specs{:stmt-head
                                                                                :masr.specs/Return}}],
                                                     :orelse []}}
                           "Assignment_70872"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Return}}],
                          :dependencies [],
                          :deterministic false,
                          :function-signature "FunctionType_70647",
                          :side-effect-free false,
                          :access
                          #:masr.specs{:term :masr.specs/access,
                                       :access-enum "Public"},
                          :return-var? "Var_69405",
                          :param* ["Var_69375"],
                          :SymbolTable
                          #:masr.specs{:term :masr.specs/SymbolTable,
                                       :symtab-id 76,
                                       :hash-map
                                       {:_lpython_return_variable
                                        "Variable_70194",
                                        :result "Variable_70197",
                                        :x "Variable_70200"}},
                          :function-name
                          "__lpython_overloaded_0__ceil"}}

