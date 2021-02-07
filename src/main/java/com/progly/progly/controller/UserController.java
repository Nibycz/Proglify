package com.progly.progly.controller;

import com.progly.progly.model.User;
import com.progly.progly.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController implements WebMvcConfigurer {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "registration")
    public void register(@Valid @RequestBody User user){
        userService.addNewUser(user);
    }
}
