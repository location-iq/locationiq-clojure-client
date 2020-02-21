(ns location-iq.specs.directions-directions
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [location-iq.specs.directions-directions-routes :refer :all]
            )
  (:import (java.io File)))


(def directions-directions-data
  {
   (ds/opt :code) string?
   (ds/opt :waypoints) (s/coll-of any?)
   (ds/opt :routes) (s/coll-of directions-directions-routes-spec)
   })

(def directions-directions-spec
  (ds/spec
    {:name ::directions-directions
     :spec directions-directions-data}))
