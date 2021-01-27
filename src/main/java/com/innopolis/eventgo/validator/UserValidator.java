package com.innopolis.eventgo.validator;

import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.db.repository.UserDAO;
import com.innopolis.eventgo.dto.UserRegistrationDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class UserValidator implements Validator {

    private final UserDAO userDAO;

    public UserValidator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegistrationDto user = (UserRegistrationDto) o;

        if (userDAO.findByLogin(user.getLogin()).isPresent()) {
            errors.rejectValue("login", "registration.DuplicateLogin", "There is an account with that login");
        }

        if (userDAO.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "registration.DuplicateEmail", "There is an account with that email address");
        }

        if (!Objects.equals(user.getMatchingPassword(), user.getPassword())) {
            errors.rejectValue("matchingPassword", "registration.DifferentPassword", "Passwords do not match");
        }
    }
}
