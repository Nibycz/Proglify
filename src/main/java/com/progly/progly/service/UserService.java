package com.progly.progly.service;

import com.progly.progly.model.User;
import com.progly.progly.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addNewUser(User user) {
        UserDetails userOptional = loadUserByUsername(user.getUsername());
        System.out.println(userOptional);
        //if (userOptional.isPresent()) {
        //    throw new IllegalStateException("Email bereits vergeben.");
        //}
        boolean samePassword = user.getPassword().equals(user.getRetypePassword());
        if (!samePassword) {
            throw new IllegalStateException("Passwörter stimmen nicht überein.");
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Benutzername %s kann nicht gefunden werden.", username)));
    }
}


