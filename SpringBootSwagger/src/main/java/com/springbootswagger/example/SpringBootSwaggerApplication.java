package com.springbootswagger.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSwaggerApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootSwaggerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSwaggerApplication.class, args);
	}
}
