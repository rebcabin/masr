(ns masr.resources.-expr_14-6023c49.Assignment.Assignment_23874)


#:masr.specs{:term :masr.specs/stmt,
             :asr-stmt-head
             #:masr.specs{:stmt-head :masr.specs/Assignment,
                          :lvalue "Var_23742",
                          :rvalue
                          #:masr.specs{:term :masr.specs/expr,
                                       :asr-expr-head
                                       #:masr.specs{:expr-head
                                                    :masr.specs/ComplexBinOp,
                                                    :complex-left
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
                                                                                                        5,
                                                                                                        :expr-head
                                                                                                        :masr.specs/IntegerConstant}},
                                                                              :cast-kind
                                                                              #:masr.specs{:term
                                                                                           :masr.specs/cast-kind,
                                                                                           :cast-kind-enum
                                                                                           "IntegerToComplex"},
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
                                                                                                        5.0,
                                                                                                        :imaginary-part
                                                                                                        0.0,
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
                                                    :complex-binop
                                                    #:masr.specs{:term
                                                                 :masr.specs/complex-binop,
                                                                 :complex-binop-enum
                                                                 "CAdd"},
                                                    :complex-right
                                                    #:masr.specs{:term
                                                                 :masr.specs/expr,
                                                                 :asr-expr-head
                                                                 #:masr.specs{:expr-head
                                                                              :masr.specs/Cast,
                                                                              :arg
                                                                              #:masr.specs{:term
                                                                                           :masr.specs/expr,
                                                                                           :asr-expr-head
                                                                                           #:masr.specs{:expr-head
                                                                                                        :masr.specs/ComplexConstant,
                                                                                                        :real-part
                                                                                                        0.0,
                                                                                                        :imaginary-part
                                                                                                        6.0,
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
                                                                                                        0.0,
                                                                                                        :imaginary-part
                                                                                                        6.0,
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
                                                    :Complex
                                                    #:masr.specs{:term
                                                                 :masr.specs/ttype,
                                                                 :asr-ttype-head
                                                                 #:masr.specs{:complex-kind
                                                                              4,
                                                                              :ttype-head
                                                                              :masr.specs/Complex,
                                                                              :dimension*
                                                                              []}},
                                                    :complex-value?
                                                    #:masr.specs{:term
                                                                 :masr.specs/expr,
                                                                 :asr-expr-head
                                                                 #:masr.specs{:expr-head
                                                                              :masr.specs/ComplexConstant,
                                                                              :real-part
                                                                              5.0,
                                                                              :imaginary-part
                                                                              6.0,
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

