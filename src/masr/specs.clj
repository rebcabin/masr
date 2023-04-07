(ns masr.specs
  (:use     [masr.numbers])
  (:require [clojure.spec.alpha            :as s   ]
            #_[clojure.zip                   :as z   ]
            [clojure.spec.gen.alpha        :as gen ]
            [clojure.test.check.generators :as tgen]))

;;; Hand-written specs for things that we don't want autospcc'ed.


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _
;; | / _` / -_) ' \  _| |  _| / -_) '_|
;; |_\__,_\___|_||_\__|_|_| |_\___|_|

;;; To break a cycle of imports, :asr.specs/identifier is
;;; specified in asr.base-specs. The definition of that spec
;;; writes into this namespace, :asr.specs, from another
;;; namespace, :asr.base-specs.


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/


(def MAX-NUMBER-OF-DIMENSIONS 9)


(s/def ::dimensions
  (s/coll-of (s/or :nat-int nat-int?,
                   :bigint :masr.numbers/bignat)
             :min-count 0,
             :max-count MAX-NUMBER-OF-DIMENSIONS,
             :into []))


(-> ::dimensions s/exercise)
;; => ([[] []]
;;     [[0] [[:nat-int 0]]]
;;     [[7 10 99920997] [[:bigint 7] [:bigint 10] [:bigint 99920997]]]
;;     [[490 109381 450782 19 17 1 0 1 863040]
;;      [[:bigint 490]
;;       [:bigint 109381]
;;       [:bigint 450782]
;;       [:bigint 19]
;;       [:bigint 17]
;;       [:nat-int 1]
;;       [:nat-int 0]
;;       [:nat-int 1]
;;       [:bigint 863040]]]
;;     [[2 899448 1 3551659585038 2 2 0 4 2]
;;      [[:bigint 2]
;;       [:bigint 899448]
;;       [:nat-int 1]
;;       [:bigint 3551659585038]
;;       [:nat-int 2]
;;       [:nat-int 2]
;;       [:nat-int 0]
;;       [:nat-int 4]
;;       [:nat-int 2]]]
;;     [[30 30 118 0 25681675814 1]
;;      [[:bigint 30]
;;       [:bigint 30]
;;       [:bigint 118]
;;       [:nat-int 0]
;;       [:bigint 25681675814]
;;       [:bigint 1]]]
;;     [[1 20 1840415] [[:nat-int 1] [:nat-int 20] [:bigint 1840415]]]
;;     [[1 1759907 8655617184 1 1176 16 68]
;;      [[:nat-int 1]
;;       [:bigint 1759907]
;;       [:bigint 8655617184]
;;       [:nat-int 1]
;;       [:bigint 1176]
;;       [:nat-int 16]
;;       [:bigint 68]]]
;;     [[0 27 1168 1 18 4391655 41505884589 15955129 1]
;;      [[:nat-int 0]
;;       [:nat-int 27]
;;       [:bigint 1168]
;;       [:nat-int 1]
;;       [:nat-int 18]
;;       [:bigint 4391655]
;;       [:bigint 41505884589]
;;       [:bigint 15955129]
;;       [:nat-int 1]]]
;;     [[0 31 111 462551250162 31 1 1]
;;      [[:nat-int 0]
;;       [:bigint 31]
;;       [:nat-int 111]
;;       [:bigint 462551250162]
;;       [:nat-int 31]
;;       [:nat-int 1]
;;       [:nat-int 1]]])
