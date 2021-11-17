# legal-entities-stats
CDQ interview 

## Requirements

* gitbash or similar
* docker
* postman or similar tool

## How to run

* Clone repo
* Run command `docker-compose up`

Application should be available on `http://localhost:8080`

## Import Data

* Use endpoint `/api/downloadData` with POST method
* Put body in JSON format with the url to download data:

`{
 	"url": "https://leidata.gleif.org/api/v1/concatenated-files/rr/20210930/zip"
 }`

## API

* List of all relations

`/api/getRelations`

* List relations by the node ID

`/api/getRelations/{nodeId}`

* Lowest number of relations of one node

`/api/getLowestNumber`

* Highest number of relations of one node

`/api/getHighestNumber`

* Average number of relations for the nodes

`/api/getAverageNumber`