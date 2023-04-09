(ns masr.specs
  (:require [clojure.spec.alpha            :as s   ]
            #_[clojure.zip                   :as z   ]
            [clojure.spec.gen.alpha        :as gen ]
            [clojure.test.check.generators :as tgen]))


;; ASDL tuples like `(1 2)` are Clojure lists.
;; ASDL lists are ASDL tuples.
;; ASDL vectors like `[expr? stmt*]` are Clojure vectors.
;; ASDL symbol_tables are Clojure maps.


;; In general, these Clojure specs are more
;; discriminating, precise, and detailed than ASDL
;; permits.


;; terms (nodes) in the ASDL grammar:
;;
;;  1 unit            = TranslationUnit(symbol_table, node*)
;;  2 symbol          = ... many heads ...
;;  3 storage_type    = Default | Save | Parameter | Allocatable
;;  4 access          = Public | Private
;;  5 intent          = Local | In | Out | InOut | ReturnVar | Unspecified
;;  6 deftype         = Implementation | Interface
;;  7 presence        = Required | Optional
;;  8 abi             = Source | LFortranModule | ... | Intrinsic
;; 09 stmt            = ... many heads ...
;; 10 expr            = ... many heads ...
;; 11 ttype           = Integer(int, dimension*) | ... | FunctionType( ... )
;; 12 restriction_arg = RestrictionArg(identifier, symbol)
;; 13 binop           = Add | Sub | ... | BitRShift
;; 14 logicalbinop    = And | Or | Xor | NEqv | Eqv
;; 15 cmpop           = Eq | NotEq | Lt | LtE | Gt | GtE
;; 16 integerboz      = Binary | Hex | Octal
;; 17 arraybound      = LBound | UBound
;; 18 arraystorage    = RowMajor | ColMajor
;; 19 cast_kind       = RealToInteger | IntegerToReal | ... | LogicalToCharacter
;; 20 dimension       = (expr? start, expr? length)
;; 21 alloc_arg       = (expr a, dimension* dims)
;; 22 attribute       = Attribute(identifier name, attribute_arg *args)
;; 23 attribute_arg   = (identifier arg)
;; 24 call_arg        = (expr? value)
;; 25 tbind           = Bind(string lang, string name)
;; 26 array_index     = (expr? left, expr? right, expr? step)
;; 27 do_loop_head    = (expr? v, expr? start, expr? end, expr? increment)
;; 28 case_stmt       = CaseStmt(expr*, stmt*) | CaseStmt_Range( ... )
;; 29 type_stmt       = TypeStmtName(symbol, stmt*) | TypeStmtType(ttype, stmt*)
;; 30 enumtype        = IntegerConsecutiveFromZero | ... | NonInteger


;; terms not specified in ASDL:
;;
;; 31 identifier      = specified below
;; 32 symbol_table    = a clojure map
;; 33 dimension       = see below
;;  0 atoms           = int, float, bool, nat, bignat


;;       _
;;  __ _| |_ ___ _ __  ___
;; / _` |  _/ _ \ '  \(_-<
;; \__,_|\__\___/_|_|_/__/


;; See "numbers.clj" for big-int, big-nat, and nat.
(s/def :masr.specs/int   int?)
(s/def :masr.specs/float float?)
(s/def :masr.specs/bool  boolean?)


;;  _    _      _     _  ___
;; | |__(_)__ _(_)_ _| ||__ \
;; | '_ \ / _` | | ' \  _|/_/
;; |_.__/_\__, |_|_||_\__(_)
;;        |___/


(defn bigint?
  "Doesn't seem to be defined in system-supplied libraries."
  [n]
  (instance? clojure.lang.BigInt n))


;;  _ _   _    _                _
;; (_|_) | |__(_)__ _ _ _  __ _| |_
;;  _ _  | '_ \ / _` | ' \/ _` |  _|
;; (_|_) |_.__/_\__, |_||_\__,_|\__|
;;              |___/

;; Overwrite print-method for clojure BigInt to get rid of
;; the "N" at the end (can't do this inside (-main) lest
;; compile errors).


(import '(java.io Writer))


(defmethod print-method clojure.lang.BigInt
  [b, ^Writer w]
  (.write w (str b))
  #_(.write "N"))


(s/def ::bignat
  (s/with-gen
    (s/and bigint? #(>= % 0))
    ;; size-bounded-bignat is not public, else I would call it
    (fn [] tgen/size-bounded-bigint)))


;; C-c C-v C-f C-c e to generate pretty-printed
;; comments. Then stub off the call to save a tiny
;; bit of runtime. Remove the #_ and press C-c C-c
;; in the expression to see results in a CIDER Emacs
;; buffer. We follow this convenience convention
;; frequently in this development section. Comments
;; are cheap.

#_
(->> ::bignat s/exercise (map second))
;; => (7 13 63 98225932 4572 28 31914670493 80 252 256185)


;;  _ _             _
;; (_|_)  _ _  __ _| |_
;;  _ _  | ' \/ _` |  _|
;; (_|_) |_||_\__,_|\__|


(s/def ::nat (s/or :nat-int  nat-int?,
                   :bignat  ::bignat))

#_
(s/exercise ::nat 5)
;; => ([0 [:nat-int 0]]
;;     [4 [:bignat 4]]
;;     [1328 [:bignat 1328]]
;;     [226 [:bignat 226]])
;;     [1 [:bignat 1]])


;;  _    _         _   _  __ _
;; (_)__| |___ _ _| |_(_)/ _(_)___ _ _
;; | / _` / -_) ' \  _| |  _| / -_) '_|
;; |_\__,_\___|_||_\__|_|_| |_\___|_|


(let [alpha-re #"[a-zA-Z_]"  ;; "let over lambda."
      alphameric-re #"[a-zA-Z0-9_]*"]

  (def alpha?
    #(re-matches alpha-re %))

  (def alphameric?
    #(re-matches alphameric-re %))

  (defn identifier? [sy]
    (and (symbol? sy)  ;; excludes strings, numbers, quoted numbers
         (let [s (str sy)]
           (and (alpha? (subs s 0 1))
                (alphameric? (subs s 1))))))

  (def identifier-generator
    (tgen/let [c (gen/char-alpha)
               s (gen/string-alphanumeric)]
      (symbol (str c s))))

  (s/def :masr.specs/identifier ;; side effects the spec registry!
    (s/with-gen
      identifier?
      (fn [] identifier-generator))))  ;; fn wrapping a macro

#_
(gen/sample (s/gen :masr.specs/identifier))
;; => (e c Q G Z2qP fXzg1 sRx2J6 YIhKlV k6 f7k1Xl4)
;; => (k hM LV QWC qW0X RGk3u W Kg6X Q2YvFO621 ODUt9)


#_
(s/valid? :masr.specs/identifier 'foobar)
;; => true


;;     _ _                   _
;;  __| (_)_ __  ___ _ _  __(_)___ _ _  ___
;; / _` | | '  \/ -_) ' \(_-< / _ \ ' \(_-<
;; \__,_|_|_|_|_\___|_||_/__/_\___/_||_/__/


;; dimension = (expr? start, expr? length)


(def MIN-START 0)  ;; TODO: 1?
(def MAX-START 2)


(s/def ::dimension
  (s/coll-of ::nat
             :min-count MIN-START,
             :max-count MAX-START,
             :into ()))

#_
(s/exercise ::dimension 5)
;; => ([(132) ([:bignat 132])]
;;     [(0) ([:nat-int 0])]
;;     [(199) ([:bignat 199])]
;;     [(1 3) ([:nat-int 1] [:nat-int 3])]
;;     [(6 1049) ([:nat-int 6] [:bignat 1049])])

#_
(gen/sample (s/gen ::dimension) 7)
;; => ((1 60)
;;     (1)
;;     (606 66979216746710640882869059905284213752707)
;;     (52862))
;;     (0 0)
;;     ()
;;     (0))


(def MIN-NUMBER-OF-DIMENSIONS 0)  ;; TODO: 1?
(def MAX-NUMBER-OF-DIMENSIONS 9)


(s/def ::dimensions
  (s/coll-of ::dimension
             :min-count 0,
             :max-count MAX-NUMBER-OF-DIMENSIONS,
             :into []))

#_
(-> ::dimensions (s/exercise 5))
;; => ([[() (1 0) (0) ()] [() ([:bignat 1] [:nat-int 0]) ([:nat-int 0]) ()]]
;;     [[(0) (0) (1) (2902780)]
;;      [([:bignat 0]) ([:bignat 0]) ([:nat-int 1]) ([:bignat 2902780])]]
;;     [[() (15) (1 1) (1 264) (1 7) () (0 1) (2 0)]
;;      [()
;;       ([:bignat 15])
;;       ([:nat-int 1] [:nat-int 1])
;;       ([:nat-int 1] [:bignat 264])
;;       ([:nat-int 1] [:bignat 7])
;;       ()
;;       ([:nat-int 0] [:nat-int 1])
;;       ([:nat-int 2] [:nat-int 0])]]
;;     [[(6038406) () () () (7810)] [([:bignat 6038406]) () () () ([:bignat 7810])]]
;;     [[(43876) (6 8) ()] [([:bignat 43876]) ([:nat-int 6] [:nat-int 8]) ()]])

#_
(gen/sample (s/gen ::dimensions) 5)
;; => ([() (0) () (7 0) (0 1) (0 22) (7)]
;;     []
;;     [() (789 2768)]
;;     [(0 221667) (0 1)]
;;     [() (2) (0) (19931) (0 397) (1) (3) () ()])
