package com.moose.expensemanager.controller;

import com.moose.expensemanager.dto.UserDTO;
import com.moose.expensemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public  AuthController(UserService userService){

        this.userService = userService;
    }

    @GetMapping({"/login", "/"}) // we can assign multiple paths that will show login.html
    public String showLoginPage(){

        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model theModel){

        theModel.addAttribute("user", new UserDTO());

        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDTO theUserDTO, Model theModel){

        System.out.println("Printing the user details: " +theUserDTO);

        userService.save(theUserDTO);

        theModel.addAttribute("successMsg", true);

        return "login";
    }

}



















