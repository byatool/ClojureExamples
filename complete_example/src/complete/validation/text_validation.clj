(ns complete.validation.text-validation
  (:use [clojure.string :only (join)]))


(defn text-is-empty [to-check prepend-name]
  (if (empty? to-check)
    (join " " [prepend-name "is required"] )
    nil))
