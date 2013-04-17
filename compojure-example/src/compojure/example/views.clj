(ns compojure.example.views
  (:use [hiccup core page]))

(defn master-page [content]
  (html5
   [:head
    [:title "Hello World"]
    (include-css "/css/style.css")]
   [:body
    [:h1 "This is the master page."]
    content]))

(defn index-page []
  (master-page [:h1 "This is the child page."]))


