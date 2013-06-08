(ns desolate.core
  (:require [quil.core :as q]))

;game-config-----
(def screen-size [323 200])

;game-data-----
(def key-queue (atom []))

(def walk-speed 5)
(def run-speed 10)

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
(defn- update-position [dx dy dz old-pos]
  (Pos. (+ dx (:x old-pos))
        (+ dy (:y old-pos))
        (+ dz (:z old-pos))))

(defn- move-player-up [speed world]
  (update-in world [:objects 0 :pos] (partial update-position 0 (* -1 speed) 0)))

(defn- move-player-left [speed world]
  (update-in world [:objects 0 :pos] (partial update-position (* -1 speed) 0 0)))

(defn- move-player-down [speed world]
  (update-in world [:objects 0 :pos] (partial update-position 0 speed 0)))

(defn- move-player-right [speed world]
  (update-in world [:objects 0 :pos] (partial update-position speed 0 0)))

(def key-bindings { \w (partial move-player-up    walk-speed) 
                    \a (partial move-player-left  walk-speed)
                    \s (partial move-player-down  walk-speed)
                    \d (partial move-player-right walk-speed)
                  
                    \W (partial move-player-up run-speed)
                    \A (partial move-player-left run-speed)
                    \S (partial move-player-down run-speed)
                    \D (partial move-player-right run-speed) })
  
(defn- update-world [world] 
  (let [[input & _] @key-queue
        input-fn (key-bindings input)]
    (reset! key-queue [])
    (if input-fn
      (input-fn world)
      world)))

;game-loop-----
(defn- run []
  (start-rendering)
  (loop []
    (Thread/sleep 200)
    (->> @world
        (update-world)
        (reset! world))
    (recur)))

;main-----
(defn -main [& args]
  (run))
