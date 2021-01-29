package com.innopolis.eventgo.mappers;

import com.innopolis.eventgo.db.entity.Category;
import com.innopolis.eventgo.db.entity.City;
import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.dto.*;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH.mm");

    public PostDto mapToDto(Post post) {
        LikesDto likesDto = LikesDto.builder()
                .id(post.getLikes().getId())
                .likes(post.getLikes().getLikes())
                .build();
        UserDto userDto = mapUserToDto(post.getUser());
        List<GroupsDto> groupsDtos = new ArrayList<>();
        post.getGroups().forEach(g -> {
            UserDto user = mapUserToDto(g.getUser());
            GroupsDto groupsDto = GroupsDto.builder()
                    .id(g.getId())
                    .user(user)
                    .build();
            groupsDtos.add(groupsDto);
        });
        List<CommentDto> commentDtos = new ArrayList<>();
        post.getComments().forEach(c -> {
            CommentDto commentDto = CommentDto.builder()
                    .id(c.getId())
                    .text(c.getText())
                    .userDto(mapUserToDto(c.getUser()))
                    .build();
            commentDtos.add(commentDto);
        });
        CategoryDto categoryDto = CategoryDto.builder()
                .id(post.getCategory().getId())
                .categoryName(post.getCategory().getNameCategory())
                .build();
        CityDto cityDto = CityDto.builder()
                .id(post.getId())
                .cityName(post.getCity().getCityName())
                .shortName(post.getCity().getShortName())
                .build();

        PostDto postDto = PostDto.builder()
                .id(post.getId())
                .header(post.getHeader())
                .description(post.getDescription())
                .address(post.getAddress())
                .likes(likesDto)
                .dateFrom(post.getDateFrom())
                .dateTo(post.getDateTo())
                .author(userDto)
                .groups(groupsDtos)
                .comment(commentDtos)
                .photo(post.getImage())
                .category(categoryDto)
                .city(cityDto)
                .build();

        postDto.setAddress(post.getAddress());
        postDto.setDescription(post.getDescription());

        return postDto;
    }

    public Post mapToPost(PostDto postDto) {
        Post post = new Post();

        User user = new User();
        user.setId(postDto.getAuthor().getId());
        user.setEmail(postDto.getAuthor().getEmail());
        user.setLogin(postDto.getAuthor().getLogin());
        user.setName(postDto.getAuthor().getName());

        Category category = new Category();
        category.setId(postDto.getCategory().getId());
        category.setNameCategory(postDto.getCategory().getCategoryName());

        City city = new City();
        city.setId(postDto.getCity().getId());
        city.setCityName(postDto.getCity().getCityName());
        city.setShortName(postDto.getCity().getShortName());

        post.setId(postDto.getId());
        post.setHeader(postDto.getHeader());
        post.setAddress(postDto.getAddress());
        post.setImage(postDto.getPhoto());
        post.setDescription(postDto.getDescription());
        post.setDateFrom(postDto.getDateFrom());
        post.setDateTo(postDto.getDateTo());
        post.setDateFrom(postDto.getDateFrom());
        post.setDateTo(postDto.getDateTo());
        post.setUser(user);
        post.setCategory(category);
        post.setCity(city);

        return post;
    }

    private UserDto mapUserToDto(User user) {
        RoleDto roleDto = RoleDto.builder()
                .id(user.getRole().getId())
                .roleCode(user.getRole().getRoleCode())
                .build();
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .login(user.getLogin())
                .role(roleDto)
                .build();
    }
}
