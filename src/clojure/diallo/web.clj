
(ns diallo.web
  (:use compojure.core
        ring.middleware.reload
        ring.middleware.stacktrace
        net.cgrand.enlive-html)
  (:require (compojure [handler :as handler]
                       [route :as route])
            [ring.util.response :as response]
            [jenko.core :as jenko]
            [diallo.html :as html]))

(defn- with-features [job]
  (merge job
    {:features (jenko/job-features (:name job))}))

(defn- jobs-for [view & [req]]
  (map with-features
       (jenko/jobs-in-view view)))

(defn- edn-response [data]
  (response/content-type
    {:body (pr-str data)} 
    "application/edn"))

(defroutes app-routes

  (GET "/" []
       (html/index-template (jenko/views)))

  (GET "/explore" []
       (html/explore-template))

  (GET "/views/:view" [view]
       (html/view-template view (jobs-for view)))

  (GET "/api/views" []
       (edn-response (jenko/views)))

  (GET "/api/views/:view" [view]
       (edn-response (jobs-for view)))

  (route/resources "/assets")
  (route/not-found "404..."))

(def app
  (-> #'app-routes
    (wrap-reload)
    (wrap-stacktrace)
    (handler/site)))

