package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.Category;
import com.innopolis.eventgo.db.entity.Place;
import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.entity.ResponseMessageEntity;
import com.innopolis.eventgo.db.repository.CityDAO;
import com.innopolis.eventgo.db.repository.PostDAO;
import com.innopolis.eventgo.db.repository.PostRepository;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.PostNotFoundException;
import com.innopolis.eventgo.mappers.PostMapper;
import com.innopolis.eventgo.service.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH.mm");
    private final PostRepository postRepository;
    private final PostDAO postDAO;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostDAO postDAO, CityDAO cityDAO, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postDAO = postDAO;
        this.postMapper = postMapper;
    }

    public PostDto getPost(long id) throws PostNotFoundException {
        Post post = postRepository.getPost(id);
        if (post == null) throw new PostNotFoundException("Post not found");

        return postMapper.mapToDto(post);
    }

    public ResponseMessageEntity createPost(PostDto postDto) throws PostNotFoundException {
        if (!isValidPost(postDto)) throw new PostNotFoundException("Bad post");

        Post postEntity = postMapper.mapToPost(postDto);
        postEntity.setDate_create(LocalDateTime.now());

        return getResponseMessage();
    }

    public ResponseMessageEntity updatePost(long id, PostDto postUpdate) throws PostNotFoundException {
        Post post = postRepository.getPost(id);
        if (!isValidPost(postUpdate) || post == null) throw new PostNotFoundException("Post not found");

        Place place = postRepository.getPlaceById(postUpdate.getPlace().getId());
        Category category = postRepository.getCategoryById(postUpdate.getCategory().getId());

        post.setHeader(postUpdate.getHeader());
        post.setDescription(postUpdate.getDescription());
        post.setDateTo(LocalDateTime.parse(postUpdate.getDateTo(), dateTimeFormatter));
        post.setDateFrom(LocalDateTime.parse(postUpdate.getDateFrom(), dateTimeFormatter));
        post.setCategory(category);
        post.setPlace(place);

        postRepository.updatePost(id, post);

        return getResponseMessage();
    }

    public Post deletePost(long id) throws PostNotFoundException {
        Post post = postRepository.deletePost(id);
        if (post == null) throw new PostNotFoundException("Post not found");
        return post;
    }

    private boolean isValidPost(PostDto post) {

        return true;
    }

    private ResponseMessageEntity getResponseMessage() {
        ResponseMessageEntity rme = new ResponseMessageEntity();
        rme.setCode(200);
        rme.setMessage("Ok");
        return rme;
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
}
