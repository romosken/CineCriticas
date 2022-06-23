package com.mosken.rodrigo.letscode.challenge.cinecriticas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CinecriticasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinecriticasApplication.class, args);
	}

}
