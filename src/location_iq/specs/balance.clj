(ns location-iq.specs.balance
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [location-iq.specs.daybalance :refer :all]
            )
  (:import (java.io File)))


(def balance-data
  {
   (ds/opt :status) string?
   (ds/opt :balance) daybalance-spec
   })

(def balance-spec
  (ds/spec
    {:name ::balance
     :spec balance-data}))
