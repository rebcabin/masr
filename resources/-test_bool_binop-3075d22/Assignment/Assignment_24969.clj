(ns masr.resources.-test_bool_binop-3075d22.Assignment.Assignment_24969)


#:masr.specs{:term :masr.specs/stmt,
             :asr-stmt-head
             #:masr.specs{:stmt-head :masr.specs/Assignment,
                          :lvalue "Var_24909",
                          :rvalue
                          #:masr.specs{:term :masr.specs/expr,
                                       :asr-expr-head
                                       #:masr.specs{:expr-head
                                                    :masr.specs/Cast,
                                                    :arg
                                                    #:masr.specs{:term
                                                                 :masr.specs/expr,
                                                                 :asr-expr-head
                                                                 #:masr.specs{:expr-head
                                                                              :masr.specs/FunctionCall,
                                                                              :symbol-ref
                                                                              #:masr.specs{:identifier
                                                                                           "_lpython_floordiv__AT____lpython_overloaded_6___lpython_floordiv",
                                                                                           :symtab-id
                                                                                           2},
                                                                              :orig-symref
                                                                              #:masr.specs{:identifier
                                                                                           "_lpython_floordiv",
                                                                                           :symtab-id
                                                                                           2},
                                                                              :call-args
                                                                              [[#:masr.specs{:term
                                                                                             :masr.specs/expr,
                                                                                             :asr-expr-head
                                                                                             #:masr.specs{:Logical
                                                                                                          #:masr.specs{:term
                                                                                                                       :masr.specs/ttype,
                                                                                                                       :asr-ttype-head
                                                                                                                       #:masr.specs{:logical-kind
                                                                                                                                    4,
                                                                                                                                    :ttype-head
                                                                                                                                    :masr.specs/Logical,
                                                                                                                                    :dimension*
                                                                                                                                    []}},
                                                                                                          :expr-head
                                                                                                          :masr.specs/LogicalConstant,
                                                                                                          :bool
                                                                                                          false}}]
                                                                               [#:masr.specs{:term
                                                                                             :masr.specs/expr,
                                                                                             :asr-expr-head
                                                                                             #:masr.specs{:Logical
                                                                                                          #:masr.specs{:term
                                                                                                                       :masr.specs/ttype,
                                                                                                                       :asr-ttype-head
                                                                                                                       #:masr.specs{:logical-kind
                                                                                                                                    4,
                                                                                                                                    :ttype-head
                                                                                                                                    :masr.specs/Logical,
                                                                                                                                    :dimension*
                                                                                                                                    []}},
                                                                                                          :expr-head
                                                                                                          :masr.specs/LogicalConstant,
                                                                                                          :bool
                                                                                                          true}}]],
                                                                              :return-type
                                                                              #:masr.specs{:term
                                                                                           :masr.specs/ttype,
                                                                                           :asr-ttype-head
                                                                                           #:masr.specs{:logical-kind
                                                                                                        4,
                                                                                                        :ttype-head
                                                                                                        :masr.specs/Logical,
                                                                                                        :dimension*
                                                                                                        []}},
                                                                              :value?
                                                                              #:masr.specs{:term
                                                                                           :masr.specs/expr,
                                                                                           :asr-expr-head
                                                                                           #:masr.specs{:Logical
                                                                                                        #:masr.specs{:term
                                                                                                                     :masr.specs/ttype,
                                                                                                                     :asr-ttype-head
                                                                                                                     #:masr.specs{:logical-kind
                                                                                                                                  1,
                                                                                                                                  :ttype-head
                                                                                                                                  :masr.specs/Logical,
                                                                                                                                  :dimension*
                                                                                                                                  []}},
                                                                                                        :expr-head
                                                                                                        :masr.specs/LogicalConstant,
                                                                                                        :bool
                                                                                                        false}},
                                                                              :dt?
                                                                              ()}},
                                                    :cast-kind
                                                    #:masr.specs{:term
                                                                 :masr.specs/cast-kind,
                                                                 :cast-kind-enum
                                                                 "LogicalToInteger"},
                                                    :ttype
                                                    #:masr.specs{:term
                                                                 :masr.specs/ttype,
                                                                 :asr-ttype-head
                                                                 #:masr.specs{:ttype-head
                                                                              :masr.specs/Integer,
                                                                              :integer-kind
                                                                              4,
                                                                              :dimension*
                                                                              []}},
                                                    :value? ()}},
                          :overloaded ()}}

