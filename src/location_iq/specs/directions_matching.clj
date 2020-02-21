(ns location-iq.specs.directions-matching
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def directions-matching-data
  {
   (ds/opt :code) string?
   (ds/opt :tracepoints) (s/coll-of any?)
   (ds/opt :matchings) (s/coll-of any?)
   })

(def directions-matching-spec
  (ds/spec
    {:name ::directions-matching
     :spec directions-matching-data}))
