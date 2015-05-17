(ns chess-server.utils)

;; given a date object, and a format(string) 
;; returns the given date in given format as string
(defn getFormattedDate [date format]
  (let [sdf (new java.text.SimpleDateFormat format)]
    (. sdf format date)
    )
  )
