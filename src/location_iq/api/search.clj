(ns location-iq.api.search
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


(defn-spec search-with-http-info any?
  "Forward Geocoding
  The Search API allows converting addresses, such as a street address, into geographic coordinates (latitude and longitude). These coordinates can serve various use-cases, from placing markers on a map to helping algorithms determine nearby bus stops. This process is also known as Forward Geocoding."
  ([q string?, format string?, normalizecity int?, ] (search-with-http-info q format normalizecity nil))
  ([q string?, format string?, normalizecity int?, {:keys [addressdetails viewbox bounded limit accept-language countrycodes namedetails dedupe extratags statecode matchquality postaladdress]} (s/map-of keyword? any?)]
   (check-required-params q format normalizecity)
   (call-api "/search.php" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"q" q "format" format "normalizecity" normalizecity "addressdetails" addressdetails "viewbox" viewbox "bounded" bounded "limit" limit "accept-language" accept-language "countrycodes" countrycodes "namedetails" namedetails "dedupe" dedupe "extratags" extratags "statecode" statecode "matchquality" matchquality "postaladdress" postaladdress }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["key"]})))

(defn-spec search (s/coll-of location-spec)
  "Forward Geocoding
  The Search API allows converting addresses, such as a street address, into geographic coordinates (latitude and longitude). These coordinates can serve various use-cases, from placing markers on a map to helping algorithms determine nearby bus stops. This process is also known as Forward Geocoding."
  ([q string?, format string?, normalizecity int?, ] (search q format normalizecity nil))
  ([q string?, format string?, normalizecity int?, optional-params any?]
   (let [res (:data (search-with-http-info q format normalizecity optional-params))]
     (if (:decode-models *api-context*)
        (st/decode (s/coll-of location-spec) res st/string-transformer)
        res))))


