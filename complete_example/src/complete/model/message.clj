(ns complete.model.message)

(defrecord MessageItem [Message])

(defrecord MessageResult [MessageItems Success RedirectUrl Item])
