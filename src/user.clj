(ns user
  (:require [integrant.repl :as ig-repl]
            [integrant.core :as ig]))

(defn load-config []
  (ig/read-string (slurp "config.edn")))

(ig-repl/set-prep! (fn []
                     (let [config (load-config)]
                       (ig/load-namespaces config)
                       (ig/prep config))))
