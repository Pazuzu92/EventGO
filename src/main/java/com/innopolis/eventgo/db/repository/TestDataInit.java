package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Transactional
@Repository
public class TestDataInit {

    @Autowired
    private EntityManager entityManager;

    public void init() {
        Role roleUser = new Role();
        roleUser.setRoleCode(Role.USER);

        Role roleModerator = new Role();
        roleModerator.setRoleCode(Role.MODERATOR);

        Role roleAdmin = new Role();
        roleAdmin.setRoleCode(Role.ADMIN);

        User user1 = new User();
        user1.setRole(roleUser);
        user1.setName(RandomStringUtils.randomAlphabetic(20));
        user1.setLogin(RandomStringUtils.randomAlphabetic(20));
        user1.setEmail(RandomStringUtils.randomAlphabetic(20));
        user1.setPassword(RandomStringUtils.randomAlphabetic(20));

        User user2 = new User();
        user2.setRole(roleModerator);
        user2.setName(RandomStringUtils.randomAlphabetic(20));
        user2.setLogin(RandomStringUtils.randomAlphabetic(20));
        user2.setEmail(RandomStringUtils.randomAlphabetic(20));
        user2.setPassword(RandomStringUtils.randomAlphabetic(20));

        User user3 = new User();
        user3.setRole(roleAdmin);
        user3.setName(RandomStringUtils.randomAlphabetic(20));
        user3.setLogin(RandomStringUtils.randomAlphabetic(20));
        user3.setEmail(RandomStringUtils.randomAlphabetic(20));
        user3.setPassword(RandomStringUtils.randomAlphabetic(20));

        Category category1 = new Category();
        category1.setNameCategory("Спорт");

        Category category2 = new Category();
        category2.setNameCategory("Театр");

        Category category3 = new Category();
        category3.setNameCategory("Музыка");

        Category category4 = new Category();
        category4.setNameCategory("Кино");

        Category category5 = new Category();
        category5.setNameCategory("Детям");

        Category category6 = new Category();
        category6.setNameCategory("Кафе");

        Category category7 = new Category();
        category7.setNameCategory("Обучение");

        Category category8 = new Category();
        category8.setNameCategory("Выставки");

        City city1 = new City();
        city1.setCityName("Москва");

        City city2 = new City();
        city2.setCityName("Санкт-Петербург");

        City city3 = new City();
        city3.setCityName("Казань");

        Place place1 = new Place();
        place1.setStreet("Wall Street");
        place1.setHouse("10");
        place1.setNumber("1");
        place1.setCity(city1);

        Place place2 = new Place();
        place2.setStreet("Nevsky");
        place2.setHouse("12");
        place2.setNumber("3");
        place2.setCity(city2);

        Place place3 = new Place();
        place3.setStreet("Yashlek");
        place3.setHouse("15");
        place3.setNumber("10");
        place3.setCity(city3);

        Place place4 = new Place();
        place4.setStreet("Vosstania");
        place4.setHouse("45");
        place4.setNumber("2");
        place4.setCity(city1);

        PostStatus postStatus1 = new PostStatus();
        postStatus1.setStatus(PostStatus.ACTIVE);

        PostStatus postStatus2 = new PostStatus();
        postStatus2.setStatus(PostStatus.ARCHIVED);

        PostStatus postStatus3 = new PostStatus();
        postStatus3.setStatus(PostStatus.DELETED);

        PostStatus postStatus4 = new PostStatus();
        postStatus4.setStatus(PostStatus.MODERATED);

        PostStatus postStatus5 = new PostStatus();
        postStatus5.setStatus(PostStatus.REJECTED);

        Likes likes1 = new Likes();
        likes1.setLikes(100);

        Likes likes2 = new Likes();
        likes2.setLikes(200);

        Likes likes3 = new Likes();
        likes3.setLikes(300);

        Likes likes4 = new Likes();
        likes4.setLikes(400);

        Dislikes dislikes1 = new Dislikes();
        dislikes1.setDislikes(20);

        Dislikes dislikes2 = new Dislikes();
        dislikes2.setDislikes(30);

        Dislikes dislikes3 = new Dislikes();
        dislikes3.setDislikes(40);

        Dislikes dislikes4 = new Dislikes();
        dislikes4.setDislikes(50);

        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();
        Group group4 = new Group();

        Post post1 = new Post();
        Post post2 = new Post();
        Post post3 = new Post();
        Post post4 = new Post();

        group1.setUser(user1);
        group1.setPost(post1);

        group2.setUser(user2);
        group2.setPost(post2);

        group3.setUser(user3);
        group3.setPost(post3);

        group4.setUser(user1);
        group4.setPost(post4);

        post1.setCategory(category1);
        post1.setHeader("Fun");
        post1.setDescription("Test post");
        post1.setLikes(likes1);
        post1.setDislikes(dislikes1);
        post1.getGroups().add(group1);
        post1.setPlace(place1);
        post1.setPostStatus(postStatus1);
        post1.setUser(user1);
        post1.setDate_create(LocalDateTime.now());
        post1.setDateFrom(LocalDateTime.of(2021, 5, 15, 15, 0));
        post1.setDateTo(LocalDateTime.of(2022, 2, 1, 10, 0));

        post2.setCategory(category2);
        post2.setHeader("Drive");
        post2.setDescription("Test post");
        post2.setLikes(likes2);
        post2.setDislikes(dislikes2);
        post2.getGroups().add(group2);
        post2.setPlace(place2);
        post2.setPostStatus(postStatus1);
        post2.setUser(user1);
        post2.setDate_create(LocalDateTime.now());
        post2.setDateFrom(LocalDateTime.of(2021, 5, 15, 15, 0));
        post2.setDateTo(LocalDateTime.of(2022, 2, 1, 10, 0));

        post3.setCategory(category3);
        post3.setHeader("Dance");
        post3.setDescription("Test post");
        post3.setLikes(likes3);
        post3.setDislikes(dislikes3);
        post3.getGroups().add(group3);
        post3.setPlace(place3);
        post3.setPostStatus(postStatus1);
        post3.setUser(user1);
        post3.setDate_create(LocalDateTime.now());
        post3.setDateFrom(LocalDateTime.of(2021, 5, 15, 15, 0));
        post3.setDateTo(LocalDateTime.of(2022, 2, 1, 10, 0));

        post4.setCategory(category1);
        post4.setHeader("Car");
        post4.setDescription("Test post");
        post4.setLikes(likes4);
        post4.setDislikes(dislikes4);
        post4.getGroups().add(group4);
        post4.setPlace(place4);
        post4.setPostStatus(postStatus1);
        post4.setUser(user1);
        post4.setDate_create(LocalDateTime.now());
        post4.setDateFrom(LocalDateTime.of(2021, 5, 15, 15, 0));
        post4.setDateTo(LocalDateTime.of(2022, 2, 1, 10, 0));

        Comment comment1 = new Comment();
        comment1.setText("this is comment");
        comment1.setPost(post1);
        comment1.setUser(user1);

        Comment comment2 = new Comment();
        comment2.setText("this is comment 2");
        comment2.setPost(post1);
        comment2.setUser(user2);

        entityManager.persist(roleUser);
        entityManager.persist(roleModerator);
        entityManager.persist(roleAdmin);

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);

        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.persist(category3);
        entityManager.persist(category4);
        entityManager.persist(category5);
        entityManager.persist(category6);
        entityManager.persist(category7);
        entityManager.persist(category8);

        entityManager.persist(city1);
        entityManager.persist(city2);
        entityManager.persist(city3);

        entityManager.persist(place1);
        entityManager.persist(place2);
        entityManager.persist(place3);
        entityManager.persist(place4);

        entityManager.persist(postStatus1);
        entityManager.persist(postStatus2);
        entityManager.persist(postStatus3);
        entityManager.persist(postStatus4);
        entityManager.persist(postStatus5);

        entityManager.persist(post1);
        entityManager.persist(post2);
        entityManager.persist(post3);
        entityManager.persist(post4);

        entityManager.persist(comment1);
        entityManager.persist(comment2);
    }
}
