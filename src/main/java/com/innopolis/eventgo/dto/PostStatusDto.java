package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostStatusDto {
    public PostStatusDto() {}

    public PostStatusDto(Long id, int status) {
        this.id = id;
        this.status = status;
    }

    private Long id;
    private int status;
}
