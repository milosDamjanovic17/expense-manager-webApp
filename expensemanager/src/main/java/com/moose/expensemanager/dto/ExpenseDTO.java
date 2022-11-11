package com.moose.expensemanager.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok => internally has all getters and setters and toString
@AllArgsConstructor // Lombok => full fields constructor
@NoArgsConstructor // Lombok => No Args constructor
// Lombok haven't been read, so we used getters and setters
public class ExpenseDTO {

	
	private Long id;
	private String expenseId;
	private String name; // expense name
	private String description;
	private BigDecimal amount;
	private Date date;
	private String dateString; // will hold the data parameters as String, will be used in Thymeleaf template only for UI purposes
	
	public ExpenseDTO() {
		
	}

	public ExpenseDTO(Long id, String expenseId, String name, String description, BigDecimal amount, Date date,
			String dateString) {
		this.id = id;
		this.expenseId = expenseId;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.date = date;
		this.dateString = dateString;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	@Override
	public String toString() {
		return "ExpenseDTO [id=" + id + ", expenseId=" + expenseId + ", name=" + name + ", description=" + description
				+ ", amount=" + amount + ", date=" + date + ", dateString=" + dateString + "]";
	}
	

}
