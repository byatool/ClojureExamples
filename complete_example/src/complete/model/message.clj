(ns complete.model.message)


;;  Junk Methods for Readability
(defn- and-the [])
(defn- with-the [])


;; Private Methods

(defn- create-new-message-list-for-the [result a message-item]
  "Used to create a new list based on the result's list, and the new message item."
  (into (result :Messages) [message-item]))

(defn- determine-success-based-on-current-success-of-the [result a message-item]
  "Success is based on the current result, and if the message-item is an error."
  (and (result :Success) (not (message-item :Error))))


;; Public Methods

(defn add-message [result message-item]
  (let [new-result (assoc result :Messages (create-new-message-list-for-the result with-the message-item))]
    (assoc new-result :Success (determine-success-based-on-current-success-of-the result and-the message-item))))


(defn contains-any-messages [result]
  (< 0 (count (:Messages result))))


(defn create-a-message-item [message error]
  {:Message message
   :Error error})


(defn create-a-result []
  {:Messages []
   :Success true
   :RedirectUrl ""
   :Item nil})


(defn set-result-value [result value]
  (if (result :Success)
    (assoc result :Item value)
    result))
