(ns chess-server.core
  (:gen-class))

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

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn fill-row [n piece] 
  (cond
    (= n 0) nil
    true (cons piece (empty-row (- n 1)))
   )
  )

(defn create-new-board []
  '((wrook wknight wbishop wqueen wking wbishop wknight wrook)
    (fill-row 8 wpawn)
    (fill-row 8 empty-s) 
    (fill-row 8 empty-s) 
    (fill-row 8 empty-s) 
    (fill-row 8 empty-s)
    (fill-row 8 bpawn)
    (wrook wknight wbishop wqueen wking wbishop wknight wrook)
    )
  )
