(defproject chess-server "0.1.0-SNAPSHOT"
  :description "a simple chess server"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [http-kit "2.1.18"]
                 [compojure "1.3.4"]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [org.clojure/java.jdbc "0.3.7"]
                 ]
  :main ^:skip-aot chess-server.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})


