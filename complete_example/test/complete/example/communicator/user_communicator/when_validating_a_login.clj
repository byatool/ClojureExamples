(ns complete.example.communicator.user-communicator.when-validating-a-login
  (:use
   clojure.test
   complete.macro.unit-test
   complete.example.communicator.user-communicator
   complete.validation.text-validation))

(string! user-name)
(string! password)

(chain-method! validate-username-mock)
(chain-method! validate-password-mock)

(def login-information (create-login-information user-name password))

(defn call-the-method []
  (validate-login login-information validate-username-mock validate-password-mock))


(it-should-attempt "to validate the user name"
                   validate-username-mock
                   #(if (= % login-information)
                      (it-was-called!)
                      nil))

(it-should-call "to validate the password"
                validate-password-mock
                validate-username-mock)

(it-should "return the final result"
           (is (= (call-the-method)(validate-password-mock nil))))
