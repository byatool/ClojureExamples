(ns complete.validation.user-validation
  (:use [complete.validation.text-validation :only (text-is-empty)]
        [complete.model.message :only (retrieve-value add-an-error-message)]))

;; create-a-message-item add-message
(defn validate-username
  ([result]
     (validate-username retrieve-value text-is-empty add-an-error-message))
  ([result ?retrieve-value ?text-is-empty ?add-an-error-message]
     (let [username (:username (?retrieve-value result))]
       (?add-an-error-message result (?text-is-empty username "Username")))))

;;(result text-is-empty add-message)
;;(:username (:value result)) "username"
;;user name
;;text-is-empty
;;passwaord





