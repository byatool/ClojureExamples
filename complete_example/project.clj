(defproject complete-example  "0.0.1"
  :description "Top to bottom site example."
  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [ring/ring-core "1.1.8"]
   [ring-json-params "0.1.0"]
   [compojure "1.1.1"]
   [clj-json "0.5.3"]
   [hiccup "1.0.0"]
   [lib-noir "0.5.0"]
   [korma "0.3.0-RC5"]
   [postgresql "9.1-901.jdbc4"]
   [lobos "1.0.0-SNAPSHOT"]
   [crypto-random "1.1.0"]
   [bouncer "0.2.3-beta1"]
   [ritz/ritz-debugger "0.7.0"]]
  :plugins [[lein-ring "0.7.1"]]
  :ring {:handler complete.example.routes/app})
