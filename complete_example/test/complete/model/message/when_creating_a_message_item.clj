(ns complete.model.message.when-creating-a-message-item
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.model.message :only (create-a-message-item)]))


;; Fields

(string! new-message)
(def ^:dynamic error false)


;; Support Functions

(defn call-the-method []
  (create-a-message-item new-message error))


;; Test Functions

(it-should "set the message"
           (is (= new-message ((call-the-method) :Message))))

(it-should "set the error flag"
           (binding [error true]
             (is ((call-the-method) :Error))))
