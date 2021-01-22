package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Category;
import com.innopolis.eventgo.db.entity.Place;
import com.innopolis.eventgo.db.entity.Post;

public interface PostRepository {

    Post getPost(Long id);
    Post savePost(Post post);
    Post updatePost(Long id, Post post);
    Post deletePost(Long id);
    Place getPlaceById(Long id);
    Category getCategoryById(Long id);
}
