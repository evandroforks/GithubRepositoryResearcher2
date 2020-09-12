# GitHub Repository Researcher 2

GitHub Repository Researcher 2 is a evolution of [GithubRepositoryResearcher](https://github.com/evandroforks/GithubRepositoryResearcher)
inspired by [AppointmentPublishing](https://github.com/evandroforks/AppointmentPublishing).
It uses a Java Spring Boot backend with GitHub V3 API with a ReactJS frontend.


## Usage

To run this project, you need installed:
1. **`javac 1.8.0_131`** or superior
1. **`Apache Maven 3.5.0`** or superior

Then, just call **`mvn package exec:java`**

Alternatively, you can install **`IntelliJ IDEA 2020`**,
go to the menu **`File -> New -> Project from Existing Sources`** and select the **`pom.xml`** project file.

After starting the project, the API will be available on the URL: `http://127.0.0.1:8081/researcher/<API_NAME>`

See the **`src/main/resources/application.properties`** file for server configurations.


### Restful API

1. **`/searchRequest`** is Restful endpoint. See **`SearchController`** and **`SearchRequest`** for the parameter details.

