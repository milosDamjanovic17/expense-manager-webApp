package com.moose.expensemanager.repository;

import com.moose.expensemanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {


    Optional<User>findByEmail(String email); // SELECT * FROM tbl_users WHERE email = ?


}
