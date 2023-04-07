(ns masr.core-test
  (:require [clojure.test :refer :all]
            [masr.core :refer :all]))


;; __   __        _      _    _
;; \ \ / /_ _ _ _(_)__ _| |__| |___
;;  \ V / _` | '_| / _` | '_ \ / -_)
;;   \_/\__,_|_| |_\__,_|_.__/_\___|


;; (Variable 3
;;           res
;;           []
;;           Local
;;           ()
;;           ()
;;           Default
;;           (Integer 4 [])
;;           Source
;;           Public
;;           Required
;;           .false.)

(def a-variable
  (map->Variable {:head 'Variable
                  :term 'symbol
                  ;;--------------
                  :symtab-id 3
                  :dependencies nil ; No need to write this; its nil by default.
                  }))

(deftest variable-test
  (is (= ('Variable (:head a-variable)))))
