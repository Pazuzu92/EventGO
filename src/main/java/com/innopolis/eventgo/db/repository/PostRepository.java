package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Category;
import com.innopolis.eventgo.db.entity.City;
import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.entity.User;

public interface PostRepository {

    Post getPost(Long id);
    void savePost(Post post);
    Post updatePost(Long id, Post post);
    Post deletePost(Long id);

    City saveCity(City city);
    City getCityById(Long id);

    void saveCategory(Category category);
    Category getCategoryById(Long id);
}
