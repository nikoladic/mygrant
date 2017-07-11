(ns mygrant.models.job
  (require [reaver :refer [parse extract-from text attr]]))

(defn all []
  ["Job1" "Job2" "Job3"])

(def infostud (slurp "https://poslovi.infostud.com/oglasi-za-posao#dist=&last_search_time=&page=0&submit=1&q=administracija&city%5B%5D=0&category%5B%5D=5&vreme_postavljanja=&rok_konkursa=&firma_uid=&education=&vrste_kategorija_posla=&jezik=&sort="))

(defn find-job
  "Scrapes job listings from websites and returns them as a map"
  []
  (extract-from (parse infostud) ".uk-margin-remove .uk-h3"
              [:headline :body :href]
              ".uk-margin-remove > a" text
              ".uk-h3 > a" text
              ".uk-margin-remove > a" (attr :href)))
