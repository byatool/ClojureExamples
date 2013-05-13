(ns complete.example.communicator.user-communicator.when-handling-the-cookie
  (:use
   clojure.test
   complete.macro.unit-test
   [complete.example.communicator.user-communicator :only (handle-cookie)]
   [complete.model.message :only (create-a-result)]))

(string! text)
(string! hashed)
(def result (create-a-result {:user-id text}))

(defd cookies-put-mock [a b] nil)
(defd hash-text-mock [a] hashed)
(defd retrieve-value-mock [a] text)

(defn call-the-method []
  (handle-cookie result retrieve-value-mock hash-text-mock cookies-put-mock))

(it-should-try "to retrieve the value"
               retrieve-value-mock
               #(= % result))

(it-should-try "to hash the text"
               hash-text-mock
               #(= % text))

(it-should-try "to put the hashed text to a cookie"
               cookies-put-mock
               #(and
                 (= %1 :user-id)
                 (= %2 hashed)))
                   ;; #(if
                   ;;    (it-was-called!)
                   ;;    nil))
