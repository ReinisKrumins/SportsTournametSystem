package com.StSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SportsTournamentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsTournamentSystemApplication.class, args);
	}

}
