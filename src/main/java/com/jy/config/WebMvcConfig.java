package com.jy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends  WebMvcConfigurerAdapter  {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	//	registry.addViewController("/login").setViewName("login");
		registry.addViewController("/ws").setViewName("/test/ws");
		registry.addViewController("/chat").setViewName("/test/chat");
	}

}
