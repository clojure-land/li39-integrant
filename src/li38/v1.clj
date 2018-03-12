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

(defmethod ig/halt-key! :ring/jetty [_ server]
  (.stop server))

(defmethod ig/suspend-key! :ring/jetty [_ server]
  (.stop server))

(defmethod ig/resume-key :ring/jetty [key opts old-opts server]
  (if (= opts old-opts)
    (.start server)
    (do
      (ig/halt-key! key server)
      (ig/init-key key opts))))

(def system
  (ig/init config))
;;=> {:handler/time #function[li38.v1/eval8065/fn--8066/fn--8068],
;;    :ring/jetty   #object[org.eclipse.jetty.server.Server@31d65f2b"]}

(ig/init config)
;;=> #:handler{:time #function[li38.v1/eval8089/fn--8090/fn--8092]}

(ig/read-string (slurp "config.edn"))

config
(prn-str
 (ig/ref :handler/time))
"#integrant.core.Ref{:key :handler/time}\n"^

(ig/halt!
 system)

(let [x (fn [])
      y (fn [])]
  (= x y ))

(.start (:ring/jetty system))

(ig/suspend! system)
(ig/resume config system)
