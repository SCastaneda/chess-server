(ns chess-server.routes
  (:use
   chess-server.chess
   chess-server.utils
   [compojure.route :only [files not-found]]
   [compojure.handler :only [site]] ; form, query params decode; cookie; session, etc
   [compojure.core :only [defroutes GET POST PUT DELETE ANY context]]
   [ring.middleware.json :as middleware]
   org.httpkit.server))

(defn update-userinfo [req]          ;; ordinary clojure function
  (let [user-id (-> req :params :id) ; param from uri
        password (-> req :body :password)] ; form param
    {:status 200
     :body { :id user-id :pw password :req (str req)}}))

(defn start-new-game [req]
  {
   :status 200
   :body { :hash "dtesting!" :board (create-new-board)}})

(defroutes all-routes
  (GET "/new-game" [] start-new-game))

;; middleware
(defn logRequest [app]
  (fn [req]
    (println (getFormattedDate (java.util.Date.) "YYYY-MM-dd:hhmmss") "-" (str req) "\n")
    (app req)))

(def app
  (->
      (compojure.handler/site all-routes)
      (middleware/wrap-json-body {:keywords? true})
      middleware/wrap-json-response
      logRequest))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn start-server [port]
  (reset! server (run-server #'app {:port port})))
