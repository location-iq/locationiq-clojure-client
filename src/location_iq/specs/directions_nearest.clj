(ns location-iq.specs.directions-nearest
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [location-iq.specs.directions-nearest-waypoints :refer :all]
            )
  (:import (java.io File)))


(def directions-nearest-data
  {
   (ds/opt :code) string?
   (ds/opt :waypoints) (s/coll-of directions-nearest-waypoints-spec)
   })

(def directions-nearest-spec
  (ds/spec
    {:name ::directions-nearest
     :spec directions-nearest-data}))
