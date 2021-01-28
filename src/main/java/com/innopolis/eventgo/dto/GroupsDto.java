package com.innopolis.eventgo.dto;

import com.innopolis.eventgo.db.entity.User;
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
