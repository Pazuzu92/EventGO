package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoDto {
    public PhotoDto() {}

    public PhotoDto(Long id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    private Long id;
    private byte[] image;
}
