(ns lobos.migrations
  (:refer-clojure :exclude [alter drop bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema config helpers)))

(defmigration add-users-table
  (up [] (create
          (tbl :authors
            (varchar  :login      100   :unique)
            (varchar  :pen_name   100) 
            (varchar  :password   100) 
            (varchar  :first_name 100)
            (varchar  :last_name  100))))
  (down [] (drop (table :users))))