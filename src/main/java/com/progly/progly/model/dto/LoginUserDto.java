package com.progly.progly.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class LoginUserDto implements Serializable {

    @Email(message = "Keine korrekte Email-Adresse.")
    @NotBlank(message = "Email darf nicht leer sein.")
    @NotNull(message = "Email darf nicht leer sein.")
    private String email;

    //String Passwörter müssen noch zu Character geändert werden.
    @NotNull(message = "Passwort darf nicht leer sein.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@*#&()–[{}]:;',?/*~$^+=<>]).{8,}$",
            message = "Passwort entspricht nicht den Erwartungen")
    private String password;
}
