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

        Post post1 = new Post();
        post1.setCategory(category1);
        post1.setHeader("Fun");
        post1.setDescription("Test post");
        post1.setLike(24);
        post1.setDislike(2);
        post1.setPlace(place1);
        post1.setPostStatus(postStatus1);
        post1.setUser(user1);
        post1.setDate_create(LocalDateTime.now());
        post1.setDateFrom(LocalDateTime.of(2021, 5, 15, 15, 0));
        post1.setDateTo(LocalDateTime.of(2022, 2, 1, 10, 0));

        Post post2 = new Post();
        post2.setCategory(category2);
        post2.setHeader("Drive");
        post2.setDescription("Test post");
        post2.setLike(27);
        post2.setDislike(7);
        post2.setPlace(place2);
        post2.setPostStatus(postStatus1);
        post2.setUser(user1);
        post2.setDate_create(LocalDateTime.now());
        post2.setDateFrom(LocalDateTime.of(2021, 5, 15, 15, 0));
        post2.setDateTo(LocalDateTime.of(2022, 2, 1, 10, 0));

        Post post3 = new Post();
        post3.setCategory(category3);
        post3.setHeader("Dance");
        post3.setDescription("Test post");
        post3.setLike(274);
        post3.setDislike(73);
        post3.setPlace(place3);
        post3.setPostStatus(postStatus1);
        post3.setUser(user1);
        post3.setDate_create(LocalDateTime.now());
        post3.setDateFrom(LocalDateTime.of(2021, 5, 15, 15, 0));
        post3.setDateTo(LocalDateTime.of(2022, 2, 1, 10, 0));

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

        entityManager.persist(postStatus1);
        entityManager.persist(postStatus2);
        entityManager.persist(postStatus3);
        entityManager.persist(postStatus4);
        entityManager.persist(postStatus5);

        entityManager.persist(post1);
        entityManager.persist(post2);
        entityManager.persist(post3);

        entityManager.persist(comment1);
        entityManager.persist(comment2);
    }
}
