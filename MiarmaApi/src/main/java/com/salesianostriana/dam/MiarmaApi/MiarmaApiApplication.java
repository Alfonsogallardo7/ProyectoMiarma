package com.salesianostriana.dam.MiarmaApi;

import com.salesianostriana.dam.MiarmaApi.config.StorageProperties;
import com.salesianostriana.dam.MiarmaApi.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class MiarmaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiarmaApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner init (StorageService storageService) {
		return args -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
