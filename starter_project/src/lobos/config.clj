(ns lobos.config
  (:use lobos.connectivity))

(def db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :user "postgres"
   :password "test"
   :subname "//localhost:5432/starter_project"})

;Uncomment this when migrations need to be done
;(open-global db)