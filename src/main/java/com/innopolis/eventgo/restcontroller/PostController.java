package com.innopolis.eventgo.restcontroller;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.entity.ResponseMessageEntity;
import com.innopolis.eventgo.db.repository.TestDataInit;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private final TestDataInit testDataInit;

    public PostController(PostService postService, TestDataInit testDataInit) {
        this.postService = postService;
        this.testDataInit = testDataInit;
    }

    @GetMapping(value = "/post/{postId}")
    public PostDto getPost(@PathVariable("postId") long id) throws NotFoundException {
        return postService.getPost(id);
    }

    @PostMapping(value = "/post")
    public ResponseMessageEntity createPost(@RequestBody PostDto post) throws NotFoundException {
        return postService.createPost(post);
    }

    @PutMapping(value = "/post/{postId}")
    public ResponseMessageEntity updatePost(@PathVariable("postId") Long id, @RequestBody PostDto postUpdate) throws NotFoundException {
        return postService.updatePost(id, postUpdate);
    }

    @DeleteMapping(value = "/post/{postId}")
    public Post deletePost(@PathVariable("postId") long id) throws NotFoundException {
        return postService.deletePost(id);
    }

    @GetMapping(value = "/init")
    public void init() {
        testDataInit.init();
    }

    @GetMapping("/post")
    public List<PostDto> getPostsByFilter(@RequestParam(required = false) Optional<String> city,
                                          @RequestParam(required = false) Optional<String> category,
                                          @RequestParam(required = false) Optional<Long> statusId,
                                          @RequestParam(required = false) Optional<Integer> page,
                                          @RequestParam(required = false) Optional<Integer> size,
                                          @RequestParam(required = false) Optional<String> sort) throws NotFoundException {
        return postService.getPostsByFilter(city, category, statusId, page, size, sort);
    }
}
