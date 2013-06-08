(defproject desolate "0.0.1-SNAPSHOT"
  :description "desolate - a space exploration roguelike."
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [quil "1.6.0"]]
  :main desolate.core
  :profiles {:dev {:dependencies [[midje "1.5.1"]]}})
  
