(ns li38.with-ig
  "Web service using integrant."
  (:require [integrant.core :as ig]
            [ring.adapter.jetty :as ring-jetty]))

(def config
  {:handler/time {:prefix "It's currently "
                  :suffix ", coding time!"}

   :ring/jetty {:port 3000
                :join? false
                :handler (ig/ref :handler/time)}})

(defmethod ig/init-key :handler/time [_ config]
  (fn [{:keys [request-method uri] :as req}]
    {:status 200
     :body (str (:prefix config) (java.util.Date.) (:suffix config))
     :headers {"Content-Type" "text/plain"}}))

(defmethod ig/init-key :ring/jetty [_ {:keys [port join? handler]}]
  (ring-jetty/run-jetty handler {:port port :join? join?}))

(def system
  (ig/init config))
