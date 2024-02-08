(ns masr.resources._pass_loop_vectorise-vec_01-fdf30b1.Function.Function_90555)


#:masr.specs{:term :masr.specs/symbol,
             :asr-symbol-head
             #:masr.specs{:symbol-head :masr.specs/Function,
                          :body
                          [#:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/If,
                                                     :test-expr
                                                     #:masr.specs{:term
                                                                  :masr.specs/expr,
                                                                  :asr-expr-head
                                                                  #:masr.specs{:expr-head
                                                                               :masr.specs/IntegerCompare,
                                                                               :integer-left
                                                                               "Var_89262",
                                                                               :integer-cmpop
                                                                               #:masr.specs{:integer-cmpop-enum
                                                                                            "Eq",
                                                                                            :term
                                                                                            :masr.specs/integer-cmpop},
                                                                               :integer-right
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
                                                     ["Assignment_90297"
                                                      #:masr.specs{:term
                                                                   :masr.specs/stmt,
                                                                   :asr-stmt-head
                                                                   #:masr.specs{:stmt-head
                                                                                :masr.specs/Return}}],
                                                     :orelse []}}
                           "Assignment_90300"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Return}}],
                          :dependencies
                          ["_mod__AT____lpython_overloaded_2___mod"],
                          :deterministic false,
                          :function-signature "FunctionType_90123",
                          :side-effect-free false,
                          :access
                          #:masr.specs{:term :masr.specs/access,
                                       :access-enum "Public"},
                          :return-var? "Var_89277",
                          :param* ["Var_89256" "Var_89259"],
                          :SymbolTable
                          #:masr.specs{:term :masr.specs/SymbolTable,
                                       :symtab-id 73,
                                       :hash-map
                                       {:_lpython_return_variable
                                        "Variable_89844",
                                        :_mod "ExternalSymbol_90339",
                                        :_mod__AT____lpython_overloaded_2___mod
                                        "ExternalSymbol_90342",
                                        :x1 "Variable_89847",
                                        :x2 "Variable_89850"}},
                          :function-name "__lpython_overloaded_1__mod"}}

