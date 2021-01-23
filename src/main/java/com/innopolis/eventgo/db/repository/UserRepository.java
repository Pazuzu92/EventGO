package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository {
    User getUser(long id);
    User updateName(long id, User user);
}
