(ns masr.logic)


(defn iff [a b]
  (or (and a b)
      (not (or a b))))


(defn implies [a b]
  (not (and a (not b)))) ;; same as (or (not a) b)
