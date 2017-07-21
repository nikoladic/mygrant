(ns mygrant.models.job
  (require [reaver :refer [parse extract-from text attr]]))

(defn all []
  ["Job1" "Job2" "Job3"])

(def get-source-website-hostname "https://www.halooglasi.com")

(def job-type-to-url {(keyword "ميلمه پالنه") "ugostiteljstvo-i-turizam" (keyword "د خرڅلاو او بازار") "prodaja-marketing-i-oglasavanje" (keyword "د ترانسپورت او ذخيري") "transport-skladistenje-i-logistika" (keyword "معمارۍ او ساختماني") "arhitektura-i-gradjevinarstvo" (keyword "امنیت او پاکول") "obezbedjenje-zastita-i-higijenski-poslovi" (keyword "د نساجۍ صنعت") "tekstilna-industrija" (keyword "د موټرو صنعت") "autoindustrija" (keyword "برق انجنیری او میخانیکي انجینري") "elektrotehnika-i-masinstvo"})

(def city-to-url {(keyword "بلګراد - Beograd") "ls=35112" (keyword "Novi Sad") "ls=35194" (keyword "Stara Pazova") "ls=35237" (keyword "Subotica") "ls=35240" (keyword "Zrenjanin") "ls=35277" (keyword "Surcin") "ls=35407" (keyword "په بهر کې") "ls=35408"})

(defn get-source-website-data
  "Adds url parts based on parameters"
  [qualification city]
  (slurp (str "https://www.halooglasi.com/posao/ponuda-poslova-" ((keyword qualification) job-type-to-url) "?gradovi_id_" ((keyword city) city-to-url))))

(defn find-job-part-one
  "Scrapes job listings from websites and returns them as a map"
  [qualification city]
  (extract-from (parse (get-source-website-data qualification city)) ".courses"
              [:headline :body :url]
              ".courses-title" text
              ".course-description" text
              ".courses-title > a" (attr :href)))

(defn add-hostname-to-urls
  [jobs]
  (map (fn [job] {:headline (:headline job) :body (:body job) :url (str get-source-website-hostname (:url job))}) jobs))

(defn find-job
  [qualification city]
  (add-hostname-to-urls (find-job-part-one qualification city)))
