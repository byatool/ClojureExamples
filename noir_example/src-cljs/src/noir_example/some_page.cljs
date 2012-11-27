(ns src-cljs.src.noir-example.some-page
	(:require 
		[goog.dom :as dom]
		[src-cljs.src.noir-example.event :as event]))

(defn ^:export set-button [button_name]
	(let [buttonToTest (dom/getElement button_name)]
		(event/set-click buttonToTest (fn[] (js/alert "hihi")))))
