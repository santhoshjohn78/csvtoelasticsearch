# CSV file to Elastic Search

This project is a java spring project that can index a CSV file into ElasticSearch

## Getting Started

Clone or download this project from github and use your fav IDE to import as a maven project.

```
git clone <url>
mvn clean install
```

### Prerequisites

This project needs ElasticSearch 5.6 running somewhere. Update the property file with
the host and port.

```
elasticsearch.host=localhost
elasticsearch.port=9200
```

### Installing

This is built on maven

```
mvn clean install
```

## Running the tests

run the junit test CsvToElasticsearchApplicationTests


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Santhosh John** - *Initial work* - [EHSS](https://github.com/santhoshjohn78)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
