package com.progly.progly.model.dto;

import com.progly.progly.validator.IPasswordMatches;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@IPasswordMatches
public class RegisterUserDto implements Serializable {

    @NotNull(message = "Benutzername darf nicht leer sein.")
    @NotBlank(message = "Benutzername darf nicht leer sein.")
    @Size(min = 4, max = 15, message = "Benutzername muss mindestens 4 Zeichen besitzen und darf max. 15 Zeichen haben.")
    private String username;

    @Email(message = "Keine korrekte Email-Adresse.")
    @NotBlank(message = "Email darf nicht leer sein.")
    @NotNull(message = "Email darf nicht leer sein.")
    private String email;

    //String Passwörter müssen noch zu Character geändert werden.
    @NotNull(message = "Passwort darf nicht leer sein.")
    /*
    Regex für Passwort:
        Muss mindestens 1 Zahl von 0-9 enthalten.
        Mindestens 1 Großbuchstabe
        Mindestens 1 Kleinbuchstabe
        Mindestens 1 Sonderzeichen -> !@#&()*
        Passwort muss mindestens 8 Zeichen lang sein.
     */
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@*#&()–[{}]:;',?/*~$^+=<>]).{8,}$",
            message = "Passwort entspricht nicht den Erwartungen")
    private String password;

    private String matchingPassword;
}
