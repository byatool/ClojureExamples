(ns complete.example.routes
  (:use compojure.core
        ring.adapter.jetty
        ring.middleware.json-params
        [hiccup.middleware :only (wrap-base-url)]
        complete.example.views
        [complete.example.communicator :only (login-user)]
        [noir.util.crypt :only (md5)])
  (:require [clj-json.core :as json]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [noir.cookies :as cookies]))



(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

;;(parameter-symbol->string '(index username ?password))
(defn parameter-symbol->string [items]
  (cond
   (empty? items) ""
   :else (split-with #(not (= \? (first %))) (map str items))))

;;(convert-to-string '(aaa bbb ccc) false)
(defn convert-to-string [items keep-last-slash]
  (let [raw (apply str (map #(apply str ":" % "/") items))]
    (if keep-last-slash
      raw
      (apply str (butlast raw)))))

;;(remove-question-mark "some?")
(defn remove-question-mark [text]
  (apply str (remove #(= \? %) text)))

;;(string-to-symbol '("aaa" "bbb" "ccc")
(defn string-to-symbol [items]
  (map #(symbol(remove-question-mark %)) items))

;;(macroexpand-1 '(-|| index  ("adfa")))
;;(macroexpand-1 '(-|| index user ("adfa")))
;;(macroexpand-1 '(-|| index user ?test ?me ("adfa")))
(defmacro -|| [& items]
  (let [method-name (first items)
        body (last items)
        [main-parameters query-string-parameters](parameter-symbol->string(butlast (rest items)))]
    `(POST ~(apply str ["/" method-name "/" (convert-to-string main-parameters (empty? query-string-parameters))])
           [~@(string-to-symbol main-parameters) ~@(string-to-symbol query-string-parameters)]
           ~body)))


(defroutes main-routes
  ;;curl -X GET -H "Content-Type: application/json" http://localhost:3000
  (GET "/" []
       (index-page))
  
  (GET "/login" []
       (login-page))
  
  ;;curl -X POST -H "Content-Type: application/json" "http://localhost:3000/login/sean?password=hihi"
  (POST "/login/:username" [username password]
        (let [user-id (login-user username password)]
          (do 
            (cookies/put! :user-id (md5 (Integer/toString user-id) "test"))
            (json-response {"your hashed id is:" (cookies/get :user-id)}))))

  ;;(POST "/index/:userid" [userid username]
  ;;  (json-response {"user id: " userid " and username: " username }))
  (-|| info userid ?username
       (json-response {"user id: " userid " and username: " username }))
  
  (route/resources "/")
  (route/not-found "Page not found"))


(def app
  (-> (handler/site main-routes)
      (wrap-base-url)
      (wrap-json-params)
      (cookies/wrap-noir-cookies)))

