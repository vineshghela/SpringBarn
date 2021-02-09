package com.qa.springExample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.qa.springExample.config.AppConfig;

@SpringBootApplication
public class BarnApplication {

	public static void main(String[] args) {
//		SpringApplication.run(BarnApplication.class, args);
		ApplicationContext beanBag = SpringApplication.run(BarnApplication.class, args);
		System.out.println(beanBag.getBean("message", String.class));
		List<String> beans = new ArrayList<>();
		AppConfig config = new AppConfig();
		beans.add(config.message());
		beans.add(config.message());
		beans.add(config.message());
		System.out.println(beans);
	}

}
