package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Long> {
    Category findByNameCategory(String NameCategory);
}
