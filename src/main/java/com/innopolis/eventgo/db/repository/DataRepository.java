package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.entity.User;

import java.util.List;

public interface DataRepository {
    void saveRole(Role role);
    void saveUser(User user);

    Role getRole(int code);
    List<User> getUser();
}
