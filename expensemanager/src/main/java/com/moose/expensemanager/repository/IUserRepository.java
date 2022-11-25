package com.moose.expensemanager.repository;

import com.moose.expensemanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
