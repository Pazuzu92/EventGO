package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.repository.PostRepository;
import com.innopolis.eventgo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post getPost(long id) throws NotFoundException {
        Post post = postRepository.getPost(id);
        if (post == null) throw new NotFoundException("Post not found");
        return post;
    }

    public Post createPost(Post post) throws NotFoundException {
        if (!isValidPost(post)) throw new NotFoundException("Bad post");
        return postRepository.savePost(post);
    }

    public Post updatePost(long id, Post postUpdate) throws NotFoundException {
        Post post = postRepository.updatePost(id, postUpdate);
        if (isValidPost(post)) throw new NotFoundException("Post not found");
        return post;
    }

    public Post deletePost(long id) throws NotFoundException {
        Post post = postRepository.deletePost(id);
        if (post == null) throw new NotFoundException("Post not found");
        return post;
    }

    private boolean isValidPost(Post post) {
        if (post == null ||
        post.getHeader() == null ||
        post.getCategory() == null ||
        post.getDescription() == null ||
        post.getDateFrom() == null ||
        post.getDateTo() == null ||
        post.getPlace() == null) return false;
        return true;
    }
}
