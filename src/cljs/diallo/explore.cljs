
(ns diallo.explore
  (:require [leonardo.paper :as p]
            [leonardo.element :as e]
            [diallo.fetch :as fetch]
            [diallo.util :as util]))

(def paper (p/paper "explorer" 758 600))

(defn show-views [views]
  (doseq [view views]
    (let [[x y] (p/random-point paper)
          circle (p/circle paper x y 40)]
      (e/attr circle {:fill "#fff" :fill-opacity 0.5}))))

(defn init []
  (if paper
    (fetch/xhr "/api/views" "" show-views)))

(set! (.-onload js/window) init)

