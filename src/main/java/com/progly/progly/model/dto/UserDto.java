package com.progly.progly.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Long id;

    @NotNull
    @NotEmpty(message = "Benutzername darf nicht leer sein.")
    private String username;

    @NotNull
    @Email(message = "Bitte eine gültige Email-Adresse eingeben.")
    @NotEmpty(message = "Email darf nicht leer sein.")
    private String email;

    private Character password;
    private Character matchingPassword;

}
