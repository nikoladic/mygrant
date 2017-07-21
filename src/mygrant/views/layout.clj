(ns mygrant.views.layout
  (:require [hiccup.page :as h]))

(defn common [title & body]
  (h/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
    [:meta {:name "viewport" :content
            "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title title]
    (h/include-css "stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css")
    (h/include-css "stylesheets/style.css")]
   [:body
    [:div {:id "header" :class "page-header margin-basic"}
     [:h1 {:id "main-title" :class "col-lg-12"} "MyGrant"]
     [:h5 {:id "subtitle" :class "col-lg-12"} "What are you good for?"]]
    [:div {:id "content" :class "container-fluid"} body]]))

(defn four-oh-four []
  (common "Page Not Found"
          [:div {:id "four-oh-four"}
           "The page you requested could not be found"]))
