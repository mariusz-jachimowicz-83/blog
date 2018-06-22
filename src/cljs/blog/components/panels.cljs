(ns blog.components.panels
  (:require
    [cljsjs.react-highlight]
    [re-frame.core :as rf]
    [reagent.core  :as reagent]
    [blog.router   :as router]))

(def highlight (reagent/adapt-react-class js/Highlight))

(def ^:const lorem-ipsum
  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")

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

(defn blog-card [state]
  [:div.col-md-6
   [:div.card
    [:div.card-body
     [:h3 "Some title"]
     [:div.text-muted "2018-06-06"]
     [:p.card-text lorem-ipsum]
     [:a {:href "#"} "Continue reading"]]]])

(defn code-block [language src-code]
  [:div.code-block
   [:span.code-block-lng language]
   [highlight {:language language} src-code]])

(defn blog-post [state]
  [:div.col-md-12
   [:div.card
    [:div.card-body
     [:h3 "Some title "]
     [:div.text-muted "2018-06-06"]
     [:p.card-text lorem-ipsum]
     [code-block "clojure" "(def a 10)"]]]])

#_(defn render-code [this]
  (->> this
       reagent/dom-node
       (.highlightBlock js/hljs)))

#_(defn code-block [code]
  (reagent/create-class
   {:render (fn []
              [:pre>code.clj
               (with-out-str (pprint code))])
    :component-did-update render-code}))

(defn content [state]
  (let [_ (rf/dispatch [:get-posts])]
    [:div.content.container-fluid
     [:div.row [blog-card] [blog-card]]
     [:div.row [blog-post]]]))

(defn main-panel [state]
  [:div
   [menu-top]
   [content]]

  #_(let [page-handler (-> state :active-page :handler)]
    [:div {}
     [:p [:a {:href (router/path-for :home)} "Home3"]]
     [:p [:a {:href (router/path-for :view-1)} "View-1"]]
     [:p [:a {:href (router/path-for :view-2)} "View-2"]]
     (case page-handler
       :view-1 [:p "View 1"]
       :view-2 [:p "View 2"]
       [:p "Home panel 35"])]))
