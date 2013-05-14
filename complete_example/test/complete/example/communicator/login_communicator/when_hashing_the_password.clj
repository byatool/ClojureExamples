(ns complete.example.communicator.login-communicator.when-hashing-the-password
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.model.message :only (add-an-error-message create-a-result retrieve-value)]
   [complete.example.communicator.login-communicator :only (hash-the-password create-login-information)]))


;; Fields

(string! password)
(string! hashed)
(def result-value {:password password})
(def ^:dynamic result (create-a-result result-value))
(def final-result (create-a-result nil))


;; Mock Functions

(defd assoc-mock [a b c] result-value)
(defd hash-text-mock [a] hashed)
(defd retrieve-value-mock [a] result-value)
(defd set-result-value-mock [a b] final-result)

;; Support Functions


(defn call-the-method []
  (hash-the-password result retrieve-value-mock hash-text-mock assoc-mock set-result-value-mock))


;; Test Functions

(it-should "return the result if it was not successful"
           (binding [result (add-an-error-message result "hihi")]
             (is (= result (call-the-method)))))


(it-should-try "to get the value from the result"
                   retrieve-value-mock
                   #(= % result))


(it-should-try "trying to hash the text"
                   hash-text-mock
                   #(= % password))


(it-should-try "to update the password on the result"
                   assoc-mock
                   #(and
                     (= %1 hashed)
                     (= %2 :password)
                     (= %3 result-value )))


(it-should-try "to set the value"
                   set-result-value-mock
                   #(and
                     (= %1 result)
                     (= %2 result-value)))


(it-should "return the updated result"
           (is (= final-result (call-the-method))))
