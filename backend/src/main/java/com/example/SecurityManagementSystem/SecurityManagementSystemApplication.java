package com.example.SecurityManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SecurityManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityManagementSystemApplication.class, args);
	}

	// @Bean
	//    public WebMvcConfigurer corsConfigurer() {
	//       return new WebMvcConfigurer() {
	//          @Override
	//          public void addCorsMappings(CorsRegistry registry) {
	//         registry.addMapping("/login").allowedMethods("*").allowedOrigins("http://localhost:3000");
	//          }
	//       };
	//    }

}
