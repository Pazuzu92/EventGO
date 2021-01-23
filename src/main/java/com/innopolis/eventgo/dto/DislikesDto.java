package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DislikesDto {
    public DislikesDto() {}

    public DislikesDto(Long id, Integer dislikes) {
        this.id = id;
        this.dislikes = dislikes;
    }

    private Long id;
    private Integer dislikes;
}
