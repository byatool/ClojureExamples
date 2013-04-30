(ns complete.example.communicator.user-communicator.when-logging-a-user-in
  (:use
   clojure.test
   complete.macro.unit-test
   complete.example.communicator.user-communicator
   complete.model.message))

(string! test-username)
(string! test-password)


(def ^:dynamic create-login-information-mock
  (fn [a b]
    "create-login-information-mock"))


(def ^:dynamic hash-the-password-mock
  (fn [a]
    "hash-the-password-mock"))

(def ^:dynamic login-the-user-mock
  (fn [a]
    "login-the-user-mock"))


(def ^:dynamic validate-login-mock
  (fn [a]
    "validate-login-mock"))


(defn swap-filler [input]
  true)


(defn call-the-method []
  (login-a-user
   test-username
   test-password
   create-login-information-mock
   validate-login-mock
   hash-the-password-mock
   login-the-user-mock))

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

(it-should-call "to hash the password"
                hash-the-password-mock
                validate-login-mock)

(it-should-call "to login the user"
                login-the-user-mock
                hash-the-password-mock)

