(ns complete.model.message.when-checking-if-a-result-has-errors
  (:use
   clojure.test
   complete.macro.unit-test
   complete.model.message))

(it-should "return false if there are no messages"
           (is (= false (contains-any-messages (create-a-result)))))

(it-should "return false if there are no messages"
           (let [result-with-a-message
                 (add-message
                  (create-a-result)
                  (create-a-message-item "" true))]
             (is (= true (contains-any-messages result-with-a-message)))))

;;TODO This doesn't check if the message items are actually errors
