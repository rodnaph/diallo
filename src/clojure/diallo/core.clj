
(ns diallo.core
  (:require [diallo.web :as web]
            [diallo.data :as data]
            [ring.adapter.jetty :as jetty]))

(defn start []
  (data/refresh)
  (jetty/run-jetty
    web/app
    {:port 1234}))

(defn -main []
  (start))

