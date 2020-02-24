(ns location-iq.specs.directions-directions-routes
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def directions-directions-routes-data
  {
   (ds/opt :legs) (s/coll-of any?)
   (ds/opt :weight_name) string?
   (ds/opt :geometry) string?
   (ds/opt :weight) float?
   (ds/opt :distance) float?
   (ds/opt :duration) float?
   })

(def directions-directions-routes-spec
  (ds/spec
    {:name ::directions-directions-routes
     :spec directions-directions-routes-data}))
