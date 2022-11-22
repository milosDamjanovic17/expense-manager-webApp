package com.moose.expensemanager.service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.moose.expensemanager.dto.ExpenseFilterDTO;
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

	// map to entity Util method, it will map DTO details to Entity class which will store in DB
	private Expense mapToEntity(ExpenseDTO theExpenseDTO) throws ParseException {

		// map the DTO to entity using modelmapper, modelmapper ce umesto nas pozvati gettere za theExpenseDTO i setovati u Expense.class
		Expense expense = modelMapper.map(theExpenseDTO, Expense.class);
		//generate the expense id, check if the expenseId already exists
		if(expense.getId() == null){
			expense.setExpenseId(UUID.randomUUID().toString());
		}

		// set the expense date
		expense.setDate(DateTimeUtil.convertStringToDate(theExpenseDTO.getDateString()));
		// return expense Entity
		return expense;
	}

	public void deleteExpense(String id){

		// find and retrieve expense by id
		Expense existingExpense = expenseRepository.findByExpenseId(id).orElseThrow(() -> new RuntimeException("Expense not found for this id: " +id) );
		// delete expense
		expenseRepository.delete(existingExpense);
	}

	public ExpenseDTO getExpenseById(String id){

		Expense existingExpense = expenseRepository.findByExpenseId(id).orElseThrow(() -> new RuntimeException("Expense not found for this id: " +id) );

		return mapToDTO(existingExpense);
	}

	public List<ExpenseDTO> getFilteredExpenses(ExpenseFilterDTO theExpenseFilterDTO) throws ParseException{

		String keyword = theExpenseFilterDTO.getKeyword();
		String sortBy = theExpenseFilterDTO.getSortBy();
		String startDateString = theExpenseFilterDTO.getStartDate();
		String endDateString = theExpenseFilterDTO.getEndDate();

		// convert Strings to Date type, ako je unet datum ?(true) konvertuj u Date type :(false) postavi new Date(0) => postavice ga kao null
		Date startDate = !startDateString.isEmpty() ? DateTimeUtil.convertStringToDate(startDateString) : new Date(0);
		Date endDate = !endDateString.isEmpty() ? DateTimeUtil.convertStringToDate(endDateString) : new Date(System.currentTimeMillis());

		// get the list
		List<Expense> entityList = expenseRepository.findByNameContainingAndDateBetween(keyword, startDate, endDate);
		// convert it to DTO
		List<ExpenseDTO> filteredDTOList = entityList.stream().map(this::mapToDTO).collect(Collectors.toList());

		// ako je odabrana opcija sortiranja po datumu, sortiraj date, ako nije, sortiraj amount
		if(sortBy.equals("date")){
			// sort by expense date
			filteredDTOList.sort(((o1, o2) -> o2.getDate().compareTo(o1.getDate())));
		}else{
			// sort by amount
			filteredDTOList.sort((o1, o2) -> o2.getAmount().compareTo(o1.getAmount()));
		}

		return filteredDTOList;
	}

}
