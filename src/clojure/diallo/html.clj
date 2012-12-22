
(ns diallo.html
  (:use net.cgrand.enlive-html)
  (:require [clojure.string :as string]))

(defsnippet
  feature-snippet "diallo/views/view.html" [:.feature]
  [feature]
  [:i] (let [icon (-> feature
                  (string/lower-case)
                  (string/replace #"\." "-"))]
         (do-> (set-attr :class (str "icon-" icon))
               (set-attr :title feature)))
  [:span] (content feature))

(defsnippet
  job-snippet "diallo/views/view.html" [:.job]
  [job]
  [:h3] (content (:name job))
  [:.features] (content
                 (map (comp feature-snippet name)
                      (:features job))))

(defsnippet
  views-snippet "diallo/views/view.html" [:.jobs]
  [jobs]
  [:*] (content (map job-snippet jobs)))

(defsnippet
  view-snippet "diallo/views/index.html" [:.view]
  [view]
  [:a] (do-> (content (:name view))
                  (set-attr :href (format "/views/%s" (:name view)))))

(defsnippet
  explore-snippet "diallo/views/explore.html" [:.explorer]
  [])

;; Public
;; ------

(deftemplate
  view-template "diallo/views/index.html"
  [view jobs]
  [:title] (append (str " - " view))
  [:h1 :a] (content view)
  [:.content] (content (views-snippet jobs)))

 (deftemplate
  index-template "diallo/views/index.html"
  [views]
  [:.views] (content (map view-snippet views)))

(deftemplate
  explore-template "diallo/views/index.html"
  []
  [:title] (append "Explore")
  [:h1 :a] (content "Exploring...")
  [:.content] (content (explore-snippet)))

