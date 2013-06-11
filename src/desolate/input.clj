(ns desolate.input 
  (:require [desolate.actions :as actions] 
            [desolate.game :as game]
            [quil.core :as q]))

(def key-queue (atom []))

(defn key-press []
  (swap! key-queue #(conj % [(q/raw-key) :pressed])))

(defn key-release []
  (swap! key-queue #(conj % [(q/raw-key) :released])))

(def key-bindings 
  {
   [\w :pressed] (partial actions/set-player-vector :up    game/walk-speed) 
   [\a :pressed] (partial actions/set-player-vector :left  game/walk-speed)
   [\s :pressed] (partial actions/set-player-vector :down  game/walk-speed)
   [\d :pressed] (partial actions/set-player-vector :right game/walk-speed)

   [\w :released] (partial actions/set-player-vector :up    0) 
   [\a :released] (partial actions/set-player-vector :left  0)
   [\s :released] (partial actions/set-player-vector :down  0)
   [\d :released] (partial actions/set-player-vector :right 0)

   [\W :pressed] (partial actions/set-player-vector :up    game/run-speed)
   [\A :pressed] (partial actions/set-player-vector :left  game/run-speed)
   [\S :pressed] (partial actions/set-player-vector :down  game/run-speed)
   [\D :pressed] (partial actions/set-player-vector :right game/run-speed)})
