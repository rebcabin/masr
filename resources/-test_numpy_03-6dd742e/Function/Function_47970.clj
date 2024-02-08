(ns masr.resources.-test_numpy_03-6dd742e.Function.Function_47970)


#:masr.specs{:term :masr.specs/symbol,
             :asr-symbol-head
             #:masr.specs{:symbol-head :masr.specs/Function,
                          :body
                          ["Assignment_47691"
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
                                                                                                         "Var_46203",
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
                                                                                                                                                             4,
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
                                                                                                                                                                                       4,
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
                                                                                                         "Var_46206",
                                                                                                         :real-cmpop
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/real-cmpop,
                                                                                                                      :real-cmpop-enum
                                                                                                                      "REq"},
                                                                                                         :real-right
                                                                                                         "Var_46209",
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
                                                     ["Assignment_47694"
                                                      #:masr.specs{:term
                                                                   :masr.specs/stmt,
                                                                   :asr-stmt-head
                                                                   #:masr.specs{:stmt-head
                                                                                :masr.specs/Return}}],
                                                     :orelse []}}
                           "Assignment_47697"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Return}}],
                          :dependencies [],
                          :deterministic false,
                          :function-signature "FunctionType_47418",
                          :side-effect-free false,
                          :access
                          #:masr.specs{:term :masr.specs/access,
                                       :access-enum "Public"},
                          :return-var? "Var_46224",
                          :param* ["Var_46194"],
                          :SymbolTable
                          #:masr.specs{:term :masr.specs/SymbolTable,
                                       :symtab-id 75,
                                       :hash-map
                                       {:_lpython_return_variable
                                        "Variable_47019",
                                        :resultf "Variable_47022",
                                        :x "Variable_47025"}},
                          :function-name
                          "__lpython_overloaded_1__floor"}}

