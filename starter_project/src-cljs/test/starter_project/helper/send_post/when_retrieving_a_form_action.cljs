(ns test.starter-project.helper.send-post
	(:require	
   	[goog.dom 															:as dom] 
		[src.starter-project.helper.send-post 				:as send]
		[src.starter-project.helper.web-template 		:as template]
		[src.starter-project.helper.conversion				:as conversion]
		[test.starter-project.tools.jasmine 					:as test]
		[test.starter-project.tools.jasmine.methods 	:as is]))

;"Constants"

(def neededAction "something")
(def noActionFormName "noAction")
(def actionFormName "action")


;Instance

(def actionLessForm 
	(dom/htmlToDocumentFragment
		(template/transform-this 
			[:form {:id noActionFormName}])))

(def actionForm 
	(dom/htmlToDocumentFragment
		(template/transform-this 
			[:form {:id actionFormName :action neededAction}])))


;Helper methods

(defn add-to-body [element currentDom]
	(do
		(currentDom/appendChild (.-body js/document) element)
		currentDom))

(defn remove-if-exists [elementName parent currentDom]
	(let [possibleElement (goog.dom/getElement noActionFormName)]
		(if possibleElement
			(.removeChild parent possibleElement)
			nil)))


;Test Suite

(js/describe "When retrieving the action of a form"
  (fn []
		(js/beforeEach 
			(fn []
				(do 
					(remove-if-exists noActionFormName (.-body js/document) goog.dom)
					(remove-if-exists actionForm (.-body js/document) goog.dom))))
		(js/it "can handle there being no action."
			(fn []
        (test/that (send/get-form-action noActionFormName (add-to-body actionLessForm goog.dom)) is/equal-to "")))
	  (js/it "can handle find the action."
				(fn []
	        (test/that (send/get-form-action actionFormName (add-to-body actionForm goog.dom)) is/equal-to "file:///C:/Development/Clojure/first/src-cljs/test/view/something")))
	)
)