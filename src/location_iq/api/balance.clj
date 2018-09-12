(ns location-iq.api.balance
  (:require [location-iq.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn balance-with-http-info
  "
  The Balance API provides a count of request credits left in the user's account for the day. Balance is reset at midnight UTC everyday (00:00 UTC)."
  []
  (call-api "/balance.php" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json"]
             :auth-names    ["key"]}))

(defn balance
  "
  The Balance API provides a count of request credits left in the user's account for the day. Balance is reset at midnight UTC everyday (00:00 UTC)."
  []
  (:data (balance-with-http-info)))

