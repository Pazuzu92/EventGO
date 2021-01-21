package com.innopolis.eventgo.restcontroller;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.exceptions.PostNotFoundException;
import com.innopolis.eventgo.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{postId}")
    public Post getPost(@PathVariable("postId") long id) throws PostNotFoundException {
        return postService.getPost(id);
    }

    @PostMapping("/post")
    public Post createPost(@RequestBody Post post) throws PostNotFoundException {
        return postService.createPost(post);
    }

    @PutMapping("/post/{postId}")
    public Post updatePost(@PathVariable("postId") long id, @RequestBody Post postUpdate) throws PostNotFoundException {
        return postService.updatePost(id, postUpdate);
    }

    @DeleteMapping("/post/{postId}")
    public Post deletePost(@PathVariable("postId") long id) throws PostNotFoundException {
        return postService.deletePost(id);
    }

    @GetMapping("/post")
    public List<Post> getPostsByCityAndCategory(@RequestParam(required = false) Optional<String> city,
                                                @RequestParam(required = false) Optional<String> category,
                                                @RequestParam(required = false, defaultValue = "0") Optional<Integer> page,
                                                @RequestParam(required = false, defaultValue = "10") Optional<Integer> size,
                                                @RequestParam(required = false, defaultValue = "like") String sort) throws PostNotFoundException{
        return postService.getPostByFilter(city, category, page, size, sort);
    }
}
