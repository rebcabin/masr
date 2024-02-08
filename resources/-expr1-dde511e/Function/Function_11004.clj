(ns masr.resources.-expr1-dde511e.Function.Function_11004)


#:masr.specs{:term :masr.specs/symbol,
             :asr-symbol-head
             #:masr.specs{:symbol-head :masr.specs/Function,
                          :body
                          ["Assignment_10995"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/If,
                                                     :test-expr
                                                     #:masr.specs{:term
                                                                  :masr.specs/expr,
                                                                  :asr-expr-head
                                                                  #:masr.specs{:expr-head
                                                                               :masr.specs/NamedExpr,
                                                                               :target
                                                                               "Var_10971",
                                                                               :value
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/expr,
                                                                                            :asr-expr-head
                                                                                            #:masr.specs{:expr-head
                                                                                                         :masr.specs/StringOrd,
                                                                                                         :string-expr
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/expr,
                                                                                                                      :asr-expr-head
                                                                                                                      #:masr.specs{:expr-head
                                                                                                                                   :masr.specs/StringConstant,
                                                                                                                                   :string
                                                                                                                                   "3",
                                                                                                                                   :Character
                                                                                                                                   #:masr.specs{:term
                                                                                                                                                :masr.specs/ttype,
                                                                                                                                                :asr-ttype-head
                                                                                                                                                #:masr.specs{:ttype-head
                                                                                                                                                             :masr.specs/Character,
                                                                                                                                                             :character-kind
                                                                                                                                                             1,
                                                                                                                                                             :len
                                                                                                                                                             1,
                                                                                                                                                             :disposition
                                                                                                                                                             "compile-time-length",
                                                                                                                                                             :len-expr?
                                                                                                                                                             (),
                                                                                                                                                             :dimension*
                                                                                                                                                             []}}}},
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
                                                                                                                                   51,
                                                                                                                                   :expr-head
                                                                                                                                   :masr.specs/IntegerConstant}}}},
                                                                               :ttype
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/ttype,
                                                                                            :asr-ttype-head
                                                                                            #:masr.specs{:ttype-head
                                                                                                         :masr.specs/Integer,
                                                                                                         :integer-kind
                                                                                                         4,
                                                                                                         :dimension*
                                                                                                         []}}}},
                                                     :body
                                                     ["Assignment_10998"],
                                                     :orelse []}}
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/WhileLoop,
                                                     :escape-target (),
                                                     :test-expr
                                                     #:masr.specs{:term
                                                                  :masr.specs/expr,
                                                                  :asr-expr-head
                                                                  #:masr.specs{:expr-head
                                                                               :masr.specs/NamedExpr,
                                                                               :target
                                                                               "Var_10977",
                                                                               :value
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
                                                                                                         1,
                                                                                                         :expr-head
                                                                                                         :masr.specs/IntegerConstant}},
                                                                               :ttype
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/ttype,
                                                                                            :asr-ttype-head
                                                                                            #:masr.specs{:ttype-head
                                                                                                         :masr.specs/Integer,
                                                                                                         :integer-kind
                                                                                                         4,
                                                                                                         :dimension*
                                                                                                         []}}}},
                                                     :body
                                                     ["Assignment_11001"]}}],
                          :dependencies [],
                          :deterministic false,
                          :function-signature "FunctionType_10992",
                          :side-effect-free false,
                          :access
                          #:masr.specs{:term :masr.specs/access,
                                       :access-enum "Public"},
                          :return-var? (),
                          :param* [],
                          :SymbolTable
                          #:masr.specs{:term :masr.specs/SymbolTable,
                                       :symtab-id 2,
                                       :hash-map
                                       {:a "Variable_10983",
                                        :x "Variable_10986",
                                        :y "Variable_10989"}},
                          :function-name "test_namedexpr"}}

