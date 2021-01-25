package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostStatusDAO extends JpaRepository<PostStatus, Long> {
    Optional<PostStatus> findByStatus(int status);
}
