(ns location-iq.api.reverse
  (:require [location-iq.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn reverse-with-http-info
  "Reverse Geocoding
  Reverse geocoding is the process of converting a coordinate or location (latitude, longitude) to a readable address or place name. This permits the identification of nearby street addresses, places, and/or area subdivisions such as a neighborhood, county, state, or country."
  ([lat lon format normalizecity ] (reverse-with-http-info lat lon format normalizecity nil))
  ([lat lon format normalizecity {:keys [addressdetails accept-language namedetails extratags ]}]
   (check-required-params lat lon format normalizecity)
   (call-api "/reverse.php" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"lat" lat "lon" lon "format" format "normalizecity" normalizecity "addressdetails" addressdetails "accept-language" accept-language "namedetails" namedetails "extratags" extratags }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["key"]})))

(defn reverse
  "Reverse Geocoding
  Reverse geocoding is the process of converting a coordinate or location (latitude, longitude) to a readable address or place name. This permits the identification of nearby street addresses, places, and/or area subdivisions such as a neighborhood, county, state, or country."
  ([lat lon format normalizecity ] (reverse lat lon format normalizecity nil))
  ([lat lon format normalizecity optional-params]
   (:data (reverse-with-http-info lat lon format normalizecity optional-params))))

