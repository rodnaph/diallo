
(ns leonardo.element)

(defn attr [shape params]
  (.attr shape (clj->js params)))
 
