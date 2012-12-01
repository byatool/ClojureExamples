(ns starter-project.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]))

(defpartial layout [& content]
	(html5
	    (include-js "/js/application.js")
		(include-css "/css/reset.css")
		[:head
			[:title "starter-project"]
		]

		[:body
			[:div#wrapper
				content
			]
		]
	)
)
