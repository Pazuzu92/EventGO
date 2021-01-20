package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.User;

public interface UserRepository {
    User getUser(long id);
    User updateName(long id, User user);
}
