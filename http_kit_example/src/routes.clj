(ns compojure.example.routes
  (:use [compojure.route :only [files not-found]]
        [compojure.handler :only [site]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server
        [hiccup core page]))


(defn index-page []
  (html5
   [:head
    [:title "Hello World"]
    (include-css "/css/style.css")]
   [:body
    [:h1 "Hello World"]]))


(defroutes main-routes
  (GET "/" [] index-page)
  (route/not-found "<p>Page not found.</p>")) ;; all other, return 404


(def app
  (-> (handler/site main-routes)
      (wrap-base-url)))


(run-server (site #'all-routes) {:port 8080})
