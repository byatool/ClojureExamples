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
  (master-page [:h1 "hadofhado"]))

