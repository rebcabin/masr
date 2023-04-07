(ns masr.core-test
  (:use     [masr.core]
            [masr.base-specs]
            [masr.specs] )
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s]))


;; __   __        _      _    _
;; \ \ / /_ _ _ _(_)__ _| |__| |___
;;  \ V / _` | '_| / _` | '_ \ / -_)
;;   \_/\__,_|_| |_\__,_|_.__/_\___|


;; (Variable 3           ;
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
  (map->Variable {:head         'Variable
                  :term         'symbol
                  ;;--------------
                  :symtab-id     3
                  :name         'res
                  :dependencies  nil ; No need to write this; its nil by default.
                  :intent       'Local
                  }))


(deftest identifier-test
  (is (s/valid? :masr.specs/identifier 'foobar))
  )


(deftest variable-test
  (is (= ('Variable (:head a-variable)))))
