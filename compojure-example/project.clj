(defproject compojure-example "0.0.1"
  :description "REST datastore interface."
  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [ring/ring-core "1.1.8"]
   [ring-json-params "0.1.0"]
   [compojure "1.1.1"]
   [clj-json "0.5.3"]
   [hiccup "1.0.0"]
   [lib-noir "0.5.0"]]
  :plugins [[lein-ring "0.7.1"]]
  :ring {:handler compojure.example.routes/app})
