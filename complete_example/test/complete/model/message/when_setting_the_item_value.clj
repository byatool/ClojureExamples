(ns complete.model.message.when-setting-the-item-value
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.model.message :only (add-message create-a-message-item create-a-result set-result-value)]))


;; Fields

(string! item-value)
(def ^:dynamic result (create-a-result))


;; Support Functions

(defn call-the-method []
  (set-result-value result item-value))


;; Test Functions

(it-should "set the result value"
           (let [changed-result (call-the-method)]
             (is  (= item-value (changed-result :Item)))))

(it-should "not change the original result"
           (let [changed-result (call-the-method)]
             (is (not (= (result :Item) (changed-result :Item))))))

(it-should "not set the value is the result is already unsuccessful"
           (binding [result (add-message result (create-a-message-item "" true))]
             (is (nil? ((call-the-method) :Item)))))
