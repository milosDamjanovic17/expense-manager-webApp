package com.moose.expensemanager.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "tbl_expenses" )
// don't use Lombok in this Entity class
public class Expense {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	@Column( unique = true ) // every expense needs to be unique
	private String expenseId;
	private String name; // expense name
	private String description;
	private BigDecimal amount;
	private Date date; // we'll store datestamp inside DB
	
	
	public Expense() {

	}

	public Expense(Long id, String expenseId, String name, String description, BigDecimal amount, Date date) {
		this.id = id;
		this.expenseId = expenseId;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.date = date;
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

	@Override
	public String toString() {
		return "Expense [id=" + id + ", expenseId=" + expenseId + ", name=" + name + ", description=" + description
				+ ", amount=" + amount + ", date=" + date + "]";
	}

	
}
