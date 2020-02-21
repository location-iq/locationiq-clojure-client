(ns location-iq.specs.directions-matrix
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [location-iq.specs.directions-matrix-sources :refer :all]
            [location-iq.specs.directions-matrix-sources :refer :all]
            )
  (:import (java.io File)))


(def directions-matrix-data
  {
   (ds/opt :code) string?
   (ds/opt :distances) (s/coll-of float?)
   (ds/opt :fallback_speed_cells) (s/coll-of float?)
   (ds/opt :sources) (s/coll-of directions-matrix-sources-spec)
   (ds/opt :destinations) (s/coll-of directions-matrix-sources-spec)
   })

(def directions-matrix-spec
  (ds/spec
    {:name ::directions-matrix
     :spec directions-matrix-data}))
