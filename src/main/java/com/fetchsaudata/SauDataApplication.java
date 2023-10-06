package com.fetchsaudata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SauDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SauDataApplication.class, args);
	}

	@GetMapping("/my-health-check")
	private ResponseEntity<?> status() {
		return ResponseEntity.ok("OK");
	}

}
