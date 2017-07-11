(defproject mygrant "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://github.com/JovanToroman/mygrant"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.2"]
                 [hiccup "1.0.5"]
                 [reaver "0.1.2"]]
  :main ^:skip-aot job.web
  :uberjar-name "shouter-standalone.jar"
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler mygrant.web/application
         :init shouter.models.migration/migrate}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}
             :uberjar {:aot :all}})
