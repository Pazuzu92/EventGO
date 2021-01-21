package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.*;
import com.innopolis.eventgo.db.repository.PostRepository;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.PostNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PostService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private PostRepository postRepository;

    public Post getPost(long id) throws PostNotFoundException {
        Post post = postRepository.getPost(id);
        if (post == null) throw new PostNotFoundException("Post not found");
        return post;
    }

    public ResponseMessageEntity createPost(PostDto post) throws PostNotFoundException {
        if (!isValidPost(post)) throw new PostNotFoundException("Bad post");

        User user = new User();
        user.setId(post.getId_user());
        City city = new City();
        city.setId(post.getId_city());

        Place place = new Place();
        place.setId(post.getId_city());
        place.setStreet(post.getPlaceDto().getStreet());
        place.setHouse(post.getPlaceDto().getHouse());
        place.setNumber(post.getPlaceDto().getNumber());
        place.setCity(city);

        Category category = new Category();
        category.setId(post.getId_category());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH.mm");

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

    public Post updatePost(long id, PostDto postUpdate) throws PostNotFoundException {
        if (isValidPost(postUpdate)) throw new PostNotFoundException("Post not found");
        Post postEntity = modelMapper.map(postUpdate, Post.class);

        return null;
    }

    public Post deletePost(long id) throws PostNotFoundException {
        Post post = postRepository.deletePost(id);
        if (post == null) throw new PostNotFoundException("Post not found");
        return post;
    }

    private boolean isValidPost(PostDto post) {
        if (post == null ||
        post.getId_user() == null ||
        post.getHeader() == null ||
        post.getDate_from() == null ||
        post.getDate_to() == null ||
        post.getId_category() == null ||
        post.getId_city() == null ||
        post.getPlaceDto() == null ||
        post.getPlaceDto().getStreet() == null ||
        post.getPlaceDto().getHouse() == null ||
        post.getPlaceDto().getNumber() == null) return false;
        return true;
    }

    private ResponseMessageEntity getResponseMessage() {
        ResponseMessageEntity rme = new ResponseMessageEntity();
        rme.setCode(200);
        rme.setMessage("Ok");
        return rme;
    }
}
