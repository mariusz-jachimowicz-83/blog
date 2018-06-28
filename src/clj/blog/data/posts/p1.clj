(ns blog.data.posts.p1
  (:require
   [blog.atomic-design.atoms :as a]))

(defn metadata []
  {:id "p1"
   :title "Quick prototype with Duct"
   :created-at "2018-06-06"
   :intro a/lorem-ipsum
   :content
   [:div
    [:p.card-text a/lorem-ipsum]
    [a/code-block "clojure" "(def a 10)"]]})
