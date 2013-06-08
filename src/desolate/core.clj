(ns desolate.core
  (:require [quil.core :as q]))
;game-config-----
(def screen-size [323 200])

;game-data-----
(def key-queue (atom []))

(def player { :icon "@" })
(def rock   { :icon "*" })

(defrecord Pos [x y z])

(def world (atom { :objects [{:type player :pos (Pos. 50000 10000 0)}
                             {:type rock   :pos (Pos. 50012 10002 0)}
                             {:type rock   :pos (Pos. 50022 10002 0)}
                             {:type rock   :pos (Pos. 50042 10012 0)}
                             {:type rock   :pos (Pos. 50052 10002 0)}
                             {:type rock   :pos (Pos. 50012 10012 0)}]}))

;helpers-----

;input-----
(defn key-press []
  (swap! key-queue #(conj % (q/raw-key)))
  (println "Kq - " @key-queue))

;drawing-----
(defn setup []
  (q/smooth)
  (q/frame-rate 30)
  (q/background 200))

(defn- draw-object [object current-pos]
  (let [x (- (get-in object [:pos :x])
             (:x current-pos))
        y (- (get-in object [:pos :y])
             (:y current-pos))]
    (q/text (get-in object [:type :icon]) x y)))

(defn- draw-world [world current-pos]
  (doseq [object (:objects world)]
    (draw-object object current-pos)))

(defn draw []
  (q/background 200)
  (draw-world @world (Pos. 49839 9900 0)))

(defn start-rendering []
  (q/defsketch desolate
    :title "desolate"
    :key-typed key-press
    :setup setup
    :draw draw
    :size [323 200]))

;updating-----
(defn- update-position [old-pos]
  (Pos. (+ 5 (:x old-pos))
        (+ 5 (:y old-pos))
        0))
  
(defn- update-world [world] 
  (update-in world [:objects 0 :pos] update-position))

;game-loop-----
(defn- run []
  (start-rendering)
  (loop []
    (Thread/sleep 200)
    (swap! world update-world)
    (recur)))

;main-----
(defn -main [& args]
  (run))
