package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application where Spring Boot runs.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
@SpringBootApplication
public class Main {

	/**
	 * Executes the main Spring Application program.
	 * 
	 * @param args Arguments from console.
	 * @return Nothing.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
