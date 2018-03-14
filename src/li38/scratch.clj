(ns li38.scratch
  (:require [integrant.core :as ig :refer :all]
            [ring.adapter.jetty :as ring-jetty]))

(derive :aaa/aaa :bbb/bbb)
(derive :bbb/bbb :ccc/ccc)

(isa? :aaa/aaa :bbb/bbb)
;;=> true

(isa? :aaa/aaa :ccc/ccc)



(defmulti emote identity)

(defmethod emote :animal/cat [_]
  "roooaaaar")

(defmethod emote :animal/dog [_]
  "bark")

(emote :animal/cat)
;;=> "roooaaaar"

(emote :animal/dog)
;;=> "bark"

(derive :animal/tiger :animal/cat)

(emote :animal/tiger)
;;=> "roooaaaar"

(derive :handler/time :ring/handler)
(derive :handler/lime :ring/handler)

(ig/find-derived {:handler/time "timey"
                  :handler/lime "tasty"}
                 :ring/handler)

;;=>([:handler/time "timey"] [:handler/lime "tasty"])


(ns-publics 'integrant.core)
