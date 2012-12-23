
(ns diallo.util)

(defn log [& params]
  (.log js/console params))

