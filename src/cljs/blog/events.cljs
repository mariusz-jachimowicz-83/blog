(ns blog.events
  (:require
   [clojure.walk :refer [postwalk]]
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [ajax.core :as ajax]
   [blog.db   :as db]
   [blog.atomic-design.atoms :as a :refer [code-block]]))

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

;;
;; IMPORTANT TRICK
;;
(defn hiccup-symbols->vars
  "Replace raw symbols in hiccup returned from server into vars so that UI will be created correctly.
   It's because symbols of my custom hiccup forms aren't resolved as an vars, I don't know why.
   So I am replacing them in a way that they are resolved properly further"
  [content]
  (postwalk
   (fn [form]
     (if (and (vector? form)
              (symbol? (first form))
              (symbol-identical? (first form) `a/code-block))
       (into [a/code-block] (rest form))
       form))
   content))

(reg-event-db
 :get-posts-ok
 (fn [db [_ result]]
   (assoc db :posts (hiccup-symbols->vars result))))

(reg-event-db
 :get-posts-fail
 (fn [db [_ result]]
   (println "-- api failure")
   (println result)
   db))
