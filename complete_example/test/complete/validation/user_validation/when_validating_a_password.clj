(ns complete.validation.user-validation.when-validating-a-password
  (:use 
   clojure.test
   complete.macro.unit-test
   [complete.validation.user-validation :only (validate-password)]
   [complete.example.communicator.login-communicator :only (create-login-information)]))

;; Fields

(string! username)
(string! password)
(string! error-message)
(def result (create-login-information username password))


;; Mock Functions

(defd add-error-message-mock [a b] result)
(defd retrieve-value-mock [a] {:password password})
(defd text-is-empty-mock [a b] error-message)


;; Support Functions

(defn call-the-method []
  (validate-password result retrieve-value-mock text-is-empty-mock add-error-message-mock))


;; Test Functions

(it-should-try "to retrieve the password"
               retrieve-value-mock
               #(= % result))

(it-should-try "to check if the password is empty"
               text-is-empty-mock
               #(and
                 (= %1 password)
                 (= %2 "Password")))

(it-should-try "to add the error message"
               add-error-message-mock
               #(and
                 (= %1 result)
                 (= %2 error-message)))

(it-should "return the result"
           (is (= result (add-error-message-mock "" ""))))

