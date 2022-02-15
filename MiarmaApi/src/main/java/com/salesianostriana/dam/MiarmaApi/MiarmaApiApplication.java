package com.salesianostriana.dam.MiarmaApi;

import com.salesianostriana.dam.MiarmaApi.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class MiarmaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiarmaApiApplication.class, args);
	}

}
