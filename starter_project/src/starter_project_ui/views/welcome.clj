(ns starter-project.views.welcome
  (:require [starter-project.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to starter-project"]))
