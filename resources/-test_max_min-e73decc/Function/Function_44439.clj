(ns masr.resources.-test_max_min-e73decc.Function.Function_44439)


#:masr.specs{:term :masr.specs/symbol,
             :asr-symbol-head
             #:masr.specs{:symbol-head :masr.specs/Function,
                          :body
                          ["Assignment_44352"
                           "Assignment_44355"
                           "Assignment_44358"
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
                                                                                                                      "min__AT____lpython_overloaded_2__min",
                                                                                                                      :symtab-id
                                                                                                                      5},
                                                                                                         :orig-symref
                                                                                                         #:masr.specs{:identifier
                                                                                                                      "min",
                                                                                                                      :symtab-id
                                                                                                                      5},
                                                                                                         :call-args
                                                                                                         [["Var_44229"]
                                                                                                          ["Var_44232"]
                                                                                                          ["Var_44235"]],
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
                                                                               "Var_44238",
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
                                                                                                                      "min__AT____lpython_overloaded_3__min",
                                                                                                                      :symtab-id
                                                                                                                      5},
                                                                                                         :orig-symref
                                                                                                         #:masr.specs{:identifier
                                                                                                                      "min",
                                                                                                                      :symtab-id
                                                                                                                      5},
                                                                                                         :call-args
                                                                                                         [["Var_44241"]
                                                                                                          ["Var_44244"]],
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
                                                                               "Var_44247",
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
                          ["min__AT____lpython_overloaded_2__min"
                           "min__AT____lpython_overloaded_3__min"],
                          :deterministic false,
                          :function-signature "FunctionType_44328",
                          :side-effect-free false,
                          :access
                          #:masr.specs{:term :masr.specs/access,
                                       :access-enum "Public"},
                          :return-var? (),
                          :param* [],
                          :SymbolTable
                          #:masr.specs{:term :masr.specs/SymbolTable,
                                       :symtab-id 5,
                                       :hash-map
                                       {:d "Variable_44298",
                                        :e "Variable_44301",
                                        :f "Variable_44304",
                                        :min "ExternalSymbol_44388",
                                        :min__AT____lpython_overloaded_2__min
                                        "ExternalSymbol_44391",
                                        :min__AT____lpython_overloaded_3__min
                                        "ExternalSymbol_44394"}},
                          :function-name "test_min_float"}}

