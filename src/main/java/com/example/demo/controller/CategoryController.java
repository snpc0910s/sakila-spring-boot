package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.services.ICategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(categoryService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> findById(@PathVariable("categoryId") Integer categoryId) {
        try {
            Optional<Category> oCategory = categoryService.findById(categoryId);
            if (oCategory.isPresent())
                return ResponseEntity.ok(oCategory.get());
            return new ResponseEntity<>("Category is not exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody Category category) {
        try {
            Optional<Category> oCategory = categoryService.insert(category);
            if (oCategory.isPresent())
                return ResponseEntity.ok(oCategory.get());
            return new ResponseEntity<>("Insert error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> update(@PathVariable("categoryId") Integer categoryId, @RequestBody Category category) {
        try {
            Optional<Category> oCategory = categoryService.update(categoryId, category);
            if (oCategory.isPresent())
                return ResponseEntity.ok(oCategory.get());
            return new ResponseEntity<>("Update error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.BAD_GATEWAY);
        }
    }
}
