(ns test-example.when-adding-two-numbers
  (:use clojure.test
        test-example.simple-math))

(deftest when-adding-two-numbers
	(let [expected-number 2]
		(testing "It will return the second if the first is zero"
    	(is (= (add-two-numbers 0 expected-number) expected-number)))))

