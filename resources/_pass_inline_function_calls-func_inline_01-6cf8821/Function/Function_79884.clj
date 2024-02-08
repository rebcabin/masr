(ns masr.resources._pass_inline_function_calls-func_inline_01-6cf8821.Function.Function_79884)


#:masr.specs{:term :masr.specs/symbol,
             :asr-symbol-head
             #:masr.specs{:symbol-head :masr.specs/Function,
                          :body
                          ["Assignment_79851"
                           "Assignment_79854"
                           #:masr.specs{:term :masr.specs/stmt,
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
                                                                               "Var_79788",
                                                                               :integer-cmpop
                                                                               #:masr.specs{:integer-cmpop-enum
                                                                                            "Lt",
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
                                                                                                                                   2,
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
                                                                                                                                   2,
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
                                                     ["Assignment_79857"
                                                      #:masr.specs{:term
                                                                   :masr.specs/stmt,
                                                                   :asr-stmt-head
                                                                   #:masr.specs{:stmt-head
                                                                                :masr.specs/GoTo,
                                                                                :goto-target
                                                                                1,
                                                                                :identifier
                                                                                "__1"}}],
                                                     :orelse []}}
                           "Assignment_79860"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/GoTo,
                                                     :goto-target 1,
                                                     :identifier
                                                     "__1"}}
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/BlockCall,
                                                     :label 1,
                                                     :symbol-ref
                                                     #:masr.specs{:identifier
                                                                  "__TILDE__empty_block",
                                                                  :symtab-id
                                                                  3}}}
                           "Assignment_79863"
                           "Print_79869"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Assert,
                                                     :test-expr
                                                     #:masr.specs{:term
                                                                  :masr.specs/expr,
                                                                  :asr-expr-head
                                                                  #:masr.specs{:expr-head
                                                                               :masr.specs/IntegerCompare,
                                                                               :integer-left
                                                                               "Var_79815",
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
                                                                                                                                   102334155,
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
                                                                                                                                   102334155,
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
                                                     :message? ()}}],
                          :dependencies ["fib"],
                          :deterministic false,
                          :function-signature "FunctionType_79842",
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
                                       {:_lpython_return_variable_fib
                                        "Variable_79824",
                                        :ans "Variable_79827",
                                        :n_fib "Variable_79830",
                                        :x "Variable_79833",
                                        :__TILDE__empty_block
                                        #:masr.specs{:term
                                                     :masr.specs/symbol,
                                                     :asr-symbol-head
                                                     #:masr.specs{:symbol-head
                                                                  :masr.specs/Block,
                                                                  :SymbolTable
                                                                  #:masr.specs{:term
                                                                               :masr.specs/SymbolTable,
                                                                               :symtab-id
                                                                               7,
                                                                               :hash-map
                                                                               {}},
                                                                  :blocknym
                                                                  "__TILDE__empty_block",
                                                                  :body
                                                                  []}}}},
                          :function-name "main"}}

