package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Category;

public interface ICategoryService {
    public List<Category> findAll();

    public Optional<Category> findById(Integer id);

    public boolean exist(Integer id);

    public Optional<Category> insert(Category saveCategory);

    public Optional<Category> update(Integer categoryId, Category saveCategory);
}

