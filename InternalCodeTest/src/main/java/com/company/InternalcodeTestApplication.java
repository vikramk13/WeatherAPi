package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.model.controller","com.company.service"})

public class InternalcodeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternalcodeTestApplication.class, args);
	}

}
