# ConnectedCities

This Project creates three Microservices(MS) in Spring Boot. First MS is Eureka server which is used for Eureka server hosting. Second MS "connected" is a proxy server which will help with client side load balancing for actual MS connected-cities where all business logic resides.
This Microservice will read the pair of comma separated cities from a file (city.txt) and load it in a graph data structure. Microservice will be exposed using below link where origin and destination cities will be sent as input parameters and it will return whether these cities are connected or not.

Link to expose MS - http://host_name:port/connected?origin=city1&destination=city2

## Input Specification

Input will have a file in which each line will have two cities' name separated by comma. Number of lines can be variable. One line of input will have two cities these two city pairs are cconnected.

## Output Specification

Output will return whether two cities sent as input parameters as origin and destination are connected or not. Output will be boolean true or false.

## Algorithm

First, connected city list provided in input file city.txt will be loaded into graph data structure as adjacency list. When Micro-service is called, code will traverse the Graph adjacency list in breadth-first manner starting from origin city and if target city is found, it will return true. If target city is not found in traversal, it will return false.

## Getting Started

User need to download the project from github and import the project as existing Maven project in Eclipse or any other Java IDE to review the code structure.

### Prerequisites

To review the code, Eclipse or any other JAVA IDE is needed along with Java 1.8.
To run the code, Java 1.8 will be needed.

```
Give examples
```

### Installing

1. Install JDK 1.8 and add its bin directory path in environment variable.
2. Install Eclipse or any other IDE.
3. Import the downloaded project as existing maven project to review the code.

## Running the server

   ## Windows OS
       1. Download scripts folder from github repo.
       2. Go to the directory using command prompt.
       3. run command <run.bat start> to start the servers.
       4. This will start 4 Tomcat servers, 1 Eureka, 1 Proxy server and 2 instances of actual Micro-service.
       5. Check if servers are up using Eureka link - http://localhost:8761. Here 3 servers will be shown as registered, 2 connected-cities(actual server) and 1 connected (proxy server).
       6. Once servers are up, copy and paste http://localhost:8080/connected?origin=city1&destination=city2 in web browser. Change the city names to check if path exists or not.

   ## Unix/Linux OS
      1. Perform setps 1 and 2 as stated above for Windows OS.
      2. run command ./runservers.sh.
      3. Perform steps 4 to 6 as stated above.

## Shutdown servers

    ## Windows OS
      1. Go to scripts directory.
      2. run command <run.bat stop> to stop the servers.

    ## Unix/Linux OS
      1. Go to scripts directory.
      2. run command <./stopServers.sh>

## Swagger Link
    UI - http://localhost:8080/swagger-ui.html
    
## Assumptions
1. If input city has space, city name will be provided with %20 in place of space. For ex. New York should be New%20YORK.

## Running the tests

To test the Microservice, copy and paste the below URL in browser with city1 and city2 changed to targeted data -

http://localhost:8081/connected?origin=city1&destination=city2

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Spring Boot](http://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Java 1.8](https://www.oracle.com/technetwork/java/javase/overview/index.html)

## Contributing


## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## Authors

* **Kaushal Kishore** - *Initial work* - [ConnectedCities](https://github.com/kaushal378/ConnectedCities)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
