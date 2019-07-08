package com.example.twgerenciadortarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.controllers")
@EntityScan("com.example.models")
@EnableJpaRepositories("com.example.repositories")
public class TwGerenciadorTarefasApplication {

 	public static void main(String[] args) {
		SpringApplication.run(TwGerenciadorTarefasApplication.class, args);
	}

}
