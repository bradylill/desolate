(ns desolate.game)

(defrecord Pos [x y z])

(def walk-speed 1)
(def run-speed 2)

(def player {:sprite "@" 
             :movement-vector [:up 0] 
             :parts []})

(def rock   {:sprite "*" 
             :movement-vector [:up 0] 
             :parts []})

(def ship   {:sprite "#" 
             :movement-vector [:up 0]
             :parts [{:sprite "=" :pos (Pos. -1 0 0) }
                     {:sprite "=" :pos (Pos. 1 0 0)  }
                     {:sprite ">" :pos (Pos. 2 0 0)  }
                     {:sprite "=" :pos (Pos. -1 -1 0)}
                     {:sprite "=" :pos (Pos. 0 -1 0) }
                     {:sprite ">" :pos (Pos. 1 -1 0) }
                     {:sprite "=" :pos (Pos. -1 1 0) }
                     {:sprite "=" :pos (Pos. 0 1 0)  }
                     {:sprite ">" :pos (Pos. 1 1 0)  }]})

(def world (atom {:objects [(assoc player :pos (Pos. 50000 10000 0))
                            (assoc ship   :pos (Pos. 49980 10000 0))
                            (assoc rock   :pos (Pos. 50012 10002 0))
                            (assoc rock   :pos (Pos. 50022 10002 0))
                            (assoc rock   :pos (Pos. 50042 10012 0))
                            (assoc rock   :pos (Pos. 50052 10002 0))
                            (assoc rock   :pos (Pos. 50012 10012 0))]}))

