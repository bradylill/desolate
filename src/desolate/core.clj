(ns desolate.core
  (:require [desolate.game :as game]
            [desolate.gfx :as gfx]
            [desolate.input :as input]
            [quil.core :as q]))

;game-config-----
(def screen-size [323 200])

;updating-----
(defn update-world [world] 
  (let [[input & _] @input/key-queue
        input-fn (input/key-bindings input)]
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
