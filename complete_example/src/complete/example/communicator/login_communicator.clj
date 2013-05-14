(ns complete.example.communicator.login-communicator
  (:use
   [noir.cookies :only (put!)]
   [complete.model.message :only (create-a-result retrieve-value set-result-value)]
   [complete.validation.user-validation :only (validate-password validate-username)]
   [complete.utility.text-transform :only (hash-text)]
   [complete.macro.site-macro :only (if-success)]))


(defn hash-user-id
  "This will hash the user id if the result's success flag is true.
  Otherwise the result is merely passed through"
  ([result]
     (hash-user-id result retrieve-value str hash-text set-result-value))
  ([result ?retrieve-value ?to-string ?hash-text ?set-result-value]
     (if-success
      result
      (?retrieve-value)
      (?to-string)
      (?hash-text)
      (#(?set-result-value result %)))))


(defn handle-cookie
  "This is used to retrieve the user id from a result, hash it,
  and add it to a cookie."
  ([result]
     (handle-cookie result retrieve-value hash-user-id put!))
  ([result ?retrieve-value ?hash-user-id ?cookies-put]
     (-> result
         (?retrieve-value)
         (?hash-user-id)
         (#(?cookies-put :user-id %)))))


(defn create-login-information
  "This will create a result with a map of the username and
  password as the value."
  ([username password]
     (create-login-information username password create-a-result))
  ([username password ?create-a-result]
     (->
      {:username username :password password}
      (#(?create-a-result %)))))


(defn validate-login
  "This is used to validate the login information attached to the
  result."
  ([result]
     (validate-login result validate-username validate-password))
  ([result ?validate-username ?validate-password]
     (->
      result
      (?validate-username)
      (?validate-password))))


(defn hash-the-password
  "This is used to take the entered password, then hash it for comparing it to the
  stored/hashed password."
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
