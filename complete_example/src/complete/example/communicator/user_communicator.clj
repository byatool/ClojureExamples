(ns complete.example.communicator.user-communicator
  (:use complete.model.message
        complete.validation.user-validation))

(defn hash-text [password] nil)
(defn find-user-by-credentials [username password] nil)
(defn handle-cookie [user-id]) ;;?hash-text ?set-cookie

(defn validate-password [result] result)



(defn create-login-information
  ([username password]
     (create-login-information username password create-a-result))
  ([username password ?create-a-result]
     (?create-a-result {:username username :password password})))


;;add error
(defn validate-login
  ([result]
     (validate-login result validate-username validate-password))
  ([result ?validate-username ?validate-password]
     (->
      (?validate-username result)
      (?validate-password))))



(defn hash-the-password [result] ;;hash text
  ;;?md5
  nil)

(defn login-the-user [result]
  nil)

(defn set-the-cookie [result]
  ;;(md5 (Integer/toString user-id) "test")
  ;;cookies/put!
  ;;
  nil)

(defn login-a-user
  ([username password]
     (username password create-login-information validate-login hash-the-password login-the-user set-the-cookie))
  ([username password ?create-login-information ?validate-login ?hash-the-password ?login-the-user ?set-the-cookie]
     (-> (?create-login-information username password)
         (?validate-login)
         (?hash-the-password)
         (?login-the-user)
         (?set-the-cookie))))


;;  (defn login [username password ?validate-login ?hash-password ?login-user]
;;      (create-login-information username password)
;;      (validate-login)
;;      (hash-password)
;;      (login-user)
;;      (set-cookie
;;         (hash-word)
;;         (set!))
;;      (return-result))
;;
;;
;;
;;  (defn create-login-information [username password]
;;    (set-value (create-result {:Username username :Password password)))
;;
;;  (defn validate-login [information result]
;;    (if (not (:Success result))
;;      result
;;    (->
;;      (validate-username result)
;;      (validate-password result)))

;;         (defn validate-username [result]
;;            (if (not (contains? :username (:Item result)))
;;              error result
;;              result
;;                   
;;
;;  (defn hash-password [result ?hash-text]
;;    (if (not (:Success result))
;;      result
;;      (->
;;        (hash-text (:Item result :Password))
