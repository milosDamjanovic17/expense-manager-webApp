package com.moose.expensemanager.validator;

import com.moose.expensemanager.dto.ExpenseDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ExpenseValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ExpenseDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // let's parse Object to ExpenseDTO, since we are creating a custom Validator for ExpenseDTO field dateString
        ExpenseDTO expenseDTO = (ExpenseDTO) target;

        if(expenseDTO.getDateString().equals("") || expenseDTO.getDateString().isEmpty() || expenseDTO.getDateString() == null){
            errors.rejectValue("dateString", null, "Expense date should not be null");
        }

    }
}
