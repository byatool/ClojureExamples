(ns starter-project.views.login
  (:require [starter-project.views.common 	 :as common]
            [noir.content.getting-started]
            [noir.response :as response]
            [starter-project.constant.action :as actions])
  (:use [noir.core :only [defpage]]))


(defpage "/login" {}
  (let  [formName "formLogin"
        buttonName "buttonSubmit"]
    (common/layout
      [:form {:id formName :action (actions/login_post) :method "post" }
        [:input   {:id "textUserName" :name "textUserName" :type "text"}]
        [:input   {:id "textPassword" :name "textPassword" :type "text"}]
        [:input   {:id buttonName :type "button" :value "login" :class "submit_button"}]
        [:script (format "src.starter_project.views.login.set_button('%s', '%s');" buttonName formName)]]
      [:div {:id "divError" :class "alert"} 
        [:div {:id "divErrorMessage" :class "info"} "error"]
      ])))

; :onclick (format "src.starter_project.views.login.send_login('%s');" formName )}]

(defpage [:post "/login_post"] {:keys [id name]}
 	(response/json {:success false :message ["First name is not long enough." "Last name is not long enough."] }))