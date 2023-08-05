package com.moose.expensemanager.service;

import com.moose.expensemanager.dto.UserDTO;
import com.moose.expensemanager.entity.User;
import com.moose.expensemanager.repository.IUserDao;
import com.moose.expensemanager.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final IUserRepository userRepo;
    private final IUserDao theUserDao;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder, IUserDao theUserDao){
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.theUserDao = theUserDao;
    }

    public void save(UserDTO theUserDTO){
        theUserDTO.setPassword(passwordEncoder.encode(theUserDTO.getPassword())); // encode the password
        User user = mapToEntity(theUserDTO);
            user.setUserId(UUID.randomUUID().toString());

            theUserDao.save(user);
    }

    public User getLoggedInUser(){
        // once the user is validated and logged Spring Security will send an Authentication object, defined in our Security Config class
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserEmail = auth.getName();

        return userRepo.findByEmail(loggedUserEmail).orElseThrow(() ->
                new UsernameNotFoundException("User email not found: " +loggedUserEmail));
    }

    private User mapToEntity(UserDTO theUserDTO){

        return modelMapper.map(theUserDTO, User.class);
    }

}
