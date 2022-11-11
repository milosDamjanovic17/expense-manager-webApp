package com.moose.expensemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.moose.expensemanager.service.ExpenseService;

@Controller
public class ExpenseController {
	
	// create loose coupling between Controller and Service
	private final ExpenseService expenseService;
	@Autowired
	public ExpenseController(ExpenseService theExpenseService) {
		
		expenseService = theExpenseService;
	}
	
	
	@GetMapping("/expenses")
	public String showExpenseList(Model theModel) {
		
		theModel.addAttribute("expensesList", expenseService.getAllExpenses());
		
		return "expenses-list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
