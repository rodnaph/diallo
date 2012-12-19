
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

(defn- index-page [req]
  (html/index-template (jenko/views)))

(defn- view-page [req]
  (let [view (get-in req [:route-params :view])]
    (html/view-template
      view
      (->> (jenko/jobs-in-view view)
           (map with-features)))))

(defroutes app-routes
  (GET "/" [] index-page)
  (GET "/:view" [] view-page)
  (route/resources "/assets")
  (route/not-found "404..."))

(def app
  (-> #'app-routes
    (wrap-reload)
    (wrap-stacktrace)
    (handler/site)))

