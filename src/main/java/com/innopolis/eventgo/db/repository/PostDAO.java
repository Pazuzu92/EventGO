package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDAO extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.place.city.cityName = :city and p.category.nameCategory = :category")
    List<Post> findPostsByCityAndCategory(@Param("city") String city, @Param("category") String category, Pageable page);

    @Query("select p from Post p where p.place.city.cityName = :city")
    List<Post> findByCity(@Param("city") String city, Pageable page);

    @Query("select p from Post p where p.category.nameCategory = :category")
    List<Post> findPByCategory(@Param("category") String category, Pageable page);
}
