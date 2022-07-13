package com.spring.mfpe.offer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Slf4j
@EnableSwagger2
@EnableFeignClients("com.spring.mfpe.offer")
public class OfferMicroserviceApplication {

	public static void main(String[] args) {
		log.info("inside offer microservice");
		SpringApplication.run(OfferMicroserviceApplication.class, args);
	}
	
	//swagger configuration
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("com.spring.mfpe.offer")).build();
	   }

}
