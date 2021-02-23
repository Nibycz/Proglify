package com.progly.progly.validator;


import com.progly.progly.model.dto.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(final Class<?> aClass) {
        return UserDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(final Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message.username", "Benutzername wird benötigt.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message.email", "Email wird benötigt.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message.password", "Passwort wird benötigt.");
    }
}
