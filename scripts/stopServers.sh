#! /bin/bash
pid1=`ps aux | grep microservice-connected-cities-0.0.1-SNAPSHOT.jar | awk '{print $2}'`
kill -9 $pid1
pid2=`ps aux | grep "spring-boot-microservice-eureka-naming-server-0.0.1-SNAPSHOT.jar" | awk '{print $2}'`
kill -9 $pid2
pid3=`ps aux | grep "microsevice-connected-0.0.1-SNAPSHOT.jar" | awk '{print $2}'`
kill -9 $pid3
