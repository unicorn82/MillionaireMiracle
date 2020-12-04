package com.millionaire.compound.miracle;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.millionaire.compound"})
@SpringBootApplication
public class MiracleApplication {

	public static void main(String[] args) {

		SpringApplication.run(MiracleApplication.class, args);
	}

}
