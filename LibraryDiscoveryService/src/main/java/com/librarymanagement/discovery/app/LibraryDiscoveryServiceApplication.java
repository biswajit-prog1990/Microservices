package com.librarymanagement.discovery.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LibraryDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryDiscoveryServiceApplication.class, args);
	}

}
