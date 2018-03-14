(ns li38.ring
  (:require [integrant.core :as ig]
            [ring.adapter.jetty :as ring-jetty]))

(defmethod ig/init-key :li38.ring/jetty [_ {:keys [handler port join?]}]
  (ring-jetty/run-jetty handler {:port port :join? join?}))

(defmethod ig/halt-key! :li38.ring/jetty [_ server]
  (.stop server))

(defmethod ig/suspend-key! :li38.ring/jetty [_ server]
  (.stop server))

(defmethod ig/resume-key :li38.ring/jetty [key opts old-opts server]
  (if (= opts old-opts)
    (.start server)
    (do
      (ig/halt-key! key server)
      (ig/init-key key opts))))
