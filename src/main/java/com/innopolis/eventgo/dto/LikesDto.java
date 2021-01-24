package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikesDto {
    public LikesDto() {}

    public LikesDto(Long id, Integer likes) {
        this.id = id;
        this.likes = likes;
    }

    private Long id;
    private Integer likes;
}
