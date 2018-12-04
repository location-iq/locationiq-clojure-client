(ns location-iq.api.search
  (:require [location-iq.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn search-with-http-info
  "Forward Geocoding
  The Search API allows converting addresses, such as a street address, into geographic coordinates (latitude and longitude). These coordinates can serve various use-cases, from placing markers on a map to helping algorithms determine nearby bus stops. This process is also known as Forward Geocoding."
  ([q format normalizecity ] (search-with-http-info q format normalizecity nil))
  ([q format normalizecity {:keys [addressdetails viewbox bounded limit accept-language countrycodes namedetails dedupe extratags statecode ]}]
   (check-required-params q format normalizecity)
   (call-api "/search.php" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"q" q "format" format "normalizecity" normalizecity "addressdetails" addressdetails "viewbox" viewbox "bounded" bounded "limit" limit "accept-language" accept-language "countrycodes" countrycodes "namedetails" namedetails "dedupe" dedupe "extratags" extratags "statecode" statecode }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["key"]})))

(defn search
  "Forward Geocoding
  The Search API allows converting addresses, such as a street address, into geographic coordinates (latitude and longitude). These coordinates can serve various use-cases, from placing markers on a map to helping algorithms determine nearby bus stops. This process is also known as Forward Geocoding."
  ([q format normalizecity ] (search q format normalizecity nil))
  ([q format normalizecity optional-params]
   (:data (search-with-http-info q format normalizecity optional-params))))

