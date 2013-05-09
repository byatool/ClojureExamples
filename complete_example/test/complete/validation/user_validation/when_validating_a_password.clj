(ns complete.validation.user-validation.when-validating-a-password
  (:use 
   clojure.test
   complete.macro.unit-test
   [complete.validation.user-validation :only (validate-password)]
   [complete.example.communicator.user-communicator :only (create-login-information)]))

;;; Fields
(string! username)
(string! password)
(string! error-message)

(def result (create-login-information username password))

(defd add-error-message-mock [a b] result)
(defd retrieve-value-mock [a] {:password password})
(defd text-is-empty-mock [a b] error-message)

;;; Support Methods
(defn call-the-method []
  (validate-password result retrieve-value-mock text-is-empty-mock add-error-message-mock))


;;; Test Methods

(it-should-attempt "retrieve the password"
                   retrieve-value-mock
                   #(if (= % result)
                      (it-was-called!)
                      nil))

(it-should-attempt "to check if the password is empty"
                   text-is-empty-mock
                   #(if (and
                         (= %1 password)
                         (= %2 "Password"))
                      (it-was-called!)
                      nil))

(it-should-attempt "to add the error message"
                   add-error-message-mock
                   #(if (and
                         (= %1 result)
                         (= %2 error-message))
                      (it-was-called!)
                      nil))

(it-should "return the result"
           (is (= result (add-error-message-mock "" ""))))

