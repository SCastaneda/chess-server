(ns chess-server.core
  (:use
    [chess-server.chess]
    [chess-server.routes]
    [chess-server.db])
  (:gen-class)
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  )

