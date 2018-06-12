(ns blog.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [blog.db :as db]))

(reg-event-db
 :app/db
 (constantly db/default-state))

(reg-event-db
 :set-active-page
 (fn [db [_ {:keys [:handler] :as match}]]
   (-> db
       (assoc :active-page match))))
