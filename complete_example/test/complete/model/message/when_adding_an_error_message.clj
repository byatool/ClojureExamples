(ns complete.model.message.when-adding-an-error-message
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.model.message :only (create-a-result add-an-error-message)]))


;; Fields

(string! error-message)
(def message-item [])
(def result (create-a-result))


;; Mock Functions

(defd add-message-mock [a b] result)
(defd create-a-message-item-mock [a b] message-item)


;; Support Functions

(defn call-the-method []
  (add-an-error-message result error-message create-a-message-item-mock add-message-mock))


;; Test Functions

(it-should-try "to create a message item"
                   create-a-message-item-mock
                   #(and
                     (= %1 error-message)
                     (= %2 true)))

(it-should-try "to add the message to the result"
                   add-message-mock
                   #(and
                     (= %1 result)
                     (= %2 message-item)))

(it-should "return the result"
           (is (= (call-the-method) result)))
