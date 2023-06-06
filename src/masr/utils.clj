(ns masr.utils
  (:require [clojure.pprint  :refer [pprint]]
            [clojure.java.io :as     io]
            ))


(defn delete-directory-recursive
  "Recursively delete a directory."
  [^java.io.File file]
  ;; when `file` is a directory, list its entries and call this
  ;; function with each entry. can't `recur` here as it's not a tail
  ;; position, sadly. could cause a stack overflow for many entries?
  ;; thanks to @nikolavojicic for the idea to use `run!` instead of
  ;; `doseq` :)
  (when (.isDirectory file)
    (run! delete-directory-recursive (.listFiles file)))
  ;; delete the file or directory. if it it's a file, it's easily
  ;; deletable. if it's a directory, we already have deleted all its
  ;; contents with the code above (remember?)
  (io/delete-file file))


(defn warnings-banner []
  (println "+--------------------------------------------------------------+")
  (println "| Note from the Authors:                                       |")
  (println "| The warnings about Integer, Character, deftype, etc.,        |")
  (println "| are expected and not maskable!                               |")
  (println "+--------------------------------------------------------------+"))


(defmacro plnecho
  "preimage and value"
  [x]
  `(let [x# ~x]
     (do (println '~x "~~~~>")
         (pprint x#)
         x#)))


(defmacro mkdict [& vars]
  (let [keys (vec (map (comp keyword str) vars))]
    `(zipmap ~keys ~(vec vars))))


(defmacro ppdict [& vars]
  (let [keys (vec (map (comp keyword str) vars))]
    `(pprint (zipmap ~keys ~(vec vars)))))


(defmacro dosafely [expr]
  `(try ~expr
        (catch Exception e#
          (with-out-str
            (println (.getMessage e#))))))


(defn pprint-file [filenamestr, obj]
  (with-open [w (io/writer filenamestr)]
    (binding [*out* w]
      (pprint obj))))


(defn plnecho-file
  [filenamestr, obj]
  (pprint-file filenamestr, obj)
  obj)
