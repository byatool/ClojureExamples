(ns complete.validation.text-validation.when-validating-if-the-text-is-empty
  (:use
   [clojure.contrib.string :only (substring?)]
   clojure.test
   complete.macro.unit-test
   [complete.validation.text-validation :only (text-is-empty)]))


;; Fields

(*string* to-check)
(string! control-name)


;; Support Functions

(defn call-the-method []
  (text-is-empty to-check control-name))


;; Test Functions

(it-should "return nil if there is text"
           (is (nil? (call-the-method))))

(it-should "return an error message if the text is nil"
           (binding [to-check nil]
             (is (not (nil? (call-the-method))))))

(it-should "return an error message if the text is empty"
           (binding [to-check ""]
             (is (not (nil? (call-the-method))))))

(it-should "contain the given name if there is an error"
           (binding [to-check nil]
             (is (substring? control-name (call-the-method)))))


