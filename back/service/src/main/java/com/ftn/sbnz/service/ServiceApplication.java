package com.ftn.sbnz.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("com.*") 
@ComponentScan(basePackages = { "com.*" }) 
@EntityScan("com.*")
public class ServiceApplication  { 
	public static void main(String[] args) {
		System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog","fatal");
		SpringApplication.run(ServiceApplication.class, args);
	}
}