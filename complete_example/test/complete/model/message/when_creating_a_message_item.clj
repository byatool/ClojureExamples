(ns complete.model.message.when-creating-a-message-item
  (:use
   clojure.test
   complete.macro.unit-test
   complete.model.message))

(string! new-message)
(def ^:dynamic error false)

;; (defn call-the-method []
;;   (add-message result new-message))

(defn call-the-method []
  (create-a-message-item new-message error))

(it-should "set the message"
           (is (= new-message ((call-the-method) :Message))))

(it-should "set the error flag"
           (binding [error true]
             (is ((call-the-method) :Error))))
