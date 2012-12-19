
(ns diallo.html
  (:use net.cgrand.enlive-html)
  (:require [clojure.string :as string]))

(defsnippet
  feature-snippet "diallo/views/view.html" [:.feature]
  [feature]
  [:*] (let [name* (name feature)]
         (do-> (content name*)
               (set-attr :class (-> name*
                                    (string/lower-case)
                                    (string/replace #"\." "-"))))))

(defsnippet
  job-snippet "diallo/views/view.html" [:.job]
  [job]
  [:h3] (content (:name job))
  [:.features] (content
                 (map feature-snippet (:features job))))

(defsnippet
  views-snippet "diallo/views/view.html" [:.jobs]
  [jobs]
  [:.job] (content (map job-snippet jobs)))

(deftemplate
  view-template "diallo/views/index.html"
  [view jobs]
  [:h1] (content view)
  [:.content] (content (views-snippet jobs)))

(defsnippet
  view-snippet "diallo/views/index.html" [:.view]
  [view]
  [:a] (do-> (content (:name view))
                  (set-attr :href (:name view))))

(deftemplate
  index-template "diallo/views/index.html"
  [views]
  [:.views] (content (map view-snippet views)))

