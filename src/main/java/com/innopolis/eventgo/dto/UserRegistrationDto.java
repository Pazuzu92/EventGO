package com.innopolis.eventgo.dto;


import com.innopolis.eventgo.validator.PasswordCheck;
import com.innopolis.eventgo.validator.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRegistrationDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    @PasswordCheck
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;
}
