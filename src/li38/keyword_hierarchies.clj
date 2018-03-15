(derive :ring/jetty :http/server)

(isa? :ring/jetty :http/server)
;;=> true

(derive ::A ::B)
(derive ::B ::C)

(isa? ::A ::C)
;;=> true

(isa? ::A ::A)
;;=> true

(isa? 7 7)
;;=> true

(isa? "string" "string")
;;=> true

(isa? {:foo :bar} {:foo :bar})
;;=> true




(require '[integrant.core :as ig])

(derive :handler/time :ring/handler)
(derive :handler/lime :ring/handler)

(ig/find-derived {:handler/time "timey"
                  :handler/lime "limey"}
                 :ring/handler)
;;=> ([:handler/time "timey"] [:handler/lime "limey"])
