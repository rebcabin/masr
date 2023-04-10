(ns masr.core-test
  (:use     [masr.core]
            ;; https://groups.google.com/g/clojure/c/i770QaIFiF0
            [masr.specs :as asr])
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [masr.core :as masr]))


;;                 _
;;  _ _ _  _ _ __ | |__  ___ _ _ ___
;; | ' \ || | '  \| '_ \/ -_) '_(_-<
;; |_||_\_,_|_|_|_|_.__/\___|_| /__/


(deftest nat-test
  (is (s/valid? ::asr/nat 42))
  (is (s/valid? ::asr/nat 4200000000000))
  (is (s/valid? ::asr/nat 0))
  (is (s/valid? ::asr/nat (bigint 42N)))
  (is (s/valid? ::asr/nat (bigint 4200000000000N)))
  (is (s/valid? ::asr/nat (bigint 0N)))
  (is (s/valid? ::asr/bignat (bigint 42N)))
  (is (s/valid? ::asr/bignat (bigint 4200000000000N)))
  (is (s/valid? ::asr/bignat (bigint 0N))))


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/


(deftest dimensions-test
  (is (s/valid? ::asr/dimension []))
  (is (s/valid? ::asr/dimension [1]))
  (is (not (s/valid? ::asr/dimensions [0]))))


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _
;; | / _` / -_) ' \  _| |  _| / -_) '_|
;; |_\__,_\___|_||_\__|_|_| |_\___|_|


(deftest identifier-test
  (is (s/valid? ::asr/identifier      'foobar))
  (is (s/valid? ::asr/identifier      '_foobar))
  (is (s/valid? ::asr/identifier      '__f_oobar))
  (is (s/valid? ::asr/identifier      '_1234))
  (is (s/valid? ::asr/identifier      '_1__234))
  (is (not (s/valid? ::asr/identifier '1234)))
  ;; '1a won't even compile, throwing java.lang.NumberFormatException
  (is (not (s/valid? ::asr/identifier "")))
  (is (not (s/valid? ::asr/identifier "asdf")))
  (is (not (s/valid? ::asr/identifier :asdf))))


;; A Clojure symbol is NOT an ASR identifier, but an
;; ASR identifier is a constrained symbol:

#_
(gen/sample (s/gen symbol?) 5)
;; => (-/! l7/!7 K/!! Z/*H nN?/C)

#_
(gen/sample (s/gen ::asr/identifier))
;; => (c Xr hm J GYGMt bTEY4 zJTt56 fz7Dan5C zw pT17Sa)


;;  _     _           _
;; (_)_ _| |_ ___ _ _| |_
;; | | ' \  _/ -_) ' \  _|
;; |_|_||_\__\___|_||_\__|

#_
(gen/sample (s/gen (s/and
                    ::asr/asr-term
                    #(= ::asr/intent (::asr/term %))))
            5)
;; => (#::asr{:term ::asr/intent, :intent-enum Out}
;;     #::asr{:term ::asr/intent, :intent-enum InOut}
;;     #::asr{:term ::asr/intent, :intent-enum InOut}
;;     #::asr{:term ::asr/intent, :intent-enum Unspecified}
;;     #::asr{:term ::asr/intent, :intent-enum Out})


(deftest intent-test
  (is (s/valid? ::asr/asr-term
                {::asr/term
                 ::asr/intent,
                 ::asr/intent-enum 'Local}))
  (is (s/valid? ::asr/asr-term
                {::asr/term
                 ::asr/intent,
                 ::asr/intent-enum 'Unspecified}))
  (testing "missing key"
    (is (not (s/valid? ::asr/asr-term
                       {::asr/intent-enum 'Unspecified}))))
  (testing "incorrect value"
    (is (not (s/valid? ::asr/asr-term
                       {::asr/term
                        ::asr/intent,
                        ::asr/intent-enum 'foobar})))))
