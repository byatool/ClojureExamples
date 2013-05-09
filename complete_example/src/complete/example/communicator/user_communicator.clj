(ns complete.example.communicator.user-communicator
  (:use complete.model.message
        [complete.validation.user-validation :only (validate-password validate-username)]
        [complete.utility.text-transform :only (hash-text)]
        [noir.cookies :only (put!)]))


(defn find-user-by-credentials [username password]
  ;;will have to return a reesult with :user-id in the value
  nil)


;; (defn handle-cookie
;;   ([result]
;;      (handle-cookie result hash-user-id put!))
;;   ([result ?hash-user-id ?cookies-put]
;;      (-> result
;;          (?hash-user-id %)
;;          (#(?cookies-put :user-id %)))))


(defn hash-user-id
  ([result]
     (hash-user-id result retrieve-value hash-text set-result-value))
  ([result ?retrieve-value ?hash-text ?set-result-value]
     (if (:Success result)
       (->
        result
        (?retrieve-value)
        (?hash-text)
        (#(?set-result-value result %)))
       result)))


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


(defn hash-the-password [result] ;;hash text
  ;;?md5
  nil)


(defn login-the-user [result]
  "This will be used to query the database"
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
