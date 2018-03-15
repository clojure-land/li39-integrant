(ns li38.nrepl
  (:gen-class)
  (:require [clojure.tools.nrepl.server :as nrepl-server]
            [cider.nrepl :refer [cider-nrepl-handler]]))

(defn -main
  []
  (println "Starting nREPL on port 7888")
  (nrepl-server/start-server :port 7888 :handler cider-nrepl-handler))
