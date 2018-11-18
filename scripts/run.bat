@ECHO OFF
IF "%1"=="start" (
    ECHO start your app name
    start "eureka" java -Dserver.port=8761 -jar spring-boot-microservice-eureka-naming-server-0.0.1-SNAPSHOT.jar --spring-config.location=./Eureka/application.properties
    start "connected" java -Dserver.port=8080 -jar microsevice-connected-0.0.1-SNAPSHOT.jar --spring-config.location=./connected/application.properties
    Start "connectedCities1" java -Dserver.port=8082 -jar microservice-connected-cities-0.0.1-SNAPSHOT.jar --spring-config.location=./connectedCities1/application.properties
    Start "connectedCities2" java -Dserver.port=8083 -jar microservice-connected-cities-0.0.1-SNAPSHOT.jar --spring-config.location=./connectedCities2/application.properties
) ELSE IF "%1"=="stop" (
    ECHO stop your app name
    TASKKILL /FI "WINDOWTITLE eq yourappname"
) ELSE (
    ECHO please, use "run.bat start" or "run.bat stop"
)
pause
