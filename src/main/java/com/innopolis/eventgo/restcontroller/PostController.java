package com.innopolis.eventgo.restcontroller;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/post/{postId}")
    public Post getPost(@PathVariable("postId") long id) throws NotFoundException {
        return postService.getPost(id);
    }

    @PostMapping(value = "/post")
    public Post createPost(@RequestBody Post post) throws NotFoundException {
        return postService.createPost(post);
    }

    @PutMapping(value = "/post/{postId}")
    public Post updatePost(@PathVariable("postId") long id, @RequestBody Post postUpdate) throws NotFoundException {
        return postService.updatePost(id, postUpdate);
    }

    @DeleteMapping(value = "/post/{postId}")
    public Post deletePost(@PathVariable("postId") long id) throws NotFoundException {
        return postService.deletePost(id);
    }
}
