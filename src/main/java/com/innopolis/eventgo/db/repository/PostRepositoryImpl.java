package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private EntityManager em;

    @Override
    public Post getPost(long id) {
        return em.find(Post.class, id);
    }

    @Transactional
    @Override
    public Post savePost(Post post) {
        try {
            em.persist(post);
        } catch (EntityExistsException e) {
            return null;
        }
        return post;
    }

    @Transactional
    @Override
    public Post updatePost(long id, Post post) {
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

    @Transactional
    @Override
    public Post deletePost(long id) {
        Post post = em.find(Post.class, id);
        if (post != null) em.remove(post);
        return post;
    }
}
