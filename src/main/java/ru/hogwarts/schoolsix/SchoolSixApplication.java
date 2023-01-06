package ru.hogwarts.schoolsix;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SchoolSixApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolSixApplication.class, args);
	}

}
