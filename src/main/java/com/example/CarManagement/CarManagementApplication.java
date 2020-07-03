package com.example.CarManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarManagementApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/cms");
		SpringApplication.run(CarManagementApplication.class, args);
	}

}
