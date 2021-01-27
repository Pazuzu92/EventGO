package com.innopolis.eventgo.dto;

import com.innopolis.eventgo.db.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupsDto {
    public GroupsDto() {}

    public GroupsDto(Long id, PostDto post, UserDto user) {
        this.id = id;
        this.post = post;
        this.user = user;
    }

    private Long id;
    private PostDto post;
    private UserDto user;
}
