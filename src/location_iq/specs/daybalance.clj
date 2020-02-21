(ns location-iq.specs.daybalance
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def daybalance-data
  {
   (ds/opt :day) int?
   (ds/opt :bonus) int?
   })

(def daybalance-spec
  (ds/spec
    {:name ::daybalance
     :spec daybalance-data}))
