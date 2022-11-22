package com.moose.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moose.expensemanager.entity.Expense;

import java.util.List;
import java.util.Optional;

// remember that JpaRepository has already implementation of @Transactional and @Repository, more details on SimpleJpaRepository class
public interface IExpenseRepository extends JpaRepository<Expense, Long> {
    // we are passing type of Expense, and id is Long
	

    // let's create a JPA finder method that will extract the Expense obj with passed ExpenseId
	Optional<Expense>findByExpenseId(String id); // => SELECT * FROM tbl_expenses WHERE expenseId = ?

    // let's create a method for finding the expense by name
    List<Expense>findByNameContaining(String keyword);  // => SELECT * FROM tbl_expenses WHERE name LIKE %keyword%
	
}
