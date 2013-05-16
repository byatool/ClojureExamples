(ns complete.query.user-query.when-logging-in-a-user
  (:use
   clojure.test
   complete.macro.unit-test
   [lobos.entity :only (users)]
   (korma core db config)
   [complete.query.user-query :only (user-login-is-correct)]))


;;Fields

(string! username)
(string! password)


;; Test Functions



(deftest it-should-find-the-user
  (do 
    (insert users (values {:name username :password password}))
    (is (= true (user-login-is-correct username password)))
    (delete users (where {:name [= username]}))))

