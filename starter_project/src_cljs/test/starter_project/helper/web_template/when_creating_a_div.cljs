(ns test.starter-project.helper.web-template
	(:require	
		[src.starter-project.helper.web-template 		:as template]
		[test.starter-project.tools.jasmine 					:as test]
		[test.starter-project.tools.jasmine.methods 	:as is]))

(js/describe "When creating a div"
  (fn []

    (js/it "is an empty div."
      (fn []
        (test/that (template/transform-this [:div]) is/equal-to "<div ></div>")))

    (js/it "has correctly added tags."
  		(fn []
  			(test/that (template/transform-this [:div {:id 1 :class "something"}]) is/equal-to "<div  id=\"1\" class=\"something\"></div>")))

		(js/it "has correctly added children."
  		(fn []
  			(test/that (template/transform-this [:div [:div] [:div]]) is/equal-to "<div ><div ></div><div ></div></div>")))
  )
)

