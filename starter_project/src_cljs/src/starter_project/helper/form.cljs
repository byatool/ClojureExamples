(ns src.starter-project.helper.form
	(:require	
		[goog.dom 		:as dom]
    [goog.array 	:as array]))

(defn retrieve-child-by-id [id parent]
    (first (filter #(= (.-id %) id) (array/toArray (dom/getChildren parent)))))