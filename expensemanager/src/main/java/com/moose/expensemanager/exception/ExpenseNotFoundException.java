package com.moose.expensemanager.exception;

public class ExpenseNotFoundException extends RuntimeException{

    private String message;

    public ExpenseNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
