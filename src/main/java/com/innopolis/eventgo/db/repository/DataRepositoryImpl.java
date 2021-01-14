package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Comment;
import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        return entityManager.merge(comment);
    }

    @Override
    public Role getRole(int code) {
        return (Role) entityManager.createNamedQuery("Role.findByCode", Role.class).setParameter("code", code).getSingleResult();
    }

    @Override
    public List<User> getUser() {
        return (List<User>) entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }

    @Override
    public List<Comment> getCommentsByUserId(Long idUser) {
        return entityManager.createNamedQuery("Comment.findByUserId", Comment.class).setParameter("idUser", idUser).getResultList();
    }
}
