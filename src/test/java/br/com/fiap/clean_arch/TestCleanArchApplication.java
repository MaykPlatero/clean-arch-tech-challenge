package br.com.fiap.clean_arch;

import org.springframework.boot.SpringApplication;

public class TestCleanArchApplication {

	public static void main(String[] args) {
		SpringApplication.from(CleanArchApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
