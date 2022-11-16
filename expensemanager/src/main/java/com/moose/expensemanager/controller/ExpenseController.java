package com.moose.expensemanager.controller;

import com.moose.expensemanager.dto.ExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.moose.expensemanager.service.ExpenseService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

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

	// bind DTO to expense form and show expense form
	@GetMapping("/createExpense")
	public String showExpenseForm(Model theModel){

		theModel.addAttribute("expense", new ExpenseDTO());

		return "expense-form";
	}

	@PostMapping("/saveOrUpdateExpense")
	public String saveOrUpdateExpenseDetails(@ModelAttribute("expense") ExpenseDTO theExpenseDTO) throws ParseException {

		System.out.println("Printing expenseDTO" +theExpenseDTO);

		expenseService.saveExpenseDetails(theExpenseDTO);

		return "redirect:/expenses";
	}

	@GetMapping("/deleteExpense")
	public String deleteExpense(@RequestParam String id){ // => th:href="@{/deleteExpense(id=${expense.expenseId})}

		System.out.println("Printing the expense Id: " +id);

		expenseService.deleteExpense(id);

		return "redirect:/expenses";
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
