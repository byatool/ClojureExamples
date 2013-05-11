(ns complete.macro.site-macro)

(defmacro if-success [& rest]
  (let [result-transform (symbol "result")]
    `(if (:Success ~result-transform)
       (->
        ~@rest)
       ~result-transform)))
