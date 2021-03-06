package com.sony.assignment;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HomeAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeAssignmentApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				 registry.addMapping("/**")
			        .allowedHeaders("*")
			        .allowedMethods("*")
			        .allowedOrigins("*");
			}
		};
	}

}
