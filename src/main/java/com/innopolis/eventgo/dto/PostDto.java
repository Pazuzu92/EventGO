package com.innopolis.eventgo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostDto {
    private Long idUser;
    private String header;
    private String description;
    private String dateFrom;
    private String dateTo;
    private List<CommentDto> comment;
    private CategoryDto category;
    private PlaceDto place;
}
