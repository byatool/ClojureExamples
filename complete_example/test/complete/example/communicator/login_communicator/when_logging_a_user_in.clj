(ns complete.example.communicator.login-communicator.when-logging-a-user-in
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.example.communicator.login-communicator :only (login-a-user)]
   complete.model.message))


;; Fields

(string! test-username)
(string! test-password)


;; Mock Functions

(chain-method! hash-the-password-mock)
(chain-method! login-the-user-mock)
(chain-method! set-the-cookie-mock)
(chain-method! validate-login-mock)
(defd create-login-information-mock [a b] "create-login-information-mock")


;; Support Functions

(defn call-the-method []
  (login-a-user
   test-username
   test-password
   create-login-information-mock
   validate-login-mock
   hash-the-password-mock
   login-the-user-mock
   set-the-cookie-mock))


;; Test Functions

(it-should-try "to assemble the need information"
               create-login-information-mock
               #(and
                 (= %1 test-username )
                 (= %2 test-password)))

(it-should-try "to validate the created login information"
               validate-login-mock
               #(= % (create-login-information-mock test-username test-password))) 

(it-should-call "to hash the password with the validation result"
                hash-the-password-mock
                validate-login-mock)

(it-should-call "to login the user with the hash password result"
                login-the-user-mock
                hash-the-password-mock)

(it-should-call "to set the cookie with the login result"
                set-the-cookie-mock
                login-the-user-mock)

(it-should "return the final result"
           (is (= (call-the-method)(set-the-cookie-mock nil))))

