package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    public CommentDto() {}

    public CommentDto(Long id, String text, UserDto userDto) {
        this.id = id;
        this.text = text;
        this.userDto = userDto;
    }

    private Long id;
    private String text;
    private UserDto userDto;
}
