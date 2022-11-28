package com.moose.expensemanager.service;

import com.moose.expensemanager.dto.UserDTO;
import com.moose.expensemanager.entity.User;
import com.moose.expensemanager.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final IUserRepository userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserDTO theUserDTO){
        theUserDTO.setPassword(passwordEncoder.encode(theUserDTO.getPassword())); // encode the password
        User user = mapToEntity(theUserDTO);
            user.setUserId(UUID.randomUUID().toString());

            userRepo.save(user);
    }

    private User mapToEntity(UserDTO theUserDTO){
        return modelMapper.map(theUserDTO, User.class);
    }

}
