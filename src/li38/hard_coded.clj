(ns li38.hard-coded
  "Hard coded web service"
  (:require [ring.adapter.jetty :as ring-jetty]))

(defn time-handler [{:keys [request-method uri] :as req}]
  {:status 200
   :body (str "It's currently " (java.util.Date.) ". CODING TIME!")
   :headers {"Content-Type" "text/plain"}})

#_
(def server
  (ring-jetty/run-jetty time-handler {:port 3000 :join? false}))

#_
(.stop server)
