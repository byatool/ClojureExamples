(ns test.starter-project.helper.error-div
	(:require	
   	[goog.dom 										:as dom] 
   	[goog.dom.classes           	:as classes]
		[src.starter-project.helper.web-template 		:as template]
		[test.starter-project.tools.jasmine 					:as test]
		[test.starter-project.tools.jasmine.methods 	:as is]
		[src.starter-project.helper.error-div 				:as error]))


(def errorDiv 
	(dom/htmlToDocumentFragment
		(template/transform-this 
			[:div {:id "divError" :class "alert"} 
				[:div {:id "divErrorMessage" :class "error"}]
			])))

(def infoDiv 
	(dom/htmlToDocumentFragment
		(template/transform-this 
			[:div {:id "divError" :class "alert"} 
				[:div {:id "divErrorMessage" :class "info"}]
			])))


(js/describe "When setting the class based on success"
  (fn []
    (js/it "is set to error when the success flag is false."
      (fn []
        (test/the-truth-of 
        	(classes/has (error/set-the-error-class infoDiv false) "error"))))
    (js/it "is ste to info when the success flag is true."
      (fn []
        (test/the-truth-of 
        	(classes/has (error/set-the-error-class infoDiv true) "info"))))
  )
)

