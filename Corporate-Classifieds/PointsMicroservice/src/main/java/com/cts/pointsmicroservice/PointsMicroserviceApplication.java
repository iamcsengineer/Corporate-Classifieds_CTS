package com.cts.pointsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@ComponentScan("com")
@Slf4j
@EnableSwagger2
public class PointsMicroserviceApplication {

	public static void main(String[] args) {
		log.info("Inside Points Microservice");
		SpringApplication.run(PointsMicroserviceApplication.class, args);
	}
	
	//swagger configuration
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("com.cts.pointsmicroservice")).build();
	   }

}
