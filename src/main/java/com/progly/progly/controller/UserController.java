package com.progly.progly.controller;

import com.progly.progly.model.User;
import com.progly.progly.model.dto.UserDto;
import com.progly.progly.service.UserService;
import com.progly.progly.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping(path = "registration")
    public void createNewUser(@Valid @RequestBody UserDto userDto, BindingResult results){
        this.userValidator.validate(userDto, results);
        userService.addNewUser(userDto);
    }

    @PostMapping(path = "login")
    public void login(@Valid @RequestBody User user){
        userService.loginUser(user);
    }
}
