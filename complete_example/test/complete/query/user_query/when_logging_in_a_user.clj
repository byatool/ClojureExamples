(ns complete.query.user-query.when-logging-in-a-user
  (:use
   clojure.test
   complete.macro.unit-test
   [lobos.entity :only (users)]
   (korma core db config)
   [crypto.random  :only (base64)]
   [complete.query.user-query :only (retrieve-user-id-by-login)]
   [complete.model.message :only (contains-any-messages retrieve-value)]))


;;Fields

(string! username)
(string! password)

;; Support Functions

(defn contains-error [result error]
  (not (not-any?  #(= error %) (:Messages result))))

(defn create-the-user []
  (insert users (values {:name username :password password})))

(defn delete-the-user []
  (delete users (where {:name [= username]})))

(defn call-the-method []
  (retrieve-user-id-by-login username password))

;; Test Fuctions

(it-should "find the user id"
           (let [user-id (:id (create-the-user))]
             (is (= (retrieve-value (call-the-method)) user-id)))
           (delete-the-user))

(it-should "retun an error if there is no user"
           (is (= true (contains-any-messages (call-the-method)))))

(it-should "have the correct error"
           (is (= false (contains-error (call-the-method) "User doesn't exist."))))
