
(ns leonardo.event)

(defn over [element handler]
  (let [node (.-node element)]
    (set! (.-onmouseover node) handler)))

(defn out [element handler]
  (let [node (.-node element)]
    (set! (.-onmouseout node) handler)))

(defn click [element handler]
  (let [node (.-node element)]
    (set! (.-onclick node) handler)))

