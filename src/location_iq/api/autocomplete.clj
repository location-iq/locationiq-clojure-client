(ns location-iq.api.autocomplete
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


(defn-spec autocomplete-with-http-info any?
  "
  The Autocomplete API is a variant of the Search API that returns place predictions in response to an HTTP request.  The request specifies a textual search string and optional geographic bounds.  The service can be used to provide autocomplete functionality for text-based geographic searches, by returning places such as businesses, addresses and points of interest as a user types. The Autocomplete API can match on full words as well as substrings. Applications can therefore send queries as the user types, to provide on-the-fly place predictions."
  ([q string?, normalizecity int?, ] (autocomplete-with-http-info q normalizecity nil))
  ([q string?, normalizecity int?, {:keys [limit viewbox bounded countrycodes accept-language tag]} (s/map-of keyword? any?)]
   (check-required-params q normalizecity)
   (call-api "/autocomplete.php" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"q" q "limit" limit "viewbox" viewbox "bounded" bounded "countrycodes" countrycodes "normalizecity" normalizecity "accept-language" accept-language "tag" tag }
              :form-params   {}
              :content-types []
              :accepts       ["application/json"]
              :auth-names    ["key"]})))

(defn-spec autocomplete (s/coll-of any?)
  "
  The Autocomplete API is a variant of the Search API that returns place predictions in response to an HTTP request.  The request specifies a textual search string and optional geographic bounds.  The service can be used to provide autocomplete functionality for text-based geographic searches, by returning places such as businesses, addresses and points of interest as a user types. The Autocomplete API can match on full words as well as substrings. Applications can therefore send queries as the user types, to provide on-the-fly place predictions."
  ([q string?, normalizecity int?, ] (autocomplete q normalizecity nil))
  ([q string?, normalizecity int?, optional-params any?]
   (let [res (:data (autocomplete-with-http-info q normalizecity optional-params))]
     (if (:decode-models *api-context*)
        (st/decode (s/coll-of any?) res st/string-transformer)
        res))))


