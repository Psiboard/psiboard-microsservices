package com.psiboard.patients_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@EnableRabbit
@EnableDiscoveryClient
@SpringBootApplication
public class PatientsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsServiceApplication.class, args);
	}

}
