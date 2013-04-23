(ns complete.example.communicator.user-communicator)

(defn hash-password [password] nil)
(defn find-user-by-credentials [username password] nil)

(defn login-user
  ([username password]
     (login-user username password hash-password find-user-by-credentials))
  ([username password ?hash-password ?find-user-by-credentials]
     (do
       (let [hashed-password (?hash-password password)]
         (let [result (?find-user-by-credentials username hashed-password)]
           result)))))


