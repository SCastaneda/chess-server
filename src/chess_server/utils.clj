(ns chess-server.utils)

(defn getFormattedDate [date format]
  "given a date object, and a format(string)
   returns the given date in given format as string"
  (let [sdf (new java.text.SimpleDateFormat format)]
    (. sdf format date)))
