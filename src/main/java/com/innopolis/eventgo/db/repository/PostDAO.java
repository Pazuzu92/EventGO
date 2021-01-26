package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostDAO extends JpaRepository<Post, Long> {
}
