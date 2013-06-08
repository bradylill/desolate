(ns desolate.game)

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
