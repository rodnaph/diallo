
(ns diallo.web
  (:use compojure.core
        ring.middleware.reload
        ring.middleware.stacktrace
        net.cgrand.enlive-html)
  (:require (compojure [handler :as handler]
                       [route :as route])
            [jenko.core :as jenko]
            [diallo.html :as html]))

(defn- with-features [job]
  (merge job
    {:features (jenko/job-features (:name job))}))

(defn- jobs-for [view & [req]]
  (map with-features
       (jenko/jobs-in-view view)))

(defn- as-edn [data]
  {:content-type "application/edn"
   :body (pr-str data)})

;; Pages
;; -----

(defn- explore-page [req]
  (html/explore-template))

(defn- index-page [req]
  (html/index-template (jenko/views)))

(defn- view-page [view req]
  (html/view-template view (jobs-for view)))

(defn- drop-req [req])

;; API
;; ---

(defn- api-views [req]
  (as-edn (jenko/views))) 

(defn- api-jobs [view req]
  (as-edn (jobs-for view)))

;; Routes
;; ------

(defroutes app-routes

  (GET "/" [] index-page)
  (GET "/explore" [] explore-page)
  (GET "/views/:view" [view] (partial view-page view))

  (GET "/api/views" [] api-views)
  (GET "/api/views/:view" [view] (partial api-jobs view))

  (route/resources "/assets")
  (route/not-found "404..."))

(def app
  (-> #'app-routes
    (wrap-reload)
    (wrap-stacktrace)
    (handler/site)))

