package com.progly.progly.controller;

import com.progly.progly.model.User;
import com.progly.progly.model.dto.UserDto;
import com.progly.progly.service.UserServiceImpl;
import com.progly.progly.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    private final UserValidator userValidator;

    public UserController(UserServiceImpl userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping(path = "registration")
    public void createNewUser(@Valid @RequestBody UserDto userDto, BindingResult results) {
        userValidator.validate(userDto, results);
        if (results.hasErrors()) {
            System.out.println(results.getFieldErrors());
        }
        Optional<User> userExists = userService.findUserByEmail(userDto.getEmail());
    }

}
