(ns user
  (:require [li38.core :refer [load-config]]
            [integrant.core :as ig]
            [integrant.repl :as ig-repl :refer [go reset reset-all]]
            [cemerick.pomegranate :as pomegranate]))

(ig-repl/set-prep! (fn []
                     (let [config (load-config)]
                       (ig/load-namespaces config)
                       (ig/prep config))))

;; (go)
;;=> :initiated

(defmacro add-dependency [dep-vec]
  (binding [*print-namespace-maps* false]
    (println "Adding" (pr-str {(first dep-vec) {:mvn/version (last dep-vec)}})))
  `(pomegranate/add-dependencies
    :coordinates ['~dep-vec]
    :repositories (assoc ~cemerick.pomegranate.aether/maven-central
                         "clojars" "https://clojars.org/repo")))
