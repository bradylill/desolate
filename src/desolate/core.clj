(ns desolate.core
  (:require [quil.core :as q]))

(defn setup []
  (q/smooth)
  (q/frame-rate 30)
  (q/background 200))

(defn draw []
  nil)

(defn render []
  (q/defsketch desolate
    :title "desolate"
    :setup setup
    :draw draw
    :size [323 200]))

(defn -main [& args]
  (render))
