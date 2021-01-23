package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.Category;
import com.innopolis.eventgo.db.repository.CategoryDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> findAll (){
        return categoryDAO.findAll();
    }
}
