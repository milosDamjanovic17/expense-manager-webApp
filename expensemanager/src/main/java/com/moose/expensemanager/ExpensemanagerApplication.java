package com.moose.expensemanager;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpensemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensemanagerApplication.class, args);
	}
	
	// define a bean for ModelMapper dependency
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
