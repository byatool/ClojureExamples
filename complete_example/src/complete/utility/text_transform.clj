(ns complete.utility.text-transform
  (:use

   [noir.util.crypt :only (md5)]))

(def seed "the-world-wonders")
;;(md5 (Integer/toString user-id) "test")

(defn hash-text
  ([to-hash]
     (hash-text to-hash md5))
  ([to-hash ?md5]
     (?md5 to-hash seed)))
