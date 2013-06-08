(ns desolate.input 
  (:require [quil.core :as q]))

(def key-queue (atom []))

(defn key-press []
  (swap! key-queue #(conj % (q/raw-key))))
