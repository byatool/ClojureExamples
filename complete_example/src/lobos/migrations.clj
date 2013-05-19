(ns lobos.migrations
  (:refer-clojure :exclude [alter drop bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema)
        (lobos config helper)))

;;(use '(lobos connectivity core schema))
;;(use '(lobos config))
;;(use '(lobos helper))
;;(use '(lobos.migrations))
;;(migrate)


(defmigration add-users-table
  (up [] (create
          (tbl :users
            (varchar :name 100 :unique)
            (check :name (> (length :name) 1))
            (varchar :password 100)
            (check :password (> (length :password) 1)))))
  (down [] (drop (table :users))))

;; (defmigration add-posts-table
;;   (up [] (create
;;           (tbl :posts
;;             (varchar :title 200 :unique)
;;             (text :content)
;;             (refer-to :users))))
;;   (down [] (drop (table :posts))))

;; (defmigration add-comments-table
;;   (up [] (create
;;           (tbl :comments
;;             (text :content)
;;             (refer-to :users)
;;             (refer-to :posts))))
;;   (down [] (drop (table :comments))))
