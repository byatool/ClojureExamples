(ns complete.macro.unit-test
  (:use
   clojure.test
   [crypto.random  :only (base64)]
   [clojure.string :only (join split)]))


;;  Support Methods

(defn create-symbol-from-string [text]
  (symbol (join "-" (split text #" "))))


;;  clojure.test amendments

;; (is (not-thrown? c expr))
(defmethod assert-expr 'not-thrown? [message form]
  "This is merely a reverse of the thrown? functionality in clojure.test."
  (let [klass (second form)
        body (nthnext form 2)]
    `(try ~@body
          (do-report {:type :pass, :message ~message,
                      :expected '~form, :actual nil})
          (catch ~klass e#
            (do-report {:type :fail, :message ~message,
                        :expected '~form, :actual e#})
            e#))))


(defmacro it-should [description & rest]
  "Overall this creates one test function to be called using clojure.test.
   It takes in a text description which it creates a function name from.
   It also adds a 'do' block to run whatever is in 'rest'."
  `(deftest ~(create-symbol-from-string description)
     (testing ~(apply str "it should " description)
       (do ~@rest ))))



;; (it-should-try "to create a result"
;;                create-a-result-mock
;;                #(= %1 {:username test-username :password test-password}))
(defmacro it-should-try [test-name test-it check]
  "This is used to shadow the method by injecting an if clause
  which will set a variable to true if the 'check' returns true."
  `(deftest ~(create-symbol-from-string test-name)
     (testing ~(apply str "it should attempt " test-name)
       (do
         (def  ~(symbol "was-called") (atom false))
         (binding [~(symbol (str test-it))
                   ~(list
                     (nth check 0)
                     (nth check 1)
                     (list
                      'if
                      (nth check 2)
                      `(swap! ~(symbol "was-called") (fn [~(symbol "a")] true))
                      nil))]
           (~(symbol "call-the-method"))
           (is (= @~(symbol "was-called") true)))))))



(defmacro it-should-call [& rest]
  (let [[description method-name preceding-method called] rest]
    `(deftest ~(create-symbol-from-string description)
       (testing ~(apply str "it should call " description)
         (do
           (def  ~(symbol "was-called") (atom false))
           (binding [~(symbol (str method-name))
                     #(if (= % (~(symbol (str preceding-method)) nil))
                        (swap! ~(symbol "was-called") (fn [~(symbol "afdsdfsfds")] true))
                        nil)]
             (~(symbol "call-the-method")))
           (is (= @~(symbol "was-called") ~(nil? called))))))))



(defmacro chain-method! [name]
  (let [name-as-string (str name)]
    "Used to create mock chain method.  Chain methods are simple in that they take
    in a value, and return a value.  The method name is used to create a return that
    is unique to the method itself, but not for each call."
    `(def ~(with-meta name {:dynamic true}) ;;~(symbol name-as-string)
       (fn [~(symbol (str (gensym)))]
         ~(str name-as-string)))))


;; (string! username)
;; (def username (crypto.random/base64 10))
(defmacro string! [name]
  "This will declare a var, and set it to a random string."
  `(def ~name (crypto.random/base64 10)))


(defmacro *string* [name]
  `(def ~(with-meta name {:dynamic true})
     (crypto.random/base64 10)))

;;(defg some-method [a b] nil)
;;(defn ^:dynamic some-method [a b] nil)
(defmacro defd [name & rest]
  "This is used to attach the dynamic tag to a method without
   haveing to add the dynamic tag manually."
  `(defn ~(with-meta name {:dynamic true})
     ~@rest))


;; (number! user-id
;; (def user-id (rand-int 10))
(defmacro number! [name]
  `(def ~name  (rand-int 10)))



