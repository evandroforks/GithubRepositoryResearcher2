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
1. **`> reactfrontend$ npm start`**

Alternatively, you can install **`IntelliJ IDEA 2020`**,
go to the menu **`File -> New -> Project from Existing Sources`** and select the **`pom.xml`** project file.

After starting the project:
1. The Java backend API will be available on the URL: **`http://127.0.0.1:8081/researcher/<API_NAME>`**
1. The React frontend will be available on the URL: **`http://127.0.0.1:3000/`**

See the **`src/main/resources/application.properties`** file for the Java Server configurations.

1. **`src/main/resources/application.properties`**
    Controls the debug and runtime information about the system.


### Restful API

1. **`/searchRequest`** is Restful endpoint. See **`SearchController`** and **`SearchRequest`** for the parameter details.
1. **`/detailRequest`** is Restful endpoint. See **`SearchController`** and **`DetailRequest`** for the parameter details.
1. **`/listRepositories`** is Restful endpoint. See **`SearchController`** and **`UserRequest`** for the parameter details.

### Docker

You can run this project directly from docker.
To pull a image from docker hub and run it locally,
you can run the command:
1. **`docker login`** (you may try to skip this)
1. **`docker run --network="host" evandrocoan/ubuntu18nodejsjavagithub`**
1. Note: Due to new docker hub policies,
   images older than 6 months are going to be deleted for free accounts.
   Therefore,
   if you do not find this image on docker hub,
   see [Dockerfile-all](Dockerfile-all) file for instructions.

Note: Due to the backend ip address to be hardcoded on **`reactfrontend/src/App.tsx`**,
the frontend will only be able to access the backend if they are accessed locally,
i.e., the browser and the backend are both running on the ip address **`127.0.0.1`**.

TODO: This limitation can be fixed by passing the backend ip address to the frontend
when running the docker command or find some other way to pass it.
