(ns complete.query.user-query
  (:use
   [lobos.entity :only (users)]
   (korma core db config)
   [complete.model.message :only (add-an-error-message create-a-result)]))

(defn retrieve-user-id-by-login [username password]
  (let [query-result
        (select users
                (where {:name username :password password})
                (fields :id))]
    (if (empty? query-result)
      (add-an-error-message (create-a-result) "User doesn't exist.")
      (create-a-result (first query-result)))))
