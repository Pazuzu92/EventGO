package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private EntityManager em;

    @Override
    public User getUser(long id) {
        return em.find(User.class, id);
    }

    @Override
    public User updateName(long id, User user) {
        User userOld = em.find(User.class, id);
        if (userOld == null) return null;
        userOld.setName(user.getName());
        em.merge(userOld);
        return userOld;
    }


}
