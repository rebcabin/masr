(ns masr.resources.-test_numpy_03-6dd742e.Assignment.Assignment_47625)


#:masr.specs{:term :masr.specs/stmt,
             :asr-stmt-head
             #:masr.specs{:stmt-head :masr.specs/Assignment,
                          :lvalue
                          #:masr.specs{:term :masr.specs/expr,
                                       :asr-expr-head
                                       #:masr.specs{:expr-head
                                                    :masr.specs/ArrayItem,
                                                    :array-expr
                                                    "Var_45846",
                                                    :array-index*
                                                    [#:masr.specs{:array-index-start?
                                                                  (),
                                                                  :array-index-end?
                                                                  "Var_45849",
                                                                  :array-index-increment?
                                                                  ()}
                                                     #:masr.specs{:array-index-start?
                                                                  (),
                                                                  :array-index-end?
                                                                  "Var_45852",
                                                                  :array-index-increment?
                                                                  ()}
                                                     #:masr.specs{:array-index-start?
                                                                  (),
                                                                  :array-index-end?
                                                                  "Var_45855",
                                                                  :array-index-increment?
                                                                  ()}],
                                                    :ttype
                                                    #:masr.specs{:term
                                                                 :masr.specs/ttype,
                                                                 :asr-ttype-head
                                                                 #:masr.specs{:ttype-head
                                                                              :masr.specs/Real,
                                                                              :real-kind
                                                                              8,
                                                                              :dimension*
                                                                              []}},
                                                    :array-storage
                                                    #:masr.specs{:term
                                                                 :masr.specs/arraystorage,
                                                                 :arraystorage-enum
                                                                 "RowMajor"},
                                                    :expr? ()}},
                          :rvalue
                          #:masr.specs{:term :masr.specs/expr,
                                       :asr-expr-head
                                       #:masr.specs{:expr-head
                                                    :masr.specs/RealBinOp,
                                                    :real-left
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
                                                                                                        :masr.specs/IntegerBinOp,
                                                                                                        :integer-left
                                                                                                        #:masr.specs{:term
                                                                                                                     :masr.specs/expr,
                                                                                                                     :asr-expr-head
                                                                                                                     #:masr.specs{:expr-head
                                                                                                                                  :masr.specs/IntegerBinOp,
                                                                                                                                  :integer-left
                                                                                                                                  "Var_45858",
                                                                                                                                  :integer-binop
                                                                                                                                  #:masr.specs{:integer-binop-enum
                                                                                                                                               "Add",
                                                                                                                                               :term
                                                                                                                                               :masr.specs/integer-binop},
                                                                                                                                  :integer-right
                                                                                                                                  "Var_45861",
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
                                                                                                                                  ()}},
                                                                                                        :integer-binop
                                                                                                        #:masr.specs{:integer-binop-enum
                                                                                                                     "Add",
                                                                                                                     :term
                                                                                                                     :masr.specs/integer-binop},
                                                                                                        :integer-right
                                                                                                        "Var_45864",
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
                                                                                                        ()}},
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
                                                                                                        8,
                                                                                                        :dimension*
                                                                                                        []}},
                                                                              :value?
                                                                              ()}},
                                                    :real-binop
                                                    #:masr.specs{:term
                                                                 :masr.specs/real-binop,
                                                                 :real-binop-enum
                                                                 "RAdd"},
                                                    :real-right
                                                    #:masr.specs{:term
                                                                 :masr.specs/expr,
                                                                 :asr-expr-head
                                                                 #:masr.specs{:float
                                                                              0.5,
                                                                              :expr-head
                                                                              :masr.specs/RealConstant,
                                                                              :Real
                                                                              #:masr.specs{:term
                                                                                           :masr.specs/ttype,
                                                                                           :asr-ttype-head
                                                                                           #:masr.specs{:ttype-head
                                                                                                        :masr.specs/Real,
                                                                                                        :real-kind
                                                                                                        8,
                                                                                                        :dimension*
                                                                                                        []}}}},
                                                    :Real
                                                    #:masr.specs{:term
                                                                 :masr.specs/ttype,
                                                                 :asr-ttype-head
                                                                 #:masr.specs{:ttype-head
                                                                              :masr.specs/Real,
                                                                              :real-kind
                                                                              8,
                                                                              :dimension*
                                                                              []}},
                                                    :real-value? ()}},
                          :overloaded ()}}

