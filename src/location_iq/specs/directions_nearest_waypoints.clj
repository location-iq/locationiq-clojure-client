(ns location-iq.specs.directions-nearest-waypoints
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def directions-nearest-waypoints-data
  {
   (ds/opt :nodes) (s/coll-of float?)
   (ds/opt :distance) float?
   (ds/opt :location) (s/coll-of float?)
   (ds/opt :name) string?
   })

(def directions-nearest-waypoints-spec
  (ds/spec
    {:name ::directions-nearest-waypoints
     :spec directions-nearest-waypoints-data}))
