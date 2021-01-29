package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.*;
import com.innopolis.eventgo.db.repository.*;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.logic.EntityLogic;
import com.innopolis.eventgo.mappers.PostMapper;
import com.innopolis.eventgo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public Post getPostEntity(long id) throws NotFoundException {
        Post post = postRepository.getPost(id);
        if (post == null) throw new NotFoundException("Post not found");

        return post;
    }

    public ResponseMessageEntity createPost(PostDto postDto) throws NotFoundException {
        if (!isValidPost(postDto)) throw new NotFoundException("Bad post");

        Post postEntity = postMapper.mapToPost(postDto);
        postEntity.setDateCreate(LocalDateTime.now());
        postRepository.savePost(postEntity);
        return getResponseMessage();
    }

    public ResponseMessageEntity updatePost(long id, PostDto postUpdate) throws NotFoundException {
        Post post = postRepository.getPost(id);
        if (!isValidPost(postUpdate) || post == null) throw new NotFoundException("Post not found");

        Optional<City> city = cityDAO.findById(postUpdate.getCity().getId());
        Category category = postRepository.getCategoryById(postUpdate.getCategory().getId());

        post.setHeader(postUpdate.getHeader());
        post.setDescription(postUpdate.getDescription());
        post.setDateTo(postUpdate.getDateTo());
        post.setDateFrom(postUpdate.getDateFrom());
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
        if (authorId.isPresent())
            user = userDAO.findById(authorId.get());
        if (!user.isPresent()) throw new NotFoundException("User nor Found");
        return EntityLogic.list(Post.class, PostDto.class, postDAO, modelMapper)
                .withPagination(page, size, sort)
                .withFiltering()
                .field("user", user)
                .getResult();
    }

    public List<Post> getGroupsPosts(Optional<Long> authorId) throws NotFoundException {
        Optional<User> user = Optional.empty();
        if (authorId.isPresent())
            user = userDAO.findById(authorId.get());
        if (!user.isPresent()) throw new NotFoundException("User nor Found");
        return postDAO.findByGroups(authorId.get());
    }

    @Override
    public List<Post> findAllOrderById() {
        return postDAO.findAllByOrderById();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postDAO.findById(id);
    }

    @Override
    public void updatePostByStatus(Post post, Long statusId) {

        if (!post.getStatus().getId().equals(statusId)) {
            Optional<PostStatus> postStatus = postStatusDAO.findById(statusId);

            if (postStatus.isPresent()) {
                post.setStatus(postStatus.get());
                postDAO.save(post);
            }
        }
    }

    @Override
    public List<Post> findAllLikeBy(String text, City city, Category category) {
        List<Post> posts;

        if (text.isEmpty()) {
            posts = postDAO.findAll();
        } else {
            posts = postDAO.findAll(containsTextInName(text));
        }

        if (city != null) {
            posts = posts.stream()
                    .filter(post -> post.getCity().equals(city))
                    .collect(Collectors.toList());
        }

        if (category != null) {
            posts = posts.stream()
                    .filter(post -> post.getCategory().equals(category))
                    .collect(Collectors.toList());
        }

        PostStatus byStatus = postStatusDAO.findByStatus(PostStatus.ACTIVE).orElse(null);

        return posts.stream().filter(post -> post.getStatus().equals(byStatus)).collect(Collectors.toList());
    }


    private Specification<Post> containsTextInName(String text) {
        if (!text.contains("%")) {
            text = "%" + text + "%";
        }

        String finalText = text;
        return (root, query, builder) -> builder.or(
                builder.like(root.get("address"), finalText),
                builder.like(root.get("header"), finalText),
                builder.like(root.get("description"), finalText)
        );
    }
    @Override
    public void likePost(Long id) {
        Post post = postRepository.getPost(id);
        Integer likes = post.getLikes().getLikes();
        likes += 1;
        post.getLikes().setLikes(likes);
        postRepository.updatePost(post.getId(), post);
    }

    @Override
    public void follow(Long idPost, Long idUser) {
        postRepository.follow(idPost, idUser);
    }

    @Override
    public int getFollowers(Long idPost) {
        return postRepository.getFollowers(idPost);
    }
}
