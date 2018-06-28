(ns blog.atomic-design.atoms
  #?(:cljs
     (:require
      [cljsjs.react-highlight]
      [reagent.core :as reagent])))

;; Important notes !!!
;; - blog resources (for instance posts) are sent from server using transit format
;; - resources are sent as maps that contains also hiccup markup
;; - in clj all tags that represent custom components are defined as symbols by using def.
;;   It was simplest way to make those resources transferable over the wire

#?(:cljs
(def highlight (reagent/adapt-react-class js/Highlight)))

#?(:clj
(def highlight `highlight))

(def ^:const lorem-ipsum
  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")

#?(:cljs
(defn code-block [language src-code]
  [:div.code-block
   [:span.code-block-lng language]
   [highlight {:language language} src-code]]))

#?(:clj
(def code-block `code-block))
