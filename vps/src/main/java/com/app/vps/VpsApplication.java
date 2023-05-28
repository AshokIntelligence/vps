package com.app.vps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VpsApplication.class, args);
	}

}
