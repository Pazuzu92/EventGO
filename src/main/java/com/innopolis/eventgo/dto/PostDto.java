package com.innopolis.eventgo.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id_user;
    private String header;
    private String description;
    private String date_from;
    private String date_to;
    private CategoryDto category;
    private PlaceDto place;
}
