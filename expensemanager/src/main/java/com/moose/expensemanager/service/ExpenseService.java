package com.moose.expensemanager.service;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moose.expensemanager.dto.ExpenseDTO;
import com.moose.expensemanager.entity.Expense;
import com.moose.expensemanager.repository.IExpenseRepository;
import com.moose.expensemanager.util.DateTimeUtil;

import lombok.RequiredArgsConstructor;

@Service
public class ExpenseService {
	
	private final IExpenseRepository expenseRepository;
	private final ModelMapper modelMapper;

	// inject fields
	@Autowired
	public ExpenseService(IExpenseRepository theExpenseRepository, ModelMapper modelMapper) {
		
		this.modelMapper = modelMapper;
		this.expenseRepository = theExpenseRepository;
	}
	
	
	// get all the data from DB and map it to data transfer object(DTO)
	public List<ExpenseDTO> getAllExpenses(){
		List<Expense> list = expenseRepository.findAll();
		
		List<ExpenseDTO> expenseList = list.stream().map(this::mapToDTO).collect(Collectors.toList());
		
		return expenseList;
	}	
	
	// private method that will map entity object and copy it to data transfer object(DTO)
	private ExpenseDTO mapToDTO(Expense exp) {
		
		// model mapper will automatically map entity to DTO class, it will automatically call for example -> (expDTO.setId(exp.getId())) etc. i tako za sva ostala polja
		// map(exp, ExpenseDTO.class) => maps from passed parameter Expense exp to a object/class ExpenseDTO.class
		ExpenseDTO expDTO = modelMapper.map(exp, ExpenseDTO.class);
			// let's show off our new date format =>
			expDTO.setDateString(DateTimeUtil.convertDateToString(expDTO.getDate()));
		
		return expDTO;
	}

	public ExpenseDTO saveExpenseDetails(ExpenseDTO theExpenseDTO) throws ParseException {

		// map the DTO to entity
		Expense expense = mapToEntity(theExpenseDTO);

		// Save entity to DB
		expense = expenseRepository.save(expense);

		// map the entity to DTO
		return mapToDTO(expense);
	}

	private Expense mapToEntity(ExpenseDTO theExpenseDTO) throws ParseException {

		// map the DTO to entity using modelmapper
		Expense expense = modelMapper.map(theExpenseDTO, Expense.class);
		//generate the expense id
		expense.setExpenseId(UUID.randomUUID().toString());

		// set the expense date
		expense.setDate(DateTimeUtil.convertStringToDate(theExpenseDTO.getDateString()));
		// return expense Entity
		return expense;
	}

}
