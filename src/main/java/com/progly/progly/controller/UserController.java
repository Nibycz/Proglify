package com.progly.progly.controller;

import com.progly.progly.model.User;
import com.progly.progly.model.dto.UserDto;
import com.progly.progly.service.UserServiceImpl;
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
    public ResponseEntity createNewUser(@Valid @RequestBody UserDto userDto, BindingResult results) {
        if (results.hasErrors()) {
            List<String> errors = results.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }

        Optional<User> userExists = userService.findUserByEmail(userDto.getEmail());
        if (userExists.isPresent()) {
            return new ResponseEntity(String.format("Ein Benutzer mit der Email-Adresse %s ist bereits vorhanden.", userDto.getEmail()), HttpStatus.BAD_REQUEST);
        } else {
            userService.createUserAccount(userDto);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
