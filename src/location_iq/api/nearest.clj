(ns location-iq.api.nearest
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


(defn-spec nearest-with-http-info any?
  "Nearest Service
  Snaps a coordinate to the street network and returns the nearest n matches. Where coordinates only supports a single {longitude},{latitude} entry."
  ([coordinates string?, ] (nearest-with-http-info coordinates nil))
  ([coordinates string?, {:keys [generate_hints exclude bearings radiuses approaches number]} (s/map-of keyword? any?)]
   (check-required-params coordinates)
   (call-api "/nearest/driving/{coordinates}" :get
             {:path-params   {"coordinates" coordinates }
              :header-params {}
              :query-params  {"generate_hints" generate_hints "exclude" exclude "bearings" bearings "radiuses" radiuses "approaches" approaches "number" number }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["key"]})))

(defn-spec nearest directions-nearest-spec
  "Nearest Service
  Snaps a coordinate to the street network and returns the nearest n matches. Where coordinates only supports a single {longitude},{latitude} entry."
  ([coordinates string?, ] (nearest coordinates nil))
  ([coordinates string?, optional-params any?]
   (let [res (:data (nearest-with-http-info coordinates optional-params))]
     (if (:decode-models *api-context*)
        (st/decode directions-nearest-spec res st/string-transformer)
        res))))


