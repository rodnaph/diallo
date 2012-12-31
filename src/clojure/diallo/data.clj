
(ns diallo.data
  (:require [jenko.core :as jenko]))

(def ^{:dynamic true :private true} *config* (ref {}))

(defn- with-features [job]
  (merge job
    {:features (jenko/job-features (:name job))}))

(defn- ^{:doc "Merge some maps into a parent map, keyed by map-key"}
  index-by [map-key col]
  (let [merger #(merge %1 {(map-key %2) %2})]
    (reduce merger {} col)))

;; Public
;; ------

(defn views []
  (vals (:views @*config*)))

(defn jobs-for [view-name]
  (let [view (get (:views @*config*) view-name)]
    (if-let [jobs (:jobs view)]
      jobs
      (let [jobs (map with-features (jenko/jobs-in-view view-name))]
        (dosync (alter *config* #(assoc-in % [:views view-name]
                                    (merge view {:jobs jobs})))
        jobs)))))

(defn refresh []
  (dosync (ref-set *config*
    {:views (index-by :name (jenko/views))})))

