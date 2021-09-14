package com.backbase.test.kalah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.backbase.test.kalah")
public class KalahApplication {

	public static void main(String[] args) {
		SpringApplication.run(KalahApplication.class, args);
	}
}
