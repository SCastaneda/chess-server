(ns chess-server.core
  (:require [clojure.data.json :as json])
  (:use
    [chess-server.chess]
    [chess-server.routes]
    [chess-server.db])
  (:gen-class)
  )

(defn -main [& args]
  (cond 
    (< (count args) 2) (print-usage)
    true
      (let [config (read-cfg (nth args 0)) 
            port (nth args 1)
            conn (get-conn (get config "db"))
            ] 

        (println "config: " config)
        (println "port: " port)
        (println "conn: " conn)
        
        )
      )
  )

(defn read-cfg [file]
  (json/read-str (slurp file))
  )

(defn print-usage []
  (println "usage:\n\tjava -jar chess-server.jar [config] [port]")
  )

(defn get-conn [config-db]
  (create-db-conn 
    (get config-db "server")  
    (get config-db "user")
    (get config-db "password")
    )
  )

