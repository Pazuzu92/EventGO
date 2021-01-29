package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.Category;
import com.innopolis.eventgo.db.entity.City;
import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.entity.ResponseMessageEntity;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.NotFoundException;


import java.util.List;
import java.util.Optional;

public interface PostService {

    PostDto getPost(long id) throws NotFoundException;
    Post getPostEntity(long id) throws NotFoundException;

    ResponseMessageEntity createPost(PostDto post) throws NotFoundException;

    ResponseMessageEntity updatePost(long id, PostDto postUpdate) throws NotFoundException;

    Post deletePost(long id) throws NotFoundException;

    List<PostDto> getPostsByFilter(Optional<String> cityName,
                                   Optional<String> categoryName,
                                   Optional<Integer> postStatusName,
                                   Optional<Integer> page,
                                   Optional<Integer> size,
                                   Optional<String> sort) throws NotFoundException;

    List<PostDto> getPostsByAuthor(Optional<Long> authorId,
                                   Optional<Integer> page,
                                   Optional<Integer> size,
                                   Optional<String> sort) throws NotFoundException;

    List<Post> getGroupsPosts(Optional<Long> authorId) throws NotFoundException;

    List<Post> findAllOrderById();

    Optional<Post> findById(Long id);

    void updatePostByStatus(Post post, Long statusId);

    List<Post> findAllLikeBy(String text, City city, Category category);

    void likePost(Long id);

    void follow(Long idPost, Long idUser);

    int getFollowers(Long idPost);
}
