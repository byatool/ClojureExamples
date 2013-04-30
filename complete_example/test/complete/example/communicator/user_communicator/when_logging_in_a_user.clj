(ns complete.example.communicator.user-communicator.when-logging-in-a-user
  (:use
   clojure.test
   complete.macro.unit-test
   complete.example.communicator.user-communicator
   complete.model.message))


;; Fields

(string! test-username)
(string! test-password)
(string! hashed-password)
(string! login-result)
(number! test-user-id)

(def ^:dynamic messageless-result (set-result-value (create-a-result) test-user-id))
(def ^:dynamic message-result (add-message (create-a-result) (create-a-message-item "" true)))

;; Support Methods

(defn ^:dynamic contains-any-messages-mock [result]
  false)


(defn ^:dynamic login-with-an-error [username password]
  message-result)


(def ^:dynamic find-user-by-credentials-mock
  (fn [a b]
    messageless-result))


(def ^:dynamic handle-cookie-mock
  (fn [user-id]
    ()))


(def ^:dynamic hash-text-mock
  (fn [a]
    hashed-password))


(defn swap-filler [input]
  true)

;; Generic Call

(defn call-the-method []
  (login-user test-username test-password hash-text-mock find-user-by-credentials-mock contains-any-messages-mock handle-cookie-mock))


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
           (binding [find-user-by-credentials-mock login-with-an-error
                     contains-any-messages-mock (fn [a] true)]
             (is (= true (contains-any-messages (call-the-method))))))


(it-should "not set the cookie if there are errors"
           (binding [contains-any-messages-mock  (fn [a] true)
                     handle-cookie-mock (fn [a] (throw (Exception. "handle-cookie-mock was called.")))]
             (is (not-thrown? Exception (call-the-method)))))


(it-should-attempt "to set the cookie"
                   handle-cookie-mock
                   #(if (= % test-user-id)
                      (swap! was-called swap-filler)
                      nil))

(it-should "return the result after setting the cookie"
           (is (= messageless-result (call-the-method))))
