package com.example.jbdl19.restapis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApisApplication {

	private static final Logger logger = LoggerFactory.getLogger(RestApisApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestApisApplication.class, args);

		logger.warn("Application has started");
	}

}
