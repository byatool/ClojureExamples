(ns src.starter-project.helper.send-post
	(:require	[goog.dom :as dom]
						[goog.dom.forms]
						[goog.json]
						[goog.net.XhrIo]
            [src.starter-project.helper.conversion :as convert]))


(defn get-form-action [formName currentDom]
	(let [form (currentDom/getElement formName)]
	form/action))


; .Net and Noir
(defn as-async [formName callback]
  (let [action 		(get-form-action formName goog.dom)
        formData 	(.toObject (.getFormDataMap goog.dom.forms (dom/getElement formName)))
	    content     (convert/make-js-map {:Content-Type "application/json"})]
        (.send goog.net.XhrIo action callback  "POST" (.serialize goog.json formData) content))) 