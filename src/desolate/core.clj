(ns desolate.core
  (:require [desolate.game :as game]
            [desolate.gfx :as gfx]
            [desolate.input :as input]
            [quil.core :as q])
  (:import  [desolate.game Pos]))

;game-config-----
(def screen-size [323 200])

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

(def key-bindings { \w (partial move-player-up    game/walk-speed) 
                    \a (partial move-player-left  game/walk-speed)
                    \s (partial move-player-down  game/walk-speed)
                    \d (partial move-player-right game/walk-speed)
                  
                    \W (partial move-player-up    game/run-speed)
                    \A (partial move-player-left  game/run-speed)
                    \S (partial move-player-down  game/run-speed)
                    \D (partial move-player-right game/run-speed) })
  
(defn- update-world [world] 
  (let [[input & _] @input/key-queue
        input-fn (key-bindings input)]
    (reset! input/key-queue [])
    (if input-fn
      (input-fn world)
      world)))

;game-loop-----
(defn- run []
  (gfx/start-rendering)
  (loop []
    (Thread/sleep 200)
    (->> @game/world
        (update-world)
        (reset! game/world))
    (recur)))

;main-----
(defn -main [& args]
  (run))
