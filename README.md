# MyGrant overview

A Clojure web app designed to help migrants find jobs in Serbia. It is unique because it gives migrants, who have a hard time learning Serbian or English language, an opportunity to explore job placement options in their native language.

# Supported Clojure Versions
MyGrant requires Clojure 1.8+. The latest stable release is recommended.

## Usage

The app is not meant to be used in other projects, but is a standalone web application, used by migrants to get a raw description of possible jobs they might work at.

The interface is translated to Pashto, with fields to choose qualification and city of residence.
Based on migrant's selection, ads are obtained from a local job website, translated into pashto and presented to the user.

## Libraries

MyGrant makes use of Compojure for routing, Ring for handling http communication, Hiccup for rendering webpages, Reaver, a small wrapper library for JSoup to scrap the webpages and Clj-http for creating http requests which can access Google Translate REST API.
The app uses Google Translate REST API to translate ad text from Serbian to Pashto.
Bootstrap CDN is used to provide styles for the view.

## Project structure

The web app has a MVC structure. The controller is responsible for handling input data forwarded from the view and invoking the appropriate function in the view. 
If the controller receives a GET request, a default start page without jobs is displayed.
When the controller receives a POST request, the index function in the view is invoked with the data obtained from the find-job function in the mygrant.models.job (a map of job titles, descriptions and links). The find-job function takes input values from job webpage form as parameteres (qualification and city of residence).
mygrant.views.layout/common sets up a basic layout for the view webpage, taking page title and body as parameters.
mygrant.views.jobs namespace constructs the whole web page from a static part (options nad submission button) and a dynamic part (the actual ads).
mygrant.models.job namespace deals with scraping data from the job ad webpages and converting it to a map. It then does modifications of the data structures by correcting the urls and translating the job title and description, utilizing the map function as a part of it's find-job function. It also contains a mapping of Serbian job categories to their Pashto counterparts. The same goes for the cities.
mygrant.util.util namespace contains utalitarian functions for encoding urls.
The web app makes use of Ring's anti-forgery token, to prevent CSRF attacks.

## License

Copyright © 2017 Jovan Toroman

Distributed under the Eclipse Public License either version 1.0.
