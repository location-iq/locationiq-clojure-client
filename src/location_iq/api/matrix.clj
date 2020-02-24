(ns location-iq.api.matrix
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


(defn-spec matrix-with-http-info any?
  "Matrix Service
  Computes duration of the fastest route between all pairs of supplied coordinates. Returns the durations or distances or both between the coordinate pairs. Note that the distances are not the shortest distance between two coordinates, but rather the distances of the fastest routes."
  ([coordinates string?, ] (matrix-with-http-info coordinates nil))
  ([coordinates string?, {:keys [bearings radiuses generate_hints approaches exclude annotations sources destinations fallback_speed fallback_coordinate]} (s/map-of keyword? any?)]
   (check-required-params coordinates)
   (call-api "/matrix/driving/{coordinates}" :get
             {:path-params   {"coordinates" coordinates }
              :header-params {}
              :query-params  {"bearings" bearings "radiuses" radiuses "generate_hints" generate_hints "approaches" approaches "exclude" exclude "annotations" annotations "sources" sources "destinations" destinations "fallback_speed" fallback_speed "fallback_coordinate" fallback_coordinate }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["key"]})))

(defn-spec matrix directions-matrix-spec
  "Matrix Service
  Computes duration of the fastest route between all pairs of supplied coordinates. Returns the durations or distances or both between the coordinate pairs. Note that the distances are not the shortest distance between two coordinates, but rather the distances of the fastest routes."
  ([coordinates string?, ] (matrix coordinates nil))
  ([coordinates string?, optional-params any?]
   (let [res (:data (matrix-with-http-info coordinates optional-params))]
     (if (:decode-models *api-context*)
        (st/decode directions-matrix-spec res st/string-transformer)
        res))))


