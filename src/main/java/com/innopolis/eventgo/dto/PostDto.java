package com.innopolis.eventgo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id_user;
    private String header;
    private String description;
    private String date_from;
    private String date_to;
    private Long id_category;
    private Long id_city;
    private PlaceDto placeDto;
}
