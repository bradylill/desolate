(ns desolate.game)

(defrecord Pos [x y z])

(def walk-speed 5)
(def run-speed 10)
(def tile-size 5)

(def player { :sprite "@" :parts []})
(def rock   { :sprite "*" :parts []})
(def ship   { :sprite "#" 
              :parts [{ :sprite "=" :pos (Pos. -5 0 0) }
                      { :sprite "=" :pos (Pos. 5 0 0)  }
                      { :sprite ">" :pos (Pos. 10 0 0) }
                      { :sprite "=" :pos (Pos. -5 -5 0)}
                      { :sprite "=" :pos (Pos. 0 -5 0) }
                      { :sprite ">" :pos (Pos. 5 -5 0) }
                      { :sprite "=" :pos (Pos. -5 5 0) }
                      { :sprite "=" :pos (Pos. 0 5 0)  }
                      { :sprite ">" :pos (Pos. 5 5 0)  }]})

(def world (atom { :objects [{:type player :pos (Pos. 50000 10000 0)}
                             {:type ship   :pos (Pos. 49980 10000 0)}
                             {:type rock   :pos (Pos. 50012 10002 0)}
                             {:type rock   :pos (Pos. 50022 10002 0)}
                             {:type rock   :pos (Pos. 50042 10012 0)}
                             {:type rock   :pos (Pos. 50052 10002 0)}
                             {:type rock   :pos (Pos. 50012 10012 0)}]}))
