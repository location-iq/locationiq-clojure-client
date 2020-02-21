(ns location-iq.api.reverse
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


(defn-spec reverse-with-http-info any?
  "Reverse Geocoding
  Reverse geocoding is the process of converting a coordinate or location (latitude, longitude) to a readable address or place name. This permits the identification of nearby street addresses, places, and/or area subdivisions such as a neighborhood, county, state, or country."
  ([lat float?, lon float?, format string?, normalizecity int?, ] (reverse-with-http-info lat lon format normalizecity nil))
  ([lat float?, lon float?, format string?, normalizecity int?, {:keys [addressdetails accept-language namedetails extratags statecode showdistance postaladdress]} (s/map-of keyword? any?)]
   (check-required-params lat lon format normalizecity)
   (call-api "/reverse.php" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"lat" lat "lon" lon "format" format "normalizecity" normalizecity "addressdetails" addressdetails "accept-language" accept-language "namedetails" namedetails "extratags" extratags "statecode" statecode "showdistance" showdistance "postaladdress" postaladdress }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["key"]})))

(defn-spec reverse location-spec
  "Reverse Geocoding
  Reverse geocoding is the process of converting a coordinate or location (latitude, longitude) to a readable address or place name. This permits the identification of nearby street addresses, places, and/or area subdivisions such as a neighborhood, county, state, or country."
  ([lat float?, lon float?, format string?, normalizecity int?, ] (reverse lat lon format normalizecity nil))
  ([lat float?, lon float?, format string?, normalizecity int?, optional-params any?]
   (let [res (:data (reverse-with-http-info lat lon format normalizecity optional-params))]
     (if (:decode-models *api-context*)
        (st/decode location-spec res st/string-transformer)
        res))))


