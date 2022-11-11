package com.moose.expensemanager.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok => internally has all getters and setters and toString
@AllArgsConstructor // Lombok => full fields constructor
@NoArgsConstructor // Lombok => No Args constructor
// We'll use Lombok in this class so no need for boilerplate code, getters/setters, NoArg Constructor, FullArgs Constructor, toString etc
public class ExpenseDTO {

	
	private Long id;
	private String expenseId;
	private String name; // expense name
	private String description;
	private BigDecimal amount;
	private Date date;
	private String dateString; // will hold the data parameters as String, will be used in Thymeleaf template only for UI purposes

	

}
