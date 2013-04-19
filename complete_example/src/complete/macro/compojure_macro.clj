(ns complete.macro.compojure-macro
  (:use compojure.core))

;;(parameter-symbol->string '(index username ?password))
(defn parameter-symbol->string [items]
  (cond
   (empty? items) ""
   :else (split-with #(not (= \? (first %))) (map str items))))

;;(convert-to-string '(aaa bbb ccc) false)
(defn convert-to-string [items keep-last-slash]
  (let [raw (apply str (map #(apply str ":" % "/") items))]
    (if keep-last-slash
      raw
      (apply str (butlast raw)))))

;;(remove-question-mark "some?")
(defn remove-question-mark [text]
  (apply str (remove #(= \? %) text)))

;;(string-to-symbol '("aaa" "bbb" "ccc")
(defn string-to-symbol [items]
  (map #(symbol(remove-question-mark %)) items))

;; ;;(macroexpand-1 '(-|| index  ("adfa")))
;; ;;(macroexpand-1 '(-|| index user ("adfa")))
;; ;;(macroexpand-1 '(-|| index user ?test ?me ("adfa")))
(defmacro |-| [& items]
  (let [method-name (first items)
        body (last items)
        [main-parameters query-string-parameters](parameter-symbol->string(butlast (rest items)))]
    `(POST ~(apply str ["/" method-name "/" (convert-to-string main-parameters (empty? query-string-parameters))])
           [~@(string-to-symbol main-parameters) ~@(string-to-symbol query-string-parameters)]
           ~body)))
