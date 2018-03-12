(ns user
  (:require [cemerick.pomegranate :as pomegranate]))

(defmacro add-dependency [dep-vec]
  (binding [*print-namespace-maps* false]
    (println "Adding" (pr-str {(first dep-vec) {:mvn/version (last dep-vec)}})))
  `(pomegranate/add-dependencies
    :coordinates ['~dep-vec]
    :repositories (assoc ~cemerick.pomegranate.aether/maven-central
                         "clojars" "https://clojars.org/repo")))

#_
(defn add-dependency* [dep-vec]
  (pomegranate/add-dependencies
    :coordinates [dep-vec]
    :repositories (assoc cemerick.pomegranate.aether/maven-central
                         "clojars" "https://clojars.org/repo")))
