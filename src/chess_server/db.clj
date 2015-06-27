(ns chess-server.db)
(require '[clojure.java.jdbc :as j])

;; TODO: make configurable
(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/chess"
               :user "chess-server"
               :password "mate"}
  )

(defn getBoardByHash [hash]
  (j/query mysql-db
           ["select * from games where hash_game = ?" hash]
           )
  )
