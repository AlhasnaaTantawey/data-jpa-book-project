package com.global.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.global.book"})
public class DataJpaBooksProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaBooksProjectApplication.class, args);

	}

}
