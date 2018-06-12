(ns blog.components.panels
  (:require
    [re-frame.core :refer [subscribe]]
    [blog.router :as router]))

(defn main-panel [state]
  (let [page-handler (-> state :active-page :handler)]
    [:div {}
     [:p [:a {:href (router/path-for :home)} "Home"]]
     [:p [:a {:href (router/path-for :view-1)} "View-1"]]
     [:p [:a {:href (router/path-for :view-2)} "View-2"]]
     (case page-handler
       :view-1 [:p "View 1"]
       :view-2 [:p "View 2"]
       [:p "Home panel"])]))
