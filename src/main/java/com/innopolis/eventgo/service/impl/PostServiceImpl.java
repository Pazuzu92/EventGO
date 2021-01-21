package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.repository.CityDAO;
import com.innopolis.eventgo.db.repository.PostDAO;
import com.innopolis.eventgo.db.repository.PostRepository;
import com.innopolis.eventgo.exceptions.PostNotFoundException;
import com.innopolis.eventgo.service.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private PostDAO postDAO;
    private CityDAO cityDAO;

    public PostServiceImpl(PostRepository postRepository, PostDAO postDAO, CityDAO cityDAO) {
        this.postRepository = postRepository;
        this.postDAO = postDAO;
        this.cityDAO = cityDAO;
    }

    public Post getPost(long id) throws PostNotFoundException {
        Post post = postRepository.getPost(id);
        if (post == null) throw new PostNotFoundException("Post not found");
        return post;
    }

    public Post createPost(Post post) throws PostNotFoundException {
        if (!isValidPost(post)) throw new PostNotFoundException("Bad post");
        return postRepository.savePost(post);
    }

    public Post updatePost(long id, Post postUpdate) throws PostNotFoundException {
        Post post = postRepository.updatePost(id, postUpdate);
        if (isValidPost(post)) throw new PostNotFoundException("Post not found");
        return post;
    }

    public Post deletePost(long id) throws PostNotFoundException {
        Post post = postRepository.deletePost(id);
        if (post == null) throw new PostNotFoundException("Post not found");
        return post;
    }

    @Override
    public List<Post> getPostByFilter(Optional<String> city, Optional<String> category, Optional<Integer> page,
                                      Optional<Integer> size, String sort) throws PostNotFoundException {

        Pageable pageRequest = PageRequest.of(page.get(), size.get(), Sort.by(sort));

        List<Post> posts;

        if (!city.isPresent() && !category.isPresent()) {
            posts = postDAO.findAll(pageRequest).stream().collect(Collectors.toList());
        } else if (city.isPresent() && category.isPresent()) {
            posts = postDAO.findPostsByCityAndCategory(city.get(), category.get(), pageRequest);
        } else if (!city.isPresent()) {
            posts = postDAO.findPByCategory(category.get(), pageRequest);
        } else {
            posts = postDAO.findByCity(city.get(), pageRequest);
        }

        if (posts.isEmpty()) throw new PostNotFoundException("Posts not found");

        return posts;
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
