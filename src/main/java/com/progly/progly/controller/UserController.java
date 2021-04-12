package com.progly.progly.controller;

import com.progly.progly.model.User;
import com.progly.progly.model.dto.LoginUserDto;
import com.progly.progly.model.dto.RegisterUserDto;
import com.progly.progly.service.UserServiceImpl;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(path = "registration")
    public ResponseEntity createNewUser(@Valid @RequestBody RegisterUserDto registerUserDto, BindingResult results) {
        if (results.hasErrors()) {
            List<String> errors = results.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }

        Optional<User> userExists = userService.findUserByEmail(registerUserDto.getEmail());
        if (userExists.isPresent()) {
            return new ResponseEntity(String.format("Ein Benutzer mit der Email-Adresse %s ist bereits vorhanden.", registerUserDto.getEmail()), HttpStatus.BAD_REQUEST);
        } else {
            userService.createUserAccount(registerUserDto);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "login")
    public ResponseEntity loginUser(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult results) {

        if (results.hasErrors()) {
            List<String> errors = results.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }

        Optional<User> userExists = userService.findUserByEmail(loginUserDto.getEmail());
        if (userExists.isPresent()) {
            userService.loginUser(loginUserDto);
        } else {
            return new ResponseEntity(String.format("Kein Benutzer mit der Email-Adresse %s gefunden.", loginUserDto.getEmail()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
