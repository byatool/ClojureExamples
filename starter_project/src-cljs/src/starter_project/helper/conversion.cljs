(ns src.starter-project.helper.conversion)

(defn ^:export clj->js
  "Recursively transforms ClojureScript maps into Javascript objects,
   other ClojureScript colls into JavaScript arrays, and ClojureScript
   keywords into JavaScript strings. Stolen from https://gist.github.com/1658431."
  [x]
  (cond
    (string? x) 
    	x
    (keyword? x) 
    	(name x)
    (map? x) 
    	(.-strobj 
  			(reduce (fn [currentItem [currentKey currentValue]](assoc currentItem (clj->js currentKey) (clj->js currentValue))) {} x))
    (coll? x) 
    	(apply array (map clj->js x))
    :else x))


(defn make-js-map
  "makes a javascript map from a clojure one"
  [cljmap]
  (let [out (js-obj)]
    (doall (map #(aset out (name (first %)) (second %)) cljmap))
    out))