(ns complete.model.message.when-adding-a-message-to-a-result
  (:use
   clojure.test
   complete.macro.unit-test
   complete.model.message))

(string! new-message)
(def ^:dynamic error-message (create-a-message-item new-message true))


(def result (create-a-result))

(defn call-the-method []
  (add-message result error-message))

(it-should "add the new message to the result"
           (is (= 1 (count ((call-the-method) :Messages)))))

(it-should "not change the original result"
           (call-the-method)
           (is (= 0 (count (result :Messages)))))

(it-should "not add an empty mesasge"
           (binding [error-message (create-a-message-item nil true)]
             (is (= 0 (count ((call-the-method) :Messages))))))

(it-should "update the success flag if an error is added"
           (is (not ((call-the-method) :Success))))

(it-should "retain the success flag if a non error is added"
           (binding [error-message (create-a-message-item new-message false)]
             (is ((call-the-method) :Success))))

(it-should "set the item to nil on error"
           (binding [error-message (create-a-message-item new-message false)]
             (is (nil? ((call-the-method) :Item)))))
