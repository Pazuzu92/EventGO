package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.Category;
import com.innopolis.eventgo.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDto> findAll ();

    Category findById(Optional<Long> categoryId);
}
