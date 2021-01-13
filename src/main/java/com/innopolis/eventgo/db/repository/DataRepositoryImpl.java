package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataRepositoryImpl implements DataRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveRole(Role role) {
        sessionFactory.getCurrentSession().saveOrUpdate(role);
    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public Role getRole(int code) {
        return (Role) sessionFactory.getCurrentSession().getNamedQuery("Role.findByCode").setParameter("code", code);
    }

    @Override
    public List<User> getUser() {
        return (List<User>) sessionFactory.getCurrentSession().getNamedQuery("User.findAll").list();
    }
}
