(defproject complete-example  "0.0.1"
  :description "Top to bottom site example."
  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [ring/ring-core "1.1.8"]
   [ring-json-params "0.1.0"]
   [compojure "1.1.1"]
   [clj-json "0.5.3"]
   [hiccup "1.0.0"]
   [lib-noir "0.5.0"]]
  :Plugins [[Lein-ring "0.7.1"]]
  :ring {:handler complete.example.routes/app})
