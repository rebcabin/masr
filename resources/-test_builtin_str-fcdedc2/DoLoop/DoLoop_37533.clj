(ns masr.resources.-test_builtin_str-fcdedc2.DoLoop.DoLoop_37533)


#:masr.specs{:term :masr.specs/stmt,
             :asr-stmt-head
             #:masr.specs{:stmt-head :masr.specs/DoLoop,
                          :escape-target (),
                          :do-loop-head
                          #:masr.specs{:loop-v "Var_37272",
                                       :loop-start
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
                                       :loop-end
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
                                                                                           :masr.specs/StringLen,
                                                                                           :string-expr
                                                                                           "Var_37275",
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
                                                                              "Sub",
                                                                              :term
                                                                              :masr.specs/integer-binop},
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
                                                                                           1,
                                                                                           :expr-head
                                                                                           :masr.specs/IntegerConstant}},
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
                                       :loop-increment
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
                                                                 :masr.specs/IntegerConstant}}},
                          :body
                          ["Assignment_37506"
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Print,
                                                     :format? (),
                                                     :value*
                                                     ["Var_37287"],
                                                     :separator? (),
                                                     :end? ()}}
                           #:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Assert,
                                                     :test-expr
                                                     #:masr.specs{:term
                                                                  :masr.specs/expr,
                                                                  :asr-expr-head
                                                                  #:masr.specs{:expr-head
                                                                               :masr.specs/StringCompare,
                                                                               :string-left
                                                                               "Var_37290",
                                                                               :string-cmpop
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/string-cmpop,
                                                                                            :string-cmpop-enum
                                                                                            "SEq"},
                                                                               :string-right
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/expr,
                                                                                            :asr-expr-head
                                                                                            #:masr.specs{:expr-head
                                                                                                         :masr.specs/StringItem,
                                                                                                         :string-expr
                                                                                                         "Var_37293",
                                                                                                         :index?
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/expr,
                                                                                                                      :asr-expr-head
                                                                                                                      #:masr.specs{:expr-head
                                                                                                                                   :masr.specs/IntegerBinOp,
                                                                                                                                   :integer-left
                                                                                                                                   "Var_37296",
                                                                                                                                   :integer-binop
                                                                                                                                   #:masr.specs{:integer-binop-enum
                                                                                                                                                "Add",
                                                                                                                                                :term
                                                                                                                                                :masr.specs/integer-binop},
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
                                                                                                                                                             1,
                                                                                                                                                             :expr-head
                                                                                                                                                             :masr.specs/IntegerConstant}},
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
                                                                                                         :character-or-integer-ttype
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/ttype,
                                                                                                                      :asr-ttype-head
                                                                                                                      #:masr.specs{:ttype-head
                                                                                                                                   :masr.specs/Character,
                                                                                                                                   :character-kind
                                                                                                                                   1,
                                                                                                                                   :len
                                                                                                                                   -2,
                                                                                                                                   :disposition
                                                                                                                                   "allocatable",
                                                                                                                                   :len-expr?
                                                                                                                                   (),
                                                                                                                                   :dimension*
                                                                                                                                   []}},
                                                                                                         :character-or-integer-value?
                                                                                                         ()}},
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
                           "Assignment_37509"]}}

