(ns masr.core
  (:gen-class))

;; | Variable(symbol_table parent_symtab,
;;            identifier name,
;;            identifier* dependencies,
;;            intent intent,
;;            expr? symbolic_value,
;;            expr? value,
;;            storage_type storage,
;;            ttype type,
;;            abi abi,
;;            access access,
;;            presence presence,
;;            bool value_attr)


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
