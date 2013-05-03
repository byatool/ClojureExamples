(ns complete.example.communicator.user-communicator.when-logging-a-user-in
  (:use
   clojure.test
   complete.macro.unit-test
   complete.example.communicator.user-communicator
   complete.model.message))

(string! test-username)
(string! test-password)

(chain-method! hash-the-password-mock)
(chain-method! login-the-user-mock)
(chain-method! set-the-cookie-mock)
(chain-method! validate-login-mock)

(def ^:dynamic create-login-information-mock
  (fn [a b]
    "create-login-information-mock"))

(defn swap-filler [input]
  true)


(defn call-the-method []
  (login-a-user
   test-username
   test-password
   create-login-information-mock
   validate-login-mock
   hash-the-password-mock
   login-the-user-mock
   set-the-cookie-mock))

(it-should-attempt "to assemble the need information"
                   create-login-information-mock
                   #(if (and
                         (= %1 test-username )
                         (= %2 test-password))
                      (it-was-called!)
                      nil))

(it-should-attempt "to validate the created login information"
                   validate-login-mock
                   #(if (= % (create-login-information-mock test-username test-password))
                      (it-was-called!)
                      nil)) 

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

