(ns masr.resources.-test_complex_02-6516823.Assignment.Assignment_42627)


#:masr.specs{:term :masr.specs/stmt,
             :asr-stmt-head
             #:masr.specs{:stmt-head :masr.specs/Assignment,
                          :lvalue "Var_42480",
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
                                                                                           "complex__AT____lpython_overloaded_9__complex",
                                                                                           :symtab-id
                                                                                           2},
                                                                              :orig-symref
                                                                              #:masr.specs{:identifier
                                                                                           "complex",
                                                                                           :symtab-id
                                                                                           2},
                                                                              :call-args
                                                                              [[#:masr.specs{:term
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
                                                                                                          3,
                                                                                                          :expr-head
                                                                                                          :masr.specs/IntegerConstant}}]
                                                                               [#:masr.specs{:term
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
                                                                                                          4,
                                                                                                          :expr-head
                                                                                                          :masr.specs/IntegerConstant}}]],
                                                                              :return-type
                                                                              #:masr.specs{:term
                                                                                           :masr.specs/ttype,
                                                                                           :asr-ttype-head
                                                                                           #:masr.specs{:complex-kind
                                                                                                        8,
                                                                                                        :ttype-head
                                                                                                        :masr.specs/Complex,
                                                                                                        :dimension*
                                                                                                        []}},
                                                                              :value?
                                                                              #:masr.specs{:term
                                                                                           :masr.specs/expr,
                                                                                           :asr-expr-head
                                                                                           #:masr.specs{:expr-head
                                                                                                        :masr.specs/ComplexConstant,
                                                                                                        :real-part
                                                                                                        3.0,
                                                                                                        :imaginary-part
                                                                                                        4.0,
                                                                                                        :Complex
                                                                                                        #:masr.specs{:term
                                                                                                                     :masr.specs/ttype,
                                                                                                                     :asr-ttype-head
                                                                                                                     #:masr.specs{:complex-kind
                                                                                                                                  8,
                                                                                                                                  :ttype-head
                                                                                                                                  :masr.specs/Complex,
                                                                                                                                  :dimension*
                                                                                                                                  []}}}},
                                                                              :dt?
                                                                              ()}},
                                                    :cast-kind
                                                    #:masr.specs{:term
                                                                 :masr.specs/cast-kind,
                                                                 :cast-kind-enum
                                                                 "ComplexToComplex"},
                                                    :ttype
                                                    #:masr.specs{:term
                                                                 :masr.specs/ttype,
                                                                 :asr-ttype-head
                                                                 #:masr.specs{:complex-kind
                                                                              4,
                                                                              :ttype-head
                                                                              :masr.specs/Complex,
                                                                              :dimension*
                                                                              []}},
                                                    :value?
                                                    #:masr.specs{:term
                                                                 :masr.specs/expr,
                                                                 :asr-expr-head
                                                                 #:masr.specs{:expr-head
                                                                              :masr.specs/ComplexConstant,
                                                                              :real-part
                                                                              3.0,
                                                                              :imaginary-part
                                                                              4.0,
                                                                              :Complex
                                                                              #:masr.specs{:term
                                                                                           :masr.specs/ttype,
                                                                                           :asr-ttype-head
                                                                                           #:masr.specs{:complex-kind
                                                                                                        4,
                                                                                                        :ttype-head
                                                                                                        :masr.specs/Complex,
                                                                                                        :dimension*
                                                                                                        []}}}}}},
                          :overloaded ()}}
