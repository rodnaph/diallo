
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

;; Pages
;; -----

(defn- page-explore [req]
  (html/explore-template))

(defn- page-index [req]
  (html/index-template (jenko/views)))

(defn- page-view [view req]
  (html/view-template view (jobs-for view)))

;; API
;; ---

(defn- api-views [req]
  (edn-response (jenko/views))) 

(defn- api-jobs [view req]
  (edn-response (jobs-for view)))

;; Routes
;; ------

(defroutes app-routes

  (GET "/" [] page-index)
  (GET "/explore" [] page-explore)
  (GET "/views/:view" [view] (partial page-view view))

  (GET "/api/views" [] api-views)
  (GET "/api/views/:view" [view] (partial api-jobs view))

  (route/resources "/assets")
  (route/not-found "404..."))

(def app
  (-> #'app-routes
    (wrap-reload)
    (wrap-stacktrace)
    (handler/site)))

