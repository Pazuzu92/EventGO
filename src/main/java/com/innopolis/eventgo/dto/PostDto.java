package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostDto {
    public PostDto() {
    }

    public PostDto(Long id, String header, String address, String description, LikesDto likes, DislikesDto dislikes, LocalDateTime dateFrom, LocalDateTime dateTo, UserDto author, List<GroupsDto> groups, List<CommentDto> comment, byte[] photo, CategoryDto category, CityDto city) {
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
        this.photo = photo;
        this.category = category;
        this.city = city;
    }

    private Long id;
    private String header;
    private String description;
    private String address;
    private LikesDto likes;
    private DislikesDto dislikes;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTo;
    private UserDto author;
    private List<GroupsDto> groups;
    private List<CommentDto> comment;
    private byte[] photo;
    private CategoryDto category;
    private CityDto city;
}
