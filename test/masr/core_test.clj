(ns masr.core-test
  (:use     [masr.core]
            ;; https://groups.google.com/g/clojure/c/i770QaIFiF0 :
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
  (testing "better syntax"
    (is (s/valid? ::asr/nat    (nat 42)))
    (is (s/valid? ::asr/nat    (nat 4200000000000)))
    (is (s/valid? ::asr/nat    (nat 0)))
    (is (s/valid? ::asr/nat    (nat (bigint 42N))))
    (is (s/valid? ::asr/nat    (nat (bigint 4200000000000N))))
    (is (s/valid? ::asr/nat    (nat (bigint 0N))))
    (is (s/valid? ::asr/bignat (nat (bigint 42N))))
    (is (s/valid? ::asr/bignat (nat (bigint 4200000000000N))))
    (is (s/valid? ::asr/bignat (nat (bigint 0N)))))
  (is (s/valid? ::asr/nat 42))
  (is (s/valid? ::asr/nat 4200000000000))
  (is (s/valid? ::asr/nat 0))
  (is (s/valid? ::asr/nat (bigint 42N)))
  (is (s/valid? ::asr/nat (bigint 4200000000000N)))
  (is (s/valid? ::asr/nat (bigint 0N)))
  (is (s/valid? ::asr/bignat (bigint 42N)))
  (is (s/valid? ::asr/bignat (bigint 4200000000000N)))
  (is (s/valid? ::asr/bignat (bigint 0N))))

#_
(gen/sample (s/gen ::asr/nat) 15)
;; => (1 0 58 0 12
;;     0 3751 13 185743679156 4
;;     9 758 2475515847708 30 474561204531338)


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_|


(deftest dimension-test
  (testing "better syntax"
    (is (s/valid? ::asr/dimension (dimension '(1 60))))
    (is (s/valid? ::asr/dimension (dimension '())))
    (is (s/valid? ::asr/dimension (dimension  ())))
    (is (s/valid? ::asr/dimension (dimension '(0))))
    (is (s/valid?
         ::asr/dimension
         (dimension
          '(606 66979216746710640882869059905284213752707))))
    (is (not (s/valid? ::asr/dimension 0)))
    (is (not (s/valid? ::asr/dimension 'foo)))
    (is (not (s/valid? ::asr/dimension "")))
    (is (not (s/valid? ::asr/dimension :bar)))
    (is (thrown? clojure.lang.ArityException
                 (not (s/valid? ::asr/dimension (dimension 1 60)))))
    (is (thrown? clojure.lang.ArityException
                 (not (s/valid? ::asr/dimension (dimension)))))
    (testing "implicit conversion to seq via 'dimension' fncall"
      (is (s/valid? ::asr/dimension (dimension [1 60])))
      (is (s/valid? ::asr/dimension (dimension [])))
      (is (s/valid? ::asr/dimension (dimension [0])))
      (is (s/valid?
           ::asr/dimension
           (dimension
            [606 66979216746710640882869059905284213752707]))))
    ))


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/


(deftest dimensions-test
  (is (s/valid? ::asr/dimension []))
  (is (s/valid? ::asr/dimension [1]))
  (is (not (s/valid? ::asr/dimensions [0]))))

#_
(gen/sample (s/gen ::asr/dimensions) 15)
;; => ([(1 1) (0) (1754) (0 15)]
;;     [() (15633) (1) (1 58)]
;;     [(0 0) (1) (45004 1) ()]
;;     [(1) (1 25) (0) (108763 1741) (306) (6 4) (2978 3963195) (1 1) ()]
;;     [() () (2 525175) (4 1) (0 0) ()]
;;     [(5)]
;;     [() () (4) (4) (25) (20837531 2) (1 37872207289)]
;;     [(10) () (13 3) () ()]
;;     [() (18)]
;;     [(2457788469) (0) (1416182 23337056983) (3893755855203 0) () ()]
;;     [() (1) () ()]
;;     [(8 13039750) (54 3) (29203272247 130) (56993925 3254579507612190)]
;;     [(21) (183 782719780057) () () (107266401) (14973)]
;;     [() (1 8140351019424905159520934198)]
;;     [(19699876832435852013432329851) (17) (88930935959209684 1648) () () ()])


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _
;; | / _` / -_) ' \  _| |  _| / -_) '_|
;; |_\__,_\___|_||_\__|_|_| |_\___|_|


(deftest identifier-test
  (testing "better syntax"
    (is (s/valid? ::asr/identifier      (identifier 'foobar)))
    (is (s/valid? ::asr/identifier      (identifier '_foobar)))
    (is (s/valid? ::asr/identifier      (identifier '__f_oobar)))
    (is (s/valid? ::asr/identifier      (identifier '_1234)))
    (is (s/valid? ::asr/identifier      (identifier '_1__234)))
    (is (not (s/valid? ::asr/identifier (identifier '1234))))
    ;; '1a won't even compile, throwing java.lang.NumberFormatException
    (is (not (s/valid? ::asr/identifier (identifier ""))))
    (is (not (s/valid? ::asr/identifier (identifier "asdf"))))
    (is (not (s/valid? ::asr/identifier (identifier :asdf)))))
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
;; => (#:masr.specs{:term :masr.specs/intent, :intent-enum ReturnVar}
;;     #:masr.specs{:term :masr.specs/intent, :intent-enum In}
;;     #:masr.specs{:term :masr.specs/intent, :intent-enum Unspecified}
;;     #:masr.specs{:term :masr.specs/intent, :intent-enum Unspecified}
;;     #:masr.specs{:term :masr.specs/intent, :intent-enum InOut})


(deftest intent-test
  (testing "better syntax"
    (is      (s/valid? ::asr/asr-term (intent 'Local)))
    (is      (s/valid? ::asr/asr-term (intent 'Unspecified)))
    (is (not (s/valid? ::asr/asr-term (intent 'foobar))))
    (is (not (s/valid? ::asr/asr-term (intent []))))
    (is (not (s/valid? ::asr/asr-term (intent "foobar"))))
    (is (not (s/valid? ::asr/asr-term (intent ""))))
    (is (not (s/valid? ::asr/asr-term (intent 42))))
    (is (thrown? clojure.lang.ArityException (intent))))
  (is (s/valid? ::asr/asr-term
                {::asr/term        ::asr/intent,
                 ::asr/intent-enum 'Local}))
  (is (s/valid? ::asr/asr-term
                {::asr/term        ::asr/intent,
                 ::asr/intent-enum 'Unspecified}))
  (testing "missing key"
    (is (not (s/valid? ::asr/asr-term
                       {::asr/intent-enum 'Unspecified}))))
  (testing "incorrect value"
    (is (not (s/valid? ::asr/asr-term
                       {::asr/term        ::asr/intent,
                        ::asr/intent-enum 'foobar})))))


;;     _                             _
;;  __| |_ ___ _ _ __ _ __ _ ___ ___| |_ _  _ _ __  ___
;; (_-<  _/ _ \ '_/ _` / _` / -_)___|  _| || | '_ \/ -_)
;; /__/\__\___/_| \__,_\__, \___|    \__|\_, | .__/\___|
;;                     |___/             |__/|_|

#_
(gen/sample (s/gen (s/and
                    ::asr/asr-term
                    #(= ::asr/storage-type (::asr/term %))))
            5)
;; => (#:masr.specs{:term :masr.specs/storage-type, :storage-type-enum Save}
;;     #:masr.specs{:term :masr.specs/storage-type, :storage-type-enum Save}
;;     #:masr.specs{:term :masr.specs/storage-type, :storage-type-enum Parameter}
;;     #:masr.specs{:term :masr.specs/storage-type, :storage-type-enum Save}
;;     #:masr.specs{:term :masr.specs/storage-type, :storage-type-enum Parameter})


(deftest storage-type-test
  (is (s/valid? ::asr/asr-term
                {::asr/term ::asr/storage-type
                 ::asr/storage-type-enum 'Default}))
  (testing "incorrect value"
    (is (not (s/valid? ::asr/asr-term
                       {::asr/term ::asr/storage-type,
                        ::asr/storage-type-enum 'foobar})))))


;;       _    _
;;  __ _| |__(_)
;; / _` | '_ \ |
;; \__,_|_.__/_|


(gen/sample (s/gen (s/and
                    ::asr/asr-term
                    #(= ::asr/abi (::asr/term %))))
            5)
;; => (#:masr.specs{:term :masr.specs/abi, :abi-enum Interactive, :abi-external true}
;;     #:masr.specs{:term :masr.specs/abi, :abi-enum Source, :abi-external false}
;;     #:masr.specs{:term :masr.specs/abi, :abi-enum Source, :abi-external false}
;;     #:masr.specs{:term :masr.specs/abi, :abi-enum Source, :abi-external false}
;;     #:masr.specs{:term :masr.specs/abi, :abi-enum Interactive, :abi-external true})


(deftest abi-test
  (is (s/valid? ::asr/asr-term
                {::asr/term        ::asr/abi
                 ::asr/abi-enum     'Source
                 ::asr/abi-external  false}))
  (testing "'Source ABI cannot be External"
    (is (not (s/valid? ::asr/asr-term
                       {::asr/term        ::asr/abi
                        ::asr/abi-enum     'Source
                        ::asr/abi-external  true})))))
