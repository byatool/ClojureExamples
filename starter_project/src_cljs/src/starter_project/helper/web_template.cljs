(ns src.starter-project.helper.web-template
	(:require	[goog.dom :as dom]
						[goog.string :as string]))

(defn create-attributes-chain [collection]
  (map #(format " %s=\"%s\"" (name %) (% collection)) (keys collection)))


(defn create-attributes [collection]
	(let [cleanCollection (dissoc collection :text)]
		(reduce str (create-attributes-chain cleanCollection))))


(defn ^export transform-this [elements]
  (let [tag (name (first elements))]
  	(format "<%s %s>%s</%s>" 
       tag
       (if (map? (second elements)) (create-attributes (second elements)) "")
       (reduce str (map #(transform-this %) (filter vector? (rest elements))))
       tag)))

(defn get-text [collection]
  	(let [text (get collection :text)]
    	(if text (str text) "")))