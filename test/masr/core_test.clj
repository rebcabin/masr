(ns masr.core-test
  (:use     [masr.core]
            ;; https://groups.google.com/g/clojure/c/i770QaIFiF0 :
            [masr.specs             :as    asr     ])

  (:require [clojure.test           :refer :all    ]
            [clojure.spec.alpha     :as    s       ]
            [clojure.spec.gen.alpha :as    gen     ]
            [clojure.pprint         :refer [pprint]]
            [clojure.set            :as    set     ]
            [clojure.walk           :refer [prewalk]]
            )

  (:require [blaster.clj-fstring    :refer [f-str ]]
            [camel-snake-kebab.core :as    csk     ])

  (:require [masr.utils             :refer [warnings-banner
                                            dosafely
                                            plnecho
                                            plnecho-file
                                            pprint-file]]
            [masr.simplespecs       :refer [nat
                                            identifier
                                            identifier-set
                                            identifier-list
                                            identifier-suit
                                            ]]))


(warnings-banner)


(defmacro idempotency-check [expr]
  (let [ee expr] ;; eval'ed once
   `(testing "idempotency"
      (do (in-ns 'masr.specs)
          (is (= ~ee (eval ~ee) (eval (eval ~ee))))))))


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
    (idempotency-check (nat 42))
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


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_|


(deftest dimension-test
  (idempotency-check (dimension '(1 60)))
  (idempotency-check (dimension [1 60]))
  (idempotency-check (dimension ()))
  (idempotency-check (dimension []))
  (testing "better syntax"
    (is (s/valid? ::asr/asr-term (dimension '(1 60))))
    (is (s/valid? ::asr/asr-term (dimension [1 60])))
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
    (is (not (s/valid? ::asr/asr-term [1])))
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
      (is (s/valid? ::asr/dimension (dimension [1 60])))
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
      (is (not (s/valid? ::asr/dimension [1])))
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
  (s/valid? ::asr/dimension* term))


(deftest dimensions-test
  (idempotency-check (dimension* [[1 60] []]))
  (idempotency-check (dimension* '([1 60] [])))
  (idempotency-check (dimension* []))
  (idempotency-check (dimension* ()))

  (is (s/valid?
       ::asr/dimension*
       [#:masr.specs{:term :masr.specs/dimension,
                     :dimension-content [1 60]}
        #:masr.specs{:term :masr.specs/dimension,
                     :dimension-content ()}]))
  (let [dims [(dimension [1 60]), (dimension [])]]
    (is (s/valid? ::asr/dimension* dims))
    (is (= dims (s/conform ::asr/dimension* dims)))
    (is (= dims (dimension* [[1 60] []])))
    (is (= dims (s/conform ::asr/dimension*
                           (dimension* [[1 60] []])))))
  (is (vds? (dimension* ['(1 60) '()])))
  (is (vds? (dimension* ['(1 60)])))
  (is (vds? (dimension* ['(1 60) []])))
  (is (vds? (dimension* [])))
  (is (vds? (dimension* '((1 60) ()))))
  (is (vds? (dimension* '((1 60)))))
  (is (vds? (dimension* '((1 60) []))))
  (is (vds? (dimension* ())))
  (is (vds? (dimension* [[1 60] []])))
  (is (vds? (dimension* [[1 60]  [42 43]])))
  (is (vds? (dimension* [[1 60] '(42 43)])))
  (is (vds? (dimension* ())))
  (is (not (vds? (dimension* #{'(1 60) []}))))
  (is (not (vds? (dimension* #{}))))
  (is (not (vds? (dimension* {}))))
  (is (not (vds? (dimension* {2 3}))))
  (is (not (vds? (dimension* '("f")))))
  (is (not (vds? (dimension* '[("f")])))))


;;               _        _        _    _
;;  ____  _ _ __| |_ __ _| |__ ___(_)__| |
;; (_-< || | '  \  _/ _` | '_ \___| / _` |
;; /__/\_, |_|_|_\__\__,_|_.__/   |_\__,_|
;;     |__/


(deftest symtab-id-test
  (idempotency-check (symtab-id 42))
  (is (not (s/valid? ::asr/symtab-id (symtab-id -42))))
  (is (not (s/valid? ::asr/symtab-id (symtab-id 'foo))))
  (is (= (symtab-id  42)                            42))
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
  (idempotency-check (identifier "foobar"))
  (testing "better syntax"
    (is      (s/valid? ::asr/identifier (identifier "foobar")))
    (is      (s/valid? ::asr/identifier (identifier "_foobar")))
    (is      (s/valid? ::asr/identifier (identifier "__f_oobar")))
    (is      (s/valid? ::asr/identifier (identifier "_1234")))
    (is      (s/valid? ::asr/identifier (identifier "_1__234")))
    (is (not (s/valid? ::asr/identifier (identifier "1234"))))
    (is (not (s/valid? ::asr/identifier (identifier "1a"))))
    (is (not (s/valid? ::asr/identifier (identifier ""))))
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
    (is (not (s/valid? ::asr/identifier (identifier :asdf)))))

  (is (s/valid? ::asr/identifier      "foobar"))
  (is (s/valid? ::asr/identifier      "_foobar"))
  (is (s/valid? ::asr/identifier      "__f_oobar"))
  (is (s/valid? ::asr/identifier      "_1234"))
  (is (s/valid? ::asr/identifier      "_1__234"))

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
  (is (not (s/valid? ::asr/identifier :asdf))))


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _ ___
;; | / _` / -_) ' \  _| |  _| / -_) '_(_-<
;; |_\__,_\___|_||_\__|_|_| |_\___|_| /__/


(deftest identifier-set-test
  (is      (s/valid? ::asr/identifier-set (identifier-set [])))
  (is      (s/valid? ::asr/identifier-set (identifier-set ())))
  (is      (s/valid? ::asr/identifier-set (identifier-set #{})))
  (is (not (s/valid? ::asr/identifier-set (identifier-set {}))))
  (is      (s/valid? ::asr/identifier-set (identifier-set ["foo"])))
  (is      (s/valid? ::asr/identifier-set (identifier-set '("foo"))))
  (is      (s/valid? ::asr/identifier-set (identifier-set #{"foo"})))
  (is (not (s/valid? ::asr/identifier-set
                     (identifier-set {"foo" "bar"}))))
  (is      (s/valid? ::asr/identifier-set (identifier-set
                                           ["foo" "bar"])))
  (is      (s/valid? ::asr/identifier-set (identifier-set
                                           '("foo" "bar"))))
  (is      (s/valid? ::asr/identifier-set (identifier-set
                                           #{"foo" "bar"})))
  (is      (s/valid? ::asr/identifier-set (identifier-set
                                           ["foo" "foo"])))
  ;; check set-ness
  (is (= 1 (count (identifier-set ["foo" "foo"]))))
  (is      (s/valid? ::asr/identifier-set (identifier-set
                                           '("foo" "foo"))))
  ;; #{'foo 'foo} won't compile!
  (is (not (s/valid? ::asr/identifier-set (identifier-set
                                           ['foo 123]))))
  (is (not (s/valid? ::asr/identifier-set
                     (identifier-set ['foo "foo"]))))
  (is (not (s/valid? ::asr/identifier-set
                     (identifier-set ['foo :foo])))))


(deftest identifier-list-test
  (is      (s/valid? ::asr/identifier-list (identifier-list [])))
  (is      (s/valid? ::asr/identifier-list (identifier-list ())))
  (is      (s/valid? ::asr/identifier-list (identifier-list #{})))
  (is (not (s/valid? ::asr/identifier-list (identifier-list {}))))
  (is      (s/valid? ::asr/identifier-list (identifier-list ["foo"])))
  (is      (s/valid? ::asr/identifier-list (identifier-list '("foo"))))
  (is      (s/valid? ::asr/identifier-list (identifier-list #{"foo"})))
  (is (not (s/valid? ::asr/identifier-list
                     (identifier-list {"foo" "bar"}))))
  (is      (s/valid? ::asr/identifier-list
                     (identifier-list ["foo" "bar"])))
  (is      (s/valid? ::asr/identifier-list
                     (identifier-list '("foo" "bar"))))
  (is      (s/valid? ::asr/identifier-list
                     (identifier-list #{"foo" "bar"})))
  (is      (s/valid? ::asr/identifier-list
                     (identifier-list ["foo" "foo"])))
  ;; check that duplicates are allowed
  (is (= 2 (count (identifier-list ["foo" "foo"]))))
  (is      (s/valid? ::asr/identifier-list
                     (identifier-list '("foo" "foo"))))
  ;; #{'foo 'foo} won't compile!
  (is (not (s/valid? ::asr/identifier-list
                     (identifier-list ['foo 123]))))
  (is (not (s/valid? ::asr/identifier-list
                     (identifier-list ['foo "foo"]))))
  (is (not (s/valid? ::asr/identifier-list
                     (identifier-list ['foo :foo])))))


(deftest identifier-suit-test
  (is      (s/valid? ::asr/identifier-suit (identifier-suit [])))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit ())))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit #{})))
  (is (not (s/valid? ::asr/identifier-suit (identifier-suit {}))))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit ["foo"])))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit '("foo"))))
  (is      (s/valid? ::asr/identifier-suit (identifier-suit #{"foo"})))
  (is (not (s/valid? ::asr/identifier-suit
                     (identifier-suit {"foo" "bar"}))))
  (is      (s/valid? ::asr/identifier-suit
                     (identifier-suit ["foo" "bar"])))
  (is      (s/valid? ::asr/identifier-suit
                     (identifier-suit '("foo" "bar"))))
  (is      (not (= (identifier-suit '("foo" "bar"))
                   (identifier-suit '("bar" "foo")))))
  (is      (s/valid? ::asr/identifier-suit
                     (identifier-suit #{"foo" "bar"})))
  (is (not (s/valid? ::asr/identifier-suit
                     (identifier-suit ["foo" "foo"]))))
  (is (not (s/valid? ::asr/identifier-suit
                     (identifier-suit '("foo" "foo")))))
  ;; #{'foo 'foo} won't compile!
  (is (not (s/valid? ::asr/identifier-suit
                     (identifier-suit ['foo 123]))))
  (is (not (s/valid? ::asr/identifier-suit
                     (identifier-suit ['foo "foo"]))))
  (is (not (s/valid? ::asr/identifier-suit
                     (identifier-suit ['foo :foo])))))


;;  _     _           _
;; (_)_ _| |_ ___ _ _| |_
;; | | ' \  _/ -_) ' \  _|
;; |_|_||_\__\___|_||_\__|



(deftest intent-test
  (testing "better syntax"
    (is      (s/valid? ::asr/asr-term Local))
    (is      (s/valid? ::asr/asr-term Unspecified))
    (is      (s/valid? ::asr/asr-term (intent "Local")))
    (is      (s/valid? ::asr/asr-term (intent "Unspecified")))
    (is (not (s/valid? ::asr/asr-term (intent "foobar"))))
    (is (not (s/valid? ::asr/asr-term (intent []))))
    (is (not (s/valid? ::asr/asr-term (intent ()))))
    (is (not (s/valid? ::asr/asr-term (intent {}))))
    (is (not (s/valid? ::asr/asr-term (intent #{}))))
    (is (not (s/valid? ::asr/asr-term (intent "foobar"))))
    (is (not (s/valid? ::asr/asr-term (intent ""))))
    (is (not (s/valid? ::asr/asr-term (intent 42))))
    (is (thrown? clojure.lang.ArityException (intent))))
  (testing "term entity-key"
    (is      (s/valid? ::asr/intent Local))
    (is      (s/valid? ::asr/intent Unspecified))
    (is      (s/valid? ::asr/intent (intent "Local")))
    (is      (s/valid? ::asr/intent (intent "Unspecified")))
    (is (not (s/valid? ::asr/intent (intent "foobar"))))
    (is (not (s/valid? ::asr/intent (intent []))))
    (is (not (s/valid? ::asr/intent (intent ()))))
    (is (not (s/valid? ::asr/intent (intent {}))))
    (is (not (s/valid? ::asr/intent (intent #{}))))
    (is (not (s/valid? ::asr/intent (intent "foobar"))))
    (is (not (s/valid? ::asr/intent (intent ""))))
    (is (not (s/valid? ::asr/intent (intent 42))))
    (is (thrown? clojure.lang.ArityException (intent))))
  (idempotency-check {::asr/term        ::asr/intent,
                      ::asr/intent-enum "Unspecified"})
  (is (not (s/valid? ::asr/asr-term
                     {::asr/term        ::asr/intent,
                      ::asr/intent-enum Local}))) ;; unquoted
  (is (s/valid? ::asr/asr-term
                {::asr/term        ::asr/intent,
                 ::asr/intent-enum "Local"}))
  (is (s/valid? ::asr/asr-term
                {::asr/term        ::asr/intent,
                 ::asr/intent-enum "Unspecified"}))
  (testing "missing key"
    (is (not (s/valid? ::asr/asr-term
                       {::asr/intent-enum "Unspecified"}))))
  (testing "incorrect value"
    (is (not (s/valid? ::asr/asr-term
                       {::asr/term        ::asr/intent,
                        ::asr/intent-enum "foobar"})))))


;;     _                             _
;;  __| |_ ___ _ _ __ _ __ _ ___ ___| |_ _  _ _ __  ___
;; (_-<  _/ _ \ '_/ _` / _` / -_)___|  _| || | '_ \/ -_)
;; /__/\__\___/_| \__,_\__, \___|    \__|\_, | .__/\___|
;;                     |___/             |__/|_|


(deftest storage-type-test
  (idempotency-check Default)

  (is (s/valid? ::asr/storage-type Default))
  (is (s/valid? ::asr/asr-term     Default))

  (is (s/valid? ::asr/storage-type (storage-type "Default")))
  (is (s/valid? ::asr/asr-term     (storage-type "Default")))

  (is (s/valid? ::asr/asr-term
                {::asr/term ::asr/storage-type
                 ::asr/storage-type-enum "Default"}))
  (testing "incorrect value"
    (is (not (s/valid? ::asr/asr-term
                       {::asr/term ::asr/storage-type,
                        ::asr/storage-type-enum "foobar"})))))


;;       _    _
;;  __ _| |__(_)
;; / _` | '_ \ |
;; \__,_|_.__/_|


(deftest abi-test
  (testing "valid examples"
    (is (s/valid? ::asr/asr-term
                  {::asr/term        ::asr/abi
                   ::asr/abi-enum     "Source"
                   ::asr/abi-external  false}))
    (is (s/valid? ::asr/asr-term
                  (abi "Source" :external false)))
    (is (s/valid? ::asr/asr-term (abi "LFortranModule" :external true)))
    (is (s/valid? ::asr/asr-term (abi "GFortranModule" :external true)))
    (is (s/valid? ::asr/asr-term (abi "BindC"          :external true)))
    (is (s/valid? ::asr/asr-term (abi "Interactive"    :external true)))
    (is (s/valid? ::asr/asr-term (abi "Intrinsic"      :external true)))
    (is (s/valid? ::asr/asr-term Source         ))
    (is (s/valid? ::asr/asr-term LFortranModule ))
    (is (s/valid? ::asr/asr-term GFortranModule ))
    (is (s/valid? ::asr/asr-term BindC          ))
    (is (s/valid? ::asr/asr-term Interactive    ))
    (is (s/valid? ::asr/asr-term Intrinsic      ))
    (is (s/valid? ::asr/abi
                  (abi "Source" :external false)))
    (is (s/valid? ::asr/abi      (abi "LFortranModule" :external true)))
    (is (s/valid? ::asr/abi      (abi "GFortranModule" :external true)))
    (is (s/valid? ::asr/abi      (abi "BindC"          :external true)))
    (is (s/valid? ::asr/abi      (abi "Interactive"    :external true)))
    (is (s/valid? ::asr/abi      (abi "Intrinsic"      :external true)))
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
                          ::asr/abi-enum     "Source"
                          ::asr/abi-external  true})))
      (is (not (s/valid? ::asr/asr-term
                         (abi "Source"         :external true))))
      (is (not (s/valid? ::asr/asr-term
                         (abi "LFortranModule" :external false))))
      (is (not (s/valid? ::asr/asr-term
                         (abi "GFortranModule" :external false))))
      (is (not (s/valid? ::asr/asr-term
                         (abi "BindC"          :external false))))
      (is (not (s/valid? ::asr/asr-term
                         (abi "Interactive"    :external false))))
      (is (not (s/valid? ::asr/asr-term
                         (abi "Intrinsic"      :external false)))))
    (testing "incorrect :external type"
      (is (not (s/valid? ::asr/asr-term (abi "Source" :external 42))))
      (is (not (s/valid? ::asr/asr-term
                         (abi "Source" :external "foo"))))
      (is (not (s/valid? ::asr/asr-term
                         (abi "Source" :external 'foo)))))
    (testing "missing keyword; :external defaults to nil"
      (is (not (s/valid? ::asr/asr-term (abi "Source" false))))
      (is (not (s/valid? ::asr/asr-term (abi "Source" true))))
      (is (not (s/valid? ::asr/asr-term (abi "Source" 42))))
      (is (not (s/valid? ::asr/asr-term (abi "foo" true))))
      (is (not (s/valid? ::asr/asr-term (abi "foo" false)))))

    (testing "incorrect :external ::bool value"
      (is (not (s/valid? ::asr/abi
                         {::asr/term        ::asr/abi
                          ::asr/abi-enum     "Source"
                          ::asr/abi-external  true})))
      (is (not (s/valid? ::asr/abi
                         (abi "Source"         :external true))))
      (is (not (s/valid? ::asr/abi
                         (abi "LFortranModule" :external false))))
      (is (not (s/valid? ::asr/abi
                         (abi "GFortranModule" :external false))))
      (is (not (s/valid? ::asr/abi
                         (abi "BindC"          :external false))))
      (is (not (s/valid? ::asr/abi
                         (abi "Interactive"    :external false))))
      (is (not (s/valid? ::asr/abi
                         (abi "Intrinsic"      :external false)))))
    (testing "incorrect :external type"
      (is (not (s/valid? ::asr/abi (abi "Source" :external 42))))
      (is (not (s/valid? ::asr/abi (abi "Source" :external "foo"))))
      (is (not (s/valid? ::asr/abi (abi "Source" :external 'foo)))))
    (testing "missing keyword; :external defaults to nil"
      (is (not (s/valid? ::asr/abi (abi "Source" false))))
      (is (not (s/valid? ::asr/abi (abi "Source" true))))
      (is (not (s/valid? ::asr/abi (abi "Source" 42))))
      (is (not (s/valid? ::asr/abi (abi "foo" true))))
      (is (not (s/valid? ::asr/abi (abi "foo" false)))))

    (testing "conformance"
      (is (= (s/valid? ::asr/asr-term
                       {::asr/term ::asr/abi
                        ::asr/abi-enum "Source"
                        ::asr/abi-external false})   true))
      (is (= (s/valid? ::asr/abi
                       {::asr/term      ::asr/abi
                        ::asr/abi-enum "Source"
                        ::asr/abi-external false})   true))

      (let [abe (abi "Source" :external false)]
        (is (= (s/conform ::asr/abi      abe)      abe))
        (is (= (s/conform ::asr/asr-term abe)      abe))
        ;; defaults to correct value
        (is (= (abi "Source")                       abe))
        (is (= Source                              abe))
        ;; missing keyword
        (is (not (s/valid? ::asr/abi (abi "Source" false))))
        ;; wrong value
        (is (not (s/valid? ::asr/abi (abi "Source" :external true))))
        ;; misspellings
        (is (not (s/valid? ::asr/abi (abi "Soruce" :external false))))
        (is (not (s/valid? ::asr/abi (abi "Source" :extrenal false)))))

      (let [abe (abi "LFortranModule" :external true)]
        (is (= (s/conform ::asr/asr-term abe)      abe))
        (is (= (s/conform ::asr/abi      abe)      abe))
        ;; defaults to correct value
        (is (= (abi "LFortranModule")               abe))
        (is (= LFortranModule                      abe))
        ;; missing keyword
        (is (not (s/valid? ::asr/abi
                           (abi "LFortranModule" true))))
        ;; wrong value
        (is (not (s/valid? ::asr/abi
                           (abi "LFortranModule" :external ))))
        ))

    (testing "ASDL Back-Channel"
      (let [ablf {::asr/term         ::asr/abi
                  ::asr/abi-enum     "Source"
                  ::asr/abi-external false}]
        (is (= (s/valid? ::asr/asr-term ablf)          true))
        (is (= (s/valid? ::asr/abi ablf)               true)))

      (is (= (s/conform ::asr/abi      Source)         Source))
      (is (= (s/conform ::asr/asr-term Source)         Source))
      (is (not (s/valid? ::asr/abi (abi "Source" false))))
      (is (not (s/valid? ::asr/abi (abi "Source" :external true))))

      (is (= (s/conform ::asr/asr-term
                        LFortranModule) LFortranModule))
      (is (= (s/conform ::asr/abi
                        LFortranModule) LFortranModule))
      (is (not (s/valid? ::asr/abi
                         (abi "LFortranModule" true))))
      (is (not (s/valid? ::asr/abi
                         (abi "LFortranModule" :external false)))))
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
       (s/valid? intermediate   ttype)
       ;; conform causes problems by inserting s/or keys.
       #_(= ttype (s/conform intermediate ttype))))


(deftest ttype-test
  (idempotency-check (Logical))
  (testing "top-level-types"
    (is (s/valid? ::asr/Logical   (Logical)))
    (is (s/valid? ::asr/Integer   (Integer)))
    (is (s/valid? ::asr/Real      (Real)))
    (is (s/valid? ::asr/Complex   (Complex)))
    (is (s/valid? ::asr/Character (Character 1 1 () [])))

    (is (s/valid? ::asr/ttype     (Logical)))
    (is (s/valid? ::asr/ttype     (Integer)))
    (is (s/valid? ::asr/ttype     (Real)))
    (is (s/valid? ::asr/ttype     (Complex)))
    (is (s/valid? ::asr/ttype     (Character 1 1 () [])))

    (is (s/valid? ::asr/asr-term  (Logical)))
    (is (s/valid? ::asr/asr-term  (Integer)))
    (is (s/valid? ::asr/asr-term  (Real)))
    (is (s/valid? ::asr/asr-term  (Complex)))
    (is (s/valid? ::asr/asr-term  (Character 1 1 () [])))

    (is (not (s/valid? ::asr/Logical   (Integer))))
    (is (not (s/valid? ::asr/Real      (Integer))))
    (is (not (s/valid? ::asr/Complex   (Integer))))
    (is (not (s/valid? ::asr/Integer   (Logical)))))
  (testing "full-sugar and light-sugar)"
    (is (vt? ::asr/Integer (Integer)))
    (is (vt? ::asr/Integer (Integer 4)))
    (is (vt? ::asr/Integer (Integer 4 [])))
    (is (vt? ::asr/Integer (Integer 4 [[6 60] [1 42]])))
    (is (vt? ::asr/Integer (Integer)))
    (is (vt? ::asr/Integer (Integer 8)))
    (is (vt? ::asr/Integer (Integer 8 [])))
    (is (vt? ::asr/Integer (Integer 8 [[6 60] [1 82]])))

    (is (vt? ::asr/Real    (Real)))
    (is (vt? ::asr/Real    (Real 4)))
    (is (vt? ::asr/Real    (Real 4 [])))
    (is (vt? ::asr/Real    (Real 4 [[6 60] [1 42]])))
    (is (vt? ::asr/Real    (Real)))
    (is (vt? ::asr/Real    (Real 8)))
    (is (vt? ::asr/Real    (Real 8 [])))
    (is (vt? ::asr/Real    (Real 8 [[6 60] [1 82]])))

    (is (vt? ::asr/Logical (Logical)))
    (is (vt? ::asr/Logical (Logical 4)))
    (is (vt? ::asr/Logical (Logical 4 [])))
    (is (vt? ::asr/Logical (Logical 4 [[6 60] [1 42]])))

    (is (vt? ::asr/Character (Character 1 1 () [])))
    (is (vt? ::asr/Character (Character 1 1 () [[6 60]])))
    (is (vt? ::asr/Character (Character 1 1)))
    (is (vt? ::asr/Character (Character 1 1 [[6 60]])))
    (is (vt? ::asr/Character (Character)))

    (is (vt? ::asr/Integer (Integer- {:dimension* [], :kind 4})))
    (is (vt? ::asr/Integer (Integer- {:kind 4, :dimension* []})))
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
           ::asr/dimension*   []}}))
    (is (vt? ::asr/Real
         {::asr/term ::asr/ttype,
          ::asr/asr-ttype-head
          {::asr/ttype-head   ::asr/Real,
           ::asr/real-kind    4
           ::asr/dimension*   []}})))
  (testing "defaults"
    (is (= (Integer)   (Integer 4 [])))
    (is (= (Integer 4) (Integer 4 []))))
  (testing "order-independence of light sugar"
    (is (= (Integer- {:dimension* [], :kind 4})
           (Integer- {:kind 4, :dimension* []}))))
  (testing "migrating from specs.clj"
    (is (= (s/valid? ::asr/asr-ttype-head
                     {::asr/ttype-head ::asr/Integer
                      ::asr/integer-kind 42 ;; wrong kind
                      ::asr/dimension* []})               false))

    (is (= (s/valid? ::asr/asr-ttype-head
                     {::asr/ttype-head ::asr/Integer
                      ::asr/integer-kind 4
                      ::asr/dimension* []})               true))

    (is (= (s/valid? ::asr/asr-ttype-head
                     {::asr/ttype-head ::asr/Integer
                      ::asr/integer-kind 4
                      ::asr/dimension*
                      (dimension* [[6 60] [1 42]])})      true))

    ;; Check a conformed one.
    (let [a {::asr/ttype-head   ::asr/Integer
             ::asr/integer-kind 4
             ::asr/dimension*
             (dimension* [[6 60] [1 42]])}]
      (is (= (s/conform ::asr/asr-ttype-head a)           a)))

    ;; Check a Real instead of an Integer.
    (let [a {::asr/ttype-head   ::asr/Real
             ::asr/real-kind    8
             ::asr/dimension*
             (dimension* [[6 60] [1 42]])}]
      (is (= (s/conform ::asr/asr-ttype-head a)           a)))

    (is (= (s/valid? ::asr/asr-term
                     {::asr/term ::asr/ttype,
                      ::asr/asr-ttype-head
                      {::asr/ttype-head ::asr/Real,
                       ::asr/real-kind  4
                       ::asr/dimension* []}})               true))

    (is (= (s/valid? ::asr/asr-term
                     {::asr/term ::asr/ttype,
                      ::asr/asr-ttype-head
                      {::asr/ttype-head ::asr/Real,
                       ::asr/real-kind  2 ;; wrong kind
                       ::asr/dimension* []}})               false)))
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
                     (Integer- {:dimension* [], :kind 4}))  true))
    (is (= (s/valid? ::asr/ttype
                     (Integer- {:kind 4, :dimension* []}))  true))
    ))


;;  ___            _         _ _____     _    _
;; / __|_  _ _ __ | |__  ___| |_   _|_ _| |__| |___
;; \__ \ || | '  \| '_ \/ _ \ | | |/ _` | '_ \ / -_)
;; |___/\_, |_|_|_|_.__/\___/_| |_|\__,_|_.__/_\___|
;;      |__/


(deftest symbol-table-test

  (idempotency-check (SymbolTable 42 {:main "main"}))

  (is (s/valid? ::asr/asr-term
                (SymbolTable 42 {:main "main"})))

  (is (s/valid? ::asr/SymbolTable
                (SymbolTable 42 {:main "main"})))

  (is (not (s/valid? ::asr/SymbolTable
                   (SymbolTable "foo" {:main "main"})))))


;;  ___             _   _        _____
;; | __|  _ _ _  __| |_(_)___ _ |_   _|  _ _ __  ___
;; | _| || | ' \/ _|  _| / _ \ ' \| || || | '_ \/ -_)
;; |_| \_,_|_||_\__|\__|_\___/_||_|_| \_, | .__/\___|
;;                                    |__/|_|


(deftest function-type-test
  (testing "idempotency of to-full-form"
    (let [ft (to-full-form
              (to-full-form
               (FunctionType ;; from 1bcc4ec
                []  ()  Source
                Implementation  ()  false
                false  false  false
                false  []  []  false
                )))]
      (testing "idempotency of eval -- 1"
        (is (= ft (eval ft) (eval (eval ft)))))
      (is (s/valid?  ::asr/asr-term      ft))
      (is (s/valid?  ::asr/ttype         ft))
      (is (s/valid?  ::asr/FunctionType  ft))))
  (let [ft (FunctionType ;; looks the same
            [] () Source
            Implementation () false
            false false false
            false [] [] false)]
    (testing "idempotency of eval -- 2"
      (is (= ft (eval ft) (eval (eval ft)))))
    (is (s/valid?  ::asr/asr-term      ft))
    (is (s/valid?  ::asr/ttype         ft))
    (is (s/valid?  ::asr/FunctionType  ft)))
  (let [ft (FunctionType
            [] "crap" Source
            Implementation () false
            false false false
            false [] [] false)]
    (is (not (s/valid?  ::asr/asr-term      ft)))
    (is (not (s/valid?  ::asr/ttype         ft)))
    (is (not (s/valid?  ::asr/FunctionType  ft)))))


;; ================================================================
;;  _______  ______  ____
;; | ____\ \/ /  _ \|  _ \
;; |  _|  \  /| |_) | |_) |
;; | |___ /  \|  __/|  _ <
;; |_____/_/\_\_|   |_| \_\


;;   ___         _
;;  / __|__ _ __| |_
;; | (__/ _` (_-<  _|
;;  \___\__,_/__/\__|


(deftest cast-test
  (is (s/valid? ::asr/call-args
                (legacy ;; fixes round brackets.
                 [((IntegerConstant 2 (Integer 4 [])))
                  ((IntegerConstant 2 (Integer 4 [])))])))

  (let [example (legacy ;; fixes round brackets.
                 (Cast
                  (FunctionCall
                   2 pow__AT____lpython_overloaded_0__pow
                   2 pow
                   [((IntegerConstant 2 (Integer 4 [])))
                    ((IntegerConstant 2 (Integer 4 [])))]
                   (Real 8 [])
                   (RealConstant 4.000000 (Real 8 [])) ())
                  RealToInteger
                  (Integer 4 [])
                  (IntegerConstant 4 (Integer 4 []))))]
    (testing "idempotency"
      (is (= example (eval example) (eval (eval example)))))
    (is (s/valid? ::asr/Cast example)))

  (let [example (legacy ;; fixes round brackets.
                 (Cast
                  (FunctionCall
                   2 pow__AT____lpython_overloaded_0__pow
                   2 0xBADBEEF
                   [((IntegerConstant 2 (Integer 4 [])))
                    ((IntegerConstant 2 (Integer 4 [])))]
                   (Real 8 [])
                   (RealConstant 4.000000 (Real 8 [])) ())
                  RealToInteger
                  (Integer 4 [])
                  (IntegerConstant 4 (Integer 4 []))))]
    (is (not (s/valid? ::asr/Cast example))))

  (let [example (Cast
                 (FunctionCall
                  2 pow__AT____lpython_overloaded_0__pow
                  2 pow
                  (legacy ;; fixes round brackets.
                   [((IntegerConstant 2 (Integer 4 [])))
                    ((IntegerConstant 2 (Integer 4 [])))])
                  (Real 8 [])
                  (RealConstant 4.000000 (Real 8 [])) ())
                 RealToInteger
                 (Integer 4 [])
                 (IntegerConstant 4 (Integer 4 [])))]
    (is (s/valid? ::asr/Cast example)))
  )


;;  _  _                   _ ___
;; | \| |__ _ _ __  ___ __| | __|_ ___ __ _ _
;; | .` / _` | '  \/ -_) _` | _|\ \ / '_ \ '_|
;; |_|\_\__,_|_|_|_\___\__,_|___/_\_\ .__/_|
;;                                  |_|


(deftest named-expr-test
  (let [example (NamedExpr
                 (Var 2 y)
                 (IntegerConstant 0 (Integer 4 []))
                 (Integer 4 [])
                 )]
    (testing "idempotency"
      (is (= example (eval example) (eval (eval example)))))
    (is (s/valid? ::asr/asr-term  example))
    (is (s/valid? ::asr/expr      example))
    (is (s/valid? ::asr/NamedExpr example))))


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
      (testing "idempotency"
        (is (= alv (eval alv) (eval (eval alv)))))
      (testing "telescoping specs"
        (is (s/valid? ::asr/asr-term        alv))
        (is (s/valid? ::asr/expr            alv))
        (is (s/valid? ::asr/LogicalConstant alv)))
      (is (= alv (LogicalConstant true)))
      (is (= alv (LogicalConstant true (Logical 4 []))))
      (is (= alv (LogicalConstant true (Logical 4))))
      (is (= alv (LogicalConstant true (Logical))))
      ))

  (testing "invalid"
    (let [alv {::asr/term ::asr/expr,
               ::asr/asr-expr-head
               {::asr/expr-head ::asr/LogicalConstant
                ::asr/bool      true
                ::asr/Logical   (Integer)}}]
      (testing "telescoping specs"
        (is (not (s/valid? ::asr/asr-term        alv)))
        (is (not (s/valid? ::asr/expr            alv)))
        (is (not (s/valid? ::asr/ttype           alv)))
        (is (not (s/valid? ::asr/LogicalConstant alv))))
      )))


;;  ___     _                     ___             _            _
;; |_ _|_ _| |_ ___ __ _ ___ _ _ / __|___ _ _  __| |_ __ _ _ _| |_
;;  | || ' \  _/ -_) _` / -_) '_| (__/ _ \ ' \(_-<  _/ _` | ' \  _|
;; |___|_||_\__\___\__, \___|_|  \___\___/_||_/__/\__\__,_|_||_\__|
;;                 |___/


(deftest IntegerConstant-test
  (testing "valid"
    (let [aiv {::asr/term ::asr/expr,
               ::asr/asr-expr-head
               {::asr/expr-head ::asr/IntegerConstant
                ::asr/int       42
                ::asr/Integer   (Integer)}}]
      (testing "idempotency"
        (is (= aiv (eval aiv) (eval (eval aiv)))))
      (testing "telescoping specs"
        (is (s/valid? ::asr/asr-term        aiv))
        (is (s/valid? ::asr/expr            aiv))
        (is (s/valid? ::asr/IntegerConstant aiv)))

      (is (= aiv (IntegerConstant 42)))
      (is (= aiv (IntegerConstant 42 (Integer 4 []))))
      (is (= aiv (IntegerConstant 42 (Integer 4))))
      (is (= aiv (IntegerConstant 42 (Integer))))
      ))

  (testing "invalid"
    (let [aiv {::asr/term ::asr/expr,
               ::asr/asr-expr-head
               {::asr/expr-head ::asr/IntegerConstant
                ::asr/int       42
                ::asr/Integer   (Logical)}}]
      (is (not (s/valid? ::asr/asr-term        aiv)))
      (is (not (s/valid? ::asr/expr            aiv)))
      (is (not (s/valid? ::asr/ttype           aiv)))
      (is (not (s/valid? ::asr/IntegerConstant aiv)))
      )))


;;  ___ _       _            ___             _            _
;; / __| |_ _ _(_)_ _  __ _ / __|___ _ _  __| |_ __ _ _ _| |_
;; \__ \  _| '_| | ' \/ _` | (__/ _ \ ' \(_-<  _/ _` | ' \  _|
;; |___/\__|_| |_|_||_\__, |\___\___/_||_/__/\__\__,_|_||_\__|
;;                    |___/


(deftest StringConstant-test
  (let [x (StringConstant "boofar")]
    (testing "idempotency"
      (is (= x (eval x) (eval (eval x)))))
    (testing "telescoping specs"
      (is (s/valid? ::asr/StringConstant x))
      (is (s/valid? ::asr/expr           x))
      (is (s/valid? ::asr/asr-term       x))))

  (is (not (s/valid? ::asr/StringConstant (StringConstant 42)))))


;;  ___ _       _            ___         _
;; / __| |_ _ _(_)_ _  __ _ / _ \ _ _ __| |
;; \__ \  _| '_| | ' \/ _` | (_) | '_/ _` |
;; |___/\__|_| |_|_||_\__, |\___/|_| \__,_|
;;                    |___/


(deftest StringOrd-test
  (let [x (StringOrd
           (StringConstant "boofar"),
           (Integer),
           (IntegerConstant 51 (Integer)))]
    (testing "idempotency"
      (is (= x (eval x) (eval (eval x)))))
    (is (s/valid? ::asr/StringOrd x)))
  (is (s/valid? ::asr/StringOrd
                (StringOrd
                 (StringConstant "boofar"),
                 (IntegerConstant 51 (Integer)))))
  (is (not (s/valid? ::asr/StringOrd
                     (StringOrd
                      (StringConstant 42),
                      (Integer),
                      (IntegerConstant 51 (Integer))))))
  (is (not (s/valid? ::asr/StringOrd
                     (StringOrd
                      (StringConstant "boofar" 42),
                      (Integer),
                      (LogicalConstant true (Logical))))))
  (is (not (s/valid? ::asr/StringOrd
                     (StringOrd
                      (StringConstant "boofar"),
                      (Real),
                      (IntegerConstant 51 (Integer))))))  )


;; __   __
;; \ \ / /_ _ _ _
;;  \ V / _` | '_|
;;   \_/\__,_|_|


(deftest Var-test
  (testing "debugging explode"
    (is (s/valid? ::asr/Var
                  (Var 2 a)))
    (is (s/valid? ::asr/Assignment
                  (eval
                   (rewrite-for-legacy
                    '(= (Var 2 a)
                        (LogicalConstant false (Logical 4 []))
                        ())))))
    (is (s/valid? ::asr/TranslationUnit
                  (eval
                   (rewrite-for-legacy
                    '(TranslationUnit
                      (SymbolTable 42 {})
                      [(Program
                        (SymbolTable 3 {})
                        main_program
                        []
                        [(= (Var 2 a)
                            (LogicalConstant false (Logical 4 []))
                            ())])]))))))
  (let [vlv {::asr/term ::asr/expr,
             ::asr/asr-expr-head
             {::asr/expr-head  ::asr/Var
              ::asr/symtab-id  2
              ::asr/varnym     "x"
              }}]
    (idempotency-check vlv)
    (idempotency-check (Var-- 2 'x))
    (is (= (Var-- 2 'x) vlv))
    (is (= (Var   2  x) vlv))
    (is (= (Var-- 2 'x) (s/conform ::asr/Var   vlv)))
    (is (= (Var   2  x) (s/conform ::asr/Var   vlv)))
    (is (= (s/conform ::asr/Var (Var-- 2 'x))  vlv))
    (is (= (s/conform ::asr/Var (Var   2  x))  vlv))
    (is (= (s/valid?  ::asr/asr-term vlv)      true))
    (is (= (s/valid?  ::asr/Var      vlv)      true))))


;;  ___     _                    ___ _      ___
;; |_ _|_ _| |_ ___ __ _ ___ _ _| _ |_)_ _ / _ \ _ __
;;  | || ' \  _/ -_) _` / -_) '_| _ \ | ' \ (_) | '_ \
;; |___|_||_\__\___\__, \___|_| |___/_|_||_\___/| .__/
;;                 |___/                        |_|


(deftest IntegerBinOp-test

  (is (s/valid? ::asr/integer-left
                (IntegerConstant 2 (Integer 4 []))))
  (is (s/valid? ::asr/integer-binop  Add))
  (is (s/valid? ::asr/integer-right
                (IntegerConstant 3 (Integer 4 []))))
  (is (s/valid? ::asr/Integer
                (Integer 4 [])))
  (is (s/valid? ::asr/integer-value?
                (IntegerConstant 5 (Integer 4 []))))

  (is (s/valid? ::asr/IntegerBinOp
                (IntegerBinOp
                 (IntegerConstant 2 (Integer 4 []))
                 Add
                 (IntegerConstant 3 (Integer 4 []))
                 (Integer 4 [])
                 (IntegerConstant 5 (Integer 4 [])))))

  (is (not (s/valid? ::asr/IntegerBinOp
                     (IntegerBinOp
                      (IntegerConstant 2 (Integer 4 []))
                      Add
                      (IntegerConstant 3 (Integer 4 []))
                      (Logical 4 []) ;; <~~~ booger
                      (IntegerConstant 5 (Integer 4 []))))))

  (let [x (IntegerBinOp
           (IntegerBinOp
            (IntegerConstant 2 (Integer 4 []))
            Add
            (IntegerConstant 3 (Integer 4 []))
            (Integer 4 [])
            (IntegerConstant 5 (Integer 4 [])))
           Mul
           (IntegerConstant 5 (Integer 4 []))
           (Integer 4 [])
           (IntegerConstant 25 (Integer 4 [])))]
    (testing "idempotency"
      (is (= x (eval x) (eval (eval x)))))
    (is (s/valid? ::asr/IntegerBinOp x)))

  (is (not (s/valid? ::asr/IntegerBinOp
                     (IntegerBinOp
                      (IntegerBinOp
                       (IntegerConstant 2 (Integer 4 []))
                       Add
                       (IntegerConstant 3 (Integer 4 []))
                       (Complex 4 [])  ;; <~~~ booger
                       (IntegerConstant 5 (Integer 4 [])))
                      Mul
                      (IntegerConstant 5 (Integer 4 []))
                      (Integer 4 [])
                      (IntegerConstant 25 (Integer 4 []))))))
  )


;;  ___          _ ___ _      ___
;; | _ \___ __ _| | _ |_)_ _ / _ \ _ __
;; |   / -_) _` | | _ \ | ' \ (_) | '_ \
;; |_|_\___\__,_|_|___/_|_||_\___/| .__/
;;                                |_|


(deftest RealBinOp-test

  (is (s/valid? ::asr/real-left
                (RealConstant 2.0 (Real 4 []))))
  (is (s/valid? ::asr/real-binop   RAdd))
  (is (s/valid? ::asr/real-right
                (RealConstant 3.0 (Real 4 []))))
  (is (s/valid? ::asr/Real
                (Real 4 [])))
  (is (s/valid? ::asr/real-value?
                (RealConstant 5.0 (Real 4 []))))

  (is (s/valid? ::asr/RealBinOp
                (RealBinOp
                 (RealConstant 2.0 (Real 4 []))
                 Add ;; legacy sugar
                 (RealConstant 3.0 (Real 4 []))
                 (Real 4 [])
                 (RealConstant 5.0 (Real 4 []))
                 )))

  (is (s/valid? ::asr/RealBinOp
                (RealBinOp
                 (RealConstant 2.0 (Real 4 []))
                 Add ;; legacy sugar
                 (RealConstant 3.0 (Real 4 []))
                 (Real 4 [])
                 () ;; <~~~ no precompute
                 )))

  (is (not (s/valid? ::asr/RealBinOp
                     (RealBinOp
                      (RealConstant 2.0 (Real 4 []))
                      Add ;; legacy sugar
                      (RealConstant 3.0 (Real 4 []))
                      () ;; <~~~ missing ttype
                      () ;; <~~~ no precompute
                      ))))

  ;; TODO: check incorrect precompute.

  (is (not (s/valid? ::asr/RealBinOp
                     (RealBinOp
                      (RealConstant 2.0 (Real 4 []))
                      Add ;; legacy sugar
                      (RealConstant 3.0 (Real 4 []))
                      (Complex 4 []) ;; <~~~ booger
                      (RealConstant 5.0 (Real 4 []))
                      ))))

  (is (not (s/valid? ::asr/RealBinOp
                     (RealBinOp
                      (IntegerConstant 2 (Integer 4 [])) ;; <~~~ booger
                      Add                                ;; legacy sugar
                      (RealConstant 3.0 (Real 4 []))
                      (Real 4 []) ;; <~~~ booger
                      (RealConstant 5.0 (Real 4 []))
                      ))))

  (let [x (RealBinOp
           (RealBinOp
            (RealConstant 2.0 (Real 4 []))
            Add ;; legacy sugar
            (RealConstant 3.0 (Real 4 []))
            (Real 4 [])
            (RealConstant 5.0 (Real 4 [])))
           Mul ;; legacy sugar
           (RealConstant 5.0 (Real 4 []))
           (Real 4 [])
           (RealConstant 25.0 (Real 4 [])))]
    (testing "idempotency"
      (is (= x (eval x) (eval (eval x)))))
    (is (s/valid? ::asr/RealBinOp x)))
  )


;;  _              _         _ ___ _      ___
;; | |   ___  __ _(_)__ __ _| | _ |_)_ _ / _ \ _ __
;; | |__/ _ \/ _` | / _/ _` | | _ \ | ' \ (_) | '_ \
;; |____\___/\__, |_\__\__,_|_|___/_|_||_\___/| .__/
;;           |___/                            |_|


(deftest LogicalBinOp-test

  (let [x (LogicalBinOp
           (Var 2 a)
           And
           (LogicalCompare
            (Var 2 b)
            Eq
            (Var 2 b)
            (Logical 4 []) ())
           (Logical 4 []) ())]
    (testing "idempotency"
      (is (= x (eval x) (eval (eval x)))))
    (is (s/valid? ::asr/LogicalBinOp x)))

  (is (not (s/valid? ::asr/LogicalBinOp
                     (LogicalBinOp
                      (Var 2 a)
                      And
                      (LogicalCompare
                       (Var 2 b)
                       Eq
                       (Var 2 b)
                       (Integer 4 []) ())  ;; <~~~ wrong type
                      (Logical 4 []) ()))))

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
  (let [x (LogicalCompare
           (Var 2 b)
           Eq
           (Var 2 b)
           (Logical 4 []) ())]
    (testing "idempotency"
      (is (= x (eval x) (eval (eval x)))))
    (is (s/valid? ::asr/LogicalCompare x)))
  (is (not (s/valid? ::asr/LogicalCompare
                     (LogicalCompare
                      ;; wrong type
                      (IntegerConstant 2 (Integer 4 []))
                      Eq
                      (Var 2 b)
                      (Logical 4 []) ())
                     )))
  (is (not (s/valid? ::asr/LogicalCompare
                 (LogicalCompare
                  (Var 2 b)
                  Eq
                  (Var 2 b)
                  (Integer 4 []) ()) ;; <~~~ wrong type
                 )))
  )


;;  _
;; | |___ __ _ __ _ __ _  _
;; | / -_) _` / _` / _| || |
;; |_\___\__, \__,_\__|\_, |
;;       |___/         |__/


(deftest legacy-test
  (is (= '[(Var 42 x)]
         (rewrite-for-legacy
          '((Var 42 x)))))
  (is (= '[(Var 42 x)]
         (rewrite-for-legacy
          '[(Var 42 x)])))
  (let [x (legacy (Var 42 x))]
    (testing "idempotency"
      (is (= x (eval x) (eval (eval x))))))
  (is (s/valid? ::asr/expr? ()))
  (is (s/valid? ::asr/expr? []))
  (is (s/valid? ::asr/expr? (legacy (Var 42 x))))
  (is (s/valid? ::asr/expr  (legacy (Var 42 x))))
  (is (s/valid? ::asr/Var   (legacy (Var 42 x))))  )


;;  ___     _       _         _    ___             _   _
;; |_ _|_ _| |_ _ _(_)_ _  __(_)__| __|  _ _ _  __| |_(_)___ _ _
;;  | || ' \  _| '_| | ' \(_-< / _| _| || | ' \/ _|  _| / _ \ ' \
;; |___|_||_\__|_| |_|_||_/__/_\__|_| \_,_|_||_\__|\__|_\___/_||_|


(deftest intrinsic-function-test
  (is (s/valid? ::asr/intrinsic-ident "Abs"))
  (is (s/valid? ::asr/call-arg-or-args
                [(RealBinOp
                  (Var 2 a3)
                  Sub
                  (RealConstant 9.000000 (Real 8 []))
                  (Real 8 [])  ()  )]))
  (let [arg (legacy
             (RealBinOp
              (Var 2 a3)
              Sub
              (RealConstant 9.000000 (Real 8 []))
              (Real 8 [])  ()  ))]
    (is (s/valid? ::asr/call-arg [arg]))
    (is (s/valid? ::asr/expr?     arg)))
  (is (s/valid? ::asr/IntrinsicFunction
                ;; no "legacy"
                (IntrinsicFunction
                 Abs
                 [] ;; empty args
                 0
                 (Real 8 [])  ()  )
                ))
  (is (s/valid? ::asr/IntrinsicFunction
                (legacy
                 (IntrinsicFunction
                  Abs
                  [] ;; empty args
                  0
                  (Real 8 [])  ()  )
                 )))
  ;; TODO: signature check of each intrinsic
  (let [x (legacy
           (IntrinsicFunction
            Abs
            [(RealBinOp
              (Var 2 a3)
              Sub
              (RealConstant 9.000000 (Real 8 []))
              (Real 8 [])  ()  )]
            0
            (Real 8 [])  ()  )
           )]
    (testing "idempotency"
      (is (= x (eval x) (eval (eval x)))))
    (is (s/valid? ::asr/IntrinsicFunction x))))


;;   ___                _         ___ _      ___
;;  / __|___ _ __  _ __| |_____ _| _ |_)_ _ / _ \ _ __
;; | (__/ _ \ '  \| '_ \ / -_) \ / _ \ | ' \ (_) | '_ \
;;  \___\___/_|_|_| .__/_\___/_\_\___/_|_||_\___/| .__/
;;                |_|                            |_|


(deftest complex-binop-test
  (is (not (s/valid? ::asr/ComplexBinOp
                     (legacy
                      (ComplexBinOp
                       ;; wrong type
                       (IntegerConstant 5 (Integer 4 []))
                       Add
                       (Cast
                        (ComplexConstant 0.000000 6.000000
                                         (Complex 8 []) )
                        ComplexToComplex
                        (Complex 4 [])
                        (ComplexConstant 0.000000 6.000000
                                         (Complex 4 []) ) )
                       (Complex 4 [])
                       (ComplexConstant 5.000000 6.000000
                                        (Complex 4 []) ) )))))
  (let [x (legacy
           (ComplexBinOp
            (Cast
             (IntegerConstant 5 (Integer 4 []))
             IntegerToComplex
             (Complex 4 [])
             (ComplexConstant 5.000000 0.000000
                              (Complex 4 []) ) )
            Add
            (Cast
             (ComplexConstant 0.000000 6.000000
                              (Complex 8 []) )
             ComplexToComplex
             (Complex 4 [])
             (ComplexConstant 0.000000 6.000000
                              (Complex 4 []) ) )
            (Complex 4 [])
            (ComplexConstant 5.000000 6.000000
                             (Complex 4 []) ) ))]
    (testing "idempotency"
      (is (= x (eval x) (eval (eval x)))))
    (is (s/valid? ::asr/ComplexBinOp x))))


;; ================================================================
;;  ____ _____ __  __ _____
;; / ___|_   _|  \/  |_   _|
;; \___ \ | | | |\/| | | |
;;  ___) || | | |  | | | |
;; |____/ |_| |_|  |_| |_|


;;  ___  __
;; |_ _|/ _|
;;  | ||  _|
;; |___|_|


(deftest if-test
  (let [x (legacy
           (If ;; TODO: weird: integer value in the pocket?
            ;; Issue 40
            (NamedExpr
             (Var 2 a)
             (StringOrd
              (StringConstant "3" (Character 1 1 () []) )
              (Integer 4 [])
              (IntegerConstant 51 (Integer 4 [])) )
             (Integer 4 []) )
            [(=
              (Var 2 x)
              (IntegerConstant 1 (Integer 4 []))
              () )]
            []
            ))]
    (idempotency-check x)
    (is (s/valid? ::asr/If x)))
  (is (s/valid?
       ::asr/If
       (legacy
        (If
         (LogicalConstant true (Logical 4 []))
         [(=
           (Var 2 x)
           (IntegerConstant 1 (Integer 4 []))
           () )]
         []
         )))))


;;    _                     ___ _
;;   /_\  _ _ _ _ __ _ _  _|_ _| |_ ___ _ __
;;  / _ \| '_| '_/ _` | || || ||  _/ -_) '  \
;; /_/ \_\_| |_| \__,_|\_, |___|\__\___|_|_|_|
;;                     |__/


(deftest ArrayItem-test
  (let [x ()]
    (idempotency-check x)
    (is (s/valid? ::asr/array-index-start? x)))

  (let [x (Var 186 k)]
    (idempotency-check x)
    (is (s/valid? ::asr/array-index-end? x)))

  (is (s/valid? ::asr/array-index-increment? ()))

  (let [x (legacy
           (array-index (()
                         (Var 186 k)
                         ())))]
    (idempotency-check x)
    (is (s/valid? ::asr/array-index x)))

  (let [x (legacy
           (vec (map array-index [(()
                                   (Var 186 k)
                                   ())])))]
    (idempotency-check x)
    (is (s/valid? ::asr/array-index* x)))

  (let [x (legacy
           (ArrayItem
            (Var 186 b)
            [(()
              (Var 186 k)
              ())]
            (Real 8 [])
            RowMajor
            ()))]
    (idempotency-check x)
    (is (s/valid? ::asr/ArrayItem x))))


;;  ___      _
;; |   \ ___| |   ___  ___ _ __
;; | |) / _ \ |__/ _ \/ _ \ '_ \
;; |___/\___/____\___/\___/ .__/
;;                        |_|


(deftest do-loop-test
  (testing "bisection of DoLoop"
    (is (s/valid?
         ::asr/escape-target ()))
    (is (s/valid?
         ::asr/loop-v (Var 2 i)))
    (is (s/valid?
         ::asr/loop-start
         (IntegerConstant 0 (Integer 4 []))))
    (is (s/valid?
         ::asr/IntegerConstant
         (IntegerConstant 0 (Integer 4 []))))
    (is (not (s/valid?
              ::asr/Integer
              (IntegerConstant 0 (Integer 4 [])))))
    (is (s/valid?
         ::asr/expr
         (IntegerConstant 0 (Integer 4 []))))
    (is (s/valid?
         ::asr/loop-end
         (IntegerBinOp
          (IntegerConstant 50 (Integer 4 []))
          Sub
          (IntegerConstant 1 (Integer 4 []))
          (Integer 4 [])
          (IntegerConstant 49 (Integer 4 [])))))
    (is (s/valid?
         ::asr/loop-increment
         (IntegerConstant 1 (Integer 4 []))))
    (let [x (legacy
             (do-loop-head
              ((Var 2 i)
               (IntegerConstant 0 (Integer 4 []))
               (IntegerBinOp
                (IntegerConstant 50 (Integer 4 []))
                Sub
                (IntegerConstant 1 (Integer 4 []))
                (Integer 4 [])
                (IntegerConstant 49 (Integer 4 [])))
               (IntegerConstant 1 (Integer 4 [])))))]
      (is (s/valid? ::asr/do-loop-head x))
      (idempotency-check x))
    (let [x {::asr/loop-v         (Var 2 i)
             ::asr/loop-start     (IntegerConstant 0 (Integer 4 []))
             ::asr/loop-end       (IntegerConstant 1 (Integer 4 []))
             ::asr/loop-increment (IntegerConstant 1 (Integer 4 []))}]
      (is (s/valid? ::asr/do-loop-head x))
      (idempotency-check x))
    (is (s/valid?
         ::asr/ListAppend
         (ListAppend
          (Var 2 l3)
          (Var 2 i)
          )))
    (is (s/valid?
         ::asr/Var
         (Var 2 l3)))
    (is (s/valid?
         ::asr/list-expr
         (Var 2 l3)))
    (is (s/valid?
         ::asr/list-element
         (Var 2 i)))
    (is (s/valid?
         ::asr/Var
         (Var 2 i)))
    (is (s/valid?
         ::asr/expr
         (Var 2 i)))
    (is (s/valid?
         ::asr/body
         [(ListAppend
           (Var 2 l3)
           (Var 2 i)
           )])))
  (testing "DoLoop proper"
    (let [x {::asr/term ::asr/stmt,
             ::asr/asr-stmt-head
             {::asr/stmt-head ::asr/DoLoop
              ::asr/escape-target   ()
              ::asr/do-loop-head
              {::asr/loop-v         (Var 2 i)
               ::asr/loop-start     (IntegerConstant 0 (Integer 4 []))
               ::asr/loop-end       (IntegerConstant 1 (Integer 4 []))
               ::asr/loop-increment (IntegerConstant 1 (Integer 4 []))}
              ::asr/body [(ListAppend
                           (Var 2 l3)
                           (Var 2 i)
                           )]}}]
      (idempotency-check x)
      (is (s/valid? ::asr/DoLoop x)))
    (let [x (DoLoop
             ()
             [(Var 2 i)
              (IntegerConstant 0 (Integer 4 []))
              (IntegerConstant 0 (Integer 4 []))
              (IntegerConstant 1 (Integer 4 []))]
             [(ListAppend
               (Var 2 l3)
               (Var 2 i)
               )] )]
      (is (s/valid? ::asr/DoLoop x))
      (testing "idempotency macro itself"
        (is (= x (eval x) (eval (eval x))))
        (idempotency-check x)))
    (let [x (DoLoop
             ()
             [(Var 2 i)
              (IntegerConstant 0 (Integer 4 []))
              (IntegerBinOp
               (IntegerConstant 50 (Integer 4 []))
               Sub
               (IntegerConstant 1 (Integer 4 []))
               (Integer 4 [])
               (IntegerConstant 49 (Integer 4 [])))
              (IntegerConstant 1 (Integer 4 []))]
             [(ListAppend
               (Var 2 l3)
               (Var 2 i)
               )] )]
      (idempotency-check x)
      (is (s/valid? ::asr/DoLoop x)))
    (let [x (legacy
             (DoLoop
              ()
              ((Var 2 i)
               (IntegerConstant 0 (Integer 4 []))
               (IntegerBinOp
                (IntegerConstant 50 (Integer 4 []))
                Sub
                (IntegerConstant 1 (Integer 4 []))
                (Integer 4 [])
                (IntegerConstant 49 (Integer 4 [])))
               (IntegerConstant 1 (Integer 4 [])))
              [(ListAppend
                (Var 2 l3)
                (Var 2 i)
                )] ))]
      (is (s/valid? ::asr/DoLoop x))
      (idempotency-check x))))


;; __      ___    _ _     _
;; \ \    / / |_ (_) |___| |   ___  ___ _ __
;;  \ \/\/ /| ' \| | / -_) |__/ _ \/ _ \ '_ \
;;   \_/\_/ |_||_|_|_\___|____\___/\___/ .__/
;;                                     |_|


(deftest while-loop-test
  (let [x (legacy
           (WhileLoop
            ()
            ;; Issue 40: Integer value in the test-expr pocket:
            (NamedExpr
             (Var 2 a)
             (IntegerConstant 1 (Integer 4 []))
             (Integer 4 []))
            [(=
              (Var 2 y)
              (IntegerConstant 1 (Integer 4 []))
              ()
              )]
            ))]
    (is (s/valid? ::asr/WhileLoop x))
    (idempotency-check x)))


;;    _          _                         _
;;   /_\   _____(_)__ _ _ _  _ __  ___ _ _| |_
;;  / _ \ (_-<_-< / _` | ' \| '  \/ -_) ' \  _|
;; /_/ \_\/__/__/_\__, |_||_|_|_|_\___|_||_\__|
;;                |___/


(deftest Assignment-test
  (is (not (s/valid? ::asr/Assignment
                     (Assignment
                      ;; wrong type of target
                      (LogicalConstant false (Logical 4 []))
                      (LogicalConstant false (Logical 4 []))
                      ()))))
  (let [x (Assignment
           (Var 2 a)
           (LogicalConstant false (Logical 4 []))
           ())
        y (legacy
           (= (Var 2 a)
              (LogicalConstant false (Logical 4 []))
              ()))
        e (legacy
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
    (testing "legacy"
      (is (= x y))
      (idempotency-check y)
      (idempotency-check e)
      (is (s/valid? ::asr/Assignment x))
      (is (s/valid? ::asr/Assignment y))
      (is (s/valid? ::asr/Assignment e))
      (is (s/valid? ::asr/stmt       e))
      (is (s/valid? ::asr/asr-term   e)))
    (let [e (legacy
             (= (Var 2 a)
                (LogicalBinOp
                 (Var 2 b)
                 Or
                 (Var 2 b)
                 (Logical 4 []) ()) ()))]
      (idempotency-check e)
      (is (s/valid? ::asr/Assignment e)))))


;;            ____
;;  _______ _/ / / ___ ________ _
;; / __/ _ `/ / / / _ `/ __/ _ `/
;; \__/\_,_/_/_/__\_,_/_/  \_, /
;;            /___/       /___/


(deftest call-arg-test
  (is (s/valid? ::asr/expr?     ()))
  (is (s/valid? ::asr/Var       (legacy (Var 42 x))))

  (is (s/valid? ::asr/expr?     (legacy ())))
  (is (s/valid? ::asr/expr?     (legacy (Var 42 x))))
  (is (s/valid? ::asr/expr?     (legacy (Var 42 x))))
  ;; not allowed
  (is (not (s/valid? ::asr/call-arg  (legacy []))))
  ;; an empty ::asr/expr?
  (is (s/valid? ::asr/call-arg  (legacy [()])))
  ;; various ways of ::asr/expr? with one ::asr/expr,
  ;; a natural expression of ::asr/expr? without
  ;; s/or and its complications, and our
  ;; normal way of expressing ? pluralities,
  ;; via one extra level of nesting.
  (is (s/valid? ::asr/call-arg  (legacy ((Var 42 x)))))
  (is (s/valid? ::asr/call-arg  (legacy [(Var 42 x)])))
  ;;                                call-args with two call-arg instances
  ;;                                       call-arg      call-arg
  ;;                                     .----^-----. .----^-----.
  (is (s/valid? ::asr/call-args (legacy [[(Var 42 x)] [(Var 43 j)]] )))
  ;; Bracket styles don't matter under `legacy`:
  (is (s/valid? ::asr/call-args (legacy (((Var 42 x)) ((Var 43 j))) )))
  (idempotency-check (legacy (((Var 42 x)) ((Var 43 j))) ))
  ;; Key must be an integer: TODO: investigate
  #_
  (is (s/valid? ::asr/call-args (legacy ([(Var 42 x)] [(Var 43 j)]) )))
  (is (s/valid? ::asr/call-args (legacy []))) ;; empty call args
  (is (s/valid? ::asr/call-args (legacy [(())]))))


;;  ___      _                 _   _           ___      _ _
;; / __|_  _| |__ _ _ ___ _  _| |_(_)_ _  ___ / __|__ _| | |
;; \__ \ || | '_ \ '_/ _ \ || |  _| | ' \/ -_) (__/ _` | | |
;; |___/\_,_|_.__/_| \___/\_,_|\__|_|_||_\___|\___\__,_|_|_|


(deftest SubroutineCall-test

  (testing "heavy sugar"
    (is (s/valid? ::asr/symbol-ref
                  (symbol-ref "test_fn1" 7)))
    (is (s/valid? ::asr/symbol-ref
                  (apply symbol-ref ["test_fn1" 7])))
    (is (s/valid? ::asr/symbol-ref
                  (symbol-ref "test_fn1" 7)))
    (is (s/valid? ::asr/symbol-ref
                  (apply symbol-ref ["test_fn1" 7])))
    (let [x (SubroutineCall--
             (symbol-ref "test_fn1" 7)
             ()  []  ())]
      (is (s/valid? ::asr/SubroutineCall x))
      (idempotency-check x))
    (is (s/valid? ::asr/orig-symref ())))
  (testing "legacy sugar"

    (is (s/valid? ::asr/symbol-ref
                  (apply symbol-ref ["test_fn1" 7])))
    (is (not (s/valid? ::asr/SubroutineCall
                       (SubroutineCall
                        'garbage 'booger
                        ()    []    ()))))
    (is (s/valid? ::asr/SubroutineCall
                  (SubroutineCall
                   7 "test_fn1"
                   ()    []    ())))
    (is (s/valid? ::asr/SubroutineCall
                  (SubroutineCall
                   7 test_fn1
                   ()    []    ())))
    (is (s/valid? ::asr/SubroutineCall
                  (legacy
                   (SubroutineCall
                    7 test_fn1
                    ()    []    ()))))
    (let [x (legacy
             (SubroutineCall
              7 test_fn1
              ()
              ((Var 42 i))
              ()))]
      (is (s/valid? ::asr/SubroutineCall x))
      (idempotency-check x))
    (let [x (legacy
             (SubroutineCall
              7 test_fn1
              ()
              ((Var 42 i) (Var 43 j))
              ()))]
      (is (s/valid? ::asr/SubroutineCall x))
      (idempotency-check x))

    (is (s/valid? ::asr/call-arg  '(())))
    (is (s/valid? ::asr/call-arg  [()]))
    (is (s/valid? ::asr/call-arg  [(Var 42 i)]))
    (is (s/valid? ::asr/call-arg  (legacy ((Var 42 i)))))
    (is (s/valid? ::asr/call-arg  (legacy [(Var 42 i)])))
    (is (s/valid? ::asr/call-args [[(Var 42 i)]]))
    (is (s/valid? ::asr/call-args (legacy [[(Var 42 i)]])))
    (is (s/valid? ::asr/call-args (legacy [((Var 42 i))])))
    (idempotency-check (legacy [((Var 42 i))]))
    ;; Not allowed TODO: investigate
    #_
    (is (s/valid? ::asr/call-args (legacy ([(Var 42 i)]))))

    (let [x (SubroutineCall--
             (symbol-ref "test_fn1" 7)
             ()
             [[(Var 42 i)]]
             ())]
      (is (s/valid? ::asr/SubroutineCall x))
      (idempotency-check x))
    (let [x (legacy ;; replace call brackets with vector
             (SubroutineCall--
              (symbol-ref "test_fn1" 7)
              ()
              (((Var 42 i))) ;; call brackets
              ()))]
      (is (s/valid? ::asr/SubroutineCall x))
      (idempotency-check x))
    (is (s/valid? ::asr/SubroutineCall
                  (legacy ;; replace call brackets with vector
                   (SubroutineCall--
                    (symbol-ref "test_fn1" 7)
                    ()
                    (((Var 42 i)) ((Var 43 j))) ;; call brackets
                    ()))))
    ))


;;                   __        __            ___
;;   ___ __ ____ _  / /  ___  / /__________ / _/
;;  (_-</ // /  ' \/ _ \/ _ \/ /___/ __/ -_) _/
;; /___/\_, /_/_/_/_.__/\___/_/   /_/  \__/_/
;;     /___/


(deftest symbol-ref-test
  (is (s/valid? ::asr/symbol-ref {::asr/identifier "foobar"
                                  ::asr/symtab-id  42}))

  (is (s/valid? ::asr/symbol-ref {::asr/identifier "foobar"
                                  ::asr/symtab-id  42
                                  ::asr/extra-noise "obi-wan"}))

  (is (not (s/valid? ::asr/symbol-ref {::asr/identifier "foobar"})))

  (is (not (s/valid? ::asr/symbol-ref {::asr/symtab-id  42})))
  (let [vsr {::asr/identifier "foobar"
             ::asr/symtab-id  42}]
    (is (s/valid? ::asr/symbol-ref? vsr))
    (is (s/valid? ::asr/symbol-ref? []))
    (is (s/valid? ::asr/symbol-ref? ()))
    (is (not (s/valid? ::asr/symbol-ref? [vsr, vsr])))
    (is (s/valid? ::asr/orig-symref vsr))
    (is (s/valid? ::asr/orig-symref []))
    (is (s/valid? ::asr/orig-symref ()))
    (is (not (s/valid? ::asr/orig-symref [vsr, vsr])))
    (is (s/valid? ::asr/extern-symref vsr))
    (is (s/valid? ::asr/extern-symref []))
    (is (s/valid? ::asr/extern-symref ()))
    (is (not (s/valid? ::asr/extern-symref [vsr, vsr])))
    (idempotency-check vsr)))


;;  ___     _                     _ ___            _         _
;; | __|_ _| |_ ___ _ _ _ _  __ _| / __|_  _ _ __ | |__  ___| |
;; | _|\ \ /  _/ -_) '_| ' \/ _` | \__ \ || | '  \| '_ \/ _ \ |
;; |___/_\_\\__\___|_| |_||_\__,_|_|___/\_, |_|_|_|_.__/\___/_|
;;                                      |__/


(deftest ExternalSymbol-test

  (let [x {::asr/term       ::asr/symbol,
           ::asr/asr-symbol-head
           {::asr/symbol-head   ::asr/ExternalSymbol
            ::asr/symtab-id     5
            ::asr/nym           "_lpython_main_program"
            ::asr/extern-symref
            (symbol-ref "_lpython_main_program" 7)
            ::asr/modulenym     "_global_symbols"
            ::asr/scope-nyms    [],
            ::asr/orig-nym      "_lpython_main_program"
            ::asr/access        Public}}]
    (is (s/valid? ::asr/ExternalSymbol x))
    (idempotency-check x))

  (let [x (ExternalSymbol--
           5 "_lpython_main_program"
           (symbol-ref "_lpython_main_program" 7)
           "_global_symbols"
           []
           "_lpython_main_program"
           Public)]
    (is (s/valid? ::asr/ExternalSymbol x))
    (idempotency-check x))

  (let [x (ExternalSymbol
           5, _lpython_main_program
           7, _lpython_main_program,
           _global_symbols
           []
           _lpython_main_program
           Public)]
    (is (s/valid? ::asr/ExternalSymbol x))
    (idempotency-check x))

  (let [x (ExternalSymbol
           5, _lpython_main_program
           ()
           _global_symbols
           []
           _lpython_main_program
           Public)]
    (is (s/valid? ::asr/ExternalSymbol x))
    (idempotency-check x))

  (is (not (s/valid? ::asr/ExternalSymbol
                     (ExternalSymbol
                      () ()
                      ()
                      _global_symbols
                      []
                      _lpython_main_program
                      Public))))

  (let [x (ExternalSymbol
           3 pow__AT____lpython_overloaded_0__pow
           6 __lpython_overloaded_0__pow
           lpython_builtin
           []
           __lpython_overloaded_0__pow
           Public
           )]
    (is (s/valid? ::asr/ExternalSymbol x))
    (idempotency-check x))
  )


;; __   __        _      _    _
;; \ \ / /_ _ _ _(_)__ _| |__| |___
;;  \ V / _` | '_| / _` | '_ \ / -_)
;;   \_/\__,_|_| |_\__,_|_.__/_\___|


(deftest Variable-test
  (testing "full-form"
    (let [a-var-head
          {::asr/symbol-head      ::asr/Variable

           ::asr/symtab-id        2
           ::asr/varnym           "x"
           ::asr/ttype            (Integer 4 [])

           ::asr/type-declaration nil
           ::asr/dependencies     #{}
           ::asr/intent           Local

           ::asr/symbolic-value   () ;; TODO sugar
           ::asr/value?           ()
           ::asr/storage-type     Default

           ::asr/abi              Source
           ::asr/access           Public
           ::asr/presence         Required
           ::asr/value-attr       false}
          ;; nested
          a-var {::asr/term ::asr/symbol
                 ::asr/asr-symbol-head a-var-head}
          a-var-light (Variable- :varnym     "x"
                                 :symtab-id  2
                                 :ttype      (Integer 4))
          avl-2  (Variable- :varnym     "x"
                            :symtab-id  2
                            :ttype      (Integer 42))]

      (is (= (s/valid? ::asr/asr-symbol-head a-var-head) true))

      (is (= (s/valid? ::asr/asr-term a-var)             true))
      (idempotency-check a-var)
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
                     :varnym           "x"
                     :intent           Local ;; ASDL back-channel

                     :ttype            (Integer)
                     :access           Private
                     :presence         Required

                     :abi              Source
                     :type-declaration nil
                     :value-attr       false

                     :symbolic-value   []
                     :value?           []
                     :storage-type     Default

                     :dependencies     ["y" "z"]
                     )]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/Variable a-valid))
      (idempotency-check a-valid))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    "x",
                     :intent    (intent "Local") ;; explicit
                     :ttype     (Integer 4 [[1 42]]))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid))
      (idempotency-check a-valid))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    "x",
                     :intent    Local ;; ASDL back-channel
                     :ttype     (Integer 4 [[1 42]]))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid))
      (idempotency-check a-valid))
    (let [a-valid ;; default intent
          (Variable- :symtab-id 2,
                     :varnym    "x",
                     :ttype     (Integer 4 [[1 42]]))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid))
      (idempotency-check a-valid))
    (let [a-valid
          (Variable- :symtab-id (symtab-id 2), ;; HERE
                     :varnym    "x",
                     :ttype     (Integer 4 [[1 42]]))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id 2, ;; HERE
                     :varnym    "x",
                     :ttype     (Integer 4 [[1 42]]))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    "x",
                     :ttype     (Integer 4 [[1 42]])
                     ;; explicit abi
                     :abi       (abi "Source" :external false))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    "x",
                     :ttype     (Integer 4 [[1 42]])
                     ;; explicit defaulted abi
                     :abi       (abi "Source"))]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid))) ;; invalid examples
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    "x",
                     :ttype     (Integer 4 [[1 42]])
                     ;; explicit ASDL back-channel abi
                     :abi       Source)]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid
          (Variable- :symtab-id 2,
                     :varnym    "x",
                     :ttype     (Integer) ;; DEFAULTED
                     :abi       Source)]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid))
      (idempotency-check a-valid))
    (let [a-inval
          (Variable- :symtab-id 2,
                     :varnym    "x",
                     :ttype     (Integer 4 [[1 42]])
                     ;; wrong abi
                     :abi       (abi "Source" :external true))]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/symbol   a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval)))))
  (testing "back tests"
    (let [v (Variable-- 2 "a" (Logical 4)
                        nil [] "Local"
                        [] [] "Default"
                        "Source" "Public" "Required"
                        false)]
      (is (not (s/valid? ::asr/LogicalBinOp    v)))
      (is (not (s/valid? ::asr/LogicalConstant v)))
      (is (not (s/valid? ::asr/Logical         v)))
      (is (not (s/valid? ::asr/stmt            v)))
      (is (not (s/valid? ::asr/Assignment      v)))
      ))
  (testing "heavy sugar valid examples"
    (let [a-valid (Variable-- 2 "x" (Integer 4)
                              nil [] (intent "Local")
                              [] []  (storage-type "Default")
                              (abi "Source")
                              (access "Public")
                              (presence "Required")
                              false)]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/Variable a-valid)))
    (let [a-valid (Variable-- 2 "x" (Integer 4)
                              nil [] Local
                              [] []  Default
                              Source Public Required
                              false)]
      (is (s/valid? ::asr/asr-term a-valid))
      (is (s/valid? ::asr/symbol   a-valid))
      (is (s/valid? ::asr/Variable a-valid))))
  (testing "heavy sugar invalid examples"
    (let [a-inval (Variable-- "foo" "x" (Integer 4) ;; bad symtab-id
                              nil [] Local
                              [] []  Default
                              Source Public Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 "x" (Integer 42424242) ;; bad ttupe
                              nil [] Local
                              [] []  Default
                              Source Public Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 "x" (Integer 42424242)
                              "FOOBAR" [] Local ;; bad dependencies
                              [] []  Default
                              Source Public Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 "x" (Integer 4)
                              nil [] (intent "FOOBAR") ;; bad intent
                              [] []  Default
                              Source Public Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 "x" (Integer 4)
                              nil ["x" "y" 'foo] ;; bad dependencies
                              Local
                              [] []  Default
                              Source Public Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 "x" (Integer 4)
                              nil [] Local
                              [] []
                              ;; bad storage-type
                              (storage-type "FOOBAR")
                              Source Public Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 "x" (Integer 4)
                              nil [] Local
                              [] []  Default
                              (abi "FOOBAR")
                              Public Required ;; bad abi
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 "x" (Integer 4)
                              nil [] Local
                              [] []  Default
                              Source
                              (access "FOOBAR") ;; bad access
                              Required
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 "x" (Integer 4)
                              nil [] Local
                              [] []  Default
                              Source Public
                              (presence "FOOBAR") ;; bad presence
                              false)]
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval))))
    (let [a-inval (Variable-- 2 "x" (Integer 4)
                              nil [] Local
                              [] []  Default
                              Source Public Required
                              "FOOBAR")] ;; bad value-attr
      (is (not (s/valid? ::asr/asr-term a-inval)))
      (is (not (s/valid? ::asr/Variable a-inval)))))
  (testing "legacy macro"
    (let [v (Variable
             2 a []
             Local () ()
             Default (Logical 4 []) Source
             Public Required false)]
      (is (s/valid? ::asr/Variable v))
      (idempotency-check v)
      ;; using "legacicated" symbols:
      (is (= v (Variable-- 2 "a" (Logical 4)
                           nil [] Local
                           [] [] Default
                           Source Public Required
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
      (idempotency-check st)
      (is (not (s/valid? ::asr/symbol st)))
      (is (s/valid? ::asr/SymbolTable st))
      (is (s/valid? ::asr/asr-term    st))))
  )


;;  ___             _   _
;; | __|  _ _ _  __| |_(_)___ _ _
;; | _| || | ' \/ _|  _| / _ \ ' \
;; |_| \_,_|_||_\__|\__|_\___/_||_|


(deftest function-test
  (let [ ;; --------------------------------
        ft (FunctionType
            [] () Source
            Implementation () false
            false false false
            false [] [] false)
        ;; --------------------------------
        rft (read-string "(FunctionType
            [] () Source
            Implementation () false
            false false false
            false [] [] false)")
        reft (to-full-form rft)
        ;; --------------------------------
        rfn (read-string "(Function
               (SymbolTable 42 {})
               test_boolOp,
               (FunctionType
                [] () Source
                Implementation () false
                false false false
                false [] [] false)
               []
               [] [] ()
               Public false false)")
        refn (to-full-form rfn)
        ;; --------------------------------
        afn (to-full-form
             '(Function
               (SymbolTable 42 {})
               test_boolOp,
               #_~ft, ;; puts "test_boolOp in masr.core-test namespace
               (FunctionType
                [] () Source
                Implementation () false
                false false false
                false [] [] false)
               []       ;; deps
               [] [] () ;; param*, body, retvar
               ;; access deterministic side-effect-free
               Public false false))]
    ;; --------------------------------
    (is (s/valid? ::asr/FunctionType reft))
    (idempotency-check reft)
    (is (s/valid? ::asr/Function refn))
    (idempotency-check refn)
    (is (s/valid? ::asr/Function afn))
    (idempotency-check afn)
    (is (s/valid? ::asr/asr-term afn))
    (is (s/valid? ::asr/symbol   afn))
    (is (s/valid? ::asr/Function afn)))

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
    (idempotency-check afn)
    (is (s/valid? ::asr/asr-term afn))
    (is (s/valid? ::asr/symbol   afn))
    (is (s/valid? ::asr/Function afn)))
  )


;;  __  __         _      _
;; |  \/  |___  __| |_  _| |___
;; | |\/| / _ \/ _` | || | / -_)
;; |_|  |_\___/\__,_|\_,_|_\___|


(deftest bisecting-Module-test
  (let [example (legacy
                 (Function
                  (SymbolTable
                   113
                   {})
                  _lpython_main_program
                  (FunctionType
                   []
                   ()
                   Source
                   Implementation
                   ()
                   false
                   false
                   false
                   false
                   false
                   []
                   []
                   false)
                  [main0]
                  []
                  [(SubroutineCall
                    114 main0
                    ()
                    []
                    ()
                    )]
                  ()
                  Public
                  false
                  false))]
    (idempotency-check example)
    (is (s/valid? ::asr/Function example)))

  (let [example (legacy
                 (Function
                  (SymbolTable
                   4
                   {:c
                    (Variable
                     4
                     c
                     []
                     Local
                     ()
                     ()
                     Default
                     (Integer 4 [])
                     Source
                     Public
                     Required
                     false
                     )
                    })
                  main0
                  (FunctionType
                   []
                   ()
                   Source
                   Implementation
                   ()
                   false
                   false
                   false
                   false
                   false
                   []
                   []
                   false
                   )
                  [test_pow
                   test_pow_1]
                  []
                  [(SubroutineCall
                    114 test_pow
                    ()
                    []
                    ()
                    )
                   (=
                    (Var 4 c)
                    (FunctionCall
                     114 test_pow_1
                     ()
                     [((IntegerConstant 1 (Integer 4 [])))
                      ((IntegerConstant 2 (Integer 4 [])))]
                     (Integer 4 [])
                     ()
                     ()
                     )
                    ()
                    )]
                  ()
                  Public
                  false
                  false
                  ))]
    (idempotency-check example)
    (is (s/valid? ::asr/Function example)))

  (let [example (legacy
                 (ExternalSymbol
                  2
                  pow
                  6 pow
                  lpython_builtin
                  []
                  pow
                  Private
                  ))]
    (idempotency-check example)
    (is (s/valid? ::asr/ExternalSymbol example)))

  (let [example (legacy
                 (ExternalSymbol
                  2
                  pow__AT____lpython_overloaded_0__pow
                  6 __lpython_overloaded_0__pow
                  lpython_builtin
                  []
                  __lpython_overloaded_0__pow
                  Public
                  ))]
    (is (s/valid? ::asr/ExternalSymbol example)))

  (let [example (legacy
                 (FunctionType
                  []
                  ()
                  Source
                  Implementation
                  ()
                  false
                  false
                  false
                  false
                  false
                  []
                  []
                  false
                  ))]
    (idempotency-check example)
    (is (s/valid? ::asr/FunctionType example)))

  (let [example (legacy
                 (Variable
                  2 a []
                  Local () ()
                  Default
                  (Integer 4 [])
                  Source  Public  Required false))]
    (idempotency-check example)
    (is (s/valid? ::asr/Variable example)))

  (let [example (legacy [((IntegerConstant 2 (Integer 4 [])))
                         ((IntegerConstant 2 (Integer 4 [])))])]
    (idempotency-check example)
    (is (s/valid? ::asr/call-args example)))

  (let [example (legacy
                 (FunctionCall
                  2 pow__AT____lpython_overloaded_0__pow
                  2 pow
                  [((IntegerConstant 2 (Integer 4 [])))
                   ((IntegerConstant 2 (Integer 4 [])))]
                  (Real 8 [])
                  (RealConstant 4.000000 (Real 8 [])) ()))]
    (idempotency-check example)
    (is (s/valid? ::asr/FunctionCall example))
    (is (s/valid? ::asr/expr         example))
    (is (s/valid? ::asr/target       example)))

  (is (s/valid? ::asr/value  (IntegerConstant 4 (Integer 4 []))))
  (is (s/valid? ::asr/value? (IntegerConstant 4 (Integer 4 []))))
  (is (s/valid? ::asr/cast-kind RealToInteger))
  (is (s/valid? ::asr/ttype     (Integer 4 [])))

  (let [example (legacy
                 (Cast
                  (FunctionCall
                   2 pow__AT____lpython_overloaded_0__pow
                   2 pow
                   [((IntegerConstant 2 (Integer 4 [])))
                    ((IntegerConstant 2 (Integer 4 [])))]
                   (Real 8 [])
                   (RealConstant 4.000000 (Real 8 [])) ())
                  RealToInteger
                  (Integer 4 [])
                  (IntegerConstant 4 (Integer 4 []))
                  ))]
    (is       (s/valid?  ::asr/Cast example)))

  (let [example (legacy
                 [(=
                   (Var 2 a)
                   (Cast
                    (FunctionCall
                     2 pow__AT____lpython_overloaded_0__pow
                     2 pow
                     [((IntegerConstant 2 (Integer 4 [])))
                      ((IntegerConstant 2 (Integer 4 [])))]
                     (Real 8 [])
                     (RealConstant
                      4.000000
                      (Real 8 [])
                      )
                     ()
                     )
                    RealToInteger
                    (Integer 4 [])
                    (IntegerConstant 4 (Integer 4 []))
                    )
                   ()
                   )])]
    (idempotency-check example)
    (is (s/valid? ::asr/body example)))

  (let [example (legacy
                 (Function
                  (SymbolTable 2 { })
                  test_pow
                  (FunctionType
                   []
                   ()
                   Source
                   Implementation
                   ()
                   false
                   false
                   false
                   false
                   false
                   []
                   []
                   false
                   )
                  [pow__AT____lpython_overloaded_0__pow]
                  []
                  [(=
                    (Var 2 a)
                    (Cast
                     (FunctionCall
                      2 pow__AT____lpython_overloaded_0__pow
                      2 pow
                      [((IntegerConstant 2 (Integer 4 [])))
                       ((IntegerConstant 2 (Integer 4 [])))]
                      (Real 8 [])
                      (RealConstant
                       4.000000
                       (Real 8 [])
                       )
                      ()
                      )
                     RealToInteger
                     (Integer 4 [])
                     (IntegerConstant 4 (Integer 4 []))
                     )
                    ()
                    )]
                  ()
                  Public
                  false
                  false
                  ))]
    (idempotency-check example)
    (is (s/valid? ::asr/Function example)))

  (let [example (legacy
                 (SymbolTable
                  114
                  {:test_pow
                   (Function
                    (SymbolTable
                     2
                     {
                      :a
                      (Variable
                       2
                       a
                       []
                       Local
                       ()
                       ()
                       Default
                       (Integer 4 [])
                       Source
                       Public
                       Required
                       false
                       ),
                      :pow
                      (ExternalSymbol
                       2
                       pow
                       6 pow
                       lpython_builtin
                       []
                       pow
                       Private
                       ),
                      :pow__AT____lpython_overloaded_0__pow
                      (ExternalSymbol
                       2
                       pow__AT____lpython_overloaded_0__pow
                       6 __lpython_overloaded_0__pow
                       lpython_builtin
                       []
                       __lpython_overloaded_0__pow
                       Public
                       )
                      })
                    test_pow
                    (FunctionType
                     []
                     ()
                     Source
                     Implementation
                     ()
                     false
                     false
                     false
                     false
                     false
                     []
                     []
                     false
                     )
                    [pow__AT____lpython_overloaded_0__pow]
                    []
                    [(=
                      (Var 2 a)
                      (Cast
                       (FunctionCall
                        2 pow__AT____lpython_overloaded_0__pow
                        2 pow
                        [((IntegerConstant 2 (Integer 4 [])))
                         ((IntegerConstant 2 (Integer 4 [])))]
                        (Real 8 [])
                        (RealConstant
                         4.000000
                         (Real 8 [])
                         )
                        ()
                        )
                       RealToInteger
                       (Integer 4 [])
                       (IntegerConstant 4 (Integer 4 []))
                       )
                      ()
                      )]
                    ()
                    Public
                    false
                    false
                    ),
                   :test_pow_1
                   (Function
                    (SymbolTable
                     3
                     {
                      :_lpython_return_variable
                      (Variable
                       3
                       _lpython_return_variable
                       []
                       ReturnVar
                       ()
                       ()
                       Default
                       (Integer 4 [])
                       Source
                       Public
                       Required
                       false
                       ),
                      :a
                      (Variable
                       3
                       a
                       []
                       In
                       ()
                       ()
                       Default
                       (Integer 4 [])
                       Source
                       Public
                       Required
                       false
                       ),
                      :b
                      (Variable
                       3
                       b
                       []
                       In
                       ()
                       ()
                       Default
                       (Integer 4 [])
                       Source
                       Public
                       Required
                       false
                       ),
                      :pow
                      (ExternalSymbol
                       3
                       pow
                       6 pow
                       lpython_builtin
                       []
                       pow
                       Private
                       ),
                      :pow__AT____lpython_overloaded_0__pow
                      (ExternalSymbol
                       3
                       pow__AT____lpython_overloaded_0__pow
                       6 __lpython_overloaded_0__pow
                       lpython_builtin
                       []
                       __lpython_overloaded_0__pow
                       Public
                       ),
                      :res
                      (Variable
                       3
                       res
                       []
                       Local
                       ()
                       ()
                       Default
                       (Integer 4 [])
                       Source
                       Public
                       Required
                       false
                       )
                      })
                    test_pow_1
                    (FunctionType
                     [(Integer 4 [])
                      (Integer 4 [])]
                     (Integer 4 [])
                     Source
                     Implementation
                     ()
                     false
                     false
                     false
                     false
                     false
                     []
                     []
                     false
                     )
                    [pow__AT____lpython_overloaded_0__pow]
                    [(Var 3 a)
                     (Var 3 b)]
                    [(=
                      (Var 3 res)
                      (Cast
                       (FunctionCall
                        3 pow__AT____lpython_overloaded_0__pow
                        3 pow
                        [((Var 3 a))
                         ((Var 3 b))]
                        (Real 8 [])
                        ()
                        ()
                        )
                       RealToInteger
                       (Integer 4 [])
                       ()
                       )
                      ()
                      )
                     (=
                      (Var 3 _lpython_return_variable)
                      (Var 3 res)
                      ()
                      )
                     (Return)]
                    (Var 3 _lpython_return_variable)
                    Public
                    false
                    false
                    )
                   })
                 )]
    (idempotency-check example)
    (is (s/valid? ::asr/SymbolTable example))))


(deftest Module-test
  (let [example
        (legacy
         (Module
          (SymbolTable
           114
           {
            :_lpython_main_program
            (Function
             (SymbolTable
              113
              {

               })
             _lpython_main_program
             (FunctionType
              []
              ()
              Source
              Implementation
              ()
              false
              false
              false
              false
              false
              []
              []
              false
              )
             [main0]
             []
             [(SubroutineCall
               114 main0
               ()
               []
               ()
               )]
             ()
             Public
             false
             false
             ),
            :main0
            (Function
             (SymbolTable
              4
              {
               :c
               (Variable
                4
                c
                []
                Local
                ()
                ()
                Default
                (Integer 4 [])
                Source
                Public
                Required
                false
                )
               })
             main0
             (FunctionType
              []
              ()
              Source
              Implementation
              ()
              false
              false
              false
              false
              false
              []
              []
              false
              )
             [test_pow
              test_pow_1]
             []
             [(SubroutineCall
               114 test_pow
               ()
               []
               ()
               )
              (=
               (Var 4 c)
               (FunctionCall
                114 test_pow_1
                ()
                [((IntegerConstant 1 (Integer 4 [])))
                 ((IntegerConstant 2 (Integer 4 [])))]
                (Integer 4 [])
                ()
                ()
                )
               ()
               )]
             ()
             Public
             false
             false
             ),
            :test_pow
            (Function
             (SymbolTable
              2
              {
               :a
               (Variable
                2
                a
                []
                Local
                ()
                ()
                Default
                (Integer 4 [])
                Source
                Public
                Required
                false
                ),
               :pow
               (ExternalSymbol
                2
                pow
                6 pow
                lpython_builtin
                []
                pow
                Private
                ),
               :pow__AT____lpython_overloaded_0__pow
               (ExternalSymbol
                2
                pow__AT____lpython_overloaded_0__pow
                6 __lpython_overloaded_0__pow
                lpython_builtin
                []
                __lpython_overloaded_0__pow
                Public
                )
               })
             test_pow
             (FunctionType
              []
              ()
              Source
              Implementation
              ()
              false
              false
              false
              false
              false
              []
              []
              false
              )
             [pow__AT____lpython_overloaded_0__pow]
             []
             [(=
               (Var 2 a)
               (Cast
                (FunctionCall
                 2 pow__AT____lpython_overloaded_0__pow
                 2 pow
                 [((IntegerConstant 2 (Integer 4 [])))
                  ((IntegerConstant 2 (Integer 4 [])))]
                 (Real 8 [])
                 (RealConstant
                  4.000000
                  (Real 8 [])
                  )
                 ()
                 )
                RealToInteger
                (Integer 4 [])
                (IntegerConstant 4 (Integer 4 []))
                )
               ()
               )]
             ()
             Public
             false
             false
             ),
            :test_pow_1
            (Function
             (SymbolTable
              3
              {
               :_lpython_return_variable
               (Variable
                3
                _lpython_return_variable
                []
                ReturnVar
                ()
                ()
                Default
                (Integer 4 [])
                Source
                Public
                Required
                false
                ),
               :a
               (Variable
                3
                a
                []
                In
                ()
                ()
                Default
                (Integer 4 [])
                Source
                Public
                Required
                false
                ),
               :b
               (Variable
                3
                b
                []
                In
                ()
                ()
                Default
                (Integer 4 [])
                Source
                Public
                Required
                false
                ),
               :pow
               (ExternalSymbol
                3
                pow
                6 pow
                lpython_builtin
                []
                pow
                Private
                ),
               :pow__AT____lpython_overloaded_0__pow
               (ExternalSymbol
                3
                pow__AT____lpython_overloaded_0__pow
                6 __lpython_overloaded_0__pow
                lpython_builtin
                []
                __lpython_overloaded_0__pow
                Public
                ),
               :res
               (Variable
                3
                res
                []
                Local
                ()
                ()
                Default
                (Integer 4 [])
                Source
                Public
                Required
                false
                )
               })
             test_pow_1
             (FunctionType
              [(Integer 4 [])
               (Integer 4 [])]
              (Integer 4 [])
              Source
              Implementation
              ()
              false
              false
              false
              false
              false
              []
              []
              false
              )
             [pow__AT____lpython_overloaded_0__pow]
             [(Var 3 a)
              (Var 3 b)]
             [(=
               (Var 3 res)
               (Cast
                (FunctionCall
                 3 pow__AT____lpython_overloaded_0__pow
                 3 pow
                 [((Var 3 a))
                  ((Var 3 b))]
                 (Real 8 [])
                 ()
                 ()
                 )
                RealToInteger
                (Integer 4 [])
                ()
                )
               ()
               )
              (=
               (Var 3 _lpython_return_variable)
               (Var 3 res)
               ()
               )
              (Return)]
             (Var 3 _lpython_return_variable)
             Public
             false
             false
             )
            })
          _global_symbols
          [lpython_builtin]
          false
          false))]
    (idempotency-check example)
    (is (s/valid? ::asr/Module example))
    (is (s/valid? ::asr/symbol example))
    (is (s/valid? ::asr/asr-term example))))


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
    (idempotency-check p)
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
    (idempotency-check p)
    (is (s/valid? ::asr/asr-term p))
    (is (s/valid? ::asr/symbol   p))
    (is (s/valid? ::asr/Program  p))))


;; ================================================================
;;  _   _ _   _ ___ _____
;; | | | | \ | |_ _|_   _|
;; | | | |  \| || |  | |
;; | |_| | |\  || |  | |
;;  \___/|_| \_|___| |_|


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
                 (vec (map
                       node
                       [(Program
                         (SymbolTable 3 {})
                         main_program
                         []
                         [])]))}}
        large {::asr/term ::asr/unit
               ::asr/asr-unit-head
               {::asr/unit-head ::asr/TranslationUnit
                ::asr/SymbolTable (SymbolTable 42 {})
                ::asr/nodes
                (vec (map
                      node
                      [(legacy
                        (Program
                         (SymbolTable 3 {})
                         main_program
                         []
                         [(= (Var 2 a)
                             (LogicalConstant false (Logical 4 []))
                             ())]))]))}}]
    (idempotency-check small)
    (idempotency-check medium)
    (idempotency-check large)
    (is (s/valid? ::asr/unit            small))
    (is (s/valid? ::asr/unit            medium))
    (is (s/valid? ::asr/unit            large))
    (is (s/valid? ::asr/asr-term        small))
    (is (s/valid? ::asr/asr-term        medium))
    (is (s/valid? ::asr/asr-term        large))
    (is (s/valid? ::asr/TranslationUnit small))
    (is (s/valid? ::asr/TranslationUnit medium))
    (is (s/valid? ::asr/TranslationUnit large)))

  (testing "smallest translation unit"
    (let [x (TranslationUnit
             (SymbolTable 42 {})
             [])]
      (idempotency-check x)
      (is (s/valid? ::asr/unit x))))

  (testing "translation unit with empty program"
    (let [x (TranslationUnit
             (SymbolTable 42 {})
             [(Program
               (SymbolTable 3 {})
               main_program
               []
               [])])]
      (idempotency-check x)
      (is (s/valid? ::asr/unit x))))

  (testing "translation unit with small program"
    (let [x (legacy
             (TranslationUnit
              (SymbolTable 42 {})
              [(Program
                (SymbolTable 3 {})
                main_program
                []
                [(= (Var 2 a)
                    (LogicalConstant false (Logical 4 []))
                    ())])]))]
      (idempotency-check x)
      (is (s/valid? ::asr/unit x))))

  (testing "5311701 with Module-- heavy sugar"
    (let [x (legacy
             (TranslationUnit
              (SymbolTable
               1 {:_global_symbols
                  (Module--
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
                            (LogicalConstant false (Logical 4 [])) ())
                         (= (Var 2 b)
                            (LogicalConstant true (Logical 4 []))  ())
                         (= (Var 2 a)
                            (LogicalBinOp (Var 2 a) And (Var 2 b)
                                          (Logical 4 []) ()) ())
                         (= (Var 2 b)
                            (LogicalBinOp
                             (Var 2 a)  Or
                             (LogicalConstant true (Logical 4 []))
                             (Logical 4 []) ()) ())
                         (= (Var 2 a)
                            (LogicalBinOp (Var 2 a) Or (Var 2 b)
                                          (Logical 4 []) ()) ())
                         (= (Var 2 a)
                            (LogicalBinOp
                             (Var 2 a)
                             And
                             (LogicalCompare (Var 2 b) Eq (Var 2 b)
                                             (Logical 4 []) ())
                             (Logical 4 []) ()) ())
                         (= (Var 2 a)
                            (LogicalBinOp
                             (Var 2 a) And
                             (LogicalCompare (Var 2 b) NotEq (Var 2 b)
                                             (Logical 4 []) ())
                             (Logical 4 []) ()) ())
                         (= (Var 2 a)
                            (LogicalBinOp (Var 2 b) Or (Var 2 b)
                                          (Logical 4 []) ()) ())]
                        () Public false false)})
                   ;; NOTE: quotes because of Module-- at top
                   "_global_symbols"
                   [] false false),
                  :main_program
                  (Program
                   (SymbolTable 3 {}) main_program [] [])}) []))]
      (idempotency-check x)
      (is (s/valid? ::asr/unit x))))

  (testing "5311701 with Module legacy sugar"
    (let [x (legacy
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
                            (LogicalConstant false (Logical 4 [])) ())
                         (= (Var 2 b)
                            (LogicalConstant true (Logical 4 []))  ())
                         (= (Var 2 a)
                            (LogicalBinOp (Var 2 a) And (Var 2 b)
                                          (Logical 4 []) ()) ())
                         (= (Var 2 b)
                            (LogicalBinOp
                             (Var 2 a)  Or
                             (LogicalConstant true (Logical 4 []))
                             (Logical 4 []) ()) ())
                         (= (Var 2 a)
                            (LogicalBinOp (Var 2 a) Or (Var 2 b)
                                          (Logical 4 []) ()) ())
                         (= (Var 2 a)
                            (LogicalBinOp
                             (Var 2 a)
                             And
                             (LogicalCompare (Var 2 b) Eq (Var 2 b)
                                             (Logical 4 []) ())
                             (Logical 4 []) ()) ())
                         (= (Var 2 a)
                            (LogicalBinOp
                             (Var 2 a) And
                             (LogicalCompare (Var 2 b) NotEq (Var 2 b)
                                             (Logical 4 []) ())
                             (Logical 4 []) ()) ())
                         (= (Var 2 a)
                            (LogicalBinOp (Var 2 b) Or (Var 2 b)
                                          (Logical 4 []) ()) ())]
                        () Public false false)})
                   _global_symbols
                   [] false false),
                  :main_program
                  (Program (SymbolTable 3 {})
                           main_program [] [])}) []))]
      (idempotency-check x)
      (is (s/valid? ::asr/unit x))))

  (testing "Program fragment with legacy sugar"
    (let [x (Program
             (SymbolTable
              5
              {
               :_lpython_main_program
               (ExternalSymbol
                5
                _lpython_main_program
                7 _lpython_main_program
                _global_symbols
                []
                _lpython_main_program
                Public
                )
               })
             main_program
             [_global_symbols]
             [(SubroutineCall
               5 _lpython_main_program
               ()
               []
               ()
               )]
             )]
      (idempotency-check x)
      (is (s/valid? ::asr/Program x))))

  (testing "smallest subroutine call"
    (is (s/valid? ::asr/SubroutineCall
                  (legacy
                   (SubroutineCall
                    5 _lpython_main_program
                    ()
                    []
                    ()
                    )))))

  (testing "function"
    (let [x (legacy
             (Function
              (SymbolTable
               6
               {

                })
              _lpython_main_program
              (FunctionType
               []
               ()
               Source
               Implementation
               ()
               false
               false
               false
               false
               false
               []
               []
               false
               )
              [test_fn1]
              []
              [(SubroutineCall
                7 test_fn1
                ()
                []
                ()
                )]
              ()
              Public
              false
              false
              ))]
      (idempotency-check x)
      (is (s/valid? ::asr/Function x))))

  (testing "symbol table"
    (let [x (legacy
             (SymbolTable
              2
              {
               :_lpython_return_variable
               (Variable
                2
                _lpython_return_variable
                []
                ReturnVar
                ()
                ()
                Default
                (Integer 4 [])
                Source
                Public
                Required
                false
                )
               }))]
      (idempotency-check x)
      (is (s/valid? ::asr/SymbolTable x))))

  (testing "function type"
    (let [x (legacy
             (FunctionType
              []
              (Integer 4 [])
              Source
              Implementation
              ()
              false
              false
              false
              false
              false
              []
              []
              false
              ))]
      (idempotency-check x)
      (is (s/valid? ::asr/FunctionType x))))

  (testing "assignment"
    (let [x (legacy
             (=
              (Var 2 _lpython_return_variable)
              (IntegerConstant 5 (Integer 4 []))
              ()
              ))]
      (idempotency-check x)
      (is (s/valid? ::asr/Assignment x))))

  (testing "return"
    (let [x (legacy
             (Return))]
      (idempotency-check x)
      (is (s/valid? ::asr/Return x))))

  (testing "function with function type"
    (let [x (legacy
             (Function
              (SymbolTable
               2
               {
                :_lpython_return_variable
                (Variable
                 2
                 _lpython_return_variable
                 []
                 ReturnVar
                 ()
                 ()
                 Default
                 (Integer 4 [])
                 Source
                 Public
                 Required
                 false
                 )
                })
              g
              (FunctionType
               []
               (Integer 4 [])
               Source
               Implementation
               ()
               false
               false
               false
               false
               false
               []
               []
               false
               )
              []
              []
              [(=
                (Var 2 _lpython_return_variable)
                (IntegerConstant 5 (Integer 4 []))
                ()
                )
               (Return)]
              (Var 2 _lpython_return_variable)
              Public
              false
              false
              ))]
      (idempotency-check x)
      (is (s/valid? ::asr/Function x))))

  (testing "another function"
    (let [x (legacy
             (Function
              (SymbolTable
               6
               {

                })
              _lpython_main_program
              (FunctionType
               []
               ()
               Source
               Implementation
               ()
               false
               false
               false
               false
               false
               []
               []
               false
               )
              [test_fn1]
              []
              [(SubroutineCall
                7 test_fn1
                ()
                []
                ()
                )]
              ()
              Public
              false
              false
              )
             )]
      (idempotency-check x)
      (is (s/valid? ::asr/Function x))))

  (testing "bigger function"
    (let [x (legacy
             (Function
              (SymbolTable
               3
               {
                :x
                (Variable
                 3
                 x
                 []
                 In
                 ()
                 ()
                 Default
                 (Integer 4 [])
                 Source
                 Public
                 Required
                 false
                 )
                })
              gsubrout
              (FunctionType
               [(Integer 4 [])]
               ()
               Source
               Implementation
               ()
               false
               false
               false
               false
               false
               []
               []
               false
               )
              []
              [(Var 3 x)]
              [(Print
                ()
                [(Var 3 x)]
                ()
                ()
                )]
              ()
              Public
              false
              false
              ))]
      (idempotency-check x)
      (is (s/valid? ::asr/Function x))))

  (testing "even bigger function"
    (let [x (legacy
             (Function
              (SymbolTable
               4
               {
                :__lcompilers_dummy
                (Variable
                 4
                 __lcompilers_dummy
                 []
                 Local
                 ()
                 ()
                 Default
                 (Integer 4 [])
                 Source
                 Public
                 Required
                 false
                 ),
                :i
                (Variable
                 4
                 i
                 []
                 Local
                 ()
                 ()
                 Default
                 (Integer 4 [])
                 Source
                 Public
                 Required
                 false
                 ),
                :j
                (Variable
                 4
                 j
                 []
                 Local
                 ()
                 ()
                 Default
                 (Integer 4 [])
                 Source
                 Public
                 Required
                 false
                 )
                })
              test_fn1
              (FunctionType
               []
               ()
               Source
               Implementation
               ()
               false
               false
               false
               false
               false
               []
               []
               false
               )
              [g
               gsubrout]
              []
              [(= (Var 4 i)
                  (FunctionCall
                   7 g
                   ()
                   []
                   (Integer 4 [])
                   ()
                   ()) ())
               (= (Var 4 j)
                  (FunctionCall
                   7 g
                   ()
                   []
                   (Integer 4 [])
                   ()
                   ()) ())
               (= (Var 4 __lcompilers_dummy)
                  (FunctionCall
                   7 g
                   ()
                   []
                   (Integer 4 [])
                   ()
                   ()) ())
               (SubroutineCall
                7 gsubrout
                ()
                ((Var 4 i))
                ())]
              ()
              Public
              false
              false
              ))]
      (idempotency-check x)
      (is (s/valid? ::asr/Function x))))

  (testing "bigger symbol table"
    (let [x (legacy
             (SymbolTable
              7
              {
               :_lpython_main_program
               (Function
                (SymbolTable
                 6
                 {

                  })
                _lpython_main_program
                (FunctionType
                 []
                 ()
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                [test_fn1]
                []
                [(SubroutineCall
                  7 test_fn1
                  ()
                  []
                  ()
                  )]
                ()
                Public
                false
                false
                ),
               :g
               (Function
                (SymbolTable
                 2
                 {
                  :_lpython_return_variable
                  (Variable
                   2
                   _lpython_return_variable
                   []
                   ReturnVar
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   )
                  })
                g
                (FunctionType
                 []
                 (Integer 4 [])
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                []
                []
                [(=
                  (Var 2 _lpython_return_variable)
                  (IntegerConstant 5 (Integer 4 []))
                  ()
                  )
                 (Return)]
                (Var 2 _lpython_return_variable)
                Public
                false
                false
                ),
               :gsubrout
               (Function
                (SymbolTable
                 3
                 {
                  :x
                  (Variable
                   3
                   x
                   []
                   In
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   )
                  })
                gsubrout
                (FunctionType
                 [(Integer 4 [])]
                 ()
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                []
                [(Var 3 x)]
                [(Print
                  ()
                  [(Var 3 x)]
                  ()
                  ()
                  )]
                ()
                Public
                false
                false
                ),
               :test_fn1
               (Function
                (SymbolTable
                 4
                 {
                  :__lcompilers_dummy
                  (Variable
                   4
                   __lcompilers_dummy
                   []
                   Local
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   ),
                  :i
                  (Variable
                   4
                   i
                   []
                   Local
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   ),
                  :j
                  (Variable
                   4
                   j
                   []
                   Local
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   )
                  })
                test_fn1
                (FunctionType
                 []
                 ()
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                [g
                 gsubrout]
                []
                [(= (Var 4 i)
                    (FunctionCall
                     7 g
                     ()
                     []
                     (Integer 4 [])
                     ()
                     ()) ())
                 (= (Var 4 j)
                    (FunctionCall
                     7 g
                     ()
                     []
                     (Integer 4 [])
                     ()
                     ()) ())
                 (= (Var 4 __lcompilers_dummy)
                    (FunctionCall
                     7 g
                     ()
                     []
                     (Integer 4 [])
                     ()
                     ()) ())
                 (SubroutineCall
                  7 gsubrout
                  ()
                  [((Var 4 i))]
                  ()
                  )]
                ()
                Public
                false
                false
                )
               }))]
      (idempotency-check x)
      (is (s/valid? ::asr/SymbolTable x))))

  (testing "module"
    (let [x (legacy
             (Module
              (SymbolTable
               7
               {
                :_lpython_main_program
                (Function
                 (SymbolTable
                  6
                  {

                   })
                 _lpython_main_program
                 (FunctionType
                  []
                  ()
                  Source
                  Implementation
                  ()
                  false
                  false
                  false
                  false
                  false
                  []
                  []
                  false
                  )
                 [test_fn1]
                 []
                 [(SubroutineCall
                   7 test_fn1
                   ()
                   []
                   ()
                   )]
                 ()
                 Public
                 false
                 false
                 ),
                :g
                (Function
                 (SymbolTable
                  2
                  {
                   :_lpython_return_variable
                   (Variable
                    2
                    _lpython_return_variable
                    []
                    ReturnVar
                    ()
                    ()
                    Default
                    (Integer 4 [])
                    Source
                    Public
                    Required
                    false
                    )
                   })
                 g
                 (FunctionType
                  []
                  (Integer 4 [])
                  Source
                  Implementation
                  ()
                  false
                  false
                  false
                  false
                  false
                  []
                  []
                  false
                  )
                 []
                 []
                 [(=
                   (Var 2 _lpython_return_variable)
                   (IntegerConstant 5 (Integer 4 []))
                   ()
                   )
                  (Return)]
                 (Var 2 _lpython_return_variable)
                 Public
                 false
                 false
                 ),
                :gsubrout
                (Function
                 (SymbolTable
                  3
                  {
                   :x
                   (Variable
                    3
                    x
                    []
                    In
                    ()
                    ()
                    Default
                    (Integer 4 [])
                    Source
                    Public
                    Required
                    false
                    )
                   })
                 gsubrout
                 (FunctionType
                  [(Integer 4 [])]
                  ()
                  Source
                  Implementation
                  ()
                  false
                  false
                  false
                  false
                  false
                  []
                  []
                  false
                  )
                 []
                 [(Var 3 x)]
                 [(Print
                   ()
                   [(Var 3 x)]
                   ()
                   ()
                   )]
                 ()
                 Public
                 false
                 false
                 ),
                :test_fn1
                (Function
                 (SymbolTable
                  4
                  {
                   :__lcompilers_dummy
                   (Variable
                    4
                    __lcompilers_dummy
                    []
                    Local
                    ()
                    ()
                    Default
                    (Integer 4 [])
                    Source
                    Public
                    Required
                    false
                    ),
                   :i
                   (Variable
                    4
                    i
                    []
                    Local
                    ()
                    ()
                    Default
                    (Integer 4 [])
                    Source
                    Public
                    Required
                    false
                    ),
                   :j
                   (Variable
                    4
                    j
                    []
                    Local
                    ()
                    ()
                    Default
                    (Integer 4 [])
                    Source
                    Public
                    Required
                    false
                    )
                   })
                 test_fn1
                 (FunctionType
                  []
                  ()
                  Source
                  Implementation
                  ()
                  false
                  false
                  false
                  false
                  false
                  []
                  []
                  false
                  )
                 [g
                  gsubrout]
                 []
                 [(= (Var 4 i)
                     (FunctionCall
                      7 g
                      ()
                      []
                      (Integer 4 [])
                      ()
                      ()) ())
                  (= (Var 4 j)
                     (FunctionCall
                      7 g
                      ()
                      []
                      (Integer 4 [])
                      ()
                      ()) ())
                  (= (Var 4 __lcompilers_dummy)
                     (FunctionCall
                      7 g
                      ()
                      []
                      (Integer 4 [])
                      ()
                      ()) ())
                  (SubroutineCall
                   7 gsubrout
                   ()
                   [((Var 4 i))]
                   ()
                   )]
                 ()
                 Public
                 false
                 false
                 )
                })
              _global_symbols
              []
              false
              false
              ))]
      (idempotency-check x)
      (is (s/valid? ::asr/Module x))))

  (testing "even bigger symbol table"
    (let [x (legacy
             (SymbolTable
              1
              {
               :_global_symbols
               (Module
                (SymbolTable
                 7
                 {
                  :_lpython_main_program
                  (Function
                   (SymbolTable
                    6
                    {

                     })
                   _lpython_main_program
                   (FunctionType
                    []
                    ()
                    Source
                    Implementation
                    ()
                    false
                    false
                    false
                    false
                    false
                    []
                    []
                    false
                    )
                   [test_fn1]
                   []
                   [(SubroutineCall
                     7 test_fn1
                     ()
                     []
                     ()
                     )]
                   ()
                   Public
                   false
                   false
                   ),
                  :g
                  (Function
                   (SymbolTable
                    2
                    {
                     :_lpython_return_variable
                     (Variable
                      2
                      _lpython_return_variable
                      []
                      ReturnVar
                      ()
                      ()
                      Default
                      (Integer 4 [])
                      Source
                      Public
                      Required
                      false
                      )
                     })
                   g
                   (FunctionType
                    []
                    (Integer 4 [])
                    Source
                    Implementation
                    ()
                    false
                    false
                    false
                    false
                    false
                    []
                    []
                    false
                    )
                   []
                   []
                   [(=
                     (Var 2 _lpython_return_variable)
                     (IntegerConstant 5 (Integer 4 []))
                     ()
                     )
                    (Return)]
                   (Var 2 _lpython_return_variable)
                   Public
                   false
                   false
                   ),
                  :gsubrout
                  (Function
                   (SymbolTable
                    3
                    {
                     :x
                     (Variable
                      3
                      x
                      []
                      In
                      ()
                      ()
                      Default
                      (Integer 4 [])
                      Source
                      Public
                      Required
                      false
                      )
                     })
                   gsubrout
                   (FunctionType
                    [(Integer 4 [])]
                    ()
                    Source
                    Implementation
                    ()
                    false
                    false
                    false
                    false
                    false
                    []
                    []
                    false
                    )
                   []
                   [(Var 3 x)]
                   [(Print
                     ()
                     [(Var 3 x)]
                     ()
                     ()
                     )]
                   ()
                   Public
                   false
                   false
                   ),
                  :test_fn1
                  (Function
                   (SymbolTable
                    4
                    {
                     :__lcompilers_dummy
                     (Variable
                      4
                      __lcompilers_dummy
                      []
                      Local
                      ()
                      ()
                      Default
                      (Integer 4 [])
                      Source
                      Public
                      Required
                      false
                      ),
                     :i
                     (Variable
                      4
                      i
                      []
                      Local
                      ()
                      ()
                      Default
                      (Integer 4 [])
                      Source
                      Public
                      Required
                      false
                      ),
                     :j
                     (Variable
                      4
                      j
                      []
                      Local
                      ()
                      ()
                      Default
                      (Integer 4 [])
                      Source
                      Public
                      Required
                      false
                      )
                     })
                   test_fn1
                   (FunctionType
                    []
                    ()
                    Source
                    Implementation
                    ()
                    false
                    false
                    false
                    false
                    false
                    []
                    []
                    false
                    )
                   [g
                    gsubrout]
                   []
                   [(= (Var 4 i)
                       (FunctionCall
                        7 g
                        ()
                        []
                        (Integer 4 [])
                        ()
                        ()) ())
                    (= (Var 4 j)
                       (FunctionCall
                        7 g
                        ()
                        []
                        (Integer 4 [])
                        ()
                        ()) ())
                    (= (Var 4 __lcompilers_dummy)
                       (FunctionCall
                        7 g
                        ()
                        []
                        (Integer 4 [])
                        ()
                        ()) ())
                    (SubroutineCall
                     7 gsubrout
                     ()
                     [((Var 4 i))]
                     ()
                     )]
                   ()
                   Public
                   false
                   false
                   )
                  })
                _global_symbols
                []
                false
                false
                ),
               :main_program
               (Program
                (SymbolTable
                 5
                 {
                  :_lpython_main_program
                  (ExternalSymbol
                   5
                   _lpython_main_program
                   7 _lpython_main_program
                   _global_symbols
                   []
                   _lpython_main_program
                   Public
                   )
                  })
                main_program
                [_global_symbols]
                [(SubroutineCall
                  5 _lpython_main_program
                  ()
                  []
                  ()
                  )]
                )
               }))]
      (idempotency-check x)
      (is (s/valid? ::asr/SymbolTable x)))))


;; ================================================================
;;  ____  _    _   _ ____  ____  _____ ____
;; / ___|| |  | | | |  _ \|  _ \| ____|  _ \
;; \___ \| |  | | | | |_) | |_) |  _| | | | |
;;  ___) | |__| |_| |  _ <|  __/| |___| |_| |
;; |____/|_____\___/|_| \_\_|   |_____|____/


(defn slurp-asr
  [refnym]
  (read-string
   (slurp
    ;;   sometimes _, sometimes -, after asr
    (str "resources/reference/asr"
         refnym
         ".stdout.clj"))))


(defn long-form-asr
  [refnym]
  (do (in-ns 'masr.specs)
      (->> refnym
           slurp-asr
           rewrite-for-legacy
           eval)))


(defmacro test-translation-unit [filenamefrag]
  (let [tstr (str "whole translation unit for " filenamefrag)
        fstr (str filenamefrag)]
    `(testing ~tstr
       (idempotency-check ~tstr)
       (is (s/valid? ::asr/unit (long-form-asr ~fstr))))))


(deftest slurp-test
  (let [hand-written-quoted-e2e0267
        '(TranslationUnit
          (SymbolTable
           1
           {
            :_global_symbols
            (Module
             (SymbolTable
              7
              {
               :_lpython_main_program
               (Function
                (SymbolTable
                 6
                 {

                  })
                _lpython_main_program
                (FunctionType
                 []
                 ()
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                [test_fn1]
                []
                [(SubroutineCall
                  7 test_fn1
                  ()
                  []
                  ()
                  )]
                ()
                Public
                false
                false
                ),
               :g
               (Function
                (SymbolTable
                 2
                 {
                  :_lpython_return_variable
                  (Variable
                   2
                   _lpython_return_variable
                   []
                   ReturnVar
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   )
                  })
                g
                (FunctionType
                 []
                 (Integer 4 [])
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                []
                []
                [(=
                  (Var 2 _lpython_return_variable)
                  (IntegerConstant 5 (Integer 4 []))
                  ()
                  )
                 (Return)]
                (Var 2 _lpython_return_variable)
                Public
                false
                false
                ),
               :gsubrout
               (Function
                (SymbolTable
                 3
                 {
                  :x
                  (Variable
                   3
                   x
                   []
                   In
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   )
                  })
                gsubrout
                (FunctionType
                 [(Integer 4 [])]
                 ()
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                []
                [(Var 3 x)]
                [(Print
                  ()
                  [(Var 3 x)]
                  ()
                  ()
                  )]
                ()
                Public
                false
                false),
               :test_fn1
               (Function
                (SymbolTable
                 4
                 {:__lcompilers_dummy
                  (Variable
                   4
                   __lcompilers_dummy
                   []
                   Local
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false),
                  :i
                  (Variable
                   4
                   i
                   []
                   Local
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false),
                  :j
                  (Variable
                   4
                   j
                   []
                   Local
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false)})
                test_fn1
                (FunctionType
                 []
                 ()
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                [g
                 gsubrout]
                []
                [(= (Var 4 i)
                  (FunctionCall
                   7 g
                   ()
                   []
                   (Integer 4 [])
                   ()
                   ()) ())
                 (= (Var 4 j)
                  (FunctionCall
                   7 g
                   ()
                   []
                   (Integer 4 [])
                   ()
                   ()) ())
                 (= (Var 4 __lcompilers_dummy)
                  (FunctionCall
                   7 g
                   ()
                   []
                   (Integer 4 [])
                   ()
                   ()) ())
                 (SubroutineCall
                  7 gsubrout
                  ()
                  [((Var 4 i))]
                  ())]
                ()
                Public
                false
                false
                )
               })
             _global_symbols
             []
             false
             false
             ),
            :main_program
            (Program
             (SymbolTable
              5
              {
               :_lpython_main_program
               (ExternalSymbol
                5
                _lpython_main_program
                7 _lpython_main_program
                _global_symbols
                []
                _lpython_main_program
                Public
                )
               })
             main_program
             [_global_symbols]
             [(SubroutineCall
               5 _lpython_main_program
               ()
               []
               ()
               )]
             )
            })
          []
          ),

        long-form-legacy-e2e0267
        (do (in-ns 'masr.specs)
            (eval (rewrite-for-legacy
                   hand-written-quoted-e2e0267)))

        rewritten-e2e0267
        (rewrite-for-legacy
         hand-written-quoted-e2e0267),

        refnym
        "_expr_10_e2e0267"

        slurped-e2e0267
        (slurp-asr refnym)

        long-form-slurped-e2e0267
        (long-form-asr refnym)]

    (testing "whole translation unit for e2e0267"
      (is (s/valid? ::asr/unit            long-form-legacy-e2e0267))
      (is (s/valid? ::asr/asr-term        long-form-legacy-e2e0267))
      (is (s/valid? ::asr/TranslationUnit long-form-legacy-e2e0267))
      (is (s/valid? ::asr/unit            long-form-slurped-e2e0267))
      (is (s/valid? ::asr/asr-term        long-form-slurped-e2e0267))
      (is (s/valid? ::asr/TranslationUnit long-form-slurped-e2e0267)))

    (testing "textual identity of slurped e2e0267"
      (is (= hand-written-quoted-e2e0267 slurped-e2e0267)))

    (test-translation-unit -expr1-dde511e)
    (test-translation-unit -expr10-31c163f)
    (test-translation-unit -expr11-1134d3f)
    (test-translation-unit -expr12-2a30333)
    (test-translation-unit -expr13-10040d8)
    (test-translation-unit -expr4-cf512ef)
    (test-translation-unit -expr6-bfb3384)
    (test-translation-unit -expr7-2ef3822)
    (test-translation-unit -expr8-2a4630a)
    (test-translation-unit -expr9-c6fe691)
    (test-translation-unit -expr_01-03055c0)
    (test-translation-unit -expr_01-eafd41c)
    (test-translation-unit -expr_14-6023c49)
    (test-translation-unit -test_bool_binop-3075d22)
    (test-translation-unit -test_bool_binop-3075d22)
    (test-translation-unit -test_builtin-4f04bbc)
    (test-translation-unit -test_builtin_abs-06a7e49)
    (test-translation-unit -test_builtin_bin-0ca34fe)
    (test-translation-unit -test_builtin_bool-fe3fe33)
    (test-translation-unit -test_builtin_float-97f9316)
    (test-translation-unit -test_builtin_hex-d4abc3e)
    (test-translation-unit -test_builtin_int-990d1de)
    (test-translation-unit -test_builtin_len-922cf65)
    (test-translation-unit -test_builtin_oct-490a98b)
    (test-translation-unit -test_builtin_pow-cea529e)
    (test-translation-unit -test_builtin_round-cca5cba)
    (test-translation-unit -test_builtin_str-fcdedc2)
    (test-translation-unit -test_c_interop_01-8bee4ec)
    (test-translation-unit -test_complex_01-c199562)
    (test-translation-unit -test_complex_02-6516823)
    (test-translation-unit -test_end_sep_keywords-49ea13f)
    (test-translation-unit -test_integer_bitnot-0d0eafa)
    (test-translation-unit -test_max_min-e73decc)
    (test-translation-unit -test_numpy_03-6dd742e)
    (test-translation-unit -test_numpy_04-3376b7a)
    (test-translation-unit -test_pow-6f6a69d)
    (test-translation-unit -tuple1-ce358d9)
    (test-translation-unit -vec_01-9b22f33)
    (test-translation-unit _expr2_5311701)
    (test-translation-unit _expr_10_e2e0267)
    (test-translation-unit _pass_inline_function_calls-func_inline_01-6cf8821)
    (comment "too big for evaluation"
             "see big_test.clj for bisection and analysis")
    #_
    (test-translation-unit _pass_print_list_tuple-print_02-1bcc4ec)
    ))


;; See tests named "big....clj" for analysis of
;; these difficult cases. Also see ANALYZERS
;; section in the code and Markdown file.


#_(def too-big-slurped-1bcc4ec
    (->> "_pass_print_list_tuple-print_02-1bcc4ec"
         slurp-asr))


#_(deftest too-big-slurped-1bcc4ec-test
  (is (s/valid? ::asr/TranslationUnit
                (to-full-form too-big-slurped-1bcc4ec))))


(deftest bisecting-6cf8821
  (is (s/valid? ::asr/identifier "__1"))
  (is (s/valid? ::asr/GoTo
                (GoTo 1 __1)))
  (is (s/valid? ::asr/If
                (legacy
                 (If
                  (IntegerCompare
                   (Var 3 n_fib)
                   Lt
                   (Cast
                    (IntegerConstant 2 (Integer 4 []))
                    IntegerToInteger
                    (Integer 8 [])
                    (IntegerConstant 2 (Integer 8 [])))
                   (Logical 4 [])
                   ())
                  [(=
                    (Var 3 _lpython_return_variable_fib)
                    (Var 3 n_fib)
                    ())
                   (GoTo 1 __1)]
                  []))))

  (is (s/valid? ::asr/BlockCall
                (legacy
                 (BlockCall 1 3 __TILDE__empty_block)))))


(deftest generic-procedure-test
  (let [x (GenericProcedure
           3
           arccos
           [3 __lpython_overloaded_0__arccos
            3 __lpython_overloaded_1__arccos]
           Public )]
   (is (s/valid? ::asr/GenericProcedure x))))


(deftest bisecting-fcdedc2
  (is (s/valid? ::asr/StringItem
                (legacy
                 (StringItem
                  (Var 5 __tmp_assign_for_loop)
                  (IntegerBinOp
                   (Var 5 __explicit_iterator)
                   Add
                   (IntegerConstant 1 (Integer 4 []))
                   (Integer 4 [])    ()    )
                  (Integer 4 [])    ()    )))))


(deftest bisecting-922cf65-test-2
  (is (s/valid? ::asr/TranslationUnit
                (TranslationUnit
                 (SymbolTable 1 {})
                 [])))
  (is (s/valid? ::asr/tuple-expr
                (Var 2 t2)))
  (is (s/valid? ::asr/Integer
                (Integer 4 [])))
  (is (s/valid? ::asr/integer-value?
                (IntegerConstant 5 (Integer 4 []))))
  (is (s/valid? ::asr/TupleLen
                (TupleLen
                 (Var 2 t2)
                 (Integer 4 [])
                 (IntegerConstant 5 (Integer 4 []))
                 )))
  (is (s/valid? ::asr/loop-v (Var 2 i)))
  (is (s/valid? ::asr/loop-start (IntegerConstant 0 (Integer 4 []))))
  (is (s/valid? ::asr/loop-end
                (IntegerBinOp
                 (TupleLen
                  (Var 2 t2)
                  (Integer 4 [])
                  (IntegerConstant 5 (Integer 4 [])))
                 Sub
                 (IntegerConstant 1 (Integer 4 []))
                 (Integer 4 [])
                 (IntegerConstant 4 (Integer 4 [])))))
  (is (s/valid? ::asr/loop-increment
                (IntegerConstant 1 (Integer 4 []))))
  (is (s/valid? ::asr/do-loop-head
                {::asr/loop-v         (Var 2 i)
                 ::asr/loop-start     (IntegerConstant 0 (Integer 4 []))
                 ::asr/loop-end       (IntegerBinOp
                                       (TupleLen
                                        (Var 2 t2)
                                        (Integer 4 [])
                                        (IntegerConstant 5 (Integer 4 [])))
                                       Sub
                                       (IntegerConstant 1 (Integer 4 []))
                                       (Integer 4 [])
                                       (IntegerConstant 4 (Integer 4 [])))
                 ::asr/loop-increment (IntegerConstant 1 (Integer 4 []))}))
  (is (= {::asr/loop-v         (Var 2 i)
          ::asr/loop-start     (IntegerConstant 0 (Integer 4 []))
          ::asr/loop-end       (IntegerBinOp
                                (TupleLen
                                 (Var 2 t2)
                                 (Integer 4 [])
                                 (IntegerConstant 5 (Integer 4 [])))
                                Sub
                                (IntegerConstant 1 (Integer 4 []))
                                (Integer 4 [])
                                (IntegerConstant 4 (Integer 4 [])))
          ::asr/loop-increment (IntegerConstant 1 (Integer 4 []))}
         (do-loop-head
          (legacy
           ((Var 2 i)
            (IntegerConstant 0 (Integer 4 []))
            (IntegerBinOp
             (TupleLen
              (Var 2 t2)
              (Integer 4 [])
              (IntegerConstant 5 (Integer 4 [])))
             Sub
             (IntegerConstant 1 (Integer 4 []))
             (Integer 4 [])
             (IntegerConstant 4 (Integer 4 [])))
            (IntegerConstant 1 (Integer 4 [])))))))
  (is (s/valid? ::asr/do-loop-head
                (legacy
                 (do-loop-head
                  ((Var 2 i)
                   (IntegerConstant 0 (Integer 4 []))
                   (IntegerBinOp
                    (TupleLen
                     (Var 2 t2)
                     (Integer 4 [])
                     (IntegerConstant 5 (Integer 4 [])))
                    Sub
                    (IntegerConstant 1 (Integer 4 []))
                    (Integer 4 [])
                    (IntegerConstant 4 (Integer 4 [])))
                   (IntegerConstant 1 (Integer 4 [])))))
                ))
  (is (s/valid? ::asr/DoLoop
                (legacy
                 (DoLoop
                  ()
                  ((Var 2 i)
                   (IntegerConstant 0 (Integer 4 []))
                   (IntegerBinOp
                    (TupleLen
                     (Var 2 t2)
                     (Integer 4 [])
                     (IntegerConstant 5 (Integer 4 []))
                     )
                    Sub
                    (IntegerConstant 1 (Integer 4 []))
                    (Integer 4 [])
                    (IntegerConstant 4 (Integer 4 []))
                    )
                   (IntegerConstant 1 (Integer 4 [])))
                  [(ListAppend
                    (Var 2 l)
                    (Var 2 i)
                    )]
                  ))))
  )


(deftest bisecting-922cf65-test
  (is (s/valid? ::asr/elements
                [(IntegerConstant 1 (Integer 4 []))
                 (IntegerConstant 2 (Integer 4 []))
                 (StringConstant
                  "a"
                  (Character 1 1 () [])
                  )]))
  (is (s/valid? ::asr/ttype* [(Integer 4 [])
                              (Integer 4 [])
                              (Character 1 1 () [])]))
  (is (s/valid? ::asr/Tuple
                {::asr/term ::asr/ttype
                 ::asr/asr-ttype-head
                 {::asr/ttype-head ::asr/Tuple
                  ::asr/ttype* [(Integer 4 [])
                                (Integer 4 [])
                                (Character 1 1 () [])]}}))
  (is (s/valid? ::asr/Tuple
                (Tuple
                 [(Integer 4 [])
                  (Integer 4 [])
                  (Character 1 1 () [])])))
  (is (s/valid? ::asr/TupleConstant
                (TupleConstant
                 [(IntegerConstant 1 (Integer 4 []))
                  (IntegerConstant 2 (Integer 4 []))
                  (StringConstant
                   "a"
                   (Character 1 1 () [])
                   )]
                 (Tuple
                  [(Integer 4 [])
                   (Integer 4 [])
                   (Character 1 1 () [])]))))
  )


(deftest bisecting-expr13-10040d8
  (is (not (s/valid? ::asr/IntegerCompare
                     (legacy (IntegerCompare
                              (IntegerConstant 5 (Integer 4 []))
                              Gt
                              (IntegerConstant 4 (Integer 4 []))
                              (Integer 4 []) ;; <~~~ booger
                              (LogicalConstant
                               true
                               (Logical 4 [])
                               ))))))
  (is (s/valid? ::asr/IntegerCompare
                (legacy (IntegerCompare
                         (IntegerConstant 5 (Integer 4 []))
                         Gt
                         (IntegerConstant 4 (Integer 4 []))
                         (Logical 4 [])
                         (LogicalConstant
                          true
                          (Logical 4 [])
                          )))))
  (is (s/valid? ::asr/StringCompare
                (legacy (StringCompare
                         (StringConstant
                          "abc"
                          (Character 1 3 () [])
                          )
                         Gt
                         (StringConstant
                          "abd"
                          (Character 1 3 () [])
                          )
                         (Logical 4 [])
                         (LogicalConstant
                          false
                          (Logical 4 [])
                          )
                         )))))


(deftest bisecting-expr9-c6fe691
  (let [example
        (FunctionType
         [(Integer 4 [])]
         (Character 1 -2 () []) ;; negative length ? Issue #36
         Source
         Implementation
         ()
         false
         false
         false
         false
         false
         []
         []
         false
         )]
    (is (s/valid? ::asr/FunctionType example)))
  )


(deftest bisecting-2ef3822-test
  (let [example
        (legacy
         (TranslationUnit
          (SymbolTable
           1
           {
            :_global_symbols
            (Module
             (SymbolTable
              114
              {
               :_lpython_main_program
               (Function
                (SymbolTable
                 113
                 {

                  })
                _lpython_main_program
                (FunctionType
                 []
                 ()
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                [main0]
                []
                [(SubroutineCall
                  114 main0
                  ()
                  []
                  ()
                  )]
                ()
                Public
                false
                false
                ),
               :main0
               (Function
                (SymbolTable
                 4
                 {
                  :c
                  (Variable
                   4
                   c
                   []
                   Local
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   )
                  })
                main0
                (FunctionType
                 []
                 ()
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                [test_pow
                 test_pow_1]
                []
                [(SubroutineCall
                  114 test_pow
                  ()
                  []
                  ()
                  )
                 (=
                  (Var 4 c)
                  (FunctionCall
                   114 test_pow_1
                   ()
                   [((IntegerConstant 1 (Integer 4 [])))
                    ((IntegerConstant 2 (Integer 4 [])))]
                   (Integer 4 [])
                   ()
                   ()
                   )
                  ()
                  )]
                ()
                Public
                false
                false
                ),
               :test_pow
               (Function
                (SymbolTable
                 2
                 {
                  :a
                  (Variable
                   2
                   a
                   []
                   Local
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   ),
                  :pow
                  (ExternalSymbol
                   2
                   pow
                   6 pow
                   lpython_builtin
                   []
                   pow
                   Private
                   ),
                  :pow__AT____lpython_overloaded_0__pow
                  (ExternalSymbol
                   2
                   pow__AT____lpython_overloaded_0__pow
                   6 __lpython_overloaded_0__pow
                   lpython_builtin
                   []
                   __lpython_overloaded_0__pow
                   Public
                   )
                  })
                test_pow
                (FunctionType
                 []
                 ()
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                [pow__AT____lpython_overloaded_0__pow]
                []
                [(=
                  (Var 2 a)
                  (Cast
                   (FunctionCall
                    2 pow__AT____lpython_overloaded_0__pow
                    2 pow
                    [((IntegerConstant 2 (Integer 4 [])))
                     ((IntegerConstant 2 (Integer 4 [])))]
                    (Real 8 [])
                    (RealConstant
                     4.000000
                     (Real 8 [])
                     )
                    ()
                    )
                   RealToInteger
                   (Integer 4 [])
                   (IntegerConstant 4 (Integer 4 []))
                   )
                  ()
                  )]
                ()
                Public
                false
                false
                ),
               :test_pow_1
               (Function
                (SymbolTable
                 3
                 {
                  :_lpython_return_variable
                  (Variable
                   3
                   _lpython_return_variable
                   []
                   ReturnVar
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   ),
                  :a
                  (Variable
                   3
                   a
                   []
                   In
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   ),
                  :b
                  (Variable
                   3
                   b
                   []
                   In
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   ),
                  :pow
                  (ExternalSymbol
                   3
                   pow
                   6 pow
                   lpython_builtin
                   []
                   pow
                   Private
                   ),
                  :pow__AT____lpython_overloaded_0__pow
                  (ExternalSymbol
                   3
                   pow__AT____lpython_overloaded_0__pow
                   6 __lpython_overloaded_0__pow
                   lpython_builtin
                   []
                   __lpython_overloaded_0__pow
                   Public
                   ),
                  :res
                  (Variable
                   3
                   res
                   []
                   Local
                   ()
                   ()
                   Default
                   (Integer 4 [])
                   Source
                   Public
                   Required
                   false
                   )
                  })
                test_pow_1
                (FunctionType
                 [(Integer 4 [])
                  (Integer 4 [])]
                 (Integer 4 [])
                 Source
                 Implementation
                 ()
                 false
                 false
                 false
                 false
                 false
                 []
                 []
                 false
                 )
                [pow__AT____lpython_overloaded_0__pow]
                [(Var 3 a)
                 (Var 3 b)]
                [(=
                  (Var 3 res)
                  (Cast
                   (FunctionCall
                    3 pow__AT____lpython_overloaded_0__pow
                    3 pow
                    [((Var 3 a))
                     ((Var 3 b))]
                    (Real 8 [])
                    ()
                    ()
                    )
                   RealToInteger
                   (Integer 4 [])
                   ()
                   )
                  ()
                  )
                 (=
                  (Var 3 _lpython_return_variable)
                  (Var 3 res)
                  ()
                  )
                 (Return)]
                (Var 3 _lpython_return_variable)
                Public
                false
                false
                )
               })
             _global_symbols
             [lpython_builtin]
             false
             false)})
          []
          ))]
    (is (s/valid? ::asr/unit example))))


;; ================================================================
;;      __    _    ____  ____  _
;;  ____\ \  / \  / ___||  _ \| |
;; |     \ \/ _ \ \___ \| | | | |
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
  (is (not (s/valid? ::asr/Program (Program
                                    "messed-up"
                                    main_program
                                    []
                                    []))))
  (is (= "Var(symbol_table stid, identifier varnym)"
         (->asdl-type (Var 2 x))))
  (is (= "Assignment(expr target, expr value, stmt? overloaded)"
         (->asdl-type (legacy
                       (= (Var 2 a)
                          (LogicalBinOp
                           (Var 2 b)
                           Or
                           (Var 2 b)
                           (Logical 4 []) ()) ())))))
  (is (= "Logical(int kind, dimension* dims)"
         (->asdl-type (Logical 4 []))))
  (is (= "Logical(int kind, dimension* dims)"
         (->asdl-type (Logical 4 [[6 60] []]))))
  (is (= "SubroutineCall(symbol name, symbol? original_name, call_arg* args, expr? dt)"
         (->asdl-type (SubroutineCall 7 test_fn1 () [] ())))))


;; ================================================================
;;  __  __ ___ ____   ____
;; |  \/  |_ _/ ___| / ___|
;; | |\/| || |\___ \| |
;; | |  | || | ___) | |___
;; |_|  |_|___|____/ \____|


(defmacro typed-constant-type-nyms-test
  [ttype ;; like Integer
   spec  ;; like int
   ]
  (let [ns    "masr.specs"
        fnstr (str ttype "Constant")   ;; like "IntegerConstant"
        fnsym (symbol fnstr)           ;; like 'IntegerConstant
        fnqkw (keyword ns fnstr)       ;; like ::IntegerConstant
        ttstr (str ttype)              ;; like "Integer"
        ttsym (symbol ttstr)           ;; like 'Integer
        ttqkw (keyword ns ttstr)       ;; like ::Integer
        spqkw (keyword ns (str spec))  ;; like ::int
        nvukw (keyword (str "invalid-" ;; like :invalid-integer-constant
                            (csk/->kebab-case
                             fnstr)))
        vpsym ;; like 'an-int
        (symbol (case spec
                  'int (str "an-" spec)
                  (str "a-" spec)))

        tpsym ;; like 'a-ttype
        (symbol (str 'a-ttype))]
    `{:fnstr  ~fnstr,
      :fnsym '~fnsym,
      :fnqkw  ~fnqkw,
      :ttsym '~ttsym
      :ttqkw  ~ttqkw,

      :spqkw  ~spqkw,
      :nvukw  ~nvukw,
      :vpsym '~vpsym,
      :tpsym '~tpsym}))

;; Sometimes, a nym is a #{string? symbol? keyword?}
(deftest camel-snake-kebab-test
  (testing "constant names"

    (let [nyms (typed-constant-type-nyms-test Logical bool)]
      (is (= "LogicalConstant"         (:fnstr nyms)))
      (is (= 'LogicalConstant          (:fnsym nyms)))
      (is (= ::asr/LogicalConstant     (:fnqkw nyms)))
      (is (= ::asr/Logical             (:ttqkw nyms)))
      (is (= 'Logical                  (:ttsym nyms)))

      (is (= ::asr/bool                (:spqkw nyms)))
      (is (= :invalid-logical-constant (:nvukw nyms)))
      (is (= 'a-bool                   (:vpsym nyms)))
      (is (= 'a-ttype                  (:tpsym nyms))))

    (let [nyms (typed-constant-type-nyms-test Integer int)]
      (is (= "IntegerConstant"         (:fnstr nyms)))
      (is (= 'IntegerConstant          (:fnsym nyms)))
      (is (= ::asr/IntegerConstant     (:fnqkw nyms)))
      (is (= ::asr/Integer             (:ttqkw nyms)))
      (is (= 'Integer                  (:ttsym nyms)))

      (is (= ::asr/int                 (:spqkw nyms)))
      (is (= :invalid-integer-constant (:nvukw nyms)))
      (is (= 'an-int                   (:vpsym nyms)))
      (is (= 'a-ttype                  (:tpsym nyms))))

    (let [nyms (typed-constant-type-nyms-test Real float)]
      (is (= "RealConstant"            (:fnstr nyms)))
      (is (= 'RealConstant             (:fnsym nyms)))
      (is (= ::asr/RealConstant        (:fnqkw nyms)))
      (is (= ::asr/Real                (:ttqkw nyms)))
      (is (= 'Real                     (:ttsym nyms)))

      (is (= ::asr/float               (:spqkw nyms)))
      (is (= :invalid-real-constant    (:nvukw nyms)))
      (is (= 'a-float                  (:vpsym nyms)))
      (is (= 'a-ttype                  (:tpsym nyms))))

    (let [nyms (typed-constant-type-nyms-test String string)]
      (is (= "StringConstant"          (:fnstr nyms)))
      (is (= 'StringConstant           (:fnsym nyms)))
      (is (= ::asr/StringConstant      (:fnqkw nyms)))
      (is (= ::asr/String              (:ttqkw nyms)))
      (is (= 'String                   (:ttsym nyms)))

      (is (= ::asr/string              (:spqkw nyms)))
      (is (= :invalid-string-constant  (:nvukw nyms)))
      (is (= 'a-string                 (:vpsym nyms)))
      (is (= 'a-ttype                  (:tpsym nyms))))

    ;; Complex is a special case
    )
  (testing "an unqualified keyword is a keyword"
    (is (keyword?                 :foo))
    (is (not (qualified-keyword?  :foo))))
  (testing "a qualified keyword is a keyword"
    (is (qualified-keyword?      ::foo))
    (is (keyword?                ::foo)))
  )
