package com.jy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jy.service.ApplicationStartListener;



@Configuration
public class ListenerConfig {

	@Bean
	public ApplicationStartListener applicationStartListener() {
		return new ApplicationStartListener();
	}
}