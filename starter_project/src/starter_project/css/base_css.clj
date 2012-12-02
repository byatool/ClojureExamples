(ns starter-project.css.base-css
	(require [gaka.core :as gaka]))


(def alerts 
	[:.alert	
		[:.error 	{:color "red" 	:background-color "gray"}]
		[:.info		{:color "white"	:background-color "green"}]])


(gaka/save-css "resources/public/css/application.css" alerts)