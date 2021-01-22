package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@Repository
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private EntityManager em;

    @Override
    public Post getPost(Long id) {
        return em.find(Post.class, id);
    }

    @Override
    public Post savePost(Post post) {
        try {
            User user = em.find(User.class, post.getUser().getId());
            post.setUser(user);

            City city = em.find(City.class, post.getPlace().getCity().getId());
            Category category = em.find(Category.class, post.getCategory().getId());

            Place place = post.getPlace();
            place.setCity(city);
            place = em.merge(place);

            post.setPlace(place);
            post.setCategory(category);

            PostStatus postStatus = (PostStatus) em.createNamedQuery(PostStatus.getStatusById).setParameter("id", 1).getSingleResult();
            post.setPostStatus(postStatus);

            em.persist(post);
        } catch (Exception e) {
            return null;
        }
        return post;
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post postOld = em.find(Post.class, id);
        if (postOld == null) return null;
        postOld.setHeader(post.getHeader());
        postOld.setDescription(post.getDescription());
        postOld.setDateFrom(post.getDateFrom());
        postOld.setDateTo(post.getDateTo());
        postOld.setPlace(post.getPlace());
        postOld.setCategory(post.getCategory());
        em.merge(postOld);
        return postOld;
    }

    @Override
    public Post deletePost(Long id) {
        Post post = em.find(Post.class, id);
        if (post != null) em.remove(post);
        return post;
    }

    public City getCityById(Long id) {
        return em.find(City.class, id);
    }

    @Override
    public Place getPlaceById(Long id) {
        return em.find(Place.class, id);
    }

    @Override
    public Category getCategoryById(Long id) {
        return em.find(Category.class, id);
    }
}
