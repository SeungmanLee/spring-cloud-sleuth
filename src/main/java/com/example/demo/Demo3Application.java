package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo3Application {

	public static void main(String[] args) {
		System.setProperty("server.port", "8083");
		SpringApplication.run(Demo3Application.class, args);
	}

}
