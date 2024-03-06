package com.intuit.craftdemotwitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
public class CraftDemoTwitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftDemoTwitterApplication.class, args);
	}
	@Bean
	public Docket swaggerConfiguration()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(appInfo());

	}

	private ApiInfo appInfo()
	{
		return new ApiInfo("Twitter API"
				, "Craft Demo - Twitter like App in Spring Boot. Allows user creation, user can follow/unfollow/tweet and check their newsfeed."
				, "0.1"
				, "Free"
				, new springfox.documentation.service.Contact("Kiruthikaa Palaniappan", "https://www.intuit.com/", "p.kiruthikaa@gmail.com")
				, "https://www.intuit.com/"
				,null, Collections.emptyList());
	}
}
