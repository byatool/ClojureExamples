(ns complete.macro.unit-test
  (:use
   clojure.test
   [crypto.random  :only (base64)]
   [clojure.string :only (join split)]))

(defn create-symbol-from-string [text]
  "This will take a string, replace spaces with hyphens, and turn it into a symbol"
  (symbol (join "-" (split text #" "))))

(defmacro it-should-attempt [description method-name check]
  `(deftest ~(create-symbol-from-string description)
     (testing ~(apply str "it should " description)
       (do
         (def  ~(symbol "was-called") (atom false))
         (binding [~(symbol (str method-name))
                   ~check]
           (~(symbol "call-the-method")))
         (is (= @~(symbol "was-called") true))))))


(defmacro it-should [description & rest]
  `(deftest ~(create-symbol-from-string description)
     (testing ~(apply str "it should " description)
       (do ~@rest ))))


;; (def username (crypto.random/base64 10))
(defmacro string! [name]
  "This will declare a var, and set it to a random string."
  `(def ~name (crypto.random/base64 10)))





