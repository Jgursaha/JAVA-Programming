package com.aiml.PricingEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PricingEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricingEurekaServerApplication.class, args);
	}

}
