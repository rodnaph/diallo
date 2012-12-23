
(ns leonardo.paper)

;; Paper
;; -----

(defn create [id width height]
  (if-let [canvas (.getElementById js/document id)]
    (js/Raphael. canvas width height)))

(defn canvas [paper]
  (.-canvas paper))

(defn width [paper]
  (.-clientWidth (canvas paper)))

(defn height [paper]
  (.-clientHeight (canvas paper)))

(defn random-point [paper]
  [(rand-int (width paper))
   (rand-int (height paper))])

;; Shapes
;; ------

(defn text [paper x y message]
  (.text paper x y message))

(defn rect [paper x y width height & [r]]
  (.rect paper x y width height r))

(defn circle [paper x y radius]
  (.circle paper x y radius))

