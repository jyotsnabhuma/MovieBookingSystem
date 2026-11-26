package com.project.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = "com.project")
@EnableJpaRepositories(basePackages = "com.project.paymentserviceRepository")
@EntityScan(basePackages = "com.project.paymentserviceEntity")
public class PaymentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentserviceApplication.class, args);
	}

@Bean
public  RestTemplate  getResttemplete() {
	return new RestTemplate();
	
	
}
}
