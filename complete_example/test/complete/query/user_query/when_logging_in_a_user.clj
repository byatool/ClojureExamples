(ns complete.query.user-query.when-logging-in-a-user
  (:use
   clojure.test
   complete.macro.unit-test
   [lobos.entity :only (users)]
   (korma core db config)
   [complete.query.user-query :only (retrieve-user-id-by-login)]
   [complete.model.message :only (contains-any-messages retrieve-value)]))


;;Fields

(string! username)
(string! password)

(defn user-id []
  (first (select users
                 (where {:name username :password password})
                 (fields :id))))



;; ;; Test Functions
;; (it-should "find the user id."
;;            (insert users (values {:name username :password password}))
;;            (let [result (retrieve-user-id-by-login username password)]
;;              (is (= (retrieve-value result) (user-id))))
;;            (delete users (where {:name [= username]})))


;; (it-should "return an error if there is no user"
;;            (let [result (retrieve-user-id-by-login username password)]
;;              (is (= true (contains-any-messages result)))))

