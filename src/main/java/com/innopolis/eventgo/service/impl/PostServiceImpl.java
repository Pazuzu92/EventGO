package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.*;
import com.innopolis.eventgo.db.repository.*;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.PostNotFoundException;
import com.innopolis.eventgo.logic.EntityLogic;
import com.innopolis.eventgo.mappers.PostMapper;
import com.innopolis.eventgo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH.mm");
    private final PostRepository postRepository;
    private final PostDAO postDAO;
    private final PostMapper postMapper;
    private final ModelMapper modelMapper = new ModelMapper();
    private final CityDAO cityDAO;
    private final CategoryDAO categoryDAO;
    private final PostStatusDAO postStatusDAO;

    public PostServiceImpl(PostRepository postRepository,
                           PostDAO postDAO,
                           CityDAO cityDAO,
                           PostMapper postMapper,
                           CategoryDAO categoryDAO,
                           PostStatusDAO postStatusDAO) {
        this.postRepository = postRepository;
        this.postDAO = postDAO;
        this.postMapper = postMapper;
        this.cityDAO = cityDAO;
        this.categoryDAO = categoryDAO;
        this.postStatusDAO = postStatusDAO;
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
    public List<PostDto> getPostsByFilter(Optional<String> cityName,
                                          Optional<String> categoryName,
                                          Optional<Long> postStatusId,
                                          Optional<Integer> page,
                                          Optional<Integer> size,
                                          Optional<String> sort) throws PostNotFoundException {

        Optional<Category> category = Optional.empty();
        Optional<PostStatus> postStatus = Optional.empty();

        if (categoryName.isPresent()) {
            category = categoryDAO.findByNameCategory(categoryName.get());
            if (!category.isPresent()) throw new PostNotFoundException("Posts not found");
        }

        if (postStatusId.isPresent()) {
            postStatus = postStatusDAO.findById(postStatusId.get());
            if (!postStatus.isPresent()) throw new PostNotFoundException("Posts not found");
        }

        List<PostDto> postDtoList = EntityLogic.list(Post.class, PostDto.class, postDAO, modelMapper)
                .withPagination(page, size, sort)
                .withFiltering()
                .field("category", category)
                .field("status", postStatus)
                .getResult();

        if (cityName.isPresent()) {
            Optional<City> finalCity = cityDAO.findByCityName(cityName.get());

            if (finalCity.isPresent()) {
                postDtoList = postDtoList.stream()
                        .filter(postDto -> Objects.equals(postDto.getPlace().getCity().getId(), finalCity.get().getId()))
                        .collect(Collectors.toList());
            } else throw new PostNotFoundException("Posts not found");
        }

        return postDtoList;
    }
}
