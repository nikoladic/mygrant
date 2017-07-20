(ns mygrant.models.job
  (require [reaver :refer [parse extract-from text attr]]))

(defn all []
  ["Job1" "Job2" "Job3"])

(def get-source-website-hostname "https://www.halooglasi.com")

(def source-website-data (slurp "https://www.halooglasi.com/posao/ponuda-poslova-ugostiteljstvo-i-turizam"))

(defn find-job-part-one
  "Scrapes job listings from websites and returns them as a map"
  []
  (extract-from (parse source-website-data) ".courses"
              [:headline :body :url]
              ".courses-title" text
              ".course-description" text
              ".courses-title > a" (attr :href)))

(defn add-hostname-to-urls
  [jobs]
  (map (fn [job] {:headline (:headline job) :body (:body job) :url (str get-source-website-hostname (:url job))}) jobs))

(defn find-job
  []
  (add-hostname-to-urls (find-job-part-one)))
