(ns complete.example.when-logging-in-a-user
  (:use clojure.test
        [clojure.string :only (join split)]
        complete.example.routes))


;;attempt to login using username and password
;;  if failure, return errors
;;  if success s


(defn create-symbol-from-string [text]
  (symbol (join "-" (split text #" "))))

(defmacro it-should [description body]
  `(deftest ~(create-symbol-from-string description)
     (testing ~(apply str "it should " description)
       ~body)))

(it-should "be equal to 1"
           (is (= 1 2)))


;; (deftest it-should4-hash-the-password
;;   (testing "" ()))

;; (deftest it-should-attempt-to-login-using-the-hashed-password
;;   (testing "adsfda"
;;     (is (=  1 2))))

;; (deftest it-should-return-an-error-if-the-login-attempt-fails
;;   (testing "" ()))

;; (deftest it-should-
;; ;;(run-tests 'complete.example.when-logging-in-a-user)



