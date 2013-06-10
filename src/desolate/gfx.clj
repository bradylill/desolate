(ns desolate.gfx
  (:require [quil.core :as q]
            [desolate.game :as game]
            [desolate.input :as input])
  (:import  [desolate.game Pos]))

(defn setup []
  (q/smooth)
  (q/frame-rate 30)
  (q/background 200))

(defn- draw-part [part reference-pos]
  (let [x (+ (:x reference-pos)
             (get-in part [:pos :x]))
        y (+ (:y reference-pos)
             (get-in part [:pos :y]))]
    (q/text (:sprite part) x y)))

(defn- draw-object [object current-pos]
  (let [x (- (get-in object [:pos :x])
             (:x current-pos))
        y (- (get-in object [:pos :y])
             (:y current-pos))]
    (q/text (get-in object [:type :sprite]) x y)
    (doseq [part (get-in object [:type :parts])]
      (draw-part part (Pos. x y 0)))))

(defn- draw-world [world current-pos]
  (doseq [object (:objects world)]
    (draw-object object current-pos)))

(defn draw []
  (q/background 200)
  (draw-world @game/world (Pos. 49839 9900 0)))

(defn start-rendering []
  (q/defsketch desolate
    :title "desolate"
    :key-typed input/key-press
    :setup setup
    :draw draw
    :size [323 200]))
