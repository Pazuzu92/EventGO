package com.innopolis.eventgo.db.repository;

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

    @Override
    public Role getRole(int code) {
        return (Role) entityManager.createNamedQuery("Role.findByCode", Role.class).setParameter("code", code).getSingleResult();
    }

    @Override
    public List<User> getUser() {
        return (List<User>) entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }
}
