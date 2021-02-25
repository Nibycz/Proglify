package com.progly.progly.model;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @CreatedDate
    @Column
    protected Timestamp createdDate;

    @PrePersist
    protected void createDateTimestamp() {
        createdDate = Timestamp.from(Instant.now());
    }
}