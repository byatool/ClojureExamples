(ns complete.example.communicator.login-communicator.when-creating-login-information
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.example.communicator.login-communicator :only (create-login-information)]
   complete.model.message))

;; Fields

(string! test-username)
(string! test-password)
(string! result)
(defd create-a-result-mock [a] result)


;; Support Functions

(defn call-the-method []
  (create-login-information test-username test-password create-a-result-mock))


;; Test Functions

(it-should-try "to create a result"
                   create-a-result-mock
                   #(= %1 {:username test-username :password test-password}))


(it-should "return a result"
           (is (= (call-the-method) result)))
