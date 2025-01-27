package com.dancesphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories  // Esta es la anotación que habilita los repositorios de MongoDB
public class DanceSphereApplication {
	public static void main(String[] args) {
		SpringApplication.run(DanceSphereApplication.class, args);
	}
}
