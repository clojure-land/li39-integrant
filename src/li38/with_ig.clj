(ns li38.with-ig
  "Web service using integrant."
  (:require [integrant.core :as ig]
            [ring.adapter.jetty :as ring-jetty]))

(def config
  {:handler/time {:prefix "It's currently "
                  :suffix ", coding time!"}
   :ring/jetty {:handler (ig/ref :handler/time)}})

(defmethod ig/prep-key :ring/jetty [_ config]
  (merge {:port 3000
          :join? false}
         config))

(defmethod ig/init-key :handler/time [_ config]
  (fn [{:keys [request-method uri] :as req}]
    {:status 200
     :body (str (:prefix config) (java.util.Date.) (:suffix config))
     :headers {"Content-Type" "text/plain"}}))

(defmethod ig/init-key :ring/jetty [_ {:keys [port join? handler]}]
  (ring-jetty/run-jetty handler {:port port :join? join?}))

(defmethod ig/halt-key! :ring/jetty [_ server]
  (.stop server))

(defmethod ig/suspend-key! :ring/jetty [_ server]
  (.stop server))

(defmethod ig/resume-key :ring/jetty [key config old-config server]
  (if (= config old-config)
    (.start server)
    (ig/init-key key config)))

(def system
  (ig/init (ig/prep config)))
