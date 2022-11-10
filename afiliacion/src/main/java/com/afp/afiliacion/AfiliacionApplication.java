package com.afp.afiliacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AfiliacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfiliacionApplication.class, args);
	}

}
