(ns blog.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [ajax.core :as ajax]
   [blog.db   :as db]))

(reg-event-db
 :app/db
 (constantly db/default-state))

(reg-event-db
 :set-active-page
 (fn [db [_ {:keys [:handler] :as match}]]
   (-> db
       (assoc :active-page match))))

(reg-event-fx
 :get-posts
 (fn
   [{db :db} _]
   {:http-xhrio {:method          :get
                 :uri             "http://localhost:5000"
                 :format          (ajax/transit-request-format)
                 :response-format (ajax/transit-response-format {:keywords? true}) 
                 :on-success      [:get-posts-ok]
                 :on-failure      [:get-posts-fail]}
    :db  (assoc db :loading? true)}))

(reg-event-db
 :get-posts-ok
 (fn [db [_ result]]
   (println "-- ok")
   (println result)
   db))

(reg-event-db
 :get-posts-fail
 (fn [db [_ result]]
   (println "-- fail")
   (println result)
   db))
