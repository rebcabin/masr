(ns masr.utils)


(defn warnings-banner []
  (println "+--------------------------------------------------------------+")
  (println "| Note from the Authors:                                       |")
  (println "| The warnings about Integer, Character, deftype, etc.,        |")
  (println "| are expected and not maskable!                               |")
  (println "+--------------------------------------------------------------+"))


(defmacro plnecho
  "preimage and value"
  [x]
  `(let [x# ~x]
     (do (println '~x "~~>" x#)
         x#)))
