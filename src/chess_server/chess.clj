(ns chess-server.chess)

(def pieces
  {
    :empty "x"
    :white {:king "wK" :queen "wq" :knight "wk" :bishop "wb" :rook "wr" :pawn "wp"}
    :black {:king "bK" :queen "bq" :knight "bk" :bishop "bb" :rook "br" :pawn "bp"}
    :start (fn [color] [(color :rook) (color :knight) (color :bishop) (color :queen)
                        (color :king) (color :bishop) (color :knight) (color :rook)
                        ])
    }
  )

(defn fill-row [n piece]
  (into [] (repeat n piece))
  )

(defn create-new-board []
  [
    ((pieces :start) (pieces :white))
    (fill-row 8 (-> pieces :white :pawn))
    (fill-row 8 (pieces :empty))
    (fill-row 8 (pieces :empty))
    (fill-row 8 (pieces :empty))
    (fill-row 8 (pieces :empty))
    (fill-row 8 (-> pieces :black :pawn))
    ((pieces :start) (pieces :black))
   ]
  )


(defn get-square [board [x y]]
  (get-in board [x y])
  )

;; just moves pieces from one position to another,
;; overriding the target position (no verification of moves yet)
(defn move [board [fromx fromy] [tox toy]]
  (let
    [from-piece (get-square board [fromx fromy])
      to-piece (get-square board [tox toy])]
    (-> b (assoc-in [tox toy] from-piece)
          (assoc-in [fromx fromy] empty-s))
    )
  )
