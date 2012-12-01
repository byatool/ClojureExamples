(ns starter-project-korma.entity
	(:use 	
		[lobos.config :as config]
		(korma core db)))

(defdb test-use config/db)

(defentity users
	(pk :id)
	(table :authors)
	(entity-fields 
		:login 
		:pen_name 
		:first_name 
		:last_name 
		:created_on 
		:updated_on)
	(database test-use))