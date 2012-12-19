
(ns diallo.html
  (:use net.cgrand.enlive-html))

(defsnippet
  feature-snippet "diallo/views/view.html" [:.feature]
  [feature]
  [:*] (let [name* (name feature)]
         (do-> (content name*)
            (set-attr :class name*))))

(defsnippet
  job-snippet "diallo/views/view.html" [:.job]
  [job]
  [:h3] (content (:name job))
  [:.features] (content
                 (map feature-snippet (:features job))))

(defsnippet
  view-snippet "diallo/views/index.html" [:.view]
  [view]
  [:a] (do-> (content (:name view))
                  (set-attr :href (:name view))))

(deftemplate
  view-template "diallo/views/view.html"
  [view jobs]
  [:.name] (content view)
  [:.jobs] (content (map job-snippet jobs)))

(deftemplate
  index-template "diallo/views/index.html"
  [views]
  [:.views] (content (map view-snippet views)))

