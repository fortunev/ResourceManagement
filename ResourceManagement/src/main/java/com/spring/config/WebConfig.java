package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.spring.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addCorsMappings (CorsRegistry r) {
		r.addMapping("/**").allowedMethods("DELETE", "PUT", "GET", "POST");
	}
}
