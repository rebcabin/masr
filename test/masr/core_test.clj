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

(let [huge     951132862023730457951132862023730457
      biggish  4200000000000
      biggishN 4200000000000N]
 (deftest nat-test
   (testing "better syntax"
     (is (s/valid? ::asr/nat    (nat 42)))
     (is (s/valid? ::asr/nat    (nat biggish)))
     (is (s/valid? ::asr/nat    (nat 0)))
     (is (s/valid? ::asr/nat    (nat (bigint 42N))))
     (is (s/valid? ::asr/nat    (nat (bigint biggishN))))
     (is (s/valid? ::asr/nat    (nat (bigint 0N))))
     (is (s/valid? ::asr/bignat (nat (bigint 42N))))
     (is (s/valid? ::asr/bignat (nat (bigint biggishN))))
     (is (s/valid? ::asr/bignat (nat (bigint 0N))))
     (is (s/valid? ::asr/bignat (nat huge)))
     (is (s/valid? ::asr/nat    (nat huge))))
   (is (s/valid? ::asr/bignat  huge))
   (is (s/valid? ::asr/nat     huge))
   (is (not (s/valid? nat-int? huge)))
   (is (s/valid? ::asr/nat 42))
   (is (s/valid? ::asr/nat biggish))
   (is (s/valid? ::asr/nat 0))
   (is (s/valid? ::asr/nat (bigint 42N)))
   (is (s/valid? ::asr/nat (bigint biggishN)))
   (is (s/valid? ::asr/nat (bigint 0N)))
   (is (s/valid? ::asr/bignat (bigint 42N)))
   (is (s/valid? ::asr/bignat (bigint biggishN)))
   (is (s/valid? ::asr/bignat (bigint 0N)))))

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
    (is (s/valid? ::asr/asr-term (dimension '(1 60))))
    (is (s/valid? ::asr/asr-term (dimension '())))
    (is (s/valid? ::asr/asr-term (dimension  ())))
    (is (s/valid? ::asr/asr-term (dimension '(0))))
    (is (s/valid?
         ::asr/asr-term
         (dimension
          '(606 66979216746710640882869059905284213752707))))
    (is (not (s/valid? ::asr/asr-term 0)))
    (is (not (s/valid? ::asr/asr-term 'foo)))
    (is (not (s/valid? ::asr/asr-term "")))
    (is (not (s/valid? ::asr/asr-term :bar)))
    (is (thrown? clojure.lang.ArityException
                 (not (s/valid? ::asr/dimension (dimension 1 60)))))
    (is (thrown? clojure.lang.ArityException
                 (not (s/valid? ::asr/dimension (dimension)))))
    (testing "implicit conversion to seq via 'dimension' fncall"
      (is (s/valid? ::asr/asr-term (dimension [1 60])))
      (is (s/valid? ::asr/asr-term (dimension [])))
      (is (s/valid? ::asr/asr-term (dimension [0])))
      (is (s/valid?
           ::asr/asr-term
           (dimension
            [606 66979216746710640882869059905284213752707]))))
    ))


#_
(dimension '(1 60))
;; => #:masr.specs{:term :masr.specs/dimension, :dimension-content (1 60)}

#_
(let [gen (s/gen (term-selector-spec ::asr/dimension))]
  (gen/sample gen 10))
;; => (#::asr{:term ::asr/dimension, :dimension-content (1 2)}
;;     #::asr{:term ::asr/dimension, :dimension-content ()}
;;     #::asr{:term ::asr/dimension, :dimension-content (0)}
;;     #::asr{:term ::asr/dimension, :dimension-content (11)}
;;     #::asr{:term ::asr/dimension, :dimension-content ()}
;;     #::asr{:term ::asr/dimension, :dimension-content (716)}
;;     #::asr{:term ::asr/dimension, :dimension-content (92974 80)}
;;     #::asr{:term ::asr/dimension, :dimension-content (2 2217367)}
;;     #::asr{:term ::asr/dimension, :dimension-content (45 2)}
;;     #::asr{:term ::asr/dimension, :dimension-content (26679 96)})


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/

;; dimensions = dimension* (implicitly defined in ASDL with * metaoperator).
;;
;; DESIGN DECISION: dimension is a ::asr/asr-term but dimensions is NOT;
;; dimensions is just a collection.
;; (s/def ::dimensions
;;   (s/coll-of ::dimension
;;              :min-count 0,
;;              :max-count MAX-NUMBER-OF-DIMENSIONS,
;;              :into []))


(deftest dimensions-test
  (is (s/valid? ::asr/dimensions (dimensions ['(1 60) '()])))
  (is (s/valid? ::asr/dimensions (dimensions ['(1 60)])))
  (is (s/valid? ::asr/dimensions (dimensions ['(1 60) []])))
  (is (s/valid? ::asr/dimensions (dimensions [])))
  (is (s/valid? ::asr/dimensions (dimensions '((1 60) ()))))
  (is (s/valid? ::asr/dimensions (dimensions '((1 60)))))
  (is (s/valid? ::asr/dimensions (dimensions '((1 60) []))))
  (is (s/valid? ::asr/dimensions (dimensions ()))))

#_
(gen/sample (s/gen ::asr/dimensions) 5)
;; => ([#:masr.specs{:term :masr.specs/dimension, :dimension-content (3)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (11 0)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (1 6674)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (1)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content ()}]
;;     [#:masr.specs{:term :masr.specs/dimension, :dimension-content (13 2)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (1)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (17)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (0 7208)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (0 6578)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content ()}]
;;     [#:masr.specs{:term :masr.specs/dimension, :dimension-content (1686186484)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (1)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (2 4)}]
;;     [#:masr.specs{:term :masr.specs/dimension, :dimension-content ()}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (1 11)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (1447797)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (31428)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (2551 2037)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content ()}]
;;     [#:masr.specs{:term :masr.specs/dimension, :dimension-content (24 10722173)}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content ()}
;;      #:masr.specs{:term :masr.specs/dimension, :dimension-content (12)}])


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
;; => (#::asr{:term ::asr/intent, :intent-enum ReturnVar}
;;     #::asr{:term ::asr/intent, :intent-enum In}
;;     #::asr{:term ::asr/intent, :intent-enum Unspecified}
;;     #::asr{:term ::asr/intent, :intent-enum Unspecified}
;;     #::asr{:term ::asr/intent, :intent-enum InOut})


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
;; => (#::asr{:term ::asr/storage-type, :storage-type-enum Save}
;;     #::asr{:term ::asr/storage-type, :storage-type-enum Save}
;;     #::asr{:term ::asr/storage-type, :storage-type-enum Parameter}
;;     #::asr{:term ::asr/storage-type, :storage-type-enum Save}
;;     #::asr{:term ::asr/storage-type, :storage-type-enum Parameter})


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
;; => (#::asr{:term ::asr/abi, :abi-enum Interactive, :abi-external true}
;;     #::asr{:term ::asr/abi, :abi-enum Source, :abi-external false}
;;     #::asr{:term ::asr/abi, :abi-enum Source, :abi-external false}
;;     #::asr{:term ::asr/abi, :abi-enum Source, :abi-external false}
;;     #::asr{:term ::asr/abi, :abi-enum Interactive, :abi-external true})


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
