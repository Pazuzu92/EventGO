package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

}
