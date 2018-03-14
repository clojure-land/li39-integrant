(ns li38.core
  (:require [integrant.core :as ig]))

(defn load-config []
  (ig/read-string (slurp "config.edn")))
