package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.*;
import com.innopolis.eventgo.db.repository.PostRepository;
import com.innopolis.eventgo.dto.*;
import com.innopolis.eventgo.exceptions.PostNotFoundException;
import com.innopolis.eventgo.mappers.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PostService {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH.mm");

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostRepository postRepository;

    public PostDto getPost(long id) throws PostNotFoundException {
        Post post = postRepository.getPost(id);
        if (post == null) throw new PostNotFoundException("Post not found");

        return postMapper.mapToDto(post);
    }

    public ResponseMessageEntity createPost(PostDto postDto) throws PostNotFoundException {
        if (!isValidPost(postDto)) throw new PostNotFoundException("Bad post");

        Post postEntity = postMapper.mapToPost(postDto);
        postEntity.setDate_create(LocalDateTime.now());

        return getResponseMessage();
    }

    public ResponseMessageEntity updatePost(long id, PostDto postUpdate) throws PostNotFoundException {
        Post post = postRepository.getPost(id);
        if (!isValidPost(postUpdate) || post == null) throw new PostNotFoundException("Post not found");

        Place place = postRepository.getPlaceById(postUpdate.getPlace().getId());
        Category category = postRepository.getCategoryById(postUpdate.getCategory().getId());

        post.setHeader(postUpdate.getHeader());
        post.setDescription(postUpdate.getDescription());
        post.setDateTo(LocalDateTime.parse(postUpdate.getDateTo(), dateTimeFormatter));
        post.setDateFrom(LocalDateTime.parse(postUpdate.getDateFrom(), dateTimeFormatter));
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
