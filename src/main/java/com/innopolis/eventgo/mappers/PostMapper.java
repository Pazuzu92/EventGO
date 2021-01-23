package com.innopolis.eventgo.mappers;

import com.innopolis.eventgo.db.entity.*;
import com.innopolis.eventgo.dto.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
        DislikesDto dislikesDto = DislikesDto.builder()
                .id(post.getDislikes().getId())
                .dislikes(post.getDislikes().getDislikes())
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
                .cityName(post.getPlace().getCity().getCityName())
                .build();
        PlaceDto placeDto = PlaceDto.builder()
                .id(post.getPlace().getId())
                .street(post.getPlace().getStreet())
                .house(post.getPlace().getHouse())
                .number(post.getPlace().getNumber())
                .city(cityDto)
                .build();

        PostDto postDto = PostDto.builder()
                .id(post.getId())
                .header(post.getHeader())
                .description(post.getDescription())
                .likes(likesDto)
                .dislikes(dislikesDto)
                .dateFrom(post.getDateFrom().toString())
                .dateTo(post.getDateTo().toString())
                .author(userDto)
                .groups(groupsDtos)
                .comment(commentDtos)
                .category(categoryDto)
                .place(placeDto)
                .build();

        return postDto;
    }

    public Post mapToPost(PostDto postDto) {
        Post post = new Post();

        Role role = new Role();
        role.setId(post.getUser().getRole().getId());
        role.setRoleCode(post.getUser().getRole().getRoleCode());

        User user = new User();
        user.setId(post.getUser().getId());
        user.setEmail(post.getUser().getEmail());
        user.setLogin(post.getUser().getLogin());
        user.setName(post.getUser().getName());
        user.setRole(role);

        Category category = new Category();
        category.setId(post.getCategory().getId());
        category.setNameCategory(post.getCategory().getNameCategory());

        City city = new City();
        city.setId(post.getPlace().getCity().getId());
        city.setCityName(post.getPlace().getCity().getCityName());

        Place place = new Place();
        place.setId(post.getPlace().getId());
        place.setStreet(post.getPlace().getStreet());
        place.setHouse(post.getPlace().getHouse());
        place.setNumber(post.getPlace().getNumber());

        post.setId(post.getId());
        post.setHeader(postDto.getHeader());
        post.setDescription(post.getDescription());
        post.setDateFrom(LocalDateTime.parse(postDto.getDateFrom(), dateTimeFormatter));
        post.setDateTo(LocalDateTime.parse(postDto.getDateTo(), dateTimeFormatter));
        post.setUser(user);
        post.setCategory(category);
        post.setPlace(place);

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
