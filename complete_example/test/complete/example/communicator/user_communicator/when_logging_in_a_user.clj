(ns complete.example.communicator.user-communicator.when-logging-in-a-user
  (:use
   clojure.test
   complete.macro.unit-test
   complete.example.communicator.user-communicator))


;; Fields

(string! test-username)
(string! test-password)
(string! hashed-password)
(string! login-result)

;; Support Methods

(def user-id -1)

(defn swap-filler [input] true)

(def ^:dynamic find-user-by-credentials-mock
  (fn [a b]
    {:Messages [] :Success true :RedirectUrl "" :Item user-id}))

(def ^:dynamic hash-text-mock
  (fn [a]
    hashed-password))

(def ^:dynamic handle-cookie-mock
  (fn [user-id]
    ()))


(defn call-the-method []
  (login-user test-username test-password hash-text-mock find-user-by-credentials-mock handle-cookie-mock))


;; Test Methods

;;method-name 
(it-should-attempt "to hash the password"
                   hash-text-mock
                   #(if (= % test-password)
                      (swap! was-called swap-filler)
                      nil))


(it-should-attempt "to login the user"
                   find-user-by-credentials-mock
                   #(if (and
                         (= %1 test-username)
                         (= %2 hashed-password))
                      (swap! was-called swap-filler)
                      nil))

(it-should "return any errors"
           (binding [find-user-by-credentials-mock
                     (fn [username password]
                       {:messages [""]})]
             (is (= 1 (count (:messages (call-the-method)))))))


(it-should-attempt "to not set the cookie if there are errors"
                   handle-cookie-mock
                   #((swap! was-called swap-filler))
                   false)


;; (it-should-attempt "set the cookie if the are no errors"
;;                    handle-cookie-mock


;;(it-should "set the url if 
;; (it-should "hash the password"
;;            (def was-called false)
;;            (binding [hash-text
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



