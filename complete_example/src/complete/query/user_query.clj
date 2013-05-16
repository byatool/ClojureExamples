(ns complete.query.user-query
  (:use
   [lobos.entity :only (users)]
   (korma core db config)))

(defn user-login-is-correct [username password]
  (not (empty? (select users (where {:name username :password password})))))
