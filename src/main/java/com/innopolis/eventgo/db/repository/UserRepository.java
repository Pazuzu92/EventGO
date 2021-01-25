package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.User;

import java.util.Optional;

public interface UserRepository {
    User getUser(Long id);
    Optional<User> updateName(long id, User user);
    User getUser(String name);
}
