package com.moose.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moose.expensemanager.entity.Expense;

// remember that JpaRepository has already implementation of @Transactional and @Repository, more details on SimpleJpaRepository class
public interface IExpenseRepository extends JpaRepository<Expense, Long> {

	
	// we are passing type of Expense, and id is Long
	
	
	
}
