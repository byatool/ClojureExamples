(ns complete.utility.text-transform.when-hashing-text
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.utility.text-transform :only (hash-text seed)]))

(string! username)
(string! result)

(defd md5-mock [a b]
  result)

(defn call-the-method []
  (hash-text username md5-mock))

(it-should-try "to hash the text"
                   md5-mock
                   #(and
                     (= %1 username)
                     (= %2 seed)))

(it-should "return the result"
           (is (= 1 (call-the-method))))




 (with-test-out
        (run-tests))