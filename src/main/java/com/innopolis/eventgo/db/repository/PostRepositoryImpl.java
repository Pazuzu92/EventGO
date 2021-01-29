package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private EntityManager em;

    @Override
    public Post getPost(Long id) {
        Post post = em.find(Post.class, id);
        return post;
    }

    @Override
    public void savePost(Post post) {
        User user = em.find(User.class, post.getUser().getId());

        Likes likes = new Likes();
        likes.setLikes(0);
        City city = em.find(City.class, post.getCity().getId());
        Category category = em.find(Category.class, post.getCategory().getId());
        PostStatus postStatus = (PostStatus) em.createNamedQuery(PostStatus.getStatusById).setParameter("id", 1).getSingleResult();

        post.setUser(user);
        post.setCity(city);
        post.setCategory(category);
        post.setStatus(postStatus);
        post.setLikes(likes);

        em.persist(post);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post postOld = em.find(Post.class, id);
        if (postOld == null) return null;
        postOld.setHeader(post.getHeader());
        postOld.setDescription(post.getDescription());
        postOld.setDateFrom(post.getDateFrom());
        postOld.setDateTo(post.getDateTo());
        postOld.setCity(post.getCity());
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

    @Override
    public void follow(Long idPost, Long idUser) {
        List<Group> groups = em.createNamedQuery(Group.findFollowers).setParameter("id", idPost).getResultList();
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getUser().getId() == idUser) return;
        }
        Group group = new Group();
        group.setPost(em.find(Post.class, idPost));
        group.setUser(em.find(User.class, idUser));
        em.persist(group);
    }

    @Override
    public int getFollowers(Long idPost) {
        List<Group> groups = em.createNamedQuery(Group.findFollowers).setParameter("id", idPost).getResultList();
        return groups.size();
    }

    public City saveCity(City city) {
        em.persist(city);
        return em.find(City.class, city.getId());
    }

    public City getCityById(Long id) {
        return em.find(City.class, id);
    }

    public void saveCategory(Category category) {
        em.persist(category);
    }

    public Category getCategoryById(Long id) {
        return em.find(Category.class, id);
    }
}
