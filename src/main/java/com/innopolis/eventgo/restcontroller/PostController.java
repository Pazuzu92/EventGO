package com.innopolis.eventgo.restcontroller;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.entity.ResponseMessageEntity;
import com.innopolis.eventgo.db.repository.TestDataInit;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.PostNotFoundException;
import com.innopolis.eventgo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class PostController {

    @Autowired
    private TestDataInit testDataInit;

    @Autowired
    private PostService postService;

    @GetMapping(value = "/post/{postId}")
    public PostDto getPost(@PathVariable("postId") long id) throws PostNotFoundException {
        return postService.getPost(id);
    }

    @PostMapping(value = "/post")
    public ResponseMessageEntity createPost(@RequestBody PostDto post) throws PostNotFoundException {
        return postService.createPost(post);
    }

    @PutMapping(value = "/post/{postId}")
    public ResponseMessageEntity updatePost(@PathVariable("postId") Long id, @RequestBody PostDto postUpdate) throws PostNotFoundException {
        return postService.updatePost(id, postUpdate);
    }

    @DeleteMapping(value = "/post/{postId}")
    public Post deletePost(@PathVariable("postId") long id) throws PostNotFoundException {
        return postService.deletePost(id);
    }

    @GetMapping(value = "/init")
    public void init() {
        testDataInit.init();
    }
}
