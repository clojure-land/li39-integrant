(ns li38.handler
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :li38.handler/time [_ config]
  (fn [{:keys [request-method uri] :as req}]
    {:status 200
     :body (str (:prefix config) (java.util.Date.) (:suffix config))
     :headers {"Content-Type" "text/plain"}}))
