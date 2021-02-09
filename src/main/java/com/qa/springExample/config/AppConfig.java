package com.qa.springExample.config;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//This indicates that the class has a bean definition method in here.
@Configuration
public class AppConfig {

	@Bean
	public String message() {
		return "Hello world! " + LocalTime.now();
	}

	@Bean
	@Scope("prototype")
	public String message2() {
		return "Hello world! " + LocalTime.now();
	}

	@Bean
	@Scope("prototype")
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}

//when working with an external library <-- the one we will use
// or if we have lots of settings/config for our projects that we want.