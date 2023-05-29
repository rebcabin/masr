(ns masr.big-test
  (:use     [masr.core]
            ;; https://groups.google.com/g/clojure/c/i770QaIFiF0 :
            [masr.specs             :as    asr     ])

  (:require [clojure.test           :refer :all    ]
            [clojure.spec.alpha     :as    s       ]
            [clojure.spec.gen.alpha :as    gen     ]
            [clojure.pprint         :refer [pprint]]
            [clojure.set            :as    set     ])

  (:require [blaster.clj-fstring    :refer [f-str ]]
            [camel-snake-kebab.core :as    csk     ])

  (:require [masr.utils             :refer [warnings-banner
                                            dosafely
                                            plnecho
                                            plnecho-file
                                            pprint-file]]
            [masr.simplespecs       :refer [nat
                                            identifier
                                            identifier-set
                                            identifier-list
                                            identifier-suit
                                            ]])
  )


(warnings-banner)


;; ================================================================
;;  ____  _    _   _ ____  ____  _____ ____
;; / ___|| |  | | | |  _ \|  _ \| ____|  _ \
;; \___ \| |  | | | | |_) | |_) |  _| | | | |
;;  ___) | |__| |_| |  _ <|  __/| |___| |_| |
;; |____/|_____\___/|_| \_\_|   |_____|____/


(defn slurp-asr
  [refnym]
  (read-string
   (slurp
    ;;   sometimes _, sometimes -, after asr
    (str "resources/reference/asr"
         refnym
         ".stdout.clj"))))


(defn long-form-asr
  [refnym]
  (do (in-ns 'masr.specs)
      (->> refnym
           slurp-asr
           rewrite-for-legacy
           eval)))


(defmacro test-unit [filenamefrag]
  (let [tstr (str "whole translation unit for " filenamefrag)
        fstr (str filenamefrag)]
    `(testing ~tstr
       (is (s/valid? ::asr/unit (long-form-asr ~fstr))))))


(deftest slurp-test

  ;; METHOD CODE TOO LARGE

  (let [_1bcc4ec
        (do
          (in-ns 'masr.specs)
          (->> "_pass_print_list_tuple-print_02-1bcc4ec"
               slurp-asr
               rewrite-for-legacy
               #_eval
               ))]
    (comment "too big for eval")
    (is (not (nil? _1bcc4ec))))

  (comment "too big for eval")
  #_
  (test-unit _pass_print_list_tuple-print_02-1bcc4ec)
  )


(def small-function-sample
  '(Function
    (SymbolTable 2 {})
    f
    (FunctionType
     []    ()    Source
     Implementation
     ()    false false
     false false false
     []    []     false)
    []    []
    [(=
      (Var 2 a)
      (ListConstant
       [(StringConstant
         "ab"
         (Character 1 2 () [])
         )
        (StringConstant
         "abc"
         (Character 1 3 () [])
         )
        (StringConstant
         "abcd"
         (Character 1 4 () [])
         )]
       (List
        (Character 1 2 () [])
        )
       )
      ()
      ) ;; body
     (=
      (Var 2 b)
      (ListConstant
       [(IntegerConstant 1 (Integer 4 []))
        (IntegerConstant 2 (Integer 4 []))
        (IntegerConstant 3 (Integer 4 []))
        (IntegerConstant 4 (Integer 4 []))]
       (List
        (Integer 4 [])
        )
       )
      ()
      )
     (Print
      ()
      [(StringConstant
        "]"
        (Character 1 1 () [])
        )]
      ()
      (StringConstant
       " "
       (Character 1 1 () [])
       )
      )
     (Print
      ()
      [(StringConstant
        "["
        (Character 1 1 () [])
        )]
      ()
      (StringConstant
       ""
       (Character 1 0 () [])
       )
      )
     (DoLoop
      ()
      ((Var 2 __list_iterator18)
       (IntegerConstant 0 (Integer 4 []))
       (IntegerBinOp
        (ListLen
         (Var 2 c)
         (Integer 4 [])
         ()
         )
        Sub
        (IntegerConstant 1 (Integer 4 []))
        (Integer 4 [])
        ()
        )
       (IntegerConstant 1 (Integer 4 [])))
      [(Print
        ()
        [(ListItem
          (Var 2 c)
          (Var 2 __list_iterator18)
          (Real 8 [])
          ()
          )]
        (StringConstant
         ""
         (Character 1 0 () [])
         )
        (StringConstant
         ""
         (Character 1 0 () [])
         )
        )
       (If
        (IntegerCompare
         (Var 2 __list_iterator18)
         Lt
         (IntegerBinOp
          (ListLen
           (Var 2 c)
           (Integer 4 [])
           ()
           )
          Sub
          (IntegerConstant 1 (Integer 4 []))
          (Integer 4 [])
          ()
          )
         (Logical 4 [])
         ()
         )
        [(Print
          ()
          [(StringConstant
            ", "
            (Character 1 2 () [])
            )]
          (StringConstant
           ""
           (Character 1 0 () [])
           )
          (StringConstant
           ""
           (Character 1 0 () [])
           )
          )]
        []
        )]
      )]
    ()
    Public
    false
    false
    ))


(defn asr-eval
  [sexp]
  (do (in-ns 'masr.specs)
      (eval (rewrite-for-legacy sexp))))


(def big-function-sample '(Function
                       (SymbolTable
                        2
                        {
                         :__list_iterator
                         (Variable
                          2
                          __list_iterator
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator1
                         (Variable
                          2
                          __list_iterator1
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator10
                         (Variable
                          2
                          __list_iterator10
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator11
                         (Variable
                          2
                          __list_iterator11
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator12
                         (Variable
                          2
                          __list_iterator12
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator13
                         (Variable
                          2
                          __list_iterator13
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator14
                         (Variable
                          2
                          __list_iterator14
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator15
                         (Variable
                          2
                          __list_iterator15
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator16
                         (Variable
                          2
                          __list_iterator16
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator17
                         (Variable
                          2
                          __list_iterator17
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator18
                         (Variable
                          2
                          __list_iterator18
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator2
                         (Variable
                          2
                          __list_iterator2
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator3
                         (Variable
                          2
                          __list_iterator3
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator4
                         (Variable
                          2
                          __list_iterator4
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator5
                         (Variable
                          2
                          __list_iterator5
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator6
                         (Variable
                          2
                          __list_iterator6
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator7
                         (Variable
                          2
                          __list_iterator7
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator8
                         (Variable
                          2
                          __list_iterator8
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :__list_iterator9
                         (Variable
                          2
                          __list_iterator9
                          []
                          Local
                          ()
                          ()
                          Default
                          (Integer 4 [])
                          Source
                          Public
                          Required
                          false
                          ),
                         :a
                         (Variable
                          2
                          a
                          []
                          Local
                          ()
                          ()
                          Default
                          (List
                           (Character 1 -2 () [])
                           )
                          Source
                          Public
                          Required
                          false
                          ),
                         :b
                         (Variable
                          2
                          b
                          []
                          Local
                          ()
                          ()
                          Default
                          (List
                           (Integer 4 [])
                           )
                          Source
                          Public
                          Required
                          false
                          ),
                         :c
                         (Variable
                          2
                          c
                          []
                          Local
                          ()
                          ()
                          Default
                          (List
                           (Real 8 [])
                           )
                          Source
                          Public
                          Required
                          false
                          ),
                         :d
                         (Variable
                          2
                          d
                          []
                          Local
                          ()
                          ()
                          Default
                          (List
                           (Integer 4 [])
                           )
                          Source
                          Public
                          Required
                          false
                          )
                         })          ;; symtab
                       f             ;; fnnym
                       (FunctionType ;; fnsig
                        []
                        ()
                        Source
                        Implementation
                        ()
                        false
                        false
                        false
                        false
                        false
                        []
                        []
                        false
                        )
                       [] ;; deps
                       [] ;; param*
                       [(=
                         (Var 2 a)
                         (ListConstant
                          [(StringConstant
                            "ab"
                            (Character 1 2 () [])
                            )
                           (StringConstant
                            "abc"
                            (Character 1 3 () [])
                            )
                           (StringConstant
                            "abcd"
                            (Character 1 4 () [])
                            )]
                          (List
                           (Character 1 2 () [])
                           )
                          )
                         ()
                         ) ;; body
                        (=
                         (Var 2 b)
                         (ListConstant
                          [(IntegerConstant 1 (Integer 4 []))
                           (IntegerConstant 2 (Integer 4 []))
                           (IntegerConstant 3 (Integer 4 []))
                           (IntegerConstant 4 (Integer 4 []))]
                          (List
                           (Integer 4 [])
                           )
                          )
                         ()
                         )
                        (=
                         (Var 2 c)
                         (ListConstant
                          [(RealConstant
                            1.230000
                            (Real 8 [])
                            )
                           (RealConstant
                            324.300000
                            (Real 8 [])
                            )
                           (RealConstant
                            56.431000
                            (Real 8 [])
                            )
                           (RealConstant
                            90.500000
                            (Real 8 [])
                            )
                           (RealConstant
                            34.100000
                            (Real 8 [])
                            )]
                          (List
                           (Real 8 [])
                           )
                          )
                         ()
                         )
                        (=
                         (Var 2 d)
                         (ListConstant
                          []
                          (List
                           (Integer 4 [])
                           )
                          )
                         ()
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 a)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(StringConstant
                             "'"
                             (Character 1 1 () [])
                             )
                            (ListItem
                             (Var 2 a)
                             (Var 2 __list_iterator)
                             (Character 1 -2 () [])
                             ()
                             )
                            (StringConstant
                             "'"
                             (Character 1 1 () [])
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 a)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         ()
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator1)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 b)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (Var 2 b)
                             (Var 2 __list_iterator1)
                             (Integer 4 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator1)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 b)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         ()
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator2)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 c)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (Var 2 c)
                             (Var 2 __list_iterator2)
                             (Real 8 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator2)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 c)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         ()
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator3)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 d)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (Var 2 d)
                             (Var 2 __list_iterator3)
                             (Integer 4 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator3)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 d)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         ()
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator4)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 a)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(StringConstant
                             "'"
                             (Character 1 1 () [])
                             )
                            (ListItem
                             (Var 2 a)
                             (Var 2 __list_iterator4)
                             (Character 1 -2 () [])
                             ()
                             )
                            (StringConstant
                             "'"
                             (Character 1 1 () [])
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator4)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 a)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator5)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 a)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(StringConstant
                             "'"
                             (Character 1 1 () [])
                             )
                            (ListItem
                             (Var 2 a)
                             (Var 2 __list_iterator5)
                             (Character 1 -2 () [])
                             ()
                             )
                            (StringConstant
                             "'"
                             (Character 1 1 () [])
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator5)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 a)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator6)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 b)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (Var 2 b)
                             (Var 2 __list_iterator6)
                             (Integer 4 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator6)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 b)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator7)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 c)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (Var 2 c)
                             (Var 2 __list_iterator7)
                             (Real 8 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator7)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 c)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator8)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 d)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (Var 2 d)
                             (Var 2 __list_iterator8)
                             (Integer 4 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator8)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 d)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         ()
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator9)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 a)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(StringConstant
                             "'"
                             (Character 1 1 () [])
                             )
                            (ListItem
                             (Var 2 a)
                             (Var 2 __list_iterator9)
                             (Character 1 -2 () [])
                             ()
                             )
                            (StringConstant
                             "'"
                             (Character 1 1 () [])
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator9)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 a)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator10)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 a)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(StringConstant
                             "'"
                             (Character 1 1 () [])
                             )
                            (ListItem
                             (Var 2 a)
                             (Var 2 __list_iterator10)
                             (Character 1 -2 () [])
                             ()
                             )
                            (StringConstant
                             "'"
                             (Character 1 1 () [])
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator10)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 a)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(IntegerConstant 1 (Integer 4 []))]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator11)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 b)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (Var 2 b)
                             (Var 2 __list_iterator11)
                             (Integer 4 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator11)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 b)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator12)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 c)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (Var 2 c)
                             (Var 2 __list_iterator12)
                             (Real 8 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator12)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 c)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(RealConstant
                           1.100000
                           (Real 8 [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator13)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 d)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (Var 2 d)
                             (Var 2 __list_iterator13)
                             (Integer 4 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator13)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 d)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         ()
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator14)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (ListConstant
                             [(IntegerUnaryMinus
                               (IntegerConstant 3 (Integer 4 []))
                               (Integer 4 [])
                               (IntegerConstant -3 (Integer 4 []))
                               )
                              (IntegerConstant 2 (Integer 4 []))
                              (IntegerConstant 1 (Integer 4 []))
                              (IntegerConstant 0 (Integer 4 []))]
                             (List
                              (Integer 4 [])
                              )
                             )
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (ListConstant
                              [(IntegerUnaryMinus
                                (IntegerConstant 3 (Integer 4 []))
                                (Integer 4 [])
                                (IntegerConstant -3 (Integer 4 []))
                                )
                               (IntegerConstant 2 (Integer 4 []))
                               (IntegerConstant 1 (Integer 4 []))
                               (IntegerConstant 0 (Integer 4 []))]
                              (List
                               (Integer 4 [])
                               )
                              )
                             (Var 2 __list_iterator14)
                             (Integer 4 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator14)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (ListConstant
                               [(IntegerUnaryMinus
                                 (IntegerConstant 3 (Integer 4 []))
                                 (Integer 4 [])
                                 (IntegerConstant -3 (Integer 4 []))
                                 )
                                (IntegerConstant 2 (Integer 4 []))
                                (IntegerConstant 1 (Integer 4 []))
                                (IntegerConstant 0 (Integer 4 []))]
                               (List
                                (Integer 4 [])
                                )
                               )
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         ()
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator15)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (ListConstant
                             [(StringConstant
                               "a"
                               (Character 1 1 () [])
                               )
                              (StringConstant
                               "b"
                               (Character 1 1 () [])
                               )
                              (StringConstant
                               "c"
                               (Character 1 1 () [])
                               )
                              (StringConstant
                               "d"
                               (Character 1 1 () [])
                               )
                              (StringConstant
                               "e"
                               (Character 1 1 () [])
                               )
                              (StringConstant
                               "f"
                               (Character 1 1 () [])
                               )]
                             (List
                              (Character 1 1 () [])
                              )
                             )
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(StringConstant
                             "'"
                             (Character 1 1 () [])
                             )
                            (ListItem
                             (ListConstant
                              [(StringConstant
                                "a"
                                (Character 1 1 () [])
                                )
                               (StringConstant
                                "b"
                                (Character 1 1 () [])
                                )
                               (StringConstant
                                "c"
                                (Character 1 1 () [])
                                )
                               (StringConstant
                                "d"
                                (Character 1 1 () [])
                                )
                               (StringConstant
                                "e"
                                (Character 1 1 () [])
                                )
                               (StringConstant
                                "f"
                                (Character 1 1 () [])
                                )]
                              (List
                               (Character 1 1 () [])
                               )
                              )
                             (Var 2 __list_iterator15)
                             (Character 1 1 () [])
                             ()
                             )
                            (StringConstant
                             "'"
                             (Character 1 1 () [])
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator15)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (ListConstant
                               [(StringConstant
                                 "a"
                                 (Character 1 1 () [])
                                 )
                                (StringConstant
                                 "b"
                                 (Character 1 1 () [])
                                 )
                                (StringConstant
                                 "c"
                                 (Character 1 1 () [])
                                 )
                                (StringConstant
                                 "d"
                                 (Character 1 1 () [])
                                 )
                                (StringConstant
                                 "e"
                                 (Character 1 1 () [])
                                 )
                                (StringConstant
                                 "f"
                                 (Character 1 1 () [])
                                 )]
                               (List
                                (Character 1 1 () [])
                                )
                               )
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         ()
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator16)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (ListConstant
                             [(IntegerConstant 1 (Integer 4 []))
                              (IntegerConstant 2 (Integer 4 []))
                              (IntegerConstant 3 (Integer 4 []))
                              (IntegerConstant 4 (Integer 4 []))]
                             (List
                              (Integer 4 [])
                              )
                             )
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (ListConstant
                              [(IntegerConstant 1 (Integer 4 []))
                               (IntegerConstant 2 (Integer 4 []))
                               (IntegerConstant 3 (Integer 4 []))
                               (IntegerConstant 4 (Integer 4 []))]
                              (List
                               (Integer 4 [])
                               )
                              )
                             (Var 2 __list_iterator16)
                             (Integer 4 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator16)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (ListConstant
                               [(IntegerConstant 1 (Integer 4 []))
                                (IntegerConstant 2 (Integer 4 []))
                                (IntegerConstant 3 (Integer 4 []))
                                (IntegerConstant 4 (Integer 4 []))]
                               (List
                                (Integer 4 [])
                                )
                               )
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator17)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 a)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(StringConstant
                             "'"
                             (Character 1 1 () [])
                             )
                            (ListItem
                             (Var 2 a)
                             (Var 2 __list_iterator17)
                             (Character 1 -2 () [])
                             ()
                             )
                            (StringConstant
                             "'"
                             (Character 1 1 () [])
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator17)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 a)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          " "
                          (Character 1 1 () [])
                          )
                         )
                        (Print
                         ()
                         [(StringConstant
                           "["
                           (Character 1 1 () [])
                           )]
                         ()
                         (StringConstant
                          ""
                          (Character 1 0 () [])
                          )
                         )
                        (DoLoop
                         ()
                         ((Var 2 __list_iterator18)
                          (IntegerConstant 0 (Integer 4 []))
                          (IntegerBinOp
                           (ListLen
                            (Var 2 c)
                            (Integer 4 [])
                            ()
                            )
                           Sub
                           (IntegerConstant 1 (Integer 4 []))
                           (Integer 4 [])
                           ()
                           )
                          (IntegerConstant 1 (Integer 4 [])))
                         [(Print
                           ()
                           [(ListItem
                             (Var 2 c)
                             (Var 2 __list_iterator18)
                             (Real 8 [])
                             ()
                             )]
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           (StringConstant
                            ""
                            (Character 1 0 () [])
                            )
                           )
                          (If
                           (IntegerCompare
                            (Var 2 __list_iterator18)
                            Lt
                            (IntegerBinOp
                             (ListLen
                              (Var 2 c)
                              (Integer 4 [])
                              ()
                              )
                             Sub
                             (IntegerConstant 1 (Integer 4 []))
                             (Integer 4 [])
                             ()
                             )
                            (Logical 4 [])
                            ()
                            )
                           [(Print
                             ()
                             [(StringConstant
                               ", "
                               (Character 1 2 () [])
                               )]
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             (StringConstant
                              ""
                              (Character 1 0 () [])
                              )
                             )]
                           []
                           )]
                         )
                        (Print
                         ()
                         [(StringConstant
                           "]"
                           (Character 1 1 () [])
                           )]
                         ()
                         ()
                         )]
                       ()
                       Public
                       false
                       false
                       ))


(deftest bisecting-1bcc4ec

  (comment "too big for Function")
  #_(is (s/valid? ::asr/Function (asr-eval big-function-sample)))

  (is (s/valid? ::asr/body  (asr-eval (nth big-function-sample 6))))

  (doseq [e (nth big-function-sample 6)]
    (is (s/valid? ::asr/stmt
                  (asr-eval e))))
  )


(deftest evaluating-body-test
  (let [it (asr-eval small-function-sample)]
    (is (s/valid? ::asr/Function it))))


(defn function-piecewise
  [[function-head
    symtab
    fnnym    fnsig    deps
    param*   body     retvar?
    access   determ   sefree]]
  (let [vfunction-head `'~function-head
        ;; (SymbolTable ...)
        vsymtab  (asr-eval symtab)
        vfnnym   (if (s/valid? ::asr/function-name fnnym)
                   `'~fnnym ::asr/invalid-function-name)
        vfnsig   (asr-eval fnsig)
        vdeps    (for [d deps] `'d)
        vparam*  (if (s/valid? ::asr/param* param*)
                   param* ::asr/invalid-param*)
        vbody    (vec (map asr-eval body))
        vretvar? (if (s/valid? ::asr/return-var? retvar?)
                   retvar? ::asr/invalid-retvar?)
        vaccess  (asr-eval access)
        vdeterm  (if (s/valid? ::asr/deterministic determ)
                   determ ::asr/invalid-deterministic)
        vsefree  (if (s/valid? ::asr/side-effect-free sefree)
                   sefree ::asr/invalid-side-effect-free)
        ]
    (let [cnd
          {::asr/term ::asr/symbol
           ::asr/asr-symbol-head
           {::asr/symbol-head         ::asr/Function

            ::asr/SymbolTable         vsymtab

            ::asr/function-name       vfnnym
            ::asr/function-signature  vfnsig
            ::asr/dependencies        vdeps

            ::asr/param*              vparam*
            ::asr/body                vbody
            ::asr/return-var?         vretvar?

            ::asr/access              vaccess
            ::asr/deterministic       vdeterm
            ::asr/side-effect-free    vsefree
            }}]
      (if (s/valid? ::asr/Function cnd)
        cnd
        ::asr/invalid-function))
    ))


(deftest function-piecewise-test
  (is (not (nil? (function-piecewise big-function-sample)))))


(deftest evaluating-function-test
  (let [ft (FunctionType
            [] () Source
            Implementation () false
            false false false
            false [] [] false)
        afn (Function
             (SymbolTable 42 {})
             test_boolOp, ft, []
             [] [] () ;; param*, body, retvar
             Public false false)]
    (is (s/valid? ::asr/asr-term afn))
    (is (s/valid? ::asr/symbol   afn))
    (is (s/valid? ::asr/Function afn))
    ))


(deftest evaluating-subroutine-call-test
  (let [it (asr-eval
               '(SubroutineCall
                 114 test_pow
                 ()
                 []
                 ()
                 ))]
    (is (s/valid? ::asr/SubroutineCall it))))
