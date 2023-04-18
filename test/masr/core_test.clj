(ns masr.core-test
  (:use     [masr.core]
            ;; https://groups.google.com/g/clojure/c/i770QaIFiF0 :
            [masr.specs :as asr])
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [masr.core :as masr]
            [masr.utils :refer [warnings-banner]]
            [clojure.set :as set]))


(warnings-banner)


;;                 _
;;  _ _ _  _ _ __ | |__  ___ _ _ ___
;; | ' \ || | '  \| '_ \/ -_) '_(_-<
;; |_||_\_,_|_|_|_|_.__/\___|_| /__/

(let [huge     951132862023730457951132862023730457
      biggish  4200000000000
      biggest  0x7FFFFFFFFFFFFFFF
      toobig   0x8000000000000000
      biggishN 4200000000000N]
  (deftest nat-test
    (testing "raw nats"
      (is (s/valid? ::asr/bignat huge))
      (is (s/valid? ::asr/nat 42))
      (is (s/valid? ::asr/nat biggish))
      (is (s/valid? ::asr/nat biggest))
      (is (s/valid? ::asr/nat 0))
      (testing "too big"
        (is (not (s/valid? ::asr/nat toobig)))
        (is (not (s/valid? ::asr/nat (bigint 42N))))
        (is (not (s/valid? ::asr/nat (bigint biggishN))))
        (is (not (s/valid? ::asr/nat (bigint 0N))))
        (is (not (s/valid? ::asr/nat huge)))
        (is (not (s/valid? nat-int? huge))))
      (testing "negative"
        (is (not (s/valid? ::asr/bignat (- huge))))
        (is (not (s/valid? ::asr/nat -42)))
        (is (not (s/valid? ::asr/nat (- biggish)))))
      (is (s/valid? ::asr/bignat (bigint 42N)))
      (is (s/valid? ::asr/bignat (bigint biggishN)))
      (is (s/valid? ::asr/bignat (bigint 0N))))
    (testing "syntax sugar"
      (is (s/valid? ::asr/nat (nat 42)))
      (is (s/valid? ::asr/nat (nat biggish)))
      (is (s/valid? ::asr/nat (nat 0)))
      (is (s/valid? ::asr/nat (nat '1234)))
      (testing "too big"
        (is (not (s/valid? ::asr/nat      (nat (bigint 42N)))))
        (is (not (s/valid? ::asr/nat      (nat (bigint biggishN)))))
        (is (not (s/valid? ::asr/nat      (nat (bigint 0N)))))
        (is (not (s/valid? ::asr/bignat   (nat (bigint 42N)))))
        (is (not (s/valid? ::asr/bignat   (nat (bigint biggishN)))))
        (is (not (s/valid? ::asr/bignat   (nat (bigint 0N)))))
        (is (not (s/valid? ::asr/bignat   (nat huge))))
        (is (not (s/valid? ::asr/nat      (nat huge)))))
      (testing "wrong type"
        (is (not (s/valid? ::asr/nat (nat "f"))))
        (is (not (s/valid? ::asr/nat (nat ()))))
        (is (not (s/valid? ::asr/nat (nat '(1)))))
        (is (not (s/valid? ::asr/nat (nat []))))
        (is (not (s/valid? ::asr/nat (nat [1 2 3]))))
        (is (not (s/valid? ::asr/nat (nat {}))))
        (is (not (s/valid? ::asr/nat (nat {2 3}))))
        (is (not (s/valid? ::asr/nat (nat #{}))))
        (is (not (s/valid? ::asr/nat (nat #{2 3}))))
        (is (not (s/valid? ::asr/nat (nat 'a1234))))
        (is (thrown? clojure.lang.ArityException
                     (nat)))))
))

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
    (is (not (s/valid?
              ::asr/asr-term
              (dimension
               '(606 66979216746710640882869059905284213752707)))))
    (is (not (s/valid? ::asr/asr-term 0)))
    (is (not (s/valid? ::asr/asr-term 'foo)))
    (is (not (s/valid? ::asr/asr-term "")))
    (is (not (s/valid? ::asr/asr-term :bar)))
    (is (not (s/valid? ::asr/asr-term ())))
    (is (not (s/valid? ::asr/asr-term {})))
    (is (not (s/valid? ::asr/asr-term #{})))
    (is (not (s/valid? ::asr/asr-term '(1))))
    (is (not (s/valid? ::asr/asr-term {1 2})))
    (is (not (s/valid? ::asr/asr-term #{3 4})))
    (is (not (s/valid? ::asr/asr-term (dimension {}))))
    (is (not (s/valid? ::asr/asr-term (dimension #{}))))
    (is (not (s/valid? ::asr/asr-term (dimension {1 2}))))
    (is (not (s/valid? ::asr/asr-term (dimension #{3 4}))))
    (is (thrown? clojure.lang.ArityException
                 (not (s/valid? ::asr/dimension (dimension 1 60)))))
    (is (thrown? clojure.lang.ArityException
                 (not (s/valid? ::asr/dimension (dimension)))))
    (testing "implicit conversion to seq via 'dimension' fncall"
      (is (s/valid? ::asr/asr-term (dimension [1 60])))
      (is (s/valid? ::asr/asr-term (dimension [])))
      (is (s/valid? ::asr/asr-term (dimension [0])))
      (is (not (s/valid?
                ::asr/asr-term
                (dimension
                 [606 66979216746710640882869059905284213752707])))))
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


(defn vds? [term]
  (s/valid? ::asr/dimensions term))


(deftest dimensions-test
  (is (vds? (dimensions ['(1 60) '()])))
  (is (vds? (dimensions ['(1 60)])))
  (is (vds? (dimensions ['(1 60) []])))
  (is (vds? (dimensions [])))
  (is (vds? (dimensions '((1 60) ()))))
  (is (vds? (dimensions '((1 60)))))
  (is (vds? (dimensions '((1 60) []))))
  (is (vds? (dimensions ())))
  (is (vds? (dimensions [[1 60] []])))
  (is (vds? (dimensions [[1 60]  [42 43]])))
  (is (vds? (dimensions [[1 60] '(42 43)])))
  (is (vds? (dimensions ())))
  (is (not (vds? (dimensions #{'(1 60) []}))))
  (is (not (vds? (dimensions #{}))))
  (is (not (vds? (dimensions {}))))
  (is (not (vds? (dimensions {2 3}))))
  (is (not (vds? (dimensions '("f")))))
  (is (not (vds? (dimensions '[("f")])))))

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
    (is (not (s/valid? ::asr/identifier (identifier :asdf))))

    (is (not (s/valid? ::asr/identifier (identifier ()))))
    (is (not (s/valid? ::asr/identifier (identifier []))))
    (is (not (s/valid? ::asr/identifier (identifier {}))))
    (is (not (s/valid? ::asr/identifier (identifier #{}))))
    (is (not (s/valid? ::asr/identifier (identifier '(foo)))))
    (is (not (s/valid? ::asr/identifier (identifier ['foo]))))
    (is (not (s/valid? ::asr/identifier (identifier {'foo 'bar}))))
    (is (not (s/valid? ::asr/identifier (identifier #{'foo}))))
    (is (not (s/valid? ::asr/identifier (identifier '1234))))
    (is (not (s/valid? ::asr/identifier (identifier ""))))
    (is (not (s/valid? ::asr/identifier (identifier "asdf"))))
    (is (not (s/valid? ::asr/identifier (identifier :asdf)))))

  (is (s/valid? ::asr/identifier      'foobar))
  (is (s/valid? ::asr/identifier      '_foobar))
  (is (s/valid? ::asr/identifier      '__f_oobar))
  (is (s/valid? ::asr/identifier      '_1234))
  (is (s/valid? ::asr/identifier      '_1__234))

  (is (not (s/valid? ::asr/identifier ())))
  (is (not (s/valid? ::asr/identifier [])))
  (is (not (s/valid? ::asr/identifier {})))
  (is (not (s/valid? ::asr/identifier #{})))
  (is (not (s/valid? ::asr/identifier '(foo))))
  (is (not (s/valid? ::asr/identifier ['foo])))
  (is (not (s/valid? ::asr/identifier {'foo 'bar})))
  (is (not (s/valid? ::asr/identifier #{'foo})))
  (is (not (s/valid? ::asr/identifier '1234)))
  ;; '1abcd won't even compile, throwing java.lang.NumberFormatException
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


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _ ___
;; | / _` / -_) ' \  _| |  _| / -_) '_(_-<
;; |_\__,_\___|_||_\__|_|_| |_\___|_| /__/


(deftest identifier-set-test
  (is (s/valid? ::asr/identifier-set (identifier-set [])))
  (is (s/valid? ::asr/identifier-set (identifier-set ())))
  (is (s/valid? ::asr/identifier-set (identifier-set #{})))
  (is (not (s/valid? ::asr/identifier-set (identifier-set {}))))
  (is (s/valid? ::asr/identifier-set (identifier-set ['foo])))
  (is (s/valid? ::asr/identifier-set (identifier-set '(foo))))
  (is (s/valid? ::asr/identifier-set (identifier-set #{'foo})))
  (is (not (s/valid? ::asr/identifier-set (identifier-set {'foo 'bar}))))
  (is (s/valid? ::asr/identifier-set (identifier-set ['foo 'bar])))
  (is (s/valid? ::asr/identifier-set (identifier-set '(foo bar))))
  (is (s/valid? ::asr/identifier-set (identifier-set #{'foo 'bar})))
  (is (s/valid? ::asr/identifier-set (identifier-set ['foo 'foo])))
  ;; check set-ness
  (is (= 1 (count (identifier-set ['foo 'foo]))))
  (is (s/valid? ::asr/identifier-set (identifier-set '(foo foo))))
  ;; #{'foo 'foo} won't compile!
  (is (not (s/valid? ::asr/identifier-set (identifier-set ['foo 123]))))
  (is (not (s/valid? ::asr/identifier-set (identifier-set ['foo "foo"]))))
  (is (not (s/valid? ::asr/identifier-set (identifier-set ['foo :foo])))))


(deftest identifier-list-test
  (is (s/valid? ::asr/identifier-list (identifier-list [])))
  (is (s/valid? ::asr/identifier-list (identifier-list ())))
  (is (s/valid? ::asr/identifier-list (identifier-list #{})))
  (is (not (s/valid? ::asr/identifier-list (identifier-list {}))))
  (is (s/valid? ::asr/identifier-list (identifier-list ['foo])))
  (is (s/valid? ::asr/identifier-list (identifier-list '(foo))))
  (is (s/valid? ::asr/identifier-list (identifier-list #{'foo})))
  (is (not (s/valid? ::asr/identifier-list (identifier-list {'foo 'bar}))))
  (is (s/valid? ::asr/identifier-list (identifier-list ['foo 'bar])))
  (is (s/valid? ::asr/identifier-list (identifier-list '(foo bar))))
  (is (s/valid? ::asr/identifier-list (identifier-list #{'foo 'bar})))
  (is (s/valid? ::asr/identifier-list (identifier-list ['foo 'foo])))
  ;; check that duplicates are allowed
  (is (= 2 (count (identifier-list ['foo 'foo]))))
  (is (s/valid? ::asr/identifier-list (identifier-list '(foo foo))))
  ;; #{'foo 'foo} won't compile!
  (is (not (s/valid? ::asr/identifier-list (identifier-list ['foo 123]))))
  (is (not (s/valid? ::asr/identifier-list (identifier-list ['foo "foo"]))))
  (is (not (s/valid? ::asr/identifier-list (identifier-list ['foo :foo])))))


(deftest identifier-suit-test
  (is (s/valid? ::asr/identifier-suit (identifier-suit [])))
  (is (s/valid? ::asr/identifier-suit (identifier-suit ())))
  (is (s/valid? ::asr/identifier-suit (identifier-suit #{})))
  (is (not (s/valid? ::asr/identifier-suit (identifier-suit {}))))
  (is (s/valid? ::asr/identifier-suit (identifier-suit ['foo])))
  (is (s/valid? ::asr/identifier-suit (identifier-suit '(foo))))
  (is (s/valid? ::asr/identifier-suit (identifier-suit #{'foo})))
  (is (not (s/valid? ::asr/identifier-suit (identifier-suit {'foo 'bar}))))
  (is (s/valid? ::asr/identifier-suit (identifier-suit ['foo 'bar])))
  (is (s/valid? ::asr/identifier-suit (identifier-suit '(foo bar))))
  (is (s/valid? ::asr/identifier-suit (identifier-suit #{'foo 'bar})))
  (is (not (s/valid? ::asr/identifier-suit (identifier-suit ['foo 'foo]))))
  (is (not (s/valid? ::asr/identifier-suit (identifier-suit '(foo foo)))))
  ;; #{'foo 'foo} won't compile!
  (is (not (s/valid? ::asr/identifier-suit (identifier-suit ['foo 123]))))
  (is (not (s/valid? ::asr/identifier-suit (identifier-suit ['foo "foo"]))))
  (is (not (s/valid? ::asr/identifier-suit (identifier-suit ['foo :foo])))))


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
    (is (not (s/valid? ::asr/asr-term (intent ()))))
    (is (not (s/valid? ::asr/asr-term (intent {}))))
    (is (not (s/valid? ::asr/asr-term (intent #{}))))
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
;; => (#::asr{:term ::asr/abi, :abi-enum Intrinsic, :abi-external true}
;;     #::asr{:term ::asr/abi, :abi-enum Interactive, :abi-external true}
;;     #::asr{:term ::asr/abi, :abi-enum Source, :abi-external false}
;;     #::asr{:term ::asr/abi, :abi-enum Source, :abi-external false}
;;     #::asr{:term ::asr/abi, :abi-enum Source, :abi-external false})


(deftest abi-test
  (testing "conforming examples"
    (is (s/valid? ::asr/asr-term
                 {::asr/term        ::asr/abi
                  ::asr/abi-enum     'Source
                  ::asr/abi-external  false}))
    (is (s/valid? ::asr/asr-term (abi 'Source         :external false)))
    (is (s/valid? ::asr/asr-term (abi 'LFortranModule :external true)))
    (is (s/valid? ::asr/asr-term (abi 'GFortranModule :external true)))
    (is (s/valid? ::asr/asr-term (abi 'BindC          :external true)))
    (is (s/valid? ::asr/asr-term (abi 'Interactive    :external true)))
    (is (s/valid? ::asr/asr-term (abi 'Intrinsic      :external true))))
  (testing "non-conforming examples"
    (testing "incorrect :external ::bool value"
      (is (not (s/valid? ::asr/asr-term
                        {::asr/term        ::asr/abi
                         ::asr/abi-enum     'Source
                         ::asr/abi-external  true})))
      (is (not (s/valid? ::asr/asr-term
                         (abi 'Source         :external true))))
      (is (not (s/valid? ::asr/asr-term
                         (abi 'LFortranModule :external false))))
      (is (not (s/valid? ::asr/asr-term
                         (abi 'GFortranModule :external false))))
      (is (not (s/valid? ::asr/asr-term
                         (abi 'BindC          :external false))))
      (is (not (s/valid? ::asr/asr-term
                         (abi 'Interactive    :external false))))
      (is (not (s/valid? ::asr/asr-term
                         (abi 'Intrinsic      :external false)))))
    (testing "incorrect :external type"
      (is (not (s/valid? ::asr/asr-term (abi 'Source :external 42))))
      (is (not (s/valid? ::asr/asr-term (abi 'Source :external "foo"))))
      (is (not (s/valid? ::asr/asr-term (abi 'Source :external 'foo)))))
    (testing "missing keyword; :external defaults to nil"
      (is (not (s/valid? ::asr/asr-term (abi 'Source false))))
      (is (not (s/valid? ::asr/asr-term (abi 'Source true))))
      (is (not (s/valid? ::asr/asr-term (abi 'Source 42))))
      (is (not (s/valid? ::asr/asr-term (abi 'foo true))))
      (is (not (s/valid? ::asr/asr-term (abi 'foo false)))))
    ))


;;  _   _
;; | |_| |_ _  _ _ __  ___
;; |  _|  _| || | '_ \/ -_)
;;  \__|\__|\_, | .__/\___|
;;          |__/|_|


(defn vh? [term]
  (s/valid? ::asr/asr-ttype-head term))


(defn vt? [term]
  (s/valid? ::asr/asr-term term))


(defn cfh [head]
  (s/conform ::asr/asr-ttype-head head))


(deftest ttype-test
  (testing "full-sugar and light-sugar)"
    (is (vt? (ttype (Integer))))
    (is (vt? (ttype (Integer 4))))
    (is (vt? (ttype (Integer 4 []))))
    (is (vt? (ttype (Integer 4 [[6 60] [42]]))))
    (is (vt? (ttype (Integer))))
    (is (vt? (ttype (Integer 8))))
    (is (vt? (ttype (Integer 8 []))))
    (is (vt? (ttype (Integer 8 [[6 60] [82]]))))
    (is (vt? (ttype (Logical))))
    (is (vt? (ttype (Logical 4))))
    (is (vt? (ttype (Logical 4 []))))
    (is (vt? (ttype (Logical 4 [[6 60] [42]]))))
    (is (vt? (ttype (Integer- {:dimensions [], :kind 4}))))
    (is (vt? (ttype (Integer- {:kind 4, :dimensions []}))))
    (testing "non-conformance"
      (is (not (vt? (ttype (Logical 4 ['fubar])))))
      (is (not (vt? (ttype (Logical 8)))))
      (is (not (vt? (ttype (Logical 0 [])))))
      (is (not (vt? (ttype (Logical 42 [[6 60] [42]])))))))
  (testing "full-form"
    (is (vt?
        {::asr/term ::asr/ttype,
         ::asr/asr-ttype-head
         {::asr/ttype-head   ::asr/Integer,
          ::asr/integer-kind 4
          ::asr/dimensions   []}}))
    (is (vt?
         {::asr/term ::asr/ttype,
          ::asr/asr-ttype-head
          {::asr/ttype-head   ::asr/Real,
           ::asr/real-kind    4
           ::asr/dimensions   []}})))
  (testing "syntax sugar"
    (is (vh? (Integer 1 [])))
    (is (vh? (Integer 2 [])))
    (is (vh? (Integer 4 [])))
    (is (vh? (Integer 8 [])))
    (is (vh? (Integer)))
    (is (vh? (Real 4 [])))
    (is (vh? (Real 8 [])))
    (is (vh? (Real))))
  (testing "non-conformance"
    (is (not (vh? (Real 1 []))))
    (is (not (vh? (Real 2 []))))
    (is (not (vh? (Integer 42))))
    (is (not (vh? (Integer 'fubar)))))
  (testing "defaults"
    (is (= (cfh (Integer))
           (Integer 4 [])))
    (is (= (cfh (Integer 4))
           (Integer 4 []))))
  (testing "order-independence of light sugar"
    (is (= (cfh (Integer- {:dimensions [], :kind 4}))
           (Integer- {:kind 4, :dimensions []})))))


;; __   __        _      _    _
;; \ \ / /_ _ _ _(_)__ _| |__| |___
;;  \ V / _` | '_| / _` | '_ \ / -_)
;;   \_/\__,_|_| |_\__,_|_.__/_\___|

;; | Variable(symbol_table   parent_symtab,   ;; really an integer id
;;            identifier     name,
;;            identifier   * dependencies,    ;; vector of dependency
;;            intent         intent,
;;            expr         ? symbolic_value,  ;; lack specified by nil
;;            expr         ? value,
;;            storage_type   storage,
;;            ttype          type,
;;            abi            abi,
;;            access         access,
;;            presence       presence,
;;            bool           value_attr)
