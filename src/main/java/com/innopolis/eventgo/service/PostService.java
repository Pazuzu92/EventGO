package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.*;
import com.innopolis.eventgo.db.repository.PostRepository;
import com.innopolis.eventgo.dto.CategoryDto;
import com.innopolis.eventgo.dto.CityDto;
import com.innopolis.eventgo.dto.PlaceDto;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PostService {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH.mm");

    @Autowired
    private PostRepository postRepository;

    public PostDto getPost(long id) throws PostNotFoundException {
        Post post = postRepository.getPost(id);
        if (post == null) throw new PostNotFoundException("Post not found");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(post.getCategory().getId());
        categoryDto.setCategoryName(post.getCategory().getNameCategory());

        CityDto cityDto = new CityDto();
        cityDto.setId(post.getPlace().getCity().getId());

        PlaceDto placeDto = new PlaceDto();
        placeDto.setId(post.getPlace().getId());
        placeDto.setHouse(post.getPlace().getHouse());
        placeDto.setStreet(post.getPlace().getStreet());
        placeDto.setNumber(post.getPlace().getNumber());
        placeDto.setCity(cityDto);

        PostDto postDto = new PostDto();
        postDto.setId_user(post.getUser().getId());
        postDto.setHeader(post.getHeader());
        postDto.setDescription(post.getDescription());
        postDto.setCategory(categoryDto);
        postDto.setPlace(placeDto);
        postDto.setDate_from(post.getDateFrom().toString());
        postDto.setDate_to(post.getDateTo().toString());
        return postDto;
    }

    public ResponseMessageEntity createPost(PostDto post) throws PostNotFoundException {
        if (!isValidPost(post)) throw new PostNotFoundException("Bad post");

        User user = new User();
        user.setId(post.getId_user());
        City city = new City();
        city.setId(post.getPlace().getCity().getId());

        Place place = new Place();
        place.setId(post.getPlace().getCity().getId());
        place.setStreet(post.getPlace().getStreet());
        place.setHouse(post.getPlace().getHouse());
        place.setNumber(post.getPlace().getNumber());
        place.setCity(city);

        Category category = new Category();
        category.setId(post.getCategory().getId());

        Post postEntity = new Post();
        postEntity.setUser(user);
        postEntity.setHeader(post.getHeader());
        postEntity.setDescription(post.getDescription());
        postEntity.setDateFrom(LocalDateTime.parse(post.getDate_from(), dateTimeFormatter));
        postEntity.setDateTo(LocalDateTime.parse(post.getDate_to(), dateTimeFormatter));
        postEntity.setCategory(category);
        postEntity.setPlace(place);
        postEntity.setDate_create(LocalDateTime.now());
        if (postRepository.savePost(postEntity) == null) throw new PostNotFoundException("Error save post");
        return getResponseMessage();
    }

    public ResponseMessageEntity updatePost(long id, PostDto postUpdate) throws PostNotFoundException {
        Post post = postRepository.getPost(id);
        if (!isValidPost(postUpdate) || post == null) throw new PostNotFoundException("Post not found");

        Place place = postRepository.getPlaceById(postUpdate.getPlace().getId());
        Category category = postRepository.getCategoryById(postUpdate.getCategory().getId());

        post.setHeader(postUpdate.getHeader());
        post.setDescription(postUpdate.getDescription());
        post.setDateTo(LocalDateTime.parse(postUpdate.getDate_to(), dateTimeFormatter));
        post.setDateFrom(LocalDateTime.parse(postUpdate.getDate_from(), dateTimeFormatter));
        post.setCategory(category);
        post.setPlace(place);

        postRepository.updatePost(id, post);

        return getResponseMessage();
    }

    public Post deletePost(long id) throws PostNotFoundException {
        Post post = postRepository.deletePost(id);
        if (post == null) throw new PostNotFoundException("Post not found");
        return post;
    }

    private boolean isValidPost(PostDto post) {

        return true;
    }

    private ResponseMessageEntity getResponseMessage() {
        ResponseMessageEntity rme = new ResponseMessageEntity();
        rme.setCode(200);
        rme.setMessage("Ok");
        return rme;
    }
}
