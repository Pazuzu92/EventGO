package com.innopolis.eventgo.restcontroller;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.repository.PostRepository;
import com.innopolis.eventgo.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/post/{postId}")
    public Post getPost(@PathVariable("postId") long id) throws PostNotFoundException {
        Post post = postRepository.getPost(id);
        if (post == null) throw new PostNotFoundException("Post not found");
        return post;
    }

    @PostMapping(value = "/post")
    public Post createPost(@RequestBody Post post) throws PostNotFoundException {
        if (post.getHeader() == null) throw new PostNotFoundException("Bad post");
        return postRepository.savePost(post);
    }

    @PutMapping(value = "/post/{postId}")
    public Post updatePost(@PathVariable("postId") long id, @RequestBody Post postUpdate) throws PostNotFoundException {
        Post post = postRepository.updatePost(id, postUpdate);
        if (post == null) throw new PostNotFoundException("Post not found");
        return post;
    }

    @DeleteMapping(value = "/post/{postId}")
    public Post deletePost(@PathVariable("postId") long id) throws PostNotFoundException {
        Post post = postRepository.deletePost(id);
        if (post == null) throw new PostNotFoundException("Post not found");
        return post;
    }
}
