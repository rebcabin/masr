(ns masr.protocols)


(defprotocol Summarize
  "shorter and flatter printouts for sane development"
  (summarize [r] "Summarize a record, i.e., print its summary.
 By convention, return 'self', like 'echo', so 'summarize'
 acts like a logging 'identity' function.")
  (summary [r] "Construct a string summary of a record."))


(defn conditional-summary
  "Some entities represent absence of a value via any empty collection or nil."
  [it]
  (if (or (nil? it) (empty? it))
    it
    (summary it)))
