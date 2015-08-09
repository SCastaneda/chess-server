(ns chess-server.db)
(require '[clojure.java.jdbc :as j])

(defn create-db-conn [server user pw]
  { :subprotocol "mysql"
    :subname (str "//" server)
    :user user
    :password pw }
  )

(defn getBoardByHash [conn hash]
  (j/query conn
           ["select * from games where hash_game = ?" hash]
           )
  )
