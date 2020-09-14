package com.aiml.DogMicroServiceEurekaApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DogMicroServiceEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogMicroServiceEurekaApplication.class, args);
	}

}
