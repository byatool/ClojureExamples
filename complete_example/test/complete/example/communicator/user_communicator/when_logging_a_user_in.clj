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
    (create-a-result)))


(def ^:dynamic hash-the-password-mock
  (fn [a]
    (create-a-result)))


(def ^:dynamic validate-login-mock
  (fn [a]
    (create-a-result)))


(defn swap-filler [input]
  true)


(defn call-the-method []
  (login-a-user
   test-username
   test-password
   create-login-information-mock
   validate-login-mock
   hash-the-password-mock))

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

(it-should-attempt "to hash the password"
                   hash-the-password-mock
                   #(if (= %1 (validate-login-mock nil))
                      (it-was-called!)
                      nil))

