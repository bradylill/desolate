(ns desolate.core
  (:require [quil.core :as q]))
;game-config-----

(def screen-size [323 200])

;game-data-----
(def player { :icon "@" })
(def rock   { :icon "*" })

(def world { :objects [{:type player :pos [50000 10000 0]}
                       {:type rock   :pos [50012 10002 0]}
                       {:type rock   :pos [50022 10002 0]}
                       {:type rock   :pos [50042 10012 0]}
                       {:type rock   :pos [50052 10002 0]}
                       {:type rock   :pos [50012 10012 0]}]})

;helpers-----


;drawing-----
(defn setup []
  (q/smooth)
  (q/frame-rate 30)
  (q/background 200))

(defn- draw-object [object current-pos]
  (let [x (- ((:pos object) 0) (current-pos 0))
        y (- ((:pos object) 1) (current-pos 1))]
    (q/text (get-in object [:type :icon]) x y)))

(defn- draw-world [world current-pos]
  (doseq [object (:objects world)]
    (draw-object object current-pos)))

(defn draw []
  (draw-world world [49839 9900 0]))

(defn render []
  (q/defsketch desolate
    :title "desolate"
    :setup setup
    :draw draw
    :size [323 200]))

;main-----
(defn -main [& args]
  (render))
