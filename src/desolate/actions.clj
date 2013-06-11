(ns desolate.actions
  (:require [desolate.game :as game])
  (:import  [desolate.game Pos]))

;helpers-----
(defn- update-position [dx dy dz old-pos]
  (Pos. (+ dx (:x old-pos))
        (+ dy (:y old-pos))
        (+ dz (:z old-pos))))

;actions-----
(defn set-player-vector [direction speed world]
  (println direction " - " speed)
  world)

(defn move-player-up [speed world]
  (update-in world [:objects 0 :pos] (partial update-position 0 (* -1 speed) 0)))

(defn move-player-left [speed world]
  (update-in world [:objects 0 :pos] (partial update-position (* -1 speed) 0 0)))

(defn move-player-down [speed world]
  (update-in world [:objects 0 :pos] (partial update-position 0 speed 0)))

(defn move-player-right [speed world]
  (update-in world [:objects 0 :pos] (partial update-position speed 0 0)))
