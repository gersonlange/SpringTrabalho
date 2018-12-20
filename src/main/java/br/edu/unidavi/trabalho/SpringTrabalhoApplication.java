package br.edu.unidavi.trabalho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@SpringBootApplication
@Import(SpringDataRestConfiguration.class)
public class SpringTrabalhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTrabalhoApplication.class, args);
	}
}
