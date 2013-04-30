(ns complete.example.communicator.user-communicator
  (:use complete.model.message))

(defn hash-text [password] nil)
(defn find-user-by-credentials [username password] nil)
(defn handle-cookie [user-id]) ;;?hash-text ?set-cookie


;;validate the login

(defn login-user
  ([username password]
     (login-user username password hash-text find-user-by-credentials contains-any-messages handle-cookie))
  ([username password ?hash-text ?find-user-by-credentials ?contains-any-messages ?handle-cookie]
     (let [hashed-password (?hash-text password)]
       (let [possible-login-result (?find-user-by-credentials username hashed-password)]
         (if (?contains-any-messages possible-login-result)
           possible-login-result
           (do (?handle-cookie (:Item possible-login-result))
               possible-login-result))))))


(defn create-login-information [username password]
  nil)
(defn validate-login [result]
  nil)
(defn hash-the-password [result]
  nil)
(defn login-the-user [result]
  nil)

(defn login-a-user
  ([username password]
     (username password create-login-information validate-login hash-the-password login-the-user))
  ([username password ?create-login-information ?validate-login ?hash-the-password ?login-the-user]
     (-> (?create-login-information username password)
         (?validate-login)
         (?hash-the-password)
         (?login-the-user))))


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
