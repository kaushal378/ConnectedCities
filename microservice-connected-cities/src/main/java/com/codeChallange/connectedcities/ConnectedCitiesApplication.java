package com.codeChallange.connectedcities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableDiscoveryClient
public class ConnectedCitiesApplication {
	
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConnectedCitiesApplication.class);
    }


	public static void main(String[] args) {
		SpringApplication.run(ConnectedCitiesApplication.class, args);
	}
}
