(ns src.starter-project.helper.event
	(:require 
		[goog.dom]
		[goog.events :as events]))

(defn ^:export set-click [element method]
	(events/listen element goog.events.EventType.CLICK method))