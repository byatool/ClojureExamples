(ns complete.example.communicator.user-communicator.when-creating-login-information
  (:use
   clojure.test
   complete.macro.unit-test
   complete.example.communicator.user-communicator
   complete.model.message))

(string! test-username)
(string! test-password)
(string! result)

;; Support Methods
(def ^:dynamic create-a-result-mock
  (fn [a]
    result))

(defn call-the-method []
  (create-login-information test-username test-password create-a-result-mock))


;; Test Methods
(it-should-attempt "to create a result"
                   create-a-result-mock
                   #(if (= %1 {:username test-username :password test-password}) 
                      (it-was-called!)
                      nil))


(it-should "return a result"
           (is (= (call-the-method) result)))
