package com.innopolis.eventgo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostDto {

    private String header;

    private String description;

    private int like;

    private int dislike;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private UserDto user;

    private CategoryDto category;

    private PostStatusDto postStatus;

    private PlaceDto place;

    private Set<CommentDto> comments = new HashSet<>();
}
