(ns complete.model.message.when-creating-a-result
  (:use
   clojure.test
   complete.macro.unit-test
   complete.model.message))

(def result (create-a-result))

;;it should 
(it-should "contain an empty list for messages"
           (let [messages (result :Messages)]
             (is (= 0 (count messages)))))

(it-should "have a success flag set to true"
           (is (result :Success)))

(it-should "contain an empty redirect url"
           (is (empty? (result :RedirectUrl))))

(it-should "contain an empty item"
           (is (and
                (contains? result :Item)
                (nil? (result :Item)))))


