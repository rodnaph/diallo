
(ns diallo.core
  (:require [diallo.web :as web]
            [ring.adapter.jetty :as jetty]))

(defn start []
  (jetty/run-jetty
    web/app
    {:port 1234}))

(defn -main []
  (start))

