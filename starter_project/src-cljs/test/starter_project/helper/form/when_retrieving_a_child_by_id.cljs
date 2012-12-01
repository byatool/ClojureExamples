(ns test.starter-project.helper.form
	(:require	
		[goog.dom 															:as dom]
		[test.starter-project.tools.jasmine 					:as test]
		[test.starter-project.tools.jasmine.methods 	:as is]
		[src.starter-project.helper.web-template 		:as template]
		[src.starter-project.helper.form 						:as form]))

(def theIdForTheChildDiv "child" )

(def parentWithAChild 
	(dom/htmlToDocumentFragment
		(template/transform-this
			[:div {:id "parent"}
				[:div {:id theIdForTheChildDiv
				}]])))
 
(def parentWithoutAChild 
	(dom/htmlToDocumentFragment
		(template/transform-this
			[:div {:id "parent"}])))


(js/describe "When retrieving a child by id"
	(fn []
		(js/it "returns null if no child is found."
			#(test/that 
				(form/retrieve-child-by-id theIdForTheChildDiv parentWithoutAChild) 
					is/equal-to 
						nil))

		(js/it "returns the existing child."
			#(test/that 
				(.-id (form/retrieve-child-by-id theIdForTheChildDiv parentWithAChild)) 
					is/equal-to 
						theIdForTheChildDiv))
))
