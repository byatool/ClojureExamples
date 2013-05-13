(ns complete.example.communicator.user-communicator.just-testing
  (:use
   clojure.test
   complete.macro.unit-test))




(string! to-go)

(defd do-something-mock [a]
  a)

(defn real-method [a ?do-something]
  (?do-something a)
  false)

(defn call-the-method []
  (real-method to-go do-something-mock))

(it-should-be "calling things correctly"
               do-something-mock
               #(= % to-go))
