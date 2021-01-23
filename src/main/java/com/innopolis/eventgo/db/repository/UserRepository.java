package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.User;

public interface UserRepository {
    User getUser(Long id);
}
