package com.jy;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class LogisticsHousekeeperApplication {
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	@Bean  
    MultipartConfigElement multipartConfigElement() {  
       MultipartConfigFactory factory = new MultipartConfigFactory();  
       factory.setLocation("C://temp");  
       return factory.createMultipartConfig();  
   }  
	public static void main(String[] args) {
		SpringApplication.run(LogisticsHousekeeperApplication.class, args);
	}
	
}
