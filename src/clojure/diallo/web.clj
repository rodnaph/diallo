
(ns diallo.web
  (:use compojure.core
        ring.middleware.reload
        ring.middleware.stacktrace)
  (:require (compojure [handler :as handler]
                       [route :as route])
            [ring.util.response :as response]
            [diallo.html :as html]
            [diallo.data :as data]))

(defn- edn-response [data]
  (response/content-type
    {:body (pr-str data)}
    "application/edn"))

(defroutes app-routes

  (GET "/" []
       (html/index-template (data/views)))

  (GET "/explore" []
       (html/explore-template))

  (GET "/views/:view" [view]
       (html/view-template view (data/jobs-for view)))

  (GET "/api/views" []
       (edn-response (data/views)))

  (GET "/api/views/:view" [view]
       (edn-response (data/jobs-for view)))

  (route/resources "/assets")
  (route/not-found "404..."))

;; Public
;; ------

(def app
  (-> #'app-routes
    (wrap-reload)
    (wrap-stacktrace)
    (handler/site)))

