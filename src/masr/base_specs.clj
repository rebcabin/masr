(ns masr.base-specs
  (:require [clojure.spec.alpha            :as s   ]
            [clojure.spec.gen.alpha        :as gen ]
            [clojure.test.check.generators :as tgen]))


;;       _
;;  __ _| |_ ___ _ __  ___
;; / _` |  _/ _ \ '  \(_-<
;; \__,_|\__\___/_|_|_/__/


(s/def :asr.specs/int   int?)
(s/def :asr.specs/float float?)
(s/def :asr.specs/bool  boolean?)


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _
;; | / _` / -_) ' \  _| |  _| / -_) '_|
;; |_\__,_\___|_||_\__|_|_| |_\___|_|


(let [alpha-re #"[a-zA-Z]" ;; "let over lambda."
      alphameric-re #"[a-zA-Z0-9]*"]
  (def alpha?
    #(re-matches alpha-re %))
  (def alphameric?
    #(re-matches alphameric-re %))
  (defn identifier? [sy]
    (let [s (str sy)]
      (and (alpha? (subs s 0 1))
           (alphameric? (subs s 1)))))
  (def identifier-generator
    (tgen/let [c (gen/char-alpha)
               s (gen/string-alphanumeric)]
      (symbol (str c s))))
  (s/def :masr.specs/identifier ;; side effects the spec registry!
    (s/with-gen
      identifier?
      (fn [] identifier-generator))))

#_
(gen/sample (s/gen :masr.specs/identifier))
;; => (e c Q G Z2qP fXzg1 sRx2J6 YIhKlV k6 f7k1Xl4)
;; => (k hM LV QWC qW0X RGk3u W Kg6X Q2YvFO621 ODUt9)

#_
(s/valid? :masr.specs/identifier 'foobar)
;; => true
