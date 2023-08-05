package com.moose.expensemanager;

import com.moose.expensemanager.dto.UserDTO;
import com.moose.expensemanager.repository.IUserRepository;
import com.moose.expensemanager.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class ExpensemanagerApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	IUserRepository userRepository;

	@Test
	void createUserService() {

		userService.save(new UserDTO("Milos", "milos@testemail.com", "12345!", "12345!"));

		assertNotNull(userRepository.findByEmail("milos@testemail.com"));
	}

}
