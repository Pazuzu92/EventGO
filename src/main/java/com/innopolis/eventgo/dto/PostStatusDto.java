package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostStatusDto {
    public PostStatusDto() {}

    public PostStatusDto(Long id, int status, String statusName) {
        this.id = id;
        this.status = status;
        this.statusName = statusName;
    }

    private Long id;
    private int status;
    private String statusName;
}
