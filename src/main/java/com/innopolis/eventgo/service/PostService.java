package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.entity.ResponseMessageEntity;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.PostNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostDto getPost(long id) throws PostNotFoundException;

    ResponseMessageEntity createPost(PostDto post) throws PostNotFoundException;

    ResponseMessageEntity updatePost(long id, PostDto postUpdate) throws PostNotFoundException;

    Post deletePost(long id) throws PostNotFoundException;

    List<PostDto> getPostsByFilter(Optional<String> cityName,
                                   Optional<String> categoryName,
                                   Optional<Long> postStatusName,
                                   Optional<Integer> page,
                                   Optional<Integer> size,
                                   Optional<String> sort) throws PostNotFoundException;
}
