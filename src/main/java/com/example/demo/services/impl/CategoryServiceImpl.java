package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.services.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Category> findById(Integer id) {
        return categoryRepo.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exist(Integer id) {
        return categoryRepo.existsById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Category> insert(Category saveCategory) {
        Category category = new Category();
        category.setCategoryId(null);
        category.setName(saveCategory.getName());
        category.setLastUpdate(saveCategory.getLastUpdate());
        Category o = categoryRepo.save(category);
        return categoryRepo.findById(o.getCategoryId());
    }

    @Transactional(readOnly = false)
    @Override
    public Optional<Category> update(Integer categoryId, Category saveCategory) {
        saveCategory.setCategoryId(categoryId);
        Optional<Category> categoryOptional = categoryRepo.findById(saveCategory.getCategoryId());
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setName(saveCategory.getName());
            category.setLastUpdate(saveCategory.getLastUpdate());
            Category o = categoryRepo.save(category);
            return categoryRepo.findById(o.getCategoryId());
        }
        return Optional.empty();
    }
}

