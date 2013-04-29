(ns complete.example.communicator.user-communicator)

(defn hash-text [password] nil)
(defn find-user-by-credentials [username password] nil)
(defn handle-cookie [user-id]) ;;?hash-text ?set-cookie


(defn login-user
  ([username password]
     (login-user username password hash-text find-user-by-credentials handle-cookie))
  ([username password ?hash-text ?find-user-by-credentials ?handle-cookie]
     (do
       (let [hashed-password (?hash-text password)]
         (let [result (?find-user-by-credentials username hashed-password)]
           result)))))


