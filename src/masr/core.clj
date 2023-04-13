(ns masr.core
  (:use     [masr.specs])
  (:require [clojure.pprint :refer [pprint]])
  (:gen-class))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
  (println "Note from the Authors:")
  (println "The warnings about Integer are expected and not maskable!")
  (println "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"))
