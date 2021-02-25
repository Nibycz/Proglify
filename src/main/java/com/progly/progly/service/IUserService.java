package com.progly.progly.service;

import com.progly.progly.model.User;
import com.progly.progly.model.dto.UserDto;

import java.util.Optional;

public interface IUserService {

    Optional<User> findUserByEmail(String email);

    void createUserAccount(UserDto user);
}
