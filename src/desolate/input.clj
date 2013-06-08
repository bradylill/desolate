(ns desolate.input 
  (:require [desolate.actions :as actions] 
            [desolate.game :as game]
            [quil.core :as q]))

(def key-queue (atom []))

(defn key-press []
  (swap! key-queue #(conj % (q/raw-key))))

(def key-bindings { \w (partial actions/move-player-up    game/walk-speed) 
                    \a (partial actions/move-player-left  game/walk-speed)
                    \s (partial actions/move-player-down  game/walk-speed)
                    \d (partial actions/move-player-right game/walk-speed)
                  
                    \W (partial actions/move-player-up    game/run-speed)
                    \A (partial actions/move-player-left  game/run-speed)
                    \S (partial actions/move-player-down  game/run-speed)
                    \D (partial actions/move-player-right game/run-speed) })
