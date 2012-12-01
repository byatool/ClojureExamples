(defproject starter_project "0.1.0-SNAPSHOT"
    :description "FIXME: write this!"		
    :dependencies [ [org.clojure/clojure "1.4.0"]
                    [noir "1.3.0-beta3"] 
                    [lobos "1.0.0-SNAPSHOT"]
                    [postgresql "9.1-901.jdbc4"]
                    [korma "0.3.0-beta9"]]
                   

   	:plugins [[lein-cljsbuild "0.2.9"]]
		:hooks [leiningen.cljsbuild]
		;This is for the clojure script building
   	:cljsbuild {
	    :builds {
          :live	
          { 
            :source-path "src-cljs/src/starter_project"       
            :compiler 
              { :output-to "resources/public/js/application.js"
                :optimizations :simple
                :pretty-print true}}
        ;   :test
        ;   { 
        ;     :source-path "src-cljs/src"       
        ;     :compiler 
        ;       { 
        ;       	:output-to  "resources/test/js/application.js"
        ;         :optimizations :simple
        ;         :pretty-print true }}
        }
  	}	
		:main starter-project.server
)

