package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Group;
import com.innopolis.eventgo.db.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostDAO extends JpaRepository<Post, Long> {

    @Query(value = "select * from post as p \n" +
            "join groups as g on g.id_post = p.id\n" +
            "where g.id_user = ?1",
            nativeQuery = true)
    List<Post> findByGroups(Long userId);
}
