package com.progly.progly.service;

import com.progly.progly.model.User;
import com.progly.progly.model.dto.RegisterUserDto;

import java.util.Optional;

public interface IUserService {

    Optional<User> findUserByEmail(String email);

    void createUserAccount(RegisterUserDto user);
}
