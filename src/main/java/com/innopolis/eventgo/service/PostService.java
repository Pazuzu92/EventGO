package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.exceptions.PostNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post getPost(long id) throws PostNotFoundException;

    Post createPost(Post post) throws PostNotFoundException;

    Post updatePost(long id, Post postUpdate) throws PostNotFoundException;

    Post deletePost(long id) throws PostNotFoundException;

    List<Post> getPostByFilter(Optional<String> city, Optional<String> category, Optional<Integer> page,
                               Optional<Integer> size, String sort) throws PostNotFoundException;
}
