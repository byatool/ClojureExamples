(ns complete.example.communicator.user-communicator.when-logging-in-a-user
  (:use
   clojure.test
   [clojure.string :only (join split)]
   complete.macro.unit-test
   complete.example.communicator.user-communicator)
  (:require
   complete.model.message)
  (:import
   [complete.model.message MessageItem MessageResult]))


;; Fields

(string! test-username)
(string! test-password)
(string! hashed-password)
(string! login-result)

;; Support Methods


(def ^:dynamic find-user-by-credentials-mock
  (fn [a b]
    {:Messages [] :Success true :RedirectUrl "" :Item 1}))

(def ^:dynamic hash-password-mock
  (fn [a]
    hashed-password))

(def ^:dynamic set-cookie-mock
  (fn [id]
    login-result))


(defn call-the-method []
  (login-user test-username test-password hash-password-mock find-user-by-credentials-mock))


;; Test Methods

;;method-name 
(it-should-attempt "to hash the password"
                   hash-password-mock
                   #(if (= % test-password)
                      (swap! was-called (fn [useless] true))
                      nil))


(it-should-attempt "to login the user"
                   find-user-by-credentials-mock
                   #(if (and
                         (= %1 test-username)
                         (= %2 hashed-password))
                      (swap! was-called (fn [useless] true))
                      nil))

(it-should "return any errors"
           (binding [find-user-by-credentials-mock
                     (fn [username password]
                       {:messages [""]})]
              (is (= 1 (count (:messages (call-the-method)))))))

(it-should-attempt "set the cookie if the are no errors"
                   set-cookie-mock
                   
           
;;(it-should "set the url if 
;; (it-should "hash the password"
;;            (def was-called false)
;;            (binding [hash-password-mock
;;                      #(if (= % test-password)
;;                         (def was-called true)
;;                         nil)]
;;              (call-the-method))
;;            (is (= was-called true)))


;; (it-should "attempt to login"
;;            (was-called?)
;;            (binding [find-user-by-credentials-mock
;;                      #(if (and (= %1 test-username ) (= %2 hashed-password))
;;                         (was-called!)
;;                         nil)]
;;              (call-the-method))
;;            (assert-that @was-called = true))

;; (it-should "return any error messages."
;;            )









;; (it-should "contruct the redirect url if there are no errors")




;; (deftest it-should-attempt-to-login-using-the-hashed-password
;;   (testing "adsfda"
;;     (is (=  1 2))))

;; (deftest it-should-return-an-error-if-the-login-attempt-fails
;;   (testing "" ()))

;; (deftest it-should-
;; ;;(run-tests 'complete.example.when-logging-in-a-user)



