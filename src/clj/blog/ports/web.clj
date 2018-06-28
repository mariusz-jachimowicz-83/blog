(ns blog.ports.web
  (:require
   [compojure.core :refer [context GET routes]]
   [clojure.java.io       :as io]
   [compojure.route       :as route]
   [integrant.core        :as ig]
   [cognitect.transit     :as t]
   [ring.middleware.cors  :as cors]
   [blog.domain.resources :as resources]
   [ring.middleware.transit :refer [wrap-transit-response]]))

(defn ->transit-response [data-vec]
  {:status 200
   :headers {"Content-Type" "application/transit+json; charset=utf-8"}
   :body data-vec})

(defn api-routes []
  (routes
   (GET "/" [] (-> (resources/get-posts) vec ->transit-response))
   (route/resources "/")
   (route/not-found "<h1>Not Found :(</h1>")))

(defmethod ig/init-key :blog.ports/web [_ conf]
  (-> (api-routes)
      (wrap-transit-response)
      (cors/wrap-cors
       :access-control-allow-origin #".*"
       :access-control-allow-methods [:head :options :get :put :post :delete :patch])))
