(ns li38.v1
  (:require [integrant.core :as ig]
            [ring.adapter.jetty :as ring-jetty]))

(def config
  {:handler/time {:prefix "It's "
                  :suffix "."}
   :ring/jetty   {:port 3004
                  :join? false
                  :handler (ig/ref :handler/time)}})

(defmethod ig/init-key :handler/time [_ config]
  (fn [{:keys [request-method uri] :as req}]
    {:status 200
     :body (str (:prefix config) (java.util.Date.) (:suffix config))
     :headers {"Content-Type" "text/plain"}}))

(defmethod ig/init-key :ring/jetty [_ {:keys [handler port join?]}]
  (ring-jetty/run-jetty handler {:port port :join? join?}))

(def system (ig/init config))
;;=> {:handler/time #function[li38.v1/eval8065/fn--8066/fn--8068],
;;    :ring/jetty   #object[org.eclipse.jetty.server.Server@31d65f2b"]}
