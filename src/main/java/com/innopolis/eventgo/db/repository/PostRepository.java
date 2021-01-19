package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Post;

public interface PostRepository {

    Post getPost(long id);
    Post savePost(Post post);
    Post updatePost(long id, Post post);
    Post deletePost(long id);
}
