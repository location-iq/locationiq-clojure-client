(ns location-iq.specs.matchquality
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            )
  (:import (java.io File)))


(def matchquality-data
  {
   (ds/opt :matchcode) string?
   (ds/opt :matchtype) string?
   (ds/opt :matchlevel) string?
   })

(def matchquality-spec
  (ds/spec
    {:name ::matchquality
     :spec matchquality-data}))
