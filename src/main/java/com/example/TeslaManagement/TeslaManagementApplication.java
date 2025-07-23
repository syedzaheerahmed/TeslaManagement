package com.example.TeslaManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.TeslaManagement.repository")
@EntityScan(basePackages = "com.example.TeslaManagement.model")
public class TeslaManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeslaManagementApplication.class, args);
	}

}
