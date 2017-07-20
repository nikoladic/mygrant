(ns mygrant.controllers.jobs
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [mygrant.views.jobs :as view]
            [mygrant.models.job :as model]))

(defn index []
  (view/index (model/find-job)))

(defn find-job
  [qualification]
  (when-not (str/blank? qualification)
    (model/find-job))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" [qualification-dd] (find-job qualification-dd)))
