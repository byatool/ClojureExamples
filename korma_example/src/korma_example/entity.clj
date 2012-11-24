(ns korma-example.entity
	(:use 	
		[lobos.config :as config]
		(korma core db)))


(defdb test-use config/db)


(defentity users
	(pk :id)
	(table :users)
	(entity-fields :name :created_on :updated_on)
	(database test-use))


(defentity posts
	(pk	:id)
	(table :posts)
	(entity-fields :id :title :content :created_on :updated_on) 
	(database test-use)
	(belongs-to users))
