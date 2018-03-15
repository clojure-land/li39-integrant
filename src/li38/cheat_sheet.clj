;; integrant.core
;; --------------

;; lifecycle functions

(init config)
(halt! system)
(suspend! system)
(resume config system)

(init config [:your/key])
(resume config system [:your/key])

;; multimethods

(init-key key value)
(halt-key! key impl)
(suspend-key! key impl)
(resume-key key value old-value old-impl)
