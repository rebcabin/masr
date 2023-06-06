(ns masr.big-1bcc4ec-test
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
;; them up. Experiment: Evaluation of some
;; components with `prewalk` visitors.
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
     (fd nymstr 'IntegerConstant)
     (fd nymstr 'RealConstant)
     (fd nymstr 'ComplexConstant)
     (fd nymstr 'StringConstant)
     (fd nymstr 'LogicalConstant)
     (fd nymstr 'ListConstant)
     (fd nymstr 'TupleConstant)
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
    (is (nil? (explode tuSmallProgram-intentionally-wrong-1)))
    (is (nil? (explode tuSmallProgram)))
    (is (nil? (explode tuMediumProgram)))
    (is (nil? (explode big-slurped-1bcc4ec))))
  (testing "implode"
    (is (nil? (implode-tu tuSmallProgram-intentionally-wrong-1)))
    (is (implode-tu tuSmallProgram))
    (is (implode-tu tuMediumProgram))
    (is (implode-tu big-slurped-1bcc4ec))))
