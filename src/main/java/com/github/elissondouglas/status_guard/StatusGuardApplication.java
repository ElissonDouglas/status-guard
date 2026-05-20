package com.github.elissondouglas.status_guard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StatusGuardApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatusGuardApplication.class, args);
	}

}
