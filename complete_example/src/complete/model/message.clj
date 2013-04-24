(ns complete.model.message)


;; Private Methods

(defn- create-new-message-list-with-new-message [result message-item]
  (into (result :Messages) [message-item]))


(defn- determine-success-based-on-current-success-and-the-message [result message-item]
  (and (result :Success) (not (message-item :Error))))


;; Public Methods

(defn add-message [result message-item]
  (let [new-result (assoc result :Messages (create-new-message-list-with-new-message result message-item))]
    (assoc new-result :Success (determine-success-based-on-current-success-and-the-message result message-item))))


(defn create-a-message-item [message error]
  {:Message message
   :Error error})


(defn create-a-result []
  {
   :Messages []
   :Success true
   :RedirectUrl ""
   :Item nil})

