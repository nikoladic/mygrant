(ns mygrant.views.jobs
  (:require [mygrant.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]))

(def qualifications ["برق انجنیری او میخانیکي انجینري" "د موټرو صنعت" "د نساجۍ صنعت" "امنیت او پاکول" "معمارۍ او ساختماني" "د ترانسپورت او ذخيري" "د خرڅلاو او بازار" "ميلمه پالنه"])

(def qualificationsserbian ["ugostiteljstvo" "prodaja i marketing" "transport i skladistenje" "arhitektura" "obezbedjenje i ciscenje" "tekstilna industrija" "auto industrija" "elektrotehnika i mašinstvo"])

(def cities ["بلګراد - Beograd" "Novi Sad" "Stara Pazova" "Subotica" "Zrenjanin" "Surcin" "په بهر کې"])

(defn job-form [dd-values]
  [:div {:id "job-form" :class "container-fluid"}
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 (form/label {:class "label label-info col-lg-12 margin-basic"} "qualification-label" "Choose your qualification and city - خپل وړتوب او ښار غوره کړئ")
                 (form/drop-down {:class "btn btn-default col-md-6 margin-basic"} "qualification-dd" qualifications (:qualification dd-values))
                 (form/drop-down {:class "btn btn-default col-md-6 margin-basic"} "city-dd" cities (:city dd-values))
                 (form/submit-button {:class "btn btn-primary col-lg-12 margin-basic"} "Get Jobs!"))])

(defn display-jobs [jobs]
  [:div {:class "jobs container-fluid margin-basic"}
   (map
    (fn [job] [:div {:class "container-fluid"} [:a {:href (h (:url job))} [:h2 {:class "job-headline"} (h (:headline job))]] [:p {:class "job-snippet"} (h (:body job))]])
    jobs)])

(defn index [jobs dd-values]
  (layout/common "MyGrant"
                 (job-form dd-values)
                 [:div {:class "clear"}]
                 (display-jobs jobs)))
