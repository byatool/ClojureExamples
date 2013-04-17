(ns complete.example.views
  (:use [hiccup core page]))

(defn master-page [to-inject]
  (html5
   [:head
    [:title "overall title"
     (include-css "/css/final.css")]]
   [:body
    to-inject]))


(defn index-page []
  (master-page [:h1 "Welcome"]))

(defn login-page []
  (master-page
   [:div
    [:input {:type "text" :id "username"}]
    [:input {:type "text" :id "password"}]]))
    
;;(-> index-page [:h1 "login"])


