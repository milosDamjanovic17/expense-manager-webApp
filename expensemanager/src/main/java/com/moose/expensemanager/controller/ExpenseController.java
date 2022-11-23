package com.moose.expensemanager.controller;

import com.moose.expensemanager.dto.ExpenseDTO;
import com.moose.expensemanager.dto.ExpenseFilterDTO;
import com.moose.expensemanager.validator.ExpenseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.moose.expensemanager.service.ExpenseService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

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

		List<ExpenseDTO> allExpenses = expenseService.getAllExpenses();

		theModel.addAttribute("expensesList", allExpenses);
		theModel.addAttribute("filter", new ExpenseFilterDTO());
		String totalExpenses = expenseService.totalExpenses(allExpenses);
		theModel.addAttribute("totalExpenses", totalExpenses);


		return "expenses-list";
	}

	// bind DTO to expense form and show expense form
	@GetMapping("/createExpense")
	public String showExpenseForm(Model theModel){

		theModel.addAttribute("expense", new ExpenseDTO());

		return "expense-form";
	}

	@PostMapping("/saveOrUpdateExpense")
	public String saveOrUpdateExpenseDetails(@Valid @ModelAttribute("expense") ExpenseDTO theExpenseDTO,
											 BindingResult theBindingResult) throws ParseException {

		System.out.println("Printing expenseDTO" +theExpenseDTO);

		// create validator Obj, call the method and pass theBinding result
		new ExpenseValidator().validate(theExpenseDTO, theBindingResult);

		if(theBindingResult.hasErrors()){
			return "expense-form";
		}

		expenseService.saveExpenseDetails(theExpenseDTO);

		return "redirect:/expenses";
	}

	// let's retrieve the expense Id, and delete it
	@GetMapping("/deleteExpense")
	public String deleteExpense(@RequestParam String id){ // => th:href="@{/deleteExpense(id=${expense.expenseId})}

		System.out.println("Printing the expense Id: " +id);

		expenseService.deleteExpense(id);

		return "redirect:/expenses";
	}

	// handler method to show the expense form that we'll update with passed expenseId => (@RequestParam String id)
	@GetMapping("/updateExpense")
	public String updateExpense(@RequestParam String id, Model theModel){

		System.out.println("Print expense Id inside update method -> " +id);

		ExpenseDTO expenseDTO = expenseService.getExpenseById(id);

		theModel.addAttribute("expense", expenseDTO);

		/* S OBZIROM DA MORAMO DA VIDIMO VREDNOSTI EXPENSEA KOJI MORAMO DA UPDATEJUEMO, MORAMO VRATITI EXPENSE OBJ I BINDOVATI ZA th:object{expense} U expense-form.html */

		return "expense-form";
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
