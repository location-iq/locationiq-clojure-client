(ns location-iq.specs.location
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [location-iq.specs.address :refer :all]
            [location-iq.specs.namedetails :refer :all]
            [location-iq.specs.matchquality :refer :all]
            )
  (:import (java.io File)))


(def location-data
  {
   (ds/opt :distance) float?
   (ds/opt :place_id) string?
   (ds/opt :licence) string?
   (ds/opt :osm_type) string?
   (ds/opt :osm_id) string?
   (ds/opt :boundingbox) (s/coll-of string?)
   (ds/opt :lat) string?
   (ds/opt :lon) string?
   (ds/opt :display_name) string?
   (ds/opt :class) string?
   (ds/opt :type) string?
   (ds/opt :importance) float?
   (ds/opt :address) address-spec
   (ds/opt :namedetails) namedetails-spec
   (ds/opt :matchquality) matchquality-spec
   })

(def location-spec
  (ds/spec
    {:name ::location
     :spec location-data}))
