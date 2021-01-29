package com.innopolis.eventgo.registration.impl;


import com.innopolis.eventgo.dto.UserRegistrationDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class PasswordConstraintValidatorTest {
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

//    @Test
//    public void testInvalidPassword() {
//        UserRegistrationDto userRegistration = new UserRegistrationDto();
//        userRegistration.setLogin("memory");
//        userRegistration.setName("denis");
//        userRegistration.setEmail("info@memorynotfound.com");
//        userRegistration.setPassword("password");
//        userRegistration.setMatchingPassword("password");
//
//        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistration);
//
//        Assert.assertEquals(constraintViolations.size(), 1);
//    }
//
//    @Test
//    public void testValidPasswords() {
//        UserRegistrationDto userRegistration = new UserRegistrationDto();
//        userRegistration.setLogin("memory");
//        userRegistration.setName("denis");
//        userRegistration.setEmail("info@memorynotfound.com");
//        userRegistration.setPassword("xJ3!dij50");
//        userRegistration.setMatchingPassword("xJ3!dij50");
//
//        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistration);
//
//        Assert.assertEquals(constraintViolations.size(), 0);
//    }
}
