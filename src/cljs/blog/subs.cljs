(ns blog.subs
  (:require
    [re-frame.core :as re-frame]))

(re-frame/reg-sub :app identity)
