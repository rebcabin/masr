(ns masr.big-test
  (:use     [masr.core]
            ;; https://groups.google.com/g/clojure/c/i770QaIFiF0 :
            [masr.specs             :as    asr       ])

  (:require [clojure.test           :refer :all      ]
            [clojure.spec.alpha     :as    s         ]
            [clojure.spec.gen.alpha :as    gen       ]
            [clojure.pprint         :refer [pprint]  ]
            [clojure.walk           :refer [prewalk] ]
            [clojure.set            :as    set       ]
            [clojure.string :as str])

  (:require [blaster.clj-fstring    :refer [f-str ]  ]
            [camel-snake-kebab.core :as    csk       ])

  (:require [masr.utils             :refer [warnings-banner
                                            delete-directory-recursive
                                            ppdict
                                            mkdict
                                            dosafely
                                            plnecho
                                            plnecho-file
                                            pprint-file]]
            [masr.simplespecs       :refer [nat
                                            identifier
                                            identifier-set
                                            identifier-list
                                            identifier-suit
                                            ]]
            [masr.core-test         :refer [slurp-asr]])
  )


(comment "too big to compile; Slows down Emacs.")
(def big-1bcc4ec-translation-unit
  nil)


(comment "too big to compile; Slows down Emacs.")
(def big-1bcc4ec-module-symbol-table
  nil)


;;
;;
;; # ANALYZERS
;;
;;


;;
;; Some ASR S-expressions are too large to compile,
;; meaning too large for a Java method code
;; block (64KB). We must analyze such by breaking
;; them up.
;;


;; ================================================================
;;     _    _   _    _    _  __   ____________ ____  ____
;;    / \  | \ | |  / \  | | \ \ / /__  / ____|  _ \/ ___|
;;   / _ \ |  \| | / _ \ | |  \ V /  / /|  _| | |_) \___ \
;;  / ___ \| |\  |/ ___ \| |___| |  / /_| |___|  _ < ___) |
;; /_/   \_\_| \_/_/   \_\_____|_| /____|_____|_| \_\____/


;;                          _
;;  _____ ____ _ _ __  _ __| |___ ___
;; / -_) \ / _` | '  \| '_ \ / -_|_-<
;; \___/_\_\__,_|_|_|_| .__/_\___/__/
;;                    |_|


(def tuSmallProgram
  '(TranslationUnit
    (SymbolTable 42 {})
    [(Program
      (SymbolTable 3 {})
      main_program
      []
      [(= (Var 2 a)
          (LogicalConstant false (Logical 4 []))
          ())])]))


(def tuSmallProgram-intentionally-wrong-1
  '(TranslationUnit
    (SymbolTable 42 {})
    [(Program
      (SymbolTable 3 {})
      42 ;; main_program
      []
      [(= (Var 2 a)
          (LogicalConstant false (Logical 4 []))
          ())])]))


(def tuMediumProgram
  '(TranslationUnit
    (SymbolTable
     1 {:_global_symbols
        (Module
         (SymbolTable
          4 {:test_boolOp
             (Function
              (SymbolTable
               2 {:a
                  (Variable
                   2 a [] Local
                   () () Default (Logical 4 [])
                   Source Public Required false),
                  :b
                  (Variable
                   2 b [] Local
                   () () Default (Logical 4 [])
                   Source Public Required false)})
              test_boolOp
              (FunctionType
               [] () Source
               Implementation () false
               false false false
               false [] [] false)
              [] []
              [(= (Var 2 a)
                  (LogicalConstant false (Logical 4 [])) ())
               (= (Var 2 b)
                  (LogicalConstant true (Logical 4 []))  ())
               (= (Var 2 a)
                  (LogicalBinOp (Var 2 a) And (Var 2 b)
                                (Logical 4 []) ()) ())
               (= (Var 2 b)
                  (LogicalBinOp
                   (Var 2 a)  Or
                   (LogicalConstant true (Logical 4 []))
                   (Logical 4 []) ()) ())
               (= (Var 2 a)
                  (LogicalBinOp (Var 2 a) Or (Var 2 b)
                                (Logical 4 []) ()) ())
               (= (Var 2 a)
                  (LogicalBinOp
                   (Var 2 a)
                   And
                   (LogicalCompare (Var 2 b) Eq (Var 2 b)
                                   (Logical 4 []) ())
                   (Logical 4 []) ()) ())
               (= (Var 2 a)
                  (LogicalBinOp
                   (Var 2 a) And
                   (LogicalCompare (Var 2 b) NotEq (Var 2 b)
                                   (Logical 4 []) ())
                   (Logical 4 []) ()) ())
               (= (Var 2 a)
                  (LogicalBinOp (Var 2 b) Or (Var 2 b)
                                (Logical 4 []) ()) ())]
              () Public false false)})
         _global_symbols
         [] false false),
        :main_program
        (Program (SymbolTable 3 {})
                 main_program [] [])}) []))


(def big-slurped-1bcc4ec
  (slurp-asr "_pass_print_list_tuple-print_02-1bcc4ec"))


(def big-slurped-fdf30b1
  (slurp-asr "_pass_loop_vectorise-vec_01-fdf30b1"))


(def offending-variable
  '(Variable
    185
    a
    []
    Local
    ()
    ()
    Default
    (Real 8 [((IntegerConstant 0 (Integer 4 []))
              (IntegerConstant 9216 (Integer 4 [])))])
    Source
    Public
    Required
    false
    ))


;;               _         _
;;  _____ ___ __| |___  __| |___
;; / -_) \ / '_ \ / _ \/ _` / -_)
;; \___/_\_\ .__/_\___/\__,_\___|
;;         |_|


(defn transform-when [pred xfn]
  #(if  (pred %)  (xfn %)  %))


(defn visit [pred xfn tree]
  (prewalk (transform-when pred xfn) tree))


(defn detect [term-sym]
  #(and (seq? %) (= (first %) term-sym)))


(defn write-tree-and-replace-fn [sample-nym]
  ;; e.g., sample-nym = 1bcc4ec
  (fn [tree]
    (let [;; e.g. DoLoop
          term     (first tree)
          ;; e.g. DoLoop_1234
          g        (gensym (str term "_"))
          ;; e.g. "resources/1bcc4ec/DoLoop"
          root     (str "resources/" sample-nym)
          _        (.mkdir (java.io.File. root))
          dir      (str root "/" term)
          _        (.mkdir (java.io.File. dir))
          ;; e.g. "resources/1bcc4ec/DoLoop/DoLoop_1234.clj"
          filename (str dir "/" g ".clj")
          ;; e.g. masr.resources.1bcc4ec.DoLoop.DoLoop-1234
          ns-nym   (str "masr.resources."
                        sample-nym "." term "." g)
          etree    (to-full-form tree)
          valid? (s/valid? ::asr/asr-term etree)
          ]
      (spit filename (str "(ns " ns-nym ")\n\n\n"))
      (spit filename (with-out-str
                       (pprint etree)) :append true)
      (spit filename "\n" :append true)
      ;; Return a string for idempotency:
      (str g)
      )))


(defn fd [sample-nym term-sym tree]
  (visit (detect term-sym)
         (write-tree-and-replace-fn sample-nym)
         tree))


(defn explode-fn
  "`nym` should be the name of a case you want to analyze and that
  might be too big for straight evaluation in Java via
  `test-translation-unit` in `core_test.clj`. Examples:
  `tuSmallProgram`, or something slurped from an ASR file and
  def'fed like `big-slurped-1bcc4ec`. `Explode` full-forms the
  sub-terms that you identify below into files in a directory
  named after the case, with sub-sub-terms replaced by strings
  that name other files in the directory tree. Identify the terms
  you want exploded in bottom-up order: finest-grained first. If
  you encounter \"method-too-big\" errors, add more fine-grained
  terms until the errors stop. It's always safe to go finer, so
  you don't have to take out the added terms when you go back to
  explode smaller cases. Build back up full-forms via `implode`."
  ;; e.g., tuSmallProgram, "tuSmallProgram"
  [tree nymstr]
  (let [root   (str "resources/" nymstr)
        _      (dosafely
                (delete-directory-recursive
                 (clojure.java.io/file root)))
        ]
    (->>
     tree
     rewrite-for-legacy
     ;; The following fd's kill `dimension` and `dimensions`
     ;; but we don't need them to beat the big examples (yet).
     ;; (fd nymstr 'IntegerConstant)
     ;; (fd nymstr 'RealConstant)
     ;; (fd nymstr 'ComplexConstant)
     ;; (fd nymstr 'StringConstant)
     ;; (fd nymstr 'LogicalConstant)
     ;; (fd nymstr 'ListConstant)
     ;; (fd nymstr 'TupleConstant)
     (fd nymstr 'Var)
     (fd nymstr 'Variable)
     (fd nymstr 'FunctionType)
     (fd nymstr 'Assignment)
     (fd nymstr 'ExternalSymbol)
     (fd nymstr 'DoLoop)
     (fd nymstr 'Print)
     (fd nymstr 'SubroutineCall)
     (fd nymstr 'Function)
     (fd nymstr 'SymbolTable)
     (fd nymstr 'Program)
     (fd nymstr 'TranslationUnit)
     (str "exploding " nymstr "/")
     pprint)))


(defmacro explode [nym]
  `(explode-fn ~nym (str '~nym)))


;;  _            _         _
;; (_)_ __  _ __| |___  __| |___
;; | | '  \| '_ \ / _ \/ _` / -_)
;; |_|_|_|_| .__/_\___/\__,_\___|
;;         |_|


(def sigilize
  (partial re-find
           #"([a-zA-Z]+)_([0-9][0-9][0-9][0-9][0-9]+)"))


(defn detect-foreign-ref
  [node]
  (and (string? node)
       (sigilize node)))


(defn implode-sigil
  [imploder nymstr [whole head gid] pr-only]
  (imploder nymstr head gid pr-only))


(defn implode-fn
  [nymstr   ;; e.g. "tuSmallProgram"
   term-str ;; e.g. "SymbolTable"
   g-str    ;; e.g. "10068"
   pr-only  ;; e.g. "TranslationUnit"
   ]
  (let [fnym (str term-str "_" g-str ".clj")
        root (str "resources/" nymstr "/" term-str)
        path (str root "/" fnym)
        _    (if (and pr-only
                      (= pr-only term-str))
               (pprint (str "imploding " path)))
        lodd (load-file path)
        rewr (visit detect-foreign-ref
                    #(implode-sigil
                      implode-fn
                      nymstr
                      (sigilize %)
                      pr-only)
                    lodd)
        vld? (s/valid?  ::asr/asr-term rewr)
        ]
    (if (not vld?)
      (let [expl (with-out-str
                   (s/explain ::asr/asr-term rewr))
            failure (mkdict rewr vld? expl)
            fail-log-fnym (str root "/_faillog"
                               "_" g-str ".clj")]
        (pprint (str "writing failure to "
                     fail-log-fnym))
        (plnecho-file fail-log-fnym failure)
        nil)
      (let [succ-log-fnym (str root "/_successlog"
                               "_" g-str ".clj")]
        ;; The big case is 45,000 lines and 10MB.
        ;; It's uninstructive most of the time to write
        ;; it out. Uncomment the following if you care
        ;; to see it.
        #_(if (= term-str pr-only)
          (do (pprint (str "writing success to "
                           succ-log-fnym))
              (pprint-file succ-log-fnym rewr)))
        rewr))
    ))


(defn non-deterministic-implode
  "Pick one of 1-or-more in a directory at random.
  Usable on any term for fine-grained testing."
  [nymstr   ;; e.g. "tuSmallProgram"
   term-str ;; e.g. "SymbolTable"
   ]
  (let [dirc (str "resources/" nymstr "/" term-str)
        dirf (clojure.java.io/file dirc)
        fils (file-seq dirf)
        rfil (rand-nth (rest  fils)) ;; exclude dir itself
        fnym (.getName rfil)
        [whole head gid] (sigilize fnym)
        _    (assert (= head term-str))
        ]
    (implode-fn nymstr term-str gid term-str)))


(defmacro implode-tu [nym]
  `(non-deterministic-implode
    (str '~nym)
    "TranslationUnit"))


(deftest round-trip-test
  (testing "explode"
    #_(is (nil? (explode tuSmallProgram-intentionally-wrong-1)))
    #_(is (nil? (explode tuSmallProgram)))
    #_(is (nil? (explode tuMediumProgram)))
    #_(is (nil? (explode offending-variable)))
    ;; succeeds, but runs a bit long
    #_(is (nil? (explode big-slurped-fdf30b1)))
    ;; succeeds, but takes 45 seconds
    #_(is (nil? (explode big-slurped-1bcc4ec))))
  (testing "implode"
    #_(is (nil? (implode-tu tuSmallProgram-intentionally-wrong-1)))
    #_(is (implode-tu tuSmallProgram))
    #_(is (implode-tu tuMediumProgram))
    #_(is (non-deterministic-implode
           "offending-variable"
           "Variable"))
    ;; succeeds, but runs a bit long
    #_(is (implode-tu big-slurped-fdf30b1))
    ;; succeeds, but takes 45 seconds
    #_(is (implode-tu big-slurped-1bcc4ec)))
  ;; A macro for the following is proving difficult.
  (testing "all-of-them"

    (let [-expr1-dde511e (slurp-asr "-expr1-dde511e")]
      (is (nil? (explode -expr1-dde511e)))
      (is (implode-tu -expr1-dde511e)))

    (let [-expr10-31c163f (slurp-asr "-expr10-31c163f")]
      (is (nil? (explode -expr10-31c163f)))
      (is (implode-tu -expr10-31c163f)))

    (let [-expr11-1134d3f (slurp-asr "-expr11-1134d3f")]
      (is (nil? (explode -expr11-1134d3f)))
      (is (implode-tu -expr11-1134d3f)))

    (let [-expr12-2a30333 (slurp-asr "-expr12-2a30333")]
      (is (nil? (explode -expr12-2a30333)))
      (is (implode-tu -expr12-2a30333)))

    (let [-expr13-10040d8 (slurp-asr "-expr13-10040d8")]
      (is (nil? (explode -expr13-10040d8)))
      (is (implode-tu -expr13-10040d8)))

    (let [-expr4-cf512ef (slurp-asr "-expr4-cf512ef")]
      (is (nil? (explode -expr4-cf512ef)))
      (is (implode-tu -expr4-cf512ef)))

    (let [-expr6-bfb3384 (slurp-asr "-expr6-bfb3384")]
      (is (nil? (explode -expr6-bfb3384)))
      (is (implode-tu -expr6-bfb3384)))

    (let [-expr7-2ef3822 (slurp-asr "-expr7-2ef3822")]
      (is (nil? (explode -expr7-2ef3822)))
      (is (implode-tu -expr7-2ef3822)))

    (let [-expr8-2a4630a (slurp-asr "-expr8-2a4630a")]
      (is (nil? (explode -expr8-2a4630a)))
      (is (implode-tu -expr8-2a4630a)))

    (let [-expr9-c6fe691 (slurp-asr "-expr9-c6fe691")]
      (is (nil? (explode -expr9-c6fe691)))
      (is (implode-tu -expr9-c6fe691)))
    #_(round-trip-tu -expr9-c6fe691)

    (let [-expr_01-03055c0 (slurp-asr "-expr_01-03055c0")]
      (is (nil? (explode -expr_01-03055c0)))
      (is (implode-tu -expr_01-03055c0)))
    #_(round-trip-tu -expr_01-03055c0)

    (let [-expr_01-eafd41c (slurp-asr "-expr_01-eafd41c")]
      (is (nil? (explode -expr_01-eafd41c)))
      (is (implode-tu -expr_01-eafd41c)))
    #_(round-trip-tu -expr_01-eafd41c)

    (let [-expr_14-6023c49 (slurp-asr "-expr_14-6023c49")]
      (is (nil? (explode -expr_14-6023c49)))
      (is (implode-tu -expr_14-6023c49)))
    #_(round-trip-tu -expr_14-6023c49)

    (let [-test_bool_binop-3075d22
          (slurp-asr "-test_bool_binop-3075d22")]
      (is (nil? (explode -test_bool_binop-3075d22)))
      (is (implode-tu -test_bool_binop-3075d22)))
    #_(round-trip-tu -test_bool_binop-3075d22)

    (let [-test_bool_binop-3075d22
          (slurp-asr "-test_bool_binop-3075d22")]
      (is (nil? (explode -test_bool_binop-3075d22)))
      (is (implode-tu -test_bool_binop-3075d22)))
    #_(round-trip-tu -test_bool_binop-3075d22)

    (let [-test_builtin-4f04bbc
          (slurp-asr "-test_builtin-4f04bbc")]
      (is (nil? (explode -test_builtin-4f04bbc)))
      (is (implode-tu -test_builtin-4f04bbc)))
    #_(round-trip-tu -test_builtin-4f04bbc)

    (let [-test_builtin_abs-06a7e49
          (slurp-asr "-test_builtin_abs-06a7e49")]
      (is (nil? (explode -test_builtin_abs-06a7e49)))
      (is (implode-tu -test_builtin_abs-06a7e49)))
    #_(round-trip-tu -test_builtin_abs-06a7e49)

    (let [-test_builtin_bin-0ca34fe
          (slurp-asr "-test_builtin_bin-0ca34fe")]
      (is (nil? (explode -test_builtin_bin-0ca34fe)))
      (is (implode-tu -test_builtin_bin-0ca34fe)))
    #_(round-trip-tu -test_builtin_bin-0ca34fe)

    (let [-test_builtin_bool-fe3fe33
          (slurp-asr "-test_builtin_bool-fe3fe33")]
      (is (nil? (explode -test_builtin_bool-fe3fe33)))
      (is (implode-tu -test_builtin_bool-fe3fe33)))
    #_(round-trip-tu -test_builtin_bool-fe3fe33)

    (let [-test_builtin_float-97f9316
          (slurp-asr "-test_builtin_float-97f9316")]
      (is (nil? (explode -test_builtin_float-97f9316)))
      (is (implode-tu -test_builtin_float-97f9316)))
    #_(round-trip-tu -test_builtin_float-97f9316)

    (let [-test_builtin_hex-d4abc3e
          (slurp-asr "-test_builtin_hex-d4abc3e")]
      (is (nil? (explode -test_builtin_hex-d4abc3e)))
      (is (implode-tu -test_builtin_hex-d4abc3e)))
    #_(round-trip-tu -test_builtin_hex-d4abc3e)

    (let [-test_builtin_int-990d1de
          (slurp-asr "-test_builtin_int-990d1de")]
      (is (nil? (explode -test_builtin_int-990d1de)))
      (is (implode-tu -test_builtin_int-990d1de)))
    #_(round-trip-tu -test_builtin_int-990d1de)

    (let [-test_builtin_len-922cf65
          (slurp-asr "-test_builtin_len-922cf65")]
      (is (nil? (explode -test_builtin_len-922cf65)))
      (is (implode-tu -test_builtin_len-922cf65)))
    #_(round-trip-tu -test_builtin_len-922cf65)

    (let [-test_builtin_oct-490a98b
          (slurp-asr "-test_builtin_oct-490a98b")]
      (is (nil? (explode -test_builtin_oct-490a98b)))
      (is (implode-tu -test_builtin_oct-490a98b)))
    #_(round-trip-tu -test_builtin_oct-490a98b)

    (let [-test_builtin_pow-cea529e
          (slurp-asr "-test_builtin_pow-cea529e")]
      (is (nil? (explode -test_builtin_pow-cea529e)))
      (is (implode-tu -test_builtin_pow-cea529e)))
    #_(round-trip-tu -test_builtin_pow-cea529e)

    (let [-test_builtin_round-cca5cba
          (slurp-asr "-test_builtin_round-cca5cba")]
      (is (nil? (explode -test_builtin_round-cca5cba)))
      (is (implode-tu -test_builtin_round-cca5cba)))
    #_(round-trip-tu -test_builtin_round-cca5cba)

    (let [-test_builtin_str-fcdedc2
          (slurp-asr "-test_builtin_str-fcdedc2")]
      (is (nil? (explode -test_builtin_str-fcdedc2)))
      (is (implode-tu -test_builtin_str-fcdedc2)))
    #_(round-trip-tu -test_builtin_str-fcdedc2)

    (let [-test_c_interop_01-8bee4ec
          (slurp-asr "-test_c_interop_01-8bee4ec")]
      (is (nil? (explode -test_c_interop_01-8bee4ec)))
      (is (implode-tu -test_c_interop_01-8bee4ec)))
    #_(round-trip-tu -test_c_interop_01-8bee4ec)

    (let [-test_complex_01-c199562
          (slurp-asr "-test_complex_01-c199562")]
      (is (nil? (explode -test_complex_01-c199562)))
      (is (implode-tu -test_complex_01-c199562)))
    #_(round-trip-tu -test_complex_01-c199562)

    (let [-test_complex_02-6516823
          (slurp-asr "-test_complex_02-6516823")]
      (is (nil? (explode -test_complex_02-6516823)))
      (is (implode-tu -test_complex_02-6516823)))
    #_(round-trip-tu -test_complex_02-6516823)

    (let [-test_end_sep_keywords-49ea13f
          (slurp-asr "-test_end_sep_keywords-49ea13f")]
      (is (nil? (explode -test_end_sep_keywords-49ea13f)))
      (is (implode-tu -test_end_sep_keywords-49ea13f)))
    #_(round-trip-tu -test_end_sep_keywords-49ea13f)

    (let [-test_integer_bitnot-0d0eafa
          (slurp-asr "-test_integer_bitnot-0d0eafa")]
      (is (nil? (explode -test_integer_bitnot-0d0eafa)))
      (is (implode-tu -test_integer_bitnot-0d0eafa)))
    #_(round-trip-tu -test_integer_bitnot-0d0eafa)

    (let [-test_max_min-e73decc
          (slurp-asr "-test_max_min-e73decc")]
      (is (nil? (explode -test_max_min-e73decc)))
      (is (implode-tu -test_max_min-e73decc)))
    #_(round-trip-tu -test_max_min-e73decc)

    (let [-test_numpy_03-6dd742e
          (slurp-asr "-test_numpy_03-6dd742e")]
      (is (nil? (explode -test_numpy_03-6dd742e)))
      (is (implode-tu -test_numpy_03-6dd742e)))
    #_(round-trip-tu -test_numpy_03-6dd742e)

    (let [-test_numpy_04-3376b7a
          (slurp-asr "-test_numpy_04-3376b7a")]
      (is (nil? (explode -test_numpy_04-3376b7a)))
      (is (implode-tu -test_numpy_04-3376b7a)))
    #_(round-trip-tu -test_numpy_04-3376b7a)

    (let [-test_pow-6f6a69d
          (slurp-asr "-test_pow-6f6a69d")]
      (is (nil? (explode -test_pow-6f6a69d)))
      (is (implode-tu -test_pow-6f6a69d)))
    #_(round-trip-tu -test_pow-6f6a69d)

    (let [-tuple1-ce358d9
          (slurp-asr "-tuple1-ce358d9")]
      (is (nil? (explode -tuple1-ce358d9)))
      (is (implode-tu -tuple1-ce358d9)))
    #_(round-trip-tu -tuple1-ce358d9)

    (let [-vec_01-9b22f33
          (slurp-asr "-vec_01-9b22f33")]
      (is (nil? (explode -vec_01-9b22f33)))
      (is (implode-tu -vec_01-9b22f33)))
    #_(round-trip-tu -vec_01-9b22f33)

    (let [_expr2_5311701
          (slurp-asr "_expr2_5311701")]
      (is (nil? (explode _expr2_5311701)))
      (is (implode-tu _expr2_5311701)))
    #_(round-trip-tu _expr2_5311701)

    (let [_expr_10_e2e0267
          (slurp-asr "_expr_10_e2e0267")]
      (is (nil? (explode _expr_10_e2e0267)))
      (is (implode-tu _expr_10_e2e0267)))
    #_(round-trip-tu _expr_10_e2e0267)

    (let [_pass_inline_function_calls-func_inline_01-6cf8821
          (slurp-asr "_pass_inline_function_calls-func_inline_01-6cf8821")]
      (is (nil? (explode _pass_inline_function_calls-func_inline_01-6cf8821)))
      (is (implode-tu _pass_inline_function_calls-func_inline_01-6cf8821)))
    #_(round-trip-tu _pass_inline_function_calls-func_inline_01-6cf8821)    ))



(deftest bisection-test

)
