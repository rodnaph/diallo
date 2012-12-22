
(defproject diallo "0.1.0"
  :description "Jenkins Badges"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [jenko "0.1.3"]
                 [compojure "1.1.3"]
                 [ring/ring-jetty-adapter "1.1.2"]
                 [ring/ring-devel "1.1.6"]
                 [enlive "1.0.1"]]
  :plugins [[lein-cljsbuild "0.2.10"]]
  :source-paths ["src/clojure"]
  :cljsbuild {
    :builds [{
      :source-path "src/cljs"
      :compiler {
        :output-to "resources/public/js/diallo.js"
        :optimizations :whitespace
        :pretty-print true
      }
    }]
  }
  :main diallo.core)

