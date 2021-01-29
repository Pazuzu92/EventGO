package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByLogin(String login);
    int countUserByRole(Role role);
    List<User> findAllByOrderById();
}
