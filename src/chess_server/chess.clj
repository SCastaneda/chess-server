(ns chess-server.chess)

(def pieces
  {
    :empty "x"
    :white {:king "K" :queen "Q" :knight "N" :bishop "B" :rook "R" :pawn "P"}
    :black {:king "k" :queen "q" :knight "n" :bishop "b" :rook "r" :pawn "p"}
    :start (fn [color] [(color :rook) (color :knight) (color :bishop) (color :queen)
                        (color :king) (color :bishop) (color :knight) (color :rook)])})

(defn fill-row [n piece]
  (into [] (repeat n piece)))

(defn create-new-board-state []
  {:board
   [((pieces :start) (pieces :black))
    (fill-row 8 (-> pieces :black :pawn))
    (fill-row 8 (pieces :empty))
    (fill-row 8 (pieces :empty))
    (fill-row 8 (pieces :empty))
    (fill-row 8 (pieces :empty))
    (fill-row 8 (-> pieces :white :pawn))
    ((pieces :start) (pieces :white))]
   :active-color "w"
   :castling "KQkq"
   :enpassantable []
   :half-move-clock 0
   :full-move-clock 0})

(defn is-white-piece? [piece]
  (some #(= piece %) (vals (:white pieces))))

(defn is-black-piece? [piece]
  (some #(= piece %) (vals (:black pieces))))

(defn color? [piece]
  (cond
      (is-white-piece? piece) :white
      (is-black-piece? piece) :black
      :else nil))

(defn are-pieces-same-color? [piece1 piece2]
  (= (color? piece1) (color? piece2)))

(defn get-square [board [x y]]
  (get-in board [x y]))

(defn legal-move? [board-state [fromx fromy] [tox toy]]
  (let [moving-piece (get-square (:board board-state) [fromx fromy])
        to-position (get-square (:board board-state) [tox toy])]
    (and
     (not (are-pieces-same-color? moving-piece to-position))
     )))

(defn id-piece [piece]
  (let [color (color? piece)]
    (cond
      (nil? color) nil
      :else [color ((clojure.set/map-invert (color pieces)) piece)])))

;; just moves pieces from one position to another,
;; overriding the target position (no verification of moves yet)
(defn move [board-state [fromx fromy] [tox toy]]
  (let
    [board (:board board-state)
     from-piece (get-square board [fromx fromy])]
    (-> board
        (assoc-in [tox toy] from-piece)
        (assoc-in [fromx fromy] (pieces :empty)))))
