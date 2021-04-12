package com.progly.progly.service;

import com.progly.progly.config.PasswordConfig;
import com.progly.progly.model.User;
import com.progly.progly.model.dto.LoginUserDto;
import com.progly.progly.model.dto.RegisterUserDto;
import com.progly.progly.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private final IUserRepository userRepository;

    @Autowired
    private final PasswordConfig passwordConfig;

    public UserServiceImpl(IUserRepository userRepository, PasswordConfig passwordConfig) {
        this.userRepository = userRepository;
        this.passwordConfig = passwordConfig;
    }


    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void createUserAccount(RegisterUserDto registerUserDto) {
        //Passwort encode für DB
        String encodedPassword = passwordConfig.passwordEncoder().encode(registerUserDto.getPassword());
        //DTO dem Model User zuweisen.
        final User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public void loginUser(LoginUserDto loginUserDto) {

    }
}


