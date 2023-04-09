(ns masr.core-test
  (:use     [masr.core]
            [masr.specs] )
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [masr.core :as masr]))


;;                 _
;;  _ _ _  _ _ __ | |__  ___ _ _ ___
;; | ' \ || | '  \| '_ \/ -_) '_(_-<
;; |_||_\_,_|_|_|_|_.__/\___|_| /__/


(deftest nat-test
  (is (s/valid? :masr.specs/nat 42))
  (is (s/valid? :masr.specs/nat 4200000000000))
  (is (s/valid? :masr.specs/nat 0))
  (is (s/valid? :masr.specs/nat (bigint 42N)))
  (is (s/valid? :masr.specs/nat (bigint 4200000000000N)))
  (is (s/valid? :masr.specs/nat (bigint 0N)))
  (is (s/valid? :masr.specs/bignat (bigint 42N)))
  (is (s/valid? :masr.specs/bignat (bigint 4200000000000N)))
  (is (s/valid? :masr.specs/bignat (bigint 0N))))


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/


(deftest dimensions-test
  (is (s/valid? :masr.specs/dimension []))
  (is (s/valid? :masr.specs/dimension [1]))
  (is (not (s/valid? :masr.specs/dimensions [0]))))


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _
;; | / _` / -_) ' \  _| |  _| / -_) '_|
;; |_\__,_\___|_||_\__|_|_| |_\___|_|


(deftest identifier-test
  (is (s/valid? :masr.specs/identifier      'foobar))
  (is (s/valid? :masr.specs/identifier      '_foobar))
  (is (s/valid? :masr.specs/identifier      '__f_oobar))
  (is (s/valid? :masr.specs/identifier      '_1234))
  (is (s/valid? :masr.specs/identifier      '_1__234))
  (is (not (s/valid? :masr.specs/identifier '1234)))
  ;; '1a won't even compile, throwing java.lang.NumberFormatException
  (is (not (s/valid? :masr.specs/identifier "")))
  (is (not (s/valid? :masr.specs/identifier "asdf")))
  (is (not (s/valid? :masr.specs/identifier :asdf))))


;; A Clojure symbol is NOT an ASR identifier, but an
;; ASR identifier is a constrained symbol:

#_
(gen/sample (s/gen symbol?) 5)
;; => (-/! l7/!7 K/!! Z/*H nN?/C)

#_
(gen/sample (s/gen :masr.specs/identifier))
;; => (c Xr hm J GYGMt bTEY4 zJTt56 fz7Dan5C zw pT17Sa)


;;  _     _           _
;; (_)_ _| |_ ___ _ _| |_
;; | | ' \  _/ -_) ' \  _|
;; |_|_||_\__\___|_||_\__|
