(ns test.starter-project.helper.parse
	(:require	
		[src.starter-project.helper.parse 			:as parser]
		[src.starter-project.helper.conversion 		:as convert]
		[test.starter-project.tools.jasmine 		:as test]
		[test.starter-project.tools.jasmine.methods :as is]))

(js/describe "When parsing a request string"
  (fn []
		(js/it "can handle an empty string"
			(fn []
        (test/that (parser/to-map "") is/equal-to {})))

		(js/it "can handle a single key value pair"
			(fn []
				(test/that 
				 	(parser/to-map "userId=1")
					is/equivalent-to
					{:userId "1"})))

		(js/it "can handle more than one key value pair."
			(fn []
				(test/that 
				 	(parser/to-map "userId=1&userName=hihi")
					is/equivalent-to
					{:userId "1" :userName "hihi"})))
  )
)