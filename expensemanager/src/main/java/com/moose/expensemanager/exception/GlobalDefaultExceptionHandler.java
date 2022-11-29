package com.moose.expensemanager.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public String handleExpenseNotFoundException(HttpServletRequest request, ExpenseNotFoundException exc, Model theModel){

        theModel.addAttribute("notFound", true);
        theModel.addAttribute("message", exc.getMessage());

        return "response";
    }

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(HttpServletRequest request, Exception exc, Model theModel){

        theModel.addAttribute("serverError", true);
        theModel.addAttribute("message", exc.getMessage());
        theModel.addAttribute("stackTrack", exc.getStackTrace());

        return "response";
    }

}
