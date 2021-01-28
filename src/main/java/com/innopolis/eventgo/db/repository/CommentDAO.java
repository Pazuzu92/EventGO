package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Comment;
import com.innopolis.eventgo.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentDAO extends JpaRepository<Comment, Long> {
     Optional<List<Comment>> findAllByUser(User user);
}
