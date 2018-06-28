(ns blog.components.panels
  (:require
   [cljsjs.react-highlight]
   [re-frame.core :as rf]
   [reagent.core  :as reagent]
   [blog.router   :as router]
   [blog.atomic-design.atoms :as a]))

(defn menu-top [state]
  [:nav.navbar.navbar-expand-lg.navbar-dark.bg-dark
   [:a.navbar-brand {:href "#"} "My Brand"]
   [:button.navbar-toggler {:type "button"
                            :data-toggle "collapse"
                            :data-target "#navbarNav"
                            :aria-controls "navbarNav"
                            :aria-expanded "false"
                            :aria-label "Toggle navigation"}
    [:span.navbar-toggler-icon]]
   [:div {:class "collapse navbar-collapse", :id "navbarNav"}
    [:ul {:class "navbar-nav"}
     [:li {:class "nav-item active"}
      [:a {:class "nav-link", :href "#"} "Home "
       [:span {:class "sr-only"} "(current)"]]]
     [:li {:class "nav-item"}
      [:a {:class "nav-link", :href "#"} "Blog"]]
     [:li {:class "nav-item"}
      [:a {:class "nav-link", :href "#"} "Videos"]]
     [:li {:class "nav-item"}
      [:a {:class "nav-link", :href "#"} "Projects"]]
     [:li {:class "nav-item"}
      [:a {:class "nav-link", :href "#"} "Privacy policy"]]
     [:li {:class "nav-item"}
      [:a {:class "nav-link", :href "#"} "About"]]
     [:li {:class "nav-item"}
      [:a {:class "nav-link disabled", :href "#"} "Disabled"]]]]])

(defn blog-card [{:keys [id title created-at intro] :as post}]
  [:div.col-md-6 {:key id}
   [:div.card
    [:div.card-body
     [:h3 title]
     [:div.text-muted created-at]
     [:p.card-text intro]
     [:a {:href "#"} "Continue reading"]]]])

(defn blog-post [{:keys [id title created-at content] :as post}]
  [:div.col-md-12 {:key id}
   [:div.card
    [:div.card-body
     [:h3 title]
     [:div.text-muted created-at]
     content]]])

(defn content [db]
  (let [posts (:posts db)
        _ (when-not false #_(:posts db)
            (rf/dispatch [:get-posts]))]
    [:div.content.container-fluid
     [:div.row (map blog-card posts)]
     [:div.row (-> posts first blog-post)]]))

(defn main-panel [db]
  [:div
   [menu-top]
   [content db]]

  #_(let [page-handler (-> state :active-page :handler)]
    [:div {}
     [:p [:a {:href (router/path-for :home)} "Home3"]]
     [:p [:a {:href (router/path-for :view-1)} "View-1"]]
     [:p [:a {:href (router/path-for :view-2)} "View-2"]]
     (case page-handler
       :view-1 [:p "View 1"]
       :view-2 [:p "View 2"]
       [:p "Home panel 35"])]))
