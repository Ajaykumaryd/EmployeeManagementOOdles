package com.Task1.OodlesA1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.02", description = "Employees Information"))
public class OodlesA1Application {

	public static void main(String[] args) {
		SpringApplication.run(OodlesA1Application.class, args);

	}

}
