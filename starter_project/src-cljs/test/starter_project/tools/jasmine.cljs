(ns test.starter-project.tools.jasmine)

	(defn that [leftValue method rightValue]
		(method leftValue rightValue))

	(defn the-truth-of [value]
		(.toBe (js/expect value) true))

	(defn the-falsity-of [value]
		(.toBe (js/expect value) false))


(ns test.starter-project.tools.jasmine.methods)

	(defn equal-to [leftValue rightValue]
		(.toBe (js/expect leftValue) rightValue))


	(defn equivalent-to [mapOne mapTwo]
		"This compares the list of key/value pairs from one map to the other. 
			If there is a missing key or the values don't match -> failure."
		(.toBe 
			(js/expect (reduce = (map #(= (% mapOne) (% mapTwo)) (keys mapTwo)))) 
			true))