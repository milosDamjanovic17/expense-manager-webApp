package com.moose.expensemanager.service;

import com.moose.expensemanager.dto.UserDTO;
import com.moose.expensemanager.entity.User;
import com.moose.expensemanager.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final IUserRepository userRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(IUserRepository userRepo, ModelMapper modelMapper){
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    public void save(UserDTO theUserDTO){
        User user = mapToEntity(theUserDTO);
            user.setUserId(UUID.randomUUID().toString());

            userRepo.save(user);
    }

    private User mapToEntity(UserDTO theUserDTO){
        return modelMapper.map(theUserDTO, User.class);
    }

}
