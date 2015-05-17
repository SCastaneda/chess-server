(ns chess-server.chess)

;; define white pieces
(def wking "wK")
(def wqueen "wq")
(def wknight "wk")
(def wbishop "wb")
(def wrook "wr")
(def wpawn "wp")

;; define black pieces
(def bking "bK")
(def bqueen "bq")
(def bknight "bk")
(def bbishop "bb")
(def brook "br")
(def bpawn "bp")

(def empty-s "x") ;; empty square


(defn fill-row [n piece] 
  (cond
    (= n 0) []
    true (conj (fill-row (- n 1) piece) piece)
   )
  )

(defn create-new-board []
  [ 
   [wrook wknight wbishop wqueen wking wbishop wknight wrook]
   (fill-row 8 wpawn)
   (fill-row 8 empty-s) 
   (fill-row 8 empty-s) 
   (fill-row 8 empty-s) 
   (fill-row 8 empty-s)
   (fill-row 8 bpawn)
   [brook bknight bbishop bqueen bking bbishop bknight brook]
   ]
  )
  

(defn get-square [board x y]
  (nth (nth board y) x)
  )

;; just moves pieces from one position to another, 
;; overriding the target position (no verification of moves yet)
(defn move [board fromx fromy tox toy] 
  (let 
    [current (get-square board fromx fromy) 
    future (get-square board tox toy)]
    (assoc 
      ;; move piece to new position
      (assoc board toy (assoc (nth board toy) tox current))
      fromy
      ;; put an empty square at the current(from) position
      (assoc (nth board fromy) fromx empty-s)
      )
    )
  )
