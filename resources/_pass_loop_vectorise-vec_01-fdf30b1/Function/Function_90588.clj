(ns masr.resources._pass_loop_vectorise-vec_01-fdf30b1.Function.Function_90588)


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
                                                                               "Var_89403",
                                                                               :integer-cmpop
                                                                               #:masr.specs{:integer-cmpop-enum
                                                                                            "Eq",
                                                                                            :term
                                                                                            :masr.specs/integer-cmpop},
                                                                               :integer-right
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
                                                                                                                      "IntegerToInteger"},
                                                                                                         :ttype
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/ttype,
                                                                                                                      :asr-ttype-head
                                                                                                                      #:masr.specs{:ttype-head
                                                                                                                                   :masr.specs/Integer,
                                                                                                                                   :integer-kind
                                                                                                                                   8,
                                                                                                                                   :dimension*
                                                                                                                                   []}},
                                                                                                         :value?
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
                                                                                                                                                             8,
                                                                                                                                                             :dimension*
                                                                                                                                                             []}},
                                                                                                                                   :int
                                                                                                                                   0,
                                                                                                                                   :expr-head
                                                                                                                                   :masr.specs/IntegerConstant}}}},
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
                                                     ["Assignment_90327"
                                                      #:masr.specs{:term
                                                                   :masr.specs/stmt,
                                                                   :asr-stmt-head
                                                                   #:masr.specs{:stmt-head
                                                                                :masr.specs/Return}}],
                                                     :orelse []}}
                           "Assignment_90330"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Return}}],
                          :dependencies
                          ["_mod__AT____lpython_overloaded_4___mod"],
                          :deterministic false,
                          :function-signature "FunctionType_90156",
                          :side-effect-free false,
                          :access
                          #:masr.specs{:term :masr.specs/access,
                                       :access-enum "Public"},
                          :return-var? "Var_89418",
                          :param* ["Var_89397" "Var_89400"],
                          :SymbolTable
                          #:masr.specs{:term :masr.specs/SymbolTable,
                                       :symtab-id 72,
                                       :hash-map
                                       {:_lpython_return_variable
                                        "Variable_89916",
                                        :_mod "ExternalSymbol_90345",
                                        :_mod__AT____lpython_overloaded_4___mod
                                        "ExternalSymbol_90348",
                                        :x1 "Variable_89919",
                                        :x2 "Variable_89922"}},
                          :function-name "__lpython_overloaded_0__mod"}}

