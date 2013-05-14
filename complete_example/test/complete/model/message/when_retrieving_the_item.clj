(ns complete.model.message.when-retrieving-the-item
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.model.message :only (create-a-result retrieve-value set-result-value)]))


;; Fields

(def item-value {:hi "there"})
(def result (set-result-value (create-a-result) item-value))


;; Test Functions

(it-should "return the item on the result"
           (is (= item-value (retrieve-value result))))
