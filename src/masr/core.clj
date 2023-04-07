(ns masr.core
  (:use     [masr.specs])
  (:require [masr.protocols :refer [Summarize, summary, conditional-summary]]
            [clojure.pprint :refer [pprint]])
  (:gen-class))


;; | Variable(symbol_table   parent_symtab,   ;; really an integer id
;;            identifier     name,
;;            identifier   * dependencies,    ;; vector of dependency
;;            intent         intent,
;;            expr         ? symbolic_value,  ;; lack specified by nil
;;            expr         ? value,
;;            storage_type   storage,
;;            ttype          type,
;;            abi            abi,
;;            access         access,
;;            presence       presence,
;;            bool           value_attr)


(defrecord Variable
    [head, term, ;; head == Variable, term == symbol
     ;; Every record has a head and a term.
     symtab-id,
     name,
     dependencies,
     intent,
     symbolic-value,
     value,
     storage,
     type,
     abi,
     access,
     presence,
     value-attr]

    Summarize
    (summary [_] [:VRBL (:head name), (conditional-summary value)])
    (summarize [c] (pprint (summary c)) c)
    )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
