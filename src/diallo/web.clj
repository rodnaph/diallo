
(ns diallo.web
  (:use compojure.core
        ring.middleware.reload
        ring.middleware.flash
        ring.middleware.stacktrace)
  (:require (compojure [handler :as handler]
                       [route :as route])
            [net.cgrand.enlive-html :as html]
            [jenko.core :as jenko]))

(html/defsnippet
  feature-snippet "diallo/views/index.html" [:.feature]
  [feature]
  [:*] (html/content (name feature)))

(html/defsnippet
  job-snippet "diallo/views/index.html" [:.job]
  [job]
  [:h3] (html/content (:name job))
  [:.features] (html/content
                 (map feature-snippet (:features job))))

(html/deftemplate
  layout "diallo/views/index.html"
  [params]
  [:.jobs] (html/content (map job-snippet (:jobs params))))

(defn- with-features [job]
  (merge job
    {:features (jenko/job-features (:name job))}))

(defn- index-page [req]
  (let [jobs (->> (jenko/jobs)
                  (filter #(= "scotam-webapp-master" (:name %)))
                  (map with-features))]
    (layout {:jobs jobs})))

(defroutes app-routes
  (GET "/" [] index-page)
  (route/resources "/assets")
  (route/not-found "404..."))

(def app
  (-> #'app-routes
    (wrap-reload)
    (wrap-flash)
    (wrap-stacktrace)
    (handler/site)))

