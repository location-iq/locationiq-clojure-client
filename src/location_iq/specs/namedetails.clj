(ns location-iq.specs.namedetails
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def namedetails-data
  {
   (ds/opt :name) string?
   })

(def namedetails-spec
  (ds/spec
    {:name ::namedetails
     :spec namedetails-data}))
