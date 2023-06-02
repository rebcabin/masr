;; #+begin_src clojure

(defmulti idempotent-eval ::term)


(defn quote-in [key]
  #(assoc-in % [key] `'~(key %)))


(defmethod idempotent-eval ::intent
  [intent-ff]
  intent-ff #_
  ((quote-in ::intent-enum) intent-ff))
;; #+end_src



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

;;
;; ## Visitor Pattern
;;
;; #+begin_src clojure

(defmacro transform-when- [pred xfn item]
  `(if (~pred ~item)
     (~xfn ~item)
     ~item))


(defn transform-when [pred xfn item]
  (transform-when- pred xfn item))


(defn visitor [pred xform tree]
  (prewalk
   (partial transform-when pred xform)
   tree))
;; #+end_src

;;
;; ## Detector Predicates
;;
;; #+begin_src clojure

(defn leaf-list? [item]
  (and (list? item)
       (empty? (->> item
                    (filter coll?)
                    (filter #(not (empty? %)))))))
;; #+end_src

;; #+begin_src clojure

(defn dependencies-vector? [item]
  (and (vector? item)
       (every? symbol? item)))
;; #+end_src

;; #+begin_src clojure

(defn symbol-ref? [item]
  (and (::identifier item)
       (::symtab-id  item)))
;; #+end_src

;; #+begin_src clojure

(defn Character? [item]
  (= ::Character (::ttype-head item)))
;; #+end_src

;; #+begin_src clojure

(defn Var? [item]
  (= ::Var (::expr-head item)))
;; #+end_src

;; #+begin_src clojure

(defn ExternalSymbol? [item]
  (= ::ExternalSymbol (::symbol-head item)))
;; #+end_src

;; #+begin_src clojure

(defn sugar? [head]
  #(and (list? %)
        (= (first %) head)))
;; #+end_src

;;
;; ## Evaluating Leaf Lists
;;
;; #+begin_src clojure

(def quote-elements
  #(vec (for [s %] `'~s)))
;; #+end_src

;; #+begin_src clojure

(defn bottom-up-full-form [tree]
  (plnecho
   (->> tree
        (visitor leaf-list?
                 to-full-form)

        (visitor dependencies-vector?
                 quote-elements)

        (visitor symbol-ref?
                 (quote-in ::identifier))

        (visitor Character?
                 (quote-in ::disposition))

        (visitor Var?
                 (quote-in ::varnym))

        (visitor ExternalSymbol?
                 (quote-in ::nym))

        (visitor ExternalSymbol?
                 (quote-in ::modulenym))

        (visitor ExternalSymbol?
                 (quote-in ::orig-nym))

        (visitor (sugar? 'Function)
                 to-full-form)

        #_to-full-form
        )))
;; #+end_src
