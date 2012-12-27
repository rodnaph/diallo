
(ns diallo.data
  (:require [jenko.core :as jenko]))

(def ^{:dynamic true :private true} *config* (atom {}))

(defn- with-features [job]
  (merge job
    {:features (jenko/job-features (:name job))}))

(defn- with-jobs [view]
  (merge view
    {:jobs (map with-features
                (jenko/jobs-in-view (:name view)))}))

(defn- ^{:doc "Merge some maps into a parent map, keyed by map-key"}
  merge-by [map-key col]
  (let [merger #(merge %1 {(map-key %2) %2})]
    (reduce merger {} col)))

;; Public
;; ------

(defn views []
  (vals (:views @*config*)))

(defn jobs-for [view-name]
  (:jobs (get view-name (views))))

(defn refresh []
  (let [new-config {:views (merge-by :name (map with-jobs (jenko/views)))
                    :updated-at #inst "now"}]
    (reset! *config* new-config)))

