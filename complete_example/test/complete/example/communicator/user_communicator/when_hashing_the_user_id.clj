(ns complete.example.communicator.user-communicator.when-hashing-the-user-id
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.example.communicator.user-communicator :only (hash-user-id)]
   [complete.model.message :only (add-an-error-message create-a-result)]))

(string! user-id)
(string! hashed)
(def ^:dynamic result (create-a-result user-id))
(def final-result (create-a-result nil))

(defd hash-text-mock [a] hashed)
(defd retrieve-value-mock [a] user-id)
(defd set-result-value-mock [a b] final-result)

(defn call-the-method []
  (hash-user-id result retrieve-value-mock hash-text-mock set-result-value-mock))

(it-should "return the result if it was not successful"
           (binding [result (add-an-error-message result "hihi")]
             (is (= result (call-the-method)))))


(it-should-attempt "to get the value from the result"
                   retrieve-value-mock
                   #(if (= % result)
                      (it-was-called!)
                      nil))

(it-should-attempt "to hash the text"
                   hash-text-mock
                   #(if (= % user-id)
                      (it-was-called!)
                      nil))


(it-should-attempt "to create a string from the id"
                   to-string-mock
                   #(if (= % userid)
                      (it-was-called!)
                      nil))


(it-should-attempt "to set the value"
                   set-result-value-mock
                   #(if (and
                         (= %1 result)
                         (= %2 hashed))
                      (it-was-called!)
                      nil))

(it-should "return the updated result"
           (is (= final-result (call-the-method))))
