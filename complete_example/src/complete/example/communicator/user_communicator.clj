(ns complete.example.communicator.user-communicator
  (:use complete.model.message
        [complete.validation.user-validation :only (validate-password validate-username)]
        [complete.utility.text-transform :only (hash-text)]
        [noir.cookies :only (put!)]
        [complete.macro.site-macro :only (if-success)]))


(defn find-user-by-credentials [username password]
  ;;will have to return a reesult with :user-id in the value
  nil)


(defn hash-user-id
  ([result]
     (hash-user-id result retrieve-value hash-text set-result-value))
  ([result ?retrieve-value ?hash-text ?set-result-value]
     (if-success
      result
      (?retrieve-value)
      (?hash-text)
      (#(?set-result-value result %)))))


(defn handle-cookie
  ([result]
     (handle-cookie result retrieve-value hash-user-id put!))
  ([result ?retrieve-value ?hash-user-id ?cookies-put]
     (-> result
         (?retrieve-value)
         (?hash-user-id)
         (#(?cookies-put :user-id %)))))


(defn create-login-information
  ([username password]
     (create-login-information username password create-a-result))
  ([username password ?create-a-result]
     (->
      {:username username :password password}
      (#(?create-a-result %)))))


(defn validate-login
  ([result]
     (validate-login result validate-username validate-password))
  ([result ?validate-username ?validate-password]
     (->
      result
      (?validate-username)
      (?validate-password))))


(defn hash-the-password
  ([result]
     (hash-the-password result retrieve-value hash-text assoc set-result-value))
  ([result ?retrieve-value ?hash-text ?assoc ?set-result-value]
     (if-success
      result
      (?retrieve-value)
      (:password)
      (?hash-text)
      (#(?assoc % :password (?retrieve-value result)))
      (#(?set-result-value result %)))))


(defn login-the-user [result]
  "This will be used to query the database"
  nil)


(defn login-a-user
  ([username password]
     (username password create-login-information validate-login hash-the-password login-the-user handle-cookie))
  ([username password ?create-login-information ?validate-login ?hash-the-password ?login-the-user ?set-the-cookie]
     (-> (?create-login-information username password)
         (?validate-login)
         (?hash-the-password)
         (?login-the-user)
         (?set-the-cookie))))
