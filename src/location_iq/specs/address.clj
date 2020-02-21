(ns location-iq.specs.address
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def address-data
  {
   (ds/opt :house_number) string?
   (ds/opt :road) string?
   (ds/opt :residential) string?
   (ds/opt :borough) string?
   (ds/opt :neighbourhood) string?
   (ds/opt :quarter) string?
   (ds/opt :hamlet) string?
   (ds/opt :suburb) string?
   (ds/opt :island) string?
   (ds/opt :village) string?
   (ds/opt :town) string?
   (ds/opt :city) string?
   (ds/opt :city_district) string?
   (ds/opt :county) string?
   (ds/opt :state) string?
   (ds/opt :state_district) string?
   (ds/opt :postcode) string?
   (ds/opt :country) string?
   (ds/opt :country_code) string?
   (ds/opt :state_code) string?
   })

(def address-spec
  (ds/spec
    {:name ::address
     :spec address-data}))
