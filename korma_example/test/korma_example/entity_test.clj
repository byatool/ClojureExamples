(ns korma-example.entity-test
  (:require 
  	[clojure.string :as string])
	(:use 
    (korma core db config)
    (lobos config helpers migrations)
    [clojure.test :only (deftest is)]
    [korma-example.entity :only (users)]))

(deftest some-test
	(do 
		(insert users (values {:name "john"}))
		(is (empty? (select users)))
    (delete users (where {:name [= "john"]})))) 