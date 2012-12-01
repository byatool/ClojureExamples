(ns src.starter-project.helper.parse
	(:require	
		[goog.dom :as dom]
		[clojure.string :as string]))

;Request to map method

(defn ^:export to-map [input]
	(if (empty? input)
		{}
		(start-the-split input)))

(defn create-the-key [input]
  (keyword (first (split-by-equal input))))


(defn create-the-value [input]
  (second (split-by-equal input)))


(defn split-by-equal [input]
  (clojure.string/split input #"="))


(defn start-the-split [requestString]
	(into{}
		(map #(assoc {} (create-the-key %) (create-the-value %)) (clojure.string/split requestString #"&"))))

;End Request to map method
