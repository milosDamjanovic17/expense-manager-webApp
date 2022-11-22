package com.moose.expensemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseFilterDTO {

    private String keyword;

    private String sortBy;

    private String startDate;
    private String endDate;
}
