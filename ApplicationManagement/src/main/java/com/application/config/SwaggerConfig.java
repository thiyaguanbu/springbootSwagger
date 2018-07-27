package com.application.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//it enables swagger support in this class
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Docket instance is created by @Bean.
	 * the select method called in the Docket instance return apiselectorBUilder
	 * this apiselectorbuilder provides two methods apis() and paths()
	 * the above methods are used to filter the controllers and methods that are being documented using String predicates.
	 * @return
	 */
	@Bean
	public Docket ApplicationApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		.select() .apis(RequestHandlerSelectors.basePackage("com.application.controllers"))
		.paths(regex("/application.*"))
		.build()
		.apiInfo(metadata());	
		
	}
	
	public ApiInfo metadata(){
		ApiInfo apiInfo = new ApiInfo("Application management Microservice", 
				"This microservice contails details about Applications",
				"v1.1", "Terms of Service",
				new Contact("Thiyagu", "http://localhost:8080/users", "thiyagu@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		
		return apiInfo;
		
	}
	
	
}
