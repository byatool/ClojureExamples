(ns complete.example.when-logging-in-a-user
  (:use
   clojure.test
   [clojure.string :only (join split)]
   complete.macro.unit-test
   complete.example.communicator))

;; Fields
(string! test-username)
(string! test-password)


;; Support Methods

(defmacro was-called? []
  `(def  was-called (atom false)))

(defmacro was-called! []
  `(swap! was-called (fn [~(gensym)] true)))


;; Test Methods
(it-should "hash the password"
           (was-called?)
           (let [hash-password
                 #(if (= % test-password)
                    (was-called!)
                    nil)]
             (login-user test-username test-password hash-password))
             (is (= @was-called true)))


;; (deftest it-should-attempt-to-login-using-the-hashed-password
;;   (testing "adsfda"
;;     (is (=  1 2))))

;; (deftest it-should-return-an-error-if-the-login-attempt-fails
;;   (testing "" ()))

;; (deftest it-should-
;; ;;(run-tests 'complete.example.when-logging-in-a-user)



