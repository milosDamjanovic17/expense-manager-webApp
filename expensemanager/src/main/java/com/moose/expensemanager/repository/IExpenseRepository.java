package com.moose.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moose.expensemanager.entity.Expense;

import java.util.Optional;

// remember that JpaRepository has already implementation of @Transactional and @Repository, more details on SimpleJpaRepository class
public interface IExpenseRepository extends JpaRepository<Expense, Long> {
    // we are passing type of Expense, and id is Long
	

    // lets create a JPA finder method that will extract the Expense obj with passed ExpenseId
	Optional<Expense>findByExpenseId(String id); // => SELECT * FROM tbl_expenses WHERE expenseId = ?
	
	
}
