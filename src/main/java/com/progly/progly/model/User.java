package com.progly.progly.model;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @NotEmpty(message = "Benutzername darf nicht leer sein.")
    private String name;

    @Column(unique = true)
    @Email(message = "Bitte eine gültige Email-Adresse eingeben.")
    @NotNull
    @NotEmpty(message = "Email darf nicht leer sein.")
    private String email;

    @Column
    @NotNull
    private String password;

    @Transient
    private String retypePassword;

    @CreatedDate
    @Column
    protected Timestamp createdDate;

    @PrePersist
    protected void createDateTimestamp(){
        createdDate = Timestamp.from(Instant.now());
    }

}