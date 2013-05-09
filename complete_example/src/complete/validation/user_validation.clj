(ns complete.validation.user-validation
  (:use [complete.validation.text-validation :only (text-is-empty)]
        [complete.model.message :only (retrieve-value add-an-error-message)]))


;; (defn validate-username
;;   ([result]
;;      (validate-username retrieve-value text-is-empty add-an-error-message))
;;   ([result ?retrieve-value ?text-is-empty ?add-an-error-message]
;;      (let [username (:username (?retrieve-value result))]
;;        (let [possible-error-message (?text-is-empty username "Username")]
;;          (?add-an-error-message result possible-error-message)))))







(defn validate-username
  ([result]
     (validate-username retrieve-value text-is-empty add-an-error-message))
  ([result ?retrieve-value ?text-is-empty ?add-an-error-message]
     (->
      (#(:username (?retrieve-value result)))
      (#(?text-is-empty % "Username"))
      (#(?add-an-error-message result %)))))






