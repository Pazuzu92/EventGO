package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Group;
import com.innopolis.eventgo.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupDAO extends JpaRepository<Group, Long> {
    Optional<List<Group>> findAllByUser(User user);
}
