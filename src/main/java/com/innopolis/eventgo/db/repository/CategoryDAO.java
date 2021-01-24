package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {
    Optional<Category> findByNameCategory(String NameCategory);
}
