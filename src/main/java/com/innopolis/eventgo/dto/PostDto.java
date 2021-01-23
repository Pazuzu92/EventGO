package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class PostDto {
    public PostDto() {}

    public PostDto(Long id, String header, String description, LikesDto likes, DislikesDto dislikes, String dateFrom, String dateTo, UserDto author, List<GroupsDto> groups, List<CommentDto> comment, CategoryDto category, PlaceDto place) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.likes = likes;
        this.dislikes = dislikes;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.author = author;
        this.groups = groups;
        this.comment = comment;
        this.category = category;
        this.place = place;
    }

    private Long id;
    private String header;
    private String description;
    private LikesDto likes;
    private DislikesDto dislikes;
    private String dateFrom;
    private String dateTo;
    private UserDto author;
    private List<GroupsDto> groups;
    private List<CommentDto> comment;
    private CategoryDto category;
    private PlaceDto place;
}
