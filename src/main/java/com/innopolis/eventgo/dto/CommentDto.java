package com.innopolis.eventgo.dto;

import lombok.Data;

@Data
public class CommentDto {

    private UserDto userDto;
    private String text;
}
