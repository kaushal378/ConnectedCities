#! /bin/bash
echo "Run Spring Boot Jars...."
rm pid.txt
nohup java -Dserver.port=8761 -jar spring-boot-microservice-eureka-naming-server-0.0.1-SNAPSHOT.jar -Dspring-config.location=./Eureka/application.properties &
echo $! >> pid.txt
nohup java -Dserver.port=8080 -jar microsevice-connected-0.0.1-SNAPSHOT.jar -Dspring-config.location=./connected/application.properties &
echo $! >> pid.txt
nohup java -Dserver.port=8082 -jar microservice-connected-cities-0.0.1-SNAPSHOT.jar -Dspring-config.location=./connectedCities1/application.properties &
echo $! >> pid.txt
nohup java -Dserver.port=8083 -jar microservice-connected-cities-0.0.1-SNAPSHOT.jar -Dspring-config.location=./connectedCities2/application.properties &
echo $! >> pid.txt
