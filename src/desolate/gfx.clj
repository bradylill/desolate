(ns desolate.gfx
  (:require [quil.core :as q]
            [desolate.game :as game]
            [desolate.input :as input])
  (:import  [desolate.game Pos]))

(def tile-size 5)

(defn setup []
  (q/smooth)
  (q/frame-rate 30)
  (q/background 200))

(defn- part-coordinate [coord part reference]
  (+ (coord reference) 
     (* (get-in part [:pos coord])
        tile-size)))

(defn- draw-part [part reference-pos]
  (let [x (part-coordinate :x part reference-pos)
        y (part-coordinate :y part reference-pos)]
    (q/text (:sprite part) x y)))

(defn- object-coordinate [coord object current-pos]
  (* (- (get-in object [:pos coord]) (coord current-pos))
     tile-size))

(defn- draw-object [object current-pos]
  (let [x (object-coordinate :x object current-pos) 
        y (object-coordinate :y object current-pos)]
    (q/text (get-in object [:type :sprite]) x y)
    (doseq [part (get-in object [:type :parts])]
      (draw-part part (Pos. x y 0)))))

(defn- draw-world [world current-pos]
  (doseq [object (:objects world)]
    (draw-object object current-pos)))

(defn draw []
  (q/background 55)
  (draw-world @game/world (Pos. 49939 9990 0)))

(defn start-rendering []
  (q/defsketch desolate
    :title "desolate"
    :key-typed input/key-press
    :setup setup
    :draw draw
    :size [800 600]))
