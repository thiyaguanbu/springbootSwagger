package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("main.java.com.application")
public class ApplicationManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationManagementApplication.class, args);
	}
}
