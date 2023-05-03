(ns masr.core-test
  (:use     [masr.core]
            ;; https://groups.google.com/g/clojure/c/i770QaIFiF0 :
            [masr.specs             :as    asr          ])

  (:require [clojure.test           :refer :all         ]
            [clojure.spec.alpha     :as    s            ]
            [clojure.spec.gen.alpha :as    gen          ]
            [clojure.pprint         :refer [pprint     ]]
            [clojure.set            :as    set          ])

  (:require [masr.utils             :refer [warnings-banner
                                            dosafely
                                            plnecho]]
            [masr.simplespecs       :refer [nat
                                            identifier
                                            identifier-set
                                            identifier-list
                                            identifier-suit
                                            ]])
  )


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
    (is (not (s/valid? ::asr/asr-term (dimension '(0)))))
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
      (is (not (s/valid? ::asr/asr-term (dimension [0]))))
      (is (not (s/valid?
                ::asr/asr-term
                (dimension
                 [606 66979216746710640882869059905284213752707])))))
    (testing "refined type ::asr/dimension"
      (is (s/valid? ::asr/dimension (dimension '(1 60))))
      (is (s/valid? ::asr/dimension (dimension '())))
      (is (s/valid? ::asr/dimension (dimension  ())))
      (is (not (s/valid? ::asr/dimension (dimension '(0)))))
      (is (not (s/valid?
                ::asr/dimension
                (dimension
                 '(606 66979216746710640882869059905284213752707)))))
      (is (not (s/valid? ::asr/dimension 0)))
      (is (not (s/valid? ::asr/dimension 'foo)))
      (is (not (s/valid? ::asr/dimension "")))
      (is (not (s/valid? ::asr/dimension :bar)))
      (is (not (s/valid? ::asr/dimension ())))
      (is (not (s/valid? ::asr/dimension {})))
      (is (not (s/valid? ::asr/dimension #{})))
      (is (not (s/valid? ::asr/dimension '(1))))
      (is (not (s/valid? ::asr/dimension {1 2})))
      (is (not (s/valid? ::asr/dimension #{3 4})))
      (is (not (s/valid? ::asr/dimension (dimension {}))))
      (is (not (s/valid? ::asr/dimension (dimension #{}))))
      (is (not (s/valid? ::asr/dimension (dimension {1 2}))))
      (is (not (s/valid? ::asr/dimension (dimension #{3 4}))))
      (is (thrown? clojure.lang.ArityException
                   (not (s/valid? ::asr/dimension (dimension 1 60)))))
      (is (thrown? clojure.lang.ArityException
                   (not (s/valid? ::asr/dimension (dimension)))))
      (testing "implicit conversion to seq via 'dimension' fncall"
        (is (s/valid? ::asr/dimension (dimension [1 60])))
        (is (s/valid? ::asr/dimension (dimension [])))
        (is (not (s/valid? ::asr/dimension (dimension [0]))))
        (is (not (s/valid?
                  ::asr/dimension
                  (dimension
                   [606 66979216746710640882869059905284213752707])))))
      ))
  (testing "conformance"
    (is (let [fullform {::asr/term ::asr/dimension,
                        ::asr/dimension-content '(1 60)}]
          (= (s/conform ::asr/asr-term  fullform)
             (s/conform ::asr/dimension fullform))))
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
  (is (s/valid?
       ::asr/dimensions
       [#:masr.specs{:term :masr.specs/dimension,
                     :dimension-content [1 60]}
        #:masr.specs{:term :masr.specs/dimension,
                     :dimension-content ()}]))
  (let [dims [(dimension [1 60]), (dimension [])]]
    (is (s/valid? ::asr/dimensions dims))
    (is (= dims (s/conform ::asr/dimensions dims)))
    (is (= dims (dimensions [[1 60] []])))
    (is (= dims (s/conform ::asr/dimensions
                           (dimensions [[1 60] []])))))
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




;;               _        _        _    _
;;  ____  _ _ __| |_ __ _| |__ ___(_)__| |
;; (_-< || | '  \  _/ _` | '_ \___| / _` |
;; /__/\_, |_|_|_\__\__,_|_.__/   |_\__,_|
;;     |__/


(deftest symtab-id-test
  (is (= (symtab-id  42)                            42))
  (is (= (symtab-id -42)                            ::asr/invalid-symtab-id))
  (is (= (symtab-id 'foo)                           ::asr/invalid-symtab-id))
  (is (= (s/conform ::asr/nat 42)                   42))
  (is (= (s/conform ::asr/nat (nat 42))             42))
  (is (= (s/conform ::asr/symtab-id 42)             42))
  (is (= (s/conform ::asr/symtab-id (symtab-id 42)) 42))
  (is (= (s/conform ::asr/symtab-id (nat 42))       42))
  )


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _
;; | / _` / -_) ' \  _| |  _| / -_) '_|
;; |_\__,_\___|_||_\__|_|_| |_\___|_|


(deftest identifier-test
  (testing "better syntax"
    (is      (s/valid? ::asr/identifier (identifier 'foobar)))
    (is      (s/valid? ::asr/identifier (identifier '_foobar)))
    (is      (s/valid? ::asr/identifier (identifier '__f_oobar)))
    (is      (s/valid? ::asr/identifier (identifier '_1234)))
    (is      (s/valid? ::asr/identifier (identifier '_1__234)))
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
  (is      (s/valid? ::asr/identifier-set (identifier-set [])))
  (is      (s/valid? ::asr/identifier-set (identifier-set ())))
  (is      (s/valid? ::asr/identifier-set (identifier-set #{})))
  (is (not (s/valid? ::asr/identifier-set (identifier-set {}))))
  (is      (s/valid? ::asr/identifier-set (identifier-set ['foo])))
  (is      (s/valid? ::asr/identifier-set (identifier-set '(foo))))
  (is      (s/valid? ::asr/identifier-set (identifier-set #{'foo})))
  (is (not (s/valid? ::asr/identifier-set (identifier-set {'foo 'bar}))))
  (is      (s/valid? ::asr/identifier-set (identifier-set ['foo 'bar])))
  (is      (s/valid? ::asr/identifier-set (identifier-set '(foo bar))))
  (is      (s/valid? ::asr/identifier-set (identifier-set #{'foo 'bar})))
  (is      (s/valid? ::asr/identifier-set (identifier-set ['foo 'foo])))
  ;; check set-ness
  (is (= 1 (count (identifier-set ['foo 'foo]))))
  (is      (s/valid? ::asr/identifier-set (identifier-set '(foo foo))))
  ;; #{'foo 'foo} won't compile!
  (is (not (s/valid? ::asr/identifier-set (identifier-set ['foo 123]))))
  (is (not (s/valid? ::asr/identifier-set (identifier-set ['foo "foo"]))))
  (is (not (s/valid? ::asr/identifier-set (identifier-set ['foo :foo])))))


(deftest identifier-list-test
  (is      (s/valid? ::asr/identifier-list (identifier-list [])))
  (is      (s/valid? ::asr/identifier-list (identifier-list ())))
  (is      (s/valid? ::asr/identifier-list (identifier-list #{})))
  (is (not (s/valid? ::asr/identifier-list (identifier-list {}))))
  (is      (s/valid? ::asr/identifier-list (identifier-list ['foo])))
  (is      (s/valid? ::asr/identifier-list (identifier-list '(foo))))
  (is      (s/valid? ::asr/identifier-list (identifier-list #{'foo})))
  (is (not (s/valid? ::asr/identifier-list (identifier-list {'foo 'bar}))))
  (is      (s/valid? ::asr/identifier-list (identifier-list ['foo 'bar])))
  (is      (s/valid? ::asr/identifier-list (identifier-list '(foo bar))))
  (is      (s/valid? ::asr/identifier-list (identifier-list #{'foo 'bar})))
  (is      (s/valid? ::asr/identifier-list (identifier-list ['foo 'foo])))
  ;; check that duplicates are allowed
  (is (= 2 (count (identifier-list ['foo 'foo]))))
  (is      (s/valid? ::asr/identifier-list (identifier-list '(foo foo))))
  ;; #{'foo 'foo} won't compile!
  (is (not (s/valid? ::asr/identifier-list (identifier-list ['foo 123]))))
  (is (not (s/valid? ::asr/identifier-list (identifier-list ['foo "foo"]))))
  (is (not (s/valid? ::asr/identifier-list (identifier-list ['foo :foo])))))


(deftest identifier-suit-test
  (is      (s/valid? ::asr/identifier-suit (identifier-suit [])))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit ())))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit #{})))
  (is (not (s/valid? ::asr/identifier-suit (identifier-suit {}))))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit ['foo])))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit '(foo))))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit #{'foo})))
  (is (not (s/valid? ::asr/identifier-suit (identifier-suit {'foo 'bar}))))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit ['foo 'bar])))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit '(foo bar))))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit #{'foo 'bar})))
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
    (is      (s/valid? ::asr/asr-term Local))
    (is      (s/valid? ::asr/asr-term Unspecified))
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
  (testing "term entity-key"
    (is      (s/valid? ::asr/intent (intent 'Local)))
    (is      (s/valid? ::asr/intent (intent 'Unspecified)))
    (is (not (s/valid? ::asr/intent (intent 'foobar))))
    (is (not (s/valid? ::asr/intent (intent []))))
    (is (not (s/valid? ::asr/intent (intent ()))))
    (is (not (s/valid? ::asr/intent (intent {}))))
    (is (not (s/valid? ::asr/intent (intent #{}))))
    (is (not (s/valid? ::asr/intent (intent "foobar"))))
    (is (not (s/valid? ::asr/intent (intent ""))))
    (is (not (s/valid? ::asr/intent (intent 42))))
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
  (is (s/valid? ::asr/storage-type Default))
  (is (s/valid? ::asr/asr-term     Default))

  (is (s/valid? ::asr/storage-type (storage-type 'Default)))
  (is (s/valid? ::asr/asr-term     (storage-type 'Default)))

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

#_
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
  (testing "valid examples"
    (is (s/valid? ::asr/asr-term
                  {::asr/term        ::asr/abi
                   ::asr/abi-enum     'Source
                   ::asr/abi-external  false}))
    (is (s/valid? ::asr/asr-term (abi 'Source         :external false)))
    (is (s/valid? ::asr/asr-term (abi 'LFortranModule :external true)))
    (is (s/valid? ::asr/asr-term (abi 'GFortranModule :external true)))
    (is (s/valid? ::asr/asr-term (abi 'BindC          :external true)))
    (is (s/valid? ::asr/asr-term (abi 'Interactive    :external true)))
    (is (s/valid? ::asr/asr-term (abi 'Intrinsic      :external true)))
    (is (s/valid? ::asr/asr-term Source         ))
    (is (s/valid? ::asr/asr-term LFortranModule ))
    (is (s/valid? ::asr/asr-term GFortranModule ))
    (is (s/valid? ::asr/asr-term BindC          ))
    (is (s/valid? ::asr/asr-term Interactive    ))
    (is (s/valid? ::asr/asr-term Intrinsic      ))
    (is (s/valid? ::asr/abi      (abi 'Source         :external false)))
    (is (s/valid? ::asr/abi      (abi 'LFortranModule :external true)))
    (is (s/valid? ::asr/abi      (abi 'GFortranModule :external true)))
    (is (s/valid? ::asr/abi      (abi 'BindC          :external true)))
    (is (s/valid? ::asr/abi      (abi 'Interactive    :external true)))
    (is (s/valid? ::asr/abi      (abi 'Intrinsic      :external true)))
    (is (s/valid? ::asr/abi      Source         ))
    (is (s/valid? ::asr/abi      LFortranModule ))
    (is (s/valid? ::asr/abi      GFortranModule ))
    (is (s/valid? ::asr/abi      BindC          ))
    (is (s/valid? ::asr/abi      Interactive    ))
    (is (s/valid? ::asr/abi      Intrinsic      ))
    )
  (testing "invalid examples"
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

    (testing "incorrect :external ::bool value"
      (is (not (s/valid? ::asr/abi
                         {::asr/term        ::asr/abi
                          ::asr/abi-enum     'Source
                          ::asr/abi-external  true})))
      (is (not (s/valid? ::asr/abi
                         (abi 'Source         :external true))))
      (is (not (s/valid? ::asr/abi
                         (abi 'LFortranModule :external false))))
      (is (not (s/valid? ::asr/abi
                         (abi 'GFortranModule :external false))))
      (is (not (s/valid? ::asr/abi
                         (abi 'BindC          :external false))))
      (is (not (s/valid? ::asr/abi
                         (abi 'Interactive    :external false))))
      (is (not (s/valid? ::asr/abi
                         (abi 'Intrinsic      :external false)))))
    (testing "incorrect :external type"
      (is (not (s/valid? ::asr/abi (abi 'Source :external 42))))
      (is (not (s/valid? ::asr/abi (abi 'Source :external "foo"))))
      (is (not (s/valid? ::asr/abi (abi 'Source :external 'foo)))))
    (testing "missing keyword; :external defaults to nil"
      (is (not (s/valid? ::asr/abi (abi 'Source false))))
      (is (not (s/valid? ::asr/abi (abi 'Source true))))
      (is (not (s/valid? ::asr/abi (abi 'Source 42))))
      (is (not (s/valid? ::asr/abi (abi 'foo true))))
      (is (not (s/valid? ::asr/abi (abi 'foo false)))))

    (testing "connformance"
     (is (= (s/valid? ::asr/asr-term
                    {::asr/term ::asr/abi
                     ::asr/abi-enum 'Source
                     ::asr/abi-external false})   true))
     (is (= (s/valid? ::asr/abi
                    {::asr/term      ::asr/abi
                     ::asr/abi-enum 'Source
                     ::asr/abi-external false})   true))

     (let [abe (abi 'Source :external false)]
       (is (= (s/conform ::asr/abi      abe)      abe))
       (is (= (s/conform ::asr/asr-term abe)      abe))
       ;; defaults to correct value
       (is (= (abi 'Source)                       abe))
       (is (= Source                              abe))
       ;; missing keyword
       (is (= (abi 'Source false)                 ::asr/invalid-abi))
       ;; wrong value
       (is (= (abi 'Source :external true)        ::asr/invalid-abi))
       ;; misspellings
       (is (= (abi 'Soruce :external false)       ::asr/invalid-abi))
       (is (= (abi 'Source :extrenal false)       ::asr/invalid-abi)))

     (let [abe (abi 'LFortranModule :external true)]
       (is (= (s/conform ::asr/asr-term abe)      abe))
       (is (= (s/conform ::asr/abi      abe)      abe))
       ;; defaults to correct value
       (is (= (abi 'LFortranModule)               abe))
       (is (= LFortranModule                      abe))
       ;; missing keyword
       (is (= (abi 'LFortranModule true)          ::asr/invalid-abi))
       ;; wrong value
       (is (= (abi 'LFortranModule :external )    ::asr/invalid-abi))
       ))

    (testing "ASDL Back-Channel"
     (let [ablf {::asr/term         ::asr/abi
                 ::asr/abi-enum     'Source
                 ::asr/abi-external false}]
       (is (= (s/valid? ::asr/asr-term ablf)          true))
       (is (= (s/valid? ::asr/abi ablf)               true)))

     (is (= (s/conform ::asr/abi      Source)         Source))
     (is (= (s/conform ::asr/asr-term Source)         Source))
     (is (= (abi 'Source false)                       ::asr/invalid-abi))
     (is (= (abi 'Source :external true)              ::asr/invalid-abi))

     (is (= (s/conform ::asr/asr-term LFortranModule) LFortranModule))
     (is (= (s/conform ::asr/abi      LFortranModule) LFortranModule))
     (is (= (abi 'LFortranModule true)                ::asr/invalid-abi))
     (is (= (abi 'LFortranModule :external false)     ::asr/invalid-abi)))
    ))


;; ================================================================
;;  _____ _______   ______  _____
;; |_   _|_   _\ \ / /  _ \| ____|
;;   | |   | |  \ V /| |_) |  _|
;;   | |   | |   | | |  __/| |___
;;   |_|   |_|   |_| |_|   |_____|


(defn vt? [intermediate ttype]
  (and (s/valid? ::asr/asr-term ttype)
       (s/valid? ::asr/ttype    ttype)
       (s/valid? intermediate   ttype)))


(deftest ttype-test
  (testing "top-level-types"
    (is      (s/valid? ::asr/Logical (Logical)))
    (is      (s/valid? ::asr/Integer (Integer)))
    (is (not (s/valid? ::asr/Logical (Integer))))
    (is (not (s/valid? ::asr/Integer (Logical)))))
  (testing "full-sugar and light-sugar)"
    (is (vt? ::asr/Integer (Integer)))
    (is (vt? ::asr/Integer (Integer 4)))
    (is (vt? ::asr/Integer (Integer 4 [])))
    (is (vt? ::asr/Integer (Integer 4 [[6 60] [1 42]])))
    (is (vt? ::asr/Integer (Integer)))
    (is (vt? ::asr/Integer (Integer 8)))
    (is (vt? ::asr/Integer (Integer 8 [])))
    (is (vt? ::asr/Integer (Integer 8 [[6 60] [1 82]])))

    (is (vt? ::asr/Logical (Logical)))
    (is (vt? ::asr/Logical (Logical 4)))
    (is (vt? ::asr/Logical (Logical 4 [])))
    (is (vt? ::asr/Logical (Logical 4 [[6 60] [1 42]])))

    (is (vt? ::asr/Integer (Integer- {:dimensions [], :kind 4})))
    (is (vt? ::asr/Integer (Integer- {:kind 4, :dimensions []})))
    (testing "non-conformance"
      (is (not (vt? ::asr/Logical (Logical 4 ['fubar]))))
      (is (not (vt? ::asr/Logical (Logical 8))))
      (is (not (vt? ::asr/Logical (Logical 0 []))))
      (is (not (vt? ::asr/Logical (Logical 42 [[6 60] [42]]))))))
  (testing "full-form"
    (is (vt? ::asr/Integer
         {::asr/term ::asr/ttype,
          ::asr/asr-ttype-head
          {::asr/ttype-head   ::asr/Integer,
           ::asr/integer-kind 4
           ::asr/dimensions   []}}))
    (is (vt? ::asr/Real
         {::asr/term ::asr/ttype,
          ::asr/asr-ttype-head
          {::asr/ttype-head   ::asr/Real,
           ::asr/real-kind    4
           ::asr/dimensions   []}})))
  (testing "defaults"
    (is (= (Integer)
           (Integer 4 [])))
    (is (= (Integer 4)
           (Integer 4 []))))
  (testing "order-independence of light sugar"
    (is (= (Integer- {:dimensions [], :kind 4})
           (Integer- {:kind 4, :dimensions []}))))
  (testing "migrating from spec.clj"
    (is (= (s/valid? ::asr/asr-ttype-head
                     {::asr/ttype-head ::asr/Integer
                      ::asr/integer-kind 42 ;; wrong kind
                      ::asr/dimensions []})               false))

    (is (= (s/valid? ::asr/asr-ttype-head
                     {::asr/ttype-head ::asr/Integer
                      ::asr/integer-kind 4
                      ::asr/dimensions []})               true))

    (is (= (s/valid? ::asr/asr-ttype-head
                     {::asr/ttype-head ::asr/Integer
                      ::asr/integer-kind 4
                      ::asr/dimensions
                      (dimensions [[6 60] [1 42]])})      true))

    ;; Check a conformed one.
    (let [a {::asr/ttype-head   ::asr/Integer
             ::asr/integer-kind 4
             ::asr/dimensions
             (dimensions [[6 60] [1 42]])}]
      (is (= (s/conform ::asr/asr-ttype-head a)           a)))

    ;; Check a Real instead of an Integer.
    (let [a {::asr/ttype-head   ::asr/Real
             ::asr/real-kind    8
             ::asr/dimensions
             (dimensions [[6 60] [1 42]])}]
      (is (= (s/conform ::asr/asr-ttype-head a)           a)))

    (is (= (s/valid? ::asr/asr-term
                     {::asr/term ::asr/ttype,
                      ::asr/asr-ttype-head
                      {::asr/ttype-head ::asr/Real,
                       ::asr/real-kind  4
                       ::asr/dimensions []}})               true))

    (is (= (s/valid? ::asr/asr-term
                     {::asr/term ::asr/ttype,
                      ::asr/asr-ttype-head
                      {::asr/ttype-head ::asr/Real,
                       ::asr/real-kind  2 ;; wrong kind
                       ::asr/dimensions []}})               false)))
  (testing "sugar"
    (is (= (s/valid? ::asr/asr-ttype-head
                     (::asr/asr-ttype-head (Integer 4)))    true))
    (is (= (s/valid? ::asr/asr-ttype-head
                     (::asr/asr-ttype-head (Integer 42)))   false))
    (is (= (s/valid? ::asr/asr-ttype-head
                     (::asr/asr-ttype-head (Integer)))      true))
    (is (= (s/valid? ::asr/asr-ttype-head
                     (::asr/asr-ttype-head (Integer 4 []))) true)))
  (testing "more sugar"
    (is (= (s/valid? ::asr/asr-term (Integer 4))                  true))
    (is (= (s/valid? ::asr/asr-term (Integer 4 []))               true))
    (is (= (s/valid? ::asr/asr-term (Integer 4 [[6 60] [1 42]]))  true))
    (is (= (s/valid? ::asr/asr-term (Integer 4 ["foo"]))          false))
    (is (= (s/valid? ::asr/asr-term (Integer 42 []))              false))
    (is (= (s/valid? ::asr/asr-term (Real  1 []))                 false))
    (is (= (s/valid? ::asr/asr-term (Logical 8 []))               false))

    (is (= (s/valid? ::asr/ttype    (Integer 4))                  true))
    (is (= (s/valid? ::asr/ttype    (Integer 4 []))               true))
    (is (= (s/valid? ::asr/ttype    (Integer 4 [[6 60] [1 42]]))  true))
    (is (= (s/valid? ::asr/ttype    (Integer 4 ["foo"]))          false))
    (is (= (s/valid? ::asr/ttype    (Integer 42 []))              false))
    (is (= (s/valid? ::asr/ttype    (Real  1 []))                 false))
    (is (= (s/valid? ::asr/ttype    (Logical 8 []))               false))

    (is (= (s/valid? ::asr/ttype
                     (Integer- {:dimensions [], :kind 4}))  true))
    (is (= (s/valid? ::asr/ttype
                     (Integer- {:kind 4, :dimensions []}))  true))
    ))


;;                _         _  _        _    _
;;  ____  _ _ __ | |__  ___| || |_ __ _| |__| |___
;; (_-< || | '  \| '_ \/ _ \ ||  _/ _` | '_ \ / -_)
;; /__/\_, |_|_|_|_.__/\___/_|_\__\__,_|_.__/_\___|
;;     |__/                 |___|


(deftest symbol-table-test
  (is (= (s/valid? ::asr/asr-term
                   (SymbolTable 42 {:main 'main}))    true))
  (is (= (s/valid? ::asr/SymbolTable
                   (SymbolTable 42 {:main 'main}))    true))
  (is (= (s/valid? ::asr/SymbolTable
                   (SymbolTable 'foo {:main 'main}))  false))
  (is (= (s/valid? ::asr/SymbolTable
                   (SymbolTable 42 [:main 'main]))    false)))


;;  ___             _   _        _____
;; | __|  _ _ _  __| |_(_)___ _ |_   _|  _ _ __  ___
;; | _| || | ' \/ _|  _| / _ \ ' \| || || | '_ \/ -_)
;; |_| \_,_|_||_\__|\__|_\___/_||_|_| \_, | .__/\___|
;;                                    |__/|_|


(deftest function-type-test
  (let [ft (FunctionType
            [] () Source
            Implementation () false
            false false false
            false [] [] false)]
    (is (= (s/valid?  ::asr/asr-term      ft)  true))
    (is (= (s/valid?  ::asr/ttype         ft)  true))
    (is (= (s/valid?  ::asr/FunctionType  ft)  true))))


;;  _              _         _  ___             _            _
;; | |   ___  __ _(_)__ __ _| |/ __|___ _ _  __| |_ __ _ _ _| |_
;; | |__/ _ \/ _` | / _/ _` | | (__/ _ \ ' \(_-<  _/ _` | ' \  _|
;; |____\___/\__, |_\__\__,_|_|\___\___/_||_/__/\__\__,_|_||_\__|
;;           |___/


(deftest LogicalConstant-test
  (testing "valid"
   (let [alv {::asr/term ::asr/expr,
              ::asr/asr-expr-head
              {::asr/expr-head ::asr/LogicalConstant
               ::asr/bool      true
               ::asr/Logical   (Logical)}}]
     ;; telescoping specs
     (is (= (s/valid? ::asr/asr-term        alv) true))
     (is (= (s/valid? ::asr/expr            alv) true))
     (is (= (s/valid? ::asr/LogicalConstant alv) true))

     (is (= alv (LogicalConstant true)))
     (is (= alv (LogicalConstant true (Logical 4 []))))
     (is (= alv (LogicalConstant true (Logical 4))))
     (is (= alv (LogicalConstant true (Logical))))
     ))

  ;; invalid
  (testing "invalid"
   (let [alv {::asr/term ::asr/expr,
              ::asr/asr-expr-head
              {::asr/expr-head ::asr/LogicalConstant
               ::asr/bool      true
               ::asr/Logical   (Integer)}}]
     (is (= (s/valid? ::asr/expr     alv)        false))
     (is (= (s/valid? ::asr/LogicalConstant alv) false))
     (is (= (s/valid? ::asr/asr-term alv)        false))))
  )


;; __   __
;; \ \ / /_ _ _ _
;;  \ V / _` | '_|
;;   \_/\__,_|_|


(deftest Var-test
 (let [vlv {::asr/term ::asr/expr,
            ::asr/asr-expr-head
            {::asr/expr-head  ::asr/Var
             ::asr/symtab-id  2
             ::asr/varnym     'x
             }}]
   (is (= (Var-- 2 'x) vlv))
   (is (= (Var   2  x) vlv))
   (is (= (Var-- 2 'x) (s/conform ::asr/Var   vlv)))
   (is (= (Var   2  x) (s/conform ::asr/Var   vlv)))
   (is (= (s/conform ::asr/Var (Var-- 2 'x))  vlv))
   (is (= (s/conform ::asr/Var (Var   2  x))  vlv))
   (is (= (s/valid?  ::asr/asr-term vlv)      true))
   (is (= (s/valid?  ::asr/Var      vlv)      true))))


;;  _              _         _ ___ _      ___
;; | |   ___  __ _(_)__ __ _| | _ |_)_ _ / _ \ _ __
;; | |__/ _ \/ _` | / _/ _` | | _ \ | ' \ (_) | '_ \
;; |____\___/\__, |_\__\__,_|_|___/_|_||_\___/| .__/
;;           |___/                            |_|


(deftest LogicalBinOp-test
  (is (s/valid? ::asr/LogicalBinOp
                (LogicalBinOp
                 (Var 2 a)
                 And
                 (Var 2 b)
                 (Logical 4 [])
                 ()))))


;;  _              _         _  ___
;; | |   ___  __ _(_)__ __ _| |/ __|___ _ __  _ __  __ _ _ _ ___
;; | |__/ _ \/ _` | / _/ _` | | (__/ _ \ '  \| '_ \/ _` | '_/ -_)
;; |____\___/\__, |_\__\__,_|_|\___\___/_|_|_| .__/\__,_|_| \___|
;;           |___/                           |_|


(deftest LogicalCompare-test
  (is (s/valid? ::asr/LogicalCompare
                (LogicalCompare
                 (Var 2 b)
                 Eq
                 (Var 2 b)
                 (Logical 4 []) ()))))


;;  _
;; | |___ __ _ __ _ __ _  _
;; | / -_) _` / _` / _| || |
;; |_\___\__, \__,_\__|\_, |
;;       |___/         |__/

;; TODO

#_
(deftest legacy-test
  (testing "switching of namespaces"
    (is (= (with-out-str (print *ns*))
           "#namespace[masr.specs]"))
    ))


;;    _          _                         _
;;   /_\   _____(_)__ _ _ _  _ __  ___ _ _| |_
;;  / _ \ (_-<_-< / _` | ' \| '  \/ -_) ' \  _|
;; /_/ \_\/__/__/_\__, |_||_|_|_|_\___|_||_\__|
;;                |___/


(deftest Assignment-test
  (is (s/valid? ::asr/Assignment
                (Assignment--
                 (Var 2 a)
                 (LogicalConstant false (Logical 4 []))
                 ())))
  (testing "legacy"
    (is (= (Assignment--
            (Var 2 a)
            (LogicalConstant false (Logical 4 []))
            ())
           (legacy
            (= (Var 2 a)
               (LogicalConstant false (Logical 4 []))
               ()))
           ))
    (is (s/valid? ::asr/Assignment
                  (legacy
                   (= (Var 2 a)
                      (LogicalConstant false (Logical 4 []))
                      ()))))
    (let [e (legacy
             (= (Var 2 a)
                (LogicalBinOp
                 (Var 2 a)
                 And
                 (LogicalCompare
                  (Var 2 b)
                  Eq
                  (Var 2 b)
                  (Logical 4 []) ())
                 (Logical 4 []) ()) ()))]
      (is (s/valid? ::asr/Assignment e))
      (is (s/valid? ::asr/stmt       e))
      (is (s/valid? ::asr/asr-term   e)))
    (is (s/valid? ::asr/Assignment
                  (legacy
                   (= (Var 2 a)
                      (LogicalBinOp
                       (Var 2 a)
                       And
                       (LogicalCompare
                        (Var 2 b)
                        NotEq
                        (Var 2 b)
                        (Logical 4 []) ())
                       (Logical 4 []) ()) ()))))
    (is (s/valid? ::asr/Assignment
                  (legacy
                   (= (Var 2 a)
                      (LogicalBinOp
                       (Var 2 b)
                       Or
                       (Var 2 b)
                       (Logical 4 []) ()) ()))))    ))


;; __   __        _      _    _
;; \ \ / /_ _ _ _(_)__ _| |__| |___
;;  \ V / _` | '_| / _` | '_ \ / -_)
;;   \_/\__,_|_| |_\__,_|_.__/_\___|


(deftest Variable-test
  (testing "full-form"
    (let [a-var-head
          {::asr/symbol-head      ::asr/Variable

           ::asr/symtab-id        2
           ::asr/varnym           'x
           ::asr/ttype            (Integer 4 [])

           ::asr/type-declaration nil
           ::asr/dependencies     ()
           ::asr/intent           Local

           ::asr/symbolic-value   () ;; TODO sugar
           ::asr/value            () ;; TODO sugar
           ::asr/storage-type     Default

           ::asr/abi              Source
           ::asr/access           Public
           ::asr/presence         Required
           ::asr/value-attr       false
           }
          ;; nested
          a-var {::asr/term ::asr/symbol
                 ::asr/asr-symbol-head a-var-head}
          a-var-light (Variable- :varnym     'x
                                 :symtab-id  2
                                 :ttype      (Integer 4))
          avl-2  (Variable- :varnym     'x
                            :symtab-id  2
                            :ttype      (Integer 42))]
      (is (= a-var-light (s/conform ::asr/asr-term a-var)))
      (is (= a-var-light (s/conform ::asr/Variable a-var)))

      (is (= (s/valid? ::asr/asr-symbol-head a-var-head) true))

      (is (= (s/valid? ::asr/asr-term a-var)             true))
      (is (= (s/valid? ::asr/asr-term a-var-light)       true))
      (is (= (s/valid? ::asr/asr-term avl-2)             false))

      (is (= (s/valid? ::asr/symbol   a-var)             true))
      (is (= (s/valid? ::asr/symbol   a-var-light)       true))
      (is (= (s/valid? ::asr/symbol   avl-2)             false))

      (is (= (s/valid? ::asr/Variable a-var)             true))
      (is (= (s/valid? ::asr/Variable a-var-light)       true))
      (is (= (s/valid? ::asr/Variable avl-2)             false))
      ))
  (testing "light sugar"
    ;; fully spec'ced, order does not matter
    (let [a-valid
          (Variable- :symtab-id        2
                     :varnym           'x
                     :intent           Local ;; ASDL back-channel

                     :ttype            (Integer)
                     :access           Private
                     :presence         Required

                     :abi              Source
                     :type-declaration nil
                     :value-attr       false

                     :symbolic-value   []
                     :value            []
                     :storage-type     Default

                     :dependencies     ['y 'z]
                     )]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    'x,
                     :intent    (intent 'Local) ;; explicit
                     :ttype     (Integer 4 [[1 42]]))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    'x,
                     :intent    Local ;; ASDL back-channel
                     :ttype     (Integer 4 [[1 42]]))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid ;; default intent
          (Variable- :symtab-id 2,
                     :varnym    'x,
                     :ttype     (Integer 4 [[1 42]]))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id (symtab-id 2),
                     :varnym    'x,
                     :ttype     (Integer 4 [[1 42]]))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    'x,
                     :ttype     (Integer 4 [[1 42]]))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    'x,
                     :ttype     (Integer 4 [[1 42]])
                     ;; explicit abi
                     :abi       (abi 'Source :external false))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    'x,
                     :ttype     (Integer 4 [[1 42]])
                     ;; explicit defaulted abi
                     :abi       (abi 'Source))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid))) ;; invalid examples
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    'x,
                     :ttype     (Integer 4 [[1 42]])
                     ;; explicit ASDL back-channel abi
                     :abi       Source)]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    'x,
                     :ttype     (Integer)
                     :abi       Source)]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-inval
          (Variable- :symtab-id 2,
                     :varnym    'x,
                     :ttype     (Integer 4 [[1 42]])
                     ;; wrong abi
                     :abi       (abi 'Source :external true))]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/symbol   a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval)))))
  (testing "back tests"
    (let [v (Variable-- 2 'a (Logical 4)
                        nil [] 'Local
                        [] [] 'Default
                        'Source 'Public 'Required
                        false)]
      (is (not (s/valid? ::asr/LogicalBinOp    v)))
      (is (not (s/valid? ::asr/LogicalConstant v)))
      (is (not (s/valid? ::asr/Logical         v)))
      (is (not (s/valid? ::asr/stmt            v)))
      (is (not (s/valid? ::asr/Assignment      v)))
      ))
  (testing "heavy sugar valid examples"
    (let [a-valid (Variable-- 2 'x (Integer 4)
                              nil [] 'Local
                              [] []  'Default
                              'Source 'Public 'Required
                              false)]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid (Variable-- 2 'x (Integer 4)
                              nil [] Local
                              [] []  Default
                              Source Public Required
                              false)]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid))))
  (testing "heavy sugar invalid examples"
    (let [a-inval (Variable-- "foo" 'x (Integer 4) ;; bad symtab-id
                              nil [] 'Local
                              [] []  'Default
                              'Source 'Public 'Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 "foo" (Integer 4) ;; bad varnym
                              nil [] 'Local
                              [] []  'Default
                              'Source 'Public 'Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 'x (Integer 42424242) ;; bad ttupe
                              nil [] 'Local
                              [] []  'Default
                              'Source 'Public 'Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 'x (Integer 42424242)
                              'FOOBAR [] 'Local ;; bad dependencies
                              [] []  'Default
                              'Source 'Public 'Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 'x (Integer 4)
                              nil [] 'FOOBAR ;; bad intent
                              [] []  'Default
                              'Source 'Public 'Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 'x (Integer 4)
                              nil ['x 'y "foo"] 'Local ;; bad dependencies
                              [] []  'Default
                              'Source 'Public 'Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 'x (Integer 4)
                              nil [] 'Local
                              [] []  'FOOBAR ;; bad storage-type
                              'Source 'Public 'Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 'x (Integer 4)
                              nil [] 'Local
                              [] []  'Default
                              'FOOBAR 'Public 'Required ;; bad abi
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 'x (Integer 4)
                              nil [] 'Local
                              [] []  'Default
                              'Source 'FOOBAR 'Required ;; bad access
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 'x (Integer 4)
                              nil [] 'Local
                              [] []  'Default
                              'Source 'Public 'FOOBAR ;; bad presence
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 'x (Integer 4)
                              nil [] 'Local
                              [] []  'Default
                              'Source 'Public 'Required
                              'FOOBAR)] ;; bad value-attr
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval)))))
  (testing "legacy macro"
   (let [v (Variable
            2 a []
            Local () ()
            Default (Logical 4 []) Source
            Public Required false)]
     ;; using "legacicated" symbols:
     (is (= v (Variable-- 2 'a (Logical 4)
                          nil [] Local
                          [] [] Default
                          Source Public Required
                          false)))
     ;; using tick marks (quoted symbolic constants)
     (is (= v (Variable-- 2 'a (Logical 4)
                          nil [] 'Local
                          [] [] 'Default
                          'Source 'Public 'Required
                          false)))
     ;; using telescoping specs
     (is (s/valid? ::asr/Variable v))
     (is (s/valid? ::asr/symbol   v))
     (is (s/valid? ::asr/asr-term v))))
  (testing "SymbolTable with Variable"
   (let [st (SymbolTable
             2 {:a
                (Variable
                 2 a [] Local
                 () () Default (Logical 4 [])
                 Source Public Required false),
                :b
                (Variable
                 2 b [] Local
                 () () Default (Logical 4 [])
                 Source Public Required false)})]
     (is (not (s/valid? ::asr/symbol st)))
     (is (s/valid? ::asr/SymbolTable st))
     (is (s/valid? ::asr/asr-term    st))))
  )


;;  ___             _   _
;; | __|  _ _ _  __| |_(_)___ _ _
;; | _| || | ' \/ _|  _| / _ \ ' \
;; |_| \_,_|_||_\__|\__|_\___/_||_|


(deftest Function-test
  (let [ft (FunctionType
            [] () Source
            Implementation () false
            false false false
            false [] [] false)
        afn (Function
             (SymbolTable 42 {})
             test_boolOp ft []
             [] [] ()
             Public false false)]
    (is (s/valid? ::asr/asr-term afn))
    (is (s/valid? ::asr/symbol   afn))
    (is (s/valid? ::asr/Function afn))
    )
  (let [afn
        (legacy
         (Function
          (SymbolTable
           2 {:a
              (Variable
               2 a [] Local
               () () Default (Logical 4 [])
               Source Public Required false),
              :b
              (Variable
               2 b [] Local
               () () Default (Logical 4 [])
               Source Public Required false)})
          test_boolOp
          (FunctionType
           [] () Source
           Implementation () false
           false false false
           false [] [] false)
          [] []
          [(= (Var 2 a)
              (LogicalConstant false (Logical 4 []))
              ())
           (= (Var 2 b)
              (LogicalConstant true (Logical 4 []))
              ())
           (= (Var 2 a)
              (LogicalBinOp
               (Var 2 a)
               And
               (Var 2 b)
               (Logical 4 []) ()) ())
           (= (Var 2 b)
              (LogicalBinOp
               (Var 2 a)
               Or
               (LogicalConstant true (Logical 4 []))
               (Logical 4 []) ()) ())
           (= (Var 2 a)
              (LogicalBinOp
               (Var 2 a)
               Or
               (Var 2 b)
               (Logical 4 []) ()) ())
           (= (Var 2 a)
              (LogicalBinOp
               (Var 2 a)
               And
               (LogicalCompare
                (Var 2 b)
                Eq
                (Var 2 b)
                (Logical 4 []) ())
               (Logical 4 []) ()) ())
           (= (Var 2 a)
              (LogicalBinOp
               (Var 2 a)
               And
               (LogicalCompare
                (Var 2 b)
                NotEq
                (Var 2 b)
                (Logical 4 []) ())
               (Logical 4 []) ()) ())
           (= (Var 2 a)
              (LogicalBinOp
               (Var 2 b)
               Or
               (Var 2 b)
               (Logical 4 []) ()) ())]
          () Public false false))]
    (is (s/valid? ::asr/asr-term afn))
    (is (s/valid? ::asr/symbol   afn))
    (is (s/valid? ::asr/Function afn))
    ))


;;  ___
;; | _ \_ _ ___  __ _ _ _ __ _ _ __
;; |  _/ '_/ _ \/ _` | '_/ _` | '  \
;; |_| |_| \___/\__, |_| \__,_|_|_|_|
;;              |___/


(deftest Program-test
  (let [p (Program
           (SymbolTable 3 {})
           main_program
           []
           [])]
    (is (s/valid? ::asr/asr-term p))
    (is (s/valid? ::asr/symbol   p))
    (is (s/valid? ::asr/Program  p)))
  (let [p (legacy
           (Program
            (SymbolTable 3 {})
            main_program
            []
            [(= (Var 2 a)
                (LogicalConstant false (Logical 4 []))
                ())]))]
    (is (s/valid? ::asr/asr-term p))
    (is (s/valid? ::asr/symbol   p))
    (is (s/valid? ::asr/Program  p))))


;;  _____                 _      _   _         _   _      _ _
;; |_   _| _ __ _ _ _  __| |__ _| |_(_)___ _ _| | | |_ _ (_) |_
;;   | || '_/ _` | ' \(_-< / _` |  _| / _ \ ' \ |_| | ' \| |  _|
;;   |_||_| \__,_|_||_/__/_\__,_|\__|_\___/_||_\___/|_||_|_|\__|


(deftest TranslationUnit-test
  (let [small {::asr/term ::asr/unit
               ::asr/asr-unit-head
               {::asr/unit-head ::asr/TranslationUnit
                ::asr/SymbolTable (SymbolTable 42 {})
                ::asr/nodes []}}
        medium {::asr/term ::asr/unit
                ::asr/asr-unit-head
                {::asr/unit-head ::asr/TranslationUnit
                 ::asr/SymbolTable (SymbolTable 42 {})
                 ::asr/nodes
                 (map
                  node
                  [(Program
                    (SymbolTable 3 {})
                    main_program
                    []
                    [])])}}
        large {::asr/term ::asr/unit
               ::asr/asr-unit-head
               {::asr/unit-head ::asr/TranslationUnit
                ::asr/SymbolTable (SymbolTable 42 {})
                ::asr/nodes
                (map
                 node
                 [(legacy
                   (Program
                    (SymbolTable 3 {})
                    main_program
                    []
                    [(= (Var 2 a)
                        (LogicalConstant false (Logical 4 []))
                        ())]))])}}]
    (is (s/valid? ::asr/unit small))
    (is (s/valid? ::asr/unit medium))
    (is (s/valid? ::asr/unit large))
    (is (s/valid? ::asr/TranslationUnit small))
    (is (s/valid? ::asr/TranslationUnit medium))
    (is (s/valid? ::asr/TranslationUnit large))

    (is (s/valid?
         ::asr/unit
         (TranslationUnit
          (SymbolTable 42 {})
          [])))
    (is (s/valid?
         ::asr/unit
         (TranslationUnit
          (SymbolTable 42 {})
          [(Program
            (SymbolTable 3 {})
            main_program
            []
            [])])))
    (is (s/valid?
         ::asr/unit
         (legacy
          (TranslationUnit
           (SymbolTable 42 {})
           [(Program
             (SymbolTable 3 {})
             main_program
             []
             [(= (Var 2 a)
                 (LogicalConstant false (Logical 4 []))
                 ())])]))))
    (is (s/valid?
         ::asr/unit
         (legacy
          (TranslationUnit
           (SymbolTable
            1 {:_global_symbols
               (Module
                (SymbolTable
                 4 {:test_boolOp
                    (Function
                     (SymbolTable
                      2 {:a
                         (Variable
                          2 a [] Local
                          () () Default (Logical 4 [])
                          Source Public Required false),
                         :b
                         (Variable
                          2 b [] Local
                          () () Default (Logical 4 [])
                          Source Public Required false)})
                     test_boolOp
                     (FunctionType
                      [] () Source
                      Implementation () false
                      false false false
                      false [] [] false)
                     [] []
                     [(= (Var 2 a)
                         (LogicalConstant false (Logical 4 []))
                         ())
                      (= (Var 2 b)
                         (LogicalConstant true (Logical 4 []))
                         ())
                      (= (Var 2 a)
                         (LogicalBinOp
                          (Var 2 a)
                          And
                          (Var 2 b)
                          (Logical 4 []) ()) ())
                      (= (Var 2 b)
                         (LogicalBinOp
                          (Var 2 a)
                          Or
                          (LogicalConstant true (Logical 4 []))
                          (Logical 4 []) ()) ())
                      (= (Var 2 a)
                         (LogicalBinOp
                          (Var 2 a)
                          Or
                          (Var 2 b)
                          (Logical 4 []) ()) ())
                      (= (Var 2 a)
                         (LogicalBinOp
                          (Var 2 a)
                          And
                          (LogicalCompare
                           (Var 2 b)
                           Eq
                           (Var 2 b)
                           (Logical 4 []) ())
                          (Logical 4 []) ()) ())
                      (= (Var 2 a)
                         (LogicalBinOp
                          (Var 2 a)
                          And
                          (LogicalCompare
                           (Var 2 b)
                           NotEq
                           (Var 2 b)
                           (Logical 4 []) ())
                          (Logical 4 []) ()) ())
                      (= (Var 2 a)
                         (LogicalBinOp
                          (Var 2 b)
                          Or
                          (Var 2 b)
                          (Logical 4 []) ()) ())]
                     () Public false false)})
                '_global_symbols ;; NOTE TICK MARK
                [] false false),
               :main_program
               (Program
                (SymbolTable 3 {})
                main_program
                [] [])}) []))))
    ))


;; ================================================================
;;      __    _    ____  ____  _
;;  ____\ \  / \  / ___||  _ \| |
;; |_____\ \/ _ \ \___ \| | | | |
;; |_____/ / ___ \ ___) | |_| | |___
;;      /_/_/   \_\____/|____/|_____|


(deftest ->asdl-test
  (is (= "Program(symbol_table stab, identifier program_name, identifier* dependencies, stmt* body)"
         (dosafely
          (->asdl-type (Program
                        (SymbolTable 3 {})
                        main_program
                        []
                        [])))))
  (is (= "No method in multimethod '->asdl-type' for dispatch value: null\n"
         (dosafely
          (->asdl-type (Program
                        "messed-up"
                        main_program
                        []
                        [])))))
  (is (= "Var(symbol_table stid, identifier varnym)"
         (->asdl-type (Var 2 x))))
  (is (= "Assignment(expr target, expr value, stmt? overloaded)"
         (->asdl-type (legacy
                       (= (Var 2 a)
                          (LogicalBinOp
                           (Var 2 b)
                           Or
                           (Var 2 b)
                           (Logical 4 []) ()) ()))))))
