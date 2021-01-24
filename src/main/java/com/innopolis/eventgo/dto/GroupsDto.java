package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupsDto {
    public GroupsDto() {}

    public GroupsDto(Long id, UserDto user) {
        this.id = id;
        this.user = user;
    }

    private Long id;
    private UserDto user;
}
