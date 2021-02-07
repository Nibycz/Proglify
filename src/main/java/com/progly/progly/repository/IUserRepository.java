package com.progly.progly.repository;

import com.progly.progly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
