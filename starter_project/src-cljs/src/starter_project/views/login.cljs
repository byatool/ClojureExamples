(ns src.starter-project.views.login
	(:require 
        [goog.style                                 :as style]
        [goog.dom                                   :as dom]
        [goog.dom.classes                           :as classes]
        [goog.array                                 :as array]
        [src.starter-project.helper.form            :as form]
        [src.starter-project.helper.send-post       :as send]
        [src.starter-project.helper.web-template    :as web]
        [src.starter-project.helper.event           :as event]
        [src.starter-project.helper.error-div       :as error]))


(defn ^:export set-button [button_name, formName]
    (let [buttonToTest (dom/getElement button_name)]
        (event/set-click buttonToTest (fn[] (send/as-async formName callback)))))


(defn callback [reply] 
    (let[result          (.getResponseJson (.-target reply))
         divError        (dom/getElement "divError")
         divErrorMessage (form/retrieve-child-by-id "divErrorMessage" divError)]
        (if (empty? (.-message result))
            (style/showElement divError false)
            (do
                ;;TODO add the messages to the div
                (style/showElement divError true)
                (error/set-the-error-class divErrorMessage (.-success result))))))