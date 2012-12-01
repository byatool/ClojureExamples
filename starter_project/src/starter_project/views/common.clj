(ns starter-project.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]))

(defpartial layout  [& content]
  (html5
    (include-js "/js/application.js")
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