(defmacro when [text & body]
  (let [namespace (create-symbol-from-string text)]
    `(~(create-symbol-from-string "ns") ~namespace
      (:use [clojure.test :as test])
      ~@body)))

(defmacro it-should [text & body]
  `(test/deftest ~(create-symbol-from-string text)
     (test/testing ~text
       ~@body)))
















(ns test-example.when-adding-two-numbers
  (:use clojure.test
        test-example.simple-math))

(deftest when-adding-two-numbers
  (let [expected-number 1]
    (testing "It will return the second if the first is zero"
      (is (= (add-two-numbers 1 expected-number) expected-number)))))

(defn create-symbol-from-string [text]
  (symbol (join "-" (split text #" "))))







(when "something is here"
  [(it-should "test stuff" (is (= 1 2)))
   (it-should "test things" (is (= 2 3)))])

