package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UserDto {
    public UserDto() {}

    public UserDto(Long id, String name, String email, String login, RoleDto role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.role = role;
    }

    private Long id;
    private String name;
    private String email;
    private String login;
    private RoleDto role;
}
