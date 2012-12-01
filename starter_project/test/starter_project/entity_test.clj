(ns starter-project.entity-test
  (:require 
  	[clojure.string :as string])
	(:use 
    (korma core db config)
    (lobos config helpers migrations)
    [clojure.test :only (deftest is)]
    [starter-project-korma.entity :only (users)]))

(deftest some-test
	(do 
		(insert users (values {:login "john"}))
		(is (empty? (select users)))
    (delete users (where {:login [= "john"]})))) 