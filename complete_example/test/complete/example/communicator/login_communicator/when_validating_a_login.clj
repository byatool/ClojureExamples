(ns complete.example.communicator.login-communicator.when-validating-a-login
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.example.communicator.login-communicator :only (create-login-information validate-login)]
   complete.validation.text-validation))

;; Fields

(string! user-name)
(string! password)

(def login-information (create-login-information user-name password))


;; Mock Functions

(chain-method! validate-username-mock)
(chain-method! validate-password-mock)


;; Support Functions

(defn call-the-method []
  (validate-login login-information validate-username-mock validate-password-mock))


;; Test Functions

(it-should-try "to validate the user name"
                   validate-username-mock
                   #(= % login-information))

(it-should-call "to validate the password"
                validate-password-mock
                validate-username-mock)

(it-should "return the final result"
           (is (= (call-the-method)(validate-password-mock nil))))
