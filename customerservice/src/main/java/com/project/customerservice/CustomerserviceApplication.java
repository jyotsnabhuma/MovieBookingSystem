package com.project.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
@ComponentScan(basePackages = "com.project")
@EntityScan(basePackages = "com.project.customerserviceEntity")
@EnableJpaRepositories(basePackages = "com.project.customerserviceRepository")
@SpringBootApplication
public class CustomerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerserviceApplication.class, args);
	}

@Bean
public  RestTemplate  getResttemplete() {
	return new RestTemplate();
	
	
}
}