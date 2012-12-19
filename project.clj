
(defproject diallo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [jenko "0.1.3"]
                 [compojure "1.1.3"]
                 [ring/ring-jetty-adapter "1.1.2"]
                 [ring/ring-devel "1.1.6"]
                 [enlive "1.0.1"]]
  :plugins [[lein-lesscss "1.2"]]
  :lesscss-paths ["resources/less"]
  :lesscss-output-path "resources/public/css/"
  :main diallo.core)

