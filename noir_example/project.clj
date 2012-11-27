(defproject noir_example "0.1.0-SNAPSHOT"
    :description "FIXME: write this!"		
    :dependencies [[org.clojure/clojure "1.4.0"]
                   [noir "1.3.0-beta3"]]
                   

   	:plugins [[lein-cljsbuild "0.2.9"]]
		:hooks [leiningen.cljsbuild]
		;This is for the clojure script building
   	:cljsbuild {
	    :builds {
          :live	
          { 
            :source-path "src-cljs/src/noir_example"       
            :compiler 
              { :output-to "resources/public/js/application.js"
                :optimizations :simple
                :pretty-print true}}
          :test
          { 
            :source-path "src-cljs/src"       
            :compiler 
              { 
              	:output-to  "src-cljs/test/noir_example/main_test.js"
                :optimizations :simple
                :pretty-print true }}
        }
  	}	
		:main noir_example.server
)

