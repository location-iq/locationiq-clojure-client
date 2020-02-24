(ns location-iq.specs.directions-matrix-sources
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def directions-matrix-sources-data
  {
   (ds/opt :distance) float?
   (ds/opt :location) (s/coll-of float?)
   (ds/opt :name) string?
   })

(def directions-matrix-sources-spec
  (ds/spec
    {:name ::directions-matrix-sources
     :spec directions-matrix-sources-data}))
