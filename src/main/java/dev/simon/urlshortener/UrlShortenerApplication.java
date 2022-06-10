package dev.simon.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
public class UrlShortenerApplication {

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> System.out.println(Arrays.toString(environment.getActiveProfiles()));
	}

}
