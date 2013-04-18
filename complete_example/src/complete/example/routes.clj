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
   
  (route/resources "/")
  (route/not-found "Page not found"))


(def app
  (-> (handler/site main-routes)
      (wrap-base-url)
      (wrap-json-params)
      (cookies/wrap-noir-cookies)))

