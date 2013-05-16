(ns lobos.config
  (:use lobos.connectivity))

(def db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :user "postgres"
   :password "test"
   :subname "//localhost:5432/test_backend"})

(open-global db)
