(ns masr.core
  (:use     [masr.specs])
  (:require [clojure.pprint :refer [pprint]]
            [masr.utils     :refer [warnings-banner]])
  (:gen-class))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (warnings-banner))
