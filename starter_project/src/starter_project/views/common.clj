(ns starter-project.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]
        [starter-project.css.base-css]))


(defpartial layout  [& content]
  (html5
    (include-js "/js/application.js")
    (include-css "/css/application.css")
    [:head
      [:title "first"]
    ]
    [:body
      [:div#wrapper 
        content
      ]
    ]
  )
)