(ns compojure.example.routes
  (:use compojure.core
        ring.adapter.jetty
        ring.middleware.json-params
        [hiccup.middleware :only (wrap-base-url)]
        compojure.example.views)
  (:require [clj-json.core :as json]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))


(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})


(defroutes main-routes
  ;;curl -X GET -H "Content-Type: application/json" http://localhost:3000
  (GET "/" [] (index-page))
  ;;curl -X POST -H "Content-Type: application/json" http://localhost:3000?id=1
  (POST "/" [id name] (json-response {"hello" id "name" name}))
  ;;curl -X POST -H "Content-Type: application/json" "http://localhost:3000/something/1?id=2&name=3"
  (POST "/something/:test" [test id name] (json-response {"test" test  "hello" id "name" name}))
  (route/resources "/")
  (route/not-found "Page not found"))


(def app
  (-> (handler/site main-routes)
      (wrap-base-url)
      (wrap-json-params)))
