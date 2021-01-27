package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private EntityManager em;

    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUser(String name) {
        return (User) em.createNamedQuery(User.findUserByName).setParameter("login", name).getSingleResult();
    }

    @Transactional
    @Override
    public Optional<User> updateName(long id, User user) {
        User userOld = em.find(User.class, id);
        if (userOld == null) return Optional.empty();
        userOld.setName(user.getName());
        em.merge(userOld);
        return Optional.of(userOld);
    }
}
