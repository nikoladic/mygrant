(ns mygrant.controllers.jobs
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [mygrant.views.jobs :as view]
            [mygrant.models.job :as model]))

(defn index []
  (view/index [] {:qualification "ميلمه پالنه" :city "بلګراد - Beograd"}))

(defn find-job
  [qualification city]
  (view/index (model/find-job qualification city) {:qualification qualification :city city}))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" [qualification-dd city-dd] (find-job qualification-dd city-dd)))
