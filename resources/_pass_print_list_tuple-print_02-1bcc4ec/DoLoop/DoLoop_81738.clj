(ns masr.resources._pass_print_list_tuple-print_02-1bcc4ec.DoLoop.DoLoop_81738)


#:masr.specs{:term :masr.specs/stmt,
             :asr-stmt-head
             #:masr.specs{:stmt-head :masr.specs/DoLoop,
                          :escape-target (),
                          :do-loop-head
                          #:masr.specs{:loop-v "Var_80655",
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
                                                                                           :masr.specs/ListLen,
                                                                                           :list-expr
                                                                                           "Var_80658",
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
                          [#:masr.specs{:term :masr.specs/stmt,
                                        :asr-stmt-head
                                        #:masr.specs{:stmt-head
                                                     :masr.specs/Print,
                                                     :format? (),
                                                     :value*
                                                     [#:masr.specs{:term
                                                                   :masr.specs/expr,
                                                                   :asr-expr-head
                                                                   #:masr.specs{:expr-head
                                                                                :masr.specs/ListItem,
                                                                                :list-expr
                                                                                "Var_80661",
                                                                                :index
                                                                                "Var_80664",
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
                                                                                :value?
                                                                                ()}}],
                                                     :separator?
                                                     #:masr.specs{:term
                                                                  :masr.specs/expr,
                                                                  :asr-expr-head
                                                                  #:masr.specs{:expr-head
                                                                               :masr.specs/StringConstant,
                                                                               :string
                                                                               "",
                                                                               :Character
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/ttype,
                                                                                            :asr-ttype-head
                                                                                            #:masr.specs{:ttype-head
                                                                                                         :masr.specs/Character,
                                                                                                         :character-kind
                                                                                                         1,
                                                                                                         :len
                                                                                                         0,
                                                                                                         :disposition
                                                                                                         "compile-time-length",
                                                                                                         :len-expr?
                                                                                                         (),
                                                                                                         :dimension*
                                                                                                         []}}}},
                                                     :end?
                                                     #:masr.specs{:term
                                                                  :masr.specs/expr,
                                                                  :asr-expr-head
                                                                  #:masr.specs{:expr-head
                                                                               :masr.specs/StringConstant,
                                                                               :string
                                                                               "",
                                                                               :Character
                                                                               #:masr.specs{:term
                                                                                            :masr.specs/ttype,
                                                                                            :asr-ttype-head
                                                                                            #:masr.specs{:ttype-head
                                                                                                         :masr.specs/Character,
                                                                                                         :character-kind
                                                                                                         1,
                                                                                                         :len
                                                                                                         0,
                                                                                                         :disposition
                                                                                                         "compile-time-length",
                                                                                                         :len-expr?
                                                                                                         (),
                                                                                                         :dimension*
                                                                                                         []}}}}}}
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
                                                                               "Var_80667",
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
                                                                                                         :masr.specs/IntegerBinOp,
                                                                                                         :integer-left
                                                                                                         #:masr.specs{:term
                                                                                                                      :masr.specs/expr,
                                                                                                                      :asr-expr-head
                                                                                                                      #:masr.specs{:expr-head
                                                                                                                                   :masr.specs/ListLen,
                                                                                                                                   :list-expr
                                                                                                                                   "Var_80670",
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
                                                     [#:masr.specs{:term
                                                                   :masr.specs/stmt,
                                                                   :asr-stmt-head
                                                                   #:masr.specs{:stmt-head
                                                                                :masr.specs/Print,
                                                                                :format?
                                                                                (),
                                                                                :value*
                                                                                [#:masr.specs{:term
                                                                                              :masr.specs/expr,
                                                                                              :asr-expr-head
                                                                                              #:masr.specs{:expr-head
                                                                                                           :masr.specs/StringConstant,
                                                                                                           :string
                                                                                                           ", ",
                                                                                                           :Character
                                                                                                           #:masr.specs{:term
                                                                                                                        :masr.specs/ttype,
                                                                                                                        :asr-ttype-head
                                                                                                                        #:masr.specs{:ttype-head
                                                                                                                                     :masr.specs/Character,
                                                                                                                                     :character-kind
                                                                                                                                     1,
                                                                                                                                     :len
                                                                                                                                     2,
                                                                                                                                     :disposition
                                                                                                                                     "compile-time-length",
                                                                                                                                     :len-expr?
                                                                                                                                     (),
                                                                                                                                     :dimension*
                                                                                                                                     []}}}}],
                                                                                :separator?
                                                                                #:masr.specs{:term
                                                                                             :masr.specs/expr,
                                                                                             :asr-expr-head
                                                                                             #:masr.specs{:expr-head
                                                                                                          :masr.specs/StringConstant,
                                                                                                          :string
                                                                                                          "",
                                                                                                          :Character
                                                                                                          #:masr.specs{:term
                                                                                                                       :masr.specs/ttype,
                                                                                                                       :asr-ttype-head
                                                                                                                       #:masr.specs{:ttype-head
                                                                                                                                    :masr.specs/Character,
                                                                                                                                    :character-kind
                                                                                                                                    1,
                                                                                                                                    :len
                                                                                                                                    0,
                                                                                                                                    :disposition
                                                                                                                                    "compile-time-length",
                                                                                                                                    :len-expr?
                                                                                                                                    (),
                                                                                                                                    :dimension*
                                                                                                                                    []}}}},
                                                                                :end?
                                                                                #:masr.specs{:term
                                                                                             :masr.specs/expr,
                                                                                             :asr-expr-head
                                                                                             #:masr.specs{:expr-head
                                                                                                          :masr.specs/StringConstant,
                                                                                                          :string
                                                                                                          "",
                                                                                                          :Character
                                                                                                          #:masr.specs{:term
                                                                                                                       :masr.specs/ttype,
                                                                                                                       :asr-ttype-head
                                                                                                                       #:masr.specs{:ttype-head
                                                                                                                                    :masr.specs/Character,
                                                                                                                                    :character-kind
                                                                                                                                    1,
                                                                                                                                    :len
                                                                                                                                    0,
                                                                                                                                    :disposition
                                                                                                                                    "compile-time-length",
                                                                                                                                    :len-expr?
                                                                                                                                    (),
                                                                                                                                    :dimension*
                                                                                                                                    []}}}}}}],
                                                     :orelse []}}]}}

