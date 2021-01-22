package com.innopolis.eventgo.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private String name;

    private String email;

    private String login;

    private RoleDto role;

//    private Set<CommentDto> comments = new HashSet<>();
}
