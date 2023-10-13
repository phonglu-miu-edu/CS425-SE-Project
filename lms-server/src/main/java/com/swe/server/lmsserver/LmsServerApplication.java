package com.swe.server.lmsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LmsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsServerApplication.class, args);
	}

}
