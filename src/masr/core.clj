(ns masr.core
  (:use     [masr.specs])
  (:require [clojure.pprint :refer [pprint]])
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


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
