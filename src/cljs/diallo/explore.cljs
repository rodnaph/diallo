
(ns diallo.explore
  (:require [leonardo.paper :as p]
            [leonardo.element :as el]
            [leonardo.event :as evt]
            [diallo.fetch :as fetch]
            [diallo.util :as util]))

(def paper (p/create "explorer" 758 600))

(defn view-hovered [e]
  (util/log "View hovered..."))

(defn show-views [views]
  (doseq [view views]
    (let [[x y] (p/random-point paper)
          shape (p/rect paper x y 100 30 5)
          text (p/text paper x y (:name view))]
      (-> shape
        (el/attr {:fill "#fc9"
                  :fill-opacity 0.8})
        (evt/over view-hovered)))))

(defn init []
  (if paper
    (fetch/xhr "/api/views" "" show-views)))

(set! (.-onload js/window) init)

