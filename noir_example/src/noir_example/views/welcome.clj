(ns noir_example.views.welcome
  (:require [noir_example.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to noir_example"]))

(defpage "/submit" {}
	(let [buttonName "someButton"]
	(common/layout
 		[:input {:type "button" :id buttonName :name buttonName :value "gogo"}]
		[:script (format "src_cljs.src.noir_example.some_page.set_button('%s');" buttonName)])))