package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.Category;
import com.innopolis.eventgo.db.repository.CategoryDAO;
import com.innopolis.eventgo.dto.CategoryDto;
import com.innopolis.eventgo.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;
    private final ModelMapper modelMapper = new ModelMapper();

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<CategoryDto> findAll() {
        List<Category> categoryList = categoryDAO.findAll();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Category findById(Optional<Long> categoryId) {
        return categoryId.flatMap(categoryDAO::findById).orElse(null);

    }


}
