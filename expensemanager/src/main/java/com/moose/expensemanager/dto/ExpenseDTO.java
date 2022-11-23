package com.moose.expensemanager.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data // Lombok => internally has all getters and setters and toString
@AllArgsConstructor // Lombok => full fields constructor
@NoArgsConstructor // Lombok => No Args constructor
// We'll use Lombok in this class so no need for boilerplate code, getters/setters, NoArg Constructor, FullArgs Constructor, toString etc
public class ExpenseDTO {

	
	private Long id;
	private String expenseId;

	// using spring validator dependency to define requirements that need to be fulfilled, in Controller we'll test if there are any errors, error message will be (message = "error message")
	// using thymeleaf we'll bind these requirements and print the error message
	@NotBlank(message = "Expense name must be filled!")
	@Size(min = 2, message = "Expense name should have at least {min} characters")
	private String name; // expense name

	private String description;

	// let's create requirements for amount
	@NotNull(message = "Please enter amount!")
	@Min(value = 1, message = "Expense amount cant be less than 1")
	private BigDecimal amount;
	private Date date;
	private String dateString; // will hold the data parameters as String, will be used in Thymeleaf template only for UI purposes

	

}
