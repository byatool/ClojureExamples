(ns complete.example.communicator.login-communicator.when-hashing-the-user-id
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.example.communicator.login-communicator :only (hash-user-id)]
   [complete.model.message :only (add-an-error-message create-a-result)]))


;; Fields

(string! user-id)
(string! hashed)
(def ^:dynamic result (create-a-result user-id))
(def final-result (create-a-result nil))


;; Mock Functions

(defd hash-text-mock [a] hashed)
(defd retrieve-value-mock [a] user-id)
(defd set-result-value-mock [a b] final-result)
(defd to-string-mock [a] user-id)


;; Support Functions

(defn call-the-method []
  (hash-user-id result retrieve-value-mock to-string-mock hash-text-mock set-result-value-mock))


;; Test Functions

(it-should "return the result if it was not successful"
           (binding [result (add-an-error-message result "hihi")]
             (is (= result (call-the-method)))))


(it-should-try "to get the value from the result"
               retrieve-value-mock
               #(= % result))


(it-should-call "to create a string from the id"
                   to-string-mock
                   retrieve-value-mock)

(it-should-call "to hash the text"
                   hash-text-mock
                   to-string-mock)


(it-should-try "to set the value"
               set-result-value-mock
               #(and
                 (= %1 result)
                 (= %2 hashed)))


(it-should "return the updated result"
           (is (= final-result (call-the-method))))
