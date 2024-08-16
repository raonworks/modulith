package com.example.modular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ModularApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModularApplication.class, args);
	}

}
