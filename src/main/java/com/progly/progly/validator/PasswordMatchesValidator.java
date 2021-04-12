package com.progly.progly.validator;

import com.progly.progly.model.dto.RegisterUserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<IPasswordMatches, Object> {


    @Override
    public void initialize(IPasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        RegisterUserDto user = (RegisterUserDto) o;

        return user.getPassword().equals(user.getMatchingPassword());
    }
}
