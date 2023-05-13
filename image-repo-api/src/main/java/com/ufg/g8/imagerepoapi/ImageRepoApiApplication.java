package com.ufg.g8.imagerepoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ImageRepoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageRepoApiApplication.class, args);
	}

}
