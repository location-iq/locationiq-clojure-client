(ns location-iq.specs.error
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def error-data
  {
   (ds/opt :error) string?
   })

(def error-spec
  (ds/spec
    {:name ::error
     :spec error-data}))
