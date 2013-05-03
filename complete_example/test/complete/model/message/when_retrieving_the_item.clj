(ns complete.model.message.when-retrieving-the-item
  (:use
   clojure.test
   complete.macro.unit-test
   complete.model.message))

(def item-value {:hi "there"})
(def result (set-result-value (create-a-result) item-value))

(it-should "return the item on the result"
           (is (= item-value (retrieve-value result))))
