(ns complete.example.communicator)

(defn hash-password [password] nil)

(defn login-user
  ([username password] (login-user username password hash-password))
  ([username password ?hash-password]
     (do
       (?hash-password password)
       nil)))
