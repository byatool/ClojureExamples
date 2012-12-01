(ns test.starter-project.helper.event
	(:require	
		[goog.dom 															:as dom]
    [goog.style                 						:as style]
		[test.starter-project.tools.jasmine 					:as test]
		[test.starter-project.tools.jasmine.methods 	:as is]
		[src.starter-project.helper.web-template 		:as template]
		[src.starter-project.helper.event 						:as event]))


(def buttonToTest
	(dom/htmlToDocumentFragment
		(template/transform-this
			[:button {:id "parent" :type "submit"}])))


(def divToChange
	(dom/htmlToDocumentFragment
		(template/transform-this
			[:div {:id "subject" }])))


(js/describe "When setting the click event for an element"
	(fn []
		
		(js/beforeEach
			#(do 
				(event/set-click buttonToTest (fn[] (style/showElement divToChange true)))
				(style/showElement divToChange false)))

		(js/it "will not change the visibilty if not clicked."
			#(test/the-falsity-of (style/isElementShown divToChange)))

		(js/it "will show a div when clicked."
			#(do 
				(.click buttonToTest)
				(test/the-truth-of (style/isElementShown divToChange))))
	))