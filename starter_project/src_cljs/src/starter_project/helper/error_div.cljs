(ns src.starter-project.helper.error-div
	(:require 
		[goog.dom]
   	[goog.dom.classes	:as classes]))

(defn set-the-error-class [divToChange success]
		(do
    	(if success
        (classes/addRemove divToChange "error" "info")
        (classes/addRemove divToChange "info" "error")))
        divToChange)