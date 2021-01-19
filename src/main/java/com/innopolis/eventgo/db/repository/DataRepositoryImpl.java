package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DataRepositoryImpl implements DataRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Role saveRole(Role role) {
        return entityManager.merge(role);
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public Role getRole(int code) {
        return (Role) entityManager.createNamedQuery("Role.findByCode", Role.class).setParameter("code", code).getSingleResult();
    }

    @Override
    public List<User> getUser() {
        return (List<User>) entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }

    @Transactional
    @Override
    public String init() {

        Role role = new Role();
        role.setRoleCode(Role.USER);

        User user = new User();
        user.setRole(role);
        user.setName(RandomStringUtils.randomAlphabetic(20));
        user.setLogin(RandomStringUtils.randomAlphabetic(20));
        user.setEmail(RandomStringUtils.randomAlphabetic(20));
        user.setPassword(RandomStringUtils.randomAlphabetic(20));

        Category category = new Category();
        category.setNameCategory(RandomStringUtils.randomAlphabetic(20));

        City city = new City();
        city.setCityName(RandomStringUtils.randomAlphabetic(20));

        Place place = new Place();
        place.setStreet(RandomStringUtils.randomAlphabetic(20));
        place.setHouse("10");
        place.setNumber("1");
        place.setCity(city);

        PostStatus postStatus = new PostStatus();
        postStatus.setStatus(PostStatus.ACTIVE);

        Photo photo = new Photo();
        photo.setImage("RandomStringUtils.random(20)".getBytes());

        Post post = new Post();
        post.setCategory(category);
        post.setDescription(RandomStringUtils.randomAlphabetic(20));
        post.setHeader(RandomStringUtils.randomAlphabetic(20));
        post.setLike(24);
        post.setDislike(2);
        post.setPlace(place);
        post.setPostStatus(postStatus);
        post.setUser(user);
        post.setDateTo(LocalDate.now());

        entityManager.persist(role);
        entityManager.persist(user);
        entityManager.persist(category);
        entityManager.persist(city);
        entityManager.persist(place);
        entityManager.persist(postStatus);
        entityManager.persist(photo);
        entityManager.persist(post);
        return "ok";
    }
}
