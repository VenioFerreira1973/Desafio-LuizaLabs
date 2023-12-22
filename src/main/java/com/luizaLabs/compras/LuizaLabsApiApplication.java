package com.luizaLabs.compras;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.luizaLabs.compras.infrastructure.repository.impl.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class LuizaLabsApiApplication {

	public static void main(String[] args) {
		
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		//var app = new SpringApplication(LuizaLabsApiApplication.class);
		//app.run(args);
		
		SpringApplication.run(LuizaLabsApiApplication.class, args);
	}

}
