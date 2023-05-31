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


(defmacro test-translation-unit [filenamefrag]
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
  (test-translation-unit -test_numpy_03-6dd742e)
  #_
  (test-translation-unit _pass_print_list_tuple-print_02-1bcc4ec)
  )


(defn asr-eval
  [sexp]
  (do (in-ns 'masr.specs)
      (eval (rewrite-for-legacy sexp))))


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


(deftest small-function-test
  (let [it (asr-eval small-function-sample)]
    (is (s/valid? ::asr/Function it))))


(def another-small-function-sample
  '(Function
    (SymbolTable
     2
     {
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
      })                                 ;; symtab
    f                                    ;; fnnym
    (FunctionType                        ;; fnsig
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
    []                        ;; deps
    []                        ;; param*
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


(deftest another-small-function-test
  (let [it (asr-eval small-function-sample)]
    (is (s/valid? ::asr/Function it))))


(def big-function-sample
  '(Function
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
                             })      ;; symtab
                           f         ;; fnnym
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


(deftest big-function-test
  (testing "bisecting Java method-code-too-large error"
    (is (s/valid? ::asr/Function
                  (asr-eval big-function-sample)))))


(def another-big-function-sample
  '(Function
    (SymbolTable
     4
     {
      :__list_iterator
      (Variable
       4
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
       4
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
      :__list_iterator2
      (Variable
       4
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
       4
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
       4
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
       4
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
       4
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
      :a
      (Variable
       4
       a
       []
       Local
       ()
       ()
       Default
       (List
        (Tuple
         [(Integer 4 [])
          (Integer 4 [])]
         )
        )
       Source
       Public
       Required
       false
       ),
      :b
      (Variable
       4
       b
       []
       Local
       ()
       ()
       Default
       (Tuple
        [(List
          (Character 1 -2 () [])
          )
         (List
          (Integer 4 [])
          )
         (Real 8 [])]
        )
       Source
       Public
       Required
       false
       ),
      :b1
      (Variable
       4
       b1
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
      :b2
      (Variable
       4
       b2
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
       4
       c
       []
       Local
       ()
       ()
       Default
       (List
        (List
         (Tuple
          [(Integer 4 [])
           (Character 1 -2 () [])]
          )
         )
        )
       Source
       Public
       Required
       false
       )
      })
    test_print_list_tuple
    (FunctionType
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
    []
    []
    [(=
      (Var 4 a)
      (ListConstant
       [(TupleConstant
         [(IntegerConstant 1 (Integer 4 []))
          (IntegerConstant 2 (Integer 4 []))]
         (Tuple
          [(Integer 4 [])
           (Integer 4 [])]
          )
         )
        (TupleConstant
         [(IntegerConstant 3 (Integer 4 []))
          (IntegerConstant 4 (Integer 4 []))]
         (Tuple
          [(Integer 4 [])
           (Integer 4 [])]
          )
         )
        (TupleConstant
         [(IntegerConstant 5 (Integer 4 []))
          (IntegerConstant 6 (Integer 4 []))]
         (Tuple
          [(Integer 4 [])
           (Integer 4 [])]
          )
         )]
       (List
        (Tuple
         [(Integer 4 [])
          (Integer 4 [])]
         )
        )
       )
      ()
      )
     (=
      (Var 4 c)
      (ListConstant
       [(ListConstant
         [(TupleConstant
           [(IntegerConstant 1 (Integer 4 []))
            (StringConstant
             "a"
             (Character 1 1 () [])
             )]
           (Tuple
            [(Integer 4 [])
             (Character 1 1 () [])]
            )
           )
          (TupleConstant
           [(IntegerConstant 2 (Integer 4 []))
            (StringConstant
             "b"
             (Character 1 1 () [])
             )]
           (Tuple
            [(Integer 4 [])
             (Character 1 1 () [])]
            )
           )]
         (List
          (Tuple
           [(Integer 4 [])
            (Character 1 1 () [])]
           )
          )
         )
        (ListConstant
         [(TupleConstant
           [(IntegerConstant 3 (Integer 4 []))
            (StringConstant
             "c"
             (Character 1 1 () [])
             )]
           (Tuple
            [(Integer 4 [])
             (Character 1 1 () [])]
            )
           )
          (TupleConstant
           [(IntegerConstant 4 (Integer 4 []))
            (StringConstant
             "d"
             (Character 1 1 () [])
             )]
           (Tuple
            [(Integer 4 [])
             (Character 1 1 () [])]
            )
           )]
         (List
          (Tuple
           [(Integer 4 [])
            (Character 1 1 () [])]
           )
          )
         )]
       (List
        (List
         (Tuple
          [(Integer 4 [])
           (Character 1 1 () [])]
          )
         )
        )
       )
      ()
      )
     (=
      (Var 4 b1)
      (ListConstant
       [(StringConstant
         "a"
         (Character 1 1 () [])
         )
        (StringConstant
         "bb"
         (Character 1 2 () [])
         )
        (StringConstant
         "ccc"
         (Character 1 3 () [])
         )
        (StringConstant
         "dddd"
         (Character 1 4 () [])
         )
        (StringConstant
         "eeeee"
         (Character 1 5 () [])
         )]
       (List
        (Character 1 1 () [])
        )
       )
      ()
      )
     (=
      (Var 4 b2)
      (ListConstant
       [(IntegerConstant 10 (Integer 4 []))
        (IntegerConstant 20 (Integer 4 []))
        (IntegerConstant 30 (Integer 4 []))
        (IntegerConstant 40 (Integer 4 []))]
       (List
        (Integer 4 [])
        )
       )
      ()
      )
     (=
      (Var 4 b)
      (TupleConstant
       [(Var 4 b1)
        (Var 4 b2)
        (RealConstant
         6.030500
         (Real 8 [])
         )]
       (Tuple
        [(List
          (Character 1 -2 () [])
          )
         (List
          (Integer 4 [])
          )
         (Real 8 [])]
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
      ((Var 4 __list_iterator)
       (IntegerConstant 0 (Integer 4 []))
       (IntegerBinOp
        (ListLen
         (Var 4 a)
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
          "("
          (Character 1 1 () [])
          )]
        ()
        (StringConstant
         ""
         (Character 1 0 () [])
         )
        )
       (Print
        ()
        [(TupleItem
          (ListItem
           (Var 4 a)
           (Var 4 __list_iterator)
           (Tuple
            [(Integer 4 [])
             (Integer 4 [])]
            )
           ()
           )
          (IntegerConstant 0 (Integer 4 []))
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
       (Print
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
        )
       (Print
        ()
        [(TupleItem
          (ListItem
           (Var 4 a)
           (Var 4 __list_iterator)
           (Tuple
            [(Integer 4 [])
             (Integer 4 [])]
            )
           ()
           )
          (IntegerConstant 1 (Integer 4 []))
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
       (Print
        ()
        [(StringConstant
          ")"
          (Character 1 1 () [])
          )]
        ()
        (StringConstant
         ""
         (Character 1 0 () [])
         )
        )
       (If
        (IntegerCompare
         (Var 4 __list_iterator)
         Lt
         (IntegerBinOp
          (ListLen
           (Var 4 a)
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
        "("
        (Character 1 1 () [])
        )]
      ()
      (StringConstant
       ""
       (Character 1 0 () [])
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
      ((Var 4 __list_iterator1)
       (IntegerConstant 0 (Integer 4 []))
       (IntegerBinOp
        (ListLen
         (TupleItem
          (Var 4 b)
          (IntegerConstant 0 (Integer 4 []))
          (List
           (Character 1 -2 () [])
           )
          ()
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
          (TupleItem
           (Var 4 b)
           (IntegerConstant 0 (Integer 4 []))
           (List
            (Character 1 -2 () [])
            )
           ()
           )
          (Var 4 __list_iterator1)
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
         (Var 4 __list_iterator1)
         Lt
         (IntegerBinOp
          (ListLen
           (TupleItem
            (Var 4 b)
            (IntegerConstant 0 (Integer 4 []))
            (List
             (Character 1 -2 () [])
             )
            ()
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
       ""
       (Character 1 0 () [])
       )
      )
     (Print
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
      ((Var 4 __list_iterator2)
       (IntegerConstant 0 (Integer 4 []))
       (IntegerBinOp
        (ListLen
         (TupleItem
          (Var 4 b)
          (IntegerConstant 1 (Integer 4 []))
          (List
           (Integer 4 [])
           )
          ()
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
          (TupleItem
           (Var 4 b)
           (IntegerConstant 1 (Integer 4 []))
           (List
            (Integer 4 [])
            )
           ()
           )
          (Var 4 __list_iterator2)
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
         (Var 4 __list_iterator2)
         Lt
         (IntegerBinOp
          (ListLen
           (TupleItem
            (Var 4 b)
            (IntegerConstant 1 (Integer 4 []))
            (List
             (Integer 4 [])
             )
            ()
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
       ""
       (Character 1 0 () [])
       )
      )
     (Print
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
      )
     (Print
      ()
      [(TupleItem
        (Var 4 b)
        (IntegerConstant 2 (Integer 4 []))
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
     (Print
      ()
      [(StringConstant
        ")"
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
      ((Var 4 __list_iterator3)
       (IntegerConstant 0 (Integer 4 []))
       (IntegerBinOp
        (ListLen
         (Var 4 c)
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
        ((Var 4 __list_iterator4)
         (IntegerConstant 0 (Integer 4 []))
         (IntegerBinOp
          (ListLen
           (ListItem
            (Var 4 c)
            (Var 4 __list_iterator3)
            (List
             (Tuple
              [(Integer 4 [])
               (Character 1 -2 () [])]
              )
             )
            ()
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
            "("
            (Character 1 1 () [])
            )]
          ()
          (StringConstant
           ""
           (Character 1 0 () [])
           )
          )
         (Print
          ()
          [(TupleItem
            (ListItem
             (ListItem
              (Var 4 c)
              (Var 4 __list_iterator3)
              (List
               (Tuple
                [(Integer 4 [])
                 (Character 1 -2 () [])]
                )
               )
              ()
              )
             (Var 4 __list_iterator4)
             (Tuple
              [(Integer 4 [])
               (Character 1 -2 () [])]
              )
             ()
             )
            (IntegerConstant 0 (Integer 4 []))
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
         (Print
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
          )
         (Print
          ()
          [(StringConstant
            "'"
            (Character 1 1 () [])
            )
           (TupleItem
            (ListItem
             (ListItem
              (Var 4 c)
              (Var 4 __list_iterator3)
              (List
               (Tuple
                [(Integer 4 [])
                 (Character 1 -2 () [])]
                )
               )
              ()
              )
             (Var 4 __list_iterator4)
             (Tuple
              [(Integer 4 [])
               (Character 1 -2 () [])]
              )
             ()
             )
            (IntegerConstant 1 (Integer 4 []))
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
         (Print
          ()
          [(StringConstant
            ")"
            (Character 1 1 () [])
            )]
          ()
          (StringConstant
           ""
           (Character 1 0 () [])
           )
          )
         (If
          (IntegerCompare
           (Var 4 __list_iterator4)
           Lt
           (IntegerBinOp
            (ListLen
             (ListItem
              (Var 4 c)
              (Var 4 __list_iterator3)
              (List
               (Tuple
                [(Integer 4 [])
                 (Character 1 -2 () [])]
                )
               )
              ()
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
         ""
         (Character 1 0 () [])
         )
        )
       (If
        (IntegerCompare
         (Var 4 __list_iterator3)
         Lt
         (IntegerBinOp
          (ListLen
           (Var 4 c)
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
      ((Var 4 __list_iterator5)
       (IntegerConstant 0 (Integer 4 []))
       (IntegerBinOp
        (ListLen
         (Var 4 b1)
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
          (Var 4 b1)
          (Var 4 __list_iterator5)
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
         (Var 4 __list_iterator5)
         Lt
         (IntegerBinOp
          (ListLen
           (Var 4 b1)
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
      ((Var 4 __list_iterator6)
       (IntegerConstant 0 (Integer 4 []))
       (IntegerBinOp
        (ListLen
         (Var 4 b2)
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
          (Var 4 b2)
          (Var 4 __list_iterator6)
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
         (Var 4 __list_iterator6)
         Lt
         (IntegerBinOp
          (ListLen
           (Var 4 b2)
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
        3.420000
        (Real 8 [])
        )
       (StringConstant
        "okay"
        (Character 1 4 () [])
        )
       (LogicalConstant
        true
        (Logical 4 [])
        )
       (IntegerConstant 14483 (Integer 4 []))]
      ()
      ()
      )]
    ()
    Public
    false
    false
    ))


(deftest another-big-function-test
  (testing "bisecting Java method-code-too-large error"
    (is (s/valid? ::asr/Function
                  (asr-eval another-big-function-sample)))))


(def big-translation-unit-literal-sample
  '(TranslationUnit
    (SymbolTable
     1
     {
      :_global_symbols
      (Module
       (SymbolTable
        9
        {
         #_:_lpython_main_program
         #_(Function
            (SymbolTable
             8
             {

              })
            _lpython_main_program
            (FunctionType
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
            [check]
            []
            [(SubroutineCall
              9 check
              ()
              []
              ()
              )]
            ()
            Public
            false
            false
            ),
         #_:check
         #_(Function
            (SymbolTable
             6
             {

              })
            check
            (FunctionType
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
            [f
             test_nested_lists
             test_nested_lists2
             test_print_list_tuple]
            []
            [(SubroutineCall
              9 f
              ()
              []
              ()
              )
             (SubroutineCall
              9 test_nested_lists
              ()
              []
              ()
              )
             (SubroutineCall
              9 test_nested_lists2
              ()
              []
              ()
              )
             (SubroutineCall
              9 test_print_list_tuple
              ()
              []
              ()
              )]
            ()
            Public
            false
            false
            ),
         #_:f
         #_(Function
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
              })
            f
            (FunctionType
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
            []
            []
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
              )
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
            ),
         #_:test_nested_lists
         #_(Function
            (SymbolTable
             3
             {
              :__list_iterator
              (Variable
               3
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
               3
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
               3
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
               3
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
              :__list_iterator2
              (Variable
               3
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
               3
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
               3
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
               3
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
               3
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
               3
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
               3
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
               3
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
              :w
              (Variable
               3
               w
               []
               Local
               ()
               ()
               Default
               (List
                (List
                 (List
                  (List
                   (List
                    (Real 8 [])
                    )
                   )
                  )
                 )
                )
               Source
               Public
               Required
               false
               ),
              :x
              (Variable
               3
               x
               []
               Local
               ()
               ()
               Default
               (List
                (List
                 (List
                  (Integer 4 [])
                  )
                 )
                )
               Source
               Public
               Required
               false
               ),
              :y
              (Variable
               3
               y
               []
               Local
               ()
               ()
               Default
               (List
                (List
                 (Real 8 [])
                 )
                )
               Source
               Public
               Required
               false
               ),
              :z
              (Variable
               3
               z
               []
               Local
               ()
               ()
               Default
               (List
                (List
                 (Character 1 -2 () [])
                 )
                )
               Source
               Public
               Required
               false
               )
              })
            test_nested_lists
            (FunctionType
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
            []
            []
            [(=
              (Var 3 w)
              (ListConstant
               [(ListConstant
                 [(ListConstant
                   [(ListConstant
                     [(ListConstant
                       [(RealConstant
                         2.130000
                         (Real 8 [])
                         )
                        (RealUnaryMinus
                         (RealConstant
                          98.170000
                          (Real 8 [])
                          )
                         (Real 8 [])
                         (RealConstant
                          -98.170000
                          (Real 8 [])
                          )
                         )]
                       (List
                        (Real 8 [])
                        )
                       )]
                     (List
                      (List
                       (Real 8 [])
                       )
                      )
                     )]
                   (List
                    (List
                     (List
                      (Real 8 [])
                      )
                     )
                    )
                   )
                  (ListConstant
                   [(ListConstant
                     [(ListConstant
                       [(RealConstant
                         1.110000
                         (Real 8 [])
                         )]
                       (List
                        (Real 8 [])
                        )
                       )]
                     (List
                      (List
                       (Real 8 [])
                       )
                      )
                     )]
                   (List
                    (List
                     (List
                      (Real 8 [])
                      )
                     )
                    )
                   )]
                 (List
                  (List
                   (List
                    (List
                     (Real 8 [])
                     )
                    )
                   )
                  )
                 )]
               (List
                (List
                 (List
                  (List
                   (List
                    (Real 8 [])
                    )
                   )
                  )
                 )
                )
               )
              ()
              )
             (=
              (Var 3 x)
              (ListConstant
               [(ListConstant
                 [(ListConstant
                   [(IntegerConstant 3 (Integer 4 []))
                    (IntegerUnaryMinus
                     (IntegerConstant 1 (Integer 4 []))
                     (Integer 4 [])
                     (IntegerConstant -1 (Integer 4 []))
                     )]
                   (List
                    (Integer 4 [])
                    )
                   )
                  (ListConstant
                   [(IntegerUnaryMinus
                     (IntegerConstant 2 (Integer 4 []))
                     (Integer 4 [])
                     (IntegerConstant -2 (Integer 4 []))
                     )
                    (IntegerConstant 5 (Integer 4 []))]
                   (List
                    (Integer 4 [])
                    )
                   )
                  (ListConstant
                   [(IntegerConstant 5 (Integer 4 []))]
                   (List
                    (Integer 4 [])
                    )
                   )]
                 (List
                  (List
                   (Integer 4 [])
                   )
                  )
                 )
                (ListConstant
                 [(ListConstant
                   [(IntegerUnaryMinus
                     (IntegerConstant 3 (Integer 4 []))
                     (Integer 4 [])
                     (IntegerConstant -3 (Integer 4 []))
                     )
                    (IntegerConstant 1 (Integer 4 []))]
                   (List
                    (Integer 4 [])
                    )
                   )
                  (ListConstant
                   [(IntegerConstant 2 (Integer 4 []))
                    (IntegerUnaryMinus
                     (IntegerConstant 5 (Integer 4 []))
                     (Integer 4 [])
                     (IntegerConstant -5 (Integer 4 []))
                     )]
                   (List
                    (Integer 4 [])
                    )
                   )
                  (ListConstant
                   [(IntegerUnaryMinus
                     (IntegerConstant 5 (Integer 4 []))
                     (Integer 4 [])
                     (IntegerConstant -5 (Integer 4 []))
                     )]
                   (List
                    (Integer 4 [])
                    )
                   )]
                 (List
                  (List
                   (Integer 4 [])
                   )
                  )
                 )]
               (List
                (List
                 (List
                  (Integer 4 [])
                  )
                 )
                )
               )
              ()
              )
             (=
              (Var 3 y)
              (ListConstant
               [(ListConstant
                 [(RealConstant
                   3.140000
                   (Real 8 [])
                   )
                  (RealUnaryMinus
                   (RealConstant
                    1.001200
                    (Real 8 [])
                    )
                   (Real 8 [])
                   (RealConstant
                    -1.001200
                    (Real 8 [])
                    )
                   )]
                 (List
                  (Real 8 [])
                  )
                 )
                (ListConstant
                 [(RealUnaryMinus
                   (RealConstant
                    2.380000
                    (Real 8 [])
                    )
                   (Real 8 [])
                   (RealConstant
                    -2.380000
                    (Real 8 [])
                    )
                   )
                  (RealConstant
                   5.510000
                   (Real 8 [])
                   )]
                 (List
                  (Real 8 [])
                  )
                 )]
               (List
                (List
                 (Real 8 [])
                 )
                )
               )
              ()
              )
             (=
              (Var 3 z)
              (ListConstant
               [(ListConstant
                 [(StringConstant
                   "bat"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "ball"
                   (Character 1 4 () [])
                   )]
                 (List
                  (Character 1 3 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "cat"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "dog"
                   (Character 1 3 () [])
                   )]
                 (List
                  (Character 1 3 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "c"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "c++"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "java"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "python"
                   (Character 1 6 () [])
                   )]
                 (List
                  (Character 1 1 () [])
                  )
                 )]
               (List
                (List
                 (Character 1 3 () [])
                 )
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
              ((Var 3 __list_iterator)
               (IntegerConstant 0 (Integer 4 []))
               (IntegerBinOp
                (ListLen
                 (Var 3 w)
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
                ((Var 3 __list_iterator1)
                 (IntegerConstant 0 (Integer 4 []))
                 (IntegerBinOp
                  (ListLen
                   (ListItem
                    (Var 3 w)
                    (Var 3 __list_iterator)
                    (List
                     (List
                      (List
                       (List
                        (Real 8 [])
                        )
                       )
                      )
                     )
                    ()
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
                  ((Var 3 __list_iterator2)
                   (IntegerConstant 0 (Integer 4 []))
                   (IntegerBinOp
                    (ListLen
                     (ListItem
                      (ListItem
                       (Var 3 w)
                       (Var 3 __list_iterator)
                       (List
                        (List
                         (List
                          (List
                           (Real 8 [])
                           )
                          )
                         )
                        )
                       ()
                       )
                      (Var 3 __list_iterator1)
                      (List
                       (List
                        (List
                         (Real 8 [])
                         )
                        )
                       )
                      ()
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
                    ((Var 3 __list_iterator3)
                     (IntegerConstant 0 (Integer 4 []))
                     (IntegerBinOp
                      (ListLen
                       (ListItem
                        (ListItem
                         (ListItem
                          (Var 3 w)
                          (Var 3 __list_iterator)
                          (List
                           (List
                            (List
                             (List
                              (Real 8 [])
                              )
                             )
                            )
                           )
                          ()
                          )
                         (Var 3 __list_iterator1)
                         (List
                          (List
                           (List
                            (Real 8 [])
                            )
                           )
                          )
                         ()
                         )
                        (Var 3 __list_iterator2)
                        (List
                         (List
                          (Real 8 [])
                          )
                         )
                        ()
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
                      ((Var 3 __list_iterator4)
                       (IntegerConstant 0 (Integer 4 []))
                       (IntegerBinOp
                        (ListLen
                         (ListItem
                          (ListItem
                           (ListItem
                            (ListItem
                             (Var 3 w)
                             (Var 3 __list_iterator)
                             (List
                              (List
                               (List
                                (List
                                 (Real 8 [])
                                 )
                                )
                               )
                              )
                             ()
                             )
                            (Var 3 __list_iterator1)
                            (List
                             (List
                              (List
                               (Real 8 [])
                               )
                              )
                             )
                            ()
                            )
                           (Var 3 __list_iterator2)
                           (List
                            (List
                             (Real 8 [])
                             )
                            )
                           ()
                           )
                          (Var 3 __list_iterator3)
                          (List
                           (Real 8 [])
                           )
                          ()
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
                          (ListItem
                           (ListItem
                            (ListItem
                             (ListItem
                              (Var 3 w)
                              (Var 3 __list_iterator)
                              (List
                               (List
                                (List
                                 (List
                                  (Real 8 [])
                                  )
                                 )
                                )
                               )
                              ()
                              )
                             (Var 3 __list_iterator1)
                             (List
                              (List
                               (List
                                (Real 8 [])
                                )
                               )
                              )
                             ()
                             )
                            (Var 3 __list_iterator2)
                            (List
                             (List
                              (Real 8 [])
                              )
                             )
                            ()
                            )
                           (Var 3 __list_iterator3)
                           (List
                            (Real 8 [])
                            )
                           ()
                           )
                          (Var 3 __list_iterator4)
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
                         (Var 3 __list_iterator4)
                         Lt
                         (IntegerBinOp
                          (ListLen
                           (ListItem
                            (ListItem
                             (ListItem
                              (ListItem
                               (Var 3 w)
                               (Var 3 __list_iterator)
                               (List
                                (List
                                 (List
                                  (List
                                   (Real 8 [])
                                   )
                                  )
                                 )
                                )
                               ()
                               )
                              (Var 3 __list_iterator1)
                              (List
                               (List
                                (List
                                 (Real 8 [])
                                 )
                                )
                               )
                              ()
                              )
                             (Var 3 __list_iterator2)
                             (List
                              (List
                               (Real 8 [])
                               )
                              )
                             ()
                             )
                            (Var 3 __list_iterator3)
                            (List
                             (Real 8 [])
                             )
                            ()
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
                       ""
                       (Character 1 0 () [])
                       )
                      )
                     (If
                      (IntegerCompare
                       (Var 3 __list_iterator3)
                       Lt
                       (IntegerBinOp
                        (ListLen
                         (ListItem
                          (ListItem
                           (ListItem
                            (Var 3 w)
                            (Var 3 __list_iterator)
                            (List
                             (List
                              (List
                               (List
                                (Real 8 [])
                                )
                               )
                              )
                             )
                            ()
                            )
                           (Var 3 __list_iterator1)
                           (List
                            (List
                             (List
                              (Real 8 [])
                              )
                             )
                            )
                           ()
                           )
                          (Var 3 __list_iterator2)
                          (List
                           (List
                            (Real 8 [])
                            )
                           )
                          ()
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
                     ""
                     (Character 1 0 () [])
                     )
                    )
                   (If
                    (IntegerCompare
                     (Var 3 __list_iterator2)
                     Lt
                     (IntegerBinOp
                      (ListLen
                       (ListItem
                        (ListItem
                         (Var 3 w)
                         (Var 3 __list_iterator)
                         (List
                          (List
                           (List
                            (List
                             (Real 8 [])
                             )
                            )
                           )
                          )
                         ()
                         )
                        (Var 3 __list_iterator1)
                        (List
                         (List
                          (List
                           (Real 8 [])
                           )
                          )
                         )
                        ()
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
                   ""
                   (Character 1 0 () [])
                   )
                  )
                 (If
                  (IntegerCompare
                   (Var 3 __list_iterator1)
                   Lt
                   (IntegerBinOp
                    (ListLen
                     (ListItem
                      (Var 3 w)
                      (Var 3 __list_iterator)
                      (List
                       (List
                        (List
                         (List
                          (Real 8 [])
                          )
                         )
                        )
                       )
                      ()
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
                 ""
                 (Character 1 0 () [])
                 )
                )
               (If
                (IntegerCompare
                 (Var 3 __list_iterator)
                 Lt
                 (IntegerBinOp
                  (ListLen
                   (Var 3 w)
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
              ((Var 3 __list_iterator5)
               (IntegerConstant 0 (Integer 4 []))
               (IntegerBinOp
                (ListLen
                 (Var 3 x)
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
                ((Var 3 __list_iterator6)
                 (IntegerConstant 0 (Integer 4 []))
                 (IntegerBinOp
                  (ListLen
                   (ListItem
                    (Var 3 x)
                    (Var 3 __list_iterator5)
                    (List
                     (List
                      (Integer 4 [])
                      )
                     )
                    ()
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
                  ((Var 3 __list_iterator7)
                   (IntegerConstant 0 (Integer 4 []))
                   (IntegerBinOp
                    (ListLen
                     (ListItem
                      (ListItem
                       (Var 3 x)
                       (Var 3 __list_iterator5)
                       (List
                        (List
                         (Integer 4 [])
                         )
                        )
                       ()
                       )
                      (Var 3 __list_iterator6)
                      (List
                       (Integer 4 [])
                       )
                      ()
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
                      (ListItem
                       (ListItem
                        (Var 3 x)
                        (Var 3 __list_iterator5)
                        (List
                         (List
                          (Integer 4 [])
                          )
                         )
                        ()
                        )
                       (Var 3 __list_iterator6)
                       (List
                        (Integer 4 [])
                        )
                       ()
                       )
                      (Var 3 __list_iterator7)
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
                     (Var 3 __list_iterator7)
                     Lt
                     (IntegerBinOp
                      (ListLen
                       (ListItem
                        (ListItem
                         (Var 3 x)
                         (Var 3 __list_iterator5)
                         (List
                          (List
                           (Integer 4 [])
                           )
                          )
                         ()
                         )
                        (Var 3 __list_iterator6)
                        (List
                         (Integer 4 [])
                         )
                        ()
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
                   ""
                   (Character 1 0 () [])
                   )
                  )
                 (If
                  (IntegerCompare
                   (Var 3 __list_iterator6)
                   Lt
                   (IntegerBinOp
                    (ListLen
                     (ListItem
                      (Var 3 x)
                      (Var 3 __list_iterator5)
                      (List
                       (List
                        (Integer 4 [])
                        )
                       )
                      ()
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
                 ""
                 (Character 1 0 () [])
                 )
                )
               (If
                (IntegerCompare
                 (Var 3 __list_iterator5)
                 Lt
                 (IntegerBinOp
                  (ListLen
                   (Var 3 x)
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
              ((Var 3 __list_iterator8)
               (IntegerConstant 0 (Integer 4 []))
               (IntegerBinOp
                (ListLen
                 (Var 3 y)
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
                ((Var 3 __list_iterator9)
                 (IntegerConstant 0 (Integer 4 []))
                 (IntegerBinOp
                  (ListLen
                   (ListItem
                    (Var 3 y)
                    (Var 3 __list_iterator8)
                    (List
                     (Real 8 [])
                     )
                    ()
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
                    (ListItem
                     (Var 3 y)
                     (Var 3 __list_iterator8)
                     (List
                      (Real 8 [])
                      )
                     ()
                     )
                    (Var 3 __list_iterator9)
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
                   (Var 3 __list_iterator9)
                   Lt
                   (IntegerBinOp
                    (ListLen
                     (ListItem
                      (Var 3 y)
                      (Var 3 __list_iterator8)
                      (List
                       (Real 8 [])
                       )
                      ()
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
                 ""
                 (Character 1 0 () [])
                 )
                )
               (If
                (IntegerCompare
                 (Var 3 __list_iterator8)
                 Lt
                 (IntegerBinOp
                  (ListLen
                   (Var 3 y)
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
              ((Var 3 __list_iterator10)
               (IntegerConstant 0 (Integer 4 []))
               (IntegerBinOp
                (ListLen
                 (Var 3 z)
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
                ((Var 3 __list_iterator11)
                 (IntegerConstant 0 (Integer 4 []))
                 (IntegerBinOp
                  (ListLen
                   (ListItem
                    (Var 3 z)
                    (Var 3 __list_iterator10)
                    (List
                     (Character 1 -2 () [])
                     )
                    ()
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
                    (ListItem
                     (Var 3 z)
                     (Var 3 __list_iterator10)
                     (List
                      (Character 1 -2 () [])
                      )
                     ()
                     )
                    (Var 3 __list_iterator11)
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
                   (Var 3 __list_iterator11)
                   Lt
                   (IntegerBinOp
                    (ListLen
                     (ListItem
                      (Var 3 z)
                      (Var 3 __list_iterator10)
                      (List
                       (Character 1 -2 () [])
                       )
                      ()
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
                 ""
                 (Character 1 0 () [])
                 )
                )
               (If
                (IntegerCompare
                 (Var 3 __list_iterator10)
                 Lt
                 (IntegerBinOp
                  (ListLen
                   (Var 3 z)
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
            ),
         :test_nested_lists2
         (Function
          (SymbolTable
           5
           {
            :__list_iterator
            (Variable
             5
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
             5
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
            :__list_iterator2
            (Variable
             5
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
             5
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
             5
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
             5
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
             5
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
             5
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
             5
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
            :p
            (Variable
             5
             p
             []
             Local
             ()
             ()
             Default
             (List
              (List
               (Integer 4 [])
               )
              )
             Source
             Public
             Required
             false
             ),
            :q
            (Variable
             5
             q
             []
             Local
             ()
             ()
             Default
             (List
              (List
               (List
                (List
                 (Real 8 [])
                 )
                )
               )
              )
             Source
             Public
             Required
             false
             ),
            :r
            (Variable
             5
             r
             []
             Local
             ()
             ()
             Default
             (List
              (List
               (List
                (Character 1 -2 () [])
                )
               )
              )
             Source
             Public
             Required
             false
             )
            })
          test_nested_lists2
          (FunctionType
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
          []
          []
          [(=
            (Var 5 p)
            (ListConstant
             [(ListConstant
               [(IntegerConstant 0 (Integer 4 []))
                (IntegerConstant 1 (Integer 4 []))
                (IntegerConstant 2 (Integer 4 []))
                (IntegerConstant 3 (Integer 4 []))
                (IntegerConstant 4 (Integer 4 []))
                (IntegerConstant 5 (Integer 4 []))
                (IntegerConstant 6 (Integer 4 []))
                (IntegerConstant 7 (Integer 4 []))
                (IntegerConstant 8 (Integer 4 []))
                (IntegerConstant 9 (Integer 4 []))]
               (List
                (Integer 4 [])
                )
               )
              (ListConstant
               [(IntegerConstant 10 (Integer 4 []))
                (IntegerConstant 11 (Integer 4 []))
                (IntegerConstant 12 (Integer 4 []))
                (IntegerConstant 13 (Integer 4 []))
                (IntegerConstant 14 (Integer 4 []))
                (IntegerConstant 15 (Integer 4 []))
                (IntegerConstant 16 (Integer 4 []))
                (IntegerConstant 17 (Integer 4 []))
                (IntegerConstant 18 (Integer 4 []))
                (IntegerConstant 19 (Integer 4 []))]
               (List
                (Integer 4 [])
                )
               )
              (ListConstant
               [(IntegerConstant 20 (Integer 4 []))
                (IntegerConstant 21 (Integer 4 []))
                (IntegerConstant 22 (Integer 4 []))
                (IntegerConstant 23 (Integer 4 []))
                (IntegerConstant 24 (Integer 4 []))
                (IntegerConstant 25 (Integer 4 []))
                (IntegerConstant 26 (Integer 4 []))
                (IntegerConstant 27 (Integer 4 []))
                (IntegerConstant 28 (Integer 4 []))
                (IntegerConstant 29 (Integer 4 []))]
               (List
                (Integer 4 [])
                )
               )
              (ListConstant
               [(IntegerConstant 30 (Integer 4 []))
                (IntegerConstant 31 (Integer 4 []))
                (IntegerConstant 32 (Integer 4 []))
                (IntegerConstant 33 (Integer 4 []))
                (IntegerConstant 34 (Integer 4 []))
                (IntegerConstant 35 (Integer 4 []))
                (IntegerConstant 36 (Integer 4 []))
                (IntegerConstant 37 (Integer 4 []))
                (IntegerConstant 38 (Integer 4 []))
                (IntegerConstant 39 (Integer 4 []))]
               (List
                (Integer 4 [])
                )
               )
              (ListConstant
               [(IntegerConstant 40 (Integer 4 []))
                (IntegerConstant 41 (Integer 4 []))
                (IntegerConstant 42 (Integer 4 []))
                (IntegerConstant 43 (Integer 4 []))
                (IntegerConstant 44 (Integer 4 []))
                (IntegerConstant 45 (Integer 4 []))
                (IntegerConstant 46 (Integer 4 []))
                (IntegerConstant 47 (Integer 4 []))
                (IntegerConstant 48 (Integer 4 []))
                (IntegerConstant 49 (Integer 4 []))]
               (List
                (Integer 4 [])
                )
               )
              (ListConstant
               [(IntegerConstant 50 (Integer 4 []))
                (IntegerConstant 51 (Integer 4 []))
                (IntegerConstant 52 (Integer 4 []))
                (IntegerConstant 53 (Integer 4 []))
                (IntegerConstant 54 (Integer 4 []))
                (IntegerConstant 55 (Integer 4 []))
                (IntegerConstant 56 (Integer 4 []))
                (IntegerConstant 57 (Integer 4 []))
                (IntegerConstant 58 (Integer 4 []))
                (IntegerConstant 59 (Integer 4 []))]
               (List
                (Integer 4 [])
                )
               )
              (ListConstant
               [(IntegerConstant 60 (Integer 4 []))
                (IntegerConstant 61 (Integer 4 []))
                (IntegerConstant 62 (Integer 4 []))
                (IntegerConstant 63 (Integer 4 []))
                (IntegerConstant 64 (Integer 4 []))
                (IntegerConstant 65 (Integer 4 []))
                (IntegerConstant 66 (Integer 4 []))
                (IntegerConstant 67 (Integer 4 []))
                (IntegerConstant 68 (Integer 4 []))
                (IntegerConstant 69 (Integer 4 []))]
               (List
                (Integer 4 [])
                )
               )
              (ListConstant
               [(IntegerConstant 70 (Integer 4 []))
                (IntegerConstant 71 (Integer 4 []))
                (IntegerConstant 72 (Integer 4 []))
                (IntegerConstant 73 (Integer 4 []))
                (IntegerConstant 74 (Integer 4 []))
                (IntegerConstant 75 (Integer 4 []))
                (IntegerConstant 76 (Integer 4 []))
                (IntegerConstant 77 (Integer 4 []))
                (IntegerConstant 78 (Integer 4 []))
                (IntegerConstant 79 (Integer 4 []))]
               (List
                (Integer 4 [])
                )
               )
              (ListConstant
               [(IntegerConstant 80 (Integer 4 []))
                (IntegerConstant 81 (Integer 4 []))
                (IntegerConstant 82 (Integer 4 []))
                (IntegerConstant 83 (Integer 4 []))
                (IntegerConstant 84 (Integer 4 []))
                (IntegerConstant 85 (Integer 4 []))
                (IntegerConstant 86 (Integer 4 []))
                (IntegerConstant 87 (Integer 4 []))
                (IntegerConstant 88 (Integer 4 []))
                (IntegerConstant 89 (Integer 4 []))]
               (List
                (Integer 4 [])
                )
               )
              (ListConstant
               [(IntegerConstant 90 (Integer 4 []))
                (IntegerConstant 91 (Integer 4 []))
                (IntegerConstant 92 (Integer 4 []))
                (IntegerConstant 93 (Integer 4 []))
                (IntegerConstant 94 (Integer 4 []))
                (IntegerConstant 95 (Integer 4 []))
                (IntegerConstant 96 (Integer 4 []))
                (IntegerConstant 97 (Integer 4 []))
                (IntegerConstant 98 (Integer 4 []))
                (IntegerConstant 99 (Integer 4 []))]
               (List
                (Integer 4 [])
                )
               )]
             (List
              (List
               (Integer 4 [])
               )
              )
             )
            ()
            )
           (=
            (Var 5 q)
            (ListConstant
             [(ListConstant
               [(ListConstant
                 [(ListConstant
                   [(RealConstant
                     74.550000
                     (Real 8 [])
                     )
                    (RealUnaryMinus
                     (RealConstant
                      77.640000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -77.640000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     52.350000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      78.250000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -78.250000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      19.240000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -19.240000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     81.380000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealConstant
                     7.860000
                     (Real 8 [])
                     )
                    (RealConstant
                     12.110000
                     (Real 8 [])
                     )
                    (RealConstant
                     27.500000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )]
                 (List
                  (List
                   (Real 8 [])
                   )
                  )
                 )
                (ListConstant
                 [(ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      98.930000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -98.930000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      79.620000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -79.620000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      73.760000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -73.760000
                      (Real 8 [])
                      )
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealConstant
                     42.050000
                     (Real 8 [])
                     )
                    (RealUnaryMinus
                     (RealConstant
                      90.290000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -90.290000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     69.950000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealConstant
                     59.320000
                     (Real 8 [])
                     )
                    (RealUnaryMinus
                     (RealConstant
                      70.780000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -70.780000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      53.220000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -53.220000
                      (Real 8 [])
                      )
                     )]
                   (List
                    (Real 8 [])
                    )
                   )]
                 (List
                  (List
                   (Real 8 [])
                   )
                  )
                 )
                (ListConstant
                 [(ListConstant
                   [(RealConstant
                     53.520000
                     (Real 8 [])
                     )
                    (RealUnaryMinus
                     (RealConstant
                      93.880000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -93.880000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     49.650000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealConstant
                     2.180000
                     (Real 8 [])
                     )
                    (RealConstant
                     19.910000
                     (Real 8 [])
                     )
                    (RealConstant
                     69.240000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealConstant
                     78.510000
                     (Real 8 [])
                     )
                    (RealConstant
                     89.690000
                     (Real 8 [])
                     )
                    (RealUnaryMinus
                     (RealConstant
                      86.680000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -86.680000
                      (Real 8 [])
                      )
                     )]
                   (List
                    (Real 8 [])
                    )
                   )]
                 (List
                  (List
                   (Real 8 [])
                   )
                  )
                 )]
               (List
                (List
                 (List
                  (Real 8 [])
                  )
                 )
                )
               )
              (ListConstant
               [(ListConstant
                 [(ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      92.480000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -92.480000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      80.750000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -80.750000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      27.760000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -27.760000
                      (Real 8 [])
                      )
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      13.350000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -13.350000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     12.280000
                     (Real 8 [])
                     )
                    (RealConstant
                     79.610000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealConstant
                     48.480000
                     (Real 8 [])
                     )
                    (RealUnaryMinus
                     (RealConstant
                      10.490000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -10.490000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     41.100000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )]
                 (List
                  (List
                   (Real 8 [])
                   )
                  )
                 )
                (ListConstant
                 [(ListConstant
                   [(RealConstant
                     20.330000
                     (Real 8 [])
                     )
                    (RealConstant
                     14.520000
                     (Real 8 [])
                     )
                    (RealConstant
                     82.560000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      57.760000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -57.760000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     76.030000
                     (Real 8 [])
                     )
                    (RealConstant
                     67.330000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      21.500000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -21.500000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      11.920000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -11.920000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      31.840000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -31.840000
                      (Real 8 [])
                      )
                     )]
                   (List
                    (Real 8 [])
                    )
                   )]
                 (List
                  (List
                   (Real 8 [])
                   )
                  )
                 )
                (ListConstant
                 [(ListConstant
                   [(RealConstant
                     9.650000
                     (Real 8 [])
                     )
                    (RealConstant
                     11.150000
                     (Real 8 [])
                     )
                    (RealUnaryMinus
                     (RealConstant
                      36.580000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -36.580000
                      (Real 8 [])
                      )
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealConstant
                     56.550000
                     (Real 8 [])
                     )
                    (RealUnaryMinus
                     (RealConstant
                      70.850000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -70.850000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      50.670000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -50.670000
                      (Real 8 [])
                      )
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealConstant
                     94.630000
                     (Real 8 [])
                     )
                    (RealConstant
                     25.680000
                     (Real 8 [])
                     )
                    (RealConstant
                     89.300000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )]
                 (List
                  (List
                   (Real 8 [])
                   )
                  )
                 )]
               (List
                (List
                 (List
                  (Real 8 [])
                  )
                 )
                )
               )
              (ListConstant
               [(ListConstant
                 [(ListConstant
                   [(RealConstant
                     28.530000
                     (Real 8 [])
                     )
                    (RealConstant
                     71.220000
                     (Real 8 [])
                     )
                    (RealConstant
                     71.870000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      58.390000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -58.390000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      6.960000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -6.960000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     42.980000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      8.950000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -8.950000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      75.090000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -75.090000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     37.960000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )]
                 (List
                  (List
                   (Real 8 [])
                   )
                  )
                 )
                (ListConstant
                 [(ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      31.750000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -31.750000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     67.330000
                     (Real 8 [])
                     )
                    (RealConstant
                     58.170000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      64.010000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -64.010000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      9.220000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -9.220000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     9.590000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealConstant
                     15.710000
                     (Real 8 [])
                     )
                    (RealConstant
                     82.360000
                     (Real 8 [])
                     )
                    (RealConstant
                     51.180000
                     (Real 8 [])
                     )]
                   (List
                    (Real 8 [])
                    )
                   )]
                 (List
                  (List
                   (Real 8 [])
                   )
                  )
                 )
                (ListConstant
                 [(ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      100.290000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -100.290000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      32.720000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -32.720000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      54.940000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -54.940000
                      (Real 8 [])
                      )
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealUnaryMinus
                     (RealConstant
                      0.320000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -0.320000
                      (Real 8 [])
                      )
                     )
                    (RealConstant
                     68.810000
                     (Real 8 [])
                     )
                    (RealUnaryMinus
                     (RealConstant
                      55.090000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -55.090000
                      (Real 8 [])
                      )
                     )]
                   (List
                    (Real 8 [])
                    )
                   )
                  (ListConstant
                   [(RealConstant
                     97.280000
                     (Real 8 [])
                     )
                    (RealUnaryMinus
                     (RealConstant
                      28.200000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -28.200000
                      (Real 8 [])
                      )
                     )
                    (RealUnaryMinus
                     (RealConstant
                      62.610000
                      (Real 8 [])
                      )
                     (Real 8 [])
                     (RealConstant
                      -62.610000
                      (Real 8 [])
                      )
                     )]
                   (List
                    (Real 8 [])
                    )
                   )]
                 (List
                  (List
                   (Real 8 [])
                   )
                  )
                 )]
               (List
                (List
                 (List
                  (Real 8 [])
                  )
                 )
                )
               )]
             (List
              (List
               (List
                (List
                 (Real 8 [])
                 )
                )
               )
              )
             )
            ()
            )
           (=
            (Var 5 r)
            (ListConstant
             [(ListConstant
               [(ListConstant
                 [(StringConstant
                   "Io"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "tl"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "bLvjV"
                   (Character 1 5 () [])
                   )
                  (StringConstant
                   "wjFKQ"
                   (Character 1 5 () [])
                   )
                  (StringConstant
                   "lY2"
                   (Character 1 3 () [])
                   )]
                 (List
                  (Character 1 2 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "Be2l6bqE"
                   (Character 1 8 () [])
                   )
                  (StringConstant
                   "pQER3utIXA"
                   (Character 1 10 () [])
                   )
                  (StringConstant
                   "llZBJj5Cdu"
                   (Character 1 10 () [])
                   )
                  (StringConstant
                   "C8"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "gwTr77PdYR"
                   (Character 1 10 () [])
                   )]
                 (List
                  (Character 1 8 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "4M6L"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "ktPdowqERy"
                   (Character 1 10 () [])
                   )
                  (StringConstant
                   "KSifqTkR"
                   (Character 1 8 () [])
                   )
                  (StringConstant
                   "ZE2p1N78f1"
                   (Character 1 10 () [])
                   )
                  (StringConstant
                   "Mi5e87Xw"
                   (Character 1 8 () [])
                   )]
                 (List
                  (Character 1 4 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "uwfzqDq9g"
                   (Character 1 9 () [])
                   )
                  (StringConstant
                   "QaM1s"
                   (Character 1 5 () [])
                   )
                  (StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   "LB"
                   (Character 1 2 () [])
                   )]
                 (List
                  (Character 1 9 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "OJFRY6k"
                   (Character 1 7 () [])
                   )
                  (StringConstant
                   "iz7Oie"
                   (Character 1 6 () [])
                   )
                  (StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   "LUYLF"
                   (Character 1 5 () [])
                   )
                  (StringConstant
                   "JBND5FuV7l"
                   (Character 1 10 () [])
                   )]
                 (List
                  (Character 1 7 () [])
                  )
                 )]
               (List
                (List
                 (Character 1 2 () [])
                 )
                )
               )
              (ListConstant
               [(ListConstant
                 [(StringConstant
                   "m"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "WIQBQfV"
                   (Character 1 7 () [])
                   )
                  (StringConstant
                   "jxjDrqxu"
                   (Character 1 8 () [])
                   )
                  (StringConstant
                   "kea"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "mu"
                   (Character 1 2 () [])
                   )]
                 (List
                  (Character 1 1 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   "GI8aOwLMe"
                   (Character 1 9 () [])
                   )
                  (StringConstant
                   "Y5m8"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "a02Rz"
                   (Character 1 5 () [])
                   )
                  (StringConstant
                   "xNKCJ"
                   (Character 1 5 () [])
                   )]
                 (List
                  (Character 1 0 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "LzkhyiJQHP"
                   (Character 1 10 () [])
                   )
                  (StringConstant
                   "uzc3xyoXL"
                   (Character 1 9 () [])
                   )
                  (StringConstant
                   "sKGnYfpRy"
                   (Character 1 9 () [])
                   )
                  (StringConstant
                   "7x"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "WTVKrnPO"
                   (Character 1 8 () [])
                   )]
                 (List
                  (Character 1 10 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "TZa6"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "GXRuyRX"
                   (Character 1 7 () [])
                   )
                  (StringConstant
                   "R"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "JQxS"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "OH"
                   (Character 1 2 () [])
                   )]
                 (List
                  (Character 1 4 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "bSVJZ1OQ"
                   (Character 1 8 () [])
                   )
                  (StringConstant
                   "M"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "I9omlF"
                   (Character 1 6 () [])
                   )
                  (StringConstant
                   "x7FR"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "XtpL"
                   (Character 1 4 () [])
                   )]
                 (List
                  (Character 1 8 () [])
                  )
                 )]
               (List
                (List
                 (Character 1 1 () [])
                 )
                )
               )
              (ListConstant
               [(ListConstant
                 [(StringConstant
                   "DKOpK"
                   (Character 1 5 () [])
                   )
                  (StringConstant
                   "eg8Nz"
                   (Character 1 5 () [])
                   )
                  (StringConstant
                   "ru"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "Sj"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "YUDxyI"
                   (Character 1 6 () [])
                   )]
                 (List
                  (Character 1 5 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "Q5uyhvp"
                   (Character 1 7 () [])
                   )
                  (StringConstant
                   "Ydx"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "p"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "DLM5RX"
                   (Character 1 6 () [])
                   )
                  (StringConstant
                   "pwOujxCO"
                   (Character 1 8 () [])
                   )]
                 (List
                  (Character 1 7 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "s5GOWnNJV"
                   (Character 1 9 () [])
                   )
                  (StringConstant
                   "af"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "KAkD"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "4IIZK"
                   (Character 1 5 () [])
                   )
                  (StringConstant
                   "JQK040x"
                   (Character 1 7 () [])
                   )]
                 (List
                  (Character 1 9 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "9vF"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "9pc7R8v"
                   (Character 1 7 () [])
                   )
                  (StringConstant
                   "nDReIU7"
                   (Character 1 7 () [])
                   )
                  (StringConstant
                   "K"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "btn"
                   (Character 1 3 () [])
                   )]
                 (List
                  (Character 1 3 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   "wVeivkdi"
                   (Character 1 8 () [])
                   )
                  (StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   "C"
                   (Character 1 1 () [])
                   )]
                 (List
                  (Character 1 0 () [])
                  )
                 )]
               (List
                (List
                 (Character 1 5 () [])
                 )
                )
               )
              (ListConstant
               [(ListConstant
                 [(StringConstant
                   "vNTtcRXD"
                   (Character 1 8 () [])
                   )
                  (StringConstant
                   "rsi"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "YsoF7mZD"
                   (Character 1 8 () [])
                   )
                  (StringConstant
                   "VrPXU50rgA"
                   (Character 1 10 () [])
                   )
                  (StringConstant
                   "mG7zqN0G"
                   (Character 1 8 () [])
                   )]
                 (List
                  (Character 1 8 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "la7cJ"
                   (Character 1 5 () [])
                   )
                  (StringConstant
                   "M5rLJ8Go"
                   (Character 1 8 () [])
                   )
                  (StringConstant
                   "gb"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "FjKwYZ7E"
                   (Character 1 8 () [])
                   )
                  (StringConstant
                   "uSPD"
                   (Character 1 4 () [])
                   )]
                 (List
                  (Character 1 5 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   "oOa79jWcMx"
                   (Character 1 10 () [])
                   )
                  (StringConstant
                   "yyAYZZ"
                   (Character 1 6 () [])
                   )
                  (StringConstant
                   "wbvggXm"
                   (Character 1 7 () [])
                   )
                  (StringConstant
                   "aE3BkCL4"
                   (Character 1 8 () [])
                   )]
                 (List
                  (Character 1 0 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "RdP"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "Hwc0x9RZ"
                   (Character 1 8 () [])
                   )
                  (StringConstant
                   "sy"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "9"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "W1d9xA2BXe"
                   (Character 1 10 () [])
                   )]
                 (List
                  (Character 1 3 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "A"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   "QnK"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "N5tzN"
                   (Character 1 5 () [])
                   )
                  (StringConstant
                   "ou7Lp"
                   (Character 1 5 () [])
                   )]
                 (List
                  (Character 1 1 () [])
                  )
                 )]
               (List
                (List
                 (Character 1 8 () [])
                 )
                )
               )
              (ListConstant
               [(ListConstant
                 [(StringConstant
                   "DL68rDF"
                   (Character 1 7 () [])
                   )
                  (StringConstant
                   "v"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "kQ3Mxm"
                   (Character 1 6 () [])
                   )
                  (StringConstant
                   "g"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "6KTeF4Eo"
                   (Character 1 8 () [])
                   )]
                 (List
                  (Character 1 7 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "Hx9"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "Y1IzQm85Z4"
                   (Character 1 10 () [])
                   )
                  (StringConstant
                   "3D8"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "ZLZ5"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "rWn"
                   (Character 1 3 () [])
                   )]
                 (List
                  (Character 1 3 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "LtT"
                   (Character 1 3 () [])
                   )
                  (StringConstant
                   "Dh5B"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "M"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "F"
                   (Character 1 1 () [])
                   )
                  (StringConstant
                   "QTARbY"
                   (Character 1 6 () [])
                   )]
                 (List
                  (Character 1 3 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   "Sh"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "WL"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   "yvAfWvZSx1"
                   (Character 1 10 () [])
                   )
                  (StringConstant
                   "90yx"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "v"
                   (Character 1 1 () [])
                   )]
                 (List
                  (Character 1 2 () [])
                  )
                 )
                (ListConstant
                 [(StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   "7IBW"
                   (Character 1 4 () [])
                   )
                  (StringConstant
                   "nI"
                   (Character 1 2 () [])
                   )
                  (StringConstant
                   ""
                   (Character 1 0 () [])
                   )
                  (StringConstant
                   "6Cbp5c8RT"
                   (Character 1 9 () [])
                   )]
                 (List
                  (Character 1 0 () [])
                  )
                 )]
               (List
                (List
                 (Character 1 7 () [])
                 )
                )
               )]
             (List
              (List
               (List
                (Character 1 2 () [])
                )
               )
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
            ((Var 5 __list_iterator)
             (IntegerConstant 0 (Integer 4 []))
             (IntegerBinOp
              (ListLen
               (Var 5 p)
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
              ((Var 5 __list_iterator1)
               (IntegerConstant 0 (Integer 4 []))
               (IntegerBinOp
                (ListLen
                 (ListItem
                  (Var 5 p)
                  (Var 5 __list_iterator)
                  (List
                   (Integer 4 [])
                   )
                  ()
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
                  (ListItem
                   (Var 5 p)
                   (Var 5 __list_iterator)
                   (List
                    (Integer 4 [])
                    )
                   ()
                   )
                  (Var 5 __list_iterator1)
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
                 (Var 5 __list_iterator1)
                 Lt
                 (IntegerBinOp
                  (ListLen
                   (ListItem
                    (Var 5 p)
                    (Var 5 __list_iterator)
                    (List
                     (Integer 4 [])
                     )
                    ()
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
               ""
               (Character 1 0 () [])
               )
              )
             (If
              (IntegerCompare
               (Var 5 __list_iterator)
               Lt
               (IntegerBinOp
                (ListLen
                 (Var 5 p)
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
            ((Var 5 __list_iterator2)
             (IntegerConstant 0 (Integer 4 []))
             (IntegerBinOp
              (ListLen
               (Var 5 q)
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
              ((Var 5 __list_iterator3)
               (IntegerConstant 0 (Integer 4 []))
               (IntegerBinOp
                (ListLen
                 (ListItem
                  (Var 5 q)
                  (Var 5 __list_iterator2)
                  (List
                   (List
                    (List
                     (Real 8 [])
                     )
                    )
                   )
                  ()
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
                ((Var 5 __list_iterator4)
                 (IntegerConstant 0 (Integer 4 []))
                 (IntegerBinOp
                  (ListLen
                   (ListItem
                    (ListItem
                     (Var 5 q)
                     (Var 5 __list_iterator2)
                     (List
                      (List
                       (List
                        (Real 8 [])
                        )
                       )
                      )
                     ()
                     )
                    (Var 5 __list_iterator3)
                    (List
                     (List
                      (Real 8 [])
                      )
                     )
                    ()
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
                  ((Var 5 __list_iterator5)
                   (IntegerConstant 0 (Integer 4 []))
                   (IntegerBinOp
                    (ListLen
                     (ListItem
                      (ListItem
                       (ListItem
                        (Var 5 q)
                        (Var 5 __list_iterator2)
                        (List
                         (List
                          (List
                           (Real 8 [])
                           )
                          )
                         )
                        ()
                        )
                       (Var 5 __list_iterator3)
                       (List
                        (List
                         (Real 8 [])
                         )
                        )
                       ()
                       )
                      (Var 5 __list_iterator4)
                      (List
                       (Real 8 [])
                       )
                      ()
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
                      (ListItem
                       (ListItem
                        (ListItem
                         (Var 5 q)
                         (Var 5 __list_iterator2)
                         (List
                          (List
                           (List
                            (Real 8 [])
                            )
                           )
                          )
                         ()
                         )
                        (Var 5 __list_iterator3)
                        (List
                         (List
                          (Real 8 [])
                          )
                         )
                        ()
                        )
                       (Var 5 __list_iterator4)
                       (List
                        (Real 8 [])
                        )
                       ()
                       )
                      (Var 5 __list_iterator5)
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
                     (Var 5 __list_iterator5)
                     Lt
                     (IntegerBinOp
                      (ListLen
                       (ListItem
                        (ListItem
                         (ListItem
                          (Var 5 q)
                          (Var 5 __list_iterator2)
                          (List
                           (List
                            (List
                             (Real 8 [])
                             )
                            )
                           )
                          ()
                          )
                         (Var 5 __list_iterator3)
                         (List
                          (List
                           (Real 8 [])
                           )
                          )
                         ()
                         )
                        (Var 5 __list_iterator4)
                        (List
                         (Real 8 [])
                         )
                        ()
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
                   ""
                   (Character 1 0 () [])
                   )
                  )
                 (If
                  (IntegerCompare
                   (Var 5 __list_iterator4)
                   Lt
                   (IntegerBinOp
                    (ListLen
                     (ListItem
                      (ListItem
                       (Var 5 q)
                       (Var 5 __list_iterator2)
                       (List
                        (List
                         (List
                          (Real 8 [])
                          )
                         )
                        )
                       ()
                       )
                      (Var 5 __list_iterator3)
                      (List
                       (List
                        (Real 8 [])
                        )
                       )
                      ()
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
                 ""
                 (Character 1 0 () [])
                 )
                )
               (If
                (IntegerCompare
                 (Var 5 __list_iterator3)
                 Lt
                 (IntegerBinOp
                  (ListLen
                   (ListItem
                    (Var 5 q)
                    (Var 5 __list_iterator2)
                    (List
                     (List
                      (List
                       (Real 8 [])
                       )
                      )
                     )
                    ()
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
               ""
               (Character 1 0 () [])
               )
              )
             (If
              (IntegerCompare
               (Var 5 __list_iterator2)
               Lt
               (IntegerBinOp
                (ListLen
                 (Var 5 q)
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
            ((Var 5 __list_iterator6)
             (IntegerConstant 0 (Integer 4 []))
             (IntegerBinOp
              (ListLen
               (Var 5 r)
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
              ((Var 5 __list_iterator7)
               (IntegerConstant 0 (Integer 4 []))
               (IntegerBinOp
                (ListLen
                 (ListItem
                  (Var 5 r)
                  (Var 5 __list_iterator6)
                  (List
                   (List
                    (Character 1 -2 () [])
                    )
                   )
                  ()
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
                ((Var 5 __list_iterator8)
                 (IntegerConstant 0 (Integer 4 []))
                 (IntegerBinOp
                  (ListLen
                   (ListItem
                    (ListItem
                     (Var 5 r)
                     (Var 5 __list_iterator6)
                     (List
                      (List
                       (Character 1 -2 () [])
                       )
                      )
                     ()
                     )
                    (Var 5 __list_iterator7)
                    (List
                     (Character 1 -2 () [])
                     )
                    ()
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
                    (ListItem
                     (ListItem
                      (Var 5 r)
                      (Var 5 __list_iterator6)
                      (List
                       (List
                        (Character 1 -2 () [])
                        )
                       )
                      ()
                      )
                     (Var 5 __list_iterator7)
                     (List
                      (Character 1 -2 () [])
                      )
                     ()
                     )
                    (Var 5 __list_iterator8)
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
                   (Var 5 __list_iterator8)
                   Lt
                   (IntegerBinOp
                    (ListLen
                     (ListItem
                      (ListItem
                       (Var 5 r)
                       (Var 5 __list_iterator6)
                       (List
                        (List
                         (Character 1 -2 () [])
                         )
                        )
                       ()
                       )
                      (Var 5 __list_iterator7)
                      (List
                       (Character 1 -2 () [])
                       )
                      ()
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
                 ""
                 (Character 1 0 () [])
                 )
                )
               (If
                (IntegerCompare
                 (Var 5 __list_iterator7)
                 Lt
                 (IntegerBinOp
                  (ListLen
                   (ListItem
                    (Var 5 r)
                    (Var 5 __list_iterator6)
                    (List
                     (List
                      (Character 1 -2 () [])
                      )
                     )
                    ()
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
               ""
               (Character 1 0 () [])
               )
              )
             (If
              (IntegerCompare
               (Var 5 __list_iterator6)
               Lt
               (IntegerBinOp
                (ListLen
                 (Var 5 r)
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
          ),
         #_:test_print_list_tuple
         #_(Function
          (SymbolTable
           4
           {
            :__list_iterator
            (Variable
             4
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
             4
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
            :__list_iterator2
            (Variable
             4
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
             4
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
             4
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
             4
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
             4
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
            :a
            (Variable
             4
             a
             []
             Local
             ()
             ()
             Default
             (List
              (Tuple
               [(Integer 4 [])
                (Integer 4 [])]
               )
              )
             Source
             Public
             Required
             false
             ),
            :b
            (Variable
             4
             b
             []
             Local
             ()
             ()
             Default
             (Tuple
              [(List
                (Character 1 -2 () [])
                )
               (List
                (Integer 4 [])
                )
               (Real 8 [])]
              )
             Source
             Public
             Required
             false
             ),
            :b1
            (Variable
             4
             b1
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
            :b2
            (Variable
             4
             b2
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
             4
             c
             []
             Local
             ()
             ()
             Default
             (List
              (List
               (Tuple
                [(Integer 4 [])
                 (Character 1 -2 () [])]
                )
               )
              )
             Source
             Public
             Required
             false
             )
            })
          test_print_list_tuple
          (FunctionType
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
          []
          []
          [(=
            (Var 4 a)
            (ListConstant
             [(TupleConstant
               [(IntegerConstant 1 (Integer 4 []))
                (IntegerConstant 2 (Integer 4 []))]
               (Tuple
                [(Integer 4 [])
                 (Integer 4 [])]
                )
               )
              (TupleConstant
               [(IntegerConstant 3 (Integer 4 []))
                (IntegerConstant 4 (Integer 4 []))]
               (Tuple
                [(Integer 4 [])
                 (Integer 4 [])]
                )
               )
              (TupleConstant
               [(IntegerConstant 5 (Integer 4 []))
                (IntegerConstant 6 (Integer 4 []))]
               (Tuple
                [(Integer 4 [])
                 (Integer 4 [])]
                )
               )]
             (List
              (Tuple
               [(Integer 4 [])
                (Integer 4 [])]
               )
              )
             )
            ()
            )
           (=
            (Var 4 c)
            (ListConstant
             [(ListConstant
               [(TupleConstant
                 [(IntegerConstant 1 (Integer 4 []))
                  (StringConstant
                   "a"
                   (Character 1 1 () [])
                   )]
                 (Tuple
                  [(Integer 4 [])
                   (Character 1 1 () [])]
                  )
                 )
                (TupleConstant
                 [(IntegerConstant 2 (Integer 4 []))
                  (StringConstant
                   "b"
                   (Character 1 1 () [])
                   )]
                 (Tuple
                  [(Integer 4 [])
                   (Character 1 1 () [])]
                  )
                 )]
               (List
                (Tuple
                 [(Integer 4 [])
                  (Character 1 1 () [])]
                 )
                )
               )
              (ListConstant
               [(TupleConstant
                 [(IntegerConstant 3 (Integer 4 []))
                  (StringConstant
                   "c"
                   (Character 1 1 () [])
                   )]
                 (Tuple
                  [(Integer 4 [])
                   (Character 1 1 () [])]
                  )
                 )
                (TupleConstant
                 [(IntegerConstant 4 (Integer 4 []))
                  (StringConstant
                   "d"
                   (Character 1 1 () [])
                   )]
                 (Tuple
                  [(Integer 4 [])
                   (Character 1 1 () [])]
                  )
                 )]
               (List
                (Tuple
                 [(Integer 4 [])
                  (Character 1 1 () [])]
                 )
                )
               )]
             (List
              (List
               (Tuple
                [(Integer 4 [])
                 (Character 1 1 () [])]
                )
               )
              )
             )
            ()
            )
           (=
            (Var 4 b1)
            (ListConstant
             [(StringConstant
               "a"
               (Character 1 1 () [])
               )
              (StringConstant
               "bb"
               (Character 1 2 () [])
               )
              (StringConstant
               "ccc"
               (Character 1 3 () [])
               )
              (StringConstant
               "dddd"
               (Character 1 4 () [])
               )
              (StringConstant
               "eeeee"
               (Character 1 5 () [])
               )]
             (List
              (Character 1 1 () [])
              )
             )
            ()
            )
           (=
            (Var 4 b2)
            (ListConstant
             [(IntegerConstant 10 (Integer 4 []))
              (IntegerConstant 20 (Integer 4 []))
              (IntegerConstant 30 (Integer 4 []))
              (IntegerConstant 40 (Integer 4 []))]
             (List
              (Integer 4 [])
              )
             )
            ()
            )
           (=
            (Var 4 b)
            (TupleConstant
             [(Var 4 b1)
              (Var 4 b2)
              (RealConstant
               6.030500
               (Real 8 [])
               )]
             (Tuple
              [(List
                (Character 1 -2 () [])
                )
               (List
                (Integer 4 [])
                )
               (Real 8 [])]
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
            ((Var 4 __list_iterator)
             (IntegerConstant 0 (Integer 4 []))
             (IntegerBinOp
              (ListLen
               (Var 4 a)
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
                "("
                (Character 1 1 () [])
                )]
              ()
              (StringConstant
               ""
               (Character 1 0 () [])
               )
              )
             (Print
              ()
              [(TupleItem
                (ListItem
                 (Var 4 a)
                 (Var 4 __list_iterator)
                 (Tuple
                  [(Integer 4 [])
                   (Integer 4 [])]
                  )
                 ()
                 )
                (IntegerConstant 0 (Integer 4 []))
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
             (Print
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
              )
             (Print
              ()
              [(TupleItem
                (ListItem
                 (Var 4 a)
                 (Var 4 __list_iterator)
                 (Tuple
                  [(Integer 4 [])
                   (Integer 4 [])]
                  )
                 ()
                 )
                (IntegerConstant 1 (Integer 4 []))
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
             (Print
              ()
              [(StringConstant
                ")"
                (Character 1 1 () [])
                )]
              ()
              (StringConstant
               ""
               (Character 1 0 () [])
               )
              )
             (If
              (IntegerCompare
               (Var 4 __list_iterator)
               Lt
               (IntegerBinOp
                (ListLen
                 (Var 4 a)
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
              "("
              (Character 1 1 () [])
              )]
            ()
            (StringConstant
             ""
             (Character 1 0 () [])
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
            ((Var 4 __list_iterator1)
             (IntegerConstant 0 (Integer 4 []))
             (IntegerBinOp
              (ListLen
               (TupleItem
                (Var 4 b)
                (IntegerConstant 0 (Integer 4 []))
                (List
                 (Character 1 -2 () [])
                 )
                ()
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
                (TupleItem
                 (Var 4 b)
                 (IntegerConstant 0 (Integer 4 []))
                 (List
                  (Character 1 -2 () [])
                  )
                 ()
                 )
                (Var 4 __list_iterator1)
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
               (Var 4 __list_iterator1)
               Lt
               (IntegerBinOp
                (ListLen
                 (TupleItem
                  (Var 4 b)
                  (IntegerConstant 0 (Integer 4 []))
                  (List
                   (Character 1 -2 () [])
                   )
                  ()
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
             ""
             (Character 1 0 () [])
             )
            )
           (Print
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
            ((Var 4 __list_iterator2)
             (IntegerConstant 0 (Integer 4 []))
             (IntegerBinOp
              (ListLen
               (TupleItem
                (Var 4 b)
                (IntegerConstant 1 (Integer 4 []))
                (List
                 (Integer 4 [])
                 )
                ()
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
                (TupleItem
                 (Var 4 b)
                 (IntegerConstant 1 (Integer 4 []))
                 (List
                  (Integer 4 [])
                  )
                 ()
                 )
                (Var 4 __list_iterator2)
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
               (Var 4 __list_iterator2)
               Lt
               (IntegerBinOp
                (ListLen
                 (TupleItem
                  (Var 4 b)
                  (IntegerConstant 1 (Integer 4 []))
                  (List
                   (Integer 4 [])
                   )
                  ()
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
             ""
             (Character 1 0 () [])
             )
            )
           (Print
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
            )
           (Print
            ()
            [(TupleItem
              (Var 4 b)
              (IntegerConstant 2 (Integer 4 []))
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
           (Print
            ()
            [(StringConstant
              ")"
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
            ((Var 4 __list_iterator3)
             (IntegerConstant 0 (Integer 4 []))
             (IntegerBinOp
              (ListLen
               (Var 4 c)
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
              ((Var 4 __list_iterator4)
               (IntegerConstant 0 (Integer 4 []))
               (IntegerBinOp
                (ListLen
                 (ListItem
                  (Var 4 c)
                  (Var 4 __list_iterator3)
                  (List
                   (Tuple
                    [(Integer 4 [])
                     (Character 1 -2 () [])]
                    )
                   )
                  ()
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
                  "("
                  (Character 1 1 () [])
                  )]
                ()
                (StringConstant
                 ""
                 (Character 1 0 () [])
                 )
                )
               (Print
                ()
                [(TupleItem
                  (ListItem
                   (ListItem
                    (Var 4 c)
                    (Var 4 __list_iterator3)
                    (List
                     (Tuple
                      [(Integer 4 [])
                       (Character 1 -2 () [])]
                      )
                     )
                    ()
                    )
                   (Var 4 __list_iterator4)
                   (Tuple
                    [(Integer 4 [])
                     (Character 1 -2 () [])]
                    )
                   ()
                   )
                  (IntegerConstant 0 (Integer 4 []))
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
               (Print
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
                )
               (Print
                ()
                [(StringConstant
                  "'"
                  (Character 1 1 () [])
                  )
                 (TupleItem
                  (ListItem
                   (ListItem
                    (Var 4 c)
                    (Var 4 __list_iterator3)
                    (List
                     (Tuple
                      [(Integer 4 [])
                       (Character 1 -2 () [])]
                      )
                     )
                    ()
                    )
                   (Var 4 __list_iterator4)
                   (Tuple
                    [(Integer 4 [])
                     (Character 1 -2 () [])]
                    )
                   ()
                   )
                  (IntegerConstant 1 (Integer 4 []))
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
               (Print
                ()
                [(StringConstant
                  ")"
                  (Character 1 1 () [])
                  )]
                ()
                (StringConstant
                 ""
                 (Character 1 0 () [])
                 )
                )
               (If
                (IntegerCompare
                 (Var 4 __list_iterator4)
                 Lt
                 (IntegerBinOp
                  (ListLen
                   (ListItem
                    (Var 4 c)
                    (Var 4 __list_iterator3)
                    (List
                     (Tuple
                      [(Integer 4 [])
                       (Character 1 -2 () [])]
                      )
                     )
                    ()
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
               ""
               (Character 1 0 () [])
               )
              )
             (If
              (IntegerCompare
               (Var 4 __list_iterator3)
               Lt
               (IntegerBinOp
                (ListLen
                 (Var 4 c)
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
            ((Var 4 __list_iterator5)
             (IntegerConstant 0 (Integer 4 []))
             (IntegerBinOp
              (ListLen
               (Var 4 b1)
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
                (Var 4 b1)
                (Var 4 __list_iterator5)
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
               (Var 4 __list_iterator5)
               Lt
               (IntegerBinOp
                (ListLen
                 (Var 4 b1)
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
            ((Var 4 __list_iterator6)
             (IntegerConstant 0 (Integer 4 []))
             (IntegerBinOp
              (ListLen
               (Var 4 b2)
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
                (Var 4 b2)
                (Var 4 __list_iterator6)
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
               (Var 4 __list_iterator6)
               Lt
               (IntegerBinOp
                (ListLen
                 (Var 4 b2)
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
              3.420000
              (Real 8 [])
              )
             (StringConstant
              "okay"
              (Character 1 4 () [])
              )
             (LogicalConstant
              true
              (Logical 4 [])
              )
             (IntegerConstant 14483 (Integer 4 []))]
            ()
            ()
            )]
          ()
          Public
          false
          false
          )
         })
       _global_symbols
       []
       false
       false
       ),
      :main_program
      (Program
       (SymbolTable
        7
        {
         :_lpython_main_program
         (ExternalSymbol
          7
          _lpython_main_program
          9 _lpython_main_program
          _global_symbols
          []
          _lpython_main_program
          Public
          )
         })
       main_program
       [_global_symbols]
       [(SubroutineCall
         7 _lpython_main_program
         ()
         []
         ()
         )]
       )
      })
    []
    ))


(deftest big-translation-unit-literal-test
  (testing "bisecting Java method-code-too-large error"
    (is (s/valid? ::asr/TranslationUnit
                  (asr-eval big-translation-unit-literal-sample)))))


(deftest bisecting-1bcc4ec

  (comment "too big for Function")
  #_(is (s/valid? ::asr/Function (asr-eval big-function-sample)))

  (let [test-body (nth big-function-sample 6)]
    (is (s/valid? ::asr/body (asr-eval test-body)))

    (doseq [e test-body]
      (is (s/valid? ::asr/stmt (asr-eval e)))))
  )
