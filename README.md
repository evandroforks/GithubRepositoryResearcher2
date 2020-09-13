# GitHub Repository Researcher 2

GitHub Repository Researcher 2 is a evolution of [GithubRepositoryResearcher](https://github.com/evandroforks/GithubRepositoryResearcher)
inspired by [AppointmentPublishing](https://github.com/evandroforks/AppointmentPublishing).
It uses a Java Spring Boot backend with GitHub V3 API with a ReactJS frontend.


## Usage

To run this project, you need installed:
1. **`javac 1.8.0_131`** or superior
1. **`Apache Maven 3.5.0`** or superior
1. **`NodeJS v12.14.1`** or superior

Then, to run the backend just call:
1. **`> javabackend$ mvn package`**
1. **`> javabackend$ mvn package exec:java`**

To run the frontend just call:
1. **`> reactfrontend$ npm install`**
1. **`> reactfrontend$ mpn start`**

Alternatively, you can install **`IntelliJ IDEA 2020`**,
go to the menu **`File -> New -> Project from Existing Sources`** and select the **`pom.xml`** project file.

After starting the project:
1. The Java backend API will be available on the URL: **`http://127.0.0.1:8081/researcher/<API_NAME>`**
1. The React frontend will be available on the URL: **`http://127.0.0.1:3000/`**

See the **`src/main/resources/application.properties`** file for server configurations.


### Restful API

1. **`/searchRequest`** is Restful endpoint. See **`SearchController`** and **`SearchRequest`** for the parameter details.
1. **`/detailRequest`** is Restful endpoint. See **`SearchController`** and **`DetailRequest`** for the parameter details.

