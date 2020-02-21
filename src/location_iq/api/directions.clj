(ns location-iq.api.directions
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


(defn-spec directions-with-http-info any?
  "Directions Service
  Finds the fastest route between coordinates in the supplied order."
  ([coordinates string?, ] (directions-with-http-info coordinates nil))
  ([coordinates string?, {:keys [bearings radiuses generate_hints approaches exclude alternatives steps annotations geometries overview continue_straight]} (s/map-of keyword? any?)]
   (check-required-params coordinates)
   (call-api "/directions/driving/{coordinates}" :get
             {:path-params   {"coordinates" coordinates }
              :header-params {}
              :query-params  {"bearings" bearings "radiuses" radiuses "generate_hints" generate_hints "approaches" approaches "exclude" exclude "alternatives" alternatives "steps" steps "annotations" annotations "geometries" geometries "overview" overview "continue_straight" continue_straight }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["key"]})))

(defn-spec directions directions-directions-spec
  "Directions Service
  Finds the fastest route between coordinates in the supplied order."
  ([coordinates string?, ] (directions coordinates nil))
  ([coordinates string?, optional-params any?]
   (let [res (:data (directions-with-http-info coordinates optional-params))]
     (if (:decode-models *api-context*)
        (st/decode directions-directions-spec res st/string-transformer)
        res))))


