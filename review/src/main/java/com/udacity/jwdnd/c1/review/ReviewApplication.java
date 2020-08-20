package com.udacity.jwdnd.c1.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@EnableAutoConfiguration
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}

	//@Primary
	//@Bean
	//public String message(){
	//	System.out.println("In simple message bean");
	//	return "Hello, Spring!";
	//}


	//@Bean
	//public String uppercaseMessage(MessageService msgServ){
	//	System.out.println("Creating uppercaseMessage Bean");
	//	return msgServ.upperCase();
	//}

	//@Bean
	//public String lowercaseMessage(MessageService msgServ){
	//	System.out.println("Creating lowercaseMessage Bean");
	//	return msgServ.lowerCase();
	//}
}
