(ns complete.example.when-logging-in-a-user
  (:use
   clojure.test
   [clojure.string :only (join split)]
   complete.macro.unit-test
   complete.example.communicator.user-communicator))

;; Fields
(string! test-username)
(string! test-password)
(string! hashed-password)



;; Possibly should be moved

(defn assert-that [left match right]
  (is (match left right)))

(defmacro was-called? []
  `(def  was-called (atom false)))

(defmacro was-called! []
  `(swap! was-called (fn [~(gensym)] true)))


;; Support Methods


(def find-user-by-credentials-mock (fn [a b] 1))

(def hash-password-mock (fn [a] hashed-password))

(defn call-the-method []
  (login-user test-username test-password hash-password-mock find-user-by-credentials-mock))


;; Test Methods

(it-should "hash the password"
           (was-called?)
           (def hash-password-mock
             #(if (= % test-password)
                (was-called!)
                nil))
           (call-the-method)
           (assert-that @was-called = true))


(it-should "attempt to login"
           (was-called?)
           (def find-user-by-credentials-mock
             #(if (and (= %1 test-username ) (= %2 hashed-password))
                (was-called!)
                nil))
             (call-the-method)
             (assert-that @was-called = true))

;; (it-should "return errors if there are any" nil)

;; (it-should "set the cookie if the are no errors" nil)

;; (it-should "contruct the redirect url if there are no errors")




;; (deftest it-should-attempt-to-login-using-the-hashed-password
;;   (testing "adsfda"
;;     (is (=  1 2))))

;; (deftest it-should-return-an-error-if-the-login-attempt-fails
;;   (testing "" ()))

;; (deftest it-should-
;; ;;(run-tests 'complete.example.when-logging-in-a-user)



