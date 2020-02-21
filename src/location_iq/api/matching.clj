(ns location-iq.api.matching
  (:require [location-iq.core :refer [call-api check-required-params with-collection-format *api-context*]]
            [clojure.spec.alpha :as s]
            [spec-tools.core :as st]
            [orchestra.core :refer [defn-spec]]
            [location-iq.specs.directions-directions-routes :refer :all]
            [location-iq.specs.directions-directions :refer :all]
            [location-iq.specs.address :refer :all]
            [location-iq.specs.directions-nearest-waypoints :refer :all]
            [location-iq.specs.directions-nearest :refer :all]
            [location-iq.specs.error :refer :all]
            [location-iq.specs.matchquality :refer :all]
            [location-iq.specs.directions-matrix :refer :all]
            [location-iq.specs.balance :refer :all]
            [location-iq.specs.daybalance :refer :all]
            [location-iq.specs.directions-matching :refer :all]
            [location-iq.specs.location :refer :all]
            [location-iq.specs.directions-matrix-sources :refer :all]
            [location-iq.specs.namedetails :refer :all]
            )
  (:import (java.io File)))


(defn-spec matching-with-http-info any?
  "Matching Service
  Matching API matches or snaps given GPS points to the road network in the most plausible way.  Please note the request might result multiple sub-traces.  Large jumps in the timestamps (> 60s) or improbable transitions lead to trace splits if a complete matching could not be found. The algorithm might not be able to match all points. Outliers are removed if they can not be matched successfully."
  ([coordinates string?, ] (matching-with-http-info coordinates nil))
  ([coordinates string?, {:keys [generate_hints approaches exclude bearings radiuses steps annotations geometries overview timestamps gaps tidy waypoints]} (s/map-of keyword? any?)]
   (check-required-params coordinates)
   (call-api "/matching/driving/{coordinates}" :get
             {:path-params   {"coordinates" coordinates }
              :header-params {}
              :query-params  {"generate_hints" generate_hints "approaches" approaches "exclude" exclude "bearings" bearings "radiuses" radiuses "steps" steps "annotations" annotations "geometries" geometries "overview" overview "timestamps" timestamps "gaps" gaps "tidy" tidy "waypoints" waypoints }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["key"]})))

(defn-spec matching directions-matching-spec
  "Matching Service
  Matching API matches or snaps given GPS points to the road network in the most plausible way.  Please note the request might result multiple sub-traces.  Large jumps in the timestamps (> 60s) or improbable transitions lead to trace splits if a complete matching could not be found. The algorithm might not be able to match all points. Outliers are removed if they can not be matched successfully."
  ([coordinates string?, ] (matching coordinates nil))
  ([coordinates string?, optional-params any?]
   (let [res (:data (matching-with-http-info coordinates optional-params))]
     (if (:decode-models *api-context*)
        (st/decode directions-matching-spec res st/string-transformer)
        res))))


