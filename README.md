# Spring-boot elasticsearch autocomplete example

## Home Page
http://localhost:8080/
demo page

## REST API
http://localhost:8080/suggestion?searchstr=jeffrey
returns the list of books written by this author

## Technology Stack
- spring boot v1.5.3 (https://projects.spring.io/spring-boot/)
- elastic search v5.1.2 (https://www.elastic.co/products/elasticsearch)
- jquery autocomplete v1.4 (https://www.devbridge.com/sourcery/components/jquery-autocomplete/)
- materialize css v0.98.2 (http://materializecss.com/)

## Instructions

For DB Installation

- Download elastic search v5.1.2 (https://www.elastic.co/downloads/past-releases/elasticsearch-5-1-2)
- Open command prompt and cd to the downloaded folder (for eg cd D:\softwares\elasticsearch5.1.2\)
- Issue the following command to start elastic server
  .\bin\elasticsearch

For Data Dumping

- Download curl (for windows os) (https://dl.uxnr.de/build/curl/curl_winssl_msys2_mingw64_stc/curl-7.57.0/curl-7.57.0.zip)
- Add curl installation path to windows path variable
- Download the "accordium.json" file located in src/main/resources folder and save it anywhere in your machine
- Open command prompt and cd to the path where you have saved the file (for eg D:\softwares\)
- Issue the following command to dump data into elastic
	curl -s -XPOST localhost:9200/_bulk --data-binary @accordium.json
	
For autocomplete demo

- Run the AutocompleteApplication.java file (preferably from eclipse)
- Open http://localhost:8080/ on our browser (preferably chrome)
- Type an author name (for eg jeffreyarcher)
