package com.moose.expensemanager.controller;

import com.moose.expensemanager.dto.ExpenseDTO;
import com.moose.expensemanager.dto.ExpenseFilterDTO;
import com.moose.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class ExpenseFilterController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseFilterController(ExpenseService theExpenseService){

        expenseService = theExpenseService;
    }

    @GetMapping("/filterExpenses")
    public String filterExpenses(@ModelAttribute("filter") ExpenseFilterDTO expenseFilterDTO, Model theModel){

        System.out.println("Printing the filter DTO: " +expenseFilterDTO);

        List<ExpenseDTO> localList = expenseService.getFilteredExpenses(expenseFilterDTO.getKeyword());

        theModel.addAttribute("expensesList", localList );

        return "expenses-list";
    }

}
