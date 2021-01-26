package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.*;
import com.innopolis.eventgo.db.repository.*;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.dto.UserDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.logic.EntityLogic;
import com.innopolis.eventgo.mappers.PostMapper;
import com.innopolis.eventgo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
    private final UserDAO userDAO;

    public PostServiceImpl(PostRepository postRepository,
                           PostDAO postDAO,
                           CityDAO cityDAO,
                           PostMapper postMapper,
                           CategoryDAO categoryDAO,
                           PostStatusDAO postStatusDAO,
                           UserDAO userDAO) {
        this.postRepository = postRepository;
        this.postDAO = postDAO;
        this.postMapper = postMapper;
        this.cityDAO = cityDAO;
        this.categoryDAO = categoryDAO;
        this.postStatusDAO = postStatusDAO;
        this.userDAO = userDAO;
    }

    public PostDto getPost(long id) throws NotFoundException {
        Post post = postRepository.getPost(id);
        if (post == null) throw new NotFoundException("Post not found");

        return postMapper.mapToDto(post);
    }

    public ResponseMessageEntity createPost(PostDto postDto) throws NotFoundException {
        if (!isValidPost(postDto)) throw new NotFoundException("Bad post");

        Post postEntity = postMapper.mapToPost(postDto);
        postEntity.setDateCreate(LocalDateTime.now());

        return getResponseMessage();
    }

    public ResponseMessageEntity updatePost(long id, PostDto postUpdate) throws NotFoundException {
        Post post = postRepository.getPost(id);
        if (!isValidPost(postUpdate) || post == null) throw new NotFoundException("Post not found");

        Optional<City> city = cityDAO.findById(postUpdate.getCity().getId());
        Category category = postRepository.getCategoryById(postUpdate.getCategory().getId());

        post.setHeader(postUpdate.getHeader());
        post.setDescription(postUpdate.getDescription());
        post.setDateTo(LocalDateTime.parse(postUpdate.getDateTo(), dateTimeFormatter));
        post.setDateFrom(LocalDateTime.parse(postUpdate.getDateFrom(), dateTimeFormatter));
        post.setCategory(category);
        city.ifPresent(post::setCity);

        postRepository.updatePost(id, post);

        return getResponseMessage();
    }

    public Post deletePost(long id) throws NotFoundException {
        Post post = postRepository.deletePost(id);
        if (post == null) throw new NotFoundException("Post not found");
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
                                          Optional<Integer> postStatusId,
                                          Optional<Integer> page,
                                          Optional<Integer> size,
                                          Optional<String> sort) throws NotFoundException {

        Optional<Category> category = Optional.empty();
        Optional<PostStatus> postStatus = Optional.empty();
        Optional<City> city = Optional.empty();

        if (categoryName.isPresent()) {
            category = categoryDAO.findByNameCategory(categoryName.get());
            if (!category.isPresent()) throw new NotFoundException("Category not found");
        }

        if (postStatusId.isPresent()) {
            postStatus = postStatusDAO.findByStatus(postStatusId.get());
            if (!postStatus.isPresent()) throw new NotFoundException("PostStatuses not found");
        }

        if (cityName.isPresent()) {
            city = cityDAO.findByShortName(cityName.get());
            if (!city.isPresent()) throw new NotFoundException("Cities not found");
        }

        return EntityLogic.list(Post.class, PostDto.class, postDAO, modelMapper)
                .withPagination(page, size, sort)
                .withFiltering()
                .field("category", category)
                .field("status", postStatus)
                .field("city", city)
                .getResult();
    }

    @Override
    public List<PostDto> getPostsByAuthor(Optional<Long> authorId,
                                          Optional<Integer> page,
                                          Optional<Integer> size,
                                          Optional<String> sort) throws NotFoundException {
        Optional<User> user = Optional.empty();
        if(authorId.isPresent())
            user = userDAO.findById(authorId.get());
            if(!user.isPresent()) throw new NotFoundException("User nor Found");
        return EntityLogic.list(Post.class, PostDto.class, postDAO, modelMapper)
                .withPagination(page, size, sort)
                .withFiltering()
                .field("user", user)
                .getResult();
    }
}
