(defproject http-kit-example "0.0.1"
  :description "REST datastore interface."
  :dependencies [[http-kit "2.0.1"]
                 [compojure "1.1.1"]
                 [hiccup "1.0.0"]]
  :plugins [[lein-ring "0.7.1"]]
  :ring {:handler http-kit-example.routes/app})
