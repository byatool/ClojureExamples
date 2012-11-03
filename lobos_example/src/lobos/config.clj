(ns lobos.config
  (:use lobos.connectivity))

(def db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   ;This is the default user on installation
   :user "postgres"
   ;This is the password for the above user... duh
   :password "chapsman1"
   ;This is the connection to the database
   ;	This example is using a local instance
   ;	LobosTest is the name of the new database I created for this example
   :subname "//localhost:5432/LobosTest"})

(open-global db)