(ns complete.validation.user-validation
  (:use [complete.validation.text-validation :only (text-is-empty)]
        [complete.model.message :only (retrieve-value add-an-error-message)]))

(defn validate-username
  ([result]
     (validate-username retrieve-value text-is-empty add-an-error-message))
  ([result ?retrieve-value ?text-is-empty ?add-an-error-message]
     (->
      (?retrieve-value result)
      (#(:username %))
      (#(?text-is-empty % "Username"))
      (#(?add-an-error-message result %)))))






