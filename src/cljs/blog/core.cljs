(ns blog.core
  (:require
   [re-frame.core :as re-frame]
   [reagent.core  :as reagent]
   [blog.events] ;; events handlers
   [blog.subs]
   [blog.components.panels :as panels]
   [blog.router :as router]))

(defn- main []
  (let [state (re-frame/subscribe [:app])]
    (fn [] [panels/main-panel @state])))


(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (.clear js/console)
  (reagent/render [main]
                  (.getElementById js/document "app"))
  (router/init))

(defn ^:export init []
  (re-frame/dispatch-sync [:app/db])
  (router/set-page-after-browser-url-change)
  (mount-root))
