(ns complete.model.message.when-adding-an-error-message
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.model.message :only (create-a-result add-an-error-message)]))

;; Fields
(string! error-message)

(def message-item [])
(def result (create-a-result))

(def ^:dynamic add-message-mock (fn [a b] result))
(def ^:dynamic create-a-message-item-mock (fn [a b] message-item))



(defn call-the-method []
  (add-an-error-message result error-message create-a-message-item-mock add-message-mock))


;; Tests
(it-should-attempt "to create a message item"
                   create-a-message-item-mock
                   #(if (and
                         (= %1 error-message)
                         (= %2 true))
                      (it-was-called!)
                      nil))

(it-should-attempt "to add the message to the result"
                   add-message-mock
                   #(if (and
                         (= %1 result)
                         (= %2 message-item))
                      (it-was-called!)
                      nil))

(it-should "return the result"
           (is (= (call-the-method) result)))
