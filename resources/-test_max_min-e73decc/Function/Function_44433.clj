(ns masr.resources.-test_max_min-e73decc.Function.Function_44433)


#:masr.specs{:term :masr.specs/symbol,
             :asr-symbol-head
             #:masr.specs{:symbol-head :masr.specs/Function,
                          :body
                          ["Assignment_44334"
                           "Assignment_44337"
                           "Assignment_44340"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Assert,
                                                     :test-expr
                                                     #:masr.specs{:term
                                                                  :masr.specs/expr,
                                                                  :asr-expr-head
                                                                  #:masr.specs{:expr-head
                                                                               :masr.specs/RealCompare,
                                                                               :real-left
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/expr,
                                                                                            :asr-expr-head
                                                                                            #:masr.specs{:expr-head
                                                                                                         :masr.specs/FunctionCall,
                                                                                                         :symbol-ref
                                                                                                         #:masr.specs{:identifier
                                                                                                                      "max__AT____lpython_overloaded_2__max",
                                                                                                                      :symtab-id
                                                                                                                      3},
                                                                                                         :orig-symref
                                                                                                         #:masr.specs{:identifier
                                                                                                                      "max",
                                                                                                                      :symtab-id
                                                                                                                      3},
                                                                                                         :call-args
                                                                                                         [["Var_44169"]
                                                                                                          ["Var_44172"]
                                                                                                          ["Var_44175"]],
                                                                                                         :return-type
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
                                                                                                         (),
                                                                                                         :dt?
                                                                                                         ()}},
                                                                               :real-cmpop
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/real-cmpop,
                                                                                            :real-cmpop-enum
                                                                                            "REq"},
                                                                               :real-right
                                                                               "Var_44178",
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
                                                     :message? ()}}
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Assert,
                                                     :test-expr
                                                     #:masr.specs{:term
                                                                  :masr.specs/expr,
                                                                  :asr-expr-head
                                                                  #:masr.specs{:expr-head
                                                                               :masr.specs/RealCompare,
                                                                               :real-left
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/expr,
                                                                                            :asr-expr-head
                                                                                            #:masr.specs{:expr-head
                                                                                                         :masr.specs/FunctionCall,
                                                                                                         :symbol-ref
                                                                                                         #:masr.specs{:identifier
                                                                                                                      "max__AT____lpython_overloaded_3__max",
                                                                                                                      :symtab-id
                                                                                                                      3},
                                                                                                         :orig-symref
                                                                                                         #:masr.specs{:identifier
                                                                                                                      "max",
                                                                                                                      :symtab-id
                                                                                                                      3},
                                                                                                         :call-args
                                                                                                         [["Var_44181"]
                                                                                                          ["Var_44184"]],
                                                                                                         :return-type
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
                                                                                                         (),
                                                                                                         :dt?
                                                                                                         ()}},
                                                                               :real-cmpop
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/real-cmpop,
                                                                                            :real-cmpop-enum
                                                                                            "REq"},
                                                                               :real-right
                                                                               "Var_44187",
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
                                                     :message? ()}}],
                          :dependencies
                          ["max__AT____lpython_overloaded_2__max"
                           "max__AT____lpython_overloaded_3__max"],
                          :deterministic false,
                          :function-signature "FunctionType_44322",
                          :side-effect-free false,
                          :access
                          #:masr.specs{:term :masr.specs/access,
                                       :access-enum "Public"},
                          :return-var? (),
                          :param* [],
                          :SymbolTable
                          #:masr.specs{:term :masr.specs/SymbolTable,
                                       :symtab-id 3,
                                       :hash-map
                                       {:d "Variable_44280",
                                        :e "Variable_44283",
                                        :f "Variable_44286",
                                        :max "ExternalSymbol_44370",
                                        :max__AT____lpython_overloaded_2__max
                                        "ExternalSymbol_44373",
                                        :max__AT____lpython_overloaded_3__max
                                        "ExternalSymbol_44376"}},
                          :function-name "test_max_float"}}

