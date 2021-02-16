package com.progly.progly.controller;

import com.progly.progly.model.User;
import com.progly.progly.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "registration")
    public ResponseEntity<Long> register(@Valid @RequestBody User user){
        userService.addNewUser(user);
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }
}
