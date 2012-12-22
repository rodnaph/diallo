
(ns diallo.explore
  (:require [diallo.fetch :as fetch]))

(def canvas (.querySelectorAll js/document ".explorer canvas"))

(defn show-views [views]
  (.log js/console (count views)))

(defn init []
  (fetch/xhr "/api/views" "" show-views))

(set! (.-onload js/window) init)

