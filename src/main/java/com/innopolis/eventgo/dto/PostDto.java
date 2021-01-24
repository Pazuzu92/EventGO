package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostDto {
    public PostDto() {
    }

    public PostDto(Long id, String header, String address, String description, LikesDto likes, DislikesDto dislikes, String dateFrom, String dateTo, UserDto author, List<GroupsDto> groups, List<CommentDto> comment, CategoryDto category, CityDto city) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.address = address;
        this.likes = likes;
        this.dislikes = dislikes;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.author = author;
        this.groups = groups;
        this.comment = comment;
        this.category = category;
        this.city = city;
    }

    private Long id;
    private String header;
    private String description;
    private String address;
    private LikesDto likes;
    private DislikesDto dislikes;
    private String dateFrom;
    private String dateTo;
    private UserDto author;
    private List<GroupsDto> groups;
    private List<CommentDto> comment;
    private CategoryDto category;
    private CityDto city;
}
