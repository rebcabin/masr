(ns masr.utils)

(defmacro plnecho
  "preimage and value"
  [x]
  `(let [x# ~x]
     (do (println '~x "~~>" x#)
         x#)))
