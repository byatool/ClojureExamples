(ns complete.validation.user-validation.when-validating-a-username
  (:use 
   clojure.test
   complete.macro.unit-test
   [complete.validation.user-validation :only (validate-username)]
   [complete.example.communicator.login-communicator :only (create-login-information)]))


;; Fields

(string! error-message)
(string! password)
(string! username)
(def result (create-login-information username password))


;; Mock Functions

(defd add-error-message-mock [a b] result)
(defd retrieve-value-mock [a] {:username username})
(defd text-is-empty-mock [a b] error-message)


;; Support Functions

(defn call-the-method []
  (validate-username result retrieve-value-mock text-is-empty-mock add-error-message-mock))


;; Test Functions

(it-should-try "to retrieve the username"
                   retrieve-value-mock
                   #(= % result))

(it-should-try "to check if the username is empty"
                   text-is-empty-mock
                   #(and
                     (= %1 username)
                     (= %2 "Username")))

(it-should-try "to add the error message"
                   add-error-message-mock
                   #(and
                     (= %1 result)
                     (= %2 error-message)))

(it-should "return the result"
           (is (= result (add-error-message-mock "" ""))))

