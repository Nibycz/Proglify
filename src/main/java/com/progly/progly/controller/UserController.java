package com.progly.progly.controller;

import com.progly.progly.model.User;
import com.progly.progly.model.dto.UserDto;
import com.progly.progly.service.UserServiceImpl;
import com.progly.progly.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
        // Valid prüft über die Annotation nach Fehlern (im DTO festgelegt). BindingResult holt sich die Daten (Fehler).
        // Falls Fehler vorhanden sind werden diese in eine Liste von Strings gespeichert(errors). Danach wird ein Response zurückgegeben.
        Boolean passwordMatch = userDto.checkPasswordMatching();

        if (results.hasErrors()) {
            List<String> errors = results.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            if(!passwordMatch) errors.add("Passwörter stimmen nicht überein.");
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }

        if(!passwordMatch) return new ResponseEntity("Passwörter stimmen nicht überein.", HttpStatus.BAD_REQUEST);

        Optional<User> userExists = userService.findUserByEmail(userDto.getEmail());
        if(userExists.isPresent()){
            return new ResponseEntity(String.format("Ein Benutzer mit der Email-Adresse %s ist bereits vorhanden.", userDto.getEmail()), HttpStatus.BAD_REQUEST);
        } else {
            userService.createUserAccount(userDto);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
